package com.point18.slg2d.robot.rmd

import xyz.ariane.util.lzDebug
import com.point18.slg2d.common.commonfunc.normalLog
import pb4client.NewChatMessage

fun mdNewChatMessage(robot: com.point18.slg2d.robot.robotData.Robot, chg: com.point18.slg2d.robot.robotruntime.RobotPropChg): Boolean {
    val newChatMessage = chg.convert<NewChatMessage>()
    if (newChatMessage == null) {
        return false
    }

    val chatInfo = newChatMessage.chatInfo
    val message = chatInfo.message

    normalLog.lzDebug { "推送的聊天消息：$message" }

    return true
}