package com.point18.slg2d.world.module.jjc

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.common.fetchChallenge
import com.point18.slg2d.world.module.ReqDealEntered
import pb4client.JjcRefreshChallengeRt

// 刷新竞技场挑战对手
class RefreshChallengeDeal : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rt = refreshChallenge(session)
        session.sendMsg(MsgType.JjcRefreshChallenge_712, rt)
    }

    private fun refreshChallenge(session: PlayerSession): (JjcRefreshChallengeRt) {
        val rt = JjcRefreshChallengeRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val areaCache = session.areaCache
        val jjc = session.areaCache.jjcCache.findJjc(session.playerId)
        if (jjc == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        // 获取玩家的当前排名
        val player = session.player

        val (rank1, rank2, rank3) = pcs.jjcChallengeCache.fetchThreeRank(player.jjcRank)

        // 保存3个挑战对手
        jjc.rank1 = rank1
        jjc.rank2 = rank2
        jjc.rank3 = rank3

        // 返回这3个挑战对手信息
        rt.setChallenge1(fetchChallenge(areaCache, rank1))
        rt.setChallenge2(fetchChallenge(areaCache, rank2))
        rt.setChallenge3(fetchChallenge(areaCache, rank3))
        return rt.build()
    }
}