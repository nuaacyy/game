package com.point18.slg2d.home.module.relic

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.zeroTime
import com.point18.slg2d.common.constg.NO_FILTER
import com.point18.slg2d.common.constg.OpenTimeBoxAdd
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResearchEffectHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.TimeBoxInfo
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.msgnotice.createTimeBoxInfoChangeNotifier
import pb4client.StudyTimeBox
import pb4client.StudyTimeBoxRt
import java.util.*

// 研究时光之盒
class StudyTimeBoxDeal(
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus1<HomePlayerDC>(
        HomePlayerDC::class.java,
        Arrays.asList(effectHelper)
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val timeBoxIndex = (msg as StudyTimeBox).timeBoxIndex
        prepare(session) { homePlayerDC ->
            // 数据返回定义
            val rt = studyTimeBox(session, timeBoxIndex, homePlayerDC)

            // 发送数据
            session.sendMsg(MsgType.StudyTimeBox_1160, rt.build())
        }
    }

    private fun studyTimeBox(session: PlayerActor, timeBoxIndex: Int, homePlayerDC: HomePlayerDC): StudyTimeBoxRt.Builder {
        val rt = StudyTimeBoxRt.newBuilder()
        rt.timeBoxIndex = timeBoxIndex
        rt.rt = ResultCode.SUCCESS.code
        val player = homePlayerDC.player

        val timeBoxMap = player.timeBoxNumMap
        val zeroTimeLong = zeroTime.toInstant().toEpochMilli()
        val nowLong = getNowTime()
        for ((_, tb) in timeBoxMap) {

            if (tb.timeBoxTimeOver != zeroTimeLong) {
                rt.rt = (ResultCode.TIME_BOX_QUEUE_ERROR.code)
                return rt
            }
        }
        val timeBoxInfo = timeBoxMap[timeBoxIndex]

        if (timeBoxInfo == null) {
            rt.rt = (ResultCode.NO_FIND_TIME_BOX_INDEX.code)
            return rt
        }


        if (timeBoxInfo.relicBoxId == 0) {
            rt.rt = (ResultCode.NO_FIND_TIME_BOX.code)
            return rt
        }


        if (timeBoxInfo.timeBoxTimeOver != zeroTimeLong) {
            rt.rt = (ResultCode.TIME_BOX_IN_STUDY.code)
            return rt
        }

        val effect = effectHelper.getResearchEffectValue(
            session,
            NO_FILTER,
            OpenTimeBoxAdd
        )
        val overTime = nowLong + (timeBoxInfo.studyTime / (10000.0 + effect) * 10000).toLong()
        val newInfo = TimeBoxInfo(
            timeBoxInfo.relicBoxId,
            timeBoxInfo.baseRate,
            timeBoxInfo.studyTime,
            overTime
        )

        player.putTimeBoxNumMap(timeBoxIndex, newInfo)

        val notice = createTimeBoxInfoChangeNotifier(timeBoxIndex, newInfo)
        notice.notice(session)

        return rt
    }
}


