package com.point18.slg2d.robot.rmd

import pb4client.VipChange

fun mdVipChange(robot: com.point18.slg2d.robot.robotData.Robot, chg: com.point18.slg2d.robot.robotruntime.RobotPropChg): Boolean {

    val vipChange = chg.convert<VipChange>()
    if (vipChange == null) {
        return false
    }
    vipChange.vipLv
    vipChange.vipExp

    return true
}
