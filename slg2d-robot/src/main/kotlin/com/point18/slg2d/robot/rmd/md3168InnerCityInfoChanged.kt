package com.point18.slg2d.robot.rmd

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.BUILDING_INNER_CITY
import com.point18.slg2d.common.constg.DELETE_INNER_CITY
import pb4client.InnerCityInfoChanged

fun mdInnerCityInfoChanged(robot: com.point18.slg2d.robot.robotData.Robot, chg: com.point18.slg2d.robot.robotruntime.RobotPropChg): Boolean {

	val innerCityInfoChanged = chg.convert<InnerCityInfoChanged>()
	if (innerCityInfoChanged == null) {
		return false
	}

	val op = innerCityInfoChanged.op
	val innerCityInfo = innerCityInfoChanged.innerCityInfo

	val innerBuildingId = (innerCityInfo.innerCityId)
    val innerBuilding = robot.thisRobotData.innerBuildingData.findSpecBuildingById(innerBuildingId)

	if (op == BUILDING_INNER_CITY) {
		if (innerBuilding != null) {
			normalLog.error("收到建筑变更 新增 时，发现目标建筑 已经 存在")
			return false
		}

        robot.thisRobotData.innerBuildingData.addInnerBuilding(innerCityInfo)

	} else if (op == DELETE_INNER_CITY) {
		if (innerBuilding == null) {
			normalLog.error("收到建筑变更 拆除 时，发现目标建筑 不 存在")
            return false
        }

        // 删除建筑
        robot.thisRobotData.innerBuildingData.deleteInnerBuilding(innerBuildingId)

    } else {
		if (innerBuilding == null) {
			normalLog.error("收到建筑变更 变化 时，发现目标建筑 不 存在")
            return false
        }

        // 更新建筑
        robot.thisRobotData.innerBuildingData.updateInnerBuilding(innerBuilding, innerCityInfo)
    }


	return true
}