package com.point18.slg2d.home.module.viewprofile

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.BlackPlayerDC
import com.point18.slg2d.home.dc.FriendDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus3
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.*
import pb4client.BagInfo
import pb4client.EquipProp
import pb4client.EquipProps
import pb4client.KingEquipCardInfo
import pb4client.PlayerInFo
import pb4server.*
import xyz.ariane.util.lzWarn

// 查询玩家个人信息
class OtherPersonalPowerDeal(
) : HomeClientMsgDeal,
    HomeHelperPlus3<HomePlayerDC, FriendDC, BlackPlayerDC>(
        HomePlayerDC::class.java, FriendDC::class.java, BlackPlayerDC::class.java
    ) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val pId = (msg as OtherPersonalPower).playerId
        playerInFoFunc(session, pId)
    }

    private fun playerInFoFunc(session: PlayerActor, id: Long) {
        prepare(session) { homePlayerDC: HomePlayerDC, friendDC: FriendDC, blackPlayerDC: BlackPlayerDC ->
            val rt = OtherPersonalPowerRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code
            val playerId = session.playerId
            if (playerId == id) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                session.sendMsg(MsgType.OtherPersonalPower_1576, rt.build())
                return@prepare
            }

            val player = homePlayerDC.player
            val worldId = player.worldId

            // 是否是好友
            var isMyFriend = 0
            val friendInfo = friendDC.findMyFriendById(id)
            if (friendInfo != null) {
                isMyFriend = 1
            }

            // 是否在黑名单中
            var isInMyBlack = 0
            val isMyBlack = blackPlayerDC.findMyBlackById(id)
            if (isMyBlack != null) {
                isInMyBlack = 1
            }

            // 到home服 获取玩家数据
            val askMsg = QueryInfoByHomeReq.newBuilder()
            val askMsgBuilder = Home2HomeAskReq.newBuilder()
            askMsgBuilder.playerId = id
            askMsgBuilder.setQueryInfoByHomeReq(askMsg)

            session.createACS<Home2HomeAskResp>()
                .ask(session.homeShardProxy, askMsgBuilder.build(), Home2HomeAskResp::class.java)
                .whenCompleteKt { res, err ->
                    if (err != null || res == null) {
                        normalLog.lzWarn { "查询玩家失败:{$res}" }
                        return@whenCompleteKt
                    }
                    askWorld(session, id, isMyFriend, isInMyBlack, res, worldId)
                }
        }
    }

    // 到world服 获取玩家数据
    private fun askWorld(
        session: PlayerActor,
        id: Long,
        isMyFriend: Int,
        isInMyBlack: Int,
        resp: Home2HomeAskResp,
        worldId: Long
    ) {
        val res = resp.queryInfoByHomeRt
        val rt = OtherPersonalPowerRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val playerInfoBuilder = PlayerInFo.newBuilder()
        playerInfoBuilder.isMyFriend = isMyFriend
        playerInfoBuilder.isMyBlackFriend = isInMyBlack

        // 请求参数
        val playerInfo = res.playerInfo
        playerInfoBuilder.name = playerInfo.name
        playerInfoBuilder.shortName = playerInfo.shortName
        playerInfoBuilder.id = playerInfo.id

        playerInfoBuilder.allianceName = playerInfo.allianceName
        playerInfoBuilder.allianceShortName = playerInfo.allianceShortName
        playerInfoBuilder.allianceId = playerInfo.allianceId
        playerInfoBuilder.addAllPositions(playerInfo.positionsList)
        playerInfoBuilder.photoProtoId = playerInfo.photoProtoId
        playerInfoBuilder.kingLv = playerInfo.kingLv
        playerInfoBuilder.kingExp = playerInfo.kingExp
        playerInfoBuilder.vipLv = playerInfo.vipLv
        playerInfoBuilder.mainHeroProtoId = playerInfo.mainHeroProtoId
        playerInfoBuilder.mainHeroStarLv = playerInfo.mainHeroStarLv
        playerInfoBuilder.mainHeroAdvLv = playerInfo.mainHeroAdvLv

        val bagInfoList = res.bagInfoList

        bagInfoList.forEach {
            val bagInfoBuilder = BagInfo.newBuilder()
            bagInfoBuilder.itemId = it.itemId
            bagInfoBuilder.itemProtoId = it.itemProtoId
            bagInfoBuilder.num = it.num
            bagInfoBuilder.itemType = it.itemType
            bagInfoBuilder.equipOnAddress = it.equipOnAddress
            bagInfoBuilder.equipLv = it.equipLv
            bagInfoBuilder.equipExp = it.equipExp
            for (prop in it.propsList) {
                val propBuilder = EquipProps.newBuilder()
                propBuilder.propAddress = prop.propAddress
                for (propValues in prop.propValuesList) {
                    val equipPropBuilder = EquipProp.newBuilder()
                    equipPropBuilder.propType = propValues.propType
                    equipPropBuilder.propValue = propValues.propValue
                    propBuilder.addPropValues(equipPropBuilder)
                }
                bagInfoBuilder.addProps(propBuilder)
            }
            for (kingEquipCardInfo in it.kingEquipCardInfosList) {
                val kingEquipCardInfoBuilder = KingEquipCardInfo.newBuilder()
                kingEquipCardInfoBuilder.address = kingEquipCardInfo.address
                kingEquipCardInfoBuilder.cardProtoId = kingEquipCardInfo.cardProtoId
                bagInfoBuilder.addKingEquipCardInfos(kingEquipCardInfoBuilder)
            }
            rt.addBagInfo(bagInfoBuilder)
        }

        val askMsg = QueryInfoByWorldAskReq.newBuilder()
        askMsg.targetId = id

        // 异步请求
        session.createACS<Home2WorldAskResp>()
            .ask(session.worldShardProxy, session.fillHome2WorldAskMsgHeader {
                it.setQueryInfoByWorldAskReq(askMsg)
            }, Home2WorldAskResp::class.java)
            .whenCompleteKt { askResp, worldErr ->

                try {
                    when {
                        worldErr != null -> {
                            normalLog.lzWarn { "查询玩家失败:{$askResp}" }
                            rt.rt = ResultCode.ASK_ERROR1.code
                            session.sendMsg(MsgType.OtherPersonalPower_1576, rt.build())
                            return@whenCompleteKt
                        }

                        askResp == null -> {
                            normalLog.lzWarn { "查询玩家失败:{$askResp}" }
                            rt.rt = ResultCode.ASK_ERROR2.code
                            session.sendMsg(MsgType.OtherPersonalPower_1576, rt.build())
                            return@whenCompleteKt
                        }

                        else -> {
                            val prisonInfo = askResp.queryInfoByWorldAskRt.prisonInfo
                            if (prisonInfo.playerId != 0L) {
                                val prisonBuilder = MyPrisonInfo.newBuilder()
                                prisonBuilder.playerId = prisonInfo.playerId
                                prisonBuilder.photoId = prisonInfo.photoId
                                prisonBuilder.allianceShortName = prisonInfo.allianceShortName
                                prisonBuilder.playerName = prisonInfo.playerName
                                prisonBuilder.x = prisonInfo.x
                                prisonBuilder.y = prisonInfo.y
                                prisonBuilder.areaNo = prisonInfo.areaNo
                                prisonBuilder.ransom = prisonInfo.ransom
                                prisonBuilder.rewardGold = prisonInfo.rewardGold
                                rt.myPrisonInfo = prisonBuilder.build()
                            }


                            playerInfoBuilder.fightValue = askResp.queryInfoByWorldAskRt.fightValue
                            playerInfoBuilder.killSoliderNum = askResp.queryInfoByWorldAskRt.killSoliderNum
                            playerInfoBuilder.currentPos = askResp.queryInfoByWorldAskRt.currentPos
                            rt.playerInFo = playerInfoBuilder.build()

                            session.sendMsg(MsgType.OtherPersonalPower_1576, rt.build())
                        }
                    }

                } catch (e: Exception) {
                    normalLog.error("QueryInfoByWorldAskReq Error!", e)
                    rt.rt = ResultCode.ASK_ERROR3.code
                    session.sendMsg(MsgType.OtherPersonalPower_1576, rt.build())
                }

            }

    }
}


