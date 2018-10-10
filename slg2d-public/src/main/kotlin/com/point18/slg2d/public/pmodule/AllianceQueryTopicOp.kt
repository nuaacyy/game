package com.point18.slg2d.public.pmodule

import com.point18.slg2d.public.datacache.*
import pb4server.*
import com.point18.slg2d.common.resultcode.ResultCode

class AllianceQueryTopicOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(
        publicCache: PublicCache,
        req: World2PublicAskReq,
        resp: World2PublicAskResp.Builder
    ) {
        val internalMessage = req.allianceQueryTopicAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMessage)

        resp.setAllianceQueryTopicAskRt(rt)
    }

     fun dealMsg(
         publicCache: PublicCache,
         allianceId: Long,
         playerId: Long,
         req: AllianceQueryTopicAskReq
     ): AllianceQueryTopicAskRt.Builder {
        val rt = newAllianceQueryTopicAskRtBuilder()

        // 玩家是否有联盟
        val alce = publicCache.allianceCache.findAllianceById(allianceId)
        if (alce == null) {
            rt.rt = ResultCode.ALLIANCE_PLAYER_NO_JOIN.code
            return rt
        }

        val player = publicCache.allianceMemberCache.findAllianceMemberById(playerId)
        if (player == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }
        var signTopics = player.signTopicList

        // 先清理一下玩家的收藏列表
        val deletes = mutableMapOf<Long, Int>()
        for ((tId, _) in signTopics) {
            if (publicCache.allianceTopicCache.findAllianceTopicBy2Id(alce.id, tId) == null) {
                deletes[tId] = 1
            }
        }

        for ((dId, _) in deletes) {
            signTopics.remove(dId)
        }
        player.signTopicList = signTopics
        signTopics = player.signTopicList

        // 查找联盟的所有邮件主题
        val topics = publicCache.allianceTopicCache.findAllianceTopicsByAllianceId(alce.id)
        for ((_, topic) in topics) {

            // 筛选符合玩家应该看到的回复消息
            val replies = getPlayerTopicReply(publicCache, player, topic, 0, 1) // 可能查询到的联盟主题是当前玩家不应该看到的，所以需要先查询一下
            if ((replies.size) > 0) {

                // 玩家是否读取邮件主题
                var isRead = 0
                val m = mutableMapOf<Long, Int>()
                val ok = m[player.id]
                if (ok != null) {
                    isRead = 1
                }

                var isSign = 0
                val ex2 = signTopics[topic.id]
                if (ex2 != null) {
                    isSign = 1
                }

                val playerM = publicCache.allianceMemberCache.findAllianceMemberById(topic.playerId)
                if (playerM == null) {
                    continue
                }
                val pbTopic = AllianceTopicInfoVo.newBuilder()

                pbTopic.topicId = topic.id
                pbTopic.type = topic.type
                pbTopic.playerId = playerM.id
                pbTopic.playerName = playerM.name
                pbTopic.photoProtoId = playerM.photoProtoId
                pbTopic.title = topic.title
                pbTopic.lastAt = (topic.lastAt / 1000).toInt()
                pbTopic.isRead = isRead
                pbTopic.isSign = isSign


                rt.addTopics(pbTopic)
            }
        }

        return rt
    }

    fun newAllianceQueryTopicAskRtBuilder(): AllianceQueryTopicAskRt.Builder {
        val rt = AllianceQueryTopicAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}