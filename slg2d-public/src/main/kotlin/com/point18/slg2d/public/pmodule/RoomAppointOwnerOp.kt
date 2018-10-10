package com.point18.slg2d.public.pmodule

import com.point18.slg2d.public.datacache.PublicCache
import pb4server.Home2PublicAskReq
import pb4server.Home2PublicAskResp
import pb4server.RoomOwnerChangeAskRt
import com.point18.slg2d.common.resultcode.ResultCode

class RoomAppointOwnerOp : Home2PublicAskDealBase() {
    override fun dealHomeAsk(publicCache: PublicCache, req: Home2PublicAskReq, resp: Home2PublicAskResp.Builder) {
        val recv = req.roomOwnerChangeAskReq
        val rt = newRoomOwnerChangeAskRt()
        rt.rt = ResultCode.SUCCESS.code
        val room = publicCache.chatRoomCache.findChatRoomById(recv.roomId)
        if (room == null || room.playerId != req.playerId) {
            rt.rt = ResultCode.NO_CHAT_ROOM.code
            resp.setRoomOwnerChangeAskRt(rt)
            return
        }

        val newRoom = publicCache.chatRoomCache.changeChatRoomOwner(
            req.playerId,
            recv.newOwner,
            recv.roomId
        )
        if (newRoom == null) {
            rt.rt = ResultCode.NO_CHAT_ROOM.code
            resp.setRoomOwnerChangeAskRt(rt)
            return
        }

        rt.chatRoomId = newRoom.id
        rt.addAllIconProtoIds(newRoom.iconProtoIds)
        rt.memberNum = newRoom.memberNum
        rt.roomName = newRoom.roomName
        rt.unreadNum = 1
        rt.roomPlayerId = newRoom.playerId
        rt.lastSendTime = newRoom.lastChatTime
        for (eachPlayer in newRoom.members) {
            rt.addMembers(eachPlayer.playerId)
        }
        resp.setRoomOwnerChangeAskRt(rt)
    }

    private fun newRoomOwnerChangeAskRt(): RoomOwnerChangeAskRt.Builder {
        val rt = RoomOwnerChangeAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.chatRoomId = 0
        rt.memberNum = 0
        rt.roomName = ""
        rt.unreadNum = 1
        rt.roomPlayerId = 0
        rt.lastSendTime = 0
        return rt
    }
}