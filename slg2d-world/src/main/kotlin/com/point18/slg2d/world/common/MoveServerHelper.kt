package com.point18.slg2d.world.common

import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.commonfunc.processConfig
import com.point18.slg2d.common.constg.IN_MOVE_SERVER
import com.point18.slg2d.common.constg.KING_PROTO_ID
import com.point18.slg2d.common.constg.NO_MOVE_SERVER
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.pc.resVoToResString
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.common.syncdomain.MoveServerEntitysReq
import com.point18.slg2d.common.syncdomain.MoveServerEntitysRt
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.Player
import com.point18.slg2d.world.area4data.fetchOnlinePlayerSession
import com.point18.slg2d.world.area4data.getTotalPower
import com.point18.slg2d.world.msgnotice.createMoveServerResultNotifier
import pb4server.*

class MoveServerHelper {
    /*
     迁服
        1.检测当前世界数据
        2.前往W2服确认坐标点,锁点
        3.把玩家当前的W1,H,P服都改成迁服状态,H服扣除资源
        4.暂时移除W1的自动化数据(心跳,优先级队列),前往W2创建数据,进行推送
        5.删除W1老数据,设置H,P状态为正常状态
      */
    fun moveServer(areaCache: AreaCache, player: Player, targetWorldId: Long, x: Int, y: Int) {
        val playerId = player.id

        var moveServerRt = ResultCode.SUCCESS.code
        // todo 1.检测当前世界数据

        // 前往目标世界服验证数据
        val reqMsg = CheckMoveServerXyReq.newBuilder()
        reqMsg.x = x
        reqMsg.y = y

        val myTarget = areaCache.targetCache.findMyTargetByPlayerId(player.id)
        if (myTarget == null) {
            return
        }

        reqMsg.power = myTarget.getTotalPower()

        areaCache.worldActor.createACS<World2WorldAskResp>()
            .ask(
                areaCache.worldActor.worldShardRegion,
                areaCache.fillW2WAskMsgHeader(targetWorldId, playerId) {
                    it.setCheckMoveServerXyReq(reqMsg)
                },
                World2WorldAskResp::class.java
            )
            .whenCompleteKt { rt, err ->
                try {
                    when {
                        err != null -> {
                            moveServerRt = ResultCode.ASK_ERROR1.code
                            return@whenCompleteKt
                        }
                        rt == null -> {
                            moveServerRt = ResultCode.ASK_ERROR2.code
                            return@whenCompleteKt
                        }
                        else -> {
                            val r = rt.checkMoveServerXyRt
                            if (r.rt != ResultCode.SUCCESS.code) {
                                // 锁点失败,返回失败消息
                                moveServerRt = r.rt
                                return@whenCompleteKt
                            } else {
                                // 锁点成功,把玩家当前的W1,H,P服都改成迁服状态,H服扣除资源
                                // 先设置home服的 顺便把钱给扣了
                                println("锁点成功,把玩家当前的W1,H,P服都改成迁服状态,H服扣除资源")
                                setMoveServerStateAfterLockXy(areaCache, player, targetWorldId, x, y, r.rank)
                            }
                        }
                    }

                } catch (e: Exception) {
                    normalLog.error("CheckMoveServerXyReq Error!", e)
                    moveServerRt = ResultCode.ASK_ERROR3.code
                }
            }
    }

