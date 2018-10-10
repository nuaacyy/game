package com.point18.slg2d.home.module.chat

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.roomChannelOf
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.WORD_CHECK_RESULT_LENGTH_EXCEED
import com.point18.slg2d.common.pc.WORD_CHECK_RESULT_LENGTH_SHORT
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.MyChat
import com.point18.slg2d.home.dc.VipDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.hpm
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.msgnotice.createRoomNotifierExtend
import pb4client.GetChatRoom
import pb4client.GetChatRoomRt
import pb4server.CreateRoomAskReq
import pb4server.Home2PublicAskResp

class CreateChatRoomDeal : HomeClientMsgDeal, HomeHelperPlus2<HomePlayerDC, VipDC>(
    HomePlayerDC::class.java, VipDC::class.java
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, vipDC: VipDC ->
            val name = (msg as GetChatRoom).name
            val rt = createRoom(session, name, homePlayerDC, vipDC)
            if (rt != null) {
                session.sendMsg(MsgType.CreateScopeMsg_304, rt)
            }
        }
    }

    private fun createRoom(session: PlayerActor, name: String, homePlayerDC: HomePlayerDC, vipDC: VipDC): GetChatRoomRt? {
        val rt = GetChatRoomRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val newNameString = name
        val checkMsg = pcs.wordCache.check(
            newNameString,
            pcs.basicProtoCache.groupNameLeng,
            com.point18.slg2d.common.pc.WORD_CHECK_MESSAGE
        )
        if (checkMsg.wordCheckResult == WORD_CHECK_RESULT_LENGTH_SHORT ||
            checkMsg.wordCheckResult == WORD_CHECK_RESULT_LENGTH_EXCEED
        ) {
            rt.rt = ResultCode.CHAT_MSG_LENGTH_OVER.code
            return rt.build()
        }

        val player = homePlayerDC.player
        if (player.chatRoomList.size >= pcs.basicProtoCache.createGroupNumLimit) {
            rt.rt = ResultCode.NO_MORE_ROOM.code
            return rt.build()
        }

        val vip = vipDC.vipInfo
        val askMsg = CreateRoomAskReq.newBuilder()
        askMsg.nameRoom = newNameString
        askMsg.iconProto = player.photoProtoId
        askMsg.playerName = player.name
        askMsg.vipLv = vip.vipLv
        askMsg.areaNo = player.areaNo
        askMsg.allianceShortName = player.allianceShortName
        askMsg.fightValue = 0
        askMsg.castleLv = player.castleLv
        askMsg.playerShortName = player.allianceNickName
        val roomId = hpm.generateObjIdNew()

        session.createACS<Home2PublicAskResp>().ask(
            session.publicShardProxy,
            session.fillHome2PublicAskMsgHeader(roomId) { it.setCreateRoomAskReq(askMsg) },
            Home2PublicAskResp::class.java
        ).whenCompleteKt { prt, askErr ->
            if (askErr != null || prt == null) {
                rt.rt = ResultCode.PROCESS_ERROR_RPC_EXCEPTION.code

            } else if (prt.createRoomAskRt.rt != ResultCode.SUCCESS.code) {
                rt.rt = prt.createRoomAskRt.rt
                session.sendMsg(MsgType.CreateScopeMsg_304, rt.build())
            } else {
                val nowTime = getNowTime()
                player.chatRoomList.add(MyChat(prt.createRoomAskRt.chatRoomId, getNowTime()))
                createRoomNotifierExtend(
                    prt.createRoomAskRt.chatRoomId,
                    prt.createRoomAskRt.roomName,
                    prt.createRoomAskRt.unreadNum,
                    prt.createRoomAskRt.iconProtoIdsList,
                    prt.createRoomAskRt.memberNum,
                    prt.createRoomAskRt.roomPlayerId,
                    nowTime / 1000,
                    nowTime / 1000
                ).notice(session)

                session.subscribeChannel(roomChannelOf(prt.createRoomAskRt.chatRoomId))
                session.sendMsg(MsgType.CreateScopeMsg_304, rt.build())
            }
        }

        return null
    }
}