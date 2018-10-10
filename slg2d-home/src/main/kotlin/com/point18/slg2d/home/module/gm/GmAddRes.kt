package com.point18.slg2d.home.module.gm

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.ACTION_GM_GIVE_HERO_EQUIP_RES
import com.point18.slg2d.common.constg.ACTION_GM_GIVE_HERO_STAR_RES
import com.point18.slg2d.common.constg.NullResYield
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.pc.resVoCombine
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.RefreshResourceHelper
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.common.updateAllianceMemberInfo
import com.point18.slg2d.home.dc.HeroDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import java.util.*
import java.util.Arrays.asList

class GmAddRes(
    private val resHelper: ResHelper = ResHelper(),
    private val refHelper: RefreshResourceHelper = RefreshResourceHelper()
) : GmCommand, HomeHelperPlus2<HomePlayerDC, HeroDC>(
    HomePlayerDC::class.java, HeroDC::class.java,
    asList(resHelper, refHelper)
) {

    override fun exec(session: PlayerActor, message: String) {
        prepare(session) { homePlayerDC: HomePlayerDC, heroDC: HeroDC ->
            // 格式1： -gm add 类型 数量
            // 格式2： -gm changeCD building
            val messages = message.split(" ")
            if (messages.size == 1) {
                return@prepare
            }

            val player = homePlayerDC.player

            if (messages.size != 4) {
                return@prepare
            }

            val gmAddType = messages[2]
            val gmAddNum = messages[3].toIntOrNull()
            if (gmAddNum == null) {
                return@prepare
            }

            // 1.添加资源：粮食，铁矿，石头，铜钱，元宝
            // 增加粮食
            if (gmAddType == "food") {
                player.food += gmAddNum
            }

            // 增加铁矿
            if (gmAddType == "iron") {
                player.iron += gmAddNum
            }

            // 增加石头
            if (gmAddType == "stone") {
                player.stone += gmAddNum
            }

            // 增加木材
            if (gmAddType == "wood") {
                player.wood += gmAddNum
            }

            // 增加元宝
            if (gmAddType == "gold") {
                player.gold += gmAddNum
            }

            // 增加铜钱
            if (gmAddType == "coin") {
                player.coin += gmAddNum
            }

            // 增加荣誉
            if (gmAddType == "honor") {
                player.honor += gmAddNum
                if (player.allianceId != 0L) {
                    val updateInfoMap = mutableMapOf<Int, String>()
                    updateInfoMap[com.point18.slg2d.common.constg.UpdateHonor] = gmAddNum.toString()
                    updateAllianceMemberInfo(session, player.allianceId, player.playerId, updateInfoMap)
                }
            }

            // 增加政令
            if (gmAddType == "bindGold") {
                player.bindGold += gmAddNum
            }

            // 增加竞技币
            if (gmAddType == "jjcCoin") {
                player.jjcCoin += gmAddNum
            }

            // 金币
            if (gmAddType == "goldCoin") {
                player.goldCoin += gmAddNum
            }

            // 银币
            if (gmAddType == "silverCoin") {
                player.silverCoin += gmAddNum
            }

            // jjcScore 增加竞技积分

            val nowTime = getNowTime()

            // 增加联盟币
            if (gmAddType == "allianceCoin") {
                player.allianceCoin += gmAddNum
            }

            // 增加英雄经验池
            if (gmAddType == "heroExpPool") {
                player.heroExpPool += gmAddNum
            }

            //英雄升星材料
            if (gmAddType == "heroStarUpRes") {
                val heroList = heroDC.findHeroList()
                val totalRes = LinkedList<ResVo>()
                for (hero in heroList) {
                    val heroStarProtoMap = pcs.heroStarProtoCache.heroStarProtoCache[hero.protoId] ?: continue
                    val heroStarProto = heroStarProtoMap[hero.advLv] ?: continue
                    totalRes += heroStarProto.starPropsResVo
                }
                val finalRes = resVoCombine(totalRes)
                resHelper.addRes(session, ACTION_GM_GIVE_HERO_STAR_RES, player, finalRes)
            }

            //英雄装备升级材料
            if (gmAddType == "heroEquipUpRes") {
                val heroList = heroDC.findHeroList()
                val totalRes = LinkedList<ResVo>()
                for (hero in heroList) {
                    for ((heroTrophiesId, _) in hero.heroEquipInfoMap) {
                        val heroTrophiesProtoMap =
                            pcs.heroTrophiesRankProtoCache.heroTrophiesRanksMap[heroTrophiesId] ?: continue
                        val heroTrophiesProto = heroTrophiesProtoMap[hero.advLv] ?: continue
                        totalRes += heroTrophiesProto.heroTrophiesPropsResVo
                    }
                }
                val finalRes = resVoCombine(totalRes)
                resHelper.addRes(session, ACTION_GM_GIVE_HERO_EQUIP_RES, player, finalRes)
            }

            // 保存资源

            //推送资源信息
            refHelper.refreshResource(session, NullResYield)
        }
    }

}
