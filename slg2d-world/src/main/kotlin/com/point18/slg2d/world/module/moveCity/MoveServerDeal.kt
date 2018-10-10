package com.point18.slg2d.world.module.moveCity

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.processConfig
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.common.MoveServerHelper
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.module.walk.dealWalkFinish
import pb4client.MoveServer
import pb4client.MoveServerRt

// 迁服
class MoveServerDeal : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val targetWorldId = (msg as MoveServer).worldId
        val x = msg.x
        val y = msg.y
        val rt = startMoveServer(session, targetWorldId, x, y)

        session.sendMsg(MsgType.MoveServer_1300, rt)
    }

    fun startMoveServer(session: PlayerSession, targetWorldId: Long, x: Int, y: Int): MoveServerRt {

        val rt = MoveServerRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = session.player

        // todo 进行一些基础验证
        if (player.inMoveServer == IN_MOVE_SERVER) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        if (player.worldId == targetWorldId) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        val (have, _, _) = session.areaCache.buffCache.findBuffValueByBuffType(
            session.player.id,
            BUFF_EFFECT_WALK_EFFECT
        )
        if (have) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        val targetAreaConfig = processConfig.findSpecAreaConfig(targetWorldId)
        if (targetAreaConfig == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        val myAreaConfig = processConfig.findSpecAreaConfig(player.worldId)
        if (myAreaConfig == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        if (targetAreaConfig.areaPublishTime + pcs.basicProtoCache.serverOpenTime > getNowTime() && myAreaConfig.areaPublishTime > targetAreaConfig.areaPublishTime) {
            // 如果需要迁服的目标服未出保,并且我所在服比目标服老,不允许
            rt.rt = ResultCode.MOVE_NEW_AREA_ERROR.code
            return rt.build()
        }

        //校验自身的行军部队，除了领主释放必须全部是静止状态
        var kingBackWalkLine: Walk? = null
        val myForces = session.areaCache.walkForceCache.findWalkForceByPlayerId(player.id)
        for (force in myForces) {
            val group = session.areaCache.walkForceGroupCache.findWalkForceGroupById(force.forceGroupId)
            if (group == null) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                return rt.build()
            }

            if (group.runningState == Running && group.gotoRunType != WalkMainHeroGoHome) {
                rt.rt = ResultCode.FORCE_RUNNING_OUTSIDE.code
                return rt.build()
            }

            if (group.runningState == MassWaiting) {
                rt.rt = ResultCode.FORCE_RUNNING_OUTSIDE.code
                return rt.build()
            }

            //判断部队是否静止，过滤掉领主回家的情况
            if (group.gotoRunType == WalkMainHeroGoHome) {
                //瞬移回家
                val walkLine = session.areaCache.walkCache.findWalkByGroupId(force.forceGroupId)
                if (walkLine == null) {
                    continue
                }

                kingBackWalkLine = walkLine
            }
        }

        if (kingBackWalkLine != null) {
            dealWalkFinish(session.areaCache, kingBackWalkLine)
        }


        val ms = MoveServerHelper()
        ms.moveServer(session.areaCache, player, targetWorldId, x, y)

        return rt.build()
    }
}
