package com.point18.slg2d.home.module.casino

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.isAfterGameRefTime
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.CasinoDC
import com.point18.slg2d.home.dc.CasinoPlayerDC
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus3
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.GetCasinosInfoRt
import java.text.SimpleDateFormat
import java.util.*

// 赌场信息
class GetCasinoInfoDeal : HomeClientMsgDeal, HomeHelperPlus3<HomePlayerDC, CasinoDC, CasinoPlayerDC>(
    HomePlayerDC::class.java, CasinoDC::class.java, CasinoPlayerDC::class.java
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, casinoDC: CasinoDC, casinoPlayerDC: CasinoPlayerDC ->
            val queryCasinoInfoRt = queryCasinoInfo(session, casinoDC, casinoPlayerDC, homePlayerDC)
            session.sendMsg(MsgType.GetCasinosInfo_1577, queryCasinoInfoRt)
        }
    }

    private fun queryCasinoInfo(
        session: PlayerActor, casinoDC: CasinoDC, casinoPlayerDC: CasinoPlayerDC, homePlayerDC: HomePlayerDC
    ): GetCasinosInfoRt {
        val rt = GetCasinosInfoRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.blessCount = 0
        rt.blessType = 0
        rt.atkNormalBossCount = 0
        rt.atkHighBossCount = 0
        rt.isAtkHighBoss = 0
        rt.isAtkNormalBoss = 0
        rt.freeCount = 0

        // 刷新的时间点
        val refreshTimeString = pcs.basicProtoCache.palaceTimeStr

        // 结束时间
        val calendar = Calendar.getInstance()
        val nowTime = Date().time
        val format = SimpleDateFormat("yyyy-MM-dd")
        val nextDayString = format.format(nowTime + 24 * 60 * 60 * 1000) + refreshTimeString
        val nextDayTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(nextDayString).time
        val days = 8 - calendar.get(Calendar.DAY_OF_WEEK)
        val finishDate = nextDayTime + days * 24 * 60 * 60 * 1000

        // 查找地宫类型
        val weekId = calendar.get(Calendar.WEEK_OF_YEAR)
        val palaceId = pcs.palaceRefreshProtoCache.palaceRefreshProtoMap[weekId]
        if (palaceId == null) {
            rt.rt = ResultCode.CASINO_ERROR.code
            return rt.build()
        }

        var casinoIdTemp = palaceId
        var casinosFinishDateTemp = (finishDate / 1000).toInt()
        val casinoPlayerInfo = casinoPlayerDC.findMyCasinoPlayerById()
        // 是否有奖池
        if (casinoPlayerInfo == null) {
            casinoPlayerDC.createCasinoPlayer(palaceId, finishDate)
        } else {
            if (casinoPlayerInfo.finishDate == finishDate) {
                casinoIdTemp = casinoPlayerInfo.palaceId
                casinosFinishDateTemp = (casinoPlayerInfo.finishDate / 1000).toInt()
            } else {
                casinoPlayerDC.delCasinoPlayerInfo(casinoPlayerInfo)
                casinoDC.delCasinosById()
                casinoPlayerDC.createCasinoPlayer(palaceId, finishDate)
            }
        }
        rt.casinosId = casinoIdTemp
        rt.casinosFinishDate = casinosFinishDateTemp

        // 每日免费次数
        val freeCount = pcs.basicProtoCache.palaceFree
        var blessType = 0   // 祝福类型
        var blessCount = 0   // 祝福次数
        var freeCountTemp = 0  // 免费次数
        var atkNormalBossCountTemp = 0  // 攻击普通boss次数
        var isAtkNormalBossTemp = 0  //  是否在攻击普通boss
        var atkHighBossCountTemp = 0 //  攻击精英boss的次数
        var isAtkHighBossTemp = 0 // 是否在攻击精英boss

        // 普通模式
        val normalBoss = casinoDC.findAllCasino(NORMAL, palaceId)
        if (normalBoss == null) {
            casinoDC.createCasino(
                palaceId, NORMAL,
                0, 0,
                0, 0,
                0, 0,
                0, 1,
                0, nowTime
            )
        } else {
            blessCount = normalBoss.useBlessCount
            if (normalBoss.isBless == 1) {
                blessType = NORMAL_BLESS
            }
            // 刷新免费时间,免费次数
            // 今日是否刷新次数
            val isRefresh = isAfterGameRefTime(normalBoss.openFreeTime)
            if(isRefresh){
                normalBoss.freeCount = freeCount
                normalBoss.openFreeTime = nowTime
            }
            freeCountTemp = normalBoss.freeCount
            atkNormalBossCountTemp = normalBoss.atkCount
            isAtkNormalBossTemp = normalBoss.isAtkBoss
        }

        // 精英模式开启的时间
        val player = homePlayerDC.player
        val openCasinoTime = player.openCasinoTime
        if (openCasinoTime.toInt() != 0) {
            // 精英模式
            val heightBoss = casinoDC.findAllCasino(HIGH, palaceId)
            if (heightBoss == null) {
                casinoDC.createCasino(
                    palaceId, HIGH,
                    0, 0,
                    0, 0,
                    0, 0,
                    0, 1,
                    0, openCasinoTime
                )
            } else {
                if (blessType == 0) {
                    if (heightBoss.isBless == 1) {
                        blessType = HIGHT_BLESS
                    } else {
                        blessType = NO_BLESS
                    }
                    blessCount = heightBoss.useBlessCount
                }
                // 刷新免费时间,免费次数
                val isRefresh = isAfterGameRefTime(heightBoss.openFreeTime)
                if(isRefresh){
                    heightBoss.freeCount = freeCount
                    heightBoss.openFreeTime = nowTime
                }
                freeCountTemp = heightBoss.freeCount
                atkHighBossCountTemp = heightBoss.atkCount
                isAtkHighBossTemp = heightBoss.isAtkBoss
            }
        } else {
            isAtkHighBossTemp = UNLOCKED
        }

        rt.blessType = blessType
        rt.blessCount = blessCount
        rt.freeCount = freeCountTemp
        rt.atkNormalBossCount = atkNormalBossCountTemp
        rt.isAtkNormalBoss = isAtkNormalBossTemp
        rt.atkHighBossCount = atkHighBossCountTemp
        rt.isAtkHighBoss = isAtkHighBossTemp

        return rt.build()
    }
}