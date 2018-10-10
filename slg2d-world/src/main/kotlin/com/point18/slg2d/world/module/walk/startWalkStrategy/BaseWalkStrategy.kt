package com.point18.slg2d.world.module.walk.startWalkStrategy

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.common.getResearchEffPlusRate
import com.point18.slg2d.world.common.getResearchEffectValue
import com.point18.slg2d.world.module.walk.walkComm.WalkParam

abstract class BaseWalkStrategy {

    var checkItem: Int = 0

    fun startCheck(areaCache: AreaCache, wp: WalkParam): Int {
        //检验距离
        if ((wp.gotoX - wp.startX) * (wp.gotoX - wp.startX) + (wp.gotoY - wp.startY) * (wp.gotoY - wp.startY) > pcs.basicProtoCache.goLimit * pcs.basicProtoCache.goLimit) {
            return ResultCode.WALK_DISTANCE_LIMIT.code
        }

        if (this.checkItem and CheckHaveHero == CheckHaveHero ||
            this.checkItem and CheckNoHero == CheckNoHero ||
            this.checkItem and CheckHaveSolider == CheckHaveSolider ||
            this.checkItem and CheckNoSolider == CheckNoSolider
        ) {
            val rst = this.checkSelf(areaCache, wp)
            if (rst != ResultCode.SUCCESS.code) {
                return rst
            }
        }

        if (this.checkItem and CheckNoCoverBuff == CheckNoCoverBuff ||
            this.checkItem and CheckSameAlliance == CheckSameAlliance ||
            this.checkItem and CheckNotSameAlliance == CheckNotSameAlliance
        ) {
            val rst = this.checkTarget(areaCache, wp)
            if (rst != ResultCode.SUCCESS.code) {
                return rst
            }
        }

        return ResultCode.SUCCESS.code
    }

    // 检测自身
    private fun checkSelf(areaCache: AreaCache, wp: WalkParam): Int {
        val player = areaCache.playerCache.findPlayerById(wp.playerId) ?: return ResultCode.PARAMETER_ERROR.code

        if (this.checkItem and CheckHaveHero == CheckHaveHero) {
            if (wp.we.HeroIds.count() == 0) {
                return ResultCode.NO_HERO_IN_FORCE_ERROR.code
            }
            val maxHeroNum = pcs.basicProtoCache.monsterHeroMax
            if (wp.we.HeroIds.count() > maxHeroNum) {
                return ResultCode.HERO_IN_FORCE_OUT_OF_RANGE_ERROR.code
            }
        }

        if (this.checkItem and CheckNoHero == CheckNoHero) {
            if (wp.we.HeroIds.count() > 0) {
                return ResultCode.HAVE_HERO_IN_FORCE_ERROR.code
            }
        }

        if (this.checkItem and CheckHaveSolider == CheckHaveSolider) {
            var haveSolider = false
            var totalSoliderNum = 0
            val barracks = areaCache.barracksCache.findBarracksMapByPlayerId(wp.playerId)
            for ((id, num) in wp.we.SoliderMap) {
                if (num > 0) {
                    haveSolider = true
                    totalSoliderNum += num
                    val barrack = barracks[id]
                    if (barrack == null || barrack.soldierNum < num) {
                        return ResultCode.NO_ENOUGH_SOLIDER_IN_BARRACK_ERROR.code
                    }
                }
            }
            if (!haveSolider) {
                return ResultCode.NO_SOLIDER_IN_FORCE_ERROR.code
            }

            var heroSoliderAdd = 0
            for (heroId in wp.we.HeroIds) {
                val hero = areaCache.heroCache.findHeroById(player.id, heroId)
                if (hero == null) {
                    normalLog.error("找不到英雄信息，玩家Id:%d,英雄Id:%d", player.id, heroId)
                    return ResultCode.PARAMETER_ERROR.code
                }
                val rankMap = pcs.heroRankProtoCache.heroRankProtoCache[hero.protoId]
                if (rankMap == null) {
                    normalLog.error("英雄阶级配置错误,英雄Id:%d", hero.protoId)
                    return ResultCode.PARAMETER_ERROR.code
                }
                val heroRank = rankMap[hero.awake]
                if (heroRank != null) {
                    heroSoliderAdd += heroRank.solider
                }
            }
            val maxSolider = ((getResearchEffectValue(
                areaCache,
                NO_FILTER,
                player,
                ForceTroopsMaxAdd
            ) + heroSoliderAdd) *
                    getResearchEffPlusRate(
                        areaCache,
                        NO_FILTER,
                        player,
                        ArmyGroupMaxAdd
                    )).toInt()
            if (totalSoliderNum > maxSolider) {
                return ResultCode.SOLIDER_IN_FORCE_OUT_OF_RANGE_ERROR.code
            }
        }

        if (this.checkItem and CheckNoSolider == CheckNoSolider) {
            var haveSolider = false
            for ((_, num) in wp.we.SoliderMap) {
                if (num > 0) {
                    haveSolider = true
                    break
                }
            }
            if (haveSolider) {
                return ResultCode.HAVE_SOLIDER_IN_FORCE_ERROR.code
            }
        }

        return ResultCode.SUCCESS.code
    }

