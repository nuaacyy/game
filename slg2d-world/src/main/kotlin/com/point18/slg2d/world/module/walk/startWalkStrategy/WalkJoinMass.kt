package com.point18.slg2d.world.module.walk.startWalkStrategy

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.MassMember
import com.point18.slg2d.world.area4data.Walk
import com.point18.slg2d.world.common.addWalkHot
import com.point18.slg2d.world.common.getResearchEffectValue
import com.point18.slg2d.world.common.noticeMassGroup
import com.point18.slg2d.world.module.walk.IWalkDeal
import com.point18.slg2d.world.module.walk.walkComm.WalkParam

// 参与集结
class WalkJoinMass : BaseWalkStrategy(), IWalkDeal {
    init {
        checkItem = CheckNoHero or CheckHaveSolider or CheckSameAlliance
    }

    override fun getPosByTarget(
        areaCache: AreaCache,
        targetId: Long,
        gotoX: Int,
        gotoY: Int
    ): IWalkDeal.GetPosByTargetResult {
        if (targetId == 0L) {
            return IWalkDeal.GetPosByTargetResult(ResultCode.PARAMETER_ERROR.code, 0, 0)
        }
        val mass = areaCache.massCache.findMassById(targetId)
        if (mass == null) {
            return IWalkDeal.GetPosByTargetResult(ResultCode.PARAMETER_ERROR.code, 0, 0)
        }
        return IWalkDeal.GetPosByTargetResult(ResultCode.SUCCESS.code, mass.startX, mass.startY)
    }

    override fun walkStartCheck(areaCache: AreaCache, wp: WalkParam, rs: IWalkDeal.WalkStartCheckResult?): Int {
        val mass = areaCache.massCache.findMassById(wp.targetId)
        if (mass == null) {
            return ResultCode.PARAMETER_ERROR.code
        }

        // 校验集结状态
        if (mass.massState != Mass) {
            return ResultCode.MassCanNotJoin.code
        }

        // 校验双方联盟
        val mine = areaCache.playerCache.findPlayerById(wp.playerId)
        if (mine == null) {
            normalLog.error("找不到玩家信息:${wp.playerId}")
            return ResultCode.PARAMETER_ERROR.code
        }

        val mainPlayer = areaCache.playerCache.findPlayerById(mass.mainPlayerId)
        if (mainPlayer == null) {
            normalLog.error("找不到玩家信息:${mass.mainPlayerId}")
            return ResultCode.PARAMETER_ERROR.code
        }

        if (mine.allianceId == 0L || mine.allianceId != mainPlayer.allianceId) {
            return ResultCode.NOT_IN_SAME_ALLIANCE.code
        }

        //校验是否已加入集结
        if (mass.findMassMember(wp.playerId) != null) {
            return ResultCode.PARAMETER_ERROR.code
        }

        // 校验可集结兵的数量
        var currentSoliderNum = 0
        for ((_, num) in wp.we.SoliderMap) {
            currentSoliderNum += num
        }

        // 集结量
        val maxSoliderNum =
            getResearchEffectValue(areaCache, NO_FILTER, mainPlayer, JijieTroopMaxAdd)
        if (currentSoliderNum + areaCache.massCache.getSoliderNumExceptMainInMass(mass) > maxSoliderNum) {
            return ResultCode.MassSoliderUpLimit.code
        }

        return ResultCode.SUCCESS.code
    }

    override fun walkStartDeal(areaCache: AreaCache, walkLineInfo: Walk, targetId: Long): Int {
        // 加入集结
        val mass = areaCache.massCache.findMassById(targetId)
        if (mass == null) {
            normalLog.error("找不到集结信息:$targetId")
            return ResultCode.PARAMETER_ERROR.code
        }

        val group = areaCache.walkForceGroupCache.findWalkForceGroupById(walkLineInfo.walkForceGroupId)
        if (group == null) {
            normalLog.error("行军线对应的行军组不存在,行军线Id:${walkLineInfo.id}")
            return ResultCode.PARAMETER_ERROR.code
        }

        val player = areaCache.playerCache.findPlayerById(group.mainPlayerId)
        if (player == null) {
            normalLog.error("找不到玩家信息:${group.mainPlayerId}")
            return ResultCode.PARAMETER_ERROR.code
        }

        mass.putMassMember(MassMember(player.id, group.id, walkLineInfo.marchTimeArrival))

        // 触发战争狂热
        if (mass.fightType == WalkFightPlayer || mass.fightType == WalkOccupyWonder) {
            addWalkHot(areaCache, player)
        }

        // 更新集结信息
        noticeMassGroup(areaCache, Update, mass)

        return ResultCode.SUCCESS.code
    }

}
