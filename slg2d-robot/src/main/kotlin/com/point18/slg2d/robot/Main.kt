package com.point18.slg2d.robot

import akka.actor.ActorSystem
import xyz.ariane.util.lzInfo
import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.handler.codec.LengthFieldBasedFrameDecoder
import io.netty.handler.codec.LengthFieldPrepender
import org.slf4j.LoggerFactory
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.robot.rai.*
import com.point18.slg2d.robot.rmd.initMsgDeal
import com.point18.slg2d.robot.robotData.*
import com.point18.slg2d.robot.robotNet.RobotClientHandler
import com.point18.slg2d.robot.robotNet.RobotTopMsgProtobufDecoder
import com.point18.slg2d.robot.robotNet.RobotTopMsgProtobufEncoder
import java.lang.Thread.sleep
import java.time.Instant
import java.util.*

const val ALWAYS_SUCCESS_0: Int = 0
const val FUNC_TEST_1: Int = 1 // 选择性的功能测试
const val FUNC_MONSTER_TEST_2: Int = 2 // 打魔物测试
const val UPDATE_BUILDING_3 :Int = 3 // 升级建筑测试
const val ENTER_GAME_LOOP_5: Int = 5 // 多次登录压力测试
const val SIMPLE_ENTER_GAME_8: Int = 8 // 简单进入游戏测试
const val HERO_FIGHT_9: Int = 9 // 英雄战测试
const val CHAT_TEST_301: Int = 301 // 聊天测试

const val MAX_FRAME_BYTES_LENGTH = 1024 * 1024  // 处理每个帧数据的最大长度

fun main(args: Array<String>) {
    val robotMachine = RobotMachine()
    robotMachine.run()
}

class RobotMachine {

    val logger = LoggerFactory.getLogger(this::class.java)

    // actor系统
    private val system: ActorSystem = ActorSystem.create("robot-system")

    private lateinit var b: Bootstrap

    private var nextCheckTime = Instant.now()

    fun run() {

        logger.lzInfo { "Start run" }

        // 加载配置robot-cfg.json
        robotCfg = initConfig()

        // 初始化游戏配置文件 xml
        pcs.initProtoCache()

        // 初始化消息处理   初始化处理服务器主推给客户端的消息
        initMsgDeal()

        // 初始化网络 获得netty引导brootstrap
        initNettyClient()

        startRobotGroup()
    }
    var pltRobotCreatedNum = 0

