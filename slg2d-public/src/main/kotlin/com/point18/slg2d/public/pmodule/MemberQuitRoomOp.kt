package com.point18.slg2d.public.pmodule

import com.point18.slg2d.public.datacache.PublicCache
import pb4server.Home2PublicAskReq
import pb4server.Home2PublicAskResp
import pb4server.QuitOneRoomAskRt
import com.point18.slg2d.common.resultcode.ResultCode

class MemberQuitRoomOp : Home2PublicAskDealBase() {
    override fun dealHomeAsk(publicCache: PublicCache, req: Home2PublicAskReq, resp: Home2PublicAskResp.Builder) {
        val rt = QuitOneRoomAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.chatRoomId = 0
        rt.roomName = ""
        rt.unreadNum = 1
        rt.memberNum = 0
        rt.roomPlayerId = 0
        rt.lastSendTime = 0
        val recv = req.quitOneRoomAskReq
        val roomId = recv.roomId
        val myRoom = publicCache.chatRoomCache.findChatRoomById(roomId)
        if (myRoom == null) {
            rt.rt = ResultCode.NO_CHAT_ROOM.code
            resp.setQuitOneRoomAskRt(rt)
            return
        }

        val member = myRoom.members.firstOrNull { it.playerId == req.playerId }
        if (member == null) {
            rt.rt = ResultCode.NO_AT_ROOM.code
            resp.setQuitOneRoomAskRt(rt)
            return
        }

        // 群主不能退出,只能解散
        if (myRoom.playerId == req.playerId) {
            rt.rt = ResultCode.OWNER_CAN_NOT_QUIT.code
            resp.setQuitOneRoomAskRt(rt)
            return
        }

        if (member.isShow) {
            myRoom.iconProtoIds.remove(member.iconProto)
        }
        myRoom.members.remove(member)
        myRoom.memberNum--

        rt.chatRoomId = myRoom.id
        rt.roomName = myRoom.roomName
        rt.unreadNum = 1
        rt.addAllIconProtoIds(myRoom.iconProtoIds)
        rt.memberNum = myRoom.memberNum
        rt.roomPlayerId = myRoom.playerId
        rt.lastSendTime = myRoom.lastChatTime
        for (eachMember in myRoom.members) {
            rt.addMemberIds(eachMember.playerId)
        }

        resp.setQuitOneRoomAskRt(rt)
        return
    }
}