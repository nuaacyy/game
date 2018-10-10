package com.point18.slg2d.home.msgnotice

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import pb4client.AddResChange

// 资源增加推送
data class AddResChangeNotifier(
    val msg: AddResChange.Builder,
    private var has: Boolean
) {
    fun append(resType: ResourceType, protoId: Int, num: Int) {
        when (resType) {
            RES_GOLD ->
                // 钻石
                this.msg.gold = this.msg.gold + num
            RES_BIND_GOLD ->
                // 绑定钻石
                this.msg.bindGold = this.msg.bindGold + num
            RES_COIN ->
                // 铜币
                this.msg.coin = this.msg.coin + num
            RES_ALLIANCE_COIN ->
                // 铜币
                this.msg.allianceCoin = this.msg.allianceCoin + num
            RES_HERO_EXP_POOL ->
                // 铜币
                this.msg.heroExpPool = this.msg.heroExpPool + num
            RES_WOOD ->
                // 木料
                this.msg.wood = this.msg.wood + num
            RES_IRON ->
                // 晶矿
                this.msg.iron = this.msg.iron + num
            RES_STONE ->
                // 石料
                this.msg.stone = this.msg.stone + num
            RES_FOOD ->
                // 粮食
                this.msg.food = this.msg.food + num
            RES_DECREE ->
                // 政令
                this.msg.decree = this.msg.decree + num
            RES_FAME ->
                // 声望
                this.msg.fame = this.msg.fame + num
            RES_JJC_COIN ->
                // 竞技币
                this.msg.jjcCoin = this.msg.jjcCoin + num
            RES_HONOR ->
                // 荣誉值
                this.msg.honor = this.msg.honor + num
            else ->
                return
        }

        this.has = true
    }

    fun notice(session: PlayerActor) {
        if (!this.has) {
            return
        }

        session.sendMsg(MsgType.AddResChange_3085, this.msg.build())
    }
}

fun createAddResChangeNotifier(action: Int): AddResChangeNotifier {
    val addResChangeBuilder = AddResChange.newBuilder()
    addResChangeBuilder.resAction = action

    return AddResChangeNotifier(addResChangeBuilder, false)
}


