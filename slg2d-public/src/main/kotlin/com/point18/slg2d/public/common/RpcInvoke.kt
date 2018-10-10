package com.point18.slg2d.public.common

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.public.actor.PublicActor
import com.point18.slg2d.public.ppm
import pb4client.OccupyWonder
import pb4server.*
import java.util.*

// 设置联盟标记之后的推送
fun dealAfterSetAllianceMark(publicActor: PublicActor, worldId: Long, allianceMarkInfoVo: AllianceMarkInfoVo, flag: Int, allianceId: Long) {
    val msgBuilder = DealAfterSetAllianceMarkTell.newBuilder()
    msgBuilder.allianceMarkInfoVo = allianceMarkInfoVo
    msgBuilder.flag = flag
    msgBuilder.allianceId = allianceId

    val public2worldTell = ppm.fillPublic2WorldAskMsgHeader(
        worldId,
        0L
    ) { it.setDealAfterSetAllianceMarkTell(msgBuilder) }
    ppm.tell2World(publicActor, public2worldTell)
}

// 通知玩家已经进入帮派
fun joinInAllianceSuccess(
    publicActor: PublicActor,
    worldId: Long, playerId: Long, allianceId: Long, allianceName: String, allianceShortName: String,
    flagColor: Int, flagStyle: Int, flagEffect: Int,
    members: LinkedList<MemberPlayerInfoVo>, marks: LinkedList<AllianceMarkInfoVo>
) {
    val msgBuilder = JoinInAllianceSuccessTell.newBuilder()
    msgBuilder.allianceId = allianceId
    msgBuilder.allianceName = allianceName
    msgBuilder.allianceShortName = allianceShortName
    msgBuilder.flagColor = flagColor
    msgBuilder.flagStyle = flagStyle
    msgBuilder.flagEffect = flagEffect
    msgBuilder.addAllMembers(members)
    msgBuilder.addAllMarks(marks)

    val public2worldTell = ppm.fillPublic2WorldAskMsgHeader(
        worldId,
        playerId
    ) { it.setJoinInAllianceSuccessTell(msgBuilder) }
    ppm.tell2World(publicActor, public2worldTell)
}

// 通知玩家被联盟踢了
fun kickAllianceMemberSuccess(publicActor: PublicActor, worldId: Long, playerId: Long, allianceId: Long, playerName: String) {
    val msgBuilder = KickAllianceMemberSuccessTell.newBuilder()
    msgBuilder.allianceId = allianceId
    msgBuilder.playerName = playerName

    val public2worldTell = ppm.fillPublic2WorldAskMsgHeader(
        worldId,
        playerId
    ) { it.setKickAllianceMemberSuccessTell(msgBuilder) }
    ppm.tell2World(publicActor, public2worldTell)
}

// 通知这个玩家被设置了新职位
fun getNewAlliancePos(
    publicActor: PublicActor,
    worldId: Long,
    playerId: Long,
    allianceId: Long,
    pos: String,
    allianceName: String,
    allianceShortName: String
) {
    val msgBuilder = GetNewAlliancePosTell.newBuilder()
    msgBuilder.allianceId = allianceId
    msgBuilder.allianceName = allianceName
    msgBuilder.allianceShortName = allianceShortName
    msgBuilder.nowPos = pos

    val public2worldTell = ppm.fillPublic2WorldAskMsgHeader(
        worldId,
        playerId
    ) { it.setGetNewAlliancePosTell(msgBuilder) }
    ppm.tell2World(publicActor, public2worldTell)
}

// 帮派职位变化通知给所有帮众
fun posChangeNoticAllAlliance(
    publicActor: PublicActor,
    worldId: Long,
    allianceId: Long,
    pos: Int,
    playerName: String,
    getPosPlayerName: String,
    changeType: Int,
    nowPos: LinkedList<Int>,
    setPlayerId: Long,
    isOnline: Int,
    photoProtoId: Int
) {
    val msgBuilder = PosChangeNoticAllAllianceTell.newBuilder()
    msgBuilder.pos = pos
    msgBuilder.playerName = playerName
    msgBuilder.getPosPlayerName = getPosPlayerName
    msgBuilder.changeType = changeType
    msgBuilder.addAllPositions(nowPos)
    msgBuilder.setPlayerId = setPlayerId
    msgBuilder.isOnline = isOnline
    msgBuilder.photoProtoId = photoProtoId
    msgBuilder.allianceId = allianceId

    val public2worldTell = ppm.fillPublic2WorldAskMsgHeader(
        worldId,
        0L
    ) { it.setPosChangeNoticAllAllianceTell(msgBuilder) }
    ppm.tell2World(publicActor, public2worldTell)
}

