package com.point18.slg2d.robot.rmd

import pb4client.AchievementChange

fun mdAchivementChange(robot: com.point18.slg2d.robot.robotData.Robot, chg: com.point18.slg2d.robot.robotruntime.RobotPropChg): Boolean {
	val achievementChange = chg.convert<AchievementChange>()
	if (achievementChange == null) {
		return false
	}
	val achievement = achievementChange.achievement
	achievement.id
	return true
}


