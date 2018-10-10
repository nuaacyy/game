package com.point18.slg2d.gate.net4g

import akka.actor.AbstractActor
import akka.actor.ActorRef
import akka.actor.Props
import akka.event.japi.LookupEventBus
import pb4server.MulticastEnvelopeMsg

/**
 * 多播服务Actor，也就是routee。
 */
class MulticastService : AbstractActor() {

    companion object {
        fun props(): Props = Props.create(MulticastService::class.java).withDispatcher("akka.actor.channel")
    }

    override fun preStart() {
    }

    override fun postStop() {
    }

    override fun createReceive(): Receive {
        return receiveBuilder()
                .match(MulticastEnvelopeMsg::class.java) { msg ->
                    ProtoMulticastEventBus.publish(msg)
                }
                .matchAny {
                }
                .build()
    }
}

object ProtoMulticastEventBus : LookupEventBus<MulticastEnvelopeMsg, ActorRef, String>() {

    /**
     * 根据什么分类
     */
    override fun classify(event: MulticastEnvelopeMsg): String {
        return event.channel
    }

    override fun compareSubscribers(a: ActorRef, b: ActorRef): Int {
        return a.compareTo(b)
    }

    /**
     * 索引数据结构的大小，有多少种可能的分类。
     */
    override fun mapSize(): Int {
        return 5000
    }

    override fun publish(event: MulticastEnvelopeMsg, subscriber: ActorRef) {

        if (event.hasUnsubscribeCmd()) {
            println("ProtoMulticastEventBus 收到  unsubscribeCmd")
            subscriber.tell(event, ActorRef.noSender())
        }
        if (event.hasSubscribeCmd()) {
            println("ProtoMulticastEventBus 收到  subscribeCmd")
            subscriber.tell(event, ActorRef.noSender())
        }
        if (event.hasNewChatMsg()) {
            println("ProtoMulticastEventBus 收到  NewChatMsg()")
            subscriber.tell(event, ActorRef.noSender()) //整体发过去，envelope还带有其他附加信息
        }
        if (event.hasGroupChatMsg()) {
            println("ProtoMulticastEventBus 收到  GroupChatMsg()")
            subscriber.tell(event, ActorRef.noSender()) //整体发过去，envelope还带有其他附加信息
        }
    }
}