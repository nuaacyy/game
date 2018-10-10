package com.point18.slg2d.robot.robot.atkMonster

import com.point18.slg2d.common.netmsg.MsgType
import xyz.ariane.util.lzDebug
import xyz.ariane.util.lzWarn
import com.point18.slg2d.common.commonfunc.normalLog
import pb4client.AllianceCreate
import pb4client.AllianceCreateRt
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.robot.robot.AiLeafBase
import com.point18.slg2d.robot.robotData.*
import com.point18.slg2d.robot.robotruntime.RobotPropChg

class AiCreateAlliance : AiLeafBase() {

    override fun reset(): RobotAction {
        return AiCreateAlliance()
    }

    override fun actionDesc(): String {
        return "AiCreateAlliance - 创建联盟"
    }

    override fun update(robot: Robot): ActionResult {
        println("创建联盟....")
        normalLog.lzDebug { "update:${this.javaClass.name}:robot[${robot.name}] - 创建联盟..." }
        val allianceCreate = AllianceCreate.newBuilder()
        // 参数设置
        var shortNameStr = ""
        val robotId = robot.id
        if(robotId >= 0 && robotId < 10){
            shortNameStr = "00" + robotId.toString()
        } else if(robotId >= 10 && robotId < 100) {
            shortNameStr = "0" + robotId.toString()
        } else {
            shortNameStr = robotId.toString()
        }
        allianceCreate.allianceName = "name" + robot.id.toString()
        allianceCreate.allianceShortName = shortNameStr
        allianceCreate.allianceLan = 1

        robot.thisRobotData.sendMsg(MsgType.AllianceCreate_802, allianceCreate.build())
        return com.point18.slg2d.robot.robotData.RUNNING
    }

    override fun msgTrigger(robot: Robot, chg: RobotPropChg): ActionResult {
        if (chg.msgNo != MsgType.AllianceCreate_802) {
            normalLog.lzWarn {
                "msgTrigger:${this.javaClass.name}:robot[${robot.name}] " +
                        "- 接收的消息类型不匹配${MsgType.AllianceCreate_802}"
            }
            return com.point18.slg2d.robot.robotData.RUNNING
        }

        val allianceCreateRt = chg.convert<AllianceCreateRt>() ?: return com.point18.slg2d.robot.robotData.RUNNING
        val rt = allianceCreateRt.rt
        if (rt != ResultCode.SUCCESS.code) {
            if(rt == ResultCode.ALLIANCE_PLAYER_HAS_ALLIANCE.code){
                println("该玩家已加入联盟....下一步...")
                return SUCCESS
            }
            normalLog.lzWarn {
                "msgTrigger:${this.javaClass.name}:robot[${robot.name}] " +
                        "- 创建联盟失败：${com.point18.slg2d.robot.robotData.getErrMsg(rt)}  错误码： $rt"
            }
            return com.point18.slg2d.robot.robotData.FAILED
        }
        println("创建联盟完成....")
        normalLog.lzDebug { "msgTrigger:${this.javaClass.name}:robot[${robot.name}] - 创建联盟完成" }
        return SUCCESS
    }

}