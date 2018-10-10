//设置联盟标记
package com.point18.slg2d.world.module.alliance

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.MARK_NORMAL
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.*
import pb4client.AllianceSetMark
import pb4client.AllianceSetMarkRt
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.common.setAllianceMark

// 设置联盟标记/集结请求标记 821
class DealSetAllianceMark : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val t = (msg as AllianceSetMark).type
        val x = msg.x
        val y = msg.y
        val pltAreaNo = msg.pltAreaNo
        val title = msg.title
        val desp = msg.desp

        var rt: AllianceSetMarkRt? = null

        if (t == MARK_NORMAL) {
            rt = this.setMark(session, t, x, y, pltAreaNo, title, desp)
        }
        if (rt != null) {
            session.sendMsg(MsgType.AllianceSetMark_821, rt)
        }
    }

    private fun setMark(session: PlayerSession, markType: Int, x: Int, y: Int, pltAreaNo: Int, title: String, desp: String):
        (AllianceSetMarkRt?) {
        val rt = AllianceSetMarkRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.endTime = 0

        val areaCache = session.areaCache

        // 联盟标记标题验证
        val res1 = pcs.wordCache.check(title, pcs.basicProtoCache.allianceMarkTitleLength, WORD_CHECK_NAME)
        when (res1.wordCheckResult) {
            WORD_CHECK_RESULT_FORBIDDEN -> {
                rt.rt = (ResultCode.CHECK_WORD_ERR.code)
                return rt.build()
            }
            WORD_CHECK_RESULT_LENGTH_SHORT -> {
                rt.rt = (ResultCode.ALLIANCE_MARK_TITLE_LENGTH_NOT_ENOUGH.code)
                return rt.build()
            }
            WORD_CHECK_RESULT_LENGTH_EXCEED -> {
                rt.rt = (ResultCode.ALLIANCE_MARK_TITLE_LENGTH_EXCEED.code)
                return rt.build()
            }

        }

        // 联盟标记描述验证
        val res2 = pcs.wordCache.check(desp, pcs.basicProtoCache.allianceMarkDescriptionLength, WORD_CHECK_MESSAGE)
        when (res2.wordCheckResult) {

            WORD_CHECK_RESULT_FORBIDDEN -> {
                rt.rt = (ResultCode.CHECK_WORD_ERR.code)
                return rt.build()
            }

            WORD_CHECK_RESULT_LENGTH_SHORT -> {
                rt.rt = (ResultCode.ALLIANCE_MARK_DESCRIPTION_LENGTH_NOT_ENOUGH.code)
                return rt.build()
            }

            WORD_CHECK_RESULT_LENGTH_EXCEED -> {
                rt.rt = (ResultCode.ALLIANCE_MARK_DESCRIPTION_LENGTH_EXCEED.code)
                return rt.build()
            }

        }

        val player = session.player

        setAllianceMark(session, player.allianceId, markType, x, y, pltAreaNo, title, desp)

        return null
    }

}



