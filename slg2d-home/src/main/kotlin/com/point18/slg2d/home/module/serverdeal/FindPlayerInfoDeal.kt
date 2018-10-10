package com.point18.slg2d.home.module.serverdeal

import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus5
import com.point18.slg2d.home.module.askDeal.H2HAsk
import pb4server.*
import java.util.*

class FindPlayerInfoDeal : H2HAsk, HomeHelperPlus5<HomePlayerDC, VipDC, HeroDC, BagDC, NewEquipDC>(
    HomePlayerDC::class.java, VipDC::class.java, HeroDC::class.java, BagDC::class.java, NewEquipDC::class.java
) {

    override fun dealHomeAsk(session: PlayerActor, req: Home2HomeAskReq, resp: Home2HomeAskResp.Builder) {
        prepare(session) { homePlayerDC, vipDC, heroDC, bagDC, newEquipDC ->
            val rt = QueryInfoByHomeRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code
            val playerInfo = PlayerInFo.newBuilder()
            playerInfo.fightValue = 0
            playerInfo.killSoliderNum = 0
            playerInfo.isMyFriend = 0
            playerInfo.isMyBlackFriend = 0

            val player = homePlayerDC.player
            // 本服的玩家
            playerInfo.name = player.name
            playerInfo.shortName = player.allianceNickName
            playerInfo.id = player.playerId

            if (player.allianceId != 0L) {
                playerInfo.allianceName = player.allianceName
                playerInfo.allianceShortName = player.allianceShortName
                playerInfo.allianceId = player.allianceId
            }

            for ((p, _) in player.alliancePosMap) {
                playerInfo.addPositions(p)
            }

            playerInfo.photoProtoId = player.photoProtoId
            playerInfo.kingLv = player.kingLv
            playerInfo.kingExp = player.kingExp
            playerInfo.vipLv = vipDC.getVipLv()

            val mainHero = heroDC.findHeroById(player.mainHeroId)
            if (mainHero != null) {
                playerInfo.mainHeroProtoId = mainHero.protoId
                playerInfo.mainHeroStarLv = mainHero.advLv
                playerInfo.mainHeroAdvLv = mainHero.awake
                rt.mainHeroPrisonPlayerId = mainHero.mainHeroPrisonPlayerId
            }

            // 君主装备信息
            val equips = newEquipDC.findKingEquipsByPlayerId(bagDC, player.playerId)
            val bagInfoList = LinkedList<BagInfo>()
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
                bagInfoList.add(bagBuilder.build())
            }
            rt.addAllBagInfo(bagInfoList)
            rt.playerInfo = playerInfo.build()
            resp.setQueryInfoByHomeRt(rt)
        }
    }
}