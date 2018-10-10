package com.point18.slg2d.world.module.walk.fight

import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.PlayerSession
import com.google.protobuf.MessageLite
import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.commonfunc.getRandIntAtArea
import com.point18.slg2d.world.module.ReqDealEntered
import com.point18.slg2d.world.module.fightdomain.FightData
import com.point18.slg2d.world.module.fightdomain.HeroData
import com.point18.slg2d.world.module.heroBattle.fightLogic.FightLogic
import com.point18.slg2d.world.module.heroBattle.fightLogic.newEntityManager
import com.point18.slg2d.common.netmsg.MsgType
import xyz.ariane.util.lzWarn
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.FIGHT_RESULT_ING
import com.point18.slg2d.common.constg.FIGHT_RESULT_LOSE
import pb4client.*
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.world.common.convertHeroFightData
import com.point18.slg2d.world.common.fightData2Builder
import com.point18.slg2d.world.wpm
import pb4battle.BattleMsg
import pb4battle.BattleServiceGrpc
import java.util.*

//英雄战测试战斗
class GetHeroFightReportDeal : ReqDealEntered() {
    override fun dealPlayReq(session: PlayerSession, msg: MessageLite) {
        val reqMsg = msg as GetHeroFightReport
        val rt = getHeroFightReport(session, reqMsg)
        if (rt == null) {
            return
        }
        session.sendMsg(MsgType.GetHeroFightReport_105, rt)
    }

    private fun getHeroFightReport(session: PlayerSession, reqMsg: GetHeroFightReport): (GetHeroFightReportRt?) {

        val rt = GetHeroFightReportRt.newBuilder()
        rt.rt = check(reqMsg)
        if (rt.rt != ResultCode.SUCCESS.code) {
            return rt.build()
        }
        doFight(session, reqMsg)
        return null
    }

    //检验消息数据
    fun check(reqMsg: GetHeroFightReport): Int {
        for (hero in reqMsg.atkHerosList) {
            val unitProto = pcs.unitBaseCache.protoMap[hero.protoId]
            if (unitProto == null) {
                normalLog.lzWarn { "找不到英雄配置:${hero.protoId}" }
                return ResultCode.NO_PROTO.code
            }
            if (hero.pos < 1 || hero.pos > 9) {
                normalLog.lzWarn { "${hero.protoId}的英雄位置错误:${hero.pos}" }
                return ResultCode.PARAMETER_ERROR.code
            }
        }

        if (reqMsg.defHerosInOnceFightList.isEmpty()) {
            return ResultCode.PARAMETER_ERROR.code
        }

        for (defHeroList in reqMsg.defHerosInOnceFightList) {
            for (hero in defHeroList.herosList) {
                val unitProto = pcs.unitBaseCache.protoMap[hero.protoId]
                if (unitProto == null) {
                    normalLog.lzWarn { "找不到英雄配置:${hero.protoId}" }
                    return ResultCode.NO_PROTO.code
                }
                if (hero.pos < 1 || hero.pos > 9) {
                    normalLog.lzWarn { "${hero.protoId}的英雄位置错误:${hero.pos}" }
                    return ResultCode.PARAMETER_ERROR.code
                }
            }
        }
        return ResultCode.SUCCESS.code
    }

    fun doFight(session: PlayerSession, reqMsg: GetHeroFightReport) {
        val areaCache = session.areaCache
        val ranSeed = getRandIntAtArea(areaCache.randSeed, Int.MAX_VALUE)
        areaCache.worldActor.createACS<pb4battle.BattleMsg.DoHeroFightResp>().computeKt {
            val channel = wpm.battleServiceMgr.selectBattleService()
            if (channel == null) {
                val rtBuilder = pb4battle.BattleMsg.DoHeroFightResp.newBuilder()
                rtBuilder.rt = ResultCode.PROCESS_ERROR_BATTLE_SERVICE_ERROR.code
                return@computeKt rtBuilder.build()
            }

            val requestBuilder = BattleMsg.DoHeroFightReq.newBuilder()
            requestBuilder.battleTime = pcs.basicProtoCache.monsterBattleTime
            requestBuilder.ranSeed = ranSeed

            val atkFightData =
                FightData(
                    0,
                    0,
                    LinkedList(),
                    LinkedList(),
                    hashMapOf(),
                    hashMapOf(),
                    hashMapOf(),
                    hashMapOf()
                )
            for (hero in reqMsg.atkHerosList) {
                val skillList = LinkedList<Int>()
                skillList += hero.uniqueSkillListList
                skillList += hero.activeSkillListList
                skillList += hero.passiveSkillListList
                atkFightData.heroList.add(
                    HeroData(
                        0,
                        hero.protoId,
                        hero.lv,
                        hero.star,
                        hero.rank,
                        0,
                        hero.pos,
                        skillList
                    )
                )
            }
            requestBuilder.setAtkFightData(fightData2Builder(areaCache, atkFightData))

            for (heroList in reqMsg.defHerosInOnceFightList) {
                val defFightData =
                    FightData(
                        0,
                        0,
                        LinkedList(),
                        LinkedList(),
                        hashMapOf(),
                        hashMapOf(),
                        hashMapOf(),
                        hashMapOf()
                    )
                for (hero in heroList.herosList) {
                    val skillList = LinkedList<Int>()
                    skillList += hero.uniqueSkillListList
                    skillList += hero.activeSkillListList
                    skillList += hero.passiveSkillListList
                    defFightData.heroList.add(
                        HeroData(
                            0,
                            hero.protoId,
                            hero.lv,
                            hero.star,
                            hero.rank,
                            0,
                            hero.pos,
                            skillList
                        )
                    )
                }

                requestBuilder.addDefFightDatas(fightData2Builder(areaCache, defFightData))
            }

            val stub = BattleServiceGrpc.newBlockingStub(channel)
            stub.doHeroFight(requestBuilder.build())
        }.whenCompleteKt { heroFightResult, err ->
            if (err != null || heroFightResult == null) {
                val rtBuilder = pb4client.GetHeroFightReportRt.newBuilder()
                rtBuilder.rt = ResultCode.ASK_ERROR3.code
                session.sendMsg(MsgType.GetHeroFightReport_105, rtBuilder.build())
                return@whenCompleteKt
            }
            if (heroFightResult.rt != ResultCode.SUCCESS.code) {
                val rtBuilder = pb4client.GetHeroFightReportRt.newBuilder()
                rtBuilder.rt = heroFightResult.rt
                session.sendMsg(MsgType.GetHeroFightReport_105, rtBuilder.build())
                return@whenCompleteKt
            }
            val rtBuilder = pb4client.GetHeroFightReportRt.newBuilder()
            rtBuilder.rt = ResultCode.SUCCESS.code
            heroFightResult.fightInfoList.forEach {
                val builder = convertHeroFightData(it)
                rtBuilder.addReport(builder)
            }
            session.sendMsg(MsgType.GetHeroFightReport_105, rtBuilder.build())
        }
    }

