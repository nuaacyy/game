//任命联盟玩家职位/盟主转让/玩家卸任
package com.point18.slg2d.world.module.alliance

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.common.resetAlliancePos
import com.point18.slg2d.world.module.ReqDealEntered
import pb4client.AllianceSetPos
import pb4client.AllianceSetPosRt
import java.util.*

// 任命新写法  倪佳彦 2017.3.31
class DealSetAlliancePos : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val setPid = (msg as AllianceSetPos).setPlayerId
        val pos = LinkedList(msg.positionList)

        val rt = this.setPos(session, setPid, pos)
        if (rt != null) {
            session.sendMsg(MsgType.AllianceSetPos_814, rt)
        }
    }

    private fun setPos(session: PlayerSession, setPid: Long, pos: LinkedList<Int>): (AllianceSetPosRt?) {
        val rt = AllianceSetPosRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.setPlayerId = setPid

        val player = session.player
        if (player.id == setPid) {
            rt.rt = (ResultCode.ALLIANCE_ARGS_ERROR.code)
            return rt.build()
        }

        resetAlliancePos(session, player.allianceId, setPid, pos)

        return null
    }

}


