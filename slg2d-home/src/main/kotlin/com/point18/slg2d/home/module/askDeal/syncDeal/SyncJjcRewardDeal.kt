package com.point18.slg2d.home.module.askDeal.syncDeal

import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomeSyncDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.askDeal.SyncMsgDeal
import com.point18.slg2d.home.msgnotice.createArenaRewardGetNotifier

class SyncJjcRewardDeal : SyncMsgDeal, HomeHelperPlus1<HomeSyncDC>(HomeSyncDC::class.java) {

    override fun dealSync(session: PlayerActor, data: String): Int {
        return prepare(session) { homeSyncDc ->
            val gold = toObj<List<Long>>(data)
            if (gold[1] + homeSyncDc.syncData.jjcGoldReward >= pcs.basicProtoCache.arenaRewardLimit) {
                homeSyncDc.syncData.jjcGoldReward = pcs.basicProtoCache.arenaRewardLimit
            } else {
                homeSyncDc.syncData.jjcGoldReward = gold[1] + homeSyncDc.syncData.jjcGoldReward
            }

            if (gold[0] + homeSyncDc.syncData.jjcCoinReward >= pcs.basicProtoCache.arenaRewardLimit) {
                homeSyncDc.syncData.jjcCoinReward = pcs.basicProtoCache.arenaRewardLimit
            } else {
                homeSyncDc.syncData.jjcCoinReward = gold[0] + homeSyncDc.syncData.jjcCoinReward
            }

            createArenaRewardGetNotifier(homeSyncDc.syncData.jjcGoldReward, homeSyncDc.syncData.jjcCoinReward).notice(
                session
            )

            return@prepare ResultCode.SUCCESS.code
        }
    }
}