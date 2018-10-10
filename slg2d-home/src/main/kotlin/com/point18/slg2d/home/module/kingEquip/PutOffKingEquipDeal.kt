package com.point18.slg2d.home.module.kingEquip

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.KING_BAG
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
import pb4client.OffKingEquip
import pb4client.OffKingEquipRt
import java.util.*

// 脱君主装备
class PutOffKingEquipDeal(
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
            val equipId = (msg as OffKingEquip).equipId
            val rt = putOffKingEquip(session, equipId, homePlayerDC, bagDC, newEquipDC, heroDC, kingEquipPlanDC)
            session.sendMsg(MsgType.OffKingEquip_1225, rt)
        }
    }

    fun putOffKingEquip(
        session: PlayerActor,
        equipId: Long,
        homePlayerDC: HomePlayerDC,
        bagDC: BagDC,
        newEquipDC: NewEquipDC,
        heroDC: HeroDC,
        kingEquipPlanDC: KingEquipPlanDC
    ): (OffKingEquipRt) {
        val rt = OffKingEquipRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.equipId = equipId

        val playerId = session.playerId
        val player = homePlayerDC.player

        val itemVo = newEquipDC.findPropsByPlayerIdAndBagId(bagDC, KING_BAG, equipId)
        if (itemVo == null) {
            rt.rt = ResultCode.NO_EQUIP_ERROR.code
            return rt.build()
        }

        val propProto = pcs.equipCache.equipProtoMap[itemVo.equipProtoId]
        if (propProto == null || propProto.mainType != PROP_KING_EQUIP) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        val planInfo = kingEquipPlanDC.findKingEquipPlanByPlayerIdAndId(player.nowUseKingEquipPlan)
        if (planInfo == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        // 穿戴装备,背包的转移
        newEquipDC.offKingEquip(bagDC, itemVo)

        // 战斗力刷新,计算套装
        val kingEquipAdd = getKingEquipEffect(player, heroDC, newEquipDC, bagDC)

        // 这边不光要传入当前装备的情况,还需要放进去被脱下来的那个装备的科技变化
        for ((effType, _) in propProto.basicEffectMap) {
            val vv = kingEquipAdd[effType]
            if (vv == null) {
                kingEquipAdd[effType] = 0
            }
        }

        // 增加宝石的属性
        for ((_, cardProtoId) in itemVo.cardInfoMap) {
            val cardProto = pcs.equipCache.equipProtoMap[cardProtoId]
            if (cardProto == null) {
                continue
            }
            for ((effType, _) in cardProto.basicEffectMap) {
                val vv = kingEquipAdd[effType]
                if (vv == null) {
                    kingEquipAdd[effType] = 0
                }
            }
        }

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