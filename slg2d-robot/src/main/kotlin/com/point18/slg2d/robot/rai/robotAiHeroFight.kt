package com.point18.slg2d.robot.rai

import com.point18.slg2d.robot.robot.AiLoop
import com.point18.slg2d.robot.robot.AiSelect
import com.point18.slg2d.robot.robot.AiSequence
import com.point18.slg2d.robot.robot.heroFight.AiGetHeroFightReport
import com.point18.slg2d.robot.robot.realm.AiOffline
import com.point18.slg2d.robot.robotData.RobotAction

fun createHeroFightRobot(): RobotAction {
    return AiSequence(
        prepareAiEnterGame(), // 打包好的进入游戏流程

        AiSelect(
            AiLoop(
                999,
                AiGetHeroFightReport()
            ),

            AiOffline() // 做完之后下线
        ),

        AiOffline() // 做完之后下线
    )
}