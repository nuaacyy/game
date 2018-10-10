package com.point18.slg2d.public.pmodule

import com.point18.slg2d.public.datacache.PublicCache
import pb4server.Home2PublicAskReq
import pb4server.Home2PublicAskResp
import pb4server.KickOutChatRoomAskRt
import com.point18.slg2d.common.resultcode.ResultCode

class KickOutChatMemberOp : Home2PublicAskDealBase() {
    override fun dealHomeAsk(publicCache: PublicCache, req: Home2PublicAskReq, resp: Home2PublicAskResp.Builder) {
        val rt = KickOutChatRoomAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.chatRoomId = 0
        rt.roomName = ""
        rt.unreadNum = 1
        rt.memberNum = 0
        rt.roomPlayerId = 0
        rt.lastSendTime = 0
        val recv = req.kickOutChatRoomAskReq
        val roomId = recv.roomId
        val myRoom = publicCache.chatRoomCache.findChatRoomById(roomId)
        if (myRoom == null || myRoom.playerId != req.playerId) {
            rt.rt = ResultCode.NO_CHAT_ROOM.code
            resp.setKickOutChatRoomAskRt(rt)
            return
        }

        val member = myRoom.members.firstOrNull { it.playerId == recv.playerIdRemove }
        if (member == null) {
            rt.rt = ResultCode.NO_AT_ROOM.code
            resp.setKickOutChatRoomAskRt(rt)
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

        resp.setKickOutChatRoomAskRt(rt)
        return
    }
}