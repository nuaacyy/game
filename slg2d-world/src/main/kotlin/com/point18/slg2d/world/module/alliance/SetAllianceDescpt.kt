//修改联盟公告
package com.point18.slg2d.world.module.alliance

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.*
import pb4client.AllianceSetDescpt
import pb4client.AllianceSetDescptRt
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.common.setAllianceDescpt

class DealSetAllianceDescpt : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val desp = (msg as AllianceSetDescpt).description
        val despType = msg.desType

        val rt = this.setDescpt(session, desp, despType)
        if (rt != null) {
            session.sendMsg(MsgType.AllianceSetDescpt_813, rt)
        }
    }

    private fun setDescpt(session: PlayerSession, desp: String, despType: Int): (AllianceSetDescptRt?) {
        val rt = AllianceSetDescptRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.description = desp
        rt.desType = despType

        if (despType == 1) {
            // 公告
            // 联盟公告验证
            val res = pcs.wordCache.check(desp, pcs.basicProtoCache.allianceDescriptionLength, WORD_CHECK_MESSAGE)
            when (res.wordCheckResult) {
                WORD_CHECK_RESULT_FORBIDDEN -> {

                    rt.rt = (ResultCode.ALLIANCE_SET_DESCRIPTION_FORBIDDEN.code)
                    return rt.build()

                }
                WORD_CHECK_RESULT_LENGTH_SHORT -> {

                    rt.rt = (ResultCode.ALLIANCE_DESCRIPTION_LENGTH_EXCEED.code)
                    return rt.build()

                }
                WORD_CHECK_RESULT_LENGTH_EXCEED -> {

                    rt.rt = (ResultCode.ALLIANCE_DESCRIPTION_LENGTH_EXCEED.code)
                    return rt.build()

                }

            }
        } else {
            // 标语
            // 联盟公告验证
            val res = pcs.wordCache.check(desp, pcs.basicProtoCache.leagueSloganLength, WORD_CHECK_ALLIANCE_SLOGAN)
            when (res.wordCheckResult) {
                WORD_CHECK_RESULT_FORBIDDEN -> {

                    rt.rt = (ResultCode.ALLIANCE_SET_DESCRIPTION_FORBIDDEN.code)
                    return rt.build()

                }
                WORD_CHECK_RESULT_LENGTH_SHORT -> {

                    rt.rt = (ResultCode.ALLIANCE_DESCRIPTION_LENGTH_EXCEED.code)
                    return rt.build()

                }
                WORD_CHECK_RESULT_LENGTH_EXCEED -> {

                    rt.rt = (ResultCode.ALLIANCE_DESCRIPTION_LENGTH_EXCEED.code)
                    return rt.build()

                }
            }
        }

        val player = session.player

        setAllianceDescpt(session, player.allianceId, desp, despType)

        return null
    }

}



