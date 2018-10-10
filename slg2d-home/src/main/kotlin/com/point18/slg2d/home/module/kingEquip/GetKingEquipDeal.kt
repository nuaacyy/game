package com.point18.slg2d.home.module.kingEquip

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.ACTION_GET_KING_EQUIP
import com.point18.slg2d.common.constg.DEL_IN_MAKE_DUILIE
import com.point18.slg2d.common.constg.MakeEquip
import com.point18.slg2d.common.constg.RES_PROPS
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.common.TargetHelper
import com.point18.slg2d.home.dc.HomeMyTargetDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.InMakeKingEquipDC
import com.point18.slg2d.home.dc.NewEquipDC
import com.point18.slg2d.home.helper.HomeHelperPlus4
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.GetKingEquipEvent
import com.point18.slg2d.home.module.fireEvent
import com.point18.slg2d.home.msgnotice.createMakeKingEquipChangeNotifier
import pb4client.GetKingEquip
import pb4client.GetKingEquipRt
import java.util.*
import java.util.Arrays.asList

// 领取君主装备
class GetKingEquipDeal(
    private val resHelper: ResHelper = ResHelper(),
    private val targetHelper: TargetHelper = TargetHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus4<HomePlayerDC, InMakeKingEquipDC, NewEquipDC, HomeMyTargetDC>(
        HomePlayerDC::class.java,
        InMakeKingEquipDC::class.java,
        NewEquipDC::class.java,
        HomeMyTargetDC::class.java,
        Arrays.asList(resHelper, targetHelper)
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC, inMakeKingEquipDC, newEquipDC, homeMyTargetDC ->
            val getId = (msg as GetKingEquip).getId
            val rt = getKingEquip(session, getId, homePlayerDC, inMakeKingEquipDC, newEquipDC, homeMyTargetDC)
            session.sendMsg(MsgType.GetKingEquip_1222, rt)
        }
    }

    private fun getKingEquip(
        session: PlayerActor,
        getId: Long,
        homePlayerDC: HomePlayerDC,
        inMakeKingEquipDC: InMakeKingEquipDC,
        newEquipDC: NewEquipDC,
        homeMyTargetDC: HomeMyTargetDC
    ): (GetKingEquipRt) {
        val rt = GetKingEquipRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.equipProtoId = 0
        rt.equipId = 0

        val player = homePlayerDC.player

        val inMakeVo = inMakeKingEquipDC.findInMakeByPlayerIdAndId(getId)
        if (inMakeVo == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        val nowTime = getNowTime()

        if (inMakeVo.overTime > nowTime) {
            rt.rt = ResultCode.KING_EQUIP_TIME_NO_OVER_ERROR.code
            return rt.build()
        }

        val makeProto = pcs.lordEquipmentCache.lordEquipmentMap[inMakeVo.kingEquipId]
        if (makeProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        val kingEquipId = makeProto.propsID

        val equipProto = pcs.equipCache.equipProtoMap[kingEquipId]
        if (equipProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        if (inMakeVo.costEquipId == 0L) {
            // 锻造
            val reward = LinkedList<ResVo>()
            reward += ResVo(RES_PROPS, kingEquipId.toLong(), 1)

            val action = ACTION_GET_KING_EQUIP

            resHelper.addRes(session, action, player, reward)

            //添加统计
            targetHelper.targetAddVal(
                session,
                MakeEquip,
                LinkedList(asList(equipProto.quality, 1))
            )

            homeMyTargetDC.targetInfo.getKingEquipNum++

        } else {
            // 进阶
            val itemVo = newEquipDC.findPropById(inMakeVo.costEquipId)
            if (itemVo == null) {
                rt.rt = ResultCode.NO_EQUIP_ERROR.code
                return rt.build()
            }
            itemVo.equipProtoId = kingEquipId
        }

        fireEvent(session, GetKingEquipEvent(kingEquipId))

        inMakeKingEquipDC.deleteInMake(inMakeVo)

        val notice = createMakeKingEquipChangeNotifier(DEL_IN_MAKE_DUILIE, inMakeVo)
        notice.notice(session)

        rt.equipProtoId = kingEquipId
        rt.equipId = inMakeVo.costEquipId

        return rt.build()
    }
}
