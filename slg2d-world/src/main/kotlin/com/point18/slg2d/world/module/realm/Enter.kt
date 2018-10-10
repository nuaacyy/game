package com.point18.slg2d.world.module.realm

import akka.actor.ActorRef
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.moduleFunClass.LocationShareInfo
import com.point18.slg2d.common.moduleFunClass.SimplifiedFightInfo
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.*
import com.point18.slg2d.world.common.createActivityBossEnterTime
import com.point18.slg2d.world.common.createWonderEnterTime
import com.point18.slg2d.world.common.generateDecree
import com.point18.slg2d.world.common.updateAllianceMemberInfo
import com.point18.slg2d.world.event.NormalEvent
import com.point18.slg2d.world.module.ReqDealWithConn
import com.point18.slg2d.world.wpm
import pb4client.*
import pb4client.Notice
import pb4client.Task
import pb4server.ChannelExpiredTell
import java.util.*

class EnterDeal : ReqDealWithConn() {

    override fun dealConnReq(cache: AreaCache, channelActor: ActorRef, msg: MessageLite, playerId: Long) {
        val enterMsg = msg as EnterGame

        val (session, player, rt) = this.enterGame(cache, enterMsg.playerId, channelActor)

        if (session == null || player == null || rt.rt != ResultCode.SUCCESS.code) {
            normalLog.error("玩家${enterMsg.playerId} 进入游戏失败！直接断线")
            channelActor.tell(ChannelExpiredTell.newBuilder().build(), ActorRef.noSender())
            return
        }

        // World 进入游戏结束 发送EnterGame_4 sent
        // 推送4号消息 World 进入游戏结束 发送EnterGame_4
        session.sendMsg(MsgType.EnterGame_4, rt.build())

        // 触发进入游戏事件
        triggerEnterGameEvent(cache, channelActor, player)

        // 推送玩家数据
        noticePlayerData(cache, player)

        // 行军数据
        getMapData(session)
    }

    // 进入游戏
    data class EnterGameReturn(
        val session: PlayerSession?,
        val player: Player?,
        val rt: EnterGameRt.Builder
    )

    private fun triggerEnterGameEvent(areaCache: AreaCache, epNo: ActorRef, player: Player) {
        val playerId = player.id

        // 触发上线事件
        val pos = LinkedList<Int>()
        for ((p, _) in player.alliancePosMap) {
            pos.add(p)
        }

        wpm.es.fire(
            areaCache, playerId, ENTER_GAME,
            NormalEvent(epNo, player.allianceId, pos)
        )
    }

    private fun noticePlayerData(
        areaCache: AreaCache,
        player: Player
    ) {
        /****************************************************** 消息推送 ******************************************************/

        val nowTime = getNowTime()

        player.loginTime = nowTime

        if (player.allianceId != 0L) {
            val updateInfoList = hashMapOf<Int, String>()
            updateInfoList[UpdateOnlineState] = OnlineType.toString()
            updateAllianceMemberInfo(areaCache, player.allianceId, player.id, updateInfoList)
        }

        if (player.isSleep == 1) {
            player.isSleep = 0 //合服之后会将玩家设为1，登录游戏会设为0
        }

        player.lastEnterGameTime = nowTime
    }

    //获取地图服上角色登录所需的数据
    private fun getMapData(session: PlayerSession) {
        val rt = getMapDataForEnter(session)
        session.sendMsg(MsgType.EnterGameMapRt_3137, rt)
    }

    private fun enterGame(
        areaCache: AreaCache,
        playerId: Long,
        channelActor: ActorRef
    ): EnterGameReturn {

        val rt = newEnterGameRt()

        // 查询该用户的数据信息是否存在
        val player = areaCache.playerCache.findPlayerById(playerId)
        if (player == null) {
            rt.rt = ResultCode.CASTLE_DONT_EXISTED.code
            return EnterGameReturn(null, null, rt)
        }

        //创建session
        val session = areaCache.sessionCache.preparePlayerSession(areaCache, channelActor, playerId, player)

        // 登录的玩家可能是流浪后没有选择新的出生地
        val focusCastleId = player.focusCastleId
        if (focusCastleId == 0L) {
            rt.rt = ResultCode.CASTLE_DONT_EXISTED.code
            return EnterGameReturn(null, null, rt)
        }

        val nowTime = getNowTime()
        rt.time = nowTime

        // 玩家信息
        getPlayerInfo(areaCache, player, rt)

        //迷雾信息
        getFogInfo(areaCache, playerId, rt)

        // 玩家的主城、分城、要塞信息
        getAllCastleInfo(areaCache, player, rt)

        // 玩家的武将信息
        getHeroInfo(areaCache, player, rt)

        // 政令信息
        getDecreeInfo(areaCache, player, rt)

        // 世界频道聊天记录信息
        getChatCache(areaCache, playerId, rt)

        // 获取任务信息
        getTaskInfo(areaCache, player, rt)

        // 服务器信息
        getServerInfo(areaCache, rt)

        // 玩家兵营
        getBarracksInfo(areaCache, playerId, rt)

        // 玩家藏兵信息
        getCaveInfo(areaCache, player, rt)

        // 玩家buff信息
        getBuffs(areaCache, player, rt)

        // 玩家活动信息
        /*getPlayerActivityInfo(areaCache, player, rt)*/

        // 活动入口开放时间信息
        getActivityEnterTimeInfo(areaCache, rt)

        //rt.nowSeason = areaCache.areaBase.areaConfig.nowSeason

        return EnterGameReturn(session, player, rt)
    }

