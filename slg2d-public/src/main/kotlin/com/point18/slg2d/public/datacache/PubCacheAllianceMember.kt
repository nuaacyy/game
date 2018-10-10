package com.point18.slg2d.public.datacache

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.mcache.TwoKeyIndex
import com.point18.slg2d.common.publicentities.ALLIANCE_MEMBER_NAMED_QUERY
import com.point18.slg2d.common.publicentities.AllianceMemberEntity
import com.point18.slg2d.public.PublicDatabase
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

class AllianceMember(
    var id: Long,

    var allianceId: Long, // 所属同盟ID
    var allianceAt: Long,  // 加入联盟时间
    var alliancePosMap: MutableMap<Int, AlliancePosInfo>,
    var allianceRnum: Int,          // R1 - R5
    var allianceNickName: String,  // 联盟昵称
    var canHelpNum: Int,         // 可被帮助次数
    var name: String, // 玩家角色名
    var photoProtoId: Int,        // 头像
    var onlineState: Int,                // 在线状态 0-离线 1-在线
    var lastLeaveTime: Long,  // 最后一次离线时间
    var memPower: Long,             // 势力
    var playerCastleLv: Int,             // 玩家的主堡等级
    var mapPltAreaId: Long, // 玩家所属地图服ID
    var mapAreaNo: Int,         // 玩家所属地图服的服务器编号
    var monsterScore: Int,   //本日获得的魔物积分
    var lastGetMonsterScore: Long, // 上次获得魔物积分时间
    var allianceCompetitionScore: Int, // 本次联盟总动员积分
    var allianceCompetitionTicket: Int,  // 当前是否拥有联盟总动员门票 0-无 1-有
    var vipLv: Int,         // VIP等级
    var officeMap: HashMap<Long, Int>,  // 官职
    var signTopicList: MutableMap<Long, Int>,        // 收藏的主题
    var inMoveServer: Int  // 迁服状态 0-不在 1-迁服中

) : EntityWrapper<AllianceMemberEntity> {
    constructor() : this(
        0, 0, 0, mutableMapOf(), 0, "",
        0, "", 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0,
        0, 0, hashMapOf(), mutableMapOf(), 0
    )

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(30L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): AllianceMemberEntity {
        return AllianceMemberEntity(
            id,
            allianceId,
            allianceAt,
            toJson(alliancePosMap),
            allianceRnum,
            allianceNickName,
            canHelpNum,
            name,
            photoProtoId,
            onlineState,
            lastLeaveTime,
            memPower,
            playerCastleLv,
            mapPltAreaId,
            mapAreaNo,
            monsterScore,
            lastGetMonsterScore,
            allianceCompetitionScore,
            allianceCompetitionTicket,
            vipLv,
            toJson(officeMap),
            toJson(signTopicList),
            inMoveServer
        )
    }

    override fun wrap(entity: AllianceMemberEntity) {
        id = entity.id
        allianceId = entity.allianceId
        allianceAt = entity.allianceAt
        alliancePosMap = toObj(entity.alliancePos)
        allianceRnum = entity.allianceRnum
        allianceNickName = entity.allianceNickName
        canHelpNum = entity.canHelpNum
        name = entity.name
        photoProtoId = entity.photoProtoId
        onlineState = entity.onlineState
        lastLeaveTime = entity.lastLeaveTime
        memPower = entity.memPower
        playerCastleLv = entity.playerCastleLv
        mapPltAreaId = entity.mapPltAreaId
        mapAreaNo = entity.mapAreaNo
        monsterScore = entity.monsterScore
        lastGetMonsterScore = entity.lastGetMonsterScore
        allianceCompetitionScore = entity.allianceCompetitionScore
        allianceCompetitionTicket = entity.allianceCompetitionTicket
        vipLv = entity.vipLv
        officeMap = toObj(entity.officeInfo)
        signTopicList = toObj(entity.signTopic)
        inMoveServer = entity.inMoveServer
    }

    // 初始化帮派初始职位
    fun resetWrapPosition() {
        this.alliancePosMap = mutableMapOf()
        val alliancePosInfo = AlliancePosInfo(getNowTime())
        this.alliancePosMap[com.point18.slg2d.common.constg.ALLIANCE_POSITION_MEMBER] = alliancePosInfo
    }

    // 新增帮派职位
    fun setWrapPosition(pos: Int) {
        val alliancePosInfo = AlliancePosInfo(getNowTime())
        this.alliancePosMap[pos] = alliancePosInfo
    }

    // 减少帮派职位
    fun delWrapPosition(pos: Int) {
        val ex = this.alliancePosMap[pos] ?: error("要移除的帮派职位玩家没有:$pos")
        this.alliancePosMap.remove(pos)
    }
}

