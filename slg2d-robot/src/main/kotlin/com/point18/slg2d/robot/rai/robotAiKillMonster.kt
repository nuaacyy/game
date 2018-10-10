package com.point18.slg2d.robot.rai

import com.point18.slg2d.robot.robot.AiLoop
import com.point18.slg2d.robot.robot.AiSelect
import com.point18.slg2d.robot.robot.AiSequence
import com.point18.slg2d.robot.robot.atkMonster.*
import com.point18.slg2d.robot.robot.gm.AiChatGm
import com.point18.slg2d.robot.robot.gm.ADD_GOLD
import com.point18.slg2d.robot.robot.map.AiShowNearMap
import com.point18.slg2d.robot.robot.realm.AiOffline
import com.point18.slg2d.robot.robotData.RobotAction

fun createAiKillMonsterFuncTest(): RobotAction {
    // AI
    val robotBeh = AiSequence(
        prepareAiEnterGame(), // 打包好的进入游戏流程

        AiChatGm(ADD_GOLD), // gm命令准备资源

        AiCreateAlliance(), // 创建联盟

        AiGetArmyPlan(), // 获取英雄上阵

        AiSetArmyPlan(), // 设置英雄上阵

        AiLoop(
            20,
            AiSelect(
                AiShowNearMap(), // 显示地图

                AiBuyAndUseProp(), // 购买行动力

                AiWalk() // 行军
            )
        ),

        AiOffline() // 做完之后下线
    )
    return robotBeh
}