// 修改联盟名称之后的推送
fun dealAfterSetAllianceName(publicActor: PublicActor, worldId: Long, allianceId: Long, setType: Int, name: String) {
    val msgBuilder = DealAfterSetAllianceNameTell.newBuilder()
    msgBuilder.allianceId = allianceId
    msgBuilder.setType = setType
    msgBuilder.name = name

    val public2worldTell = ppm.fillPublic2WorldAskMsgHeader(
        worldId,
        0L
    ) { it.setDealAfterSetAllianceNameTell(msgBuilder) }
    ppm.tell2World(publicActor, public2worldTell)
}

// 修改联盟旗帜之后的推送
fun dealAfterSetAllianceFlag(publicActor: PublicActor, worldId: Long, allianceId: Long, color: Int, style: Int, effect: Int) {
    val msgBuilder = DealAfterSetAllianceFlagTell.newBuilder()
    msgBuilder.allianceId = allianceId
    msgBuilder.color = color
    msgBuilder.style = style
    msgBuilder.effect = effect
    val public2worldTell = ppm.fillPublic2WorldAskMsgHeader(
        worldId,
        0L
    ) { it.setDealAfterSetAllianceFlagTell(msgBuilder) }
    ppm.tell2World(publicActor, public2worldTell)
}

// 发布联盟邮件主题之后的推送
fun dealAfterAlliancePublishTopic(publicActor: PublicActor, worldId: Long, allianceId: Long, aTopicId: Long) {
    val msgBuilder = DealAfterAlliancePublishTopicTell.newBuilder()
    msgBuilder.allianceId = allianceId
    msgBuilder.aTopicId = aTopicId

    val public2worldTell = ppm.fillPublic2WorldAskMsgHeader(
        worldId,
        0L
    ) { it.setDealAfterAlliancePublishTopicTell(msgBuilder) }
    ppm.tell2World(publicActor, public2worldTell)
}

// 写联盟外交之后的推送
fun dealAfterWriteAllianceWaijiao(publicActor: PublicActor, worldId: Long, redPointType: Int, allianceId: Long) {
    val msgBuilder = DealAfterWriteAllianceWaijiaoTell.newBuilder()
    msgBuilder.redPointType = (redPointType)
    msgBuilder.allianceId = (allianceId)
    msgBuilder.nowSec = getNowTime()

    val public2worldTell = ppm.fillPublic2WorldAskMsgHeader(
        worldId,
        0L
    ) { it.setDealAfterWriteAllianceWaijiaoTell(msgBuilder) }
    ppm.tell2World(publicActor, public2worldTell)
}

// 联盟服通知游戏服有联盟申请信息变化
fun allianceReqInfoChange(
    publicActor: PublicActor,
    worldId: Long,
    playerIds: LinkedList<Long>,
    changeInfo: Int,
    req: AllianceQueryReqListInfoVo
) {

    val msgBuilder = AllianceReqInfoChangeNotic2GTell.newBuilder()
    msgBuilder.addAllPlayers(playerIds)
    msgBuilder.changeInfo = changeInfo
    msgBuilder.allianceQueryReqListInfo = req

    val public2worldTell = ppm.fillPublic2WorldAskMsgHeader(
        worldId,
        0L
    ) { it.setAllianceReqInfoChangeNotic2GTell(msgBuilder) }
    ppm.tell2World(publicActor, public2worldTell)
}

