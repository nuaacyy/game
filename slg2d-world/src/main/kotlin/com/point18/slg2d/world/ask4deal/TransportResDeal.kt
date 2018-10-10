package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.constg.MAX_MAP_SIZE
import com.point18.slg2d.common.constg.ResearchEffectWalkQueueAdd
import com.point18.slg2d.common.constg.WalkTransport
import com.point18.slg2d.common.constg.Transfer
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.Home2WorldAskDealBase
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.module.walk.dealWalk
import com.point18.slg2d.world.module.walk.walkM
import pb4server.Home2WorldAskReq
import pb4server.Home2WorldAskResp
import pb4server.TransportResAskRt
import java.util.*

data class TransportResRt(
        var rt: Int,
        var groupId: Long = 0
)

class TransportResDeal : Home2WorldAskDealBase() {
    override fun dealHomeAsk(areaCache: AreaCache, req: Home2WorldAskReq, resp: Home2WorldAskResp.Builder) {
        val askMsg = req.transportResAskReq
        val playerId = req.playerId
        val targetPlayerId = askMsg.targetPlayerId
        val res = askMsg.res
        val effectMap = askMsg.effectMapList

        val rt = TransportResAskRt.newBuilder()
        val resList = toObj<LinkedList<ResVo>>(res)
        val tmpMap = hashMapOf<Int, Int>()
        for (each in effectMap) {
            tmpMap[each.effectId] = each.effectValue
        }

        val rst = transportRes(areaCache, playerId, targetPlayerId, resList, tmpMap)
        rt.rt = rst.rt
        rt.groupId = rst.groupId
        resp.setTransportResAskRt(rt)
        return
    }

    private fun transportRes(
            areaCache: AreaCache,
            playerId: Long,
            targetPlayerId: Long,
            res: LinkedList<ResVo>,
            effectMap: HashMap<Int, Int>
    ): TransportResRt {
        val player = areaCache.playerCache.findPlayerById(playerId)
        if (player == null) {
            normalLog.error("找不到玩家信息:%d", playerId)
            return TransportResRt(ResultCode.PARAMETER_ERROR.code)
        }
        val castle = areaCache.castleCache.findCastleById(player.focusCastleId)
        if (castle == null) {
            normalLog.error("找不到玩家城数据:%d", player.focusCastleId)
            return TransportResRt(ResultCode.PARAMETER_ERROR.code)
        }
        val startX = castle.x
        val startY = castle.y
        // 根据不同的出征类型验证各自的特殊需求
        val newWalkDeal = walkM.walkDeals[WalkTransport]
        if (newWalkDeal == null) {
            return TransportResRt(ResultCode.PARAMETER_ERROR.code)
        }
        val rst = newWalkDeal.getPosByTarget(areaCache, targetPlayerId, 0, 0)
        if (rst.result != ResultCode.SUCCESS.code) {
            return TransportResRt(rst.result)
        }
        val gotoX = rst.posX
        val gotoY = rst.posY

        // 读取表
        if (gotoX >= MAX_MAP_SIZE || gotoY >= MAX_MAP_SIZE) {
            return TransportResRt(ResultCode.MAP_PROTO_NOT_EXIST.code)
        }

        // 出征队列上限校验
        val forces = areaCache.walkForceCache.findWalkForceByPlayerId(playerId)
        if (forces.count() >= effectMap[ResearchEffectWalkQueueAdd] ?: 0) {
            return TransportResRt(ResultCode.WalkQueueUpLimit.code)
        }

        val dealRt = dealWalk(
                areaCache,
                playerId,
                WalkTransport,
                startX,
                startY,
                targetPlayerId,
                gotoX,
                gotoY,
                LinkedList(),
                hashMapOf(),
                Transfer.toString(),
                res
        )
        return TransportResRt(dealRt.rt, dealRt.groupId)
    }
}

