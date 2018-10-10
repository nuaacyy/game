package com.point18.slg2d.home.module.kingEquip

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.ACTION_MAKE_KING_EQUIP_OFF_CARD
import com.point18.slg2d.common.constg.PROP_KING_EQUIP
import com.point18.slg2d.common.constg.RES_PROPS
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.pc.props2GoldCost
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.*
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus4
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.ResearchEffectChangeEvent
import com.point18.slg2d.home.module.fireEvent
import pb4client.KingEquipOffCard
import pb4client.KingEquipOffCardRt
import java.util.*

// 取下君主装备上的卡片
class KingEquipOffCardDeal(
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
            val equipId = (msg as KingEquipOffCard).equipId
            val address = msg.address
            val rt = kingEquipOffCard(session, equipId, address, homePlayerDC, bagDC, newEquipDC, heroDC)
            session.sendMsg(MsgType.KingEquipOffCard_1233, rt)
        }
    }

    private fun kingEquipOffCard(
        session: PlayerActor,
        equipId: Long,
        address: Int,
        homePlayerDC: HomePlayerDC,
        bagDC: BagDC,
        newEquipDC: NewEquipDC,
        heroDC: HeroDC
    ): (KingEquipOffCardRt) {
        val rt = KingEquipOffCardRt.newBuilder()
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

        val cardNum = propProto.extend1.toIntOrNull() // 这个装备能打卡片的个数
        if (cardNum == null || address > cardNum) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        val nowCard = itemVo.cardInfoMap[address]
        if (nowCard == null) {
            // 槽位空闲,异常
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        val action = ACTION_MAKE_KING_EQUIP_OFF_CARD

        // 检测拆宝石的道具
        var costs = LinkedList<ResVo>()
        costs.addAll(pcs.basicProtoCache.removeEquipCard)
        val checkCost = resHelper.checkRes(session, costs)
        if (!checkCost) {
            val (ok, needRes) = props2GoldCost(costs[0])
            if (ok != ResultCode.SUCCESS) {
                rt.rt = (ResultCode.LESS_RESOUCE.code)
                return rt.build()
            }

            //校验需要的资源
            if (!resHelper.checkRes(session, needRes)) {
                rt.rt = (ResultCode.LESS_RESOUCE.code)
                return rt.build()
            }

            costs = needRes
        }

        resHelper.costRes(session, action, player, costs)

        itemVo.cardInfoMap.remove(address)

        // 把那个卡片返还给玩家
        val reward = LinkedList<ResVo>()
        reward += ResVo(RES_PROPS, nowCard.toLong(), 1)
        resHelper.addRes(session, action, player, reward)

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


        fireEvent(
            session,
            ResearchEffectChangeEvent(
                kingEquipAdd,
                targetHelper,
                effectHelper,
                refreshRes
            )
        )
        return rt.build()
    }

}