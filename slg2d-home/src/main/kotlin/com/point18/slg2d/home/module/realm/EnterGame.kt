package com.point18.slg2d.home.module.realm

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.*
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.pc.randomSelect
import com.point18.slg2d.common.pc.resVoToResString
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.*
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus27
import com.point18.slg2d.home.helper.IDcContainer27
import com.point18.slg2d.home.module.HomeClientMsgDeal
import com.point18.slg2d.home.module.event.BuildingUpFinishEvent
import com.point18.slg2d.home.module.event.EnterGameEvent
import com.point18.slg2d.home.module.event.GainHeroEvent
import com.point18.slg2d.home.module.event.ResearchEffectChangeEvent
import com.point18.slg2d.home.module.fireEvent
import com.point18.slg2d.home.msgnotice.createGetVipLoginRewardNotifier
import com.point18.slg2d.home.msgnotice.createHeroPbByProps
import com.point18.slg2d.home.msgnotice.createNewChatWindowNotifier
import pb4client.*
import pb4client.BagInfo
import pb4client.EquipProp
import pb4client.EquipProps
import pb4client.FriendApply
import pb4client.KingEquipCardInfo
import pb4server.*
import java.util.*
import java.util.Arrays.asList

class DcContainer27(
) : IDcContainer27<BagDC, BankAccelerateDC, BankDC, BattleReportDC, BlackPlayerDC,
        FriendApplyDC, FriendChatRecordDC, FriendDC, FriendGroupDC, GiftBagDC,
        HeroDC, HomeAchievementDC, HomePlayerDC, HomeSyncDC, HomeTaskDC,
        InMakeKingEquipDC, InnerCityDC, JjcHomeDC, LibraryDC, LotteryDC,
        MailDC, MarkDC, MerchantShipDC, NewEquipDC, ResourceYieldDC,
        SkinDC, VipDC> {

    lateinit var bagDC: BagDC
    lateinit var bankAccelerateDC: BankAccelerateDC
    lateinit var bankDC: BankDC
    lateinit var battleReportDC: BattleReportDC
    lateinit var blackPlayerDC: BlackPlayerDC
    lateinit var friendApplyDC: FriendApplyDC
    lateinit var friendChatRecordDC: FriendChatRecordDC
    lateinit var friendDC: FriendDC
    lateinit var friendGroupDC: FriendGroupDC
    lateinit var giftBagDC: GiftBagDC
    lateinit var heroDC: HeroDC
    lateinit var homeAchievementDC: HomeAchievementDC
    lateinit var homePlayerDC: HomePlayerDC
    lateinit var homeSyncDC: HomeSyncDC
    lateinit var homeTaskDC: HomeTaskDC
    lateinit var inMakeKingEquipDC: InMakeKingEquipDC
    lateinit var innerCityDC: InnerCityDC
    lateinit var jjcHomeDC: JjcHomeDC
    lateinit var libraryDC: LibraryDC
    lateinit var lotteryDC: LotteryDC
    lateinit var mailDC: MailDC
    lateinit var markDC: MarkDC
    lateinit var merchantShipDC: MerchantShipDC
    lateinit var equipDC: NewEquipDC
    lateinit var resourceYieldDC: ResourceYieldDC
    lateinit var skinDC: SkinDC
    lateinit var vipDC: VipDC

    override fun configDcs(
        dc1: BagDC, dc2: BankAccelerateDC, dc3: BankDC, dc4: BattleReportDC, dc5: BlackPlayerDC,
        dc6: FriendApplyDC, dc7: FriendChatRecordDC, dc8: FriendDC, dc9: FriendGroupDC, dc10: GiftBagDC,
        dc11: HeroDC, dc12: HomeAchievementDC, dc13: HomePlayerDC, dc14: HomeSyncDC, dc15: HomeTaskDC,
        dc16: InMakeKingEquipDC, dc17: InnerCityDC, dc18: JjcHomeDC, dc19: LibraryDC, dc20: LotteryDC,
        dc21: MailDC, dc22: MarkDC, dc23: MerchantShipDC, dc24: NewEquipDC, dc25: ResourceYieldDC,
        dc26: SkinDC, dc27: VipDC
    ) {
        this.bagDC = dc1
        this.bankAccelerateDC = dc2
        this.bankDC = dc3
        this.battleReportDC = dc4
        this.blackPlayerDC = dc5
        this.friendApplyDC = dc6
        this.friendChatRecordDC = dc7
        this.friendDC = dc8
        this.friendGroupDC = dc9
        this.giftBagDC = dc10
        this.heroDC = dc11
        this.homeAchievementDC = dc12
        this.homePlayerDC = dc13
        this.homeSyncDC = dc14
        this.homeTaskDC = dc15
        this.inMakeKingEquipDC = dc16
        this.innerCityDC = dc17
        this.jjcHomeDC = dc18
        this.libraryDC = dc19
        this.lotteryDC = dc20
        this.mailDC = dc21
        this.markDC = dc22
        this.merchantShipDC = dc23
        this.equipDC = dc24
        this.resourceYieldDC = dc25
        this.skinDC = dc26
        this.vipDC = dc27
    }

    override fun <T> invoke(c: () -> Unit) {
        c()
    }
}

