package com.point18.slg2d.home.common

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus6
import com.point18.slg2d.home.msgnotice.createAllianceMissionGiftNumChangeNotifier
import com.point18.slg2d.home.msgnotice.createRefreshResourceNotifier
import com.point18.slg2d.home.msgnotice.createStoreLimitChangeNotifier
import com.point18.slg2d.home.msgnotice.createYieldChangeNotifier
import xyz.ariane.util.lzDebug
import java.util.Arrays.asList

class RefreshResourceHelper(
    private val effectHelper: ResearchEffectHelper = ResearchEffectHelper()
) : HomeHelperPlus6<HomePlayerDC, VipDC, InnerCityDC, ResourceYieldDC, HomeSyncDC, HeroDC>(
    HomePlayerDC::class.java, VipDC::class.java, InnerCityDC::class.java, ResourceYieldDC::class.java,
    HomeSyncDC::class.java, HeroDC::class.java, asList(effectHelper)
) {

    /** 刷新玩家资源：在产量无变化的情况下调用，刷新玩家的资源产量，并跟客户端同步资源数量。 **/
    fun refreshResource(
        session: PlayerActor,
        what: ResYieldWhat
    ) {
        return prepare(session) { homePlayerDC: HomePlayerDC, vipDC: VipDC, innerCityDC: InnerCityDC,
                                  resourceYieldDC: ResourceYieldDC, homeSyncDC: HomeSyncDC, heroDC: HeroDC ->
            val player = homePlayerDC.player
            val resYield = resourceYieldDC.resourceYield

            val now = getNowTime()

            //刷新存储上限
            val limitChange = refreshResourceLimit(session, resourceYieldDC)

            if (limitChange) {
                //推送产量上限变化消息
                val notifier = createStoreLimitChangeNotifier(
                    resYield.woodLimit,
                    resYield.ironLimit,
                    resYield.stoneLimit,
                    resYield.foodLimit,
                    resYield.coinLimit
                )
                notifier.notice(session)
            }

            // 刷新玩家的资源数量：但是不保存到数据库
            val rst = refreshPlayerResource(session, now)

            // 向客户端推送临时计算的资源数量信息
            val refreshResourceNotifier = createRefreshResourceNotifier(
                player,
                rst.wood,
                rst.food,
                rst.iron,
                rst.stone,
                player.decree,
                now
            )
            refreshResourceNotifier.notice(session)

            // 如果只刷新资源数量，不重新计算产量
            if (what == NullResYield) {
                return@prepare
            }

            // 重新计算产量
            updatePlayerResourceYield(session, what, now)

            //保存玩家的资源数量
            player.wood = rst.wood
            player.iron = rst.iron
            player.stone = rst.stone
            player.food = rst.food
            player.coin = rst.coin

            //向客户端推送资源产量
            val yieldChangeNotifier = createYieldChangeNotifier(
                resYield.totalWood,
                resYield.totalIron,
                resYield.totalStone,
                resYield.totalFood,
                resYield.totalCoin,
                (resYield.calTime / 1000).toInt()
            )
            yieldChangeNotifier.notice(session)

            player.allianceGiftNum = 0
            val msgNotifier =
                createAllianceMissionGiftNumChangeNotifier(RESET_ALLIANCE_INFO, 0)
            msgNotifier.notice(session)
        }
    }

    data class CalcResourceNotUpdateResult(
        val wood: Long,
        val iron: Long,
        val stone: Long,
        val food: Long,
        val coin: Long
    )

    /** 只计算玩家的资源数量，不保存数据库 **/
    fun calcResourceNotUpdate(
        session: PlayerActor,
        now: Long
    ): CalcResourceNotUpdateResult {
        return prepare(session) { homePlayerDC: HomePlayerDC, vipDC: VipDC, innerCityDC: InnerCityDC,
                                  resourceYieldDC: ResourceYieldDC, homeSyncDC: HomeSyncDC, heroDC: HeroDC ->
            refreshResourceLimit(session, resourceYieldDC)

            val rst = refreshPlayerResource(session, now)
            return@prepare CalcResourceNotUpdateResult(rst.wood, rst.iron, rst.stone, rst.food, rst.coin)
        }
    }

    /** 计算玩家的资源产量，并且保存数据库 **/
    fun updatePlayerResourceYield(
        session: PlayerActor,
        what: ResYieldWhat,
        now: Long
    ) {
        return prepare(session) { homePlayerDC: HomePlayerDC, vipDC: VipDC, innerCityDC: InnerCityDC,
                                  resourceYieldDC: ResourceYieldDC, homeSyncDC: HomeSyncDC, heroDC: HeroDC ->
            val player = homePlayerDC.player
            val resYield = resourceYieldDC.resourceYield

            if (what and BuildingResYield == BuildingResYield) {
                var wood0 = 0
                var iron0 = 0
                var stone0 = 0
                var food0 = 0
                var coin0 = 0
                val castleBuildings = innerCityDC.findInnerBuildingsFromCastleId(player.focusCastleId)
                for (building in castleBuildings) {
                    if (building.lv == 0) {
                        continue
                    }
                    val buildingProto =
                        pcs.innerBuildingDataCache.fetchProtoByTypeLv(building.cityType, building.lv)
                            ?: error("找不到建筑类型:${building.cityType}建筑等级:${building.lv}的模板")
                    for ((effType, effValue) in buildingProto.upEff) {
                        when (effType) {
                            WoodCost ->
                                wood0 += effValue
                            IronCost ->
                                iron0 += effValue
                            StoneCost ->
                                stone0 += effValue
                            FoodCost ->
                                food0 += effValue
                            CoinCost ->
                                coin0 += effValue
                        }
                    }

                    // 计算城池建筑设施的基本资源产量
                    resYield.buildingWood = wood0
                    resYield.buildingIron = iron0
                    resYield.buildingStone = stone0
                    resYield.buildingFood = food0
                    resYield.buildingCoin = coin0
                }
            }

            //重新计算联盟加成
            if (what and AllianceResYield == AllianceResYield) {
                resYield.allianceWood = 0
                resYield.allianceIron = 0
                resYield.allianceStone = 0
                resYield.allianceFood = 0
            }

            //重新计算科技加成
            if (what and ResearchResYield == ResearchResYield) {
                val wood0 = player.researchEffectInfoMap[ResearchEffectWoodAdd] ?: 0
                val iron0 = player.researchEffectInfoMap[ResearchEffectIronAdd] ?: 0
                val stone0 = player.researchEffectInfoMap[ResearchEffectStoneAdd] ?: 0
                val food0 = player.researchEffectInfoMap[ResearchEffectFoodAdd] ?: 0
                val coin0 = player.researchEffectInfoMap[ResearchEffectCoinAdd] ?: 0

                resYield.researchWood = wood0
                resYield.researchIron = iron0
                resYield.researchStone = stone0
                resYield.researchFood = food0
                resYield.researchCoin = coin0
            }

            // 重新计算城池加成
            if (what and NpcCityResYield == NpcCityResYield) {
                resYield.npcCityWood = 0
                resYield.npcCityIron = 0
                resYield.npcCityStone = 0
                resYield.npcCityFood = 0
            }

            // 重新计算vip加成
            if (what and VipResYield == VipResYield) {
                val vipEffectMap = vipDC.getVipEffectInfoMapByPlayerId()
                resYield.vipWood = vipEffectMap[ResearchEffectWoodAdd] ?: 0
                resYield.vipIron = vipEffectMap[ResearchEffectIronAdd] ?: 0
                resYield.vipStone = vipEffectMap[ResearchEffectStoneAdd] ?: 0
                resYield.vipFood = vipEffectMap[ResearchEffectFoodAdd] ?: 0
                resYield.vipIron = vipEffectMap[ResearchEffectCoinAdd] ?: 0
            }

            // 重新计算天赋加成
            if (what and TalentResYield == TalentResYield) {
                val talentMap = effectHelper.findTalentEffectInfoMapByPlayer(heroDC, player)
                resYield.talentWood = talentMap[ResearchEffectWoodAdd] ?: 0
                resYield.talentIron = talentMap[ResearchEffectIronAdd] ?: 0
                resYield.talentStone = talentMap[ResearchEffectStoneAdd] ?: 0
                resYield.talentFood = talentMap[ResearchEffectFoodAdd] ?: 0
                resYield.talentCoin = talentMap[ResearchEffectCoinAdd] ?: 0
            }

            // 重新计算联盟成员加成
            if (what and MemberResYield == MemberResYield) {
                resYield.memWood = 0
                resYield.memIron = 0
                resYield.memStone = 0
                resYield.memFood = 0
            }

            // 重新计算维持消耗
            if (what and UseUpResYield == UseUpResYield) {
                val useUpWood = 0.0
                val useUpIron = 0.0
                val useUpStone = 0.0
                var useUpFood = 0.0
                homeSyncDC.syncData.barracksMap.forEach { soliderId, barrack ->
                    val soliderProto = pcs.soliderCache.soliderProtoMap[soliderId]
                    if (soliderProto == null) {
                        com.point18.slg2d.common.commonfunc.normalLog.lzDebug {
                            "refreshPlayerResource.kt :" +
                                " pcs.soliderCache.soliderProtoMap[barrack.soldierId] == null"
                        }
                        return@forEach
                    }
                    useUpFood += soliderProto.expendFood * (barrack.soldierNum + barrack.canCureNum + barrack.nowMakeNum +
                        barrack.nowCureNum + barrack.upNum + barrack.canEventCureNum + barrack.nowEventCureNum)
                }

                resYield.useUpWood = useUpWood.toInt()
                resYield.useUpIron = useUpIron.toInt()
                resYield.useUpStone = useUpStone.toInt()
                resYield.useUpFood = useUpFood.toInt()
            }

            // 重新计算产量总计
            val baseWoodYield = pcs.basicProtoCache.baseWoodYield + resYield.buildingWood
            resYield.totalWood = baseWoodYield +
                Math.ceil(
                    baseWoodYield *
                        (resYield.allianceWood / 100.0 +
                            resYield.researchWood / 10000 +
                            resYield.allianceResearchWood / 10000 +
                            resYield.vipWood / 10000 +
                            resYield.talentWood / 10000)
                ).toInt() +
                resYield.npcCityWood + resYield.memWood - resYield.useUpWood

            val baseIronYield = pcs.basicProtoCache.baseIronYield + resYield.buildingIron
            resYield.totalIron = baseIronYield +
                Math.ceil(
                    baseIronYield *
                        (resYield.allianceIron / 100.0 +
                            resYield.researchIron / 10000 +
                            resYield.allianceResearchIron / 10000 +
                            resYield.vipIron / 10000 +
                            resYield.talentIron / 10000)
                ).toInt() +
                resYield.npcCityIron + resYield.memIron - resYield.useUpIron

            val baseStoneYield = pcs.basicProtoCache.baseStoneYield + resYield.buildingStone
            resYield.totalStone = baseStoneYield +
                Math.ceil(
                    baseStoneYield *
                        (resYield.allianceStone / 100.0 +
                            resYield.researchStone / 10000 +
                            resYield.allianceResearchStone / 10000 +
                            resYield.vipStone / 10000 +
                            resYield.talentStone / 10000)
                ).toInt() +
                resYield.npcCityStone + resYield.memStone - resYield.useUpStone

            val baseFoodYield = pcs.basicProtoCache.baseFoodYield + resYield.buildingFood
            resYield.totalFood = baseFoodYield +
                Math.ceil(
                    baseFoodYield *
                        (resYield.allianceFood / 100.0 +
                            resYield.researchFood / 10000 +
                            resYield.allianceResearchFood / 10000 +
                            resYield.vipFood / 10000 +
                            resYield.talentFood / 10000)
                ).toInt() +
                resYield.npcCityFood + resYield.memFood - resYield.useUpFood

            val baseCoinYield = pcs.basicProtoCache.baseCoinYield + resYield.buildingCoin
            resYield.totalCoin = baseCoinYield +
                baseCoinYield *
                (resYield.researchCoin / 10000 +
                    resYield.vipCoin / 10000 +
                    resYield.talentCoin / 10000) -
                resYield.useUpCoin

            //保存玩家资源产量
            resYield.calTime = now
        }
    }

    data class RefreshResourceResult(
        val wood: Long,
        val iron: Long,
        val stone: Long,
        val food: Long,
        val coin: Long
    )

    /** 刷新玩家的资源数量:不保存到数据库,只是计算玩家的资源数量 **/
    fun refreshPlayerResource(
        session: PlayerActor,
        now: Long
    ): RefreshResourceResult {
        return prepare(session) { homePlayerDC: HomePlayerDC, vipDC: VipDC, innerCityDC: InnerCityDC,
                                  resourceYieldDC: ResourceYieldDC, homeSyncDC: HomeSyncDC, heroDC: HeroDC ->
            val player = homePlayerDC.player
            val resYield = resourceYieldDC.resourceYield

            var wood: Long
            var iron: Long
            var stone: Long
            var food: Long
            var coin: Long

            //计算时间差
            val sub = (now - resYield.calTime) / 1000

            //木料
            val woodMax = resYield.woodLimit
            if (player.wood < woodMax) {
                wood = (player.wood + resYield.totalWood * sub / 3600.0).toLong()
                if (wood > woodMax) {
                    //资源刷新不可能超过仓库上限
                    wood = woodMax
                } else if (wood < 0) {
                    //即使产量总计是负数，扣减后结果也不能为小于零
                    wood = 0
                }
            } else {
                //如果已超过仓库上限，不能再刷新资源
                wood = player.wood
            }

            //铁矿
            val ironMax = resYield.ironLimit
            if (player.iron < ironMax) {
                iron = (player.iron + resYield.totalIron * sub / 3600.0).toLong()
                if (iron > ironMax) {
                    iron = ironMax
                } else if (iron < 0) {
                    iron = 0
                }
            } else {
                iron = player.iron
            }

            //石料
            val stoneMax = resYield.stoneLimit
            if (player.stone < stoneMax) {
                stone = (player.stone + resYield.totalStone * sub / 3600.0).toLong()
                if (stone > stoneMax) {
                    stone = stoneMax
                } else if (stone < 0) {
                    stone = 0
                }
            } else {
                stone = player.stone
            }

            //粮食
            val foodMax = resYield.foodLimit
            if (player.food < foodMax) {
                food = (player.food + resYield.totalFood * sub / 3600.0).toLong()
                if (food > foodMax) {
                    food = foodMax
                } else if (food < 0) {
                    food = 0
                }
            } else {
                food = player.food
            }

            //金币
            val coinMax = resYield.coinLimit
            if (player.coin < coinMax) {
                coin = (player.coin + resYield.totalCoin * sub / 3600.0).toLong()
                if (coin > coinMax) {
                    coin = coinMax
                } else if (coin < 0) {
                    coin = 0
                }
            } else {
                coin = player.coin
            }

            return@prepare RefreshResourceResult(wood, iron, stone, food, coin)
        }
    }

    //刷新产量上限
    private fun refreshResourceLimit(
        session: PlayerActor,
        resourceYieldDC: ResourceYieldDC
    ): Boolean {
        val resYield = resourceYieldDC.resourceYield

        var limitChange = false

        val woodLimit =
            pcs.basicProtoCache.baseWoodLimit + effectHelper.getResearchEffectValue(
                session,
                NO_FILTER,
                WoodMaxAdd
            )
        if (resYield.woodLimit != woodLimit.toLong()) {
            limitChange = true
            resYield.woodLimit = woodLimit.toLong()
        }

        val ironLimit =
            pcs.basicProtoCache.baseIronLimit + effectHelper.getResearchEffectValue(
                session,
                NO_FILTER,
                IronMaxAdd
            )
        if (resYield.ironLimit != ironLimit.toLong()) {
            limitChange = true
            resYield.ironLimit = ironLimit.toLong()
        }

        val stoneLimit = pcs.basicProtoCache.baseStoneLimit + effectHelper.getResearchEffectValue(
            session,
            NO_FILTER,
            StoneMaxAdd
        )
        if (resYield.stoneLimit != stoneLimit.toLong()) {
            limitChange = true
            resYield.stoneLimit = stoneLimit.toLong()
        }

        val foodLimit =
            pcs.basicProtoCache.baseFoodLimit + effectHelper.getResearchEffectValue(
                session,
                NO_FILTER,
                FoodMaxAdd
            )
        if (resYield.foodLimit != foodLimit.toLong()) {
            limitChange = true
            resYield.foodLimit = foodLimit.toLong()
        }

        val coinLimit =
            pcs.basicProtoCache.baseCoinLimit + effectHelper.getResearchEffectValue(
                session,
                NO_FILTER,
                CoinMaxAdd
            )
        if (resYield.coinLimit != coinLimit.toLong()) {
            limitChange = true
            resYield.coinLimit = coinLimit.toLong()
        }

        return limitChange
    }
}