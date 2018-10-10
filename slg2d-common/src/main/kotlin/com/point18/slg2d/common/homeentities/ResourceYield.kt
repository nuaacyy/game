package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.OneToOnePlayerAsset
import javax.persistence.*

const val RESOURCE_YIELD_QUERY_PLAYER = "findResourceYieldByPlayerId"

@NamedQueries(
    NamedQuery(
        name = RESOURCE_YIELD_QUERY_PLAYER,
        // language=HQL
        query = "from ResourceYieldEntity where playerId = :playerId"
    )
)

@Entity
@Table(name = "resource_yields")
data class ResourceYieldEntity(
    @Id
    @Column(name = "player_id", nullable = false)
    override var playerId: Long,         // 玩家ID

    @Column(name = "building_wood", nullable = false)
    var buildingWood: Int,  // 设施产量：木料

    @Column(name = "building_iron", nullable = false)
    var buildingIron: Int,  // 设施产量：铁矿

    @Column(name = "building_stone", nullable = false)
    var buildingStone: Int,  // 设施产量：石料

    @Column(name = "building_food", nullable = false)
    var buildingFood: Int,  // 设施产量：粮食

    @Column(name = "building_coin", nullable = false)
    var buildingCoin: Int,  // 设施产量：金币

    @Column(name = "alliance_wood", nullable = false)
    var allianceWood: Int,  // 联盟加成：木料（%）

    @Column(name = "alliance_iron", nullable = false)
    var allianceIron: Int,  // 联盟加成：铁矿（%）

    @Column(name = "alliance_stone", nullable = false)
    var allianceStone: Int,  // 联盟加成：石料（%）

    @Column(name = "alliance_food", nullable = false)
    var allianceFood: Int,  // 联盟加成：粮食（%）

    @Column(name = "research_wood", nullable = false)
    var researchWood: Int,  // 科技加成：木料（%）

    @Column(name = "research_iron", nullable = false)
    var researchIron: Int,  // 科技加成：铁矿（%）

    @Column(name = "research_stone", nullable = false)
    var researchStone: Int,  // 科技加成：石料（%）

    @Column(name = "research_food", nullable = false)
    var researchFood: Int,  // 科技加成：粮食（%）

    @Column(name = "research_coin", nullable = false)
    var researchCoin: Int,  // 科技加成：金币（%）

    @Column(name = "alliance_research_wood", nullable = false)
    var allianceResearchWood: Int,  // 帮派科技加成：木料（%）

    @Column(name = "alliance_research_iron", nullable = false)
    var allianceResearchIron: Int,  // 帮派科技加成：铁矿（%）

    @Column(name = "alliance_research_stone", nullable = false)
    var allianceResearchStone: Int,  // 帮派科技加成：石料（%）

    @Column(name = "alliance_research_food", nullable = false)
    var allianceResearchFood: Int,  // 帮派科技加成：粮食（%）

    @Column(name = "npc_city_wood", nullable = false)
    var npcCityWood: Int,  // 城池加成：木料

    @Column(name = "npc_city_iron", nullable = false)
    var npcCityIron: Int,  // 城池加成：铁矿

    @Column(name = "npc_city_stone", nullable = false)
    var npcCityStone: Int,  // 城池加成：石料

    @Column(name = "npc_city_food", nullable = false)
    var npcCityFood: Int,  // 城池加成：粮食

    @Column(name = "mem_wood", nullable = false)
    var memWood: Int,  // 同盟成员加成：木料

    @Column(name = "mem_iron", nullable = false)
    var memIron: Int,  // 同盟成员加成：铁矿

    @Column(name = "mem_stone", nullable = false)
    var memStone: Int,  // 同盟成员加成：石料

    @Column(name = "mem_food", nullable = false)
    var memFood: Int,  // 同盟成员加成：粮食

    @Column(name = "use_up_wood", nullable = false)
    var useUpWood: Int,  // 维持消耗：木料

    @Column(name = "use_up_iron", nullable = false)
    var useUpIron: Int,  // 维持消耗：铁矿

    @Column(name = "use_up_stone", nullable = false)
    var useUpStone: Int,  // 维持消耗：石料

    @Column(name = "use_up_food", nullable = false)
    var useUpFood: Int,  // 维持消耗：粮食

    @Column(name = "use_up_coin", nullable = false)
    var useUpCoin: Int,  // 维持消耗：金币

    @Column(name = "cal_time", nullable = false)
    var calTime: Long,  // 产量计算时间

    @Column(name = "total_wood", nullable = false)
    var totalWood: Int,                // 产量总计：木料

    @Column(name = "total_iron", nullable = false)
    var totalIron: Int,                // 产量总计：铁矿

    @Column(name = "total_stone", nullable = false)
    var totalStone: Int,                // 产量总计：石料

    @Column(name = "total_food", nullable = false)
    var totalFood: Int,                // 产量总计：粮食

    @Column(name = "total_coin", nullable = false)
    var totalCoin: Int,                // 产量总计：金币

    @Column(name = "vip_wood", nullable = false)
    var vipWood: Int,  // vip加成：木料（%）

    @Column(name = "vip_iron", nullable = false)
    var vipIron: Int,  // vip加成：铁矿（%）

    @Column(name = "vip_stone", nullable = false)
    var vipStone: Int,  // vip加成：石料（%）

    @Column(name = "vip_food", nullable = false)
    var vipFood: Int,  // vip加成：粮食（%）

    @Column(name = "vip_coin", nullable = false)
    var vipCoin: Int,  // vip加成：金币（%）

    @Column(name = "talent_wood", nullable = false)
    var talentWood: Int,  // 天赋加成：木料（%）

    @Column(name = "talent_iron", nullable = false)
    var talentIron: Int,  // 天赋加成：铁矿（%）

    @Column(name = "talent_stone", nullable = false)
    var talentStone: Int,  // 天赋加成：石料（%）

    @Column(name = "talent_food", nullable = false)
    var talentFood: Int,  // 天赋加成：粮食（%）

    @Column(name = "talent_coin", nullable = false)
    var talentCoin: Int,  // 天赋加成：金币（%）

    @Column(name = "wood_limit", nullable = false)
    var woodLimit: Long,  //木材产量上限

    @Column(name = "iron_limit", nullable = false)
    var ironLimit: Long,  //铁矿产量上限

    @Column(name = "stone_limit", nullable = false)
    var stoneLimit: Long,  //石头产量上限

    @Column(name = "food_limit", nullable = false)
    var foodLimit: Long,  //粮食产量上限

    @Column(name = "coin_limit", nullable = false)
    var coinLimit: Long  //金币产量上限

) : OneToOnePlayerAsset {
    constructor() : this(
         0,0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0L,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0L, 0L, 0L, 0L, 0L
    )
}
