package com.point18.slg2d.home.dc

import com.google.protobuf.ByteString
import com.point18.slg2d.common.homeentities.*
import com.point18.slg2d.home.AbstractDataContainer
import com.point18.slg2d.home.hpm
import com.point18.slg2d.common.mcache.OneKeyIndex
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.commonfunc.zeroTime
import com.point18.slg2d.common.constg.JJC_FIGHT_REPORT
import com.point18.slg2d.common.constg.PlayerId
import com.point18.slg2d.common.constg.UnRead
import java.io.Serializable
import java.time.Duration
import java.util.*

class BattleReportDC : AbstractDataContainer<List<BattleReportEntity>>() {

    val battleReportMap =
        OneKeyIndex { it: BattleReport -> it.id } // 按玩家ID分类的简报缓存

    val battleReportQueue = hashMapOf<Int, PriorityQueue<BattleReport>>()

    override fun initImpl(data: List<BattleReportEntity>?, depDCRepo: DataContainerRepo) {
        data?.map {
            val report = wdb.recover(it) { BattleReport() }

            battleReportMap.insertByKey(report)
            battleReportQueue.getOrPut(report.reportType) {
                PriorityQueue { c1, c2 ->
                    when {
                        c1.pastTime > c2.pastTime -> 1
                        c1.pastTime < c2.pastTime -> -1
                        else -> 0
                    }
                }
            }.add(report)
        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): List<BattleReportEntity>? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(BATTLE_REPORT_NAMED_QUERY)
                .setLong("playerId", playerId)
                .listNoDup<BattleReportEntity>()
            list
        }
        return data
    }

    // 存入简单战报
    fun createBattleReport(
        readState: Int,
        reportType: Int,
        fightTime: Long,
        posX: Int,
        posY: Int,
        pastTime: Long,
        reportContent: ByteString,
        fightDetail: ByteString
    ): BattleReport {
        val battleReport = BattleReport(
            hpm.generateObjIdNew(),
            readState,
            zeroTime.time,
            reportType,
            fightTime,
            posX,
            posY,
            pastTime,
            reportContent.toByteArray(),
            fightDetail.toByteArray(),
            playerId
        )

        wdb.save(battleReport)

        // 存入缓存
        battleReportMap.insertByKey(battleReport)

        battleReportQueue.getOrPut(battleReport.reportType) {
            PriorityQueue { c1, c2 ->
                when {
                    c1.pastTime > c2.pastTime -> 1
                    c1.pastTime < c2.pastTime -> -1
                    else -> 0
                }
            }
        }.add(battleReport)

        return battleReport
    }

    // 删除一个
    fun deleteBattleReport(battleReport: BattleReport?) {
        if (battleReport == null) {
            return
        }

        wdb.delete(battleReport)

        // 从缓存中删除
        battleReportMap.deleteByKey(battleReport)
        battleReportQueue[battleReport.reportType]?.remove(battleReport)
        if (battleReportQueue[battleReport.reportType]?.isEmpty() == true) {
            battleReportQueue.remove(battleReport.reportType)
        }
    }

    // 根据战报ID拿简单战报信息
    fun findBattleReportById(battleReportId: Long): BattleReport? {
        // 根据部队ID获取部队
        return battleReportMap.findByKey(battleReportId)
    }

    // 找出玩家所有主界面按钮里的简单战报的信息
    fun findAllShowBattleReport(): LinkedList<BattleReport> {
        val battleReportWraps = LinkedList<BattleReport>()
        battleReportMap.map {
            if (it.reportType != JJC_FIGHT_REPORT) {
                battleReportWraps += it
            }
            true
        }

        return battleReportWraps
    }

    // 找出帮派所有简单战报的信息
    fun findAllJjcReport(): LinkedList<BattleReport> {
        val battleReportWraps = LinkedList<BattleReport>()
        battleReportQueue[JJC_FIGHT_REPORT]?.forEach {
            battleReportWraps += it
        }

        return battleReportWraps
    }

    //查找未读战报数量
    fun findUnreadReportNumByPlayerId(): Int {
        var unreadNum = 0
        battleReportMap.map {
            if (it.readState == UnRead) {
                unreadNum++
            }
            true
        }
        return unreadNum
    }
}

// 战报
class BattleReport(
    // 战斗数据
    var id: Long,

    var readState: Int,                 // 阅读状态  0-未读 1-已读
    var archived: Long,                 // 收藏时间。0：未收藏，1：收藏
    var reportType: Int,               // 战报类型
    var fightTime: Long,                // 战斗时间
    var fightAddressX: Int,               // 战斗地点
    var fightAddressY: Int,               // 战斗地点
    var pastTime: Long,                 // 过期时间
    var reportContent: ByteArray,       // 战报内容
    var fightDetail: ByteArray,         // 战斗过程
    var playerId: Long  // 玩家ID，
) : EntityWrapper<BattleReportEntity> {
    constructor() : this(0, 0, 0, 0, 0, 0, 0, 0, byteArrayOf(), "".toByteArray(), 0)

    override fun getCheckModInterval(): Duration = Duration.ofMinutes(3L)

    override fun getPrimaryKey(): Serializable = BattleReportPK(playerId, id)

    override fun toEntity(): BattleReportEntity {
        return BattleReportEntity(
            id,
            readState,
            archived,
            reportType,
            fightTime,
            fightAddressX,
            fightAddressY,
            pastTime,
            reportContent,
            fightDetail,
            playerId
        )
    }

    override fun wrap(entity: BattleReportEntity) {
        id = entity.id
        readState = entity.readState
        archived = entity.archived
        reportType = entity.reportType
        fightTime = entity.fightTime
        fightAddressX = entity.fightAddressX
        fightAddressY = entity.fightAddressY
        pastTime = entity.pastTime
        reportContent = entity.reportContent
        fightDetail = entity.fightDetail
        playerId = entity.playerId
    }
}
