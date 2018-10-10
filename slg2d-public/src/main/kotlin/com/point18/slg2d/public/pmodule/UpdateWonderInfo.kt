package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.constg.Add
import com.point18.slg2d.common.constg.BIG_WONDER
import com.point18.slg2d.common.constg.Del
import com.point18.slg2d.common.constg.Update
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.public.common.noticeOccupyWonder2Home
import com.point18.slg2d.public.common.noticeOccupyWonder2World
import com.point18.slg2d.public.datacache.PublicCache
import pb4server.UpdateWonderInfoAskRt
import pb4server.World2PublicAskReq
import pb4server.World2PublicAskResp
import java.util.*
import kotlin.collections.HashMap

class UpdateWonderInfoSyncOp : World2PublicAskDealBase() {
    override fun dealWorldAsk(publicCache: PublicCache, req: World2PublicAskReq, resp: World2PublicAskResp.Builder) {

        val allianceId = req.publicId
        val worldId = req.worldId
        val msg = req.updateWonderInfoAskReq
        val changeType = msg.changeType
        val wonderProtoId = msg.wonderProtoId
        val status = msg.status

        val rt = UpdateWonderInfoAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        // todo jh 奇观数据暂时不需要同步
        /*if (msg.addOrDel == constg.Del) {
            val wonder = publicCache.wonderCache.findWonder(msg.pltAreaNo, msg.wonderId)
            if (wonder != null) {
                publicCache.wonderCache.deleteWonder(publicCache, wonder)
            }
            resp.setUpdateWonderInfoAskRt(rt)
            return
        }
        publicCache.wonderCache.createOrUpdateWonder(
            publicCache, msg.pltAreaNo, msg.wonderId, msg.status, msg.warStartTime, msg.warFinishTime,
            msg.belongAllianceId, msg.positionInfo
        )*/

        val alliance = publicCache.allianceCache.findAllianceById(allianceId)
        if (alliance == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            resp.setUpdateWonderInfoAskRt(rt)
            return
        }

        when (changeType) {
            Add -> {
                // 处理占领奇观
                val worldOccupyMap = alliance.allianceOccupyInfo.getOrPut(worldId) { HashMap() }
                worldOccupyMap[wonderProtoId] = 1

                if (wonderProtoId == BIG_WONDER) {
                    syncAllianceInfo2AM(publicCache, alliance, 0, 0, 0)
                }
            }
            Del -> {
                // 处理失去奇观
                val worldOccupyMap = alliance.allianceOccupyInfo[worldId]
                if (worldOccupyMap != null) {
                    worldOccupyMap.remove(wonderProtoId)
                    if (worldOccupyMap.size == 0) {
                        alliance.allianceOccupyInfo.remove(worldId)
                    }
                }

                if (wonderProtoId == BIG_WONDER) {
                    syncAllianceInfo2AM(publicCache, alliance, 0, 0, 0)
                }
            }
            Update -> {}
            else -> {}
        }

        // 通知不同world服联盟成员占领奇观
        val occupyInfo =
            hashMapOf<Long, Map<Int, Int>>(Pair(worldId, hashMapOf(Pair(wonderProtoId, 1))))
        val allMembers =
                publicCache.allianceMemberCache.findAllianceMembersByAllianceId(allianceId)
        allMembers.groupBy { it.mapPltAreaId }.forEach { pltAreaId, members ->
            val memberIds = LinkedList<Long>()
            members.forEach { memberIds.add(it.id) }
            noticeOccupyWonder2World(publicCache.publicActor, pltAreaId, memberIds, changeType, occupyInfo)
        }

        // 通知home服占领奇观
        if (changeType != Update) {
            allMembers.forEach {
                noticeOccupyWonder2Home(publicCache.publicActor, it.id, changeType, occupyInfo)
            }
        }

        resp.setUpdateWonderInfoAskRt(rt)
        return
    }
}