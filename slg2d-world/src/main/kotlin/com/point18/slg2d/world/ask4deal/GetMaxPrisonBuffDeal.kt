package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.ADD_PRISON
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.Home2WorldAskDealBase
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Prison
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.common.noticeCellUpdate
import com.point18.slg2d.world.msgnotice.createPlayerPrisonChangeNotifier
import pb4server.Home2WorldAskReq
import pb4server.Home2WorldAskResp
import pb4server.MaxPrisonBuffAskRt

class GetMaxPrisonBuffDeal : Home2WorldAskDealBase() {

    override fun dealHomeAsk(areaCache: AreaCache, req: Home2WorldAskReq, resp: Home2WorldAskResp.Builder) {
        val dealRt = MaxPrisonBuffAskRt.newBuilder()
        dealRt.rt = ResultCode.SUCCESS.code

        val player = areaCache.playerCache.findPlayerById(req.playerId)
        if (player == null) {
            dealRt.rt = ResultCode.NO_PLAYER.code
            resp.setMaxPrisonBuffAskRt(dealRt)
            return
        }

        val castle = areaCache.castleCache.findCastleById(player.focusCastleId)
        if (castle == null) {
            dealRt.rt = ResultCode.CASTLE_DONT_EXISTED.code
            resp.setMaxPrisonBuffAskRt(dealRt)
            return
        }

        // 有防护罩效果，不能开
        val (isHaveCover, _) = areaCache.buffCache.isHaveCoverTypeBuff(req.playerId)
        if (isHaveCover) {
            dealRt.rt = ResultCode.DEF_COVER_EFFECT_ON.code
            resp.setMaxPrisonBuffAskRt(dealRt)
            return
        }

        // 玩家有那些监禁者p
        val prisonInfo = areaCache.prisonCache.findPrisonsByPlayerId(req.playerId)
        // 因为buff也当作一个prison记录，所以加1
        if (prisonInfo.size + 1 >= pcs.basicProtoCache.prisonNum) {
            dealRt.rt = ResultCode.NO_MORE_PRISON_ADD.code
            resp.setMaxPrisonBuffAskRt(dealRt)
            return
        }

        val nowTime = getNowTime()
        val buffEndTime = nowTime + pcs.basicProtoCache.fakeLordDisappear * 60 * 1000
        areaCache.playerCache.updatePlayerMaxLvPrisonBuffEndTime(player, buffEndTime)

        // 这是一个buff，作为填充
        val prison = Prison(
            -1,
            0,
            -1,
            0,
            0,
            req.playerId
        )

        // 推送给关人者监狱信息变化
        val notifier = createPlayerPrisonChangeNotifier(areaCache, ADD_PRISON, prison)
        val worldSession = fetchOnlinePlayerSession(areaCache, req.playerId)
        if (notifier != null && worldSession != null) {
            notifier.notice(worldSession)
        }

        noticeCellUpdate(areaCache, castle.x, castle.y)

        resp.setMaxPrisonBuffAskRt(dealRt)
    }
}