package com.point18.slg2d.world.module.wonder

import akka.actor.ActorRef
import com.point18.slg2d.world.area4data.AreaCache
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.msgtrans.ScMessageAtSend
import com.point18.slg2d.common.commonfunc.tellNoSender
import com.point18.slg2d.world.module.ReqDealWithConn
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.FameHallInfo
import pb4client.PlayerNameInfo
import pb4client.QueryFameHallRt
import com.point18.slg2d.common.resultcode.ResultCode

class QueryFameHallDeal : ReqDealWithConn() {

    override fun dealConnReq(cache: AreaCache, channelActor: ActorRef, msg: MessageLite, playerId: Long) {
        val rt = queryFameHall(cache)
        val scMsg =
            ScMessageAtSend(MsgType.QueryFameHall_1464, cache.currentClientMsgNo, rt.build())
        channelActor.tellNoSender(scMsg)
    }

    private fun queryFameHall(areaCache: AreaCache): QueryFameHallRt.Builder {
        val rtBuilder = newQueryFameHallRtBuilder()

        for (fameHall in areaCache.fameHallCache.fameHallList) {
            val fameHallBuilder = FameHallInfo.newBuilder()
            val playerNameInfoBuilder = PlayerNameInfo.newBuilder()
            playerNameInfoBuilder.playerId = fameHall.playerId
            playerNameInfoBuilder.playerName = fameHall.playerName
            playerNameInfoBuilder.allianceName = fameHall.allianceName
            playerNameInfoBuilder.allianceShortName = fameHall.allianceShortName
            playerNameInfoBuilder.playerPhoto = fameHall.playerPhotoId
            fameHallBuilder.setPlayerNameInfo(playerNameInfoBuilder)
            fameHallBuilder.occupyTime = (fameHall.occupyTime / 1000).toInt()
            fameHallBuilder.createTime = (fameHall.createTime / 1000).toInt()
            rtBuilder.addFameHallItems(fameHallBuilder)
        }

        return rtBuilder
    }

    private fun newQueryFameHallRtBuilder(): QueryFameHallRt.Builder {
        val rtBuilder = QueryFameHallRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        return rtBuilder
    }
}