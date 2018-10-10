package com.point18.slg2d.home.module.merchantShip

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.UiConditionCheckHelper
import com.point18.slg2d.home.common.merchantShipRefresh
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.InnerCityDC
import com.point18.slg2d.home.dc.MerchantShipDC
import com.point18.slg2d.home.helper.HomeHelperPlus3
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.MerchantShipQueryRt
import pb4client.MerchantShipRecord
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class MerchantShipQueryDeal(
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
        prepare(session) { homePlayerDC, innerCityDC, merchantShipDC ->
            val rt = merchantShipQuery(session, homePlayerDC, innerCityDC, merchantShipDC)
            session.sendMsg(MsgType.MerchantShipQuery_32, rt)
        }
    }

    private fun merchantShipQuery(
        session: PlayerActor,
        homePlayerDC: HomePlayerDC,
        innerCityDC: InnerCityDC,
        merchantShipDC: MerchantShipDC
    ): MerchantShipQueryRt {
        val rt = MerchantShipQueryRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.refreshTime = 0

        // 功能开启
        val re = uiConditionHelper.uiConditionCheck(session, com.point18.slg2d.common.constg.SHIP_OPEN)
        if (re != ResultCode.SUCCESS.code) {
            rt.rt = re
            return rt.build()
        }

        // 检测商船开启条件
        val player = homePlayerDC.player
        val needBuilding = innerCityDC.findMaxLvInnerBuildingFromCastleIdAndType(
            player.focusCastleId,
            pcs.basicProtoCache.tradShipOpenCondition[1]
        )
        if (needBuilding == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }
        if (needBuilding.lv < pcs.basicProtoCache.tradShipOpenCondition[2]) {
            rt.rt = ResultCode.UI_CONDITION_ERROR.code
            return rt.build()
        }

        var sMerchantShip = merchantShipDC.merchantShip
        if (sMerchantShip == null) {
            val mainBuilding =
                innerCityDC.findMaxLvInnerBuildingFromCastleIdAndType(
                    player.focusCastleId,
                    com.point18.slg2d.common.constg.MainBuilding
                )
            if (mainBuilding == null) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                return rt.build()
            }
            val (result, shopInfoList) = merchantShipRefresh(mainBuilding.lv)
            if (result != ResultCode.SUCCESS) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                return rt.build()
            }

            if (shopInfoList == null) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                return rt.build()
            }

            sMerchantShip = merchantShipDC.createMerchantShip(shopInfoList)
        }

        // 检测是否需要刷新一下
        val refreshResult = refreshShipInfo(homePlayerDC, merchantShipDC, innerCityDC)

        if (refreshResult != ResultCode.SUCCESS.code) {
            rt.rt = (refreshResult)
            return rt.build()
        }

//	rt.recordsBuilderList = LinkedList()
        for ((_, info) in sMerchantShip.shopInfoList) {
            val shipRecordBuilder = MerchantShipRecord.newBuilder()
            shipRecordBuilder.exchangeId = info.exchangeId
            shipRecordBuilder.srcProtoId = info.srcProtoId
            shipRecordBuilder.srcType = info.srcType
            shipRecordBuilder.exchanged = info.exchanged
            shipRecordBuilder.locked = info.locked
            rt.addRecords(shipRecordBuilder)
        }

        rt.refreshTime = (com.point18.slg2d.common.commonfunc.getTimeSec(sMerchantShip.nextReTime))

        return rt.build()
    }
}

fun refreshShipInfo(homePlayerDc: HomePlayerDC, shipDc: MerchantShipDC, innerCityDc: InnerCityDC): Int {
    val sMerchantShip = shipDc.merchantShip
    if (sMerchantShip == null) {
        return -1 // todo
    }

    val player = homePlayerDc.player
    val now = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
    if (com.point18.slg2d.common.commonfunc.getTimeSec(now) >= com.point18.slg2d.common.commonfunc.getTimeSec(
            sMerchantShip.nextReTime
        )
    ) {
        // 当前时间大于下次刷新时间,实行刷新
        //  刷新
        val building =
            innerCityDc.findMaxLvInnerBuildingFromCastleIdAndType(
                player.focusCastleId,
                com.point18.slg2d.common.constg.MainBuilding
            )
        if (building == null) {
            return ResultCode.PARAMETER_ERROR.code
        }

        val (result1, shopInfoList) = merchantShipRefresh(building.lv)
        if (result1 != ResultCode.SUCCESS || shopInfoList == null) {
            return result1.code
        }

        val nowShopInfoList = sMerchantShip.shopInfoList

        for ((index, info) in nowShopInfoList) {

            if (info.locked == 1) {
                // 锁着 不动
                continue
            }
            info.srcType = 1
            val shopInfoList = shopInfoList[index]
            if (shopInfoList == null) {
                return ResultCode.PARAMETER_ERROR.code
            }
            info.srcProtoId = shopInfoList.srcProtoId
            info.exchanged = 0
            info.exchangeId = index
        }

        //  改写下次刷新时间
        val diffSec =
            (com.point18.slg2d.common.commonfunc.getTimeSec(now) - com.point18.slg2d.common.commonfunc.getTimeSec(
                sMerchantShip.nextReTime
            )) % pcs.basicProtoCache.tradShipRefreshTime
        sMerchantShip.nextReTime = now + (pcs.basicProtoCache.tradShipRefreshTime - diffSec) * 1000
        sMerchantShip.shopInfoList = sMerchantShip.shopInfoList
        sMerchantShip.nowTimes += 1
        if (sMerchantShip.nowTimes > pcs.basicProtoCache.tradShipSurpriseMaxNum) {
            sMerchantShip.nowTimes = 1
        }
    }

    return ResultCode.SUCCESS.code
}


