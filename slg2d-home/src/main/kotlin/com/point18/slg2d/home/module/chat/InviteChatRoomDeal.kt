package com.point18.slg2d.home.module.chat

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.msgnotice.createRoomNotifierExtend
import pb4client.AddGroupChatMember
import pb4client.AddGroupChatMemberRt
import pb4client.GroupMember
import pb4server.*

class InviteChatRoomDeal : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) {
            val receiveMsg = msg as AddGroupChatMember
            val roomId = receiveMsg.roomId
            val players = receiveMsg.playerId

            val rt = invitePlayers(session, roomId, players)
            if (rt != null) {
                session.sendMsg(MsgType.AddGroupChatMember_315, rt)
            }
        }
    }

    private fun invitePlayers(session: PlayerActor, roomId: Long, playerIdAdd: Long): AddGroupChatMemberRt? {

        // 去home找这个player的信息
        val askHomeMsg = BeforeJoinRoomAskReq.newBuilder()
        askHomeMsg.groupId = roomId
        session.createACS<Home2HomeAskResp>().ask(
            session.homeShardProxy,
            session.fillHome2HomeAskMsgHeader {
                it.setBeforeJoinRoomAskReq(askHomeMsg)
                it.playerId = playerIdAdd
            },
            Home2HomeAskResp::class.java
        ).whenCompleteKt { resp, err ->
            val rt = AddGroupChatMemberRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code
            try {

                when {
                    err != null -> {
                        rt.rt = ResultCode.ASK_ERROR1.code
                        session.sendMsg(MsgType.AddGroupChatMember_315, rt.build())

                    }
                    resp == null -> {
                        rt.rt = ResultCode.ASK_ERROR2.code
                        session.sendMsg(MsgType.AddGroupChatMember_315, rt.build())

                    }
                    else -> {
                        if (resp.beforeJoinRoomAskRt.rt != ResultCode.SUCCESS.code) {

                            val newGroupMember = GroupMember.newBuilder()
                            newGroupMember.playerId = playerIdAdd
                            newGroupMember.playerName = resp.beforeJoinRoomAskRt.playerName
                            newGroupMember.protoId = resp.beforeJoinRoomAskRt.protoId
                            newGroupMember.vipLv = resp.beforeJoinRoomAskRt.vipLv
                            newGroupMember.areaNo = resp.beforeJoinRoomAskRt.areaNo
                            newGroupMember.allianceShortName = resp.beforeJoinRoomAskRt.allianceShortName
                            newGroupMember.fightValue = resp.beforeJoinRoomAskRt.fightValue
                            newGroupMember.castleLv = resp.beforeJoinRoomAskRt.castleLv
                            rt.newMember = newGroupMember.build()

                            rt.rt = resp.beforeJoinRoomAskRt.rt
                            session.sendMsg(MsgType.AddGroupChatMember_315, rt.build())
                            return@whenCompleteKt
                        }
                        // 去公共服存这个玩家的 然后让玩家订阅
                        afterFindPlayerInfo(session, roomId, resp.beforeJoinRoomAskRt, playerIdAdd)

                    }
                }

            } catch (e: Exception) {
                normalLog.error("BeforeJoinRoomAskReq Error!", e)
                rt.rt = ResultCode.ASK_ERROR3.code
                session.sendMsg(MsgType.AddGroupChatMember_315, rt.build())
            }
        }
        return null
    }

    private fun afterFindPlayerInfo(session: PlayerActor, roomId: Long, askHomeRes: BeforeJoinRoomAskRt, addPlayer: Long) {
        val askMsg = InviteJoinChatAskReq.newBuilder()
        askMsg.roomId = roomId
        askMsg.playerIdAdd = addPlayer
        askMsg.playerName = askHomeRes.playerName
        askMsg.protoId = askHomeRes.protoId
        askMsg.vipLv = askHomeRes.vipLv
        askMsg.areaNo = askHomeRes.areaNo
        askMsg.allianceShortName = askHomeRes.allianceShortName
        askMsg.fightValue = askHomeRes.fightValue
        askMsg.castleLv = askHomeRes.castleLv
        askMsg.playerShortName = askHomeRes.playerShortName

        session.createACS<Home2PublicAskResp>().ask(
            session.publicShardProxy,
            session.fillHome2PublicAskMsgHeader(roomId) { it.setInviteJoinChatAskReq(askMsg) },
            Home2PublicAskResp::class.java
        ).whenCompleteKt { prt, askErr ->
            val rt = AddGroupChatMemberRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code
            if (askErr != null || prt == null) {
                println("同步数据失败")
            } else if (prt.inviteJoinChatAskRt.rt != ResultCode.SUCCESS.code) {
                rt.rt = prt.inviteJoinChatAskRt.rt

                val newGroupMember = GroupMember.newBuilder()
                newGroupMember.playerId = addPlayer
                newGroupMember.playerName = askHomeRes.playerName
                newGroupMember.protoId = askHomeRes.protoId
                newGroupMember.vipLv = askHomeRes.vipLv
                newGroupMember.areaNo = askHomeRes.areaNo
                newGroupMember.allianceShortName = askHomeRes.allianceShortName
                newGroupMember.fightValue = askHomeRes.fightValue
                newGroupMember.castleLv = askHomeRes.castleLv
                rt.newMember = newGroupMember.build()

                session.sendMsg(MsgType.AddGroupChatMember_315, rt.build())
            } else {
                rt.rt = prt.inviteJoinChatAskRt.rt

                val newGroupMember = GroupMember.newBuilder()
                newGroupMember.playerId = addPlayer
                newGroupMember.playerName = askHomeRes.playerName
                newGroupMember.protoId = askHomeRes.protoId
                newGroupMember.vipLv = askHomeRes.vipLv
                newGroupMember.areaNo = askHomeRes.areaNo
                newGroupMember.allianceShortName = askHomeRes.allianceShortName
                newGroupMember.fightValue = askHomeRes.fightValue
                newGroupMember.castleLv = askHomeRes.castleLv
                rt.newMember = newGroupMember.build()

                //  告诉home去订阅这个channel 并且记录下这个频道
                val tellHomeMsg = JoinChatRoomTell.newBuilder()
                tellHomeMsg.chatRoomId = prt.inviteJoinChatAskRt.chatRoomId
                tellHomeMsg.roomName = prt.inviteJoinChatAskRt.roomName
                tellHomeMsg.unreadNum = prt.inviteJoinChatAskRt.unreadNum
                tellHomeMsg.addAllIconProtoIds(prt.inviteJoinChatAskRt.iconProtoIdsList)
                tellHomeMsg.memberNum = prt.inviteJoinChatAskRt.memberNum
                tellHomeMsg.roomPlayerId = prt.inviteJoinChatAskRt.roomPlayerId
                tellHomeMsg.lastTalkTime = prt.inviteJoinChatAskRt.lastSendTime

                val home2homeTell = session.fillHome2HomeTellMsgHeader(
                    prt.inviteJoinChatAskRt.freshmen
                ) { it.setJoinChatRoomTell(tellHomeMsg) }
                session.tellHome(home2homeTell)

                session.sendMsg(MsgType.AddGroupChatMember_315, rt.build())
                createRoomNotifierExtend(
                    prt.inviteJoinChatAskRt.chatRoomId,
                    prt.inviteJoinChatAskRt.roomName,
                    prt.inviteJoinChatAskRt.unreadNum,
                    prt.inviteJoinChatAskRt.iconProtoIdsList,
                    prt.inviteJoinChatAskRt.memberNum,
                    prt.inviteJoinChatAskRt.roomPlayerId,
                    getNowTime() / 1000,
                    prt.inviteJoinChatAskRt.lastSendTime / 1000
                ).notice(session)
                prt.inviteJoinChatAskRt.memberIdsList.remove(session.playerId)

                // 通知人数变化
                for (eachMember in prt.inviteJoinChatAskRt.memberIdsList) {
                    val tellMsg = ChatRoomChangeInfoTell.newBuilder()
                    tellMsg.chatRoomId = prt.inviteJoinChatAskRt.chatRoomId
                    tellMsg.chatRoomId = prt.inviteJoinChatAskRt.chatRoomId
                    tellMsg.unreadNum = prt.inviteJoinChatAskRt.unreadNum
                    tellMsg.addAllIconProtoIds(prt.inviteJoinChatAskRt.iconProtoIdsList)
                    tellMsg.memberNum = prt.inviteJoinChatAskRt.memberNum
                    tellMsg.roomPlayerId = prt.inviteJoinChatAskRt.roomPlayerId
                    tellMsg.lastSendTime = prt.inviteJoinChatAskRt.lastSendTime

                    val home2Home = session.fillHome2HomeTellMsgHeader(
                        eachMember
                    ) { it.setChatRoomChangeInfoTell(tellMsg) }
                    session.tellHome(home2Home)

                }
            }
        }
    }
}