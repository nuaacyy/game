package com.point18.slg2d.robot.robotNet

import com.point18.slg2d.common.netmsg.C2SMsg
import com.point18.slg2d.common.netmsg.MsgType
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToMessageDecoder
import org.slf4j.LoggerFactory

//  解码器
class RobotTopMsgProtobufDecoder : MessageToMessageDecoder<ByteBuf>() {

    private val logger = LoggerFactory.getLogger(RobotTopMsgProtobufDecoder::class.java)

    @Throws(Exception::class)
    override fun decode(ctx: ChannelHandlerContext, inMsg: ByteBuf, out: MutableList<Any>) {
        // 读取消息类型。
        val msgType = inMsg.readShort().toInt() // 从ByteBuf读2 bytes短Int 是msgType消息号
        val topMsg = C2SMsg(MsgType.fromValue(msgType, MsgType.Unknown_Msg_20001))

//        println("接收解码 msgType ${msgType}")

        // 解析消息。
        // MsgType对应的消息类型
        val prototype = MsgType.fromResp(msgType)  // 获得消息号msgType对应的回复类型，用于解码
        if (prototype == null) {  // 找不到时，把这个C2SMsg放进out
            out.add(topMsg)
            return
        }

        // 消息序号
        inMsg.readInt()

        // 消息体
        val array: ByteArray
        val offset: Int
        val length = inMsg.readableBytes()  // 获取inMsg剩下的可以读取的Bytes长度
        if (inMsg.hasArray()) {   // ？？？？？？？？？？？？？？？？？？？？？？？？？？？？
            assert(false) // 判断下有没有可能出现这种情况
            array = inMsg.array()
            val readerIndex = inMsg.readerIndex()
            offset = inMsg.arrayOffset() + readerIndex
        } else {
            array = ByteArray(length)  //  建立一个数组存储可读的Bytes
            inMsg.readBytes(array, 0, length)  // 把剩下的Bytes放进一个ByteArray
            offset = 0   // 补偿
        }

        val ml = prototype.newBuilderForType().mergeFrom(array, offset, length)
            .build()  // 根据得到的消息类型，把array解码成该类型的MessageLite
        topMsg.msgBody = ml  // MessageLite: 这就是解码后的消息类型

        out.add(topMsg)  // 解码后添加到输出队列
    }
}