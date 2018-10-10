package com.point18.slg2d.home.module.equip

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.ACTION_SELL_EQUIP
import com.point18.slg2d.common.constg.RemoveProps
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.pc.resVoAddX
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.PropsHelper
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.BagDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.NewEquipDC
import com.point18.slg2d.home.helper.HomeHelperPlus3
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.msgnotice.createPropsChangeNotifier
import pb4client.SellEquip
import pb4client.SellEquipRt
import java.util.*
import java.util.Arrays.asList

// 出售装备
class SellEquipDeal(
    private val resHelper: ResHelper = ResHelper(),
    private val propHelper: PropsHelper = PropsHelper()
) : HomeClientMsgDeal, HomeHelperPlus3<HomePlayerDC, NewEquipDC, BagDC>(
    HomePlayerDC::class.java, NewEquipDC::class.java, BagDC::class.java,
    asList(resHelper, propHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, newEquipDC: NewEquipDC, bagDC: BagDC ->
            val ss = (msg as SellEquip).sellVosList
            val sellVos = hashMapOf<Long, Long>()
            for (s in ss) {
                sellVos[s.equipId] = s.equipNum
            }
            val sellEquipRt = sellEquip(
                session, sellVos, homePlayerDC, newEquipDC, bagDC
            )
            session.sendMsg(MsgType.SellEquip_175, sellEquipRt)
        }
    }

    private fun sellEquip(
        session: PlayerActor, sellVos: HashMap<Long, Long>,
        homePlayerDC: HomePlayerDC, newEquipDC: NewEquipDC, bagDC: BagDC
    ): SellEquipRt {

        val rt = SellEquipRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        val action = ACTION_SELL_EQUIP

        val player = homePlayerDC.player

        val addRes = LinkedList<ResVo>()
        // 删除物品并推送给客户端
        val propsChangeNotifier = createPropsChangeNotifier()
        for ((equipId, num) in sellVos) {
            val equipVo = newEquipDC.findPropById(equipId)
            if (equipVo == null) {
                rt.rt = ResultCode.NO_EQUIP_ERROR.code
                return rt.build()
            }

            if (equipVo.haveNum < num) {
                rt.rt = ResultCode.HERO_CARD_NOT_ENOUGH.code
                return rt.build()
            }

            if (newEquipDC.findEquipIsOnHero(bagDC, equipVo.id) != 0L) {
                rt.rt = ResultCode.EQUIP_NO_IN_BAG_ERROR.code
                return rt.build()
            }

            val equipProto = pcs.equipCache.equipProtoMap[equipVo.equipProtoId]
            if (equipProto == null) {
                rt.rt = ResultCode.NO_BUY_PROTO.code
                return rt.build()
            }

            val (ok, newRes) = resVoAddX(equipProto.salePriceMap, num.toInt())
            if (!ok) {
                continue
            }

            propsChangeNotifier.append(
                RemoveProps,
                equipVo.id,
                equipVo.equipProtoId,
                num.toInt(),
                equipVo.lv,
                equipVo.exp,
                equipVo.propertyMap
            )


            addRes += newRes
        }

        for ((equipId, num) in sellVos) {
            propHelper.removeProps(session, equipId, num.toInt())
        }

        resHelper.addRes(session, action, player, addRes)
        propsChangeNotifier.notice(session)

        return rt.build()
    }

}