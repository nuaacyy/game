package com.point18.slg2d.home.module.onlineGift

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.isAfterGameRefTime
import com.point18.slg2d.common.constg.ONLINE_GIFT_OPEN
import com.point18.slg2d.common.constg.RES_PROPS
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.pc.randomSelect
import com.point18.slg2d.common.pc.resVoToResString
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.*
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.OnlineRewardInfo
import pb4client.OpenOnlineRewardRt
import java.util.*
import java.util.Arrays.asList

// 打开在线礼包面板
class OpenOnlineGiftDeal(
    private val uiConditionCheck: UiConditionCheckHelper = UiConditionCheckHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus1<HomePlayerDC>(
        HomePlayerDC::class.java,
        Arrays.asList(uiConditionCheck)
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC ->
            val rt = openOnlineGift(session, homePlayerDC)
            session.sendMsg(MsgType.OpenOnlineReward_1402, rt)
        }
    }

    private fun openOnlineGift(session: PlayerActor, homePlayerDC: HomePlayerDC): (OpenOnlineRewardRt) {
        val rt = OpenOnlineRewardRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = homePlayerDC.player

        // 功能开启
        val re = uiConditionCheck.uiConditionCheck(session, ONLINE_GIFT_OPEN)
        if (re != ResultCode.SUCCESS.code) {
            rt.rt = re
            return rt.build()
        }

        val nowTime = getNowTime()

        if (isAfterGameRefTime(player.lastGetOnlineTime)) {
            // 上次领取时间与当前已经跨天,重置玩家所有数据
            val castleLv = player.castleLv
            player.todayOnlineNum = 0

            val onlineGiftProtoMap = pcs.onlineGiftProtoCache.protoMap[castleLv]
            if (onlineGiftProtoMap == null) {
                rt.rt = ResultCode.NO_PROTO.code
                return rt.build()
            }

            val onlineGiftProto = onlineGiftProtoMap[1]
            if (onlineGiftProto == null) {
                rt.rt = ResultCode.NO_PROTO.code
                return rt.build()
            }

            val propMap = pcs.dropPropsProtoCache.dropPropsMap[onlineGiftProto.reward]
            if (propMap == null) {
                rt.rt = ResultCode.NO_PROTO.code
                return rt.build()
            }

            val prop = randomSelect(propMap.dropMap)
            if (prop == null) {
                rt.rt = ResultCode.NO_PROTO.code
                return rt.build()
            }

            val res =
                LinkedList(asList(ResVo(RES_PROPS, prop.id.toLong(), prop.num.toLong())))

            val resString = resVoToResString(res)

            // 随机全勤奖
            val bigOnlineGiftProtoMap = pcs.onlineGiftProtoCache.protoMap[castleLv]
            if (bigOnlineGiftProtoMap == null) {
                rt.rt = ResultCode.NO_PROTO.code
                return rt.build()
            }

            val bigOnlineGiftProto = bigOnlineGiftProtoMap[10]
            if (bigOnlineGiftProto == null) {
                rt.rt = ResultCode.NO_PROTO.code
                return rt.build()
            }

            val bigPropMap = pcs.dropPropsProtoCache.dropPropsMap[bigOnlineGiftProto.reward]
            if (bigPropMap == null) {
                rt.rt = ResultCode.NO_PROTO.code
                return rt.build()
            }

            val bigProp = randomSelect(bigPropMap.dropMap)
            if (bigProp == null) {
                rt.rt = ResultCode.NO_PROTO.code
                return rt.build()
            }

            val bigRes = LinkedList(
                asList(
                    ResVo(RES_PROPS, bigProp.id.toLong(), bigProp.num.toLong())
                )
            )

            val bigResString = resVoToResString(bigRes)

            player.nextOnlineReward = resString
            player.nextOnlineRewardOverTime = nowTime + onlineGiftProto.cdTime * 1000

            player.bigOnlineReward = bigResString
            player.lastGetOnlineTime = nowTime
        }

        if (player.todayOnlineNum == 10) {
            // 已经全部领完了
            rt.rt = ResultCode.ONLINE_GIFT_OVER_ERROR.code
            return rt.build()
        }

        val onlineRewardInfo = OnlineRewardInfo.newBuilder()
        onlineRewardInfo.todayOnlineNum = player.todayOnlineNum
        onlineRewardInfo.nextOnlineReward = player.nextOnlineReward
        onlineRewardInfo.overTime = (player.nextOnlineRewardOverTime / 1000).toInt()
        onlineRewardInfo.bigOnlineReward = player.bigOnlineReward

        rt.setOnlineRewardInfo(onlineRewardInfo)

        return rt.build()
    }

}