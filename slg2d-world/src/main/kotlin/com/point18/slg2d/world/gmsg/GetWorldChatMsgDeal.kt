package com.point18.slg2d.world.gmsg

import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.constg.CHAT_TYPE_WORLD
import com.point18.slg2d.common.constg.FIGHT_INFO_SHARE
import com.point18.slg2d.common.constg.LOCATION_SHARE
import com.point18.slg2d.common.moduleFunClass.LocationShareInfo
import com.point18.slg2d.common.moduleFunClass.SimplifiedFightInfo
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.Home2WorldTellDealBase
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import pb4client.ChatInfo
import pb4client.GetChatInfoRt
import pb4client.Notice
import pb4client.SimpleFightReport
import pb4server.GetChatHistoryTell
import pb4server.Home2WorldTell
import java.util.*

class GetWorldChatMsgDeal : Home2WorldTellDealBase() {
    override fun dealHomeTell(areaCache: AreaCache, worldId: Long, playerId: Long, msg: Home2WorldTell) {
        val tellMsg = msg.getChatHistoryTell
        dealGetChatHistory(areaCache, playerId, tellMsg)
    }

    private fun dealGetChatHistory(areaCache: AreaCache, playerId: Long, worldMessage: GetChatHistoryTell) {
        val chatId = worldMessage.chatId
        val rtToPlayer = GetChatInfoRt.newBuilder()
        rtToPlayer.rt = ResultCode.SUCCESS.code

        val session = fetchOnlinePlayerSession(areaCache, playerId)
        session?.sendMsg(MsgType.GetChatInfo_319) {
            val history = areaCache.chatCache. getWorldChatHistory(chatId)
            val chatInfoList = LinkedList<ChatInfo>()
            for (eachChat in history) {
                if (worldMessage.blackListList.contains(eachChat.playerId)) {
                    continue
                }

                val chatInfoBuilder = ChatInfo.newBuilder()
                chatInfoBuilder.id = eachChat.id
                chatInfoBuilder.type = CHAT_TYPE_WORLD
                chatInfoBuilder.isSystem = 1
                chatInfoBuilder.country = 24
                chatInfoBuilder.allianceName = eachChat.allianceName
                chatInfoBuilder.allianceShortName = eachChat.allianceShortName
                chatInfoBuilder.alliancePositions = eachChat.alliancePos.toString()
                chatInfoBuilder.player = eachChat.playerName
                chatInfoBuilder.playerId = eachChat.playerId
                chatInfoBuilder.playerShortName = eachChat.playerShortName
                chatInfoBuilder.playerIcon = eachChat.iconProtoId
                chatInfoBuilder.sendTime = (eachChat.chatTime / 1000).toInt()
                chatInfoBuilder.messageType = eachChat.msgType
                chatInfoBuilder.office = eachChat.wonderPos
                chatInfoBuilder.vipLv = eachChat.vipLv
                chatInfoBuilder.areaNo = 0
                val noticeBuilder = Notice.newBuilder()
                noticeBuilder.readType = eachChat.readType
                noticeBuilder.noticeLanId = eachChat.msg

                if (eachChat.msgType == FIGHT_INFO_SHARE) {
                    noticeBuilder.noticeLanId = ""
                    val fightInfo = toObj<SimplifiedFightInfo>(eachChat.msg)
                    val easyFightInfo = SimpleFightReport.newBuilder()
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
                }

                chatInfoBuilder.message = noticeBuilder.build()
                chatInfoList.add(chatInfoBuilder.build())
            }

            rtToPlayer.addAllChatInfos(chatInfoList)
            rtToPlayer.build()
        }
    }
}