package com.point18.slg2d.home.module.merchantShip

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.UiConditionCheckHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.InnerCityDC
import com.point18.slg2d.home.dc.MerchantShipDC
import com.point18.slg2d.home.helper.HomeHelperPlus3
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.MerchantShipLock
import pb4client.MerchantShipLockRt
import java.util.*

class MerchantShipLockDeal(
    private val uiConditionHelper: UiConditionCheckHelper = UiConditionCheckHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus3<
            HomePlayerDC,
            InnerCityDC,
            MerchantShipDC
            >(
        HomePlayerDC::class.java,
        InnerCityDC::class.java,
        MerchantShipDC::class.java,
        Arrays.asList(uiConditionHelper)
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val exchangeId = (msg as MerchantShipLock).exchangeId
        val lockType = msg.lockType
        prepare(session) { homePlayerDC, innerCityDC, merchantShipDC ->
            val rt = merchantShipLock(session, exchangeId, lockType, homePlayerDC, innerCityDC, merchantShipDC)
            session.sendMsg(MsgType.MerchantShipLock_34, rt)
        }
    }

    fun merchantShipLock(
        session: PlayerActor,
        exchangeId: Int,
        lockType: Int,
        homePlayerDC: HomePlayerDC,
        innerCityDC: InnerCityDC,
        merchantShipDC: MerchantShipDC
    ): MerchantShipLockRt {
        val rt = MerchantShipLockRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

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
        if (lockType != 1 && lockType != 2) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        // 检测是否需要刷新一下
        val refreshResult = refreshShipInfo(homePlayerDC, merchantShipDC, innerCityDC)

        if (refreshResult != ResultCode.SUCCESS.code) {
            rt.rt = refreshResult
            return rt.build()
        }

        val info = merchantShipDC.findMerchantShipInfoByExchangeId(exchangeId)


        if (info == null) {
            rt.rt = (ResultCode.PARAMETER_ERROR.code)
            return rt.build()
        }


        if (info.exchanged == 1) {
            rt.rt = (ResultCode.MER_IS_EXCHANGE_ERROR.code)
            return rt.build()
        }


        if (lockType == 1) {
            // 锁定物品

            if (info.locked == 1) {
                // 已经锁着了
                rt.rt = (ResultCode.MER_INFO_IS_LOCK.code)
                return rt.build()
            }
            info.locked = 1

        } else {

            if (info.locked == 0) {
                // 已经锁着了
                rt.rt = (ResultCode.MER_INFO_IS_NO_LOCK.code)
                return rt.build()
            }

            info.locked = 0
        }

        return rt.build()
    }
}


