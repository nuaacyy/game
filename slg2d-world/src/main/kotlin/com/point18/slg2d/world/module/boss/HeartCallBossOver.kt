package com.point18.slg2d.world.module.boss

import com.point18.slg2d.common.baseg.IHeartHandler
import com.point18.slg2d.world.area4data.*
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.world.module.walk.walkComm.halfWayGoHome
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.common.noticeCellUpdate
import com.point18.slg2d.world.common.sendMail
import com.point18.slg2d.world.common.updateBossInvite
import com.point18.slg2d.world.msgnotice.createHunterRecordNotifier
import java.util.*
import java.util.Arrays.asList

class CallBossHeartHandler: IHeartHandler<AreaCache> {

    override fun handleHeart(cache: AreaCache) {
        callBossHeart(cache)
    }

    //召唤魔物消失的心跳
    private fun callBossHeart(areaCache: AreaCache) {
        val allTimeOverCallBoss = areaCache.callBossCache.findAllTimeOverCallBoss()
        for (removeBoss in allTimeOverCallBoss) {
            onCallBossOver(areaCache, removeBoss)
        }
    }
}

fun onCallBossOver(areaCache: AreaCache, callBoss: CallBoss) {
    // 删除个人召唤魔物
    areaCache.callBossCache.deleteCallBoss(callBoss)

    val gotoWalks = areaCache.walkCache.findWalksByGotoXy(callBoss.x, callBoss.y)
    gotoWalks.forEach {
        halfWayGoHome(areaCache, it)
        //发送遣返邮件
        val mailInfo = MailInfo(
            TEXT_READ_LAN,
            SYSTEM_MAIL,
            LinkedList(),
            TARGET_DISAPPEAR_CONTENT,
            LinkedList(asList(WALK_PARAS + it.marchState.toString()))
        )
        val forces = areaCache.walkForceCache.findWalkForceByWalkForceGroupId(it.walkForceGroupId)
        for (force in forces) {
            sendMail(areaCache, force.playerId, mailInfo)
        }
    }

    // 删除个人记录
    val notifier = createHunterRecordNotifier(
        Del,
        callBoss.x,
        callBoss.y,
        callBoss.bossId,
        callBoss.nowHp,
        0
    )
    callBoss.atkRecordsMap.forEach {
        val session = fetchOnlinePlayerSession(areaCache, it.key)
        if (session != null) {
            notifier.notice(session)
        }
    }

    updateBossInvite(areaCache, callBoss, Del)

    removeWaitFightGroup(areaCache, callBoss, WalkCallBoss)

    //地块刷新
    noticeCellUpdate(areaCache, callBoss.x, callBoss.y)
}