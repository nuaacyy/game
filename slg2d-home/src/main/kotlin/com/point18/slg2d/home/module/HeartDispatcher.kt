package com.point18.slg2d.home.module

import com.point18.slg2d.common.constg.HomeHeartAction
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.HomeHelper
import java.util.*

/**
 * 心跳处理
 */
interface IHeartDeal {
    fun dealHeart(session: PlayerActor, actionId: Long, onComplete: (rt: Int) -> Unit)
}

val heartDealsAtHome: MutableMap<HomeHeartAction, IHeartDeal> = mutableMapOf()

fun registerHeart(action: HomeHeartAction, deal: IHeartDeal) {
    heartDealsAtHome[action] = deal

    if (deal is HomeHelper) {
        deal.initHelper()
    }
}

/**
 * 长时间的心跳处理
 */
interface ILongTimeHeartDeal {
    fun dealHeart(session: PlayerActor)
}

val longTimeHeartDealsAtHome = LinkedList<ILongTimeHeartDeal>()

fun registerLongTimeHeart(deal: ILongTimeHeartDeal) {
    longTimeHeartDealsAtHome.add(deal)
}