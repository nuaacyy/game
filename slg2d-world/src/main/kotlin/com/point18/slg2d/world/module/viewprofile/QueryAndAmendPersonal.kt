package com.point18.slg2d.world.module.viewprofile

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.*
import pb4client.QueryAndAmendPersonal
import pb4client.QueryAndAmendPersonalRt
import com.point18.slg2d.common.resultcode.ResultCode

// 查询与修改个人签名
class QueryAndAmendPersonalDeal : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val reqMsg = msg as QueryAndAmendPersonal
        val operationType = reqMsg.operationType
        val playerNames = reqMsg.playerName
        val amendInfo = reqMsg.amendInfo
        val queryAndAmendPersonalRt = queryAndAmendPersonal(session, playerNames, operationType, amendInfo)

        session.sendMsg(MsgType.QueryAndAmendPersonal_11, queryAndAmendPersonalRt
        )
    }

    private fun queryAndAmendPersonal(
        session: PlayerSession,
        playerName: String,
        operationType: Int,
        desp: String
    ): QueryAndAmendPersonalRt {

        val rtBuilder = QueryAndAmendPersonalRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        // 个人简介验证
        val res = pcs.wordCache.check(desp, pcs.basicProtoCache.playerDescriptionLength, WORD_CHECK_MESSAGE)
        when (res.wordCheckResult) {
            WORD_CHECK_RESULT_FORBIDDEN -> {
                rtBuilder.rt = ResultCode.KEYWORDS.code
                return rtBuilder.build()
            }
            WORD_CHECK_RESULT_LENGTH_SHORT -> {
                rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                return rtBuilder.build()
            }
            WORD_CHECK_RESULT_LENGTH_EXCEED -> {
                rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                return rtBuilder.build()
            }
        }

        val areaCache = session.areaCache

        // 操作类型  1.查询 2.修改
        if (operationType == 1) {
            val player = areaCache.playerCache.findPlayerByName(playerName)
            if (player == null) {
                rtBuilder.rt = ResultCode.NAME_NONENTITY.code
                return rtBuilder.build()
            }

            rtBuilder.personalInfo = player.selfIntroduction

        } else if (operationType == 2) {
            val player = session.player

            player.selfIntroduction = desp

            rtBuilder.personalInfo = player.selfIntroduction
        }

        return rtBuilder.build()
    }

}

