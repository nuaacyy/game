package com.point18.slg2d.world.module.moveCity

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.area4data.getTotalPower
import com.point18.slg2d.world.module.ReqDealEntered
import pb4client.MoveServerCostRt
import pb4server.CheckMoveServerXyReq
import pb4server.World2WorldAskResp

// 请求我的战斗力在即将迁往的目标服务器上的排名
class MoveServerCost : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val targetWorldId = (msg as pb4client.MoveServerCost).worldId
        val rt = startMoveServer(session, targetWorldId)
        if (rt != null) {
            session.sendMsg(MsgType.MoveServerCost_1303, rt)
        }
    }

    fun startMoveServer(session: PlayerSession, targetWorldId: Long): MoveServerCostRt? {

        val rt = MoveServerCostRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = session.player
        val playerId = player.id
        val areaCache = session.areaCache

        // 前往目标世界服验证数据
        val reqMsg = CheckMoveServerXyReq.newBuilder()
        reqMsg.x = 0
        reqMsg.y = 0

        val myTarget = areaCache.targetCache.findMyTargetByPlayerId(player.id)
        if (myTarget == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        reqMsg.power = myTarget.getTotalPower()
        areaCache.worldActor.createACS<World2WorldAskResp>()
            .ask(
                areaCache.worldActor.worldShardRegion,
                areaCache.fillW2WAskMsgHeader(targetWorldId, playerId) {
                    it.setCheckMoveServerXyReq(reqMsg)
                },
                World2WorldAskResp::class.java
            )
            .whenCompleteKt { wrt, err ->
                try {
                    when {
                        err != null -> {
                            rt.rt = ResultCode.ASK_ERROR1.code
                            session.sendMsg(MsgType.MoveServerCost_1303, rt.build())
                        }
                        wrt == null -> {
                            rt.rt = ResultCode.ASK_ERROR2.code
                            session.sendMsg(MsgType.MoveServerCost_1303, rt.build())
                        }
                        else -> {
                            val r = wrt.checkMoveServerXyRt
                            if (r.rt != ResultCode.SUCCESS.code) {
                                // 锁点失败,返回失败消息
                                rt.rt = r.rt
                                session.sendMsg(MsgType.MoveServerCost_1303, rt.build())
                            } else {
                                rt.rt = r.rt
                                rt.rank = r.rank
                                session.sendMsg(MsgType.MoveServerCost_1303, rt.build())
                            }
                        }
                    }

                } catch (e: Exception) {
                    rt.rt = ResultCode.ASK_ERROR3.code
                    session.sendMsg(MsgType.MoveServerCost_1303, rt.build())
                }
            }

        return null
    }
}
