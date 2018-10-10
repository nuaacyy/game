package com.point18.slg2d.robot.robot.atkMonster

import com.point18.slg2d.common.netmsg.MsgType
import xyz.ariane.util.lzDebug
import com.point18.slg2d.common.commonfunc.normalLog
import pb4client.HeroPos
import pb4client.SetArmyPlan
import pb4client.SetArmyPlanRt
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.robot.robot.AiLeafBase
import com.point18.slg2d.robot.robotData.*
import com.point18.slg2d.robot.robotruntime.RobotPropChg

class AiSetArmyPlan : AiLeafBase() {

    override fun reset(): RobotAction {
        return AiSetArmyPlan()
    }

    override fun actionDesc(): String {
        return "AiSetArmyPlan - 英雄战预设部队"
    }

    override fun update(robot: Robot): ActionResult {
        if(robot.thisRobotData.isArmyPlan == 0) {
            println("设置英雄战预设部队....")
            normalLog.lzDebug { "update:${this.javaClass.name}:robot[${robot.name}] - 设置英雄战预设部队..." }
            val setArmyPlan = SetArmyPlan.newBuilder()
            // 参数设置
            setArmyPlan.bigTarget = 3
            setArmyPlan.smallTarget = 1

            val heroInfo = HeroPos.newBuilder()
            heroInfo.pos = 5
            heroInfo.heroId = robot.thisRobotData.heroIdList[0]
            setArmyPlan.addHeroInfo(heroInfo.build())

            robot.thisRobotData.sendMsg(MsgType.SetArmyPlan_1266, setArmyPlan.build())
            return com.point18.slg2d.robot.robotData.RUNNING
        }else{
            return com.point18.slg2d.robot.robotData.SUCCESS
        }
    }

    override fun msgTrigger(robot: Robot, chg: RobotPropChg): ActionResult {
        if (chg.msgNo != MsgType.SetArmyPlan_1266) {
            normalLog.lzDebug {
                "msgTrigger:${this.javaClass.name}:robot[${robot.name}] " +
                        "- 接收的消息类型不匹配${MsgType.SetArmyPlan_1266}"
            }
            return com.point18.slg2d.robot.robotData.RUNNING
        }

        val setArmyPlanRt = chg.convert<SetArmyPlanRt>() ?: return com.point18.slg2d.robot.robotData.RUNNING

        val rt = setArmyPlanRt.rt
        if (rt != ResultCode.SUCCESS.code) {
            normalLog.lzDebug {
                "msgTrigger:${this.javaClass.name}:robot[${robot.name}] " +
                        "- 英雄战预设部队失败：${com.point18.slg2d.robot.robotData.getErrMsg(rt)}"
            }
            return com.point18.slg2d.robot.robotData.FAILED
        }
        println("设置英雄战预设部队完成....")
        normalLog.lzDebug { "msgTrigger:${this.javaClass.name}:robot[${robot.name}] - 英雄战预设部队完成" }
        return com.point18.slg2d.robot.robotData.SUCCESS
    }
}