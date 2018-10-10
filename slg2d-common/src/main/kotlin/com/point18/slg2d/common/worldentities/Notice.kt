package com.point18.slg2d.common.worldentities

import com.point18.slg2d.common.persistence.WorldAsset
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

const val NOTICE_NAMED_QUERY = "findNoticeByWorldId"

@NamedQueries(
    NamedQuery(
        name = NOTICE_NAMED_QUERY,
        // language=HQL
        query = "from NoticeEntity where worldId = :worldId"
    )
)
@Entity
@Table(name = "notices")
data class NoticeEntity(
    @Id
    @Column(name = "world_id")
    override var worldId: Long,

    @Id
    @GeneratedValue(generator = "areaObjIdGen")
    @GenericGenerator(name = "areaObjIdGen", strategy = "assigned")
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "notice_next_time", nullable = false)
    var noticeNextTime: Long,  // 下次公告时间

    @Column(name = "content", nullable = false, length = 800)
    var content: String,       // 内容

    @Column(name = "notice_time_start", nullable = false)
    var noticeTimeStart: Long,  // 公告时间(开始)

    @Column(name = "notice_time_end", nullable = false)
    var noticeTimeEnd: Long,  // 公告时间(结束)

    @Column(name = "frequency", nullable = false)
    var frequency: Int,                // 频率（秒）

    @Column(name = "notice_type", nullable = false)
    var noticeType: Int,                // 公告类型

    @Column(name = "notice_position", nullable = false)
    var noticePosition: Int,                // 公告位置

    @Column(name = "notice_tid", nullable = false)
    var noticeTid: Int                // 游族公告id
) : WorldAsset {
    constructor() : this(
        0,
        0, 0, "", 0, 0, 0, 0, 0, 0
    )

    override fun primaryKey() = id
}