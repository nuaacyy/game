package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.processConfig
import com.point18.slg2d.common.constg.ADD_ALLIANCE_INFO
import com.point18.slg2d.common.constg.ALLIANCE_COMPETITION_CHANGE_CLOSE
import com.point18.slg2d.common.constg.ALLIANCE_COMPETITION_CHANGE_OPEN
import com.point18.slg2d.common.constg.ALLIANCE_COMPETITION_CHANGE_OVER
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.public.datacache.*
import com.point18.slg2d.public.ppm
import pb4server.AllianceCompetitionOpenTell
import pb4server.SyncAllianceSimpleInfoTell
import pb4server.SyncAllianceSimpleInfoVo
import java.util.*

// 联盟总动员的开启
fun allianceCompetitionStart(publicCache: PublicManagerCache) {
    // 遍历所有联盟,根据段位分组,给符合要求的联盟发门票,然后把分组数据记录下来
    val allAlliance = publicCache.allianceCache.alianceSimpleInfos
    val allAllianceByRankLv = mutableMapOf<Int, LinkedList<CacheAllianceManager.AllianceSimpleInfoVo>>()
    for ((id, alce) in allAlliance) {
        if (alce.allianceMemberNum < com.point18.slg2d.common.pc.pcs.basicProtoCache.allianceCompetitionConditions) {
            // 人数不够要求
            continue
        }

        val abrl = allAllianceByRankLv.getOrPut(alce.allianceRankLv) { LinkedList() }
        abrl.add(alce)

        alce.allianceCompetitionScore = 0
        alce.allianceCompetitionScoreChangeTime = 0
        // 通知到普通联盟节点获得参赛资格了
        val allianceCompetitionOpenTell = AllianceCompetitionOpenTell.newBuilder()
        allianceCompetitionOpenTell.state = ALLIANCE_COMPETITION_CHANGE_OPEN
        val pm2pTell =
            publicCache.publicManagerActor.fillPublicManager2PublicTellMsgHeader(id) {
                it.allianceCompetitionOpenTell = allianceCompetitionOpenTell.build()
            }
        publicCache.publicManagerActor.tell2Public(pm2pTell)
    }

    // 最后再开个新表记录下来规划的分组
    for ((rankLv, alliances) in allAllianceByRankLv) {
        var allianceCompetitionPartakeNub = 0
        val allianceCompetitionRankingProto =
            com.point18.slg2d.common.pc.pcs.allianceCompetitionRankingProtoCache.protoMapById[rankLv]
        if (allianceCompetitionRankingProto == null) {
            allianceCompetitionPartakeNub = 20
        } else {
            allianceCompetitionPartakeNub = allianceCompetitionRankingProto.allianceCompetitionPartakeNub
        }
        val allianceCount = alliances.size // 一共有这么多个帮派
        if (allianceCount == 0) {
            continue
        }

        var groupNum = (allianceCount.toDouble() / allianceCompetitionPartakeNub.toDouble()).toInt() // 这个段位要分成这么多个组
        val ac = (allianceCount % allianceCompetitionPartakeNub)
        if (ac != 0) {
            groupNum += 1
        }

        // 给联盟安排进组
        // 0-19
        // 20-39
        // 40-59
        for (i in 1..groupNum) {
            val startIndex = (i - 1) * allianceCompetitionPartakeNub
            val overIndex = startIndex + (allianceCompetitionPartakeNub - 1)

            if ((allianceCount - 1) < overIndex) {
                // 这是最后一组了,把剩余的全部放进去
                val ag = alliances.subList(startIndex, alliances.size)
                for (a in ag) {
                    publicCache.allianceCompetitionGroupCacheManager.createAllianceCompetitionGroup(
                        rankLv * 10000 + i,
                        a.allianceRankLv,
                        a.allianceId
                    )
                }
            } else {
                // 还有充足的剩余帮派未分组
                val ag = alliances.subList(startIndex, overIndex)
                for (a in ag) {
                    publicCache.allianceCompetitionGroupCacheManager.createAllianceCompetitionGroup(
                        rankLv * 10000 + i,
                        a.allianceRankLv,
                        a.allianceId
                    )
                }
            }
        }
    }
}

