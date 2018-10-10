package com.point18.slg2d.world.module.walk

import com.point18.slg2d.common.baseg.IHeartHandler
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Mass
import com.point18.slg2d.world.common.*
import com.point18.slg2d.world.module.walk.walkComm.createWalkOnMap
import com.point18.slg2d.world.module.walk.walkComm.goHome
import java.util.*

class HeartMass : IHeartHandler<AreaCache> {

    override fun handleHeart(cache: AreaCache) {
        val allMass = cache.massCache.findAllOverMass()

        for (mass in allMass) {
            when (mass.massState) {
                Mass ->
                    dealMassOver(cache, mass)
                Wait ->
                    dealWaitOver(cache, mass)
                Run ->
                    dealRunOver(cache, mass)
            }
        }
    }

    private fun dealMassOver(areaCache: AreaCache, mass: Mass) {
        //处理集结结束，判断是否全部抵达，设置集结状态
        if (mass.goTime > getNowTime()) {
            //集结时间未到
            return
        }
        if (mass.fightType == WalkRelic) {
            //攻击遗迹有人数限制
            if (mass.memberInfoList.count() < pcs.basicProtoCache.massRelicMinMember + 1) {
                areaCache.massCache.deleteMass(mass)
                noticeMassGroup(areaCache, Del, mass)
                //集结解散
                val mailInfo = MailInfo(
                    TEXT_READ_LAN,
                    SYSTEM_MAIL,
                    LinkedList(),
                    MASS_DISMISS_CONTENT,
                    LinkedList()
                )
                for (member in mass.memberInfoList) {
                    sendMail(areaCache, member.playerId, mailInfo)

                    //部队回城
                    val group = areaCache.walkForceGroupCache.findWalkForceGroupById(member.groupId)
                    if (group != null) {
                        goHome(areaCache, mass.startX, mass.startY, group)
                    }
                }
                return
            }
        }

        //进入等待状态
        mass.massState = Wait
        noticeMassGroup(areaCache, Update, mass)
        if (mass.fightType == WalkFightPlayer || mass.fightType == WalkOccupyWonder) {
            noticeReinforceMassGroup(areaCache, Update, mass)
        }
    }

    private fun dealWaitOver(areaCache: AreaCache, mass: Mass) {
        val nowTime = getNowTime()
        var allArrive = true
        for (member in mass.memberInfoList) {
            if (member.arriveTime < nowTime) {
                continue
            }
            val group = areaCache.walkForceGroupCache.findWalkForceGroupById(member.groupId)
            if (group == null || group.nowX != mass.startX || group.nowY != mass.startY) {
                continue
            }
            allArrive = false
            break
        }
        if (!allArrive) {
            //未全部抵达
            return
        }

        //处理等待结束，发出集结线，设置集结状态
        val newGroup = areaCache.walkForceGroupCache.createWalkForceGroup(
            mass.mainPlayerId,
            0,
            Running,
            mass.fightType,
            mass.startX,
            mass.startY,
            mass.id
        )
        val soliderMap = hashMapOf<Int, Int>()
        for (member in mass.memberInfoList) {
            //成员行军组删除通知
            val memberGroup = areaCache.walkForceGroupCache.findWalkForceGroupById(member.groupId)
            if (memberGroup == null) {
                normalLog.error("集结中的行军组不存在:${member.groupId}")
                continue
            }
            noticeSelfWalkForceGroup(areaCache, Del, memberGroup)

            //删除旧行军组
            areaCache.walkForceGroupCache.delWalkForceGroup(memberGroup)

            //旧行军组部队绑定到新的行军组中
            val forces = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(member.groupId)
            for (force in forces) {
                areaCache.walkForceCache.updateWalkForceGroupId(force, newGroup.id)
                for ((id, num) in force.soliderMap) {
                    if (num <= 0) {
                        continue
                    }
                    soliderMap[id] = soliderMap[id] ?: 0 + num
                }
            }

            member.groupId = newGroup.id
        }
        if (soliderMap.count() == 0) {
            //处理行军结束，删除集结
            areaCache.massCache.deleteMass(mass)
            noticeMassGroup(areaCache, Del, mass)
            if (mass.fightType == WalkFightPlayer || mass.fightType == WalkOccupyWonder) {
                noticeReinforceMassGroup(areaCache, Del, mass)
            }
            return
        }
        // 创建行军线
        val startX = mass.startX
        val startY = mass.startY
        val gotoX = mass.gotoX
        val gotoY = mass.gotoY
        val walkTimeRt =
            calWalkTime(
                areaCache,
                mass.mainPlayerId,
                mass.fightType,
                soliderMap,
                startX,
                startY,
                gotoX,
                gotoY,
                false,
                true
            )
        val arriveTime = getNowTime() + walkTimeRt.walkTime * 1000
        createWalkOnMap(
            areaCache, mass.fightType, arriveTime, walkTimeRt.walkSpeed,
            startX, startY, gotoX, gotoY, getNowTime(), newGroup.id, 0
        )

        // 设置集结状态
        areaCache.massCache.massRun(mass, newGroup.id, arriveTime)

        // 通知新增行军组信息
        noticeSelfWalkForceGroup(areaCache, Add, newGroup)

        noticeMassGroup(areaCache, Update, mass)
        if (mass.fightType == WalkFightPlayer || mass.fightType == WalkOccupyWonder) {
            noticeReinforceMassGroup(areaCache, Update, mass)
        }

        //刷新预警
        updateWarnWithPos(areaCache, gotoX, gotoY)
    }

    private fun dealRunOver(areaCache: AreaCache, mass: Mass) {
        if (mass.arriveTime > getNowTime()) {
            //行军未抵达
            return
        }
        //处理行军结束，删除集结
        areaCache.massCache.deleteMass(mass)
        noticeMassGroup(areaCache, Del, mass)
        if (mass.fightType == WalkFightPlayer || mass.fightType == WalkOccupyWonder) {
            noticeReinforceMassGroup(areaCache, Del, mass)
        }
    }
}