// 进入游戏
class EnterGameDeal(
    private val targetHelper: TargetHelper = TargetHelper(),
    private val refreshResHelper: RefreshResourceHelper = RefreshResourceHelper(),
    private val conditionHelper: UiConditionCheckHelper = UiConditionCheckHelper(),
    private val vipHelper: VipHelper = VipHelper(),
    private val resHelper: ResHelper = ResHelper(),
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper(),
    private val propHelper: PropsHelper = PropsHelper()
) : HomeClientMsgDeal, HomeHelperPlus27<BagDC, BankAccelerateDC, BankDC, BattleReportDC, BlackPlayerDC, FriendApplyDC,
        FriendChatRecordDC, FriendDC, FriendGroupDC, GiftBagDC, HeroDC, HomeAchievementDC, HomePlayerDC, HomeSyncDC,
        HomeTaskDC, InMakeKingEquipDC, InnerCityDC, JjcHomeDC, LibraryDC, LotteryDC, MailDC, MarkDC,
        MerchantShipDC, NewEquipDC, ResourceYieldDC, SkinDC, VipDC>(
    BagDC::class.java,
    BankAccelerateDC::class.java,
    BankDC::class.java,
    BattleReportDC::class.java,
    BlackPlayerDC::class.java,
    FriendApplyDC::class.java,
    FriendChatRecordDC::class.java,
    FriendDC::class.java,
    FriendGroupDC::class.java,
    GiftBagDC::class.java,
    HeroDC::class.java,
    HomeAchievementDC::class.java,
    HomePlayerDC::class.java,
    HomeSyncDC::class.java,
    HomeTaskDC::class.java,
    InMakeKingEquipDC::class.java,
    InnerCityDC::class.java,
    JjcHomeDC::class.java,
    LibraryDC::class.java,
    LotteryDC::class.java,
    MailDC::class.java,
    MarkDC::class.java,
    MerchantShipDC::class.java,
    NewEquipDC::class.java,
    ResourceYieldDC::class.java,
    SkinDC::class.java,
    VipDC::class.java,
    asList(targetHelper, refreshResHelper, conditionHelper, vipHelper, resHelper, effectHelper, propHelper)
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val dcC = DcContainer27()
        prepare(session, dcC) {
            val player = dcC.homePlayerDC.player
            session.worldId = player.worldId

            val rt = EnterGameHomeRt.newBuilder()
            rt.worldId = player.worldId
            rt.playerName = player.name
            rt.photoProtoId = player.photoProtoId
            rt.kingLv = player.kingLv
            rt.kingExp = player.kingExp
            rt.mainHeroId = player.mainHeroId
            rt.nowKingEquipPlan = player.nowUseKingEquipPlan
            rt.isFirstJoinAlliance = player.isFirstJoinAlliance
            rt.nowUnitTaskId = player.unitTaskId

            // 首次登录逻辑
            if (dcC.homePlayerDC.player.isFirstEnterGame == FIRST_ENTER_GAME) {
                // 内城建筑的效果初始化
                innerCityEffectInit(session, dcC.innerCityDC, dcC.homePlayerDC)

                heroInit(session, dcC.heroDC)

                // 初始化任务
                for (taskProtoId in pcs.basicProtoCache.firstTask) {
                    // 判断玩家是否已经有这个任务了
                    val t = dcC.homeTaskDC.findTaskByProtoId(taskProtoId)
                    if (t != null) {
                        continue
                    }

                    // 判断任务模板是否存在
                    val taskProto = pcs.questCache.findSpecTaskProto(taskProtoId)
                    if (taskProto == null) {
                        continue
                    }

                    // 创建任务
                    dcC.homeTaskDC.createTask(taskProto, 0)
                }

                // 初始化成就
                dcC.homeAchievementDC.initAchievementInfo(session)

                //初始化皮肤
                skinEffectInit(session, dcC.skinDC)

                // 送一批道具
                for (r in pcs.basicProtoCache.guideGetItemMap) {
                    if (r.resType != RES_PROPS) {
                        continue
                    }
                    propHelper.getProps(session, r.subType.toInt(), r.num.toInt())
                }

                dcC.homePlayerDC.player.isFirstEnterGame = OLD_PLAYER
            }

            // 初始化章节任务
            initTaskInfo(dcC.homeTaskDC, session, player)

            // 初始化抽卡池
            dcC.lotteryDC.initLotteryInfo(session)

            getLibraryInfo(rt, dcC.libraryDC)

            getOnlineRewardInfo(session, rt, dcC.homePlayerDC)

            getInstanceInfo(rt, dcC.homePlayerDC)

            getBankInfo(rt, dcC.bankDC, dcC.bankAccelerateDC)

            getInMakeKingEquip(rt, dcC.homePlayerDC, dcC.inMakeKingEquipDC)

            getTalent(rt, dcC.homePlayerDC)

            getVip(rt, dcC.vipDC)

            getGuidelineState(rt, dcC.homePlayerDC)

            getResearchInfo(rt, dcC.homePlayerDC)

            getMail(rt, dcC.mailDC)

            getMerchantShip(rt, dcC.merchantShipDC)

            getMark(rt, dcC.markDC)

            getJjc(rt, dcC.jjcHomeDC, dcC.homeSyncDC)

            getResInfo(session, rt, dcC.homePlayerDC, dcC.resourceYieldDC)

            getAllBuildings(rt, dcC.homePlayerDC, dcC.innerCityDC)

            getUnlockArea(rt, dcC.homePlayerDC)

            getHeroInfo(rt, dcC.heroDC)

            getBagInfo(rt, dcC.equipDC, dcC.bagDC)

            getFriendApplyInfo(rt, dcC.friendApplyDC)

            getFriendInfo(rt, dcC.friendDC, dcC.homePlayerDC, dcC.friendChatRecordDC)

            getFriendGroupInfo(rt, dcC.friendGroupDC)

            getBlackPlayerInfo(rt, dcC.blackPlayerDC)

            getAchievementInfo(session, rt, dcC.homeAchievementDC)

            getTaskInfo(rt, dcC.homeTaskDC)

            getGuidelineState(rt, dcC.homePlayerDC)

            getBattleReportInfo(rt, dcC.battleReportDC)

            getTimeBoxInfo(rt, dcC.homePlayerDC)

            ckeckGiftBag(rt, dcC.giftBagDC)

            checkMonthCard(rt, dcC.homePlayerDC)

            session.sendMsg(MsgType.EnterGameHomeRt_3200, rt.build())

            // 广播时过滤掉黑名单的人消息
            fillBlackListEnterGame(session, dcC.blackPlayerDC)

            // 订阅全部相关频道
            subscribeAllChannel(session, dcC.homePlayerDC)

            // 处理完登录后的处理
            getVipRewardDataForEnter(session, dcC.vipDC, dcC.homePlayerDC)

            // 去公共服拿聊天室数据
            enterGameChatRoomInfo(session, dcC.homePlayerDC)

            // 拿最近的陌生人聊天
            enterGameStrangerInfo(session, dcC.homePlayerDC, dcC.friendDC, dcC.friendChatRecordDC)

            fireEvent(session, EnterGameEvent())
        }
    }


    /**
     * 初始化玩家任务
     */
    private fun initTaskInfo(homeTaskDC: HomeTaskDC, session: PlayerActor, player: HomePlayer) {
        val unitId = player.unitTaskId + 1
        var tasks: List<Int> = listOf()
        val unitTaskProto = pcs.unitTaskProtoCache.unitTaskProtoMap[unitId]
        if (unitTaskProto != null) {
            tasks = unitTaskProto.tasks
        }

        for (questDictVoId in tasks) {
            // 初始化任务
            val taskProto = pcs.questCache.findSpecTaskProto(questDictVoId)
            if (taskProto == null) {
                println("home服任务创建失败,任务ID为:$questDictVoId")
                continue
            }

            if (taskProto.type != TaskChapter) {
                continue
            }

            // 判断玩家是否有这个任务了。
            val playerTask = homeTaskDC.findTaskByProtoId(questDictVoId)
            if (playerTask != null) {
                continue
            }

            val task = homeTaskDC.createTask(taskProto, 0)

            if (task.onWorld == 1) {
                // 世界任务,转移到world,home本地存一个底
                val tell = CreateTaskToWorldTell.newBuilder()
                tell.taskProtoId = questDictVoId
                session.tellWorld(session.fillHome2WorldTellMsgHeader {
                    it.setCreateTaskToWorldTell(tell)
                })
            }
        }
    }

    private fun subscribeAllChannel(session: PlayerActor, homePlayerDC: HomePlayerDC) {
        // 订阅世界频道
        session.subscribeChannel(worldChannelOf(session.worldId))

        // 有联盟就订阅联盟
        if (homePlayerDC.player.allianceId > 0) {
            session.subscribeChannel(allianceChannelOf(homePlayerDC.player.allianceId))
        }

        // 订阅聊天室
        for (eachRoom in homePlayerDC.player.chatRoomList) {
            session.subscribeChannel(roomChannelOf(eachRoom.chatRoomId))
        }
    }

    /**
     * 进入游戏时填充黑名单
     */
    private fun fillBlackListEnterGame(session: PlayerActor, blackPlayerDC: BlackPlayerDC) {
        val blackList = blackPlayerDC.blackPlayers
        blackList.forEach { blackPlayer ->
            session.tellMsg {
                val multicastEnvelopeMsg = MulticastEnvelopeMsg.newBuilder()
                val addMulticastBlackList = AddMulticastBlackList.newBuilder()
                addMulticastBlackList.blackPlayerId = blackPlayer.blackPlayerId
                multicastEnvelopeMsg.addMulticastBlackList = addMulticastBlackList.build()
                multicastEnvelopeMsg.build()
            }
        }
    }

    private fun skinEffectInit(session: PlayerActor, skinDC: SkinDC) {
        updateInUseSkin2World(session, skinDC)
        effectHelper.syncEffect2World(session, SKIN_EFFECT)
    }

    private fun innerCityEffectInit(session: PlayerActor, innerCityDC: InnerCityDC, playerDc: HomePlayerDC) {
        // 内城建筑的效果初始化
        for (vo in pcs.innerBuildingLocationCache.protoList) {
            val buildingProto = pcs.innerBuildingCache.innerBuildingProtoMap[vo.interfaceTypeList[0]]
            if (buildingProto == null) {
                continue
            }

            if (buildingProto.bornType != BORN_HAVE && buildingProto.bornType != BORN_LOCK) {
                continue
            }

            val cityType = buildingProto.buildType
            var lv = 0
            if (buildingProto.bornType == BORN_HAVE) {
                lv = 1
            }
            // 触发建筑升级完成事件
            val innerCityInfo =
                innerCityDC.findMaxLvInnerBuildingFromCastleIdAndType(playerDc.player.focusCastleId, cityType)
            if (innerCityInfo != null && innerCityInfo.lv == lv) {
                fireEvent(
                    session,
                    BuildingUpFinishEvent(
                        cityType,
                        lv,
                        innerCityInfo.id,
                        targetHelper,
                        refreshResHelper,
                        effectHelper,
                        innerCityInfo.cityId
                    )
                )
            }
        }
    }

    private fun heroInit(session: PlayerActor, heroDC: HeroDC) {
        val heroList = heroDC.findHeroMapByPlayer()

        heroList.forEach { _, hero ->
            val effectIds = getNeedRefreshType(hero)
            fireEvent(
                session,
                ResearchEffectChangeEvent(
                    effectIds,
                    targetHelper,
                    effectHelper,
                    refreshResHelper
                )
            )
            fireEvent(session, GainHeroEvent(hero.id))
        }

        //刷新英雄实力
        targetHelper.targetAddVal(session, HeroStrength)
        targetHelper.refreshAllHeroPower(session, false)
    }

    private fun getTimeBoxInfo(rt: EnterGameHomeRt.Builder, homePlayerDC: HomePlayerDC) {
        for ((index, timeBoxInfo) in homePlayerDC.player.timeBoxNumMap) {
            val timeBoxInfoBuilder = pb4client.TimeBoxInfo.newBuilder()
            timeBoxInfoBuilder.index = index
            timeBoxInfoBuilder.timeBoxId = timeBoxInfo.relicBoxId
            timeBoxInfoBuilder.studyTime = getTimeSec(timeBoxInfo.studyTime)
            timeBoxInfoBuilder.overTime = getTimeSec(timeBoxInfo.timeBoxTimeOver)
            rt.addTimeBoxInfos(timeBoxInfoBuilder)
        }
    }

    private fun getBattleReportInfo(rt: EnterGameHomeRt.Builder, battleReportDC: BattleReportDC) {
        // 未读战报数
        rt.noReadFightInfoNum = battleReportDC.findUnreadReportNumByPlayerId()
        val jjcReport = battleReportDC.findAllJjcReport().filter { it.readState == UnRead }
        rt.jjcReportNum = jjcReport.size
    }

    private fun getLibraryInfo(rt: EnterGameHomeRt.Builder, libraryDC: LibraryDC) {
        val library = libraryDC.library

        for (type in library.newItem) {
            rt.addNewItem(type)
        }
    }

    private fun getOnlineRewardInfo(session: PlayerActor, rt: EnterGameHomeRt.Builder, homePlayerDC: HomePlayerDC) {
        val player = homePlayerDC.player

        val nowTime = getNowTime()

        // 功能没开启,直接return
        val re = conditionHelper.uiConditionCheck(session, ONLINE_GIFT_OPEN)
        if (re != ResultCode.SUCCESS.code) {
            val onlineRewardInfo = OnlineRewardInfo.newBuilder()

            onlineRewardInfo.todayOnlineNum = 0
            onlineRewardInfo.nextOnlineReward = ""
            onlineRewardInfo.overTime = 0
            onlineRewardInfo.bigOnlineReward = ""

            rt.onlineRewardInfo = onlineRewardInfo.build()
            return
        }

        if (isAfterGameRefTime(player.lastGetOnlineTime)) {
            // 上次领取时间与当前已经跨天,重置玩家所有数据
            val castleLv = player.castleLv
            player.todayOnlineNum = 0
            val onlineGiftProtoMap = pcs.onlineGiftProtoCache.protoMap[castleLv]
            if (onlineGiftProtoMap == null) {
                return
            }
            val onlineGiftProto = onlineGiftProtoMap[1]
            if (onlineGiftProto == null) {
                return
            }

            val propMap = pcs.dropPropsProtoCache.dropPropsMap[onlineGiftProto.reward]
            if (propMap == null) {
                return
            }

            val prop = randomSelect(propMap.dropMap)
            if (prop == null) {
                return
            }
            val res =
                LinkedList<ResVo>(
                    asList(
                        ResVo(RES_PROPS, prop.id.toLong(), prop.num.toLong())
                    )
                )
            val resString = resVoToResString(res)

            // 随机全勤奖
            val bigOnlineGiftProtoMap = pcs.onlineGiftProtoCache.protoMap[castleLv]
            if (bigOnlineGiftProtoMap == null) {
                return
            }

            val bigOnlineGiftProto = bigOnlineGiftProtoMap[10]
            if (bigOnlineGiftProto == null) {
                return
            }
            val bigPropMap = pcs.dropPropsProtoCache.dropPropsMap[bigOnlineGiftProto.reward]
            if (bigPropMap == null) {
                return
            }

            val bigProp = randomSelect(bigPropMap.dropMap)
            if (bigProp == null) {
                return
            }
            val bigRes = LinkedList(
                asList(
                    ResVo(RES_PROPS, bigProp.id.toLong(), bigProp.num.toLong())
                )
            )
            val bigResString = resVoToResString(bigRes)
            player.bigOnlineReward = bigResString
            player.lastGetOnlineTime = nowTime
            player.nextOnlineReward = resString
            player.nextOnlineRewardOverTime = nowTime + onlineGiftProto.cdTime * 1000
        }

        val onlineRewardInfo = OnlineRewardInfo.newBuilder()

        onlineRewardInfo.todayOnlineNum = player.todayOnlineNum
        onlineRewardInfo.nextOnlineReward = player.nextOnlineReward
        onlineRewardInfo.overTime = (player.nextOnlineRewardOverTime / 1000).toInt()
        onlineRewardInfo.bigOnlineReward = player.bigOnlineReward

        rt.onlineRewardInfo = onlineRewardInfo.build()
    }

    private fun getAchievementInfo(
        session: PlayerActor,
        rt: EnterGameHomeRt.Builder,
        homeAchievementDC: HomeAchievementDC
    ) {
        val achievementTypeMap = homeAchievementDC.findAchievementTypeMapByPlayerId(session)
        for ((achievementType, achievements) in pcs.achievementProtoCache.achievementProtoMapByType) {
            val a1 = achievements[1] ?: continue
            val ex = achievementTypeMap[achievementType]
            if (ex == null) {
                //补全成就
                val achievementProto = pcs.achievementProtoCache.achievementProtoMap[a1.id]
                if (achievementProto != null) {
                    val progressMap = hashMapOf<Int, Long>()
                    for ((checkType, _) in achievementProto.completeCondMap) {
                        progressMap[checkType] = 0
                    }
                    val newAchievement =
                        homeAchievementDC.createHomeAchievement(session, session.playerId, a1.id, progressMap)
                    if (newAchievement != null) {
                        achievementTypeMap[achievementType] = newAchievement
                    }
                }
            }
        }

        for ((_, achievement) in achievementTypeMap) {
            var fillAchievement = achievement

            //校验已领取奖励的成就是否有下一星
            val newAchievement = homeAchievementDC.intoNextStarAchievement(achievement)
            if (newAchievement != null) {
                fillAchievement = newAchievement
            }

            //填充4号消息
            val achievementBuilder = Achievement.newBuilder()

            achievementBuilder.id = fillAchievement.id
            achievementBuilder.protoId = fillAchievement.achievementId
            achievementBuilder.state = fillAchievement.state

            for ((checkType, progress) in fillAchievement.progressMap) {
                val p = ProgressInfo.newBuilder()
                p.checkType = checkType
                p.progress = progress.toInt()
                achievementBuilder.addAllProgress(p)
            }
            rt.addHomeAchievements(achievementBuilder)
        }
    }

    private fun getTaskInfo(rt: EnterGameHomeRt.Builder, homeTaskDC: HomeTaskDC) {
        // 任务信息
        val tasks = homeTaskDC.findAllTaskByPlayerId()
        val timeOverTasks = LinkedList<Long>()
        for (task in tasks) {
            if (task.overTime != -1L && task.overTime <= getNowTime()) {
                timeOverTasks.add(task.id)
            }

            if (task.onWorld == 1) {
                // 如果这个任务在世界那边跑着并且还没有过期,那就不用管他
                continue
            }
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
            rt.addHomeTasks(taskVo)
        }
    }

    private fun getInstanceInfo(rt: EnterGameHomeRt.Builder, homePlayerDC: HomePlayerDC) {
        val player = homePlayerDC.player
        if (isAfterGameRefTime(player.lastBuyStrengthTime)) {
            player.buyStrengthNum = 0
            player.lastBuyStrengthTime = getNowTime()
        }
        rt.buyStrengthNum = player.buyStrengthNum
        rt.lastBuyStrengthTime = (player.lastBuyStrengthTime / 1000).toInt()
    }

    private fun getBankInfo(
        rt: EnterGameHomeRt.Builder, bankDC: BankDC, bankAccelerateDC: BankAccelerateDC
    ) {
        val bankInfo = bankDC.findBankByPlayerId()
        val bankAccelerateInfo = bankAccelerateDC.findBankQuickByPlayerId()
        if (bankInfo != null) {
            val b = BankInfo.newBuilder()

            b.userPlan = bankInfo.usePlan
            b.useMoney = bankInfo.useMoney + bankInfo.useBindMoney
            b.rate = bankInfo.rate
            b.overTime = (bankInfo.overTime / 1000).toInt()
            b.type = BANK_DIAMOND

            rt.addBankInfo(b)
        }
        if (bankAccelerateInfo != null) {
            val ba = BankInfo.newBuilder()

            ba.type = BANK_ACCELERATE
            ba.userPlan = bankAccelerateInfo.usePlan
            ba.overTime = (bankAccelerateInfo.overTime / 1000).toInt()

            rt.addBankInfo(ba)
        }
    }

    private fun getInMakeKingEquip(
        rt: EnterGameHomeRt.Builder, homePlayerDC: HomePlayerDC, inMakeKingEquipDC: InMakeKingEquipDC
    ) {
        val player = homePlayerDC.player
        val inMakes = inMakeKingEquipDC.findInMakeByPlayerId()
        for (inMakeInfo in inMakes) {
            val b = MakeKingEquipChangeVo.newBuilder()
            b.id = inMakeInfo.id
            b.makeProto = inMakeInfo.kingEquipId
            b.heiyaoshiId = inMakeInfo.heiYaoId
            b.overTime = (inMakeInfo.overTime / 1000).toInt()
            b.equipId = inMakeInfo.costEquipId

            rt.addMakeKingEquipChangeVos(b)
        }

        rt.nowKingEquipPlan = player.nowUseKingEquipPlan
    }

    private fun getTalent(rt: EnterGameHomeRt.Builder, homePlayerDC: HomePlayerDC) {
        val player = homePlayerDC.player
        for ((pointKey, pointVal) in player.talentPointMap) {
            val talentPoint = TalentPoint.newBuilder()
            talentPoint.talentType = pointKey
            talentPoint.leftTalentPoint = pointVal
            rt.addLeftTalentPoint(talentPoint)
        }
        for ((unlockKey, unlockVal) in player.unlockedTalentMap) {
            val unlockedTalent = UnlockedTalent.newBuilder()
            unlockedTalent.talentId = unlockKey
            unlockedTalent.talentLevel = unlockVal
            rt.addHaveUnlockTalent(unlockedTalent)
        }
    }

    private fun getVip(rt: EnterGameHomeRt.Builder, vipDC: VipDC) {
        rt.vipLv = vipDC.vipInfo.vipLv
        rt.vipExp = vipDC.vipInfo.vipExp
    }

    private fun getGuidelineState(rt: EnterGameHomeRt.Builder, homePlayerDC: HomePlayerDC) {
        val player = homePlayerDC.player
        rt.guideStep = player.guideStep
        rt.addAllFinishGuidelineId(player.haveFinishGuideLine)
    }

    private fun getResearchInfo(rt: EnterGameHomeRt.Builder, homePlayerDC: HomePlayerDC) {
        val player = homePlayerDC.player

        for ((researchId, researchVo) in player.researchInfoMap) {
            val rtVo = ResearchInfo.newBuilder()
            rtVo.researchId = researchId
            rtVo.researchLv = researchVo.researchLv
            rtVo.researchOverTime = (researchVo.researchOverTime / 1000).toInt()
            rtVo.helperId = researchVo.helpId
            rt.addResearchs(rtVo)
        }
    }

    private fun getMail(rt: EnterGameHomeRt.Builder, mailDC: MailDC) {
        mailDC.newMailNum().forEach {
            rt.addNewMailNum(it)
        }
    }

    private fun getMerchantShip(rt: EnterGameHomeRt.Builder, merchantShipDC: MerchantShipDC) {
        val ship = merchantShipDC.merchantShip
        rt.shipCanExchange = 0
        rt.shipNextRefTime = 0
        if (ship != null) {
            var shipCanExchange = 0
            for ((_, shipInfo) in ship.shopInfoList) {
                if (shipInfo.exchanged == 0) {
                    shipCanExchange = 1
                    break
                }
            }
            rt.shipCanExchange = shipCanExchange
            rt.shipNextRefTime = (ship.nextReTime / 1000).toInt()
        }
    }

    private fun getMark(rt: EnterGameHomeRt.Builder, markDC: MarkDC) {
        //mark
        markDC.marks.forEach {
            val markInfoBuilder = MarkInfo.newBuilder()
            markInfoBuilder.id = it.id
            markInfoBuilder.landX = it.x
            markInfoBuilder.landY = it.y
            markInfoBuilder.name = it.name
            markInfoBuilder.group = it.group
            markInfoBuilder.areaNo = it.pltAreaNo
            rt.addMarkInfo(markInfoBuilder)
        }
    }

    private fun getJjc(rt: EnterGameHomeRt.Builder, jjcHomeDC: JjcHomeDC, homeSyncDC: HomeSyncDC) {
        val jjcHome = jjcHomeDC.jjcHome
        val homeSyncData = homeSyncDC.syncData

        val playerArenaInfo = PlayerArenaInfo.newBuilder()
        playerArenaInfo.maxRank = homeSyncData.maxJjcRank
        playerArenaInfo.rank = homeSyncData.jjcRank
        playerArenaInfo.rankGold = homeSyncData.jjcGoldReward
        playerArenaInfo.rankJJCoin = homeSyncData.jjcCoinReward
        playerArenaInfo.addAllAchievementExchangeRewards(jjcHome.achievementRewards)
        playerArenaInfo.addAllDrawRewards(jjcHome.drawRewards)
        playerArenaInfo.times = jjcHome.todayNum

        //todo 竞技场战报数量可能需要拆至地图服
        //val jjcReport = findAllJjcReport(session.areaCache, session.playerId).filter { it.readState == constg.UnRead }
        //playerArenaInfo.jjcReportNum = jjcReport.size

        rt.arenaInfo = playerArenaInfo.build()
    }

    private fun getResInfo(
        session: PlayerActor,
        rt: EnterGameHomeRt.Builder,
        homePlayerDC: HomePlayerDC,
        resourceYieldDC: ResourceYieldDC
    ) {
        // 全部重新计算一下玩家的资源数量

        val nowTime = getNowTime()
        val (wood, iron, stone, food, coin) = refreshResHelper.calcResourceNotUpdate(session, nowTime)

        val player = homePlayerDC.player

        // 获取资源信息
        val res = ResourceInfoRt.newBuilder()
        res.wood = wood
        res.iron = iron
        res.stone = stone
        res.food = food
        res.coin = coin
        //TODO 使用world服的行动力数据
        res.decree = player.decree
        res.gold = player.gold
        res.bindGold = player.bindGold
        res.jjcCoin = player.jjcCoin
        res.casinoCoin = player.casinoCoin
        res.allianceCoin = player.allianceCoin
        res.heroExpPool = player.heroExpPool
        res.goldCoin = player.goldCoin
        res.silverCoin = player.silverCoin
        res.res4Time = (getNowTime() / 1000).toInt()
        rt.setRes(res)

        // 资源产量
        val yieldVo = resourceYieldDC.resourceYield

        // 由于调用重新计算资源产量，会保存最新产量，所以需要保存本期的资源数量
        refreshResHelper.updatePlayerResourceYield(session, AllResYield, nowTime)
        player.wood = wood
        player.iron = iron
        player.stone = stone
        player.food = food
        player.coin = coin

        val yieldChange = YieldChange.newBuilder()
        yieldChange.addWood = yieldVo.totalWood
        yieldChange.addIron = yieldVo.totalIron
        yieldChange.addStone = yieldVo.totalStone
        yieldChange.addFood = yieldVo.totalFood
        yieldChange.addCoin = yieldVo.totalCoin
        yieldChange.calcTime = (yieldVo.calTime / 1000).toInt()
        rt.yields = yieldChange.build()

        //仓库上限
        val storeLimitChange = StoreLimitChange.newBuilder()
        storeLimitChange.woodLimit = yieldVo.woodLimit
        storeLimitChange.ironLimit = yieldVo.ironLimit
        storeLimitChange.stoneLimit = yieldVo.stoneLimit
        storeLimitChange.foodLimit = yieldVo.foodLimit
        storeLimitChange.coinLimit = yieldVo.coinLimit
        rt.setStorageLimit(storeLimitChange)
    }

    private fun getAllBuildings(rt: EnterGameHomeRt.Builder, homePlayerDC: HomePlayerDC, innerCityDC: InnerCityDC) {
        // 内城建筑
        val innerCityList = innerCityDC.findInnerCityListFromCastleId(homePlayerDC.player.focusCastleId)
        for (innerCity in innerCityList) {
            var completeTime: Int
            if (innerCity.state == DESTROY) {
                completeTime = getTimeSec(innerCity.destroyTime)
            } else {
                completeTime = getTimeSec(innerCity.completeTime)
            }
            val innerCityInfo = InnerCityInfo.newBuilder()
            innerCityInfo.completeTime = completeTime
            innerCityInfo.innerCityType = innerCity.cityType
            innerCityInfo.innerCityId = innerCity.id
            innerCityInfo.startTime = (innerCity.startTime / 1000).toInt()
            innerCityInfo.state = innerCity.state
            innerCityInfo.positionIndex = innerCity.positionIndex
            innerCityInfo.lv = innerCity.lv
            innerCityInfo.helpId = innerCity.helpId

            rt.addInnerCityInfo(innerCityInfo)
        }
    }

    private fun getUnlockArea(rt: EnterGameHomeRt.Builder, homePlayerDC: HomePlayerDC) {
        // 区域
        homePlayerDC.player.innerBuildingUnlockAreaMap.keys.map {
            rt.addUnlockArea(it)
        }
    }

    private fun getHeroInfo(rt: EnterGameHomeRt.Builder, heroDC: HeroDC) {
        val heroList = heroDC.findHeroList()
        for (hero in heroList) {
            val heroProperty = generateHeroRefPropsByDbData(hero)
            val heroInfoPb = createHeroPbByProps(heroProperty)
            rt.addDetailedQueryHerosInFo(heroInfoPb)
        }
    }

    private fun getBagInfo(rt: EnterGameHomeRt.Builder, newEquipDC: NewEquipDC, bagDC: BagDC) {
        val allEquip = newEquipDC.findPropsByPlayerId()
        for (equip in allEquip) {
            val bInfo = BagInfo.newBuilder()
            bInfo.itemId = equip.id
            bInfo.itemProtoId = equip.equipProtoId
            bInfo.num = equip.haveNum
            bInfo.itemType = newEquipDC.findEquipIsOnHero(bagDC, equip.id)
            bInfo.equipOnAddress = equip.equipOnAddress
            bInfo.equipLv = equip.lv
            bInfo.equipExp = equip.exp
            for ((address, pps) in equip.propertyMap) {
                val p = EquipProps.newBuilder()
                p.propAddress = address
                for ((ppType, ppValue) in pps) {
                    val pv = EquipProp.newBuilder()
                    pv.propType = ppType
                    pv.propValue = ppValue
                    p.addPropValues(pv)
                }
                bInfo.addProps(p)
            }
            for ((address, pps) in equip.cardInfoMap) {
                val p = KingEquipCardInfo.newBuilder()
                p.address = address
                p.cardProtoId = pps
                bInfo.addKingEquipCardInfos(p)
            }
            rt.addBagInfo(bInfo)
        }
    }

    private fun getFriendApplyInfo(rt: EnterGameHomeRt.Builder, friendApplyDC: FriendApplyDC) {
        val applyInfoList = friendApplyDC.findPlayerFriendApply()

        for (apply in applyInfoList) {
            val friendApplyBuilder = FriendApply.newBuilder()
            friendApplyBuilder.applyPlayerId = apply.applyPlayerId
            friendApplyBuilder.applyPlayerName = apply.applyPlayerName
            friendApplyBuilder.applyPlayerAreaNo = apply.applyPlayerAreaNo
            friendApplyBuilder.applyPlayerVipLv = apply.applyPlayerVipLv
            friendApplyBuilder.applyPlayerAllianceShortName = apply.applyPlayerAllianceShortName
            friendApplyBuilder.applyPlayerPhotoId = apply.applyPlayerPhotoId
            friendApplyBuilder.applyState = apply.applyState
            friendApplyBuilder.castleLv = apply.applyCastleLv
            friendApplyBuilder.skinType = apply.applyCastleSkin
            friendApplyBuilder.applyTime = (apply.applyTime / 1000).toInt()
            friendApplyBuilder.shortName = apply.applyPlayerShortName
            rt.addFriendApplys(friendApplyBuilder)
        }
    }

    private fun getFriendInfo(
        rt: EnterGameHomeRt.Builder,
        friendDC: FriendDC,
        homePlayerDC: HomePlayerDC,
        friendChatRecordDC: FriendChatRecordDC
    ) {

        val myFriends = friendDC.findFriendById()
        val player = homePlayerDC.player
        val privateChat = player.chatPlayerList

        var nowTime = getNowTime()
        // 查询每个好友的信息
        for (myFriend in myFriends) {
            if (myFriend.isRealFriend == 1) {
                val privateChats = privateChat.firstOrNull { it.chatRoomId == myFriend.tarPlayerId }
                if (privateChats != null) {
                    nowTime = privateChats.lastReadTime
                }

                // 查最后一条聊天
                val findResList = friendChatRecordDC.findRecordByFriendId(myFriend.tarPlayerId)
                var talkTime: Long = 0
                val eachRecord = findResList.lastOrNull()
                if (eachRecord != null) {
                    eachRecord.talkTime > talkTime
                    talkTime = eachRecord.talkTime
                }

                val friendBuilder = FriendInfo.newBuilder()
                friendBuilder.playerId = myFriend.tarPlayerId
                friendBuilder.name = myFriend.name
                friendBuilder.photoId = myFriend.photoId
                friendBuilder.fightValue = (myFriend.fightValue).toLong()
                friendBuilder.castleLv = myFriend.castleLv
                friendBuilder.skinType = myFriend.castleSkin
                friendBuilder.townState = 0
                friendBuilder.areaNo = myFriend.areaNo
                friendBuilder.allianceShortName = myFriend.allianceShortName
                friendBuilder.vipLv = myFriend.vipLv
                friendBuilder.groupId = myFriend.groupId
                friendBuilder.lastTalkTime = talkTime / 1000
                friendBuilder.lastReadTime = nowTime / 1000
                friendBuilder.msgNum = 0
                friendBuilder.shortName = myFriend.shortName
                rt.addFriendInfos(friendBuilder)
            }
        }
    }

    private fun getBlackPlayerInfo(rt: EnterGameHomeRt.Builder, blackPlayerDC: BlackPlayerDC) {

        val blackPlayers = blackPlayerDC.findAllBlackPlayers()

        for (blackPlayer in blackPlayers) {
            val friendInfoBuilder = FriendInfo.newBuilder()
            friendInfoBuilder.playerId = blackPlayer.blackPlayerId
            friendInfoBuilder.name = blackPlayer.name
            friendInfoBuilder.photoId = blackPlayer.photoId
            friendInfoBuilder.fightValue = (blackPlayer.fightValue).toLong()
            friendInfoBuilder.townState = 0
            friendInfoBuilder.areaNo = blackPlayer.areaNo
            friendInfoBuilder.allianceShortName = blackPlayer.allianceShortName
            friendInfoBuilder.vipLv = blackPlayer.vipLv
            friendInfoBuilder.groupId = GROUP_ID_INIT
            friendInfoBuilder.castleLv = blackPlayer.castleLv
            friendInfoBuilder.lastTalkTime = 0
            friendInfoBuilder.lastReadTime = 0
            friendInfoBuilder.shortName = blackPlayer.allianceShortName
            rt.addBlackPlayerInfo(friendInfoBuilder)
        }
    }

    private fun getFriendGroupInfo(rt: EnterGameHomeRt.Builder, friendGroupDC: FriendGroupDC) {

        val myGroups = friendGroupDC.findPlayerFriendGroup()
        for (myGroup in myGroups) {
            val group = GroupInfo.newBuilder()
            group.groupId = myGroup.id
            group.groupName = myGroup.groupName
            rt.addGroupInfos(group)
        }
    }

    // 获取vip奖励数据
    private fun getVipRewardDataForEnter(session: PlayerActor, vipDC: VipDC, homePlayerDC: HomePlayerDC) {
        val player = homePlayerDC.player

        val nowTime = getNowTime()
        val vipInfo = vipDC.vipInfo
        val nowRefreshTime = getVipRefreshTime(nowTime)
        val lastRefreshEnergyTime = getVipRefreshTime(vipInfo.lastRefreshEnergyTime)

        val subRefreshEnergyHours = (nowRefreshTime - lastRefreshEnergyTime).toDouble() / (1000 * 3600)
        if (subRefreshEnergyHours >= 24.0) {
            vipHelper.resetDailyEnergy(session, true)
        }

        if (vipInfo.vipLv >= pcs.vipSetCache.maxLvVip.level) {
            // 10级满级，没有vip经验
            return
        }

        val birthDayVipTime = getVipRefreshTime(player.birthTime)
        val lastRefreshTime = getVipRefreshTime(vipInfo.lastGetVipRewardTime)
        val subRefreshHours = (nowRefreshTime - lastRefreshTime).toDouble() / (1000 * 3600)

        //当天第N次登录
        if (subRefreshHours == 0.0) {
            // 注册第一天
            if (vipInfo.continueOnlineDay == 0) {
                // 注册第一天不给vip经验，但是应该提示明天的登陆的经验
                vipInfo.continueOnlineDay = 0
                vipInfo.lastGetVipRewardTime = birthDayVipTime
                val nowGetExp = 0
                val nowReward =
                    LinkedList<ResVo>(
                        asList(
                            ResVo(RES_VIP_EXP, NOT_PROPS_SUB_TYPE, nowGetExp.toLong())
                        )
                    )

                val nextGetExp = pcs.basicProtoCache.vipEveryDayGet
                val nextReward =
                    LinkedList<ResVo>(
                        asList(
                            ResVo(RES_VIP_EXP, NOT_PROPS_SUB_TYPE, nextGetExp.toLong())
                        )
                    )
                val notifier = createGetVipLoginRewardNotifier(
                    resVoToResString(nowReward), resVoToResString(nextReward),
                    VIP_REWARD_BIRTHDAY, GAIN_VIP_REWARD
                )
                notifier.notice(session)
                return
            }

            //当天第N次登录
            var nowGetExp =
                (vipInfo.vipLv * pcs.basicProtoCache.vipEveryDayGet) * (1 + (0.2) * (vipInfo.continueOnlineDay - 1))
            if (nowGetExp > pcs.basicProtoCache.vipEveryDayMost) {
                nowGetExp = pcs.basicProtoCache.vipEveryDayMost.toDouble()
            }
            val nowReward =
                LinkedList<ResVo>(
                    asList(
                        ResVo(RES_VIP_EXP, NOT_PROPS_SUB_TYPE, nowGetExp.toLong())
                    )
                )

            var nextGetExp =
                (vipInfo.vipLv * pcs.basicProtoCache.vipEveryDayGet) * (1 + 0.2 * vipInfo.continueOnlineDay)
            if (nextGetExp > pcs.basicProtoCache.vipEveryDayMost) {
                nextGetExp = pcs.basicProtoCache.vipEveryDayMost.toDouble()
            }
            val nextReward =
                LinkedList<ResVo>(
                    asList(
                        ResVo(RES_VIP_EXP, NOT_PROPS_SUB_TYPE, nextGetExp.toLong())
                    )
                )
            val notifier = createGetVipLoginRewardNotifier(
                resVoToResString(nowReward), resVoToResString(nextReward),
                vipInfo.continueOnlineDay, NOT_GAIN_VIP_REWARD
            )
            notifier.notice(session)
            return
        }

        if (subRefreshHours == 24.0) {
            //正好一个刷新周期
            vipInfo.continueOnlineDay += 1
            vipInfo.lastGetVipRewardTime = nowTime
            var nowGetExp =
                (vipInfo.vipLv * pcs.basicProtoCache.vipEveryDayGet) * (1 + 0.2 * (vipInfo.continueOnlineDay - 1))
            if (nowGetExp > pcs.basicProtoCache.vipEveryDayMost) {
                nowGetExp = pcs.basicProtoCache.vipEveryDayMost.toDouble()
            }
            val nowReward =
                LinkedList<ResVo>(
                    asList(
                        ResVo(RES_VIP_EXP, NOT_PROPS_SUB_TYPE, nowGetExp.toLong())
                    )
                )

            var nextGetExp =
                (vipInfo.vipLv * pcs.basicProtoCache.vipEveryDayGet) * (1 + 0.2 * vipInfo.continueOnlineDay)
            if (nextGetExp > pcs.basicProtoCache.vipEveryDayMost) {
                nextGetExp = pcs.basicProtoCache.vipEveryDayMost.toDouble()
            }
            val nextReward =
                LinkedList<ResVo>(
                    asList(
                        ResVo(RES_VIP_EXP, NOT_PROPS_SUB_TYPE, nextGetExp.toLong())
                    )
                )
            val notifier = createGetVipLoginRewardNotifier(
                resVoToResString(nowReward), resVoToResString(nextReward),
                vipInfo.continueOnlineDay, GAIN_VIP_REWARD
            )
            notifier.notice(session)
            resHelper.addRes(session, ACTION_GET_VIP_LOGIN_REWARD, player, nowReward)
            return
        }

        if (subRefreshHours > 24.0) {
            //两次时间超过一天
            vipInfo.continueOnlineDay = 1
            vipInfo.lastGetVipRewardTime = nowTime
            val nowGetExp = vipInfo.vipLv * pcs.basicProtoCache.vipEveryDayGet
            val nowReward =
                LinkedList<ResVo>(
                    asList(
                        ResVo(RES_VIP_EXP, NOT_PROPS_SUB_TYPE, nowGetExp.toLong())
                    )
                )

            val nextGetExp =
                (vipInfo.vipLv * pcs.basicProtoCache.vipEveryDayGet) * (1 + 0.2 * vipInfo.continueOnlineDay)
            val nextReward =
                LinkedList<ResVo>(
                    asList(
                        ResVo(RES_VIP_EXP, NOT_PROPS_SUB_TYPE, nextGetExp.toLong())
                    )
                )
            val notifier = createGetVipLoginRewardNotifier(
                resVoToResString(nowReward),
                resVoToResString(nextReward),
                vipInfo.continueOnlineDay,
                GAIN_VIP_REWARD
            )
            notifier.notice(session)
            resHelper.addRes(session, ACTION_GET_VIP_LOGIN_REWARD, player, nowReward)
            return

        }

    }

    private fun enterGameChatRoomInfo(session: PlayerActor, homePlayerDC: HomePlayerDC) {
        val player = homePlayerDC.player
        val roomList = LinkedList<Long>()
        for (eachRoom in player.chatRoomList) {
            roomList.add(eachRoom.chatRoomId)
        }

        for (eachRoomId in roomList) {
            val askMsg = GetChatroomAskReq.newBuilder()
            askMsg.roomId = eachRoomId
            session.createACS<Home2PublicAskResp>()
                .ask(
                    session.publicShardProxy,
                    session.fillHome2PublicAskMsgHeader(eachRoomId) { it.setGetChatroomAskReq(askMsg) },
                    Home2PublicAskResp::class.java
                )
                .whenCompleteKt { prt, askErr ->
                    val rt = EnterGameChatRoomInfo.newBuilder()
                    if (askErr != null || prt == null) {
                        // todo 重试...
                    } else {
                        val chatRoomInfo = ChatRoom.newBuilder()
                        chatRoomInfo.chatRoomId = prt.getChatroomAskRt.chatRoomVo.chatRoomId
                        chatRoomInfo.chatRoomName = prt.getChatroomAskRt.chatRoomVo.chatRoomName
                        chatRoomInfo.memberNum = prt.getChatroomAskRt.chatRoomVo.memberNum
                        chatRoomInfo.playerId = prt.getChatroomAskRt.chatRoomVo.playerId
                        chatRoomInfo.lastTalkTime = prt.getChatroomAskRt.chatRoomVo.lastTalkTime
                        chatRoomInfo.addAllMemberIcons(prt.getChatroomAskRt.chatRoomVo.memberIconsList)
                        val myRoom = player.chatRoomList
                            .firstOrNull { it.chatRoomId == prt.getChatroomAskRt.chatRoomVo.chatRoomId }
                        if (myRoom != null) {
                            chatRoomInfo.lastReadTime = myRoom.lastReadTime
                        } else {
                            chatRoomInfo.lastReadTime = 0
                        }

                        rt.chatRoomInfo = chatRoomInfo.build()
                        session.sendMsg(MsgType.EnterChatRoomInfo_3188, rt.build())
                    }
                }
        }

    }

    private fun enterGameStrangerInfo(
        session: PlayerActor,
        homePlayerDC: HomePlayerDC,
        friendDC: FriendDC,
        friendChatRecordDC: FriendChatRecordDC
    ) {
        val player = homePlayerDC.player
        val myFriends = friendDC.findFriendById()
        val contacts = LinkedList<MyChat>()
        for (eachPerson in player.chatPlayerList) {
            // 如果是好友就不加
            val friend = myFriends.firstOrNull { it.tarPlayerId == eachPerson.chatRoomId }
            if (friend != null) {
                continue
            }
            contacts.add(eachPerson)
        }

        for (eachContact in contacts) {
            // 循环ask home
            val askMsg = AskStrangerInfoAskReq.newBuilder()
            askMsg.targetPlayerId = eachContact.chatRoomId

            session.createACS<Home2HomeAskResp>()
                .ask(
                    session.homeShardProxy,
                    session.fillHome2HomeAskMsgHeader {
                        it.setAskStrangerInfoAskReq(askMsg)
                        it.playerId = eachContact.chatRoomId
                    },
                    Home2HomeAskResp::class.java
                )
                .whenCompleteKt { resp, err ->

                    try {
                        when {
                            err != null -> {
                                // todo
                                ResultCode.ASK_ERROR1.code

                            }
                            resp == null -> {
                                // todo
                                ResultCode.ASK_ERROR2.code

                            }
                            else -> {
                                // 找聊天记录的时间
                                var lastSendTime = 0L
                                val recordLast =
                                    friendChatRecordDC.findRecordByFriendId(eachContact.chatRoomId).lastOrNull()
                                if (recordLast != null) {
                                    lastSendTime = recordLast.talkTime
                                }

                                createNewChatWindowNotifier(
                                    resp.askStrangerInfoAskRt.targetPlayer.myPlayerId,
                                    resp.askStrangerInfoAskRt.targetPlayer.worldId,
                                    resp.askStrangerInfoAskRt.targetPlayer.name,
                                    resp.askStrangerInfoAskRt.targetPlayer.photoProtoId,
                                    resp.askStrangerInfoAskRt.targetPlayer.power.toLong(),
                                    resp.askStrangerInfoAskRt.targetPlayer.castleLv,
                                    resp.askStrangerInfoAskRt.targetPlayer.skinType,
                                    resp.askStrangerInfoAskRt.targetPlayer.vipLv,
                                    resp.askStrangerInfoAskRt.targetPlayer.areaNo,
                                    resp.askStrangerInfoAskRt.targetPlayer.allianceShortName,
                                    lastSendTime,
                                    eachContact.lastReadTime,
                                    resp.askStrangerInfoAskRt.targetPlayer.allianceNickName
                                ).notice(session)
                            }
                        }

                    } catch (e: Exception) {
                        normalLog.error("AskStrangerInfoAskReq Error!", e)
                        ResultCode.ASK_ERROR3.code
                    }
                }
        }
    }

    /**
     * 检查礼包数据，清除过期礼包
     */
    private fun ckeckGiftBag(rt: EnterGameHomeRt.Builder, giftBagDC: GiftBagDC) {
        // 删除过期的礼包数据
        val it = giftBagDC.giftBags.index.entries.iterator()
        it.forEach { entry ->
            val protos = pcs.giftBagProtoCache.giftBagMapByGiftBagId[entry.key]
            if (protos == null || protos.isEmpty()) {
                return
            }

            val proto = protos[0]
            if (!proto.isActive(entry.value.endTime)) {
                if (proto.giftType != TRIGGER_GIFTBAG) {
                    it.remove()
                    giftBagDC.delGiftBag(entry.value)
                    return
                }
            }

            val giftBagInfo = GiftBagInfo.newBuilder()
            giftBagInfo.giftBagId = entry.key
            giftBagInfo.endTime = entry.value.endTime
            giftBagInfo.curLevel = entry.value.curLevel
            giftBagInfo.curCount = entry.value.curCount
            rt.addGiftBagInfos(giftBagInfo)
        }
    }

    /**
     * 检查月卡数据,清除过期月卡
     */
    private fun checkMonthCard(rt: EnterGameHomeRt.Builder, homePlayerDC: HomePlayerDC) {
        val monthCards = homePlayerDC.player.monthCards
        val it = monthCards.entries.iterator()
        it.forEach { entry ->
            if (!entry.value.isActive()) {
                it.remove()
                return
            }

            val inDayRecv = if (inDay(entry.value.lastRecvTime)) {
                1
            } else {
                0
            }
            rt.addMonthCardInfos(
                MonthCardInfo.newBuilder()
                    .setMonthCardId(entry.key)
                    .setInDayRecv(inDayRecv)
                    .setOverdueTime(entry.value.overdueTime)
            )
        }
    }
}