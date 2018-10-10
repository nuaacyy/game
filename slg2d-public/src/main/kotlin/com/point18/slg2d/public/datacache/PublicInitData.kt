package com.point18.slg2d.public.datacache

import com.point18.slg2d.common.publicentities.*

class PublicInitData(val publicId: Long) {

    lateinit var allianceActivities: List<AllianceActivityEntity>
    lateinit var everyAllianceActivities: List<EveryAllianceActivityEntity>
    lateinit var allianceActivityRanks: List<AllianceActivityRankEntity>
    lateinit var allianceChats: List<AllianceChatEntity>
    lateinit var allianceCompetitionGroups: List<AllianceCompetitionGroupEntity>
    lateinit var allianceCompetitionQuests: List<AllianceCompetitionQuestEntity>
    lateinit var allianceGifts: List<AllianceGiftEntity>
    lateinit var allianceHelps: List<AllianceHelpEntity>
    lateinit var allianceLogs: List<AllianceLogEntity>
    lateinit var allianceMarks: List<AllianceMarkEntity>
    lateinit var allianceMembers: List<AllianceMemberEntity>
    lateinit var allianceMemberTraces: List<AllianceMemberTraceEntity>
    lateinit var allianceReplies: List<AllianceReplyEntity>
    lateinit var allianceReqs: List<AllianceReqEntity>
    lateinit var alliances: List<AllianceEntity>
    lateinit var allianceTopics: List<AllianceTopicEntity>
    lateinit var allianceWaijiaos: List<AllianceWaijiaoEntity>
    lateinit var publicActivities: List<PublicActivityEntity>
    lateinit var chatRooms: List<ChatRoomEntity>
    lateinit var roomChatRecords: List<RoomChatRecordEntity>
    lateinit var casinosWinners: List<CasinosWinnerEntity>
    lateinit var jackpot: List<JackpotEntity>

}