    // 成功锁定坐标之后设置多个服务器的玩家状态
    private fun setMoveServerStateAfterLockXy(
        areaCache: AreaCache,
        player: Player,
        targetWorldId: Long,
        x: Int,
        y: Int,
        rank: Int
    ) {
        val playerId = player.id
        var moveServerRt = ResultCode.SUCCESS.code
        val setHomeMoveServerStateReq = SetHomeMoveServerStateReq.newBuilder()
        setHomeMoveServerStateReq.state = IN_MOVE_SERVER
        setHomeMoveServerStateReq.cost =
                resVoToResString(pcs.emigrationCostProtoCache.findEmigrationCostProtoByRank(rank))
        areaCache.worldActor.createACS<World2HomeAskResp>()
            .ask(
                areaCache.worldActor.homeShardRegion,
                areaCache.fillW2HAskMsgHeader(playerId) {
                    it.setSetHomeMoveServerStateReq(setHomeMoveServerStateReq)
                },
                World2HomeAskResp::class.java
            )
            .whenCompleteKt { hrt, herr ->
                try {
                    when {
                        herr != null -> {
                            val tellWorldUnLock = MoveServerCellUnLockTell.newBuilder()
                            tellWorldUnLock.x = x
                            tellWorldUnLock.y = y
                            areaCache.tellWorld(areaCache.fillW2WTellMsgHeader(targetWorldId, playerId) {
                                it.setMoveServerCellUnLockTell(tellWorldUnLock)
                            })
                            moveServerRt = ResultCode.ASK_ERROR1.code
                            return@whenCompleteKt
                        }
                        hrt == null -> {
                            val tellWorldUnLock = MoveServerCellUnLockTell.newBuilder()
                            tellWorldUnLock.x = x
                            tellWorldUnLock.y = y
                            areaCache.tellWorld(areaCache.fillW2WTellMsgHeader(targetWorldId, playerId) {
                                it.setMoveServerCellUnLockTell(tellWorldUnLock)
                            })
                            moveServerRt = ResultCode.ASK_ERROR2.code
                            return@whenCompleteKt
                        }
                        else -> {
                            val r = hrt.setHomeMoveServerStateRt
                            println("成功锁定坐标之后设置HHHH服务器的玩家状态:${r.rt}")
                            if (r.rt != ResultCode.SUCCESS.code) {
                                // 扣除资源失败,改状态失败
                                // 得知具体错误原因 属正常情况,遂去目标服解锁地块 一个tell就可以了
                                val tellWorldUnLock = MoveServerCellUnLockTell.newBuilder()
                                tellWorldUnLock.x = x
                                tellWorldUnLock.y = y
                                areaCache.tellWorld(areaCache.fillW2WTellMsgHeader(targetWorldId, playerId) {
                                    it.setMoveServerCellUnLockTell(tellWorldUnLock)
                                })

                                moveServerRt = r.rt
                                return@whenCompleteKt
                            } else {
                                // home服修改成功,资源扣除成功,如果玩家拥有联盟,前往公共服修改状态
                                if (player.allianceId != 0L) {
                                    stateMoveAfterSetAllianceState(areaCache, player, playerId, x, y, targetWorldId)
                                } else {
                                    // 其他服务器修改状态成功,修改当前世界状态,准备进行数据迁移
                                    stateMoveAfterSetState(areaCache, player, targetWorldId, x, y)
                                }
                            }
                        }
                    }

                } catch (e: Exception) {
                    normalLog.error("SetHomeMoveServerStateReq Error!", e)
                    moveServerRt = ResultCode.ASK_ERROR3.code
                }
            }
    }

    // home服修改成功,资源扣除成功,如果玩家拥有联盟,前往公共服修改状态
    private fun stateMoveAfterSetAllianceState(
        areaCache: AreaCache,
        player: Player,
        playerId: Long,
        x: Int,
        y: Int,
        targetWorldId: Long
    ) {
        val setPublicMoveServerStateReq = SetPublicMoveServerStateReq.newBuilder()
        setPublicMoveServerStateReq.state = IN_MOVE_SERVER
        areaCache.worldActor.createACS<World2PublicAskResp>()
            .ask(
                areaCache.worldActor.publicShardRegion,
                areaCache.fillW2PAskMsgHeader(player.allianceId, playerId) {
                    it.setSetPublicMoveServerStateReq(setPublicMoveServerStateReq)
                },
                World2PublicAskResp::class.java
            )
            .whenCompleteKt { prt, pErr ->
                try {
                    when {
                        pErr != null -> {
                            val tellWorldUnLock = MoveServerCellUnLockTell.newBuilder()
                            tellWorldUnLock.x = x
                            tellWorldUnLock.y = y
                            areaCache.tellWorld(
                                areaCache.fillW2WTellMsgHeader(
                                    targetWorldId,
                                    playerId
                                ) {
                                    it.setMoveServerCellUnLockTell(tellWorldUnLock)
                                })
                            return@whenCompleteKt
                        }
                        prt == null -> {
                            val tellWorldUnLock = MoveServerCellUnLockTell.newBuilder()
                            tellWorldUnLock.x = x
                            tellWorldUnLock.y = y
                            areaCache.tellWorld(
                                areaCache.fillW2WTellMsgHeader(
                                    targetWorldId,
                                    playerId
                                ) {
                                    it.setMoveServerCellUnLockTell(tellWorldUnLock)
                                })
                            return@whenCompleteKt
                        }
                        else -> {
                            val r = prt.setPublicMoveServerStateRt
                            if (r.rt != ResultCode.SUCCESS.code) {
                                // 改状态失败
                                // 得知具体错误原因 属正常情况,遂去目标服解锁地块 一个tell就可以了
                                val tellWorldUnLock = MoveServerCellUnLockTell.newBuilder()
                                tellWorldUnLock.x = x
                                tellWorldUnLock.y = y
                                areaCache.tellWorld(
                                    areaCache.fillW2WTellMsgHeader(
                                        targetWorldId,
                                        playerId
                                    ) {
                                        it.setMoveServerCellUnLockTell(tellWorldUnLock)
                                    })

                                // todo 之前扣除的home资源需要补吗?
                                return@whenCompleteKt
                            } else {
                                // 其他服务器修改状态成功,修改当前世界状态,准备进行数据迁移
                                stateMoveAfterSetState(
                                    areaCache,
                                    player,
                                    targetWorldId,
                                    x,
                                    y
                                )
                            }
                        }
                    }

                } catch (e: Exception) {
                    normalLog.error("SetPublicMoveServerStateReq Error!", e)
                }
            }
    }

