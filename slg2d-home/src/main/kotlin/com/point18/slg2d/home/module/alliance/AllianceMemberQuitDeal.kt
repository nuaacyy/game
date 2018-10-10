package com.point18.slg2d.home.module.alliance

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.ALLIANCE_POSITION_BOSS
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.allianceMemberQuit
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus5
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.AllianceQuitRt
import java.util.*

//玩家主动退出联盟 811
class AllianceMemberQuitDeal : HomeClientMsgDeal, HomeHelperPlus5<HomePlayerDC, VipDC, SkinDC, FriendDC, InnerCityDC>(
    HomePlayerDC::class.java, VipDC::class.java, SkinDC::class.java, FriendDC::class.java, InnerCityDC::class.java
) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC, vipDC: VipDC, skinDC: SkinDC,
                             friendDC: FriendDC, innerCityDC: InnerCityDC ->
            val rt = this.quit(session, homePlayerDC, innerCityDC, vipDC, skinDC, friendDC)
            if (rt != null) {
                session.sendMsg(MsgType.AllianceQuit_811, rt)
            }
        }
    }

    /********************************************* 811 玩家主动退出联盟 *********************************************/
    private fun quit(
        session: PlayerActor, homePlayerDC: HomePlayerDC, innerCityDC: InnerCityDC,
        vipDC: VipDC, skinDC: SkinDC, friendDC: FriendDC
    ): AllianceQuitRt? {
        val rt = AllianceQuitRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val player = homePlayerDC.player
        val oldPos = LinkedList<Int>()
        for ((p, _) in player.alliancePosMap) {
            oldPos.add(p)
        }

        // 验证玩家是否是盟主：盟主必须先禅让，才可以退出联盟
        if (player.playerIsHavePos(ALLIANCE_POSITION_BOSS)) {
            rt.rt = (ResultCode.ALLIANCE_QUIT_FORBIDDEN.code)
            return rt.build()
        }

        val isRook = 0
        allianceMemberQuit(session, player.allianceId, player, isRook, innerCityDC, vipDC, skinDC, friendDC)

        return null
    }

}

