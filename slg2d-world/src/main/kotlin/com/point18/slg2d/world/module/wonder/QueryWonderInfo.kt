package com.point18.slg2d.world.module.wonder

import akka.actor.ActorRef
import com.point18.slg2d.world.area4data.*
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.msgtrans.ScMessageAtSend
import com.point18.slg2d.common.commonfunc.tellNoSender
import com.point18.slg2d.world.module.ReqDealWithConn
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.ALLIANCE_POSITION_BOSS
import com.point18.slg2d.world.common.isWonderPeace
import pb4client.PlayerNameInfo
import pb4client.QueryWonderInfo
import pb4client.QueryWonderInfoRt
import pb4client.WonderInfo
import com.point18.slg2d.common.resultcode.ResultCode

// 查询所有奇观的状态
class QueryWonderInfoDeal : ReqDealWithConn() {

    override fun dealConnReq(cache: AreaCache, channelActor: ActorRef, msg: MessageLite, playerId: Long) {
        val rtBuilder = queryWonderInfo(cache, msg as QueryWonderInfo)
        val scMsg =
            ScMessageAtSend(MsgType.QueryWonderInfo_1571, cache.currentClientMsgNo, rtBuilder.build())
        channelActor.tellNoSender(scMsg)
    }

    private fun queryWonderInfo(areaCache: AreaCache, msg: QueryWonderInfo): QueryWonderInfoRt.Builder {
        val rtBuilder = newQueryWonderInfoRtBuilder()

        for (wonder in areaCache.wonderCache.findAllWonders()) {
            val wonderInfoBuilder = WonderInfo.newBuilder()
            wonderInfoBuilder.protoId = wonder.wonderProtoId
            wonderInfoBuilder.status = wonder.status
            wonderInfoBuilder.allianceId = wonder.belongToAllianceId
            if (wonder.belongToAllianceId != 0L) {
                val allianceMembers = areaCache.playerCache.findPlayersByAllianceId(wonder.belongToAllianceId)
                for (member in allianceMembers) {
                    if (playerIsHavePos(member, ALLIANCE_POSITION_BOSS)) {
                        val playerInfoBuilder = PlayerNameInfo.newBuilder()
                        val castle = areaCache.castleCache.findCastleById(member.focusCastleId)
                        if (castle == null) {
                            normalLog.error("未找到玩家的城池，玩家Id:${member.id},城池Id:${member.focusCastleId}")
                            break
                        }
                        val mainHero = areaCache.heroCache.findHeroById(member.id, member.mainHeroId)
                        if (mainHero == null) {
                            normalLog.error("未找到玩家设置的领主，玩家Id:${member.id},heroId:${member.mainHeroId}")
                            break
                        }
                        playerInfoBuilder.playerId = member.id
                        playerInfoBuilder.playerName = member.name
                        playerInfoBuilder.playerPhoto = member.photoProtoId
                        playerInfoBuilder.allianceId = member.allianceId
                        playerInfoBuilder.allianceShortName = member.allianceShortName
                        playerInfoBuilder.allianceName = member.allianceName
                        playerInfoBuilder.posX = castle.x
                        playerInfoBuilder.posY = castle.y
                        playerInfoBuilder.mainHeroProtoId = mainHero.protoId
                        wonderInfoBuilder.setPlayer(playerInfoBuilder)
                        break
                    }
                }
            }
            if (!isWonderPeace(wonder)) {
                if (wonder.belongToAllianceId == 0L) {
                    wonderInfoBuilder.startTime = (wonder.warStartTime / 1000).toInt()
                    wonderInfoBuilder.endTime = (wonder.warFinishTime / 1000).toInt()
                } else {
                    wonderInfoBuilder.startTime = (wonder.occupyStartTime / 1000).toInt()
                    if (wonder.occupyOverTime <= wonder.warFinishTime) {
                        wonderInfoBuilder.endTime = (wonder.occupyOverTime / 1000).toInt()
                    } else {
                        wonderInfoBuilder.endTime = (wonder.warFinishTime / 1000).toInt()
                    }
                }
            } else {
                wonderInfoBuilder.startTime = (wonder.warStartTime / 1000).toInt()
                wonderInfoBuilder.endTime = (wonder.warFinishTime / 1000).toInt()
            }

            rtBuilder.addWonder(wonderInfoBuilder)
        }

        return rtBuilder
    }

    private fun newQueryWonderInfoRtBuilder(): QueryWonderInfoRt.Builder {
        val rtBuilder = QueryWonderInfoRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        return rtBuilder
    }

}