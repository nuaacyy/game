package com.point18.slg2d.world.module.gmCommand

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.pc.pcs
import pb4server.AddRelicRewardAskReq
import pb4server.World2HomeAskResp

class GmAddTimeBoxDrop : GmCommand {
    override fun exec(session: PlayerSession, message: String): Int {
        // 格式1： -gm add 类型 数量
        // 格式2： -gm changeCD building
        val messages = message.split(" ")
        if (messages.size == 1) {
            return 2
        }

        val player = session.player

        if (messages.size != 4) {
            return 2
        }

        val timeBoxId = messages[2].toIntOrNull()
        if (timeBoxId == null) {
            return 2
        }

        val rate = messages[3].toIntOrNull()
        if (rate == null) {
            return 2
        }

        val relicBox = pcs.relicBoxCache.relicBoxMap[timeBoxId]
        if (relicBox == null) {
            return 2
        }

        val reqMsg = AddRelicRewardAskReq.newBuilder()
        reqMsg.timeBoxId = timeBoxId
        reqMsg.dropRate = rate
        val areaCache = session.areaCache
        areaCache.worldActor.createACS<World2HomeAskResp>()
            .ask(
                areaCache.worldActor.homeShardRegion,
                areaCache.fillW2HAskMsgHeader(session.playerId) {
                    it.setAddRelicRewardAskReq(reqMsg)
                },
                World2HomeAskResp::class.java
            )
            .whenCompleteKt { rt, err ->
                //todo 添加遗迹奖励失败处理
            }
        return 1
    }
}