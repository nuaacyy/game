package com.point18.slg2d.world.module.instance

import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getRandInt
import com.point18.slg2d.common.constg.ACTION_INSTANCE_WIPE
import com.point18.slg2d.common.constg.INSTANCE_OPEN
import com.point18.slg2d.common.constg.PVE_FIGHT_WIN
import com.point18.slg2d.common.constg.RES_PROPS
import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.common.pc.ResVo
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.pc.resVoToResString
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.area4data.PlayerSession
import com.point18.slg2d.world.common.addResToHome
import com.point18.slg2d.world.common.checkStrength
import com.point18.slg2d.world.common.costStrength
import com.point18.slg2d.world.event.InstanceFightEvent
import com.point18.slg2d.world.event.InstanceWin
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.module.uiCondition.uiConditionCheck
import com.point18.slg2d.world.wpm
import pb4client.InstanceWipeRt
import java.util.*

// 推图扫荡
class InstanceFWipe : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val floorId = (msg as pb4client.InstanceWipe).floorId
        val wipeNum = msg.wipeNum
        val rt = instanceWipe(session, floorId, wipeNum)
        session.sendMsg(MsgType.InstanceWipe_1472, rt)
    }

    private fun instanceWipe(session: PlayerSession, floorId: Int, wipeNum: Int): (InstanceWipeRt) {
        val rt = InstanceWipeRt.newBuilder()
        rt.rt = ResultCode.SUCCESS.code
        rt.floorId = floorId
        rt.wipeNum = wipeNum

        if (wipeNum <= 0) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        // 验证一些模版基础数据
        val player = session.player

        // 功能开启
        val uiConditionRt = uiConditionCheck(session.areaCache, player, INSTANCE_OPEN)
        if (uiConditionRt != ResultCode.SUCCESS.code) {
            rt.rt = uiConditionRt
            return rt.build()
        }

        val instanceProto = pcs.instanceProtoCache.protoMap[floorId]
        if (instanceProto == null) {
            rt.rt = ResultCode.NO_PROTO.code
            return rt.build()
        }

        val instanceVo = session.areaCache.instanceCache.findInstance(session.playerId)
        if (instanceVo == null) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        if (floorId >= instanceVo.nowFight && instanceVo.nowFight != 0) {
            rt.rt = ResultCode.PARAMETER_ERROR.code
            return rt.build()
        }

        // 起码得有扫荡一次的体力 才可以开始扫荡
        var checkCost = checkStrength(session.areaCache, player, instanceProto.winStrength)
        if (checkCost != ResultCode.SUCCESS) {
            rt.rt = checkCost.code
            return rt.build()
        }

        val rewardsRt = LinkedList<String>()
        for (i in 0..(wipeNum - 1)) {
            checkCost = checkStrength(session.areaCache, player, instanceProto.winStrength)
            if (checkCost != ResultCode.SUCCESS) {
                rt.wipeNum = i
                rt.addAllInstanceWipeRewards(rewardsRt)
                return rt.build()
            }
            costStrength(session.areaCache, player, instanceProto.winStrength)
            val winReward = LinkedList<ResVo>() // 战斗胜利奖励
            // 获取随机奖励
            for (rList in instanceProto.randomRewardMap) {
                val r = getRandInt(10000)
                // 查看该掉落物品有没有控制掉落
                val dropExtra = pcs.instanceDropExtraProtoCache.instanceDropExtraProtoMap[rList.dropPropsId]
                if (dropExtra == null) {
                    // 没有控制
                    if (r <= rList.dropOdds) {
                        // 随到了
                        winReward.add(
                            ResVo(RES_PROPS, rList.dropPropsId.toLong(), rList.dropNum.toLong())
                        )
                    }
                } else {
                    // 监控了,进行保底逻辑
                    val instanceDropVo =
                        session.areaCache.instanceDropCache.findInstanceDropByPropsId(
                            session.playerId,
                            rList.dropPropsId
                        )
                    if (r <= rList.dropOdds) {
                        // 随到了  但是有可能已经达到峰值了,不一定给全
                        if (instanceDropVo.nowCheckTime == dropExtra.times - 1) {
                            if (instanceDropVo.nowGet < dropExtra.number) {
                                winReward.add(
                                    ResVo(
                                        RES_PROPS,
                                        rList.dropPropsId.toLong(),
                                        (dropExtra.number - instanceDropVo.nowGet).toLong()
                                    )
                                )
                            }
                            // 然后重置状态
                            instanceDropVo.nowCheckTime = 0
                            instanceDropVo.nowGet = 0
                        } else {
                            // 检测下正常给会不会溢出
                            var canGetNum = rList.dropNum
                            if (instanceDropVo.nowGet + rList.dropNum > dropExtra.number) {
                                canGetNum = dropExtra.number - instanceDropVo.nowGet
                            }
                            winReward.add(
                                ResVo(RES_PROPS, rList.dropPropsId.toLong(), canGetNum.toLong())
                            )
                            // 还没到峰值
                            instanceDropVo.nowCheckTime += 1
                            instanceDropVo.nowGet += canGetNum
                        }
                    } else {
                        // 没随到  但是有可能已经达到峰值了,必须给
                        if (instanceDropVo.nowCheckTime == dropExtra.times - 1) {
                            if (instanceDropVo.nowGet < dropExtra.number) {
                                winReward.add(
                                    ResVo(
                                        RES_PROPS,
                                        rList.dropPropsId.toLong(),
                                        (dropExtra.number - instanceDropVo.nowGet).toLong()
                                    )
                                )
                            }
                            // 然后重置状态
                            instanceDropVo.nowCheckTime = 0
                            instanceDropVo.nowGet = 0
                        } else {
                            // 还没到峰值
                            instanceDropVo.nowCheckTime += 1
                        }
                    }
                }
            }
            winReward += instanceProto.heroExpPropsMap
            addResToHome(session.areaCache, ACTION_INSTANCE_WIPE, player.id, winReward)

            wpm.es.fire(
                session.areaCache,
                session.playerId,
                PVE_FIGHT_WIN,
                InstanceWin(floorId)
            )

            rewardsRt.add(resVoToResString(winReward))
        }
        rt.addAllInstanceWipeRewards(rewardsRt)

        val myTarget = session.areaCache.targetCache.findMyTargetByPlayerId(player.id)
        if (myTarget != null) {
            myTarget.instanceFightNum += rt.wipeNum
        }

        wpm.es.fire(
            session.areaCache,
            session.playerId,
            com.point18.slg2d.common.constg.INSTANCE_FIGHT,
            InstanceFightEvent(rt.wipeNum, 0, floorId)
        )

        return rt.build()
    }
}
