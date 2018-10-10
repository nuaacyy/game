package com.point18.slg2d.robot.rmd

import pb4client.EnterGameHomeRt

fun md3200EnterGameHomeRt(robot: com.point18.slg2d.robot.robotData.Robot, chg: com.point18.slg2d.robot.robotruntime.RobotPropChg): Boolean {
    val msg = chg.convert<EnterGameHomeRt>() ?: return false

    // 内城建筑信息获取
    for (eachInnerCity in msg.innerCityInfoList){
        robot.thisRobotData.innerBuildingData.addInnerBuilding(eachInnerCity)
    }

    return true
}