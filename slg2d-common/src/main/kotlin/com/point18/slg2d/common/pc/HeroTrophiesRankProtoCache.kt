package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class HeroTrophiesRankResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<HeroTrophiesRankProto>
): Serializable

data class HeroTrophiesRankProto(
    val id: Int,             //序号
    val heroTrophiesId: Int,  //战利品包ID
    val rank: Int,  //阶数
    val heroTrophiesProps: String,  //进阶需要材料	奖励格式
    val heroTrophiesRequire: String,  //进阶要求	"配置格式：代号：num1表示领主等级  2表示阶数  3表示星数"
    val effect: String, //装备属性
    val otherEffect: String //装备额外属性
) : Serializable{
    var heroTrophiesPropsResVo: List<ResVo> = listOf()
    var heroTrophiesRequireConditionMap: Map<Int, Int> = mapOf()
    var equipEffectMap: Map<Int, Double> = mapOf()
    var equipOtherEffectMap: Map<Int, Double> = mapOf()

}

class HeroTrophiesRankProtoCache : ProtoCacheInit("heroTrophiesRank.xml") {
    var heroTrophiesRanksMap: Map<Int, Map<Int, HeroTrophiesRankProto>> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<HeroTrophiesRankResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as HeroTrophiesRankResult

        val tmpHeroTrophiesRanksMap: HashMap<Int, HashMap<Int, HeroTrophiesRankProto>> = hashMapOf()
        for (vo in readXmlResult.l) {
            // 奖励模版
            val resVo = resStringToResVoList(vo.heroTrophiesProps)
                ?: throw RuntimeException("heroTrophiesRank.xml :: HeroTrophiesProps ${vo.heroTrophiesProps} 奖励格式不合法.")
            vo.heroTrophiesPropsResVo = resVo
            val heroTrophiesRequireConditionMap = hashMapOf<Int, Int>()
            if (vo.heroTrophiesRequire != "0") {
                val heroTrophiesRequiress = stringsSplit(vo.heroTrophiesRequire, "|")

                for (heroTrophiesRequires in heroTrophiesRequiress) {
                    val heroTrophiesRequire = stringsSplit(heroTrophiesRequires, ";")
                    if (heroTrophiesRequire.size != 2) {
                        throw RuntimeException("heroTrophiesRank.xml :: HeroTrophiesRequire $heroTrophiesRequires 字段配置有错,检测长度不是2.")
                    }

                    val checkType = strconvAtoi(heroTrophiesRequire[0])
                    if (checkType != com.point18.slg2d.common.constg.KING_LV_CHECK && checkType != com.point18.slg2d.common.constg.HERO_SUPER_LV_CHECK &&
                        checkType != com.point18.slg2d.common.constg.HERO_STAR_LV_CHECK && checkType != com.point18.slg2d.common.constg.HERO_LV_CHECK
                    ) {
                        throw RuntimeException("heroTrophiesRank.xml :: HeroTrophiesRequire $heroTrophiesRequires 字段配置有错,检测大类型不存在.")
                    }

                    val value = strconvAtoi(heroTrophiesRequire[1])
                        ?: throw RuntimeException("heroTrophiesRank.xml :: HeroTrophiesRequire $heroTrophiesRequires 字段配置有错,检测大类型不存在.")
                    heroTrophiesRequireConditionMap[checkType] = value
                }
            }
            vo.heroTrophiesRequireConditionMap = heroTrophiesRequireConditionMap

            val heroTrophiesRanksMapvoheroTrophiesId =
                tmpHeroTrophiesRanksMap.getOrPut(vo.heroTrophiesId) { hashMapOf() }
            heroTrophiesRanksMapvoheroTrophiesId[vo.rank] = vo
            val voequipEffectMap =
                parseFloatMap(vo.effect) ?: throw RuntimeException("heroTrophiesRank.xml :: 属性错误 ${vo.effect}")
            vo.equipEffectMap = voequipEffectMap
            val voequipOtherEffectMap = parseFloatMap(vo.otherEffect)
                ?: throw RuntimeException("heroTrophiesRank.xml :: 额外属性错误 ${vo.otherEffect}")
            vo.equipOtherEffectMap = voequipOtherEffectMap

        }
        this.heroTrophiesRanksMap = tmpHeroTrophiesRanksMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}