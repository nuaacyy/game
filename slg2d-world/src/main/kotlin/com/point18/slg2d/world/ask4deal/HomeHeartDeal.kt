package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.common.constg.CreateHeart
import com.point18.slg2d.common.constg.DeleteHeart
import com.point18.slg2d.common.constg.UpdateHeart
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.Home2WorldAskDealBase
import pb4server.DealHeartAskRt
import pb4server.Home2WorldAskReq
import pb4server.Home2WorldAskResp
import com.point18.slg2d.common.resultcode.ResultCode

class HomeHeartDeal : Home2WorldAskDealBase() {
    override fun dealHomeAsk(areaCache: AreaCache, req: Home2WorldAskReq, resp: Home2WorldAskResp.Builder) {
        val msg =req.dealHeartAskReq
        val playerId = req.playerId
        val dealType = msg.dealType
        val action = msg.action
        val actionId = msg.actionId
        val triggerTime = msg.triggerTime
        when (dealType) {
            CreateHeart -> {
                areaCache.homeHeartCache.createHomeHeart(playerId, action, actionId, triggerTime)
            }
            DeleteHeart -> {
                areaCache.homeHeartCache.deleteHomeHeart(playerId, actionId)
            }
            UpdateHeart -> {
                areaCache.homeHeartCache.updateHomeHeart(playerId, actionId, triggerTime)
            }
        }
        val rt = DealHeartAskRt.newBuilder()
        //todo 根据处理情况返回不同结果
        rt.rt = ResultCode.SUCCESS.code
        resp.setDealHeartAskRt(rt)
        return
    }
}