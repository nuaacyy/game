package com.point18.slg2d.robot.robotruntime

import com.point18.slg2d.common.netmsg.C2SMsg
import com.point18.slg2d.common.netmsg.MsgType
import java.util.*
import com.google.protobuf.MessageLite

class RobotPropChg(var msgNo: MsgType, var msg: C2SMsg?, var recvTime: Date) {

    inline fun <reified T : MessageLite> convert(): T? {
        val msg = this.msg ?: return null
        val body = msg.msgBody ?: return null
        if (body is T) {
            return body
        } else {
            println("${msgNo.msgType}消息转型失败 ")
            throw RuntimeException("${msgNo.msgType}消息转型失败 ")
        }
    }
}
