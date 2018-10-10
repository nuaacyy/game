package com.point18.slg2d.robot.robot.realm

import com.point18.slg2d.common.netmsg.MsgType
import xyz.ariane.util.lzInfo
import xyz.ariane.util.lzWarn
import com.point18.slg2d.common.commonfunc.normalLog
import pb4client.Login
import pb4client.LoginRt
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.robot.robot.AiLeafBase
import com.point18.slg2d.robot.robotData.*
import com.point18.slg2d.robot.robotruntime.LockObj
import com.point18.slg2d.robot.robotruntime.RobotPropChg

var loginMutex = LockObj()
var loginNum = 0 // 登录成功数
var sendNum = 0 // 登录消息发送数

class AiLogin : AiLeafBase() {

    override fun reset(): RobotAction {
        return AiLogin()
    }

    override fun actionDesc(): String {
        return "AiLogin - 账号登录"
    }

    override fun update(robot: Robot): ActionResult {
        normalLog.lzInfo { "update:${this.javaClass.name}:robot[${robot.name}] - 尝试登录..." }

        if (robot.thisRobotData.logined) {
            normalLog.lzInfo { "update:${this.javaClass.name}:robot[${robot.name}] - 尝试登录失败，已经登录" }
            return FAILED
        }

        // 登录
        val login = Login.newBuilder()
        login.account = "lqh"+robot.name
        login.pwd = "111"
        login.sid = robot.pltAreaNo.toString()
        login.loginType = 1
        login.token = ""
        login.device = ""
        robot.thisRobotData.sendMsg(MsgType.Login_1, login.build())
        synchronized(loginMutex) {
            sendNum += 1
        }
        return com.point18.slg2d.robot.robotData.RUNNING
    }

    override fun msgTrigger(robot: Robot, chg: RobotPropChg): ActionResult {
        val loginRt = chg.convert<LoginRt>() ?: return RUNNING  // 把消息里的msgBody转换成LoginRt类型

        val rt = loginRt.rt  // 获取消息返回结果
        if (rt != ResultCode.SUCCESS.code) {  // 结果表明了不成功
            normalLog.lzWarn {
                "msgTrigger:${this.javaClass.name}:robot[${robot.name}] - 登录失败:${com.point18.slg2d.robot.robotData.getErrMsg(
                    rt
                )}"
            }
            return FAILED
        }

        robot.thisRobotData.logined = true
        robot.thisRobotData.playerData.playerId = loginRt.playerId

        normalLog.lzInfo { "update:${this.javaClass.name}:robot[${robot.name}] - 登录完成..." }

        // 增加登录总数
        synchronized(loginMutex) {
            loginNum += 1
        }

        val hasPlayer = loginRt.exist  //  登陆结果会返回是否存在角色
        if (hasPlayer == 1) {
            robot.thisRobotData.hasPlayer = true  //  标记一下
        }

        return SUCCESS
    }
}