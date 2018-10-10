package com.point18.slg2d.world

import akka.actor.ActorRef
import com.point18.slg2d.world.area4data.AreaCache
import java.util.*

var channelTerminatedDeal = ChannelTerminatedDeal()

interface TerminatedBaseDeal {
    fun dealTerminated(areaCache: AreaCache, channelActor: ActorRef)
}

class ChannelTerminatedDeal {
    private val dealList = LinkedList<TerminatedBaseDeal>()

    fun registerTerminatedDeal(deal: TerminatedBaseDeal) {
        dealList.add(deal)
    }

    fun dealTerminated(areaCache: AreaCache, channelActor: ActorRef) {
        dealList.forEach {
            it.dealTerminated(areaCache, channelActor)
        }
    }
}