package com.point18.slg2d.home.dc

import com.point18.slg2d.common.homeentities.THUMB_INFO_NAMED_QUERY_PLAYER
import com.point18.slg2d.common.homeentities.ThumbInfoEntity
import com.point18.slg2d.home.AbstractDataContainer
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.constg.PlayerId
import java.io.Serializable
import java.time.Duration

// 点赞信息

class ThumbInfoDC : AbstractDataContainer<ThumbInfoEntity>() {

    lateinit var thumbInfo: ThumbInfo

    override fun initImpl(data: ThumbInfoEntity?, depDCRepo: DataContainerRepo) {
        if (data != null) {
            val thumbInfoWrap = wdb.recover(data) { ThumbInfo() }
            thumbInfo = thumbInfoWrap
        } else {
            val thumbInfo = ThumbInfo(
                hashMapOf(), hashMapOf(), hashMapOf(),
                0, 0, 0, 0,
                playerId
            )

            wdb.save(thumbInfo)
            this.thumbInfo = thumbInfo
        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): ThumbInfoEntity? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(THUMB_INFO_NAMED_QUERY_PLAYER)
                .setLong("playerId", playerId)
                .listNoDup<ThumbInfoEntity>()
            if (list.isEmpty()) {
                null
            } else {
                list[0]
            }
        }
        return data
    }
}

class ThumbInfo(
    var thumbIn: HashMap<Long, Thumb>,
    var thumbOut: HashMap<Long, Thumb>,
    var thumbInAlliance: HashMap<Long, Thumb>,
    var thumbInTotal: Int, // 被赞总数
    var thumbInNum: Int, // 今日被赞数
    var thumbOutNum: Int,  // 今日点赞数
    var checkTime: Long,  // 查看点赞信息时间
    var playerId: Long //玩家Id
) : EntityWrapper<ThumbInfoEntity> {

    constructor() : this(
        hashMapOf(), hashMapOf(), hashMapOf(),
        0, 0, 0, 0, 0
    )

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = playerId

    override fun toEntity(): ThumbInfoEntity {
        return ThumbInfoEntity(
            playerId,
            toJson(thumbIn),
            toJson(thumbOut),
            toJson(thumbInAlliance),
            thumbInTotal,
            thumbInNum,
            thumbOutNum,
            checkTime
        )
    }

    override fun wrap(entity: ThumbInfoEntity) {
        playerId = entity.playerId
        thumbIn = toObj(entity.thumbIn)
        thumbOut = toObj(entity.thumbOut)
        thumbInAlliance = toObj(entity.thumbInAlliance)
        thumbInTotal = entity.thumbInTotal
        thumbInNum = entity.thumbInNum
        thumbOutNum = entity.thumbOutNum
        checkTime = entity.checkTime
    }
}

class Thumb(
    var id: Long, // 玩家的Id
    var name: String, // 玩家名
    var lv: Int, // 玩家君主等级
    var intro: String, // 自我介绍
    var thumbTime: Long // 点赞时间
) {
    constructor() : this(
        0, "", 0, "", 0
    )
}



