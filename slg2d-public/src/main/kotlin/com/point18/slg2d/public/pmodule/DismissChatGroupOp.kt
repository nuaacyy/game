package com.point18.slg2d.public.pmodule

import com.point18.slg2d.public.datacache.PublicCache
import pb4server.DissmissChatRoomAskRt
import pb4server.Home2PublicAskReq
import pb4server.Home2PublicAskResp
import com.point18.slg2d.common.resultcode.ResultCode

class DismissChatGroupOp : Home2PublicAskDealBase() {

    override fun dealHomeAsk(publicCache: PublicCache, req: Home2PublicAskReq, resp: Home2PublicAskResp.Builder) {
        val roomId = req.dissmissChatRoomAskReq.roomId
        val rt = DissmissChatRoomAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        resp.setDissmissChatRoomAskRt(rt)

        // 查自己创建的聊天室
        val myRoom = publicCache.chatRoomCache.findChatRoomById(roomId)
        if (myRoom == null || myRoom.playerId != req.playerId) {
            rt.rt = ResultCode.NO_CHAT_ROOM.code
            resp.setDissmissChatRoomAskRt(rt)
            return
        }

        rt.chatRoomId = myRoom.id
        rt.addAllIconProtoIds(myRoom.iconProtoIds)
        rt.roomName = myRoom.roomName
        rt.unreadNum = 1
        rt.memberNum = myRoom.memberNum
        rt.roomPlayerId = myRoom.playerId
        for (eachMember in myRoom.members) {
            rt.addMembers(eachMember.playerId)
        }

        // 删除这个聊天室
        publicCache.chatRoomCache.deleteChatRoom(myRoom)
        publicCache.roomChatRecordsCache.clearRoomChatRecord(myRoom.id)

        resp.setDissmissChatRoomAskRt(rt)
    }

    private fun newDissmissChatRoomAskRt(): DissmissChatRoomAskRt.Builder {
        val rt = DissmissChatRoomAskRt.newBuilder()
        rt.chatRoomId = 0
        rt.roomName = ""
        rt.unreadNum = 1
        rt.memberNum = 0
        rt.roomPlayerId = 0

        return rt
    }
}