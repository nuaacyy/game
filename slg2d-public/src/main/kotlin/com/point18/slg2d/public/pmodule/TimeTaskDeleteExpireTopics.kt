package com.point18.slg2d.public.pmodule

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.public.common.dealAfterAlliancePublishTopic
import com.point18.slg2d.public.datacache.PublicCache

// 定时任务：删除联盟邮件主题及回复消息
fun timeTaskDeleteExpireTopics(publicCache: PublicCache) {
    // 过期时间
    val expireAt = getNowTime() - pcs.basicProtoCache.mailExpireDuration
    val alce = publicCache.allianceCache.findAllianceById(publicCache.publicActor.publicId)
    if (alce == null) {
        return
    }
    val aTopics = publicCache.allianceTopicCache.findAllianceTopicsByAllianceId(alce.id)
    for ((_, aTopic) in aTopics) {
        if (aTopic.lastAt > expireAt) {
            continue
        }

        // 删除邮件主题的回复消息
        publicCache.allianceReplyCache.deleteAllianceRepliesByTopicId(aTopic.id)

        // 删除联盟邮件主题
        publicCache.allianceTopicCache.deleteAllianceTopic(aTopic)

        // 向相关成员推送联盟主题邮件变化提醒
        val mdl = aTopic.type

        val sPlayers = fetchNotifierPlayersByOfManager(publicCache, alce.id, 0, mdl, aTopic.type, aTopic.createAt)
        // 推送消息提醒
        val pltAreas = mutableMapOf<Long, Int>()

        for (am in sPlayers) {
            pltAreas[am.mapPltAreaId] = 1
        }

        for ((pltAreaId, _) in pltAreas) {
            dealAfterAlliancePublishTopic(publicCache.publicActor, pltAreaId, alce.id, aTopic.id)
        }
    }
}
