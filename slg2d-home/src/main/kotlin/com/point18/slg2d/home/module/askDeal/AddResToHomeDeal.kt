package com.point18.slg2d.home.module.askDeal

import com.point18.slg2d.common.pc.resStringToResVoList
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.helper.HomeHelperPlus1
import pb4server.AddResToHomeAskReq
import pb4server.AddResToHomeAskRt
import pb4server.World2HomeAskReq
import pb4server.World2HomeAskResp
import java.util.Arrays.asList

class AddResToHomeDeal(
    private val resHelper: ResHelper = ResHelper()
) : W2HAsk, HomeHelperPlus1<HomePlayerDC>(
    HomePlayerDC::class.java,
    asList(resHelper)
) {

    override fun dealWorldAsk(session: PlayerActor, req: World2HomeAskReq, resp: World2HomeAskResp.Builder) {
        val internalMessage = req.addResToHomeAskReq
        val rt = addRes2Home(session, internalMessage)
        resp.setAddResToHomeAskRt(rt)
    }

    private fun addRes2Home(session: PlayerActor, req: AddResToHomeAskReq): AddResToHomeAskRt.Builder {
        return prepare(session) { homePlayerDC: HomePlayerDC ->
            val rt = AddResToHomeAskRt.newBuilder()
            val actionId = req.actionId
            val addResStr = req.addRes
            val addRes = resStringToResVoList(addResStr)
            if (addRes == null) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                return@prepare rt
            }

            val player = homePlayerDC.player

            val addRst = resHelper.addRes(session, actionId, player, addRes)
            if (!addRst) {
                rt.rt = ResultCode.PARAMETER_ERROR.code
                return@prepare rt
            }

            rt.rt = ResultCode.SUCCESS.code
            return@prepare rt
        }
    }
}