// 活动结束 进行排名 等待领奖
fun allianceCompetitionOver(publicCache: PublicManagerCache) {
    val groups = publicCache.allianceCompetitionGroupCacheManager.findAllianceCompetitionGroups()
    for ((rankLv, alliances) in groups) {
        val allianceCompetitionRankingProto =
            com.point18.slg2d.common.pc.pcs.allianceCompetitionRankingProtoCache.protoMapById[rankLv]
        if (allianceCompetitionRankingProto == null) {
            continue
        }
        if (alliances.size > 1) {
            alliances.sortWith(Comparator { a1, a2 ->
                val allianceI = publicCache.allianceCache.alianceSimpleInfos[a1.allianceId]
                val allianceJ = publicCache.allianceCache.alianceSimpleInfos[a2.allianceId]
                if (allianceI == null || allianceJ == null) {
                    0
                } else {
                    if (allianceI.allianceCompetitionScore > allianceJ.allianceCompetitionScore) {
                        -1
                    } else if (allianceI.allianceCompetitionScore == allianceJ.allianceCompetitionScore) {
                        if (allianceI.allianceCompetitionScoreChangeTime < allianceJ.allianceCompetitionScoreChangeTime) {
                            -1
                        } else if (allianceI.allianceCompetitionScoreChangeTime == allianceJ.allianceCompetitionScoreChangeTime) {
                            0
                        } else {
                            1
                        }
                    } else {
                        1
                    }
                }
            })
        }

        // 排序
        for (index in alliances.indices) {
            val allianceGroup = alliances[index]
            val allianceInfo =
                publicCache.allianceCache.alianceSimpleInfos[allianceGroup.allianceId]
            if (allianceInfo == null) {
                continue
            }
            // 判断升级与降级
            val allianceGroupInfo =
                publicCache.allianceCompetitionGroupCacheManager.findAllianceCompetitionGroupByAllianceId(
                    allianceGroup.allianceId
                )
            if (allianceGroupInfo == null) {
                continue
            }

            val rankLv = allianceGroupInfo.stateRankLv
            if (index <= (allianceCompetitionRankingProto.upNub - 1)) {
                // 没有达到升级段位的最低档次要求
                val socreProtos =
                    com.point18.slg2d.common.pc.pcs.allianceCompetitionRewardProtoCache.allianceCompetitionRewardProtoMapByLvAndScore[rankLv]
                if (socreProtos == null) {
                    continue
                }
                var finishStage = 0
                for ((_, socreProto) in socreProtos) {
                    if (allianceGroupInfo.score >= socreProto.score) {
                        finishStage += 1
                    }
                }

                if (finishStage < allianceCompetitionRankingProto.upStageLimit) {
                    continue
                }

                // 升级
                val ex = com.point18.slg2d.common.pc.pcs.allianceCompetitionRankingProtoCache.protoMapById[rankLv + 1]
                if (ex != null) {
                    allianceGroupInfo.overRankLv = rankLv + 1
                    allianceInfo.allianceRankLv = rankLv + 1
                } else {
                    // 顶级了
                    allianceGroupInfo.overRankLv = allianceGroupInfo.stateRankLv
                    allianceInfo.allianceRankLv = allianceGroupInfo.stateRankLv
                }
            } else if ((index + 1) >= allianceCompetitionRankingProto.downNub) {
                // 掉级
                if (rankLv != com.point18.slg2d.common.pc.pcs.basicProtoCache.allianceCompetitionFirstLevel) {
                    allianceGroupInfo.overRankLv = rankLv - 1
                    allianceInfo.allianceRankLv = rankLv - 1
                } else {
                    // 最低级了
                    allianceGroupInfo.overRankLv = allianceGroupInfo.stateRankLv
                    allianceInfo.allianceRankLv = allianceGroupInfo.stateRankLv
                }
            } else {
                // 不变
                allianceGroupInfo.overRankLv = allianceGroupInfo.stateRankLv
            }
            allianceGroupInfo.score = allianceInfo.allianceCompetitionScore.toInt()

            // 通知到普通联盟节点获得参赛资格了
            val allianceCompetitionOpenTell = AllianceCompetitionOpenTell.newBuilder()
            allianceCompetitionOpenTell.state = ALLIANCE_COMPETITION_CHANGE_OVER
            allianceCompetitionOpenTell.rankLv = allianceGroupInfo.overRankLv
            allianceCompetitionOpenTell.indexLv = index + 1
            val pm2pTell =
                publicCache.publicManagerActor.fillPublicManager2PublicTellMsgHeader(allianceInfo.allianceId) {
                    it.allianceCompetitionOpenTell = allianceCompetitionOpenTell.build()
                }
            publicCache.publicManagerActor.tell2Public(pm2pTell)
        }
    }
}

