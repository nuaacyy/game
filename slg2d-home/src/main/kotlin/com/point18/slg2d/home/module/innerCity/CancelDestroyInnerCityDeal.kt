package com.point18.slg2d.home.module.innerCity

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.CHANGE_INNER_CITY
import com.point18.slg2d.common.constg.DESTROY
import com.point18.slg2d.common.constg.STABLE
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.InnerCityDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.msgnotice.createInnerCityInfoChangedNotifier
import pb4client.CancelDestroyInnerCity
import pb4client.CancelDestroyInnerCityRt

// 取消升级内城建筑
class CancelDestroyInnerCityDeal : HomeClientMsgDeal, HomeHelperPlus1<InnerCityDC>(InnerCityDC::class.java) {
    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {

        prepare(session) { innerCityDC: InnerCityDC ->
            val reqMsg = msg as CancelDestroyInnerCity
            val cancelDestroyInnerCityRt = cancelDestroyInnerCity(
                innerCityDC, session, reqMsg.cityId, msg.innerCityId
            )
            session.sendMsg(MsgType.CancelDestroyInnerCity_55, cancelDestroyInnerCityRt)
        }
    }

    private fun cancelDestroyInnerCity(
        innerCityDC: InnerCityDC,
        session: PlayerActor,
        castleId: Long,
        innerCityId: Long
    ): CancelDestroyInnerCityRt {
        val rt = CancelDestroyInnerCityRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val playerId = innerCityDC.playerId

        val innerCity = innerCityDC.findInnerCityFromId(innerCityId)
        if (innerCity == null) {
            // 没有该建筑
            rt.rt = ResultCode.INNER_CITY_NOT_FIND_BUILDING.code
            return rt.build()
        }

        if (innerCity.state != DESTROY) {
            // 状态不对
            rt.rt = ResultCode.INNER_CITY_STATE_ERROR.code
            return rt.build()
        }

        innerCityDC.updateInnerCityDestroyState2World(session, innerCity, STABLE, 0, 0)

        createInnerCityInfoChangedNotifier(
            CHANGE_INNER_CITY,
            innerCity.cityType,
            innerCity.id,
            (innerCity.startTime / 1000).toInt(),
            (innerCity.completeTime / 1000).toInt(),
            innerCity.state, innerCity.positionIndex,
            innerCity.lv, innerCity.helpId
        ).notice(session)

        return rt.build()
    }

}
