package com.point18.slg2d.common.constg

//推送状态

const val AddProps = 1 //新增
const val RemoveProps = 2 //减少

// 物品大类型

const val PROP_RES = 1  // 资源类道具
const val PROP_QUICK = 2  // 加速类道具
const val PROP_KING_EQUIP = 3  // 君主装备
const val PROP_BOX = 4  // 宝箱类型的物品
const val PROP_GIFT = 5  // 帮派礼物类型的物品
const val PROP_HERO = 6  // 英雄碎片
const val PROP_USE = 7  // 功能型道具
const val PROP_EQUIP = 8  // 英雄装备
const val PROP_BUFF = 9  // 加成类,读prop中的ex1字段,获取到buff表ID 进行buff加成
const val PROP_SEED = 10 // 种子
const val PROP_KING_EQUIP_CARD = 11  // 卡片宝石

// 资源类大类型下的小类型 1

const val FoodBag = 1  // 粮食礼包
const val StoneBag = 2  // 石料礼包
const val WoodBag = 3  // 木材礼包
const val IronBag = 4  // 铁矿礼包
const val CoinBag = 5  // 铜币礼包
const val GoldBag = 6  // 元宝礼包
const val InstanceStrengthBag = 7  // 体力道具
const val ZhenglingBag = 8  // 政令礼包
const val KingExpCard = 9  // 君主经验卡
const val ExpCardBag = 10 // 经验卡礼包
const val CasinoCoinBag = 11 // 赌场币礼包
const val VipExpCard = 12 // vip经验卡
const val VipTimeCard = 13 // vip时间卡
const val SoliderBag = 15 // 士兵礼包

// 加速类大类型下的小类型 2

const val ALL_QUICK_TIME_PROP = 1  // 通用加速道具
const val BUILD_QUICK_TIME_PROP = 2  // 建筑加速道具
const val RESEARCH_QUICK_TIME_PROP = 3  // 研发加速道具
const val BINGYING_QUICK_TIME_PROP = 4  // 兵营加速道具
const val SHANGBING_QUICK_TIME_PROP = 5  // 伤兵加速道具
const val WALK_QUICK_TIME_PROP = 10 // 行军加速卡
const val MASS_QUICK_TIME_PROP = 11 // 集结加速卡
const val TIME_BOX_QUICK_TIME_PROP = 12

// 宝箱大类型下的小类型 4

const val BOX_NORMAL = 1 // 普通宝箱,直接使用useGet字段获得所有物品
const val BOX_RAND1 = 2 // 特殊宝箱,使用策略是使用extend2中随机产出一个物品获得
const val BOX_RAND2 = 3 // 特殊宝箱,使用策略是使用extend2中随机产出一个物品获得
const val BOX_RAND3 = 4 // 特殊宝箱,使用策略是使用extend2中随机产出一个物品获得

// 礼物类大类型下的小类型 5

const val GIFT_RAND1 = 1 // 礼物,使用策略是使用extend2中随机产出一个物品获得
const val GIFT_RAND2 = 2 // 礼物,使用策略是使用extend2中随机产出一个物品获得

// 功能型类大类型下的小类型 7

const val HalfWayHome = 5  //行军召回
const val RandomPointMoveCity = 6  //随机传送
const val FixedPointMoveCity = 7  //定点传送
const val ADD_KING_EQUIP_BAG_NUM = 17 // 增加君主背包上限道具
const val ADD_MARK_NUM = 16 // 增加君主背包上限道具
const val HeroGift = 8  // 后宅英雄礼物

const val BUFF_FOOD_ADD = 1  // 粮食产量加成
const val BUFF_STONE_ADD = 2  // 石头产量加成
const val BUFF_WOOD_ADD = 3  // 木材产量加成
const val BUFF_IRON_ADD = 4  // 铁矿产量加成
const val BUFF_GOLD_ADD = 5  // 金币产量加成
const val BUFF_ADD_ATK = 6  // 加军团攻击
const val BUFF_ADD_DEF = 7  // 加军团防御
const val BUFF_ADD_HP = 8  // 加军团生命
const val BUFF_SOLIDER_MAX = 9  // 加带兵上限,百分比
const val BUFF_KING_EXP = 10 // 君主经验加成
const val BUFF_MONSTER_HURT_ADD = 11 // BOSS伤害加成
const val BUFF_PLAYER_DEF = 12 // 保护罩
const val BUFF_DE_LOOK = 13 // 反侦察
const val BUFF_ZHABING = 14 // 诈兵术
const val BUFF_JIANGDIJUNXIANG = 15 // 降低军饷
const val BUFF_ADD_SPEEK_WALK = 16 // 行军速度加成
const val BUFF_ADD_SPEEK_FARM = 17 // 采集速度加成
const val BUFF_ADD_SPEEK_BUILDING = 18 // 建造速度加成
const val BUFF_ADD_SPEEK_RESEARCH = 19 // 科研速度加成
const val BUFF_ADD_SPEEK_MAKE_SOLIDER = 20 // 造兵速度加成
const val CALL_BOSS = 23 //召唤魔物

// 背包类型

const val NORMAL_BAG = 1 // 1-普通背包
const val HERO_BAG = 2 // 2-武将身上佩戴背包
const val KING_BAG = 3 // 3-君主身上佩戴背包

// 君主装备锻造/升级前置条件常量

const val MAKE_NORMAL_KING_EQUIP_CONDITION = 1 // 1-普通装备打造
const val MAKE_SUPER_KING_EQUIP_CONDITION = 2 // 2-套装装备打造

// 道具品质

const val PROPS_QUALITY_BAI = 1 //白
const val PROPS_QUALITY_LV = 2 //绿
const val PROPS_QUALITY_LAN = 3 //蓝
const val PROPS_QUALITY_ZI = 4 //紫
const val PROPS_QUALITY_CHENG = 5 //橙

const val ADD_IN_MAKE_DUILIE = 1 // 新增锻造君主装备队列
const val DEL_IN_MAKE_DUILIE = 2 // 删除锻造君主装备队列
const val UPDATE_IN_MAKE_DUILIE = 3 // 修改锻造君主装备队列

const val NORMAL_MAKE_KING_EQUIP = 1 // 普通锻造
const val YBL_MAKE_KING_EQUIP = 2 // 元宝秒锻造

// 领主装备卡片合成类型
const val KING_EQUIP_COMPOUND_CARD_IN_BAG = 1 // 背包内合
const val KING_EQUIP_COMPOUND_CARD_IN_KING = 2 // 装备上的合

// 迁城道具类型
const val COMMON_MOVE_CITY_PROP = 0
const val BIG_WONDER_MOVE_CITY_PROP = 1
const val SMALL_WONDER_MOVE_CITY_PROP = 2
const val SNOW_MOVE_CITY_PROP = 3

fun moveCityTypeByWonderType(wonderType: Int): Int? {
    return when (wonderType) {
        BIG_WONDER_TYPE -> BIG_WONDER_MOVE_CITY_PROP
        SMALL_WONDER_TYPE -> SMALL_WONDER_MOVE_CITY_PROP
        else -> -1
    }
}