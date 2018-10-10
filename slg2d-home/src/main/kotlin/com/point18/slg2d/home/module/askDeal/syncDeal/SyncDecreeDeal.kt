package com.point18.slg2d.home.module.askDeal.syncDeal

import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.common.syncdomain.DecreeData
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.askDeal.SyncMsgDeal

class SyncDecreeDeal : SyncMsgDeal, HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java) {

    override fun dealSync(session: PlayerActor, data: String): Int {
        return prepare(session) { homePlayerDC ->
            val decreeInfo: DecreeData
            try {
                decreeInfo = toObj(data)
            } catch (e: Exception) {
                return@prepare ResultCode.PARAMETER_ERROR.code
            }

            try {
                val player = homePlayerDC.player
                player.decree = decreeInfo.num
                player.decreeLimit = decreeInfo.limit
                player.decreeTime = decreeInfo.time
            } catch (e: Exception) {
                println("出现异常：${session.playerId} ")
                e.printStackTrace()
            }

            return@prepare ResultCode.SUCCESS.code
        }
    }
}