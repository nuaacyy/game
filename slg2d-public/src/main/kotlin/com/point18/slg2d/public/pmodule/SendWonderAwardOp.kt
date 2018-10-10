package com.point18.slg2d.public.pmodule

import com.point18.slg2d.public.datacache.PublicCache
import pb4server.SendWonderAwardAskRt
import pb4server.World2PublicAskReq
import pb4server.World2PublicAskResp
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.public.common.receiveWonderAward
import com.point18.slg2d.common.resultcode.ResultCode
import java.util.*

class SendWonderAwardOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(publicCache: PublicCache, req: World2PublicAskReq, resp: World2PublicAskResp.Builder) {
        val msg = req.sendWonderAwardAskReq
        val rt = SendWonderAwardAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        //校验是否同联盟
        val player = publicCache.allianceMemberCache.findAllianceMemberById(req.playerId)
        val targetPlayer = publicCache.allianceMemberCache.findAllianceMemberById(msg.targetPlayerId)
        if (player == null || targetPlayer == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            resp.setSendWonderAwardAskRt(rt)
            return
        }
        if (player.allianceId == 0L || player.allianceId != targetPlayer.allianceId) {
            rt.rt = ResultCode.ALLIANCE_PLAYER_NO_JOIN.code
            resp.setSendWonderAwardAskRt(rt)
            return
        }

        val alliance = publicCache.allianceCache.findAllianceById(player.allianceId)
        if (alliance == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            resp.setSendWonderAwardAskRt(rt)
            return
        }

        //校验是否已发奖励、剩余次数
        var awardList = alliance.wonderAwardMap[msg.awardId]
        if (awardList == null) {
            rt.rt = ResultCode.NO_PROTO.code
            resp.setSendWonderAwardAskRt(rt)
            return
        }

        awardList = LinkedList()
        var awardMap = alliance.wonderAwardMap
        awardMap[msg.awardId] = awardList
        alliance.wonderAwardMap = awardMap

        val awardProto = pcs.kingAwardProtoCache.kingAwardProtoMap[msg.awardId]
        if (awardProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            resp.setSendWonderAwardAskRt(rt)
            return
        }

        if (awardList.size >= awardProto.limit) {
            rt.rt = ResultCode.AWARD_NUM_LIMIT.code
            resp.setSendWonderAwardAskRt(rt)
            return
        }
        for (id in awardList) {
            if (id == msg.targetPlayerId) {
                rt.rt = ResultCode.HAVE_AWARDED.code
                resp.setSendWonderAwardAskRt(rt)
                return
            }
        }

        //添加发奖记录
        awardMap = alliance.wonderAwardMap
        awardList.add(msg.targetPlayerId)
        awardMap[msg.awardId] = awardList
        alliance.wonderAwardMap = awardMap

        //推送给游戏服  这个推送是为了发邮件
        receiveWonderAward(publicCache.publicActor, targetPlayer.mapPltAreaId, targetPlayer.id, msg.awardId)
        resp.setSendWonderAwardAskRt(rt)
        return
    }
}