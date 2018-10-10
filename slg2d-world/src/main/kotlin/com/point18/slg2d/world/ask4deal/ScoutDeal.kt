package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.MAX_MAP_SIZE
import com.point18.slg2d.common.constg.WalkScout
import com.point18.slg2d.common.constg.ResearchEffectWalkQueueAdd
import com.point18.slg2d.common.constg.WalkScout
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.Home2WorldAskDealBase
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.module.walk.dealWalk
import com.point18.slg2d.world.module.walk.walkM
import pb4server.Home2WorldAskReq
import pb4server.Home2WorldAskResp
import pb4server.Walk4ScoutAskRt
import java.util.*

data class DoScoutRt(
    var rt: Int,
    var groupId: Long = 0
)

class ScoutDeal : Home2WorldAskDealBase() {
    override fun dealHomeAsk(areaCache: AreaCache, req: Home2WorldAskReq, resp: Home2WorldAskResp.Builder) {
        val askMsg = req.walk4ScoutAskReq
        val playerId = req.playerId
        val effectList = askMsg.effectMapList

        val effectMap = hashMapOf<Int, Int>()
        effectList.forEach {
            effectMap[it.effectId] = it.effectValue
        }

        val rst = doScout(areaCache, playerId, askMsg.aimX, askMsg.aimY, effectMap)

        val rtBuilder = Walk4ScoutAskRt.newBuilder()
        rtBuilder.rt = rst.rt
        rtBuilder.groupId = rst.groupId

        resp.setWalk4ScoutAskRt(rtBuilder)
    }

    private fun doScout(
        areaCache: AreaCache,
        playerId: Long,
        aimX: Int,
        aimY: Int,
        effectMap: HashMap<Int, Int>
    ): DoScoutRt {
        val player = areaCache.playerCache.findPlayerById(playerId)
        if (player == null) {
            normalLog.error("找不到玩家信息:%d", playerId)
            return DoScoutRt(ResultCode.PARAMETER_ERROR.code)
        }
        val castle = areaCache.castleCache.findCastleById(player.focusCastleId)
        if (castle == null) {
            normalLog.error("找不到玩家城数据:%d", player.focusCastleId)
            return DoScoutRt(ResultCode.PARAMETER_ERROR.code)
        }
        val startX = castle.x
        val startY = castle.y
        // 根据不同的出征类型验证各自的特殊需求
        val newWalkDeal = walkM.walkDeals[WalkScout]
        if (newWalkDeal == null) {
            return DoScoutRt(ResultCode.PARAMETER_ERROR.code)
        }

        // 读取表
        if (aimX >= MAX_MAP_SIZE || aimY >= MAX_MAP_SIZE) {
            return DoScoutRt(ResultCode.MAP_PROTO_NOT_EXIST.code)
        }

        // 出征队列上限校验
        val forces = areaCache.walkForceCache.findWalkForceByPlayerId(playerId)
        if (forces.count() >= effectMap[ResearchEffectWalkQueueAdd] ?: 0) {
            return DoScoutRt(ResultCode.WalkQueueUpLimit.code)
        }

        val dealRt = dealWalk(
            areaCache,
            playerId,
            WalkScout,
            startX,
            startY,
            0,
            aimX,
            aimY,
            LinkedList(),
            hashMapOf()
        )
        return DoScoutRt(dealRt.rt, dealRt.groupId)
    }
}