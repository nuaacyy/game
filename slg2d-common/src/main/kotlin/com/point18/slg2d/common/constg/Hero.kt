package com.point18.slg2d.common.constg

const val HERO_SOLDIER_NUM: Int = 1 // 兵力
const val HERO_HURT_STATUS: Int = 2 // 状态 0-正常 1-重伤
const val HERO_HURT_RECOVER_TIME: Int = 3 // 重伤恢复时间
const val HERO_SKILL: Int = 4 // 技能
const val HERO_SOLDIER_LIMIT: Int = 5 // 兵力上限
const val HERO_ARMY_TYPE: Int = 6 // 兵种
const val HERO_SKILL_2: Int = 7 // 武将2号技能
const val HERO_SKILL_3: Int = 8 // 武将3号技能

const val HERO_TILI: Int = 10 // 武将体力
const val HERO_TILI_TIME: Int = 11 // 武将体力上次更新时间
const val HERO_LV: Int = 12 // 武将等级
const val HERO_EXP: Int = 13 // 武将经验
const val HERO_STAR_LV: Int = 14 // 武将星级
const val HERO_AWAKE: Int = 15 // 武将觉醒级
const val HERO_SKILL_1: Int = 16 // 第一个技能变化
const val HERO_SOLIDER_ID: Int = 17 // 所带士兵ID
const val HERO_INT_ID: Int = 18 // 执政ID
const val HERO_POS_STATE: Int = 19 // 英雄位置
const val HERO_SKILL_4: Int = 20 // 武将4号技能
const val HERO_STAR_LV_UP_ENDTIME_CHANGE: Int = 21 // 升星结束时间变化
const val HERO_SUPER_LV_UP_ENDTIME_CHANGE: Int = 22 // 升阶结束时间变化
const val MAIN_HERO_STATE: Int = 23 // 武将领主状态变化
const val MAIN_HERO_STATE_START_TIME: Int = 24 // 武将领主状态的开始时间
const val MAIN_HERO_STATE_OVER_TIME: Int = 25 // 武将领主状态的结束时间
const val MAIN_HERO_STATE_PRISON_PLAYERID: Int = 26 // 武将领主被谁关了
const val HERO_POWER: Int = 30 // 英雄实力
const val HERO_ARMY_LV_CHANGE: Int = 31 // 兵团等级变化
const val HERO_ON_FLOOR_IDX: Int = 32 // 英雄所在楼层

//战斗单位类型

const val WuliXing: Int = 1 //物理型
const val FashuXing: Int = 2 //法术型

//属性代号

// 通用战斗属性

const val ARR_WUGONG: Int = 1  //1-物攻
const val ARR_WUFANG: Int = 2  //2-物防
const val ARR_FAGONG: Int = 3  //3-法攻
const val ARR_FAFANG: Int = 4  //4-法防
const val ARR_INTERVAL: Int = 5  //5-攻击间隔
const val ARR_GONGJIJULI: Int = 6  //6-攻击距离
const val ARR_BAOJI: Int = 7  //7-暴击
const val ARR_BAOJILV: Int = 8  //8-暴击伤害
const val ARR_SUDU: Int = 9  //9-速度 N个Tick走一格
const val ARR_HPLIMIT: Int = 10 //10-生命上限
const val ARR_HPRECOVREY: Int = 11 //11-生命恢复
const val ARR_MORALERECOVERY: Int = 12 //12-士气回复
const val ARR_ATKADDHP: Int = 13 //13-攻击回血
const val ARR_ATKADDMORALE: Int = 14 //14-攻击回气
const val ARR_DODGE: Int = 15 //15-闪避

fun isHeroIntProperty(property: Int): Boolean {
    when (property) {
        ARR_HPLIMIT,
        ARR_HPRECOVREY,
        ARR_ATKADDHP -> {
            return true
        }
    }
    return false
}

//=====================新增成长字段=================
const val ARR_PUGONG_GROWTH: Int = 31 //31-物攻成长
const val ARR_FANGYU_GROWTH: Int = 32 //32-物防成长
const val ARR_MOULUE_GROWTH: Int = 33 //33-法攻成长
const val ARR_MOUFANG_GROWTH: Int = 34 //34-法防成长
const val ARR_HP_GROWTH: Int = 40 //40-生命成长


const val NO_MAIN_HERO: Int = 0 // 不是领主
const val MAIN_HERO: Int = 1 // 正常状态领主
const val PRISON_ESCAP_FROM: Int = 2 // 等待逃脱中
const val PRISON_AWAITING_EXECUTION: Int = 3 // 等待处决
const val PRISON_EXECUTION: Int = 4 // 可处决
const val PRISON_EAT_MUSHROOM: Int = 5 // 吃毒蘑菇中
const val PRISON_DIE: Int = 6 // 死亡中
const val CAN_RESURGENCE: Int = 7 // 可领取复活
const val IN_HIDE: Int = 8 // 藏宝地穴中
const val VIRTUAL_BUFF: Int = 9 // 虚拟英雄 用作buff填充

const val IN_CITY: Int = 0 //在城中
const val OUT_CITY: Int = 1 //外出行军

// 英雄无敌版武将养成检测常量

const val KING_LV_CHECK: Int = 1 // 君主等级
const val HERO_SUPER_LV_CHECK: Int = 2 // 阶数
const val HERO_STAR_LV_CHECK: Int = 3 // 星数
const val HERO_LV_CHECK: Int = 4 // 等级

//领主的状态
typealias MainHeroState = Int

const val MAIN_HERO_IN_WALK: MainHeroState = 1  //领主行军中
const val MAIN_HERO_BE_PRISON: MainHeroState = 2 //领主被抓
const val MAIN_HERO_IN_CAVE: MainHeroState = 3 // 领主在藏兵中
const val MAIN_HERO_IN_MASS: MainHeroState = 4   //领主在城内集结部队中
const val MAIN_HERO_DEAD: MainHeroState = 5      //领主死亡
const val MAIN_HERO_IN_CITY: MainHeroState = 6   //领主在城内
