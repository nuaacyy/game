package com.point18.slg2d.home.module.kingEquip

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.ACTION_CANCEL_MAKE_KING_EQUIP
import com.point18.slg2d.common.constg.DEL_IN_MAKE_DUILIE
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.InMakeKingEquipDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.msgnotice.createMakeKingEquipChangeNotifier
import pb4client.CancelMakeKingEquip
import pb4client.CancelMakeKingEquipRt
import java.util.*

class CancelMakeKingEquipDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal,
    HomeHelperPlus2<HomePlayerDC, InMakeKingEquipDC>(
        HomePlayerDC::class.java,
        InMakeKingEquipDC::class.java,
        Arrays.asList(resHelper)
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC, inMakeKingEquipDC ->
            val makeId = (msg as CancelMakeKingEquip).makeId
            val rt = cancelMakeKingEquip(session, makeId, homePlayerDC, inMakeKingEquipDC)
            session.sendMsg(MsgType.CancelMakeKingEquip_1231, rt)
        }
    }

    fun cancelMakeKingEquip(
        session: PlayerActor,
        makeId: Long,
        homePlayerDC: HomePlayerDC,
        inMakeKingEquipDC: InMakeKingEquipDC
    ): (CancelMakeKingEquipRt) {
        val rt = CancelMakeKingEquipRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.makeId = makeId

        val player = homePlayerDC.player

        val nowTime = getNowTime()

        val inMake = inMakeKingEquipDC.findInMakeByPlayerIdAndId(makeId)
        if (inMake == null || inMake.overTime < nowTime) {
            rt.rt = ResultCode.KING_EQUIP_NO_IN_MAKE_ERROR.code
            return rt.build()
        }

        val makeProto = pcs.lordEquipmentCache.lordEquipmentMap[inMake.kingEquipId]
        if (makeProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        // 资源的返还
        val rewards = LinkedList<ResVo>()
        rewards += makeProto.forgeMoneyMap
        rewards += makeProto.forgeCostMap

        resHelper.addRes(session, ACTION_CANCEL_MAKE_KING_EQUIP, player, rewards)

        // 数据删除
        inMakeKingEquipDC.deleteInMake(inMake)

        // 推送
        val notice = createMakeKingEquipChangeNotifier(DEL_IN_MAKE_DUILIE, inMake)
        notice.notice(session)

        return rt.build()
    }

}