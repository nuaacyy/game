package com.point18.slg2d.home.module.onlineGift

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.isAfterGameRefTime
import com.point18.slg2d.common.constg.ACTION_GET_ONLINE_GIFT
import com.point18.slg2d.common.constg.ONLINE_GIFT_OPEN
import com.point18.slg2d.common.constg.RES_PROPS
import com.point18.slg2d.common.constg.ReceiveOnlineGiftCount
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.*
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.*
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.GetOnlineGiftEvent
import com.point18.slg2d.home.module.fireEvent
import pb4client.GetOnlineRewardRt
import java.util.*
import java.util.Arrays.asList

// 领取在线礼包奖励
class GetOnlineGiftRewardDeal(
    private val resHelper: ResHelper = ResHelper(),
    private val uiConditionCheck: UiConditionCheckHelper = UiConditionCheckHelper(),
    private val targetHelper: TargetHelper = TargetHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus1<HomePlayerDC>(
        HomePlayerDC::class.java,
        Arrays.asList(resHelper, uiConditionCheck, targetHelper)
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC ->
            val rt = getOnlineGiftReward(session, homePlayerDC)
            session.sendMsg(MsgType.GetOnlineReward_1401, rt)
        }
    }

    private fun getOnlineGiftReward(session: PlayerActor, homePlayerDC: HomePlayerDC): (GetOnlineRewardRt) {
        val rt = GetOnlineRewardRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.nextReward = ""
        rt.nextRewardOverTime = 0
        rt.getReward = ""

        val player = homePlayerDC.player

        // 功能开启
        val re = uiConditionCheck.uiConditionCheck(session, ONLINE_GIFT_OPEN)
        if (re != ResultCode.SUCCESS.code) {
            rt.rt = re
            return rt.build()
        }

        val castleLv = player.castleLv

        val nowTime = getNowTime()

        if (isAfterGameRefTime(player.lastGetOnlineTime)) {
            // 上次领取时间与当前已经跨天,重置玩家所有数据
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

            val res = LinkedList<ResVo>(asList(ResVo(RES_PROPS, prop.id.toLong(), prop.num.toLong())))
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

            val bigRes = LinkedList<ResVo>(
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

        if (nowTime < player.nextOnlineRewardOverTime) {
            rt.rt = ResultCode.ONLINE_GIFT_TIME_ERROR.code
            return rt.build()
        }

        // 收获奖励
        val resVo = resStringToResVoList(player.nextOnlineReward)
        if (resVo == null) {
            rt.rt = ResultCode.ONLINE_GIFT_RESVO_ERROR.code
            return rt.build()
        }

        rt.getReward = player.nextOnlineReward

        resHelper.addRes(session, ACTION_GET_ONLINE_GIFT, player, resVo)
        player.todayOnlineNum += 1
        var isBig = false
        if (player.todayOnlineNum == 10) {
            // 已经拿到最后一档奖励
        } else {
            if (player.todayOnlineNum != 9) {

                val onlineGiftProtoMap = pcs.onlineGiftProtoCache.protoMap[castleLv]
                if (onlineGiftProtoMap == null) {
                    rt.rt = ResultCode.NO_PROTO.code
                    return rt.build()
                }

                val onlineGiftProto = onlineGiftProtoMap[player.todayOnlineNum + 1]
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

                val res = LinkedList(
                    asList(
                        ResVo(RES_PROPS, prop.id.toLong(), prop.num.toLong())
                    )
                )

                val resString = resVoToResString(res)
                player.nextOnlineReward = resString
                player.nextOnlineRewardOverTime = nowTime + onlineGiftProto.cdTime * 1000
            } else {
                isBig = true
                val onlineGiftProtoMap = pcs.onlineGiftProtoCache.protoMap[castleLv]
                if (onlineGiftProtoMap == null) {
                    rt.rt = ResultCode.NO_PROTO.code
                    return rt.build()
                }
                val onlineGiftProto = onlineGiftProtoMap[player.todayOnlineNum + 1]
                if (onlineGiftProto == null) {
                    rt.rt = ResultCode.NO_PROTO.code
                    return rt.build()
                }

                player.nextOnlineReward = player.bigOnlineReward
                player.nextOnlineRewardOverTime = nowTime + onlineGiftProto.cdTime * 1000
            }

            player.lastGetOnlineTime = nowTime

            rt.nextReward = player.nextOnlineReward
            rt.nextRewardOverTime = (player.nextOnlineRewardOverTime / 1000).toInt()
        }

        //添加统计
        targetHelper.targetAddVal(
            session,
            ReceiveOnlineGiftCount,
            LinkedList(asList(1))
        )

        fireEvent(session, GetOnlineGiftEvent(isBig))

        return rt.build()
    }

}
