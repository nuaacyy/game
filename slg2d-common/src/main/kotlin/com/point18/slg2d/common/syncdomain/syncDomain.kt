package com.point18.slg2d.common.syncdomain

import com.point18.slg2d.common.constg.EffectType
import com.point18.slg2d.common.homeentities.HeroEquipVo
import com.point18.slg2d.common.msgtrans.InternalMessage
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.common.worldentities.*
import java.io.Serializable
import java.util.*

//------------------------------结构数据----------------------------------
data class HeroData(
    var id: Long,
    var protoId: Int,
    var lv: Int,
    var star: Int,
    var awake: Int,
    var exp: Int,
    var skill1: Int,
    var skill2: Int,
    var skill3: Int,
    var skill4: Int,
    var equipMap: HashMap<Int, HeroEquipVo> = hashMapOf()
) : Serializable

data class TargetData(
    var targetId: Int,
    var values: LinkedList<Long> = LinkedList()
) : Serializable

data class HomeEffectData(
    var effectType: EffectType,
    var effectMap: HashMap<Int, Int> = hashMapOf()
) : Serializable

data class DecreeData(
    val time: Long,
    val limit: Int,
    var num: Int
) : Serializable

//同步的兵营数据
data class BarrackData(
    var soldierId: Int,                 // 士兵模板ID
    var soldierNum: Int,                // 士兵数量
    var nowMakeNum: Int,                // 正在造的士兵数量
    var canCureNum: Int,                // 当前可治疗的士兵数量
    var nowCureNum: Int,                // 当前正在治疗的士兵数量
    var canEventCureNum: Int,           // 当前可治疗(活动)的士兵数量
    var nowEventCureNum: Int,           // 当前正在治疗(活动)的士兵数量
    var upNum: Int,                     // 当前正在晋升的数量
    var makeOverTime: Long,             // 造兵结束时间
    var cureOverTime: Long,             // 治疗兵结束时间
    var eventCureOverTime: Long,        // 治疗兵结束时间
    var upOverTime: Long                // 晋升结束时间
) : Serializable

//同步的效果数据
data class EffectData(
    var syncEffectType: Int,            //同步的数据类型
    var effectMap: HashMap<Int, Int>    //效果map
) : Serializable

data class CountryBuffData(
    var buffId: Long,                   //buffId
    var buffProtoId: Int,               //buff的配置Id
    var startTime: Long,                //buff开始时间
    var endTime: Long                   //buff结束时间
) : Serializable

// 同步玩家设置信息
data class PlayerSettingData(
    var refuseDisturbOpen: Int, // 勿扰开始时间
    var refuseDisturbEnd: Int, // 勿扰结束时间
    var cautionLv: Int, //警戒等级
    var switches: HashMap<Int, NoticeSwitchVo> //通知开关
) : Serializable

/**
 * World向World请求的消息 , 用kryo
 */
interface KryoAskWorldMessage : InternalMessage {
    var worldId: Long
    var playerId: Long
}

abstract class KryoKryoAskWorldMessageBase : KryoAskWorldMessage {
    override var worldId: Long = 0
    override var playerId: Long = 0
}

// 迁服需要迁走的entity数据
data class MoveServerEntitysReq(
    var x: Int,
    var y: Int,
    var pubCacheAchievement: LinkedList<AchievementEntity>,
    var pubCacheArmyPlan: LinkedList<ArmyPlanEntity>,
    var pubCacheBarracks: LinkedList<BarracksEntity>,
    var pubCacheBuff: LinkedList<BuffEntity>,
    var pubCacheCastle: LinkedList<CastleEntity>,
    var pubCacheHero: LinkedList<WorldHeroEntity>,
    var pubCacheFog: LinkedList<FogEntity>,
    var pubCacheHomeHeart: LinkedList<HomeHeartEntity>,
    var pubCacheIdMyAllianceGift: LinkedList<MyAllianceGiftEntity>,
    var pubCacheInfoByHome: InfoByHomeEntity,
    var pubCacheInstance: InstanceEntity,
    var pubCacheInstanceDrop: LinkedList<InstanceDropEntity>,
    var pubCacheMyTarget: MyTargetEntity,
    var pubCachePlayer: PlayerEntity,
    var pubCachePrison: LinkedList<PrisonEntity>,
    var pubCacheTask: LinkedList<TaskEntity>,
    var pubCachePlayerActivity: LinkedList<PlayerActivityEntity>,
    var pubCachePlayerSetting: PlayerSettingEntity
) : KryoKryoAskWorldMessageBase()

// 迁服需要迁走的entity数据
data class MoveServerEntitysRt(
    var rt: Int = ResultCode.SUCCESS.code,
    var newAreaNo: Int  // 目标世界服的区名
) : KryoKryoAskWorldMessageBase()