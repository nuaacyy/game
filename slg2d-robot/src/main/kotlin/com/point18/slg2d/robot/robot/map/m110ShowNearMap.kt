package com.point18.slg2d.robot.robot.map

import com.point18.slg2d.common.netmsg.MsgType
import xyz.ariane.util.lzDebug
import com.point18.slg2d.common.commonfunc.normalLog
import pb4client.NewShowNearMap
import pb4client.NewShowNearMapRt
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.robot.robot.AiLeafBase
import com.point18.slg2d.robot.robotData.*
import com.point18.slg2d.robot.robotruntime.RobotPropChg

class AiShowNearMap : AiLeafBase() {

    override fun reset(): RobotAction {
        return AiShowNearMap()
    }

    override fun actionDesc(): String {
        return "AiShowNearMap - 显示地图"
    }

    override fun update(robot: Robot): ActionResult {
        normalLog.lzDebug { "update:${this.javaClass.name}:robot[${robot.name}] - 显示地图..." }
        robot.thisRobotData.monster.clear()
        val castleInfo = robot.thisRobotData.castleData.castles
        var castleX = 100
        var castleY = 100
        if(castleInfo != null){
            castleX = castleInfo.x
            castleY = castleInfo.y
        }
        val showNearMap = NewShowNearMap.newBuilder()
        showNearMap.isForce = 1
        showNearMap.x = castleX
        showNearMap.y = castleY
        robot.thisRobotData.sendMsg(MsgType.NewShowNearMap_110, showNearMap.build())
        return com.point18.slg2d.robot.robotData.RUNNING
    }

    override fun msgTrigger(robot: Robot, chg: RobotPropChg): ActionResult {
        if (chg.msgNo != MsgType.NewShowNearMap_110) {
            normalLog.lzDebug {
                "msgTrigger:${this.javaClass.name}:robot[${robot.name}] " +
                        "- 接收的消息类型不匹配${MsgType.NewShowNearMap_110}"
            }
            return com.point18.slg2d.robot.robotData.RUNNING
        }

        val showNearMapRt = chg.convert<NewShowNearMapRt>() ?: return com.point18.slg2d.robot.robotData.RUNNING

        val rt = showNearMapRt.rt
        if (rt != ResultCode.SUCCESS.code) {
            normalLog.lzDebug {
                "msgTrigger:${this.javaClass.name}:robot[${robot.name}] " +
                        "- 显示地图失败：${com.point18.slg2d.robot.robotData.getErrMsg(rt)}"
            }
            return com.point18.slg2d.robot.robotData.FAILED
        }

        val allLandInfo = showNearMapRt.landsList
        for(landInfo in allLandInfo){
            // celltype:2 为魔物
            if(landInfo.cellType == 2){

                robot.thisRobotData.monster.add(MonstersData(landInfo.x,landInfo.y))
            }
        }

        normalLog.lzDebug { "msgTrigger:${this.javaClass.name}:robot[${robot.name}] - 显示地图完成" }
        return com.point18.slg2d.robot.robotData.FAILED
    }

}