    private fun getServerInfo(areaCache: AreaCache, rt: EnterGameRt.Builder) {
        val targetServerConfig = areaCache.areaConfig
        val serverInfo = ServerInfo.newBuilder()
        serverInfo.serverId = targetServerConfig.pltAreaNo
        serverInfo.serverName = targetServerConfig.areaName
        serverInfo.areaNo = targetServerConfig.areaNo
        rt.setServerInfo(serverInfo)
    }

    private fun getFogInfo(areaCache: AreaCache, playerId: Long, rt: EnterGameRt.Builder) {
        val fogs = areaCache.fogCache.findFogByPlayerId(playerId)
        fogs.forEach {
            val fogBuilder = FogInfo.newBuilder()
            fogBuilder.fogId = it.fogId
            fogBuilder.state = it.state
            fogBuilder.power = it.calFogPower()
            rt.addFogs(fogBuilder)
        }
    }

    private fun getPlayerInfo(areaCache: AreaCache, player: Player, rt: EnterGameRt.Builder) {
        // 玩家ID/名字
        val playerId = player.id
        rt.playerId = playerId

        // 开服时间
        //val targetServerConfig = areaCache.areaBase.areaConfig
        //rt.serverOpenTime = (targetServerConfig.areaPublishTime / 1000).toInt()

        //设置总战力
        val target = areaCache.targetCache.findMyTargetByPlayerId(playerId)
        if (target == null) {
            return
        }
        rt.totalPower = target.getTotalPower()
        rt.strength = player.strength
        rt.strengthTime = (player.lastStrengthTime / 1000).toInt()
        val instanceVo = areaCache.instanceCache.findInstance(playerId) ?: return

        rt.nowInstanceId = instanceVo.nowFight
        rt.newPlayerActivity = player.newPlayerActivity
    }

    private fun getBarracksInfo(areaCache: AreaCache, playerId: Long, rt: EnterGameRt.Builder) {
        val barrackMap = areaCache.barracksCache.findBarracksMapByPlayerId(playerId)
        if (barrackMap.size < pcs.soliderCache.soliderProtoMap.size) {
            for ((k, _) in pcs.soliderCache.soliderProtoMap) {
                val barrack = barrackMap[k]
                if (barrack != null) {
                    continue
                }
                barrackMap[k] = areaCache.barracksCache.createBarracks(k, playerId)
            }
        }

        for ((_, b) in barrackMap) {
            val rtVo = BarracksInfo.newBuilder()

            rtVo.soldierId = b.soldierId
            rtVo.soldierNum = b.soldierNum
            rtVo.overTime = (b.overTime / 1000).toInt()
            rtVo.nowMakeNum = b.nowMakeNum
            rtVo.canCureNum = b.canCureNum
            rtVo.canEventCureNum = b.canEventCureNum
            rtVo.cureOverTime = (b.cureOverTime / 1000).toInt()
            rtVo.eventCureOverTime = (b.eventCureOverTime / 1000).toInt()
            rtVo.nowCureNum = b.nowCureNum
            rtVo.nowEventCureNum = b.nowEventCureNum
            rtVo.cureQueue = b.cureQueue
            rtVo.eventCureQueue = b.eventCureQueue
            rtVo.upNum = b.upNum
            rtVo.upToSoliderId = b.upToSoliderId
            rtVo.upOverTime = (b.upOverTime / 1000).toInt()
            rtVo.makeNeedTime = (b.needTime / 1000).toInt()
            rtVo.cureNeedTime = (b.cureNeedTime / 1000).toInt()
            rtVo.eventCureNeedTime = (b.eventCureNeedTime / 1000).toInt()
            rtVo.upNeedTime = (b.upNeedTime / 1000).toInt()

            rt.addBarracks(rtVo)
        }
    }

