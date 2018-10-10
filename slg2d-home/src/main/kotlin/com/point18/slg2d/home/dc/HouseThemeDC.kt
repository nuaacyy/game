package com.point18.slg2d.home.dc

import com.point18.slg2d.common.homeentities.HOUSE_THEME_NAMED_QUERY_PLAYER
import com.point18.slg2d.common.homeentities.HouseThemeEntity
import com.point18.slg2d.common.homeentities.HouseThemePK
import com.point18.slg2d.home.AbstractDataContainer
import com.point18.slg2d.home.hpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.constg.PlayerId
import java.io.Serializable
import java.time.Duration
import java.util.*

class HouseThemeDC : AbstractDataContainer<List<HouseThemeEntity>>() {

    val houseThemeMap = hashMapOf<Long, HouseTheme>()

    override fun initImpl(data: List<HouseThemeEntity>?, depDCRepo: DataContainerRepo) {
        data?.forEach {
            val houseTheme = wdb.recover(it) { HouseTheme() }

            houseThemeMap[houseTheme.id] = houseTheme
        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): List<HouseThemeEntity>? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(HOUSE_THEME_NAMED_QUERY_PLAYER)
                .setLong("playerId", playerId)
                .listNoDup<HouseThemeEntity>()
            list
        }
        return data
    }

    fun createHouseTheme(
        playerId: Long,
        themeName: String,
        subjectId: Int,
        furnitureInfoList: LinkedList<FurnitureInfo>
    ): HouseTheme {
        val id = hpm.generateObjIdNew()
        val houseTheme = HouseTheme(
            id,
            themeName,
            furnitureInfoList,
            subjectId,
            playerId
        )

        wdb.save(houseTheme)

        this.houseThemeMap[id] = houseTheme

        return houseTheme
    }

    fun deleteHouseTheme(
        houseTheme: HouseTheme
    ) {
        this.houseThemeMap.remove(houseTheme.id)
        wdb.delete(houseTheme)
    }
}

class HouseTheme(
    // 主题唯一ID
    var id: Long,
    var themeName: String,
    var furnitureInfoList: LinkedList<FurnitureInfo>,  // 主题中的家具信息
    var subjectId: Int,   // 是否是商店主题
    var playerId: Long    // 所属玩家Id
) : EntityWrapper<HouseThemeEntity> {

    constructor() : this(
        0, "", LinkedList(), 0, 0
    )

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = HouseThemePK(playerId, id)

    override fun toEntity(): HouseThemeEntity {
        return HouseThemeEntity(
            id,
            themeName,
            toJson(furnitureInfoList),
            subjectId,
            playerId
        )
    }

    override fun wrap(entity: HouseThemeEntity) {
        id = entity.id
        themeName = entity.themeName
        furnitureInfoList = toObj(entity.template)
        subjectId = entity.subjectId
        playerId = entity.playerId
    }
}

class FurnitureInfo(
    var x: Int,    // 家具x坐标
    var y: Int,    // 家具y坐标
    var direction: Int,    //家具朝向
    var protoId: Int    // 家具模板Id
) {
    constructor() : this(
        0, 0, 0, 0
    )
}


