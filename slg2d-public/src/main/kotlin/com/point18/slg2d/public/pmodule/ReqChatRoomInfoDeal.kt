package com.point18.slg2d.public.pmodule

import com.point18.slg2d.public.datacache.PublicCache
import pb4client.ChatRoom
import pb4server.GetChatroomAskRt
import pb4server.Home2PublicAskReq
import pb4server.Home2PublicAskResp

class ReqChatRoomInfoDeal : Home2PublicAskDealBase() {
    override fun dealHomeAsk(publicCache: PublicCache, req: Home2PublicAskReq, resp: Home2PublicAskResp.Builder) {

        val recv = req.getChatroomAskReq
        val eachRoomIds = recv.roomId
        val rt = GetChatroomAskRt.newBuilder()
        val tmp = ChatRoom.newBuilder()
        tmp.chatRoomId = 0
        tmp.chatRoomName = ""
        tmp.memberNum = 0
        tmp.playerId = 0
        tmp.lastTalkTime = 0
        tmp.lastReadTime = 0
        val room = publicCache.chatRoomCache.findChatRoomById(eachRoomIds)
        if (room == null) {
            rt.chatRoomVo = tmp.build()
            resp.setGetChatroomAskRt(rt)
            return
        }

        if (room.members.firstOrNull { it.playerId == req.playerId } != null) {
            tmp.chatRoomId = room.id
            tmp.chatRoomName = room.roomName
            tmp.addAllMemberIcons(room.iconProtoIds)
            tmp.memberNum = room.memberNum
            tmp.playerId = room.playerId
            tmp.lastReadTime = 0
            tmp.lastTalkTime = room.lastChatTime
        }
        rt.chatRoomVo = tmp.build()

        resp.setGetChatroomAskRt(rt)
        return
    }
}