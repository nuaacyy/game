package com.point18.slg2d.public.pmodule

import com.point18.slg2d.public.datacache.PublicCache
import pb4server.GroupMemberVo
import pb4server.Home2PublicAskReq
import pb4server.Home2PublicAskResp
import pb4server.QueryMemberInRoomAskRt
import com.point18.slg2d.common.resultcode.ResultCode

class QueryMemberChatDeal : Home2PublicAskDealBase() {
    override fun dealHomeAsk(publicCache: PublicCache, req: Home2PublicAskReq, resp: Home2PublicAskResp.Builder) {

        val recv = req.queryMemberInRoomAskReq
        val ids = recv.roomId
        val rt = QueryMemberInRoomAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        val room = publicCache.chatRoomCache.findChatRoomById(ids)
        if (room == null) {
            rt.rt = ResultCode.NO_CHAT_ROOM.code
            resp.setQueryMemberInRoomAskRt(rt)
            return
        }

        for (eachMember in room.members) {
            val tmpGroupMember = GroupMemberVo.newBuilder()
            tmpGroupMember.playerId = eachMember.playerId // 玩家id
            tmpGroupMember.playerName = eachMember.playerName // 玩家名字
            tmpGroupMember.protoId = eachMember.iconProto // 头像ID
            tmpGroupMember.vipLv = eachMember.vipLv
            tmpGroupMember.areaNo = eachMember.areaNo
            tmpGroupMember.allianceShortName = eachMember.allianceShortName
            tmpGroupMember.fightValue = eachMember.playerId
            tmpGroupMember.castleLv = eachMember.castleLv
            tmpGroupMember.playerShortName = eachMember.playerShortName
            rt.addGroupMember(tmpGroupMember.build())
        }
        resp.setQueryMemberInRoomAskRt(rt)
        return
    }
}