    //进行战斗
    fun test(areaCache: AreaCache, reqMsg: GetHeroFightReport): pb4client.GetHeroFightReportRt.Builder {
        val dt = getNowTime()

        val atkFightData =
            FightData(
                0,
                0,
                LinkedList(),
                LinkedList(),
                hashMapOf(),
                hashMapOf(),
                hashMapOf(),
                hashMapOf()
            )
        for (hero in reqMsg.atkHerosList) {
            val skillList = LinkedList<Int>()
            skillList += hero.uniqueSkillListList
            skillList += hero.activeSkillListList
            skillList += hero.passiveSkillListList
            atkFightData.heroList.add(
                HeroData(
                    0,
                    hero.protoId,
                    hero.lv,
                    hero.star,
                    hero.rank,
                    0,
                    hero.pos,
                    skillList
                )
            )
        }

        val rtBuilder = pb4client.GetHeroFightReportRt.newBuilder()
        rtBuilder.rt = ResultCode.SUCCESS.code

        for (heroList in reqMsg.defHerosInOnceFightList) {
            val defFightData =
                FightData(
                    0,
                    0,
                    LinkedList(),
                    LinkedList(),
                    hashMapOf(),
                    hashMapOf(),
                    hashMapOf(),
                    hashMapOf()
                )
            for (hero in heroList.herosList) {
                val skillList = LinkedList<Int>()
                skillList += hero.uniqueSkillListList
                skillList += hero.activeSkillListList
                skillList += hero.passiveSkillListList
                defFightData.heroList.add(
                    HeroData(
                        0,
                        hero.protoId,
                        hero.lv,
                        hero.star,
                        hero.rank,
                        0,
                        hero.pos,
                        skillList
                    )
                )
            }

            val manager = newEntityManager(areaCache, pcs.basicProtoCache.battleTime)
            val logic = FightLogic(areaCache, manager, FIGHT_RESULT_ING, atkFightData, defFightData)
            logic.initLogic()

            val reportBuilder = HeroFightReport.newBuilder()
            for (entity in logic.manager.entityList) {
                val entityBuilder = FightEntity.newBuilder()
                entityBuilder.id = entity.id.toLong()

                for ((k, v) in entity.intPropertyMap) {
                    val propertyBuilder = IntProperty.newBuilder()
                    propertyBuilder.propertyType = k
                    propertyBuilder.propertyValue = v.toLong()
                    entityBuilder.addIntPropertys(propertyBuilder)
                }

                for ((k, v) in entity.floatPropertyMap) {
                    val propertyBuilder = FloatProperty.newBuilder()
                    propertyBuilder.propertyType = k
                    propertyBuilder.propertyValue = v
                    entityBuilder.addFloatPropertys(propertyBuilder)
                }

                reportBuilder.addEntitys(entityBuilder)
            }

            logic.beforeFight()
            logic.fight()
            logic.afterFight()

            reportBuilder.fightResult = logic.fightResult
            logic.manager.requestRecord.forEach {
                if (it is FightRecord.Builder) {
                    reportBuilder.addRecords(it)
                }
            }

            rtBuilder.addReport(reportBuilder)

            if (logic.fightResult == FIGHT_RESULT_LOSE) {
                break
            }
        }

        println("战斗耗时:${getNowTime() - dt}毫秒")

        return rtBuilder
    }
}
