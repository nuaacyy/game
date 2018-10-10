package com.point18.slg2d.home.dc

import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.homeentities.HOME_SYNC_NAMED_QUERY
import com.point18.slg2d.common.homeentities.HomeSyncEntity
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.syncdomain.BarrackData
import com.point18.slg2d.common.syncdomain.CountryBuffData
import com.point18.slg2d.common.worldentities.NoticeSwitchVo
import com.point18.slg2d.home.AbstractDataContainer
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

// 世界服同步到HOME服的数据
open class HomeSyncDC : AbstractDataContainer<HomeSyncEntity>() {

    open lateinit var syncData: HomeSync
        protected set

    override fun loadAllFromDB(playerId: Long, dao: CommonDao): HomeSyncEntity? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(HOME_SYNC_NAMED_QUERY)
                .setLong("playerId", playerId)
                .listNoDup<HomeSyncEntity>()
            list.firstOrNull()
        }
        return data
    }

    override fun initImpl(data: HomeSyncEntity?, depDCRepo: DataContainerRepo) {
        if (data != null) {
            val syncWrap = wdb.recover(data) { HomeSync() }
            syncData = syncWrap
        } else {
            val sync = HomeSync(
                playerId,
                0,
                hashMapOf(),
                hashMapOf(),
                LinkedList(),
                LinkedList(),
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                hashMapOf(),
                pcs.basicProtoCache.firstInstance,
                hashMapOf(),
                -1,
                -1,
                -1,
                hashMapOf()
            )

            wdb.save(sync)

            this.syncData = sync
        }
    }
}

class HomeSync(
    var playerId: Long,                                  // 玩家Id
    var maxLvInPrison: Int,
    var barracksMap: HashMap<Int, BarrackData>,
    var effectMap: HashMap<Int, HashMap<Int, Int>>,     //在world服的效果
    var countryBuffList: LinkedList<CountryBuffData>,   //国家buff效果
    var buffs: LinkedList<Int>,   // 玩家拥有的buff模版IDS
    var maxJjcRank: Int,
    var jjcRank: Int,
    var scoreTime: Long,
    var score: Int,
    var jjcCoinReward: Long,
    var jjcGoldReward: Long,
    var castleState: Int,
    var castleStatusEndTime: Long,
    var officeMap: HashMap<Long, Int>,  //<worldId, 官职Id>
    var instanceFloor: Int, // 当前的推图关卡
    var targetMap: HashMap<Int, Long>,
    var refuseDisturbOpen: Int, // 勿扰开始时间
    var refuseDisturbEnd: Int, // 勿扰结束时间
    var cautionLv: Int, //警戒等级
    var switches: HashMap<Int, NoticeSwitchVo> //通知开关
) : EntityWrapper<HomeSyncEntity> {

    constructor() : this(
        0, 0, hashMapOf(), hashMapOf(), LinkedList(), LinkedList(),
        0, 0, 0, 0,
        0, 0, 0, 0L, hashMapOf(), 0, hashMapOf(),
        -1, -1, -1, hashMapOf()
    )

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = playerId

    override fun toEntity(): HomeSyncEntity {
        return HomeSyncEntity(
            playerId,
            maxLvInPrison,
            toJson(barracksMap),
            toJson(effectMap),
            toJson(countryBuffList),
            toJson(buffs),
            maxJjcRank,
            jjcRank,
            scoreTime,
            score,
            jjcCoinReward,
            jjcGoldReward,
            castleState,
            castleStatusEndTime,
            toJson(officeMap),
            instanceFloor,
            toJson(targetMap)
        )
    }

    override fun wrap(entity: HomeSyncEntity) {
        playerId = entity.playerId
        maxLvInPrison = entity.maxLvInPrison
        barracksMap = toObj(entity.allBarracks)
        effectMap = toObj(entity.allEffects)
        countryBuffList = toObj(entity.allCountryBuffs)
        buffs = toObj(entity.buffs)
        maxJjcRank = entity.maxJjcRank
        jjcRank = entity.jjcRank
        scoreTime = entity.scoreTime
        score = entity.score
        jjcCoinReward = entity.jjcCoinReward
        jjcGoldReward = entity.jjcGoldReward
        castleState = entity.castleState
        castleStatusEndTime = entity.castleStatusEndTime
        officeMap = toObj(entity.officeInfo)
        instanceFloor = entity.instanceFloor
        targetMap = toObj(entity.targets)
    }
}