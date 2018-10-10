package com.point18.slg2d.robot.rai

import com.point18.slg2d.robot.robot.AiLoop
import com.point18.slg2d.robot.robot.AiSequence
import com.point18.slg2d.robot.robot.realm.AiOfflineAndOnline

fun createAiManyTimesEnterGame(n: Int, playerName: String): com.point18.slg2d.robot.robotData.RobotAction {
    //AI
    val robotBeh = AiSequence(
        AiLoop(
            n, AiSequence(
                prepareAiEnterGame(), // 打包好的进入游戏流程
                AiOfflineAndOnline() // 下线
            )
        )
    )
    return robotBeh
}