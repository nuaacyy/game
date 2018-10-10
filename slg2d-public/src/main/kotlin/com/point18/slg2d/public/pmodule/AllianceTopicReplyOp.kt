package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.public.common.dealAfterAlliancePublishTopic
import com.point18.slg2d.public.datacache.AllianceMember
import com.point18.slg2d.public.datacache.PublicCache
import pb4server.AllianceTopicReplyAskReq
import pb4server.AllianceTopicReplyAskRt
import pb4server.World2PublicAskReq
import pb4server.World2PublicAskResp
import java.util.*

class AllianceTopicReplyOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(
        publicCache: PublicCache,
        req: World2PublicAskReq,
        resp: World2PublicAskResp.Builder
    ) {
        val internalMessage = req.allianceTopicReplyAskReq
        val allianceId = req.publicId
        val playerId = req.playerId
        val rt = dealMsg(publicCache, allianceId, playerId, internalMessage)

        resp.setAllianceTopicReplyAskRt(rt)
    }

    fun dealMsg(
        publicCache: PublicCache,
        allianceId: Long,
        playerId: Long,
        req: AllianceTopicReplyAskReq
    ): AllianceTopicReplyAskRt.Builder {
        val rt = newAllianceTopicReplyAskRtBuilder()

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
        val aTopic = publicCache.allianceTopicCache.findAllianceTopicBy2Id(allianceId, req.topicId)
        if (aTopic == null) {
            rt.rt = ResultCode.ALLIANCE_TOPIC_NOT_EXISTS.code
            return rt
        }

        // 保存
        val aReply =
            publicCache.allianceReplyCache.createAllianceReply(aTopic.id, playerId, req.message)

        // 返回回复的消息
        val reply = pb4server.AllianceReplyInfoVo.newBuilder()

        reply.replyId = aReply.id
        reply.playerId = player.id
        reply.playerName = player.name
        reply.playerShortName = player.allianceNickName
        reply.photoProtoId = player.photoProtoId
        reply.message = aReply.message
        reply.replyAt = (aReply.replyAt / 1000).toInt()
        reply.vipLv = player.vipLv
        // todo jh pos
        // reply.curentPos = player.curentPos
        reply.curentPos = 0

        val pp = LinkedList<Int>()
        for ((p, _) in player.alliancePosMap) {
            pp.add(p)
        }
        reply.addAllPositions(pp)

        rt.setReply(reply)
        // 查找需要推送的EpNo
        val mdl = aTopic.type
        val isMgrMe = hasRight(player, mdl)
        val sPlayers = LinkedList<AllianceMember>()
        if (isMgrMe) {
            // 管理人员
            val sPlayers = fetchNotifierPlayersByOfManager(publicCache, alce.id, player.id, mdl, aTopic.type, aTopic.createAt)
        } else {
            // 非管理人员: 只向管理人员推送
            val aPlayers = publicCache.allianceMemberCache.findAllianceMembersByAllianceId(alce.id)
            for (aPlayer in aPlayers) {
                if (hasRight(aPlayer, mdl) && aPlayer.allianceAt < aTopic.createAt) {
                    sPlayers.add(aPlayer)
                }
            }
        }
        // 推送消息提醒
        val pltAreas = mutableMapOf<Long, Int>()
        for (am in sPlayers) {
            pltAreas[am.mapPltAreaId] = 1
        }

        for ((pltAreaId, _) in pltAreas) {
            dealAfterAlliancePublishTopic(publicCache.publicActor, pltAreaId, allianceId, aTopic.id)
        }

        // 重新保存新的已读信息
        var m = mutableMapOf<Long, Int>()

        // 如果是非管理人员的回复，需要设置管理人员未读状态
        if (!isMgrMe) {
            m = toObj(aTopic.read)
            for ((pidA, _) in m) {
                val playerA = publicCache.allianceMemberCache.findAllianceMemberById(pidA)
                val isMgr = hasRight(playerA, mdl)
                if (isMgr) {
                    m.remove(pidA)
                }
            }
        }

        // 无论是不是管理人员，都需要设置自己已读
        m[player.id] = 1

        aTopic.lastAt = getNowTime() // 记录最后一次回复时间
        aTopic.read = toJson(m)

        return rt
    }

    fun newAllianceTopicReplyAskRtBuilder(): AllianceTopicReplyAskRt.Builder {
        val rt = AllianceTopicReplyAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        return rt
    }
}