package com.point18.slg2d.robot.robotNet

import com.point18.slg2d.common.netmsg.C2SMsg
import io.netty.buffer.Unpooled.wrappedBuffer
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageEncoder

// 编码器  要编码的类型是C2SMsg
class RobotTopMsgProtobufEncoder : MessageToMessageEncoder<C2SMsg>() {

    override fun encode(ctx: ChannelHandlerContext, msg: C2SMsg, out: MutableList<Any>) {
//        println("发送编码 msgType ${msg.msgType}")

        val msgType = msg.msgType  // 对消息好进行编码
        val intByte = ByteArray(2)  // 消息号是16位，2 Bytes
        intByte[1] = (msgType.msgType and 0xff).toByte()    // 消息号 &  0xff
        intByte[0] = (msgType.msgType shr 8 and 0xff).toByte()// 消息号 右移 8 位 与 0xff
        // intByte[1] = (byte) ((msgType >> 16) & 0xff);
        // intByte[0] = (byte) ((msgType >> 24));

        val msgNo = msg.clientMsgNo
        val msgNoByte = ByteArray(4)
        msgNoByte[3] = (msgNo and 0xff).toByte()
        msgNoByte[2] = (msgNo shr 8 and 0xff).toByte()
        msgNoByte[1] = (msgNo shr 16 and 0xff).toByte()
        msgNoByte[0] = (msgNo shr 24).toByte()

        var datas = msg.msgBodyBin  // 二进制消息主题，一般都是空啦，而后根据MessageLite进行编码再放进去
        if (datas == null) {
            val protobufMsg = msg.msgBody
            if (protobufMsg != null) {  // 消息MessageLite可以是空的
                datas = protobufMsg.toByteArray()  // 将MessageLite编码成二进制
                msg.msgBodyBin = datas  // 二进制消息主题
//                println("发送编码类型 ${msg.msgType}， msg.msgBodyBin生成,大小为 ${datas.size}")

                val wrappedBuffer = wrappedBuffer(intByte, msgNoByte, datas)  // 将编码后的消息编号，消息次号，消息主题打包
//                println("msgType+msgBody数据包生成，大小为 ${wrappedBuffer.readableBytes()}")
                out.add(wrappedBuffer)  //  放进输出消息队列

            } else {
                out.add(wrappedBuffer(intByte, msgNoByte))  //  放进输出消息队列
            }

        } else {
            out.add(wrappedBuffer(intByte, msgNoByte, datas))  //  放进输出消息队列
        }

    }
}