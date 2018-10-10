package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.public.common.allianceHelpInfoChange
import com.point18.slg2d.public.common.noticeOccupyWonder2Home
import com.point18.slg2d.public.common.noticeOccupyWonder2World
import com.point18.slg2d.public.datacache.*
import pb4server.AllianceRankInfos
import pb4server.AllianceSimpleInfoVo
import java.io.Serializable
import java.util.*


// 插入联盟日志
fun insertLog(publicCache: PublicCache, aid: Long, lt: com.point18.slg2d.common.constg.LogType, vararg args: String) {
    if (aid == 0L) {
        return
    }

    val src = toJson(args)
    publicCache.allianceLogCache.createAllianceLog(aid, "", lt, src)
}


// 验证职位是否有权限：返回true，有权限；反之，没有权限
fun hasRight(allianceMember: AllianceMember?, right: com.point18.slg2d.common.constg.RightType): (Boolean) {
    if (allianceMember == null) {
        return false
    }

    for ((posId, _) in allianceMember.alliancePosMap) {
        val ok = com.point18.slg2d.common.pc.pcs.posRightCache.hasRight(posId, right)
        if (ok) {
            return true
        }
    }

    return false
}

// 离开联盟
fun resetPlayerAllianceInfo(
    publicCache: PublicCache,
    alce: Alliance,
    allianceMember: AllianceMember?,
    isRook: Int
) {
    if (allianceMember == null || allianceMember.allianceId == 0L) {
        return
    }

    publicCache.allianceMemberCache.deleteAllianceMember(allianceMember)

    val helpIds = LinkedList<Long>()
    for (help in publicCache.allianceHelpCache.findAllianceHelpByPlayerId(allianceMember.id)) {
        publicCache.allianceHelpCache.deleteAllianceHelp(help)
        helpIds.add(help.id)
    }

    val allAllianceMembers =
        publicCache.allianceMemberCache.findAllianceMembersByAllianceId(allianceMember.allianceId)

    val pltAreas = mutableMapOf<Long, LinkedList<Long>>()

    for (am in allAllianceMembers) {
        val ps = pltAreas.getOrPut(am.mapPltAreaId) { LinkedList() }
        if (allianceMember.mapPltAreaId == am.mapPltAreaId && allianceMember.id != am.id) {
            ps.add(am.id)
        }
    }

    for ((pltAreaId, playerIds) in pltAreas) {
        allianceHelpInfoChange(publicCache.publicActor, pltAreaId, playerIds, REMOVE_ALLIANCE_INFO, helpIds)
    }

    // 通知home服失去奇观
    noticeOccupyWonder2Home(publicCache.publicActor, allianceMember.id, Del, alce.allianceOccupyInfo)

    // 通知world服失去奇观
    val memberIds = LinkedList<Long>()
    memberIds.add(allianceMember.id)
    noticeOccupyWonder2World(publicCache.publicActor, allianceMember.mapPltAreaId, memberIds, Del, alce.allianceOccupyInfo)

    // 联盟基础信息发生变化 推送到公共服
    syncAllianceInfo2AM(publicCache, alce, 0 - allianceMember.memPower, 0, 0)

}

class AllianceReplyInfoVo : Serializable {
    var replyId: Long = 0 //回复的回复ID
    var playerId: Long = 0  //回复的玩家ID
    var playerName: String = ""  //回复的玩家名称
    var playerShortName: String = "" //回复的玩家昵称
    var positions = LinkedList<Int>()  //回复者的职位
    var photoProtoId: Int = 0  //回复者的头像模版
    var message: String = "" //回复内容
    var replyAt: Int = 0  //回复时间
    var vipLv: Int = 0  //vip等级
    var curentPos: Int = 0  // 官职
}

