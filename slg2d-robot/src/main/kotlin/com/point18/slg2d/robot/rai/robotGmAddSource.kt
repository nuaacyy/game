package com.point18.slg2d.robot.rai

import com.point18.slg2d.robot.robot.AiSequence
import com.point18.slg2d.robot.robot.gm.*
import com.point18.slg2d.robot.robotData.RobotAction

fun robotGmAddSource(): RobotAction {

    val robotBeh = AiSequence(
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