package com.point18.slg2d.common.homeentities

import com.point18.slg2d.common.persistence.PlayerAsset
import org.hibernate.annotations.Index
import xyz.ariane.util.annotation.NoArgConstructor
import java.io.Serializable
import java.util.*
import javax.persistence.*

@NoArgConstructor
data class MailPK(
    @Id
    @Column(name = "player_id")
    override var playerId: Long,

    @Id
    @Column(name = "id", nullable = false)
    var id: Long
) : PlayerAsset {
    override fun primaryKey() = this
}

const val MAIL_NAMED_QUERY_PLAYER = "findMailByPlayerId"

@NamedQueries(
    NamedQuery(
        name = MAIL_NAMED_QUERY_PLAYER,
        // language=HQL
        query = "from MailEntity where playerId = :playerId"
    )
)
@Entity
@Table(name = "mails")
@IdClass(MailPK::class)
data class MailEntity(

    @Id
    @Column(name = "id", nullable = false)
    var id: Long,

    @Column(name = "send_player_id", nullable = false)
    var sendPlayerId: Long,  // 发送玩家Id

    @Column(name = "send_player_name", nullable = false, length = 50)
    var sendPlayerName: String,

    @Column(name = "send_player_nick_name", nullable = false, length = 50)
    var sendPlayerNickName: String,

    @Column(name = "send_alliance_id", nullable = false)
    var sendAllianceId: Long,    // 发送人所属联盟

    @Column(name = "send_alliance_short_name", nullable = false, length = 50)
    var sendAllianceShortName: String,  //发送人所属联盟简称

    @Column(name = "type", nullable = false)
    var type: Int,               // 邮件类型

    @Column(name = "is_read", nullable = false)
    var isRead: Int,               // 是否阅读

    @Column(name = "attach", nullable = false)
    var attach: String,       // 邮件附件

    @Column(name = "is_draw", nullable = false)
    var isDraw: Int,               // 是否领取附件

    @Column(name = "send_time", nullable = false)
    var sendTime: Long,  // 邮件发送时间

    @Column(name = "player_id", nullable = false)
    @Index(name = "MAILS_PLAYER_ID")
    override var playerId: Long,    // 接受者ID

    @Column(name = "gm_email_id", nullable = false)
    var gMEmailId: Long,    // 用来记录后台发送奖励时的Id

    @Column(name = "mail_info_string", nullable = false, length = 1000)
    var mailInfoString: String,          // 邮件内容(标题 + 内容)

    @Column(name = "extend1", nullable = false, length = 200)
    var extend1: String,       // 扩展字段

    @Column(name = "is_sign", nullable = false)
    var isSign: Int               // 是否标记  0-否 1-是
) : PlayerAsset {
    constructor() : this(
        0, 0, "", "", 0, "", 0, 0, "", 0, 0, 0, 0, "",
        "", 0
    )

    override fun primaryKey() = MailPK(playerId, id)
}

class MailInfo(
    val readType: Int,     //邮件结构体的读取类型 1-读lan (一般都是游戏内发的) 2-直接读取内容(一般都是后台发的)
    val title: String, //邮件标题lanId
    val titleParam: LinkedList<String>, //邮件标题中参数
    val message: String, //邮件内容lanId
    val messageParam: LinkedList<String> //邮件内容中参数
) : Serializable {
    constructor() : this(0, "", LinkedList<String>(), "", LinkedList<String>())
}