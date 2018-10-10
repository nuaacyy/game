package com.point18.slg2d.robot.robot.gm

import com.point18.slg2d.common.netmsg.MsgType
import xyz.ariane.util.lzDebug
import com.point18.slg2d.common.commonfunc.normalLog
import pb4client.SendChat
import pb4client.SendChatRt
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.robot.robot.AiLeafBase
import com.point18.slg2d.robot.robotData.ActionResult
import com.point18.slg2d.robot.robotData.Robot
import com.point18.slg2d.robot.robotData.RobotAction
import com.point18.slg2d.robot.robotData.SUCCESS
import com.point18.slg2d.robot.robotruntime.RobotPropChg

const val ADD_GOLD = "-gm add gold 100000000" // 压力测试用，添加各种资源
const val ADD_FOOD = "-gm add food 100000000"
const val ADD_STONE = "-gm add stone 100000000"
const val ADD_WOOD = "-gm add wood 100000000"
const val ADD_IRON = "-gm add iron 100000000"
const val ADD_COIN = "-gm add coin 100000000"
const val ADD_GOLDCOIN = "-gm add goldCoin 100000000"
const val ADD_SILVERCOIN = "-gm add silverCoin 100000000"
const val ADD_DECREE = "-gm add decree 100000000"
const val UPGRADE_CITY = "-gm allBuildingToTopLv"  // 升级所有

// gm命令添加资源
class AiChatGm(private var message: String) : AiLeafBase() {
    override fun actionDesc(): String {
        return "AiChatGm - Chat GM命令"
    }

    override fun reset(): RobotAction {
        return AiChatGm(this.message)
    }

    override fun msgTrigger(robot: Robot, chg: RobotPropChg): ActionResult {

        if (chg.msgNo != MsgType.Chat_300) {
            normalLog.lzDebug { "msgTrigger:${this.javaClass.name}:robot[${robot.name}] - 接收的消息类型不匹配${MsgType.Chat_300}" }
            return com.point18.slg2d.robot.robotData.RUNNING
        }

        val sendChatRt = chg.convert<SendChatRt>()
            ?: return com.point18.slg2d.robot.robotData.RUNNING

        val rt = sendChatRt.rt
        if (rt != ResultCode.SUCCESS.code) {
            normalLog.lzDebug {
                "msgTrigger:${this.javaClass.name}:robot[${robot.name}] - GM命令失败：${com.point18.slg2d.robot.robotData.getErrMsg(
                    rt
                )}"
            }
            return com.point18.slg2d.robot.robotData.FAILED
        }

        normalLog.lzDebug { "msgTrigger:${this.javaClass.name}:robot[${robot.name}] - GM命令完成" }

        return SUCCESS
    }

    override fun update(robot: Robot): ActionResult {
        normalLog.lzDebug { "update:${this.javaClass.name}:robot[${robot.name}] - 发送GM命令..." }

        // 如果是加资源，但是又不缺资源，就当作 SUCCESS 返回
        if (
            (this.message == ADD_GOLD ||
                this.message == ADD_FOOD ||
                this.message == ADD_STONE ||
                this.message == ADD_WOOD ||
                this.message == ADD_IRON ||
                this.message == ADD_COIN ||
                this.message == ADD_GOLDCOIN ||
                this.message == ADD_SILVERCOIN ||
                this.message == ADD_DECREE)
        ) {
            if (!robot.thisRobotData.isSourceLack){
                return SUCCESS
            }
        }

        val sendChat = SendChat.newBuilder()

        // 300 现在只是GM命令使用 所以这个三个可以填大于0就可以了
        sendChat.type = 1
        sendChat.playerId = 1
        sendChat.messageType = 1

        sendChat.message = this.message
        robot.thisRobotData.sendMsg(MsgType.Chat_300, sendChat.build())

        return com.point18.slg2d.robot.robotData.RUNNING
    }

}









