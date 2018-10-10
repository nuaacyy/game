package com.point18.slg2d.robot.robotData

import akka.actor.AbstractActor
import akka.actor.ActorRef
import akka.actor.Cancellable
import akka.actor.Props
import com.point18.slg2d.common.netmsg.MsgType
import xyz.ariane.util.lzInfo
import xyz.ariane.util.lzWarn
import com.point18.slg2d.common.commonfunc.normalLog
import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelFuture
import com.point18.slg2d.robot.robotruntime.LockObj
import com.point18.slg2d.robot.robotruntime.RobotPropChg
import scala.concurrent.duration.FiniteDuration
import java.util.*
import java.util.concurrent.TimeUnit

interface RobotWebReq

const val SLEEP_TIME: Long = 1000

var PVE_NPC_CITY_X: Int = 751
var PVE_NPC_CITY_Y: Int = 715

// 最大联盟数必须是国家数的倍数
var MAX_ALLIANCE_NUM: Int = 24
var MAX_COUNTRY_NUM: Int = 3

data class RobotHeartMsg(val now: Long)

// 机器人实例
class Robot(
    var b: Bootstrap,               //netty 引导
    var id: Int,                    // 机器人ID
    var pltAreaNo: Int,            //机器人要登录的区
    var robotBeh: RobotAction,        // AI动作
    var disconnectFailed: Boolean    // 如果断开连接连接，是否把机器人标记为failed
) : AbstractActor() {

    lateinit var robot: ActorRef                        //  akka ActorRef和AbstractActor的关系
    var name: String = "robot$id"                    // 机器人名字
    var requestCloseConnection = false
    var closedConnection: Boolean = false                // 连接已经关闭
    var failed: Boolean = false                           // 这个机器人失败
    var normalOffLine: Boolean = false                        // 一般情况下正常的下线，与failed区别起来
    var thisRobotData: RobotData = RobotData()                    // 这个机器人数据
    var runCount: Int = 0                                // 这个机器人总运行次数
    var chgsPoolLock: LockObj = LockObj()                // 这个机器人的消息队列锁
    var chgsPool: MutableList<RobotPropChg> = mutableListOf() // 这个机器人接收到的消息的队列
    var startTime = Date()
    var endTime = Date()
    var costTime = 0L

    lateinit var sch: Cancellable

    init {

    }

    // 伴随类 创建Robot实例
    companion object {
        fun props(b: Bootstrap, id: Int, pltAreaNo: Int, robotBeh: RobotAction, disconnectFailed: Boolean): Props {
            return Props.create(Robot::class.java, b, id, pltAreaNo, robotBeh, disconnectFailed)
        }
    }

    fun addPropsChgMsg(msgEvent: RobotPropChg) {
        synchronized(this.chgsPoolLock) {
            // 异步锁添加消息到消息池
            chgsPool.add(msgEvent)
        }
    }

    //  AbstractActor开始时要添加的动作
    override fun preStart() {
        this.robot = self()
        this.startTime = Date()

        // 500ms后执行：每500ms发送一个心跳，发送者self，接收者self
        sch = context.system().scheduler().schedule(
            FiniteDuration(SLEEP_TIME, TimeUnit.MILLISECONDS),
            FiniteDuration(SLEEP_TIME, TimeUnit.MILLISECONDS),
            self(),
            RobotHeartMsg(0L),
            context.dispatcher(),
            self()
        )

    }

    override fun postStop() {
        sch.cancel()
        this.endTime = Date()
        this.costTime = endTime.time - startTime.time
        // println("robot actor postStop")
    }

    override fun createReceive(): Receive {  //  心跳和消息接受后的处理逻辑
        return receiveBuilder()
            .match(RobotHeartMsg::class.java) {
                // 收到心跳消息时执行AI逻辑
                thisRobotRun()
            }
            .match(RobotPropChg::class.java) { propChg ->
                // 收到proChg消息时把消息放到addChgs队列
                normalLog.lzInfo { "接受消息，添加到消息池  msgNo= ${propChg.msgNo} name= ${this.name}" }
                this.addPropsChgMsg(propChg)
            }
            .build()
    }

    private fun thisRobotRun() {
        // - 增加总循环次数
        synchronized(allForNumLock) {
            allForNum++
        }

        // - 机器人AI
        this.runThisAiUpdate()    // 运行这个机器人的update（） 非阻塞的

        // - 处理消息
        // 这里面放的时接收到的消息  时服务器发送给客户端的
        val queue: MutableList<RobotPropChg> = mutableListOf()
        synchronized(this.chgsPoolLock) {
            for (chgEle in this.chgsPool) {   // 异步锁，从消息池里面拿收到的消息
                queue.add(chgEle)
            }
            this.chgsPool = mutableListOf()    //  将消息池清空
        }

        // 遍历临时队列，处理消息
        if (queue.size == 0) {
//             println("接收的消息队列没有内容")
        } else {
//             println("${this.name}有${queue.size}消息要处理")
        }

        for (chgElement in queue) {   //  遍历处理拿到的消息
            this.onReceiveMsg(chgElement)  //  非阻塞的处理消息
        }

        this.runCount++       // - 增加机器人本身的循环次数

        // - 结束判断
        if (this.failed) { // 失败的话
            subRobot(this.robot)
            synchronized(failedNumLock) {
                failedNum++
            }
            context.stop(this.robot)
            return
        }

        if (this.normalOffLine) {    // 正常下线的话，吃毒药挂掉
//            println("机器人normalOffLine")
            subRobot(this.robot)
            synchronized(normalOfflineNumLock) {
                normalOfflineNum++
            }
            context.stop(this.robot)
            return
        }
    }

    //负责在 runeach() 里面处理消息  除了msgTrigger() 还有msgDeal()    3000以后的消息是服务器主动发给客户端的，不需要客户端请求的
    private fun onReceiveMsg(chg: RobotPropChg) {   // RobotPropChg结构：msgNo: MsgType, var msg: C2SMsg?, var recvTime: Date
        val msgType = chg.msgNo
//        println("收到${chg.msgNo}号消息,正在处理~")

        // 记录运行次数
        val runCount = this.thisRobotData.alreadyRunCountMap[msgType] ?: 0  // 该类消息 运行的次数
        this.thisRobotData.alreadyRunCountMap[msgType] = runCount + 1   //次数自加

        // 推送处理
        val msgDeal = msgDeals[msgType] // 这张map表中对应的是各种3000以后消息类型客户端所要做的事情
        if (msgDeal != null) {  // 找到处理消息的方法
            val rt = msgDeal(this, chg)  // 消息处理结果
            if (!rt) {    // rt返回false是有问题
                if (msgType != MsgType.Disconnected) {        // 如果消息类型不是上一次断开连接发送的10000，就失败了
                    normalLog.lzInfo { "在处理推送消息msgtype= $msgType 时，失败，机器人结束" }
                    this.failed = true  //  标志机器人运行失败
                    return      // 返回
                } else {
                    normalLog.lzInfo { "在处理推送消息msgtype= $msgType 时，断开连接，机器人结束" }
                }
            }

        } else {
            // msgDeals里面找不到处理消息的方法
            if (msgType.msgType > 3000) {
//                 normalLog.lzWarn { "找不到推送消息:msgtype= ${msgType} 的处理方法" }
            }
        }

        // 行为对推送消息的处理
        val result = this.robotBeh.msgTrigger(this, chg) // 在这里处理完服务器返回的消息之后  在交给MsgTrigger去处理
        if (result == RUNNING) {  // 返回RUNNING 则是消息处理中，还需继续等下一个onReceiveMsg继续处理
            return
        } else {
            if (result == FAILED) {
                // msgTrigger处理失败
                normalLog.lzWarn {
                    " 消息已经运行次数： ${this.thisRobotData.alreadyRunCountMap[msgType]}，" + "msgType消息号: $msgType 触发处理失败，机器人${this.name} 结束"
                }
                this.failed = true  //  标志机器人运行失败
            }
        }
    }

    private fun runThisAiUpdate() {  // 运行这个机器人的update（）
        // 运行AI(这里要看上层传进来的ai是什么类型的，也就是取决去Update的实现)
        showExecPath(this.robotBeh)  //  显示这个AI动作的执行路径 节点类型是leaf才打印
        val rt = this.robotBeh.update(this) // 消息也是从update中发送出去的
        if (rt == RUNNING) {
            // 如果返回的是running就什么都不做  等待一个不是running的返回就退出
        } else if (rt == FAILED) { //返回的不是running 就退出   也就只有offline（下线的时候才会退出返回的是success）
            this.failed = true   //  标志机器人运行失败
        } else if (rt == SUCCESS) {
            this.normalOffLine = true
        }
    }

    /**
     * 请求服务器连接返回Future
     */
    fun requestConn(): ChannelFuture {
        val addr = robotCfg.host   //  服务器IP
        val port = robotCfg.port   //  服务器port
        val f = synchronized(robotManage) {
            //  这个异步锁作用: awaitConnectRobots资源操作不能同时共享
            val future = b.connect(addr, port)  //  非阻塞链接得到一个future
            addAwaitConnectRobot(future.channel(), robot)  //发出请求后，加入到等待回复建立连接的队列
            future
        }
        return f
    }

}


