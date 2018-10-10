package com.point18.slg2d.common.netmsg

import com.google.protobuf.MessageLite
import java.io.Serializable

class C2SMsg(val msgType: MsgType) : Serializable {

    var clientMsgNo: Int = 0            //  客户端消息序号,保证客户端的发送消息序号和得到消息序号 有序 对应
    var msgBody: MessageLite? = null    //  发送消息的主要内容protoBuf
    var msgBodyBin: ByteArray? = null   //  二进制消息

    constructor(msgType: MsgType, msgBody: MessageLite?) : this(msgType) {  //构造器  有MessageLite的
        this.msgBody = msgBody
    }

    constructor(msgType: MsgType, clientMsgNo: Int, msgBodyBin: ByteArray?) : this(msgType) {  //构造器  无MessageLite的
        this.clientMsgNo = clientMsgNo
        this.msgBodyBin = msgBodyBin
    }

}