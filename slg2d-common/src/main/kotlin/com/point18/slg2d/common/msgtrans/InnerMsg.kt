package com.point18.slg2d.common.msgtrans

import akka.cluster.sharding.ShardRegion
import com.google.common.hash.HashFunction
import com.google.common.hash.Hashing
import com.google.common.math.IntMath
import com.point18.slg2d.common.netmsg.C2SMsg
import com.point18.slg2d.common.syncdomain.KryoAskWorldMessage
import pb4server.*
import java.io.Serializable

// 内部消息，即不是从客户端发过来的消息
interface InternalMessage : Serializable

class WorldMessageExtractor : ShardRegion.MessageExtractor {

    override fun entityId(message: Any): String {
        return when (message) {
            is ProtoWorld -> message.worldId.toString()
            is Home2WorldAskReq -> message.worldId.toString()
            is World2WorldAskReq -> message.worldId.toString()
            is Home2WorldTell -> message.worldId.toString()
            is Public2WorldTell -> message.worldId.toString()
            is World2WorldTell -> message.worldId.toString()
            is WakeUpWorld -> message.worldId.toString()
            is KryoAskWorldMessage -> message.worldId.toString()
            is AddNewAreaTell -> message.worldId.toString()


            is C2SMsg -> {
                //println("--------------------------------------------------- 计算 entityId 时，World Shard 收到了C2S消息！")
                throw IllegalArgumentException(errInfo(message))
                //                return "188"
            }
            else -> {
                //println("--------------------------------------------------- 计算 entityId 时，World Shard 收到了 其他 消息！${message}")
                throw IllegalArgumentException(errInfo(message))
            }
        }
    }

    override fun entityMessage(message: Any): Any = message

    /**
     * one world one shard
     */
    override fun shardId(message: Any): String {
        return when (message) {
            is ProtoWorld -> message.worldId.toString()
            is Home2WorldAskReq -> message.worldId.toString()
            is World2WorldAskReq -> message.worldId.toString()
            is Home2WorldTell -> message.worldId.toString()
            is Public2WorldTell -> message.worldId.toString()
            is World2WorldTell -> message.worldId.toString()
            is WakeUpWorld -> message.worldId.toString()
            is KryoAskWorldMessage -> message.worldId.toString()
            is AddNewAreaTell -> message.worldId.toString()

            is C2SMsg -> {
                throw IllegalArgumentException(errInfo(message))
            }
            else -> {
                throw IllegalArgumentException(errInfo(message))
            }
        }
    }
}

// TODO 这边的计算需要改造消息，暂时写0！
class PublicMessageExtractor(private val maxShardNum: Int) : ShardRegion.MessageExtractor {

    override fun entityId(message: Any): String {
        when (message) {
            is World2PublicAskReq -> {
                return message.publicId.toString()
            }
            is Home2PublicAskReq -> {
                return message.publicId.toString()
            }
            is PublicManager2PublicTell -> {
                return message.publicId.toString()
            }
            is InitPublic -> {
                return message.publicId.toString()
            }
            else -> {
                throw IllegalArgumentException(errInfo(message))
            }
        }
    }

    override fun entityMessage(message: Any): Any = message

    override fun shardId(message: Any): String {
        when (message) {
            is World2PublicAskReq -> {
                return message.publicId.toString()
            }
            is Home2PublicAskReq -> {
                return message.publicId.toString()
            }
            is PublicManager2PublicTell -> {
                return message.publicId.toString()
            }
            is InitPublic -> {
                return message.publicId.toString()
            }
            else -> throw IllegalArgumentException(errInfo(message))
        }
    }

    private fun shardIdOf(publicId: Long) = IntMath.mod(hashPublicId(publicId), maxShardNum).toString()

    private val publicIdHashFunction: HashFunction = Hashing.murmur3_32(6121559)

    private fun hashPublicId(cityWarId: Long): Int =
        Math.abs(publicIdHashFunction.hashString(cityWarId.toString(), Charsets.UTF_8).asInt())

}

// TODO 这边的计算需要改造消息，暂时写0！
class HomeMessageExtractor(private val maxShardNum: Int) : ShardRegion.MessageExtractor {

    override fun entityId(message: Any): String {
        return when (message) {
            is ProtoPlayer -> message.playerId.toString()
            is World2HomeAskReq -> message.playerId.toString()
            is Home2HomeAskReq -> message.playerId.toString()
            is Home2HomeTell -> message.playerId.toString()
            is World2HomeTell -> message.playerId.toString()
            is Public2HomeTell -> message.playerId.toString()
            is PingAskRt -> message.playerId.toString()

            else -> {
                //println("--------------------------------------------------- 计算 entityId 时，Home Shard 收到了 其他 消息！${message}")
                throw IllegalArgumentException(errInfo(message))
            }
        }
    }

    override fun entityMessage(message: Any): Any = message

    override fun shardId(message: Any): String {
        return when (message) {
            is ProtoPlayer -> "0"
            is World2HomeAskReq -> "0"
            is Home2HomeAskReq -> "0"
            is Home2WorldAskResp -> "0"
            is Home2HomeTell -> "0"
            is World2HomeTell -> "0"
            is Public2HomeTell -> "0"
            is PingAskRt -> "0"
            else -> throw IllegalArgumentException(errInfo(message))
        }
    }

    private fun shardIdOf(publicId: Long) = IntMath.mod(hashPublicId(publicId), maxShardNum).toString()

    private val homeIdHashFunction: HashFunction = Hashing.murmur3_32(6121559)

    private fun hashPublicId(cityWarId: Long): Int =
        Math.abs(homeIdHashFunction.hashString(cityWarId.toString(), Charsets.UTF_8).asInt())

}

private fun errInfo(message: Any) = "Unknown message type ${message.javaClass}"

//// 向客户端推送的多播消息
//data class SubscribeCmd(val channel: String) : Serializable
//
//data class UnsubscribeCmd(val channel: String) : Serializable
//
//data class KickoutCmd(val channel: String) : Serializable
//
//data class MulticastEnvelopeMsg(
//    val subscribeCmd: SubscribeCmd?,
//    val unsubscribeCmd: UnsubscribeCmd?,
//    val kickOutCmd: KickoutCmd?,
//    val msg: C2SMsg?,
//    val channel: String,
//    val excludePlayerIds: List<PlayerId>? = null
//) : SystemMessage