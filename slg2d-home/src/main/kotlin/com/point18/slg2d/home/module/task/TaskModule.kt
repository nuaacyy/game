package com.point18.slg2d.home.module.task

import com.point18.slg2d.common.baseg.IModule
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.home.module.registerEvent
import java.util.*

/**
任务检测的接口
 */
data class AllCheckReturn(var b: Boolean, val v: Long)

class TaskModule : IModule {
    val checkHandles: HashMap<Int, AllCheck> = hashMapOf()

    override fun moduleInit() {
        // 注册事件处理
        taskModuleRegisterEvent()

        // 注册检测条件处理器
        checkHandlesInit()
    }

    private fun taskModuleRegisterEvent() {

        registerEvent(GET_TASK_REWARD, TaskCheckEventHandler())

        registerEvent(ENTER_GAME, TaskCheckEventHandler())

        registerEvent(GET_UNIT_TASK_REWARD, GetUnitTaskRewardEventHandler())

        registerEvent(GET_HERO_CARD, TaskChangeEventHandler())

        registerEvent(BUILDING_UP_FINISH, TaskChangeEventHandler())

        registerEvent(COMPOUND_CARD, TaskChangeEventHandler())

        registerEvent(GET_NEW_HERO, TaskChangeEventHandler())

        registerEvent(GET_KING_EQUIP, TaskChangeEventHandler())

        registerEvent(GET_ONLINE_REWARD, TaskChangeEventHandler())

        registerEvent(HERO_EQUIP_LV_UP, TaskChangeEventHandler())

        registerEvent(HERO_SKILL_LV_UP, TaskChangeEventHandler())

        registerEvent(HERO_STAR_UP, TaskChangeEventHandler())

        registerEvent(HERO_SUPER_UP, TaskChangeEventHandler())

        registerEvent(HERO_UP_FINISH, TaskChangeEventHandler())

        registerEvent(JJC_FIGHT, TaskChangeEventHandler())

        registerEvent(HERO_SKILL_LV_UP, TaskChangeEventHandler())

        registerEvent(JJC_SHOP_REFRESH, TaskChangeEventHandler())

        registerEvent(INSTANCE_FIGHT, TaskChangeEventHandler())

        registerEvent(GET_HERO_CARD, TaskChangeEventHandler())

        registerEvent(MERCHANT_SHIP_BUY_SURPRISE, TaskChangeEventHandler())

        registerEvent(KING_EQUIP_ON_CARD, TaskChangeEventHandler())

        registerEvent(RESEARCH_EFFECT_CHANGE, TaskChangeEventHandler())

        registerEvent(VIP_LV_CHANGE, TaskChangeEventHandler())

        registerEvent(ACHIEVEMENT_FINISH, TaskChangeEventHandler())

        registerEvent(GET_ALLIANCE_COMPETITION_REWARD, TaskChangeEventHandler())

        registerEvent(ALLIANCE_COMPETITION_OVER, TaskChangeEventHandler())

        registerEvent(CHAT, TaskChangeEventHandler())

        registerEvent(STRENGTH_SKIN, TaskChangeEventHandler())

        registerEvent(GET_NEW_SKIN, TaskChangeEventHandler())

        registerEvent(GM_REFRESH_EVENT, TaskChangeEventHandler())

        registerEvent(MOVE_CITY, TaskChangeEventHandler())

        registerEvent(RES_CHANGE, TaskChangeEventHandler())

        registerEvent(GET_BANK_REWARD, TaskChangeEventHandler())

        registerEvent(GET_NEW_LIBRARY, TaskChangeEventHandler())

        registerEvent(GET_NEW_PHOTO, TaskChangeEventHandler())

        registerEvent(BUY_SHOP_TOTAL, TaskChangeEventHandler())

        registerEvent(GO_ALLIANCE_HELP, TaskChangeEventHandler())

        registerEvent(GET_NEW_FRIEND, TaskChangeEventHandler())

        registerEvent(GET_CASINO_BOSS_NUM, TaskChangeEventHandler())

        registerEvent(GET_CASINO_KILL_BOSS_NUM, TaskChangeEventHandler())

        registerEvent(KING_LV_CHANGE, TaskChangeEventHandler())

        registerEvent(CLEAR_TIME_EVENT, TaskChangeEventHandler())

        registerEvent(TALENT_LV_CHANGE, TaskChangeEventHandler())

    }

