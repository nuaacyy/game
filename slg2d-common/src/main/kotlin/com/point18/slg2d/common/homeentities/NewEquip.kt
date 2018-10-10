package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.PlayerAsset
import xyz.ariane.util.annotation.NoArgConstructor
import org.hibernate.annotations.Index
import javax.persistence.*

@NoArgConstructor
data class NewEquipPK(
    @Id
    @Column(name = "player_id")
    override var playerId: Long,

    @Id
    @Column(name = "id", nullable = false)
    var id: Long
) : PlayerAsset {
    override fun primaryKey() = this
}

const val NEW_EQUIP_NAMED_QUERY = "findNewEquipByPlayerId"

@NamedQueries(
    NamedQuery(
        name = NEW_EQUIP_NAMED_QUERY,
        // language=HQL
        query = "from NewEquipEntity where playerId = :playerId"
    )
)
@Entity
@Table(name = "new_equips")
@IdClass(NewEquipPK::class)
data class NewEquipEntity(
    @Id
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "equip_proto_id", nullable = false)
    var equipProtoId: Int,  // 装备模板

    @Column(name = "equip_on_address", nullable = false)
    var equipOnAddress: Int,  // 佩戴部位  1=武器；2=衣服；3=鞋子；4=饰品；5=腰带；6=戒指

    @Column(name = "have_num", nullable = false)
    var haveNum: Int,  // 物品数量

    @Column(name = "propertys", nullable = false, length = 1000)
    var propertys: String,  // 装备属性

    @Column(name = "lv", nullable = false)
    var lv: Int,

    @Column(name = "exp", nullable = false)
    var exp: Int,

    @Column(name = "bag_id", nullable = false)
    var bagId: Long,  // 隶属的背包ID

    @Column(name = "card_info", nullable = false, length = 5000)
    var cardInfo: String,  // 卡片信息

    @Column(name = "foreign_id", nullable = false)
    var foreignId: Long,  // 外键ID,表示这个道具所属,如果是穿在武将身上 就是武将ID ,如果是在普通背包或者君主装备在身上,就是玩家ID

    @Column(name = "player_id", nullable = false)
    @Index(name = "NEW_EQUIPS_PLAYER_ID")
    override var playerId: Long                                  // 玩家Id
) : PlayerAsset {
    constructor() : this(0, 0, 0, 0, "", 0, 0, 0, "", 0, 0)

    override fun primaryKey() = NewEquipPK(playerId, id)
}