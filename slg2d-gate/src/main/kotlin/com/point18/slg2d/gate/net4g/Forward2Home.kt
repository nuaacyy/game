package com.point18.slg2d.gate.net4g

import com.point18.slg2d.common.netmsg.MsgType
import java.util.*

/**
 * 2500消息转发到Home
 */
val INIT_HOME_DATA_MSG_NO_RANGE = listOf(3350..3400)

/**
 * 需要转发至Home服务服的消息
 */
val FORWARD_TO_HOME_MSG_SET: Set<MsgType> = EnumSet.of(
    MsgType.EnterGame_4,
    MsgType.TransportResource_16,
    MsgType.PersonalPower_17,
    MsgType.WalkScout_18,
    MsgType.AddMark_19, //添加土地收藏
    MsgType.DelMark_20, //删除土地收藏

    MsgType.UpHeroSkill_31, //请求武将升级技能

    MsgType.MerchantShipQuery_32, // 商船查询
    MsgType.MerchantShipExchange_33, // 商船兑换
    MsgType.MerchantShipLock_34, // 商城物品锁定

    MsgType.Bank_37, // 银行投资
    MsgType.GetBank_38, // 收获银行投资
    MsgType.CancelBank_39, // 取消银行投资

    MsgType.BuySkin_44, // 购买城堡皮肤
    MsgType.ChangeSkin_45, // 切换城堡皮肤
    MsgType.StrengSkin_46, // 强化城堡皮肤

    MsgType.CreateInnerCity_50, // 新建内城建筑
    MsgType.UnlockInnerCity_51, // 解锁内城建筑
    MsgType.UpInnerCity_52, // 升级内城建筑
    MsgType.CancelUpInnerCity_53, // 取消内城建筑升级
    MsgType.DestroyInnerCity_54, // 拆除内城建筑
    MsgType.CancelDestroyInnerCity_55, // 取消拆除内城建筑
    MsgType.MoveInnerCity_56, // 移动内城建筑
    MsgType.UnlockInnerCityArea_57, // 解锁内城区域

    // 新手引导
    MsgType.GuideFin_119, // 完成一个引导

    //
    MsgType.GetTaskReward_113,
    MsgType.GetUnitTaskReward_120,

    //战报
    MsgType.GetEasyFightInfo_103,
    MsgType.GetDetailFightInfo_104,
    MsgType.DelBattleReport_130,
    MsgType.ArchiveBattleReport_131,
    MsgType.ReadedBattleReport_132,

    //装备
    MsgType.SellEquip_175,
    MsgType.ItemCompound_183,
    MsgType.ItemSplit_184,

    // 黑名单
    MsgType.InBlack_321,
    MsgType.OffBlack_322,

    //邮件
    MsgType.AllMails_450, //邮件列表查询
    MsgType.ReadMail_451, //阅读邮件
    MsgType.ReadAllMail_452,//一键已读
    MsgType.DrawMail_453, //邮件领取附件
    MsgType.DrawAllMail_454,//一键领取
    MsgType.DelMail_455, //删除邮件
    MsgType.BatchDelMail_456,//批量删除邮件
    MsgType.SignMail_457, // 收藏邮件

    MsgType.HeroCompound_670,

    // 好友
    MsgType.MakeFriend_701,
    MsgType.RemoveFriend_702,
    MsgType.MakeFriendGroup_703,
    MsgType.MoveInGroup_704,
    MsgType.RemoveGroup_706,
    MsgType.ChangeGroup_707,
    MsgType.QueryPlayerByName_709,
    MsgType.HandleFriendApply_710,

    // 竞技场
    MsgType.BuyJjcCount_722,   // 购买竞技场挑战次数
    MsgType.JjcGetRewards_714, // 领取奖励
    MsgType.GetRankGold_725, // 领取排名累计奖励
    MsgType.QueryJjcShopInfo_726, // 查询jjc商店
    MsgType.BuyJjcShopItem_727, // 购买竞技场商店的东西
    MsgType.RefreshJjcShopItem_728, // 刷新竞技场商店的东西
    MsgType.ExchangeJjcAchievementReward_729, // 兑换竞技场成就的东西
    MsgType.JjcQueryInfo_711, // 查询竞技场
    MsgType.JjcFight_720,   //  战斗 验证一些home东西，然后ask处理

    // (英雄无敌版)武将养成 1011-1020
    MsgType.HeroLvUp_1011, // 武将升级
    MsgType.HeroStarLvUp_1012, // 武将升星
    MsgType.HeroSuperUp_1013, // 武将进阶
    MsgType.HeroEquipUp_1014, // 武将装备进阶
    MsgType.HeroSkillLvUp_1015, // 武将技能升级
    MsgType.HeroCancelStarLvUp_1016, // 取消武将升星
    MsgType.HeroCancelSuperUp_1017, // 取消武将升阶
    MsgType.HeroGetStarLvUp_1019, // 武将兵团升级

    // 科技 1051-1060
    MsgType.ResearchLvUp_1051, // 升级科技
    MsgType.CancelResearchLvUp_1052, // 取消升级科技

    // 快速使用 1061-1070
    MsgType.ClearTime_1061, // 秒加速
    MsgType.BuyResShop_1062, // 购买resShop表中物品
    MsgType.UseProp_1063, // 使用道具
    MsgType.BuyAndUseProp_1064, // 购买并使用道具

    // 兵营,配兵,伤兵营 1081-1090
    MsgType.MakeSolider_1081, // 造兵
    MsgType.CancelMakeSolider_1082, // 取消造兵

    MsgType.CureSolider_1084, // 治疗兵
    MsgType.CancelCureSolider_1085, // 取消治疗兵

    //时光之盒
    MsgType.StudyTimeBox_1160, //研究时光之盒
    MsgType.CancelStudyTimeBox_1161, //取消研究时光之盒
    MsgType.GetStudyTimeBoxReward_1162, //领取时光之盒奖励
    MsgType.RemoveStudyTimeBoxReward_1163, //移除时光之盒

    // 君主功能 1211-1250
    MsgType.UseTalentPlan_1220, // 套用一个君主天赋预设
    MsgType.UnlockTalent_1211, // 解锁天赋
    MsgType.ResetTalentPoint_1212, // 重置天赋点数
    MsgType.QueryKingInfo_1216, //查询君主信息
    MsgType.SetTalentPlan_1217, // 设置一个君主天赋预设
    MsgType.DelTalentPlan_1218, // 删除一个君主天赋预设
    MsgType.QueryTalentPlans_1219, // 查询一个君主所有的天赋预设
    MsgType.MakeKingEquip_1221, // 锻造/升级君主装备
    MsgType.GetKingEquip_1222, // 领取君主装备
    MsgType.OnKingEquip_1224, // 穿君主装备
    MsgType.OffKingEquip_1225, // 脱君主装备
    MsgType.SplitKingEquip_1226, // 拆解君主装备
    MsgType.SetKingEquipPlan_1227, // 设置一个君主装备预设
    MsgType.DelKingEquipPlan_1228, // 删除一个君主装备预设
    MsgType.QueryKingEquipPlans_1229, // 查询一个君主所有的装备预设
    MsgType.UseKingEquipPlan_1230, // 套用一个君主装备预设
    MsgType.CancelMakeKingEquip_1231, // 取消一个君主装备锻造的队列

    MsgType.KingEquipAddCard_1232, // 给一个君主装备打卡片

    MsgType.KingEquipOffCard_1233, // 取下君主装备上的卡片

    MsgType.KingEquipCompoundCard_1234, // 卡片合成

    //战斗相关 1251-1299
    MsgType.SetForcePlan_1265, // 设置玩家的出征预设部队

    // 商店 1311 - 1315
    MsgType.ShopTotalBuy_1311, // 商店购买物品
    MsgType.ShopLimitTotalBuy_1312, // 商店购买物品

    // 在线礼包消息号 1401 - 1410
    MsgType.GetOnlineReward_1401, // 领取在线礼包
    MsgType.OpenOnlineReward_1402, // 打开在线礼包

    // 推图消息 1471 - 1490
    MsgType.BuyInstanceStrength_1475, // 购买体力

    //补充体力
    MsgType.SupplyEnergy_1494,

    // 头像   1511 - 1520
    MsgType.ChangeIcon_1511,
    MsgType.QueryAllIcon_1512,

    // 后宅   1521 - 1540
    MsgType.EnterGuildHouse_1521,
    MsgType.QueryFurnitureBag_1522,
    MsgType.PutFurniture_1523,
    MsgType.MoveFurniture_1524,
    MsgType.RemoveFurniture_1525,
    MsgType.BuyFurniture_1526,
    MsgType.UseHouseTheme_1527,
    MsgType.SaveHouseTheme_1528,
    MsgType.GatherFurnitureProduce_1529,
    MsgType.PutHeroIntoGuildHouse_1530,
    MsgType.ThumbUp_1531,
    MsgType.QueryThumbInfo_1532,
    MsgType.BuyHouseTheme_1533,
    MsgType.RemoveHouseTheme_1534,
    MsgType.RemoveTypeFurniture_1535,
    MsgType.ChangeGuildHouse_1536,
    MsgType.ChangeThemeName_1537,

    // 城墙
    MsgType.WallFireFight_1542,

    // 抽卡
    MsgType.QueryLottery_1545,
    MsgType.PlayLottery_1546,

    // 图书馆
    MsgType.SwitchLibTag_1556,

    // 他人领主信息
    MsgType.OtherPersonalPower_1576,

    // 赌场
    MsgType.GetCasinosInfo_1577,
    MsgType.GetJackpotInfo_1578,
    MsgType.CasinosLottery_1579,

    // 聊天
    MsgType.DelStrangerChat_296,
    MsgType.ChangeChatWindow_298,
    MsgType.OpenNewChatWindow_297,
    MsgType.ChangeRoomInfo_299,
    MsgType.SendChat_301,
    MsgType.GetChatInfo_319,
    MsgType.AddGroupChatMember_315,
    MsgType.CreateScopeMsg_304,
    MsgType.QueryGroupMembers_314,
    MsgType.DelGroupChatMember_316,
    MsgType.DelGroupChat_317,
    MsgType.AppointRoomOwner_309,
    MsgType.QuitChatRoom_312,
    MsgType.ChatFightInfoDetail_106,

    // vip
    MsgType.GainVipDayReward_740,

    // 联盟消息
    MsgType.AllianceQuit_811,
    MsgType.AllianceSetFlag_837,
    MsgType.AllianceNickName_897,
    MsgType.AllianceSetName_898,
    MsgType.AllianceImpeach_899,
    MsgType.BuyAllianceCompetitionQuest_910,
    MsgType.OpenAllianceHelp_1071,
    MsgType.SendAllianceHelp_1072,
    MsgType.GoAllianceHelp_1073,
    MsgType.OpenAllianceCompetitionMain_906,
    MsgType.OpenAllianceCompetitionReward_907,
    MsgType.GetAllianceCompetitionQuest_908,
    MsgType.RemoveAllianceCompetitionQuest_909,
    MsgType.BuyAllianceCompetitionQuest_910,
    MsgType.RewardAllianceCompetitionQuest_911,
    MsgType.CancelAllianceCompetitionQuest_912,
    MsgType.GetAllianceCompetitionReward_913,
    MsgType.OpenAfterAllianceCompetition_914,
    MsgType.RemoveAllianceWaijiao_919,

    // 银行
    MsgType.BankAccelerate_1561,
    MsgType.CancelBankAccelerate_1562,
    MsgType.GetBankAccelerate_1563,

    // 迁城
    MsgType.MakeGroundBuild_360,

    // 改名
    MsgType.ChangePlayerName_27,

    // 监狱
    MsgType.GainMaxPrisonLvBuff_1360,
    MsgType.EatPoison_1353,
    MsgType.GoRansom_1356,
    MsgType.KillPrison_1352,
    MsgType.ResurgenceAtOnce_1357,
    MsgType.SetRewardGold_1355,

    // 奇观
    MsgType.SendNoticeToLeaderOfAlliance_1460,

    // 礼包
    MsgType.QueryPayOrder_1582,
    MsgType.RecvMonthCardReward_1583,
    MsgType.QueryQuotaBagInfo_1584

)