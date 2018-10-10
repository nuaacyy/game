package com.point18.slg2d.home.module.relic

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.zeroTime
import com.point18.slg2d.common.constg.ACTION_REMOVE_TIME_BOX
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.TimeBoxInfo
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.msgnotice.createTimeBoxInfoChangeNotifier
import pb4client.RemoveStudyTimeBoxReward
import pb4client.RemoveStudyTimeBoxRewardRt
import java.util.*

// 放弃时光之盒奖励
class RemoveStudyTimeBoxRewardDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus1<HomePlayerDC>(
        HomePlayerDC::class.java,
        Arrays.asList(resHelper)
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val timeBoxIndex = (msg as RemoveStudyTimeBoxReward).timeBoxIndex
        prepare(session) { homePlayerDC ->
            // 数据返回定义
            val rt = removeStudyTimeBoxRewardDeal(session, timeBoxIndex, homePlayerDC)
            // 发送数据
            session.sendMsg(MsgType.RemoveStudyTimeBoxReward_1163, rt.build())
        }
    }

    private fun removeStudyTimeBoxRewardDeal(
        session: PlayerActor,
        timeBoxIndex: Int,
        homePlayerDC: HomePlayerDC
    ): RemoveStudyTimeBoxRewardRt.Builder {
        val rt = RemoveStudyTimeBoxRewardRt.newBuilder()
        rt.timeBoxIndex = timeBoxIndex
        rt.rt = ResultCode.SUCCESS.code

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

        val darkDeleteCosts = pcs.basicProtoCache.darkDelete

        if (!resHelper.checkRes(session, darkDeleteCosts)) {
            rt.rt = (ResultCode.LESS_RESOUCE.code)
            return rt
        }

        resHelper.costRes(session, ACTION_REMOVE_TIME_BOX, player, darkDeleteCosts)

        val newInfo = TimeBoxInfo(
            0,
            1,
            0,
            zeroTime.toInstant().toEpochMilli()
        )
        player.putTimeBoxNumMap(timeBoxIndex, newInfo)

        val notice = createTimeBoxInfoChangeNotifier(timeBoxIndex, newInfo)
        notice.notice(session)

        return rt
    }
}


