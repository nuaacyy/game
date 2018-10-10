package com.point18.slg2d.robot.rai

import com.point18.slg2d.robot.robot.AiSequence
import com.point18.slg2d.robot.robot.realm.AiOffline
import com.point18.slg2d.robot.robotData.RobotAction

fun createAiSimpleEnterGame(): RobotAction {
    // AI
    val robotBeh = AiSequence(
        prepareAiEnterGame(), // 打包好的进入游戏流程
        AiOffline()        // 做完之后下线
    )
    return robotBeh
}