data class AlliancePosInfo(
    var getPosTime: Long //获得职位的时间
)

class CacheAllianceMember(publicCache: PublicCache, val db: PublicDatabase) : BaseCache(publicCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.public.context.system(), javaClass)

    val allianceMemberMap = OneKeyIndex { it: AllianceMember -> it.id }                  // 玩家缓存

    val allianceMemberMapByAllianceId = TwoKeyIndex({ it: AllianceMember -> it.allianceId },
        { it: AllianceMember -> it.id }) // 联盟中的所有成员[联盟ID:玩家ID]

    override fun init() {
    }

    override fun doCacheLoad(publicInitData: PublicInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            publicInitData.allianceMembers =
                    session.getNamedQuery(ALLIANCE_MEMBER_NAMED_QUERY)
                        .setLong("allianceId", publicInitData.publicId)
                        .listNoDup()
        }
    }

    override fun doInitData(publicInitData: PublicInitData) {
        publicInitData.allianceMembers.forEach { entity ->
            try {
                val b = db.wdb.recover(entity) {
                    AllianceMember()

                }

                this.cacheSinglePlayer(b)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
    }

    fun cacheSinglePlayer(player: AllianceMember) {
        this.allianceMemberMap.insertByKey(player)
        this.allianceMemberMapByAllianceId.insertByKey(player)
    }

    fun deleteAllianceMember(allianceMember: AllianceMember) {
        delete(publicCache, allianceMember)
        allianceMemberMap.deleteByKey(allianceMember)
        allianceMemberMapByAllianceId.deleteByKey(allianceMember)

        val alce = publicCache.allianceCache.findAllianceById(allianceMember.allianceId)
        if (alce != null) {
            alce.allianceMemberNum -= 1
            if (alce.allianceMemberNum < 1) {
                alce.allianceMemberNum = 1
            }
        }
    }

    fun createAllianceMember(
        allianceId: Long, playerId: Long, name: String, power: Long,
        lastLeaveTime: Long, honor: Int, canHelpNum: Int, allianceRnum: Int, gamePltAreaId: Long, mapPltAreaId: Long,
        photoProtoId: Int, isOnline: Int, mapAreaNo: Int, playerCastleLv: Int
    ): AllianceMember {
        val allianceMember = AllianceMember(
            playerId,
            allianceId,
            getNowTime(),
            mutableMapOf(),
            allianceRnum,
            "",
            canHelpNum,
            name,
            photoProtoId,
            isOnline,
            lastLeaveTime,
            power,
            playerCastleLv,
            mapPltAreaId,
            mapAreaNo,
            0,
            getNowTime(),
            0,
            0,
            0,
            hashMapOf(),
            mutableMapOf(),
            0
        )
        insert(publicCache, allianceMember)

        // 存入缓存

        allianceMemberMap.insertByKey(allianceMember)
        allianceMemberMapByAllianceId.insertByKey(allianceMember)

        val alce = publicCache.allianceCache.findAllianceById(allianceId)
        if (alce != null) {
            alce.allianceMemberNum += 1
        }

        return allianceMember
    }

    // 根据ID获取玩家
    fun findAllianceMemberById(id: Long): (AllianceMember?) {
        if (id == 0L) {
            return null
        }
        // 尝试从缓存中获取
        return allianceMemberMap.findByKey(id)
    }

    // 根据联盟ID获取联盟中所有玩家信息
    fun findAllianceMembersByAllianceId(aid: Long): (LinkedList<AllianceMember>) {
        val members = LinkedList<AllianceMember>()
        allianceMemberMapByAllianceId.findByOneKeyFilter(aid) {
            members.add(it)
        }
        return members
    }

    // 根据联盟ID获取联盟中所有玩家信息(map格式)
    fun findAllianceMembersMapByAllianceId(aid: Long): (MutableMap<Long, AllianceMember>) {
        val members = mutableMapOf<Long, AllianceMember>()
        allianceMemberMapByAllianceId.findByOneKeyFilter(aid) {
            members[it.id] = it
            true
        }

        return members
    }

    // 查找联盟势力值
    fun findAlliancesAllPower(allianceId: Long): Int {
        var allPower = 0
        val mems = findAllianceMembersByAllianceId(allianceId)
        for (m in mems) {
            allPower += m.memPower.toInt()
        }

        return allPower
    }
}

// 判断玩家是否有这个职位
fun playerIsHavePos(player: AllianceMember, pos: Int): (Boolean) {
    val ex = player.alliancePosMap[pos]
    return ex != null
}



