package com.point18.slg2d.common.netmsg

import com.google.protobuf.MessageLite
import pb4client.*

//找到消息号，才能根据消息号进行编码和解码
enum class MsgType(val msgType: Int, val req: MessageLite?, val resp: MessageLite?) {

    Connected(0, null, null),
    Disconnected(10000, null, null),

    CtrlNoticeOffline(10001, null, null), // 通知后端，客户端连接已断开

    Error(20000, null, null),
    Unknown_Msg_20001(20001, null, null),

    Offline_2999(2999, null, null), // 系统机制消息,不要直接发送

    Login_1(1, Login.getDefaultInstance(), LoginRt.getDefaultInstance()), //发送登录消息，包括账号和密码
    MakeCity_2(2, MakeCity.getDefaultInstance(), MakeCityRt.getDefaultInstance()), //创建城池
    //	Random_Name_3(3, ), //获取随机名
    EnterGame_4(4, EnterGame.getDefaultInstance(), EnterGameRt.getDefaultInstance()), //进入游戏

    QueryAndAmendPersonal_11(
        11,
        QueryAndAmendPersonal.getDefaultInstance(),
        QueryAndAmendPersonalRt.getDefaultInstance()
    ), //查询和修改个人介绍

    Walk_15(15, Walk.getDefaultInstance(), WalkRt.getDefaultInstance()), //部队行军
    TransportResource_16(16, TransportResource.getDefaultInstance(), TransportResourceRt.getDefaultInstance()), //运输资源
    PersonalPower_17(17, PersonalPower.getDefaultInstance(), PersonalPowerRt.getDefaultInstance()), //查询个人势力

    WalkScout_18(18, WalkScout.getDefaultInstance(), WalkScoutRt.getDefaultInstance()), //侦查

    AddMark_19(19, AddMark.getDefaultInstance(), AddMarkRt.getDefaultInstance()), //添加土地收藏
    DelMark_20(20, DelMark.getDefaultInstance(), DelMarkRt.getDefaultInstance()), //删除土地收藏
    ServerTime_21(21, FetchServerTime.getDefaultInstance(), FetchServerTimeRt.getDefaultInstance()), //服务器时间
    DecreeChange_23(
        23,
        DiamondConvertDecree.getDefaultInstance(),
        DiamondConvertDecreeRt.getDefaultInstance()
    ), //钻石换取政令请求
    PrepareConscription_24(
        24,
        PrepareConscription.getDefaultInstance(),
        PrepareConscriptionRt.getDefaultInstance()
    ), //预备兵请求
    ResourceParticular_25(
        25,
        ResourceParticular.getDefaultInstance(),
        ResourceParticularRt.getDefaultInstance()
    ), //个人势力(资源详情)
    CheckPlayerName_26(26, CheckPlayerName.getDefaultInstance(), CheckPlayerNameRt.getDefaultInstance()), //实时检测改名
    ChangePlayerName_27(27, ChangePlayerName.getDefaultInstance(), ChangePlayerNameRt.getDefaultInstance()), //改名

    UpHeroAdvLv_30(30, UpHeroAdvLv.getDefaultInstance(), UpHeroAdvLvRt.getDefaultInstance()), //请求武将升阶
    UpHeroSkill_31(31, UpHeroSkill.getDefaultInstance(), UpHeroSkillRt.getDefaultInstance()), //请求武将升级技能

    MerchantShipQuery_32(32, MerchantShipQuery.getDefaultInstance(), MerchantShipQueryRt.getDefaultInstance()), // 商船查询
    MerchantShipExchange_33(
        33,
        MerchantShipExchange.getDefaultInstance(),
        MerchantShipExchangeRt.getDefaultInstance()
    ), // 商船兑换
    MerchantShipLock_34(34, MerchantShipLock.getDefaultInstance(), MerchantShipLockRt.getDefaultInstance()), // 商城物品锁定

    CaveConfig_35(35, CaveConfig.getDefaultInstance(), CaveConfigRt.getDefaultInstance()), //藏兵
    CaveCancel_36(36, CaveCancel.getDefaultInstance(), CaveCancelRt.getDefaultInstance()), //取消藏兵

    Bank_37(37, Bank.getDefaultInstance(), BankRt.getDefaultInstance()), // 银行投资
    GetBank_38(38, GetBank.getDefaultInstance(), GetBankRt.getDefaultInstance()), // 收获银行投资
    CancelBank_39(39, CancelBank.getDefaultInstance(), CancelBankRt.getDefaultInstance()), // 取消银行投资

    ShowMap_40(40, ShowMap.getDefaultInstance(), ShowMapRt.getDefaultInstance()), //大地图外观显示

    PhotoQuery_41(41, PhotoQuery.getDefaultInstance(), PhotoQueryRt.getDefaultInstance()), //打开换头像画面的查询
    PhotoChange_42(42, PhotoChange.getDefaultInstance(), PhotoChangeRt.getDefaultInstance()), //设置玩家的头像

    SetDefHero_43(43, SetDefHere.getDefaultInstance(), SetDefHereRt.getDefaultInstance()), // 设置守城英雄
    BuySkin_44(44, BuySkin.getDefaultInstance(), BuySkinRt.getDefaultInstance()), // 购买城堡皮肤
    ChangeSkin_45(45, ChangeSkin.getDefaultInstance(), ChangeSkinRt.getDefaultInstance()), // 切换城堡皮肤
    StrengSkin_46(46, StrengSkin.getDefaultInstance(), StrengSkinRt.getDefaultInstance()), // 强化城堡皮肤

    ExecuteInteriorTask_47(
        47,
        ExecuteInteriorTask.getDefaultInstance(),
        ExecuteInteriorTaskRt.getDefaultInstance()
    ), // 执行内政任务
    QueryInteriorTask_48(
        48,
        QueryInteriorTask.getDefaultInstance(),
        QueryInteriorTaskRt.getDefaultInstance()
    ), // 查询内政任务
    ResetInteriorTask_49(
        49,
        ResetInteriorTask.getDefaultInstance(),
        ResetInteriorTaskRt.getDefaultInstance()
    ), // 重置内政任务
    CreateInnerCity_50(50, CreateInnerCity.getDefaultInstance(), CreateInnerCityRt.getDefaultInstance()), // 新建内城建筑
    UnlockInnerCity_51(51, UnlockInnerCity.getDefaultInstance(), UnlockInnerCityRt.getDefaultInstance()), // 解锁内城建筑
    UpInnerCity_52(52, UpInnerCity.getDefaultInstance(), UpInnerCityRt.getDefaultInstance()), // 升级内城建筑
    CancelUpInnerCity_53(
        53,
        CancelUpInnerCity.getDefaultInstance(),
        CancelUpInnerCityRt.getDefaultInstance()
    ), // 取消内城建筑升级
    DestroyInnerCity_54(54, DestroyInnerCity.getDefaultInstance(), DestroyInnerCityRt.getDefaultInstance()), // 拆除内城建筑
    CancelDestroyInnerCity_55(
        55,
        CancelDestroyInnerCity.getDefaultInstance(),
        CancelDestroyInnerCityRt.getDefaultInstance()
    ), // 取消拆除内城建筑
    MoveInnerCity_56(56, MoveInnerCity.getDefaultInstance(), MoveInnerCityRt.getDefaultInstance()), // 移动内城建筑
    UnlockInnerCityArea_57(
        57,
        UnlockInnerCityArea.getDefaultInstance(),
        UnlockInnerCityAreaRt.getDefaultInstance()
    ), // 解锁内城区域

    QueryBuildCity_99(99, MoveBuilding.getDefaultInstance(), MoveBuildingRt.getDefaultInstance()), //查询建造时间
    Conscription_100(100, Conscription.getDefaultInstance(), ConscriptionRt.getDefaultInstance()), //开始征兵
    CancelConscription_101(
        101,
        Cancelconscription.getDefaultInstance(),
        CancelconscriptionRt.getDefaultInstance()
    ), //取消征兵
    BuildCity_102(102, BuildCity.getDefaultInstance(), BuildCityRt.getDefaultInstance()), //新造建筑
    GetEasyFightInfo_103(103, GetEasyFightInfo.getDefaultInstance(), GetEasyFightInfoRt.getDefaultInstance()), //简单战报请求
    GetDetailFightInfo_104(
        104,
        GetDetailFightInfo.getDefaultInstance(),
        GetDetailFightInfoRt.getDefaultInstance()
    ), //详细战报请求
    GetHeroFightReport_105(
        105,
        GetHeroFightReport.getDefaultInstance(),
        GetHeroFightReportRt.getDefaultInstance()
    ), //获取英雄战战报记录（测试用）

    ChatFightInfoDetail_106(
        106,
        GetShareBattle.getDefaultInstance(),
        GetShareBattleRt.getDefaultInstance()
    ), // 获取聊天时的战报分享的详细信息

    CancelWalk_107(107, OpenAllianceHelp.getDefaultInstance(), OpenAllianceHelpRt.getDefaultInstance()), //取消行军
    ChangeWorldWatch_108(108, ChangeWorldWatch.getDefaultInstance(), ChangeWorldWatchRt.getDefaultInstance()), //切换世界视野
    GetAllServerInfo_109(109, GetAllServerInfo.getDefaultInstance(), GetAllServerInfoRt.getDefaultInstance()), //查找所有世界
    NewShowNearMap_110(
        110,
        NewShowNearMap.getDefaultInstance(),
        NewShowNearMapRt.getDefaultInstance()
    ), //新版的显示大地图(老105)
    QueryCell_111(111, QueryCell.getDefaultInstance(), QueryCellRt.getDefaultInstance()), //查询地块
    GetTaskReward_113(113, GetTaskReward.getDefaultInstance(), GetTaskRewardRt.getDefaultInstance()), //领取任务奖励
    TestFight_114(114, TestFight.getDefaultInstance(), TestFightRt.getDefaultInstance()), //战斗模拟器
    RestoreCityEndTime_118(
        118,
        RestoreCityEndTime.getDefaultInstance(),
        RestoreCityEndTimeRt.getDefaultInstance()
    ), //查询某野外要塞/军营的守军恢复时间
    GuideFin_119(119, GuideFin.getDefaultInstance(), GuideFinRt.getDefaultInstance()), //新手引导完成一步
    GetUnitTaskReward_120(
        120,
        GetUnitTaskReward.getDefaultInstance(),
        GetUnitTaskRewardRt.getDefaultInstance()
    ), //领取章节任务奖励

    DelBattleReport_130(130, DelBattleReport.getDefaultInstance(), DelBattleReportRt.getDefaultInstance()), // 删除战报
    ArchiveBattleReport_131(
        131,
        ArchiveBattleReport.getDefaultInstance(),
        ArchiveBattleReportRt.getDefaultInstance()
    ), // 收藏战报
    ReadedBattleReport_132(
        132,
        ReadedBattleReport.getDefaultInstance(),
        ReadedBattleReportRt.getDefaultInstance()
    ), // 设置战报为已读

