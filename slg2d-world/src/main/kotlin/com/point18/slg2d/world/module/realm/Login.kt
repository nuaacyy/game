package com.point18.slg2d.world.module.realm

import akka.actor.ActorRef
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.HeroEquipVo
import com.point18.slg2d.common.msgtrans.ScMessageAtSend
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Player
import com.point18.slg2d.world.area4data.updateIntAddressId
import com.point18.slg2d.world.common.findFreeCastlePos
import com.point18.slg2d.world.common.refreshAllHeroPower
import com.point18.slg2d.world.common.targetAddVal
import com.point18.slg2d.world.event.GainHero
import com.point18.slg2d.world.module.ReqDealWithConn
import com.point18.slg2d.world.wpm
import pb4client.Login
import pb4client.LoginRt
import pb4server.MakeCityAskHomeReq
import pb4server.World2HomeAskResp
import xyz.ariane.util.lzWarn
import java.util.*

class LoginDeal : ReqDealWithConn() {

    override fun dealConnReq(cache: AreaCache, channelActor: ActorRef, msg: MessageLite, playerId: Long) {
        val reqMsg = msg as Login
        val account = reqMsg.account
        val worldId = reqMsg.sid.toLongOrNull() //客户端传
        val currentClientMsgNo = cache.currentClientMsgNo
        if (worldId == null) {
            normalLog.lzWarn { "客户端传的world是错误的:${reqMsg.sid}" }
            returnMsg(ResultCode.PARAMETER_ERROR.code, channelActor, currentClientMsgNo, 0, 0)
            return
        }

        //todo 校验world
        if (account == "") {
            normalLog.lzWarn { "客户端传的账号名是空字符串" }
            returnMsg(ResultCode.PARAMETER_ERROR.code, channelActor, currentClientMsgNo, 0, 0)
            return
        }

        val channelId = "test"
        val accOpId = 0

        //todo 解析账号信息、判断黑白名单
        val p = cache.playerCache.findPlayerByUser(account)
        if (p == null) {
            // 新号 进行角色创建 并且到home去创建相关信息
            val createPlayerId = wpm.generateObjIdNew(cache) // 弄出来playerId
            val dbId = wpm.generateObjIdNew(cache)
            //查找可用位置
            val (x, y) = findFreeCastlePos(cache)
            if (x == -1 && y == -1) {
                returnMsg(ResultCode.NO_VALID_BORN_POS.code, channelActor, currentClientMsgNo, createPlayerId, worldId)
                return
            }

            // 创建玩家信息
            val playerProto = pcs.creatingPropertiesCache.proto
            val nowT = getNowTime()

            val jjcRank =
                JJC_RANK_CAN_BE_CHALLENGE + cache.playerCache.findPlayerNum() + 1 // 创建角色给一个不能被挑战又没有影响的竞技场排名

            val player = Player(
                dbId,
                cache.areaConfig.pltAreaNo,
                createPlayerId,
                account,
                cache.areaConfig.areaNo,                        // 所属区
                "",
                pcs.basicProtoCache.initialEnergy,                   // 政令数量
                nowT,                                              // 最近一次政令结算时间
                playerProto.resource.toLong(),                              // 木料
                playerProto.resource.toLong(),                              // 铁矿
                playerProto.resource.toLong(),                              // 石料
                playerProto.resource.toLong(),                              // 粮食
                playerProto.acer.toLong(),                                  // 钻石
                playerProto.boundDiamond.toLong(),                          // 绑定钻石
                playerProto.gold.toLong(),                                  // 铜币
                playerProto.meritoriousService.toLong(),                    // 荣誉值
                0,
                pcs.creatingPropertiesCache.proto.heroExp,
                0,
                0,                                                 // 所属同盟ID
                0,                               // 加入联盟时间
                hashMapOf(),
                "",
                "",
                0,
                0,
                0,
                0,
                0,                               // 退盟惩罚结束时间
                "",                                                // 个人简介
                0,                               // 最近一次上线时间
                0,                               // 最后一次离线时间
                nowT,                                              // 建号时间
                nowT + pcs.basicProtoCache.playerProtectDuration, // 新手期结束时间
                nowT,                                              // 免费宝箱领取时间
                0,                                                 // 免费宝箱数量
                nowT,                                              // 击杀宝箱时间
                0,                                                 // 击杀宝箱击杀数量
                0,                                                 // 是否已经领取击杀宝箱
                0,
                0,                               // 可免费换头像时间
                pcs.basicProtoCache.defaultPhoto,                                      // 头像
                pcs.basicProtoCache.photoFreeCount,                  // 免费换头像次数
                pcs.basicProtoCache.guideFirstId,                    // 新手引导步骤ID
                NormalAcc,                                  // 角色类型
                0,                                                 // 休眠状态
                0,                               // 首次充值时间
                0,                               // 最后次充值时间
                "",
                0,                                               // 家园币数量
                0,                                                 // 本日外交次数
                nowT,                                        // 上次外交时间
                jjcRank,                                           // 初始化玩家名次
                0,
                0,
                0,
                "",
                pcs.basicProtoCache.interiorHeroFirstNum,
                hashMapOf(),
                0,
                nowT,
                0,
                nowT,
                1,
                0,
                0,
                hashMapOf(),
                nowT,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                pcs.basicProtoCache.firstInstancePower,
                nowT,
                LinkedList(),
                0,                                         // 关联的账号ID
                0,
                0,
                0,
                LinkedList(),
                0,
                1,
                hashMapOf(),
                hashMapOf()
            )

            val (playerWrap, isSuccess) = cache.playerCache.createPlayer(player)
            if (!isSuccess || playerWrap == null) {
                returnMsg(ResultCode.SERVER_MAX_PLAYER.code, channelActor, currentClientMsgNo, createPlayerId, worldId)
                return
            }

            // 存储玩家地块类型部位空的土地
            val action = ACTION_ROLE_ACT
            val areaOnly = cache.areaOnlyCache.findAreaOnly()

            // 绑定地图服城池ID
            val castle = cache.castleCache.createCastle(createPlayerId, playerWrap.name, 0, x, y, 1, CASTLE_MAIN)
            val castleId = castle.id
            playerWrap.focusCastleId = castleId // 保存玩家关注的城池

            // 创建角色的时候进行一些玩法模块的数据初始化
            initPlayerGameInfo(cache, playerWrap, jjcRank, action)

            // 修改游戏区玩家序号
            areaOnly.nowPlayerNum += 1

            val makeCityAskToHome = MakeCityAskHomeReq.newBuilder()
            makeCityAskToHome.castleId = castle.id
            makeCityAskToHome.playerName = playerWrap.name
            makeCityAskToHome.account = account
            makeCityAskToHome.channelId = channelId
            makeCityAskToHome.accOpId = accOpId
            makeCityAskToHome.worldId = worldId
            makeCityAskToHome.playerId = createPlayerId
            makeCityAskToHome.areaNo = player.areaNo

            cache.worldActor.createACS<World2HomeAskResp>()
                .ask(
                    cache.worldActor.homeShardRegion,
                    cache.fillW2HAskMsgHeader(createPlayerId) {
                        it.setMakeCityAskHomeReq(makeCityAskToHome)
                    },
                    World2HomeAskResp::class.java
                )
                .whenCompleteKt { hrt, err ->
                    try {
                        when {
                            err != null -> {
                                returnMsg(
                                    ResultCode.ASK_ERROR1.code,
                                    channelActor,
                                    currentClientMsgNo,
                                    createPlayerId,
                                    worldId
                                )
                            }
                            hrt == null -> {
                                returnMsg(
                                    ResultCode.ASK_ERROR2.code,
                                    channelActor,
                                    currentClientMsgNo,
                                    createPlayerId,
                                    worldId
                                )
                            }
                            else -> {
                                val askRt = hrt.makeCityAskHomeRt
                                if (askRt.rt != ResultCode.SUCCESS.code) {
                                    returnMsg(askRt.rt, channelActor, currentClientMsgNo, createPlayerId, worldId)
                                } else {
                                    // 初始化的时候赠送一些东西给玩家
                                    addResForMakeCity(cache, askRt.initHeroMapList, playerWrap)
                                    returnMsg(askRt.rt, channelActor, currentClientMsgNo, createPlayerId, worldId)
                                }
                            }
                        }
                    } catch (e: Exception) {
                        normalLog.error("makeCityAskToHome Error!", e)
                        returnMsg(ResultCode.ASK_ERROR3.code, channelActor, currentClientMsgNo, createPlayerId, worldId)
                    }
                }
        } else {
            // 老号 返回登录成功消息 等待客户端发送进入游戏消息号
            returnMsg(ResultCode.SUCCESS.code, channelActor, currentClientMsgNo, p.id, worldId)
        }
    }

