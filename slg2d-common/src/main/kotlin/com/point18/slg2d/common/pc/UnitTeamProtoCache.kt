package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.point18.slg2d.common.constg.UNIT_TYPE_HERO
import java.io.Serializable
import java.util.*

data class UnitTeamResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<UnitTeamProto>
) : Serializable

data class UnitTeamProto(
    val id: Int,
    val grid1: String,
    val grid2: String,
    val grid3: String,
    val grid4: String,
    val grid5: String,
    val grid6: String,
    val grid7: String,
    val grid8: String,
    val grid9: String
) : Serializable {
    var gridMap: Map<Int, UnitTeam> = mapOf() // 一个对手

}

data class UnitTeam(
    var unitProto: Int,  // 模版ID
    var lv: Int,  // 等级
    var starLv: Int,  // 星级
    var awakeLv: Int,  // 阶级
    var skillIds: List<Int> // 技能ids
)

class UnitTeamProtoCache : ProtoCacheInit("unitTeam.xml") {
    var protoMap: Map<Int, UnitTeamProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<UnitTeamResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as UnitTeamResult

        val tmpProtoMap: HashMap<Int, UnitTeamProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            tmpProtoMap[vo.id] = vo
        }
        this.protoMap = tmpProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((_, vo) in this.protoMap) {
            val gridMap = hashMapOf<Int, UnitTeam>()

            if (vo.grid1 != "0") {
                gridMap[1] = this.stringToUnitTeam(pcs, vo.grid1)
            }

            if (vo.grid2 != "0") {
                gridMap[2] = this.stringToUnitTeam(pcs, vo.grid2)

            }


            if (vo.grid3 != "0") {
                gridMap[3] = this.stringToUnitTeam(pcs, vo.grid3)

            }


            if (vo.grid4 != "0") {
                gridMap[4] = this.stringToUnitTeam(pcs, vo.grid4)

            }


            if (vo.grid5 != "0") {
                gridMap[5] = this.stringToUnitTeam(pcs, vo.grid5)

            }


            if (vo.grid6 != "0") {
                gridMap[6] = this.stringToUnitTeam(pcs, vo.grid6)

            }


            if (vo.grid7 != "0") {
                gridMap[7] = this.stringToUnitTeam(pcs, vo.grid7)

            }


            if (vo.grid8 != "0") {
                gridMap[8] = this.stringToUnitTeam(pcs, vo.grid8)

            }


            if (vo.grid9 != "0") {
                gridMap[9] = this.stringToUnitTeam(pcs, vo.grid9)

            }

            vo.gridMap = gridMap
        }
    }

    fun stringToUnitTeam(pcs: ProtoCacheStore, grid: String): (UnitTeam) {
        val gridss = stringsSplit(grid, ":")
        if (gridss.size != 5) {
            throw RuntimeException("unitTeam 中的格子中数据长度不为5:$grid")
        }

        val protoId = strconvAtoi(gridss[0]) ?: throw RuntimeException("unitTeam 中的格子中模版ID字段解析错误:${gridss[0]}")

        val proto = pcs.unitBaseCache.protoMap[protoId]
            ?: throw RuntimeException("unitTeam 中的格子中模版ID字段在unitbase中找不到:$protoId")

        if (proto.npcType != UNIT_TYPE_HERO) {
            return UnitTeam(
                protoId,
                0,
                0,
                0,
                listOf()
            )
        }

        val lv = strconvAtoi(gridss[1]) ?: throw RuntimeException("unitTeam 中的格子中武将等级字段解析错误:${gridss[1]}")


        if (lv >= pcs.basicProtoCache.heroMaxLv) {
            throw RuntimeException("unitTeam 中的格子中武将等级字段超过等级上限:${gridss[1]}")
        }

        val starLv = strconvAtoi(gridss[2]) ?: throw RuntimeException("unitTeam 中的格子中武将星级字段解析错误:${gridss[2]}")

        val tmp2 = pcs.heroStarProtoCache.heroStarProtoCache[protoId]
            ?: throw RuntimeException("unitTeam 中的格子中武将星级字段找不到匹配行:$grid  $protoId")

        tmp2[starLv] ?: throw RuntimeException("unitTeam 中的格子中武将星级字段找不到匹配行:$grid,$starLv")

        val awakeLv = strconvAtoi(gridss[3]) ?: throw RuntimeException("unitTeam 中的格子中武将阶级字段解析错误:${gridss[3]}")

        val tmp4 = pcs.heroRankProtoCache.heroRankProtoCache[protoId]
            ?: throw RuntimeException("unitTeam 中的格子中武将阶级字段找不到匹配行:$grid,$protoId")

        tmp4[awakeLv] ?: throw RuntimeException("unitTeam 中的格子中武将阶级字段找不到匹配行:$grid,$awakeLv")

        val skillIds = LinkedList<Int>()
        if (gridss[4] != "0") {
            val ss = stringsSplit(gridss[4], "_")

            for (s in ss) {
                val skillId = strconvAtoi(s) ?: throw RuntimeException("unitTeam 中的格子中武将技能字段解析错误:${gridss[4]}")

                pcs.heroSkillProtoCache.heroSkillMap[skillId]
                    ?: throw RuntimeException("unitTeam 中的格子中武将技能在技能表中找不到:$skillId")

                skillIds.add(skillId)
            }
        }

        return UnitTeam(
            protoId,
            lv,
            starLv,
            awakeLv,
            skillIds
        )
    }
}