    // 这些缓存需要直接迁移到目标服务器
    private fun stateMoveAfterSetState(areaCache: AreaCache, player: Player, targetWorldId: Long, x: Int, y: Int) {
        var moveServerRt = ResultCode.SUCCESS.code
        val playerId = player.id
        val (reqMsg, rt) = initMoveServerInfo(areaCache, targetWorldId, player, x, y)
        if (rt != ResultCode.SUCCESS.code || reqMsg == null) {
            // 有错误
            val tellWorldUnLock = MoveServerCellUnLockTell.newBuilder()
            tellWorldUnLock.x = x
            tellWorldUnLock.y = y
            areaCache.tellWorld(areaCache.fillW2WTellMsgHeader(targetWorldId, playerId) {
                it.setMoveServerCellUnLockTell(tellWorldUnLock)
            })
            return
        }

        // 开始迁移,停止home的一些优先级队列与心跳,如果迁服失败的话,会重新补进去的
        areaCache.homeHeartCache.stopHomeHeartForMoveServer(player.id)
        areaCache.barracksCache.stopBarracksForMoveServer(player.id)
        areaCache.buffCache.stopBuffForMoveServer(player.id)
        areaCache.castleCache.stopCastleForMoveServer(player.id)
        areaCache.heroCache.stopHeroForMoveServer(player.id)
        areaCache.playerCache.stopPlayerForMoveServer(player.id)

        //删除视野
        val session = fetchOnlinePlayerSession(areaCache, player.id)
        if (session != null) {
            areaCache.mapCellWatcherCache.removeFromMapCellWatch(session.channelActor)
            areaCache.worldActor.context.unwatch(session.channelActor)
        }

        areaCache.worldActor.createACS<MoveServerEntitysRt>()
            .ask(
                areaCache.worldActor.worldShardRegion,
                reqMsg,
                MoveServerEntitysRt::class.java
            )
            .whenCompleteKt { acsRt, err ->
                try {
                    when {
                        err != null -> {
                            val tellWorldUnLock = MoveServerCellUnLockTell.newBuilder()
                            tellWorldUnLock.x = x
                            tellWorldUnLock.y = y
                            areaCache.tellWorld(areaCache.fillW2WTellMsgHeader(targetWorldId, playerId) {
                                it.setMoveServerCellUnLockTell(tellWorldUnLock)
                            })
                            moveServerRt = ResultCode.ASK_ERROR1.code

                            // 心跳复苏
                            reviveHeart(areaCache, player)
                            return@whenCompleteKt
                        }
                        acsRt == null -> {
                            val tellWorldUnLock = MoveServerCellUnLockTell.newBuilder()
                            tellWorldUnLock.x = x
                            tellWorldUnLock.y = y
                            areaCache.tellWorld(areaCache.fillW2WTellMsgHeader(targetWorldId, playerId) {
                                it.setMoveServerCellUnLockTell(tellWorldUnLock)
                            })
                            moveServerRt = ResultCode.ASK_ERROR2.code

                            // 心跳复苏
                            reviveHeart(areaCache, player)
                            return@whenCompleteKt
                        }
                        else -> {
                            println("数据转移:${acsRt.rt}")
                            if (acsRt.rt != ResultCode.SUCCESS.code) {
                                // 数据转移失败 , 这边不需要去世界解锁 因为消息就是世界返回过来的 并且是明确的错误,在那边直接就可以解锁
                                moveServerRt = acsRt.rt

                                // 心跳复苏
                                reviveHeart(areaCache, player)
                                return@whenCompleteKt
                            } else {
                                // 数据转移成功,清除这边的原始数据,并且把服务器状态全部解锁
                                // 清除数据
                                moveServerInfoToJson(areaCache, player, targetWorldId, x, y, acsRt.newAreaNo)
                            }
                        }
                    }

                } catch (e: Exception) {
                    normalLog.error("MoveServerEntityListReq Error!", e)
                    moveServerRt = ResultCode.ASK_ERROR3.code

                    // 心跳复苏
                    reviveHeart(areaCache, player)
                }
            }
    }

