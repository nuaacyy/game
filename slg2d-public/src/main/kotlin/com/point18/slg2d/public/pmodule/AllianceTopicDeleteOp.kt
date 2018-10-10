package com.point18.slg2d.public.pmodule

import pb4server.AllianceTopicDeleteAskReq
import pb4server.AllianceTopicDeleteAskRt
import pb4server.World2PublicAskReq
import pb4server.World2PublicAskResp
import com.point18.slg2d.public.common.dealAfterAlliancePublishTopic
import com.point18.slg2d.public.datacache.*
import com.point18.slg2d.common.resultcode.ResultCode

class AllianceTopicDeleteOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(
        publicCache: PublicCache,
        req: World2PublicAskReq,
        resp: World2PublicAskResp.Builder
    ) {
        val internalMessage = req.allianceTopicDeleteAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMessage)

        resp.setAllianceTopicDeleteAskRt(rt)
    }

    fun dealMsg(
        publicCache: PublicCache,
        allianceId: Long,
        playerId: Long,
        req: AllianceTopicDeleteAskReq
    ): AllianceTopicDeleteAskRt.Builder {
        val rt = newAllianceTopicDeleteAskRtBuilder()

        val player = publicCache.allianceMemberCache.findAllianceMemberById(playerId)
        if (player == null) {
            rt.rt = ResultCode.ALLIANCE_TOPIC_NOT_EXISTS.code
            return rt
        }
        // 验证联盟邮件主题是否存在
        val aTopic =
            publicCache.allianceTopicCache.findAllianceTopicBy2Id(player.allianceId, req.topicId)
        if (aTopic == null) {
            rt.rt = ResultCode.ALLIANCE_TOPIC_NOT_EXISTS.code
            return rt
        }

        // 验证操作权限
        val mdl = aTopic.type
        if (!hasRight(player, mdl)) {
            rt.rt = ResultCode.ALLIANCE_POSITION_RIGHT_FORBIDDEN.code
            return rt
        }

        // 删除邮件主题的回复消息
        publicCache.allianceReplyCache.deleteAllianceRepliesByTopicId(aTopic.id)

        // 删除联盟邮件主题
        publicCache.allianceTopicCache.deleteAllianceTopic(aTopic)

        // 向相关成员推送联盟主题邮件变化提醒
        val sPlayers = fetchNotifierPlayersByOfManager(publicCache, player.allianceId, player.id, mdl, aTopic.type, aTopic.createAt)

        val pltAreas = mutableMapOf<Long, Int>()
        for (am in sPlayers) {
            pltAreas[am.mapPltAreaId] = 1
        }

        for ((pltAreaId, _) in pltAreas) {
            dealAfterAlliancePublishTopic(publicCache.publicActor, pltAreaId, player.allianceId, aTopic.id)
        }

        return rt
    }

    fun newAllianceTopicDeleteAskRtBuilder(): AllianceTopicDeleteAskRt.Builder {
        val rt = AllianceTopicDeleteAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}