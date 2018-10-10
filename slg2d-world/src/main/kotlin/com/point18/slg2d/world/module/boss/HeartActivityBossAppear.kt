package com.point18.slg2d.world.module.boss

import com.point18.slg2d.common.baseg.IHeartHandler
import com.point18.slg2d.common.commonfunc.getDaysOfTwo
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.getWeekDateZeroTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.pc.resVoToResString
import com.point18.slg2d.world.area4data.ActivityBoss
import com.point18.slg2d.world.area4data.ActivityBossArea
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.common.*
import com.point18.slg2d.world.event.GetNewBuff
import com.point18.slg2d.world.module.walk.walkComm.halfWayGoHome
import com.point18.slg2d.world.msgnotice.createHunterRecordNotifier
import com.point18.slg2d.world.wpm
import xyz.ariane.util.lzWarn
import java.util.*
import java.util.Arrays.asList

class ActivityBossHeartHandler : IHeartHandler<AreaCache> {

    override fun handleHeart(cache: AreaCache) {
        activityBossHeart(cache)
    }

    private fun activityBossHeart(areaCache: AreaCache) {
        // 处理boss出现
        val bossAreas = areaCache.activityBossAreaCache.findBossAreaAppear()
        for (activityBossArea in bossAreas) {
            // 上雪地罩子
            giveSnowCover(areaCache, activityBossArea)
            bossAppear(areaCache, activityBossArea)
        }
        if (bossAreas.size > 0) {
            // 四天龙出现的跑马灯
            sendMarqueeNotice2All(areaCache, NOTICE_TYPE_CENTER, MARQUEE_ACTIVITY_BOSS_APPEAR, TEXT_READ_LAN)
        }

        // 处理boss刷新
        for (activityBossArea in areaCache.activityBossAreaCache.findBossAreaRefresh()) {
            giveSnowCover(areaCache, activityBossArea)
            bossRefresh(areaCache, activityBossArea)
        }

        // 处理boss消失
        for (activityBossArea in areaCache.activityBossAreaCache.findBossAreaDisappear()) {
            bossDisappear(areaCache, activityBossArea)
        }

        // 发送预告邮件
        sendAdvanceMailOrNot(areaCache)
    }

    private fun sendAdvanceMailOrNot(areaCache: AreaCache) {
        // 是否要发预告邮件，不需要就return掉
        var sendAdvanceMail = true
        val allBossAreas = areaCache.activityBossAreaCache.findAllActivityBossArea()
        for (activityBossArea in allBossAreas) {
            if (activityBossArea.advanceMail == SEND_ADVANCE_MAIL) {
                sendAdvanceMail = false
                break
            }
        }
        if (!sendAdvanceMail) {
            return
        }

        // 计算发送邮件的时间
        var startTime = 0L
        var finishTime = 0L
        for (activityBossArea in areaCache.activityBossAreaCache.findAllActivityBossArea()) {
            startTime = activityBossArea.startTime
            finishTime = activityBossArea.finishTime
            break
        }
        val sendMailTime = startTime - pcs.basicProtoCache.monsterActivityMail * 1000

        //发送四天龙预告邮件
        val nowTime = getNowTime()
        if (nowTime in sendMailTime..finishTime) {
            val mailInfo = MailInfo(
                TEXT_READ_LAN,
                FOUR_DRAGON_ADVANCE_TITLE,
                LinkedList(),
                FOUR_DRAGON_ADVANCE_CONTENT,
                LinkedList()
            )
            for (player in areaCache.playerCache.findAllPlayers()) {
                sendMail(areaCache, player.id, mailInfo)
            }

            // 标记为已经发送，活动结束后去掉标记
            allBossAreas.forEach {
                it.advanceMail = SEND_ADVANCE_MAIL
            }
        }
    }

    private fun bossAppear(areaCache: AreaCache, activityBossArea: ActivityBossArea) {
        val bossProtoList = pcs.monsterActivityProtoCache.locationMonsterMap[activityBossArea.locationId]
        if (bossProtoList == null) {
            normalLog.lzWarn { "没有找到monsterActivity[locationId=${activityBossArea.locationId}]的模板" }
            return
        }

        val locationProto =
            pcs.monsterActivityLocationProtoCache.monsterActivityLocationProtoMap[activityBossArea.locationId]
        if (locationProto == null) {
            normalLog.lzWarn { "没有找到monsterActivityLocation[id=${activityBossArea.locationId}]的模板" }
            return
        }

        val initTime = areaCache.activityBossAreaCache.getInitAreaTime() // 开服时间
        val diffDay = getDaysOfTwo(Date(initTime), Date()) // 现在距离开服多少天

        // 在活动boss区域生成首个活动魔物
        var monsterActivityId = 0
        for (bossProto in bossProtoList) {
            if (diffDay >= bossProto.openingTimeInterval[0] && diffDay < bossProto.openingTimeInterval[1]
                || bossProto.openingTimeInterval[1] == 0
            ) {
                monsterActivityId = bossProto.id

                val monsterProto = pcs.monsterProtoCache.findMonsterProto(bossProto.monsterId)
                if (monsterProto == null) {
                    normalLog.lzWarn { "没有找到monster[d=${bossProto.monsterId}]的模板" }
                    return
                }

                val unitProto = pcs.unitBaseCache.protoMap[monsterProto.unit]
                if (unitProto == null) {
                    normalLog.lzWarn { "没有找到newBossProto[id=${monsterProto.unit}]的模板" }
                    return
                }

                //创建魔物
                areaCache.activityBossCache.createActivityBoss(
                    bossProto.monsterId,
                    monsterActivityId,
                    locationProto.baseIndex[0],
                    locationProto.baseIndex[1],
                    Math.ceil(unitProto.hp).toInt()
                )
                break
            }
        }

        // 更新活动boss区域的状态
        activityBossArea.status = BOSS_APPEAR
        activityBossArea.activityBossId = monsterActivityId

        // 更新地图地块
        noticeCellUpdate(areaCache, locationProto.baseIndex[0], locationProto.baseIndex[1])
    }

