package com.point18.slg2d.public.tellMsg

import com.point18.slg2d.common.constg.ADD_ALLIANCE_INFO
import com.point18.slg2d.common.constg.REMOVE_ALLIANCE_INFO
import com.point18.slg2d.public.datacache.AllianceMember
import com.point18.slg2d.public.datacache.CacheAllianceManager
import com.point18.slg2d.public.datacache.PublicManagerCache
import com.point18.slg2d.public.pmodule.Public2PublicManagerTellDealBase
import com.point18.slg2d.public.pmodule.makeAllianceRank
import com.point18.slg2d.public.ppm
import pb4server.*
import xyz.ariane.util.tellNoSender

// 修改联盟属性的变化
class SyncAllianceSimpleInfoTellDeal : Public2PublicManagerTellDealBase() {
    override fun dealPublicTell(publicCache: PublicManagerCache, msg: Public2PublicManagerTell) {
        val tellMsg = msg.syncAllianceSimpleInfoTell
        dealSyncAllianceSimpleInfoTell(
            publicCache,
            tellMsg.changeType,
            tellMsg.allianceId,
            tellMsg.syncAllianceSimpleInfoVo
        )
    }

    private fun dealSyncAllianceSimpleInfoTell(
        publicCache: PublicManagerCache,
        changeType: Int,
        allianceId: Long,
        syncAllianceInfo: SyncAllianceSimpleInfoVo
    ) {
        val allianceOccupyInfo = AllianceOccupyInfo.newBuilder()

        if (changeType == ADD_ALLIANCE_INFO) {
            val nowInfo = publicCache.allianceCache.alianceSimpleInfos[allianceId]
            if (nowInfo == null) {
                publicCache.allianceCache.alianceSimpleInfos[allianceId] = CacheAllianceManager.AllianceSimpleInfoVo(
                    syncAllianceInfo.allianceName,
                    syncAllianceInfo.allianceShortName,
                    syncAllianceInfo.allianceId,
                    syncAllianceInfo.flagColor,
                    syncAllianceInfo.flagStyle,
                    syncAllianceInfo.flagEffect,
                    syncAllianceInfo.power,
                    syncAllianceInfo.killSolider,
                    syncAllianceInfo.allianceCompetitionScore,
                    syncAllianceInfo.allianceCompetitionScoreChangeTime,
                    syncAllianceInfo.monsterScore,
                    syncAllianceInfo.allianceRankLv,
                    syncAllianceInfo.allianceMemberNum,
                    syncAllianceInfo.allianceOccupyInfosList,
                    syncAllianceInfo.allianceMainMemberName,
                    syncAllianceInfo.allianceAreaNo
                )
            } else {
                var newPower = nowInfo.power + syncAllianceInfo.power
                if (newPower < 0) {
                    newPower = 0
                }
                val killSolider = nowInfo.killSolider + syncAllianceInfo.killSolider
                val monsterScore = nowInfo.monsterScore + syncAllianceInfo.monsterScore

                nowInfo.allianceName = syncAllianceInfo.allianceName
                nowInfo.allianceShortName = syncAllianceInfo.allianceShortName
                nowInfo.flagColor = syncAllianceInfo.flagColor
                nowInfo.flagStyle = syncAllianceInfo.flagStyle
                nowInfo.flagEffect = syncAllianceInfo.flagEffect
                nowInfo.power = newPower
                nowInfo.killSolider = killSolider
                nowInfo.allianceCompetitionScore = syncAllianceInfo.allianceCompetitionScore
                nowInfo.allianceCompetitionScoreChangeTime = syncAllianceInfo.allianceCompetitionScoreChangeTime
                nowInfo.monsterScore = monsterScore
                nowInfo.allianceRankLv = syncAllianceInfo.allianceRankLv
                nowInfo.allianceMemberNum = syncAllianceInfo.allianceMemberNum
                nowInfo.allianceOccupyInfos = syncAllianceInfo.allianceOccupyInfosList
                nowInfo.allianceMemberName = syncAllianceInfo.allianceMainMemberName
                nowInfo.allianceAreaNo = syncAllianceInfo.allianceAreaNo

                allianceOccupyInfo.allianceId = nowInfo.allianceId
                allianceOccupyInfo.allianceMainMemberName = syncAllianceInfo.allianceMainMemberName
                allianceOccupyInfo.allianceAreaNo = syncAllianceInfo.allianceAreaNo
                allianceOccupyInfo.allianceName = nowInfo.allianceName
                allianceOccupyInfo.allianceShortName = nowInfo.allianceShortName
                allianceOccupyInfo.addAllWorldId(nowInfo.allianceOccupyInfos)
            }

            // 通知世界进程修改这个联盟的数据
            val allianceSimpleInfoChangeTell = AllianceSimpleInfoChangeTell.newBuilder()
            allianceSimpleInfoChangeTell.addAllAllianceRankInfo(makeAllianceRank(publicCache))
            allianceSimpleInfoChangeTell.allianceOccupyInfo = allianceOccupyInfo.build()

            ppm.p2wSyncInfoRouter.tellNoSender(
                ppm.fillPublicManager2WorldManagerTellMsgHeader {
                    it.setAllianceSimpleInfoChangeTell(
                        allianceSimpleInfoChangeTell
                    )
                }
            )
        } else if (changeType == REMOVE_ALLIANCE_INFO) {
            val nowInfo = publicCache.allianceCache.alianceSimpleInfos[allianceId]
            if (nowInfo != null) {
                // 删除节点上的缓存数据
                publicCache.allianceCache.nowUseNameMap.remove(nowInfo.allianceName)
                publicCache.allianceCache.nowUseShortNameMap.remove(nowInfo.allianceShortName)
                publicCache.allianceCache.allianceOccupyInfos.remove(allianceId)
                publicCache.allianceCache.alianceSimpleInfos.remove(allianceId)
                publicCache.allianceCache.allianceMemberByAllianceId.remove(allianceId)
                publicCache.allianceCache.allianceMemberTraceByAllianceId.remove(allianceId)

                // 删除掉这个联盟的总动员数据
                val groupInfo =
                    publicCache.allianceCompetitionGroupCacheManager.findAllianceCompetitionGroupByAllianceId(allianceId)
                if (groupInfo != null) {
                    publicCache.allianceCompetitionGroupCacheManager.deleteAllianceCompetitionGroup(groupInfo)
                }

                // 通知世界进程删除这个联盟的数据
                val allianceRemoveTell = AllianceRemoveTell.newBuilder()
                allianceRemoveTell.allianceId = allianceId
                allianceRemoveTell.allianceName = nowInfo.allianceName
                allianceRemoveTell.allianceShortName = nowInfo.allianceShortName

                ppm.p2wSyncInfoRouter.tellNoSender(
                    ppm.fillPublicManager2WorldManagerTellMsgHeader {
                        it.setAllianceRemoveTell(
                            allianceRemoveTell
                        )
                    }
                )
            }
        }

    }
}