    ClearEquipShopCd_172(172, ClearEquipShopCd.getDefaultInstance(), ClearEquipShopCdRt.getDefaultInstance()), // 清除刷新CD
    BuyEquip_174(174, BuyEquip.getDefaultInstance(), BuyEquipRt.getDefaultInstance()), // 购买装备
    SellEquip_175(175, SellEquip.getDefaultInstance(), SellEquipRt.getDefaultInstance()), // 出售装备
    ItemCompound_183(183, ItemCompound.getDefaultInstance(), ItemCompoundRt.getDefaultInstance()), // 合成道具
    ItemSplit_184(184, ItemSplit.getDefaultInstance(), ItemSplitRt.getDefaultInstance()), // 拆分道具

    //帮派商城模块
    OpenAllianceShop_250(250, OpenAllianceShop.getDefaultInstance(), OpenAllianceShopRt.getDefaultInstance()), // 打开帮派商城
    RefreshAllianceShop_251(
        251,
        RefreshAllianceShop.getDefaultInstance(),
        RefreshAllianceShopRt.getDefaultInstance()
    ), // 刷新帮派商城物品
    BuyAllianceShop_252(252, BuyAllianceShop.getDefaultInstance(), BuyAllianceShopRt.getDefaultInstance()), // 购买帮派商城物品

    DelStrangerChat_296(296, DelStrangerChat.getDefaultInstance(), DelStrangerChatRt.getDefaultInstance()),
    OpenNewChatWindow_297(297, OpenNewChatWindow.getDefaultInstance(), OpenNewChatWindowRt.getDefaultInstance()),
    ChangeChatWindow_298(298, ChangeChatWindow.getDefaultInstance(), ChangeChatWindowRt.getDefaultInstance()),
    ChangeRoomInfo_299(299, ChangeRoomInfo.getDefaultInstance(), ChangeRoomInfoRt.getDefaultInstance()), //修改聊天室信息
    Chat_300(300, SendChat.getDefaultInstance(), SendChatRt.getDefaultInstance()), //发送聊天
    SendChat_301(301, SendChatMsg.getDefaultInstance(), SendChatMsgRt.getDefaultInstance()), //发送聊天
    CreateScopeMsg_304(304, GetChatRoom.getDefaultInstance(), GetChatRoomRt.getDefaultInstance()), //生成聊天室
    ChatRoomInfo_305(305, ChatRoomInfo.getDefaultInstance(), ChatRoomInfoRt.getDefaultInstance()), //聊天室信息
    InRoomInfo_306(306, InRoomInfo.getDefaultInstance(), InRoomInfoRt.getDefaultInstance()), //进入聊天室
    GroupChat_307(307, GroupChat.getDefaultInstance(), GroupChatRt.getDefaultInstance()), //群组聊天
    AllianceMembers_308(308, AllianceMembers.getDefaultInstance(), AllianceMembersRt.getDefaultInstance()), //同盟成员查询
    AppointRoomOwner_309(309, ChatRoomAppoint.getDefaultInstance(), ChatRoomAppointRt.getDefaultInstance()), //群主转让
    LeaveChatRoom_311(
        311,
        LeaveChatRoom.getDefaultInstance(),
        LeaveChatRoomRt.getDefaultInstance()
    ), //离开当前聊天室（只是不在这个聊天室频道）
    QuitChatRoom_312(312, QuitChatRoom.getDefaultInstance(), QuitChatRoomRt.getDefaultInstance()), //退出群组聊天（彻底的离开聊天室）

    QueryGroupMembers_314(
        314,
        QueryGroupMembers.getDefaultInstance(),
        QueryGroupMembersRt.getDefaultInstance()
    ), //查询群组聊天室成员

    AddGroupChatMember_315(
        315,
        AddGroupChatMember.getDefaultInstance(),
        AddGroupChatMemberRt.getDefaultInstance()
    ), //向群组聊天室中增加聊天成员
    DelGroupChatMember_316(
        316,
        DelGroupChatMember.getDefaultInstance(),
        DelGroupChatMemberRt.getDefaultInstance()
    ), //删除讨论组人员
    DelGroupChat_317(317, DelGroupChat.getDefaultInstance(), DelGroupChatRt.getDefaultInstance()), //删除讨论组
    GetChatInfo_319(319, GetChatInfo.getDefaultInstance(), GetChatInfoRt.getDefaultInstance()), //获取聊天内容

    InBlack_321(321, InBlack.getDefaultInstance(), InBlackRt.getDefaultInstance()), // 拉入黑名单
    OffBlack_322(322, OffBlack.getDefaultInstance(), OffBlackRt.getDefaultInstance()), // 移除黑名单
    ShareBroadcast_323(323, ShareBroadcast.getDefaultInstance(), ShareBroadcastRt.getDefaultInstance()), // 客户端请求服务器发送广播
    GetRedBag_324(324, GetRedBag.getDefaultInstance(), GetRedBagRt.getDefaultInstance()), // 领取红包
    SendRedBagInfo_325(325, SendRedBagInfo.getDefaultInstance(), SendRedBagInfoRt.getDefaultInstance()), // 查询玩家发出的红包数据
    GetRedBagInfo_326(326, GetRedBagInfo.getDefaultInstance(), GetRedBagInfoRt.getDefaultInstance()), // 查询玩家抢到的红包数据
    SelectTimeOverRedBag_327(
        327,
        SelectTimeOverRedBag.getDefaultInstance(),
        SelectTimeOverRedBagRt.getDefaultInstance()
    ), // 领取已经过期并且没有领完的红包
    GroupChatGiveMaster_329(
        329,
        GroupChatGiveMaster.getDefaultInstance(),
        GroupChatGiveMasterRt.getDefaultInstance()
    ), // 群主转让
//	SignAllianceReply_330(330), 					// 主题标记,这边服务器会自动检测,如果是标记着的邮件 就取消标记 未标记 就标记上

    UpdateLandInfo_340(340, UpdateLandInfo.getDefaultInstance(), UpdateLandInfoRt.getDefaultInstance()), // 内城铺路
    CancelLandInfo_341(341, CancelLand.getDefaultInstance(), CancelLandRt.getDefaultInstance()), // 取消内城铺路
    TownExpend_342(342, TownExpend.getDefaultInstance(), TownExpendRt.getDefaultInstance()), // 内城扩建
    Plant_343(343, Plant.getDefaultInstance(), PlantRt.getDefaultInstance()), // 种菜
    GetPlant_344(344, GetPlant.getDefaultInstance(), GetPlantRt.getDefaultInstance()), // 收菜
    BuySeed_345(345, BuySeed.getDefaultInstance(), BuySeedRt.getDefaultInstance()), // 买菜
    GetExpend_346(346, GetTownExpend.getDefaultInstance(), GetTownExpendRt.getDefaultInstance()), // 收获扩建

    MakeGroundBuild_360(360, MoveCity.getDefaultInstance(), MoveCityRt.getDefaultInstance()), //迁城

    UseCode_400(400, UseCode.getDefaultInstance(), UseCodeRt.getDefaultInstance()), //使用礼包码

    //邮件
    AllMails_450(450, AllMails.getDefaultInstance(), AllMailsRt.getDefaultInstance()), //邮件列表查询
    ReadMail_451(451, ReadMail.getDefaultInstance(), ReadMailRt.getDefaultInstance()), //阅读邮件
    ReadAllMail_452(452, ReadAllMail.getDefaultInstance(), ReadAllMailRt.getDefaultInstance()),//一键已读
    DrawMail_453(453, DrawMail.getDefaultInstance(), DrawMailRt.getDefaultInstance()), //邮件领取附件
    DrawAllMail_454(454, DrawAllMail.getDefaultInstance(), DrawAllMailRt.getDefaultInstance()),//一键领取
    DelMail_455(455, DelMail.getDefaultInstance(), DelMailRt.getDefaultInstance()), //删除邮件
    BatchDelMail_456(456, BatchDelMail.getDefaultInstance(), BatchDelMailRt.getDefaultInstance()),//批量删除邮件
    SignMail_457(457, MailSign.getDefaultInstance(), MailSignRt.getDefaultInstance()), // 收藏邮件
    SendAllianceMail_458(458, SendAllianceMail.getDefaultInstance(), SendAllianceMailRt.getDefaultInstance()), //发送联盟邮件

    QueryAllianceRankFirst_500(
        500,
        QueryAllianceRankFirst.getDefaultInstance(),
        QueryAllianceRankFirstRt.getDefaultInstance()
    ), //查询联盟排行榜首页
    QueryRankFirst_501(501, QueryRankFirst.getDefaultInstance(), QueryRankFirstRt.getDefaultInstance()), //查询排行榜首页
    QueryRank_502(502, QueryRank.getDefaultInstance(), QueryRankRt.getDefaultInstance()), //查询排行

    // 帅土武将玩法
    UseExpCard_667(667, UseExpCard.getDefaultInstance(), UseExpCardRt.getDefaultInstance()), //使用经验卡
    UpHeroLevel_668(668, UpHeroLevel.getDefaultInstance(), UpHeroLevelRt.getDefaultInstance()), //升级武将
    DrawHero_669(669, DrawHero.getDefaultInstance(), DrawHeroRt.getDefaultInstance()), //新抽武将
    HeroCompound_670(670, HeroCompound.getDefaultInstance(), HeroCompoundRt.getDefaultInstance()), //武将合成

    MakeFriend_701(701, MakeFriend.getDefaultInstance(), MakeFriendRt.getDefaultInstance()), // 加好友
    RemoveFriend_702(702, RemoveFriend.getDefaultInstance(), RemoveFriendRt.getDefaultInstance()), // 删除好友
    MakeFriendGroup_703(703, MakeFriendGroup.getDefaultInstance(), MakeFriendGroupRt.getDefaultInstance()), // 创建分组
    MoveInGroup_704(704, MoveInGroup.getDefaultInstance(), MoveInGroupRt.getDefaultInstance()), // 移动至分组
    RemoveGroup_706(706, RemoveGroup.getDefaultInstance(), RemoveGroupRt.getDefaultInstance()), // 删除分组
    ChangeGroup_707(707, ChangeGroup.getDefaultInstance(), ChangeGroupRt.getDefaultInstance()), // 修改分组
    QueryPlayerByName_709(
        709,
        QueryPlayerByName.getDefaultInstance(),
        QueryPlayerByNameRt.getDefaultInstance()
    ), // 查询玩家
    HandleFriendApply_710(
        710,
        HandleFriendApply.getDefaultInstance(),
        HandleFriendApplyRt.getDefaultInstance()
    ), // 忽略别人的添加好友请求

