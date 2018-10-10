package com.point18.slg2d.public.pmodule

import com.point18.slg2d.public.datacache.*
import com.point18.slg2d.common.commonfunc.getNowTime
import pb4server.Home2PublicAskReq
import pb4server.Home2PublicAskResp
import pb4server.SendRoomMsgAskRt
import com.point18.slg2d.common.resultcode.ResultCode

class SendChatRoomMsgOp : Home2PublicAskDealBase() {

    override fun dealHomeAsk(publicCache: PublicCache, req: Home2PublicAskReq, resp: Home2PublicAskResp.Builder) {
        val rt = SendRoomMsgAskRt.newBuilder()
        rt.rt = ResultCode.NO_CHAT_ROOM.code
        rt.chatId = 0

        val recv =req.sendRoomMsgAskReq
        val fightInfo = recv.easyFightId
        val msgType = recv.messageType
        val allianceId = req.publicId
        val playerId = req.playerId

        // 做验证
        val room = publicCache.chatRoomCache.findChatRoomById(recv.roomId)
        if (room == null){
            rt.rt = ResultCode.NO_CHAT_ROOM.code
            resp.setSendRoomMsgAskRt(rt)
            return
        }
        val playerInRoom = room.members.firstOrNull { it.playerId == playerId }
        if (playerInRoom == null){
            rt.rt = ResultCode.NO_AT_ROOM.code
            resp.setSendRoomMsgAskRt(rt)
            return
        }
        rt.rt = ResultCode.SUCCESS.code

        // 聊天cd
        val now = getNowTime()
        val chatRecords = publicCache.roomChatRecordsCache.getRoomChatRecordHistory(0, recv.roomId)
        val myChatContent = chatRecords.filter { it.playerId == playerId }.sortedByDescending { it.chatTime }
        if (myChatContent.isNotEmpty()) {
            if (now - myChatContent[0].chatTime < com.point18.slg2d.common.pc.pcs.basicProtoCache.groupChatSpaceTime * 1000) {
                rt.rt = ResultCode.WAIT_TALK.code
                resp.setSendRoomMsgAskRt(rt)
                return
            }
        }

        val chatRecord = RoomChatRecord()
        chatRecord.roomId = recv.roomId
        chatRecord.allianceId = allianceId
        chatRecord.msg = recv.message
        chatRecord.msgType = msgType
        chatRecord.areaNo = recv.areaNo
        chatRecord.playerId = playerId
        chatRecord.vipLv = recv.vipLv
        chatRecord.alliancePos = recv.alliancePos
        chatRecord.allianceName = recv.allianceName
        chatRecord.allianceShortName = recv.allianceShortName
        chatRecord.playerName = recv.playerName
        chatRecord.playerShortName = recv.playerShortName
        chatRecord.kingdomPos = 0
        chatRecord.wonderPos = recv.wonderPos
        chatRecord.chatTime = getNowTime()
        chatRecord.pltAreaNo = recv.pltAreaId
        chatRecord.iconProtoId = recv.iconProtoId
        chatRecord.publicId = recv.roomId

        publicCache.roomChatRecordsCache.createRoomChatRecord(chatRecord)

        room.lastChatTime = getNowTime()
        rt.chatId = chatRecord.id
        resp.setSendRoomMsgAskRt(rt)
        return
    }
}