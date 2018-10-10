package com.point18.slg2d.robot.rmd

import pb4client.DecreeChange

fun mdDecreeChange(robot: com.point18.slg2d.robot.robotData.Robot, chg: com.point18.slg2d.robot.robotruntime.RobotPropChg): Boolean {
    val decreeChange = chg.convert<DecreeChange>()
    if (decreeChange == null) {
        return false
    }
    val decreeNum = decreeChange.decreeNum
    val decreeLimit = decreeChange.decreeLimit
    val decreeTime = decreeChange.time

    robot.thisRobotData.playerData.decreeNum = (decreeNum)
    robot.thisRobotData.playerData.decreeLimit = (decreeLimit)
    robot.thisRobotData.playerData.decreeTime = (decreeTime).toLong()

    return true
}