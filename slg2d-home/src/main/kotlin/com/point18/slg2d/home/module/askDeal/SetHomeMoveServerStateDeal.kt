package com.point18.slg2d.home.module.askDeal

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.pc.resStringToResVoList
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.common.ResHelper
import com.point18.slg2d.home.dc.HomePlayerDC
import com.point18.slg2d.home.dc.HomeSyncDC
import com.point18.slg2d.home.helper.HomeHelperPlus2
import pb4server.SetHomeMoveServerStateReq
import pb4server.SetHomeMoveServerStateRt
import pb4server.World2HomeAskReq
import pb4server.World2HomeAskResp

class SetHomeMoveServerStateDeal(
    private val resHelper: ResHelper = ResHelper()
) : W2HAsk, HomeHelperPlus2<HomePlayerDC, HomeSyncDC>(
    HomePlayerDC::class.java, HomeSyncDC::class.java
) {

    override fun dealWorldAsk(session: PlayerActor, req: World2HomeAskReq, resp: World2HomeAskResp.Builder) {
        val internalMessage = req.setHomeMoveServerStateReq
        val rt = setMoveServerState(session, internalMessage)
        resp.setSetHomeMoveServerStateRt(rt)
    }

    private fun setMoveServerState(
        session: PlayerActor,
        req: SetHomeMoveServerStateReq
    ): SetHomeMoveServerStateRt.Builder {
        return prepare(session) { homePlayerDC: HomePlayerDC, homeSyncDC: HomeSyncDC ->
            val rtBuilder = SetHomeMoveServerStateRt.newBuilder()
            rtBuilder.rt = ResultCode.SUCCESS.code

            val player = homePlayerDC.player
            val syncData = homeSyncDC.syncData

            if (req.state == IN_MOVE_SERVER) {
                //  检测资源扣除资源
                val cost = resStringToResVoList(req.cost)
                if (cost == null) {
                    rtBuilder.rt = ResultCode.RES_ERROR.code
                    return@prepare rtBuilder
                }
                val checkCost = resHelper.checkRes(session, cost)
                if (!checkCost) {
                    rtBuilder.rt = ResultCode.LESS_RESOUCE.code
                    return@prepare rtBuilder
                }

                // 扣除消耗
                resHelper.costRes(
                    session,
                    ACTION_MOVE_SERVER,
                    player,
                    cost
                )

                // 关闭世界聊天的注册
                session.unsubscribeChannel(worldChannelOf(session.worldId))

                println("老的世界聊天关闭")
            }

            // 修改状态
            player.inMoveServer = req.state
            if (req.state == NO_MOVE_SERVER) {
                // 修改数据
                var isSuccess = false
                if (req.newWorldId != player.worldId) {
                    isSuccess = true
                }

                // 迁服成功
                if (isSuccess) {
                    player.areaNo = req.areaNo
                    player.worldId = req.newWorldId

                    // 重置jjc数据
                    syncData.jjcRank = 0
                    syncData.scoreTime = getNowTime()

                    // 清空官职
                    val toClean = arrayListOf<Long>()
                    for ((worldId, officeId) in syncData.officeMap) {
                        if (officeId != KING_PROTO_ID) {
                            toClean.add(worldId)
                        }
                    }
                    for (worldId in toClean) {
                        syncData.officeMap.remove(worldId)
                    }

                    // 检测一下资源是否超出上限
                    val resMaxInfo = pcs.basicProtoCache.transferResourcesLimitMap[player.castleLv]
                    if (resMaxInfo != null) {
                        for ((resType, resNum) in resMaxInfo) {
                            if (resType == RES_COIN && player.coin > resNum) {
                                player.coin = resNum
                            } else if (resType == RES_FOOD && player.food > resNum) {
                                player.food = resNum
                            } else if (resType == RES_WOOD && player.wood > resNum) {
                                player.wood = resNum
                            } else if (resType == RES_STONE && player.stone > resNum) {
                                player.stone = resNum
                            } else if (resType == RES_IRON && player.iron > resNum) {
                                player.iron = resNum
                            }
                        }
                    }
                }

                // 订阅新的世界聊天的注册
                session.subscribeChannel(worldChannelOf(session.worldId))
                println("新的世界聊天注册")
            }

            return@prepare rtBuilder
        }
    }
}