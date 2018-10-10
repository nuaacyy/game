package com.point18.slg2d.world.module.worldmap

import akka.actor.ActorRef
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.getTimeSec
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.commonfunc.tellNoSender
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.msgtrans.ScMessageAtSend
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.getTotalPower
import com.point18.slg2d.world.area4data.playerIsHavePos
import com.point18.slg2d.world.common.*
import com.point18.slg2d.world.module.ReqDealWithConn
import pb4client.*

//查询地块
class QueryCellDeal : ReqDealWithConn() {

    override fun dealConnReq(cache: AreaCache, channelActor: ActorRef, msg: MessageLite, playerId: Long) {
        val queryCellMsg = msg as QueryCell
        val posX = queryCellMsg.posX
        val posY = queryCellMsg.posY
        val rtBuilder = this.queryCell(cache, posX, posY, playerId)
        val scMsg =
            ScMessageAtSend(MsgType.QueryCell_111, cache.currentClientMsgNo, rtBuilder.build())
        channelActor.tellNoSender(scMsg)
    }

    private fun queryCell(
        cache: AreaCache,
        posX: Int,
        posY: Int,
        playerId: Long
    ): QueryCellRt.Builder {
        val rtBuilder = QueryCellRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        //普通魔物判定
        val commonBoss = cache.commonBossCache.findCommonBossByXY(posX, posY)
        if (commonBoss != null) {
            rtBuilder.cellType = CELL_MONSTER
            val cellBuilder = CommonBossCell.newBuilder()
            cellBuilder.bossId = commonBoss.bossId
            cellBuilder.bossHp = commonBoss.nowHp
            cellBuilder.disappearTime = getTimeSec(cache.commonBossCache.calOverTime(posX, posY))
            rtBuilder.setCommonBossCell(cellBuilder)
            return rtBuilder
        }

        //召唤魔物判定
        val callBoss = cache.callBossCache.findCallBossByXy(posX, posY)
        if (callBoss != null) {
            rtBuilder.cellType = CELL_MONSTER
            val cellBuilder = CallBossCell.newBuilder()
            val callPlayer = cache.playerCache.findPlayerById(callBoss.playerId)
            if (callPlayer != null) {
                cellBuilder.playerName = callPlayer.name
                cellBuilder.playerPhotoId = callPlayer.photoProtoId
            }
            cellBuilder.bossId = callBoss.bossId
            cellBuilder.bossHp = callBoss.nowHp
            cellBuilder.disappearTime = getTimeSec(callBoss.overTime)
            cellBuilder.unlockTime = getTimeSec(callBoss.protectOverTime)
            val member = cache.playerCache.findFirstPlayerByAllianceId(callBoss.allianceId)
            if (member != null) {
                cellBuilder.allianceShortName = member.allianceShortName
            }
            cellBuilder.helpMemberCount = callBoss.helpMemberMap.count()
            rtBuilder.setCallBossCell(cellBuilder)
            return rtBuilder
        }

        //活动魔物判定
        val activityBoss = cache.activityBossCache.findActivityBossByXy(posX, posY)
        if (activityBoss != null) {
            rtBuilder.cellType = CELL_ACTIVITY_BOSS

            val cellBuilder = ActivityBossCell.newBuilder()
            for ((_, locationProto) in pcs.monsterActivityLocationProtoCache.monsterActivityLocationProtoMap) {
                if (posX == locationProto.baseIndex[0] && posY == locationProto.baseIndex[1]) {
                    // 找到当前活动魔物的地区模板
                    val activityBossArea = cache.activityBossAreaCache.findActivityBossAreaByLocationId(locationProto.id)
                    if (activityBossArea == null) {
                        normalLog.error("QueryCell.kt 找不到活动boss区域${locationProto.id}")
                        rtBuilder.rt = ResultCode.NO_PROTO.code
                        return rtBuilder
                    }
                    cellBuilder.disappearTime = (activityBossArea.finishTime / 1000).toInt() //活动魔物消失时间
                    cellBuilder.unlockTime = (activityBossArea.refreshTime / 1000).toInt() //Boss解锁攻击限制的时间(开始后+5s动画时间)
                    break
                }
            }
            if (activityBoss.nowHp > 0) {
                cellBuilder.bossHp = activityBoss.nowHp
                cellBuilder.bossId = activityBoss.bossId
            } else {
                cellBuilder.bossHp = 0
                cellBuilder.bossId = 0
            }

            rtBuilder.setActivityBossCell(cellBuilder)
            return rtBuilder
        }

        // 采集地判定

        val commonResCell = cache.commonResCache.findCommonResByXY(posX, posY)
        if (commonResCell != null) {
            rtBuilder.cellType = CELL_RESOURCE
            val resProto = pcs.resPointProtoCache.resPointMap[commonResCell.resId]
            if (resProto == null) {
                normalLog.error("找不到资源点配置:%d", commonResCell.resId)
                rtBuilder.rt = ResultCode.NO_PROTO.code
                return rtBuilder
            }

            val resWeight = pcs.basicProtoCache.resWeight[resProto.resType] ?: 0.0
            if (resWeight <= 0.0) {
                normalLog.error("资源负重配置错误:%d", resProto.resType)
                rtBuilder.rt = ResultCode.NO_PROTO.code
                return rtBuilder
            }

            val group = cache.walkForceGroupCache.findWalkForceGroupById(commonResCell.groupId)
            if (group == null) {
                rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                return rtBuilder
            }

            val cellBuilder = FarmCell.newBuilder()
            val playerInfoBuilder = this.getPlayerNameInfo(cache, group.mainPlayerId)
            if (playerInfoBuilder == null) {
                normalLog.error("有采集数据，但找不到玩家信息:%d", group.mainPlayerId)
                rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                return rtBuilder
            }
            cellBuilder.setInfo(playerInfoBuilder)
            cellBuilder.groupId = commonResCell.groupId
            cellBuilder.farmWeight = calWeight(cache, commonResCell.groupId)
            cellBuilder.startFarmTime = getTimeSec(commonResCell.farmStartTime)
            cellBuilder.endFarmTime = getTimeSec(commonResCell.farmEndTime)
            rtBuilder.setFarm(cellBuilder)
            return rtBuilder
        }

        val deadBossResCell = cache.deadBossResCache.findDeadBossResByXy(posX, posY)
        if (deadBossResCell != null) {
            rtBuilder.cellType = CELL_RESOURCE
            val resProto = pcs.resPointProtoCache.resPointMap[deadBossResCell.resId]
            if (resProto == null) {
                normalLog.error("找不到资源点配置:%d", deadBossResCell.resId)
                rtBuilder.rt = ResultCode.NO_PROTO.code
                return rtBuilder
            }

            val resWeight = pcs.basicProtoCache.resWeight[resProto.resType] ?: 0.0
            if (resWeight <= 0.0) {
                normalLog.error("资源负重配置错误:%d", resProto.resType)
                rtBuilder.rt = ResultCode.NO_PROTO.code
                return rtBuilder
            }

            val group = cache.walkForceGroupCache.findWalkForceGroupById(deadBossResCell.groupId)
            if (group == null) {
                rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                return rtBuilder
            }

            val cellBuilder = FarmCell.newBuilder()
            val playerInfoBuilder = this.getPlayerNameInfo(cache, group.mainPlayerId)
            if (playerInfoBuilder == null) {
                normalLog.error("有采集数据，但找不到玩家信息:%d", group.mainPlayerId)
                rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                return rtBuilder
            }
            cellBuilder.setInfo(playerInfoBuilder)
            cellBuilder.groupId = deadBossResCell.groupId
            cellBuilder.farmWeight = calWeight(cache, deadBossResCell.groupId)
            cellBuilder.startFarmTime = getTimeSec(deadBossResCell.farmStartTime)
            cellBuilder.endFarmTime = getTimeSec(deadBossResCell.farmEndTime)
            rtBuilder.setFarm(cellBuilder)
            return rtBuilder
        }

        // 玩家城判定
        val castle = cache.castleCache.findCastleByXy(posX, posY)
        if (castle != null) {
            //是玩家城
            rtBuilder.cellType = CELL_CASTLE
            //获取大使馆建筑等级
            val infoByHome = cache.infoByHomeCache.findInfoByHomeByPlayerId(castle.playerId)
            val castleInfo = this.getCastleInfo(cache, castle.playerId)
            castleInfo?.buildingLv = infoByHome?.findBuildingMaxLv(Embassy) ?: 0
            rtBuilder.setCastle(castleInfo)
            return rtBuilder
        }

        //奇观判断
        val rst = pcs.wonderLocationProtoCache.findInWonderType(posX, posY)
        val wonderProto = rst.wonderLocationProto
        if (wonderProto != null && rst.int == WONDER_BASE) {
            val wonder = cache.wonderCache.findWonder(wonderProto.id)
            if (wonder == null) {
                normalLog.error("根据配置Id未找到奇观数据:${wonderProto.id}")
                rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                return rtBuilder
            }
            rtBuilder.cellType = CELL_WONDER
            val cellBuilder = WonderCell.newBuilder()
            cellBuilder.wonderId = wonderProto.id
            cellBuilder.wonderStatus = wonder.status
            if (!isWonderPeace(wonder)) {
                if (wonder.belongToAllianceId == 0L) {
                    cellBuilder.warStartTime = (wonder.warStartTime / 1000).toInt()
                    cellBuilder.warOverTime = (wonder.warFinishTime / 1000).toInt()
                } else {
                    cellBuilder.warStartTime = (wonder.occupyStartTime / 1000).toInt()
                    if (wonder.occupyOverTime <= wonder.warFinishTime) {
                        cellBuilder.warOverTime = (wonder.occupyOverTime / 1000).toInt()
                    } else {
                        cellBuilder.warOverTime = (wonder.warFinishTime / 1000).toInt()
                    }
                }
            } else {
                val ifDelay = if (getNowTime() > wonder.warStartTime && getNowTime() < wonder.warFinishTime) {
                    TRUE
                } else {
                    FALSE
                }
                cellBuilder.warStartTime = (wonder.warStartTime / 1000).toInt() + ifDelay * (7 * 24 * 3600)
                cellBuilder.warOverTime = (wonder.warFinishTime / 1000).toInt() + ifDelay * (7 * 24 * 3600)
            }
            if (wonder.belongToAllianceId != 0L) {
                val allianceMembers = cache.playerCache.findPlayersByAllianceId(wonder.belongToAllianceId)
                // 占领联盟盟主信息玩家
                for (member in allianceMembers) {
                    if (playerIsHavePos(member, ALLIANCE_POSITION_BOSS)) {
                        val infoBuilder = this.getPlayerNameInfo(cache, member.id)
                        if (infoBuilder == null) {
                            continue
                        }
                        val castle = cache.castleCache.findCastleById(member.focusCastleId)
                        if (castle == null) {
                            normalLog.error("未找到玩家的城池，玩家Id:${member.id},城池Id:${member.focusCastleId}")
                            continue
                        }
                        val mainHero = cache.heroCache.findHeroById(member.id, member.mainHeroId)
                        if (mainHero == null) {
                            normalLog.error("未找到玩家设置的领主，玩家Id:${member.id},heroId:${member.mainHeroId}")
                            continue
                        }
                        infoBuilder.posX = castle.x
                        infoBuilder.posY = castle.y
                        infoBuilder.flagColor = member.flagColor
                        infoBuilder.flagEffect = member.flagEffect
                        infoBuilder.flagStyle = member.flagStyle
                        infoBuilder.mainHeroProtoId = mainHero.protoId
                        cellBuilder.setBelongInfo(infoBuilder)
                        break
                    }
                }

                val player = cache.playerCache.findPlayerById(playerId)
                val playerAllianceId = player?.allianceId ?: 0L

                if (wonder.belongToAllianceId == playerAllianceId) {
                    //填充大赦天下次数
                    if (wonder.wonderProtoId == BIG_WONDER) {
                        cellBuilder.amnestyCount = wonder.pardonCount
                    }

                    //同联盟能查询到驻军信息
                    val reinforceRst = findAllWonderReinforce(cache, wonder.wonderProtoId)
                    if (reinforceRst != null) {
                        val commandGroup = reinforceRst.commandGroup
                        cellBuilder.groupId = commandGroup.id
                        cellBuilder.commandPlayerId = commandGroup.mainPlayerId

                        val mainForces = cache.walkForceCache.findWalkForceByWalkForceGroupId(commandGroup.id)
                        forceToMsgBuilder(cache, mainForces).forEach {
                            cellBuilder.addForces(it)
                        }

                        val mainPlayer = cache.playerCache.findPlayerById(commandGroup.mainPlayerId)
                        if (mainPlayer == null) {
                            normalLog.error("有奇观部队，但找不到部队玩家信息:${commandGroup.mainPlayerId}")
                        } else {
                            val baseNum = getResearchEffectValue(cache, NO_FILTER, mainPlayer, JijieTroopMaxAdd)
                            cellBuilder.maxReinforceNum = baseNum + cache.walkForceGroupCache.getSoliderNumInGroup(commandGroup.id)
                        }

                        for (reinforceGroup in reinforceRst.reinforceGroups) {
                            val forces = cache.walkForceCache.findWalkForceByWalkForceGroupId(reinforceGroup.id)
                            forceToMsgBuilder(cache, forces).forEach { cellBuilder.addForces(it) }
                        }
                    }

                }
            }

            rtBuilder.setWonderCell(cellBuilder)
            return rtBuilder
        }

        // 驻扎部队
        val walkForceGroups = cache.walkForceGroupCache.findWalkForceGroupsByPosAndState(posX, posY, Stationed)
        if (walkForceGroups.count() > 0) {
            val group = walkForceGroups[0]
            rtBuilder.cellType = CELL_EMPTY
            val cellBuilder = OccupyCell.newBuilder()
            cellBuilder.setCastle(this.getCastleInfo(cache, group.mainPlayerId))
            cellBuilder.groupId = group.id
            val forces = cache.walkForceCache.findWalkForceByWalkForceGroupId(group.id)
            if (forces.count() > 0) {
                val force = forces[0]
                for (heroId in force.heroIdList) {
                    val hero = cache.heroCache.findHeroById(force.playerId, heroId)
                    if (hero == null) {
                        normalLog.error("找不到部队数据中的英雄数据,部队Id:${force.id},英雄Id:$heroId")
                        continue
                    }
                    val heroBuilder = HeroForWalk.newBuilder()
                    heroBuilder.protoId = hero.protoId
                    heroBuilder.lv = hero.level
                    heroBuilder.starLv = hero.advLv
                    heroBuilder.awake = hero.awake
                    heroBuilder.isLord = boolToInt(hero.mainHeroState != 0)
                    cellBuilder.addHeros(heroBuilder)
                }
                for ((id, num) in force.soliderMap) {
                    val soliderBuilder = SoliderForWalk.newBuilder()
                    soliderBuilder.propId = id
                    soliderBuilder.num = num
                    cellBuilder.addSoliders(soliderBuilder)
                }
            }

            rtBuilder.occupy = cellBuilder.build()
            return rtBuilder
        }

        rtBuilder.rt = ResultCode.NothingOnCell.code
        return rtBuilder
    }

