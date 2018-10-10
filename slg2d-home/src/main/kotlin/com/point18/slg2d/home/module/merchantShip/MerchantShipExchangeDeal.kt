package com.point18.slg2d.home.module.merchantShip

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.common.UiConditionCheckHelper
import com.point18.slg2d.home.dc.HomeMyTargetDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.InnerCityDC
import com.point18.slg2d.home.dc.MerchantShipDC
import com.point18.slg2d.home.helper.HomeHelperPlus4
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.MerchantShipBuySurpriseEvent
import com.point18.slg2d.home.module.fireEvent
import pb4client.MerchantShipExchange
import pb4client.MerchantShipExchangeRt
import java.util.*

class MerchantShipExchangeDeal(
    private val resHelper: ResHelper = ResHelper(),
    private val uiConditionHelper: UiConditionCheckHelper = UiConditionCheckHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus4<
            HomePlayerDC,
            HomeMyTargetDC,
            InnerCityDC,
            MerchantShipDC
            >(
        HomePlayerDC::class.java,
        HomeMyTargetDC::class.java,
        InnerCityDC::class.java,
        MerchantShipDC::class.java,
        Arrays.asList(resHelper, uiConditionHelper)
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val exchangeId = (msg as MerchantShipExchange).exchangeId
        prepare(session) { homePlayerDC, homeMyTargetDC, innerCityDC, merchantShipDC ->
            val rt =
                merchantShipExchange(session, exchangeId, homePlayerDC, homeMyTargetDC, innerCityDC, merchantShipDC)
            session.sendMsg(MsgType.MerchantShipExchange_33, rt)
        }
    }

    private fun merchantShipExchange(
        session: PlayerActor,
        exchangeId: Int,
        homePlayerDC: HomePlayerDC,
        homeMyTargetDC: HomeMyTargetDC,
        innerCityDC: InnerCityDC,
        merchantShipDC: MerchantShipDC
    ): MerchantShipExchangeRt {
        val rt = MerchantShipExchangeRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.srcProtoId = 0
        val player = homePlayerDC.player

        // 功能开启
        val re = uiConditionHelper.uiConditionCheck(session, com.point18.slg2d.common.constg.SHIP_OPEN)
        if (re != ResultCode.SUCCESS.code) {
            rt.rt = re
            return rt.build()
        }

        val sMerchantShip = merchantShipDC.merchantShip
        if (sMerchantShip == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        // 检测是否需要刷新一下
        val refreshResult = refreshShipInfo(homePlayerDC, merchantShipDC, innerCityDC)

        if (refreshResult != ResultCode.SUCCESS.code) {
            rt.rt = (refreshResult)
            return rt.build()
        }

        val info = merchantShipDC.findMerchantShipInfoByExchangeId(exchangeId)


        if (info == null) {
            rt.rt = (ResultCode.PARAMETER_ERROR.code)
            return rt.build()
        }


        if (info.exchanged == 1) {
            // 已经兑换过了
            rt.rt = (ResultCode.MER_IS_EXCHANGE_ERROR.code)
            return rt.build()
        }

        // 验证资源
        val getResVo: LinkedList<ResVo>
        val surpriseRate: Int

        if (info.srcType == 1) {
            // 普通物品
            val tradShipProto = pcs.tradShipProtoCache.tradShipProtoMap[info.srcProtoId]

            if (tradShipProto == null) {
                rt.rt = (ResultCode.NO_PROTO.code)
                return rt.build()
            }

            val checkCost = resHelper.checkRes(session, tradShipProto.exchangeResVo)

            if (!checkCost) {
                rt.rt = (ResultCode.LESS_RESOUCE.code)
                return rt.build()
            }

            // 扣除消耗
            resHelper.costRes(
                session,
                com.point18.slg2d.common.constg.ACTION_SHIP_EXCHANGE,
                player,
                tradShipProto.exchangeResVo
            )
            getResVo = LinkedList(tradShipProto.propsResVo)

            surpriseRate = tradShipProto.surpriseRate

        } else if (info.srcType == 2) {
            // TODO 惊喜物品 这个还没充值 不知道怎么做
            rt.rt = (ResultCode.MER_INFO_IS_NO_PAY.code)
            homeMyTargetDC.targetInfo.buySurprise++
            return rt.build()
        } else {
            rt.rt = (ResultCode.PARAMETER_ERROR.code)
            return rt.build()
        }

        fireEvent(session, MerchantShipBuySurpriseEvent(true))

        // 成功兑换,获得奖励
        resHelper.addRes(session, com.point18.slg2d.common.constg.ACTION_SHIP_EXCHANGE, player, getResVo)

        // 检测是否激活成了惊喜
        var isHaveSurprise = false
        var surpriseId = 0

        for ((_, nowInfo) in sMerchantShip.shopInfoList) {

            if (nowInfo.srcType == 2) {
                isHaveSurprise = true
                break
            }
        }

        if (info.srcType == 1 && !isHaveSurprise) {
            // 普通物品的兑换才可能激活惊喜
            var isRes = false
            if (sMerchantShip.nowTimes == pcs.basicProtoCache.tradShipSurpriseMaxNum) {
                // 必出
                isRes = true
            } else {
                if (sMerchantShip.nowTimes >= pcs.basicProtoCache.tradShipSurpriseMinNum && surpriseRate != -1) {
                    val rand = com.point18.slg2d.common.commonfunc.getRandInt(10000)
                    if (rand <= surpriseRate) {
                        isRes = true
                    }
                }
            }
            if (isRes) {
                // 出现了惊喜
                val building =
                    innerCityDC.findMaxLvInnerBuildingFromCastleIdAndType(
                        player.focusCastleId,
                        com.point18.slg2d.common.constg.MainBuilding
                    )
                if (building == null) {
                    rt.rt = (ResultCode.NO_PROTO.code)
                    return rt.build()
                }
                val tradShipSurpriseRefreshProto =
                    pcs.tradShipSurpriseRefreshProtoCache.tradShipSurpriseRefreshMap[building.lv]
                if (tradShipSurpriseRefreshProto == null) {
                    rt.rt = (ResultCode.NO_PROTO.code)
                    return rt.build()
                }
                val sId = com.point18.slg2d.common.pc.findValueFromDropBag(tradShipSurpriseRefreshProto.surpriseMap)
                surpriseId = (sId)
                rt.srcProtoId = (surpriseId)

                sMerchantShip.nowTimes = 1
            }

        }


        if (surpriseId != 0) {
            // 激活了惊喜,改写这个格子的数据
            info.srcProtoId = surpriseId
            info.srcType = 2
        } else {
            // 兑换结束,修改数据
            info.exchanged = 1
        }


        if (info.locked == 1) {
            info.locked = 0
        }

        return rt.build()
    }
}



