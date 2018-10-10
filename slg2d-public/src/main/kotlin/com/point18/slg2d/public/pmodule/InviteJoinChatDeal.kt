package com.point18.slg2d.public.pmodule

import com.point18.slg2d.public.datacache.ChatPlayerInfo
import com.point18.slg2d.public.datacache.PublicCache
import pb4server.Home2PublicAskReq
import pb4server.Home2PublicAskResp
import pb4server.InviteJoinChatAskRt
import com.point18.slg2d.common.resultcode.ResultCode

class InviteJoinChatDeal : Home2PublicAskDealBase() {
    override fun dealHomeAsk(publicCache: PublicCache, req: Home2PublicAskReq, resp: Home2PublicAskResp.Builder) {
        val rt = InviteJoinChatAskRt.newBuilder()
        rt.freshmen = 0
        rt.chatRoomId = 0
        rt.roomName = ""
        rt.unreadNum = 0
        rt.memberNum = 0
        rt.roomPlayerId = 0
        rt.lastSendTime = 0
        rt.rt = ResultCode.SUCCESS.code

        val recv = req.inviteJoinChatAskReq
        val roomId = recv.roomId

        val myRoom = publicCache.chatRoomCache.findChatRoomById(roomId)
        if (myRoom == null || myRoom.playerId != req.playerId) {
            rt.rt = ResultCode.NO_CHAT_ROOM.code
            resp.setInviteJoinChatAskRt(rt)
            return
        }

        if (myRoom.members.firstOrNull { it.playerId == recv.playerIdAdd } != null) {
            rt.rt = ResultCode.HAVE_ROOM.code
            resp.setInviteJoinChatAskRt(rt)
            return
        }

        if (myRoom.members.size >= com.point18.slg2d.common.pc.pcs.basicProtoCache.groupMemberMaxNum) {
            rt.rt = ResultCode.ROOM_FULL.code
            resp.setInviteJoinChatAskRt(rt)
            return
        }

        val tmpMember = ChatPlayerInfo(
            recv.playerIdAdd,
            recv.protoId,
            recv.playerName,
            recv.vipLv,
            recv.areaNo,
            recv.allianceShortName,
            recv.fightValue,
            recv.castleLv,
            recv.playerShortName,
            false
        )

        myRoom.members.add(tmpMember)

        // 聊天室头像
        if (myRoom.iconProtoIds.size < 4) {
            tmpMember.isShow = true
            myRoom.iconProtoIds.add(tmpMember.iconProto)
        }

        // 成员数++
        myRoom.memberNum++

        rt.freshmen = recv.playerIdAdd
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
        resp.setInviteJoinChatAskRt(rt)
    }
}