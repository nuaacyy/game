package com.point18.slg2d.world.module.walk

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.boolToInt
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.module.ReqDealEntered
import pb4client.*
import pb4client.QueryWalkLineDetailInfo
import xyz.ariane.util.lzWarn

// 查询行军线详细
class QueryWalkLineDetailInfo : ReqDealEntered() {

    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rt = this.queryWalkLineDetailInfo(session, msg as QueryWalkLineDetailInfo)
        session.sendMsg(MsgType.QueryWalkLineDetailInfo_1252, rt.build())
    }

    private fun queryWalkLineDetailInfo(
            session: PlayerSession,
            queryMsg: QueryWalkLineDetailInfo
    ): QueryWalkLineDetailInfoRt.Builder {
        val rtBuilder = QueryWalkLineDetailInfoRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val areaCache = session.areaCache
        val walkId = queryMsg.walkId
        val walkLine = areaCache.walkCache.findWalkById(walkId)
        if (walkLine == null) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }

        val forces = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(walkLine.walkForceGroupId)
        for (force in forces) {
            val player = areaCache.playerCache.findPlayerById(force.playerId)
            if (player == null) {
                normalLog.lzWarn { "找不到部队对应的玩家信息:${force.playerId}" }
                rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                return rtBuilder
            }
            val forceBuilder = ForceInWalk.newBuilder()
            forceBuilder.playerId = force.playerId
            forceBuilder.playerName = player.name
            forceBuilder.allianceName = player.allianceName
            forceBuilder.allianceShortName = player.allianceShortName
            for (heroId in force.heroIdList) {
                val hero = areaCache.heroCache.findHeroById(force.playerId, heroId)
                if (hero == null) {
                    normalLog.lzWarn { "找不到部队对应的英雄信息:$heroId" }
                    rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                    return rtBuilder
                }
                val heroBuilder = HeroForWalk.newBuilder()
                heroBuilder.protoId = hero.protoId
                heroBuilder.lv = hero.level
                heroBuilder.starLv = hero.advLv
                heroBuilder.awake = hero.awake
                heroBuilder.isLord = boolToInt(hero.id == player.mainHeroId)
                forceBuilder.addHeros(heroBuilder)
            }
            for ((id, num) in force.soliderMap) {
                val soliderBuilder = SoliderForWalk.newBuilder()
                soliderBuilder.propId = id
                soliderBuilder.num = num
                forceBuilder.addSoliders(soliderBuilder)
            }
            rtBuilder.addForces(forceBuilder)
        }
        return rtBuilder
    }
}