    // 竞技场
    JjcQueryInfo_711(711, JjcQueryInfo.getDefaultInstance(), JjcQueryInfoRt.getDefaultInstance()), // 查询竞技场
    JjcRefreshChallenge_712(
        712,
        JjcRefreshChallenge.getDefaultInstance(),
        JjcRefreshChallengeRt.getDefaultInstance()
    ), // 刷新竞技场挑战对手
    JjcGetRewards_714(714, JjcGetRewards.getDefaultInstance(), JjcGetRewardsRt.getDefaultInstance()), // 领取奖励
    JjcDelPlanGrids_718(718, JjcDelPlanGrids.getDefaultInstance(), JjcDelPlanGridsRt.getDefaultInstance()), // 删除预设布阵
    JjcFight_720(720, JjcFight.getDefaultInstance(), JjcFightRt.getDefaultInstance()), // 竞技场战斗
    GetJjcFightInfo_721(721, GetJjcFightInfo.getDefaultInstance(), GetJjcFightInfoRt.getDefaultInstance()), // 查看竞技场战报
    BuyJjcCount_722(722, BuyJjcCount.getDefaultInstance(), BuyJjcCountRt.getDefaultInstance()), // 购买竞技场挑战次数
    ClearJjcCd_723(723, ClearJjcCd.getDefaultInstance(), ClearJjcCdRt.getDefaultInstance()), // 秒竞技场挑战CD
    SelectJjcAtkForce_724(
        724,
        SelectJjcDefForce.getDefaultInstance(),
        SelectJjcDefForceRt.getDefaultInstance()
    ), // 查询某玩家竞技场防守阵容
    GetRankGold_725(725, GetRankGold.getDefaultInstance(), GetRankGoldRt.getDefaultInstance()), // 领取排名累计奖励
    QueryJjcShopInfo_726(726, GetJjcShopInfo.getDefaultInstance(), GetJjcShopInfoRt.getDefaultInstance()), // 查询jjc商店
    BuyJjcShopItem_727(727, BuyJjcItem.getDefaultInstance(), BuyJjcItemRt.getDefaultInstance()), // 购买竞技场商店的东西
    RefreshJjcShopItem_728(
        728,
        RefreshJjcShopItem.getDefaultInstance(),
        RefreshJjcShopItemRt.getDefaultInstance()
    ), // 刷新竞技场商店的东西
    ExchangeJjcAchievementReward_729(
        729,
        ExchangeJjcAchievementReward.getDefaultInstance(),
        ExchangeJjcAchievementRewardRt.getDefaultInstance()
    ), // 兑换竞技场成就的东西

    // 获取每天vip奖励
    GainVipDayReward_740(
        740,
        GainVipReward.getDefaultInstance(),
        GainVipRewardRt.getDefaultInstance()
    ),

    AllianceCreate_802(802, AllianceCreate.getDefaultInstance(), AllianceCreateRt.getDefaultInstance()), // 联盟创建
    AllianceJoinById_804(
        804,
        AllianceJoinById.getDefaultInstance(),
        AllianceJoinByIdRt.getDefaultInstance()
    ), // 根据联盟ID申请加入联盟
    AllianceJoinCancel_805(
        805,
        AllianceJoinCancel.getDefaultInstance(),
        AllianceJoinCancelRt.getDefaultInstance()
    ), // 玩家取消加入联盟申请
    AllianceDealJoinReq_806(
        806,
        AllianceDealJoinReq.getDefaultInstance(),
        AllianceDealJoinReqRt.getDefaultInstance()
    ), // 处理玩家加入联盟申请
    SetPowerLimit_807(
        807,
        AllianceSetPowerLimit.getDefaultInstance(),
        AllianceSetPowerLimitRt.getDefaultInstance()
    ), // 设置允许申请联盟的势力最低值
    AllianceQueryList_808(
        808,
        AllianceQueryList.getDefaultInstance(),
        AllianceQueryListRt.getDefaultInstance()
    ), // 查询可加入联盟列表
    AllianceQueryInfo_809(
        809,
        AllianceQueryInfo.getDefaultInstance(),
        AllianceQueryInfoRt.getDefaultInstance()
    ), // 查询联盟概要信息
    AllianceQueryReqList_810(
        810,
        AllianceQueryReqList.getDefaultInstance(),
        AllianceQueryReqListRt.getDefaultInstance()
    ), // 查询申请加入联盟的玩家信息
    AllianceQuit_811(811, AllianceQuit.getDefaultInstance(), AllianceQuitRt.getDefaultInstance()), // 玩家主动退出联盟
    AllianceRemovePlayer_812(
        812,
        AllianceRemovePlayer.getDefaultInstance(),
        AllianceRemovePlayerRt.getDefaultInstance()
    ), // 从联盟中剔除玩家
    AllianceSetDescpt_813(
        813,
        AllianceSetDescpt.getDefaultInstance(),
        AllianceSetDescptRt.getDefaultInstance()
    ), // 修改联盟公告
    AllianceSetPos_814(814, AllianceSetPos.getDefaultInstance(), AllianceSetPosRt.getDefaultInstance()), // 任命玩家在联盟中职位

    AllianceDonate_815(815, AllianceDonate.getDefaultInstance(), AllianceDonateRt.getDefaultInstance()), // 联盟捐献

    AllianceQueryPlayer_816(
        816,
        AllianceQueryPlayer.getDefaultInstance(),
        AllianceQueryPlayerRt.getDefaultInstance()
    ), // 查询联盟成员信息
    AllianceDismiss_817(817, AllianceDismiss.getDefaultInstance(), AllianceDismissRt.getDefaultInstance()), // 联盟解散
    AllianceSetRelation_818(
        818,
        AllianceSetRelation.getDefaultInstance(),
        AllianceSetRelationRt.getDefaultInstance()
    ), // 设置联盟外交关系
    AllianceQueryRelation_819(
        819,
        AllianceQueryRelation.getDefaultInstance(),
        AllianceQueryRelationRt.getDefaultInstance()
    ), // 查询联盟外交列表
    AllianceQueryLog_820(820, AllianceQueryLog.getDefaultInstance(), AllianceQueryLogRt.getDefaultInstance()), // 查询联盟日志
    AllianceSetMark_821(821, AllianceSetMark.getDefaultInstance(), AllianceSetMarkRt.getDefaultInstance()), // 设置联盟标记
    AllianceRemoveMark_823(
        823,
        AllianceRemoveMark.getDefaultInstance(),
        AllianceRemoveMarkRt.getDefaultInstance()
    ), // 删除联盟标记
    AllianceRebelRes_824(
        824,
        AllianceRebelRes.getDefaultInstance(),
        AllianceRebelResRt.getDefaultInstance()
    ), // 反叛（上缴资源1）
    CreateAllianceCheckAllianceName_825(
        825,
        CheckAllianceName.getDefaultInstance(),
        CheckAllianceNameRt.getDefaultInstance()
    ), // 检测联盟名是否可用
    AllianceQueryRebel_828(
        828,
        AllianceQueryRebel.getDefaultInstance(),
        AllianceQueryRebelRt.getDefaultInstance()
    ), // 查询已交反叛资源
    AllianceRebelExec_829(
        829,
        AllianceRebelExec.getDefaultInstance(),
        AllianceRebelExecRt.getDefaultInstance()
    ), // 玩家执行反叛操作
    AllianceQueryWithState_830(
        830,
        AllianceQueryWithState.getDefaultInstance(),
        AllianceQueryWithStateRt.getDefaultInstance()
    ), // 查询联盟及所在州的简要信息（联盟目标需要）
    AllianceQueryMemCell_831(
        831,
        AllianceQueryMemCell.getDefaultInstance(),
        AllianceQueryMemCellRt.getDefaultInstance()
    ), // 查询联盟成员土地完成进度信息
    AlliancePublishMission_832(
        832,
        AlliancePublishMission.getDefaultInstance(),
        AlliancePublishMissionRt.getDefaultInstance()
    ), // 联盟目标发布
    AllianceCloseMission_833(
        833,
        AllianceCloseMission.getDefaultInstance(),
        AllianceCloseMissionRt.getDefaultInstance()
    ), // 联盟目标删除
    AllianceQueryMissionPro_834(
        834,
        AllianceQueryMissionPro.getDefaultInstance(),
        AllianceQueryMissionProRt.getDefaultInstance()
    ), // 联盟目标统计
    AllianceRecallPos_835(
        835,
        AllianceRecallPos.getDefaultInstance(),
        AllianceRecallPosRt.getDefaultInstance()
    ), // 罢免玩家帮派职位
    AllianceSetFlag_837(837, AllianceSetFlag.getDefaultInstance(), AllianceSetFlagRt.getDefaultInstance()), // 设置联盟旗帜
    QueryApplyAllianceList_838(
        838,
        QueryApplyAllianceList.getDefaultInstance(),
        QueryApplyAllianceListRt.getDefaultInstance()
    ), // 查询已申请联盟列表
    AllianceExchange_841(
        841,
        AllianceExchange.getDefaultInstance(),
        AllianceExchangeRt.getDefaultInstance()
    ), // 联盟换卡/捐献：显示列表
    AllianceExchangeDemand_842(
        842,
        AllianceExchangeDemand.getDefaultInstance(),
        AllianceExchangeDemandRt.getDefaultInstance()
    ), // 联盟换卡/捐献：发布需求
    AllianceExchangeDonate_843(
        843,
        AllianceExchangeDonate.getDefaultInstance(),
        AllianceExchangeDonateRt.getDefaultInstance()
    ), // 联盟换卡/捐献：捐献卡牌
    AllianceQueryDemand_844(
        844,
        AllianceQueryDemand.getDefaultInstance(),
        AllianceQueryDemandRt.getDefaultInstance()
    ), // 查询请求捐卡信息
    AllianceCount_850(850, AllianceCount.getDefaultInstance(), AllianceCountRt.getDefaultInstance()), // 联盟成员统计
    AllianceQueryTopic_890(
        890,
        AllianceQueryTopic.getDefaultInstance(),
        AllianceQueryTopicRt.getDefaultInstance()
    ), // 联盟邮件主题列表
    AlliancePublishTopic_891(
        891,
        AlliancePublishTopic.getDefaultInstance(),
        AlliancePublishTopicRt.getDefaultInstance()
    ), // 发布联盟邮件主题
    AllianceTopicReply_892(
        892,
        AllianceTopicReply.getDefaultInstance(),
        AllianceTopicReplyRt.getDefaultInstance()
    ), // 对联盟邮件主题进行回复
    AllianceTopicGetReply_893(
        893,
        AllianceTopicGetReply.getDefaultInstance(),
        AllianceTopicGetReplyRt.getDefaultInstance()
    ), // 玩家滚动回复列表时: 请求历史回复内容
    AllianceTopicDelete_894(
        894,
        AllianceTopicDelete.getDefaultInstance(),
        AllianceTopicDeleteRt.getDefaultInstance()
    ), // 联盟邮件主题删除
    AllianceOpenWaijiao_895(
        895,
        AllianceOpenWaijiao.getDefaultInstance(),
        AllianceOpenWaijiaoRt.getDefaultInstance()
    ), // 打开联盟外交界面
    WriteAllianceWaijiao_896(
        896,
        WriteAllianceWaijiao.getDefaultInstance(),
        WriteAllianceWaijiaoRt.getDefaultInstance()
    ), // 写联盟外交
    AllianceNickName_897(897, AllianceNickName.getDefaultInstance(), AllianceNickNameRt.getDefaultInstance()), // 设置联盟昵称
    AllianceSetName_898(
        898,
        SetAllianceName.getDefaultInstance(),
        SetAllianceNameRt.getDefaultInstance()
    ), // 设置联盟名称或者简称
    AllianceImpeach_899(899, AllianceImpeach.getDefaultInstance(), AllianceImpeachRt.getDefaultInstance()), // 弹劾盟主
    AllianceInvite_900(900, AllianceInvite.getDefaultInstance(), AllianceInviteRt.getDefaultInstance()), // 邀请玩家加入联盟
    AllianceGiftOpen_901(
        901,
        AllianceGiftOpen.getDefaultInstance(),
        AllianceGiftOpenRt.getDefaultInstance()
    ), // 联盟礼物主界面

