package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchEpNo
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.Home2WorldAskDealBase
import com.point18.slg2d.world.common.isWonderWar
import com.point18.slg2d.world.msgnotice.createBarracksChangeNotifier
import pb4server.BarrackSpeedAskReq
import pb4server.BarrackSpeedAskRt
import pb4server.Home2WorldAskReq
import pb4server.Home2WorldAskResp
import com.point18.slg2d.common.resultcode.ResultCode

class BarrackSpeedDeal : Home2WorldAskDealBase() {
    override fun dealHomeAsk(areaCache: AreaCache, req: Home2WorldAskReq, resp: Home2WorldAskResp.Builder) {
        val askMsg = req.barrackSpeedAskReq
        val rt = dealBarrackSpeed(areaCache, req.playerId, askMsg)
        resp.setBarrackSpeedAskRt(rt)
    }

    private fun dealBarrackSpeed(
        areaCache: AreaCache,
        playerId: Long,
        askMsg: BarrackSpeedAskReq
    ): BarrackSpeedAskRt.Builder {
        val rt = BarrackSpeedAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val barrackMap = areaCache.barracksCache.findBarracksMapByPlayerId(playerId)
        val barrack = barrackMap[askMsg.soliderId]
        if (barrack == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt
        }

        val now = getNowTime()
        val epNo = fetchEpNo(areaCache, barrack.playerId)
        val session = fetchOnlinePlayerSession(areaCache, barrack.playerId)
        var isCure = false
        when (askMsg.speedType) {
            BarrackMakeSpeed -> {
                if (barrack.overTime < now) {
                    rt.rt = ResultCode.PARAMETER_ERROR.code
                    return rt
                }
                var newOverTime = barrack.overTime
                if (askMsg.speedTime == 0L) {
                    newOverTime = now
                } else {
                    newOverTime -= askMsg.speedTime
                }
                areaCache.barracksCache.makeSoliderUpdate(barrack, newOverTime)
            }
            BarrackCureSpeed -> {
                val barracksVos = areaCache.barracksCache.findCureSoliderListByCureQueue(playerId, NormalCure, 1)
                for (barracksVo in barracksVos) {
                    if (barracksVo.cureOverTime < now) {
                        rt.rt = ResultCode.PARAMETER_ERROR.code
                        return rt
                    }
                    var newOverTime = barracksVo.cureOverTime
                    if (askMsg.speedTime == 0L) {
                        newOverTime = now
                    } else {
                        newOverTime -= askMsg.speedTime
                    }
                    areaCache.barracksCache.cureSoliderUpdate(barracksVo, newOverTime)

                    if (session != null) {
                        createBarracksChangeNotifier(barracksVo).notice(session)
                    }

                    isCure = true
                }
            }
            BarrackEventCureSpeed -> {
                if (!isWonderWar(areaCache)) { // 非奇迹争夺战没有活动伤兵营
                    rt.rt = ResultCode.PARAMETER_ERROR.code
                    return rt
                }
                val barracksVos = areaCache.barracksCache.findCureSoliderListByCureQueue(playerId, EventCure, 1)
                for (barracksVo in barracksVos) {
                    if (barracksVo.eventCureOverTime < now) {
                        rt.rt = ResultCode.PARAMETER_ERROR.code
                        return rt
                    }
                    var newOverTime = barracksVo.eventCureOverTime
                    if (askMsg.speedTime == 0L) {
                        newOverTime = now
                    } else {
                        newOverTime -= askMsg.speedTime
                    }
                    areaCache.barracksCache.eventCureSoliderUpdate(barracksVo, newOverTime)

                    if (session != null) {
                        createBarracksChangeNotifier(barracksVo).notice(session)
                    }

                    isCure = true
                }
            }
            BarrackUpSpeed -> {
                if (barrack.upOverTime < now) {
                    rt.rt = ResultCode.PARAMETER_ERROR.code
                    return rt
                }
                var newOverTime = barrack.upOverTime
                if (askMsg.speedTime == 0L) {
                    newOverTime = now
                } else {
                    newOverTime -= askMsg.speedTime
                }
                areaCache.barracksCache.soliderUpUpdate(barrack, newOverTime)
            }
        }

        if (!isCure) {
            if (session != null) {
                createBarracksChangeNotifier(barrack).notice(session)
            }
        }

        return rt
    }
}