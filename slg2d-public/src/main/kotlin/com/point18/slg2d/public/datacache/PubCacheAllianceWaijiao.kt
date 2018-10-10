package com.point18.slg2d.public.datacache

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.publicentities.ALLIANCE_WAIJIAO_NAMED_QUERY
import com.point18.slg2d.common.publicentities.AllianceWaijiaoEntity
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.mcache.OneKeyIndexSlice
import com.point18.slg2d.public.PublicDatabase
import com.point18.slg2d.public.ppm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.commonfunc.getNowTime
import java.io.Serializable
import java.time.Duration
import java.util.*

class AllianceWaijiao(
    var id: Long,

    var flagColor: Int, // 联盟旗帜的颜色
    var flagStyle: Int, // 联盟旗帜的样式
    var flagEffect: Int, // 联盟旗帜图案
    var aid: Long, // 联盟ID（留言者的联盟ID）
    var name: String, // 联盟名称
    var shortName: String, // 联盟简称
    var playerId: Long, // 玩家ID
    var playerName: String, // 玩家名
    var playerPosition: String, // 职位
    var createTime: Long, // 发布时间
    var waijiaoInfo: String, // 内容
    var areaNo: Int, // 留言时玩家在第几服
    var photoProtoId: Int, // 头像
    var nickName: String, // 玩家昵称
    var allianceId: Long//联盟ID
) : EntityWrapper<AllianceWaijiaoEntity> {
    constructor() : this(0, 0, 0, 0, 0, "", "", 0, "", "", 0, "", 0, 0, "", 0)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(30L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): AllianceWaijiaoEntity {
        return AllianceWaijiaoEntity(
            id,
            allianceId,
            flagColor,
            flagStyle,
            flagEffect,
            aid,
            name,
            shortName,
            playerId,
            playerName,
            playerPosition,
            createTime,
            waijiaoInfo,
            areaNo,
            photoProtoId,
            nickName
        )
    }

    override fun wrap(entity: AllianceWaijiaoEntity) {
        id = entity.id
        allianceId = entity.allianceId
        flagColor = entity.flagColor
        flagStyle = entity.flagStyle
        flagEffect = entity.flagEffect
        aid = entity.aid
        name = entity.name
        shortName = entity.shortName
        playerId = entity.playerId
        playerName = entity.playerName
        playerPosition = entity.playerPosition
        createTime = entity.createTime
        waijiaoInfo = entity.waijiaoInfo
        areaNo = entity.areaNo
        photoProtoId = entity.photoProtoId
        nickName = entity.nickName
    }

}

class CacheAllianceWaijiao(publicCache: PublicCache, val db: PublicDatabase) : BaseCache(publicCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.public.context.system(), javaClass)

    val allAllianceWaijiaoMap = OneKeyIndex { it: AllianceWaijiao -> it.id }     // 联盟外交表 Key:唯一ID
    val allianceWaijiaoMap = OneKeyIndexSlice({ it: AllianceWaijiao -> it.allianceId },
        { ita: AllianceWaijiao, itb: AllianceWaijiao -> ita.id == itb.id }) // 联盟外交表 Key:帮派ID

    override fun init() {
    }

    override fun doCacheLoad(publicInitData: PublicInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            publicInitData.allianceWaijiaos =
                    session.getNamedQuery(ALLIANCE_WAIJIAO_NAMED_QUERY)
                        .setLong("allianceId", publicInitData.publicId)
                        .listNoDup()
        }
    }

    override fun doInitData(publicInitData: PublicInitData) {
        publicInitData.allianceWaijiaos.forEach { entity ->
            try {
                val b = db.wdb.recover(entity) {
                    AllianceWaijiao()

                }

                this.allianceWaijiaoMap.insertByKey(b)
                this.allAllianceWaijiaoMap.insertByKey(b)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
    }

    fun createAllianceWaijiao(
        flagColor: Int, flagStyle: Int, flagEffect: Int, aid: Long, name: String,
        shortName: String, playerId: Long, playerName: String, playerPosition: String,
        allianceId: Long, info: String, areaNo: Int, photoProtoId: Int, nickName: String
    ): (AllianceWaijiao) {
        val id = ppm.generateObjIdNew()
        val allianceWaijiao = AllianceWaijiao(
            id,
            flagColor,      // 联盟旗帜的颜色
            flagStyle,      // 联盟旗帜的样式
            flagEffect,     // 联盟旗帜图案
            aid,            // 联盟ID（留言者的联盟ID）
            name,           // 联盟名称
            shortName,      // 联盟简称
            playerId,       // 玩家ID
            playerName,     // 玩家名
            playerPosition, // 职位
            getNowTime(),    // 发布时间
            info,           //内容
            areaNo,      // 所属服务器
            photoProtoId,  // 头像
            nickName, // 昵称
            allianceId     //联盟ID
        )

        insert(publicCache, allianceWaijiao)

        // 添加到缓存中
        allAllianceWaijiaoMap.insertByKey(allianceWaijiao)
        allianceWaijiaoMap.insertByKey(allianceWaijiao)

        return allianceWaijiao
    }

    // 查询联盟中所有的外交信息
    fun findAllianceWaijiaoByAllianceId(allianceId: Long): (LinkedList<AllianceWaijiao>) {
        val waijiaos = LinkedList<AllianceWaijiao>()
        allianceWaijiaoMap.findByKey(allianceId) {
            waijiaos.add(it)
        }

        return waijiaos
    }

    // 通过ID找到联盟外交数据
    fun findAllianceWaijiaoByid(id: Long): AllianceWaijiao? {
        return allAllianceWaijiaoMap.findByKey(id)
    }

    fun deleteAllianceWaijiaoById(allianceWaijiao: AllianceWaijiao?) {
        if (allianceWaijiao == null || allianceWaijiao.id == 0L) {
            return
        }

        delete(publicCache, allianceWaijiao)

        // 从缓存中删除
        allianceWaijiaoMap.deleteByKey(allianceWaijiao)
        allAllianceWaijiaoMap.deleteByKey(allianceWaijiao)
    }


    // 找出所有到期的外交信息
    fun findAllAllianceWaijiaoTimeOver(): (LinkedList<AllianceWaijiao>) {
        val deleteInfo = LinkedList<AllianceWaijiao>()
        allAllianceWaijiaoMap.map {
            val pastTime =
                it.createTime + 24 * (com.point18.slg2d.common.pc.pcs.basicProtoCache.allianceAiplomacyTimelimit * 24 * 3600) * 1000
            if (pastTime > 0 && pastTime < getNowTime()) {
                deleteInfo.add(it)
            }

            true
        }

        return deleteInfo
    }

    // 查询联盟中最早的一封外交信息
    fun findAllianceEarlyWaijiaoByAllianceId(allianceId: Long): (AllianceWaijiao?) {
        var deleteWaijiao: AllianceWaijiao? = null

        allianceWaijiaoMap.findByKey(allianceId) {
            val nDeleteWaijiao = deleteWaijiao
            if (nDeleteWaijiao == null) {
                deleteWaijiao = it
                return@findByKey true
            }
            if (it.createTime < nDeleteWaijiao.createTime) {
                deleteWaijiao = it
            }
            true
        }

        return deleteWaijiao
    }
}