    AllianceGiftGet_902(
        902,
        AllianceGiftGet.getDefaultInstance(),
        AllianceGiftGetRt.getDefaultInstance()
    ),
    AllianceGiftRemove_903(
        903,
        AllianceGiftRemove.getDefaultInstance(),
        AllianceGiftRemoveRt.getDefaultInstance()
    ), // 删除联盟礼物
    GetAllianceMissionGift_904(
        904,
        GetAllianceMissionGift.getDefaultInstance(),
        GetAllianceMissionGiftRt.getDefaultInstance()
    ), // 领取联盟活跃度礼物
    GiftToAllianceMember_905(
        905,
        GiftToAllianceMemeber.getDefaultInstance(),
        GiftToAllianceMemeberRt.getDefaultInstance()
    ), // 联盟赠礼
    OpenAllianceCompetitionMain_906(
        906,
        OpenAllianceCompetitionMain.getDefaultInstance(),
        OpenAllianceCompetitionMainRt.getDefaultInstance()
    ), // 打开联盟总动员主界面
    OpenAllianceCompetitionReward_907(
        907,
        OpenAllianceCompetitionReward.getDefaultInstance(),
        OpenAllianceCompetitionRewardRt.getDefaultInstance()
    ), // 打开联盟总动员领取奖励界面
    GetAllianceCompetitionQuest_908(
        908,
        GetAllianceCompetitionQuest.getDefaultInstance(),
        GetAllianceCompetitionQuestRt.getDefaultInstance()
    ), // 领取联盟总动员任务
    RemoveAllianceCompetitionQuest_909(
        909,
        RemoveAllianceCompetitionQuest.getDefaultInstance(),
        RemoveAllianceCompetitionQuestRt.getDefaultInstance()
    ), // 删除联盟总动员任务
    BuyAllianceCompetitionQuest_910(
        910,
        BuyAllianceCompetitionQuest.getDefaultInstance(),
        BuyAllianceCompetitionQuestRt.getDefaultInstance()
    ), // 购买联盟总动员任务
    RewardAllianceCompetitionQuest_911(
        911,
        RewardAllianceCompetitionQuest.getDefaultInstance(),
        RewardAllianceCompetitionQuestRt.getDefaultInstance()
    ), // 领取任务奖励
    CancelAllianceCompetitionQuest_912(
        912,
        CancelAllianceCompetitionQuest.getDefaultInstance(),
        CancelAllianceCompetitionQuestRt.getDefaultInstance()
    ), // 有权限的人取消掉任务
    GetAllianceCompetitionReward_913(
        913,
        GetAllianceCompetitionReward.getDefaultInstance(),
        GetAllianceCompetitionRewardRt.getDefaultInstance()
    ), // 领取联盟总动员阶段奖励
    OpenAfterAllianceCompetition_914(
        914,
        OpenAfterAllianceCompetition.getDefaultInstance(),
        OpenAfterAllianceCompetitionRt.getDefaultInstance()
    ), // 活动结束打开界面
    QueryInAllianceRank_915(
        915,
        QueryInAllianceRank.getDefaultInstance(),
        QueryInAllianceRankRt.getDefaultInstance()
    ), // 查询联盟内部数据排行榜
    QueryAllianceRank_916(
        916,
        QueryAllianceRank.getDefaultInstance(),
        QueryAllianceRankRt.getDefaultInstance()
    ), // 查询联盟排行榜
    AllianceTopicSign_917(
        917,
        AllianceTopicSign.getDefaultInstance(),
        AllianceTopicSignRt.getDefaultInstance()
    ), // 收藏联盟主题
    QueryOccupyWonder_918(
        918,
        QueryOccupyWonder.getDefaultInstance(),
        QueryOccupyWonderRt.getDefaultInstance()
    ), // 查询占领的奇观
    RemoveAllianceWaijiao_919(
        919,
        RemoveAllianceWaijiao.getDefaultInstance(),
        RemoveAllianceWaijiaoRt.getDefaultInstance()
    ), // 删除联盟留言

    // (阴阳师版,已弃)武将养成 1000-1010
    HeroAwaken_1002(1002, HeroAwake.getDefaultInstance(), HeroAwakeRt.getDefaultInstance()), // 武将觉醒
    HeroLock_1003(1003, HeroLock.getDefaultInstance(), HeroLockRt.getDefaultInstance()), // 武将锁定
    HeroUnlock_1004(1004, HeroUnlock.getDefaultInstance(), HeroUnlockRt.getDefaultInstance()), // 武将解锁

    //
    UpdateMainHero_1006(1006, UpdateMainHero.getDefaultInstance(), UpdateMainHeroRt.getDefaultInstance()), // 更换领主

    // (英雄无敌版)武将养成 1011-1020
    HeroLvUp_1011(1011, InvincibleHeroLvUp.getDefaultInstance(), InvincibleHeroLvUpRt.getDefaultInstance()), // 武将升级
    HeroStarLvUp_1012(
        1012,
        InvincibleHeroStarLvUp.getDefaultInstance(),
        InvincibleHeroStarLvUpRt.getDefaultInstance()
    ), // 武将升星
    HeroSuperUp_1013(
        1013,
        InvincibleHeroSuperUp.getDefaultInstance(),
        InvincibleHeroSuperUpRt.getDefaultInstance()
    ), // 武将进阶
    HeroEquipUp_1014(
        1014,
        InvincibleHeroEquipUp.getDefaultInstance(),
        InvincibleHeroEquipUpRt.getDefaultInstance()
    ), // 武将装备进阶
    HeroSkillLvUp_1015(
        1015,
        InvincibleHeroSkillLvUp.getDefaultInstance(),
        InvincibleHeroSkillLvUpRt.getDefaultInstance()
    ), // 武将技能升级
    HeroCancelStarLvUp_1016(
        1016,
        InvincibleHeroCancelStarLvUp.getDefaultInstance(),
        InvincibleHeroCancelStarLvUpRt.getDefaultInstance()
    ), // 取消武将升星
    HeroCancelSuperUp_1017(
        1017,
        InvincibleHeroCancelSuperUp.getDefaultInstance(),
        InvincibleHeroCancelSuperUpRt.getDefaultInstance()
    ), // 取消武将升阶
    HeroGetStarLvUp_1019(
        1019,
        InvincibleHeroGetStarLvUp.getDefaultInstance(),
        InvincibleHeroGetStarLvUpRt.getDefaultInstance()
    ), // 武将兵团升级

    // 装备养成 1021-1040
    OnEquip_1021(1021, OnEquip.getDefaultInstance(), OnEquipRt.getDefaultInstance()), // 穿装备
    OffEquip_1022(1022, OffEquip.getDefaultInstance(), OffEquipRt.getDefaultInstance()), // 脱装备
    EquipLvUp_1023(1023, LvUpEquip.getDefaultInstance(), LvUpEquipRt.getDefaultInstance()), // 装备强化

    // 推图1041-1050
    MissonFight_1041(1041, MissionFight.getDefaultInstance(), MissionFightRt.getDefaultInstance()), // 推图战斗

    // 科技 1051-1060
    ResearchLvUp_1051(1051, ResearchLvUp.getDefaultInstance(), ResearchLvUpRt.getDefaultInstance()), // 升级科技
    CancelResearchLvUp_1052(
        1052,
        CancelResearchLvUp.getDefaultInstance(),
        CancelResearchLvUpRt.getDefaultInstance()
    ), // 取消升级科技

    // 快速使用 1061-1070
    ClearTime_1061(1061, ClearTime.getDefaultInstance(), ClearTimeRt.getDefaultInstance()), // 秒加速
    BuyResShop_1062(1062, BuyResShop.getDefaultInstance(), BuyResShopRt.getDefaultInstance()), // 购买resShop表中物品
    UseProp_1063(1063, UseProp.getDefaultInstance(), UsePropRt.getDefaultInstance()), // 使用道具
    BuyAndUseProp_1064(1064, BuyAndUseProp.getDefaultInstance(), BuyAndUsePropRt.getDefaultInstance()), // 购买并使用道具

    // 联盟帮助 1071-1080
    OpenAllianceHelp_1071(
        1071,
        OpenAllianceHelp.getDefaultInstance(),
        OpenAllianceHelpRt.getDefaultInstance()
    ), // 打开联盟帮助
    SendAllianceHelp_1072(
        1072,
        SendAllianceHelp.getDefaultInstance(),
        SendAllianceHelpRt.getDefaultInstance()
    ), // 登记帮助信息
    GoAllianceHelp_1073(1073, GoAllianceHelp.getDefaultInstance(), GoAllianceHelpRt.getDefaultInstance()), // 帮助

    // 兵营,配兵,伤兵营 1081-1090
    MakeSolider_1081(1081, MakeSolider.getDefaultInstance(), MakeSoliderRt.getDefaultInstance()), // 造兵
    CancelMakeSolider_1082(
        1082,
        CancelMakeSolider.getDefaultInstance(),
        CancelMakeSoliderRt.getDefaultInstance()
    ), // 取消造兵

    CureSolider_1084(1084, CureSolider.getDefaultInstance(), CureSoliderRt.getDefaultInstance()), // 治疗兵
    CancelCureSolider_1085(
        1085,
        CancelCureSolider.getDefaultInstance(),
        CancelCureSoliderRt.getDefaultInstance()
    ), // 取消治疗兵

    SoliderUp_1086(1086, SoliderUp.getDefaultInstance(), SoliderUpRt.getDefaultInstance()), // 兵种晋升
    CancelSoliderUp_1087(1087, CancelSoliderUp.getDefaultInstance(), CancelSoliderUpRt.getDefaultInstance()), // 取消兵种晋升

    // 内政英雄 1091 - 1100
    OnIntHero_1091(1091, OnIntHero.getDefaultInstance(), OnIntHeroRt.getDefaultInstance()), // 上阵内政武将
    OffIntHero_1092(1092, OffIntHero.getDefaultInstance(), OffIntHeroRt.getDefaultInstance()), // 下阵内政武将

    // 帮派科技  1111-1150
    SetAllianceResearchFirst_1111(
        1111,
        SetAllianceResearchFirst.getDefaultInstance(),
        SetAllianceResearchFirstRt.getDefaultInstance()
    ), // 设置研发优先级
    GoAllianceResearch_1112(
        1112,
        GoAllianceResearch.getDefaultInstance(),
        GoAllianceResearchRt.getDefaultInstance()
    ), // 进行研发
    OpenAllianceResearch_1113(
        1113,
        OpenAllianceResearch.getDefaultInstance(),
        OpenAllianceResearchRt.getDefaultInstance()
    ), // 打开界面

