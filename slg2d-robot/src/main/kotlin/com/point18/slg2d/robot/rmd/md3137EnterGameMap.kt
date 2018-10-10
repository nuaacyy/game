package com.point18.slg2d.robot.rmd

import pb4client.EnterGameMapRt

fun mdEnterGameMap(robot: com.point18.slg2d.robot.robotData.Robot, chg: com.point18.slg2d.robot.robotruntime.RobotPropChg): Boolean {

    val enterGameMap = chg.convert<EnterGameMapRt>()
    if (enterGameMap == null) {
        return false
    }
    val myWalks = enterGameMap.myWalksList
    val warnWalks = enterGameMap.warnWalksList
    val massGroups = enterGameMap.massWalksList

    for (myWalk in myWalks) {
        robot.thisRobotData.walkGroupData.addMyWalkGroup(myWalk)
    }

    for (warnWalk in warnWalks) {
        robot.thisRobotData.walkGroupData.addWarnWalkGroup(warnWalk)
    }

    for (massGroup in massGroups) {
        robot.thisRobotData.massGroupData.addMassGroup(massGroup)
    }


    return true
}