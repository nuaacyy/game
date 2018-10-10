package com.point18.slg2d.world.area4data

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.constg.OPEN_SETTING_TEST
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.worldentities.NoticeSwitchVo
import com.point18.slg2d.common.worldentities.PLAYER_SETTING_NAMED_QUERY_PLAYER
import com.point18.slg2d.common.worldentities.PlayerSettingEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.time.LocalDateTime
import java.util.*

class PlayerSetting(
    var worldId: Long,
    var id: Long,

    var refuseDisturbOpen: Int, // 勿扰开始时间
    var refuseDisturbEnd: Int, // 勿扰结束时间
    var cautionLv: Int, //警戒等级
    var switches: HashMap<Int, NoticeSwitchVo>, //通知开关
    var playerId: Long
) : EntityWrapper<PlayerSettingEntity> {

    constructor() : this(
        0L, 0L, 0, 0, 0, hashMapOf(), 0L
    )

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): PlayerSettingEntity {
        return PlayerSettingEntity(
            worldId,
            id,
            refuseDisturbOpen,
            refuseDisturbEnd,
            cautionLv,
            toJson(switches),
            playerId
        )
    }

    override fun wrap(entity: PlayerSettingEntity) {
        worldId = entity.worldId
        id = entity.id
        refuseDisturbOpen = entity.refuseDisturbOpen
        refuseDisturbEnd = entity.refuseDisturbEnd
        cautionLv = entity.cautionLv
        switches = toObj(entity.switches)
        playerId = entity.playerId
    }
}

class CachePlayerSetting(areaCache: AreaCache,val db: WorldDatabase) : BaseCache(areaCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.world.context.system(), javaClass)

    private val playerSettingMap = OneKeyIndex<Long, PlayerSetting> { it.playerId }

    override fun init() {
    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            worldInitData.playerSettingEntities =
                session.getNamedQuery(PLAYER_SETTING_NAMED_QUERY_PLAYER).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.playerSettingEntities.forEach { entity ->
            try {
                val playerSetting = db.wdb.recover(entity) { PlayerSetting() }

                this.playerSettingMap.insertByKey(playerSetting)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
    }

    // 管局玩家找到数据
    fun findPlayerSettingEntityByPlayerId(playerId: Long): PlayerSettingEntity? {
        val info = playerSettingMap.findByKey(playerId)
        return info?.toEntity()
    }

    fun createPlayerSettingByMoveServer(b: PlayerSettingEntity) {
        val playerSetting = PlayerSetting()
        playerSetting.wrap(b)
        val id = wpm.generateObjIdNew(areaCache)
        playerSetting.worldId = worldId
        playerSetting.id = id

        insert(areaCache, playerSetting)

        playerSettingMap.insertByKey(playerSetting)
    }

    // 移除某个玩家的所有数据
    fun clearPlayerSettingForMoveServer(playerId: Long) {
        val del = findPlayerSettingByPlayerId(playerId)
        if (del != null) {
            delete(areaCache, del)
            // 从缓存中删除
            playerSettingMap.deleteByKey(del)
        }
    }

    fun createPlayerSetting(playerId: Long): PlayerSetting {
        val id = wpm.generateObjIdNew(areaCache)
        val playerSetting = PlayerSetting(
            worldId,
            id,
            -1,
            -1,
            -1,
            hashMapOf(),
            playerId
        )

        insert(areaCache, playerSetting)

        // 存入缓存
        playerSettingMap.insertByKey(playerSetting)

        return playerSetting
    }

    fun findPlayerSettingByPlayerId(playerId: Long): PlayerSetting? {
        return playerSettingMap.findByKey(playerId)
    }

    class NoticeSettingCheckRt(
        var flag: Boolean,
        var titleLanId: String,
        var contentLanId: String,
        var music: String,
        var icon: String
    )

    fun checkNoticeSetting(playerId: Long, protoId: Int, enemyLv: Int): NoticeSettingCheckRt {

        val rt = NoticeSettingCheckRt(false, "", "", "", "")

        if (!OPEN_SETTING_TEST) {
            fetchOnlinePlayerSession(areaCache, playerId) ?: return rt
        }

        val playerSetting = this.findPlayerSettingByPlayerId(playerId) ?: return rt

        val appNoticeProto = pcs.appNoticeProtoCache.appNoticeProtoMap[protoId] ?: return rt

        // 获取开关
        val switchRecord = playerSetting.switches[appNoticeProto.typeId]
        val switch = if (switchRecord == null) {
            NoticeSwitchVo(appNoticeProto.typeId, appNoticeProto.disturbSwitch, appNoticeProto.switch)
        } else {
            switchRecord
        }

        // 禁用通知，返回false
        if (switch.switch == 0) return rt

        // 勿扰开启，判断勿扰设置
        if (switch.refuseDisturb == 1) {
            // 小于警戒等级返回false
            val cautionLv = if (playerSetting.cautionLv == -1) {
                pcs.basicProtoCache.ignoreTownLevel
            } else playerSetting.cautionLv

            if (enemyLv != 0) {
                if (enemyLv <= cautionLv) return rt
            }

            val refuseDisturbOpen = if (playerSetting.refuseDisturbOpen == -1) {
                val startHour = pcs.basicProtoCache.disturbMin[0][0]
                val startMin = pcs.basicProtoCache.disturbMin[0][1]
                startHour * 60 + startMin
            } else playerSetting.refuseDisturbOpen

            val refuseDisturbEnd = if (playerSetting.refuseDisturbEnd == -1) {
                val endHour = pcs.basicProtoCache.disturbMin[1][0]
                val endMin = pcs.basicProtoCache.disturbMin[1][1]
                endHour * 60 + endMin
            } else playerSetting.refuseDisturbEnd

            val now = LocalDateTime.now()
            val allMinutes = now.hour * 60 + now.minute
            if (refuseDisturbOpen <= refuseDisturbEnd) {
                if (allMinutes in refuseDisturbOpen..refuseDisturbEnd) {
                    return rt
                }
            } else {
                if (allMinutes >= refuseDisturbOpen || allMinutes <= refuseDisturbEnd) {
                    return rt
                }
            }
        }

        rt.flag = true
        rt.titleLanId = appNoticeProto.idTitle
        rt.contentLanId = appNoticeProto.message
        rt.music = appNoticeProto.messagemusic
        rt.icon = appNoticeProto.messageIcon

        return rt
    }
}