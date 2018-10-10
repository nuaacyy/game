package com.point18.slg2d.home.dc

import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.homeentities.*
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.AbstractDataContainer
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration

class GiftBagRecordDC : AbstractDataContainer<GiftBagRecordEntity>() {

    lateinit var giftBagRecord: GiftBagRecord

    override fun loadAllFromDB(playerId: Long, dao: CommonDao): GiftBagRecordEntity? {
        return dao.findWithTransaction { session ->
            val list = session.getNamedQuery(GIFT_BAG_RECORD_NAMED_QUERY)
                .setLong("playerId", playerId)
                .listNoDup<GiftBagRecordEntity>()
            list.firstOrNull()
        }
    }

    override fun initImpl(data: GiftBagRecordEntity?, depDCRepo: DataContainerRepo) {
        if (data != null) {
            val giftBagRecordWrap = wdb.recover(GiftBagRecord::class.java, data)

            giftBagRecord = giftBagRecordWrap
        }
    }

    fun createRecord(playerId: Long) {
        giftBagRecord = GiftBagRecord(playerId)
        wdb.save(giftBagRecord)
    }
}

class GiftBagRecord(
    var playerId: Long // 玩家编号
) : EntityWrapper<GiftBagRecordEntity> {
    private var records: HashMap<Int, Int> = hashMapOf()
    var effects: HashMap<Int, Int> = hashMapOf()

    constructor(): this(0)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = playerId

    override fun toEntity(): GiftBagRecordEntity {
        return GiftBagRecordEntity(
            playerId,
            toJson(records)
        )
    }

    override fun wrap(entity: GiftBagRecordEntity) {
        playerId = entity.playerId
        records = toObj(entity.records)
        // 加载时初始化effects数据
        for ((k, v) in records) {
            val giftBagProto =
                pcs.giftBagProtoCache.giftBagMap[k] ?: throw RuntimeException("giftBag.xml :: id:$k 不存在")
            for (i in 0..v) {
                for ((id, value) in giftBagProto.upEff) {
                    effects[id] = (effects[id] ?: 0) + value
                }
            }
        }
    }

    fun addRecord(id: Int) {
        records[id] = (records[id] ?: 0) + 1

        val giftBagProto = pcs.giftBagProtoCache.giftBagMap[id] ?: throw RuntimeException("giftBag.xml :: id:$id 不存在")
        for ((effectId, value) in giftBagProto.upEff) {
            effects[effectId] = (effects[effectId] ?: 0) + value
        }
    }
}