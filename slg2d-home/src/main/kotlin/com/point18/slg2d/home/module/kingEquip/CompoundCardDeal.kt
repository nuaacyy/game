package com.point18.slg2d.home.module.kingEquip

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.*
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus4
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.CompoundCardEvent
import com.point18.slg2d.home.module.event.PropChangeEvent
import com.point18.slg2d.home.module.event.ResearchEffectChangeEvent
import com.point18.slg2d.home.module.fireEvent
import pb4client.KingEquipCompoundCard
import pb4client.KingEquipCompoundCardRt
import java.util.*

// 宝石合成
// compound = 1; // 合成类型  1-背包内合  2-装备上的合
// playerId = 2; // 被操作对象的唯一ID  如果是背包内合 给任意一个宝石的唯一ID就可以  如果是装备上的合 就个装备ID
// address = 3; // 如果是装备上的合  给槽位序号  ,如果是背包内合 给需要合成的数量
class CompoundCardDeal(
    private val resHelper: ResHelper = ResHelper(),
    private val targetHelper: TargetHelper = TargetHelper(),
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper(),
    private val refreshRes: RefreshResourceHelper = RefreshResourceHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus4<
            HomePlayerDC,
            HeroDC,
            NewEquipDC,
            BagDC
            >(
        HomePlayerDC::class.java,
        HeroDC::class.java,
        NewEquipDC::class.java,
        BagDC::class.java,
        Arrays.asList(
            resHelper,
            targetHelper,
            effectHelper,
            refreshRes
        )
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC, heroDC, newEquipDC, bagDC ->
            val compound = (msg as KingEquipCompoundCard).compound
            val id = msg.id
            val address = msg.address
            val num = msg.num
            val rt = kingEquipAddCard(session, compound, id, address, num, homePlayerDC, heroDC, newEquipDC, bagDC)
            session.sendMsg(MsgType.KingEquipCompoundCard_1234, rt)
        }
    }

    private fun kingEquipAddCard(
        session: PlayerActor,
        compoundType: Int,
        id: Long,
        address: Int,
        num: Int,
        homePlayerDC: HomePlayerDC,
        heroDC: HeroDC,
        newEquipDC: NewEquipDC,
        bagDC: BagDC
    ): (KingEquipCompoundCardRt) {
        val rt = KingEquipCompoundCardRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = homePlayerDC.player

        if (num < 1) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        var qul = 0 // 合成的宝石的品质
        val action = ACTION_MAKE_KING_EQUIP_COMPOUND_CARD
        if (compoundType == KING_EQUIP_COMPOUND_CARD_IN_BAG) {
            val itemVo = newEquipDC.findPropsByPlayerIdAndBagId(bagDC, NORMAL_BAG, id)
            if (itemVo == null) {
                rt.rt = ResultCode.NO_EQUIP_ERROR.code
                return rt.build()
            }

            val propProto = pcs.equipCache.equipProtoMap[itemVo.equipProtoId]
            if (propProto == null || propProto.mainType != PROP_KING_EQUIP_CARD) {
                rt.rt = ResultCode.NO_PROTO.code
                return rt.build()
            }

            val lordEquipCardProto = pcs.lordEquipCardProtoCache.lordEquipCardProtoMap[itemVo.equipProtoId]
            if (lordEquipCardProto == null) {
                rt.rt = ResultCode.NO_PROTO.code
                return rt.build()
            }

            val superCardProto = pcs.equipCache.equipProtoMap[lordEquipCardProto.propsId]
            if (superCardProto == null || superCardProto.mainType != PROP_KING_EQUIP_CARD) {
                rt.rt = ResultCode.NO_PROTO.code
                return rt.build()
            }

            val costs = ResVo(RES_PROPS, itemVo.equipProtoId.toLong(), (lordEquipCardProto.itemNum * num).toLong())
            val checkCost = resHelper.checkRes(session, costs)
            if (!checkCost) {
                rt.rt = ResultCode.LESS_RESOUCE.code
                return rt.build()
            }

            resHelper.costRes(session, action, player, costs)

            // 获得一个新的宝石
            val reward = LinkedList<ResVo>()
            reward += ResVo(RES_PROPS, lordEquipCardProto.propsId.toLong(), num.toLong())
            qul = superCardProto.quality
            resHelper.addRes(session, action, player, reward)

            // 道具升阶
            fireEvent(session, PropChangeEvent(lordEquipCardProto.propsId))

        } else if (compoundType == KING_EQUIP_COMPOUND_CARD_IN_KING) {
            val itemVo = newEquipDC.findPropById(id)
            if (itemVo == null) {
                rt.rt = ResultCode.NO_EQUIP_ERROR.code
                return rt.build()
            }

            val card = itemVo.cardInfoMap[address]
            if (card == null) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                return rt.build()
            }

            val lordEquipCardProto = pcs.lordEquipCardProtoCache.lordEquipCardProtoMap[card]
            if (lordEquipCardProto == null) {
                rt.rt = ResultCode.NO_PROTO.code
                return rt.build()
            }

            val superCardProto = pcs.equipCache.equipProtoMap[lordEquipCardProto.propsId]
            if (superCardProto == null || superCardProto.mainType != PROP_KING_EQUIP_CARD) {
                rt.rt = ResultCode.NO_PROTO.code
                return rt.build()
            }

            val costNum = lordEquipCardProto.itemNum * num - 1
            if (costNum < 0) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                return rt.build()
            }

            val costs = ResVo(RES_PROPS, card.toLong(), costNum.toLong())
            val checkCost = resHelper.checkRes(session, costs)
            if (!checkCost) {
                rt.rt = ResultCode.LESS_RESOUCE.code
                return rt.build()
            }

            resHelper.costRes(session, action, player, costs)

            // 装备的宝石模版发生变化
            itemVo.cardInfoMap[address] = lordEquipCardProto.propsId

            // 获得一个新的宝石
            if (num != 1) {
                val reward = LinkedList<ResVo>()
                reward += ResVo(RES_PROPS, lordEquipCardProto.propsId.toLong(), (num - 1).toLong())
                resHelper.addRes(session, action, player, reward)
            }

            // 道具升阶
            fireEvent(session, PropChangeEvent(lordEquipCardProto.propsId))
            qul = superCardProto.quality

            // 战斗力刷新,计算套装
            val kingEquipAdd = getKingEquipEffect(player, heroDC, newEquipDC, bagDC)

            fireEvent(
                session,
                ResearchEffectChangeEvent(
                    kingEquipAdd,
                    targetHelper,
                    effectHelper,
                    refreshRes
                )
            )

        } else {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }


        fireEvent(session, CompoundCardEvent(num, qul))

        return rt.build()
    }

}