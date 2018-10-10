package com.point18.slg2d.home.module.guildhouse

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.GuildHouseDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.ChangeGuildHouse
import pb4client.ChangeGuildHouseRt

// 修改后宅属性
class ChangeGuildHouseDeal : HomeClientMsgDeal, HomeHelperPlus1<GuildHouseDC>(GuildHouseDC::class.java) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { guildHouseDC: GuildHouseDC ->
            val rtBuilder = this.handleChangeGuildHouse(
                session, msg as ChangeGuildHouse, guildHouseDC
            )
            session.sendMsg(MsgType.ChangeGuildHouse_1536, rtBuilder.build())
        }
    }

    private fun handleChangeGuildHouse(
        session: PlayerActor, msg: ChangeGuildHouse, guildHouseDC: GuildHouseDC
    ): ChangeGuildHouseRt.Builder {
        val rtBuilder = ChangeGuildHouseRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        val name = msg.name

        // 获取后宅缓存
        val guildHouse = guildHouseDC.guildHouse
        guildHouse.name = name

        return rtBuilder
    }
}