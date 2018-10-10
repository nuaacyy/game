package com.point18.slg2d.home.dc

import com.point18.slg2d.common.homeentities.SKIN_NAMED_QUERY
import com.point18.slg2d.common.homeentities.SkinEntity
import com.point18.slg2d.common.homeentities.SkinPK
import com.point18.slg2d.home.AbstractDataContainer
import com.point18.slg2d.home.hpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.constg.PlayerId
import com.point18.slg2d.common.constg.intToBool
import java.io.Serializable
import java.time.Duration
import java.util.*

class SkinDC : AbstractDataContainer<List<SkinEntity>>() {
    val skins = LinkedList<Skin>()
    var nowSkin: Skin? = null

    override fun initImpl(data: List<SkinEntity>?, depDCRepo: DataContainerRepo) {
        data?.map {
            val skin = wdb.recover(it) { Skin() }
            if (intToBool(skin.isUse)) {
                nowSkin = skin
            }

            skins.add(skin)
        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): List<SkinEntity>? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(SKIN_NAMED_QUERY)
                .setLong("playerId", playerId)
                .listNoDup<SkinEntity>()
            list
        }
        return data
    }

    // 新建Skin
    fun createSkin(skinType: Int, star: Int): Skin {
        val id = hpm.generateObjIdNew()
        val skin = Skin(
            id,
            skinType,
            star,
            0,
            playerId
        )

        wdb.save(skin)
        skins.add(skin)
        return skin
    }

    // 删除Skin
    fun deleteSkin(skin: Skin) {
        // 删除数据库
        wdb.delete(skin)
        this.skins.remove(skin)
    }

    // 获取玩家所有的Skins
    fun findSkinsByPlayerId(): LinkedList<Skin> {
        // 尝试从缓存中获取
        // 全部
        return this.skins
    }
}

class Skin(
    var id: Long,

    var skinType: Int,  // 皮肤类型 1默认皮肤
    var star: Int,  // 星数
    var isUse: Int,
    var playerId: Long    // 玩家ID
) : EntityWrapper<SkinEntity> {
    constructor() : this(0, 0, 0, 0, 0L)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = SkinPK(playerId, id)

    override fun toEntity(): SkinEntity {
        return SkinEntity(
            id,
            skinType,
            star,
            isUse,
            playerId
        )
    }

    override fun wrap(entity: SkinEntity) {
        id = entity.id
        skinType = entity.skinType
        star = entity.star
        isUse = entity.isUse
        playerId = entity.playerId
    }

}