// 根据联盟邮件主题及玩家，过滤出符合条件的回复消息: player-当前玩家	topic-联盟邮件主题
fun getPlayerTopicReply(
    publicCache: PublicCache,
    player: AllianceMember,
    topic: AllianceTopic,
    lastId: Long,
    count: Int
): (LinkedList<AllianceReplyInfoVo>) {
    val replies = LinkedList<AllianceReplyInfoVo>()

    // 如果发布联盟邮件主题的玩家跟当前玩家不在一个联盟（退盟或被移除），应该再也看不到这些内容
    // 如果这个玩家在加入联盟之前，已经有了这个联盟主题，也要看不见聊天内容
    val topicPlayer = publicCache.allianceMemberCache.findAllianceMemberById(topic.playerId)
    if (topicPlayer == null || topicPlayer.allianceId != player.allianceId || player.allianceAt > topic.createAt) {
        return replies
    }

    // 判定当前玩家是否是管理人员
    val mdl = topic.type
    val isMgr = hasRight(player, mdl)
    //if !isMgr && topic.GetType() != constg.A_RIGHT_TOPIC_ALL {
    //	// 如果是非管理人员（忽略全盟邮件），那么需要验证是否是对应的团成员
    //	if (topic.GetType() == constg.A_RIGHT_TOPIC_TEAM_1 && com.point18.slg2d.aPublic.datacache.playerIsHavePos(player, constg.POSITION_NORMAL_1)) ||
    //		(topic.GetType() == constg.A_RIGHT_TOPIC_TEAM_2 && com.point18.slg2d.aPublic.datacache.playerIsHavePos(player, constg.POSITION_NORMAL_2)) ||
    //		(topic.GetType() == constg.A_RIGHT_TOPIC_TEAM_3 && com.point18.slg2d.aPublic.datacache.playerIsHavePos(player, constg.POSITION_NORMAL_3)) ||
    //		(topic.GetType() == constg.A_RIGHT_TOPIC_TEAM_4 && com.point18.slg2d.aPublic.datacache.playerIsHavePos(player, constg.POSITION_NORMAL_4)) {
    //		return replies
    //	}
    //}
    val aReplies = publicCache.allianceReplyCache.findAllianceRepliesByTopicId(topic.id)

    // 需要从切片下标最大的开始取（ID越大，说明离现在越近）
    for (i in aReplies.size - 1 downTo 1) {
        val reply = aReplies[i]

        // 跳过客户端已经拥有的回复记录
        if (lastId != 0L && reply.id >= lastId) {
            continue
        }

        // 回复消息的玩家已经不在联盟中，无论是管理人员和普通人员，都不会看到这条消息
        val replyPlayer = publicCache.allianceMemberCache.findAllianceMemberById(reply.playerId)
        if (replyPlayer == null || topicPlayer.allianceId != player.allianceId) {
            continue
        }

        // 1.管理人员可以看到全部的回复消息
        // 2.非管理人员只能看到管理人员的回复消息和自己的回复消息
        val isMgrReply = hasRight(replyPlayer, mdl)

        if (isMgr || isMgrReply || player.id == replyPlayer.id) {
            val pbReply = AllianceReplyInfoVo()
            val pp = LinkedList<Int>()
            for ((p, _) in replyPlayer.alliancePosMap) {
                pp.add(p)
            }
            pbReply.replyId = reply.id
            pbReply.playerId = replyPlayer.id
            pbReply.playerName = replyPlayer.name
            pbReply.playerShortName = replyPlayer.allianceNickName
            pbReply.photoProtoId = replyPlayer.photoProtoId
            pbReply.message = reply.message
            pbReply.replyAt = (reply.replyAt / 1000).toInt()
            pbReply.vipLv = replyPlayer.vipLv
            // todo jh pos
            //pbReply.curentPos = replyPlayer.curentPos
            pbReply.curentPos = 0
            pbReply.positions = pp


            replies.add(pbReply)
            if (replies.size == count) {
                break
            }
        }
    }

    return replies
}

