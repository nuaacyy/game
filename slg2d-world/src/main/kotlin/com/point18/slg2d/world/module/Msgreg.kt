package com.point18.slg2d.world.module

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.syncdomain.MoveServerEntitysReq
import com.point18.slg2d.world.ask4deal.*
import com.point18.slg2d.world.gmsg.*
import com.point18.slg2d.world.module.alliance.*
import com.point18.slg2d.world.module.barracks.DismissSoliderDeal
import com.point18.slg2d.world.module.barracks.QueryWallInfo
import com.point18.slg2d.world.module.boss.InviteHunterDeal
import com.point18.slg2d.world.module.boss.QueryActivityBossInfoDeal
import com.point18.slg2d.world.module.boss.QueryHunterBossRankDeal
import com.point18.slg2d.world.module.boss.SetHunterBossConfigDeal
import com.point18.slg2d.world.module.cave.CaveCancelDeal
import com.point18.slg2d.world.module.cave.CaveConfigDeal
import com.point18.slg2d.world.module.chat.ChatDeal
import com.point18.slg2d.world.module.fog.FightWithFogArmy
import com.point18.slg2d.world.module.fog.GetFogReward
import com.point18.slg2d.world.module.fog.QueryFogArmyDeal
import com.point18.slg2d.world.module.hero.UpdateMainHeroDeal
import com.point18.slg2d.world.module.instance.BeginInstanceFight
import com.point18.slg2d.world.module.instance.GetInstanceStarReward
import com.point18.slg2d.world.module.instance.InstanceFWipe
import com.point18.slg2d.world.module.instance.InstanceFight
import com.point18.slg2d.world.module.intHero.SetDefHeroDeal
import com.point18.slg2d.world.module.jjc.RefreshChallengeDeal
import com.point18.slg2d.world.module.jjc.SelectJjcAtkForceDeal
import com.point18.slg2d.world.module.mail.SendAllianceMailDeal
import com.point18.slg2d.world.module.mainHeroprison.CheckEatPoisonDeal
import com.point18.slg2d.world.module.mainHeroprison.GetResurgenceDeal
import com.point18.slg2d.world.module.mainHeroprison.PrisonFreeDeal
import com.point18.slg2d.world.module.mainHeroprison.SetRansomDeal
import com.point18.slg2d.world.module.moveCity.AllServerInfo
import com.point18.slg2d.world.module.moveCity.InitPlayerSessionAfterMoveServerDeal
import com.point18.slg2d.world.module.moveCity.MoveServerCost
import com.point18.slg2d.world.module.moveCity.MoveServerDeal
import com.point18.slg2d.world.module.playerActivity.*
import com.point18.slg2d.world.module.rank.QueryRankDeal
import com.point18.slg2d.world.module.rank.QueryRankFirstDeal
import com.point18.slg2d.world.module.realm.*
import com.point18.slg2d.world.module.setting.ChangeNoticeSettingDeal
import com.point18.slg2d.world.module.setting.GetNoticeSettingDeal
import com.point18.slg2d.world.module.task.GetAchievementRewardDeal
import com.point18.slg2d.world.module.transport.DeleteTransportRequestDeal
import com.point18.slg2d.world.module.transport.PublishTransportRequestDeal
import com.point18.slg2d.world.module.viewprofile.DealCheckPlayerName
import com.point18.slg2d.world.module.viewprofile.QueryAndAmendPersonalDeal
import com.point18.slg2d.world.module.viewprofile.ResourceParticularDeal
import com.point18.slg2d.world.module.walk.*
import com.point18.slg2d.world.module.walk.fight.GetHeroFightReportDeal
import com.point18.slg2d.world.module.walk.fight.TestFightDeal
import com.point18.slg2d.world.module.wonder.*
import com.point18.slg2d.world.module.worldmap.GetAllServerInfoDeal
import com.point18.slg2d.world.module.worldmap.QueryCellDeal
import com.point18.slg2d.world.module.worldmap.RegisterWatchDeal
import com.point18.slg2d.world.module.worldmap.ShowNearMapDeal
import com.point18.slg2d.world.worldMsgDeal
import com.point18.slg2d.world.wpm
import pb4server.*

