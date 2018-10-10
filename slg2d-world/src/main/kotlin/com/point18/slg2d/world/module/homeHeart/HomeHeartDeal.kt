package com.point18.slg2d.world.module.homeHeart

import com.point18.slg2d.common.baseg.IHeartHandler
import com.point18.slg2d.world.area4data.*
import xyz.ariane.util.lzWarn
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.Deleted
import pb4server.TriggerHeartAskReq
import pb4server.World2HomeAskResp
import com.point18.slg2d.common.resultcode.ResultCode

class HomeHeartHandler : IHeartHandler<AreaCache> {
    override fun handleHeart(cache: AreaCache) {
        var i = 0
        while (true) {
            val heart = cache.homeHeartCache.peekHomeHeart()
            if (heart == null) {
                return
            }

            // 弹出目标元素
            cache.homeHeartCache.popHomeHeart()

            if (heart.state == Deleted) {
                //心跳已失效
                continue
            }

            forward2HomeTriggerHeart(cache, heart)
            i++
            if (i > 100000) {
                return
            }
        }
    }

    /**
     * 通知home服心跳触发
     */
    private fun forward2HomeTriggerHeart(areaCache: AreaCache, heart: HomeHeart) {
        //心跳加入等到回复
        areaCache.homeHeartCache.addHeart2WaitReply(heart)

        val msg = TriggerHeartAskReq.newBuilder()
        msg.action = heart.homeAction
        msg.actionId = heart.actionId
        areaCache.worldActor.createACS<World2HomeAskResp>()
            .ask(
                areaCache.worldActor.homeShardRegion,
                areaCache.fillW2HAskMsgHeader(heart.playerId) {
                    it.setTriggerHeartAskReq(msg)
                },
                World2HomeAskResp::class.java
            )
            .whenCompleteKt { hrt, err ->
                if (err != null || hrt == null) {
                    normalLog.lzWarn { "home心跳返回错误:{$err}" }
                    return@whenCompleteKt
                }
                val rt = hrt.triggerHeartAskRt
                if (rt.rt != ResultCode.SUCCESS.code) {
                    normalLog.lzWarn { "home心跳返回错误:{${rt.rt}},心跳Id${heart.id}" }
                    areaCache.homeHeartCache.addHeart2ReplyError(heart)
                    return@whenCompleteKt
                }
                areaCache.homeHeartCache.removeHeartFromWait(heart)
            }
    }
}

