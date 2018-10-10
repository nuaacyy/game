package com.point18.slg2d.world.module.gmCommand

import com.point18.slg2d.world.area4data.PlayerSession
import pb4server.TaskFinishOnWorldTell

class GmWorldTaskOver : GmCommand {
    override fun exec(session: PlayerSession, message: String): Int {
        val areaCache = session.areaCache
        val playerId = session.playerId

        // 格式1： -gm add 类型 数量
        // 格式2： -gm changeCD building
        val messages = message.split(" ")
        if (messages.size == 1) {
            return 2
        }

        if (messages.size != 3) {
            return 2
        }

        val taskId = (messages[2].toIntOrNull())
        if (taskId == null) {
            return 2
        }

        val task = areaCache.taskCache.findTaskByProtoId(playerId, taskId)
        if (task == null) {
            return 1
        }

        val tell = TaskFinishOnWorldTell.newBuilder()
        tell.taskProtoId = task.taskProtoId
        areaCache.tellHome(areaCache.fillW2HTellMsgHeader(playerId) {
            it.setTaskFinishOnWorldTell(tell)
        })

        areaCache.taskCache.deleteTask(task)

        return 1
    }
}