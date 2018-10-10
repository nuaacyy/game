package com.point18.slg2d.home.module.relic

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.TimeBoxInfo
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.msgnotice.createTimeBoxInfoChangeNotifier
import pb4client.CancelStudyTimeBox
import pb4client.CancelStudyTimeBoxRt
import java.time.Instant

// 取消研究时光之盒
class CancelStudyTimeBoxDeal(
) : HomeClientMsgDeal,
    HomeHelperPlus1<HomePlayerDC>(
        HomePlayerDC::class.java
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val timeBoxIndex = (msg as CancelStudyTimeBox).timeBoxIndex
        prepare(session) { homePlayerDC ->
            // 数据返回定义
            val rt = cancelStudyTimeBox(session, timeBoxIndex, homePlayerDC)
            // 发送数据
            session.sendMsg(MsgType.CancelStudyTimeBox_1161, rt.build())
        }
    }

    private fun cancelStudyTimeBox(session: PlayerActor, timeBoxIndex: Int, homePlayerDC: HomePlayerDC): CancelStudyTimeBoxRt.Builder {
        val rt = CancelStudyTimeBoxRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.timeBoxIndex = timeBoxIndex

        val player = homePlayerDC.player

        val timeBoxMap = player.timeBoxNumMap
        val timeBoxInfo = timeBoxMap[timeBoxIndex]

        if (timeBoxInfo == null) {
            rt.rt = (ResultCode.NO_FIND_TIME_BOX_INDEX.code)
            return rt
        }


        if (timeBoxInfo.relicBoxId == 0) {
            rt.rt = (ResultCode.NO_FIND_TIME_BOX.code)
            return rt
        }

        val zeroTimeLong = com.point18.slg2d.common.commonfunc.zeroTime.toInstant().toEpochMilli()
        val nowTimeLong = Instant.now().toEpochMilli()
        if (timeBoxInfo.timeBoxTimeOver == zeroTimeLong || timeBoxInfo.timeBoxTimeOver < nowTimeLong) {
            rt.rt = (ResultCode.TIME_BOX_STUDY_OVER.code)
            return rt
        }

        val newInfo = TimeBoxInfo(
            timeBoxInfo.relicBoxId,
            timeBoxInfo.baseRate,
            timeBoxInfo.studyTime,
            zeroTimeLong
        )
        player.putTimeBoxNumMap(timeBoxIndex, newInfo)

        val notice = createTimeBoxInfoChangeNotifier(timeBoxIndex, newInfo)
        notice.notice(session)

        return rt
    }
}


