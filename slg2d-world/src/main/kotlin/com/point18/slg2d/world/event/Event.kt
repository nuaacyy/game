package com.point18.slg2d.world.event

import akka.actor.ActorRef
import com.point18.slg2d.common.pc.ResVo
import java.util.*

class NoHandle

class ShowNearMap

// 获得赏金
data class GetRewardGold(
    var rewardGold: Long
)

data class InOrOffAlliance(
    var epNo: ActorRef?,
    var allianceId: Long,
    var positions: LinkedList<Int>
)

data class AllianceNameChange(
    var allianceId: Long,
    var setType: Int,
    var name: String
)

data class AllianceSetPosition(
    var allianceId: Long,
    var posId: LinkedList<Int>
)

data class PlayerActivityChange(
    var activityConditionType: Int,      // 活动积分来源类型 eventCondition.xml id
    var activityAddNum: Int,    // 本次新增的值
    var castleLv: Int           // 本次活动参加时的等级
)

data class CreateAlliance(
    var epNo: ActorRef?,
    var allianceId: Long
)

data class NormalEvent(
    var epNo: ActorRef,
    var alliance: Long,//所属同盟
    var positions: LinkedList<Int>//职位
)

data class GetNewBuff(
    var buffPlayerId: Long, // buff接受者   有操作是玩家A给玩家B了一个buff
    var buffProtoId: Int,
    var buffOverTime: Long  // buff结束时间
)

data class KingLvUp(
    var oldLv: Int,
    var newLv: Int
)

data class InstanceWin(
    var floorId: Int
)

data class BossFight(
    var disappearX: Int,
    var disappearY: Int,
    var bossId: Int,
    var mainId: Int,
    var lv: Int,
    var remainingHp: Int,
    var bossType: Int,
    var num: Int
)

data class RelicDisappear(
    var disappearX: Int,
    var disappearY: Int,
    var relicId: Int
)

data class Transport(
    var targetPlayerId: Long,
    var resVo: LinkedList<ResVo>
)

data class TransportSuccess(
    val resNum: Long
)

data class PrisonEvent(
    var winPlayerId: Long,
    var losePlayerId: Long
)

data class RescueCastlePrison(
    var atkPlayerId: Long,
    var defPlayerId: Long
)

data class PowerChange(
    var oldPower: Long,
    var newPower: Long
)

data class PowerAdd(
    val powerType: Int,
    val value: Long
)

class TargetChange

class AchievementFinish

data class DefCastle(
    var atkPlayerId: Long,
    var defPlayerId: Long,
    var atkBattleRs: Int,
    var defBattleRs: Int
)

data class FarmEnd(
    var resType: Int,
    var farmNum: Int
)

data class FarmEmpty(
    var resType: Int,
    var lv: Int
)

data class StopFarm(
    var x: Int,
    var y: Int
)

data class GainHero(
    var id: Long
)

data class InstanceFightEvent(
    var fightNum: Int, // 战斗次数
    var star: Int, // 星数
    var floorId: Int // 战斗层数
)

data class GetAllianceGift(
    var num: Int  // 打开个数
)

class DefendCoverOn

class GodOfWarOn

class WonderOver

data class ActivityScoreOver(
    var conditionType: Int // 积分获取途径
)

data class CureSoliderEvent(
    var cureNum: Int
)

data class ActivityGetAdvReward(
    var maxIndex: Int // 一次性获得的最大的阶级
)

data class ActivityGetRankReward(
    var rank: Int //  挑战获得排名
)

data class MakeSoliderEvent(
    var armyType: Int,
    var step: Int,
    var makeNum: Int
)

data class MakeTrapEvent(
    var trapType: Int,
    var step: Int,
    var makeNum: Int
)

data class AtkEvent(
    var win: Int
)

data class KillSoliderEvent(
    var killMap: HashMap<Int, Int>
)

data class DamageSoliderEvent(
    var damageMap: HashMap<Int, Int>
)

data class MassEvent(
    var isMaster: Boolean
)

data class FarmResEvent(
    var resType: Int,
    var resNum: Long
)

class KillPrison

class JjcFightWin

class SetArmyPlanEvent

class SetCityDefHero

data class CountryPosChange(
    var playerId: Long,
    var posId: Int
)

data class OccupyWonder(
    var wonderProtoId: Int
)

data class ClearFog(
    var fogId: Int
)