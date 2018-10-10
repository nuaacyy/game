package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.public.datacache.PublicCache
import pb4server.AllianceTopicGetReplyAskReq
import pb4server.AllianceTopicGetReplyAskRt
import pb4server.World2PublicAskReq
import pb4server.World2PublicAskResp
import com.point18.slg2d.common.resultcode.ResultCode

class AllianceTopicGetReplyOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(
        publicCache: PublicCache,
        req: World2PublicAskReq,
        resp: World2PublicAskResp.Builder
    ) {
        val internalMessage = req.allianceTopicGetReplyAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMessage)

        resp.setAllianceTopicGetReplyAskRt(rt)
    }

    fun dealMsg(
        publicCache: PublicCache,
        allianceId: Long,
        playerId: Long,
        req: AllianceTopicGetReplyAskReq
    ): AllianceTopicGetReplyAskRt.Builder {
        val rt = newAllianceTopicGetReplyAskRtBuilder()

        val player = publicCache.allianceMemberCache.findAllianceMemberById(playerId)
        if (player == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }
        // 玩家是否有联盟
        val alce = publicCache.allianceCache.findAllianceById(allianceId)
        if (alce == null) {
            rt.rt = ResultCode.ALLIANCE_PLAYER_NO_JOIN.code
            return rt
        }

        // 联盟邮件主题是否存在
        val topic =
            publicCache.allianceTopicCache.findAllianceTopicBy2Id(player.allianceId, req.topicId)
        if (topic == null) {
            rt.rt = ResultCode.ALLIANCE_TOPIC_NOT_EXISTS.code
            return rt
        }

        // 回复消息
        getAllPlayerTopicReply(publicCache, player, topic, req.replyId, com.point18.slg2d.common.constg.TOPIC_REPLY_COUNT_PRE_PAGE).forEach {
            rt.addReplys(it)
        }

        // 设置玩家已经读取邮件主题
        if (req.replyId == 0L) {
            val m = mutableMapOf<Long, Int>()
            m[player.id] = 1

            // 重新保存新的已读信息
            val bs = toJson(m)
            topic.read = bs
        }

        return rt
    }

    fun newAllianceTopicGetReplyAskRtBuilder(): AllianceTopicGetReplyAskRt.Builder {
        val rt = AllianceTopicGetReplyAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}