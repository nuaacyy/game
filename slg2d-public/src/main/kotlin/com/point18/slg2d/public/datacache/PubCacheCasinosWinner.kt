package com.point18.slg2d.public.datacache

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.publicentities.CasinosWinnerEntity
import com.point18.slg2d.common.publicentities.PUBLIC_WINNER_ID_QUERY
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.public.PublicDatabase
import com.point18.slg2d.public.ppm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.constg.CASINO_ID
import java.io.Serializable
import java.time.Duration
import java.util.*

class CasinosWinner(
    var id: Long,
    var publicId:Long,
    var areaNo: Int, // 服务器编号
    var playerName: String, // 玩家名
    var allianceShortName: String, // 玩家昵称
    var rewardTime: Long, // 获奖日期
    var giftNum: Long, // 获奖金额
    var playerId: Long // 玩家id

) : EntityWrapper<CasinosWinnerEntity> {
    constructor() : this(0, 0,0, "", "", 0,0,0)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(30L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): CasinosWinnerEntity {
        return CasinosWinnerEntity(
            id,
            publicId,
            areaNo,
            playerName,
            allianceShortName,
            rewardTime,
            giftNum,
            playerId
        )
    }

    override fun wrap(entity: CasinosWinnerEntity) {
        id = entity.id
        publicId = entity.publicId
        areaNo = entity.areaNo
        playerName = entity.playerName
        allianceShortName = entity.allianceShortName
        rewardTime = entity.rewardTime
        giftNum = entity.giftNum
        playerId = entity.playerId
    }

}

class CacheCasinosWinner(publicCache: PublicCache, val db: PublicDatabase) : BaseCache(publicCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.public.context.system(), javaClass)

    val casinosWinnerMap = OneKeyIndex { it: CasinosWinner -> it.id }

    override fun init() {
    }

    override fun doCacheLoad(publicInitData: PublicInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            publicInitData.casinosWinners =
                    session.getNamedQuery(PUBLIC_WINNER_ID_QUERY)
                        .setLong("publicId", publicInitData.publicId)
                        .listNoDup()
        }
    }

    override fun doInitData(publicInitData: PublicInitData) {
        publicInitData.casinosWinners.forEach { entity ->
            try {
                val b = db.wdb.recover(entity) {
                    CasinosWinner()

                }

                this.casinosWinnerMap.insertByKey(b)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
    }

    fun createCasinosWinner(
        areaNo: Int,
        playerName: String,
        allianceShortName: String,
        rewardTime: Long,
        giftNum: Long,
        playerId: Long
    ): CasinosWinner {
        val id = ppm.generateObjIdNew()
        val publicId = CASINO_ID
        val casinosWinner = CasinosWinner(
            id,
            publicId,
            areaNo,
            playerName,
            allianceShortName,
            rewardTime,
            giftNum,
            playerId
        )

        insert(publicCache, casinosWinner)

        // 添加到缓存中
        casinosWinnerMap.insertByKey(casinosWinner)

        return casinosWinner
    }

    fun deleteCasinosWinner(casinosWinner: CasinosWinner?) {
        if (casinosWinner == null || casinosWinner.id == 0L) {
            return
        }
        delete(publicCache, casinosWinner)
        casinosWinnerMap.deleteByKey(casinosWinner)
    }

    fun findCasinosWinner(): LinkedList<CasinosWinner>? {
        val players = LinkedList<CasinosWinner>()
        casinosWinnerMap.map {
            players.add(it)
        }

        return players
    }
}






