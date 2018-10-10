package com.point18.slg2d.robot.rai

import com.point18.slg2d.robot.robot.AiSequence
import com.point18.slg2d.robot.robot.behnull.AiAlwaysSuccess
import com.point18.slg2d.robot.robot.chat.AiChat
import com.point18.slg2d.robot.robot.realm.AiOffline
import com.point18.slg2d.robot.robotData.RobotAction

fun createAiChatTest(): RobotAction {
    // AI
    val robotBeh = AiSequence(
        prepareAiEnterGame(), // 打包好的进入游戏流程

        AiChat(),

        AiAlwaysSuccess(),
        AiAlwaysSuccess(),
        AiAlwaysSuccess(),
        AiAlwaysSuccess(),
        AiAlwaysSuccess(),
        AiAlwaysSuccess(),
        AiAlwaysSuccess(),

        AiOffline()        // 做完之后下线
    )
    return robotBeh
}