package com.point18.slg2d.home.module.chat

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.ChangeRoomInfo
import pb4client.ChangeRoomInfoRt
import pb4server.ChatRoomNewNameTell
import pb4server.Home2PublicAskResp
import pb4server.RenameChatRoomAskReq

class RenameChatRoomDeal : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {

        prepare(session) {
            val msgReceive = msg as ChangeRoomInfo
            val roomId = msgReceive.roomId
            val newName = msgReceive.newName
            val rt = renameMyRoom(session, roomId, newName)
            if (rt != null) {
                session.sendMsg(MsgType.ChangeRoomInfo_299, rt)
            }
        }
    }

    private fun renameMyRoom(session: PlayerActor, roomId: Long, newName: String): ChangeRoomInfoRt? {

        // 去public改名,  tell给在这个群的所在的home玩家通知改名了
        val askMsg = RenameChatRoomAskReq.newBuilder()
        askMsg.roomId = roomId
        askMsg.roomName = newName
        session.createACS<Home2PublicAskResp>().ask(
            session.publicShardProxy,
            session.fillHome2PublicAskMsgHeader(roomId) { it.setRenameChatRoomAskReq(askMsg) },
            Home2PublicAskResp::class.java
        ).whenCompleteKt { prt, askErr ->
            val rt = ChangeRoomInfoRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code
            rt.newName = newName
            rt.roomId = roomId
            if (askErr != null || prt == null) {
                println("同步数据失败")
            } else {
                rt.rt = prt.renameChatRoomAskRt.rt
                session.sendMsg(MsgType.ChangeRoomInfo_299, rt.build())
                if (prt.renameChatRoomAskRt.rt == ResultCode.SUCCESS.code) {
                    for (eachPlayerId in prt.renameChatRoomAskRt.membersList) {
                        val tellMsg = ChatRoomNewNameTell.newBuilder()
                        tellMsg.chatRoomId = roomId
                        tellMsg.roomName = newName
                        tellMsg.unreadNum = 1
                        tellMsg.addAllIconProtoIds(prt.renameChatRoomAskRt.iconProtoIdsList)
                        tellMsg.memberNum = prt.renameChatRoomAskRt.memberNum
                        tellMsg.roomPlayerId = prt.renameChatRoomAskRt.roomPlayerId
                        val home2Home = session.fillHome2HomeTellMsgHeader(
                            eachPlayerId
                        ) { it.setChatRoomNewNameTell(tellMsg) }
                        session.tellHome(home2Home)
                    }
                }
            }
        }

        return null
    }
}