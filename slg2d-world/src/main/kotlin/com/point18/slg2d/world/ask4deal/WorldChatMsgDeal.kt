package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.world.area4data.*
import com.point18.slg2d.world.Home2WorldAskDealBase
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.LOCATION_SHARE
import com.point18.slg2d.common.constg.TRMPET
import com.point18.slg2d.world.common.findOfficeByPlayerId
import com.point18.slg2d.common.moduleFunClass.LocationShareInfo
import pb4server.Home2WorldAskReq
import pb4server.Home2WorldAskResp
import pb4server.WorldChatAskRt
import com.point18.slg2d.common.resultcode.ResultCode

class WorldChatMsgDeal : Home2WorldAskDealBase() {
    override fun dealHomeAsk(areaCache: AreaCache, req: Home2WorldAskReq, resp: Home2WorldAskResp.Builder) {
        val receiveFromHome = req.worldChatAskReq
        val rt = WorldChatAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.chatId = 0L
        var message = receiveFromHome.message

        val player = areaCache.playerCache.findPlayerById(req.playerId)
        if (player == null) {
            rt.rt = ResultCode.NO_PLAYER.code
            resp.setWorldChatAskRt(rt)
            return
        }

        if (receiveFromHome.messageType == LOCATION_SHARE) {
            val location = LocationShareInfo(player.areaNo, receiveFromHome.x, receiveFromHome.y, receiveFromHome.message)
            message = toJson(location)
            rt.locationInfo = message
        }

        val chat = Chat()
        chat.msg = message
        chat.chatTime = getNowTime()
        chat.msgType = receiveFromHome.messageType
        chat.playerId = req.playerId
        chat.worldId = req.worldId
        chat.allianceName = receiveFromHome.allianceName
        chat.allianceShortName = receiveFromHome.allianceShortName
        chat.playerShortName = receiveFromHome.playerShortName
        chat.playerName = receiveFromHome.playerName
        chat.alliancePos = player.getMaxAlliancePos()
        chat.kingdomPos = 0  // todo 国王功能还有?
        chat.wonderPos = findOfficeByPlayerId(areaCache, player.id)
        chat.vipLv = receiveFromHome.vipLv
        chat.iconProtoId = receiveFromHome.iconProtoId

        // 喇叭不存库
        if (chat.msgType != TRMPET) {
            areaCache.chatCache.createChat(chat)
        }

        rt.chatId = chat.id
        resp.setWorldChatAskRt(rt)
        return
    }
}