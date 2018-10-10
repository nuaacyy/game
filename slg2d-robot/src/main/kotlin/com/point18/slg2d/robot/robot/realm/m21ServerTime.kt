package com.point18.slg2d.robot.robot.realm

import com.point18.slg2d.common.netmsg.MsgType
import xyz.ariane.util.lzDebug
import com.point18.slg2d.common.commonfunc.normalLog
import pb4client.FetchServerTime
import pb4client.FetchServerTimeRt
import com.point18.slg2d.robot.robotData.*
import com.point18.slg2d.robot.robotruntime.RobotPropChg

class AiServerTime : com.point18.slg2d.robot.robot.AiLeafBase() {
    override fun reset(): RobotAction {
        return AiServerTime()
    }

    override fun actionDesc(): String {
        return "AiServerTime - 获取服务器时间"
    }

    override fun msgTrigger(robot: Robot, chg: RobotPropChg): ActionResult {
        if (chg.msgNo != MsgType.ServerTime_21) {
            return com.point18.slg2d.robot.robotData.RUNNING
        }

        val fetchServerTimeRt = chg.convert<FetchServerTimeRt>() ?: return com.point18.slg2d.robot.robotData.RUNNING
        val rt = (fetchServerTimeRt.rt)
        if (rt != com.point18.slg2d.common.resultcode.ResultCode.SUCCESS.code) {
            normalLog.lzDebug { "服务器时间失败：${com.point18.slg2d.robot.robotData.getErrMsg(rt)}" }
            return com.point18.slg2d.robot.robotData.FAILED
        }

        return com.point18.slg2d.robot.robotData.SUCCESS
    }

    override fun update(robot: Robot): ActionResult {
        // 添加土地收藏
        val fetchServerTime = FetchServerTime.newBuilder()
        fetchServerTime.reqTime = 1

        robot.thisRobotData.sendMsg(MsgType.ServerTime_21, fetchServerTime.build())

        normalLog.lzDebug { "尝试服务器时间" }

        return com.point18.slg2d.robot.robotData.RUNNING
    }
}