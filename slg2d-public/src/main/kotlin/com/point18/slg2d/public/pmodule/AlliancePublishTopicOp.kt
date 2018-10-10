package com.point18.slg2d.public.pmodule

import pb4server.*
import com.point18.slg2d.public.common.dealAfterAlliancePublishTopic
import com.point18.slg2d.public.datacache.*
import com.point18.slg2d.common.resultcode.ResultCode

class AlliancePublishTopicOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(
        publicCache: PublicCache,
        req: World2PublicAskReq,
        resp: World2PublicAskResp.Builder
    ) {
        val internalMessage = req.alliancePublishTopicAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMessage)

        resp.setAlliancePublishTopicAskRt(rt)
    }

    fun dealMsg(
        publicCache: PublicCache,
        allianceId: Long,
        playerId: Long,
        req: AlliancePublishTopicAskReq
    ): AlliancePublishTopicAskRt.Builder {
        val rt = newAlliancePublishTopicAskRtBuilder()

        // 玩家是否有联盟
        val player = publicCache.allianceMemberCache.findAllianceMemberById(playerId)
        if (player == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }
        val alce = publicCache.allianceCache.findAllianceById(allianceId)
        if (alce == null) {
            rt.rt = ResultCode.ALLIANCE_PLAYER_NO_JOIN.code
            return rt
        }

        // 职位权限验证
        val mdl = req.type
        if (!hasRight(player, mdl)) {
            rt.rt = ResultCode.ALLIANCE_POSITION_RIGHT_FORBIDDEN.code
            return rt
        }

        // 创建联盟邮件主题
        val aTopic = publicCache.allianceTopicCache.createAllianceTopic(
            alce.id,
            req.type,
            player.id,
            req.title
        )

        // 创建消息
        publicCache.allianceReplyCache.createAllianceReply(aTopic.id, player.id, req.message)

        // 设置返回的消息记录
        val allianceTopicInfo = AllianceTopicInfoVo.newBuilder()
        allianceTopicInfo.topicId = aTopic.id
        allianceTopicInfo.type = aTopic.type
        allianceTopicInfo.playerId = player.id
        allianceTopicInfo.playerName = player.name
        allianceTopicInfo.photoProtoId = player.photoProtoId
        allianceTopicInfo.title = aTopic.title
        allianceTopicInfo.lastAt = (aTopic.lastAt / 1000).toInt()
        allianceTopicInfo.isRead = 1
        allianceTopicInfo.isSign = 0
        rt.setTopic(allianceTopicInfo)

        // 向相关成员推送联盟主题邮件提醒
        val sPlayers = fetchNotifierPlayersByOfManager(publicCache, alce.id, player.id, mdl, aTopic.type, aTopic.createAt)

        // 推送
        val pltAreas = mutableMapOf<Long, Int>()

        for (am in sPlayers) {
            pltAreas[am.mapPltAreaId] = 1
        }

        for ((pltAreaId, _) in pltAreas) {
            dealAfterAlliancePublishTopic(publicCache.publicActor, pltAreaId, alce.id, aTopic.id)
        }

        return rt
    }

    fun newAlliancePublishTopicAskRtBuilder(): AlliancePublishTopicAskRt.Builder {
        val rt = AlliancePublishTopicAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}
