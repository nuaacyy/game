package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.common.constg.PowerRank
import com.point18.slg2d.world.area4data.*
import com.point18.slg2d.world.World2WorldAskDealBase
import com.point18.slg2d.world.common.checkFreeCell
import pb4server.*
import com.point18.slg2d.common.resultcode.ResultCode

// 检测地块是否空闲,如果XY都是0 表示就是来拿玩家战斗力名次的
class CheckMoveServerCellLockMsgDeal : World2WorldAskDealBase() {
    override fun dealWorldAsk(areaCache: AreaCache, req: World2WorldAskReq, resp: World2WorldAskResp.Builder) {
        val checkMoveServerCellLockReq = req.checkMoveServerXyReq
        val rt = CheckMoveServerXyRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        val x = checkMoveServerCellLockReq.x
        val y = checkMoveServerCellLockReq.y

        var isOnlyRank = false
        if (x == 0 && y == 0) {
            isOnlyRank = true
        }
        val power = checkMoveServerCellLockReq.power

        // 根据战斗力算出在本区的战斗力排名
        val ranks = areaCache.rankCache.findRankInfo(PowerRank)
        if (ranks == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return
        }

        var rank = 1
        for (v in ranks.queryValue(100)) {
            if (v.getTotalPower() > power) {
                rank += 1
            } else {
                break
            }
        }

        rt.rank = rank

        if (!isOnlyRank) {
            // 检测地块有效性
            val freeCell = checkFreeCell(areaCache, x, y, 0)
            if (!freeCell) {
                rt.rt = ResultCode.MOVE_CITY_POS_ERROR.code
                return
            }

            // 检测坐标锁定情况
            val moveServerCellLockCache = areaCache.moveServerCellLockCache
            val p = moveServerCellLockCache.findMoveServerCellLockByPlayerId(req.playerId)
            if (p != null) {
                rt.rt = ResultCode.MOVE_SERVER_PLAYER_EXIST_ERROR.code
                return
            }

            val vo = moveServerCellLockCache.findMoveServerCellLockByXy(x, y)
            if (vo != null) {
                rt.rt = ResultCode.MOVE_SERVER_XY_EXIST_ERROR.code
                return
            }


            // 锁定坐标
            moveServerCellLockCache.createMoveServerCellLock(req.playerId, x, y)
        }

        resp.setCheckMoveServerXyRt(rt)
        return
    }
}