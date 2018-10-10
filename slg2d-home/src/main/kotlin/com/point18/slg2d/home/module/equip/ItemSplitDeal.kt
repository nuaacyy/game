package com.point18.slg2d.home.module.equip

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.ACTION_EQUIP_SPLIT
import com.point18.slg2d.common.constg.RES_PROPS
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.ItemCompoundRt
import pb4client.ItemSplit
import java.util.*
import java.util.Arrays.asList

// 道具拆分
class ItemSplitDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(
    HomePlayerDC::class.java, asList(resHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC ->
            val cPropId = (msg as ItemSplit).compoundPropId
            val num = msg.num
            val compoundRt = itemSplit(session, cPropId, num, homePlayerDC)
            session.sendMsg(MsgType.ItemSplit_184, compoundRt)
        }
    }

    private fun itemSplit(session: PlayerActor, cPropId: Int, num: Int, homePlayerDC: HomePlayerDC): ItemCompoundRt {
        val rt = ItemCompoundRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        val action = ACTION_EQUIP_SPLIT

        if (num <= 0) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }
        val compoundProto = pcs.compoundCache.compoundProtoMap[cPropId]
        if (compoundProto == null) {
            rt.rt = (ResultCode.NO_BUY_PROTO.code)
            return rt.build()
        }
        if (compoundProto.isChange == 0) {
            rt.rt = (ResultCode.ITEM_CAN_NO_SPLIT_ERROR.code)
            return rt.build()
        }

        val player = homePlayerDC.player
        val costs = ResVo(RES_PROPS, compoundProto.propsId.toLong(), num.toLong())
        val checkCost = resHelper.checkRes(session, costs)

        if (!checkCost) {
            rt.rt = ResultCode.LESS_RESOUCE.code
            return rt.build()
        }

        val canGetItem = LinkedList<ResVo>()

        for ((itemId, itemNum) in compoundProto.itemIdMap) {
            canGetItem += ResVo(RES_PROPS, itemId.toLong(), itemNum.toLong())
        }

        resHelper.costRes(session, action, player, costs)
        resHelper.addRes(session, action, player, canGetItem)

        return rt.build()
    }

}