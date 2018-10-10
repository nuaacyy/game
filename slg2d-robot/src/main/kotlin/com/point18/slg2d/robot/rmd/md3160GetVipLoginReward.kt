package com.point18.slg2d.robot.rmd

import pb4client.GetVipLoginReward

fun mdGetVipLoginReward(robot: com.point18.slg2d.robot.robotData.Robot, chg: com.point18.slg2d.robot.robotruntime.RobotPropChg): Boolean {

	val getVipLoginReward = chg.convert<GetVipLoginReward>()
	if (getVipLoginReward == null) {
		return false
	}
	val continueOnlineDay = getVipLoginReward.continueOnlineDay
    //rewardInfo := getVipLoginReward.GetRewardInfo()

    robot.thisRobotData.playerData.continueOnlineDay = (continueOnlineDay)

    return true
}

