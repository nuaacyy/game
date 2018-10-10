package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.constg.CHAT_TYPE_ALLIANCE
import com.point18.slg2d.common.constg.FIGHT_INFO_SHARE
import com.point18.slg2d.common.constg.LOCATION_SHARE
import com.point18.slg2d.common.constg.MASS_INFO
import com.point18.slg2d.common.moduleFunClass.LocationShareInfo
import com.point18.slg2d.common.moduleFunClass.SimplifiedFightInfo
import com.point18.slg2d.common.moduleFunClass.SimplifiedMassInfo
import com.point18.slg2d.public.datacache.PublicCache
import pb4server.*
import com.point18.slg2d.common.resultcode.ResultCode

class GetAllianceChatHistoryOp : Home2PublicAskDealBase() {

    override fun dealHomeAsk(publicCache: PublicCache, req: Home2PublicAskReq, resp: Home2PublicAskResp.Builder) {
        val rt = GetAllianceChatAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        val getChatInfoRtVo = GetChatInfoRtVo.newBuilder()
        getChatInfoRtVo.rt = ResultCode.SUCCESS.code
        getChatInfoRtVo.chatType = CHAT_TYPE_ALLIANCE
        val recv = req.getAllianceChatAskReq
        val msgId = recv.chatId
        val allianceId = req.publicId

        val history = publicCache.allianceChatCache.getAllianceChatHistory(msgId, allianceId)
        val chatInfoList = mutableListOf<ChatInfoVo>()
        for (eachChat in history) {
            val chatInfoBuilder = ChatInfoVo.newBuilder()
            chatInfoBuilder.id = eachChat.id
            chatInfoBuilder.type = CHAT_TYPE_ALLIANCE
            chatInfoBuilder.isSystem = 1
            chatInfoBuilder.country = 24
            chatInfoBuilder.allianceName = eachChat.allianceName
            chatInfoBuilder.allianceShortName = eachChat.allianceName
            chatInfoBuilder.alliancePositions = eachChat.alliancePos.toString()
            chatInfoBuilder.player = eachChat.playerName
            chatInfoBuilder.playerId = eachChat.playerId
            chatInfoBuilder.playerShortName = eachChat.playerShortName
            chatInfoBuilder.playerIcon = eachChat.iconProtoId
            chatInfoBuilder.sendTime = (eachChat.chatTime / 1000).toInt()
            chatInfoBuilder.messageType = eachChat.msgType
            chatInfoBuilder.office = eachChat.wonderPos
            chatInfoBuilder.vipLv = eachChat.vipLv
            chatInfoBuilder.areaNo = eachChat.areaNo

            val noticeBuilder = NoticeVo.newBuilder()
            noticeBuilder.readType = com.point18.slg2d.common.constg.TEXT_READ_INFO
            noticeBuilder.noticeLanId = eachChat.msg

            if (eachChat.msgType == FIGHT_INFO_SHARE) {
                noticeBuilder.noticeLanId = ""
                val fightInfo = toObj<SimplifiedFightInfo>(eachChat.msg)
                val easyFightInfo = SimpleFightReportVo.newBuilder()
                easyFightInfo.reportType = fightInfo.reportType
                easyFightInfo.mainPlayer = fightInfo.mainPlayer
                easyFightInfo.mainPlayerAlliance = fightInfo.mainPlayerAlliance
                easyFightInfo.atkOrDef = fightInfo.atkOrDef
                easyFightInfo.targetName = fightInfo.targetName
                easyFightInfo.allianceOrLv = fightInfo.allianceOrLv
                easyFightInfo.reportId = fightInfo.reportId
                easyFightInfo.mainIconId = fightInfo.mainIconId
                easyFightInfo.iconId = fightInfo.iconId
                easyFightInfo.monsterId = fightInfo.monster
                easyFightInfo.world = fightInfo.worldId
                chatInfoBuilder.easyFightInfo = easyFightInfo.build()
            } else if (eachChat.msgType == LOCATION_SHARE) {
                val location = toObj<LocationShareInfo>(eachChat.msg)
                chatInfoBuilder.x = location.x
                chatInfoBuilder.y = location.y
                noticeBuilder.noticeLanId = location.locationName
            } else if (eachChat.msgType == MASS_INFO) {
                noticeBuilder.noticeLanId = ""
                val massInfo = toObj<SimplifiedMassInfo>(eachChat.msg)
                val simpleMassInfoVo = SimpleMassInfoVo.newBuilder()
                simpleMassInfoVo.massId = massInfo.massId
                simpleMassInfoVo.massName = massInfo.massName
                chatInfoBuilder.massInfo = simpleMassInfoVo.build()
            }

            chatInfoBuilder.message = noticeBuilder.build()
            chatInfoList.add(chatInfoBuilder.build())
        }

        getChatInfoRtVo.addAllChatInfos(chatInfoList)
        rt.getChatInfoRt = getChatInfoRtVo.build()

        resp.setGetAllianceChatAskRt(rt)
    }
}