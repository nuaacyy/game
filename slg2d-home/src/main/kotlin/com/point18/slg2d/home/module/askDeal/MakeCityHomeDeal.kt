package com.point18.slg2d.home.module.askDeal

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.commonfunc.processConfig
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.dealCastleInnerCityFinish
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus8
import com.point18.slg2d.home.hpm
import pb4server.*

class MakeCityHomeDeal :
    W2HAsk,
    HomeHelperPlus8<HomePlayerDC, InnerCityDC, HeroDC, BagDC, KingEquipPlanDC, SkinDC, GiftBagRecordDC, QuotaBagDC>(
        HomePlayerDC::class.java, InnerCityDC::class.java, HeroDC::class.java, BagDC::class.java,
        KingEquipPlanDC::class.java, SkinDC::class.java, GiftBagRecordDC::class.java, QuotaBagDC::class.java
    ) {

    override fun dealWorldAsk(session: PlayerActor, req: World2HomeAskReq, resp: World2HomeAskResp.Builder) {
        prepare(session) { homePlayerDC: HomePlayerDC, innerCityDC: InnerCityDC,
                           heroDC: HeroDC, bagDC: BagDC, kingEquipPlanDC: KingEquipPlanDC,
                           skinDC: SkinDC, giftBagRecordDC: GiftBagRecordDC, quotaBagDC: QuotaBagDC ->
            val reqMsg = req.makeCityAskHomeReq
            val rt = homeMakeCity(
                reqMsg,
                homePlayerDC,
                innerCityDC,
                heroDC,
                bagDC,
                kingEquipPlanDC,
                skinDC,
                giftBagRecordDC,
                quotaBagDC
            )
            resp.makeCityAskHomeRt = rt
        }
    }

    private fun homeMakeCity(
        reqMsg: MakeCityAskHomeReq,
        homePlayerDC: HomePlayerDC, innerCityDC: InnerCityDC,
        heroDC: HeroDC, bagDC: BagDC, kingEquipPlanDC: KingEquipPlanDC,
        skinDC: SkinDC, giftBagRecordDC: GiftBagRecordDC, quotaBagDC: QuotaBagDC
    ): MakeCityAskHomeRt {

        val account = reqMsg.account
        val worldId = reqMsg.worldId

        val osdkUserId = reqMsg.account
        val channelId = reqMsg.channelId
        val accOpId = reqMsg.accOpId

        return createPlayer(
            reqMsg.playerId,
            worldId,
            reqMsg.areaNo,
            reqMsg.castleId,
            reqMsg.playerName,
            account,
            osdkUserId,
            channelId,
            accOpId,
            homePlayerDC,
            innerCityDC,
            heroDC,
            bagDC,
            kingEquipPlanDC,
            skinDC,
            giftBagRecordDC,
            quotaBagDC
        )
    }

    private fun createPlayer(
        playerId: Long, worldId: Long, areaNo: Int, castleId: Long, playerName: String,
        account: String, osdkUserId: String, channelId: String, accOpId: Int,
        homePlayerDC: HomePlayerDC, innerCityDC: InnerCityDC,
        heroDC: HeroDC, bagDC: BagDC, kingEquipPlanDC: KingEquipPlanDC,
        skinDC: SkinDC, giftBagRecordDC: GiftBagRecordDC, quotaBagDC: QuotaBagDC
    ): MakeCityAskHomeRt {
        // 创建Player
        val rt = MakeCityAskHomeRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        val initHeroList = mutableListOf<MakeCityInitHero>()
        for (heroProtoId in pcs.basicProtoCache.guideGetHero) {
            val heroId = hpm.generateObjIdNew()
            initHeroList.add(MakeCityInitHero(heroId, heroProtoId))

            val heroBuilder = HeroForWorldMakeCity.newBuilder()
            heroBuilder.heroId = heroId
            heroBuilder.heroProtoId = heroProtoId
            rt.addInitHeroMap(heroBuilder)
        }

        try {
            makeCity(
                worldId, areaNo, playerId, account, "", osdkUserId,
                channelId, accOpId, castleId, playerName, initHeroList,
                homePlayerDC, innerCityDC, heroDC, bagDC, kingEquipPlanDC,
                skinDC, giftBagRecordDC, quotaBagDC
            )
        } catch (e: Exception) {
            normalLog.error("makeCity Error!", e)
            rt.rt = ResultCode.ASK_ERROR3.code
        }

        return rt.build()
    }

    private fun makeCity(
        worldId: Long, areaNo: Int, playerId: Long, user: String, pwd: String, osdkUserId: String,
        channelId: String, opId: Int, castleId: Long, playerName: String, initHeroList: List<MakeCityInitHero>,
        homePlayerDC: HomePlayerDC, innerCityDC: InnerCityDC, heroDC: HeroDC, bagDC: BagDC,
        kingEquipPlanDC: KingEquipPlanDC, skinDC: SkinDC, giftBagRecordDC: GiftBagRecordDC, quotaBagDC: QuotaBagDC
    ): HomePlayer {
        // 创建玩家信息
        val playerProto = pcs.creatingPropertiesCache.proto
        val nowT = getNowTime()

        val player = HomePlayer()
        player.playerId = playerId
        player.worldId = worldId
        player.user = user
        player.pwd = pwd
        player.osdkUserId = osdkUserId
        player.channelId = channelId
        player.opId = opId
        player.createAccTime = nowT
        player.name = playerName
        player.decree = pcs.basicProtoCache.initialEnergy
        player.decreeLimit = pcs.basicProtoCache.energyLimit
        player.decreeTime = nowT
        player.power = playerProto.powerValue
        player.accType = NormalAcc
        player.birthTime = nowT
        player.photoProtoId = pcs.basicProtoCache.defaultPhoto
        player.photoFreeCount = pcs.basicProtoCache.photoFreeCount
        player.wood = playerProto.resource.toLong()
        player.iron = playerProto.resource.toLong()
        player.stone = playerProto.resource.toLong()
        player.food = playerProto.resource.toLong()
        player.gold = playerProto.acer.toLong()
        player.coin = playerProto.gold.toLong()
        player.heroExpPool = playerProto.heroExp
        player.bindGold = playerProto.boundDiamond.toLong()
        player.chestFreePrizeTime = nowT
        player.chestKillRefTime = nowT
        player.honor = playerProto.meritoriousService.toLong()
        player.rookieEndTime = nowT + pcs.basicProtoCache.playerProtectDuration
        player.guideStep = pcs.basicProtoCache.guideFirstId
        player.lastWaijiaoCount = nowT
        player.intHeroNum = pcs.basicProtoCache.interiorHeroFirstNum
        player.lastAllianceResearchTime = nowT
        player.allianceResearchRewardTime = nowT
        player.kingLv = 1
        player.lastAllianceGiftGetTime = nowT
        player.kingEquipBagNum = pcs.basicProtoCache.addEquipmentPositionMin
        player.lastBuyStrengthTime = nowT
        player.nowUseKingEquipPlan = 1
        player.maxMark = pcs.basicProtoCache.favoritesMarkStart
        player.focusCastleId = castleId
        player.inMoveServer = 0
        player.areaNo = areaNo


        // 初始化玩家科技效果值
        val updateMap = hashMapOf<Int, Int>()
        updateMap[AddStorageLimit] = playerProto.storageLimit
        updateMap[CanHelpNum] = pcs.basicProtoCache.helpStarTimes
        updateMap[MakeSoliderNum] = pcs.basicProtoCache.trainStarNum
        updateMap[CureMaxNum] = pcs.basicProtoCache.cureStarNum
        updateMap[AddCityCOST] = pcs.creatingPropertiesCache.proto.heroCost.toInt()
        player.putInnerBuildingEffectInfoMap(updateMap)

        // 创建武将
        homePlayerDC.createPlayer(player)

        // 新玩家进入写一条武将
        for (vo in initHeroList) {
            val hero = heroDC.createHero(playerId, vo.id, vo.protoId)
            // 设置为守城英雄
            if (hero != null) {
                if (player.mainHeroId == 0L) {
                    player.mainHeroId = hero.id
                    hero.mainHeroState = MAIN_HERO
                    hero.intHeroAddress = 1
                }
            }
        }

        // 创建礼包购买记录
        giftBagRecordDC.createRecord(player.playerId)

        // 创建满额礼包数据
        quotaBagDC.createRecord(player.playerId)

        // 初始化玩家的背包信息
        bagDC.createBag(playerId, NORMAL_BAG)
        bagDC.createBag(playerId, HERO_BAG)
        bagDC.createBag(playerId, KING_BAG)

        // 君主装备第一页初始化
        kingEquipPlanDC.createKingEquipPlan(1, "1", hashMapOf(), playerId)

        //初始化皮肤
        val skinProto =
            requireNotNull(com.point18.slg2d.common.pc.pcs.skinAttributeCache.skinAttributeProtoMap[com.point18.slg2d.common.pc.pcs.basicProtoCache.defaultSkinId])
        skinDC.createSkin(skinProto.type, skinProto.star)

        dealCastleInnerCityFinish(player,innerCityDC, castleId, playerId)

        return player
    }

    /**
    创建角色的时候进行一些玩法模块的数据初始化
     */
    data class MakeCityInitHero(var id: Long, var protoId: Int)
}
