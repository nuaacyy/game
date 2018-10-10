package com.point18.slg2d.common.constg

typealias LibType = Int

// 查询图鉴类型
const val LIB_BOSS: LibType = 1
const val LIB_CARD: LibType = 2
const val LIB_EQUIP: LibType = 3
const val LIB_PROP: LibType = 4
const val LIB_MONSTER: LibType = 5

// 道具配置表(props.xml)中的mainType
const val PROP_TYPE_EQUIP = 3 //装备
const val PROP_TYPE_CARD = 11 //卡片

// World->Home 开启图鉴类型
const val OPEN_EQUIP_LIB_ITEM: Int = 1
const val OPEN_PROP_LIB_ITEM: Int = 2
const val OPEN_CARD_LIB_ITEM: Int = 3
const val OPEN_MONSTER_LIB_ITEM: Int = 4
const val OPEN_BOSS_LIB_ITEM: Int = 5

fun getLibTypeByBossType(bossType: Int): Int {
    return when (bossType) {
        COMMON_BOSS_TYPE -> OPEN_BOSS_LIB_ITEM
        ACTIVITY_BOSS_TYPE -> OPEN_BOSS_LIB_ITEM
        else -> -1
    }
}

const val UNIT_TYPE_HERO = 1 //npcType=1 英雄
const val UNIT_TYPE_MONSTER = 2 //npcType=2 怪物

const val MONSTER_LIB_ATK = 0 // 攻击魔物
const val MONSTER_LIB_KILL = 1 // 杀死魔物