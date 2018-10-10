package com.point18.slg2d.world.module.moveCity

import com.point18.slg2d.common.baseg.IModule
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.common.*
import com.point18.slg2d.world.module.walk.dealWalkFinish
import com.point18.slg2d.world.module.walk.doCancelMass
import com.point18.slg2d.world.module.walk.walkComm.goHome
import com.point18.slg2d.world.msgnotice.createCastlePosChangeNotifier
import com.point18.slg2d.world.wpm
import xyz.ariane.util.lzDebug
import java.util.*
import java.util.Arrays.asList

var MoveCityM: MoveCityModule = MoveCityModule()

class MoveCityModule : IModule {
    override fun moduleInit() {
        wpm.hs.registerHeartHandler(BelongCellOverHeartHandler())

        wpm.es.register(PVP_DEF_CASTLE_LOSE, PvpDefCastleLoseEventHandler())

        wpm.es.register(BOSS_FIGHT, BossDieEventHandler())
    }
}

fun randomFlyCastleAway(areaCache: AreaCache, playerId: Long) {
    val player = areaCache.playerCache.findPlayerById(playerId)
    if (player == null) {
        normalLog.error("moveCity : player == null ")
        return
    }
    val castle = areaCache.castleCache.findCastleById(player.focusCastleId)
    if (castle == null) {
        normalLog.error("moveCity : castle == null ")
        return
    }
    val oldX = castle.x
    val oldY = castle.y

    // 获取随机的空坐标
    var (newX, newY) = findFreeCastlePos(areaCache)
    if (newX == -1 || newY == -1) {

        normalLog.lzDebug { "被打飞时，居然找不到可用的出生点,就让他原地降落" }
        newX = oldX
        newY = oldY
    }

    //将在本城的驻扎/增援部队全部遣返
    val groupsInCastle =
        areaCache.walkForceGroupCache.findWalkForceGroupsByPosAndState(oldX, oldY, Reinforce.and(Stationed))
    for (group in groupsInCastle) {
        goHome(areaCache, oldX, oldY, group)

        //发送遣返邮件
        val messageParams = LinkedList<String>()
        messageParams += player.name
        val mailInfo = MailInfo(
            TEXT_READ_LAN,
            SYSTEM_MAIL,
            LinkedList(),
            FORCE_BE_SEND_HOME_CONTENT,
            messageParams
        )
        sendMail(areaCache, group.mainPlayerId, mailInfo)
    }

    //自己行军中的部队回家
    val allMass = areaCache.massCache.findMassByAllianceId(player.allianceId)
    val myForces = areaCache.walkForceCache.findWalkForceByPlayerId(playerId)

    for (force in myForces) {
        val walkLine = areaCache.walkCache.findWalkByGroupId(force.forceGroupId)
        if (walkLine == null) {
            continue
        }

        allMass.find {
            it.findMassMember(force.playerId) != null
        }?.let { findMass ->
            //集结发起者，直接解散集结
            if (findMass.mainPlayerId == force.playerId) {
                doCancelMass(areaCache, force.playerId, findMass.id)
                return@let
            }

            //清除加入集结
            findMass.removeMassMember(force.playerId)
            noticeDelMassGroup(areaCache, findMass.id, asList(force.playerId))
            noticeMassGroup(areaCache, Update, findMass)
        }

        //瞬移回家
        dealWalkFinish(areaCache, walkLine)
    }

    //修改城池坐标
    areaCache.castleCache.updateCastlePos(castle, newX, newY)

    val session = fetchOnlinePlayerSession(areaCache, player.id)

    if (session != null) {
        val notifier = createCastlePosChangeNotifier(newX, newY)
        notifier.notice(session)
    }

    //添加统计
    targetAddVal(areaCache, player.id, MoveCity)

    noticeCellUpdate(areaCache, oldX, oldY)
    noticeCellUpdate(areaCache, newX, newY)

    //到来的部队瞬移
    val gotoOldPosWalks = areaCache.walkCache.findWalksByGotoXy(oldX, oldY)

    for (walkLine in gotoOldPosWalks) {
        dealWalkFinish(areaCache, walkLine)
    }
    val gotoNewPosWalks = areaCache.walkCache.findWalksByGotoXy(newX, newY)

    for (walkLine in gotoNewPosWalks) {
        dealWalkFinish(areaCache, walkLine)
    }

    //刷新当前集结
    updateMassPos(areaCache, oldX, oldY, newX, newY)
}


