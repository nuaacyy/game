package com.point18.slg2d.world.area4data

import com.point18.slg2d.common.homeentities.HeroEquipVo

data class HeroProperty(
    var heroId: Long,// 武将操作码
    var heroProtoId: Int,// 武将ID
    var lv: Int,// 武将等级
    var advLv: Int,// 武将进阶等级
    var exp: Int,// 武将经验
    var attW: Double,// 武将攻击资质
    var hpW: Double,// 武将生命资质
    var defenceW: Double,// 武将防御资质
    var speed: Double,// 武将速度
    var baoji: Double,//暴击
    var baojiLv: Double,//暴击率
    var skill1: Int,// 技能1
    var skill2: Int,// 技能2
    var skill3: Int,// 技能3
    var skill4: Int,// 技能4
    var awake: Int,// 觉醒等级
    var IntSkillId1: Int,// 武将内政技能1
    var IntSkillId2: Int,// 武将内政技能2
    var IntSkillId3: Int,// 武将内政技能3
    var IntHeroAddress: Int,// 非0表示武将在执政中,这个值表示执政位ID
    var mainHeroState: Int,// 领主属性状态字段 0-不是领主 1-正常状态领主 2-被监禁领主 3-..........
    var heroPosState: Int,//英雄位置状态 1、在城内 2、出征中 3、在藏兵洞穴中
    var heroEquips: HashMap<Int, HeroEquipVo>,
    var starLvUpEndTime: Long,// 升星到点时间
    var superLvUpEndTime: Long,// 升阶到点时间
    var mainHeroStartTime: Long,// 领主状态开始时间
    var mainHeroOverTime: Long,// 领主状态结束时间
    var heroPower: Long,// 英雄实力
    var onFloorIdx: Int        // 所在后宅楼层
)
