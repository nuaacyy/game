package com.point18.slg2d.public.datacache

import akka.event.Logging
import akka.event.LoggingAdapter
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.commonfunc.zeroTime
import com.point18.slg2d.common.constg.ALLIANCE_NAME_IN_USE
import com.point18.slg2d.common.mcache.OneKeyIndex
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.publicentities.*
import com.point18.slg2d.public.PublicDatabase
import com.point18.slg2d.public.PublicMenagerDatabase
import xyz.ariane.util.CommonDao
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*
import kotlin.collections.HashMap

class Alliance(
    var id: Long,

    var name: String, // 名称
    var shortName: String, // 简称

    var mainPlayerId: Long, // 盟主
    var description: String, // 联盟公告
    var slogan: String, // 联盟标语
    var nextMarkTime: Long, // 下次可发布联盟标记时间
    var powerLimit: Long, // 允许加入联盟的势力
    var canAddPower: Long, // 可以直接加入联盟的势力  < 0 表示未开启这个功能
    var makeOverTime: Long, // 执行盟主转让时间
    var makeOverPid: Long, // 接收转让的玩家ID
    var flagColor: Int, // 联盟旗帜的颜色
    var flagStyle: Int, // 联盟旗帜的样式
    var flagEffect: Int, // 联盟旗帜图案
    var allianceLan: Int, // 语种

    var createAt: Long, //联盟创建时间
    var allianceHelpNumAdd: Int, // 联盟帮助的上限次数增加

    // 联盟BOSS活动情况
    var allianceBossScore: Int, //	联盟BOSS当前积分进度
    var allianceBossInfoMap: MutableMap<Int, LinkedList<AllianceBossVo>>,

    // 联盟总动员字段
    var allianceRankLv: Int,                                    // 联盟所处段位
    var allianceCompetitionScore: Int,                          // 联盟总动员积分
    var allianceCompetitionScoreChangeTime: Long,               // 联盟总动员积分变化时间
    var allianceCompetitionTicket: Int,                         // 当前是否拥有联盟总动员门票 0-无 1-有
    var wonderAwardMap: MutableMap<Int, LinkedList<Long>>,      // 赏赐礼包Id-[]玩家Id
    var joinActivity: LinkedList<AllianceJoinActivity>,         // 联盟参与过的活动

    var alliancePower: Long, // 联盟总战斗力
    var allianceMemberNum: Int, // 联盟人数,
    var allianceAreaNo: Int, // 联盟所在服务器 (就是盟主所在)
    var allianceOccupyInfo: HashMap<Long, HashMap<Int, Int>>,  // 联盟占领世界情况  <世界Id, <奇观模板Id, 1>>
    var allianceId: Long

) : EntityWrapper<AllianceEntity> {
    constructor() : this(
        0, "", "", 0, "", "",
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0,
        mutableMapOf(), 0, 0, 0, 0,
        mutableMapOf(), LinkedList(), 0L, 0, 0, HashMap(), 0
    )

    // todo 临时改短改表的落地时间
    override fun getCheckModInterval(): Duration = Duration.ofSeconds(3L)

    override fun getPrimaryKey(): Serializable = id

    override fun toEntity(): AllianceEntity {
        return AllianceEntity(
            id,
            allianceId,
            name,
            shortName,
            mainPlayerId,
            description,
            slogan,
            nextMarkTime,
            powerLimit,
            canAddPower,
            makeOverTime,
            makeOverPid,
            flagColor,
            flagStyle,
            flagEffect,
            allianceLan,
            createAt,
            allianceHelpNumAdd,
            allianceBossScore,
            toJson(allianceBossInfoMap),
            allianceRankLv,
            allianceCompetitionScore,
            allianceCompetitionScoreChangeTime,
            allianceCompetitionTicket,
            toJson(wonderAwardMap),
            toJson(joinActivity),
            alliancePower,
            allianceMemberNum,
            allianceAreaNo,
            toJson(allianceOccupyInfo)
        )
    }

    override fun wrap(entity: AllianceEntity) {
        id = entity.id
        allianceId = entity.allianceId
        name = entity.name
        shortName = entity.shortName
        mainPlayerId = entity.mainPlayerId
        description = entity.description
        slogan = entity.slogan
        nextMarkTime = entity.nextMarkTime
        powerLimit = entity.powerLimit
        canAddPower = entity.canAddPower
        makeOverTime = entity.makeOverTime
        makeOverPid = entity.makeOverPid
        flagColor = entity.flagColor
        flagStyle = entity.flagStyle
        flagEffect = entity.flagEffect
        allianceLan = entity.allianceLan
        createAt = entity.createAt
        allianceHelpNumAdd = entity.allianceHelpNumAdd
        allianceBossScore = entity.allianceBossScore
        allianceBossInfoMap = toObj(entity.allianceBossInfo)
        allianceRankLv = entity.allianceRankLv
        allianceCompetitionScore = entity.allianceCompetitionScore
        allianceCompetitionScoreChangeTime = entity.allianceCompetitionScoreChangeTime
        allianceCompetitionTicket = entity.allianceCompetitionTicket
        wonderAwardMap = toObj(entity.wonderAwardInfo)
        joinActivity = toObj(entity.joinActivity)
        alliancePower = entity.alliancePower
        allianceMemberNum = entity.allianceMemberNum
        allianceAreaNo = entity.allianceAreaNo
        allianceOccupyInfo = toObj(entity.allianceOccupyInfo)
    }
}

