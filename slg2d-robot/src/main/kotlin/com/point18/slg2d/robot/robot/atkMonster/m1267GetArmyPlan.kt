package com.point18.slg2d.robot.robot.atkMonster

import com.point18.slg2d.common.netmsg.MsgType
import xyz.ariane.util.lzDebug
import com.point18.slg2d.common.commonfunc.normalLog
import pb4client.GetArmyPlan
import pb4client.GetArmyPlanRt
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.robot.robot.AiLeafBase
import com.point18.slg2d.robot.robotData.*
import com.point18.slg2d.robot.robotruntime.RobotPropChg

class AiGetArmyPlan : AiLeafBase() {

    override fun reset(): RobotAction {
        return AiGetArmyPlan()
    }

    override fun actionDesc(): String {
        return "AiGetArmyPlan - 英雄战预设部队"
    }

    override fun update(robot: Robot): ActionResult {
        println("获取英雄战预设部队....")
        normalLog.lzDebug { "update:${this.javaClass.name}:robot[${robot.name}] - 英雄战预设部队..." }
        val getArmyPlan = GetArmyPlan.newBuilder()
        // 参数设置
        getArmyPlan.bigTarget = 3
        getArmyPlan.smallTarget = 1

        robot.thisRobotData.sendMsg(MsgType.GetArmyPlan_1267, getArmyPlan.build())
        return com.point18.slg2d.robot.robotData.RUNNING
    }

    override fun msgTrigger(robot: Robot, chg: RobotPropChg): ActionResult {
        if (chg.msgNo != MsgType.GetArmyPlan_1267) {
            normalLog.lzDebug {
                "msgTrigger:${this.javaClass.name}:robot[${robot.name}] " +
                        "- 接收的消息类型不匹配${MsgType.GetArmyPlan_1267}"
            }
            return com.point18.slg2d.robot.robotData.RUNNING
        }

        val getArmyPlanRt = chg.convert<GetArmyPlanRt>() ?: return com.point18.slg2d.robot.robotData.RUNNING

        val rt = getArmyPlanRt.rt
        if (rt != ResultCode.SUCCESS.code) {
            normalLog.lzDebug {
                "msgTrigger:${this.javaClass.name}:robot[${robot.name}] " +
                        "- 英雄战预设部队失败：${com.point18.slg2d.robot.robotData.getErrMsg(rt)}"
            }
            return com.point18.slg2d.robot.robotData.FAILED
        }

        println("获取英雄战预设部队完成....")

        val heroList = getArmyPlanRt.heroInfoList
        if (heroList.size > 0) {
            robot.thisRobotData.isArmyPlan = 1
            // return robotData.FAILED
        }

        normalLog.lzDebug { "msgTrigger:${this.javaClass.name}:robot[${robot.name}] - 英雄战预设部队完成" }
        return com.point18.slg2d.robot.robotData.SUCCESS
    }

}