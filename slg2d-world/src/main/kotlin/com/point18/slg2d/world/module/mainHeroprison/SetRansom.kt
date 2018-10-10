package com.point18.slg2d.world.module.mainHeroprison

import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.ADD_PRISON
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.constg.CONTENT_RANSOM
import com.point18.slg2d.common.constg.TEXT_READ_LAN
import com.point18.slg2d.common.constg.TITLE_RANSOM
import com.point18.slg2d.world.msgnotice.createPlayerPrisonChangeNotifier
import com.point18.slg2d.world.msgnotice.createPlayerRansomChangeNotifier
import pb4client.SetRansom
import pb4client.SetRansomRt
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.common.sendMail
import com.point18.slg2d.world.common.noticeCellUpdate
import java.util.*
import java.util.Arrays.asList

// 设置赎金
class SetRansomDeal : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val prisonPlayerId = (msg as SetRansom).prisonPlayerId
        val setRansom = msg.setNum
        val rt = setRansom(session, prisonPlayerId, setRansom)
        session.sendMsg(MsgType. SetRansom_1354, rt)
    }

    private fun setRansom(session: PlayerSession, prisonPlayerId: Long, setRansom: Long): SetRansomRt {
        val rt = SetRansomRt.newBuilder()

        rt.rt = ResultCode.SUCCESS.code
        rt.prisonPlayerId = prisonPlayerId
        rt.setNum = setRansom

        if (setRansom < pcs.basicProtoCache.ransomMin || setRansom > pcs.basicProtoCache.ransomMax) {
            rt.rt = ResultCode.RANSOM_ERROR.code
            return rt.build()
        }

        val areaCache = session.areaCache
        val prisonInfo =
            areaCache.prisonCache.findPrisonsByPlayerIdAndPrisonPlayerId(session.playerId, prisonPlayerId)
        if (prisonInfo == null) {
            rt.rt = ResultCode.NO_FIND_PRISON_PLAYER.code
            return rt.build()
        }

        val prisonPlayer = areaCache.playerCache.findPlayerById(prisonPlayerId)
        if (prisonPlayer == null) {
            rt.rt = ResultCode.NO_PLAYER.code
            return rt.build()
        }

        val player = session.player
        val castle = areaCache.castleCache.findCastleById(player.focusCastleId)
        if (castle == null) {
            rt.rt = ResultCode.CASTLE_DONT_EXISTED.code
            return rt.build()
        }

        prisonInfo.ransom = setRansom

        // 推送给关人者监狱信息变化
        val winSession = fetchOnlinePlayerSession(areaCache, session.playerId)
        if (winSession != null) {
            val notifier = createPlayerPrisonChangeNotifier(areaCache, ADD_PRISON, prisonInfo)
            if (notifier != null) {
                notifier.notice(winSession)
            }
        }

        // 推送给被关者监狱信息变化
        val loseSession = fetchOnlinePlayerSession(areaCache, prisonPlayerId)
        if (loseSession != null) {
            val notifierToLoser = createPlayerRansomChangeNotifier(prisonInfo,castle,player)
            if (notifierToLoser != null) {
                notifierToLoser.notice(loseSession)
            }
        }

        noticeCellUpdate(areaCache, castle.x, castle.y)

        // 邮件通知
        val mailInfoToPrison = MailInfo(
            TEXT_READ_LAN,
            TITLE_RANSOM,
            LinkedList(),
            CONTENT_RANSOM,
            LinkedList(asList(prisonPlayer.name,"$setRansom"))
        )
        sendMail(areaCache, prisonPlayerId, mailInfoToPrison)

        return rt.build()
    }
}


