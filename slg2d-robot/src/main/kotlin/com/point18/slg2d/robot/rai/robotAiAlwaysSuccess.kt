package com.point18.slg2d.robot.rai

import com.point18.slg2d.robot.robot.AiLoop
import com.point18.slg2d.robot.robot.AiSelect
import com.point18.slg2d.robot.robot.AiSequence
import com.point18.slg2d.robot.robot.behnull.AiAlwaysSuccess

fun createAlwaysSuccess(): com.point18.slg2d.robot.robotData.RobotAction {
    // AI
    val robotBeh = AiSequence(
        AiAlwaysSuccess(),
        AiAlwaysSuccess(),
        AiAlwaysSuccess(),
        AiLoop(
            2, AiSelect(
                AiAlwaysSuccess(),
                AiAlwaysSuccess(),
                AiAlwaysSuccess()
            )
        ),

        AiAlwaysSuccess()
    )
    return robotBeh
}