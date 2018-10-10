package com.point18.slg2d.home.dc

import com.point18.slg2d.home.AbstractDataContainer
import com.point18.slg2d.home.hpm
import com.point18.slg2d.common.homeentities.*
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.constg.PlayerId
import java.io.Serializable
import java.time.Duration

class BagDC : AbstractDataContainer<List<BagEntity>>() {
    val bagTypeInfo1 = mutableMapOf<Long, Int>() // KEY : 背包ID  VALUE : 类型
    val bagTypeInfo2 = mutableMapOf<Int, Long>() //  KEY : 背包类型  VALUE : 背包ID

    override fun initImpl(data: List<BagEntity>?, depDCRepo: DataContainerRepo) {
        data?.map {
            val bag = wdb.recover(it) { Bag() }

            bagTypeInfo1[bag.id] = bag.bagType
            bagTypeInfo2[bag.bagType] = bag.id
        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): List<BagEntity>? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(BAG_NAMED_QUERY)
                .setLong("playerId", playerId)
                .listNoDup<BagEntity>()
            list
        }
        return data
    }

    // 找到某玩家的某类型的背包的ID
    fun findBagIdByPlayerIdAndBagType(bagType: Int): Long {
        val m = this.bagTypeInfo2[bagType]
        if (m == null) {
            return 0L
        } else {
            return m
        }
    }

    fun createBag(playerId: Long, bagType: Int): Bag {
        val id = hpm.generateObjIdNew()
        val bag = Bag(id, bagType, playerId)

        wdb.save(bag)
        bagTypeInfo1[bag.id] = bag.bagType
        bagTypeInfo2[bag.bagType] = bag.id

        return bag
    }
}

// 玩家背包
class Bag(
    var id: Long,

    var bagType: Int,  // 背包类型  1-普通背包 2-武将身上佩戴背包 3-君主身上佩戴背包
    var playerId: Long
) : EntityWrapper<BagEntity> {
    constructor() : this(0, 0, 0)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = BagPK(playerId, id)

    override fun toEntity(): BagEntity {
        return BagEntity(
            id,
            bagType,
            playerId
        )
    }

    override fun wrap(entity: BagEntity) {
        id = entity.id
        bagType = entity.bagType
        playerId = entity.playerId
    }
}

