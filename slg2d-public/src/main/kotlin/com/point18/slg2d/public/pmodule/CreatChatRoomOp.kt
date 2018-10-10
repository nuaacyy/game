package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.public.datacache.PublicCache
import pb4server.CreateRoomAskRt
import pb4server.Home2PublicAskReq
import pb4server.Home2PublicAskResp
import com.point18.slg2d.common.resultcode.ResultCode

class CreatChatRoomOp : Home2PublicAskDealBase() {

    override fun dealHomeAsk(publicCache: PublicCache, req: Home2PublicAskReq, resp: Home2PublicAskResp.Builder) {
        val name = req.createRoomAskReq.nameRoom
        val rt = newCreateRoomAskRt()

        // 自己创建的聊天室不能重名
        val myRoom = publicCache.chatRoomCache.findChatRoomByPlayerId(req.playerId)
        if (myRoom.firstOrNull { it.roomName == name } != null) {
            rt.rt = ResultCode.SAME_ROOM_NAME.code
            resp.setCreateRoomAskRt(rt)
            return
        }

        // 验证数量
        if (myRoom.size >= com.point18.slg2d.common.pc.pcs.basicProtoCache.createGroupNumLimit) {
            rt.rt = ResultCode.NO_MORE_ROOM.code
            resp.setCreateRoomAskRt(rt)
            return
        }

        // 创建推送一个新聊天室
        val newRoom = publicCache.chatRoomCache.createChatRoom(
            req.publicId,
            req.playerId,
            getNowTime(),
            name,
            req.createRoomAskReq.iconProto,
            req.createRoomAskReq.playerName,
            req.createRoomAskReq.vipLv,
            req.createRoomAskReq.areaNo,
            req.createRoomAskReq.allianceShortName,
            req.createRoomAskReq.fightValue,
            req.createRoomAskReq.castleLv,
            req.createRoomAskReq.playerName
        )

        rt.chatRoomId = newRoom.id
        rt.addAllIconProtoIds(newRoom.iconProtoIds)
        rt.roomName = name
        rt.unreadNum = 1
        rt.memberNum = newRoom.memberNum
        rt.roomPlayerId = newRoom.playerId

        resp.setCreateRoomAskRt(rt)
    }

    private fun newCreateRoomAskRt(): CreateRoomAskRt.Builder {
        val rt = CreateRoomAskRt.newBuilder()
        rt.rt = 1
        rt.chatRoomId = 0
        rt.roomName = ""
        rt.unreadNum = 0
        rt.memberNum = 0
        rt.roomPlayerId = 0
        return rt
    }
}