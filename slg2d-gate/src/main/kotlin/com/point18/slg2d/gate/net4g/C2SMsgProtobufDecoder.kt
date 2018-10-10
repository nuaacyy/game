package com.point18.slg2d.gate.net4g

import com.point18.slg2d.common.netmsg.C2SMsg
import com.point18.slg2d.common.netmsg.MsgType
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import org.slf4j.LoggerFactory

class C2SMsgProtobufDecoder : MessageToMessageDecoder<ByteBuf>() {

    private val logger = LoggerFactory.getLogger(C2SMsgProtobufDecoder::class.java)

    @Throws(Exception::class)
    override fun decode(ctx: ChannelHandlerContext, inMsg: ByteBuf, out: MutableList<Any>) {
        // 读取消息类型。

        val msgType = inMsg.readShort().toInt()
        val c2sMsg = C2SMsg(MsgType.fromValue(msgType, MsgType.Unknown_Msg_20001))

        // 解析消息。
        val prototype = MsgType.fromReq(msgType)
        if (prototype == null) {
            out.add(c2sMsg)
            return
        }

        val array: ByteArray
        val clientMsgNo = inMsg.readInt()
        c2sMsg.clientMsgNo = clientMsgNo
        val length = inMsg.readableBytes()
        val offset = if (inMsg.hasArray()) {
            assert(false) // 判断下有没有可能出现这种情况
            array = inMsg.array()
            val readerIndex = inMsg.readerIndex()
            inMsg.arrayOffset() + readerIndex
        } else {
            array = ByteArray(length)
            inMsg.readBytes(array, 0, length)
            0
        }

        val ml = prototype.newBuilderForType().mergeFrom(array, offset, length).build()
        c2sMsg.msgBody = ml
        c2sMsg.msgBodyBin = array

        out.add(c2sMsg)
    }
}