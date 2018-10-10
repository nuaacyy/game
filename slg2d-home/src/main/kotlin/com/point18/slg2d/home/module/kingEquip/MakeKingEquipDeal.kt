package com.point18.slg2d.home.module.kingEquip

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
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
import com.point18.slg2d.home.module.event.GetKingEquipEvent
import com.point18.slg2d.home.module.event.PropChangeEvent
import com.point18.slg2d.home.module.fireEvent
import com.point18.slg2d.home.msgnotice.createMakeKingEquipChangeNotifier
import pb4client.MakeKingEquip
import pb4client.MakeKingEquipRt
import java.util.*
import java.util.Arrays.asList

// 锻造君主装备
class MakeKingEquipDeal(
    private val resHelper: ResHelper = ResHelper(),
    private val targetHelper: TargetHelper = TargetHelper(),
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus4<HomePlayerDC, NewEquipDC, InMakeKingEquipDC, HomeMyTargetDC>(
        HomePlayerDC::class.java,
        NewEquipDC::class.java,
        InMakeKingEquipDC::class.java,
        HomeMyTargetDC::class.java,
        Arrays.asList(resHelper, targetHelper, effectHelper)
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC, newEquipDC, inMakeKingEquipDC, homeMyTargetDC ->
            val makeProto = (msg as MakeKingEquip).makeProto
            val costEquipId = msg.equipId
            val makeType = msg.makeType
            val rt = makeKingEquip(
                session,
                makeProto,
                costEquipId,
                makeType.toInt(),
                homePlayerDC,
                newEquipDC,
                inMakeKingEquipDC,
                homeMyTargetDC
            )
            session.sendMsg(MsgType.MakeKingEquip_1221, rt)
        }
    }

    private fun makeKingEquip(
        session: PlayerActor,
        makeProtoId: Int,
        costEquipId: Long,
        makeType: Int,
        homePlayerDC: HomePlayerDC,
        newEquipDC: NewEquipDC,
        inMakeKingEquipDC: InMakeKingEquipDC,
        homeMyTargetDC: HomeMyTargetDC
    ): (MakeKingEquipRt) {
        val rt = MakeKingEquipRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val playerId = session.playerId
        val player = homePlayerDC.player

        val makeProto = pcs.lordEquipmentCache.lordEquipmentMap[makeProtoId]
        if (makeProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        if (makeType != NORMAL_MAKE_KING_EQUIP && makeType != YBL_MAKE_KING_EQUIP) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        if (makeProto.preEquipment == 0 && costEquipId != 0L) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        if (makeProto.preEquipment != 0 && costEquipId == 0L) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        val kingEquipNum = newEquipDC.findAllKingEquipsByPlayerId()
        if (kingEquipNum >= player.kingEquipBagNum) {
            rt.rt = ResultCode.King_EQUIP_BAG_ERROR.code
            return rt.build()
        }

        val propProto = pcs.equipCache.equipProtoMap[makeProto.propsID]
        if (propProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        val nowTime = getNowTime()
        if (costEquipId != 0L) {
            // 判断物品数据准确性
            val itemVo = newEquipDC.findPropById(costEquipId)
            if (itemVo == null) {
                rt.rt = ResultCode.NO_EQUIP_ERROR.code
                return rt.build()
            }

            val itemProto = pcs.equipCache.equipProtoMap[itemVo.equipProtoId]
            if ((itemProto == null) || itemProto.mainType != PROP_KING_EQUIP) {
                rt.rt = ResultCode.NO_PROTO.code
                return rt.build()
            }

            if (itemProto.id != makeProto.preEquipment) {
                rt.rt = ResultCode.KING_EQUIP_CONDITION_ERROR.code
                return rt.build()
            }
        }

        val inMakeList = inMakeKingEquipDC.findInMakeByPlayerId()
        if (inMakeList.size != 0) {
            rt.rt = ResultCode.KING_EQUIP_DUILIE_ERROR.code
            return rt.build()
        }

        // 一般资源检测
        val costs = LinkedList<ResVo>()
        costs += makeProto.forgeCostMap

        val action = ACTION_MAKE_KING_EQUIP

        if (makeType == YBL_MAKE_KING_EQUIP) {
            val reallyCost = LinkedList<ResVo>()
            reallyCost += makeProto.forgeMoneyMap

            // 补齐资源的模式
            var allCost = 0.0
            val (isCheck, lockVos, haveVos) = resHelper.checkAndTellRes(session, player, reallyCost)
            if (!isCheck) {
                rt.rt = ResultCode.RES_ERROR.code
                return rt.build()
            }

            val equipProto = pcs.equipCache.equipProtoMap[makeProto.propsID]
            if (equipProto == null) {
                rt.rt = ResultCode.NO_PROTO.code
                return rt.build()
            }

            for (lockVo in lockVos) {
                if (lockVo.lackType == RES_BIND_GOLD) {
                    allCost += lockVo.lackNum
                } else {
                    val cost = pcs.resShopCache.needCost(RESSHOP_TYPE_RES, lockVo.lackType, lockVo.lackNum)
                    allCost += cost
                }
            }

            for (haveVo in haveVos) {
                if (haveVo.extend1 == 0) {
                    costs += ResVo(haveVo.lackType, NOT_PROPS_SUB_TYPE, haveVo.lackNum)
                } else {
                    costs += ResVo(haveVo.lackType, haveVo.extend1.toLong(), haveVo.lackNum)
                }
            }

            val diffSec = makeProto.forgeTime
            val cost = pcs.resShopCache.needCost(RESSHOP_TYPE_CLEAN_TIME, 1, diffSec)
            costs += ResVo(
                RES_BIND_GOLD,
                NOT_PROPS_SUB_TYPE,
                (Math.ceil(cost).toInt()) + (Math.ceil(allCost).toInt()).toLong()
            )

        } else {
            costs += makeProto.forgeMoneyMap
        }

        val checkCost = resHelper.checkRes(session, costs)
        if (!checkCost) {
            rt.rt = ResultCode.LESS_RESOUCE.code
            return rt.build()
        }

        resHelper.costRes(session, action, player, costs)

        // 开始锻造
        val kingEquipId = makeProto.propsID
        if (makeType == YBL_MAKE_KING_EQUIP) {
            rt.equipProto = kingEquipId
            if (costEquipId == 0L) {
                // 锻造
                val reward = LinkedList<ResVo>()
                reward += ResVo(RES_PROPS, kingEquipId.toLong(), 1)

                resHelper.addRes(session, ACTION_GET_KING_EQUIP_BY_YBMAKE, player, reward)

                //添加统计
                val equipProto = pcs.equipCache.equipProtoMap[kingEquipId]
                if (equipProto != null) {
                    targetHelper.targetAddVal(
                        session,
                        MakeEquip,
                        LinkedList(asList(equipProto.quality, 1))
                    )
                }

                homeMyTargetDC.targetInfo.getKingEquipNum++
            } else {
                // 升阶
                val itemVo = newEquipDC.findPropById(costEquipId)
                if (itemVo == null) {
                    rt.rt = ResultCode.NO_EQUIP_ERROR.code
                    return rt.build()
                }
                itemVo.equipProtoId = kingEquipId
            }

            fireEvent(session, GetKingEquipEvent(kingEquipId))
        } else {

            val add =
                effectHelper.getResearchEffectValue(session, NO_FILTER, MakeKingEquipSpeedAdd)
            val overTime = nowTime + ((makeProto.forgeTime / (1 + (add.toDouble() / 10000))).toInt() * 1000)

            val inMake = inMakeKingEquipDC.createInMakeKingEquip(
                session,
                makeProtoId,
                costEquipId,
                0,
                overTime,
                playerId
            )

            val notice = createMakeKingEquipChangeNotifier(ADD_IN_MAKE_DUILIE, inMake)
            notice.notice(session)
        }

        // 道具升阶
        fireEvent(session, PropChangeEvent(makeProto.propsID))

        return rt.build()
    }

}