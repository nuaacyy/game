package com.point18.slg2d.home.module

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.HomeHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.alliance.*
import com.point18.slg2d.home.module.askDeal.*
import com.point18.slg2d.home.module.bank.*
import com.point18.slg2d.home.module.barracks.*
import com.point18.slg2d.home.module.blackPlayer.InBlackDeal
import com.point18.slg2d.home.module.blackPlayer.OffBlackDeal
import com.point18.slg2d.home.module.buyInstanceStrength.BuyInstanceStrengthDeal
import com.point18.slg2d.home.module.casino.CasinosLotteryDeal
import com.point18.slg2d.home.module.casino.GetCasinoInfoDeal
import com.point18.slg2d.home.module.casino.GetJackpotInfoDeal
import com.point18.slg2d.home.module.changePlayerName.ChangePlayerNameDeal
import com.point18.slg2d.home.module.chat.*
import com.point18.slg2d.home.module.equip.*
import com.point18.slg2d.home.module.forceplan.SetForcePlanDeal
import com.point18.slg2d.home.module.friend.*
import com.point18.slg2d.home.module.getFightInfo.*
import com.point18.slg2d.home.module.giftBag.QueryPayOrderDeal
import com.point18.slg2d.home.module.giftBag.QueryQuotaBagInfoDeal
import com.point18.slg2d.home.module.giftBag.RecvMonthCardRewardDeal
import com.point18.slg2d.home.module.guideline.FinishGuidelineDeal
import com.point18.slg2d.home.module.guildhouse.*
import com.point18.slg2d.home.module.heroInvincible.*
import com.point18.slg2d.home.module.innerCity.*
import com.point18.slg2d.home.module.jjc.*
import com.point18.slg2d.home.module.kingEquip.*
import com.point18.slg2d.home.module.library.SwitchLibDeal
import com.point18.slg2d.home.module.lottery.PlayLotteryDeal
import com.point18.slg2d.home.module.lottery.QueryLotteryDeal
import com.point18.slg2d.home.module.mail.*
import com.point18.slg2d.home.module.mark.AddMarkDeal
import com.point18.slg2d.home.module.mark.DelMarkDeal
import com.point18.slg2d.home.module.merchantShip.MerchantShipExchangeDeal
import com.point18.slg2d.home.module.merchantShip.MerchantShipLockDeal
import com.point18.slg2d.home.module.merchantShip.MerchantShipQueryDeal
import com.point18.slg2d.home.module.moveCity.MoveCityDeal
import com.point18.slg2d.home.module.onlineGift.GetOnlineGiftRewardDeal
import com.point18.slg2d.home.module.onlineGift.OpenOnlineGiftDeal
import com.point18.slg2d.home.module.photo.ChangeIconDeal
import com.point18.slg2d.home.module.photo.QueryAllIconDeal
import com.point18.slg2d.home.module.prison.*
import com.point18.slg2d.home.module.realm.ClientDisconnectDeal
import com.point18.slg2d.home.module.realm.EnterGameDeal
import com.point18.slg2d.home.module.realm.RequireModuleDataDeal
import com.point18.slg2d.home.module.relic.CancelStudyTimeBoxDeal
import com.point18.slg2d.home.module.relic.GetStudyTimeBoxRewardDeal
import com.point18.slg2d.home.module.relic.RemoveStudyTimeBoxRewardDeal
import com.point18.slg2d.home.module.relic.StudyTimeBoxDeal
import com.point18.slg2d.home.module.research.CancelResearchDeal
import com.point18.slg2d.home.module.research.ResearchDeal
import com.point18.slg2d.home.module.serverdeal.*
import com.point18.slg2d.home.module.shop.BuyShopTotalDeal
import com.point18.slg2d.home.module.shop.QueryLimitShopDeal
import com.point18.slg2d.home.module.skin.BuySkinDeal
import com.point18.slg2d.home.module.skin.ChangeSkin
import com.point18.slg2d.home.module.skin.StrengthSkinDeal
import com.point18.slg2d.home.module.talent.*
import com.point18.slg2d.home.module.task.GetUnitTaskRewardDeal
import com.point18.slg2d.home.module.task.TaskRewardDeal
import com.point18.slg2d.home.module.useTimeSpeedUp.BuyClearTimeDeal
import com.point18.slg2d.home.module.useTimeSpeedUp.ClearTimeDeal
import com.point18.slg2d.home.module.viewprofile.OtherPersonalPowerDeal
import com.point18.slg2d.home.module.viewprofile.PersonalPowerDeal
import com.point18.slg2d.home.module.viewprofile.QueryKingInfoDeal
import com.point18.slg2d.home.module.vip.GainDayVipRewardDeal
import com.point18.slg2d.home.module.walk.ScoutDeal
import com.point18.slg2d.home.module.walk.TransportResDeal
import pb4server.*
import java.util.Arrays.asList

