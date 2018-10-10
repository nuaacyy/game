package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class HeroStarResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<HeroStarProto>
): Serializable

data class HeroStarProto(
    val id: Int,   //序号
    val heroId: Int,    //武将id
    val star: Int,    //星数
    val mainStar: Int,   // 所属大星数
    val time: Int,   //升星时间（秒）
    val starProps: String,  //升星消耗
    val effectStar: String, //升星属性
    val grow: String,  //升星的成长率变化
    val power: Int  //实力
) : Serializable{
    var starPropsResVo: List<ResVo> = listOf()
    var growMap: Map<Int, Double> = mapOf()
    var starEffectMap: Map<Int, Double> = mapOf()  // //升星增加的属性

}

class HeroStarProtoCache : ProtoCacheInit("heroStar.xml") {
    var heroStarProtoCache: Map<Int, Map<Int, HeroStarProto>> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<HeroStarResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as HeroStarResult

        val tmpHeroStarProtoCache: HashMap<Int, HashMap<Int, HeroStarProto>> = hashMapOf()
        for (vo in readXmlResult.l) {
            // 奖励模版
            val resVo = resStringToResVoList(vo.starProps)
                ?: throw RuntimeException("heroStar.xml :: StarProps ${vo.starProps} 奖励格式不合法.")
            parseFloatMap(vo.effectStar)
                ?: throw RuntimeException("heroStar.xml :: StarProps 升星属性错误${vo.effectStar}")
            val vogrowMap =
                parseFloatMap(vo.grow) ?: throw RuntimeException("heroStar.xml :: StarProps 升星成长率错误${vo.grow}")
            vo.growMap = vogrowMap
            vo.starPropsResVo = resVo
            val tmp = tmpHeroStarProtoCache.getOrPut(vo.heroId) { hashMapOf() }
            tmp[vo.star] = vo
        }
        this.heroStarProtoCache = tmpHeroStarProtoCache
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}