    StudyTimeBox_1160(1160, StudyTimeBox.getDefaultInstance(), StudyTimeBoxRt.getDefaultInstance()), // 研究时光之盒
    CancelStudyTimeBox_1161(
        1161,
        CancelStudyTimeBox.getDefaultInstance(),
        CancelStudyTimeBoxRt.getDefaultInstance()
    ), // 取消研究时光之盒
    GetStudyTimeBoxReward_1162(
        1162,
        GetStudyTimeBoxReward.getDefaultInstance(),
        GetStudyTimeBoxRewardRt.getDefaultInstance()
    ), // 领取研究时光之盒奖励
    RemoveStudyTimeBoxReward_1163(
        1163,
        RemoveStudyTimeBoxReward.getDefaultInstance(),
        RemoveStudyTimeBoxRewardRt.getDefaultInstance()
    ), // 放弃时光之盒奖励

    // Vip功能 1201-1210

    // 君主功能 1211-1250
    UnlockTalent_1211(1211, UpgradeTalent.getDefaultInstance(), UpgradeTalentRt.getDefaultInstance()), // 解锁天赋
    ResetTalentPoint_1212(
        1212,
        ResetTalentPoint.getDefaultInstance(),
        ResetTalentPointRt.getDefaultInstance()
    ), // 重置天赋点数
    QueryKingInfo_1216(1216, QueryKingInfo.getDefaultInstance(), QueryKingInfoRt.getDefaultInstance()), // 查询君主信息
    SetTalentPlan_1217(1217, SetTalentPlan.getDefaultInstance(), SetTalentPlanRt.getDefaultInstance()), // 设置一个君主天赋预设
    DelTalentPlan_1218(1218, DelTalentPlan.getDefaultInstance(), DelTalentPlanRt.getDefaultInstance()), // 删除一个君主天赋预设
    QueryTalentPlans_1219(
        1219,
        QueryTalentPlans.getDefaultInstance(),
        QueryTalentPlansRt.getDefaultInstance()
    ), // 查询一个君主所有的天赋预设
    UseTalentPlan_1220(1220, UseTalentPlan.getDefaultInstance(), UseTalentPlanRt.getDefaultInstance()), // 套用一个君主天赋预设
    MakeKingEquip_1221(1221, MakeKingEquip.getDefaultInstance(), MakeKingEquipRt.getDefaultInstance()), // 锻造/升级君主装备
    GetKingEquip_1222(1222, GetKingEquip.getDefaultInstance(), GetKingEquipRt.getDefaultInstance()), // 领取君主装备
    OnKingEquip_1224(1224, OnKingEquip.getDefaultInstance(), OnKingEquipRt.getDefaultInstance()), // 穿君主装备
    OffKingEquip_1225(1225, OffKingEquip.getDefaultInstance(), OffKingEquipRt.getDefaultInstance()), // 脱君主装备
    SplitKingEquip_1226(1226, SplitKingEquip.getDefaultInstance(), SplitKingEquipRt.getDefaultInstance()), // 拆解君主装备
    SetKingEquipPlan_1227(
        1227,
        SetKingEquipPlan.getDefaultInstance(),
        SetKingEquipPlanRt.getDefaultInstance()
    ), // 设置一个君主装备预设
    DelKingEquipPlan_1228(
        1228,
        DelKingEquipPlan.getDefaultInstance(),
        DelKingEquipPlanRt.getDefaultInstance()
    ), // 删除一个君主装备预设
    QueryKingEquipPlans_1229(
        1229,
        QueryKingEquipPlans.getDefaultInstance(),
        QueryKingEquipPlansRt.getDefaultInstance()
    ), // 查询一个君主所有的装备预设
    UseKingEquipPlan_1230(
        1230,
        UseKingEquipPlan.getDefaultInstance(),
        UseKingEquipPlanRt.getDefaultInstance()
    ), // 套用一个君主装备预设
    CancelMakeKingEquip_1231(
        1231,
        CancelMakeKingEquip.getDefaultInstance(),
        CancelMakeKingEquipRt.getDefaultInstance()
    ), // 取消一个君主装备锻造的队列

    KingEquipAddCard_1232(
        1232,
        KingEquipAddCard.getDefaultInstance(),
        KingEquipAddCardRt.getDefaultInstance()
    ), // 给一个君主装备打卡片

    KingEquipOffCard_1233(
        1233,
        KingEquipOffCard.getDefaultInstance(),
        KingEquipOffCardRt.getDefaultInstance()
    ), // 取下君主装备上的卡片

    KingEquipCompoundCard_1234(
        1234,
        KingEquipCompoundCard.getDefaultInstance(),
        KingEquipCompoundCard.getDefaultInstance()
    ), // 卡片合成

    //战斗相关 1251-1299
    QueryWalkLineDetailInfo_1252(
        1252,
        QueryWalkLineDetailInfo.getDefaultInstance(),
        QueryWalkLineDetailInfoRt.getDefaultInstance()
    ), // 查询行军线详细信息
    GoBackHome_1253(1253, GoBackHome.getDefaultInstance(), GoBackHomeRt.getDefaultInstance()), // 回城
    StartMass_1254(1254, StartMass.getDefaultInstance(), StartMassRt.getDefaultInstance()), // 发起集结
    CancelMass_1255(1255, CancelMass.getDefaultInstance(), CancelMassRt.getDefaultInstance()), // 取消集结
    QueryReinforce_1256(1256, QueryReinfore.getDefaultInstance(), QueryReinforeRt.getDefaultInstance()), // 查询增援
    QueryDetailReinforce_1257(
        1257,
        QueryDetailReinforce.getDefaultInstance(),
        QueryDetailReinforceRt.getDefaultInstance()
    ), // 查询详细增援
    QueryAllianceMemberPos_1258(
        1258,
        QueryAllianceMemberPos.getDefaultInstance(),
        QueryAllianceMemberPosRt.getDefaultInstance()
    ), // 查询联盟成员坐标
    SendMassMemberHome_1259(
        1259,
        SendMassMemberHome.getDefaultInstance(),
        SendMassMemberHomeRt.getDefaultInstance()
    ), // 遣返集结玩家
    SendReinforcePlayerHome_1260(
        1260,
        SendReinforcePlayerHome.getDefaultInstance(),
        SendReinforcePlayerHomeRt.getDefaultInstance()
    ), // 遣返增援玩家
    DismissSolider_1261(1261, DismissSolider.getDefaultInstance(), DismissSoliderRt.getDefaultInstance()), // 遣散士兵
    PublishTransportRequest_1262(
        1262,
        PublishTransportRequest.getDefaultInstance(),
        PublishTransportRequestRt.getDefaultInstance()
    ), // 发布运输请求
    DeleteTransportRequest_1263(
        1263,
        DeleteTransportRequest.getDefaultInstance(),
        DeleteTransportRequestRt.getDefaultInstance()
    ), // 删除运输请求
    SendWonderWarPlayerHome_1264(
        1264,
        SendWonderWarPlayerHome.getDefaultInstance(),
        SendWonderWarPlayerHomeRt.getDefaultInstance()
    ), // 遣返奇观参战玩家
    SetForcePlan_1265(1265, SetForcePlan.getDefaultInstance(), SetForcePlanRt.getDefaultInstance()), // 设置玩家的出征预设部队
    SetArmyPlan_1266(1266, SetArmyPlan.getDefaultInstance(), SetArmyPlanRt.getDefaultInstance()), // 设置英雄战部队预设
    GetArmyPlan_1267(1267, GetArmyPlan.getDefaultInstance(), GetArmyPlanRt.getDefaultInstance()), // 获取英雄战部队预设

    // 迁服 1300 - 1310
    MoveServer_1300(1300, MoveServer.getDefaultInstance(), MoveServerRt.getDefaultInstance()), //  迁服
    InitPlayerSessionAfterMoveServer_1301(
        1301,
        InitPlayerSessionAfterMoveServer.getDefaultInstance(),
        InitPlayerSessionAfterMoveServerRt.getDefaultInstance()
    ), //  迁服成功初始化数据
    AllServerInfo_1302(1302, AllServerInfo.getDefaultInstance(), AllServerInfoRt.getDefaultInstance()), //  迁服列表
    MoveServerCost_1303(
        1303,
        MoveServerCost.getDefaultInstance(),
        MoveServerCostRt.getDefaultInstance()
    ), //  请求我的战斗力在即将迁往的目标服务器上的排名

    // 商店 1311 - 1315
    ShopTotalBuy_1311(1311, BuyShopTotal.getDefaultInstance(), BuyShopTotalRt.getDefaultInstance()), // 商店购买物品
    ShopLimitTotalBuy_1312(
        1312,
        BuyLimitShopTotal.getDefaultInstance(),
        BuyLimitShopTotalRt.getDefaultInstance()
    ), // 商店购买物品

    // 世界BOSS + 联盟BOSS 1316 - 1330
    SummonAllianceBoss_1316(
        1316,
        SummonAllianceBoss.getDefaultInstance(),
        SummonAllianceBossRt.getDefaultInstance()
    ), // 召唤联盟BOSS
    QueryAllianceBossLiveness_1317(
        1317,
        QueryAllianceBossLiveness.getDefaultInstance(),
        QueryAllianceBossLivenessRt.getDefaultInstance()
    ), // 查看联盟boss活跃度进度
    QueryAllianceBoss_1318(
        1318,
        QueryAllianceBoss.getDefaultInstance(),
        QueryAllianceBossRt.getDefaultInstance()
    ), // 查看所有被召唤的联盟boss

    // 活动消息号 1331 - 1350
    GetPlayerRewardBag_1331(
        1331,
        GetPlayerRewardBag.getDefaultInstance(),
        GetPlayerRewardBagRt.getDefaultInstance()
    ), // 领取玩家领奖背包物品
    SelectActivityHistory_1332(
        1332,
        SelectActivityHistory.getDefaultInstance(),
        SelectActivityHistoryRt.getDefaultInstance()
    ), // 查询活动历史
    OpenActivity_1333(1333, OpenActivity.getDefaultInstance(), OpenActivityRt.getDefaultInstance()), // 请求单个活动界面
    SeleteAllianceActivityInfos_1334(
        1334,
        SeleteAllianceActivityInfos.getDefaultInstance(),
        SeleteAllianceActivityInfosRt.getDefaultInstance()
    ), // 打开活动总界面,只请求联盟活动的数据

    SelectActivityRank_1335(
        1335,
        SelectActivityRank.getDefaultInstance(),
        SelectActivityRankRt.getDefaultInstance()
    ), // 查询我参与过的活动的历史排行榜

    SelectNowRank_1336(
        1336,
        SelectNowRank.getDefaultInstance(),
        SelectNowRankRt.getDefaultInstance()
    ), // 查询正在进行中的活动的排行榜

