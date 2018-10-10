package com.point18.slg2d.robot.robotData

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.C2SMsg
import com.point18.slg2d.common.netmsg.MsgType
import io.netty.channel.Channel
import java.util.*

class RobotData {  //每个机器人的数据
    var channel: Channel? = null  //  robot链接服务器完成后获得一个Channel 以后该机器人发消息都是通过这个Channel
    var sendNo: Int = 1  //  该机器人发送的第sendNo个消息
    var logined: Boolean = false  //是否登陆成功
    var hasPlayer: Boolean = false // 是否有角色
    var enterGame: Boolean = false // 是否已经进入游戏了
    var serverTime: Long = 0 // 服务器时间
    var isArmyPlan: Int = 0 // 是否已有布阵英雄 0：没有，1：有
    var isSourceLack: Boolean = false // 是否缺少资源

    // 玩家各个模块的数据
    val playerData: RDataPlayer = RDataPlayer()        // 玩家信息
    val walkGroupData: RDataWalkGroup = RDataWalkGroup()  // 行军信息
    val massGroupData: RDataMassGroup = RDataMassGroup()   // 集结信息
    val castleData: RDataCastle = RDataCastle()     // 城池信息
    val innerBuildingData: RDataInnerBuilding = RDataInnerBuilding()// 内城建筑信息
    val heroIdList = LinkedList<Long>()         // 武将id列表
    val monster = LinkedList<MonstersData>()   // 魔物地址信息

    val alreadyRunCountMap: MutableMap<MsgType, Int> = mutableMapOf() // 每个msgType在机器人已经运行次数

    init {

    }

    // 发送积压的消息
    fun sendMsg(msgNo: MsgType, gnetMsg: MessageLite) {
        // 发送消息
        val ch = this.channel  // 获取这个机器人连接服务器后获得的channel
        if (ch != null) {    //  如果channel不为空 证明是有链接的
            val c2sMsg = C2SMsg(msgNo)  //  构造发送的消息MsgType
            c2sMsg.clientMsgNo = this.sendNo        //  发送的消息包含this.sendNo
            this.sendNo++            //  自加
            c2sMsg.msgBody = gnetMsg    //  消息的重点内容MessageLite
            ch.writeAndFlush(c2sMsg)    //  准备好了，要传到编码器进行编码，从channel发送出去
        }
    }

}

class MonstersData(
    var x: Int,
    var y: Int
)

