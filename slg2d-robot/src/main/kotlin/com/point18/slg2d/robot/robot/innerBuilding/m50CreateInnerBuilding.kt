package com.point18.slg2d.robot.robot.innerBuilding

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.InnerBuildingLocationProto
import com.point18.slg2d.common.pc.InnerBuildingProto
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.robot.robot.AiLeafBase
import com.point18.slg2d.robot.robotData.ActionResult
import com.point18.slg2d.robot.robotData.FAILED
import com.point18.slg2d.robot.robotData.Robot
import com.point18.slg2d.robot.robotData.RobotAction
import com.point18.slg2d.robot.robotruntime.RobotPropChg
import pb4client.CreateInnerCity
import pb4client.CreateInnerCityRt

class AiCreateInnerBuilding : AiLeafBase() {

    override fun reset(): RobotAction {
        return AiCreateInnerBuilding()
    }

    override fun actionDesc(): String {
        return "AiCreateInnerBuilding - 建造内城建筑"
    }

    override fun msgTrigger(robot: Robot, chg: RobotPropChg): ActionResult {
        if (chg.msgNo != MsgType.CreateInnerCity_50) {
            return com.point18.slg2d.robot.robotData.RUNNING
        }

        val createInnerCityRt = chg.convert<CreateInnerCityRt>()
            ?: return com.point18.slg2d.robot.robotData.RUNNING

        val rt = createInnerCityRt.rt
        if (rt != ResultCode.SUCCESS.code && rt != ResultCode.LESS_RESOUCE.code) {
            return com.point18.slg2d.robot.robotData.FAILED
        }

        if (rt == ResultCode.LESS_RESOUCE.code) {
            println("新建建筑资源不足~！")
            robot.thisRobotData.isSourceLack = true
        } else {
            println("新建建筑成功~!")
        }

        return com.point18.slg2d.robot.robotData.SUCCESS
    }

