package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.askDeal.W2HAsk
import pb4server.QueryPlayerInfoAskRt
import pb4server.QueryPlayerInfoVo
import pb4server.World2HomeAskReq
import pb4server.World2HomeAskResp

class QueryPlayerInfoDeal : W2HAsk,
    HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java) {

    override fun dealWorldAsk(session: PlayerActor, req: World2HomeAskReq, resp: World2HomeAskResp.Builder) {
        prepare(session) { homePlayerDC ->
            val player = homePlayerDC.player

            val rt = QueryPlayerInfoAskRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code
            val queryPlayerInfoVo = QueryPlayerInfoVo.newBuilder()
            queryPlayerInfoVo.gamePltAreaNo = player.worldId
            queryPlayerInfoVo.playerName = player.name
            queryPlayerInfoVo.photoProtoId = player.photoProtoId
            queryPlayerInfoVo.offTime = player.lastLeaveTime
            queryPlayerInfoVo.mapPltAreaNo = player.worldId
            queryPlayerInfoVo.lastLoginTime = player.loginTime
            queryPlayerInfoVo.mapAreaNo = player.areaNo
            queryPlayerInfoVo.playerCastleLv = player.castleLv
            queryPlayerInfoVo.playerNickName = player.allianceNickName
            rt.setQueryPlayerInfoVo(queryPlayerInfoVo)

            resp.setQueryPlayerInfoAskRt(rt)
        }
    }
}








