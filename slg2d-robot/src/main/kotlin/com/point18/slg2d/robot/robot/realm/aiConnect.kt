package com.point18.slg2d.robot.robot.realm

import com.point18.slg2d.common.netmsg.MsgType
import xyz.ariane.util.lzDebug
import xyz.ariane.util.lzInfo
import com.point18.slg2d.common.commonfunc.normalLog
import io.netty.channel.ChannelFuture
import com.point18.slg2d.robot.robot.AiLeafBase
import com.point18.slg2d.robot.robotData.*
import com.point18.slg2d.robot.robotruntime.RobotPropChg

class AiConnectServer : AiLeafBase() {

    private var future: ChannelFuture? = null  //  连接的ChannelFuture

    override fun reset(): RobotAction {
        return AiConnectServer()
    }

    override fun actionDesc(): String {
        return "AiConnectServer - 连接目标游戏服务器"
    }

    override fun msgTrigger(robot: Robot, chg: RobotPropChg): ActionResult {
        if (chg.msgNo != MsgType.Connected) {  // 这个消息是在Handler里面的active函数年自己触发的
            normalLog.lzDebug { "msgTrigger:${this.javaClass.name}:robot[${robot.name}] - 接收的消息类型不匹配" }
            return RUNNING
        }

        val f = this.future
        if (f == null) {
            normalLog.lzDebug { "msgTrigger:${this.javaClass.name}:robot[${robot.name}] - 找不到ChannelFuture" }
            return FAILED  // update做完竟然没有future， 那就 return FAILED
        }

        if (f.isDone) { // 连接成功
            normalLog.lzDebug { "msgTrigger:${this.javaClass.name}:robot[${robot.name}] - 连接成功" }
            robot.thisRobotData.channel = f.channel()  //  链接成功则从future中获取channel
            return SUCCESS  // 返回成功
        }

        return RUNNING
    }

    override fun update(robot: Robot): ActionResult {
        normalLog.lzInfo { "update:${this.javaClass.name}:robot[${robot.name}] - 请求连接..." }

        if (robot.thisRobotData.channel != null) {
            normalLog.lzDebug { "update:${this.javaClass.name}:robot[${robot.name}] - 连接已经存在" }
            return FAILED
        }

        val future = robot.requestConn()  // 请求服务器连接
        this.future = future  // 异步操作占位符
        return RUNNING
    }
}