    private fun getCaveInfo(areaCache: AreaCache, player: Player, rt: EnterGameRt.Builder) {
        val playerId = player.id
        val cave = areaCache.caveCache.findCaveByPlayerId(playerId) ?: return

        val caveInfo = CaveInfo.newBuilder()
        caveInfo.startTime = (cave.hideStartTime / 1000).toInt()
        caveInfo.endTime = (cave.hideOverTime / 1000).toInt()
        caveInfo.holdKingId = 0

        val mainHero = areaCache.heroCache.findHeroById(playerId, player.mainHeroId)
        if (mainHero != null && mainHero.mainHeroState == IN_HIDE) {
            caveInfo.holdKingId = player.mainHeroId
        }
        val forces = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(cave.hideForceGroupId)
        for (force in forces) {
            for ((id, num) in force.soliderMap) {
                val caveSoldier = CaveSoldier.newBuilder()
                caveSoldier.soldierType = id
                caveSoldier.soldierNum = num
                caveInfo.addSoldiers(caveSoldier)
            }
        }
        rt.setInfo(caveInfo)
    }

    private fun getBuffs(areaCache: AreaCache, player: Player, rt: EnterGameRt.Builder) {
        val nowTime = getNowTime()

        val buffs = areaCache.buffCache.findBuffsByPlayerId(player.id)

        for (buff in buffs) {
            rt.addBuffs(newBuffVoBuilder(buff.id, buff.protoId, buff.startTime, buff.overTime))
        }

        //加入全国Buff
        val bigWonder = areaCache.wonderCache.findBigWonder() ?: return

        val rankList = LinkedList<WonderRankVo>(bigWonder.rankInfoMap.values)
        rankList.sortByDescending { it.score }
        val top1AllianceId = if (rankList.size >= 1) rankList[0].allianceId else null
        val top2AllianceId = if (rankList.size >= 2) rankList[1].allianceId else null
        val top3AllianceId = if (rankList.size >= 3) rankList[2].allianceId else null

        for ((_, kingBuff) in bigWonder.buffMap) {
            if (nowTime < kingBuff[0].startTime || nowTime > kingBuff[0].endTime) {
                continue
            }

            rt.addBuffs(
                newBuffVoBuilder(
                    kingBuff[0].id,
                    kingBuff[0].protoId,
                    kingBuff[0].startTime,
                    kingBuff[0].endTime
                )
            )

            val allianceId = player.allianceId
            when (allianceId) {
                top1AllianceId -> rt.addBuffs(
                    newBuffVoBuilder(
                        kingBuff[1].id,
                        kingBuff[1].protoId,
                        kingBuff[1].startTime,
                        kingBuff[1].endTime
                    )
                )
                top2AllianceId -> rt.addBuffs(
                    newBuffVoBuilder(
                        kingBuff[2].id,
                        kingBuff[2].protoId,
                        kingBuff[2].startTime,
                        kingBuff[2].endTime
                    )
                )
                top3AllianceId -> rt.addBuffs(
                    newBuffVoBuilder(
                        kingBuff[3].id,
                        kingBuff[3].protoId,
                        kingBuff[3].startTime,
                        kingBuff[3].endTime
                    )
                )
            }
        }
    }

    private fun newBuffVoBuilder(id: Long, buffProtoId: Int, startTime: Long, endTime: Long): BuffVo.Builder {
        val buffBuilder = BuffVo.newBuilder()
        buffBuilder.buffId = id
        buffBuilder.buffProtoId = buffProtoId
        buffBuilder.buffStartTime = (startTime / 1000).toInt()
        buffBuilder.buffOverTime = (endTime / 1000).toInt()
        return buffBuilder
    }

//    private fun getPlayerActivityInfo(areaCache: AreaCache, player: Player, rt: EnterGameRt.Builder) {
//
//        val playerActivityList = areaCache.playerActivityCache.findPlayerActivityListByPlayerId(player.id)
//        for (playerActivity in playerActivityList) {
//            val eventInProtoMap = pcs.eventInformationProtoCache.protoMap[playerActivity.activityId] ?: continue
//
//            val eventInProto = eventInProtoMap[playerActivity.castleLv] ?: continue
//
//            var myRank = 0
//            val allJoinActivityPlayersByCastleLv =
//                areaCache.playerActivityCache.findAllPlayerActivityInfoMap(playerActivity.activityId)
//            val p = allJoinActivityPlayersByCastleLv[eventInProto.id]
//            if (p != null) {
//                for (index in p.indices) {
//                    val info = p[index]
//                    if (info.playerId == player.id) {
//                        myRank = index + 1
//                        break
//                    }
//                }
//            }
//
//            val playerActivityP = PlayerActivity.newBuilder()
//            playerActivityP.activityId = playerActivity.activityId
//            playerActivityP.score = playerActivity.score
//            playerActivityP.rank = myRank
//            playerActivityP.isActivityOver = 0
//            rt.addPlayerActivitys(playerActivityP)
//        }
//    }

