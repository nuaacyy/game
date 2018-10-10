package com.point18.slg2d.home.dc

import com.point18.slg2d.common.homeentities.*
import com.point18.slg2d.home.AbstractDataContainer
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.constg.PlayerId
import java.io.Serializable
import java.time.Duration

class ResourceYieldDC : AbstractDataContainer<ResourceYieldEntity>() {

    lateinit var resourceYield: ResourceYield

    override fun initImpl(data: ResourceYieldEntity?, depDCRepo: DataContainerRepo) {
        if (data != null) {
            val resourceYield = wdb.recover(data) { ResourceYield() }
            this.resourceYield = resourceYield
        } else {
            val resourceYield = ResourceYield(
                0, 0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                playerId
            )

            this.resourceYield = resourceYield
            wdb.save(resourceYield)
        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): ResourceYieldEntity? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(RESOURCE_YIELD_QUERY_PLAYER)
                .setLong("playerId", playerId)
                .listNoDup<ResourceYieldEntity>()
            if (list.isEmpty()) {
                null
            } else {
                list[0]
            }
        }


        return data
    }

    fun findResourceYield(): ResourceYield {
        return this.resourceYield
    }
}

// 资源产量表
class ResourceYield(
    var buildingWood: Int,  // 设施产量：木料
    var buildingIron: Int,  // 设施产量：铁矿
    var buildingStone: Int,  // 设施产量：石料
    var buildingFood: Int,  // 设施产量：粮食
    var buildingCoin: Int,  // 设施产量：金币

    var allianceWood: Int,  // 联盟加成：木料（%）
    var allianceIron: Int,  // 联盟加成：铁矿（%）
    var allianceStone: Int,  // 联盟加成：石料（%）
    var allianceFood: Int,  // 联盟加成：粮食（%）

    var researchWood: Int,  // 科技加成：木料（%）
    var researchIron: Int,  // 科技加成：铁矿（%）
    var researchStone: Int,  // 科技加成：石料（%）
    var researchFood: Int,  // 科技加成：粮食（%）
    var researchCoin: Int,  // 科技加成：金币（%）

    var allianceResearchWood: Int,  // 帮派科技加成：木料（%）
    var allianceResearchIron: Int,  // 帮派科技加成：铁矿（%）
    var allianceResearchStone: Int,  // 帮派科技加成：石料（%）
    var allianceResearchFood: Int,  // 帮派科技加成：粮食（%）

    var npcCityWood: Int,  // 城池加成：木料
    var npcCityIron: Int,  // 城池加成：铁矿
    var npcCityStone: Int,  // 城池加成：石料
    var npcCityFood: Int,  // 城池加成：粮食

    var memWood: Int,  // 同盟成员加成：木料
    var memIron: Int,  // 同盟成员加成：铁矿
    var memStone: Int,  // 同盟成员加成：石料
    var memFood: Int,  // 同盟成员加成：粮食

    var useUpWood: Int,  // 维持消耗：木料
    var useUpIron: Int,  // 维持消耗：铁矿
    var useUpStone: Int,  // 维持消耗：石料
    var useUpFood: Int,  // 维持消耗：粮食
    var useUpCoin: Int,  // 维持消耗：金币

    var calTime: Long,  // 产量计算时间
    var totalWood: Int,                // 产量总计：木料
    var totalIron: Int,                // 产量总计：铁矿
    var totalStone: Int,                // 产量总计：石料
    var totalFood: Int,                // 产量总计：粮食
    var totalCoin: Int,                // 产量总计：金币

    var vipWood: Int,  // vip加成：木料（%）
    var vipIron: Int,  // vip加成：铁矿（%）
    var vipStone: Int,  // vip加成：石料（%）
    var vipFood: Int,  // vip加成：粮食（%）
    var vipCoin: Int,  // vip加成：金币（%）

    var talentWood: Int,  // 天赋加成：木料（%）
    var talentIron: Int,  // 天赋加成：铁矿（%）
    var talentStone: Int,  // 天赋加成：石料（%）
    var talentFood: Int,  // 天赋加成：粮食（%）
    var talentCoin: Int,  // 天赋加成：金币（%）

    var woodLimit: Long,  //木材产量上限
    var ironLimit: Long,  //铁矿产量上限
    var stoneLimit: Long,  //石头产量上限
    var foodLimit: Long,  //粮食产量上限
    var coinLimit: Long,  //金币产量上限

    var playerId: Long  // 玩家ID
) : EntityWrapper<ResourceYieldEntity> {
    constructor() : this(
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0L,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0L, 0L, 0L, 0L, 0L, 0L
    )

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = playerId

    override fun toEntity(): ResourceYieldEntity {
        return ResourceYieldEntity(
            playerId,
            buildingWood,
            buildingIron,
            buildingStone,
            buildingFood,
            buildingCoin,
            allianceWood,
            allianceIron,
            allianceStone,
            allianceFood,
            researchWood,
            researchIron,
            researchStone,
            researchFood,
            researchCoin,
            allianceResearchWood,
            allianceResearchIron,
            allianceResearchStone,
            allianceResearchFood,
            npcCityWood,
            npcCityIron,
            npcCityStone,
            npcCityFood,
            memWood,
            memIron,
            memStone,
            memFood,
            useUpWood,
            useUpIron,
            useUpStone,
            useUpFood,
            useUpCoin,
            calTime,
            totalWood,
            totalIron,
            totalStone,
            totalFood,
            totalCoin,
            vipWood,
            vipIron,
            vipStone,
            vipFood,
            vipCoin,
            talentWood,
            talentIron,
            talentStone,
            talentFood,
            talentCoin,
            woodLimit,
            ironLimit,
            stoneLimit,
            foodLimit,
            coinLimit
        )
    }

    override fun wrap(entity: ResourceYieldEntity) {
        playerId = entity.playerId
        buildingWood = entity.buildingWood
        buildingIron = entity.buildingIron
        buildingStone = entity.buildingStone
        buildingFood = entity.buildingFood
        buildingCoin = entity.buildingCoin
        allianceWood = entity.allianceWood
        allianceIron = entity.allianceIron
        allianceStone = entity.allianceStone
        allianceFood = entity.allianceFood
        researchWood = entity.researchWood
        researchIron = entity.researchIron
        researchStone = entity.researchStone
        researchFood = entity.researchFood
        researchCoin = entity.researchCoin
        allianceResearchWood = entity.allianceResearchWood
        allianceResearchIron = entity.allianceResearchIron
        allianceResearchStone = entity.allianceResearchStone
        allianceResearchFood = entity.allianceResearchFood
        npcCityWood = entity.npcCityWood
        npcCityIron = entity.npcCityIron
        npcCityStone = entity.npcCityStone
        npcCityFood = entity.npcCityFood
        memWood = entity.memWood
        memIron = entity.memIron
        memStone = entity.memStone
        memFood = entity.memFood
        useUpWood = entity.useUpWood
        useUpIron = entity.useUpIron
        useUpStone = entity.useUpStone
        useUpFood = entity.useUpFood
        useUpCoin = entity.useUpCoin
        calTime = entity.calTime
        totalWood = entity.totalWood
        totalIron = entity.totalIron
        totalStone = entity.totalStone
        totalFood = entity.totalFood
        totalCoin = entity.totalCoin
        vipWood = entity.vipWood
        vipIron = entity.vipIron
        vipStone = entity.vipStone
        vipFood = entity.vipFood
        vipCoin = entity.vipCoin
        talentWood = entity.talentWood
        talentIron = entity.talentIron
        talentStone = entity.talentStone
        talentFood = entity.talentFood
        talentCoin = entity.talentCoin
        woodLimit = entity.woodLimit
        ironLimit = entity.ironLimit
        stoneLimit = entity.stoneLimit
        foodLimit = entity.foodLimit
        coinLimit = entity.coinLimit
    }

}