    // 在转移数据的时候异步失败了,把老的世界服的心跳重新启动
    private fun reviveHeart(areaCache: AreaCache, player: Player) {
        // 开始迁移,停止home的一些优先级队列与心跳,如果迁服失败的话,会重新补进去的
        areaCache.homeHeartCache.reviveHomeHeartForMoveServer(player.id)
        areaCache.barracksCache.reviveBarracksForMoveServer(player.id)
        areaCache.buffCache.reviveBuffForMoveServer(player.id)
        areaCache.castleCache.reviveCastleForMoveServer(player.id)
        areaCache.heroCache.reviveHeroForMoveServer(player.id)
        areaCache.playerCache.revivePlayerForMoveServer(player.id)

        // todo 视野加回来

    }

    // 初始化迁服数据类
    data class InitMoveServerInfoReturn(var msg: MoveServerEntitysReq?, var rt: Int)

    private fun initMoveServerInfo(
        areaCache: AreaCache,
        targetWorldId: Long,
        player: Player,
        x: Int,
        y: Int
    ): InitMoveServerInfoReturn {
        var rt = ResultCode.SUCCESS.code
        val playerId = player.id

        val playerEntity = areaCache.playerCache.findPlayerEntityById(playerId)
        val infoByHomeEntity = areaCache.infoByHomeCache.findInfoByHomeEntityByPlayerId(playerId)
        val instanceEntity = areaCache.instanceCache.findInstanceEntity(playerId)
        val myTargetEntity = areaCache.targetCache.findMyTargetEntityByPlayerId(playerId)
        val playerSettingEntity = areaCache.playerSettingCache.findPlayerSettingEntityByPlayerId(playerId)
        if (infoByHomeEntity == null ||
            instanceEntity == null ||
            myTargetEntity == null ||
            playerEntity == null ||
            playerSettingEntity == null
        ) {
            rt = ResultCode.MOVE_SERVER_ENTITY_ERROR.code
            return InitMoveServerInfoReturn(null, rt)
        }
        val reqMsg = MoveServerEntitysReq(
            x, y,
            areaCache.achievementCache.findAchievementEntitiesByPlayerId(playerId),
            areaCache.armyPlanCache.findMyArmyPlanEntityList(playerId),
            areaCache.barracksCache.findBarrackEntityListByPlayerId(playerId),
            areaCache.buffCache.findBuffEntityListByPlayerId(playerId),
            areaCache.castleCache.findCastleEntityListFromPlayerId(playerId),
            areaCache.heroCache.findHeroListByPlayerId(playerId),
            areaCache.fogCache.findFogEntityByPlayerId(playerId),
            areaCache.homeHeartCache.findAllHeartEntityListByPlayerId(playerId),
            areaCache.myAllianceGiftCache.findMyAllianceGiftEntityListByPlayerId(playerId),
            infoByHomeEntity,
            instanceEntity,
            areaCache.instanceDropCache.findInstanceDropEntityListByPlayId(playerId),
            myTargetEntity,
            playerEntity,
            areaCache.prisonCache.findPrisonEntityListByPlayerId(playerId),
            areaCache.taskCache.findAllTaskEntityListByPlayerId(playerId),
            areaCache.playerActivityCache.findPlayerActivityEntityListByPlayerId(playerId),
            playerSettingEntity
        )
        reqMsg.worldId = targetWorldId
        reqMsg.playerId = player.id

        return InitMoveServerInfoReturn(reqMsg, rt)

    }

