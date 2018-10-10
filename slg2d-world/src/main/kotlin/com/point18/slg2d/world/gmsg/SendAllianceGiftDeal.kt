package com.point18.slg2d.world.gmsg

import com.point18.slg2d.common.constg.ALLIANCE_AWARD_SETTING
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.world.Public2WorldTellDealBase
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.msgnotice.createGetAllianceGiftNotifier
import pb4client.AllianceGiftVo
import pb4server.Public2WorldTell
import xyz.ariane.util.toDefaultEpochMilli
import java.time.LocalDate
import java.time.LocalDateTime

class SendAllianceGiftDeal : Public2WorldTellDealBase() {

    override fun dealPublicTell(areaCache: AreaCache, worldId: Long, playerId: Long, msg: Public2WorldTell) {
        val tell = msg.sendAllianceGiftNotic2GTell
        val player = areaCache.playerCache.findPlayerById(playerId)
        if (player == null) {
            return
        }

        val localDate = LocalDate.now()
        val localDateTime = LocalDateTime.of(
            localDate.year, localDate.month, localDate.dayOfMonth,
            pcs.basicProtoCache.timeZone, 0, 0
        )
        val nowDateTime = LocalDateTime.now()
        if (nowDateTime < localDateTime) {
            localDateTime.plusDays(-1)
        }

        if (player.lastAllianceGiftGetTime < localDateTime.toDefaultEpochMilli()) {
            player.allianceGiftGetMap = hashMapOf()
        }

        if (tell.extend1 != "") {
            val condition = tell.extend1.toInt()
            val ex = player.allianceGiftGetMap[condition]
            if (ex != null) {
                // 玩家今天已经拿过一档这个奖励了,操作是玩家换过帮了
                return
            }
        }

        val gift =
            areaCache.myAllianceGiftCache.createMyAllianceGift(playerId, tell.overTime, tell.giftId, tell.giftInfo)
        // 推送给玩家多了个礼物

        val agVo = AllianceGiftVo.newBuilder()
        agVo.onlyId = gift.id
        agVo.giftId = gift.giftId
        agVo.isGet = gift.isGet
        agVo.giftInfo = gift.giftInfo
        agVo.overTime = (gift.overTime / 1000).toInt()

        // 告诉联盟成员playerM的信息变化
        val session = fetchOnlinePlayerSession(areaCache, playerId)
        if (session != null) {
            val msgNotifier = createGetAllianceGiftNotifier(agVo)
            msgNotifier.notice(session)
        }

        // 发送app通知
        areaCache.pushAppNotice(
            playerId,
            ALLIANCE_AWARD_SETTING,
            0
        )

        if (tell.extend1 != "") {
            val condition = tell.extend1.toInt()
            player.allianceGiftGetMap[condition] = 1
        }
    }
}

