package com.point18.slg2d.robot.robot.heroFight

import com.point18.slg2d.common.netmsg.MsgType
import xyz.ariane.util.RandomUtils
import xyz.ariane.util.RandomUtils.randomBoolean
import xyz.ariane.util.lzDebug
import xyz.ariane.util.lzWarn
import com.point18.slg2d.common.commonfunc.normalLog
import pb4client.GetHeroFightReport
import pb4client.GetHeroFightReportRt
import pb4client.HeroFightHeroInfo
import pb4client.HeroInfoInOnceFight
import com.point18.slg2d.common.pc.UnitBaseProto
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.robot.robot.AiLeafBase
import com.point18.slg2d.robot.robotData.*
import com.point18.slg2d.robot.robotruntime.RobotPropChg
import java.util.*

class AiGetHeroFightReport : AiLeafBase() {
    override fun actionDesc(): String {
        return "AiGetHeroFightReport - 获取英雄战战报记录（测试用）"
    }

    override fun update(robot: Robot): ActionResult {
        val msg = GetHeroFightReport.newBuilder()

        // 取出英雄模板
        val heroProtoList: List<UnitBaseProto>
        val tmpProtoList = ArrayList<UnitBaseProto>()
        for (proto in pcs.unitBaseCache.protos) {
            if (proto.npcType == 1) {
                tmpProtoList.add(proto)
            }
        }
        heroProtoList = tmpProtoList

        // lv，rank，star等模板数据
        val maxLv = pcs.heroLevelUpCache.maxLv
        val rankProtoMap = pcs.heroRankProtoCache.heroRankProtoCache
        val starProtoMap = pcs.heroStarProtoCache.heroStarProtoCache

        // 随机位置
        val posArray = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        val tmpPosList = posArray.toMutableList()
        tmpPosList.shuffle()

        // 布置1~5个攻击方英雄
        var isFirst = true
        for (i in 0..4) {
            if (isFirst || randomBoolean()) {
                val infoBuilder = HeroFightHeroInfo.newBuilder()

                // 从英雄模板中随一个英雄
                val index = RandomUtils.nextInt(heroProtoList.size)
                val heroProto = heroProtoList[index]

                // 获取最高rank
                val rankMap = rankProtoMap[heroProto.id]
                val maxRank = if (rankMap == null) {
                    1
                } else {
                    rankMap.size
                }
                val randRank = RandomUtils.nextInt(maxRank) + 1

                // 获取最高star
                val starMap = starProtoMap[heroProto.id]
                val maxStar = if (starMap == null) {
                    1
                } else {
                    starMap.size
                }

                infoBuilder.protoId = heroProto.id
                infoBuilder.lv = RandomUtils.nextInt(maxLv) + 1
                infoBuilder.rank = randRank
                infoBuilder.star = RandomUtils.nextInt(maxStar) + 1
                infoBuilder.pos = tmpPosList[i]

                // 技能填充
                var maxSkillLv = checkSkillLv(1, heroProto.id, randRank)
                if (maxSkillLv != 0) {
                    val trueSkillLv = RandomUtils.nextInt(maxSkillLv)
                    val skillId = heroProto.skill1 + trueSkillLv
                    infoBuilder.addUniqueSkillList(skillId)
                }

                maxSkillLv = checkSkillLv(2, heroProto.id, randRank)
                if (maxSkillLv != 0) {
                    val trueSkillLv = RandomUtils.nextInt(maxSkillLv)
                    val skillId = heroProto.skill2 + trueSkillLv
                    infoBuilder.addActiveSkillList(skillId)
                }

                maxSkillLv = checkSkillLv(3, heroProto.id, randRank)
                if (maxSkillLv != 0) {
                    val trueSkillLv = RandomUtils.nextInt(maxSkillLv)
                    val skillId = heroProto.skill3 + trueSkillLv
                    infoBuilder.addActiveSkillList(skillId)
                }

                maxSkillLv = checkSkillLv(4, heroProto.id, randRank)
                if (maxSkillLv != 0) {
                    val trueSkillLv = RandomUtils.nextInt(maxSkillLv)
                    val skillId = heroProto.skill3 + trueSkillLv
                    infoBuilder.addPassiveSkillList(skillId)
                }

                msg.addAtkHeros(infoBuilder.build())
            }

            if (isFirst) {
                isFirst = false
            }
        }

        // 布置loop轮1~5个防守方英雄
        val loop = RandomUtils.nextInt(5)
        for (j in 0..loop) {
            val groupBuilder = HeroInfoInOnceFight.newBuilder()
            isFirst = true
            tmpPosList.shuffle()
            for (i in 0..4) {
                if (isFirst || randomBoolean()) {
                    val infoBuilder = HeroFightHeroInfo.newBuilder()

                    // 从英雄模板中随一个英雄
                    val index = RandomUtils.nextInt(heroProtoList.size)
                    val heroProto = heroProtoList[index]

                    // 获取最高rank
                    val rankMap = rankProtoMap[heroProto.id]
                    val maxRank = if (rankMap == null) {
                        1
                    } else {
                        rankMap.size
                    }
                    val randRank = RandomUtils.nextInt(maxRank) + 1

                    // 获取最高star
                    val starMap = starProtoMap[heroProto.id]
                    val maxStar = if (starMap == null) {
                        1
                    } else {
                        starMap.size
                    }

                    infoBuilder.protoId = heroProto.id
                    infoBuilder.lv = RandomUtils.nextInt(maxLv) + 1
                    infoBuilder.rank = randRank
                    infoBuilder.star = RandomUtils.nextInt(maxStar) + 1
                    infoBuilder.pos = tmpPosList[i]

                    // 技能填充
                    var maxSkillLv = checkSkillLv(1, heroProto.id, randRank)
                    if (maxSkillLv != 0) {
                        val trueSkillLv = RandomUtils.nextInt(maxSkillLv)
                        val skillId = heroProto.skill1 + trueSkillLv
                        infoBuilder.addUniqueSkillList(skillId)
                    }

                    maxSkillLv = checkSkillLv(2, heroProto.id, randRank)
                    if (maxSkillLv != 0) {
                        val trueSkillLv = RandomUtils.nextInt(maxSkillLv)
                        val skillId = heroProto.skill2 + trueSkillLv
                        infoBuilder.addActiveSkillList(skillId)
                    }

                    maxSkillLv = checkSkillLv(3, heroProto.id, randRank)
                    if (maxSkillLv != 0) {
                        val trueSkillLv = RandomUtils.nextInt(maxSkillLv)
                        val skillId = heroProto.skill3 + trueSkillLv
                        infoBuilder.addActiveSkillList(skillId)
                    }

                    maxSkillLv = checkSkillLv(4, heroProto.id, randRank)
                    if (maxSkillLv != 0) {
                        val trueSkillLv = RandomUtils.nextInt(maxSkillLv)
                        val skillId = heroProto.skill3 + trueSkillLv
                        infoBuilder.addPassiveSkillList(skillId)
                    }

                    groupBuilder.addHeros(infoBuilder.build())
                }

                if (isFirst) {
                    isFirst = false
                }
            }

            msg.addDefHerosInOnceFight(groupBuilder.build())
        }

        robot.thisRobotData.sendMsg(MsgType.GetHeroFightReport_105, msg.build())
        return RUNNING
    }