    private fun getPlayerNameInfo(areaCache: AreaCache, playerId: Long): PlayerNameInfo.Builder? {
        val player = areaCache.playerCache.findPlayerById(playerId)
        if (player == null) {
            return null
        }
        val builder = PlayerNameInfo.newBuilder()
        builder.playerId = playerId
        builder.playerName = player.name
        builder.playerPhoto = player.photoProtoId
        builder.allianceId = player.allianceId
        builder.allianceName = player.allianceName
        builder.allianceShortName = player.allianceShortName
        return builder
    }

    private fun getCastleInfo(areaCache: AreaCache, playerId: Long): CastleCell.Builder? {
        val player = areaCache.playerCache.findPlayerById(playerId) ?: return null
        val target = areaCache.targetCache.findMyTargetByPlayerId(playerId) ?: return null

        val castleBuilder = CastleCell.newBuilder()
        castleBuilder.setInfo(this.getPlayerNameInfo(areaCache, playerId))
        castleBuilder.vipLv = areaCache.infoByHomeCache.findInfoByHomeByPlayerId(playerId)?.vipLv ?: 0
        castleBuilder.photoId = player.photoProtoId
        castleBuilder.power = target.getTotalPower()
        castleBuilder.killer = target.totalKill
        castleBuilder.alliancePos = 1 //TODO 待修改

        val office = findOfficeByPlayerId(areaCache, playerId)
        if (office != 0) {
            castleBuilder.office = office
        }

        return castleBuilder
    }
}

