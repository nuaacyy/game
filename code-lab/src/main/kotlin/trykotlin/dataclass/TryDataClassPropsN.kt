package trykotlin.dataclass

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

data class Account(
	var id: Long,
	var name: String,
	var age: Long
)

fun main(args: Array<String>) {
	val account1 = Account(1, "jack", 100)
	val account2 = Account(1, "jack", 100)
	val account3 = Account(1, "jack", 90)

	println(account1)
	println(account2)
	println("hash1: ${account1.hashCode()}, hash2: ${account2.hashCode()}，hash3: ${account3.hashCode()}")

	val p1 = PlayerActivity(1, 0, 100, 200, 300, 400, 500)
	val p2 = PlayerActivity(1, 0, 100, 200, 300, 400, 500)
	val p3 = PlayerActivity(1, 0, 100, 200, 300, 400, 1500)

	val pe11 = p1.toEntity()
	val pe12 = p1.toEntity()
	val pe21 = p2.toEntity()
	val pe31 = p3.toEntity()

	println("hash1: ${pe11.hashCode()}, hash2: ${pe12.hashCode()}，hash3: ${pe21.hashCode()}，hash4: ${pe31.hashCode()}")

	val copyOfAccount1 = account1.copy()
	println("原始：${account1}，复制的：${copyOfAccount1}，是否相等：${account1 == copyOfAccount1}，引用是否相等：${account1 === copyOfAccount1}")
}

// 玩家活动数据
class PlayerActivity(
	var id: Long,
	var oldHash: Int,
	var activityId: Int, //`gorm:"not null;"`             // 活动模版ID
	var score: Int, //`gorm:"not null;"`             // 本活动积分
	var nowTarget: Int, //`gorm:"not null;"`             // 当前完成度(是一个中间值,比如条件是杀100个兵,本次击杀了99个.那么下次击杀1个也就可以完成1次积分获取了)
	var castleLv: Int, //`gorm:"not null;"`             // 参加这个活动时的主堡等级
	var playerId: Long   //`gorm:"not null;type:big:Int;"` // 玩家ID
) {
	constructor() : this(0L, 0, 0, 0, 0, 0, 0L)

	fun toEntity(): PlayerActivityEntity {
		return PlayerActivityEntity(
			id,
			activityId,
			score,
			nowTarget,
			castleLv,
			playerId
		)
	}
}

@Entity
@Table(name = "player_activities")
data class PlayerActivityEntity(
	@Id
	@GeneratedValue(generator = "areaObjIdGen")
	@GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
	@Column(name = "id", nullable = false)
	var id: Long,

	@Column(name = "activity_id", nullable = false)
	var activityId: Int, //`gorm:"not null;"`             // 活动模版ID

	@Column(name = "score", nullable = false)
	var score: Int, //`gorm:"not null;"`             // 本活动积分

	@Column(name = "now_target", nullable = false)
	var nowTarget: Int, //`gorm:"not null;"`             // 当前完成度(是一个中间值,比如条件是杀100个兵,本次击杀了99个.那么下次击杀1个也就可以完成1次积分获取了)

	@Column(name = "castle_lv", nullable = false)
	var castleLv: Int, //`gorm:"not null;"`             // 参加这个活动时的主堡等级

	@Column(name = "player_id", nullable = false)
	var playerId: Long   //`gorm:"not null;type:big:Int;"` // 玩家ID
) {
	constructor() : this(0L, 0, 0, 0, 0, 0L)
}