package com.point18.slg2d.common.constg

// 科技研发条件
const val RESEARCH_CODITION_BUILDING: Int = 1 // 科技研发的建筑需求类型
const val RESEARCH_CODITION_RESEARCH: Int = 2 // 科技研发的科技需求

// 科技研发模式
const val RESEARCH_LVUP_NORMAL: Int = 1 // 正常研究
const val RESEARCH_LVUP_RMB: Int = 2 // 元宝秒资源研究

// 某科技效果变化之后是否会影响产量
fun isRefreshResAtOnce(effectType: Int): Boolean {
    if (effectType == ResearchEffectFoodAdd ||
        effectType == ResearchEffectWoodAdd ||
        effectType == ResearchEffectStoneAdd ||
        effectType == ResearchEffectIronAdd ||
        effectType == AddCityWoodYield ||
        effectType == AddCityIronYield ||
        effectType == AddCityStoneYield ||
        effectType == AddCityFoodYield ||
        effectType == WoodCost ||
        effectType == IronCost ||
        effectType == FoodCost ||
        effectType == StoneCost
    ) {
        return true
    }
    return false
}
