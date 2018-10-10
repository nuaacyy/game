package com.point18.slg2d.home.dc

import com.point18.slg2d.common.homeentities.MAIL_NAMED_QUERY_PLAYER
import com.point18.slg2d.common.homeentities.MailEntity
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.common.homeentities.MailPK
import com.point18.slg2d.home.AbstractDataContainer
import com.point18.slg2d.home.hpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.commonfunc.DescComparator
import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.constg.*
import java.io.Serializable
import java.time.Duration
import java.util.*

class MailDC : AbstractDataContainer<List<MailEntity>>() {
    // 所有邮件缓存
    private val mailMap = hashMapOf<Int, TreeMap<Long, Mail>>()
    private val newNumMap = hashMapOf<Int, HashSet<Long>>()
    val mailQueue = PriorityQueue<Mail> { c1, c2 ->
        when {
            c1.sendTime > c2.sendTime -> 1
            c1.sendTime < c2.sendTime -> -1
            else -> 0
        }
    }

    override fun initImpl(data: List<MailEntity>?, depDCRepo: DataContainerRepo) {
        data?.map {
            val mailWrap = wdb.recover(it) { Mail() }

            add2Cache(mailWrap)
        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): List<MailEntity>? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(MAIL_NAMED_QUERY_PLAYER)
                .setLong("playerId", playerId)
                .listNoDup<MailEntity>()
            list
        }
        return data
    }

    fun add2Cache(v: Mail) {
        val typeMap = this.mailMap.getOrPut(v.getMailType()) { TreeMap(DescComparator<Long>()) }
        typeMap[v.id] = v

        mailQueue += v

        checkMail(v)
    }

    fun delFromCache(mail: Mail) {
        val mailType = mail.getMailType()
        mailMap[mailType]?.remove(mail.id)

        mailQueue.remove(mail)

        val newMap = newNumMap.getOrPut(mailType) { hashSetOf() }
        newMap.remove(mail.id)
    }

    fun findById(id: Long): Mail? {
        for ((_, typeMap) in mailMap) {
            val mail = typeMap[id]
            if (mail != null) {
                return mail
            }
        }
        return null
    }

    // 游戏和后台可能会向不在线的玩家发送邮件，所以参数不能传PlayerSession
    fun createMail(
        playerId: Long,
        sendPlayerId: Long,
        sendPlayerName: String,
        sendPlayerNickName: String,
        sendAllianceId: Long,
        sendAllianceShortName: String,
        mailType: Int,
        isRead: Int,
        attach: String,
        isDraw: Int,
        sendTime: Long,
        sysId: Long,
        mailInfoParam: MailInfo,
        extend1: String
    ): Mail {
        val id = hpm.generateObjIdNew()
        val mail = Mail(
            id,
            sendPlayerId,
            sendPlayerName,
            sendPlayerNickName,
            sendAllianceId,
            sendAllianceShortName,
            mailType,
            isRead,
            attach,
            isDraw,
            sendTime,
            playerId,
            sysId,
            extend1,
            0
        )

        mail.mailInfo = mailInfoParam

        wdb.save(mail)
        add2Cache(mail)

        return mail
    }

    // 删除一个
    fun deleteMail(mail: Mail) {
        if (mail.id == 0L) {
            return
        }

        wdb.delete(mail)

        delFromCache(mail)
    }

    /**
     *  新邮件数量
     */
    fun newMailNum(): LinkedList<Int> {
        val newMailList = LinkedList<Int>()
        newMailList += newNumMap.getOrPut(SYS_MAIL) { hashSetOf() }.count()
        newMailList += newNumMap.getOrPut(ALLIANCE_MAIL) { hashSetOf() }.count()
        newMailList += newNumMap.getOrPut(SIGN_MAIL) { hashSetOf() }.count()
        return newMailList
    }

    /**
     * 检测邮件是否是新的
     */
    fun checkMail(mail: Mail) {
        val mailType = mail.getMailType()
        val newMap = newNumMap.getOrPut(mailType) { hashSetOf() }
        if (!intToBool(mail.isRead) || !intToBool(mail.isDraw)) {
            //未读或者未领取
            newMap += mail.id
            return
        }
        newMap.remove(mail.id)
    }

    /**
     *  分页获取邮件
     */
    fun findMailsByPage(mailType: Int, page: Int, num: Int): LinkedList<Mail>? {
        if (page <= 0 || num <= 0) {
            return null
        }
        val mailList = LinkedList<Mail>()
        val typeMap = this.mailMap[mailType] ?: return mailList
        val startIndex = (page - 1) * num
        val iterator = typeMap.iterator()
        var index = 0
        while (iterator.hasNext()) {
            val valueEntry = iterator.next()
            if (startIndex > index++) {
                continue
            }

            mailList += valueEntry.value
            if (mailList.count() >= num) {
                break
            }
        }
        return mailList
    }

    /**
     * 根据类型查找邮件
     */
    fun findMailsByType(mailType: Int, filter: (Mail) -> Boolean): LinkedList<Mail> {
        val mailList = LinkedList<Mail>()
        val typeMap = mailMap[mailType]
        if (typeMap == null) {
            return mailList
        }
        for ((_, mail) in typeMap) {
            if (!filter(mail)) {
                continue
            }
            mailList += mail
        }
        return mailList
    }
}

