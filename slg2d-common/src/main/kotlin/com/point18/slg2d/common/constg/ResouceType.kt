package com.point18.slg2d.common.constg

//1代表铜币
//2代表粮食
//3代表木材
//4代表石料
//5代表铁矿

const val NOT_PROPS_SUB_TYPE = 0L // 非道具子类型

typealias ResourceType = Int

// =====================================当增加新的资源类型的时候.记得修改下面的方法哦  IsResType()

const val RES_COIN: ResourceType = 1  // 铜币
const val RES_FOOD: ResourceType = 2  // 粮食
const val RES_WOOD: ResourceType = 3  // 木材
const val RES_STONE: ResourceType = 4  // 石料
const val RES_IRON: ResourceType = 5  // 晶矿（铁矿）
const val RES_BIND_GOLD: ResourceType = 7 // 绑定钻石（绑定元宝）
const val RES_DECREE: ResourceType = 8  // 行动力
const val RES_FAME: ResourceType = 10 // 声望
const val RES_GOLD: ResourceType = 12  // 钻石（元宝）
const val RES_PROPS: ResourceType = 14 // 道具
const val RES_HONOR: ResourceType = 15 // 贡献(老的荣誉值)
const val RES_JJC_COIN: ResourceType = 17 // 竞技币
const val RES_KING_EXP: ResourceType = 18 // 君主经验
const val RES_VIP_EXP: ResourceType = 20 // vip经验
const val RES_ACTION_NUM: ResourceType = 21 // 体力
const val RES_ALLIANCE_COIN: ResourceType = 22 // 联盟币
const val RES_HERO_EXP_POOL: ResourceType = 23 // 英雄经验池
const val RES_HOUSE_COIN: ResourceType = 24 // 家园币
const val RES_CASINO_COIN: ResourceType = 25 // 赌场币
const val RES_SILVER_COIN: ResourceType = 26 // 挑战银币
const val RES_GOLD_COIN: ResourceType = 27 // 挑战金币

const val RES_RELIC_REWARD: ResourceType = 101 //遗迹奖励

fun isResType(t: ResourceType): Boolean {
    if (t == RES_COIN ||
        t == RES_FOOD ||
        t == RES_WOOD ||
        t == RES_STONE ||
        t == RES_IRON ||
        t == RES_GOLD ||
        t == RES_DECREE ||
        t == RES_FAME ||
        t == RES_BIND_GOLD ||
        t == RES_PROPS ||
        t == RES_HONOR ||
        t == RES_JJC_COIN ||
        t == RES_KING_EXP ||
        t == RES_VIP_EXP ||
        t == RES_ACTION_NUM ||
        t == RES_ALLIANCE_COIN ||
        t == RES_HERO_EXP_POOL ||
        t == RES_CASINO_COIN ||
        t == RES_SILVER_COIN ||
        t == RES_GOLD_COIN
    ) {
        return true
    }
    return false
}

fun isTransportResource(t: ResourceType): Boolean {
    return t == RES_COIN || t == RES_FOOD || t == RES_WOOD || t == RES_STONE || t == RES_IRON
}

//影响哪些资源产量变化
typealias ResYieldWhat = Int

const val NullResYield: ResYieldWhat = 1                    //不需要计算资源产量
const val LandResYield: ResYieldWhat = 2                    //领地产量
const val BuildingResYield: ResYieldWhat = 4                //设施产量
const val AllianceResYield: ResYieldWhat = 8                //联盟加成
const val NpcCityResYield: ResYieldWhat = 16                //城池加成
const val MemberResYield: ResYieldWhat = 32                 //同盟成员加成
const val ResearchResYield: ResYieldWhat = 64               //科技加成
const val AllianceResearchResYield: ResYieldWhat = 128      //帮派科技加成
const val UseUpResYield: ResYieldWhat = 256                 //维持消耗
const val VipResYield: ResYieldWhat = 512                   //vip加成
const val TalentResYield: ResYieldWhat = 1024               //天赋加成

const val AllResYield =
    LandResYield or
            BuildingResYield or
            AllianceResYield or
            NpcCityResYield or
            MemberResYield or
            UseUpResYield or
            ResearchResYield or
            AllianceResearchResYield or
            VipResYield or
            TalentResYield //全部重新计算资源产量
