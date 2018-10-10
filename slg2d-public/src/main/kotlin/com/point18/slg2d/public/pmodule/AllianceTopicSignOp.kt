package com.point18.slg2d.public.pmodule

import com.point18.slg2d.public.datacache.PublicCache
import pb4server.AllianceTopicSignAskReq
import pb4server.AllianceTopicSignAskRt
import pb4server.World2PublicAskReq
import pb4server.World2PublicAskResp
import com.point18.slg2d.common.resultcode.ResultCode

class AllianceTopicSignOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(publicCache: PublicCache, req: World2PublicAskReq, resp: World2PublicAskResp.Builder) {
        val internalMessage = req.allianceTopicSignAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val worldId = req.worldId
        val rt = dealMsg(publicCache, allianceId, playerId, worldId, internalMessage)

        resp.setAllianceTopicSignAskRt(rt)
    }

    fun dealMsg(publicCache: PublicCache,
                allianceId: Long,
                playerId: Long,
                worldId: Long,
                req: AllianceTopicSignAskReq): AllianceTopicSignAskRt.Builder {

        val rt = AllianceTopicSignAskRt.newBuilder()

        val player = publicCache.allianceMemberCache.findAllianceMemberById(playerId)
        if (player == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }
        // 验证联盟邮件主题是否存在
        val aTopic =
            publicCache.allianceTopicCache.findAllianceTopicBy2Id(player.allianceId, req.topicId)
        if (aTopic == null) {
            rt.rt = ResultCode.ALLIANCE_TOPIC_NOT_EXISTS.code
            return rt
        }

        val signTopics = player.signTopicList
        val ex = signTopics[req.topicId]

        if (ex != null) {
            signTopics.remove(req.topicId)
        } else {
            signTopics[req.topicId] = 1
        }

        player.signTopicList = signTopics

        return rt
    }
}