package com.point18.slg2d.world.module.playerActivity

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.ALLIANCE_ACTIVITY
import pb4client.PlayerActivityRankVo
import pb4client.SelectActivityRank
import pb4client.SelectActivityRankRt
import com.point18.slg2d.common.resultcode.ResultCode
import java.util.*

// 查询活动历史记录
class SelectActivityRankDeal : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val activityType = (msg as SelectActivityRank).activityType
        val rankId = msg.rankId
        val (rt, isRpc) = selectActivityRank(session, activityType, rankId)

        if (!isRpc && rt != null) {
            session.sendMsg(MsgType.SelectActivityRank_1335, rt)
        }
    }

    data class SelectActivityRankReturn(var selectActivityHistoryRt: SelectActivityRankRt?, var isRpc: Boolean)

    private fun selectActivityRank(session: PlayerSession, activityType: Int, rankId: Long): (SelectActivityRankReturn) {
        val rt = SelectActivityRankRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val areaCache = session.areaCache

        val player = session.player

        if (activityType == ALLIANCE_ACTIVITY) {
            if (player.allianceId == 0L) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                return SelectActivityRankReturn(rt.build(), false)
            }

            // 查询的是联盟活动,去联盟服拿数据
            com.point18.slg2d.world.common.selectActivityRank(session, player.allianceId, rankId)
            return SelectActivityRankReturn(null, true)
        } else {
            val rankInfo = areaCache.activityRankCache.findActivityRankById(rankId)
            if (rankInfo == null) {
                // 这条记录已经过期了
                rt.rt = ResultCode.ACTIVITY_RANK_TIME_OVER.code
                return SelectActivityRankReturn(rt.build(), false)
            } else {
                if (getNowTime() > rankInfo.overTime) {
                    areaCache.activityRankCache.deleteActivityRankById(rankInfo)
                    rt.rt = ResultCode.ACTIVITY_RANK_TIME_OVER.code
                    return SelectActivityRankReturn(rt.build(), false)
                }
            }

            val pars = LinkedList<PlayerActivityRankVo>()

            for ((_, rInfo) in rankInfo.rankInfoMap) {
                val par = pb4client.PlayerActivityRankVo.newBuilder()
                par.playerId = rInfo.playerId
                par.playerName = rInfo.playerName
                par.nickName = rInfo.playerNickName
                par.allianceShortName = rInfo.allianceShortName
                par.score = rInfo.score
                par.photoId = rInfo.photoId
                pars.add(par.build())
            }


            rt.addAllPActivityRankVos(pars)

            return SelectActivityRankReturn(rt.build(), false)
        }

    }
}
