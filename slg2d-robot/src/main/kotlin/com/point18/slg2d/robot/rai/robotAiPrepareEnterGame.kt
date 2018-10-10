package com.point18.slg2d.robot.rai

import com.point18.slg2d.robot.robot.AiSequence
import com.point18.slg2d.robot.robot.gm.*
import com.point18.slg2d.robot.robot.realm.AiConnectServer
import com.point18.slg2d.robot.robot.realm.AiEnterGame
import com.point18.slg2d.robot.robot.realm.AiLogin

// 准备部队
fun prepareAiEnterGame(): com.point18.slg2d.robot.robotData.RobotAction {
    // AI
    val robotBeh = AiSequence(
        AiConnectServer(), // 0:连接服务器
        AiLogin(),         // 1:登录
        AiEnterGame(),   // 4:进入游戏
        AiChatGm(ADD_GOLD),
        AiChatGm(ADD_FOOD),
        AiChatGm(ADD_WOOD),
        AiChatGm(ADD_IRON),
        AiChatGm(ADD_COIN),
        AiChatGm(ADD_GOLDCOIN),
        AiChatGm(ADD_SILVERCOIN),
        AiChatGm(ADD_STONE),
        AiChatGm(ADD_DECREE)

    )
    return robotBeh
}