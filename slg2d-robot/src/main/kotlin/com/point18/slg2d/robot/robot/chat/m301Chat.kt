package com.point18.slg2d.robot.robot.chat

import com.point18.slg2d.common.netmsg.MsgType
import xyz.ariane.util.lzDebug
import xyz.ariane.util.lzInfo
import com.point18.slg2d.common.commonfunc.normalLog
import pb4client.*
import com.point18.slg2d.robot.robotData.*
import com.point18.slg2d.robot.robotruntime.RobotPropChg

class AiChat : com.point18.slg2d.robot.robot.AiLeafBase() {

    override fun reset(): RobotAction {
        return AiChat()
    }

    override fun actionDesc(): String {
        return "AiChat - 聊天"
    }

    override fun msgTrigger(robot: Robot, chg: RobotPropChg): ActionResult {
        val sendChatMsgRt = chg.convert<SendChatMsgRt>()
            ?: return com.point18.slg2d.robot.robotData.RUNNING

        val rt = (sendChatMsgRt.rt)
        if (rt != com.point18.slg2d.common.resultcode.ResultCode.SUCCESS.code) {
            normalLog.lzDebug {
                "msgTrigger:${this.javaClass.name}:robot[${robot.name}]" +
                    " - 聊天失败：${com.point18.slg2d.robot.robotData.getErrMsg(rt)}"
            }
            return com.point18.slg2d.robot.robotData.FAILED
        }
        println("聊天成功~!~!~!~!~!")
        return com.point18.slg2d.robot.robotData.SUCCESS

    }

    override fun update(robot: Robot): ActionResult {
        normalLog.lzInfo { "update:${this.javaClass.name}:robot[${robot.name}] - 正在进入聊天..." }
        val chatMsg = SendChatMsg.newBuilder()

        val chatMsgString = "机器人号${robot.thisRobotData.playerData.playerId}聊天~~!!!@"

        // 此处聊天只是世界聊天
        chatMsg.type = 0
        chatMsg.playerId = robot.thisRobotData.playerData.playerId
        chatMsg.message = chatMsgString
        chatMsg.messageType = 1

        println("AiChat 动作发送了消息:$chatMsgString")
        robot.thisRobotData.sendMsg(MsgType.SendChat_301, chatMsg.build())

        return RUNNING
    }
}