    private fun getActivityEnterTimeInfo(areaCache: AreaCache, rt: EnterGameRt.Builder) {
        // 奇观战入口开放时间
        rt.addEnterTimeInfo(createWonderEnterTime(areaCache, WONDER_WAR_GATE))

        // 奇观战活动时间
        rt.addEnterTimeInfo(createWonderEnterTime(areaCache, WONDER_WAR_ACTIVITY))

        // 四天龙入口开放时间
        rt.addEnterTimeInfo(createActivityBossEnterTime(areaCache, FOUR_DRAGON_GATE))

        // 四天龙活动时间
        rt.addEnterTimeInfo(createActivityBossEnterTime(areaCache, FOUR_DRAGON_ACTIVITY))
    }

    private fun getHeroInfo(areaCache: AreaCache, player: Player, rt: EnterGameRt.Builder) {
        val playerId = player.id
        val heroList = areaCache.heroCache.findHeroListByType(playerId)
        for (hero in heroList) {
            val heroStateBuilder = HeroStateInFo.newBuilder()
            heroStateBuilder.heroId = hero.id
            heroStateBuilder.heroProtoId = hero.protoId
            heroStateBuilder.mainHeroState = hero.mainHeroState
            heroStateBuilder.heroPosState = hero.posState
            heroStateBuilder.intHeroAddress = hero.intHeroAddress
            heroStateBuilder.mainHeroStartTime = (hero.mainHeroStateStartTime / 1000).toInt()
            heroStateBuilder.mainHeroOverTime = (hero.mainHeroStateEndTime / 1000).toInt()
            rt.addHeroStateInfo(heroStateBuilder)
        }

    }

    private fun getChatCache(areaCache: AreaCache, playerId: Long, rt: EnterGameRt.Builder) {

        val history = areaCache.chatCache.getWorldChatHistory(0)
        val chatInfoList = LinkedList<ChatInfo>()
        for (eachChat in history) {
            val chatInfoBuilder = ChatInfo.newBuilder()
            chatInfoBuilder.id = eachChat.id
            chatInfoBuilder.type = CHAT_TYPE_WORLD
            chatInfoBuilder.isSystem = 1
            chatInfoBuilder.country = 24
            chatInfoBuilder.areaNo = 0
            chatInfoBuilder.redBagState = 0
            chatInfoBuilder.allianceName = eachChat.allianceName
            chatInfoBuilder.allianceShortName = eachChat.allianceName
            chatInfoBuilder.alliancePositions = eachChat.alliancePos.toString()
            chatInfoBuilder.player = eachChat.playerName
            chatInfoBuilder.playerId = eachChat.playerId
            chatInfoBuilder.playerShortName = eachChat.playerShortName
            chatInfoBuilder.playerIcon = eachChat.iconProtoId
            chatInfoBuilder.sendTime = (eachChat.chatTime / 1000).toInt()
            chatInfoBuilder.messageType = eachChat.msgType
            chatInfoBuilder.office = eachChat.wonderPos
            chatInfoBuilder.vipLv = eachChat.vipLv
            val noticeBuilder = Notice.newBuilder()
            noticeBuilder.readType = eachChat.readType
            noticeBuilder.noticeLanId = eachChat.msg

            if (eachChat.msgType == FIGHT_INFO_SHARE) {
                noticeBuilder.noticeLanId = ""
                val fightInfo = toObj<SimplifiedFightInfo>(eachChat.msg)
                val easyFightInfo = SimpleFightReport.newBuilder()
                easyFightInfo.reportType = fightInfo.reportType
                easyFightInfo.mainPlayer = fightInfo.mainPlayer
                easyFightInfo.mainPlayerAlliance = fightInfo.mainPlayerAlliance
                easyFightInfo.atkOrDef = fightInfo.atkOrDef
                easyFightInfo.targetName = fightInfo.targetName
                easyFightInfo.allianceOrLv = fightInfo.allianceOrLv
                easyFightInfo.reportId = fightInfo.reportId
                easyFightInfo.mainIconId = fightInfo.mainIconId
                easyFightInfo.iconId = fightInfo.iconId
                easyFightInfo.monsterId = fightInfo.monster
                easyFightInfo.world = fightInfo.worldId
                chatInfoBuilder.easyFightInfo = easyFightInfo.build()
            } else if (eachChat.msgType == LOCATION_SHARE) {
                val location = toObj<LocationShareInfo>(eachChat.msg)
                chatInfoBuilder.x = location.x
                chatInfoBuilder.y = location.y
                noticeBuilder.noticeLanId = location.locationName
            }

            chatInfoBuilder.message = noticeBuilder.build()
            chatInfoList.add(chatInfoBuilder.build())
        }
        rt.addAllChatInfos(chatInfoList)

        return
    }

