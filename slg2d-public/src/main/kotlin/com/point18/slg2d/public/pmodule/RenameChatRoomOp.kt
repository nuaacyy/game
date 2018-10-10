package com.point18.slg2d.public.pmodule

import com.point18.slg2d.public.datacache.PublicCache
import pb4server.Home2PublicAskReq
import pb4server.Home2PublicAskResp
import pb4server.RenameChatRoomAskRt
import com.point18.slg2d.common.resultcode.ResultCode

class RenameChatRoomOp : Home2PublicAskDealBase() {
    override fun dealHomeAsk(publicCache: PublicCache, req: Home2PublicAskReq, resp: Home2PublicAskResp.Builder) {
        val recv = req.renameChatRoomAskReq
        val id = recv.roomId
        val newName = recv.roomName
        val rt = RenameChatRoomAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val room = publicCache.chatRoomCache.findChatRoomById(id)
        if (room == null) {
            rt.rt = ResultCode.NO_CHAT_ROOM.code
            resp.setRenameChatRoomAskRt(rt)
            return
        }

        if (newName == room.roomName) {
            rt.rt = ResultCode.SAME_ROOM_NAME.code
            resp.setRenameChatRoomAskRt(rt)
            return
        }
        room.roomName = newName
        rt.chatRoomId = room.id
        rt.addAllIconProtoIds(room.iconProtoIds)
        rt.memberNum = room.memberNum
        rt.roomPlayerId = room.playerId
        rt.unreadNum = 1

        for (eachMember in room.members) {
            rt.addMembers(eachMember.playerId)
        }
        resp.setRenameChatRoomAskRt(rt)
        return
    }
}