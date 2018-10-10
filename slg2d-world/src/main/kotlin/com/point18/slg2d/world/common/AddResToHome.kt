package com.point18.slg2d.world.common

import com.point18.slg2d.world.area4data.AreaCache
import pb4server.AddResToHomeAskReq
import pb4server.World2HomeAskResp
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.resVoToResString
import java.util.*

fun addResToHome(areaCache: AreaCache, actionId: Int, playerId: Long, addRes: List<ResVo>) {
    val reqMsg = AddResToHomeAskReq.newBuilder()
    reqMsg.addRes = resVoToResString(LinkedList(addRes))
    reqMsg.actionId = actionId
    areaCache.worldActor.createACS<World2HomeAskResp>()
        .ask(
            areaCache.worldActor.homeShardRegion,
            areaCache.fillW2HAskMsgHeader(playerId) {
                it.setAddResToHomeAskReq(reqMsg)
            },
            World2HomeAskResp::class.java
        )
        .whenCompleteKt { askResp, err ->
            //todo 增加资源失败
        }
}