// 领奖时间结束,活动数据移除
fun allianceCompetitionClose(publicCache: PublicManagerCache) {
    // 先清除掉公共服的数据

    // 收会所有帮派的门票
    val groups = publicCache.allianceCompetitionGroupCacheManager.findAllianceCompetitionGroups()
    for ((_, alliances) in groups) {
        for (allianceGroup in alliances) {
            val allianceInfo =
                publicCache.allianceCache.alianceSimpleInfos[allianceGroup.allianceId]
            if (allianceInfo == null) {
                continue
            }

            // 通知到普通联盟节点获得参赛资格了
            val allianceCompetitionOpenTell = AllianceCompetitionOpenTell.newBuilder()
            allianceCompetitionOpenTell.state = ALLIANCE_COMPETITION_CHANGE_CLOSE

            val pm2pTell =
                publicCache.publicManagerActor.fillPublicManager2PublicTellMsgHeader(allianceInfo.allianceId) {
                    it.allianceCompetitionOpenTell = allianceCompetitionOpenTell.build()
                }
            publicCache.publicManagerActor.tell2Public(pm2pTell)

            allianceInfo.allianceCompetitionScore = 0
            allianceInfo.allianceCompetitionScoreChangeTime = 0

            // 删除组内数据
            publicCache.allianceCompetitionGroupCacheManager.deleteAllianceCompetitionGroup(allianceGroup)

            allianceInfo.allianceCompetitionScore = 0
            allianceInfo.allianceCompetitionScoreChangeTime = 0
        }
    }
}

// 联盟基础信息发生 推送到联盟管理服
fun syncAllianceInfo2AM(publicCache: PublicCache, alce: Alliance, power: Long, killSolider: Long, monsterScore: Long) {
    val syncAllianceSimpleInfoTell = SyncAllianceSimpleInfoTell.newBuilder()
    syncAllianceSimpleInfoTell.changeType = ADD_ALLIANCE_INFO
    syncAllianceSimpleInfoTell.allianceId = alce.id

    val vo = SyncAllianceSimpleInfoVo.newBuilder()
    vo.allianceName = alce.name
    vo.allianceShortName = alce.shortName
    vo.allianceId = alce.allianceId
    vo.flagColor = alce.flagColor
    vo.flagStyle = alce.flagStyle
    vo.flagEffect = alce.flagEffect
    vo.allianceCompetitionScore = alce.allianceCompetitionScore.toLong()
    vo.allianceCompetitionScoreChangeTime = alce.allianceCompetitionScoreChangeTime
    vo.power = power
    vo.killSolider = killSolider
    vo.monsterScore = monsterScore
    vo.allianceRankLv = alce.allianceRankLv
    vo.allianceMemberNum = alce.allianceMemberNum
    for ((worldId, _) in alce.allianceOccupyInfo) {
        vo.addAllianceOccupyInfos(worldId)
    }

    var mName = ""

    val mainPlayer = publicCache.allianceMemberCache.findAllianceMemberById(alce.mainPlayerId)
    if (mainPlayer != null) {
        mName = mainPlayer.name
    }
    vo.allianceMainMemberName = mName

    vo.allianceAreaNo = alce.allianceAreaNo

    syncAllianceSimpleInfoTell.syncAllianceSimpleInfoVo = vo.build()

    val p2pmTell2 = ppm.fillPublic2PublicManagerTellMsgHeader {
        it.syncAllianceSimpleInfoTell = syncAllianceSimpleInfoTell.build()
    }
    ppm.tell2PublicManager(p2pmTell2)

}

// 检测是否可以替换成为新盟主(只是开服时间上的检测)
fun checkChangeMainPlayer(
    publicCache: PublicCache,
    newMainPlayer: AllianceMember,
    oldMainPlayer: AllianceMember
): Boolean {
    val targetAreaConfig = processConfig.findSpecAreaConfig(newMainPlayer.mapPltAreaId)
    if (targetAreaConfig == null) {
        return false
    }

    val myAreaConfig = processConfig.findSpecAreaConfig(oldMainPlayer.mapPltAreaId)
    if (myAreaConfig == null) {
        return false
    }

    if (newMainPlayer.mapPltAreaId == oldMainPlayer.mapPltAreaId) {
        // 如果是同服的两个玩家 直接允许
        return true
    }

    if (targetAreaConfig.areaPublishTime + pcs.basicProtoCache.serverOpenTime > getNowTime() && myAreaConfig.areaPublishTime < targetAreaConfig.areaPublishTime) {
        // 如果老盟主所在服未出保,并且我所在服比老盟主所在服更新,不允许
        return false
    }

    if (targetAreaConfig.areaPublishTime + pcs.basicProtoCache.serverOpenTime < getNowTime() && myAreaConfig.areaPublishTime + pcs.basicProtoCache.serverOpenTime > getNowTime()) {
        // 如果老盟主所在服已经出保,并且我所在服比未出,不允许
        return false
    }

    return true

}