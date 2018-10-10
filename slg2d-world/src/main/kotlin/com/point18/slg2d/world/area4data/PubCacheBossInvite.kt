package com.point18.slg2d.world.area4data

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.mcache.OneKeyIndexSlice
import com.point18.slg2d.common.mcache.TwoKeyIndexSlice
import com.point18.slg2d.common.worldentities.BOSS_INVITE_NAMED_QUERY
import com.point18.slg2d.common.worldentities.BossInviteEntity
import com.point18.slg2d.world.WorldDatabase
import com.point18.slg2d.world.wpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

class BossInvite(
    var worldId: Long,
    var id: Long,

    var x: Int,         // 所在坐标
    var y: Int,         // 所在坐标
    var allianceId: Long,// 联盟Id
    var latestInviteTime: Long  //最新邀请时间
) : EntityWrapper<BossInviteEntity> {
    constructor() : this(0, 0, 0, 0, 0, 0)

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): BossInviteEntity {
        return BossInviteEntity(
            worldId,
            id,
            x,
            y,
            allianceId,
            latestInviteTime
        )
    }

    override fun wrap(entity: BossInviteEntity) {
        worldId = entity.worldId
        id = entity.id
        x = entity.posX
        y = entity.posY
        allianceId = entity.allianceId
        latestInviteTime = entity.latestInviteTime
    }
}

interface IBossInfo {
    fun getPosX(): Int

    fun getPosY(): Int

    fun getBossProtoId(): Int

    fun getCurrentHp(): Int

    fun getAtkRecordSet(): HashSet<Long>
}

class CacheBossInvite(areaCache: AreaCache, val db: WorldDatabase) : BaseCache(areaCache) {
    private val inviteByXy =
        TwoKeyIndexSlice<Int, Int, BossInvite>({ it.x }, { it.y }, { ita, itb -> ita.allianceId == itb.allianceId })
    private val inviteByAllianceId =
        OneKeyIndexSlice<Long, BossInvite>({ it.allianceId }, { ita, itb -> ita.allianceId == itb.allianceId })

    override fun init() {
    }

    override fun doCacheLoad(worldInitData: WorldInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            worldInitData.bossEntities =
                    session.getNamedQuery(BOSS_INVITE_NAMED_QUERY).setLong("worldId", worldId).listNoDup()
        }
    }

    override fun doInitData(worldInitData: WorldInitData) {
        worldInitData.bossEntities.forEach {
            val invite = db.wdb.recover(it) { BossInvite() }
            inviteByXy.insertByKey(invite)
            inviteByAllianceId.insertByKey(invite)
        }
    }

    override fun doCacheLink() {
    }

    //创建魔物邀请
    fun createBossInvite(player: Player, bossInfo: IBossInfo): BossInvite {
        val id = wpm.generateObjIdNew(areaCache)
        val invite = BossInvite(worldId, id, bossInfo.getPosX(), bossInfo.getPosY(), player.allianceId, getNowTime())
        inviteByXy.insertByKey(invite)
        inviteByAllianceId.insertByKey(invite)

        insert(areaCache, invite)

        return invite
    }

    //删除魔物邀请
    fun deleteBossInvite(invite: BossInvite) {
        delete(areaCache, invite)

        inviteByXy.deleteByKey(invite)
        inviteByAllianceId.deleteByKey(invite)
    }

    //根据xy坐标查找邀请
    fun findInvitesByXy(x: Int, y: Int): List<BossInvite> {
        val inviteList = LinkedList<BossInvite>()
        inviteByXy.findByKey(x, y) {
            inviteList.add(it)
        }
        return inviteList
    }

    //根据坐标和玩家Id查找邀请
    fun findInviteByPosAndAllianceId(x: Int, y: Int, allianceId: Long): BossInvite? {
        var invite: BossInvite? = null
        inviteByAllianceId.findByKey(allianceId) {
            if (it.x == x && it.y == y) {
                invite = it
                return@findByKey false
            }
            return@findByKey true
        }
        return invite
    }

    //根据联盟Id查找邀请
    fun findInviteByAllianceId(allianceId: Long): List<BossInvite> {
        val invites = LinkedList<BossInvite>()
        inviteByAllianceId.findByKey(allianceId) {
            invites.add(it)
        }
        return invites
    }
}