package com.point18.slg2d.home.module.kingEquip

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.findValueFromDropBag
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.pc.resVoToResString
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.*
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus4
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.msgnotice.createPropsChangeNotifier
import pb4client.SplitKingEquip
import pb4client.SplitKingEquipRt
import java.util.*

// 分解君主装备
class SplitKingEquipDeal(
    private val resHelper: ResHelper = ResHelper(),
    private val propHelper: PropsHelper = PropsHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus4<HomePlayerDC, BagDC, NewEquipDC, InMakeKingEquipDC>(
        HomePlayerDC::class.java,
        BagDC::class.java,
        NewEquipDC::class.java,
        InMakeKingEquipDC::class.java,
        Arrays.asList(resHelper, propHelper)
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC, bagDC, newEquipDC, inMakeKingEquipDC ->
            val equipId = (msg as SplitKingEquip).equipId
            val rt = splitKingEquip(session, equipId, homePlayerDC, bagDC, newEquipDC, inMakeKingEquipDC)
            session.sendMsg(MsgType.SplitKingEquip_1226, rt)
        }
    }

    fun splitKingEquip(
        session: PlayerActor,
        equipId: Long,
        homePlayerDC: HomePlayerDC,
        bagDC: BagDC,
        newEquipDC: NewEquipDC,
        inMakeKingEquipDC: InMakeKingEquipDC
    ): (SplitKingEquipRt) {
        val rt = SplitKingEquipRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.equipId = equipId
        rt.res = ""

        val player = homePlayerDC.player

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

        val nowMakeEquip = inMakeKingEquipDC.findInMakeByPlayerId()
        for (inMake in nowMakeEquip) {
            if (inMake.costEquipId == equipId) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                return rt.build()
            }
        }

        val addRes = LinkedList<ResVo>()
        // 把装备上打的宝石返回给玩家
        for ((_, cardProtoId) in itemVo.cardInfoMap) {
            addRes += ResVo(RES_PROPS, cardProtoId.toLong(), 1)
        }

        // 进行拆解
        // 删除物品并推送给客户端
        val action = ACTION_SPLIT_KING_EQUIP

        val propNotifier = createPropsChangeNotifier()
        propNotifier.append(
            RemoveProps,
            itemVo.id,
            itemVo.equipProtoId,
            1,
            itemVo.lv,
            itemVo.exp,
            itemVo.propertyMap
        )
        propHelper.removeProps(session, itemVo.id, 1)
        propNotifier.notice(session)

        // 返额外资源

        for (i in 0..(propProto.destroyRandomNum - 1)) {
            val splitReward = findValueFromDropBag(propProto.destroyRandomMap)
            if (splitReward != 0) {
                val dropId = splitReward
                //commonfunc.debugAssert(err == null, "拆解君主装备转型错误", splitReward)
                val drop = pcs.dropBagCache.dropBagMap[dropId]
                if (drop == null) {
                    continue
                    //  commonfunc.debugAssert(ex, "拆解君主装备在掉落表里找不到", dropId)
                }
                addRes += drop.dropMap
            }
        }

        addRes += propProto.destroyTureMap

        resHelper.addRes(session, action, player, addRes)

        rt.res = resVoToResString(addRes)

        return rt.build()
    }

}
