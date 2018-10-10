package com.point18.slg2d.public.pmodule

import com.point18.slg2d.public.datacache.PublicCache
import pb4server.*
import com.point18.slg2d.common.resultcode.ResultCode

class GetChatRoomHistoryOp : Home2PublicAskDealBase() {

    override fun dealHomeAsk(publicCache: PublicCache, req: Home2PublicAskReq, resp: Home2PublicAskResp.Builder) {
        val rt = GetGroupChatAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        val recv = req.getGroupChatAskReq
        val msgId = recv.chatId
        val roomId = recv.roomId
        val playerId = req.playerId

        val history = publicCache.roomChatRecordsCache.getRoomChatRecordHistory(msgId, roomId)
        for (eachChat in history) {
            val chatInfoBuilder = ChatInfoVo.newBuilder()
            chatInfoBuilder.isSystem = 1
            chatInfoBuilder.country = 24
            chatInfoBuilder.allianceName = eachChat.allianceName
            chatInfoBuilder.allianceShortName = eachChat.allianceShortName
            chatInfoBuilder.alliancePositions = eachChat.alliancePos.toString()
            chatInfoBuilder.player = eachChat.playerName
            chatInfoBuilder.playerShortName = eachChat.playerShortName
            chatInfoBuilder.playerIcon = eachChat.iconProtoId
            val noticeVo = NoticeVo.newBuilder()
            noticeVo.noticeLanId = eachChat.msg
            noticeVo.readType = 1
            chatInfoBuilder.message = noticeVo.build()
            chatInfoBuilder.sendTime = (eachChat.chatTime / 1000).toInt()
            chatInfoBuilder.id = eachChat.id
            chatInfoBuilder.playerId = eachChat.playerId
            chatInfoBuilder.messageType = eachChat.msgType
            chatInfoBuilder.office = eachChat.wonderPos
            chatInfoBuilder.vipLv = eachChat.vipLv
            chatInfoBuilder.areaNo = eachChat.areaNo
            chatInfoBuilder.chatRoomId = eachChat.roomId

            rt.addChatRecordList(chatInfoBuilder)
        }
        resp.setGetGroupChatAskRt(rt)
    }
}