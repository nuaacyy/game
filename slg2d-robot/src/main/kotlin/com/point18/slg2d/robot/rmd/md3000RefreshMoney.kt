package com.point18.slg2d.robot.rmd

import pb4client.RefreshMoney

fun md3000RefreshMoney(robot: com.point18.slg2d.robot.robotData.Robot, chg: com.point18.slg2d.robot.robotruntime.RobotPropChg): Boolean {
	val refreshMoney = chg.convert<RefreshMoney>()
    if (refreshMoney == null) {
        return false
    }
    val res = refreshMoney.res
    robot.thisRobotData.playerData.updatePlayerRes(res)

    return true
}
