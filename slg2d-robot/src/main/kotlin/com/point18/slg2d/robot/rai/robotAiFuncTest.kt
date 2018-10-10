package com.point18.slg2d.robot.rai

import com.point18.slg2d.robot.robot.AiLoop
import com.point18.slg2d.robot.robot.AiSelect
import com.point18.slg2d.robot.robot.AiSequence
import com.point18.slg2d.robot.robot.behnull.AiAlwaysSuccess
import com.point18.slg2d.robot.robot.gm.AiChatGm
import com.point18.slg2d.robot.robot.gm.ADD_GOLD
import com.point18.slg2d.robot.robot.innerBuilding.AiCreateInnerBuilding
import com.point18.slg2d.robot.robot.innerBuilding.AiUnlockInnerCity
import com.point18.slg2d.robot.robot.innerBuilding.AiUpInnerBuilding
import com.point18.slg2d.robot.robot.realm.AiOffline
import com.point18.slg2d.robot.robotData.RobotAction

fun createAiFuncTest(): RobotAction {
    // AI
    val robotBeh = AiSequence(
        prepareAiEnterGame(), // 打包好的进入游戏流程

        AiChatGm(ADD_GOLD), // gm命令准备资源

//        AiShowNearMap(), // 显示地图

        // 一次升满所有建筑比较慢,需要循环很多次
        AiLoop(
            1,
            AiSelect(
                AiCreateInnerBuilding(), // 建造建筑
                AiUnlockInnerCity(),        // 解锁建筑
                AiUpInnerBuilding(),     // 升级建筑
                AiAlwaysSuccess()       // 始终成功
            )
        ),

        AiChatGm(ADD_GOLD), // gm命令准备资源

        AiLoop(
            1,
            AiSelect(
                AiCreateInnerBuilding(), // 建造建筑
                AiUnlockInnerCity(),        // 解锁建筑
                AiUpInnerBuilding(),     // 升级建筑
                AiAlwaysSuccess()       // 始终成功
            )
        ),

        AiOffline() // 做完之后下线
    )
    return robotBeh
}