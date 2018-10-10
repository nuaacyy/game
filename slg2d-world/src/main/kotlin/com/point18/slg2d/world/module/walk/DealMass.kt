package com.point18.slg2d.world.module.walk

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.common.*
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.module.walk.walkComm.WalkElement
import com.point18.slg2d.world.module.walk.walkComm.WalkParam
import com.point18.slg2d.world.msgnotice.createValueChgNotifier
import pb4client.StartMass
import pb4client.StartMassRt
import xyz.ariane.util.lzWarn
import java.util.*

// 开始集结
class StartMassDeal : ReqDealEntered() {

    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rt = this.startMass(session, msg as StartMass)
        session.sendMsg(MsgType.StartMass_1254, rt.build())
    }

    private fun startMass(session: PlayerSession, startMass: StartMass): StartMassRt.Builder {
        val rtBuilder = StartMassRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val areaCache = session.areaCache
        val playerId = session.playerId

        // 集结需要在联盟中
        val player = session.player
        if (player.allianceId == 0L) {
            rtBuilder.rt = ResultCode.MassNeedInAlliance.code
            return rtBuilder
        }

        // 集结需要战争大厅
        val infoByHome = areaCache.infoByHomeCache.findInfoByHomeByPlayerId(player.id)
        if (infoByHome == null) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }
        val buildingLv = infoByHome.findBuildingMaxLv(FightLobby)
        if (buildingLv == 0) {
            rtBuilder.rt = ResultCode.MassNeedFightLobby.code
            return rtBuilder
        }

        var targetPlayerId = 0L

        val posX = startMass.aimsX
        val posY = startMass.aimsY
        val runType = startMass.runType
        when (runType) {
            WalkRelic -> {
            }
            WalkFightPlayer -> {
                // 集结玩家，需要判断对方主城等级
                val targetCastle = areaCache.castleCache.findCastleByXy(posX, posY)
                if (targetCastle == null) {
                    rtBuilder.rt = ResultCode.CASTLE_DONT_EXISTED.code
                    return rtBuilder
                }
                val targetPlayer = areaCache.playerCache.findPlayerById(targetCastle.playerId)
                targetPlayerId = targetCastle.playerId
                if (targetPlayer == null) {
                    normalLog.lzWarn { "DealMass.kt 找不到玩家数据:${targetCastle.playerId}" }
                    rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                    return rtBuilder
                }
                if (targetPlayer.allianceId == 0L) {
                    val targetInfoByHome = areaCache.infoByHomeCache.findInfoByHomeByPlayerId(targetCastle.playerId)
                    if (targetInfoByHome == null) {
                        rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                        return rtBuilder
                    }
                    val targetBuildingLv = infoByHome.findBuildingMaxLv(MainBuilding)
                    if (targetBuildingLv < pcs.basicProtoCache.protectUnMassCityLv) {
                        rtBuilder.rt = ResultCode.MassTargetBuildTooLow.code
                        return rtBuilder
                    }
                }
                // 目的地已满18个集结,不能继续创建集结.
                // 集结数=联盟数：同一个联盟，只能对同一个玩家发起一个集结；并且集结必须有联盟
                val massByPos = areaCache.massCache.findAllMassByPos(posX, posY)
                if (massByPos.size >= pcs.basicProtoCache.playerMassedLimit) {
                    rtBuilder.rt = ResultCode.OVER_LIMIT_MASS.code
                    return rtBuilder
                }
                // 已经有同联盟的集结，不能继续创建集结
                massByPos.forEach {
                    if (it.allianceId == player.allianceId) {
                        rtBuilder.rt = ResultCode.ONLY_ONE_MASS_IN_ALLIANCE.code
                        return rtBuilder
                    }
                }
            }
            WalkOccupyWonder -> {
                // 集结奇观，奇观需要非自己联盟的
                val rst = pcs.wonderLocationProtoCache.findInWonderType(posX, posY)
                val wonderProto = rst.wonderLocationProto
                if (rst.int != WONDER_BASE || wonderProto == null) {
                    rtBuilder.rt = ResultCode.CASTLE_DONT_EXISTED.code
                    return rtBuilder
                }
                val wonder = areaCache.wonderCache.findWonder(wonderProto.id)
                if (wonder == null) {
                    rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                    return rtBuilder
                }
                if (isWonderPeace(wonder)) {
                    rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                    return rtBuilder
                }
                if (wonder.occupyGroupId == player.allianceId) {
                    rtBuilder.rt = ResultCode.HAVE_OCCUPY_WONDER.code
                    return rtBuilder
                }
            }
            else -> {
                rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                return rtBuilder
            }
        }

        // 校验英雄
        for (heroId in startMass.heroIdsList) {
            val hero = areaCache.heroCache.findHeroById(playerId, heroId)
            if (hero == null) {
                rtBuilder.rt = ResultCode.NO_HERO.code
                return rtBuilder
            }
            if (hero.posState != IN_CITY) {
                rtBuilder.rt = ResultCode.HERO_POS_STATE_ERROR.code
                return rtBuilder
            }
            if (hero.mainHeroState != NO_MAIN_HERO && hero.mainHeroState != MAIN_HERO) {
                rtBuilder.rt = ResultCode.HERO_POS_STATE_ERROR.code
                return rtBuilder
            }
        }

        // 出征队列上限校验
        val forces = areaCache.walkForceCache.findWalkForceByPlayerId(playerId)
        if (forces.count() >= getResearchEffectValue(
                areaCache,
                NO_FILTER,
                player,
                ResearchEffectWalkQueueAdd
            )
        ) {
            rtBuilder.rt = ResultCode.WalkQueueUpLimit.code
            return rtBuilder
        }

        //自身集结部队校验
        val myMass = areaCache.massCache.findMassByPlayerId(playerId)
        if (myMass.count() > 0) {
            rtBuilder.rt = ResultCode.HaveStartMass.code
            return rtBuilder
        }

        val joinMassTime = startMass.joinMassTime

        val soliderMap = hashMapOf<Int, Int>()
        for (sInfo in startMass.solidersList) {
            soliderMap[sInfo.protoId] = sInfo.num
        }

        // 集结时间校验
        var haveMassTime = false
        for (mTime in pcs.basicProtoCache.massTime) {
            if (joinMassTime == mTime * 60) {
                haveMassTime = true
                break
            }
        }
        if (!haveMassTime) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }

        // 找到行军处理
        val walkDeal = walkM.walkDeals[runType]
        if (walkDeal == null) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }

        val walkElement = WalkElement(LinkedList(startMass.heroIdsList), soliderMap)
        // 自身城池坐标
        val castle = areaCache.castleCache.findCastleById(player.focusCastleId)
        if (castle == null) {
            normalLog.lzWarn { "DealMass.kt 找不到玩家城:${player.focusCastleId}" }
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }
        val startX = castle.x
        val startY = castle.y

        // 出发检测
        val wp = WalkParam(
            playerId,
            startX,
            startY,
            0,
            posX,
            posY,
            walkElement
        )
        var result = walkDeal.startCheck(areaCache, wp)
        if (result != ResultCode.SUCCESS.code) {
            rtBuilder.rt = result
            return rtBuilder
        }

        // 其他独立的检测
        result = walkDeal.walkStartCheck(areaCache, wp)
        if (result != ResultCode.SUCCESS.code) {
            rtBuilder.rt = result
            return rtBuilder
        }

        // 创建行军组
        val newGroup = areaCache.walkForceGroupCache.createWalkForceGroup(playerId, 0, MassWaiting, runType, startX, startY)
        areaCache.walkForceCache.createWalkForce(
            LinkedList(startMass.heroIdsList),
            soliderMap,
            "",
            LinkedList(),
            newGroup.id,
            playerId
        )
        for ((soliderId, soliderNum) in soliderMap) {
            costSolider(areaCache, playerId, soliderId, soliderNum)
        }

        // 创建集结
        val mass = areaCache.massCache.createMass(
            player,
            runType,
            startMass.massName,
            startX,
            startY,
            posX,
            posY,
            joinMassTime,
            newGroup.id
        )

        if (runType == WalkFightPlayer) {
            val action = if (joinMassTime < 3600) {
                BE_MASSED_1H
            } else {
                BE_MASSED_8H
            }

            // 个人遭受集结app通知
            areaCache.pushAppNotice(
                targetPlayerId,
                action,
                player.kingLv,
                player.name
            )
        }

        // 触发战争狂热
        if (runType == WalkFightPlayer || runType == WalkOccupyWonder) {
            addWalkHot(areaCache, player)
        }

        // 联盟聊天频道广播!
        multicastAllianceMass(
            areaCache,
            player.allianceId,
            playerId,
            player.allianceNickName,
            player.allianceName,
            player.allianceShortName,
            player.allianceName,
            mass.id,
            startMass.massName,
            player.photoProtoId,
            infoByHome.vipLv,
            player.worldId,
            player.areaNo,
            findOfficeByPlayerId(areaCache, playerId)
        )

        // 集结方通知
        noticeMassGroup(areaCache, Add, mass)

        // 被集结方通知
        if (mass.fightType == WalkFightPlayer || mass.fightType == WalkOccupyWonder) {
            noticeReinforceMassGroup(areaCache, Add, mass)
        }

        // 通知行军状态
        noticeSelfWalkForceGroup(areaCache, Add, newGroup)

        // 设置英雄状态
        val heroChangeMsg = createValueChgNotifier()
        for (heroId in startMass.heroIdsList) {
            val hero = session.areaCache.heroCache.findHeroById(session.playerId, heroId)
            if (hero == null) {
                normalLog.lzWarn { "DealMass.kt 找不到玩家英雄数据:$heroId" }
                rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                return rtBuilder
            }
            hero.posState = OUT_CITY
            heroChangeMsg.append(hero.id, HERO_POS_STATE, (OUT_CITY).toLong())
        }
        heroChangeMsg.notice(session)

        return rtBuilder
    }
}