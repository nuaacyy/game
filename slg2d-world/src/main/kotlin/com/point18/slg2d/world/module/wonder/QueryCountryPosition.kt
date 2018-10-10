package com.point18.slg2d.world.module.wonder

import akka.actor.ActorRef
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.commonfunc.tellNoSender
import com.point18.slg2d.common.msgtrans.ScMessageAtSend
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.module.ReqDealWithConn
import pb4client.CountryPosition
import pb4client.PlayerNameInfo
import pb4client.QueryCountryPositionRt

class QueryCountryPositionDeal : ReqDealWithConn() {

    override fun dealConnReq(cache: AreaCache, channelActor: ActorRef, msg: MessageLite, playerId: Long) {
        val rtBuilder = this.queryPos(cache)
        val scMsg =
            ScMessageAtSend(MsgType.QueryCountryPosition_1461, cache.currentClientMsgNo, rtBuilder.build())
        channelActor.tellNoSender(scMsg)
    }

    private fun queryPos(areaCache: AreaCache): QueryCountryPositionRt.Builder {
        val rtBuilder = QueryCountryPositionRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val wonder = areaCache.wonderCache.findBigWonder()
        if (wonder == null) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder
        }

        for ((playerId, posId) in wonder.officeMap) {
            val player = areaCache.playerCache.findPlayerById(playerId)
            if (player == null) {
                normalLog.error("找不到奇观上的玩家数据:$playerId")
                rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                return rtBuilder
            }
            val posBuilder = CountryPosition.newBuilder()
            posBuilder.posId = posId
            val playerInfoBuilder = PlayerNameInfo.newBuilder()
            playerInfoBuilder.playerId = playerId
            playerInfoBuilder.playerName = player.name
            playerInfoBuilder.playerPhoto = player.photoProtoId
            playerInfoBuilder.allianceId = player.allianceId
            playerInfoBuilder.allianceName = player.allianceName
            playerInfoBuilder.allianceShortName = player.allianceShortName
            posBuilder.setPlayerInfo(playerInfoBuilder)
            rtBuilder.addPositionInfos(posBuilder)
        }
        return rtBuilder
    }
}


