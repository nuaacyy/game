package com.point18.slg2d.world.module.gmCommand

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.getRandInt
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Player
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.common.checkFreeCell
import com.point18.slg2d.world.common.checkMoveCastleFreeCell
import com.point18.slg2d.world.common.noticeCellUpdate
import com.point18.slg2d.world.common.targetAddVal
import java.util.*

class GmCreatePlayer : GmCommand {
    override fun exec(session: PlayerSession, message: String): Int {

        val areaCache = session.areaCache

        val messages = message.split(" ")
        if (messages.size == 1) {
            return 2
        }
        val player = session.player

        if (messages.size != 4) {
            return 2
        }
        val x = messages[2].toIntOrNull()
        if (x == null) {
            return 2
        }

        val y = messages[3].toIntOrNull()
        if (y == null) {
            return 2
        }

        // 校验坐标
        if (!checkMoveCastleFreeCell(areaCache, player.id, x, y)) {
            return 2
        }

        val freeCell = checkFreeCell(areaCache, x, y, player.id)
        if (!freeCell) {
            return 2
        }

        createRobot(areaCache, x * 10000 + y, x, y, false)

        return 1
    }
}

fun createRobot(areaCache: AreaCache, i: Int, x: Int, y: Int, isRandState: Boolean) {
    val nowT = getNowTime()

    val playerProto = pcs.creatingPropertiesCache.proto
    val jjcRank = JJC_RANK_CAN_BE_CHALLENGE + 1 // 创建角色给一个不能被挑战又没有影响的竞技场排名
    val player = Player(
        dbId = (i * 1000000).toLong(),
        id = i.toLong(),
        worldId = areaCache.areaConfig.pltAreaNo,
        user = i.toString(),
        decree = pcs.basicProtoCache.initialEnergy,                   // 政令数量
        decreeTime = nowT,                                              // 最近一次政令结算时间
        accType = NormalAcc,                                  // 角色类型
        birthTime = nowT,                                              // 建号时间
        quitPunishTime = 0,                               // 退盟惩罚结束时间
        photoFreeTime = 0,                               // 可免费换头像时间
        photoProtoId = pcs.basicProtoCache.defaultPhoto,                                      // 头像
        photoFreeCount = pcs.basicProtoCache.photoFreeCount,                  // 免费换头像次数
        wood = playerProto.resource.toLong(),                              // 木料
        iron = playerProto.resource.toLong(),                              // 铁矿
        stone = playerProto.resource.toLong(),                              // 石料
        food = playerProto.resource.toLong(),                              // 粮食
        gold = playerProto.acer.toLong(),                                  // 钻石
        coin = playerProto.gold.toLong(),                                  // 铜币
        bindGold = playerProto.boundDiamond.toLong(),                          // 绑定钻石
        chestFreePrizeTime = nowT,                                              // 免费宝箱领取时间
        chestFreeCnt = 0,                                                 // 免费宝箱数量
        chestKillCnt = 0,                                                 // 击杀宝箱击杀数量
        chestKillPrize = 0,                                                 // 是否已经领取击杀宝箱
        chestKillRefTime = nowT,                                              // 击杀宝箱时间
        honor = playerProto.meritoriousService.toLong(),                    // 荣誉值
        allianceId = 999,                                                 // 所属同盟ID
        allianceAt = 0,                               // 加入联盟时间
        selfIntroduction = "",                                                // 个人简介
        lastLeaveTime = 0,                               // 最后一次离线时间
        lastEnterGameTime = 0,                               // 最近一次上线时间
        areaNo = areaCache.areaConfig.areaNo,                        // 所属区
        accountId = 0,                                         // 关联的账号ID
        rookieEndTime = nowT + pcs.basicProtoCache.playerProtectDuration, // 新手期结束时间
        guideStep = pcs.basicProtoCache.guideFirstId,                    // 新手引导步骤ID
        isSleep = 0,                                                 // 休眠状态
        firstPayTime = 0,                               // 首次充值时间
        lastPayTime = 0,                               // 最后次充值时间
        houseCoin = 0,                                               // 家园币数量
        waijiaoCount = 0,                                                 // 本日外交次数
        lastWaijiaoCount = nowT,                                        // 上次外交时间
        jjcRank = jjcRank,                                           // 初始化玩家名次
        alliancePosMap = hashMapOf(),
        intHeroNum = pcs.basicProtoCache.interiorHeroFirstNum,
        diamondShopInfoMap = hashMapOf(),
        allianceResearchNum = 0,
        lastAllianceResearchTime = nowT,
        weekAllianceResearchReward = 0,
        allianceResearchRewardTime = nowT,
        kingLv = 1,
        allianceName = "nnnjy",
        allianceShortName = "nxx",
        lastAllianceGiftGetTime = nowT,
        allianceGiftGetMap = hashMapOf(),
        name = "",
        allianceCoin = 0,
        heroExpPool = pcs.creatingPropertiesCache.proto.heroExp,
        focusCastleId = 0,
        flagColor = 0,
        flagStyle = 0,
        flagEffect = 0,
        allianceRNum = 0,
        isNotFirstChest = 0,
        banShuNotice = "",
        jjcRankGold = 0,
        isFirstJoinAlliance = 0,
        allianceNickName = "",
        kingExp = 0,
        mainHeroId = 0,
        allianceGiftProtoId = 0,
        allianceGiftNum = 0,
        openAllianceSendGift = 0,
        unitTaskId = 0,
        prisonKillNum = 0,
        loginTime = 0,
        autoHunter = 0,
        lastStrengthTime = nowT,
        strength = pcs.basicProtoCache.firstInstancePower,
        autoUseEnergy = 0,
        joinActivityList = LinkedList(),
        jjcRankJJCoin = 0L,
        wallFireEndTime = 0L,
        maxLvPrisonBuffEndTime = 0,
        joinChatRooms = LinkedList(),
        inMoveServer = 0,
        newPlayerActivity = 1,
        joinAllianceReqs = hashMapOf(),
        allianceOccupyInfo = hashMapOf()
    )

    val (playerWrap, isSuccess) = areaCache.playerCache.createPlayerForGm(player)
    if (!isSuccess || playerWrap == null) {
        return
    }

    // 初始化领主
    val mh = areaCache.heroCache.createHero(player.id, 1001)
    player.mainHeroId = mh!!.id

    // 存储玩家地块类型部位空的土地
    val playerId = player.id
    val action = ACTION_ROLE_ACT
    val areaOnly = areaCache.areaOnlyCache.findAreaOnly()

    // 绑定地图服城池ID
    val castle = areaCache.castleCache.createCastle(playerId, playerWrap.name, 1, x, y, 1, CASTLE_MAIN)
    val castleId = castle.id
    playerWrap.focusCastleId = castleId  // 保存玩家关注的城池

    // 重新修改城等级
    val castleLv = getRandInt(8)
    castle.lv = castleLv + 1

    // 创建角色的时候进行一些玩法模块的数据初始化
    initPlayerGameInfo(areaCache, playerWrap, jjcRank, action)

    // 修改游戏区玩家序号
    areaOnly.nowPlayerNum = areaOnly.nowPlayerNum + 1

    if (isRandState) {
        // 修正城池状态
        val castleStateOdds = getRandInt(10000)
        if (castleStateOdds in 3000..6000) {
            castle.castleState = 3
        } else if (castleStateOdds > 6000) {
            castle.castleState = 4
        }

        // 检测是否送一个防护罩
        val buffOdds = getRandInt(10000)
        if (buffOdds < 5000) {
            areaCache.buffCache.createBuff(playerId, 12002, nowT + 9999999999)
        }

    }

    noticeCellUpdate(areaCache, x, y)
}

/**
创建角色的时候进行一些玩法模块的数据初始化
 */
fun initPlayerGameInfo(areaCache: AreaCache, player: Player, jjcRank: Int, action: Int) {
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
}