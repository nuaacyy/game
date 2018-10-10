package com.point18.slg2d.robot.robot.innerBuilding

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.constg.LOCK
import com.point18.slg2d.common.constg.Lianjinzhe
import com.point18.slg2d.common.constg.UNLOCK_BY_BUILDING_LV
import pb4client.UnlockInnerCity
import pb4client.UnlockInnerCityRt
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.robot.robot.AiLeafBase
import com.point18.slg2d.robot.robotData.*
import com.point18.slg2d.robot.robotruntime.RobotPropChg
import java.util.*

class AiUnlockInnerCity : AiLeafBase() {

    override fun actionDesc(): String {
        return "AiUnlockInnerCity - 解锁内城建筑"
    }

    override fun update(robot: Robot): ActionResult {

        val buildings = robot.thisRobotData.innerBuildingData.innerBuildings.index.values
        val unlockBuildings = buildings
            .filter { it.type != Lianjinzhe && it.state == LOCK && it.lv < 1 }

        val canUpdates = LinkedList<InnerBuildingData>()

        var failedUpdateNum = 0
        for (eachBuilding in unlockBuildings) {

            // 找模板
            val buildingDataProto = pcs.innerBuildingDataCache.fetchProtoByTypeLv(eachBuilding.type, eachBuilding.lv + 1)
            if (buildingDataProto == null) {
                println("找模板 failedUpdateNum = ${++failedUpdateNum},unlockBuildings size = ${unlockBuildings.size}")
                continue
            }

            // 检测升级条件
            val check = unlockCondition(robot, buildings, buildingDataProto.unLockMap)
            if (check != ResultCode.SUCCESS) {
                println("检测升级条件 failedUpdateNum = ${++failedUpdateNum},unlockBuildings size = ${unlockBuildings.size}")
                continue
            }

            canUpdates.add(eachBuilding)

        }

        // 在可以升级的建筑中拿一个升级,没有可以升级的建筑就返回failed
        if (canUpdates.isEmpty()) {
            return FAILED
        } else {
            val updateBuilding = canUpdates.first()
            val unlockInnerCity = UnlockInnerCity.newBuilder()
            unlockInnerCity.innerCityId = updateBuilding.id
            unlockInnerCity.cityId = 1
            robot.thisRobotData.sendMsg(MsgType.UnlockInnerCity_51, unlockInnerCity.build())
        }

        return RUNNING
    }

    override fun msgTrigger(robot: Robot, chg: RobotPropChg): ActionResult {
        if (chg.msgNo != MsgType.UnlockInnerCity_51) {
            return com.point18.slg2d.robot.robotData.RUNNING
        }

        val unlockInnerCityRt = chg.convert<UnlockInnerCityRt>() ?: return com.point18.slg2d.robot.robotData.RUNNING
        val rt = unlockInnerCityRt.rt
        // 缺资源可以补资源，不算失败
        if (rt != ResultCode.SUCCESS.code && rt != ResultCode.LESS_RESOUCE.code) {
            return com.point18.slg2d.robot.robotData.FAILED
        }

        if (rt == ResultCode.LESS_RESOUCE.code) {
            println("解锁建筑资源不足~！")
            robot.thisRobotData.isSourceLack = true
        } else {
            robot.thisRobotData.isSourceLack = false
            println("解锁建筑成功~!")
        }

        return SUCCESS
    }

    override fun reset(): RobotAction {
        return AiUnlockInnerCity()
    }

    private fun unlockCondition(robot: Robot, cityInfos: Collection<InnerBuildingData>, unlockMap: Map<Int, Map<Int, Int>>): ResultCode {
        for ((unlockType, subMap) in unlockMap) {
            if (unlockType == UNLOCK_BY_BUILDING_LV) {
                for ((type, level) in subMap) {
                    val innerCityInfos = cityInfos.filter { it.type == type }

                    var canUnlock = false
                    for (innerCityInfo in innerCityInfos) {
                        if (innerCityInfo.lv >= level) {
                            canUnlock = true
                            break
                        }
                    }

                    if (!canUnlock) {
                        return ResultCode.INNER_CITY_LOCK
                    }
                }
            }
        }
        return ResultCode.SUCCESS
    }

}