//修改联盟公告
package com.point18.slg2d.home.module.alliance

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.constg.ACTION_ALLIANCE_FLAG
import com.point18.slg2d.common.constg.FLAG_TYPE_COLOR
import com.point18.slg2d.common.constg.FLAG_TYPE_EFFECT
import com.point18.slg2d.common.constg.FLAG_TYPE_STYLE
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.CostResWithoutNoticeResult
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.HomePlayer
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import com.point18.slg2d.home.module.HomeClientMsgDeal
import pb4client.AllianceSetFlag
import pb4client.AllianceSetFlagRt
import pb4server.Home2PublicAskResp
import pb4server.SetAllianceFlagAskReq
import java.util.Arrays.asList

//设置联盟旗帜 837
class SetAllianceFlagDeal(
    private val resHelper: ResHelper = ResHelper()
) : HomeClientMsgDeal, HomeHelperPlus1<HomePlayerDC>(HomePlayerDC::class.java, asList(resHelper)) {

    override fun dealPlayerReq(session: PlayerActor, msg: MessageLite) {
        prepare(session) { homePlayerDC: HomePlayerDC ->
            val color = (msg as AllianceSetFlag).color
            val style = msg.style
            val effect = msg.effect
            val rt = this.changeFlag(session, color, style, effect, homePlayerDC)
            if (rt != null) {
                session.sendMsg(MsgType.AllianceSetFlag_837, rt)
            }
        }
    }

    private fun changeFlag(session: PlayerActor, color: Int, style: Int, effect: Int, homePlayerDC: HomePlayerDC): AllianceSetFlagRt? {
        val rt = AllianceSetFlagRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code

        val action = ACTION_ALLIANCE_FLAG
        val player = homePlayerDC.player

        //验证参数是否有效
        if (!pcs.allianceFlagCache.existFlag(FLAG_TYPE_COLOR, color) ||
            !pcs.allianceFlagCache.existFlag(FLAG_TYPE_STYLE, style) ||
            !pcs.allianceFlagCache.existFlag(FLAG_TYPE_EFFECT, effect)) {
            rt.rt = (ResultCode.ALLIANCE_FLAG_NOT_EXIST.code)
            return rt.build()
        }

        // 资源检测
        val resVo = pcs.basicProtoCache.allianceFlagCost
        val ok = resHelper.checkRes(session, resVo)
        if (!ok) {
            rt.rt = (ResultCode.LESS_RESOUCE.code)
            return rt.build()
        }

        // 扣除资源
        val costResWithoutNoticeResult = resHelper.costResWithoutNotice(session, action, player, resVo)

        setFlag(session, player.allianceId, player, color, style, effect, resHelper, costResWithoutNoticeResult)

        return null
    }

    // 修改联盟旗帜
    private fun setFlag(
        session: PlayerActor,
        allianceId: Long,
        player: HomePlayer,
        color: Int,
        style: Int,
        effect: Int,
        resHelper: ResHelper,
        costResWithoutNoticeResult: CostResWithoutNoticeResult
    ) {
        val setAllianceFlagAskReq = SetAllianceFlagAskReq.newBuilder()
        setAllianceFlagAskReq.color = color
        setAllianceFlagAskReq.style = style
        setAllianceFlagAskReq.effect = effect

        session.createACS<Home2PublicAskResp>().ask(
            session.publicShardProxy,
            session.fillHome2PublicAskMsgHeader(allianceId) {
                it.setAllianceFlagAskReq = setAllianceFlagAskReq.build()
            },
            Home2PublicAskResp::class.java
        ).whenCompleteKt { resp, askErr ->
            val rt = AllianceSetFlagRt.newBuilder()
            rt.rt = ResultCode.SUCCESS.code
            val resVo = pcs.basicProtoCache.allianceFlagCost
            if (askErr != null || resp == null) {
                // todo 重试...
                resHelper.addResWithoutNotice(session, ACTION_ALLIANCE_FLAG, player, resVo)

                rt.rt = ResultCode.PROCESS_ERROR_RPC_EXCEPTION.code
                session.sendMsg(MsgType.AllianceSetFlag_837, rt.build())
                return@whenCompleteKt
            }

            val setAllianceFlagAskRt = resp.setAllianceFlagAskRt
            if (setAllianceFlagAskRt.rt != ResultCode.SUCCESS.code) {
                // todo 重试????? 由于W服的数据已经异常了,重试100次这个都是错的怎么办...
                resHelper.addResWithoutNotice(session, ACTION_ALLIANCE_FLAG, player, resVo)

                rt.rt = setAllianceFlagAskRt.rt
                session.sendMsg(MsgType.AllianceSetFlag_837, rt.build())
                return@whenCompleteKt
            }

            // 消耗资源？
            costResWithoutNoticeResult.noticeCostRes(session, player)

            session.sendMsg(MsgType.AllianceSetFlag_837, rt.build())
        }
    }
}




