package com.point18.slg2d.world.module.alliance

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.SET_ALLIANCE_NAME
import com.point18.slg2d.common.constg.SET_ALLIANCE_SHORT_NAME
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.WORD_CHECK_RESULT_FORBIDDEN
import com.point18.slg2d.common.pc.WORD_CHECK_RESULT_LENGTH_EXCEED
import com.point18.slg2d.common.pc.WORD_CHECK_RESULT_LENGTH_SHORT
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.wpm
import pb4client.CheckAllianceName
import pb4client.CheckAllianceNameRt

// 检测联盟名是否可用
class DealCreateAllianceCheckAllianceName : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val name = (msg as CheckAllianceName).name
        val nameType = (msg.nameType)
        val rt = this.checkTargetAllianceName(session, name, nameType)
        if (rt != null) {
            session.sendMsg(MsgType.CreateAllianceCheckAllianceName_825, rt)
        }
    }

    private fun checkTargetAllianceName(session: PlayerSession, name: String, nameType: Int): (CheckAllianceNameRt?) {
        val rt = CheckAllianceNameRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.errorType = ResultCode.SUCCESS.code

        if (name.length <= 1) {
            rt.errorType = (ResultCode.ALLIANCE_NAME_ERROR.code)
            return rt.build()
        }

        if (name[0] == ' ' || name.last() == ' ') {
            rt.errorType = (ResultCode.ALLIANCE_NAME_ERROR.code)
            return rt.build()
        }

        if (nameType == 1) {
            // 联盟名称验证
            val res1 = pcs.wordCache.check(
                name,
                pcs.basicProtoCache.allianceNameLength,
                com.point18.slg2d.common.pc.WORD_CHECK_ALLIANCE_NAME
            )
            when (res1.wordCheckResult) {
                WORD_CHECK_RESULT_FORBIDDEN -> {
                    rt.errorType = (ResultCode.ALLIANCE_NAME_ERROR.code)
                    return rt.build()
                }
                WORD_CHECK_RESULT_LENGTH_SHORT -> {
                    rt.errorType = (ResultCode.ALLIANCE_NAME_LENGTH_NOT_ENOUGH.code)
                    return rt.build()
                }
                WORD_CHECK_RESULT_LENGTH_EXCEED -> {
                    rt.errorType = (ResultCode.ALLIANCE_NAME_LENGTH_EXCEED.code)
                    return rt.build()
                }

            }
        } else {
            // 联盟简称 验证
            val res2 = pcs.wordCache.check(
                name,
                pcs.basicProtoCache.allianceShortNameLength,
                com.point18.slg2d.common.pc.WORD_CHECK_SHORT_ALLIANCE_NAME
            )
            when (res2.wordCheckResult) {

                WORD_CHECK_RESULT_FORBIDDEN -> {
                    rt.errorType = (ResultCode.ALLIANCE_SHORT_NAME_NOT_ALLOWED.code)
                    return rt.build()
                }
                WORD_CHECK_RESULT_LENGTH_SHORT -> {
                    rt.errorType = (ResultCode.ALLIANCE_SHORT_NAME_NOT_ENOUGH.code)
                    return rt.build()
                }
                WORD_CHECK_RESULT_LENGTH_EXCEED -> {
                    rt.errorType = (ResultCode.ALLIANCE_SHORT_NAME_LENGTH_EXCEED.code)
                    return rt.build()
                }
            }
        }

        // 然后发送到公共服去检测是否有重复的
        if (nameType == SET_ALLIANCE_NAME) {
            val use = wpm.getWorldManagerInfoFromPublicManager().useAllianceName[name]
            if (use != null) {
                rt.errorType = (ResultCode.ALLIANCE_NAME_ALREADY_EXISTED.code)
                return rt.build()
            }

        } else if (nameType == SET_ALLIANCE_SHORT_NAME) {
            val use = wpm.getWorldManagerInfoFromPublicManager().useAllianceShortName[name]
            if (use != null) {
                rt.errorType = (ResultCode.ALLIANCE_SHORT_NAME_ALREADY_EXIST.code)
                return rt.build()
            }
        } else {
            rt.errorType = (ResultCode.PARAMETER_ERROR.code)
            return rt.build()
        }
        // com.point18.slg2d.world.common.checkAllianceName(session, name, nameType)

        return rt.build()
    }
}