interface HomeClientMsgDeal {
    fun dealPlayerReq(session: PlayerActor, msg: MessageLite)
}

val msgDealsAtHome: Array<IClientDealWrap?> = arrayOfNulls<IClientDealWrap?>(3000)

val worldAskDeal: MutableMap<World2HomeAskReq.MsgCase, AskHomeDealBase> = mutableMapOf()
val worldTellDeal: MutableMap<World2HomeTell.MsgCase, TellHomeDealBase> = mutableMapOf()
val homeAskDeal: MutableMap<Home2HomeAskReq.MsgCase, AskHomeDealBase> = mutableMapOf()
val homeTellDeal: MutableMap<Home2HomeTell.MsgCase, TellHomeDealBase> = mutableMapOf()
val publicTellDeal: MutableMap<Public2HomeTell.MsgCase, TellHomeDealBase> = mutableMapOf()

fun registerMsgDeal() {
    worldAskDeal[World2HomeAskReq.MsgCase.SYNCHOMEASKREQ] = W2HAskDealWrap(SyncHomeDataDeal())
    worldAskDeal[World2HomeAskReq.MsgCase.ADDRELICREWARDASKREQ] = W2HAskDealWrap(AddRelicRewardDeal())
    worldAskDeal[World2HomeAskReq.MsgCase.ADDRESTOHOMEASKREQ] = W2HAskDealWrap(AddResToHomeDeal())
    worldAskDeal[World2HomeAskReq.MsgCase.ADDHEROEXPASKREQ] = W2HAskDealWrap(AddHeroExpDeal())
    worldAskDeal[World2HomeAskReq.MsgCase.RELOADHOMECONFIGASKREQ] = W2HAskDealWrap(ReloadConfigDeal())
    worldAskDeal[World2HomeAskReq.MsgCase.OPENLIBRARYITEMASKREQ] = W2HAskDealWrap(OpenLibraryItemDeal())
    worldAskDeal[World2HomeAskReq.MsgCase.TRIGGERHEARTASKREQ] = W2HAskDealWrap(TriggerHeartDeal())
    worldAskDeal[World2HomeAskReq.MsgCase.QUERYFRIENDBLACKASKREQ] = W2HAskDealWrap(QueryFriendBlackDeal())
    worldAskDeal[World2HomeAskReq.MsgCase.QUERYPLAYERINFOASKREQ] = W2HAskDealWrap(QueryPlayerInfoDeal())
    worldAskDeal[World2HomeAskReq.MsgCase.PLUNDERASKREQ] = W2HAskDealWrap(PlunderDeal())
    worldAskDeal[World2HomeAskReq.MsgCase.GETACHIEVEMENTREWARDASKREQ] = W2HAskDealWrap(GetAchievementRewardDeal())
    worldAskDeal[World2HomeAskReq.MsgCase.MAKECITYASKHOMEREQ] = W2HAskDealWrap(MakeCityHomeDeal())
    worldAskDeal[World2HomeAskReq.MsgCase.CREATEBATTLEREPORTASKHOMEREQ] = W2HAskDealWrap(AddBattleReportDeal())
    worldAskDeal[World2HomeAskReq.MsgCase.SETHOMEMOVESERVERSTATEREQ] = W2HAskDealWrap(SetHomeMoveServerStateDeal())
    worldAskDeal[World2HomeAskReq.MsgCase.CHECKJOINALLIANCESTATEREQ] =
            W2HAskDealWrap(CheckJoinAllianceStateFromWorldDeal())

    for (eachDeal in worldAskDeal.values) {
        if (eachDeal is HomeHelper) {
            eachDeal.initHelper()
        }
    }

    worldTellDeal[World2HomeTell.MsgCase.CREATEMAILTELL] = W2HTellDealWrap(CreateMailDeal())
    worldTellDeal[World2HomeTell.MsgCase.LEAVEALLIANCETELL] = W2HTellDealWrap(LeaveAllianceDeal())
    worldTellDeal[World2HomeTell.MsgCase.HAVEALLIANCETELL] = W2HTellDealWrap(HaveAllianceDeal())
    worldTellDeal[World2HomeTell.MsgCase.ALLIANCEINFOCHANGETELL] = W2HTellDealWrap(AllianceInfoChangeDeal())
    worldTellDeal[World2HomeTell.MsgCase.TASKFINISHONWORLDTELL] = W2HTellDealWrap(TaskFinishOnWorldDeal())
    worldTellDeal[World2HomeTell.MsgCase.USEGMREQTELL] = W2HTellDealWrap(UseGmDeal())
    worldTellDeal[World2HomeTell.MsgCase.ACHIEVEFINISHONWORLDTELL] = W2HTellDealWrap(AchieveFinishOnWorldDeal())
    worldTellDeal[World2HomeTell.MsgCase.FRIENDCHANGETOHOMETELL] = W2HTellDealWrap(FriendChangeToHomeDeal())
    worldTellDeal[World2HomeTell.MsgCase.UNLOCKJOINALLIANCESTATETELL] =
            W2HTellDealWrap(UnLockJoinAllianceStateTellDeal())
    worldTellDeal[World2HomeTell.MsgCase.JOINNOSUCCESSTELL] = W2HTellDealWrap(JoinNoSuccessTellDeal())


    for (eachDeal in worldTellDeal.values) {
        if (eachDeal is HomeHelper) {
            eachDeal.initHelper()
        }
    }

    homeAskDeal[Home2HomeAskReq.MsgCase.BEFOREJOINROOMASKREQ] = H2HAskDealWrap(BeforeJoinChatDeal())
    homeAskDeal[Home2HomeAskReq.MsgCase.ASKSTRANGERINFOASKREQ] = H2HAskDealWrap(AskStrangerInfoDeal())
    homeAskDeal[Home2HomeAskReq.MsgCase.THUMBTOPLAYERASKREQ] = H2HAskDealWrap(ThumbInDeal())
    homeAskDeal[Home2HomeAskReq.MsgCase.FRIENDNOTICEASKREQ] = H2HAskDealWrap(FriendNoticeDeal())
    homeAskDeal[Home2HomeAskReq.MsgCase.FRIENDACCEPTASKREQ] = H2HAskDealWrap(FriendAcceptDeal())
    homeAskDeal[Home2HomeAskReq.MsgCase.FRIENDREMOVEASKREQ] = H2HAskDealWrap(FriendRemoveDeal())
    homeAskDeal[Home2HomeAskReq.MsgCase.FRIENDGROUPNOTICEASKREQ] = H2HAskDealWrap(FriendGroupNoticeDeal())
    homeAskDeal[Home2HomeAskReq.MsgCase.FINDPLAYERINBLACKASKREQ] = H2HAskDealWrap(FindPlayerDeal())
    homeAskDeal[Home2HomeAskReq.MsgCase.QUERYINFOBYHOMEREQ] = H2HAskDealWrap(FindPlayerInfoDeal())
    homeAskDeal[Home2HomeAskReq.MsgCase.QUERYBATTLEREPORTINFOASKREQ] = H2HAskDealWrap(QueryBattleReportDeal())
    homeAskDeal[Home2HomeAskReq.MsgCase.ASKFIGHTINFODETAILASKREQ] = H2HAskDealWrap(AskChatBattleDetail())
    homeAskDeal[Home2HomeAskReq.MsgCase.QUERYPLAYERTARGETASKREQ] = H2HAskDealWrap(QueryPlayerTargetDeal())

    for (eachDeal in homeAskDeal.values) {
        if (eachDeal is HomeHelper) {
            eachDeal.initHelper()
        }
    }

    homeTellDeal[Home2HomeTell.MsgCase.CHATROOMDISMISSTELL] = H2HTellDealWrap(RoomDismissDeal())
    homeTellDeal[Home2HomeTell.MsgCase.JOINCHATROOMTELL] = H2HTellDealWrap(SubscribeRoomMsgDeal())
    homeTellDeal[Home2HomeTell.MsgCase.CHATROOMKICKOUTTELL] = H2HTellDealWrap(UnsubscribeRoomDeal())
    homeTellDeal[Home2HomeTell.MsgCase.CHATROOMNEWNAMETELL] = H2HTellDealWrap(ChatRoomNewNameDeal())
    homeTellDeal[Home2HomeTell.MsgCase.CHATROOMCHANGEINFOTELL] = H2HTellDealWrap(ChatRoomInfoChangeDeal())
    homeTellDeal[Home2HomeTell.MsgCase.SAVEFRIENDCHATRECORDTELL] = H2HTellDealWrap(FriendPrivateChatDeal())
    homeTellDeal[Home2HomeTell.MsgCase.BLACKTELL] = H2HTellDealWrap(BlackPlayerDeal())
    homeTellDeal[Home2HomeTell.MsgCase.FRIENDREFRESHNOTICETELL] = H2HTellDealWrap(FriendRefreshNoticeDeal())
    homeTellDeal[Home2HomeTell.MsgCase.CREATEMAILTELL] = H2HTellDealWrap(HomeCreateMailDeal())

    for (eachDeal in homeTellDeal.values) {
        if (eachDeal is HomeHelper) {
            eachDeal.initHelper()
        }
    }

    // public -> home 的tell
    publicTellDeal[Public2HomeTell.MsgCase.DEALAFTERHELPTELL] =
        P2HTellDealWrap(DealAfterHelpDeal())
    publicTellDeal[Public2HomeTell.MsgCase.ALLIANCECOMPETITIONOVERNOTIC2GTELL] =
        P2HTellDealWrap(AllianceCompetitionOverNotice2GDeal())
    publicTellDeal[Public2HomeTell.MsgCase.ALLIANCECOMPETITIONINFOCHANGENOTIC2GTELL] =
        P2HTellDealWrap(AllianceCompetitionInfoChangeNotice2GDeal())
    publicTellDeal[Public2HomeTell.MsgCase.OCCUPYWONDERTELL] =
        P2HTellDealWrap(OccupyWonderDeal())

    for (eachDeal in publicTellDeal.values) {
        if (eachDeal is HomeHelper) {
            eachDeal.initHelper()
        }
    }

    //进入游戏
    registerClientDeal(MsgType.EnterGame_4.msgType, EnterGameDeal())

    //运输资源
    registerClientDeal(MsgType.TransportResource_16.msgType, TransportResDeal())

    //领主信息
    registerClientDeal(MsgType.PersonalPower_17.msgType, PersonalPowerDeal())
    registerClientDeal(MsgType.QueryKingInfo_1216.msgType, QueryKingInfoDeal())

    //他人领主信息
    registerClientDeal(MsgType.OtherPersonalPower_1576.msgType, OtherPersonalPowerDeal())

    //侦查
    registerClientDeal(MsgType.WalkScout_18.msgType, ScoutDeal())

    //坐标收藏
    registerClientDeal(MsgType.AddMark_19.msgType, AddMarkDeal())
    registerClientDeal(MsgType.DelMark_20.msgType, DelMarkDeal())

    //商船
    registerClientDeal(MsgType.MerchantShipQuery_32.msgType, MerchantShipQueryDeal())
    registerClientDeal(MsgType.MerchantShipExchange_33.msgType, MerchantShipExchangeDeal())
    registerClientDeal(MsgType.MerchantShipLock_34.msgType, MerchantShipLockDeal())

    //银行投资
    registerClientDeal(MsgType.Bank_37.msgType, BankDeal())
    registerClientDeal(MsgType.GetBank_38.msgType, GetBankDeal())
    registerClientDeal(MsgType.CancelBank_39.msgType, CancelBankDeal())

    //皮肤
    registerClientDeal(MsgType.BuySkin_44.msgType, BuySkinDeal())
    registerClientDeal(MsgType.ChangeSkin_45.msgType, ChangeSkin())
    registerClientDeal(MsgType.StrengSkin_46.msgType, StrengthSkinDeal())

    //内城模块
    registerClientDeal(MsgType.CreateInnerCity_50.msgType, CreateInnerCityDeal())
    registerClientDeal(MsgType.UnlockInnerCity_51.msgType, UnlockInnerCityDeal())
    registerClientDeal(MsgType.UpInnerCity_52.msgType, UpgradeInnerCityDeal())
    registerClientDeal(MsgType.CancelUpInnerCity_53.msgType, CancelUpgradeInnerCityDeal())
    registerClientDeal(MsgType.DestroyInnerCity_54.msgType, DestroyInnerCityDeal())
    registerClientDeal(MsgType.CancelDestroyInnerCity_55.msgType, CancelDestroyInnerCityDeal())
    registerClientDeal(MsgType.MoveInnerCity_56.msgType, MoveInnerCityDeal())
    registerClientDeal(MsgType.UnlockInnerCityArea_57.msgType, UnlockInnerCityAreaDeal())

    // 新手引导
    registerClientDeal(MsgType.GuideFin_119.msgType, FinishGuidelineDeal())

    //战报
    registerClientDeal(MsgType.GetEasyFightInfo_103.msgType, GetBattleReportDeal())
    registerClientDeal(MsgType.GetDetailFightInfo_104.msgType, GetDetailBattleReportDeal())
    registerClientDeal(MsgType.DelBattleReport_130.msgType, DelBattleReportDeal())
    registerClientDeal(MsgType.ArchiveBattleReport_131.msgType, ArchiveBattleReportDeal())
    registerClientDeal(MsgType.ReadedBattleReport_132.msgType, ReadedBattleReportDeal())

    // 黑名单
    registerClientDeal(MsgType.InBlack_321.msgType, InBlackDeal())
    registerClientDeal(MsgType.OffBlack_322.msgType, OffBlackDeal())

    //邮件消息
    registerClientDeal(MsgType.AllMails_450.msgType, AllMailDeal())
    registerClientDeal(MsgType.ReadMail_451.msgType, ReadMailDeal())
    registerClientDeal(MsgType.ReadAllMail_452.msgType, ReadAllMailDeal())
    registerClientDeal(MsgType.DrawMail_453.msgType, DrawMailDeal())
    registerClientDeal(MsgType.DrawAllMail_454.msgType, DrawAllMailDeal())
    registerClientDeal(MsgType.DelMail_455.msgType, DelMailDeal())
    registerClientDeal(MsgType.BatchDelMail_456.msgType, BatchDelMailDeal())
    registerClientDeal(MsgType.SignMail_457.msgType, SignMailDeal())
    //直接由Public处理
//    registerClientDeal(MsgType.SendAllianceMail_458.msgType, SendAllianceMailDeal())

    // 装备模块
    registerClientDeal(MsgType.SellEquip_175.msgType, SellEquipDeal())
    registerClientDeal(MsgType.ItemCompound_183.msgType, ItemCompoundDeal())
    registerClientDeal(MsgType.ItemSplit_184.msgType, ItemSplitDeal())

    // 好友
    registerClientDeal(MsgType.MakeFriend_701.msgType, MakeFriendDeal())
    registerClientDeal(MsgType.RemoveFriend_702.msgType, RemoveFriendDeal())
    registerClientDeal(MsgType.MakeFriendGroup_703.msgType, CreateGroupDeal())
    registerClientDeal(MsgType.MoveInGroup_704.msgType, MoveInGroupDeal())
    registerClientDeal(MsgType.RemoveGroup_706.msgType, RemoveGroupDeal())
    registerClientDeal(MsgType.ChangeGroup_707.msgType, UpdateGroupNameDeal())
    registerClientDeal(MsgType.QueryPlayerByName_709.msgType, QueryPlayerByNameDeal())
    registerClientDeal(MsgType.HandleFriendApply_710.msgType, HandleFriendApplyDeal())

    //加速
    registerClientDeal(MsgType.ClearTime_1061.msgType, ClearTimeDeal())
    registerClientDeal(MsgType.BuyResShop_1062.msgType, BuyClearTimeDeal())

    //使用道具
    registerClientDeal(MsgType.UseProp_1063.msgType, UsePropDeal())
    registerClientDeal(MsgType.BuyAndUseProp_1064.msgType, BuyAndUsePropDeal())

    //兵营
    registerClientDeal(MsgType.MakeSolider_1081.msgType, MakeSoliderDeal())
    registerClientDeal(MsgType.CancelMakeSolider_1082.msgType, CancelMakeSoliderDeal())
    registerClientDeal(MsgType.CureSolider_1084.msgType, CureSoliderDeal())
    registerClientDeal(MsgType.CancelCureSolider_1085.msgType, CancelCureSoliderDeal())

    // 城墙
    registerClientDeal(MsgType.WallFireFight_1542.msgType, CastleFireDeal())

    //天赋
    registerClientDeal(MsgType.UseTalentPlan_1220.msgType, UseTalentPlanDeal())
    registerClientDeal(MsgType.UnlockTalent_1211.msgType, UpgradeTalentDeal())
    registerClientDeal(MsgType.ResetTalentPoint_1212.msgType, ResetTalentPointDeal())
    registerClientDeal(MsgType.SetTalentPlan_1217.msgType, SetTalentPlanDeal())
    registerClientDeal(MsgType.DelTalentPlan_1218.msgType, DelTalentPlanDeal())
    registerClientDeal(MsgType.QueryTalentPlans_1219.msgType, QueryTalentPlansDeal())

    //kinginfo
    registerClientDeal(MsgType.MakeKingEquip_1221.msgType, MakeKingEquipDeal())
    registerClientDeal(MsgType.GetKingEquip_1222.msgType, GetKingEquipDeal())
    registerClientDeal(MsgType.OnKingEquip_1224.msgType, PutOnKingEquipDeal())
    registerClientDeal(MsgType.OffKingEquip_1225.msgType, PutOffKingEquipDeal())
    registerClientDeal(MsgType.SplitKingEquip_1226.msgType, SplitKingEquipDeal())
    registerClientDeal(MsgType.SetKingEquipPlan_1227.msgType, SetKingEquipPlanDeal())
    registerClientDeal(MsgType.DelKingEquipPlan_1228.msgType, DelKingEquipPlanDeal())
    registerClientDeal(MsgType.QueryKingEquipPlans_1229.msgType, QueryKingEquipPlansDeal())
    registerClientDeal(MsgType.UseKingEquipPlan_1230.msgType, UseKingEquipPlanDeal())
    registerClientDeal(MsgType.CancelMakeKingEquip_1231.msgType, CancelMakeKingEquipDeal())
    registerClientDeal(MsgType.KingEquipAddCard_1232.msgType, KingEquipAddCardDeal())
    registerClientDeal(MsgType.KingEquipOffCard_1233.msgType, KingEquipOffCardDeal())
    registerClientDeal(MsgType.KingEquipCompoundCard_1234.msgType, CompoundCardDeal())

    //部队阵容
    registerClientDeal(MsgType.SetForcePlan_1265.msgType, SetForcePlanDeal())

    // 商店购买
    registerClientDeal(MsgType.ShopTotalBuy_1311.msgType, BuyShopTotalDeal())
    registerClientDeal(MsgType.ShopLimitTotalBuy_1312.msgType, QueryLimitShopDeal())

    // 科技模块
    registerClientDeal(MsgType.ResearchLvUp_1051.msgType, ResearchDeal())
    registerClientDeal(MsgType.CancelResearchLvUp_1052.msgType, CancelResearchDeal())

    // 在线礼包
    registerClientDeal(MsgType.GetOnlineReward_1401.msgType, GetOnlineGiftRewardDeal())
    registerClientDeal(MsgType.OpenOnlineReward_1402.msgType, OpenOnlineGiftDeal())

    registerClientDeal(MsgType.BuyInstanceStrength_1475.msgType, BuyInstanceStrengthDeal())
    registerClientDeal(MsgType.SupplyEnergy_1494.msgType, SupplyEnergyDeal())

    //头像
    registerClientDeal(MsgType.ChangeIcon_1511.msgType, ChangeIconDeal())
    registerClientDeal(MsgType.QueryAllIcon_1512.msgType, QueryAllIconDeal())

    // 后宅
    registerClientDeal(MsgType.EnterGuildHouse_1521.msgType, EnterGuildHouseDeal())
    registerClientDeal(MsgType.QueryFurnitureBag_1522.msgType, QueryFurnitureBagDeal())
    registerClientDeal(MsgType.PutFurniture_1523.msgType, PutFurnitureDeal())
    registerClientDeal(MsgType.MoveFurniture_1524.msgType, MoveFurnitureDeal())
    registerClientDeal(MsgType.RemoveFurniture_1525.msgType, RemoveFurnitureDeal())
    registerClientDeal(MsgType.BuyFurniture_1526.msgType, BuyFurnitureDeal())
    registerClientDeal(MsgType.UseHouseTheme_1527.msgType, UseHouseThemeDeal())
    registerClientDeal(MsgType.SaveHouseTheme_1528.msgType, SaveHouseThemeDeal())
    registerClientDeal(MsgType.GatherFurnitureProduce_1529.msgType, GatherFurnitureProduceDeal())
    registerClientDeal(MsgType.PutHeroIntoGuildHouse_1530.msgType, PutHeroIntoGuildHouseDeal())
    registerClientDeal(MsgType.ThumbUp_1531.msgType, ThumbUpDeal())
    registerClientDeal(MsgType.QueryThumbInfo_1532.msgType, QueryThumbInfoDeal())
    registerClientDeal(MsgType.BuyHouseTheme_1533.msgType, BuyHouseThemeDeal())
    registerClientDeal(MsgType.RemoveHouseTheme_1534.msgType, RemoveHouseThemeDeal())
    registerClientDeal(MsgType.RemoveTypeFurniture_1535.msgType, RemoveTypeFurnitureDeal())
    registerClientDeal(MsgType.ChangeGuildHouse_1536.msgType, ChangeGuildHouseDeal())
    registerClientDeal(MsgType.ChangeThemeName_1537.msgType, ChangeThemeNameDeal())

    registerClientDeal(MsgType.QueryLottery_1545.msgType, QueryLotteryDeal())
    registerClientDeal(MsgType.PlayLottery_1546.msgType, PlayLotteryDeal())

    // 迁城
    registerClientDeal(MsgType.MakeGroundBuild_360.msgType, MoveCityDeal())

    // 图书馆
    registerClientDeal(MsgType.SwitchLibTag_1556.msgType, SwitchLibDeal())

    //银行投资
    registerClientDeal(MsgType.BankAccelerate_1561.msgType, BankAccelerateDeal())
    registerClientDeal(MsgType.GetBankAccelerate_1563.msgType, GetBankAccelerateDeal())
    registerClientDeal(MsgType.CancelBankAccelerate_1562.msgType, CancelBankAccelerateDeal())

    //赌场
    registerClientDeal(MsgType.GetCasinosInfo_1577.msgType, GetCasinoInfoDeal())
    registerClientDeal(MsgType.GetJackpotInfo_1578.msgType, GetJackpotInfoDeal())
    registerClientDeal(MsgType.CasinosLottery_1579.msgType, CasinosLotteryDeal())

    // 联盟
    registerClientDeal(MsgType.AllianceQuit_811.msgType, AllianceMemberQuitDeal())
    registerClientDeal(MsgType.AllianceSetFlag_837.msgType, SetAllianceFlagDeal())
    registerClientDeal(MsgType.AllianceNickName_897.msgType, SetAllianceNickNameDeal())
    registerClientDeal(MsgType.AllianceSetName_898.msgType, SetAllianceNameDeal())
    registerClientDeal(MsgType.AllianceImpeach_899.msgType, AllianceImpeachDeal())
    registerClientDeal(MsgType.OpenAllianceCompetitionMain_906.msgType, OpenAllianceCompetitionMainDeal())
    registerClientDeal(MsgType.OpenAllianceCompetitionReward_907.msgType, OpenAllianceCompetitionRewardDeal())
    registerClientDeal(MsgType.GetAllianceCompetitionQuest_908.msgType, GetAllianceCompetitionQuestDeal())
    registerClientDeal(MsgType.RemoveAllianceCompetitionQuest_909.msgType, RemoveAllianceCompetitionQuestDeal())
    registerClientDeal(MsgType.BuyAllianceCompetitionQuest_910.msgType, BuyAllianceCompetitionQuestDeal())
    registerClientDeal(MsgType.RewardAllianceCompetitionQuest_911.msgType, RewardAllianceCompetitionQuestDeal())
    registerClientDeal(MsgType.CancelAllianceCompetitionQuest_912.msgType, CancelAllianceCompetitionQuestDeal())
    registerClientDeal(MsgType.GetAllianceCompetitionReward_913.msgType, GetAllianceCompetitionRewardDeal())
    registerClientDeal(MsgType.OpenAfterAllianceCompetition_914.msgType, OpenAfterAllianceCompetitionDeal())
    registerClientDeal(MsgType.RemoveAllianceWaijiao_919.msgType, RemoveAllianceWaijiaoDeal())

    registerClientDeal(MsgType.OpenAllianceHelp_1071.msgType, OpenAllianceHelpDeal2())
    registerClientDeal(MsgType.SendAllianceHelp_1072.msgType, SendAllianceHelpDeal2())
    registerClientDeal(MsgType.GoAllianceHelp_1073.msgType, GoAllianceHelpDeal2())

    // 奇观
    registerClientDeal(MsgType.SendNoticeToLeaderOfAlliance_1460.msgType, SendNoticeToLeaderOfAllianceDeal())

    // 竞技场
    registerClientDeal(MsgType.JjcFight_720.msgType, ArenaFightDeal())
    registerClientDeal(MsgType.BuyJjcShopItem_727.msgType, BuyJjcShopItemDeal())
    registerClientDeal(MsgType.BuyJjcCount_722.msgType, BuyJjcCountDeal())
    registerClientDeal(MsgType.ExchangeJjcAchievementReward_729.msgType, ExchangeRewardDeal())
    registerClientDeal(MsgType.GetRankGold_725.msgType, GetRankGoldDeal())
    registerClientDeal(MsgType.JjcGetRewards_714.msgType, GetRewardsDeal())
    registerClientDeal(MsgType.JjcQueryInfo_711.msgType, QueryJjcInfoDeal())
    registerClientDeal(MsgType.QueryJjcShopInfo_726.msgType, QueryJjcShopInfoDeal())
    registerClientDeal(MsgType.RefreshJjcShopItem_728.msgType, RefreshJjcShopItemDeal())

    // vip
    registerClientDeal(MsgType.GainVipDayReward_740.msgType, GainDayVipRewardDeal())

    // 聊天
    registerClientDeal(MsgType.SendChat_301.msgType, SendMessageDeal())
    registerClientDeal(MsgType.GetChatInfo_319.msgType, GetMessageDeal())
    registerClientDeal(MsgType.AddGroupChatMember_315.msgType, InviteChatRoomDeal())
    registerClientDeal(MsgType.CreateScopeMsg_304.msgType, CreateChatRoomDeal())
    registerClientDeal(MsgType.QueryGroupMembers_314.msgType, QueryRoomMemberDeal())
    registerClientDeal(MsgType.DelGroupChatMember_316.msgType, KickOutMemberDeal())
    registerClientDeal(MsgType.ChangeRoomInfo_299.msgType, RenameChatRoomDeal())
    registerClientDeal(MsgType.DelGroupChat_317.msgType, DismissChatGroupDeal())
    registerClientDeal(MsgType.AppointRoomOwner_309.msgType, AppointRoomOwnerDeal())
    registerClientDeal(MsgType.OpenNewChatWindow_297.msgType, OpenChatWindowDeal())
    registerClientDeal(MsgType.ChangeChatWindow_298.msgType, TurnChatWindowDeal())
    registerClientDeal(MsgType.DelStrangerChat_296.msgType, DelStrangerChatDeal())
    registerClientDeal(MsgType.QuitChatRoom_312.msgType, QuitChatRoomDeal())
    registerClientDeal(MsgType.ChatFightInfoDetail_106.msgType, GetChatBattleReportDeal())

    // 改玩家名
    registerClientDeal(MsgType.ChangePlayerName_27.msgType, ChangePlayerNameDeal())

    // 监狱
    registerClientDeal(MsgType.GainMaxPrisonLvBuff_1360.msgType, GainMaxPrisonBuffDeal())
    registerClientDeal(MsgType.EatPoison_1353.msgType, EatPoisonDeal())
    registerClientDeal(MsgType.GoRansom_1356.msgType, GiveRansomDeal())
    registerClientDeal(MsgType.SetRewardGold_1355.msgType, SetRewardGold())
    registerClientDeal(MsgType.KillPrison_1352.msgType, KillPrisonHomeDeal())
    registerClientDeal(MsgType.ResurgenceAtOnce_1357.msgType, ResurgenceAtOnceHomeDeal())

    // 武将相关
    registerClientDeal(MsgType.HeroCancelStarLvUp_1016.msgType, HeroCancelStarLvUpDeal())
    registerClientDeal(MsgType.HeroCancelSuperUp_1017.msgType, HeroCancelSuperLvUpDeal())
    registerClientDeal(MsgType.HeroCompound_670.msgType, HeroCompoundDeal())
    registerClientDeal(MsgType.HeroEquipUp_1014.msgType, HeroEquipLvUpDeal())
    registerClientDeal(MsgType.HeroGetStarLvUp_1019.msgType, HeroGetStarLvUpDeal())
    registerClientDeal(MsgType.HeroLvUp_1011.msgType, HeroLvUpDeal())
    registerClientDeal(MsgType.HeroSkillLvUp_1015.msgType, HeroSkillLvUpDeal())
    registerClientDeal(MsgType.HeroStarLvUp_1012.msgType, HeroStarLvUpDeal())
    registerClientDeal(MsgType.HeroSuperUp_1013.msgType, HeroSuperLvUpDeal())

    //时光之盒
    registerClientDeal(MsgType.StudyTimeBox_1160.msgType, StudyTimeBoxDeal())
    registerClientDeal(MsgType.CancelStudyTimeBox_1161.msgType, CancelStudyTimeBoxDeal())
    registerClientDeal(MsgType.GetStudyTimeBoxReward_1162.msgType, GetStudyTimeBoxRewardDeal())
    registerClientDeal(MsgType.RemoveStudyTimeBoxReward_1163.msgType, RemoveStudyTimeBoxRewardDeal())

    // 任务模块
    registerClientDeal(MsgType.GetTaskReward_113.msgType, TaskRewardDeal())
    registerClientDeal(MsgType.GetUnitTaskReward_120.msgType, GetUnitTaskRewardDeal())

    // 客户端初始数据
    registerClientDeal(MsgType.RequireModuleData_2500.msgType, RequireModuleDataDeal())

    // 礼包请求
    registerClientDeal(MsgType.QueryPayOrder_1582.msgType, QueryPayOrderDeal())
    registerClientDeal(MsgType.RecvMonthCardReward_1583.msgType, RecvMonthCardRewardDeal())
    registerClientDeal(MsgType.QueryQuotaBagInfo_1584.msgType, QueryQuotaBagInfoDeal())

    // 断线处理
    registerClientDeal(MsgType.Offline_2999.msgType, ClientDisconnectDeal())

    for (eachDeal in msgDealsAtHome) {
        if (eachDeal is HomeHelper) {
            eachDeal.initHelper()
        }
    }

}

interface IClientDealWrap {
    fun dealPlayerReq(session: PlayerActor, clientMsgNo: Int, msg: MessageLite)
}

class ClientDealWrap(private val homeClientMsgDeal: HomeClientMsgDeal) : IClientDealWrap,
    HomeHelperPlus1<HomePlayerDC>(
        HomePlayerDC::class.java, asList(homeClientMsgDeal as HomeHelper)
    ) {

    override fun dealPlayerReq(session: PlayerActor, clientMsgNo: Int, msg: MessageLite) {
        // 这里是客户端消息处理的起点，所以在这里require需要的DC。
        requireDc(session) {

            session.clientMsgNo = clientMsgNo // 这步的位置很关键，必须在requireDc方法之内执行！

            homeClientMsgDeal.dealPlayerReq(session, msg)
        }
    }

}

fun registerClientDeal(msgType: Int, deal: HomeClientMsgDeal) {
    val wrap = ClientDealWrap(deal)
    msgDealsAtHome[msgType] = wrap
}
