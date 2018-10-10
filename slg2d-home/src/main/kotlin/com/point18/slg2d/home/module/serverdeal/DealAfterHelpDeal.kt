package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.forwardHeartDeal2World
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.InnerCityDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import com.point18.slg2d.home.module.askDeal.P2HTell
import com.point18.slg2d.home.msgnotice.createInnerCityInfoChangedNotifier
import com.point18.slg2d.home.msgnotice.createResearchChangeNotifier
import pb4server.Public2HomeTell

class DealAfterHelpDeal : P2HTell,
    HomeHelperPlus2<HomePlayerDC, InnerCityDC>(HomePlayerDC::class.java, InnerCityDC::class.java) {

    override fun dealPublicTell(session: PlayerActor, playerId: Long, msg: Public2HomeTell) {
        val tell = msg.dealAfterHelpTell

        val helpType = tell.helpType
        val helpValue1 = tell.helpValue1
        val helpValue2 = tell.helpValue2
        val helpValue3 = tell.helpValue3
        val helperId = tell.helpPlayerId // 帮助者ID

        prepare(session) { homePlayerDC: HomePlayerDC, innerCityDC: InnerCityDC ->

            val helpPlayer = homePlayerDC.player
            if (helpType == CLEAR_TIME_RESEARCH) {
                val researchId = helpValue1
                val researchLv = helpValue2 - 1

                // 检测玩家是否是当前等级
                val researchVo = helpPlayer.researchInfoMap
                val vo = researchVo[researchId.toInt()]
                if (vo == null) {
                    return@prepare
                }
                if (vo.researchLv.toLong() != researchLv) {
                    return@prepare
                }

                if (vo.researchOverTime == 0L) {
                    return@prepare
                }
                val ex = vo.helperIds[helperId]
                if (ex != null) {
                    return@prepare
                }

                var helpTime = (vo.researchOverTime - vo.researchStartTime) / 100
                if (helpTime < 60000) {
                    helpTime = 60000
                }
                vo.helperIds[helperId] = 1
                vo.researchOverTime = vo.researchOverTime - helpTime

                forwardHeartDeal2World(session, UpdateHeart, ResearchLvUp, researchId, vo.researchOverTime)
                helpPlayer.researchInfoMap[researchId.toInt()] = vo

                // 推送给客户端变更
                val notice =
                    createResearchChangeNotifier(researchId.toInt(), vo.researchLv, vo.researchOverTime, vo.helpId)
                notice.notice(session)

            } else if (helpType == CLEAR_TIME_INNER_CITY_BUILDING_UPGRADE) {
                val buildType = helpValue1
                val buildLv = helpValue2 - 1
                val buildId = helpValue3

                // 检测玩家是否是当前等级
                val building = innerCityDC.findInnerCityFromId(buildId)
                if (building == null) {
                    return@prepare
                }

                if (building.completeTime == 0L) {
                    return@prepare
                }

                if (building.lv != buildLv.toInt()) {
                    return@prepare
                }

                if (building.cityType != buildType.toInt()) {
                    return@prepare
                }

                if (building.helpId == 0L) {
                    return@prepare
                }

                val ex = building.helperIdMap[helperId]
                if (ex != null) {
                    return@prepare
                }

                var helpTime = (building.completeTime - building.startTime) / 100
                if (helpTime < 60000) {
                    helpTime = 60000
                }
                building.helperIdMap[helperId] = 1
                building.completeTime = building.completeTime - helpTime

                innerCityDC.updateInnerCityUpgradeState2World(
                    session,
                    building,
                    building.state,
                    building.completeTime,
                    building.startTime
                )
                // 推送给客户端变更
                createInnerCityInfoChangedNotifier(
                    CHANGE_INNER_CITY,
                    building.cityType,
                    building.id,
                    (building.startTime / 1000).toInt(),
                    (building.completeTime / 1000).toInt(),
                    building.state,
                    building.positionIndex,
                    building.lv,
                    building.helpId
                ).notice(session)

            }
        }

        return

    }
}