    // 迁服结束 这些是需要清除的缓存
    private fun moveServerInfoToJson(
        areaCache: AreaCache,
        player: Player,
        targetWorldId: Long,
        x: Int,
        y: Int,
        newAreaNo: Int
    ) {
        areaCache.activityBossCache.delAllActivityBossByPlayerId(player.id) //  打活动魔物的记录 删
        areaCache.belongCellCache.delAllBelongByPlayerId(player.id) // 玩家属地 删

        val transportReq = areaCache.transportRequestCache.findTransportRequestByPlayerId(
            player.id
        ) // PubCacheTransportRequest 运输请求 删
        if (transportReq != null) {
            areaCache.transportRequestCache.deleteTransportRequest(transportReq)
        }

        // todo 如果玩家有行军或者预警 直接走正常逻辑处理完 下面的几个大地图行军缓存就不应该有了
//    areaCache.activityBossCache.(areaCache, player.id) // PubCacheMass 集结表 删
//    areaCache.activityBossCache.(areaCache, player.id) // PubCacheWalk 删
//    areaCache.activityBossCache.(areaCache, player.id) // PubCacheWalkForce删
//    areaCache.activityBossCache.(areaCache, player.id) // PubCacheWalkForceGroup删
//    areaCache.activityBossCache.(areaCache, player.id) // PubCacheWarn删

        // todo 藏兵也要召回来

        // 奇观官职清除
        val bigWonder = areaCache.wonderCache.findBigWonder()
        if (bigWonder != null) {
            val officeProtoId = bigWonder.officeMap[player.id]
            if (officeProtoId != null && officeProtoId != KING_PROTO_ID) {
                bigWonder.officeMap.remove(player.id)
            }
        }

        areaCache.chatCache.deleteChatInfoByPlayerId(player.id) // PubCacheChat 世界聊天 删

        // 删
        areaCache.homeHeartCache.clearHomeHeartForMoveServer(
            player.id
        )//    var pubCacheHomeHeart: LinkedList<HomeHeartEntity>,
        areaCache.achievementCache.clearAchievementForMoveServer(
            player.id
        )//    var pubCacheAchievement: LinkedList<AchievementEntity>,
        areaCache.armyPlanCache.clearArmyPlanForMoveServer(
            player.id
        )//    var pubCacheArmyPlan: LinkedList<ArmyPlanEntity>,
        areaCache.barracksCache.clearBarracksForMoveServer(
            player.id
        )//    var pubCacheBarracks: LinkedList<BarracksEntity>,
        areaCache.buffCache.clearBuffForMoveServer(player.id)//    var pubCacheBuff: LinkedList<BuffEntity>,
        areaCache.castleCache.clearCastleForMoveServer(
            player.id
        )//    var pubCacheCastle: LinkedList<CastleEntity>,
        areaCache.heroCache.clearHeroForMoveServer(player.id)//    var pubCacheHero: LinkedList<WorldHeroEntity>,
        areaCache.fogCache.clearFogForMoveServer(player.id)//    var pubCacheFog: LinkedList<FogEntity>,
        areaCache.myAllianceGiftCache.clearMyAllianceGiftForMoveServer(
            player.id
        )//    var pubCacheIdMyAllianceGift: LinkedList<MyAllianceGiftEntity>,
        areaCache.infoByHomeCache.clearArmyPlanForMoveServer(
            player.id
        )//    var pubCacheInfoByHome: InfoByHomeEntity,
        areaCache.instanceCache.clearInstanceForMoveServer(player.id)//    var pubCacheInstance: InstanceEntity,
        areaCache.instanceDropCache.clearInstanceDropForMoveServer(
            player.id
        )//    var pubCacheInstanceDrop: LinkedList<InstanceDropEntity>,
        areaCache.targetCache.clearMyTargetForMoveServer(player.id)//    var pubCacheMyTarget: MyTargetEntity,
        areaCache.playerCache.clearPlayerForMoveServer(player.id)//    var pubCachePlayer: PlayerEntity,
        areaCache.prisonCache.clearPrisonForMoveServer(
            player.id
        )//    var pubCachePrison: LinkedList<PrisonEntity>,
        areaCache.taskCache.clearTaskForMoveServer(player.id)//    var pubCacheTask: LinkedList<TaskEntity>,
        areaCache.playerActivityCache.clearPlayerActivityForMoveServer(
            player.id
        )//    var pubCachePlayerActivity: LinkedList<PlayerActivityEntity>
        areaCache.playerSettingCache.clearPlayerSettingForMoveServer(player.id)

        // 刷我移走的坐标,让其他人看见我走了
        noticeCellUpdate(areaCache, x, y)

        // 成功迁移数据之后设置多个服务器的玩家状态
        setMoveServerStateAfterMoveInfo(areaCache, player, targetWorldId, x, y, newAreaNo)

        // 移除老服的playerSession
        val session = fetchOnlinePlayerSession(areaCache, player.id)
        if (session != null) {
            // 迁服完毕 在删除老session之前发消息到客户端通知客户端,并且通过gate服注册新区的playerSession
            val targetWorldConfig = processConfig.findSpecAreaConfig(targetWorldId)
            if (targetWorldConfig != null) {
                createMoveServerResultNotifier(
                    targetWorldConfig.areaNo,
                    targetWorldConfig.pltAreaNo,
                    targetWorldConfig.areaName,
                    x,
                    y
                ).notice(session)
            } else {
                println("找不到目标区的配置:$targetWorldId")
            }
            areaCache.sessionCache.playerSessionOffline(session)
        }
    }