data class AllianceJoinActivity(
    var activityProtoId: Int, // 参与过的活动模版ID
    var overTime: Long,  // 活动结束时间
    var myScore: Long, // 我的积分
    var myRank: Int, // 我的排名
    var rankId: Long // 对应的排行榜记录行ID
)

data class AllianceBossVo(
    var bossId: Int,
    var state: Int // 0 - 还没召唤过 1-已召唤
)

class CacheAllianceManager(val db: PublicMenagerDatabase) {
    val logger: LoggingAdapter = Logging.getLogger(db.aPublic.context.system(), javaClass)

    fun doCacheLoad(publicInitData: PublicInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            publicInitData.alliances =
                session.getNamedQuery(All_ALLIANCE_NAMED_QUERY)
                    .listNoDup()

            publicInitData.allianceMembers =
                session.getNamedQuery(ALLIANCE_ALL_MEMBER_NAMED_QUERY)
                    .listNoDup()

            publicInitData.allianceMemberTraces =
                session.getNamedQuery(ALLIANCE_ALL_MEMBER_TRACE_NAMED_QUERY)
                    .listNoDup()
        }
    }

    fun doInitDataForMenager(publicInitData: PublicInitData) {
        // 准备联盟简单数据
        publicInitData.allianceMemberTraces.forEach { entity ->
            try {
                val b = db.wdb.recover(entity) {
                    AllianceMemberTrace()
                }
                allianceMemberTraceByAllianceId.getOrPut(b.allianceId) { mutableListOf() }.add(b)
            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }

        publicInitData.allianceMembers.forEach { entity ->
            try {
                val b = db.wdb.recover(entity) {
                    AllianceMember()
                }
                allianceMemberByAllianceId.getOrPut(b.allianceId) { mutableListOf() }.add(b)
            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }


        publicInitData.alliances.forEach { entity ->
            try {
                val b = db.wdb.recover(entity) {
                    Alliance()

                }
                nowUseNameMap[b.name] = NameUseVo(ALLIANCE_NAME_IN_USE, 0)
                nowUseShortNameMap[b.shortName] = NameUseVo(ALLIANCE_NAME_IN_USE, 0)

                var power = 0L
                var killSolider = 0L
                var monsterScore = 0L


                val ams = allianceMemberByAllianceId[b.allianceId]
                if (ams != null) {
                    for (v in ams) {
                        power += v.memPower
                    }
                }


                val amts = allianceMemberTraceByAllianceId[b.allianceId]
                if (amts != null) {
                    for (v in amts) {
                        killSolider += v.killSolider
                        monsterScore += v.monsterScore
                    }
                }

                var allianceMainMemberName = ""
                val members = allianceMemberByAllianceId[b.allianceId]
                if (members != null) {
                    for (m in members) {
                        if (m.id == b.mainPlayerId) {
                            allianceMainMemberName = m.name
                            break
                        }
                    }
                }
                alianceSimpleInfos[b.allianceId] =
                    AllianceSimpleInfoVo(
                        b.name,
                        b.shortName,
                        b.allianceId,
                        b.flagColor,
                        b.flagStyle,
                        b.flagEffect,
                        power,
                        killSolider,
                        b.allianceCompetitionScore.toLong(),
                        b.allianceCompetitionScoreChangeTime,
                        monsterScore,
                        b.allianceRankLv,
                        b.allianceMemberNum,
                        b.allianceOccupyInfo.keys.toMutableList(),
                        allianceMainMemberName,
                        b.allianceAreaNo
                    )

                allianceOccupyInfos[b.allianceId] = AllianceOccupyInfo(
                    allianceMainMemberName,
                    b.allianceAreaNo,
                    b.name,
                    b.shortName,
                    b.allianceOccupyInfo.keys.toMutableList()
                )

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }

        nowSycnNameVersion = 1
    }

    // 联盟管理节点使用的缓存
    val nowUseNameMap = mutableMapOf<String, NameUseVo>()
    val nowUseShortNameMap = mutableMapOf<String, NameUseVo>()
    var nowSycnNameVersion = 0 // 当前缓存版本

    data class NameUseVo(
        var state: Int, // 名字状态 0-正在尝试使用 1-已经被使用
        var overTime: Long // 尝试创建超时时间
    )

    val allianceOccupyInfos = mutableMapOf<Long, AllianceOccupyInfo>()

    data class AllianceOccupyInfo(
        var allianceMainMemberName: String, // 盟主名字
        var allianceAreaNo: Int, // 联盟所属服务器
        var allianceName: String,// 联盟名字
        var allianceShortName: String, // 联盟简称
        var worldIds: MutableList<Long>// 占领的世界ID
    )

    // 用于排行功能
    val alianceSimpleInfos = mutableMapOf<Long, AllianceSimpleInfoVo>() // 以联盟ID为KEY的联盟简要数据信息
    val allianceMemberByAllianceId = mutableMapOf<Long, MutableList<AllianceMember>>() // 以联盟ID为key的联盟成员缓存
    val allianceMemberTraceByAllianceId = mutableMapOf<Long, MutableList<AllianceMemberTrace>>() // 以联盟ID为key的联盟成员哼唧缓存

    data class AllianceSimpleInfoVo(
        var allianceName: String,
        var allianceShortName: String,
        var allianceId: Long,
        var flagColor: Int,
        var flagStyle: Int,
        var flagEffect: Int,
        var power: Long,
        var killSolider: Long,
        var allianceCompetitionScore: Long,
        var allianceCompetitionScoreChangeTime: Long,
        var monsterScore: Long,
        var allianceRankLv: Int,
        var allianceMemberNum: Int,
        var allianceOccupyInfos: MutableList<Long>,
        var allianceMemberName: String,
        var allianceAreaNo: Int
    )
}

class CacheAlliance(publicCache: PublicCache, val db: PublicDatabase) : BaseCache(publicCache) {

    val logger: LoggingAdapter = Logging.getLogger(db.public.context.system(), javaClass)

    val allianceMap = OneKeyIndex<Long, Alliance> { it.id }             // 联盟ID为索引的联盟缓存
    val allianceNameMap = mutableMapOf<String, Alliance>()       // 联盟名称为索引的联盟缓存
    val allianceShortNameMap = mutableMapOf<String, Alliance>() // 联盟简称为索引的联盟缓存

    override fun init() {
    }

    override fun doCacheLoad(publicInitData: PublicInitData, dao: CommonDao) {
        dao.execWithTransaction { session ->
            publicInitData.alliances =
                session.getNamedQuery(ALLIANCE_NAMED_QUERY)
                    .setLong("allianceId", publicInitData.publicId)
                    .listNoDup()
        }
    }

    override fun doInitData(publicInitData: PublicInitData) {
        publicInitData.alliances.forEach { entity ->
            try {
                val b = db.wdb.recover(entity) {
                    Alliance()

                }

                this.saveAlliance2Cache(b)

            } catch (e: Throwable) {
                logger.error(e, "Recover error: {}", entity)
            }
        }
    }

    override fun doCacheLink() {
    }

    fun saveAlliance2Cache(alce: Alliance) {

        this.allianceMap.insertByKey(alce)

        this.allianceNameMap[alce.name] = alce

        this.allianceShortNameMap[alce.shortName] = alce
    }

    // 创建联盟
    fun createAlliance(
        allianceId: Long,
        name: String,
        shortName: String,
        pid: Long,
        allianceLan: Int,
        power: Long,
        areaNo: Int
    ): Alliance {
        val alce = Alliance(
            allianceId,
            name,
            shortName,
            pid,
            "",
            "",
            zeroTime.time,
            0,
            -1,
            zeroTime.time,
            0,
            pcs.basicProtoCache.allianceDefaultFlag[0],
            pcs.basicProtoCache.allianceDefaultFlag[1],
            pcs.basicProtoCache.allianceDefaultFlag[2],
            allianceLan,
            0,
            0,
            0,
            mutableMapOf(),
            pcs.basicProtoCache.allianceCompetitionFirstLevel,
            0,
            zeroTime.time,
            0,
            mutableMapOf(),
            LinkedList(),
            power,
            0,
            areaNo,
            HashMap(),
            allianceId
        )

        insert(publicCache, alce)

        // 存入联盟缓存
        saveAlliance2Cache(alce)

        return alce
    }

    // 删除联盟
    fun deleteAlliance(alce: Alliance?) {
        if (alce == null || alce.id == 0L) {
            return
        }

        delete(publicCache, alce)

        // 从缓存中删除
        assert(allianceMap.findByKey(alce.id) != null)

        allianceMap.deleteByKey(alce)

        allianceNameMap.remove(alce.name)

        // 联盟简称的MAP转换成大写来遍历
        allianceShortNameMap.remove(alce.shortName)
    }

    // 根据联盟名称查找联盟信息
    fun findAllianceById(aid: Long): Alliance? {
        if (aid == 0L) {
            return null
        }
        return allianceMap.findByKey(aid)
    }

    // 查找所有的联盟
    fun findAlliances(): LinkedList<Alliance> {
        val alliances = LinkedList<Alliance>()
        allianceMap.map {
            alliances.add(it)
        }
        return alliances
    }

    // 修改联盟名
    fun updateAllianceName(oldName: String, name: String) {
        val vo = allianceNameMap[oldName]
        if (vo != null) {
            allianceNameMap.remove(oldName)
            vo.name = name
            allianceNameMap[name] = vo
        }
    }

    // 修改联盟简称
    fun updateAllianceShortName(oldName: String, name: String) {
        val vo = allianceShortNameMap[oldName]
        if (vo != null) {
            allianceShortNameMap.remove(oldName)
            vo.shortName = name
            allianceShortNameMap[name] = vo
        }
    }

    // 获取玩家的真实可帮助次数
    fun getReallyAllianceCanHelpNum(player: AllianceMember): (Int) {
        var allianceAddNum = 0
        if (player.allianceId != 0L) {
            val allianceInfo = findAllianceById(player.allianceId)
            if (allianceInfo != null) {
                allianceAddNum = allianceInfo.allianceHelpNumAdd
            }
        }
        return player.canHelpNum + allianceAddNum
    }
}