package com.point18.slg2d.world.module.boss

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.common.getResearchEffectValue
import com.point18.slg2d.world.module.ReqDealEntered

//设置狩猎配置
class SetHunterBossConfigDeal : ReqDealEntered() {

    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val setConfigMsg = msg as pb4client.SetHunterConfig
        val autoHunter = setConfigMsg.autoHunter
        val autoUseEnergy = setConfigMsg.autoUseEnergy
        val bossType = setConfigMsg.bossType
        val rt = dealSetHunterConfig(session, autoHunter, autoUseEnergy, bossType)
        session.sendMsg(MsgType.SetHunterConfig_1491, rt.build())
    }

    private fun dealSetHunterConfig(
        session: PlayerSession,
        autoHunter: Int,
        autoUseEnergy: Int,
        bossType: Int
    ): pb4client.SetHunterConfigRt.Builder {
        val rtBuilder = pb4client.SetHunterConfigRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code
        rtBuilder.autoHunter = autoHunter
        rtBuilder.autoUseEnergy = autoUseEnergy

        val player = session.player
        val areaCache = session.areaCache

        //判断vip
        val researchEffectType = when (bossType) {
            COMMON_BOSS_TYPE -> AUTO_ATK_MONSTER
            ACTIVITY_BOSS_TYPE -> AUTO_ATK_FOUR_DRAGON
            else -> {
                rtBuilder.rt = ResultCode.PARAMETER_ERROR.code
                return rtBuilder
            }
        }
        if (intToBool(autoHunter)) {
            val researchVal = getResearchEffectValue(areaCache, NO_FILTER, player, researchEffectType)
            if (researchVal == 0) {
                //无权限
                rtBuilder.rt = ResultCode.UI_CONDITION_VIPLV_ERROR.code
                return rtBuilder
            }
        }

        player.autoHunter = autoHunter
        player.autoUseEnergy = autoUseEnergy

        //若是自动猎杀，则必定是自动使用行动力
        if (intToBool(autoHunter)) {
            player.autoUseEnergy = autoHunter
        }
        return rtBuilder
    }
}