    // 成功迁移数据之后设置多个服务器的玩家状态
    private fun setMoveServerStateAfterMoveInfo(
        areaCache: AreaCache,
        player: Player,
        targetWorldId: Long,
        x: Int,
        y: Int,
        newAreaNo: Int
    ) {
        val playerId = player.id
        var moveServerRt = ResultCode.SUCCESS.code
        val setHomeMoveServerStateReq = SetHomeMoveServerStateReq.newBuilder()
        setHomeMoveServerStateReq.state = NO_MOVE_SERVER
        setHomeMoveServerStateReq.x = x
        setHomeMoveServerStateReq.y = y
        setHomeMoveServerStateReq.newWorldId = targetWorldId
        setHomeMoveServerStateReq.areaNo = newAreaNo
        areaCache.worldActor.createACS<World2HomeAskResp>()
            .ask(
                areaCache.worldActor.homeShardRegion,
                areaCache.fillW2HAskMsgHeader(playerId) {
                    it.setSetHomeMoveServerStateReq(setHomeMoveServerStateReq)
                },
                World2HomeAskResp::class.java
            )
            .whenCompleteKt { hrt, herr ->
                try {
                    when {
                        herr != null -> {
                            moveServerRt = ResultCode.ASK_ERROR1.code
                            return@whenCompleteKt
                        }
                        hrt == null -> {
                            moveServerRt = ResultCode.ASK_ERROR2.code
                            return@whenCompleteKt
                        }
                        else -> {
                            val r = hrt.setHomeMoveServerStateRt
                            println("结束:${r.rt}")
                            if (r.rt != ResultCode.SUCCESS.code) {
                                moveServerRt = r.rt
                                return@whenCompleteKt
                            } else {
                                // home服修改成功,如果玩家拥有联盟,前往公共服修改状态
                                if (player.allianceId != 0L) {
                                    changeAllianeAfterMoveSucc(
                                        areaCache,
                                        player,
                                        playerId,
                                        targetWorldId,
                                        x,
                                        y,
                                        newAreaNo
                                    )
                                } else {
                                }
                            }
                        }
                    }

                } catch (e: Exception) {
                    normalLog.error("SetHomeMoveServerStateReq Error!", e)
                    moveServerRt = ResultCode.ASK_ERROR3.code
                }
            }
    }

    // home服修改成功,如果玩家拥有联盟,前往公共服修改状态
    private fun changeAllianeAfterMoveSucc(
        areaCache: AreaCache,
        player: Player,
        playerId: Long,
        targetWorldId: Long,
        x: Int,
        y: Int,
        newAreaNo: Int
    ) {
        val setPublicMoveServerStateReq = SetPublicMoveServerStateReq.newBuilder()
        setPublicMoveServerStateReq.state = NO_MOVE_SERVER
        setPublicMoveServerStateReq.x = x
        setPublicMoveServerStateReq.y = y
        setPublicMoveServerStateReq.newWorldId = targetWorldId
        setPublicMoveServerStateReq.areaNo = newAreaNo

        areaCache.worldActor.createACS<World2PublicAskResp>()
            .ask(
                areaCache.worldActor.publicShardRegion,
                areaCache.fillW2PAskMsgHeader(player.allianceId, playerId) {
                    it.setSetPublicMoveServerStateReq(setPublicMoveServerStateReq)
                },
                World2PublicAskResp::class.java
            )
            .whenCompleteKt { prt, pErr ->
                try {
                    when {
                        pErr != null -> {
                            return@whenCompleteKt
                        }
                        prt == null -> {
                            return@whenCompleteKt
                        }
                        else -> {
                            val r = prt.setPublicMoveServerStateRt
                            if (r.rt != ResultCode.SUCCESS.code) {
                                // 改状态失败
                                return@whenCompleteKt
                            } else {
                            }
                        }
                    }

                } catch (e: Exception) {
                    normalLog.error("SetPublicMoveServerStateReq Error!", e)
                }
            }
    }
}
