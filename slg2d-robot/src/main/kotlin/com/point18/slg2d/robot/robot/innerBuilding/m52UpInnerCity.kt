package com.point18.slg2d.robot.robot.innerBuilding

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.robot.robot.AiLeafBase
import com.point18.slg2d.robot.robotData.*
import com.point18.slg2d.robot.robotruntime.RobotPropChg
import pb4client.UpInnerCity
import pb4client.UpInnerCityRt

class AiUpInnerBuilding : AiLeafBase() {

    override fun reset(): RobotAction {
        return AiUpInnerBuilding()
    }

    override fun actionDesc(): String {
        return "AiUpInnerBuilding - 升级内城建筑"
    }

    override fun msgTrigger(robot: Robot, chg: RobotPropChg): ActionResult {

        if (chg.msgNo != MsgType.UpInnerCity_52) {
            return RUNNING
        }

        val upInnerCityRt = chg.convert<UpInnerCityRt>()
            ?: return FAILED

        val rt = upInnerCityRt.rt
        if (rt != ResultCode.SUCCESS.code && rt != ResultCode.LESS_RESOUCE.code) {
            return FAILED
        }

        if (rt == ResultCode.LESS_RESOUCE.code) {
            println("升级建筑资源不足~！")
            robot.thisRobotData.isSourceLack = true
        } else {
            robot.thisRobotData.isSourceLack = false
            println("升级建筑成功~!")
        }
        return com.point18.slg2d.robot.robotData.SUCCESS
    }

    override fun update(robot: Robot): ActionResult {
        // 找到主城
        val mainCastle = robot.thisRobotData.castleData.castles

        // 找到可造建筑或者
        var upgradeBuildingId = 0L

        val buildings = robot.thisRobotData.innerBuildingData.findStableBuildings()
        for (building in buildings) {
            val buildingType = building.type
            val buildingLv = building.lv
            val specTypeBuildingProto =
                pcs.innerBuildingDataCache.fetchProtoByTypeLv(buildingType, buildingLv + 1) ?: continue // 满级了！

            // 判断升级条件
            var canBuild = true
            BUILD_CHECK@ for ((checkType, checkParams) in specTypeBuildingProto.unLockMap) {
                if (checkType == UNLOCK_BY_BUILDING_LV) {
                    for ((checkBuilding, lv) in checkParams) {
                        val innerBuildings = robot.thisRobotData.innerBuildingData.findSpecTypeBuildings(checkBuilding)
                        if (innerBuildings.isEmpty()) {
                            canBuild = false
                            break@BUILD_CHECK
                        }

                        var lvOk = false
                        for (eachBuilding in innerBuildings) {
                            if ((eachBuilding.state == STABLE && eachBuilding.lv >= lv) ||
                                (eachBuilding.state == UPGRADE && eachBuilding.lv > lv)
                            ) {
                                // 找到等级满足的
                                lvOk = true
                                break
                            }
                        }

                        if (!lvOk) {
                            canBuild = false
                            break@BUILD_CHECK
                        }
                    }
                }
            }

            if (!canBuild) {
                continue
            }

            upgradeBuildingId = building.id
            break
        }

        // 没有可以升级的建筑 就failed
        if (upgradeBuildingId == 0L) {
            return FAILED
        }

        val buildingUp = robot.thisRobotData.innerBuildingData.innerBuildings.findByKey(upgradeBuildingId)
            ?: return FAILED

        val buildingUpProto = pcs.innerBuildingDataCache.fetchProtoByTypeLv(buildingUp.type, buildingUp.lv + 1)
            ?: return FAILED

        val upInnerCity = UpInnerCity.newBuilder()
        upInnerCity.cityId = mainCastle.id
        upInnerCity.innerCityId = upgradeBuildingId

        // 要道具的就不要普通升级，直接刷上去
        if (buildingUpProto.levelUpConsumeRes.any { it.resType == RES_PROPS }) {
            upInnerCity.lvUpType = RESEARCH_LVUP_RMB   // 普通升级- RESEARCH_LVUP_NORMAL  立即升级-RESEARCH_LVUP_RMB
        } else {
            upInnerCity.lvUpType = RESEARCH_LVUP_NORMAL   // 普通升级- RESEARCH_LVUP_NORMAL  立即升级-RESEARCH_LVUP_RMB
        }

        robot.thisRobotData.sendMsg(MsgType.UpInnerCity_52, upInnerCity.build())

        return com.point18.slg2d.robot.robotData.RUNNING
    }
}