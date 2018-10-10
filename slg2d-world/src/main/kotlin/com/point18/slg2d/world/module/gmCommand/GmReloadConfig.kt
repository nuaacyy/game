package com.point18.slg2d.world.module.gmCommand

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.area4data.fetchAllOnlinePlayerSessions
import xyz.ariane.util.lzWarn
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.SYNC_CONFIG_PID
import com.point18.slg2d.world.msgnotice.createCheckConfigNotifier
import pb4server.ReloadHomeConfigAskReq
import pb4server.ReloadPublicConfigAskReq
import pb4server.World2HomeAskResp
import pb4server.World2PublicAskResp
import com.point18.slg2d.common.pc.ProtoCacheStore
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode

class GmReloadConfig : GmCommand {
    override fun exec(session: PlayerSession, message: String): Int {
        val areaCache = session.areaCache
        val playerId = session.playerId

        val newPcs = ProtoCacheStore()
//        val newPcs = ProtoCacheStore(wpm.zkDao.zkClient)

        var info = "Game配置更新完毕"
        try {
            newPcs.initProtoCache()
        } catch (e: Exception) {
            normalLog.lzWarn { "重载配置错误:$e" }
            info = "重载配置错误:$e"
            val noticeHomeNotifier = createCheckConfigNotifier(info)
            noticeHomeNotifier.notice(session)
            return 2
        }
        pcs = newPcs


        val noticeGameNotifier = createCheckConfigNotifier(info)
        noticeGameNotifier.notice(session)

        areaCache.worldActor.createACS<World2HomeAskResp>()
            .ask(
                areaCache.worldActor.homeShardRegion,
                areaCache.fillW2HAskMsgHeader(playerId) {
                    it.setReloadHomeConfigAskReq(ReloadHomeConfigAskReq.newBuilder())
                },
                World2HomeAskResp::class.java
            )
            .whenCompleteKt { askResp, err ->
                if (err != null || askResp == null) {
                    info = "Home配置更新错误:$err"
                    val noticeHomeAllNotifier = createCheckConfigNotifier(info)
                    for ((_, sessionTemp) in fetchAllOnlinePlayerSessions(areaCache)) {
                        noticeHomeAllNotifier.notice(sessionTemp)
                    }
                    return@whenCompleteKt
                }
                if (askResp.reloadHomeConfigAskRt.rt != ResultCode.SUCCESS.code) {
                    info = "Home配置更新失败:${askResp.reloadHomeConfigAskRt.rt}"
                    val noticeHomeNotifier = createCheckConfigNotifier(info)
                    noticeHomeNotifier.notice(session)
                    return@whenCompleteKt
                }
                info = "Home配置更新完毕"
                val noticeHomeNotifier = createCheckConfigNotifier(info)
                noticeHomeNotifier.notice(session)
            }

        areaCache.worldActor.createACS<World2PublicAskResp>()
            .ask(
                areaCache.worldActor.publicShardRegion,
                areaCache.fillW2PAskMsgHeader(SYNC_CONFIG_PID, playerId) {
                    it.setReloadPublicConfigAskReq(ReloadPublicConfigAskReq.newBuilder())
                },
                World2PublicAskResp::class.java
            )
            .whenCompleteKt { askResp, err ->
                if (err != null || askResp == null) {

                    info = "Public配置更新错误:$err"
                    val noticePublicAllNotifier = createCheckConfigNotifier(info)
                    for ((_, sessionTemp) in fetchAllOnlinePlayerSessions(areaCache)) {
                        noticePublicAllNotifier.notice(sessionTemp)
                    }
                    return@whenCompleteKt
                }
                if (askResp.reloadPublicConfigAskRt.rt != ResultCode.SUCCESS.code) {
                    info = "Public配置更新失败:${askResp.reloadPublicConfigAskRt.rt}"
                    val noticePublicNotifier = createCheckConfigNotifier(info)
                    noticePublicNotifier.notice(session)
                    return@whenCompleteKt
                }

                info = "Public配置更新完毕"
                val noticePublicNotifier = createCheckConfigNotifier(info)
                noticePublicNotifier.notice(session)
            }

        return 1
    }
}