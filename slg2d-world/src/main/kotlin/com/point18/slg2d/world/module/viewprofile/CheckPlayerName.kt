package com.point18.slg2d.world.module.viewprofile

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.*
import pb4client.CheckPlayerName
import pb4client.CheckPlayerNameRt
import com.point18.slg2d.common.resultcode.ResultCode

// 检测名字是否可用
class DealCheckPlayerName : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val reqMsg = (msg as CheckPlayerName)
        val name = reqMsg.name
        val nameType = reqMsg.nameType
        val rt = this.checkAllianceName(session, name, nameType)
        if (!rt.isGoRpc) {
            session.sendMsg(MsgType.CheckPlayerName_26, rt.rtMsg!!)
        }
    }

    data class CheckAllianceNameRst(
        var rtMsg: CheckPlayerNameRt?,
        var isGoRpc: Boolean
    )

    private fun checkAllianceName(session: PlayerSession, name: String, nameType: Int): CheckAllianceNameRst {
        val rtBuilder = CheckPlayerNameRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code
        rtBuilder.errorType = ResultCode.SUCCESS.code

        val areaCache = session.areaCache

        if (name.length <= 1) {
            rtBuilder.errorType = ResultCode.PLAYER_NAME_ERROR.code
            return CheckAllianceNameRst(rtBuilder.build(), false)
        }

        if (name.startsWith(" ") || name.endsWith(" ")) {
            rtBuilder.errorType = ResultCode.PLAYER_NAME_ERROR.code
            return CheckAllianceNameRst(rtBuilder.build(), false)
        }

        if (nameType == 1) {
            // 玩家名字
            //检测是否以系统前缀King开头
            if (name.startsWith(pcs.basicProtoCache.namePrefix)) {
                rtBuilder.errorType = ResultCode.KEYWORDS.code
                return CheckAllianceNameRst(rtBuilder.build(), false)
            }
            //检测是否合法
            val rst = pcs.wordCache.check(
                name,
                pcs.basicProtoCache.playerNameLength,
                WORD_CHECK_LETTER_NUMBER_NOCHINESE
            )
            when (rst.wordCheckResult) {
                WORD_CHECK_RESULT_LENGTH_SHORT -> {
                    rtBuilder.errorType = ResultCode.NAME_NIL.code
                    return CheckAllianceNameRst(rtBuilder.build(), false)
                }
                WORD_CHECK_RESULT_LENGTH_EXCEED -> {
                    rtBuilder.errorType = ResultCode.NAME_LONG.code
                    return CheckAllianceNameRst(rtBuilder.build(), false)
                }
                WORD_CHECK_RESULT_FORBIDDEN -> {
                    rtBuilder.errorType = ResultCode.KEYWORDS.code
                    return CheckAllianceNameRst(rtBuilder.build(), false)
                }
            }
            // 检测本服重名
            val searchPlayer = areaCache.playerCache.findPlayerByName(name)
            if (searchPlayer != null) {
                rtBuilder.errorType = ResultCode.USE_NAME.code
                return CheckAllianceNameRst(rtBuilder.build(), false)
            }

            //todo 登录服校验
//			// 然后发送到登录服去检测是否有重复的
//			// 请求登录服查找重名
//			checkPlayerNameRpcCall = & grpcinvoke4r . CheckPlayerNameRpcCall {
//				areaCache:      session.areaCache,
//				playerId:       player.GetId(),
//				playerName:     name,
//				SucessCallback: null,
//				FailCallback:   null,
//				CheckType:      1,
//			}
//			baseg.RpcRoutiner.AsyncExecRpcCall(grpccall.NewGameAsyncRpcAction(session.areaCache.areaBase, checkPlayerNameRpcCall))
//			return CheckAllianceNameRst(null, true)
        } else {
            // 玩家昵称
            val res1 = pcs.wordCache.check(
                name,
                pcs.basicProtoCache.allianceNickNameLength,
                WORD_CHECK_NAME
            )
            when (res1.wordCheckResult) {
                WORD_CHECK_RESULT_FORBIDDEN ->
                    rtBuilder.errorType = ResultCode.KEYWORDS.code
                WORD_CHECK_RESULT_LENGTH_SHORT ->
                    rtBuilder.errorType = ResultCode.NAME_NIL.code
                WORD_CHECK_RESULT_LENGTH_EXCEED ->
                    rtBuilder.errorType = ResultCode.NAME_LONG.code
            }
        }

        return CheckAllianceNameRst(rtBuilder.build(), false)
    }
}