    // 监禁消息号  1351 - 1400
    PrisonFree_1351(1351, PrisonFree.getDefaultInstance(), PrisonFreeRt.getDefaultInstance()), // 释放玩家
    KillPrison_1352(1352, KillPrison.getDefaultInstance(), KillPrisonRt.getDefaultInstance()), // 杀玩家
    EatPoison_1353(1353, EatPoison.getDefaultInstance(), EatPoisonRt.getDefaultInstance()), // 服毒自杀
    SetRansom_1354(1354, SetRansom.getDefaultInstance(), SetRansomRt.getDefaultInstance()), // 设置赎金
    SetRewardGold_1355(1355, SetRewardGold.getDefaultInstance(), SetRewardGoldRt.getDefaultInstance()), // 设置赏金
    GoRansom_1356(1356, GoRansom.getDefaultInstance(), GoRansomRt.getDefaultInstance()), // 交钱赎人
    ResurgenceAtOnce_1357(1357, ResurgenceAtOnce.getDefaultInstance(), ResurgenceAtOnceRt.getDefaultInstance()), // 买活
    GetResurgence_1358(1358, GetResurgence.getDefaultInstance(), GetResurgenceRt.getDefaultInstance()), // 领取复活
    CheckEatPoisonNum_1359(
        1359,
        CheckEatPoisonNum.getDefaultInstance(),
        CheckEatPoisonNumRt.getDefaultInstance()
    ),
    GainMaxPrisonLvBuff_1360(
        1360,
        GainMaxPrisonLvBuff.getDefaultInstance(),
        GainMaxPrisonLvBuffRt.getDefaultInstance()
    ),
    // 查询玩家需要使用的毒蘑菇数量

    // 在线礼包消息号 1401 - 1410
    GetOnlineReward_1401(1401, GetOnlineReward.getDefaultInstance(), GetOnlineRewardRt.getDefaultInstance()), // 领取在线礼包
    OpenOnlineReward_1402(
        1402, OpenOnlineReward.getDefaultInstance(), OpenOnlineRewardRt.getDefaultInstance()
    ), // 打开在线礼包

    // 联盟宝藏消息 1411 - 1412
    OpenAllianceTreasure_1411(
        1411,
        OpenAllianceTreasure.getDefaultInstance(),
        OpenAllianceTreasureRt.getDefaultInstance()
    ), // 打开联盟宝藏信息
    GetAllianceTreasureReward_1412(
        1412,
        GetAllianceTreasureReward.getDefaultInstance(),
        GetAllianceTreasureRewardRt.getDefaultInstance()
    ), // 领取联盟宝藏
    RefreshAllianceTreasure_1413(
        1413,
        RefreshAllianceTreasure.getDefaultInstance(),
        RefreshAllianceTreasureRt.getDefaultInstance()
    ), // 刷新联盟宝藏
    GoAllianceTreasure_1414(
        1414,
        GoAllianceTreasure.getDefaultInstance(),
        GoAllianceTreasureRt.getDefaultInstance()
    ), // 挖掘联盟宝藏

    //成就消息 1451 - 1454
    ReceiveAchievementReward_1451(
        1451,
        ReceiveAchievementReward.getDefaultInstance(),
        ReceiveAchievementRewardRt.getDefaultInstance()
    ), // 领取成就奖励
    ReceiveAchievementShareReward_1452(
        1452,
        ReceiveAchievementShareReward.getDefaultInstance(),
        ReceiveAchievementShareRewardRt.getDefaultInstance()
    ), // 领取成就分享奖励
    GetAchievementInfo_1453(
        1453,
        GetAchievementInfo.getDefaultInstance(),
        GetAchievementInfoRt.getDefaultInstance()
    ), // 领取成就分享奖励

    //国王官职消息 1455 - 1470
    SetCountryPosition_1455(
        1455,
        SetCountryPosition.getDefaultInstance(),
        SetCountryPositionRt.getDefaultInstance()
    ), // 设置国家官职
    AwardAlliance_1456(1456, AwardAlliance.getDefaultInstance(), AwardAllianceRt.getDefaultInstance()), // 赏赐
    OpenWholeCountryBuff_1457(
        1457,
        OpenWholeCountryBuff.getDefaultInstance(),
        OpenWholeCountryBuffRt.getDefaultInstance()
    ), // 开启全国Buff
    AmnestyWholeCountry_1458(
        1458,
        AmnestyWholeCountry.getDefaultInstance(),
        AmnestyWholeCountryRt.getDefaultInstance()
    ), // 天下大赦
    EditorCountryNotice_1459(
        1459,
        EditorCountryNotice.getDefaultInstance(),
        EditorCountryNoticeRt.getDefaultInstance()
    ), // 修改国家公告
    SendNoticeToLeaderOfAlliance_1460(
        1460,
        SendNoticeToLeaderOfAlliance.getDefaultInstance(),
        SendNoticeToLeaderOfAllianceRt.getDefaultInstance()
    ), // 发送公告给盟主
    QueryCountryPosition_1461(
        1461,
        QueryCountryPosition.getDefaultInstance(),
        QueryCountryPositionRt.getDefaultInstance()
    ), // 查询官职信息
    QueryCountryNotice_1462(
        1462,
        QueryCountryNotice.getDefaultInstance(),
        QueryCountryNoticeRt.getDefaultInstance()
    ), // 查询国家公告
    QueryAllianceAward_1463(
        1463,
        QueryAllianceAward.getDefaultInstance(),
        QueryAllianceAwardRt.getDefaultInstance()
    ), // 查询赏赐信息
    QueryFameHall_1464(
        1464,
        QueryFameHall.getDefaultInstance(),
        QueryFameHallRt.getDefaultInstance()
    ), // 查询名人堂

    // 推图消息 1470 - 1490
    BeginInstanceFight_1470(1470, BeginInstanceFight.getDefaultInstance(), BeginInstanceFightRt.getDefaultInstance()), // 开始推图战斗
    InstanceFight_1471(1471, InstanceFight.getDefaultInstance(), InstanceFightRt.getDefaultInstance()), // 推图战斗
    InstanceWipe_1472(1472, InstanceWipe.getDefaultInstance(), InstanceWipeRt.getDefaultInstance()), // 推图扫荡
    InstanceGetStarReward_1473(
        1473,
        InstanceGetStarReward.getDefaultInstance(),
        InstanceGetStarRewardRt.getDefaultInstance()
    ), // 领取每章宝箱
    InstanceGetChapterReward_1474(
        1474,
        InstanceGetChapterReward.getDefaultInstance(),
        InstanceGetChapterRewardRt.getDefaultInstance()
    ), // 领取章节大奖
    BuyInstanceStrength_1475(
        1475,
        BuyInstanceStrength.getDefaultInstance(),
        BuyInstanceStrengthRt.getDefaultInstance()
    ), // 购买体力

    // 魔物消息 1491-1510
    //设置攻打魔物配置
    SetHunterConfig_1491(
        1491,
        SetHunterConfig.getDefaultInstance(),
        SetHunterConfigRt.getDefaultInstance()
    ),
    //查询魔物伤害排行
    QueryHunterRank_1492(
        1492,
        QueryHunterRank.getDefaultInstance(),
        QueryHunterRankRt.getDefaultInstance()
    ),
    //邀请共同猎杀
    InviteTogetherHunter_1493(
        1493,
        InviteTogetherHunter.getDefaultInstance(),
        InviteTogetherHunterRt.getDefaultInstance()
    ),
    //补充体力
    SupplyEnergy_1494(
        1494,
        SupplyEnergy.getDefaultInstance(),
        SupplyEnergyRt.getDefaultInstance()
    ),
    //查询活动boss信息
    QueryActivityBossInfo_1495(
        1495,
        QueryActivityBossInfo.getDefaultInstance(),
        QueryActivityBossInfoRt.getDefaultInstance()
    ),

    // 头像   1511 - 1520

    ChangeIcon_1511(1511, ChangeIcon.getDefaultInstance(), ChangeIconRt.getDefaultInstance()),
    QueryAllIcon_1512(1512, QueryAllIcon.getDefaultInstance(), QueryAllIconRt.getDefaultInstance()),

    // 后宅   1521 - 1540
    EnterGuildHouse_1521(1521, EnterGuildHouse.getDefaultInstance(), EnterGuildHouseRt.getDefaultInstance()),
    QueryFurnitureBag_1522(1522, QueryFurnitureBag.getDefaultInstance(), QueryFurnitureBagRt.getDefaultInstance()),
    PutFurniture_1523(1523, PutFurniture.getDefaultInstance(), PutFurnitureRt.getDefaultInstance()),
    MoveFurniture_1524(1524, MoveFurniture.getDefaultInstance(), MoveFurnitureRt.getDefaultInstance()),
    RemoveFurniture_1525(1525, RemoveFurniture.getDefaultInstance(), RemoveFurnitureRt.getDefaultInstance()),
    BuyFurniture_1526(1526, BuyFurniture.getDefaultInstance(), BuyFurnitureRt.getDefaultInstance()),
    UseHouseTheme_1527(1527, UseHouseTheme.getDefaultInstance(), UseHouseThemeRt.getDefaultInstance()),
    SaveHouseTheme_1528(1528, SaveHouseTheme.getDefaultInstance(), SaveHouseThemeRt.getDefaultInstance()),
    GatherFurnitureProduce_1529(1529, FurnitureProduce.getDefaultInstance(), FurnitureProduceRt.getDefaultInstance()),
    PutHeroIntoGuildHouse_1530(
        1530,
        PutHeroIntoGuildHouse.getDefaultInstance(),
        PutHeroIntoGuildHouseRt.getDefaultInstance()
    ),
    ThumbUp_1531(1531, ThumbUp.getDefaultInstance(), ThumbUpRt.getDefaultInstance()),
    QueryThumbInfo_1532(1532, QueryThumbInfo.getDefaultInstance(), QueryThumbInfoRt.getDefaultInstance()),
    BuyHouseTheme_1533(1533, BuyHouseTheme.getDefaultInstance(), BuyHouseThemeRt.getDefaultInstance()),
    RemoveHouseTheme_1534(1534, RemoveHouseTheme.getDefaultInstance(), RemoveHouseThemeRt.getDefaultInstance()),
    RemoveTypeFurniture_1535(
        1535,
        RemoveTypeFurniture.getDefaultInstance(),
        RemoveTypeFurnitureRt.getDefaultInstance()
    ),
    ChangeGuildHouse_1536(1536, ChangeGuildHouse.getDefaultInstance(), ChangeGuildHouseRt.getDefaultInstance()),
    ChangeThemeName_1537(1537, ChangeThemeName.getDefaultInstance(), ChangeThemeNameRt.getDefaultInstance()),

    // 城墙
    QueryWallInfo_1540(1540, QueryWallInfo.getDefaultInstance(), QueryWallInfoRt.getDefaultInstance()),
    WallFireFight_1542(1542, WallFireFight.getDefaultInstance(), QueryWallInfoRt.getDefaultInstance()),

    // 图书馆
    SwitchLibTag_1556(1556, SwitchLibTag.getDefaultInstance(), SwitchLibTagRt.getDefaultInstance()),