class Mail(
    var id: Long,

    var sendPlayerId: Long,  // 发送玩家Id
    var sendPlayerName: String,
    var sendPlayerNickName: String,
    var sendAllianceId: Long,    // 发送人所属联盟
    var sendAllianceShortName: String,//发送人所属联盟简称
    var type: Int,               // 邮件类型
    var isRead: Int,               // 是否阅读
    var attach: String,       // 邮件附件
    var isDraw: Int,               // 是否领取附件
    var sendTime: Long,  // 邮件发送时间
    var playerId: Long,    // 接受者ID
    var gMEmailId: Long,    // 用来记录后台发送奖励时的Id
    var extend1: String,       // 扩展字段
    var isSign: Int               // 是否收藏
) : EntityWrapper<MailEntity> {

    var mailInfo = MailInfo() // 邮件内容

    constructor() : this(
        0, 0, "", "", 0, "", 0, 0, "", 0, 0, 0, 0,
        "", 0
    )

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = MailPK(playerId, id)

    override fun toEntity(): MailEntity {
        return MailEntity(
            id,
            sendPlayerId,
            sendPlayerName,
            sendPlayerNickName,
            sendAllianceId,
            sendAllianceShortName,
            type,
            isRead,
            attach,
            isDraw,
            sendTime,
            playerId,
            gMEmailId,
            toJson(mailInfo),
            extend1,
            isSign
        )
    }

    override fun wrap(entity: MailEntity) {
        id = entity.id
        sendPlayerId = entity.sendPlayerId
        sendPlayerName = entity.sendPlayerName
        sendPlayerNickName = entity.sendPlayerNickName
        sendAllianceId = entity.sendAllianceId
        sendAllianceShortName = entity.sendAllianceShortName
        type = entity.type
        isRead = entity.isRead
        attach = entity.attach
        isDraw = entity.isDraw
        sendTime = entity.sendTime
        playerId = entity.playerId
        gMEmailId = entity.gMEmailId
        extend1 = entity.extend1
        isSign = entity.isSign
        mailInfo = toObj(entity.mailInfoString)
    }

    /**
     *  获取最终的邮件类型
     */
    fun getMailType(): Int {
        if (intToBool(this.isSign)) {
            //收藏邮件
            return SIGN_MAIL
        }
        if (this.type == ALLIANCE_INVITE) {
            //联盟邀请，替换成联盟邮件
            return ALLIANCE_MAIL
        }
        return this.type
    }
}
