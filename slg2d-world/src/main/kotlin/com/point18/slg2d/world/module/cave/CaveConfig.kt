package com.point18.slg2d.world.module.cave

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.wpm
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.world.event.NoHandle
import com.point18.slg2d.world.msgnotice.createBarracksChangeNotifier
import com.point18.slg2d.world.msgnotice.createCaveChangeNotifier
import com.point18.slg2d.world.msgnotice.createValueChgNotifier
import pb4client.CaveConfig
import pb4client.CaveConfigRt
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.common.getResearchEffectValue
import java.util.*

//藏兵
class CaveConfigDeal : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val rt = caveConfig(session, msg as CaveConfig)
        session.sendMsg(MsgType.CaveConfig_35, rt)
    }

    private fun caveConfig(session: PlayerSession, configMsg: CaveConfig): (CaveConfigRt) {
        val rt = CaveConfigRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val areaCache = session.areaCache
        val playerId = session.playerId
        val caveTime = configMsg.protoId
        val holdKing = configMsg.holdKing
        val caveSoliderList = configMsg.soldiersList
        val player = session.player

        var cave = areaCache.caveCache.findCaveByPlayerId(playerId)
        if (cave != null) {
            rt.rt = ResultCode.HAVE_CAVE_FORCE_GROUP.code
            return rt.build()
        }

        // 藏兵时间校验
        var haveCaveTime = false
        for (ct in pcs.basicProtoCache.caveTime) {
            if (ct == caveTime) {
                haveCaveTime = true
                break
            }
        }
        if (!haveCaveTime) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        var heroSoliderAdd = 0
        if (holdKing != 0) {
            // 领主状态校验
            val mainHero = areaCache.heroCache.findHeroById(playerId, player.mainHeroId)
            if (mainHero == null) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                return rt.build()
            }
            if (mainHero.mainHeroState != MAIN_HERO) {
                rt.rt = ResultCode.MAIN_HERO_NOT_IN_CITY.code
                return rt.build()
            }

            val heroRankMap = pcs.heroRankProtoCache.heroRankProtoCache[mainHero.protoId]
            if (heroRankMap != null) {
                val heroRank = heroRankMap[mainHero.awake]
                if (heroRank != null) {
                    heroSoliderAdd += heroRank.solider
                }
            }
        }

        // 士兵数量校验
        var totalSoliderNum = 0
        val soliderMap = hashMapOf<Int, Int>()
        val barrackMap = areaCache.barracksCache.findBarracksMapByPlayerId(playerId)
        for (cSolider in caveSoliderList) {
            if (cSolider.soldierNum <= 0) {
                continue
            }
            val soliderId = cSolider.soldierType
            val soliderNum = cSolider.soldierNum
            val barrack = barrackMap[soliderId]
            if (barrack == null || barrack.soldierNum < soliderNum) {
                rt.rt = ResultCode.NO_SUCH_SOLIDER_IN_CITY.code
                return rt.build()
            }
            val smi = soliderMap[soliderId]
            if (smi == null) {
                soliderMap[soliderId] = soliderNum
            } else {
                soliderMap[soliderId] = smi + soliderNum
            }
            totalSoliderNum += soliderNum
        }
        if (soliderMap.size == 0) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        if (getResearchEffectValue(
                areaCache,
                NO_FILTER,
                player,
                ForceTroopsMaxAdd
            ) + heroSoliderAdd < totalSoliderNum
        ) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        val mainHero = areaCache.heroCache.findHeroById(playerId, player.mainHeroId)
        if (mainHero == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }
        if (holdKing != 0) {
            // 设置领主状态
            mainHero.mainHeroState = IN_HIDE
            val valueChangeNotice = createValueChgNotifier()
            valueChangeNotice.append(mainHero.id, MAIN_HERO_STATE, mainHero.mainHeroState.toLong())
            valueChangeNotice.notice(session)
        }

        // 扣除士兵
        for (cSolider in caveSoliderList) {
            if (cSolider.soldierNum <= 0) {
                continue
            }
            val barrack = barrackMap[cSolider.soldierType]

            if (barrack == null) {
                continue
            }
            barrack.soldierNum = barrack.soldierNum - cSolider.soldierNum
            createBarracksChangeNotifier(barrack).notice(session)
        }

        // 创建藏兵部队
        val caveGroup = areaCache.walkForceGroupCache.createWalkForceGroup(playerId, 0, Hiding, WalkHiding, 0, 0)
        areaCache.walkForceCache.createWalkForce(LinkedList(), soliderMap, "", LinkedList(), caveGroup.id, playerId)

        // 创建藏兵
        cave = areaCache.caveCache.createCave(playerId, caveTime, caveGroup.id)

        // 推送消息
        createCaveChangeNotifier(CaveStart, player, cave, caveSoliderList, holdKing)
            .notice(session)

        val myTarget = areaCache.targetCache.findMyTargetByPlayerId(playerId)
        if (myTarget != null) {
            myTarget.caveSoliderNum++
        }

        wpm.es.fire(areaCache, player.id, CAVE_SOLIDER, NoHandle())

        return rt.build()
    }
}

