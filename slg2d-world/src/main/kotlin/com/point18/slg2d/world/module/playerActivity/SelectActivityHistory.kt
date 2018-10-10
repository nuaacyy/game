package com.point18.slg2d.world.module.playerActivity

import com.point18.slg2d.world.area4data.*
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.ALLIANCE_ACTIVITY
import pb4client.ActivityHistoryVo
import pb4client.SelectActivityHistory
import pb4client.SelectActivityHistoryRt
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import java.util.*

// 查询活动历史记录
class SelectActivityHistoryDeal : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val activityType = (msg as SelectActivityHistory).activityType
        val (rt, isRpc) = selectActivityHistory(session, activityType)

        if (!isRpc && rt != null) {
            session.sendMsg(MsgType.SelectActivityHistory_1332, rt)
        }
    }

    data class SelectActivityHistoryReturn(var selectActivityHistoryRt: SelectActivityHistoryRt?, var isRpc: Boolean)

    private fun selectActivityHistory(
        session: PlayerSession,
        activityType: Int
    ): (SelectActivityHistoryReturn) {
        val rt = SelectActivityHistoryRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val areaCache = session.areaCache

        val player = session.player

        if (activityType == ALLIANCE_ACTIVITY) {
            if (player.allianceId == 0L) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                return SelectActivityHistoryReturn(rt.build(), false)
            }

            // 查询的是联盟活动,去联盟服拿数据
            com.point18.slg2d.world.common.selectActivityHistory(session, player.allianceId)
            return SelectActivityHistoryReturn(null, true)
        } else {
            val joinActivityList = LinkedList<ActivityHistoryVo>()
            val delInfo = LinkedList<JoinActivity>()
            for (joinActivity in player.joinActivityList) {
                // 验证是否记录已经过期了

                val eventInProto = pcs.eventInformationProtoCache.protoMapById[joinActivity.activityProtoId]
                if (eventInProto == null) {
                    rt.rt = ResultCode.NO_PROTO.code
                    return SelectActivityHistoryReturn(rt.build(), false)
                }

                val eventTimeProto = pcs.eventTimeProtoCache.protoMap[eventInProto.aid]
                if (eventTimeProto == null) {
                    rt.rt = ResultCode.NO_PROTO.code
                    return SelectActivityHistoryReturn(rt.build(), false)
                }

                if (eventTimeProto.eventType != activityType) {
                    continue
                }

                val rankInfo = areaCache.activityRankCache.findActivityRankById(joinActivity.rankId)
                if (rankInfo == null) {
                    // 这条记录已经过期了
                    delInfo.add(joinActivity)
                    continue
                } else {
                    if (getNowTime() > rankInfo.overTime) {
                        delInfo.add(joinActivity)
                        areaCache.activityRankCache.deleteActivityRankById(rankInfo)
                        continue
                    }
                }

                val ahVo = pb4client.ActivityHistoryVo.newBuilder()
                ahVo.activityProtoId = joinActivity.activityProtoId
                ahVo.overTime = (joinActivity.overTime / 1000).toInt()
                ahVo.myScore = joinActivity.myScore
                ahVo.myRank = joinActivity.myRank
                ahVo.rankId = joinActivity.rankId
                joinActivityList.add(ahVo.build())
            }

            rt.addAllActivityHistoryVos(joinActivityList)

            for (del in delInfo) {
                player.joinActivityList.remove(del)
            }

            return SelectActivityHistoryReturn(rt.build(), false)
        }

    }
}