    // 检测对方
    private fun checkTarget(areaCache: AreaCache, wp: WalkParam): Int {
        val player = areaCache.playerCache.findPlayerById(wp.playerId) ?: return ResultCode.PARAMETER_ERROR.code

        var targetPlayerId = 0L
        val cellType = areaCache.mapCellCache.getCellType(wp.gotoX, wp.gotoY)
        when (cellType) {
            CELL_EMPTY -> {
                val groups =
                    areaCache.walkForceGroupCache.findWalkForceGroupsByPosAndState(wp.gotoX, wp.gotoY, Stationed)
                if (groups.count() > 0) {
                    targetPlayerId = groups[0].mainPlayerId
                }
            }
            CELL_WONDER -> {
                val rst = pcs.wonderLocationProtoCache.findInWonderType(wp.gotoX, wp.gotoY)
                val wonderProto = rst.wonderLocationProto
                if (wonderProto == null) {
                    normalLog.error("在坐标(%d,%d)的奇观数据不存在", wp.gotoX, wp.gotoY)
                    return ResultCode.PARAMETER_ERROR.code
                }
                val wonder = areaCache.wonderCache.findWonder(wonderProto.id)
                if (wonder == null) {
                    normalLog.error("找不到奇观数据:%d", wonderProto.id)
                    return ResultCode.PARAMETER_ERROR.code
                }
                val allianceMembers = areaCache.playerCache.findPlayersByAllianceId(wonder.belongToAllianceId)
                if (allianceMembers.count() > 0) {
                    targetPlayerId = allianceMembers[0].id
                }
            }
            CELL_CASTLE -> {
                val castle = areaCache.castleCache.findCastleByXy(wp.gotoX, wp.gotoY)
                if (castle == null) {
                    normalLog.error("找不到坐标(%d,%d)的城池", wp.gotoX, wp.gotoY)
                    return ResultCode.PARAMETER_ERROR.code
                }
                targetPlayerId = castle.playerId
            }
        }

        // 判断是否没有防护罩
        if ((this.checkItem and CheckNoCoverBuff) == CheckNoCoverBuff) {
            if (targetPlayerId != 0L) {
                val (isHaveCover, _) = areaCache.buffCache.isHaveCoverTypeBuff(targetPlayerId)
                if (isHaveCover) {
                    return ResultCode.HAVE_DEF_COVER_BUFF_WHEN_FIGHT.code
                }
            }
        }

        // 判断两个玩家是否在相同公会
        if ((this.checkItem and CheckSameAlliance) == CheckSameAlliance) {
            val targetPlayer =
                areaCache.playerCache.findPlayerById(targetPlayerId) ?: return ResultCode.PARAMETER_ERROR.code

            if (player.allianceId == 0L || player.allianceId != targetPlayer.allianceId) {
                return ResultCode.NOT_IN_SAME_ALLIANCE.code
            }
        }

        // 判断两个玩家是否在不同公会
        if ((this.checkItem and CheckNotSameAlliance) == CheckNotSameAlliance) {
            if (targetPlayerId != 0L) {
                val targetPlayer =
                    areaCache.playerCache.findPlayerById(targetPlayerId) ?: return ResultCode.SUCCESS.code

                if (player.allianceId != 0L && player.allianceId == targetPlayer.allianceId) {
                    return ResultCode.IN_SAME_ALLIANCE.code
                }
            }
        }

        return ResultCode.SUCCESS.code
    }
}