// 联盟服通知游戏服有联盟帮助信息变化
fun allianceHelpInfoChange(publicActor: PublicActor, worldId: Long, playerIds: LinkedList<Long>, changeInfo: Int, helpIds: LinkedList<Long>) {
    val msgBuilder = AllianceHelpInfoChangeNotic2GTell.newBuilder()
    msgBuilder.addAllPlayerIds(playerIds)
    msgBuilder.changeInfo = (changeInfo)
    msgBuilder.addAllHelpId(helpIds)

    val public2worldTell = ppm.fillPublic2WorldAskMsgHeader(
        worldId,
        0L
    ) { it.setAllianceHelpInfoChangeNotic2GTell(msgBuilder) }
    ppm.tell2World(publicActor, public2worldTell)
}

// 被帮助之后的推送(推送的是数据的变化)
fun dealAfterHelp(
    publicActor: PublicActor,
    worldId: Long,
    playerId: Long,
    helpType: Int,
    helpValue1: Long,
    helpValue2: Long,
    helpValue3: Long,
    helpValue4: Long,
    helpPlayerId: Long
) {
    val msgBuilder = DealAfterHelpTell.newBuilder()
    msgBuilder.helpType = helpType
    msgBuilder.helpValue1 = helpValue1
    msgBuilder.helpValue2 = helpValue2
    msgBuilder.helpValue3 = helpValue3
    msgBuilder.helpValue4 = helpValue4
    msgBuilder.helpPlayerId = helpPlayerId

    val public2HomeTell = ppm.fillPublic2HomeTellMsgHeader(
        playerId
    ) { it.setDealAfterHelpTell(msgBuilder) }
    ppm.tell2Home(publicActor, public2HomeTell)
}

// 被帮助之后的推送(推送的是数据的变化)
fun dealHelperNotice(publicActor: PublicActor, worldId: Long, playerId: Long, playerName: String, helpType: Int) {

    val msgBuilder = DealHelperNoticeTell.newBuilder()
    msgBuilder.playerName = playerName
    msgBuilder.helpType = helpType

    val public2worldTell = ppm.fillPublic2WorldAskMsgHeader(
        worldId,
        playerId
    ) { it.setDealHelperNoticeTell(msgBuilder) }
    ppm.tell2World(publicActor, public2worldTell)
}

// 联盟成员发生变化
fun allianceMemberInfoChange(publicActor: PublicActor,
                             worldId: Long, allianceId: Long,
                             changeType: com.point18.slg2d.common.constg.AllianceMemberFlag, changeplayerId: Long, playerName: String,
                             nowPos: LinkedList<Int>, isOnline: Int, photoProtoId: Int
) {

    val msgBuilder = AllianceMemberInfoChangeTell.newBuilder()
    msgBuilder.allianceId = (allianceId)
    msgBuilder.changePlayerId = (changeplayerId)
    msgBuilder.playerName = playerName
    msgBuilder.changeType = (changeType)
    msgBuilder.addAllPositions(nowPos)
    msgBuilder.isOnline = (isOnline)
    msgBuilder.protoId = photoProtoId

    val public2worldTell = ppm.fillPublic2WorldAskMsgHeader(
        worldId,
        0L
    ) { it.setAllianceMemberInfoChangeTell(msgBuilder) }
    ppm.tell2World(publicActor, public2worldTell)
}

// 玩家上线所需的公共服数据
fun playerOnlineNotic(publicActor: PublicActor, worldId: Long, playerId: Long, rt: EnterGamePublicRtVo) {
    val msgBuilder = PlayerOnlineNoticTell.newBuilder()
    msgBuilder.enterGamePublicRt = rt

    val public2worldTell = ppm.fillPublic2WorldAskMsgHeader(
        worldId,
        playerId
    ) { it.setPlayerOnlineNoticTell(msgBuilder) }
    ppm.tell2World(publicActor, public2worldTell)
}

// 联盟解散通知给所有玩家游戏服
fun allianceDismissNotic2G(publicActor: PublicActor, worldId: Long, allianceId: Long) {
    val msgBuilder = AllianceDismissNotic2GTell.newBuilder()
    msgBuilder.allianceId = (allianceId)

    val public2worldTell = ppm.fillPublic2WorldAskMsgHeader(
        worldId,
        0L
    ) { it.setAllianceDismissNotic2GTell(msgBuilder) }
    ppm.tell2World(publicActor, public2worldTell)
}

