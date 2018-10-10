//任命联盟玩家职位/盟主转让/玩家卸任
package com.point18.slg2d.world.module.alliance

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.ALLIANCE_POSITION_BOSS
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.AllianceRecallPos
import pb4client.AllianceRecallPosRt
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.common.allianceRecallPos

//罢免新写法  倪佳彦 2017.3.31
class DealAllianceRecallPos : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val setPid = (msg as AllianceRecallPos).setPlayerId
        val pos = msg.position

        val rt = this.recallPos(session, setPid, pos)
        session.sendMsg(MsgType.AllianceRecallPos_835, rt)
    }

    fun recallPos(session: PlayerSession, setPid: Long, pos: Int): AllianceRecallPosRt {
        val rt = AllianceRecallPosRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.setPlayerId = setPid
        val areaCache = session.areaCache
        val player = session.player

        //参数有效性
        if (pos == ALLIANCE_POSITION_BOSS) {
            rt.rt = (ResultCode.ALLIANCE_ARGS_ERROR.code)
            return rt.build()
        }

        val setPlayer = areaCache.playerCache.findPlayerById(setPid)
        if (setPlayer == null) {
            rt.rt = (ResultCode.ALLIANCE_ARGS_ERROR.code)
            return rt.build()
        }

        if (player.allianceId == 0L) {
            rt.rt = (ResultCode.ALLIANCE_PLAYER_NO_JOIN.code)
            return rt.build()
        }

        allianceRecallPos(session, player.allianceId, setPid, pos)

        return rt.build()
    }
}




