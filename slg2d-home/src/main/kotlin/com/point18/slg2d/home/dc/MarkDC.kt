package com.point18.slg2d.home.dc

import com.point18.slg2d.common.homeentities.MARK_NAMED_QUERY_PLAYER
import com.point18.slg2d.common.homeentities.MarkEntity
import com.point18.slg2d.common.homeentities.MarkPK
import com.point18.slg2d.home.AbstractDataContainer
import com.point18.slg2d.home.hpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.constg.PlayerId
import java.io.Serializable
import java.time.Duration
import java.util.*

class MarkDC : AbstractDataContainer<List<MarkEntity>>() {

    val marks = LinkedList<Mark>()

    override fun initImpl(data: List<MarkEntity>?, depDCRepo: DataContainerRepo) {
        data?.map {
            val mark = wdb.recover(it) { Mark() }

            marks += mark
        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): List<MarkEntity>? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(MARK_NAMED_QUERY_PLAYER)
                .setLong("playerId", playerId)
                .listNoDup<MarkEntity>()
            list
        }

        return data
    }

    // 添加标记
    fun createPlayerMark(
        x: Int,
        y: Int,
        pltAreaNo: Int,
        group: Int,
        name: String
    ): Mark {
        val id = hpm.generateObjIdNew()
        val mark = Mark(
            id,
            x,
            y,
            group,
            pltAreaNo,
            name,
            playerId
        )

        wdb.save(mark)
        this.marks += mark

        return mark
    }

    fun delMark(mark: Mark) {
        wdb.delete(mark)

        // 删除缓存
        this.marks.removeIf { it.id == mark.id }
    }

    // 获取玩家标记列表
    fun findMarkListByPlayerId(): LinkedList<Mark> {
        // 尝试从缓存中获取
        return this.marks
    }

    // 查询玩家指定的点是否已标记
    fun findMarkByPos(x: Int, y: Int, pltAreaNo: Int): Mark? {
        return this.marks.firstOrNull { it.x == x && it.y == y && it.pltAreaNo == pltAreaNo }
    }

    // 查询玩家指定的点是否已标记
    fun findMarkById(id: Long): Mark? {
        return this.marks.firstOrNull { it.id == id }
    }
}

class Mark(
    var id: Long,

    var x: Int,  // 位置x
    var y: Int,  // 位置y
    var group: Int,              // 分组
    var pltAreaNo: Int,  // 所属服务器  游戏内K轴
    var name: String,
    var playerId: Long    // 玩家编号
) : EntityWrapper<MarkEntity> {
    constructor() : this(0, 0, 0, 0, 0, "", 0)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = MarkPK(playerId, id)

    override fun toEntity(): MarkEntity {
        return MarkEntity(
            id,
            x,
            y,
            group,
            pltAreaNo,
            name,
            playerId
        )
    }

    override fun wrap(entity: MarkEntity) {
        id = entity.id
        x = entity.x
        y = entity.y
        group = entity.group
        pltAreaNo = entity.pltAreaNo
        name = entity.name
        playerId = entity.playerId
    }
}