    private fun getDecreeInfo(areaCache: AreaCache, player: Player, rt: EnterGameRt.Builder) {
        // 政令信息
        val generateDecreeRt = generateDecree(areaCache, player, false)

        val decreeChange = DecreeChange.newBuilder()
        decreeChange.time = (player.decreeTime / 1000).toInt()
        decreeChange.decreeLimit = generateDecreeRt.decreeLimit
        decreeChange.decreeNum = player.decree

        rt.decreeChange = decreeChange.build()
    }

    private fun getTaskInfo(areaCache: AreaCache, player: Player, rt: EnterGameRt.Builder) {
        // 任务信息
        val tasks = areaCache.taskCache.findAllTaskByPlayerId(player.id)
        for (task in tasks) {
            val taskProto = pcs.questCache.findSpecTaskProto(task.taskProtoId) ?: continue
            if (taskProto.type != TaskChapter) {
                if (task.taskNowState == TaskHasGetReward) {
                    continue
                }
            }

            val taskVo = Task.newBuilder()
            taskVo.taskId = task.id
            taskVo.taskProtoId = task.taskProtoId
            taskVo.taskState = task.taskNowState
            taskVo.taskFinish = task.taskFinish
            if (task.overTime == -1L) {
                taskVo.overTime = -1
            } else {
                taskVo.overTime = (task.overTime / 1000).toInt()
            }
            rt.addTasks(taskVo)
        }
    }

    private fun getAllCastleInfo(areaCache: AreaCache, player: Player, rt: EnterGameRt.Builder) {
        // 将所有的城池Id发出去
        val playerId = player.id
        val castles = areaCache.castleCache.findCastleFromPlayerId(playerId)
        for (castle in castles) {
            // 把这个城池中的部队取出来给客户端
            // 分割
            val c = CityInfo.newBuilder()

            // 获取城池信息
            val lv = castle.lv
            val x = castle.x
            val y = castle.y
            val castleType = castle.type

            c.name = castle.name
            c.lv = lv
            c.x = x
            c.y = y
            c.cityType = castleType
            c.cityId = castle.id

            rt.addCityInfo(c)
        }
    }

    private fun newEnterGameRt(): (EnterGameRt.Builder) {
        val enterGameRt = EnterGameRt.newBuilder()
        enterGameRt.rt = ResultCode.SUCCESS.code

        val nowTime = getNowTime()

        val yields = YieldChange.newBuilder()
        yields.addWood = 0
        yields.addIron = 0
        yields.addStone = 0
        yields.addFood = 0
        yields.addCoin = 0
        yields.calcTime = (nowTime / 1000).toInt()

        val storageLimit = StoreLimitChange.newBuilder()
        storageLimit.woodLimit = 0
        storageLimit.ironLimit = 0
        storageLimit.stoneLimit = 0
        storageLimit.foodLimit = 0
        storageLimit.coinLimit = 0

        enterGameRt.playerId = 0

        val serverInfo = ServerInfo.newBuilder()
        serverInfo.serverId = 0
        serverInfo.serverName = ""
        serverInfo.areaNo = 0
        enterGameRt.setServerInfo(serverInfo)

        val caveInfo = CaveInfo.newBuilder()
        caveInfo.startTime = 0
        caveInfo.endTime = 0
        caveInfo.holdKingId = 0
        enterGameRt.setInfo(caveInfo)

        //todo 临时补全enterGameRt
        //enterGameRt.maxRank = 0
        //enterGameRt.missionId = 0
        //enterGameRt.missionDurability = 0
        enterGameRt.totalPower = 0
        //enterGameRt.allianceTreasureNextRefTime = 0
        enterGameRt.strength = 0
        enterGameRt.strengthTime = 0
        enterGameRt.nowInstanceId = 0
        enterGameRt.time = 0L
        enterGameRt.decreeChange = DecreeChange.newBuilder().build()

        return enterGameRt
    }
}