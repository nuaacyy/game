package com.point18.slg2d.robot.robotData

import pb4client.ResourceInfoRt

// 玩家信息
class RDataPlayer {

    var playerId: Long = 0  // 玩家ID
    var playerName: String = ""// 玩家名称
    // 政令
    var decreeNum: Int = 0
    var decreeLimit: Int = 0
    var decreeTime: Long = 0 // Date() 变成Long

    // 资源
    var wood: Int = 0
    var food: Int = 0
    var iron: Int = 0
    var stone: Int = 0
    var decree: Int = 0
    var coin: Int = 0
    var gold: Int = 0
    var bindGold: Int = 0
    var jjcCoin: Int = 0
    var res4Time: Long = 0   // Date() 变成Long
    var allianceCoin: Int = 0
    var heroExpPool: Int = 0

    var continueOnlineDay: Int = 0// 持续在线天数

    init {

    }

    fun updatePlayerRes(res: ResourceInfoRt) {
        this.wood = (res.wood).toInt()
        this.food = (res.food).toInt()
        this.iron = (res.iron).toInt()
        this.stone = (res.stone).toInt()
        this.decree = (res.decree)
        this.coin = (res.coin).toInt()
        this.gold = (res.gold).toInt()
        this.bindGold = (res.bindGold).toInt()
        this.jjcCoin = (res.jjcCoin)
        this.res4Time = res.res4Time.toLong()
        this.allianceCoin = (res.allianceCoin).toInt()
        this.heroExpPool = (res.heroExpPool).toInt()
    }
}



