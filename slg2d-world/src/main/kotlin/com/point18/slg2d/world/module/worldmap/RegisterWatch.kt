package com.point18.slg2d.world.module.worldmap

import akka.actor.ActorRef
import com.point18.slg2d.world.area4data.AreaCache
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.msgtrans.ScMessageAtSend
import com.point18.slg2d.common.commonfunc.tellNoSender
import com.point18.slg2d.world.module.ReqDealWithConn
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.commonfunc.AreaConfig
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.commonfunc.processConfig
import pb4client.ChangeWorldWatch
import pb4client.ChangeWorldWatchRt
import pb4client.ServerInfo
import pb4server.ChangeWatchAskReq
import pb4server.World2WorldAskResp
import com.point18.slg2d.common.resultcode.ResultCode

class RegisterWatchDeal : ReqDealWithConn() {
    override fun dealConnReq(cache: AreaCache, channelActor: ActorRef, msg: MessageLite, playerId: Long) {
        msg as ChangeWorldWatch
        registerWatch(cache, channelActor, msg.worldId, msg.areaId)
    }

    private fun registerWatch(areaCache: AreaCache, channelActor: ActorRef, newWorldId: Long, newAreaId: Int) {
        val rtBuilder = ChangeWorldWatchRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val areaConfig = getAreaConfig(newWorldId, newAreaId)
        if (areaConfig == null) {
            rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
            returnMsg(areaCache, channelActor, rtBuilder)
            return
        }

        val serverInfo = ServerInfo.newBuilder()
        serverInfo.serverId = areaConfig.pltAreaNo
        serverInfo.serverName = areaConfig.areaName
        serverInfo.areaNo = areaConfig.areaNo
        rtBuilder.setServerInfo(serverInfo)

        if (areaCache.areaConfig.pltAreaNo == areaConfig.pltAreaNo) {
            returnMsg(areaCache, channelActor, rtBuilder)
            return
        }

        val changeWatchAskReq = ChangeWatchAskReq.newBuilder()
        areaCache.worldActor.createACS<World2WorldAskResp>()
            .ask(
                areaCache.worldActor.worldShardRegion,
                areaCache.fillW2WAskMsgHeader(
                    areaConfig.pltAreaNo, 0
                ) {
                    it.setChangeWatchAskReq(changeWatchAskReq)
                },
                World2WorldAskResp::class.java
            ).whenCompleteKt { askResp, err ->
                try {
                    when {
                        err != null -> {
                            normalLog.error("ChangeWatchAskReq:$err")
                            rtBuilder.rt = ResultCode.ASK_ERROR1.code
                            returnMsg(areaCache, channelActor, rtBuilder)
                            return@whenCompleteKt
                        }
                        askResp == null -> {
                            rtBuilder.rt = ResultCode.ASK_ERROR2.code
                            returnMsg(areaCache, channelActor, rtBuilder)
                            return@whenCompleteKt
                        }
                        else -> {
                            val rt = askResp.changeWatchAskResp

                            //清理本服视野
                            areaCache.mapCellWatcherCache.removeFromMapCellWatch(channelActor)

                            rtBuilder.rt = rt.rt
                            returnMsg(areaCache, channelActor, rtBuilder)
                        }
                    }

                } catch (e: Exception) {
                    normalLog.error("ChangeWatchAskReq Error!", e)
                    rtBuilder.rt = ResultCode.ASK_ERROR3.code
                    returnMsg(areaCache, channelActor, rtBuilder)
                }
            }
    }

    private fun returnMsg(
        areaCache: AreaCache,
        channelActor: ActorRef,
        rtBuilder: ChangeWorldWatchRt.Builder
    ) {
        val scMsg =
            ScMessageAtSend(MsgType.ChangeWorldWatch_108, areaCache.currentClientMsgNo, rtBuilder.build())
        channelActor.tellNoSender(scMsg)
    }

    private fun getAreaConfig(worldId: Long, areaId: Int): AreaConfig? {
        if (worldId != 0L) {
            return processConfig.findSpecAreaConfig(worldId)
        }
        return processConfig.findSpecAreaConfig(areaId)
    }
}

