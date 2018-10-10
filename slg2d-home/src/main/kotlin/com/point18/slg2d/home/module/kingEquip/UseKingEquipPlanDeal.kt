package com.point18.slg2d.home.module.kingEquip

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.KingEquipPlanNum
import com.point18.slg2d.common.constg.NO_FILTER
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
import pb4client.KingEquip
import pb4client.UseKingEquipPlan
import pb4client.UseKingEquipPlanRt
import java.util.*

// 套用一个君主装备预设
class UseKingEquipPlanDeal(
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
            val planId = (msg as UseKingEquipPlan).planId
            val rt = useKingEquipPlan(session, planId, homePlayerDC, bagDC, newEquipDC, heroDC, kingEquipPlanDC)
            session.sendMsg(MsgType.UseKingEquipPlan_1230, rt)
        }
    }

    private fun useKingEquipPlan(
        session: PlayerActor,
        planId: Int,
        homePlayerDC: HomePlayerDC,
        bagDC: BagDC,
        newEquipDC: NewEquipDC,
        heroDC: HeroDC,
        kingEquipPlanDC: KingEquipPlanDC
    ): (UseKingEquipPlanRt) {
        val rt = UseKingEquipPlanRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val playerId = session.playerId

        val player = homePlayerDC.player

        if (player.nowUseKingEquipPlan == planId) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        // 检测客户端发来的基本数据
        if (planId > effectHelper.getResearchEffectValue(session, NO_FILTER, KingEquipPlanNum)) {
            rt.rt = ResultCode.KING_EQUIP_PLAN_NUM_ERROR.code
            return rt.build()
        }

        var playerPlan = kingEquipPlanDC.findKingEquipPlanByPlayerIdAndId(planId)
        if (playerPlan == null) {
            playerPlan = kingEquipPlanDC.createKingEquipPlan(planId, planId.toString(), hashMapOf(), playerId)
        }

        // 检测一下预设里的数据有没有异常
        val protoMap = hashMapOf<Int, Int>()

        val reallyPlanInfo = hashMapOf<Long, Int>()
        for ((kingEquipId, port) in playerPlan.planMap) {
            val ex = protoMap[port]
            if (ex != null) {
                rt.rt = ResultCode.KING_EQUIP_ON_POS_ERROR.code
                return rt.build()
            }
            val itemVo = newEquipDC.findPropById(kingEquipId)
            if (itemVo != null) {
                reallyPlanInfo[kingEquipId] = port
            } else {
                // 如果预设里的一个装备已经不存在,返回出去  否则的话记录下来
                continue
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

            val canOn = lordEquipPos.positionIdMap[port]
            if (canOn == null) {
                rt.rt = ResultCode.KING_EQUIP_PLAN_MORE_PORT_ERROR.code
                return rt.build()
            }

            // 检测是否传来了多个装备穿一个位置
            protoMap[port] = 1

        }

        if (reallyPlanInfo.size != playerPlan.planMap.size) {
            playerPlan.planMap = reallyPlanInfo
        }

        // 卸下身上所有的装备,然后把预设中的都给穿上
        val newOnEquips = newEquipDC.findKingEquipsByPlayerId(bagDC, playerId)
        for (e in newOnEquips) {
            newEquipDC.offKingEquip(bagDC, e)
        }

        // 把预设里的装备都穿上
        val newKingEquips = LinkedList<KingEquip>()
        for ((kingEquipId, port) in reallyPlanInfo) {
            val itemVo = newEquipDC.findPropById(kingEquipId)
            if (itemVo == null) {
                continue
            }
            newEquipDC.onKingEquip(bagDC, playerId, itemVo, port)
            val kingEquip = KingEquip.newBuilder()
            kingEquip.equipId = kingEquipId
            kingEquip.equipProt = port

            newKingEquips.add(kingEquip.build())
        }

        player.nowUseKingEquipPlan = planId

        // 刷新战斗力等
        val kingEquipAdd = getKingEquipEffect(player, heroDC, newEquipDC, bagDC)
        fireEvent(session, ResearchEffectChangeEvent(kingEquipAdd, targetHelper, effectHelper, refreshRes))

        rt.addAllKingEquips(newKingEquips)
        return rt.build()
    }

}