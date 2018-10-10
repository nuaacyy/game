package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

const val HERO_NAMED_QUERY = "findHeroByWorldId"

@NamedQueries(
    NamedQuery(
        name = HERO_NAMED_QUERY,
        // language=HQL
        query = "from WorldHeroEntity where worldId = :worldId"
    )
)
@Entity
@Table(name = "worldheros")
data class WorldHeroEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "db_id", nullable = false)
    var dbId: Long,

    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "proto_id", nullable = false)
    var protoId: Int,                                              // 武将模板ID

    @Column(name = "level", nullable = false)
    var level: Int,                                              // 武将等级

    @Column(name = "adv_lv", nullable = false)
    var advLv: Int,                                              // 星数

    @Column(name = "exp", nullable = false)
    var exp: Int,                                  // 武将经验

    @Column(name = "skill_id1", nullable = false)
    var skillId1: Int,                                              // 武将技能1

    @Column(name = "skill_id2", nullable = false)
    var skillId2: Int,                                              // 武将技能2

    @Column(name = "skill_id3", nullable = false)
    var skillId3: Int,                                              // 武将技能3

    @Column(name = "skill_id4", nullable = false)
    var skillId4: Int,                                              // 武将技能4

    @Column(name = "player_id", nullable = false)
    var playerId: Long,                                  // 玩家编号

    @Column(name = "awake", nullable = false)
    var awake: Int,                                              // 觉醒等级

    @Column(name = "int_skill_id1", nullable = false)
    var intSkillId1: Int,                                              // 武将内政技能1

    @Column(name = "int_skill_id2", nullable = false)
    var intSkillId2: Int,                                              // 武将内政技能2

    @Column(name = "int_skill_id3", nullable = false)
    var intSkillId3: Int,                                              // 武将内政技能3

    @Column(name = "int_hero_address", nullable = false)
    var intHeroAddress: Int,                                              // 非0表示武将在执政中,这个值表示执政位ID

    @Column(name = "main_hero_state", nullable = false)
    var mainHeroState: Int,                                              // 领主属性状态字段 0-不是领主 1-正常状态领主 2...3....4...8-在藏兵洞穴中

    @Column(name = "main_hero_state_start_time", nullable = false)
    var mainHeroStateStartTime: Long,  // 领主状态变化的开始时间,要记录这个是因为有科技会影响这个时间,但是不影响已经关押的人的时间

    @Column(name = "main_hero_state_end_time", nullable = false)
    var mainHeroStateEndTime: Long,  // 领主状态变化的结束时间,这个字段的意义取决于当前所属的状态

    @Column(name = "main_hero_prison_player_id", nullable = false)
    var mainHeroPrisonPlayerId: Long,                                  // 关押该领主的玩家ID

    @Column(name = "pos_state", nullable = false)
    var posState: Int,                                              // 位置状态 1、在城内 2、出征中

    @Column(name = "hero_equip_info", nullable = false, length = 100000)
    var heroEquipInfo: String,                                    // 英雄佩戴装备情况

    @Column(name = "star_up_end_time", nullable = false)
    var starUpEndTime: Long,  // 升星结束时间

    @Column(name = "super_up_end_time", nullable = false)
    var superUpEndTime: Long,  // 升阶结束时间

    @Column(name = "hero_strength", nullable = false)
    var heroStrength: Long,                                  // 英雄实力

    @Column(name = "intimacy_exp", nullable = false)        // 好感度
    var intimacyExp: Int,

    @Column(name = "intimacy_lv", nullable = false)         // 好感度等级
    var intimacyLv: Int,

    @Column(name = "on_floor_idx", nullable = false)        // 后宅楼层
    var onFloorIdx: Int
) : WorldAsset {

    constructor() : this(
        0, 0,
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0,
        0, "", 0, 0, 0,
        0, 0, 0
    )

    override fun primaryKey() = dbId
}