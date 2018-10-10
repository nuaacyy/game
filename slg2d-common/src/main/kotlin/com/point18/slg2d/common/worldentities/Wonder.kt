package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import javax.persistence.*

const val WONDER_NAMED_QUERY = "findWonderByWorldId"

@NamedQueries(
    NamedQuery(
        name = WONDER_NAMED_QUERY,
        // language=HQL
        query = "from WonderEntity where worldId = :worldId"
    )
)

@Entity
@Table(name = "wonder")
data class WonderEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "wonder_proto_id", nullable = false)
    var wonderProtoId: Int,  // 奇观配置Id

    @Column(name = "status", nullable = false)
    var status: Int, // 奇观显示状态 1、和平 2、争夺 3、冒烟 4、冒火

    @Column(name = "status_over_time", nullable = false)
    var statusOverTime: Long, // 奇观冒烟/冒火状态 结束时间

    @Column(name = "war_start_time", nullable = false)
    var warStartTime: Long,  // 开始争夺时间，和平时期指示下次争夺时间

    @Column(name = "occupy_start_time", nullable = false)
    var occupyStartTime: Long,  // 占领开始计算的时间

    @Column(name = "occupy_time", nullable = false)
    var occupyOverTime: Long,  // 被占领时间

    @Column(name = "war_finish_time", nullable = false)
    var warFinishTime: Long,  // 结束争夺时间，跟随部队占领

    @Column(name = "occupy_group_id", nullable = false)
    var occupyGroupId: Long,                                  // 占领的部队Id

    @Column(name = "belong_to_alliance_id", nullable = false)
    var belongToAllianceId: Long,                                  // 归属的联盟Id

    @Column(name = "notice", nullable = false, length = 5000)
    var notice: String,                                    // 公告

    @Column(name = "office_info", nullable = false, length = 1000)
    var officeInfo: String,                                    // 官职信息

    @Column(name = "buff_info", nullable = false, length = 1000)
    var buffInfo: String,                                    // 奇观的buff效果信息

    @Column(name = "buff_cool_time", nullable = false)
    var buffCoolTime: Long,  //Buff冷却结束时间

    @Column(name = "pardon_count", nullable = false)
    var pardonCount: Int,                                              //剩余赦免次数

    @Column(name = "last_notice_time", nullable = false)
    var lastNoticeTime: Long,            //上次发送公告时间

    @Type(type = "text")
    @Column(name = "rank_info", nullable = false)
    var rankInfo: String,                // 奇观占领时长信息

    @Column(name = "award_info", nullable = false, length = 1000)
    var awardInfo: String,                // 赏赐信息

    @Column(name = "wonder_war_status", nullable = false)
    var wonderWarStatus: Int,            //奇观战状态

    @Column(name = "init_time", nullable = false)
    var initTime: Long            //奇观初始化时间

) : WorldAsset {
    constructor() : this(
        0,
        0L, 0, 0, 0, 0, 0, 0,
        0, 0, 0, "", "", "", 0,
        0, 0, "", "", 0, 0
    )

    override fun primaryKey() = id
}