package com.point18.slg2d.home.dc

import com.point18.slg2d.common.homeentities.FURNITURE_NAMED_QUERY_PLAYER
import com.point18.slg2d.common.homeentities.FurnitureEntity
import com.point18.slg2d.common.homeentities.FurniturePK
import com.point18.slg2d.home.AbstractDataContainer
import com.point18.slg2d.home.hpm
import com.point18.slg2d.common.mcache.ThreeKeyIndex
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.constg.PlayerId
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import java.io.Serializable
import java.time.Duration
import java.util.*

// 家具

class FurnitureDC : AbstractDataContainer<List<FurnitureEntity>>() {

    val nowFurniture: MutableMap<Long, Furniture> = mutableMapOf() // 玩家后宅家具缓存
    val furnitureBag: MutableMap<Int, LinkedList<Furniture>> = mutableMapOf()  // 玩家家具背包
    var area: ThreeKeyIndex<Int, Int, Int, Array<Int>> = ThreeKeyIndex(
        { it: Array<Int> -> it[0] },
        { it: Array<Int> -> it[1] },
        { it: Array<Int> -> it[2] }
    )

    override fun initImpl(data: List<FurnitureEntity>?, depDCRepo: DataContainerRepo) {
        data?.forEach {
            val furnitureWrap = wdb.recover(it) { Furniture() }

            add(furnitureWrap)
        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): List<FurnitureEntity>? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(FURNITURE_NAMED_QUERY_PLAYER)
                .setLong("playerId", playerId)
                .listNoDup<FurnitureEntity>()
            list
        }
        return data
    }

    private fun add(v: Furniture) {
        if (v.state == 0) {
            // 未摆放，放入背包
            var typeList = furnitureBag[v.protoId]
            if (typeList == null) {
                typeList = LinkedList()
                furnitureBag[v.protoId] = typeList
            }
            typeList.add(v)

        } else {
            val furProto = pcs.furnitureProtoCache.furnitureProtoMap[v.protoId]
            // 连模板都找不到，就不放这个家具了
            if (furProto != null) {
                nowFurniture[v.id] = v
                // 初始化占地
                for (x in v.x until v.x + furProto.floorSpace[0]) {
                    for (y in v.y until v.y + furProto.floorSpace[1]) {
                        area.insertByKey(arrayOf(v.floorIdx, x, y))
                    }
                }
            }
        }
    }

    /**
     * 把状态为未摆放(0)的家具从场景中放入包里
     * 把状态为已摆放(1)的家具从包中放入场景中
     */
    fun putOutOrInBag(furniture: Furniture) {
        if (furniture.state == 0) { // 状态为未摆放(0)
            val fur = nowFurniture[furniture.id]
            if (fur != null) {
                nowFurniture.remove(fur.id)
                var typeList = furnitureBag[furniture.protoId]
                if (typeList == null) {
                    typeList = LinkedList()
                    furnitureBag[furniture.protoId] = typeList
                }
                typeList.add(furniture)
            }
        } else if (furniture.state == 1) { // 状态为已摆放(0)
            val typeList = furnitureBag[furniture.protoId]
            if (typeList != null) {
                typeList.remove(furniture)
                if (typeList.size == 0) {
                    furnitureBag.remove(furniture.protoId)
                }
            }
            nowFurniture[furniture.id] = furniture
        }
    }

    fun createFurniture(
        playerId: Long,
        protoId: Int
    ): Furniture {
        val id = hpm.generateObjIdNew()
        val furniture = Furniture(
            id,
            0, 0, 0, 0,
            0, 0, 0,
            LinkedList(), protoId,
            0, playerId
        )

        wdb.save(furniture)
        add(furniture)

        return furniture
    }

}

class Furniture(
    // 家具唯一ID
    var id: Long,

    var floorIdx: Int,    // 楼层 1-1F 2-2F
    var x: Int,    // 家具x坐标
    var y: Int,    // 家具y坐标
    var direction: Int,    // 家具朝向
    var startTime: Long,  // 开始收集时间
    var checkTime: Long,  // 刷新收集时间
    var endTime: Long,  // 收集满时间
    var produceRes: LinkedList<ResVo>,  // 产出资源数量
    var protoId: Int,    // 家具模板id
    var state: Int,    // 家具状态 0-未放置 1-已放置
    var playerId: Long    // 所属玩家Id
) : EntityWrapper<FurnitureEntity> {

    constructor() : this(
        0, 0, 0, 0, 0,
        0, 0, 0,
        LinkedList<ResVo>(), 0, 0, 0
    )

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = FurniturePK(playerId, id)

    override fun toEntity(): FurnitureEntity {
        return FurnitureEntity(
            this.id,
            this.floorIdx,
            this.x,
            this.y,
            this.direction,
            this.startTime,
            this.checkTime,
            this.endTime,
            toJson(produceRes),
            this.protoId,
            this.state,
            this.playerId
        )
    }

    override fun wrap(entity: FurnitureEntity) {
        id = entity.id
        floorIdx = entity.floorIdx
        x = entity.x
        y = entity.y
        direction = entity.direction
        startTime = entity.startTime
        checkTime = entity.checkTime
        endTime = entity.endTime
        produceRes = toObj(entity.produceRes)
        protoId = entity.protoId
        state = entity.state
        playerId = entity.playerId
    }
}