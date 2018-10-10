package com.point18.slg2d.public.datacache

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.publicentities.ALLIANCE_GIFT_NAMED_QUERY
import com.point18.slg2d.common.publicentities.AllianceGiftEntity
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.public.PublicDatabase
import com.point18.slg2d.public.ppm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration

class AllianceGift(
    var id: Long,

    var bigGiftId: Int, // 左侧大礼物ID
    var bigGiftExp: Int, // 左侧大礼物钥匙值
    var giftLv: Int, // 上侧的礼物等级
    var giftExp: Int, // 上侧的礼物经验
    var allianceId: Long //联盟ID

) : EntityWrapper<AllianceGiftEntity> {
    constructor() : this(0, 0, 0, 0, 0, 0)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(30L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): AllianceGiftEntity {
        return AllianceGiftEntity(
            id,
            allianceId,
            bigGiftId,
            bigGiftExp,
            giftLv,
            giftExp
        )
    }

    override fun wrap(entity: AllianceGiftEntity) {
        id = entity.id
        allianceId = entity.allianceId
        bigGiftId = entity.bigGiftId
        bigGiftExp = entity.bigGiftExp
        giftLv = entity.giftLv
        giftExp = entity.giftExp
    }

}

class CacheAllianceGift(publicCache: PublicCache, val db: PublicDatabase) : BaseCache(publicCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.public.context.system(), javaClass)

    val allAllianceGiftMap = OneKeyIndex { it: AllianceGift -> it.allianceId } // 联盟礼物表 Key:帮派唯一ID

    override fun init() {
    }

    override fun doCacheLoad(publicInitData: PublicInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            publicInitData.allianceGifts =
                session.getNamedQuery(ALLIANCE_GIFT_NAMED_QUERY)
                    .setLong("allianceId", publicInitData.publicId)
                    .listNoDup()
        }
    }

    override fun doInitData(publicInitData: PublicInitData) {
        publicInitData.allianceGifts.forEach { entity ->
            try {
                val b = db.wdb.recover(entity) {
                    AllianceGift()

                }

                this.allAllianceGiftMap.insertByKey(b)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
    }

    fun createAllianceGift(bigGiftId: Int, allianceId: Long): (AllianceGift) {
        val id = ppm.generateObjIdNew()
        val allianceGift = AllianceGift(id, bigGiftId, 0, 0, 0, allianceId)

        insert(publicCache, allianceGift)

        // 添加到缓存中
        allAllianceGiftMap.insertByKey(allianceGift)

        return allianceGift
    }

    // 删除联盟礼物信息
    fun deleteAllianceGift(alce: AllianceGift?) {
        if (alce == null || alce.id == 0L) {
            return
        }
        delete(publicCache, alce)
        allAllianceGiftMap.deleteByKey(alce)
    }

    // 查询联盟礼物信息
    fun findAllianceGiftById(allianceId: Long): (AllianceGift?) {
        return allAllianceGiftMap.findByKey(allianceId)
    }
}