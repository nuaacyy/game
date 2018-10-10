package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.Home2WorldAskDealBase
import com.point18.slg2d.common.constg.Update
import com.point18.slg2d.common.constg.WarnWalkGroup
import com.point18.slg2d.world.common.getWarnData
import com.point18.slg2d.world.msgnotice.createWalkGroupChangeNotifier
import pb4server.Home2WorldAskReq
import pb4server.Home2WorldAskResp
import pb4server.RefreshWarnAskRt
import com.point18.slg2d.common.resultcode.ResultCode

class RefreshWarnDeal : Home2WorldAskDealBase() {
    override fun dealHomeAsk(areaCache: AreaCache, req: Home2WorldAskReq, resp: Home2WorldAskResp.Builder) {
        val playerId = req.playerId

        val rt = RefreshWarnAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = areaCache.playerCache.findPlayerById(playerId)
        if (player == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
        } else {
            val session = fetchOnlinePlayerSession(areaCache, playerId)
            if (session != null) {
                val warnGroups = getWarnData(areaCache, player)
                for (warnGroup in warnGroups) {
                    createWalkGroupChangeNotifier(Update, WarnWalkGroup, warnGroup).notice(session)
                }
            }
        }
        resp.setRefreshWarnAskRt(rt)
    }
}