package com.point18.slg2d.world.module.wonder

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.COUNTRY_NOTICE_SYSTEM_CHAT
import com.point18.slg2d.common.constg.OfficeFunction
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.WORD_CHECK_MESSAGE
import com.point18.slg2d.common.pc.WORD_CHECK_RESULT_LENGTH_EXCEED
import com.point18.slg2d.common.pc.WORD_CHECK_RESULT_LENGTH_SHORT
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.common.checkOfficeFunction
import com.point18.slg2d.world.common.findOfficeByPlayerId
import com.point18.slg2d.world.module.ReqDealEntered
import pb4client.EditorCountryNotice
import pb4client.EditorCountryNoticeRt

class EditorCountryNoticeDeal : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val editorMsg = msg as EditorCountryNotice
        val rt = this.editorNotice(session, editorMsg.notice)
        session.sendMsg(MsgType.EditorCountryNotice_1459, rt)
    }

    private fun editorNotice(session: PlayerSession, notice: String): EditorCountryNoticeRt {
        val rtBuilder = EditorCountryNoticeRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val areaCache = session.areaCache
        val playerId = session.playerId

        //校验权限
        val posId = findOfficeByPlayerId(areaCache, playerId)
        val ok = checkOfficeFunction(OfficeFunction.EditorCountryNotice, posId)
        if (!ok) {
            rtBuilder.rt = ResultCode.LIMIT_TO_EDITOR_NOTICE.code
            return rtBuilder.build()
        }

        val wonder = areaCache.wonderCache.findBigWonder()
        if (wonder == null) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            return rtBuilder.build()
        }

        //判断时间间隔
        if (wonder.lastNoticeTime + pcs.basicProtoCache.noticeCd * 1000 > getNowTime()) {
            rtBuilder.rt = ResultCode.COUNTRY_NOTICE_IN_COOL.code
            return rtBuilder.build()
        }

        //校验文字
        val rst = pcs.wordCache.check(notice, pcs.basicProtoCache.allianceDescriptionLength, WORD_CHECK_MESSAGE)
        when (rst.wordCheckResult) {
            WORD_CHECK_RESULT_LENGTH_SHORT -> {
                rtBuilder.rt = ResultCode.COUNTRY_NOTICE_NOT_ENOUGH.code
                return rtBuilder.build()
            }
            WORD_CHECK_RESULT_LENGTH_EXCEED -> {
                rtBuilder.rt = ResultCode.COUNTRY_NOTICE_EXCEED.code
                return rtBuilder.build()
            }
        }

        wonder.notice = rst.newString
        rtBuilder.notice = rst.newString

        wonder.lastNoticeTime = getNowTime()

        areaCache.sendSysBroadcastMsg(COUNTRY_NOTICE_SYSTEM_CHAT)

        //加入日志
        return rtBuilder.build()
    }
}
