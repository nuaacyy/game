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
import com.point18.slg2d.home.module.event.PutOnKingEquipCardEvent
import com.point18.slg2d.home.module.event.ResearchEffectChangeEvent
import com.point18.slg2d.home.module.fireEvent
import pb4client.KingEquipAddCard
import pb4client.KingEquipAddCardRt
import java.util.*

// 给一个君主装备打卡片
class KingEquipAddCardDeal(
    private val resHelper: ResHelper = ResHelper(),
    private val targetHelper: TargetHelper = TargetHelper(),
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper(),
    private val refreshRes: RefreshResourceHelper = RefreshResourceHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus4<HomePlayerDC, BagDC, NewEquipDC, HeroDC>(
        HomePlayerDC::class.java,
        BagDC::class.java,
        NewEquipDC::class.java,
        HeroDC::class.java,
        Arrays.asList(resHelper, targetHelper, effectHelper, refreshRes)
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC, bagDC, newEquipDC, heroDC ->
            val equipId = (msg as KingEquipAddCard).equipId
            val cardId = msg.cardId
            val address = msg.address
            val rt = kingEquipAddCard(session, equipId, cardId, address, homePlayerDC, bagDC, newEquipDC, heroDC)
            session.sendMsg(MsgType.KingEquipAddCard_1232, rt)
        }
    }

    fun kingEquipAddCard(
        session: PlayerActor,
        equipId: Long,
        cardId: Long,
        address: Int,
        homePlayerDC: HomePlayerDC,
        bagDC: BagDC,
        newEquipDC: NewEquipDC,
        heroDC: HeroDC
    ): (KingEquipAddCardRt) {
        val rt = KingEquipAddCardRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = homePlayerDC.player

        val itemVo = newEquipDC.findPropById(equipId)
        if (itemVo == null) {
            rt.rt = ResultCode.NO_EQUIP_ERROR.code
            return rt.build()
        }

        val propProto = pcs.equipCache.equipProtoMap[itemVo.equipProtoId]
        if (propProto == null || propProto.mainType != PROP_KING_EQUIP) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        val cardVo = newEquipDC.findPropsByPlayerIdAndBagId(bagDC, NORMAL_BAG, cardId)
        if (cardVo == null) {
            rt.rt = ResultCode.NO_EQUIP_ERROR.code
            return rt.build()
        }

        val cardProto = pcs.equipCache.equipProtoMap[cardVo.equipProtoId]
        if (cardProto == null || cardProto.mainType != PROP_KING_EQUIP_CARD) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        val cardNum = propProto.extend1.toIntOrNull() // 这个装备能打卡片的个数
        if (cardNum == null || address > cardNum) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        // 检测当前有没有这个类型的卡片已经佩戴
        val lordEquipCardProto = pcs.lordEquipCardProtoCache.lordEquipCardProtoMapBySelf[cardProto.id]
        if (lordEquipCardProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        for ((_, cpId) in itemVo.cardInfoMap) {
            val cpProto = pcs.lordEquipCardProtoCache.lordEquipCardProtoMapBySelf[cpId]
            if (cpProto == null) {
                rt.rt = ResultCode.NO_PROTO.code
                return rt.build()
            }
            if (cpProto.group == lordEquipCardProto.group) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                return rt.build()
            }
        }

        val nowCard = itemVo.cardInfoMap[address]
        if (nowCard == null) {
            // 槽位空闲,直接装上
            itemVo.cardInfoMap[address] = cardVo.equipProtoId
            val costs = ResVo(RES_PROPS, cardVo.equipProtoId.toLong(), 1)
            val checkCost = resHelper.checkRes(session, costs)
            if (!checkCost) {
                rt.rt = ResultCode.LESS_RESOUCE.code
                return rt.build()
            }

            resHelper.costRes(session, ACTION_MAKE_KING_EQUIP_ADD_CARD, player, costs)

        } else {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

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

        fireEvent(session, PutOnKingEquipCardEvent(cardVo.equipProtoId))

        return rt.build()
    }

}