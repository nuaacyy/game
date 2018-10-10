package com.point18.slg2d.robot.robot.realm

import xyz.ariane.util.lzInfo
import xyz.ariane.util.lzWarn
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.robot.robot.AiLeafBase
import com.point18.slg2d.robot.robotData.*
import com.point18.slg2d.robot.robotruntime.RobotPropChg

class AiOffline : AiLeafBase() {
    override fun reset(): RobotAction {
        return AiOffline()
    }

    override fun actionDesc(): String {
        return "AiOffline - 下线"
    }

    override fun msgTrigger(robot: Robot, chg: RobotPropChg): ActionResult {
        return RUNNING
    }

    override fun update(robot: Robot): ActionResult {
        normalLog.lzInfo { "update:${this.javaClass.name}:robot[${robot.name}] - 机器人尝试下线" }

//		robot.failed = true
//        robot.normalOffLine = true
//        synchronized(robotsDealLock) {
//            offlineNum += 1  //  记录下线的机器人数量
//        }
//        robotData.subRobot(robot.robot)  // 下线后对当前在线机器人数目减一操作

        // 断开连接
        val tmpChannel = robot.thisRobotData.channel
        if (tmpChannel != null) {
            robot.requestCloseConnection = true  // 请求关闭连接标记位
            tmpChannel.close()  // 关闭连接
        } else {
            normalLog.lzWarn { "robot：${robot.name} 尝试进行机器人下线 失败，channel == null" }
        }

        return SUCCESS
    }
}