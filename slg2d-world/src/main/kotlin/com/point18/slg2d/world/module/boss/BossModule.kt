package com.point18.slg2d.world.module.boss

import com.point18.slg2d.common.baseg.IModule
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.getRandInt
import com.point18.slg2d.common.commonfunc.sec2MilliSec
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.*
import com.point18.slg2d.world.common.noticeCellUpdate
import com.point18.slg2d.world.wpm
import java.util.*

var BossM = CallBossModule()

class CallBossModule : IModule {

    override fun moduleInit() {
        //召唤魔物消失的心跳
        wpm.hs.registerHeartHandler(CallBossHeartHandler())

        //活动魔物心跳
        wpm.hs.registerHeartHandler(ActivityBossHeartHandler())

        wpm.es.register(CREATE_ALLIANCE, JoinAllianceEventHandler())
        wpm.es.register(ALLOW_ALLIANCE, JoinAllianceEventHandler())
        wpm.es.register(QUIT_ALLIANCE, QuitAllianceEventHandler())
        wpm.es.register(BOSS_FIGHT, OnBossDieEventHandler())
    }

    //处理召唤魔物
    fun dealCallBoss(areaCache: AreaCache, playerId: Long, callInfo: String, x: Int, y: Int): ResultCode {
        //校验坐标
        if (!isValidXY(x, y)) {
            return ResultCode.PARAMETER_ERROR
        }
        //判断是否在城池旁边
        val player = areaCache.playerCache.findPlayerById(playerId)
        if (player == null) {
            return ResultCode.NO_PLAYER
        }

        //判断联盟
        if (player.allianceId == 0L) {
            return ResultCode.ALLIANCE_PLAYER_NO_JOIN
        }

        val castle = areaCache.castleCache.findCastleById(player.focusCastleId)
        if (castle == null) {
            return ResultCode.CASTLE_DONT_EXISTED
        }
        if (Math.abs(castle.x - x) > 1 || Math.abs(castle.y - y) > 1) {
            //todo 默认是主城周围一圈，待策划配置
            return ResultCode.PARAMETER_ERROR
        }

        //校验地块是否为空地
        val cellType = areaCache.mapCellCache.getCellType(x, y)
        if (cellType != CELL_EMPTY) {
            //非空地
            return ResultCode.CALL_BOSS_CELL_NOT_EMPTY
        }
        val groups = areaCache.walkForceGroupCache.findWalkForceGroupsByPos(x, y)
        if (groups.count() > 0) {
            //空地上有驻扎部队
            return ResultCode.CALL_BOSS_CELL_NOT_EMPTY
        }

        //解析召唤信息
        val callMap = LinkedList<CallBossInfo>()
        val callInfoStrList = callInfo.split("|")
        for (callInfoStr in callInfoStrList) {
            val strList = callInfoStr.split(";")
            if (strList.count() != 2) {
                return ResultCode.PARAMETER_ERROR
            }
            val monsterId = strList[0].toIntOrNull()
            val rate = strList[1].toIntOrNull()
            if (monsterId == null || rate == null || rate < 0) {
                return ResultCode.PARAMETER_ERROR
            }
            callMap.add(CallBossInfo(monsterId, rate))
        }
        if (callMap.count() == 0) {
            return ResultCode.PARAMETER_ERROR
        }

        //选择魔物配置
        val callBossInfo = selectMonster(callMap)
        if (callBossInfo == null) {
            return ResultCode.PARAMETER_ERROR
        }
        val monsterProto = pcs.monsterProtoCache.findMonsterProto(callBossInfo.monsterId)
        if (monsterProto == null) {
            return ResultCode.PARAMETER_ERROR
        }

        val unitProto = pcs.unitBaseCache.protoMap[monsterProto.unit]
        if (unitProto == null) {
            return ResultCode.PARAMETER_ERROR
        }

        //创建魔物
        areaCache.callBossCache.createCallBoss(
            callBossInfo.monsterId,
            x,
            y,
            getNowTime() + sec2MilliSec(monsterProto.proctTime),
            getNowTime() + sec2MilliSec(monsterProto.refreshTime),
            Math.ceil(unitProto.hp).toInt(),
            playerId,
            player.allianceId
        )

        //刷新地块
        noticeCellUpdate(areaCache, x, y)

        return ResultCode.SUCCESS
    }

    data class CallBossInfo(
        var monsterId: Int,
        var rate: Int
    )

    private fun selectMonster(callMap: List<CallBossInfo>): CallBossInfo? {
        var totalRate = 0
        for (info in callMap) {
            totalRate += info.rate
        }
        val dropRand = getRandInt(totalRate)
        var tempRate = 0
        for (info in callMap) {
            if (dropRand <= tempRate + info.rate) {
                return info
            }
            tempRate += info.rate
        }
        return null
    }
}