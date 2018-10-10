package com.point18.slg2d.robot.robot.atkMonster

import com.point18.slg2d.common.netmsg.MsgType
import xyz.ariane.util.lzDebug
import com.point18.slg2d.common.commonfunc.normalLog
import pb4client.BuyAndUseProp
import pb4client.BuyAndUsePropRt
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.robot.robot.AiLeafBase
import com.point18.slg2d.robot.robotData.*
import com.point18.slg2d.robot.robotruntime.RobotPropChg

class AiBuyAndUseProp : AiLeafBase() {

    override fun reset(): RobotAction {
        return AiBuyAndUseProp()
    }

    override fun actionDesc(): String {
        return "AiBuyAndUseProp - 购买行动力"
    }

    override fun update(robot: Robot): ActionResult {
        println("购买行动力....")
        normalLog.lzDebug { "update:${this.javaClass.name}:robot[${robot.name}] - 购买行动力..." }
        val buyAndUseProp = BuyAndUseProp.newBuilder()
        // 参数设置
        buyAndUseProp.usePropId = 10807
        buyAndUseProp.usePropNum = 1

        robot.thisRobotData.sendMsg(MsgType.BuyAndUseProp_1064, buyAndUseProp.build())
        return com.point18.slg2d.robot.robotData.RUNNING
    }

    override fun msgTrigger(robot: Robot, chg: RobotPropChg): ActionResult {
        if (chg.msgNo != MsgType.BuyAndUseProp_1064) {
            normalLog.lzDebug {
                "msgTrigger:${this.javaClass.name}:robot[${robot.name}] " +
                        "- 接收的消息类型不匹配${MsgType.BuyAndUseProp_1064}"
            }
            return com.point18.slg2d.robot.robotData.RUNNING
        }

        val buyAndUsePropRt = chg.convert<BuyAndUsePropRt>() ?: return com.point18.slg2d.robot.robotData.RUNNING

        val rt = buyAndUsePropRt.rt
        if (rt != ResultCode.SUCCESS.code) {
            if(rt == ResultCode.PARAMETER_ERROR.code){
                println("已拥有该道具.....")
            }
            normalLog.lzDebug {
                "msgTrigger:${this.javaClass.name}:robot[${robot.name}] " +
                        "- 购买行动力失败：${com.point18.slg2d.robot.robotData.getErrMsg(rt)}"
            }
            return com.point18.slg2d.robot.robotData.FAILED
        }
        println("购买行动力完成....")
        normalLog.lzDebug { "msgTrigger:${this.javaClass.name}:robot[${robot.name}] - 购买行动力完成" }
        return com.point18.slg2d.robot.robotData.FAILED
    }
}