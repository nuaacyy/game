package com.point18.slg2d.common.commonfunc

import akka.actor.ActorRef
import akka.event.LoggingAdapter
import com.google.protobuf.MessageOrBuilder
import xyz.ariane.util.lzDebug
import com.google.protobuf.ProtobufMessageFormat

fun formatMessage(message: Any?): String {
    return when (message) {
        null -> "null"
        else -> "${message.javaClass.simpleName} " +
            when (message) {
                is MessageOrBuilder -> ProtobufMessageFormat.shortDebugUnicodeString(message)
                else -> message.toString()
            }
    }
}

@JvmOverloads
fun ActorRef.tellNoSender(msg: Any, logger: LoggingAdapter? = null) {
    tell(msg, ActorRef.noSender())
    logger?.lzDebug { "Tell $this ${formatMessage(msg)}" }
}