// 公共服发起邮件给玩家
fun sendMailToPlayer(
    publicActor: PublicActor,
    worldId: Long,
    playerIds: LinkedList<Long>,
    readType: Int,
    title: String,
    titleParam: LinkedList<String>,
    message: String,
    messageParam: LinkedList<String>,
    action: Int,
    mailType: Int,
    attach: String,
    sysId: Long,
    extend1: String,
    sendPlayerId: Long = 0,
    sendPlayerName: String = "",
    sendPlayerNickName: String = "",
    sendAllianceId: Long = 0,
    sendAllianceName: String = "",
    sendAllianceShortName: String = ""
) {
    val msgBuilder = SendMailToPlayerNotic2GTell.newBuilder()
    msgBuilder.addAllPlayerIds(playerIds)
    msgBuilder.sendPlayerId = sendPlayerId
    msgBuilder.sendPlayerName = sendPlayerName
    msgBuilder.sendPlayerNickName = sendPlayerNickName
    msgBuilder.sendAllianceId = sendAllianceId
    msgBuilder.sendAllianceName = sendAllianceName
    msgBuilder.sendAllianceShortName = sendAllianceShortName
    msgBuilder.readType = (readType)
    msgBuilder.title = title
    msgBuilder.addAllTitleParam(titleParam)
    msgBuilder.message = message
    msgBuilder.addAllMessageParam(messageParam)
    msgBuilder.action = ((action))
    msgBuilder.mailType = (mailType)
    msgBuilder.attach = attach
    msgBuilder.sysId = (sysId)
    msgBuilder.extend1 = extend1

    val public2worldTell = ppm.fillPublic2WorldAskMsgHeader(
        worldId,
        0L
    ) { it.setSendMailToPlayerNotic2GTell(msgBuilder) }
    ppm.tell2World(publicActor, public2worldTell)
}

// 推送给盟友联盟挑战信息发生变化
fun allianceActivityScoreChange(
    publicActor: PublicActor,
    worldId: Long,
    playerIds: LinkedList<Long>,
    activityId: Int,
    score: Int,
    rank: Int,
    isActivityOver: Boolean
) {
    val msg = AllianceActivityChangeNotic2GTell.newBuilder()
    msg.activityId = activityId
    msg.score = score
    msg.rank = rank

    var ias = 0
    if (isActivityOver) {
        ias = 1
    }
    msg.isActivityOver = ias

    val public2worldTell = ppm.fillPublic2WorldAskMsgHeader(
        worldId,
        0L
    ) { it.setAllianceActivityChangeNotic2GTell(msg) }
    ppm.tell2World(publicActor, public2worldTell)
}

// 推送给盟友联盟活跃度信息发生变化
fun allianceMissionGiftAdd(publicActor: PublicActor, worldId: Long, allianceId: Long, addNumMap: MutableMap<Int, Int>) {
    val msgBuilder = AllianceMissionGiftAddNotic2GTell.newBuilder()
    msgBuilder.allianceId = allianceId
    for ((scoreId, num) in addNumMap) {
        val tmp = AllianceMissionGiftAddVo.newBuilder()
        tmp.addNum = num
        tmp.scoreId = scoreId
        msgBuilder.addAllianceMissionGiftAddVos(tmp)
    }

    val public2worldTell = ppm.fillPublic2WorldAskMsgHeader(
        worldId,
        0L
    ) { it.setAllianceMissionGiftAddNotic2GTell(msgBuilder) }
    ppm.tell2World(publicActor, public2worldTell)
}