    private fun initNettyClient() {
        b = Bootstrap()  // netty引导
        b.group(NioEventLoopGroup())
            .channel(NioSocketChannel::class.java)      //指定所使用的NIO传输channel
            .option(ChannelOption.TCP_NODELAY, true)   // 要求低延迟，禁用nagle算法
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)   // 连接超时
            .option(ChannelOption.SO_SNDBUF, MAX_FRAME_BYTES_LENGTH) //sendBuff大小
            .option(ChannelOption.SO_RCVBUF, MAX_FRAME_BYTES_LENGTH) //receiveBuff大小
            .option(ChannelOption.SO_REUSEADDR, true) //  可以重复使用本地地址和端口
            .handler(object : ChannelInitializer<SocketChannel>() {
                override fun initChannel(ch: SocketChannel) {
                    // 解码处理
                    ch.pipeline().addLast(
                        "frameDecoder", LengthFieldBasedFrameDecoder(
                            MAX_FRAME_BYTES_LENGTH, 0, 4, -4, 4, true
                        )
                    )
                    ch.pipeline().addLast("protobufDecoder", RobotTopMsgProtobufDecoder())

                    // 编码处理
                    ch.pipeline().addLast("frameEncoder", LengthFieldPrepender(4, true))
                    ch.pipeline().addLast("protobufEncoder", RobotTopMsgProtobufEncoder())

                    ch.pipeline().addLast(RobotClientHandler())
                }
            })
    }

    private fun startRobotGroup() {
        val aiType = robotCfg.dealAction  // robot类型
        val addRobotStartTime = Date()
        var addRobotEndTime = Date()
        val observeInterval = 1000L
        var runEndTime = Date()

        logger.lzInfo { "即将开始执行机器人,本次配置代号是${robotCfg.dealAction}" }

        for (eachRobotConfig in robotCfg.robots) {  // 每个平台的机器人数目
            while (pltRobotCreatedNum < eachRobotConfig.robotNum) {   //这个while循环作用就是添加actor机器人
                // 显示当前运行状态
                showStatus()

                // 选择AI
                var disconnectFailed = true // 一般情况下，如果断开连接，就把机器人normalOffLine置true
                lateinit var ai: RobotAction  // ai就是机器人的动作，根据json的配置，选择ai

                when (aiType) {
                    FUNC_TEST_1 -> {
                        ai = createAiFuncTest()
                    }

                    FUNC_MONSTER_TEST_2 -> {
                        ai = createAiKillMonsterFuncTest()
                    }

                    UPDATE_BUILDING_3 ->{
                        ai = robotUnlockBuilding()
                    }

                    ENTER_GAME_LOOP_5 -> {
                        ai = createAiManyTimesEnterGame(100, "ai_$pltRobotCreatedNum") // 参数n 代表进入游戏次数
                        disconnectFailed = false // 多次登陆测试，所以如果断开连接，不会把机器人normalOffLine置false

                    }

                    SIMPLE_ENTER_GAME_8 -> {
                        ai = createAiSimpleEnterGame()
                    }

                    HERO_FIGHT_9 -> {
                        ai = createHeroFightRobot()
                    }

                    ALWAYS_SUCCESS_0 -> {
                        ai = createAlwaysSuccess()
                    }

                    CHAT_TEST_301 -> {
                        // 聊天测试
                        ai = createAiChatTest()
                    }

                    else -> {
                        throw RuntimeException("robot-cfg.json配置错误：不存在这个类型的机器人")
                    }
                }

                // 创建一个机器人Actor
                val robotActor = system.actorOf(
                    Robot.props(b, pltRobotCreatedNum, eachRobotConfig.pltAreaNo, ai, disconnectFailed),
                    "robot-$pltRobotCreatedNum"
                )
                com.point18.slg2d.robot.robotData.addRobot(robotActor)  // 把机器人添加到机器人列表  添加机器人数量

                addRobotEndTime = Date()
                while (com.point18.slg2d.robot.robotData.fetchCurrentNum() > robotCfg.maxOnlineNum) {
                    //达到在线人数之后 暂停添加机器人  等待要处理的消息 达到在线上限，阻塞等待
                    //当机器人人数小于在线人数的话就 跳出循环 继续添加机器人
                    logger.lzInfo { "达到最大在线人数 ${robotCfg.maxOnlineNum}，休息1s" }
                    sleep(1000)
                }

                pltRobotCreatedNum += 1

                if ((pltRobotCreatedNum % 500) == 0) {
                    logger.lzInfo { "已经创建了 $pltRobotCreatedNum 个机器人，休息1s" }
                    sleep(1000)
                }
            }
        }
        logger.lzInfo {
            val minituesOfAddTimes = (addRobotEndTime.time - addRobotStartTime.time) / 60000
            val secondsOfAddTimes = ((addRobotEndTime.time - addRobotStartTime.time) % 60000) / 1000
            "所有区的机器人都已经添加完成，添加机器人需要的时间：${minituesOfAddTimes}分钟 " +
                    "${secondsOfAddTimes}秒"
        }

        do {
            // 显示当前运行状态
            showStatus()

            // 阻塞线程，观测服务器运行情况
            sleep(observeInterval)

            if (currentNum == 0) {  // 如果队列中没有robot，那么就是所有robot都下线了

                runEndTime = Date()

                logger.lzInfo {
                    "所有机器人已经运行完毕，" +
                            "没完成数量：${currentNum}，" +
                            "已经完成的数量：${normalOfflineNum}，运行失败数量：$failedNum,  " +
                            "失败占比：${100 * failedNum / fetchSumNum()}%，" +
                            "未完成占比${100 * currentNum / fetchSumNum()}%, " +
                            "完成占比${100 * normalOfflineNum / fetchSumNum()}%，" +
                            "用时：${(runEndTime.time - addRobotStartTime.time) / 60000}分钟 " +
                            "${((runEndTime.time - addRobotStartTime.time) % 60000) / 1000}"
                }

                break
            }

        } while (true)
    }

    val statusFormat = "%14s %40d"

    private fun showStatus() {
        val now = Instant.now()
        if (now.isAfter(nextCheckTime)) {
//            logger.lzInfo { String.format(statusFormat, "已经创建：", pltRobotCreatedNum) }

            nextCheckTime = nextCheckTime.plusSeconds(5)
        }

    }
}