    // 1545~1555 抽卡
    QueryLottery_1545(1545, QueryLottery.getDefaultInstance(), QueryLotteryRt.getDefaultInstance()),
    PlayLottery_1546(1546, PlayLottery.getDefaultInstance(), PlayLotteryRt.getDefaultInstance()),

    // 银行加速投资
    BankAccelerate_1561(1561, BankAccelerate.getDefaultInstance(), BankAccelerateRt.getDefaultInstance()), // 银行投资
    CancelBankAccelerate_1562(
        1562,
        CancleBankAccelerate.getDefaultInstance(),
        CancleBankAccelerateRt.getDefaultInstance()
    ), // 取消银行投资
    GetBankAccelerate_1563(
        1563,
        GetBankAccelerate.getDefaultInstance(),
        GetBankAccelerateRt.getDefaultInstance()
    ), // 取消银行投资

    // 奇观争夺战活动
    QueryWonderInfo_1571(1571, QueryWonderInfo.getDefaultInstance(), QueryWonderInfoRt.getDefaultInstance()),
    QueryWonderRank_1572(1572, QueryWonderRank.getDefaultInstance(), QueryWonderRankRt.getDefaultInstance()),

    //迷雾
    FightWithFogArmy_1573(1573, FightWithFogArmy.getDefaultInstance(), FightWithFogArmyRt.getDefaultInstance()),
    GetFogReward_1574(1574, GetFogReward.getDefaultInstance(), GetFogRewardRt.getDefaultInstance()),
    QueryFogArmy_1575(1575, QueryFogArmy.getDefaultInstance(), QueryFogArmyRt.getDefaultInstance()),

    // 查询他人信息
    OtherPersonalPower_1576(1576, OtherPersonalPower.getDefaultInstance(), OtherPersonalPowerRt.getDefaultInstance()),

    // 赌场
    GetCasinosInfo_1577(1577, GetCasinosInfo.getDefaultInstance(), GetCasinosInfoRt.getDefaultInstance()),
    GetJackpotInfo_1578(1578, GetJackpotInfo.getDefaultInstance(), GetJackpotInfoRt.getDefaultInstance()),
    CasinosLottery_1579(1579, CasinosLottery.getDefaultInstance(), CasinosLotteryRt.getDefaultInstance()),

    // 游戏设置
    GetNoticeSetting_1580(1580, GetNoticeSetting.getDefaultInstance(), GetNoticeSettingRt.getDefaultInstance()),
    ChangeNoticeSetting_1581(
        1581,
        ChangeNoticeSetting.getDefaultInstance(),
        ChangeNoticeSettingRt.getDefaultInstance()
    ),

    // 礼包
    QueryPayOrder_1582(1582, QueryPayOrder.getDefaultInstance(), QueryPayOrderRt.getDefaultInstance()),
    RecvMonthCardReward_1583(1583, MonthCardReward.getDefaultInstance(), MonthCardRewardRt.getDefaultInstance()),
    QueryQuotaBagInfo_1584(1584, QueryQuotaBag.getDefaultInstance(), QueryQuotaBagRt.getDefaultInstance()),

    // 模块数据
    RequireModuleData_2500(2500, RequireModuleData.getDefaultInstance(), RequireModuleDataRt.getDefaultInstance()),

    //客户端推给服务器的消息.消息头从3000开始
    RefreshMoney_3000(3000, null, RefreshMoney.getDefaultInstance()), //刷新资源
    GoHome_3001(3001, null, GoHome.getDefaultInstance()), //部队回城
    StartTrain_3003(3003, null, StartTrain.getDefaultInstance()), //开始练兵推送
    BingliChange_3004(3004, null, ValuesChange.getDefaultInstance()), //武将属性发生变化
    ForceValueChange_3006(3006, null, ForceValuesChange.getDefaultInstance()), //部队某属性变化推送
    PlayerChange_3008(3008, null, PlayerChange.getDefaultInstance()), //玩家属性变化推送
    YieldChange_3009(3009, null, YieldChange.getDefaultInstance()), //产量变化主推
    HeroChange_3010(3010, null, HeroChange.getDefaultInstance()), //武将升级推送
    RelationChange_3012(3012, null, RelationChange.getDefaultInstance()), //地块关系变化
    BuildUpOverChange_3013(3013, null, BuildUpOverChange.getDefaultInstance()), //建筑升级推送
    UpdateLandBelong_3014(3014, null, UpdateLandBelong.getDefaultInstance()),
    DecreeChange_3015(3015, null, DecreeChange.getDefaultInstance()), //令牌变化主推
    AllianceMarksChange_3017(3017, null, AllianceMarksChange.getDefaultInstance()), //联盟标记推送
    AllianceInfoChange_3018(3018, null, AllianceInfoChange.getDefaultInstance()), //联盟信息变化推送（自己）
    LostBuildCity_3021(3021, null, LoseBuildCity.getDefaultInstance()), //玩家失去正在建造中的城池
    RequestBreak_3022(3022, null, ReturnLogin.getDefaultInstance()), //请求断线
    AddWalkLine_3031(3031, null, AddWalkLine.getDefaultInstance()), //新增行军主推
    RemoveWalkLine_3032(3032, null, RemoveWalkLine.getDefaultInstance()), //结束行军主推
    NoReadFightInfoNum_3033(3033, null, NoReadFightInfoNum.getDefaultInstance()), //当前战报未读数
    TaskOperation_3035(3035, null, TaskOperation.getDefaultInstance()), //任务操作

    ExchangeDemandChange_3037(3037, null, ExchangeDemandChange.getDefaultInstance()), //联盟捐献/换卡新需求推送
    StrayFreeTimeChange_3038(3038, null, StrayFreeTimeChange.getDefaultInstance()), //玩家流浪后免战推送
    ChestKillNumChange_3040(3040, null, ChestKillNumChange.getDefaultInstance()), //击杀宝箱击杀数量更新
    OccupiedChange_3051(3051, null, OccupiedChange.getDefaultInstance()), //玩家沦陷推送
    LoseFencheng_3052(3052, null, LoseFencheng.getDefaultInstance()), //玩家失去分城推送
    NoReadMessageChange_3053(3053, null, NoReadMessageChange.getDefaultInstance()), //玩家未读聊天信息

    NoticeInfo_3055(3055, null, NoticeInfo.getDefaultInstance()), //公告信息
    RelationShipChange_3057(3057, null, RelationShipChange.getDefaultInstance()), //联盟外交关系变化
    AllianceFlagChange_3058(3058, null, AllianceFlagChange.getDefaultInstance()), //推送联盟旗帜变化信息
    AllianceMemberChange_3060(3060, null, AllianceMemberChange.getDefaultInstance()), //联盟信息变化推送（非自己）

    WorldTaskChange_3063(3063, null, WorldTaskChange.getDefaultInstance()), //天下大势ID改变

    RoomInfoChange_3070(3070, null, RoomInfoChange.getDefaultInstance()), // 聊天群 名字变化
    RoomAddMember_3071(3071, null, RoomAddMember.getDefaultInstance()), // 聊天室新增成员
    RoomDelMember_3072(3072, null, RoomDelMember.getDefaultInstance()), // 聊天室删除成员
    RoomDel_3073(3073, null, RoomDel.getDefaultInstance()), // 删除聊天室
    //    NewRoom_3074(3074, null, NewRoom.getDefaultInstance()), // 新增聊天室
    EnterGameOffline_3075(3075, null, EnterGameOffline.getDefaultInstance()), // 私聊、好友上下线主推
    GroupChatInfo_3076(3076, null, GroupChatInfo.getDefaultInstance()), // 聊天室消息推送
    ChanelChatInfo_3077(3077, null, ChanelChatInfo.getDefaultInstance()), // 频道消息推送
    GroupGiveMaster_3078(3078, null, GroupGiveMaster.getDefaultInstance()), // 转让群主
    PrivateChatInfo_3079(3079, null, PrivateChatInfo.getDefaultInstance()), // 接收私聊消息
    NewChatMessage_3080(3080, null, NewChatMessage.getDefaultInstance()), // 接收聊天消息
    BagChange_3081(3081, null, BagChange.getDefaultInstance()), // 玩家物品背包变化

    AddResChange_3085(3085, null, AddResChange.getDefaultInstance()), // 玩家获取资源的推送

    FbOnStageMapChange_3090(3090, null, FbOnStageMapChange.getDefaultInstance()), //正在进行中的演武副本推送
    FbOnStageChange_3091(3091, null, FbOnStageChange.getDefaultInstance()), //推送正在进行中的副本的概要信息
    FbOnStageCellChange_3092(3092, null, FbOnStageCellChange.getDefaultInstance()), //推送正在进行中的副本的地块信息
    FbOnStageForceChange_3093(3093, null, FbOnStageForceChange.getDefaultInstance()), //推送正在进行中的副本的部队信息
    FbOnStageGridsChange_3094(3094, null, FbOnStageGridsChange.getDefaultInstance()), //推送正在进行中的副本的格子信息
    FbStageOverChange_3099(3099, null, FbStageOverChange.getDefaultInstance()), //完成/结束副本推送

    AllianceTopicReplyChange_3100(3100, null, AllianceTopicReplyChange.getDefaultInstance()), //联盟邮件新主题或新回复通知
    AlliancePlayerDemandChange_3101(3101, null, AlliancePlayerDemandChange.getDefaultInstance()), //请求捐卡信息推送
    GetNewMail_3102(3102, null, GetNewMail.getDefaultInstance()), //获得新邮件主推
    PlayFightInfo_3103(3103, null, PlayFightInfo.getDefaultInstance()), //立即播放下面的战斗
    CodeUseRt_3104(
        3104,
        null,
        CodeUseRt.getDefaultInstance()
    ), //礼包码使用结果推送                                             ), //结果---0：成功;1：校验码错误;2：时间过期;3：缺少参数;100：参数错误;104：活动批次的码失效;105：码已经失效;106：码不存在;107：活动过期;108：码超过使用次数;109：活动游戏编码错误;112：用户名非该码绑定用户

    ForceLeave_3106(3106, null, ForceLeave.getDefaultInstance()), //地块上离开了部队

    RemoveAllForShiye_3108(3108, null, RemoveAllForShiye.getDefaultInstance()), //玩家丢失的地块视野,客户端收到直接清除所有此地块上的视野信息

    AddWarningLine_3109(3109, null, AddWarningLine.getDefaultInstance()), //新增预警主推
    RemoveWarningLine_3110(3110, null, RemoveWarningLine.getDefaultInstance()), //结束预警主推

    TowerNumChange_3111(3111, null, TowerNumChange.getDefaultInstance()), // 爬塔券变化主推
    TowerRefresh_3113(3113, null, TowerRefresh.getDefaultInstance()), // 爬塔数据刷新

    PaySuccess_3115(3115, null, PaySuccess.getDefaultInstance()), // 充值成功之后推送给客户端

    NewRedPoint_3116(3116, null, NewRedPoint.getDefaultInstance()), // 推送给客户端新出现一个小红点
    HideRedPoint_3117(3117, null, HideRedPoint.getDefaultInstance()), // 推送给客户端隐藏一个小红点

