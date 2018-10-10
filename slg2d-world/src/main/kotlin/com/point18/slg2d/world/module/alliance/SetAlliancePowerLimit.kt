package com.point18.slg2d.world.module.alliance

import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.common.netmsg.MsgType
import pb4client.AllianceSetPowerLimit
import pb4client.AllianceSetPowerLimitRt
import com.point18.slg2d.common.resultcode.ResultCode

//设置允许加入联盟的最低战斗力 807
class DealSetAlliancePowerLimit : ReqDealEntered() {

    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val power = (msg as AllianceSetPowerLimit).powerLimit
        val canAddPower = msg.canAddPower
        val rt = this.setPowerLimit(session, power, canAddPower)
        if (rt != null) {
            session.sendMsg(MsgType.SetPowerLimit_807, rt)
        }
    }

    /********************************************* 807 设置允许加入联盟的最低战斗力 *********************************************/
    private fun setPowerLimit(session: PlayerSession, power: Long, canAddPower: Long): (AllianceSetPowerLimitRt?) {
        val rt = AllianceSetPowerLimitRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.powerLimit = power
        rt.canAddPower = canAddPower

        val player = session.player
        if (power < 0) {
            rt.rt = (ResultCode.ALLIANCE_ARGS_ERROR.code)
            return rt.build()
        }

        com.point18.slg2d.world.common.setAlliancePowerLimit(session, player.allianceId, power, canAddPower)

        return null
    }

}

/********************************************* 807 设置允许加入联盟的最低战斗力 *********************************************/