package com.point18.slg2d.world.module.walk.endWalkStrategy

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.area4data.WalkForce
import com.point18.slg2d.world.area4data.WalkForceGroup
import com.point18.slg2d.world.common.addResToHome
import com.point18.slg2d.world.common.noticeSelfWalkForceGroup
import com.point18.slg2d.world.common.targetAddVal
import com.point18.slg2d.world.event.FarmResEvent
import com.point18.slg2d.world.module.fightdomain.RewardInfoForReport
import com.point18.slg2d.world.module.fightdomain.createFightDataByForceGroup
import com.point18.slg2d.world.module.report.ReportInfo
import com.point18.slg2d.world.module.walk.ICellDeal
import com.point18.slg2d.world.module.walk.walkComm.removeForce
import com.point18.slg2d.world.wpm
import java.util.Arrays.asList

class DealGoHomeOnCastleCell : ICellDeal {

    override fun walkFinishCheck(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk): Int {
        return ResultCode.SUCCESS.code
    }

    override fun walkFinishDeal(areaCache: AreaCache, group: WalkForceGroup, walkLineInfo: Walk) {
        // 通知删除行军组
        noticeSelfWalkForceGroup(areaCache, Del, group)

        // 推送app消息
        val action = when (group.gotoRunType) {
            WalkFarm -> FARM_GROUP_BACK
            WalkTransport -> TRANSPORT_GROUP_BACK
            WalkJoinMass -> MASS_GROUP_BACK
            WalkReinforce -> REINFORCE_GROUP_BACK
            WalkStation -> STATION_GROUP_BACK
            WalkReinforceWonder -> WONDER_REINFORCE_GROUP_BACK // 器官增援部队返回 包括被遣返和打回来
            else -> {
                0
            }
        }

        areaCache.pushAppNotice(
            group.mainPlayerId,
            action,
            0
        )

        val forces = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(walkLineInfo.walkForceGroupId)
        for (force in forces) {
            giveBackForceRes(areaCache, force, group, walkLineInfo)

            removeForce(areaCache, force)
        }

        areaCache.walkForceGroupCache.delWalkForceGroup(group)
    }

    //返还部队资源
    private fun giveBackForceRes(areaCache: AreaCache, force: WalkForce, group: WalkForceGroup, walk: Walk) {
        val player = areaCache.playerCache.findPlayerById(force.playerId)
        if (player == null) {
            normalLog.error("找不到部队的玩家信息，部队Id:%d", force.id)
            return
        }

        val resVos = force.resVo
        var totalResNum = 0L
        for (resVo in resVos) {
            totalResNum += resVo.num
        }

        if (totalResNum == 0L) {
            return
        }

        //解析资源来源
        val strList = force.resFromInfo.split("_")
        if (strList.count() < 1) {
            return
        }
        val resFrom = Integer.parseInt(strList[0])

        when (resFrom) {
            Plunder -> {
                // 添加行军组所带资源
                addResToHome(areaCache, ACTION_FIGHT_CASTLE, player.id, resVos)

                //添加统计
                targetAddVal(
                    areaCache,
                    force.playerId,
                    PlunderResNum,
                    asList(totalResNum)
                )
            }
            Farm -> {
                if (strList.count() != 3) {
                    return
                }
                val resType = Integer.parseInt(strList[1])
                val resLv = Integer.parseInt(strList[2])

                // 发送资源采集战报
                val report = ReportInfo(
                    areaCache,
                    walk.marchPlaceX,
                    walk.marchPlaceY,
                    createFightDataByForceGroup(areaCache, group),
                    null,
                    RewardInfoForReport(0, hashMapOf(), force.resVo),
                    null, "".toByteArray()
                )
                report.genFarmReport(resType, resLv)

                // 添加行军组所带资源
                addResToHome(areaCache, ACTION_FARM_RES, player.id, resVos)

                //添加统计
                targetAddVal(
                    areaCache,
                    force.playerId,
                    FarmResNum,
                    asList(resType.toLong(), totalResNum)
                )

                wpm.es.fire(
                    areaCache, force.playerId, FARM_RES,
                    FarmResEvent(resType, totalResNum)
                )
            }
            Transfer ->
                // 添加行军组所带资源
                addResToHome(areaCache, ACTION_TRANSPORT, player.id, resVos)
        }
    }
}
