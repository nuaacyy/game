package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.mcache.TwoKeyIndex
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.resStringToResVoList
import com.point18.slg2d.common.pc.resVoToResString
import com.point18.slg2d.common.worldentities.TRANSPORT_REQUEST_NAMED_QUERY
import com.point18.slg2d.common.worldentities.TransportRequestEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

//运输请求
class TransportRequest(
    var worldId: Long,
    var id: Long,

    var resVo: LinkedList<ResVo>,
    var allianceId: Long,  //联盟Id
    var playerId: Long            // 玩家ID
) : EntityWrapper<TransportRequestEntity> {
    constructor() : this(0L, 0, LinkedList(), 0L, 0L)

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): TransportRequestEntity {
        return TransportRequestEntity(
            worldId,
            id,
            resVoToResString(resVo),
            allianceId,
            playerId
        )
    }

    override fun wrap(entity: TransportRequestEntity) {
        val resList = resStringToResVoList(entity.res)
        if (resList == null) {
            assert(false) { "TransportRequest中的res字段反编译错误:${entity.res}" }
            return
        }

        worldId = entity.worldId
        id = entity.id
        resVo = LinkedList(resList)
        allianceId = entity.allianceId
        playerId = entity.playerId
    }

    fun putResVo(res: LinkedList<ResVo>) {
        this.resVo = res
    }
}

class CacheTransportRequest(areaCache: AreaCache,val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    private val reqMap = OneKeyIndex<Long, TransportRequest> { it.id }
    private val reqMapByPlayerId = OneKeyIndex<Long, TransportRequest> { it.playerId }
    private val reqMapByAllianceId = TwoKeyIndex<Long, Long, TransportRequest>({ it.allianceId }, { it.playerId })

    override fun init() {
    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        val worldId = areaCache.areaConfig.pltAreaNo
        dao.execWithTransaction { session ->
            worldInitData.transportRequestEntities =
                session.getNamedQuery(TRANSPORT_REQUEST_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.transportRequestEntities.forEach { entity ->
            try {
                val transportRequest = db.wdb.recover(entity) { TransportRequest() }

                reqMap.insertByKey(transportRequest)
                reqMapByPlayerId.insertByKey(transportRequest)
                reqMapByAllianceId.insertByKey(transportRequest)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
    }

    //创建运输请求
    fun createTransportRequest(player: Player, res: LinkedList<ResVo>): TransportRequest {
        val id = wpm.generateObjIdNew(areaCache)
        val req = TransportRequest(
            worldId,
            id,
            res,
            player.allianceId,
            player.id
        )
        insert(areaCache, req)
        reqMap.insertByKey(req)
        reqMapByPlayerId.insertByKey(req)
        reqMapByAllianceId.insertByKey(req)
        return req
    }

    //删除运输请求
    fun deleteTransportRequest(req: TransportRequest) {
        if (req.id == 0L) {
            return
        }

        delete(areaCache, req)

        reqMap.deleteByKey(req)
        reqMapByPlayerId.deleteByKey(req)
        reqMapByAllianceId.deleteByKey(req)
    }

    //根据Id查询运输请求
    fun findTransportRequestById(id: Long): TransportRequest? {
        return reqMap.findByKey(id)
    }

    //根据联盟Id查询运输请求
    fun findTransportRequestByAllianceId(allianceId: Long): List<TransportRequest> {
        val reqList = LinkedList<TransportRequest>()
        reqMapByAllianceId.findByOneKeyFilter(allianceId) { reqList.add(it) }
        return reqList
    }

    //根据玩家Id查询运输请求
    fun findTransportRequestByPlayerId(playerId: Long): TransportRequest? {
        return reqMapByPlayerId.findByKey(playerId)
    }

}
