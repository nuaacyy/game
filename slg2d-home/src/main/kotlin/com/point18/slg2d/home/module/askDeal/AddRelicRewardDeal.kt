package com.point18.slg2d.home.module.askDeal

import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.TimeBoxInfo
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.msgnotice.createTimeBoxInfoChangeNotifier
import pb4server.AddRelicRewardAskReq
import pb4server.AddRelicRewardAskRt
import pb4server.World2HomeAskReq
import pb4server.World2HomeAskResp

class AddRelicRewardDeal() : W2HAsk, HomeHelperPlus1<HomePlayerDC>(
    HomePlayerDC::class.java
) {

    override fun dealWorldAsk(session: PlayerActor, req: World2HomeAskReq, resp: World2HomeAskResp.Builder) {
        val internalMessage = req.addRelicRewardAskReq
        val rt = addRelicReward(session, internalMessage)
        resp.setAddRelicRewardAskRt(rt)
    }

    private fun addRelicReward(session: PlayerActor, req: AddRelicRewardAskReq): AddRelicRewardAskRt.Builder {
        return prepare(session) { homePlayerDC: HomePlayerDC ->
            val relicBoxId = req.timeBoxId
            val dropRate = req.dropRate

            val rtBuilder = AddRelicRewardAskRt.newBuilder()
            rtBuilder.rt = ResultCode.SUCCESS.code

            val player = homePlayerDC.player

            //添加时光之盒奖励
            var canIndex = -1
            for ((index, playerTimeBox) in player.timeBoxNumMap) {
                if (playerTimeBox.relicBoxId == 0) {

                    if (canIndex == -1) {
                        canIndex = index
                    } else {

                        if (index < canIndex) {
                            canIndex = index
                        }
                    }
                }
            }

            if (canIndex == -1) {
                //无空位
                rtBuilder.rt = ResultCode.TIME_BOX_QUEUE_ERROR.code
                return@prepare rtBuilder
            }

            val relicBox = pcs.relicBoxCache.relicBoxMap[relicBoxId]

            if (relicBox == null) {
                rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                return@prepare rtBuilder
            }

            val timeBoxInfo =
                TimeBoxInfo(
                    relicBoxId,
                    dropRate,
                    relicBox.conversionTime * 3600000L,
                    0
                )
            player.putTimeBoxNumMap(canIndex, timeBoxInfo)
            val notice = createTimeBoxInfoChangeNotifier(canIndex, timeBoxInfo)
            notice.notice(session)

            return@prepare rtBuilder
        }
    }
}