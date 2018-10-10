package com.point18.slg2d.home.module.equip

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
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
import pb4client.ItemCompound
import pb4client.ItemCompoundRt
import java.util.*
import java.util.Arrays.asList

// 道具合成
class ItemCompoundDeal(
    private val resHelper: ResHelper = ResHelper(),
    private val propHelper: PropsHelper = PropsHelper()
) : HomeClientMsgDeal, HomeHelperPlus3<HomePlayerDC, NewEquipDC, BagDC>(
    HomePlayerDC::class.java, NewEquipDC::class.java, BagDC::class.java,
    asList(resHelper, propHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, newEquipDC: NewEquipDC, bagDC: BagDC ->
            val cPropId = (msg as ItemCompound).compoundPropId
            val propsId = LinkedList(msg.propIdsList)
            val num = msg.num
            val compoundRt = itemCompound(
                session, cPropId, propsId, num,
                homePlayerDC, newEquipDC, bagDC
            )
            session.sendMsg(MsgType.ItemCompound_183, compoundRt)
        }
    }

    private fun itemCompound(
        session: PlayerActor,
        cPropId: Int,
        propsId: LinkedList<Long>,
        num: Int,
        homePlayerDC: HomePlayerDC,
        newEquipDC: NewEquipDC,
        bagDC: BagDC
    ): ItemCompoundRt {
        val rt = ItemCompoundRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val action = ACTION_EQUIP_COMP
        if (num <= 0) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        val compoundProto = pcs.compoundCache.compoundProtoMap[cPropId]
        if (compoundProto == null) {
            rt.rt = ResultCode.NO_BUY_PROTO.code
            return rt.build()
        }

        val player = homePlayerDC.player
        // 如果是君主装备,检测君主装备仓库容量
        val equipProto = pcs.equipCache.equipProtoMap[compoundProto.propsId]
        if (equipProto == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }
        if (equipProto.mainType == PROP_KING_EQUIP) {
            val kingEquipNum = newEquipDC.findAllKingEquipsByPlayerId()
            if (kingEquipNum >= player.kingEquipBagNum) {
                rt.rt = ResultCode.King_EQUIP_BAG_ERROR.code
                return rt.build()
            }
        }

        val costs = ResVo(RES_COIN, NOT_PROPS_SUB_TYPE, (compoundProto.cost * num).toLong())
        val checkCost = resHelper.checkRes(session, costs)
        if (!checkCost) {
            rt.rt = ResultCode.LESS_RESOUCE.code
            return rt.build()
        }

        if (propsId.size != compoundProto.itemIdMap.size) {
            rt.rt = ResultCode.COMMON_TAOZHUANG_SANJIAN_ERROR.code
            return rt.build()
        }

        //验证部件
        for (pId in propsId) {
            val propVo = newEquipDC.findPropById(pId)
            if (propVo == null) {
                rt.rt = ResultCode.NO_EQUIP_ERROR.code
                return rt.build()
            }
            val propProto = pcs.equipCache.equipProtoMap[propVo.equipProtoId]
            if (propProto == null) {
                rt.rt = ResultCode.NO_BUY_PROTO.code
                return rt.build()
            }

            if (newEquipDC.findEquipIsOnHero(bagDC, propVo.id) != 0L) {
                rt.rt = ResultCode.EQUIP_NO_IN_BAG_ERROR.code
                return rt.build()
            }
            //验证装备
            var isTrue = false
            for ((itemId, itemNum) in compoundProto.itemIdMap) {
                if (itemId == propVo.equipProtoId) {
                    isTrue = true
                    if (propVo.haveNum < itemNum * num) {
                        rt.rt = ResultCode.ITEM_HECHENG_NO_EN_ERROR.code
                        return rt.build()
                    }
                }
            }
            if (!isTrue) {
                //上面循环验证结束这个字段仍然是flase说明 这个装备根本不在配方里
                rt.rt = ResultCode.COMMON_TAOZHUANG_SANJIAN_ERROR.code
                return rt.build()
            }
        }

        resHelper.costRes(session, action, player, costs)

        // 验证结束,删除物品.收到新物品
        val newEquips = propHelper.getProps(session, compoundProto.propsId, num)

        // 推送给客户端
        val propsChangeNotifier = createPropsChangeNotifier()
        for (newEquip in newEquips) {
            propsChangeNotifier.append(
                AddProps,
                newEquip.id,
                newEquip.equipProtoId,
                num,
                newEquip.lv,
                newEquip.exp,
                newEquip.propertyMap
            )
        }

        for (pId in propsId) {
            val propVo = newEquipDC.findPropById(pId)
            if (propVo == null) {
                continue
            }
            var allNum = 0
            for ((itemId, itemNum) in compoundProto.itemIdMap) {
                if (itemId == propVo.equipProtoId) {
                    allNum = itemNum * num
                }
            }

            // 删除物品并推送给客户端
            propsChangeNotifier.append(
                RemoveProps,
                propVo.id,
                propVo.equipProtoId,
                allNum,
                propVo.lv,
                propVo.exp,
                propVo.propertyMap
            )

            propHelper.removeProps(session, propVo.id, allNum)
        }
        propsChangeNotifier.notice(session)

        return rt.build()
    }

}