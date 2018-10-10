//可以申请的联盟列表
package com.point18.slg2d.world.module.alliance

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.module.uiCondition.uiConditionCheck
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.constg.ALLIANCE_OPEN
import pb4client.AllianceQueryList
import pb4client.AllianceQueryListRt
import com.point18.slg2d.common.resultcode.ResultCode

//查询可加入联盟列表 808
class DealQueryAllianceList : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val allianceName = (msg as AllianceQueryList).allianceName
        val allianceLan = msg.allianceLan

        val rt = this.queryList(session, allianceName, allianceLan)
        if (rt != null) {
            session.sendMsg(MsgType.AllianceQueryList_808, rt)
        }
    }

    fun queryList(session: PlayerSession, allianceName: String, allianceLan: Int): (AllianceQueryListRt?) {
        val rt = AllianceQueryListRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = session.player

        // 功能开启
        val uiConditionRt = uiConditionCheck(session.areaCache, player, ALLIANCE_OPEN)
        if (uiConditionRt != ResultCode.SUCCESS.code) {
            rt.rt = uiConditionRt
            return rt.build()
        }


        if (allianceName == "" && allianceLan == 0) {
            rt.rt = (ResultCode.PARAMETER_ERROR.code)
            return rt.build()
        }

        com.point18.slg2d.world.common.queryAllianceList(session, player.areaNo, allianceName, allianceLan)

        return null
    }

}

