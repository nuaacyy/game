package com.point18.slg2d.world.module.realm

import akka.actor.ActorRef
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.msgtrans.ScMessageAtSend
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.module.ReqDealWithConn
import pb4client.FetchServerTime
import pb4client.FetchServerTimeRt
import xyz.ariane.util.systemDefaultZoneId
import java.time.Instant
import java.util.*

class ServerTimeDeal : ReqDealWithConn() {

    override fun dealConnReq(cache: AreaCache, channelActor: ActorRef, msg: MessageLite, playerId: Long) {
        // 获取用户ID
        val reqTime = (msg as FetchServerTime).reqTime

        // 数据返回定义
        val makePlayRt = reqServerTime(reqTime)

        // 发送数据
        val scMsg = ScMessageAtSend(MsgType.ServerTime_21, cache.currentClientMsgNo, makePlayRt)
        channelActor.tell(scMsg, ActorRef.noSender())
    }

    // 获取服务器时间
    private fun reqServerTime(reqTime: Long): (FetchServerTimeRt) {
        val rt = FetchServerTimeRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.reqTime = reqTime
        rt.time = Instant.now().toEpochMilli()
        rt.timeZoneName = systemDefaultZoneId.toString()
        rt.timeZoneValue = Calendar.getInstance().timeZone.rawOffset

        return rt.build()
    }

}
