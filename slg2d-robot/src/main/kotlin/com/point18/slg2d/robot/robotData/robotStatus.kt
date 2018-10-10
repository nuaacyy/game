package com.point18.slg2d.robot.robotData

import akka.actor.ActorRef
import io.netty.channel.Channel
import com.point18.slg2d.robot.robotruntime.LockObj

// 全部机器人的大体状况

var currentNum: Int = 0 // 当前数量
var sumNum: Int = 0  // 完成的数量
var offlineNum: Int = 0

// 机器人实例表
var robotManage = LockObj()
private var robots: MutableList<ActorRef> = mutableListOf()  // 正在运行的机器人队列

// 连接中的机器人跟踪
private val awaitConnectRobots: MutableMap<Channel, ActorRef> = mutableMapOf()  // 每个发出请求连接但未获得连接机器人与其连接channel的map

var robotsDealLock: LockObj = LockObj()  //对robots的增删查都要异步锁

var allForNum: Int = 0        // 机器人总循环次数
var allOfflineErrNum: Int = 0 // 机器人一轮AI没找到任务做并且还未达到下线要求的次数
var allForNumLock: LockObj = LockObj()
var allOfflineErrNumLock: LockObj = LockObj()
var normalOfflineNum = 0 // 正常下线的robot数量
var normalOfflineNumLock = LockObj()
var failedNum = 0 // 失败的robot数量
var failedNumLock = LockObj()

// 添加机器人
fun addRobot(robotInstance: ActorRef) {
    synchronized(robotsDealLock) {
        currentNum++
        sumNum++
        robots.add(robotInstance)
    }
}

// 减少机器人  robot失败或者下线时就会subrobot
fun subRobot(robotInstance: ActorRef) {
    synchronized(robotsDealLock) {
        currentNum--
        robots.remove(robotInstance)
    }
}

fun addAwaitConnectRobot(channel: Channel, robotInstance: ActorRef) {
    awaitConnectRobots[channel] = robotInstance
}

fun removeAwaitConnectRobot(channel: Channel) {
    awaitConnectRobots.remove(channel)
}

fun findAwaitConnectRobot(channel: Channel): ActorRef? {
    return awaitConnectRobots[channel]
}

// 获取当前数量
fun fetchCurrentNum(): Int {
    synchronized(robotsDealLock) {
        val nowNum = currentNum
        return nowNum
    }
}

// 获取总数量
fun fetchSumNum(): Int {
    synchronized(robotsDealLock) {
        val sumNum = sumNum
        return sumNum
    }
}