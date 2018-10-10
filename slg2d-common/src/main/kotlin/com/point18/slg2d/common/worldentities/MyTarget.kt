package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

const val MY_TARGET_NAMED_QUERY = "findMyTargetByWorldId"

@NamedQueries(
    NamedQuery(
        name = MY_TARGET_NAMED_QUERY,
        // language=HQL
        query = "from MyTargetEntity where worldId = :worldId"
    )
)
@Entity
@Table(name = "my_targets")
data class MyTargetEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    var playerId: Long,  //玩家ID

    @Column(name = "win_record", nullable = false)
    var winRecord: Int,  //总胜场

    @Column(name = "fail_record", nullable = false)
    var failRecord: Int,  //总败场

    @Column(name = "attack_win_record", nullable = false)
    var attackWinRecord: Int,  //进攻胜次

    @Column(name = "attack_fail_record", nullable = false)
    var attackFailRecord: Int,  //进攻败次

    @Column(name = "defend_win_record", nullable = false)
    var defendWinRecord: Int,  //防御胜次

    @Column(name = "defend_fail_record", nullable = false)
    var defendFailRecord: Int,  //防御败次

    @Column(name = "total_help_ally", nullable = false)
    var totalHelpAlly: Int,  //帮助盟友总数

    @Column(name = "total_kill", nullable = false)
    var totalKill: Long,  //累计的击杀数

    @Column(name = "weaken_strength", nullable = false)
    var weakenStrength: Long,  //削弱的敌军实力

    @Column(name = "solider_strength", nullable = false)
    var soliderStrength: Long,  //士兵实力

    @Column(name = "trap_strength", nullable = false)
    var trapStrength: Long,  //陷阱实力

    @Column(name = "mission_strength", nullable = false)
    var missionStrength: Long,  //任务实力

    @Column(name = "research_strength", nullable = false)
    var researchStrength: Long,  //研发实力

    @Column(name = "build_strength", nullable = false)
    var buildStrength: Long,  //建造实力

    @Column(name = "hero_strength", nullable = false)
    var heroStrength: Long,  //英雄实力

    @Column(name = "king_strength", nullable = false)
    var kingStrength: Long,  //君主实力

    @Column(name = "research_count", nullable = false)
    var researchCount: Int,              //研究次数

    @Column(name = "receive_online_gift_count", nullable = false)
    var receiveOnlineGiftCount: Int,              //领取在线礼包个数

    @Column(name = "plunder_res_num", nullable = false)
    var plunderResNum: Long,  //掠夺的资源数量

    @Column(name = "solider_die_num", nullable = false)
    var soliderDieNum: Long,  //死亡的士兵数量

    @Column(name = "trap_die_num", nullable = false)
    var trapDieNum: Long,  //死亡的陷阱数量

    @Column(name = "cure_solider_num", nullable = false)
    var cureSoliderNum: Long,  //治疗的士兵数量

    @Column(name = "move_city_count", nullable = false)
    var moveCityCount: Int,              //迁城次数

    @Column(name = "defense_success", nullable = false)
    var defenseSuccess: Int,  // 守城成功次数

    @Column(name = "defense_failed", nullable = false)
    var defenseFailed: Int,  // 守城失败次数

    @Column(name = "scout_num", nullable = false)
    var scoutNum: Int,  // 完成侦查行为次数 （成功算）

    @Column(name = "mass_num", nullable = false)
    var massNum: Int,  // 完成集结行为次数 （）

    @Column(name = "join_mass_num", nullable = false)
    var joinMassNum: Int,  // 加入集结次数

    @Column(name = "atk_monster_num", nullable = false)
    var atkMonsterNum: Int,  // 完成魔物猎杀次数 （地图上普通魔物）

    @Column(name = "kill_monster_score", nullable = false)
    var killMonsterScore: Long,  //

    @Column(name = "last_power_change_time", nullable = false)
    var lastPowerChangeTime: Long,  //

    @Column(name = "last_kill_solider_time", nullable = false)
    var lastKillSoliderTime: Long,  //

    @Column(name = "last_kill_monster_score_time", nullable = false)
    var lastKillMonsterScoreTime: Long,  //

    @Column(name = "jjc_rank", nullable = false)
    var jjcRank: Int,  //竞技场排名

    @Column(name = "max_jjc_rank", nullable = false)
    var maxJjcRank: Int,  //竞技场历史最高排名

    @Column(name = "jjc_win_record", nullable = false)
    var jjcWinRecord: Int, //竞技场胜场

    @Column(name = "last_Jjc_Rank_Time", nullable = false)
    var lastJjcRankTime: Long,  //

    @Column(name = "instance_fight_num", nullable = false)
    var instanceFightNum: Int,  // 副本推图次数

    @Column(name = "cave_solider_num", nullable = false)
    var caveSoliderNum: Int,  // 藏兵次数

    @Column(name = "get_prison_num", nullable = false)
    var getPrisonNum: Int,  // 抓捕领主次数

    @Column(name = "be_prison_num", nullable = false)
    var bePrisonNum: Int,  // 领主被抓次数

    @Column(name = "kill_king_num", nullable = false)
    var killKingNum: Int,  // 击杀领主数

    @Column(name = "king_be_kill_num", nullable = false)
    var kingBeKillNum: Int,  // 领主被杀次数

    @Column(name = "catch_king_escape_num", nullable = false)
    var catchKingEscapeNum: Int,  // 领主被逃脱次数

    @Column(name = "king_escape_num", nullable = false)
    var kingEscapeNum: Int,  // 领主逃脱次数

    @Column(name = "get_reward_gold_num", nullable = false)
    var getRewardGoldNum: Long,  // 获得总赏金

    @Column(name = "make_equip_info", nullable = false, length = 1000)
    var makeEquipInfo: String,    //锻造的装备信息

    @Column(name = "atk_monster_info", nullable = false, length = 1000)
    var atkMonsterInfo: String,    //攻击魔物

    @Column(name = "make_solider_info", nullable = false, length = 1000)
    var makeSoliderInfo: String,    //造兵信息

    @Column(name = "make_trap_info", nullable = false, length = 1000)
    var makeTrapInfo: String,    //造陷阱信息

    @Column(name = "kill_solider_info", nullable = false, length = 1000)
    var killSoliderInfo: String,    //杀兵信息

    @Column(name = "damage_solider_info", nullable = false, length = 1000)
    var damageSoliderInfo: String,    //击伤兵信息

    @Column(name = "kill_monster_info", nullable = false, length = 1000)
    var killMonsterInfo: String,    //杀魔物信息

    @Column(name = "farm_res_num_info", nullable = false, length = 1000)
    var farmResNumInfo: String,    //采集信息

    @Column(name = "farm_res_count_info", nullable = false, length = 1000)
    var farmResCountInfo: String,    //采集信息

    @Column(name = "farm_empty_info", nullable = false, length = 1000)
    var farmEmptyInfo: String,    //采集信息

    @Column(name = "activity_score_info", nullable = false, length = 1000)
    var activityScoreInfo: String,    // 挑战积分获得信息

    @Column(name = "pos_type_info", nullable = false, length = 1000)
    var posTypeInfo: String,    //王国官职

    @Column(name = "transport_res_info", nullable = false, length = 1000)
    var transportResInfo: String    //运输资源数量

) : WorldAsset {

    constructor() : this(
        0
        , 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        , 0, 0, 0, 0, 0, 0, 0, 0
        , 0, 0, 0, 0, 0, 0, 0, 0, 0
        , 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, "",
        "", "", "", "", "", "", "", "", "", "", "", ""
    )

    override fun primaryKey() = id

}