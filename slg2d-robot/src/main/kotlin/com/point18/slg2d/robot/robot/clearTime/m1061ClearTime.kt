package com.point18.slg2d.robot.robot.clearTime

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.constg.CLEAR_TIME_INNER_CITY_BUILDING_UPGRADE
import pb4client.ClearTime
import pb4client.ClearTimeRt
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.robot.robot.AiLeafBase
import com.point18.slg2d.robot.robotData.*
import com.point18.slg2d.robot.robotruntime.RobotPropChg

class AiClearTime(val type: Int) : AiLeafBase() {

    override fun actionDesc(): String {
        return "AiClearTime - 加速"
    }

    override fun update(robot: Robot): ActionResult {

        val clearTime = ClearTime.newBuilder()
        clearTime.clearType = type
        clearTime.clearPropsId = 0
        clearTime.clearPropsNum = 0
        clearTime.extend1 = 0

        // 选择加速类型
        when (type) {

        // 建筑立即完成加速
            CLEAR_TIME_INNER_CITY_BUILDING_UPGRADE -> {
                val canClearTimeBuildings = robot.thisRobotData.innerBuildingData.innerBuildings.index
                    .filter { it.value.completeTime != 0 }
                if (canClearTimeBuildings.isEmpty()) {
                    return SUCCESS
                }
                clearTime.extend1 = robot.thisRobotData.castleData.castles.id
            }

        // todo 其他加速类型 各自补充
            else -> {
                return FAILED
            }

        }

        robot.thisRobotData.sendMsg(MsgType.ClearTime_1061, clearTime.build())
        return RUNNING
    }

    override fun msgTrigger(robot: Robot, chg: RobotPropChg): ActionResult {
        if (chg.msgNo != MsgType.ClearTime_1061) {
            return com.point18.slg2d.robot.robotData.RUNNING
        }

        val clearTimeRt = chg.convert<ClearTimeRt>() ?: return com.point18.slg2d.robot.robotData.RUNNING
        val rt = clearTimeRt.rt
        // 缺资源可以补资源，不算失败
        if (rt != ResultCode.SUCCESS.code && rt != ResultCode.LESS_RESOUCE.code) {
            return com.point18.slg2d.robot.robotData.FAILED
        }

        if (rt == ResultCode.LESS_RESOUCE.code) {
            println("加速建筑资源不足~！")
            robot.thisRobotData.isSourceLack = true
        } else {
            robot.thisRobotData.isSourceLack = false
            println("加速建筑成功~!")
        }

        return SUCCESS
    }

    override fun reset(): RobotAction {
        return AiClearTime(type)
    }

}