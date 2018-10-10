package com.point18.slg2d.robot.rmd

import xyz.ariane.util.lzInfo
import xyz.ariane.util.lzWarn
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.robot.robotData.Robot
import com.point18.slg2d.robot.robotData.offlineNum
import com.point18.slg2d.robot.robotData.robotsDealLock
import com.point18.slg2d.robot.robotruntime.RobotPropChg

// 这是服务器要客户端下线的msgDeal，这属于正常下线还是异常下线？？？？？？？？？？？？？？？？？？？？？？
fun mdCloseConnection(robot: Robot, chg: RobotPropChg): Boolean {

    if (robot.disconnectFailed) {
        synchronized(robotsDealLock) {
            offlineNum += 1  //  记录下线的机器人数量
        }
        robot.closedConnection = true
        if (robot.requestCloseConnection) {
            normalLog.lzInfo{"ai主动请求下线  ${robot.name}"}
            robot.normalOffLine = true
        } else {
            normalLog.lzWarn{"不是ai主动请求下线，连接断开  ${robot.name}"}
            robot.failed = true
        }
//        robot.failed = true
    }
    return false
}