// 通知玩家联盟总动员数据变化
fun allianceCompetitionInfoChange(
    publicActor: PublicActor,
    worldId: Long, playerIds: LinkedList<Long>, isRefReward: Int, allianceRankLv: Int,
    allianceCompetitionId: Long, allianceCompetitionTicket: Int,
    allianceCompetitionNowTaskId: Int, allianceCompetitionNowTaskState: Int,
    allianceCompetitionNowTaskOverTime: Int,
    allianceCompetitionGetTaskNum: Int, allianceCompetitionBuyTaskNum: Int
) {
    val tmp = AllianceCompetitionInfoVoTell.newBuilder()
    tmp.allianceCompetitionId = allianceCompetitionId
    tmp.allianceCompetitionTicket = allianceCompetitionTicket
    tmp.allianceCompetitionNowTaskId = allianceCompetitionNowTaskId
    tmp.allianceCompetitionNowTaskState = allianceCompetitionNowTaskState
    tmp.allianceCompetitionNowTaskOverTime = allianceCompetitionNowTaskOverTime
    tmp.allianceCompetitionGetTaskNum = allianceCompetitionGetTaskNum
    tmp.allianceCompetitionBuyTaskNum = allianceCompetitionBuyTaskNum
    tmp.allianceCompetitionRankLv = allianceRankLv
    tmp.allianceCompetitionNowTaskValue = 0

    for (playerId in playerIds) {
        val msg = AllianceCompetitionInfoChangeNotic2GTell.newBuilder()
        msg.isRefReward = (isRefReward)
        msg.acInfo = tmp.build()
        val public2worldTell = ppm.fillPublic2HomeTellMsgHeader(
            playerId
        ) { it.setAllianceCompetitionInfoChangeNotic2GTell(msg) }
        ppm.tell2Home(publicActor, public2worldTell)
    }
}

// 通知玩家联盟总动员结束
fun allianceCompetitionOver(publicActor: PublicActor, playerId: Long, rankLv: Int, rank: Int) {
    val msg = AllianceCompetitionOverNotic2GTell.newBuilder()
    msg.rank = rank
    msg.rankLv = rankLv

    val public2worldTell = ppm.fillPublic2HomeTellMsgHeader(
        playerId
    ) { it.setAllianceCompetitionOverNotic2GTell(msg) }
    ppm.tell2Home(publicActor, public2worldTell)
}

// 通知世界服重置奇观
fun noticeCleanWonder2World(publicActor: PublicActor, worldId: Long, wonderIds: List<Int>) {
    val msgBuilder = CleanWonder2WorldTell.newBuilder()
    msgBuilder.addAllWonderProtoId(wonderIds)

    val tell = ppm.fillPublic2WorldAskMsgHeader(
        worldId,
        0L
    ) { it.setCleanWonderTell(msgBuilder) }
    ppm.tell2World(publicActor, tell)
}

// 通知世界服国王改变
fun noticeChangeKing2World(publicActor: PublicActor, worldId: Long, newKingId: Long) {
    val msgBuilder = ResetKing2WorldTell.newBuilder()
    msgBuilder.newKingId = newKingId

    val tell = ppm.fillPublic2WorldAskMsgHeader(
        worldId,
        0L
    ) { it.setChangeKingTell(msgBuilder) }
    ppm.tell2World(publicActor, tell)
}

// 通知世界服联盟玩家占领/失去奇观
fun noticeOccupyWonder2World(
    publicActor: PublicActor, worldId: Long, playerIds: List<Long>,
    changeType: Int, occupyInfo: Map<Long, Map<Int, Int>>
) {
    val msgBuilder = OccupyWonder2WorldTell.newBuilder()
    msgBuilder.addAllPlayerIds(playerIds)
    msgBuilder.changeType = changeType

    for ((belongWorldId, wonderMap) in occupyInfo) {
        val occupyWonder = OccupyWonder.newBuilder()
        occupyWonder.worldId = belongWorldId
        for ((wonderProtoId, _) in wonderMap) {
            occupyWonder.addWonderIds(wonderProtoId)
        }
        msgBuilder.addOccupyWonderInfo(occupyWonder)
    }

    val tell = ppm.fillPublic2WorldAskMsgHeader(
        worldId,
        0L
    ) { it.setOccupyWonderTell(msgBuilder) }
    ppm.tell2World(publicActor, tell)
}

