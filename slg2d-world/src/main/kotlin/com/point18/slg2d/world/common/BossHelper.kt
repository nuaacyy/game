package com.point18.slg2d.world.common

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.world.area4data.*
import com.point18.slg2d.world.module.boss.removeWaitFightGroup
import com.point18.slg2d.world.module.walk.walkComm.halfWayGoHome
import com.point18.slg2d.world.msgnotice.createHunterRecordNotifier
import com.point18.slg2d.world.msgnotice.newHunterInviteNotifier
import pb4client.HunterInviteInfo
import java.util.*

//更新魔物所有狩猎邀请
fun updateBossInvite(areaCache: AreaCache, bossInfo: IBossInfo, addOrDel: Int = Add) {
    var realAddOrDel = addOrDel
    if (bossInfo.getCurrentHp() <= 0) {
        realAddOrDel = Del
    }
    val invites = areaCache.bossInviteCache.findInvitesByXy(bossInfo.getPosX(), bossInfo.getPosY())
    if (invites.isEmpty()) {
        return
    }
    val atkRecordSet = bossInfo.getAtkRecordSet()
    for (invite in invites) {
        if (realAddOrDel == Del) {
            delBossInvite(areaCache, invite)
            continue
        }

        val allMembers = areaCache.playerCache.findPlayersByAllianceId(invite.allianceId)
        val allAtkNum = allMembers.count {
            atkRecordSet.contains(it.id)
        }
        for (member in allMembers) {
            val selfInAtk = boolToInt(atkRecordSet.contains(member.id))

            val session = fetchOnlinePlayerSession(areaCache, member.id)
            if (session != null) {
                val hunterChangeNotice = newHunterInviteNotifier(
                    realAddOrDel,
                    invite.id,
                    invite.latestInviteTime,
                    invite.worldId,
                    invite.x,
                    invite.y,
                    bossInfo.getBossProtoId(),
                    bossInfo.getCurrentHp(),
                    allAtkNum - selfInAtk
                )
                hunterChangeNotice.notice(session)
            }
        }
    }
}

//更新固定狩猎邀请
fun updateBossInvite(
    areaCache: AreaCache,
    invite: BossInvite,
    bossInfo: IBossInfo,
    addOrDel: Int = Add
) {
    var realAddOrDel = addOrDel
    if (bossInfo.getCurrentHp() <= 0) {
        realAddOrDel = Del
    }
    if (realAddOrDel == Del) {
        delBossInvite(areaCache, invite)
        return
    }

    val atkRecordSet = bossInfo.getAtkRecordSet()
    val allMembers = areaCache.playerCache.findPlayersByAllianceId(invite.allianceId)
    val allAtkNum = allMembers.count {
        atkRecordSet.contains(it.id)
    }
    for (member in allMembers) {
        val selfInAtk = boolToInt(atkRecordSet.contains(member.id))

        val session = fetchOnlinePlayerSession(areaCache, member.id)
        if (session != null) {
            val hunterChangeNotice = newHunterInviteNotifier(
                realAddOrDel,
                invite.id,
                invite.latestInviteTime,
                invite.worldId,
                invite.x,
                invite.y,
                bossInfo.getBossProtoId(),
                bossInfo.getCurrentHp(),
                allAtkNum - selfInAtk
            )
            hunterChangeNotice.notice(session)
        }
    }
}

fun delBossInvite(areaCache: AreaCache, invite: BossInvite) {
    areaCache.bossInviteCache.deleteBossInvite(invite)

    val allMembers = areaCache.playerCache.findPlayersByAllianceId(invite.allianceId)

    for (member in allMembers) {
        val session = fetchOnlinePlayerSession(areaCache, member.id)
        if (session != null) {
            val hunterChangeNotice = newHunterInviteNotifier(
                Del,
                invite.id,
                invite.latestInviteTime,
                invite.worldId,
                invite.x,
                invite.y,
                0,
                0,
                0
            )
            hunterChangeNotice.notice(session)
        }
    }
}

//获取联盟邀请信息
fun getAllianceInvites(areaCache: AreaCache, player: Player): List<HunterInviteInfo.Builder> {
    val allianceInvites = LinkedList<HunterInviteInfo.Builder>()

    val invites = areaCache.bossInviteCache.findInviteByAllianceId(player.allianceId)
    for (invite in invites) {
        val bossInfo: IBossInfo = areaCache.commonBossCache.findCommonBossByXY(invite.x, invite.y)
            ?: areaCache.callBossCache.findCallBossByXy(invite.x, invite.y)
            ?: areaCache.activityBossCache.findActivityBossByXy(invite.x, invite.y) ?: continue
        val atkRecordSet = bossInfo.getAtkRecordSet()
        val allMembers = areaCache.playerCache.findPlayersByAllianceId(player.allianceId)
        val allAtkNum = allMembers.count {
            atkRecordSet.contains(it.id)
        }

        val selfInAtk = boolToInt(atkRecordSet.contains(player.id))
        val inviteBuilder = pb4client.HunterInviteInfo.newBuilder()
        inviteBuilder.inviteId = invite.id
        inviteBuilder.inviteTime = invite.latestInviteTime
        inviteBuilder.pltAreaNo = invite.worldId
        inviteBuilder.posX = invite.x
        inviteBuilder.posY = invite.y
        inviteBuilder.bossId = bossInfo.getBossProtoId()
        inviteBuilder.nowHp = bossInfo.getCurrentHp()
        inviteBuilder.haveHunterNum = allAtkNum - selfInAtk

        allianceInvites.add(inviteBuilder)
    }
    return allianceInvites
}

//普通魔物移除时的通用处理
fun onCommonBossRemove(areaCache: AreaCache, commonBoss: CommonBoss) {
    //删除魔物数据
    areaCache.commonBossCache.deleteCommonBossByXY(commonBoss.x, commonBoss.y)

    //遣返未到达行军
    val gotoWalks = areaCache.walkCache.findWalksByGotoXy(commonBoss.x, commonBoss.y)
    gotoWalks.forEach {
        halfWayGoHome(areaCache, it)
        //发送遣返邮件
        val mailInfo = MailInfo(
            TEXT_READ_LAN,
            SYSTEM_MAIL,
            LinkedList(),
            TARGET_DISAPPEAR_CONTENT,
            LinkedList(Arrays.asList(WALK_PARAS + it.marchState.toString()))
        )
        val forces = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(it.walkForceGroupId)
        for (force in forces) {
            sendMail(areaCache, force.playerId, mailInfo)
        }
    }

    //删除个人攻打记录
    val notifier = createHunterRecordNotifier(
        Del,
        commonBoss.x,
        commonBoss.y,
        commonBoss.bossId,
        commonBoss.nowHp,
        0
    )
    commonBoss.atkRecordsMap.forEach {
        val session = fetchOnlinePlayerSession(areaCache, it.key)
        if (session != null) {
            notifier.notice(session)
        }
    }

    //删除魔物邀请
    updateBossInvite(areaCache, commonBoss, Del)

    removeWaitFightGroup(areaCache, commonBoss, WalkCommonBoss)
}