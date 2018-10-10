package com.point18.slg2d.world.gmsg

import com.point18.slg2d.common.constg.NO_HANDLER
import com.point18.slg2d.world.Home2WorldTellDealBase
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.event.NoHandle
import com.point18.slg2d.world.module.task.checkIsFinish
import com.point18.slg2d.world.msgnotice.createTaskOperationNotifier
import pb4server.Home2WorldTell
import pb4server.TaskFinishOnWorldTell

class CreateTaskToWorldDeal : Home2WorldTellDealBase() {
    override fun dealHomeTell(areaCache: AreaCache, worldId: Long, playerId: Long, msg: Home2WorldTell) {
        val tellMsg = msg.createTaskToWorldTell
        val player = areaCache.playerCache.findPlayerById(msg.playerId)
        if (player == null) {
            return
        }

        // 初始化任务
        if (areaCache.taskCache.findTaskByProtoId(player.id, tellMsg.taskProtoId) != null) {
            return
        }
        val t = areaCache.taskCache.createTask(tellMsg.taskProtoId, 0, 0, player.id)
        if (t != null) {
            val (isOk, _) = checkIsFinish(areaCache, NO_HANDLER, NoHandle(), t, player.id)

            if (isOk) {
                // 任务完成,删除世界服的数据,转移保存到玩家服
                areaCache.taskCache.deleteTask(t)
                val tell = TaskFinishOnWorldTell.newBuilder()
                tell.taskProtoId = t.taskProtoId
                val tell2HomeMsg = areaCache.fillW2HTellMsgHeader(player.id) {
                    it.setTaskFinishOnWorldTell(tell)
                }
                areaCache.tellHome(tell2HomeMsg)

            } else {
                // 给客户端推送任务变化
                val session = fetchOnlinePlayerSession(areaCache, player.id)
                if (session != null) {
                    createTaskOperationNotifier(
                        1, t.id, t.taskProtoId, t.taskNowState,
                        t.taskFinish, (t.overTime / 1000).toInt()
                    ).notice(session)
                }
            }
        }
    }
}

