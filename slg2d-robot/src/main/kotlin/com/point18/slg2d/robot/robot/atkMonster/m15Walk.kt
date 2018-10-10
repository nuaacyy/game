package com.point18.slg2d.robot.robot.atkMonster

import com.point18.slg2d.common.netmsg.MsgType
import xyz.ariane.util.lzDebug
import com.point18.slg2d.common.commonfunc.normalLog
import pb4client.Walk
import pb4client.WalkRt
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.robot.robot.AiLeafBase
import com.point18.slg2d.robot.robotData.*
import com.point18.slg2d.robot.robotruntime.RobotPropChg

class AiWalk : AiLeafBase() {

    override fun reset(): RobotAction {
        return AiWalk()
    }

    override fun actionDesc(): String {
        return "AiWalk - 行军"
    }

    override fun update(robot: Robot): ActionResult {
        println("行军....")
        normalLog.lzDebug { "update:${this.javaClass.name}:robot[${robot.name}] - 行军..." }
        val monsters = robot.thisRobotData.monster

        /*val random = Random()
        val randomNum = random.nextInt(monsters.size)*/

        val castleInfo = robot.thisRobotData.castleData.castles
        var castleX = 100
        var castleY = 100
        if(castleInfo != null){
            castleX = castleInfo.x
            castleY = castleInfo.y
        }
        var minRange = 1000000000000000000
        var aimsX = 0
        var aimsY = 0
        for(monster in monsters){
            val a = Math.abs(castleX - monster.x).toDouble()
            val b = Math.abs(castleY - monster.y).toDouble()
            val c = Math.sqrt(a*a + b*b)
            if(c<minRange){
                minRange = c.toLong()
                aimsX = monster.x
                aimsY = monster.y
            }
        }


        val walk = Walk.newBuilder()
        walk.runType = 1
        walk.runTargetType = 1
        walk.aimsX = aimsX
        walk.aimsY = aimsY
        walk.addHeros(robot.thisRobotData.heroIdList[0])
        walk.solidersList
        robot.thisRobotData.sendMsg(MsgType.Walk_15, walk.build())
        return com.point18.slg2d.robot.robotData.RUNNING
    }

    override fun msgTrigger(robot: Robot, chg: RobotPropChg): ActionResult {
        if (chg.msgNo != MsgType.Walk_15) {
            normalLog.lzDebug {
                "msgTrigger:${this.javaClass.name}:robot[${robot.name}] " +
                        "- 接收的消息类型不匹配${MsgType.Walk_15}"
            }
            return com.point18.slg2d.robot.robotData.RUNNING
        }

        val walkRt = chg.convert<WalkRt>() ?: return com.point18.slg2d.robot.robotData.RUNNING
        val rt = walkRt.rt
        if (rt != ResultCode.SUCCESS.code) {
            normalLog.lzDebug {
                "msgTrigger:${this.javaClass.name}:robot[${robot.name}] " +
                        "- 行军失败：${com.point18.slg2d.robot.robotData.getErrMsg(rt)}"
            }
            return com.point18.slg2d.robot.robotData.FAILED
        }
        println("行军完成....")

        normalLog.lzDebug { "msgTrigger:${this.javaClass.name}:robot[${robot.name}] - 行军完成" }
        return SUCCESS
    }

}