    /**
    创建角色的时候进行一些玩法模块的数据初始化
     */
    private fun initPlayerGameInfo(areaCache: AreaCache, player: Player, jjcRank: Int, action: Int) {
        val playerId = player.id
        // 初始化任务
        for (taskProtoId in pcs.basicProtoCache.firstTask) {
            areaCache.taskCache.createTask(taskProtoId, 0, 0, playerId)
        }

        // 初始化玩家的竞技场数据
        areaCache.jjcCache.createJjc(playerId, jjcRank)

        // 初始化每周一刷的排行
        areaCache.targetCache.createMyTarget(playerId)

        //进行初始化
        targetAddVal(areaCache, playerId, NowJJcRank)

        // 初始化玩家的推图数据
        areaCache.instanceCache.createInstance(playerId)

        // 初始化成就
        areaCache.achievementCache.initAchievementInfo(playerId)

        // 来自home服的数据的维护
        areaCache.infoByHomeCache.createInfoByHome(playerId)

        //初始化迷雾
        areaCache.fogCache.createFog(playerId)

        // 初始化玩家设置
        areaCache.playerSettingCache.createPlayerSetting(playerId)
    }

    /**
    创建角色的时候赠送一些初始物品
     */
    private fun addResForMakeCity(
        areaCache: AreaCache,
        initHeroList: List<pb4server.HeroForWorldMakeCity>,
        player: Player
    ) {
        // 新玩家进入在数据库中写武将
        for (initHero in initHeroList) {
            val protoId = initHero.heroProtoId
            val id = initHero.heroId
            val heroProto = pcs.unitBaseCache.protoMap[protoId]
            if (heroProto == null) {
                continue
            }
            val equipMap = hashMapOf<Int, HeroEquipVo>()
            heroProto.heroTrophiesMap.forEach {
                equipMap[it.key] = HeroEquipVo(it.value)
            }
            val hero = areaCache.heroCache.createHero(player.id, id, protoId, equipMap)
            // 设置为守城英雄
            if (player.mainHeroId == 0L && hero != null) {
                player.mainHeroId = hero.id
                hero.mainHeroState = MAIN_HERO
                updateIntAddressId(hero, 1)
            }

            if (hero != null) {
                wpm.es.fire(areaCache, player.id, GET_NEW_HERO, GainHero(hero.id))
            }
        }

        //刷新英雄实力
        refreshAllHeroPower(areaCache, player.id, false)
    }

    private fun returnMsg(
        rt: Int,
        channelActor: ActorRef,
        currentClientMsgNo: Int,
        playerId: Long,
        worldId: Long = 0L
    ) {
        val rtMsgBuilder = LoginRt.newBuilder()
        rtMsgBuilder.rt = rt
        rtMsgBuilder.playerId = playerId
        rtMsgBuilder.worldId = worldId

        val rtMsgBuilderBuild = rtMsgBuilder.build()
        val scMsg = ScMessageAtSend(MsgType.Login_1, currentClientMsgNo, rtMsgBuilderBuild)

        channelActor.tell(scMsg, ActorRef.noSender())

    }
}