    private fun checkHandlesInit() {
        checkHandles[CHECK_WORLD_TALK_NUM] = CheckWorldTalkNum()
        checkHandles[CHECK_ALLIANCE_TALK_NUM] = CheckAllianceTalkNum()
        checkHandles[CHECK_ADD_FRIEND_NUM] = CheckFriendNum()
        checkHandles[CHECK_NORMAL_LOTTERY_ALL] = CheckNormalLotteryNum()
        checkHandles[CHECK_NORMAL_LOTTERY] = CheckNormalLotteryNowNum()
        checkHandles[CHECK_SUPER_LOTTERY_ALL] = CheckSuperLotteryNum()
        checkHandles[CHECK_SUPER_LOTTERY] = CheckSuperLotteryNowNum()
        checkHandles[CHECK_HAVE_HERO_NUM] = CheckHeroNum()
        checkHandles[CHECK_HAVE_AWAKE_HERO_NUM] = CheckHeroAwakeNum()
        checkHandles[CHECK_HAVE_STAR_HERO_NUM] = CheckHeroStarNum()
        checkHandles[CHECK_HERO_EQUIP_LV_UP_NUM_ALL] = CheckHeroEquipAdvNum()
        checkHandles[CHECK_HERO_EQUIP_LV_UP_NUM] = CheckHeroEquipAdvNowNum()
        checkHandles[CHECK_HERO_SKILL_STRONG_ALL] = CheckHeroSkillLvUpNum()
        checkHandles[CHECK_HERO_SKILL_STRONG] = CheckHeroSkillLvUpNowNum()
        checkHandles[CHECK_HERO_SKILL_NUM] = CheckHeroSkillNum()
        checkHandles[CHECK_BUILD_TYPE_LV] = CheckBuildLvToX()
        checkHandles[CHECK_BUILD_TYPE_LV_NUM] = CheckTypeBuildLvToX()
        checkHandles[CHECK_BUILD_RES_SPEED] = CheckBuildResSpeed()
        checkHandles[CHECK_SKIN_NUM] = CheckSkinNum()
        checkHandles[CHECK_SKIN_STRONG_NUM] = CheckSkinStrongNum()
        checkHandles[CHECK_GET_ONE_SKIN] = CheckSkinHave()
        checkHandles[CHECK_SKIN_LV_NUM] = CheckSkinLvNum()
        checkHandles[CHECK_MAKE_KING_EQUIP_NUM] = CheckGetKingEquipNum()
        checkHandles[CHECK_HAVE_KING_EQUIP_Q] = CheckKingEquipQNum()
        checkHandles[CHECK_MAKE_EQUIP] = CheckMakeKingEquipQNowNum()
        checkHandles[CHECK_COMM_CARD_NUM] = CheckCompoundCardNowNum()
        checkHandles[CHECK_ON_CARD_NUM] = CheckKingEquipOnCardNowNum()
        checkHandles[CHECK_NORMAL_MOVE_CITY_NUM] = CheckNormalMoveCityNum()
        checkHandles[CHECK_RANK_MOVE_CITY_NUM] = CheckRandMoveCityNum()
        checkHandles[CHECK_JJC_FIGHT_NUM] = CheckJjcFightNowNum()
        checkHandles[CHECK_JJC_FIGHT_NUM_ALL] = CheckJjcFightNum()
        checkHandles[CHECK_JJC_TO_RANK] = CheckJjcRankChangeNum()
        checkHandles[CHECK_JJC_SHOP_BUY_NUM] = CheckJjcShopBuyNum()
        checkHandles[CHECK_JJC_SHOP_REFRESH_NUM] = CheckJjcShopRefNum()
        checkHandles[CHECK_BUY_SUPRISE_NUM] = CheckSurpriseBuyNowNum()
        checkHandles[CHECK_BUY_SUPRISE_NUM_ALL] = CheckSurpriseBuyNum()
        checkHandles[CHECK_BANK_NUM] = CheckBankNum()
        checkHandles[CHECK_BANK_MONEY] = CheckBankMoney()
        checkHandles[CHECK_BEFORE_BANK_NUM] = CheckBeforeBankNum()
        checkHandles[CHECK_LIBRARY_NUM] = CheckLibraryNum()
        checkHandles[CHECK_PHOTO_NUM] = CheckPhotoNum()
        checkHandles[CHECK_DIAMOND_SHOP_BUY_NUM] = CheckDiamondShopBuyNum()
        checkHandles[CHECK_ALLIANCE_SHOP_BUY_NUM] = CheckAllianceShopBuyNum()
        checkHandles[CHECK_ACTIVITY_SHOP_BUY_NUM] = CheckActivityShopBuyNum()
        checkHandles[CHECK_GET_ONLINE_GIFT_NUM] = CheckGetOnlineGiftNum()
        checkHandles[CHECK_GET_ONLINE_BIG_GIFT_NUM] = CheckGetOnlineBigGiftNum()
        checkHandles[CHECK_DIAMOND_COST_NUM] = CheckCostDiamondNum()
        checkHandles[CHECK_ALLIANCE_COIN__COST_NUM] = CheckCostAllianceCoinNum()
        checkHandles[CHECK_ALLIANCE_HELP_NUM] = CheckAllianceHelpNowNum()
        checkHandles[CHECK_VIP_LV] = CheckVipLv()
        checkHandles[CHECK_UNIT_TASK_FINISH] = CheckUnitTask()
        checkHandles[CHECK_UNIT_TASK_NUM] = CheckUnitTaskNum()
        checkHandles[CHECK_MAIN_TASK_NUM] = CheckMainTaskNum()
        checkHandles[CHECK_MAIN_TASK_FINISH] = CheckMainTask()
        checkHandles[CHECK_ALLIANCE_COMPETITION_SCORE] = CheckAllianceCompetitionScore()
        checkHandles[CHECK_ALLIANCE_COMPETITION_TASK_NUM] = CheckAllianceCompetitionTaskNum()
        checkHandles[CHECK_ALLIANCE_COMPETITION_REWARD] = CheckAllianceCompetitionReward()
        checkHandles[CHECK_ALLIANCE_COMPETITION_RANK_LV] = CheckAllianceCompetitionRankLv()
        checkHandles[CHECK_ALLIANCE_COMPETITION_RANK] = CheckAllianceCompetitionRank()
        checkHandles[CHECK_CASINO_NUM] = CheckCasinoNum()
        checkHandles[CHECK_CASINO_BOSS_NUM] = CheckCasinoBossNum()
        checkHandles[CHECK_CASINO_KILL_BOSS_NUM] = CheckCasinoKillBossNum()
        checkHandles[CHECK_ALL_GET_RES_NUM] = CheckGetResNum()
        checkHandles[CHECK_KING_LV] = CheckKingLv()
        checkHandles[CHECK_RESEARCH] = CheckResearch()
        checkHandles[CHECK_COMPETITION_SCORE_NUM] = CheckCompetitionScoreNum()
        checkHandles[CHECK_BUY_SHIP_PROP_COUNT] = CheckBuyShipPropCount()
        checkHandles[CHECK_EFFECT_VALUE] = CheckResearchEffect()
        checkHandles[CHECK_CLEAR_TIME_NUM] = CheckClearTimeNum()
        checkHandles[CHECK_CLEAR_TIME_NOW_NUM] = CheckClearTimeNowNum()
    }
}

var taskM: TaskModule = TaskModule()