fun noticeOccupyWonder2Home(
    publicActor: PublicActor, playerId: Long,
    changeType: Int, occupyInfo: Map<Long, Map<Int, Int>>
) {
    val msgBuilder = OccupyWonder2HomeTell.newBuilder()
    msgBuilder.playerId = playerId
    msgBuilder.changeType = changeType

    for ((belongWorldId, wonderMap) in occupyInfo) {
        val occupyWonder = OccupyWonder.newBuilder()
        occupyWonder.worldId = belongWorldId
        for ((wonderProtoId, _) in wonderMap) {
            occupyWonder.addWonderIds(wonderProtoId)
        }
        msgBuilder.addOccupyWonderInfo(occupyWonder)
    }

    val tell = ppm.fillPublic2HomeTellMsgHeader(
        playerId
    ) { it.setOccupyWonderTell(msgBuilder) }
    ppm.tell2Home(publicActor, tell)
}

//通知收到奇观战奖励
fun receiveWonderAward(publicActor: PublicActor, worldId: Long, playerId: Long, awardId: Int) {
    val msgBuilder = ReceiveWonderAwardNotic2GTell.newBuilder()
    msgBuilder.awardId = awardId

    val public2worldTell = ppm.fillPublic2WorldAskMsgHeader(
        worldId,
        playerId
    ) { it.setReceiveWonderAwardNotic2GTell(msgBuilder) }
    ppm.tell2World(publicActor, public2worldTell)
}

// 给全盟的玩家发送一个礼物
fun sendAllianceGift(publicActor: PublicActor, worldId: Long, playerId: Long, overTime: Long, giftId: Int, giftInfo: String, extend1: String) {
    val msg = SendAllianceGiftNotic2GTell.newBuilder()
    msg.overTime = overTime
    msg.giftId = giftId
    msg.giftInfo = giftInfo
    msg.extend1 = extend1

    val public2worldTell = ppm.fillPublic2WorldAskMsgHeader(
        worldId,
        playerId
    ) { it.setSendAllianceGiftNotic2GTell(msg) }
    ppm.tell2World(publicActor, public2worldTell)
}

// 联盟大礼物数据发生变化
fun allianceGiftChange(publicActor: PublicActor, worldId: Long, playerId: Long, bigGiftId: Int, bigGiftExp: Int, giftLv: Int, giftExp: Int) {
    val msgBuilder = AllianceGiftChangeNotic2GTell.newBuilder()
    val allianceBigGiftVo = AllianceBigGiftVo.newBuilder()
    allianceBigGiftVo.bigGiftId = bigGiftId
    allianceBigGiftVo.bigGiftExp = bigGiftExp
    allianceBigGiftVo.giftLv = giftLv
    allianceBigGiftVo.giftExp = giftExp
    msgBuilder.allianceBigGiftVo = allianceBigGiftVo.build()

    val public2worldTell = ppm.fillPublic2WorldAskMsgHeader(
        worldId,
        playerId
    ) { it.setAllianceGiftChangeNotic2GTell(msgBuilder) }
    ppm.tell2World(publicActor, public2worldTell)
}

//猎杀邀请变更
fun hunterInviteChange(
    publicActor: PublicActor,
    addOrDel: Int,
    worldId: Long,
    posX: Int,
    posY: Int,
    inviteId: Long,
    allianceId: Long,
    bossId: Int,
    nowHp: Int,
    atkRecordMap: HashMap<Long, Int>
) {

    val msgBuilder = SendHunterInviteChangeNotice2GTell.newBuilder()
    msgBuilder.addOrDel = addOrDel
    msgBuilder.posX = posX
    msgBuilder.posY = posY
    msgBuilder.inviteId = inviteId
    msgBuilder.allianceId = allianceId
    msgBuilder.bossId = bossId
    msgBuilder.nowHp = nowHp
    for ((rPlayerId, damage) in atkRecordMap) {
        val atkRecordVo = AtkRecordVo.newBuilder()
        atkRecordVo.playerId = rPlayerId
        atkRecordVo.damage = damage
        msgBuilder.addAtkRecord(atkRecordVo)
    }

    val public2worldTell = ppm.fillPublic2WorldAskMsgHeader(
        worldId,
        0L
    ) { it.setSendHunterInviteChangeNotice2GTell(msgBuilder) }
    ppm.tell2World(publicActor, public2worldTell)
}
