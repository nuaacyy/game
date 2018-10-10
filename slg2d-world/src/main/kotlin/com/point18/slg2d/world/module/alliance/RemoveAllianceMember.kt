package com.point18.slg2d.world.module.alliance

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.A_RIGHT_MEMBER_MANAGER
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.common.removeAllianceMember
import com.point18.slg2d.world.module.ReqDealEntered
import pb4client.AllianceRemovePlayer
import pb4client.AllianceRemovePlayerRt
import java.util.*

//从联盟中剔除玩家 812
class DealRemoveAllianceMember : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rmPid = (msg as AllianceRemovePlayer).removePlayerId

        val rt = this.removePlayer(session, rmPid)
        if (rt != null) {
            session.sendMsg(MsgType.AllianceRemovePlayer_812, rt)
        }
    }

    private fun removePlayer(session: PlayerSession, rmPid: Long): (AllianceRemovePlayerRt?) {
        val rt = AllianceRemovePlayerRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.removePlayerId = rmPid

        val player = session.player

        val oldPos = LinkedList<Int>()
        for ((p, _) in player.alliancePosMap) {
            oldPos.add(p)
        }

        val myMax = player.getMaxAlliancePos()

        if (myMax == 0) {
            rt.rt = (ResultCode.PARAMETER_ERROR.code)
            return rt.build()
        }

        // 验证玩家的职位是否拥有该模块操作权限
        if (!hasRight(player, A_RIGHT_MEMBER_MANAGER)) {
            rt.rt = (ResultCode.ALLIANCE_POSITION_RIGHT_FORBIDDEN.code)
            return rt.build()
        }

        removeAllianceMember(session, player.allianceId, rmPid)

        return null
    }

}


