package com.point18.slg2d.home.module.kingEquip

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.NORMAL_BAG
import com.point18.slg2d.common.constg.NO_FILTER
import com.point18.slg2d.common.constg.OpenKingEquipNum
import com.point18.slg2d.common.constg.PROP_KING_EQUIP
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.*
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus5
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.ResearchEffectChangeEvent
import com.point18.slg2d.home.module.fireEvent
import pb4client.OnKingEquip
import pb4client.OnKingEquipRt
import java.util.*

// 穿君主装备
class PutOnKingEquipDeal(
    private val targetHelper: TargetHelper = TargetHelper(),
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper(),
    private val refreshRes: RefreshResourceHelper = RefreshResourceHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus5<HomePlayerDC, BagDC, NewEquipDC, HeroDC, KingEquipPlanDC>(
        HomePlayerDC::class.java,
        BagDC::class.java,
        NewEquipDC::class.java,
        HeroDC::class.java,
        KingEquipPlanDC::class.java,
        Arrays.asList(targetHelper, effectHelper, refreshRes)
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC, bagDC, newEquipDC, heroDC, kingEquipPlanDC ->
            val equipId = (msg as OnKingEquip).equipId
            val position = msg.position
            val rt =
                putOnKingEquip(session, equipId, position, homePlayerDC, bagDC, newEquipDC, heroDC, kingEquipPlanDC)
            session.sendMsg(MsgType.OnKingEquip_1224, rt)
        }

    }

    private fun putOnKingEquip(
        session: PlayerActor,
        equipId: Long,
        position: Int,
        homePlayerDC: HomePlayerDC,
        bagDC: BagDC,
        newEquipDC: NewEquipDC,
        heroDC: HeroDC,
        kingEquipPlanDC: KingEquipPlanDC
    ): (OnKingEquipRt) {
        val rt = OnKingEquipRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.equipId = equipId
        rt.position = position

        val playerId = session.playerId
        val player = homePlayerDC.player

        if (position < effectHelper.getResearchEffectValue(
                session,
                NO_FILTER,
                OpenKingEquipNum
            )
        ) {
            rt.rt = ResultCode.KING_EQUIP_NO_OPEN_LOCK.code
            return rt.build()
        }
        val itemVo = newEquipDC.findPropsByPlayerIdAndBagId(bagDC, NORMAL_BAG, equipId)
        if (itemVo == null) {
            rt.rt = ResultCode.NO_EQUIP_ERROR.code
            return rt.build()
        }

        val propProto = pcs.equipCache.equipProtoMap[itemVo.equipProtoId]
        if (propProto == null || propProto.mainType != PROP_KING_EQUIP) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        if (player.kingLv < propProto.level) {
            rt.rt = ResultCode.KING_EQUIP_SUIT_CONDITION_ERROR.code
            return rt.build()
        }

        val lordEquipPos = pcs.lordEquipmentPositionCache.lordEquipmentPositionMap[propProto.subType]
        if (lordEquipPos == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        val canOn = lordEquipPos.positionIdMap[position]
        if (canOn == null) {
            rt.rt = ResultCode.KING_EQUIP_ON_POS_ERROR.code
            return rt.build()
        }

        // 检测是否之前就有装备穿戴着
        val oldEquip = newEquipDC.findKingEquipByPlayerIdAndPart(bagDC, position)
        if (oldEquip != null) {
            newEquipDC.offKingEquip(bagDC, oldEquip)
        }

        val planInfo = kingEquipPlanDC.findKingEquipPlanByPlayerIdAndId(player.nowUseKingEquipPlan)
        if (planInfo == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        // 穿戴装备,背包的转移
        newEquipDC.onKingEquip(bagDC, playerId, itemVo, position)

        // 战斗力刷新,计算套装
        val kingEquipAdd = getKingEquipEffect(player, heroDC, newEquipDC, bagDC)
        fireEvent(session, ResearchEffectChangeEvent(kingEquipAdd, targetHelper, effectHelper, refreshRes))

        // 修改预设方案
        val plan = hashMapOf<Long, Int>()
        val nowEquipInfo = newEquipDC.findKingEquipsByPlayerId(bagDC, playerId)
        for (e in nowEquipInfo) {
            plan[e.id] = e.equipOnAddress
        }
        planInfo.planMap = plan

        return rt.build()
    }

}