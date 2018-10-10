package com.point18.slg2d.home.module.relic

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.getRandInt
import com.point18.slg2d.common.constg.ACTION_GET_TIME_BOX_REWARD
import com.point18.slg2d.common.constg.RES_PROPS
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.*
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.TimeBoxInfo
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.msgnotice.createTimeBoxInfoChangeNotifier
import pb4client.GetStudyTimeBoxReward
import pb4client.GetStudyTimeBoxRewardRt
import java.util.*

// 领取研究时光之盒奖励
class GetStudyTimeBoxRewardDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus1<HomePlayerDC>(
        HomePlayerDC::class.java,
        Arrays.asList(resHelper)
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val timeBoxIndex = (msg as GetStudyTimeBoxReward).timeBoxIndex
        prepare(session) { homePlayerDC ->
            // 数据返回定义
            val rt = getStudyTimeBoxReward(session, timeBoxIndex, homePlayerDC)
            // 发送数据
            session.sendMsg(MsgType.GetStudyTimeBoxReward_1162, rt.build())
        }
    }

    private fun getStudyTimeBoxReward(session: PlayerActor, timeBoxIndex: Int, homePlayerDC: HomePlayerDC): GetStudyTimeBoxRewardRt.Builder {
        val rt = GetStudyTimeBoxRewardRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.timeBoxIndex = timeBoxIndex
        rt.timeBoxReward = ""

        val player = homePlayerDC.player

        val timeBoxMap = player.timeBoxNumMap
        val timeBoxInfo = timeBoxMap[timeBoxIndex]

        if (timeBoxInfo == null) {
            rt.rt = (ResultCode.NO_FIND_TIME_BOX_INDEX.code)
            return rt
        }


        if (timeBoxInfo.relicBoxId == 0) {
            rt.rt = ResultCode.NO_FIND_TIME_BOX.code
            return rt
        }


        if (timeBoxInfo.timeBoxTimeOver > getNowTime()) {
            rt.rt = ResultCode.TIME_BOX_GET_REWARD_ERROR.code
            return rt
        }

        val relicBoxProto = pcs.relicBoxCache.relicBoxMap[timeBoxInfo.relicBoxId]
        if (relicBoxProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt
        }

        //发送奖励
        val baseTotalRes = LinkedList<ResVo>()
        for ((dropPropId, percent) in relicBoxProto.dropPropMap) {
            val dropPropProto = pcs.dropPropsProtoCache.dropPropsMap[dropPropId]
            if (dropPropProto == null) {
                rt.rt = ResultCode.NO_PROTO.code
                return rt
            }
            val randVal = getRandInt(10000)
            if (randVal > percent) {
                continue
            }
            val prop = randomSelect(dropPropProto.dropMap)
            if (prop == null) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                return rt
            }
            baseTotalRes += ResVo(RES_PROPS, prop.id.toLong(), prop.num.toLong())
        }
        val res = relicBoxProto.selectRes()
        if (res == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }
        baseTotalRes.add(res)
        val resAddRt = resVoAddX(baseTotalRes, timeBoxInfo.baseRate)
        if (!resAddRt.res) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        val totalRes = resAddRt.listOfResVo

        resHelper.addRes(session, ACTION_GET_TIME_BOX_REWARD, player, totalRes)

        val newInfo = TimeBoxInfo(
            0,
            1,
            0,
            0
        )
        player.putTimeBoxNumMap(timeBoxIndex, newInfo)

        val notice = createTimeBoxInfoChangeNotifier(timeBoxIndex, newInfo)
        notice.notice(session)

        val addString = resVoToResString(totalRes)
        rt.timeBoxReward = addString

        return rt
    }
}