// 根据联盟邮件主题及玩家，过滤出符合条件的回复消息: player-当前玩家	topic-联盟邮件主题
fun getAllPlayerTopicReply(
    publicCache: PublicCache,
    player: AllianceMember,
    topic: AllianceTopic,
    lastId: Long,
    count: Int
): (LinkedList<pb4server.AllianceReplyInfoVo.Builder>) {
    val replies = LinkedList<pb4server.AllianceReplyInfoVo.Builder>()

    // 如果发布联盟邮件主题的玩家跟当前玩家不在一个联盟（退盟或被移除），应该再也看不到这些内容
    // 如果这个玩家在加入联盟之前，已经有了这个联盟主题，也要看不见聊天内容
    val topicPlayer = publicCache.allianceMemberCache.findAllianceMemberById(topic.playerId)
    if (topicPlayer == null || topicPlayer.allianceId != player.allianceId || player.allianceAt > topic.createAt) {
        return replies
    }

    // 判定当前玩家是否是管理人员
    val mdl = topic.type
    val isMgr = hasRight(player, mdl)
    //if !isMgr && topic.GetType() != constg.A_RIGHT_TOPIC_ALL {
    //	// 如果是非管理人员（忽略全盟邮件），那么需要验证是否是对应的团成员
    //	if (topic.GetType() == constg.A_RIGHT_TOPIC_TEAM_1 && com.point18.slg2d.aPublic.datacache.playerIsHavePos(player, constg.POSITION_NORMAL_1)) ||
    //		(topic.GetType() == constg.A_RIGHT_TOPIC_TEAM_2 && com.point18.slg2d.aPublic.datacache.playerIsHavePos(player, constg.POSITION_NORMAL_2)) ||
    //		(topic.GetType() == constg.A_RIGHT_TOPIC_TEAM_3 && com.point18.slg2d.aPublic.datacache.playerIsHavePos(player, constg.POSITION_NORMAL_3)) ||
    //		(topic.GetType() == constg.A_RIGHT_TOPIC_TEAM_4 && com.point18.slg2d.aPublic.datacache.playerIsHavePos(player, constg.POSITION_NORMAL_4)) {
    //		return replies
    //	}
    //}
    val aReplies = publicCache.allianceReplyCache.findAllianceRepliesByTopicId(topic.id)

    // 需要从切片下标最大的开始取（ID越大，说明离现在越近）
    for (i in aReplies.size - 1 downTo 1) {
        val reply = aReplies[i]

        // 跳过客户端已经拥有的回复记录
        if (lastId != 0L && reply.id >= lastId) {
            continue
        }

        // 回复消息的玩家已经不在联盟中，无论是管理人员和普通人员，都不会看到这条消息
        val replyPlayer = publicCache.allianceMemberCache.findAllianceMemberById(reply.playerId)
        if (replyPlayer == null || topicPlayer.allianceId != player.allianceId) {
            continue
        }

        // 1.管理人员可以看到全部的回复消息
        // 2.非管理人员只能看到管理人员的回复消息和自己的回复消息
        val isMgrReply = hasRight(replyPlayer, mdl)

        if (isMgr || isMgrReply || player.id == replyPlayer.id) {
            val pbReply = pb4server.AllianceReplyInfoVo.newBuilder()
            val pp = LinkedList<Int>()
            for ((p, _) in replyPlayer.alliancePosMap) {
                pp.add(p)
            }
            pbReply.replyId = reply.id
            pbReply.playerId = replyPlayer.id
            pbReply.playerName = replyPlayer.name
            pbReply.playerShortName = replyPlayer.allianceNickName
            pbReply.photoProtoId = replyPlayer.photoProtoId
            pbReply.message = reply.message
            pbReply.replyAt = (reply.replyAt / 1000).toInt()
            pbReply.vipLv = replyPlayer.vipLv
            // todo jh pos
            //pbReply.curentPos = replyPlayer.curentPos
            pbReply.curentPos = 0
            pbReply.addAllPositions(pp)


            replies.add(pbReply)
            if (replies.size == count) {
                break
            }
        }
    }

    return replies
}

fun fetchNotifierPlayersByOfManager(
    publicCache: PublicCache,
    aid: Long,
    playerId: Long,
    mdl: com.point18.slg2d.common.constg.RightType,
    t: Int,
    at: Long
): (LinkedList<AllianceMember>) {
    val sPlayers = LinkedList<AllianceMember>()
    val aPlayers = publicCache.allianceMemberCache.findAllianceMembersByAllianceId(aid)
    for (aPlayer in aPlayers) {
        // 忽略自己；如果成员加入前，已经创建这个联盟主题，也忽略
        if (playerId == aPlayer.id || aPlayer.allianceAt > at) {
            continue
        }

        // 如果是管理人员，肯定是必须要推送的
        if (hasRight(aPlayer, mdl)) {
            sPlayers.add(aPlayer)
            continue
        }

        sPlayers.add(aPlayer)
    }

    return sPlayers
}

