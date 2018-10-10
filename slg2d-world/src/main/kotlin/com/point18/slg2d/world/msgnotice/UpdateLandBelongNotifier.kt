package com.point18.slg2d.world.msgnotice

import akka.actor.ActorRef
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.common.msgtrans.ScMessageAtSend
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.CellPoint
import pb4client.NewEveryLandInfo
import pb4client.UpdateLandBelong

// 格子变化推送
class UpdateLandBelongNotifier(
    addOrUpdateLandList: List<NewEveryLandInfo.Builder>?,
    delList: List<CellPoint.Builder>?
) {
    private val msg: UpdateLandBelong.Builder = UpdateLandBelong.newBuilder()

    init {
        addOrUpdateLandList?.forEach { msg.addLands(it) }
        delList?.forEach { msg.addDels(it) }
    }

    fun addUpdateLand(builder: NewEveryLandInfo.Builder?) {
        builder?.let {
            msg.addLands(it)
        }
    }

    fun addDelLand(builder: CellPoint.Builder?) {
        builder?.let {
            msg.addDels(it)
        }
    }

    fun notice(session: PlayerSession) {
        session.sendMsg(MsgType.UpdateLandBelong_3014, msg.build())
    }

    fun notice(areaCache: AreaCache, channelActor: ActorRef) {
        val scMsg = ScMessageAtSend(MsgType.UpdateLandBelong_3014, areaCache.currentClientMsgNo, msg.build())
        channelActor.tell(scMsg, ActorRef.noSender())
    }
}