    DeleteHero_3118(3118, null, DeleteHero.getDefaultInstance()), //删除N个武将

    EquipMasterChange_3119(3119, null, EquipMasterChange.getDefaultInstance()), //装备主人发生变化

    MissionInfoChange_3120(3120, null, MissionInfoChange.getDefaultInstance()), //推图信息变化

    ResearchChange_3121(3121, null, ResearchChange.getDefaultInstance()), // 玩家科技信息变化

    GetAllianceHelp_3122(3122, null, GetAllianceHelp.getDefaultInstance()), // 玩家获得帮助提示窗

    BarracksChange_3123(3123, null, BarracksChange.getDefaultInstance()), // 玩家兵营信息变化

    BuildInfoByAllianceHelp_3124(3124, null, BuildInfoByAllianceHelp.getDefaultInstance()), // 建筑的某些公共信息变化的推送,关于联盟帮助的

    AllianceResearchChange_3125(3125, null, AllianceResearchChange.getDefaultInstance()), // 联盟科技发生变化
    PlayerAllianceResearchChange_3126(3126, null, PlayerAllianceResearchChange.getDefaultInstance()), // 玩家帮派科技研发数据发生变化
    StartBuildFlag_3127(3127, null, StartBuildFlag.getDefaultInstance()), // 开始帮忙建造战旗推送
    BuildFlagEnd_3128(3128, null, BuildFlagEnd.getDefaultInstance()), // 帮忙建造战旗完毕推送给战旗主人时间变化推送
    RelicRefreshInfoChange_3129(3129, null, RelicRefreshInfoChange.getDefaultInstance()), // 遗迹刷新时间变化
    AddOrRemoveRelicBus_3130(3130, null, AddOrRemoveRelicBus.getDefaultInstance()), // 新增/删除遗迹车
    TimeBoxInfoChange_3131(3131, null, TimeBoxInfoChange.getDefaultInstance()), // 某一个时光之盒槽位变化

    TalentPointChange_3132(3132, null, TalentPointChange.getDefaultInstance()), // 天赋点变化
    VipChange_3133(3133, null, VipChange.getDefaultInstance()), // vip变化
    KingExpChange_3134(3134, null, KingExpChange.getDefaultInstance()), // 君主经验变化
    EnterGamePublicRt_3135(3135, null, EnterGamePublicRt.getDefaultInstance()), // 进游戏时的public服推送过来的数据
    MakeKingEquipChange_3136(3136, null, MakeKingEquipChange.getDefaultInstance()), // 玩家锻造君主装备队列变化
    EnterGameMapRt_3137(3137, null, EnterGameMapRt.getDefaultInstance()), // 进游戏时的map服推送过来的数据

    BuildBagChange_3138(3138, null, BuildBagChange.getDefaultInstance()), // 玩家建筑收纳包中的数据

    WalkRobotUpdate_3139(3139, null, WalkRobotUpdate.getDefaultInstance()), // 行军小人的位置更新
    WalkRobotShow_3140(3140, null, WalkRobotShow.getDefaultInstance()), // 行军线的新增与删除

    AttackNotice_3141(3141, null, AttackNotice.getDefaultInstance()), //攻击通知

    WalkGroupChange_3142(3142, null, NoticeWalkGroupChange.getDefaultInstance()), //行军组变化
    MassGroupChange_3143(3143, null, NoticeMassGroupChange.getDefaultInstance()), //集结组变化

    CaveChange_3144(3144, null, NoticeCaveChg.getDefaultInstance()), //藏兵变化
    GetNewAllianceGift_3145(3145, null, GetNewAllianceGift.getDefaultInstance()), //获得一个联盟礼物
    AllianceGiftChange_3146(3146, null, AllianceGiftChange.getDefaultInstance()), //联盟大礼物数据发生变化

    TransportRequestChange_3150(3150, null, TransportRequestChange.getDefaultInstance()), // 运输请求发生变化
    BuffChange_3151(3151, null, BuffChange.getDefaultInstance()), // buff情况发生变化
    AllianceReqInfoChange_3152(3152, null, AllianceReqInfoChange.getDefaultInstance()), // 联盟申请信息变化
    AllianceHelpInfoChange_3153(3153, null, AllianceHelpInfoChange.getDefaultInstance()), // 联盟帮助信息变化
    NoticeLanMsg_3154(3154, null, NoticeLanMsg.getDefaultInstance()), // 通知lan消息
    PlayerActivityChange_3155(3155, null, PlayerActivityChange.getDefaultInstance()), // 玩家活动信息发生变化
    PlayerRewardBagInfoChange_3156(3156, null, PlayerRewardBagInfoChange.getDefaultInstance()), // 玩家领奖中心变更
    StrongholdCountChange_3157(3157, null, StrongholdCountChange.getDefaultInstance()), // 攻打据点次数变化
    AllianceLivenessVoChange_3158(3158, null, AllianceLivenessVoChange.getDefaultInstance()), // 联盟活跃信息发生变化
    AllianceMissionGiftNumChange_3159(3159, null, AllianceMissionGiftNumChange.getDefaultInstance()), // 联盟活跃度奖励个数
    GetVipLoginReward_3160(3160, null, GetVipLoginReward.getDefaultInstance()), // 获得vip每日登陆奖励
    PlayerPowerChange_3161(3161, null, PlayerPowerChange.getDefaultInstance()), // 玩家实力变化
    PlayerPrisonChange_3162(3162, null, PlayerPrisonChange.getDefaultInstance()), // 玩家监狱信息变化
    KingEquipBagNumChange_3163(3163, null, KingEquipBagNumChange.getDefaultInstance()), // 玩家君主装备背包容量变化
    AchievementChange_3164(3164, null, AchievementChange.getDefaultInstance()), // 成就变化
    StoreLimitChange_3165(3165, null, StoreLimitChange.getDefaultInstance()), // 资源产量上限变化
    CountryPositionChange_3166(3166, null, CountryPositionChange.getDefaultInstance()), // 官职变化主推
    CastlePosChange_3167(3167, null, CastlePosChange.getDefaultInstance()), // 玩家城位置变化
    InnerCityInfoChanged_3168(3168, null, InnerCityInfoChanged.getDefaultInstance()), // 内城建筑变化
    WonderForceChange_3169(3169, null, WonderForceChange.getDefaultInstance()), //奇观部队变化
    AmnestyCountChange_3170(3170, null, AmnestyCountChange.getDefaultInstance()), //赦免次数变化
    BigMapRef_3171(3171, null, BigMapRef.getDefaultInstance()), // 收到消息请求110
    FriendApplyChange_3172(3172, null, FriendApplyChange.getDefaultInstance()),//玩家的好友添加信息发生变化
    NewBattleReport_3173(3173, null, NewBattleReport.getDefaultInstance()),
    HunterInfoChange_3174(3174, null, HunterInfoChange.getDefaultInstance()),
    StrengthChange_3175(3175, null, StrengthChange.getDefaultInstance()),
    MonsterDamageInfo_3176(3176, null, MonsterDamageInfo.getDefaultInstance()),
    MarkNumChange_3177(3177, null, MarkNumChange.getDefaultInstance()),// 玩家收藏变化
    ArenaRankChange_3178(3178, null, ArenaRankChange.getDefaultInstance()), // 竞技场排名变化
    ArenaRewardGet_3179(3179, null, ArenaRewardGet.getDefaultInstance()),
    FurnitureProduceChange_3180(3180, null, FurnitureProduceChange.getDefaultInstance()),
    PlayerRansomChange_3181(3181, null, PlayerRansomChange.getDefaultInstance()),
    LotteryChange_3182(3182, null, LotteryChange.getDefaultInstance()),
    NewLibraryItem_3183(3183, null, NewLibraryItem.getDefaultInstance()),
    GetRes_3184(3184, null, GetRes.getDefaultInstance()),
    FriendApplySuccess_3185(3185, null, FriendApplySuccess.getDefaultInstance()),
    FriendGroupChange_3186(3186, null, FriendGroupChange.getDefaultInstance()),
    ActivityEnterTimeChange_3187(3187, null, ActivityEnterTimeChange.getDefaultInstance()),
    EnterChatRoomInfo_3188(3188, null, EnterGameChatRoomInfo.getDefaultInstance()),
    SendStrangerInfo_3189(3189, null, SendStrangerInfo.getDefaultInstance()),
    KillActivityBossReport_3190(3190, null, KillActivityBossReport.getDefaultInstance()),
    WonderOccupied_3191(3191, null, WonderOccupied.getDefaultInstance()),
    CheckConfig_3192(3192, null, CheckConfig.getDefaultInstance()),
    MoveServerResult_3193(3193, null, MoveServerResult.getDefaultInstance()),
    NewPlayerActivityOver_3194(3194, null, NewPlayerActivityOver.getDefaultInstance()),
    EnterGameHomeRt_3200(3200, null, EnterGameHomeRt.getDefaultInstance()),

    TransportRequestInit_3300(3300, null, TransportRequestInit.getDefaultInstance()),
    PlayerPrisonInit_3301(3301, null, PlayerPrisonInit.getDefaultInstance()),
    AchievementInit_3302(3302, null, AchievementInit.getDefaultInstance()),
    HunterInfoInit_3303(3303, null, HunterInfoInit.getDefaultInstance()),
    WonderInfoInit_3304(3304, null, WonderInfoInit.getDefaultInstance()),
    InstanceInfoInit_3305(3305, null, InstanceInfoInit.getDefaultInstance()),

    SkinInfoInit_3350(3350, null, SkinInfoInit.getDefaultInstance()),
    PlayerAddInfoInit_3351(3351, null, PlayerAddInfoInit.getDefaultInstance()),
    AllianceGiftInfoInit_3352(3352, null, AllianceGiftInfoInit.getDefaultInstance()),
    MarkInfoInit_3353(3353, null, MarkInfoInit.getDefaultInstance()),
    LotteryInfoInit_3354(3354, null, LotteryInfoInit.getDefaultInstance()),

    TriggerQuotaBag_3355(3355, null, TriggerQuotaBag.getDefaultInstance()),
    TriggerGiftBag_3356(3356, null, TriggerGiftBag.getDefaultInstance()),
    ChangeQuotaBagDegree_3357(3357, null, ChangeQuotaBagDegree.getDefaultInstance()),
    FinishQuotaBag_3358(3358, null, FinishQuotaBag.getDefaultInstance()),
    PayGiftBagSuccess_3359(3359, null, PayGiftBagSuccess.getDefaultInstance()),
    PayMonthCardSuccess_3360(3360, null, PayGiftBagSuccess.getDefaultInstance());

    companion object : EnumConverter<Int, MsgType>(buildValueMap(MsgType::msgType)) {

        // request
        fun fromReq(value: Int): MessageLite? {
            val msgType = fromValue(value)
            if (msgType == null) {
                return null
            }
            return msgType.req
        }

        //response
        fun fromResp(value: Int): MessageLite? {
            val msgType = fromValue(value)
            if (msgType == null) {
                return null
            }
            return msgType.resp
        }

    }
}