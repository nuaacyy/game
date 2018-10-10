package com.point18.slg2d.home.module.friend

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.QueryPlayerByName
import pb4client.QueryPlayerByNameRt
import pb4client.QueryPlayerByNameVo
import pb4server.FindAllPlayerAskReq
import pb4server.Home2WorldAskReq
import pb4server.Home2WorldAskResp
import xyz.ariane.util.lzWarn

// 查询玩家
class QueryPlayerByNameDeal : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC ->
            val playerName = (msg as QueryPlayerByName).playerName

            val rt = QueryPlayerByNameRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code

            val player = homePlayerDC.player
            // playerName的初步检测
            if (playerName.length < 1) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                session.sendMsg(MsgType.QueryPlayerByName_709, rt.build())
                return@prepare
            }

            val req = FindAllPlayerAskReq.newBuilder()
            req.playerName = playerName
            val askMsgBuilder = Home2WorldAskReq.newBuilder()
            askMsgBuilder.playerId = player.playerId
            askMsgBuilder.worldId = player.worldId
            askMsgBuilder.setFindAllPlayerAskReq(req)

            session.createACS<Home2WorldAskResp>().ask(
                session.worldShardProxy,
                askMsgBuilder.build(),
                Home2WorldAskResp::class.java
            ).whenCompleteKt { res, err ->
                if (err != null || res == null) {
                    normalLog.lzWarn { "查询玩家失败:{$res}" }
                    return@whenCompleteKt
                }

                val matePlayers = res.findAllPlayerAskRt.playersList
                for (playerTemp in matePlayers) {
                    val queryPlayerByNameVo = QueryPlayerByNameVo.newBuilder()
                    queryPlayerByNameVo.playerId = playerTemp.myPlayerId
                    queryPlayerByNameVo.playerName = playerTemp.name
                    queryPlayerByNameVo.photoId = playerTemp.photoProtoId
                    queryPlayerByNameVo.vipLv = playerTemp.vipLv
                    queryPlayerByNameVo.areaNo = playerTemp.areaNo
                    queryPlayerByNameVo.allianceShortName = playerTemp.allianceShortName
                    queryPlayerByNameVo.castleLv = playerTemp.castleLv
                    queryPlayerByNameVo.shortName = playerTemp.shortName
                    rt.addQueryPlayerByNameVos(queryPlayerByNameVo)
                }

                session.sendMsg(MsgType.QueryPlayerByName_709, rt.build())

            }
        }
    }
}