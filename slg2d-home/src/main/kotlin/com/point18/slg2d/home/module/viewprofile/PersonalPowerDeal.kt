package com.point18.slg2d.home.module.viewprofile

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus5
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.*
import pb4server.Home2WorldAskResp
import pb4server.QueryInfoByWorldAskReq
import xyz.ariane.util.lzWarn

// 查询玩家个人信息
class PersonalPowerDeal : HomeClientMsgDeal, HomeHelperPlus5<
        HomePlayerDC, VipDC, HeroDC, NewEquipDC, BagDC>(
    HomePlayerDC::class.java,
    VipDC::class.java,
    HeroDC::class.java,
    NewEquipDC::class.java,
    BagDC::class.java
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        val pId = (msg as PersonalPower).playerId
        playerInFoFunc(session, pId)
    }

    private fun playerInFoFunc(session: PlayerActor, id: Long) {
        prepare(session) { homePlayerDC: HomePlayerDC, vipDC: VipDC, heroDC: HeroDC, newEquipDC: NewEquipDC, bagDC: BagDC ->
            val rt = PersonalPowerRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code
            val playerInfoBuilder = PlayerInFo.newBuilder()
            val playerId = session.playerId
            if (playerId != id) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                session.sendMsg(MsgType.PersonalPower_17, rt.build())
                return@prepare
            }
            val player = homePlayerDC.player

            ///// 赌场解锁，过后删除//////////
            player.openCasinoTime = getNowTime()
            ////////////////////////////

            // 本服的玩家
            playerInfoBuilder.name = player.name
            playerInfoBuilder.shortName = player.allianceNickName
            playerInfoBuilder.id = player.playerId

            if (player.allianceId != 0L) {
                playerInfoBuilder.allianceName = player.allianceName
                playerInfoBuilder.allianceShortName = player.allianceShortName
                playerInfoBuilder.allianceId = player.allianceId
            }

            for ((p, _) in player.alliancePosMap) {
                playerInfoBuilder.addPositions(p)
            }

            playerInfoBuilder.photoProtoId = player.photoProtoId
            playerInfoBuilder.kingLv = player.kingLv
            playerInfoBuilder.kingExp = player.kingExp
            playerInfoBuilder.vipLv = vipDC.getVipLv()

            val mainHero = heroDC.findHeroById(player.mainHeroId)
            if (mainHero == null) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                session.sendMsg(MsgType.PersonalPower_17, rt.build())
                return@prepare
            }
            playerInfoBuilder.mainHeroProtoId = mainHero.protoId
            playerInfoBuilder.mainHeroStarLv = mainHero.advLv
            playerInfoBuilder.mainHeroAdvLv = mainHero.awake

            playerInfoBuilder.isMyFriend = 0
            playerInfoBuilder.isMyBlackFriend = 0

            // 君主装备信息
            val equips = newEquipDC.findKingEquipsByPlayerId(bagDC, player.playerId)
            for (equip in equips) {
                val bagBuilder = BagInfo.newBuilder()
                bagBuilder.itemId = equip.id
                bagBuilder.itemProtoId = equip.equipProtoId
                bagBuilder.num = equip.haveNum
                bagBuilder.itemType = 0
                bagBuilder.equipOnAddress = equip.equipOnAddress
                bagBuilder.equipLv = equip.lv
                bagBuilder.equipExp = equip.exp

                for ((address, pps) in equip.propertyMap) {
                    val equipPropsBuilder = EquipProps.newBuilder()
                    equipPropsBuilder.propAddress = address
                    for ((ppType, ppValue) in pps) {
                        val equipPropBuilder = EquipProp.newBuilder()
                        equipPropBuilder.propType = ppType
                        equipPropBuilder.propValue = ppValue
                        equipPropsBuilder.addPropValues(equipPropBuilder)
                    }
                    bagBuilder.addProps(equipPropsBuilder)
                }


                for ((address, pps) in equip.cardInfoMap) {
                    val p = KingEquipCardInfo.newBuilder()
                    p.address = address
                    p.cardProtoId = pps
                    bagBuilder.addKingEquipCardInfos(p)
                }
                rt.addBagInfo(bagBuilder)
            }

            // 获取好友信息
            val askMsg = QueryInfoByWorldAskReq.newBuilder()
            askMsg.targetId = id

            session.createACS<Home2WorldAskResp>()
                .ask(session.worldShardProxy, session.fillHome2WorldAskMsgHeader {
                    it.setQueryInfoByWorldAskReq(askMsg)
                }, Home2WorldAskResp::class.java)
                .whenCompleteKt { askResp, askErr ->

                    try {
                        when {
                            askErr != null -> {
                                normalLog.lzWarn { "查询玩家失败:{$askResp}" }
                                rt.rt = ResultCode.ASK_ERROR1.code
                                session.sendMsg(MsgType.PersonalPower_17, rt.build())
                                return@whenCompleteKt
                            }

                            askResp == null -> {
                                normalLog.lzWarn { "查询玩家失败:{$askResp}" }
                                rt.rt = ResultCode.ASK_ERROR2.code
                                session.sendMsg(MsgType.PersonalPower_17, rt.build())
                                return@whenCompleteKt
                            }

                            else -> {
                                rt.rt = askResp.queryInfoByWorldAskRt.rt
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
                                    rt.setMyPrisonInfo(prisonBuilder)
                                }
                                playerInfoBuilder.fightValue = askResp.queryInfoByWorldAskRt.fightValue
                                playerInfoBuilder.killSoliderNum = askResp.queryInfoByWorldAskRt.killSoliderNum
                                playerInfoBuilder.currentPos = askResp.queryInfoByWorldAskRt.currentPos

                                rt.setPlayerInFo(playerInfoBuilder)

                                session.sendMsg(MsgType.PersonalPower_17, rt.build())
                            }
                        }

                    } catch (e: Exception) {
                        normalLog.error("QueryInfoByWorldAskReq Error!", e)
                        rt.rt = ResultCode.ASK_ERROR3.code
                        session.sendMsg(MsgType.PersonalPower_17, rt.build())
                    }

                }

        }
    }
}


