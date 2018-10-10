package com.point18.slg2d.world.module.boss

import akka.actor.ActorRef
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.tellNoSender
import com.point18.slg2d.common.msgtrans.ScMessageAtSend
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.module.ReqDealWithConn
import pb4client.ActivityBossInfo
import pb4client.QueryActivityBossInfoRt

//查询活动魔物信息
class QueryActivityBossInfoDeal : ReqDealWithConn() {

    override fun dealConnReq(cache: AreaCache, channelActor: ActorRef, msg: MessageLite, playerId: Long) {
        val rt = queryActivityBossInfo(cache)
        val scMsg =
            ScMessageAtSend(MsgType.QueryActivityBossInfo_1495, cache.currentClientMsgNo, rt.build())
        channelActor.tellNoSender(scMsg)
    }

    private fun queryActivityBossInfo(
        cache: AreaCache
    ): QueryActivityBossInfoRt.Builder {
        val rtBuilder = QueryActivityBossInfoRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        cache.activityBossAreaCache.activityBossAreaMap.map {
            val activityBossId = it.activityBossId
            val refreshTime = it.refreshTime
            val activityBoss = cache.activityBossCache.findActivityBossByMonsterId(activityBossId)
            val nowHp = if (activityBoss != null) {
                if (activityBoss.nowHp > 0) activityBoss.nowHp else 0
            } else 0

            val activityBossInfo = ActivityBossInfo.newBuilder()
            activityBossInfo.monsterActivityId = activityBossId
            activityBossInfo.unlockTime = (refreshTime / 1000).toInt()
            activityBossInfo.bossHp = nowHp
            rtBuilder.addBossInfo(activityBossInfo)
            true
        }

        return rtBuilder
    }
}