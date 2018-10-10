package com.point18.slg2d.robot.robot.realm

import com.point18.slg2d.common.netmsg.MsgType
import xyz.ariane.util.lzDebug
import xyz.ariane.util.lzInfo
import com.point18.slg2d.common.commonfunc.normalLog
import pb4client.EnterGame
import pb4client.EnterGameHomeRt
import pb4client.EnterGameRt
import com.point18.slg2d.robot.robotData.*
import com.point18.slg2d.robot.robotruntime.RobotPropChg

class AiEnterGame : com.point18.slg2d.robot.robot.AiLeafBase() {

    private var worldEnterFinished = false
    private var homeEnterFinished = false

    override fun reset(): RobotAction {
        return AiEnterGame()
    }

    override fun actionDesc(): String {
        return "AiEnterGame - 进入游戏"
    }

    override fun msgTrigger(robot: Robot, chg: RobotPropChg): ActionResult {
        val data = robot.thisRobotData
        if (!worldEnterFinished) {
            // 尚未进入世界服
            val enterGameRt = chg.convert<EnterGameRt>()
                ?: return com.point18.slg2d.robot.robotData.RUNNING

            val rt = (enterGameRt.rt)
            if (rt != com.point18.slg2d.common.resultcode.ResultCode.SUCCESS.code) {
                normalLog.lzDebug {
                    "msgTrigger:${this.javaClass.name}:robot[${robot.name}]" +
                        " - 进入游戏失败：${com.point18.slg2d.robot.robotData.getErrMsg(rt)}"
                }
                return com.point18.slg2d.robot.robotData.FAILED
            }

            data.playerData.playerId = enterGameRt.playerId
            data.serverTime = enterGameRt.time

            // 城池信息
            for (cityInfo in enterGameRt.cityInfoList) {
                // 城池信息
                data.castleData.castles = CastleData(
                    cityInfo.cityId,
                    cityInfo.x,
                    cityInfo.y,
                    cityInfo.name,
                    cityInfo.cityType,
                    cityInfo.lv
                )
            }

            normalLog.lzInfo { "msgTrigger:${this.javaClass.name}:robot[${robot.name}] - 进入游戏进行了一半" }

            worldEnterFinished = true

            return com.point18.slg2d.robot.robotData.RUNNING

        } else {
            // 尚未进入玩家服
            val enterGameHomeRt = chg.convert<EnterGameHomeRt>()
                ?: return com.point18.slg2d.robot.robotData.RUNNING

            robot.thisRobotData.enterGame = true

            for (heroInfo in enterGameHomeRt.detailedQueryHerosInFoList) {
                // 英雄信息
                data.heroIdList.add(heroInfo.heroId)
            }

            normalLog.lzInfo { "msgTrigger:${this.javaClass.name}:robot[${robot.name}] - 进入游戏成功" }

            homeEnterFinished = true

            return com.point18.slg2d.robot.robotData.SUCCESS
        }

    }

    override fun update(robot: Robot): ActionResult {
        normalLog.lzInfo { "update:${this.javaClass.name}:robot[${robot.name}] - 正在进入游戏..." }

        if (robot.thisRobotData.enterGame) {
            normalLog.lzDebug { "update:${this.javaClass.name}:robot[${robot.name}] - 进入游戏失败，已经进入" }
            return FAILED
        }

        val enterGame = EnterGame.newBuilder()

        enterGame.playerId = robot.thisRobotData.playerData.playerId

        robot.thisRobotData.sendMsg(MsgType.EnterGame_4, enterGame.build())

        return RUNNING
    }
}