package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.common.constg.Running
import com.point18.slg2d.common.constg.WalkMainHeroGoHome
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.Home2WorldAskDealBase
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchEpNo
import com.point18.slg2d.world.common.findFreeCastlePos
import com.point18.slg2d.world.common.noticeCellRemove
import com.point18.slg2d.world.common.noticeCellUpdate
import pb4server.Home2WorldAskReq
import pb4server.Home2WorldAskResp
import pb4server.RandomPointMoveCityAskRt

class RandomPointMoveCityDeal : Home2WorldAskDealBase() {

    override fun dealHomeAsk(areaCache: AreaCache, req: Home2WorldAskReq, resp: Home2WorldAskResp.Builder) {
        val rt = dealRandomPointMoveCity(areaCache, req.playerId)
        resp.setRandomPointMoveCityAskRt(rt)
    }

    private fun dealRandomPointMoveCity(
        areaCache: AreaCache,
        playerId: Long
    ): RandomPointMoveCityAskRt.Builder {

        val rt = RandomPointMoveCityAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = areaCache.playerCache.findPlayerById(playerId)
        if (player == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        //取随机坐标
        val (x, y) = findFreeCastlePos(areaCache)
        if (x == -1 || y == -1) {
            rt.rt = ResultCode.NO_CAN_MOVE_POS.code
            return rt
        }

        //校验自身的行军部队，除了领主释放必须全部是静止状态
        val myForces = areaCache.walkForceCache.findWalkForceByPlayerId(playerId)
        for (force in myForces) {
            val group = areaCache.walkForceGroupCache.findWalkForceGroupById(force.forceGroupId)
            if (group == null) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                return rt
            }

            //判断部队是否静止，过滤掉领主回家的情况
            if (group.runningState == Running && group.gotoRunType != WalkMainHeroGoHome) {
                rt.rt = ResultCode.FORCE_RUNNING_OUTSIDE.code
                return rt
            }
        }

        val (rrt, oldX, oldY, newX, newY) = moveCity(areaCache, player, x, y)
        if (rrt == ResultCode.SUCCESS) {
            noticeCellUpdate(areaCache, oldX, oldY)

            noticeCellUpdate(areaCache, newX, newY) { notifier ->
                //随机迁城时，额外发一条通知自己的消息
                fetchEpNo(areaCache, playerId)?.let { channel ->
                    notifier.notice(areaCache, channel)
                }
            }
        }

        return rt
    }
}