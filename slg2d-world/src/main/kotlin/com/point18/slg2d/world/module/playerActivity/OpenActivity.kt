package com.point18.slg2d.world.module.playerActivity

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.OpenActivityRt
import pb4client.OpenActivityVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import java.util.*

// 请求单个活动界面
class OpenActivityDeal : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val (rt, isRpc) = openActivity(session)

        if (!isRpc && rt != null) {
            session.sendMsg(MsgType.OpenActivity_1333, rt)
        }
    }

    data class OpenActivityReturn(
        val openActivityRt: OpenActivityRt?,
        val isRpc: Boolean
    )

    fun openActivity(session: PlayerSession): (OpenActivityReturn) {

        val rt = OpenActivityRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val areaCache = session.areaCache

        val player = session.player

        val activityInfoList = LinkedList<OpenActivityVo>()
        val playerActivityList = areaCache.playerActivityCache.findPlayerActivityListByPlayerId(session.playerId)
        for (playerActivity in playerActivityList) {
            val openActivityVo = OpenActivityVo.newBuilder()
            openActivityVo.myRank = 0
            openActivityVo.castleLv = playerActivity.castleLv
            openActivityVo.activityId = playerActivity.activityId
            openActivityVo.score = playerActivity.score
            val eventInProtoMap = pcs.eventInformationProtoCache.protoMap[playerActivity.activityId]
            if (eventInProtoMap == null) {
                rt.rt = ResultCode.UI_CONDITION_ERROR.code
                return OpenActivityReturn(rt.build(), false)
            }

            val eventInProto = eventInProtoMap[playerActivity.castleLv]
            if (eventInProto == null) {
                rt.rt = ResultCode.UI_CONDITION_ERROR.code
                return OpenActivityReturn(rt.build(), false)
            }

            val allJoinActivityPlayersByCastleLv = areaCache.playerActivityCache.findAllPlayerActivityInfoMap(playerActivity.activityId)
            val p = allJoinActivityPlayersByCastleLv[eventInProto.id]
            if (p != null) {
                var myRank = 0
                p.sortByDescending { it.score }
                for (index in p.indices) {
                    if (p[index].playerId == session.playerId) {
                        myRank = index + 1
                        break
                    }
                }

                openActivityVo.myRank = myRank
            }

            activityInfoList.add(openActivityVo.build())
        }



        if (player.allianceId != 0L) {
            // 有联盟,要去一趟拿联盟活动
            com.point18.slg2d.world.common.openActivity(session, player.allianceId, activityInfoList)
            return OpenActivityReturn(null, true)
        } else {
            // 没有联盟,直接返回个人数据给客户端
            rt.addAllOpenActivityVos(activityInfoList)
            return OpenActivityReturn(rt.build(), false)
        }
    }

}