    private fun giveSnowCover(areaCache: AreaCache, activityBossArea: ActivityBossArea) {
        val locationProto =
            pcs.monsterActivityLocationProtoCache.monsterActivityLocationProtoMap[activityBossArea.locationId]
        if (locationProto != null) {
            for ((x, subMap) in locationProto.snowMap) {
                for ((y, _) in subMap) {
                    val castle = areaCache.castleCache.findCastleByXy(x, y)
                    if (castle != null) {
                        val snowCoverBuffId = pcs.basicProtoCache.specialActivityProtect
                        val proto = pcs.buffBasicProtoCache.protoMap[snowCoverBuffId]
                        if (proto == null) {
                            assert(false) { "找不到雪地保护罩buff$snowCoverBuffId" }
                        } else {
                            val buffTime = proto.time * 1000
                            if (buffTime != 0) {
                                val buffOverTime = getNowTime() + buffTime
                                wpm.es.fire(
                                    areaCache, castle.playerId, GET_BUFF,
                                    GetNewBuff(castle.playerId, snowCoverBuffId, buffOverTime)
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun bossRefresh(areaCache: AreaCache, activityBossArea: ActivityBossArea) {
        val locationProto =
            pcs.monsterActivityLocationProtoCache.monsterActivityLocationProtoMap[activityBossArea.locationId]
        if (locationProto == null) {
            normalLog.lzWarn { "没有找到monsterActivityLocation[id=${activityBossArea.locationId}]的模板" }
            return
        }

        val oldBossProto =
            pcs.monsterActivityProtoCache.monsterActivityProtoMap[activityBossArea.activityBossId]
        if (oldBossProto == null) {
            normalLog.lzWarn { "没有找到monsterActivity[id = ${activityBossArea.activityBossId}]的模板" }
            return
        }

        val newBossProto = pcs.monsterActivityProtoCache.monsterActivityProtoMap[oldBossProto.afterMonsterId]
        if (newBossProto == null) {
            normalLog.lzWarn { "没有找到monsterActivity[id = ${oldBossProto.afterMonsterId}]的模板" }
            return
        }

        val monsterProto = pcs.monsterProtoCache.findMonsterProto(newBossProto.monsterId)
        if (monsterProto == null) {
            normalLog.lzWarn { "没有找到monster[id=${newBossProto.monsterId}]的模板" }
            return
        }

        val unitProto = pcs.unitBaseCache.protoMap[monsterProto.unit]
        if (unitProto == null) {
            normalLog.lzWarn { "没有找到unitBase[id=${monsterProto.unit}]的模板" }
            return
        }

        // 删除旧的魔物(实际在被击杀后不会去删除魔物，所以在刷新时删除)
        val oldBoss =
            areaCache.activityBossCache.findActivityBossByXy(locationProto.baseIndex[0], locationProto.baseIndex[1])
        if (oldBoss != null) {
            areaCache.activityBossCache.deleteActivityBoss(oldBoss)
        }

        //创建新魔物
        areaCache.activityBossCache.createActivityBoss(
            newBossProto.monsterId,
            newBossProto.id,
            locationProto.baseIndex[0],
            locationProto.baseIndex[1],
            Math.ceil(unitProto.hp).toInt()
        )

        // 更新活动boss区域的状态
        activityBossArea.status = BOSS_APPEAR
        activityBossArea.activityBossId = newBossProto.id

        // 更新地图地块
        noticeCellUpdate(areaCache, locationProto.baseIndex[0], locationProto.baseIndex[1])
    }
}

fun bossDisappear(areaCache: AreaCache, activityBossArea: ActivityBossArea) {
    val locationProto =
        pcs.monsterActivityLocationProtoCache.monsterActivityLocationProtoMap[activityBossArea.locationId]
    if (locationProto == null) {
        normalLog.lzWarn { "没有找到monsterActivityLocation[id=${activityBossArea.locationId}]的模板" }
        return
    }

    val posX = locationProto.baseIndex[0]
    val posY = locationProto.baseIndex[1]

    val activityBoss = areaCache.activityBossCache.findActivityBossByXy(posX, posY)

    val allianceRank = areaCache.activityBossCache.findAllianceActivityBossRankByXY(posX, posY)


    if (activityBoss != null && allianceRank != null) {
        // 获取联盟排行奖励
        val bossName = pcs.monsterProtoCache.findMonsterProto(activityBoss.bossId)?.name ?: "未知Boss"
        val joinNum = allianceRank.rank.queryAllJoinNum()
        for ((i, atkRecord) in allianceRank.rank.queryValue(joinNum).withIndex()) {
            val rank = i + 1
            val award = pcs.monsterActivityProtoCache.findEventRankRewardByRank(activityBoss.activityBossId, rank)
            if (award == null) {
                continue
            }
            val allAward = LinkedList<ResVo>()
            for (dropId in award.rewardBags) {
                val drop = pcs.dropBagCache.dropBagMap[dropId]
                if (drop == null) {
                    continue
                }
                allAward += drop.dropMap
            }
            // 发送联盟奖励邮件
            sendAllianceAwardMail(
                areaCache,
                atkRecord.allianceId,
                TEXT_READ_LAN,
                ACTIVITY_BOSS_ALLIANCE_RANK_TITLE,
                listOf(),
                ACTIVITY_BOSS_ALLIANCE_RANK_CONTENT,
                asList(bossName, rank.toString()),
                resVoToResString(allAward)
            )
        }

        // 删除对应的魔物
        onActivityBossOver(areaCache, activityBoss)
        areaCache.activityBossCache.deleteActivityBoss(activityBoss)
    } else {
        normalLog.lzWarn { "($posX,$posY)对应的魔物不存在或联盟排行不存在" }
    }

    // 更新活动boss区域的状态
    /** 根据basic配置设置时间 **/
    val startWeek = pcs.basicProtoCache.monsterActivityOpen[0]
    val startHour = pcs.basicProtoCache.monsterActivityOpen[1]
    val continueSec = pcs.basicProtoCache.monsterActivityContinue

    // 根据配置计算奇观开始结束时间与当前状态
    val startTime = getWeekDateZeroTime(activityBossArea.startTime, startWeek) + startHour * 3600 * 1000
    val finishTime = startTime + continueSec * 1000

    val round = pcs.basicProtoCache.monsterActivityRound // 活动boss周期
    activityBossArea.status = BOSS_DISAPPEAR
    activityBossArea.activityBossId = 0
    activityBossArea.startTime = startTime + round * 24 * 3600 * 1000
    activityBossArea.finishTime = finishTime + round * 24 * 3600 * 1000
    activityBossArea.refreshTime = activityBossArea.startTime

    // 重置活动邮件
    activityBossArea.advanceMail = NOT_SEND_ADVANCE_MAIL

    // 更新地图地块
    noticeCellUpdate(areaCache, locationProto.baseIndex[0], locationProto.baseIndex[1])
}

fun updateRefreshTime(areaCache: AreaCache, activityBoss: ActivityBoss) {
    val locationProtoXYMap = pcs.monsterActivityLocationProtoCache.monsterActivityLocationXYProtoMap
    val locationProto = locationProtoXYMap.findByKey(
        activityBoss.x,
        activityBoss.y
    )
    if (locationProto != null) {
        val bossArea = areaCache.activityBossAreaCache.findActivityBossAreaByLocationId(locationProto.id)
        if (bossArea != null) {
            val activityBossId = bossArea.activityBossId
            val bossProto = pcs.monsterActivityProtoCache.monsterActivityProtoMap[activityBossId]
            if (bossProto != null) {
                bossArea.refreshTime = getNowTime() + bossProto.resurrectionTime * 1000
                bossArea.status = BOSS_WAIT_REFRESH
            }
        }
    }
}

fun onActivityBossOver(areaCache: AreaCache, activityBoss: ActivityBoss) {
    // 不直接删除魔物数据，而是在下一个魔物刷新的时候删除
    //deleteActivityBoss(areaCache, activityBoss)

    val gotoWalks = areaCache.walkCache.findWalksByGotoXy(activityBoss.x, activityBoss.y)
    gotoWalks.forEach {
        halfWayGoHome(areaCache, it)
        //发送遣返邮件
        val mailInfo = MailInfo(
            TEXT_READ_LAN,
            SYSTEM_MAIL,
            LinkedList(),
            TARGET_DISAPPEAR_CONTENT,
            LinkedList(asList(WALK_PARAS + it.marchState.toString()))
        )
        val forces = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(it.walkForceGroupId)
        for (force in forces) {
            sendMail(areaCache, force.playerId, mailInfo)
        }
    }

    // 删除个人记录
    val notifier = createHunterRecordNotifier(
        Del,
        activityBoss.x,
        activityBoss.y,
        activityBoss.bossId,
        activityBoss.nowHp,
        0
    )
    activityBoss.atkRecordsMap.forEach {
        val session = fetchOnlinePlayerSession(areaCache, it.key)
        if (session != null) {
            notifier.notice(session)
        }
    }

    updateBossInvite(areaCache, activityBoss, Del)

    removeWaitFightGroup(areaCache, activityBoss, WalkActivityBoss)

    //地块刷新
    noticeCellUpdate(areaCache, activityBoss.x, activityBoss.y)
}

