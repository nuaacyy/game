package com.point18.slg2d.home.dc

import com.point18.slg2d.common.homeentities.LIBRARY_NAMED_QUERY_PLAYER
import com.point18.slg2d.common.homeentities.LibraryEntity
import com.point18.slg2d.home.AbstractDataContainer
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.constg.PlayerId
import java.io.Serializable
import java.time.Duration

class LibraryDC : AbstractDataContainer<LibraryEntity>() {

    lateinit var library: Library

    override fun initImpl(data: LibraryEntity?, depDCRepo: DataContainerRepo) {
        if (data != null) {
            val libraryWrap = wdb.recover(data) { Library() }
            library = libraryWrap
        } else {
            val library = Library(
                hashMapOf(), hashMapOf(), hashMapOf(), hashMapOf(), hashMapOf(),
                hashSetOf(), playerId
            )

            wdb.save(library)
            this.library = library
        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): LibraryEntity? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(LIBRARY_NAMED_QUERY_PLAYER)
                .setLong("playerId", playerId)
                .listNoDup<LibraryEntity>()
            if (list.isEmpty()) {
                null
            } else {
                list[0]
            }
        }
        return data
    }
}

class Library(
    var prop: HashMap<Int, CommonLibVo>, // <图书馆类型, 图鉴>
    var equip: HashMap<Int, CommonLibVo>, // <图书馆类型, 图鉴>
    var card: HashMap<Int, CommonLibVo>, // <图书馆类型, 图鉴>
    var boss: HashMap<Int, HashMap<Int, BossLibVo>>,
    var monster: HashMap<Int, HashMap<Int, MonsterLibVo>>,
    var newItem: HashSet<Int>, // <图鉴类型>
    var playerId: Long
) : EntityWrapper<LibraryEntity> {

    constructor() : this(
        hashMapOf(), hashMapOf(), hashMapOf(),
        hashMapOf(), hashMapOf(), hashSetOf(), 0
    )

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = playerId

    override fun toEntity(): LibraryEntity {
        return LibraryEntity(
            playerId,
            toJson(prop),
            toJson(equip),
            toJson(card),
            toJson(boss),
            toJson(monster),
            toJson(newItem)
        )
    }

    override fun wrap(entity: LibraryEntity) {
        playerId = entity.playerId
        prop = toObj(entity.prop)
        equip = toObj(entity.equip)
        card = toObj(entity.card)
        boss = toObj(entity.boss)
        monster = toObj(entity.monster)
        newItem = toObj(entity.newItem)
    }

    // 获取总成就完成数
    fun findLibraryAllNum(): Int {
        var value = 0
        for (p in prop) {
            value += p.value.protoIds.size
        }

        for (e in equip) {
            value += e.value.protoIds.size
        }

        for (c in card) {
            value += c.value.protoIds.size
        }

        for (bb in boss) {
            value += bb.value.size
        }

        value += monster.size

        return value
    }

}

data class CommonLibVo(
    var protoId: Int, // 显示的类型
    var protoIds: HashSet<Int>, // 详细页面中的其他类型(比如不同品质的)
    var num: Int // 记录次数
)

data class MonsterLibVo(
    var protoId: Int,
    var killNum: Int
)

data class BossLibVo(
    var protoId: Int,
    var killNum: Int,
    var attackNum: Int
)