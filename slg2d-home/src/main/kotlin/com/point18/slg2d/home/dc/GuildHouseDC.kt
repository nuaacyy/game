package com.point18.slg2d.home.dc

import com.point18.slg2d.common.homeentities.GUILD_HOUSE_NAMED_QUERY_PLAYER
import com.point18.slg2d.common.homeentities.GuildHouseEntity
import com.point18.slg2d.home.AbstractDataContainer
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.constg.PlayerId
import java.io.Serializable
import java.time.Duration

// 后宅

class GuildHouseDC : AbstractDataContainer<GuildHouseEntity>() {

    lateinit var guildHouse: GuildHouse

    override fun initImpl(data: GuildHouseEntity?, depDCRepo: DataContainerRepo) {
        if (data != null) {
            val guildHouseWrap = wdb.recover(data) { GuildHouse() }
            guildHouse = guildHouseWrap
        }else{
            val guildHouse = GuildHouse(
                "", 0, playerId
            )

            wdb.save(guildHouse)
            this.guildHouse = guildHouse
        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): GuildHouseEntity? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(GUILD_HOUSE_NAMED_QUERY_PLAYER)
                .setLong("playerId", playerId)
                .listNoDup<GuildHouseEntity>()
            if (list.isEmpty()) {
                null
            } else {
                list[0]
            }
        }
        return data
    }
}

class GuildHouse(
    var name: String,    // 后宅名称
    var comfort: Int,    // 舒适度
    var playerId: Long    // 所属玩家Id
) : EntityWrapper<GuildHouseEntity> {

    constructor() : this(
        "", 0, 0
    )

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = playerId

    // GuildHouse -> GuildHouseEntity
    override fun toEntity(): GuildHouseEntity {
        return GuildHouseEntity(
            playerId,
            name,
            comfort
        )
    }

    override fun wrap(entity: GuildHouseEntity) {
        playerId = entity.playerId
        name = entity.name
        comfort = entity.comfort
    }
}