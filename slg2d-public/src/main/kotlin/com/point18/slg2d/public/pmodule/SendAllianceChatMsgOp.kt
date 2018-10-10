package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.public.datacache.AllianceChat
import com.point18.slg2d.public.datacache.PublicCache
import pb4server.Home2PublicAskReq
import pb4server.Home2PublicAskResp
import pb4server.SendAllianceChatAskRt

class SendAllianceChatMsgOp : Home2PublicAskDealBase() {

    override fun dealHomeAsk(publicCache: PublicCache, req: Home2PublicAskReq, resp: Home2PublicAskResp.Builder) {
        val rt = SendAllianceChatAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val recv = req.sendAllianceChatAskReq
        val fightInfo = recv.easyFightId
        val msgType = recv.messageType
        val allianceId = req.publicId
        val playerId = req.playerId

        val player = publicCache.allianceMemberCache.findAllianceMemberById(playerId)
        if (player == null) {
            rt.rt = ResultCode.NO_PLAYER.code
            resp.setSendAllianceChatAskRt(rt)
            return
        }

        val allianceChat = AllianceChat()
        allianceChat.allianceId = allianceId
        allianceChat.msg = recv.message
        allianceChat.msgType = msgType
        allianceChat.areaNo = recv.areaNo
        allianceChat.playerId = playerId
        allianceChat.vipLv = recv.vipLv
        // todo jh pos
        allianceChat.alliancePos = 0
        // allianceChat.alliancePos = player.curentPos
        allianceChat.allianceName = recv.allianceName
        allianceChat.allianceShortName = recv.allianceShortName
        allianceChat.playerName = recv.playerName
        allianceChat.playerShortName = recv.playerShortName
        allianceChat.kingdomPos = recv.kingdomPos
        allianceChat.wonderPos = recv.wonderPos
        allianceChat.chatTime = getNowTime()
        allianceChat.pltAreaNo = recv.pltAreaId
        allianceChat.iconProtoId = recv.iconProtoId

        publicCache.allianceChatCache.createAllianceChat(allianceChat)
        rt.chatId = allianceChat.id
        resp.setSendAllianceChatAskRt(rt)
        return
    }
}