package com.point18.slg2d.robot.robot.realm

import com.point18.slg2d.robot.robotData.*
import com.point18.slg2d.robot.robotruntime.RobotPropChg

var sumNum = 0//合计登录次数

class AiOfflineAndOnline : com.point18.slg2d.robot.robot.AiLeafBase() {
    override fun reset(): RobotAction {
        return AiOfflineAndOnline()
    }

    override fun actionDesc(): String {
        return "AiOfflineAndOnline - 下线并清理机器人缓存"
    }

    override fun msgTrigger(robot: Robot, chg: RobotPropChg): ActionResult {
        return SUCCESS
    }

    override fun update(robot: Robot): ActionResult {
        val tmp = robot.thisRobotData.channel
        if (tmp != null) {
            tmp.close()  // 让机器人连接关闭
        } else {
            return FAILED
        }

        // 重置机器人数据
        robot.thisRobotData = RobotData()

        sumNum++
        if (sumNum > 200000) {
            robot.normalOffLine = true
        }
        return SUCCESS
    }
}