fun makeAllianceRank(publicCache: PublicManagerCache): (MutableList<AllianceRankInfos>) {
    // 排行那边的数据
    val infos = mutableListOf<AllianceRankInfos>()
    val allAlliance = mutableListOf<CacheAllianceManager.AllianceSimpleInfoVo>()
    for ((_, a) in publicCache.allianceCache.alianceSimpleInfos) {
        allAlliance.add(a)
    }

    if (allAlliance.size == 0) {
        return infos
    }

    // 挨个准备排行榜数据
    val alliancePowerRank = AllianceRankInfos.newBuilder()
    alliancePowerRank.rankType = AlliancePowerRank
    if (allAlliance.count() > 1) {
        allAlliance.sortWith(Comparator { a, b ->
            if (a.power > b.power) {
                return@Comparator -1
            }
            if (a.power == b.power) {
                if (a.allianceId < b.allianceId) {
                    return@Comparator -1
                } else if (a.allianceId == b.allianceId) {
                    return@Comparator 0
                }
            }
            return@Comparator 1
        })

    }

    var maxLen = 100
    if (allAlliance.count() < 100) {
        maxLen = allAlliance.count()
    }
    for (i in 0 until maxLen) {
        val vo = AllianceSimpleInfoVo.newBuilder()
        val vv = allAlliance[i]
        vo.allianceName = vv.allianceName
        vo.allianceShortName = vv.allianceShortName
        vo.allianceId = vv.allianceId
        vo.flagColor = vv.flagColor
        vo.flagStyle = vv.flagStyle
        vo.flagEffect = vv.flagEffect
        vo.value = vv.power
        alliancePowerRank.addAllianceRankInfo(vo.build())
    }

    infos.add(alliancePowerRank.build())

    // 挨个准备排行榜数据
    val allianceKillSoliderRank = AllianceRankInfos.newBuilder()
    allianceKillSoliderRank.rankType = AllianceKillSoliderRank
    if (allAlliance.count() > 1) {
        allAlliance.sortWith(Comparator { a, b ->
            if (a.killSolider > b.killSolider) {
                return@Comparator -1
            }
            if (a.killSolider == b.killSolider) {
                if (a.allianceId < b.allianceId) {
                    return@Comparator -1
                } else if (a.allianceId == b.allianceId) {
                    return@Comparator 0
                }
            }
            return@Comparator 1
        })

    }

    maxLen = 100
    if (allAlliance.count() < 100) {
        maxLen = allAlliance.count()
    }
    for (i in 0 until maxLen) {
        val vo = AllianceSimpleInfoVo.newBuilder()
        val vv = allAlliance[i]
        vo.allianceName = vv.allianceName
        vo.allianceShortName = vv.allianceShortName
        vo.allianceId = vv.allianceId
        vo.flagColor = vv.flagColor
        vo.flagStyle = vv.flagStyle
        vo.flagEffect = vv.flagEffect
        vo.value = vv.killSolider
        allianceKillSoliderRank.addAllianceRankInfo(vo.build())
    }

    infos.add(allianceKillSoliderRank.build())

    // 挨个准备排行榜数据
    val allianceCompetitionRank = AllianceRankInfos.newBuilder()
    allianceCompetitionRank.rankType = AllianceCompetitionRank
    if (allAlliance.count() > 1) {
        allAlliance.sortWith(Comparator { a, b ->
            if (a.allianceCompetitionScore > b.allianceCompetitionScore) {
                return@Comparator -1
            }
            if (a.allianceCompetitionScore == b.allianceCompetitionScore) {
                if (a.allianceCompetitionScoreChangeTime > b.allianceCompetitionScoreChangeTime) {
                    return@Comparator -1
                } else if (a.allianceCompetitionScoreChangeTime == b.allianceCompetitionScoreChangeTime) {
                    return@Comparator 0
                }
            }
            return@Comparator 1
        })

    }

    maxLen = 100
    if (allAlliance.count() < 100) {
        maxLen = allAlliance.count()
    }
    for (i in 0 until maxLen) {
        val vo = AllianceSimpleInfoVo.newBuilder()
        val vv = allAlliance[i]
        vo.allianceName = vv.allianceName
        vo.allianceShortName = vv.allianceShortName
        vo.allianceId = vv.allianceId
        vo.flagColor = vv.flagColor
        vo.flagStyle = vv.flagStyle
        vo.flagEffect = vv.flagEffect
        vo.value = vv.allianceCompetitionScore
        val allianceGroupInfo =
            publicCache.allianceCompetitionGroupCacheManager.findAllianceCompetitionGroupByAllianceId(vv.allianceId)
        if (allianceGroupInfo != null) {
            vo.extend1 = allianceGroupInfo.groupId.toString()
        }
        allianceCompetitionRank.addAllianceRankInfo(vo.build())
    }

    infos.add(allianceCompetitionRank.build())

    // 挨个准备排行榜数据
    val allianceMonsterScoreRank = AllianceRankInfos.newBuilder()
    allianceMonsterScoreRank.rankType = AllianceMonsterScoreRank
    if (allAlliance.count() > 1) {
        allAlliance.sortWith(Comparator { a, b ->
            if (a.monsterScore > b.monsterScore) {
                return@Comparator -1
            }
            if (a.monsterScore == b.monsterScore) {
                if (a.allianceId < b.allianceId) {
                    return@Comparator -1
                } else if (a.allianceId == b.allianceId) {
                    return@Comparator 0
                }
            }
            return@Comparator 1
        })

    }

    maxLen = 100
    if (allAlliance.count() < 100) {
        maxLen = allAlliance.count()
    }
    for (i in 0 until maxLen) {
        val vo = AllianceSimpleInfoVo.newBuilder()
        val vv = allAlliance[i]
        vo.allianceName = vv.allianceName
        vo.allianceShortName = vv.allianceShortName
        vo.allianceId = vv.allianceId
        vo.flagColor = vv.flagColor
        vo.flagStyle = vv.flagStyle
        vo.flagEffect = vv.flagEffect
        vo.value = vv.monsterScore
        allianceMonsterScoreRank.addAllianceRankInfo(vo.build())
    }

    infos.add(allianceKillSoliderRank.build())

    return infos
}