package com.point18.slg2d.world.module.chat

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.module.gmCommand.GmM
import com.point18.slg2d.world.module.gmCommand.detectionGm
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.msgnotice.createChatMessageNotifier
import pb4client.RedBagInfo
import pb4client.SendChat
import pb4client.SendChatRt
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.common.findOfficeByPlayerId
import com.point18.slg2d.world.common.targetAddVal

class ChatDeal : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val chatType = (msg as SendChat).type // 聊天频道
        val message = msg.message
        val messType = msg.messageType
        val redBagInfo = msg.redBagInfo

        // 数据返回定义
        val rt = sendChat(session, chatType, message, messType, redBagInfo)
        session.sendMsg(MsgType.Chat_300, rt)
    }

    private fun sendChat(
        session: PlayerSession,
        chatType: Int,
        message: String,
        messType: Int,
        redBagInfo2: RedBagInfo?
    ): SendChatRt {
        val rt = SendChatRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        if (chatType < 0) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        if (message.isEmpty()) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        if (messType == RED_BAG_MESSAGE_NOTICE && redBagInfo2 == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        val areaCache = session.areaCache
        val playerId = session.playerId
        val player = session.player

        // 每个频道发言cd限制验证(忽略GM命令)
        if (detectionGm(message)) {
            val gmReturn = GmM.disposeGm(session, message)
            if (gmReturn == 100) {
                //发送给了地图服，直接返回
                return rt.build()
            }

            val mm = if (gmReturn == 1) {
                "gm 执行成功"
            } else {
                "gm 执行失败"
            }

            val chatMessageNotifier = createChatMessageNotifier(
                chatType,
                1,
                24,
                "",
                "",
                "",
                playerId,
                player.name,
                player.photoProtoId,
                findOfficeByPlayerId(areaCache, playerId),
                (getNowTime() / 1000).toInt(),
                mm,
                TEXT_READ_INFO,
                NORMAL_MESSAGE_NOTICE,
                areaCache.infoByHomeCache.findInfoByHomeByPlayerId(playerId)?.vipLv ?: 0,
                player.areaNo
            )
            chatMessageNotifier.notice(session)

            //重算军团实力
            targetAddVal(areaCache, playerId, SoliderStrength)
            targetAddVal(areaCache, playerId, TrapStrength)
            // 直接发送给当前玩家
            return rt.build()
        }

        return rt.build()
    }

}