// 注册消息处理
fun registerDeals() {
    // world -> world 的ask
    wpm.worldAskMsgDeal[World2WorldAskReq.MsgCase.CHECKMOVESERVERXYREQ] = CheckMoveServerCellLockMsgDeal()
    wpm.worldAskMsgDeal[World2WorldAskReq.MsgCase.CHANGEWATCHASKREQ] = ChangeWatchDeal()
    // world -> world 的ask  kryo的请求
    wpm.gMsgDeal[MoveServerEntitysReq::class.java] = MoveServerInfoMsgDeal()

    // home -> world 的ask
    wpm.homeAskMsgDeal[Home2WorldAskReq.MsgCase.WORLDCHATASKREQ] = WorldChatMsgDeal()
    wpm.homeAskMsgDeal[Home2WorldAskReq.MsgCase.FINDALLPLAYERASKREQ] = FindAllPlayersDeal()
    wpm.homeAskMsgDeal[Home2WorldAskReq.MsgCase.JJCINFOQUERYASKREQ] = QueryArenaInfoDeal()
    wpm.homeAskMsgDeal[Home2WorldAskReq.MsgCase.ARENAFIGHTASKREQ] = ArenaFightWorldDeal()
    wpm.homeAskMsgDeal[Home2WorldAskReq.MsgCase.FIREFIGHTASKREQ] = FireFightWorldDeal()
    wpm.homeAskMsgDeal[Home2WorldAskReq.MsgCase.MAXPRISONBUFFASKREQ] = GetMaxPrisonBuffDeal()
    wpm.homeAskMsgDeal[Home2WorldAskReq.MsgCase.EATPOISONNUMASKREQ] = EatPoisonDeal()
    wpm.homeAskMsgDeal[Home2WorldAskReq.MsgCase.GIVERANSOMASKREQ] = GiveRansomDeal()
    wpm.homeAskMsgDeal[Home2WorldAskReq.MsgCase.SETMAINHEROREWARDASKREQ] = AddMainHeroRewardDeal()
    wpm.homeAskMsgDeal[Home2WorldAskReq.MsgCase.KILLPRISONASKREQ] = KillPrisonDeal()
    wpm.homeAskMsgDeal[Home2WorldAskReq.MsgCase.RESURGENCEASKREQ] = ResurgenceAtOnceDeal()
    wpm.homeAskMsgDeal[Home2WorldAskReq.MsgCase.CURESOLIDERASKREQ] = CureSoliderDeal()
    wpm.homeAskMsgDeal[Home2WorldAskReq.MsgCase.DEALHEARTASKREQ] = HomeHeartDeal()
    wpm.homeAskMsgDeal[Home2WorldAskReq.MsgCase.UPDATEINFOBYHOMEASKREQ] = UpdateInfoByHomeDeal()
    wpm.homeAskMsgDeal[Home2WorldAskReq.MsgCase.MAKESOLIDERASKREQ] = MakeSoliderDeal()
    wpm.homeAskMsgDeal[Home2WorldAskReq.MsgCase.CANCELMAKESOLIDERASKREQ] = CancelMakeSoliderDeal()
    wpm.homeAskMsgDeal[Home2WorldAskReq.MsgCase.CANCELCURESOLIDERASKREQ] = CancelCureSoliderDeal()
    wpm.homeAskMsgDeal[Home2WorldAskReq.MsgCase.TRANSPORTRESASKREQ] = TransportResDeal()
    wpm.homeAskMsgDeal[Home2WorldAskReq.MsgCase.WALK4SCOUTASKREQ] = ScoutDeal()
    wpm.homeAskMsgDeal[Home2WorldAskReq.MsgCase.CALLBOSSASKREQ] = CallBossDeal()
    wpm.homeAskMsgDeal[Home2WorldAskReq.MsgCase.HALFWAYHOMEASKREQ] = HalfWayHomeDeal()
    wpm.homeAskMsgDeal[Home2WorldAskReq.MsgCase.MASSSPEEDASKREQ] = MassSpeedDeal()
    wpm.homeAskMsgDeal[Home2WorldAskReq.MsgCase.WALKSPEEDASKREQ] = WalkSpeedDeal()
    wpm.homeAskMsgDeal[Home2WorldAskReq.MsgCase.RANDOMPOINTMOVECITYASKREQ] = RandomPointMoveCityDeal()
    wpm.homeAskMsgDeal[Home2WorldAskReq.MsgCase.USESOLIDERADDASKREQ] = UseSoliderAddDeal()
    wpm.homeAskMsgDeal[Home2WorldAskReq.MsgCase.REFRESHWARNASKREQ] = RefreshWarnDeal()
    wpm.homeAskMsgDeal[Home2WorldAskReq.MsgCase.BUFFBAGASKREQ] = UseBuffBagDeal()
    wpm.homeAskMsgDeal[Home2WorldAskReq.MsgCase.ADDDECREEASKREQ] = AddDecreeDeal()
    wpm.homeAskMsgDeal[Home2WorldAskReq.MsgCase.BARRACKSPEEDASKREQ] = BarrackSpeedDeal()
    wpm.homeAskMsgDeal[Home2WorldAskReq.MsgCase.ADDINSTANCESTRENGTHASKREQ] = AddInstanceStrengthDeal()
    wpm.homeAskMsgDeal[Home2WorldAskReq.MsgCase.MOVECITYASKREQ] = MoveCityDeal()
    wpm.homeAskMsgDeal[Home2WorldAskReq.MsgCase.QUERYINFOBYWORLDASKREQ] = QueryInfoByWorldDeal()
    wpm.homeAskMsgDeal[Home2WorldAskReq.MsgCase.SENDNOTICETOLEADERASKREQ] = SendLeaderNotice()
    wpm.homeAskMsgDeal[Home2WorldAskReq.MsgCase.MARQUEEASKREQ] = MarqueeDeal()

    // public -> world 的tell
    wpm.publicTellMsgDeal[Public2WorldTell.MsgCase.DEALAFTERSETALLIANCENAMETELL] =
            com.point18.slg2d.world.gmsg.DealAfterSetAllianceNameDeal()
    wpm.publicTellMsgDeal[Public2WorldTell.MsgCase.DEALAFTERSETALLIANCEFLAGTELL] =
            com.point18.slg2d.world.gmsg.DealAfterSetAllianceFlagDeal()
    wpm.publicTellMsgDeal[Public2WorldTell.MsgCase.POSCHANGENOTICALLALLIANCETELL] = PosChangeNoticeAllAllianceDeal()
    wpm.publicTellMsgDeal[Public2WorldTell.MsgCase.GETNEWALLIANCEPOSTELL] =
            com.point18.slg2d.world.gmsg.GetNewAlliancePosDeal()
    wpm.publicTellMsgDeal[Public2WorldTell.MsgCase.KICKALLIANCEMEMBERSUCCESSTELL] = KickAllianceMemberSuccessDeal()
    wpm.publicTellMsgDeal[Public2WorldTell.MsgCase.JOININALLIANCESUCCESSTELL] = JoinInAllianceSuccessDeal()
    wpm.publicTellMsgDeal[Public2WorldTell.MsgCase.DEALAFTERALLIANCEPUBLISHTOPICTELL] =
            com.point18.slg2d.world.gmsg.DealAfterAlliancePublishTopicDeal()
    wpm.publicTellMsgDeal[Public2WorldTell.MsgCase.DEALAFTERWRITEALLIANCEWAIJIAOTELL] =
            com.point18.slg2d.world.gmsg.DealAfterWriteAllianceWaijiaoDeal()
    wpm.publicTellMsgDeal[Public2WorldTell.MsgCase.DEALAFTERSETALLIANCEMARKTELL] =
            com.point18.slg2d.world.gmsg.DealAfterSetAllianceMarkDeal()
    wpm.publicTellMsgDeal[Public2WorldTell.MsgCase.DEALHELPERNOTICETELL] =
            com.point18.slg2d.world.gmsg.DealHelperNoticeDeal()
    wpm.publicTellMsgDeal[Public2WorldTell.MsgCase.ALLIANCEMEMBERINFOCHANGETELL] =
            com.point18.slg2d.world.gmsg.AllianceMemberInfoChangeDeal()
    wpm.publicTellMsgDeal[Public2WorldTell.MsgCase.PLAYERONLINENOTICTELL] = PlayerOnlineNoticeDeal()
    wpm.publicTellMsgDeal[Public2WorldTell.MsgCase.ALLIANCEDISMISSNOTIC2GTELL] =
            com.point18.slg2d.world.gmsg.AllianceDismissNotice2GDeal()
    wpm.publicTellMsgDeal[Public2WorldTell.MsgCase.SENDMAILTOPLAYERNOTIC2GTELL] = SendMailToPlayerNoticeDeal()
    wpm.publicTellMsgDeal[Public2WorldTell.MsgCase.ALLIANCEGIFTCHANGENOTIC2GTELL] =
            com.point18.slg2d.world.gmsg.AllianceGiftChangeDeal()
    wpm.publicTellMsgDeal[Public2WorldTell.MsgCase.ALLIANCEREQINFOCHANGENOTIC2GTELL] =
            com.point18.slg2d.world.gmsg.AllianceReqInfoChangeDeal()
    wpm.publicTellMsgDeal[Public2WorldTell.MsgCase.ALLIANCEHELPINFOCHANGENOTIC2GTELL] =
            com.point18.slg2d.world.gmsg.AllianceHelpInfoChangeNotice2GDeal()
    wpm.publicTellMsgDeal[Public2WorldTell.MsgCase.ALLIANCEMISSIONCHANGENOTIC2GTELL] =
            com.point18.slg2d.world.gmsg.AllianceMissionChangeNotice2GDeal()
    wpm.publicTellMsgDeal[Public2WorldTell.MsgCase.ALLIANCEMISSIONGIFTADDNOTIC2GTELL] =
            com.point18.slg2d.world.gmsg.AllianceMissionGiftAddNotice2GDeal()
    wpm.publicTellMsgDeal[Public2WorldTell.MsgCase.ALLIANCEACTIVITYCHANGENOTIC2GTELL] =
            com.point18.slg2d.world.gmsg.AllianceActivityAddNotice2GDeal()
    wpm.publicTellMsgDeal[Public2WorldTell.MsgCase.SENDALLIANCEGIFTNOTIC2GTELL] = SendAllianceGiftDeal()
    wpm.publicTellMsgDeal[Public2WorldTell.MsgCase.RECEIVEWONDERAWARDNOTIC2GTELL] = ReceiveWonderAwardDeal()
    wpm.publicTellMsgDeal[Public2WorldTell.MsgCase.OCCUPYWONDERTELL] = OccupyWonderDeal()
    wpm.publicTellMsgDeal[Public2WorldTell.MsgCase.CLEANWONDERTELL] = CleanWonderDeal()
    wpm.publicTellMsgDeal[Public2WorldTell.MsgCase.CHANGEKINGTELL] = ResetKingDeal()

    //======================================================================================
    // home -> world tell 的处理
    wpm.homeTellMsgDeal[Home2WorldTell.MsgCase.GETCHATHISTORYTELL] = GetWorldChatMsgDeal()
    wpm.homeTellMsgDeal[Home2WorldTell.MsgCase.LEAVEALLIANCEBYSELFTELL] = LeaveAllianceBySelfDeal()
    wpm.homeTellMsgDeal[Home2WorldTell.MsgCase.JOINNOSUCTELL] = JoinNoSucTellDeal()
    wpm.homeTellMsgDeal[Home2WorldTell.MsgCase.CREATETASKTOWORLDTELL] =
            com.point18.slg2d.world.gmsg.CreateTaskToWorldDeal()
    wpm.homeTellMsgDeal[Home2WorldTell.MsgCase.REMOVETASKTOWORLDTELL] = RemoveTaskToWorldDeal()
    wpm.homeTellMsgDeal[Home2WorldTell.MsgCase.CLIENTDISCONNECTTELL] = OfflineDeal()

    //======================================================================================

    // world -> world tell 的处理
    wpm.worldTellMsgDeal[World2WorldTell.MsgCase.MOVESERVERCELLUNLOCKTELL] = MoveServerCellUnLockMsgDeal()

    // publicManager -> worldManager tell 处理
    wpm.publicManagerTellMsgDeal[PublicManager2WorldManagerTell.MsgCase.ALLIANCENAMECHANGETELL] =
            AllianceNameChangeDeal()
    wpm.publicManagerTellMsgDeal[PublicManager2WorldManagerTell.MsgCase.ALLIANCESIMPLEINFOCHANGETELL] =
            AllianceSimpleInfoChangeTellDeal()
    wpm.publicManagerTellMsgDeal[PublicManager2WorldManagerTell.MsgCase.ALLIANCEREMOVETELL] =
            AllianceRemoveDeal()

    // world -> worldManager tell 处理
    wpm.worldManagerTellMsgDeal[World2WorldManagerTell.MsgCase.ADDNEWAREATELL] = AddNewAreaTellDeal()
    wpm.worldManagerTellMsgDeal[World2WorldManagerTell.MsgCase.WORLDSYNCINFO2WORLDMANAGERTELL] =
            WorldSyncInfo2WorldManagerTellDeal()


    //======================================================================================

//    worldMsgDeal.registerMsgDeal(MsgType.Offline_2000, OfflineDeal())
    worldMsgDeal.registerMsgDeal(MsgType.Login_1, LoginDeal())
    worldMsgDeal.registerMsgDeal(MsgType.EnterGame_4, EnterDeal())
    worldMsgDeal.registerMsgDeal(MsgType.QueryAndAmendPersonal_11, QueryAndAmendPersonalDeal())

    worldMsgDeal.registerMsgDeal(MsgType.Walk_15, StartWalkDeal())
    // worldMsgDeal.registerMsgDeal(MsgType.PersonalPower_17, PersonalPowerDeal())

    worldMsgDeal.registerMsgDeal(MsgType.ServerTime_21, ServerTimeDeal())
    worldMsgDeal.registerMsgDeal(MsgType.ResourceParticular_25, ResourceParticularDeal())
    worldMsgDeal.registerMsgDeal(MsgType.CheckPlayerName_26, DealCheckPlayerName())

    worldMsgDeal.registerMsgDeal(MsgType.CaveConfig_35, CaveConfigDeal())
    worldMsgDeal.registerMsgDeal(MsgType.CaveCancel_36, CaveCancelDeal())

    worldMsgDeal.registerMsgDeal(MsgType.PhotoQuery_41, PhotoQueryDeal())   // todo 头像和1511 和1512消息重复
    worldMsgDeal.registerMsgDeal(MsgType.SetDefHero_43, SetDefHeroDeal())

    worldMsgDeal.registerMsgDeal(MsgType.GetHeroFightReport_105, GetHeroFightReportDeal())

    worldMsgDeal.registerMsgDeal(MsgType.TestFight_114, TestFightDeal())

    worldMsgDeal.registerMsgDeal(MsgType.ChangeWorldWatch_108, RegisterWatchDeal())
    worldMsgDeal.registerMsgDeal(MsgType.GetAllServerInfo_109, GetAllServerInfoDeal())
    worldMsgDeal.registerMsgDeal(MsgType.NewShowNearMap_110, ShowNearMapDeal())
    worldMsgDeal.registerMsgDeal(MsgType.QueryCell_111, QueryCellDeal())

    //邮件
    worldMsgDeal.registerMsgDeal(MsgType.SendAllianceMail_458, SendAllianceMailDeal())

    worldMsgDeal.registerMsgDeal(MsgType.QueryAllianceRankFirst_500, DealQueryAllianceRankFirst())
    worldMsgDeal.registerMsgDeal(MsgType.QueryRankFirst_501, QueryRankFirstDeal())
    worldMsgDeal.registerMsgDeal(MsgType.QueryRank_502, QueryRankDeal())

    // 竞技场
//    worldMsgDeal.registerMsgDeal(MsgType.JjcQueryInfo_711, QueryInfoDeal())
    worldMsgDeal.registerMsgDeal(MsgType.JjcRefreshChallenge_712, RefreshChallengeDeal())
//    worldMsgDeal.registerMsgDeal(MsgType.JjcDelPlanGrids_718, DelPlanGridsDeal())

//    worldMsgDeal.registerMsgDeal(MsgType.JjcFight_720, FightJjcDeal())
    worldMsgDeal.registerMsgDeal(MsgType.SelectJjcAtkForce_724, SelectJjcAtkForceDeal())

    worldMsgDeal.registerMsgDeal(MsgType.QueryWalkLineDetailInfo_1252, QueryWalkLineDetailInfo())
    worldMsgDeal.registerMsgDeal(MsgType.GoBackHome_1253, CallBackHomeDeal())
    worldMsgDeal.registerMsgDeal(MsgType.StartMass_1254, StartMassDeal())
    worldMsgDeal.registerMsgDeal(MsgType.CancelMass_1255, CancelMassDeal())
    worldMsgDeal.registerMsgDeal(MsgType.QueryReinforce_1256, QueryReinforceDeal())
    worldMsgDeal.registerMsgDeal(MsgType.QueryDetailReinforce_1257, QueryDetailReinforceDeal())
    worldMsgDeal.registerMsgDeal(MsgType.QueryAllianceMemberPos_1258, QueryAllianceMemberPosDeal())
    worldMsgDeal.registerMsgDeal(MsgType.SendMassMemberHome_1259, SendMassMemberHomeDeal())
    worldMsgDeal.registerMsgDeal(MsgType.SendReinforcePlayerHome_1260, SendReinforcePlayerHomeDeal())
    worldMsgDeal.registerMsgDeal(MsgType.DismissSolider_1261, DismissSoliderDeal())
    worldMsgDeal.registerMsgDeal(MsgType.PublishTransportRequest_1262, PublishTransportRequestDeal())
    worldMsgDeal.registerMsgDeal(MsgType.DeleteTransportRequest_1263, DeleteTransportRequestDeal())
    worldMsgDeal.registerMsgDeal(MsgType.SendWonderWarPlayerHome_1264, SendWonderWarPlayerHomeDeal())
    worldMsgDeal.registerMsgDeal(MsgType.SetArmyPlan_1266, SetArmyPlanDeal())
    worldMsgDeal.registerMsgDeal(MsgType.GetArmyPlan_1267, GetArmyPlanDeal())

    // 迁服
    worldMsgDeal.registerMsgDeal(MsgType.MoveServer_1300, MoveServerDeal())
    worldMsgDeal.registerMsgDeal(MsgType.InitPlayerSessionAfterMoveServer_1301, InitPlayerSessionAfterMoveServerDeal())
    worldMsgDeal.registerMsgDeal(MsgType.AllServerInfo_1302, AllServerInfo())
    worldMsgDeal.registerMsgDeal(MsgType.MoveServerCost_1303, MoveServerCost())

    // 活动模块
    worldMsgDeal.registerMsgDeal(MsgType.SelectActivityHistory_1332, SelectActivityHistoryDeal())
    worldMsgDeal.registerMsgDeal(MsgType.OpenActivity_1333, OpenActivityDeal())
    worldMsgDeal.registerMsgDeal(MsgType.SeleteAllianceActivityInfos_1334, SelectAllianceActivityInfoDeal())
    worldMsgDeal.registerMsgDeal(MsgType.SelectActivityRank_1335, SelectActivityRankDeal())
    worldMsgDeal.registerMsgDeal(MsgType.SelectNowRank_1336, SelectNowRankDeal())

    worldMsgDeal.registerMsgDeal(MsgType.UpdateMainHero_1006, UpdateMainHeroDeal())

    // 监禁模块
    worldMsgDeal.registerMsgDeal(MsgType.PrisonFree_1351, PrisonFreeDeal())
    worldMsgDeal.registerMsgDeal(MsgType.SetRansom_1354, SetRansomDeal())
    worldMsgDeal.registerMsgDeal(MsgType.GetResurgence_1358, GetResurgenceDeal())
    worldMsgDeal.registerMsgDeal(MsgType.CheckEatPoisonNum_1359, CheckEatPoisonDeal())

    //成就
    worldMsgDeal.registerMsgDeal(MsgType.ReceiveAchievementReward_1451, GetAchievementRewardDeal())

    //官职
    worldMsgDeal.registerMsgDeal(MsgType.SetCountryPosition_1455, SetCountryPositionDeal())
    worldMsgDeal.registerMsgDeal(MsgType.AwardAlliance_1456, AwardAllianceDeal())
    worldMsgDeal.registerMsgDeal(MsgType.OpenWholeCountryBuff_1457, OpenWholeCountryBuffDeal())
    worldMsgDeal.registerMsgDeal(MsgType.AmnestyWholeCountry_1458, AmnestyWholeCountryDeal())
    worldMsgDeal.registerMsgDeal(MsgType.EditorCountryNotice_1459, EditorCountryNoticeDeal())
    worldMsgDeal.registerMsgDeal(MsgType.QueryCountryPosition_1461, QueryCountryPositionDeal())
    worldMsgDeal.registerMsgDeal(MsgType.QueryCountryNotice_1462, QueryCountryNoticeDeal())
    worldMsgDeal.registerMsgDeal(MsgType.QueryAllianceAward_1463, QueryAllianceAwardDeal())
    worldMsgDeal.registerMsgDeal(MsgType.QueryFameHall_1464, QueryFameHallDeal())

    // GM命令
    worldMsgDeal.registerMsgDeal(MsgType.Chat_300, ChatDeal())

    // 聊天
//    worldMsgDeal.registerMsgDeal(MsgType.CreateScopeMsg_304, AddChatRoomDeal())
//    worldMsgDeal.registerMsgDeal(MsgType.AddGroupChatMember_315, JoinChatRoomDeal())

    // RPC联盟模块
    worldMsgDeal.registerMsgDeal(MsgType.AllianceCreate_802, DealCreateAlliance())
    worldMsgDeal.registerMsgDeal(MsgType.AllianceJoinById_804, DealJoinAllianceById())
    worldMsgDeal.registerMsgDeal(MsgType.AllianceJoinCancel_805, DealAllianceJoinCancel())
    worldMsgDeal.registerMsgDeal(MsgType.AllianceDealJoinReq_806, DealJoinAllianceReq())
    worldMsgDeal.registerMsgDeal(MsgType.SetPowerLimit_807, DealSetAlliancePowerLimit())
    worldMsgDeal.registerMsgDeal(MsgType.AllianceQueryList_808, DealQueryAllianceList())
    worldMsgDeal.registerMsgDeal(MsgType.AllianceQueryInfo_809, DealQueryAllianceInfo())
    worldMsgDeal.registerMsgDeal(MsgType.AllianceQueryReqList_810, DealQueryAllianceReqList())
    worldMsgDeal.registerMsgDeal(MsgType.AllianceRemovePlayer_812, DealRemoveAllianceMember())
    worldMsgDeal.registerMsgDeal(MsgType.AllianceSetDescpt_813, DealSetAllianceDescpt())
    worldMsgDeal.registerMsgDeal(MsgType.AllianceSetPos_814, DealSetAlliancePos())
    worldMsgDeal.registerMsgDeal(MsgType.AllianceQueryPlayer_816, DealQueryAllianceMember())
    worldMsgDeal.registerMsgDeal(MsgType.AllianceDismiss_817, DealAllianceDismiss())
    worldMsgDeal.registerMsgDeal(MsgType.AllianceQueryLog_820, DealQueryAllianceLog())
    worldMsgDeal.registerMsgDeal(MsgType.AllianceSetMark_821, DealSetAllianceMark())
    worldMsgDeal.registerMsgDeal(MsgType.AllianceRemoveMark_823, DealRemoveAllianceMark())
    worldMsgDeal.registerMsgDeal(MsgType.CreateAllianceCheckAllianceName_825, DealCreateAllianceCheckAllianceName())
    worldMsgDeal.registerMsgDeal(MsgType.AllianceRecallPos_835, DealAllianceRecallPos())
    worldMsgDeal.registerMsgDeal(MsgType.QueryApplyAllianceList_838, QueryApplyAllianceList())
    worldMsgDeal.registerMsgDeal(MsgType.AllianceQueryTopic_890, DealAllianceQueryTopic())
    worldMsgDeal.registerMsgDeal(MsgType.AlliancePublishTopic_891, DealAlliancePublishTopic())
    worldMsgDeal.registerMsgDeal(MsgType.AllianceTopicReply_892, DealAllianceTopicReply())
    worldMsgDeal.registerMsgDeal(MsgType.AllianceTopicGetReply_893, DealAllianceTopicGetReply())
    worldMsgDeal.registerMsgDeal(MsgType.AllianceTopicDelete_894, DealAllianceTopicDelete())
    worldMsgDeal.registerMsgDeal(MsgType.AllianceOpenWaijiao_895, AllianceOpenWaijiao())
    worldMsgDeal.registerMsgDeal(MsgType.WriteAllianceWaijiao_896, AllianceWriteWaijiao())
    worldMsgDeal.registerMsgDeal(MsgType.AllianceInvite_900, AllianceInvite())
    worldMsgDeal.registerMsgDeal(MsgType.AllianceGiftOpen_901, OpenAllianceGiftDeal())
    worldMsgDeal.registerMsgDeal(MsgType.AllianceGiftGet_902, GetAllianceGiftDeal())
    worldMsgDeal.registerMsgDeal(MsgType.AllianceGiftRemove_903, RemoveAllianceGiftDeal())
    worldMsgDeal.registerMsgDeal(MsgType.QueryInAllianceRank_915, DealQueryInAllianceRank())
    worldMsgDeal.registerMsgDeal(MsgType.QueryAllianceRank_916, DealQueryAllianceRank())
    worldMsgDeal.registerMsgDeal(MsgType.AllianceTopicSign_917, DealAllianceTopicSign())
    worldMsgDeal.registerMsgDeal(MsgType.QueryOccupyWonder_918, DealQueryOccupyWonder())

    //魔物
    worldMsgDeal.registerMsgDeal(MsgType.SetHunterConfig_1491, SetHunterBossConfigDeal())
    worldMsgDeal.registerMsgDeal(MsgType.QueryHunterRank_1492, QueryHunterBossRankDeal())
    worldMsgDeal.registerMsgDeal(MsgType.InviteTogetherHunter_1493, InviteHunterDeal())
    worldMsgDeal.registerMsgDeal(MsgType.QueryActivityBossInfo_1495, QueryActivityBossInfoDeal())

    // 推图
    worldMsgDeal.registerMsgDeal(MsgType.BeginInstanceFight_1470, BeginInstanceFight())
    worldMsgDeal.registerMsgDeal(MsgType.InstanceFight_1471, InstanceFight())
    worldMsgDeal.registerMsgDeal(MsgType.InstanceWipe_1472, InstanceFWipe())
    worldMsgDeal.registerMsgDeal(MsgType.InstanceGetStarReward_1473, GetInstanceStarReward())

    // 城墙
    worldMsgDeal.registerMsgDeal(MsgType.QueryWallInfo_1540, QueryWallInfo())

    // 奇观争夺战
    worldMsgDeal.registerMsgDeal(MsgType.QueryWonderInfo_1571, QueryWonderInfoDeal())
    worldMsgDeal.registerMsgDeal(MsgType.QueryWonderRank_1572, QueryWonderRankDeal())

    //迷雾
    worldMsgDeal.registerMsgDeal(MsgType.FightWithFogArmy_1573, FightWithFogArmy())
    worldMsgDeal.registerMsgDeal(MsgType.GetFogReward_1574, GetFogReward())
    worldMsgDeal.registerMsgDeal(MsgType.QueryFogArmy_1575, QueryFogArmyDeal())

    // 游戏设置
    worldMsgDeal.registerMsgDeal(MsgType.GetNoticeSetting_1580, GetNoticeSettingDeal())
    worldMsgDeal.registerMsgDeal(MsgType.ChangeNoticeSetting_1581, ChangeNoticeSettingDeal())

    //客户端初始数据
    worldMsgDeal.registerMsgDeal(MsgType.RequireModuleData_2500, RequireModuleDataDeal())
}
