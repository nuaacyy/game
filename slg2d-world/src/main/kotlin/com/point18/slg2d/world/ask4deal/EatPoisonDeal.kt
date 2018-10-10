package com.point18.slg2d.world.ask4deal

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.Home2WorldAskDealBase
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.common.noticeCellUpdate
import com.point18.slg2d.world.common.sendMail
import com.point18.slg2d.world.common.syncData2Home
import com.point18.slg2d.world.msgnotice.createPlayerPrisonChangeNotifier
import com.point18.slg2d.world.msgnotice.createValueChgNotifier
import pb4server.EatPoisonNumAskRt
import pb4server.Home2WorldAskReq
import pb4server.Home2WorldAskResp
import java.util.*
import java.util.Arrays.asList

class EatPoisonDeal : Home2WorldAskDealBase() {

    override fun dealHomeAsk(areaCache: AreaCache, req: Home2WorldAskReq, resp: Home2WorldAskResp.Builder) {
        val receiveNum = req.eatPoisonNumAskReq.num
        val rt = EatPoisonNumAskRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        
        val player = areaCache.playerCache.findPlayerById(req.playerId)
        if (player == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            resp.setEatPoisonNumAskRt(rt)
            return
        }
        val hero = areaCache.heroCache.findHeroById(player.id, player.mainHeroId)
        if (hero == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            resp.setEatPoisonNumAskRt(rt)
            return
        }

        if (hero.mainHeroState != PRISON_AWAITING_EXECUTION) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
                resp.setEatPoisonNumAskRt(rt)
            return
        }

        val poisonProto = pcs.equipCache.equipProtoMap[pcs.basicProtoCache.fastDead]
        if (poisonProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
                resp.setEatPoisonNumAskRt(rt)
            return
        }

        val poisonTime = (poisonProto.extend1.toInt())

        // 判断时间是否允许服毒
        val nowTime = getNowTime()
        if ((hero.mainHeroStateEndTime - nowTime) < poisonTime * 1000) {
            rt.rt = ResultCode.EAR_POISON_TIME_ERROR.code
                resp.setEatPoisonNumAskRt(rt)
            return
        }

        // 维护关人者监狱缓存
        val delPrisonInfo =
            areaCache.prisonCache.findPrisonsByPlayerIdAndPrisonPlayerId(hero.mainHeroPrisonPlayerId, hero.playerId)
        if (delPrisonInfo == null) {
            rt.rt = ResultCode.NO_FIND_PRISON_PLAYER.code
            resp.setEatPoisonNumAskRt(rt)
            return
        }

        // 判断玩家的实力,决定消耗
        val myPowerRank = areaCache.playerCache.findPowerRankByPlayerId(req.playerId)
        if (myPowerRank == 0) {
            rt.rt = ResultCode.RANK_ERROR.code
            resp.setEatPoisonNumAskRt(rt)
            return
        }

        val winPlayer = areaCache.playerCache.findPlayerById(hero.mainHeroPrisonPlayerId)
        if (winPlayer == null) {
            rt.rt = ResultCode.NO_PLAYER.code
            resp.setEatPoisonNumAskRt(rt)
            return
        }

        val castle = areaCache.castleCache.findCastleById(winPlayer.focusCastleId)
        if (castle == null) {
            rt.rt = ResultCode.CASTLE_DONT_EXISTED.code
            resp.setEatPoisonNumAskRt(rt)
            return
        }

        // 验证和发过来的数量是不是一样
        val eatPoisonNum = pcs.fastDeadPropsProtoCache.findEventRankRewardByRank(myPowerRank)
        if (eatPoisonNum != receiveNum){
            rt.rt = ResultCode.PARAMETER_ERROR.code
            resp.setEatPoisonNumAskRt(rt)
            return
        }


        hero.mainHeroState = PRISON_EAT_MUSHROOM
        hero.mainHeroStateStartTime = nowTime
        areaCache.heroCache.updateMainHeroStateEndTime(
            hero,
            nowTime + 1000 * poisonTime
        )

        //同步数据至home服
        syncData2Home(
            areaCache,
            delPrisonInfo.playerId,
            MaxLvInPrison,
            areaCache.prisonCache.findMaxLvInPrison(delPrisonInfo.playerId).toString()
        )

        // 推送给被关方领主信息变化
        val loseSession = fetchOnlinePlayerSession(areaCache, hero.playerId)
        if (loseSession != null) {
            val valueChgNotifier = createValueChgNotifier()
            valueChgNotifier.append(hero.id, MAIN_HERO_STATE, hero.mainHeroState.toLong())
            valueChgNotifier.append(
                hero.id,
                MAIN_HERO_STATE_START_TIME,
                hero.mainHeroStateStartTime / 1000
            )
            valueChgNotifier.append(
                hero.id,
                MAIN_HERO_STATE_OVER_TIME,
                hero.mainHeroStateEndTime / 1000
            )
            valueChgNotifier.notice(loseSession)
        }

        // 推送给关人者监狱信息变化
        val winSession = fetchOnlinePlayerSession(areaCache, hero.mainHeroPrisonPlayerId)
        if (winSession != null) {
            val notifier = createPlayerPrisonChangeNotifier(areaCache, ADD_PRISON, delPrisonInfo)
            if (notifier != null) {
                notifier.notice(winSession)
            }
        }

        // 发送app通知
        areaCache.pushAppNotice(
            hero.mainHeroPrisonPlayerId,
            PRISON_EAT_POISON_SETTING,
            0,
            player.name
        )

        // 发邮件
        val mailInfoToPrison = MailInfo(
            TEXT_READ_LAN,
            TITLE_POISON_USE,
            LinkedList(),
            CONTENT_POISON_USE,
            LinkedList(asList(player.name, (((hero.mainHeroStateEndTime - nowTime) / 1000).toString()+"秒")))
        )
        sendMail(areaCache, hero.mainHeroPrisonPlayerId, mailInfoToPrison)

        noticeCellUpdate(areaCache, castle.x, castle.y)

        resp.setEatPoisonNumAskRt(rt)
    }
}