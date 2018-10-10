package com.point18.slg2d.home.module.gm

import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.event.NoHandleEvent
import com.point18.slg2d.home.module.task.HandleTaskEffectAtEventFire
import java.util.Arrays.asList

class GmGetTask(
    private val handleTaskEffectHelper: HandleTaskEffectAtEventFire = HandleTaskEffectAtEventFire()
) : GmCommand, HomeHelperPlus1<HomePlayerDC>(
    HomePlayerDC::class.java,
    asList(handleTaskEffectHelper)
) {

    override fun exec(session: PlayerActor, message: String) {
        // 格式1： -gm add 类型 数量
        // 格式2： -gm changeCD building
        val messages = message.split(" ")
        if (messages.size == 1) {
            return
        }

        if (messages.size != 3) {
            return
        }

        val questDictVoId = messages[2].toInt()
        handleTaskEffectHelper.initTask(session, questDictVoId, NoHandleEvent())
    }
}