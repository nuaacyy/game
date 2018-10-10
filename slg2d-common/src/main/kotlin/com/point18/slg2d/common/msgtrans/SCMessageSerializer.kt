package com.point18.slg2d.common.msgtrans

import akka.serialization.JSerializer
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import pb4server.ProtoPlayerEnvelope
import pb4server.ProtoWorldEnvelope
import pb4server.ScMsgEnvelope

/** 表示在集群内部传送的SCMessage */
data class ScMessageAtSend(val msgType: MsgType, val clientMsgNo: Int, val msg: MessageLite)

data class ScMessageAtReceive(val msgType: MsgType, val clientMsgNo: Int, val msgBin: ByteArray)

/**
 * 专门用于序列化[InternalSCMessage]，增加此[JSerializer]的原因：
 * - 提高性能，gate不必再deserialize Response和SC消息
 * - 增强gate的向前兼容性，可以在不更新重启gate的前提下新增SCMessage类型，从而增强了集群的在线更新能力
 *
 * Created by liul on 2017/5/24.
 */
class InternalSCMessageSerializer : JSerializer() {

    override fun identifier(): Int = 17175513

    override fun toBinary(o: Any): ByteArray {
        o as ScMessageAtSend
        val scMsgEnvelope = ScMsgEnvelope.newBuilder()
        scMsgEnvelope.msgType = o.msgType.msgType
        scMsgEnvelope.clientMsgNo = o.clientMsgNo
        scMsgEnvelope.msgBin = o.msg.toByteString()
        return scMsgEnvelope.build().toByteArray()
    }

    override fun includeManifest(): Boolean = false

    override fun fromBinaryJava(bytes: ByteArray, manifest: Class<*>?): Any {
        val scMsgEnvelope = ScMsgEnvelope.newBuilder().mergeFrom(bytes).build()
        val scMessage = ScMessageAtReceive(
            MsgType.fromValue(scMsgEnvelope.msgType, MsgType.Unknown_Msg_20001),
            scMsgEnvelope.clientMsgNo,
            scMsgEnvelope.msgBin.toByteArray()
        )
        return scMessage
    }
}

// 发送到Home服的客户端消息
data class ProtoPlayer(val playerId: Long, val msgType: MsgType, val clientMsgNo: Int, val msg: MessageLite?)

class InternalProtoPlayerSerializer : JSerializer() {

    override fun identifier(): Int = 27175513

    override fun toBinary(o: Any): ByteArray {
        o as ProtoPlayer
        val protoPlayerEnvelope = ProtoPlayerEnvelope.newBuilder()
        protoPlayerEnvelope.playerId = o.playerId
        protoPlayerEnvelope.msgType = o.msgType.msgType
        protoPlayerEnvelope.clientMsgNo = o.clientMsgNo
        o.msg?.let {
            protoPlayerEnvelope.msgBin = it.toByteString()
        }
        return protoPlayerEnvelope.build().toByteArray()
    }

    override fun includeManifest(): Boolean = false

    override fun fromBinaryJava(bytes: ByteArray, manifest: Class<*>?): Any {
        val protoPlayerEnvelope = ProtoPlayerEnvelope.newBuilder().mergeFrom(bytes).build()
        val prototype = MsgType.fromReq(protoPlayerEnvelope.msgType)
        val messageLite =
            if (prototype == null) {
                null
            } else {
                prototype.newBuilderForType().mergeFrom(protoPlayerEnvelope.msgBin).build()
            }
        val protoPlayer = ProtoPlayer(
            protoPlayerEnvelope.playerId,
            MsgType.fromValue(protoPlayerEnvelope.msgType, MsgType.Unknown_Msg_20001),
            protoPlayerEnvelope.clientMsgNo,
            messageLite
        )
        return protoPlayer
    }
}

// 发送到World服的客户端消息
data class ProtoWorld(
    val playerId: Long,
    val worldId: Long,
    val msgType: MsgType,
    val clientMsgNo: Int,
    val msg: MessageLite?
)

class InternalProtoWorldSerializer : JSerializer() {

    override fun identifier(): Int = 37175513

    override fun toBinary(o: Any): ByteArray {
        o as ProtoWorld
        val protoWorldEnvelope = ProtoWorldEnvelope.newBuilder()
        protoWorldEnvelope.playerId = o.playerId
        protoWorldEnvelope.worldId = o.worldId
        protoWorldEnvelope.msgType = o.msgType.msgType
        protoWorldEnvelope.clientMsgNo = o.clientMsgNo
        o.msg?.let {
            protoWorldEnvelope.msgBin = it.toByteString()
        }
        return protoWorldEnvelope.build().toByteArray()
    }

    override fun includeManifest(): Boolean = false

    override fun fromBinaryJava(bytes: ByteArray, manifest: Class<*>?): Any {
        val protoWorldEnvelope = ProtoWorldEnvelope.newBuilder().mergeFrom(bytes).build()
        val prototype = MsgType.fromReq(protoWorldEnvelope.msgType)
        val messageLite =
            if (prototype == null) {
                null
            } else {
                prototype.newBuilderForType().mergeFrom(protoWorldEnvelope.msgBin).build()
            }
        val protoWorld = ProtoWorld(
            protoWorldEnvelope.playerId,
            protoWorldEnvelope.worldId,
            MsgType.fromValue(protoWorldEnvelope.msgType, MsgType.Unknown_Msg_20001),
            protoWorldEnvelope.clientMsgNo,
            messageLite
        )
        return protoWorld
    }
}