    private fun checkSkillLv(idx: Int, heroProtoId: Int, rank: Int): Int {
        val rankProtoMap = pcs.heroRankProtoCache.heroRankProtoCache[heroProtoId] ?: return 0
        val rankProto = rankProtoMap[rank] ?: return 0
        return rankProto.rpgSkillMaxMap[idx] ?: return 0
    }

    override fun msgTrigger(robot: Robot, chg: RobotPropChg): ActionResult {
        if (chg.msgNo != MsgType.GetHeroFightReport_105) {
            normalLog.lzWarn {
                "msgTrigger:${this.javaClass.name}:robot[${robot.name}] " +
                    "- 接收的消息类型不匹配${MsgType.GetHeroFightReport_105}"
            }
            return com.point18.slg2d.robot.robotData.RUNNING
        }

        val getHeroFightReportRt = chg.convert<GetHeroFightReportRt>() ?: return com.point18.slg2d.robot.robotData.RUNNING
        val rt = getHeroFightReportRt.rt
        if (rt != ResultCode.SUCCESS.code) {
            normalLog.lzWarn {
                "msgTrigger:${this.javaClass.name}:robot[${robot.name}] " +
                    "- 获取英雄战战报记录：${com.point18.slg2d.robot.robotData.getErrMsg(rt)}  错误码： $rt"
            }
            return com.point18.slg2d.robot.robotData.FAILED
        }
        println("获取英雄战战报记录完成....")
        normalLog.lzDebug { "msgTrigger:${this.javaClass.name}:robot[${robot.name}] - 获取英雄战战报记录完成" }
        return SUCCESS
    }

    override fun reset(): RobotAction {
        return AiGetHeroFightReport()
    }

}