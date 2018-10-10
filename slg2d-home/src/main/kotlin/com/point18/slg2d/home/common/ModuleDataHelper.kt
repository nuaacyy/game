package com.point18.slg2d.home.common

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.LotteryDC
import com.point18.slg2d.home.dc.SkinDC
import com.point18.slg2d.home.helper.HomeHelperPlus3
import com.point18.slg2d.home.msgnotice.*

class ModuleDataHelper : HomeHelperPlus3<HomePlayerDC, SkinDC, LotteryDC>(
    HomePlayerDC::class.java, SkinDC::class.java, LotteryDC::class.java
) {
    fun initModuleData(session: PlayerActor, moduleId: Int) {
        prepare(session) { homePlayerDC: HomePlayerDC, skinDC: SkinDC, lotteryDC: LotteryDC ->
            when (moduleId) {
                SKIN_INFO_INIT -> initSkinInfo(session, skinDC)
                PLAYER_ADD_INFO_INIT -> initPlayerAddInfo(session, homePlayerDC)
                ALLIANCE_GIFT_INFO_INIT -> initAllianceGiftInfo(session, homePlayerDC)
                MARK_INFO_INIT -> initMarkInfo(session, homePlayerDC)
                LOTTERY_INFO_INIT -> initLotteryInfo(lotteryDC, session)
            }
        }
    }

    private fun initSkinInfo(session: PlayerActor, skinDC: SkinDC) {
        val skins = skinDC.findSkinsByPlayerId()

        val notifier = createSkinInfoInitNotifier()
        for (skin in skins) {
            notifier.append(skin.skinType, skin.star, skin.isUse)
        }

        notifier.notice(session)
    }

    private fun initPlayerAddInfo(session: PlayerActor, homePlayerDC: HomePlayerDC) {
        val player = homePlayerDC.player

        val notifier = createPlayerAddInfoInitNotifier(player.allianceNickName)
        notifier.notice(session)
    }

    private fun initAllianceGiftInfo(session: PlayerActor, homePlayerDC: HomePlayerDC) {
        val player = homePlayerDC.player

        val notifier = createAllianceGiftInfoInitNotifier(player.openAllianceSendGift)
        notifier.notice(session)
    }

    private fun initMarkInfo(session: PlayerActor, homePlayerDC: HomePlayerDC) {
        val player = homePlayerDC.player

        val notifier = createMarkInfoInitNotifier(player.maxMark)
        notifier.notice(session)
    }

    private fun initLotteryInfo(lotteryDC: LotteryDC, session: PlayerActor) {
        val lotteryPools = lotteryDC.findDrawHeroByPlayerId(session.playerId)
        val notifier = createLotteryInfoInitNotifier(lotteryPools)
        notifier.notice(session)
    }
}