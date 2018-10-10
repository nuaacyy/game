package com.point18.slg2d.public.pmodule

import com.point18.slg2d.public.ppm
import com.point18.slg2d.public.tellMsg.SyncAllianceSimpleInfoTellDeal
import com.point18.slg2d.public.tellMsg.UnLockAllianceNameTellDeal
import pb4server.*

// 注册消息处理
fun registerDeals() {
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.CREATEALLIANCEASKREQ] = CreateAllianceSyncOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.JOINALLIANCEBYIDASKREQ] = JoinAllianceByIdSyncOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.DEALJOINALLIANCEASKREQ] = DealJoinAllianceSyncOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.ALLIANCEINVITEASKREQ] = AllianceInviteOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.OPENALLIANCEGIFTASKREQ] = OpenAllianceGiftOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.ALLIANCERECALLPOSASKREQ] = AllianceRecallPosOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.SETALLIANCEPOWERLIMITASKREQ] = SetAlliancePowerLimitOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.ALLIANCEJOINCANCELASKREQ] = AllianceJoinCancelOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.WRITEALLIANCEWAIJIAOASKREQ] = WriteAllianceWaijiaoOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.OPENALLIANCEWAIJIAOASKREQ] = OpenAllianceWaijiaoOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.ALLIANCETOPICDELETEASKREQ] = AllianceTopicDeleteOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.ALLIANCETOPICGETREPLYASKREQ] = AllianceTopicGetReplyOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.ALLIANCETOPICREPLYASKREQ] = AllianceTopicReplyOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.ALLIANCEPUBLISHTOPICASKREQ] = AlliancePublishTopicOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.ALLIANCEQUERYTOPICASKREQ] = AllianceQueryTopicOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.QUERYALLIANCELOGASKREQ] = QueryAllianceLogSyncOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.SETALLIANCEDESCPTASKREQ] = SetAllianceDescptSyncOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.REMOVEALLIANCEMARKASKREQ] = RemoveAllianceMarkSyncOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.SETALLIANCEMARKASKREQ] = SetAllianceMarkSyncOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.ALLIANCEDISMISSASKREQ] = AllianceDismissSyncOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.RESETALLIANCEPOSASKREQ] = SetAlliancePosOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.REMOVEALLIANCEMEMBERASKREQ] = RemoveAllianceMemberSyncOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.GETALLIANCEGIFTASKREQ] = GetAllianceGiftOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.QUERYALLIANCEMEMBERASKREQ] = QueryAllianceMemberSyncOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.UPDATEALLIANCEMEMBERINFOASKREQ] = UpdateAllianceMemberInfoSyncOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.QUERYALLIANCEREQLISTASKREQ] = QueryAllianceReqListSyncOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.QUERYALLIANCEINFOASKREQ] = QueryAllianceInfoSyncOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.QUERYALLIANCELISTASKREQ] = QueryAllianceListSyncOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.QUERYINALLIANCERANKREQ] = QueryInAllianceRankOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.ALLIANCETOPICSIGNASKREQ] = AllianceTopicSignOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.ALLIANCEACTIVITYSCOREADDASKREQ] = AllianceActivityScoreAddOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.SELETEALLIANCEACTIVITYINFOSREQ] = SeleteAllianceActivityInfosOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.SELECTACTIVITYHISTORYREQ] = SelectActivityHistoryOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.OPENACTIVITYASKREQ] = OpenActivityOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.SELECTACTIVITYRANKASKREQ] = SelectActivityRankOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.SELECTNOWRANKREQ] = SelectNowRankOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.RECEIVEALLIANCEGIFTASKREQ] = ReceiveAllianceGiftOpByWorld()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.SENDALLIANCEMAILASKREQ] = SendAllianceMailSyncOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.SENDALLIANCEAWARDMAILASKREQ] = SendAllianceAwardMailOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.SENDALLIANCEMASSASKREQ] = SendAllianceMassInfoOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.RELOADPUBLICCONFIGASKREQ] = ReloadConfigOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.GMOVERALLIANCEACTIVITYASKREQ] = GmOverAllianceActivityOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.SENDWONDERAWARDASKREQ] = SendWonderAwardOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.QUERYWONDERAWARDRECORDASKREQ] = QueryWonderAwardRecordOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.CLEARWONDERAWARDRECORDASKREQ] = ClearWonderAwardRecordOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.UPDATEWONDERINFOASKREQ] = UpdateWonderInfoSyncOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.QUERYOCCUPYWONDERASKREQ] = QueryOccupyWonderSyncOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.SETPUBLICMOVESERVERSTATEREQ] = SetPublicMoveServerStateOp()
    ppm.worldMsgDeal[World2PublicAskReq.MsgCase.QUERYAPPLYALLIANCELISTASKREQ] = QueryApplyAllianceListSyncOp()

    // world -> publicManager
    ppm.worldMsgManagerDeal[World2PublicManagerAskReq.MsgCase.CREATEALLIANCEASKREQ] = CheckAllianceNameOp()

    // worldManager -> publicManager
    ppm.worldManagerMsgManagerDeal[WorldManager2PublicManagerAskReq.MsgCase.FINDUSEALLIANCENAMESREQ] =
            FindAllianceInfoOp()

    // home -> publicManager
    ppm.homeMsgManagerDeal[Home2PublicManagerAskReq.MsgCase.SETALLIANCENAMEASKREQ] = SetAllianceNameCheckOp()
    ppm.homeMsgManagerDeal[Home2PublicManagerAskReq.MsgCase.OPENAFTERALLIANCECOMPETITIONASKREQ] =
            OpenAfterAllianceCompetitionOp()

    // world -> tell -> publicManager
    ppm.publicTellPublicManagerMsgDeal[Public2PublicManagerTell.MsgCase.UNLOCKALLIANCENAMETELL] =
            UnLockAllianceNameTellDeal()

    ppm.publicTellPublicManagerMsgDeal[Public2PublicManagerTell.MsgCase.SYNCALLIANCESIMPLEINFOTELL] =
            SyncAllianceSimpleInfoTellDeal()

    // =======================HOME
    ppm.homeMsgDeal[Home2PublicAskReq.MsgCase.SETALLIANCEFLAGASKREQ] = SetAllianceFlagOp()
    ppm.homeMsgDeal[Home2PublicAskReq.MsgCase.REMOVEALLIANCEHELPASKREQ] = RemoveAllianceHelpOp()
    ppm.homeMsgDeal[Home2PublicAskReq.MsgCase.ALLIANCEIMPEACHASKREQ] = AllianceImpeachOp()
    ppm.homeMsgDeal[Home2PublicAskReq.MsgCase.ALLIANCEMEMBERQUITASKREQ] = AllianceMemberQuitSyncOp()
    ppm.homeMsgDeal[Home2PublicAskReq.MsgCase.SETALLIANCENAMEASKREQ] = SetAllianceNameOp()
    ppm.homeMsgDeal[Home2PublicAskReq.MsgCase.SENDALLIANCEHELPASKREQ] = SendAllianceHelpOp()
    ppm.homeMsgDeal[Home2PublicAskReq.MsgCase.GOALLIANCEHELPASKREQ] = GoAllianceHelpOp()
    ppm.homeMsgDeal[Home2PublicAskReq.MsgCase.OPENALLIANCEHELPASKREQ] = OpenAllianceHelpOp()
    ppm.homeMsgDeal[Home2PublicAskReq.MsgCase.OPENALLIANCECOMPETITIONMAINASKREQ] = OpenAllianceCompetitionMainOp()
    ppm.homeMsgDeal[Home2PublicAskReq.MsgCase.GETALLIANCECOMPETITIONQUESTASKREQ] = GetAllianceCompetitionQuestOp()
    ppm.homeMsgDeal[Home2PublicAskReq.MsgCase.REWARDALLIANCECOMPETITIONQUESTASKREQ] = RewardAllianceCompetitionQuestOp()
    ppm.homeMsgDeal[Home2PublicAskReq.MsgCase.CANCELALLIANCECOMPETITIONQUESTASKREQ] = CancelAllianceCompetitionQuestOp()
    ppm.homeMsgDeal[Home2PublicAskReq.MsgCase.GETALLIANCECOMPETITIONREWARDASKREQ] = GetAllianceCompetitionRewardOp()
    ppm.homeMsgDeal[Home2PublicAskReq.MsgCase.GETCASINOASKREQ] = CasinoInfoOp()
    ppm.homeMsgDeal[Home2PublicAskReq.MsgCase.DRAWCASINOASKREQ] = CasinoGetRewardOp()
    ppm.homeMsgDeal[Home2PublicAskReq.MsgCase.UPDATEALLIANCEMEMBERINFOINHOMEASKREQ] =
            UpdateAllianceMemberInfoInHomeSyncOp()
    ppm.homeMsgDeal[Home2PublicAskReq.MsgCase.ROOMOWNERCHANGEASKREQ] = RoomAppointOwnerOp()
    ppm.homeMsgDeal[Home2PublicAskReq.MsgCase.CREATEROOMASKREQ] = CreatChatRoomOp()
    ppm.homeMsgDeal[Home2PublicAskReq.MsgCase.DISSMISSCHATROOMASKREQ] = DismissChatGroupOp()
    ppm.homeMsgDeal[Home2PublicAskReq.MsgCase.GETALLIANCECHATASKREQ] = GetAllianceChatHistoryOp()
    ppm.homeMsgDeal[Home2PublicAskReq.MsgCase.GETGROUPCHATASKREQ] = GetChatRoomHistoryOp()
    ppm.homeMsgDeal[Home2PublicAskReq.MsgCase.KICKOUTCHATROOMASKREQ] = KickOutChatMemberOp()
    ppm.homeMsgDeal[Home2PublicAskReq.MsgCase.QUERYMEMBERINROOMASKREQ] = QueryMemberChatDeal()
    ppm.homeMsgDeal[Home2PublicAskReq.MsgCase.QUITONEROOMASKREQ] = MemberQuitRoomOp()
    ppm.homeMsgDeal[Home2PublicAskReq.MsgCase.RENAMECHATROOMASKREQ] = RenameChatRoomOp()
    ppm.homeMsgDeal[Home2PublicAskReq.MsgCase.SENDALLIANCECHATASKREQ] = SendAllianceChatMsgOp()
    ppm.homeMsgDeal[Home2PublicAskReq.MsgCase.SENDROOMMSGASKREQ] = SendChatRoomMsgOp()
    ppm.homeMsgDeal[Home2PublicAskReq.MsgCase.GETCHATROOMASKREQ] = ReqChatRoomInfoDeal()
    ppm.homeMsgDeal[Home2PublicAskReq.MsgCase.INVITEJOINCHATASKREQ] = InviteJoinChatDeal()
    ppm.homeMsgDeal[Home2PublicAskReq.MsgCase.RECEIVEALLIANCEGIFTASKREQ] = ReceiveAllianceGiftOpByHome()
    ppm.homeMsgDeal[Home2PublicAskReq.MsgCase.REMOVEALLIANCEWAIJIAOREQ] = RemoveAllianceWaijiaoOp()

    ppm.publicManagerTellDeal[PublicManager2PublicTell.MsgCase.ALLIANCECOMPETITIONOPENTELL] =
            AllianceCompetitionOpenTellDeal()


}
