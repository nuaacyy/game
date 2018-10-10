package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.public.datacache.AllianceChat
import com.point18.slg2d.public.datacache.PublicCache
import pb4server.SendAllianceMassAskRt
import pb4server.World2PublicAskReq
import pb4server.World2PublicAskResp

class SendAllianceMassInfoOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(
        publicCache: PublicCache,
        req: World2PublicAskReq,
        resp: World2PublicAskResp.Builder
    ) {

        val rt = SendAllianceMassAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val member = publicCache.allianceMemberCache.findAllianceMemberById(req.playerId)
        if (member == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            resp.setSendAllianceMassAskRt(rt)
            return
        }

        // todo jh pos
        //rt.pos = member.curentPos
        rt.pos = 0

        val alliance = publicCache.allianceCache.findAllianceById(member.allianceId)
        if (alliance == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            resp.setSendAllianceMassAskRt(rt)
            return
        }

        val allianceChat = AllianceChat()
        allianceChat.allianceId = req.publicId
        allianceChat.msg = req.sendAllianceMassAskReq.message
        allianceChat.msgType = req.sendAllianceMassAskReq.messageType
        allianceChat.areaNo = req.sendAllianceMassAskReq.areaNo
        allianceChat.playerId = req.playerId
        allianceChat.vipLv = req.sendAllianceMassAskReq.vipLv
        // todo jh pos
        //allianceChat.alliancePos = member.curentPos
        allianceChat.alliancePos = 0
        allianceChat.allianceName = req.sendAllianceMassAskReq.allianceName
        allianceChat.allianceShortName = req.sendAllianceMassAskReq.allianceShortName
        allianceChat.playerName = req.sendAllianceMassAskReq.playerName
        allianceChat.playerShortName = req.sendAllianceMassAskReq.playerShortName
        allianceChat.kingdomPos
        allianceChat.wonderPos = req.sendAllianceMassAskReq.wonderPos
        allianceChat.chatTime = getNowTime()
        allianceChat.pltAreaNo = req.sendAllianceMassAskReq.pltAreaId
        allianceChat.iconProtoId = req.sendAllianceMassAskReq.iconProtoId

        publicCache.allianceChatCache.createAllianceChat(allianceChat)
        rt.chatId = allianceChat.id
        resp.setSendAllianceMassAskRt(rt)
        return
    }
}