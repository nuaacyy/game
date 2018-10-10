package com.point18.slg2d.gate.net4g

import com.point18.slg2d.common.netmsg.C2SMsg
import io.netty.buffer.Unpooled.wrappedBuffer
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageEncoder

class C2SMsgProtobufEncoder : MessageToMessageEncoder<C2SMsg>() {

    override fun encode(ctx: ChannelHandlerContext, msg: C2SMsg, out: MutableList<Any>) {
        val msgType = msg.msgType
        val intBytes = ByteArray(2)
        intBytes[1] = (msgType.msgType and 0xff).toByte()
        intBytes[0] = (msgType.msgType shr 8 and 0xff).toByte()

		val msgNo = msg.clientMsgNo

		val msgNoBytes = ByteArray(4)
        msgNoBytes[3] = (msgNo and 0xff).toByte()
        msgNoBytes[2] = (msgNo shr 8 and 0xff).toByte()
        msgNoBytes[1] = (msgNo shr 16 and 0xff).toByte()
        msgNoBytes[0] = (msgNo shr 24).toByte()

        var datas = msg.msgBodyBin
        if (datas == null) {
            val protobufMsg = msg.msgBody
            if (protobufMsg != null) {
                datas = protobufMsg.toByteArray()
                msg.msgBodyBin = datas

                val wrappedBuffer = wrappedBuffer(intBytes, msgNoBytes, datas)

                out.add(wrappedBuffer)

            } else {
                out.add(wrappedBuffer(intBytes, msgNoBytes))
            }

        } else {
            out.add(wrappedBuffer(intBytes, msgNoBytes, datas))
        }
    }
}