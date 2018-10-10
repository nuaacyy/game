package com.point18.slg2d.home.dc

import com.point18.slg2d.common.homeentities.ICON_NAMED_QUERY_PLAYER
import com.point18.slg2d.common.homeentities.IconEntity
import com.point18.slg2d.common.homeentities.IconPK
import com.point18.slg2d.home.AbstractDataContainer
import com.point18.slg2d.home.hpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.constg.PlayerId
import java.io.Serializable
import java.time.Duration


class IconDC : AbstractDataContainer<List<IconEntity>>() {

    val iconsMap = hashMapOf<Int, Icon>()

    override fun initImpl(data: List<IconEntity>?, depDCRepo: DataContainerRepo) {
        data?.forEach {
            val iconWrap = wdb.recover(it) { Icon() }

            iconsMap[it.iconId] = iconWrap
        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): List<IconEntity>? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(ICON_NAMED_QUERY_PLAYER)
                .setLong("playerId", playerId)
                .listNoDup<IconEntity>()
            list
        }
        return data
    }

    fun createIcon(overTime: Long, iconId: Int): (Icon) {
        val id = hpm.generateObjIdNew()
        val icon = Icon(id, iconId, overTime, playerId)

        wdb.save(icon)
        this.iconsMap[iconId] = icon

        return icon
    }
}

class Icon(
    var id: Long,

    var iconId: Int,
    var overTime: Long,
    var playerId: Long
) : EntityWrapper<IconEntity> {

    constructor() : this(
        0, 0, 0, 0
    )

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = IconPK(playerId, id)

    override fun toEntity(): IconEntity {
        return IconEntity(
            id,
            iconId,
            playerId,
            overTime
        )
    }

    override fun wrap(entity: IconEntity) {
        id = entity.id
        iconId = entity.iconId
        playerId = entity.playerId
        overTime = entity.overTime
    }
}


