package com.point18.slg2d.home.dc

import com.point18.slg2d.common.homeentities.*
import com.point18.slg2d.home.AbstractDataContainer
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.hpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.constg.PlayerId
import java.io.Serializable
import java.time.Duration
import java.util.*

class InMakeKingEquipDC : AbstractDataContainer<List<InMakeKingEquipEntity>>() {
    val inMakeKingEquipMapByPlayer = LinkedList<InMakeKingEquip>()

    override fun initImpl(data: List<InMakeKingEquipEntity>?, depDCRepo: DataContainerRepo) {
        data?.map {
            val kingEquipPlan = wdb.recover(it) { InMakeKingEquip() }

            inMakeKingEquipMapByPlayer += kingEquipPlan
        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): List<InMakeKingEquipEntity>? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(IN_MAKE_KING_EQUIP_NAMED_QUERY)
                .setLong("playerId", playerId)
                .listNoDup<InMakeKingEquipEntity>()
            list
        }
        return data
    }

    fun createInMakeKingEquip(
        session: PlayerActor,
        kingEquipId: Int,
        costEquipId: Long,
        heiYaoId: Int,
        overTime: Long,
        playerId: Long
    ): InMakeKingEquip {
        val id = hpm.generateObjIdNew()
        val inMakeKingEquip = InMakeKingEquip(
            id,
            kingEquipId,
            heiYaoId,
            costEquipId,
            overTime,
            playerId
        )

        wdb.save(inMakeKingEquip)

        // 添加到缓存中
        inMakeKingEquipMapByPlayer += inMakeKingEquip

        return inMakeKingEquip
    }

    // 查询某玩家的所有的正在锻造的信息
    fun findInMakeByPlayerId(): LinkedList<InMakeKingEquip> {
        return inMakeKingEquipMapByPlayer
    }

    // 查询某玩家的某ID的信息
    fun findInMakeByPlayerIdAndId(id: Long): InMakeKingEquip? {
        for (vo in inMakeKingEquipMapByPlayer) {
            if (vo.id == id) {
                return vo
            }
        }

        return null
    }

    fun deleteInMake(inMake: InMakeKingEquip) {
        if (inMake.id == 0L) {
            return
        }

        wdb.delete(inMake)

        // 从缓存中删除
        inMakeKingEquipMapByPlayer.remove(inMake)
    }

}

// 正在锻造中的君主装备
class InMakeKingEquip(
    var id: Long,

    var kingEquipId: Int,                // 要锻造的模版
    var heiYaoId: Int,                // 放入的黑曜石模版ID
    var costEquipId: Long,                // 放入的材料装备唯一ID // 进阶才会有 锻造的话是0
    var overTime: Long,  // 锻造结束时间
    var playerId: Long           // 玩家ID
) : EntityWrapper<InMakeKingEquipEntity> {
    constructor() : this(0, 0, 0, 0, 0, 0)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = InMakeKingEquipPK(playerId, id)

    override fun toEntity(): InMakeKingEquipEntity {
        return InMakeKingEquipEntity(
            id,
            kingEquipId,
            heiYaoId,
            costEquipId,
            overTime,
            playerId
        )
    }

    override fun wrap(entity: InMakeKingEquipEntity) {
        id = entity.id
        kingEquipId = entity.kingEquipId
        heiYaoId = entity.heiYaoId
        costEquipId = entity.costEquipId
        overTime = entity.overTime
        playerId = entity.playerId
    }
}

