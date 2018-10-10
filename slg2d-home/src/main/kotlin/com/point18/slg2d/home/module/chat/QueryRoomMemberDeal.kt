package com.point18.slg2d.home.module.chat

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.GroupMember
import pb4client.QueryGroupMembers
import pb4client.QueryGroupMembersRt
import pb4server.Home2PublicAskResp
import pb4server.QueryMemberInRoomAskReq

class QueryRoomMemberDeal : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) {
            val msgReceive = msg as QueryGroupMembers
            val roomId = msgReceive.groupId
            val rt = queryMember(session, roomId)
            if (rt != null) {
                session.sendMsg(MsgType.QueryGroupMembers_314, rt)
            }
        }
    }

    private fun queryMember(session: PlayerActor, groupId: Long): QueryGroupMembersRt? {

        val askMsg = QueryMemberInRoomAskReq.newBuilder()
        askMsg.roomId = groupId
        session.createACS<Home2PublicAskResp>().ask(
            session.publicShardProxy,
            session.fillHome2PublicAskMsgHeader(groupId) { it.setQueryMemberInRoomAskReq(askMsg) },
            Home2PublicAskResp::class.java
        ).whenCompleteKt { prt, askErr ->
            val rt = QueryGroupMembersRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code

            if (askErr != null || prt == null) {
                println("返回数据失败")
            } else {
                rt.rt = prt.queryMemberInRoomAskRt.rt
                for (eachMember in prt.queryMemberInRoomAskRt.groupMemberList) {
                    rt.addGroupMember(PbGroupMember(eachMember).build())
                }
                session.sendMsg(MsgType.QueryGroupMembers_314, rt.build())
            }
        }

        return null
    }
}

class PbGroupMember() {
    private val builder = GroupMember.newBuilder()

    constructor(vo: pb4server.GroupMemberVo) : this() {
        this.builder.playerId = vo.playerId
        this.builder.playerName = vo.playerName
        this.builder.protoId = vo.protoId
        this.builder.vipLv = vo.vipLv
        this.builder.areaNo = vo.areaNo
        this.builder.allianceShortName = vo.allianceShortName
        this.builder.fightValue = vo.fightValue
        this.builder.castleLv = vo.castleLv
        this.builder.playerShortName = vo.playerShortName
    }

    fun build(): GroupMember {
        return this.builder.build()
    }
}