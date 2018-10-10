package com.point18.slg2d.world.module.realm

import com.point18.slg2d.common.constg.ADD_WALKROBOTINFO
import com.point18.slg2d.common.constg.Hiding
import com.point18.slg2d.common.constg.Running
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.common.*
import com.point18.slg2d.world.msgnotice.createWalkRobotShowNotifier
import pb4client.EnterGameMapRt

fun getMapDataForEnter(session: PlayerSession): EnterGameMapRt {
    val rt = EnterGameMapRt.newBuilder()

    //自己行军组数据
    val areaCache = session.areaCache
    val player = session.player
    val selfGroups = areaCache.walkForceGroupCache.findWalkForceGroupsByPlayerId(player.id)
    for (group in selfGroups) {
        if (group.runningState == Hiding) {
            //过滤藏兵
            continue
        }
        val walkGroup = createWalkGroupMsg(areaCache, group)
        if (walkGroup == null) {
            continue
        }
        if (group.runningState == Running) {
            val walk = areaCache.walkCache.findWalkByGroupId(group.id) ?: continue

            val walkRobot = walkWrap2WalkRobot(areaCache, walk) ?: continue

            val walkRobotShowNotifier = createWalkRobotShowNotifier(ADD_WALKROBOTINFO, walkRobot)
            walkRobotShowNotifier.notice(session)
        }

        rt.addMyWalks(walkGroup)
    }

    if (player.allianceId != 0L) {
        // 联盟内的集结数据
        val allMass = areaCache.massCache.findMassByAllianceId(player.allianceId)
        if (allMass.size > 0) {
            for (mass in allMass) {
                rt.addMassWalks(createMassGroupBuilder(areaCache, mass))
            }
        }

        // 联盟内被集结
        val allMembers = areaCache.playerCache.findPlayersByAllianceId(player.allianceId)
        for (member in allMembers) {
            val castle = areaCache.castleCache.findCastleById(member.focusCastleId)
            if (castle == null) {
                continue
            }

            val specAllMass = areaCache.massCache.findAllMassByPos(castle.x, castle.y)
            for (mass in specAllMass) {
                rt.addMassWalks(createReinforceMassGroupBuilder(areaCache, mass))
            }
        }
    }

    // 预警信息
    getWarnData(areaCache, player).forEach { rt.addWarnWalks(it) }

    return rt.build()
}
