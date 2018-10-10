package com.point18.slg2d.world.module.alliance

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.isAfterGameRefTime
import com.point18.slg2d.common.pc.*
import pb4client.WriteAllianceWaijiao
import pb4client.WriteAllianceWaijiaoRt
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.common.writeAllianceWaijiao

// 写联盟外交
class AllianceWriteWaijiao : ReqDealEntered() {

    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val allianceId = (msg as WriteAllianceWaijiao).allianceId
        val waijiaoInfo = msg.waijiaoInfo
        val rt = this.writeWaijiao(session, allianceId, waijiaoInfo)
        if (rt != null) {
            session.sendMsg(MsgType.WriteAllianceWaijiao_896, rt)
        }
    }

    private fun writeWaijiao(session: PlayerSession, allianceId: Long, waijiaoInfo: String)
            : WriteAllianceWaijiaoRt? {
        val rt = WriteAllianceWaijiaoRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        val areaCache = session.areaCache

        // 判断内容
        var newInfo = waijiaoInfo
        val res1 = pcs.wordCache.check(waijiaoInfo, pcs.basicProtoCache.allianceWaijiaoLength, WORD_CHECK_NAME)
        when (res1.wordCheckResult) {
            WORD_CHECK_RESULT_FORBIDDEN -> {
                newInfo = res1.newString
            }
            WORD_CHECK_RESULT_LENGTH_SHORT -> {
                rt.rt = (ResultCode.WAIJIAO_INFO_TOO_SHORT.code)
                return rt.build()
            }
            WORD_CHECK_RESULT_LENGTH_EXCEED -> {
                rt.rt = (ResultCode.WAIJIAO_INFO_TOO_LANG.code)
                return rt.build()
            }
        }

        val player = session.player

        // 判断玩家每日次数
        if (isAfterGameRefTime(player.lastWaijiaoCount)) {
            player.waijiaoCount = 0
            player.lastWaijiaoCount = getNowTime()
        }

        if (pcs.basicProtoCache.numOfStatementsLimit != 0 && player.waijiaoCount >= pcs.basicProtoCache.numOfStatementsLimit) {
            rt.rt = (ResultCode.PLAYER_WAIJIAO_MAX_NUM.code)
            return rt.build()
        }

        player.waijiaoCount = player.waijiaoCount + 1
        player.lastWaijiaoCount = getNowTime()

        writeAllianceWaijiao(
            session, player, allianceId, newInfo,
            player.areaNo, player.name, player.photoProtoId, player.allianceNickName
        )

        return null
    }

}

