package com.point18.slg2d.world.module.wonder

import akka.actor.ActorRef
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.tellNoSender
import com.point18.slg2d.common.msgtrans.ScMessageAtSend
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.module.ReqDealWithConn
import pb4client.AllianceAwardInfo
import pb4client.PlayerNameInfo
import pb4client.QueryAllianceAwardRt

class QueryAllianceAwardDeal : ReqDealWithConn() {

    override fun dealConnReq(cache: AreaCache, channelActor: ActorRef, msg: MessageLite, playerId: Long) {
        val rt = this.queryAllianceAward(cache)
        val scMsg = ScMessageAtSend(MsgType.QueryAllianceAward_1463, cache.currentClientMsgNo, rt)
        channelActor.tellNoSender(scMsg)
    }

    private fun queryAllianceAward(areaCache: AreaCache): QueryAllianceAwardRt {
        val rtBuilder = QueryAllianceAwardRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val wonder = areaCache.wonderCache.findBigWonder()
        if (wonder == null || wonder.belongToAllianceId == 0L) {
            return rtBuilder.build()
        }

        val awardMap = wonder.awardInfoMap
        for ((awardId, playerIds) in awardMap) {
            val infoBuilder = AllianceAwardInfo.newBuilder()
            infoBuilder.awardId = awardId
            for (awardPlayerId in playerIds) {
                val playerInfoBuilder = PlayerNameInfo.newBuilder()
                val player = areaCache.playerCache.findPlayerById(awardPlayerId)
                if (player != null) {
                    playerInfoBuilder.playerId = player.id
                    playerInfoBuilder.playerName = player.name
                    playerInfoBuilder.playerPhoto = player.photoProtoId
                    playerInfoBuilder.allianceShortName = player.allianceShortName
                }
                infoBuilder.addAwardPlayers(playerInfoBuilder)
            }
            rtBuilder.addAwardInfos(infoBuilder)
        }

        return rtBuilder.build()
    }
}