    override fun update(robot: Robot): ActionResult {
        // 找到主城
        val mainCastle = robot.thisRobotData.castleData.castles

        // 找到可造建筑
        var targetType = 0
        var targetPos = 0

        val posCanBuildMap = hashMapOf<Int, InnerBuildingLocationProto>()

        // 找出一个建东西的位置 把空的位置全部填满 如果没有位置就是已经完成这个动作了
        for ((specPos, locationProto) in pcs.innerBuildingLocationCache.protoMap) {
            val posBuilding = robot.thisRobotData.innerBuildingData.findSpecBuildingAtSpecPos(specPos)
            if (posBuilding == null) {
                posCanBuildMap[specPos] = locationProto
            }
        }

        // 没有坑位了 失败吧
        if (posCanBuildMap.size == 0) {
            return FAILED
        }

        //  找出那些 buildingProto.bornType == constg.BORN_NEED 并且通过条件的建筑
        val canBuilds = mutableListOf<InnerBuildingProto>()
        val buildingProtos = pcs.innerBuildingCache.innerBuildingProtoMap
            .filter { it.value.bornType == BORN_NEED }

        for ((eachType, buildingProto) in buildingProtos) {

            val exist = robot.thisRobotData.innerBuildingData.haveBuilt[eachType]
            if (exist != null && exist >= 2) {
                // 这建筑数量超过2个就不要再造了
                continue
            }

            val specTypeBuildingProto = pcs.innerBuildingDataCache.fetchProtoByTypeLv(eachType, 1)
                ?: throw  RuntimeException("m50creatinnerbuilding.kt : specTypeBuildingProtos[1] == null")

            // 判断升级条件
            var canBuild = true
            BUILD_CHECK@ for ((checkType, checkParams) in specTypeBuildingProto.unLockMap) {
                if (checkType == UNLOCK_BY_BUILDING_LV) {
                    for ((buildingType, lv) in checkParams) {
                        val innerBuildingss = robot.thisRobotData.innerBuildingData.findSpecTypeBuildings(buildingType)
                        if (innerBuildingss.size == 0) {
                            canBuild = false
                            break@BUILD_CHECK
                        }
                        var lvOk = false
                        for (eachBuilding in innerBuildingss) {
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

            canBuilds.add(buildingProto)
        }

        // 没有建筑可以建造了
        if (canBuilds.isEmpty()) {
            return FAILED
        }

        //  把其中一个坑填上
        for ((pos, location) in posCanBuildMap) {
            canBuilds.forEach {
                if (it.buildType in location.interfaceTypeList) {
                    targetPos = pos
                    targetType = it.buildType
                }
            }
        }

        val existNum = robot.thisRobotData.innerBuildingData.haveBuilt[targetType]
        if (existNum == null) {
            robot.thisRobotData.innerBuildingData.haveBuilt[targetType] = 1
        } else {
            robot.thisRobotData.innerBuildingData.haveBuilt[targetType] = existNum + 1
        }

        val createInnerCity = CreateInnerCity.newBuilder()
        createInnerCity.cityId = mainCastle.id
        createInnerCity.positionIndex = targetPos
        createInnerCity.innerCityType = targetType
        createInnerCity.createType = RESEARCH_LVUP_RMB
        robot.thisRobotData.sendMsg(MsgType.CreateInnerCity_50, createInnerCity.build())

        return com.point18.slg2d.robot.robotData.RUNNING

//        // 根据模板找不是那种需要建造才有的
//        for ((eachType, buildingProto) in pcs.innerBuildingCache.innerBuildingProtoMap) {
//            if (buildingProto.bornType != BORN_NEED) {
//                // 需要建造=3 不是建造型建筑
//                continue
//            }
//
//            val exist = robot.thisRobotData.innerBuildingData.haveBuilt[eachType]
//            if (exist != null) {
//                // 已经存在这类建筑了，跳过
//                continue
//            }
//
//            val innerBuildings = robot.thisRobotData.innerBuildingData.findSpecTypeBuildings(eachType)
//            if (innerBuildings.size != 0) {
//                robot.thisRobotData.innerBuildingData.haveBuilt[eachType] = 1
//                continue
//            }
//
//            // 还没有这类建筑，判断条件是否满足
//            var canBuild = true
//            val specTypeBuildingProtos = pc.pcs.innerBuildingDataCache.protoMap[eachType]
//                ?: throw RuntimeException("m50creatinnerbuilding.kt : specTypeBuildingProtos == null")
//            val specTypeBuildingProto = specTypeBuildingProtos[1]
//                ?: throw  RuntimeException("m50creatinnerbuilding.kt : specTypeBuildingProtos[1] == null")
//
//            // 判断升级条件
//            BUILD_CHECK@ for ((checkType, checkParams) in specTypeBuildingProto.unLockMap) {
//                if (checkType == UNLOCK_BY_BUILDING_LV) {
//                    for ((buildingType, lv) in checkParams) {
//                        val innerBuildingss = robot.thisRobotData.innerBuildingData.findSpecTypeBuildings(buildingType)
//                        if (innerBuildingss.size == 0) {
//                            loggerAct.lzDebug { "update:${this.javaClass.name}:robot[${robot.name}] - 找不到依赖的建筑" }
//                            canBuild = false
//                            break@BUILD_CHECK
//                        }
//                        var lvOk = false
//                        for (eachBuilding in innerBuildingss) {
//                            if ((eachBuilding.state == STABLE && eachBuilding.lv >= lv) ||
//                                (eachBuilding.state == UPGRADE && eachBuilding.lv > lv)
//                            ) {
//                                // 找到等级满足的
//                                lvOk = true
//                                break
//                            }
//                        }
//                        if (!lvOk) {
//                            loggerAct.lzDebug { "update:${this.javaClass.name}:robot[${robot.name}] - 关联建筑 lv 未满足" }
//                            canBuild = false
//                            break@BUILD_CHECK
//                        }
//                    }
//                }
//            }
//
//            loggerAct.lzDebug { "update:${this.javaClass.name}:robot[${robot.name}] - 建造 type:$eachType, canBuild:$canBuild" }
//            if (!canBuild) {
//                continue
//            }
//
//            // 找到可造建筑的位置
//            var pos = 0
//
//            PosSearch@ for ((specPos, locationProto) in pc.pcs.innerBuildingLocationCache.protoMap) {
//                val posBuilding = robot.thisRobotData.innerBuildingData.findSpecBuildingAtSpecPos(specPos)
//                if (posBuilding != null) {
//                    // 这个位置有建筑了，跳过
//                    continue
//                }
//
//                for (buildingType in locationProto.interfaceTypeList) {
//                    if (buildingType == eachType) {
//
//                        pos = locationProto.buildType
//                        break@PosSearch
//                    }
//                }
//            }
//            if (pos == 0) {
//                continue
//            }
//
//            targetType = eachType
//            targetPos = pos
//            break
//        }
//
//        if (targetType == 0) {
//            loggerAct.lzDebug { "update:${this.javaClass.name}:robot[${robot.name}] - 建造内城建筑失败：找不到可造建筑类型" }
//            return robotData.FAILED
//        }
//
//        val createInnerCity = CreateInnerCity.newBuilder()
//        createInnerCity.cityId = mainCastle.id
//        createInnerCity.positionIndex = targetPos
//        createInnerCity.innerCityType = targetType
//        createInnerCity.createType = 2
//        robot.thisRobotData.sendMsg(MsgType.CreateInnerCity_50, createInnerCity.build())
//        loggerAct.lzDebug { "update:${this.javaClass.name}:robot[${robot.name}] - 建造内城建筑...[位置：$targetPos, 类型：$targetType]" }
//        return robotData.RUNNING

    }
}
