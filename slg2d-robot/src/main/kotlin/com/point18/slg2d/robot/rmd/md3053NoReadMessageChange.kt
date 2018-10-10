package com.point18.slg2d.robot.rmd

import pb4client.NoReadMessageChange


fun mdNoReadMessageChange(robot: com.point18.slg2d.robot.robotData.Robot, chg: com.point18.slg2d.robot.robotruntime.RobotPropChg): Boolean {
    val noReadMessageChange = chg.convert<NoReadMessageChange>()
    if (noReadMessageChange == null) {
        return false
    }
    noReadMessageChange.privateChat
    noReadMessageChange.groupChat
    noReadMessageChange.systemMailNum

    return true
}