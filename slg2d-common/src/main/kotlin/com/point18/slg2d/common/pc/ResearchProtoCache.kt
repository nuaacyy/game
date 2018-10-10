package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.point18.slg2d.common.constg.RES_PROPS
import java.io.Serializable
import java.util.*

data class ResearchResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<ResearchProto>
) : Serializable

data class ResearchProto(
    val id: Int, //唯一ID
    val name: String, // 科技名
    val researchID: Int,  //科技ID
    val researchType: Int, //类型
    val level: Int,//等级
    val time: Int,  //升级时间 秒
    val resources: String, //资源消耗
    val cancel: String, //取消研发资源返还
    val condition: String,  //1:建筑ID_等级%2:科技ID_等级
    val effect: String, // 效果
    val power: Int //实力
) : Serializable {
    var resourcesMap: List<ResVo> = listOf()                        //资源消耗
    var conditionMap: Map<Int, List<Int>> = mapOf()
    var effectMap: Map<Int, Int> = mapOf()                // 效果
    var cancelResourcesMap: List<ResVo> = listOf()                //取消研发资源返还

}

class ResearchProtoCache : ProtoCacheInit("research.xml") {
    var researchProtoMap: Map<Int, ResearchProto> = mapOf()
    var researchProtoMapByLv: Map<Int, HashMap<Int, ResearchProto>> = mapOf()
    var researchProtoMapByEffectType: Map<Int, Map<Int, Int>> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<ResearchResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as ResearchResult

        val tmpResearchProtoMap: HashMap<Int, ResearchProto> = hashMapOf()
        val tmpResearchProtoMapByLv: HashMap<Int, HashMap<Int, ResearchProto>> = hashMapOf()
        val tmpResearchProtoMapByEffectType: HashMap<Int, HashMap<Int, Int>> = hashMapOf()
        for (vo in readXmlResult.l) {
            if (tmpResearchProtoMap[vo.id] != null) {
                throw RuntimeException("research.xml中的ID重复:${vo.id}")
            }
            vo.resourcesMap = LinkedList()
            val reward1 = resStringToResVoList(vo.resources)

            if (reward1 == null) {
                throw RuntimeException("research.xml中的Resources字段解析出错${vo.resources}")
            } else {
                vo.resourcesMap = reward1
            }

            val reward2 = resStringToResVoList(vo.cancel)

            if (reward2 == null) {
                throw RuntimeException("research.xml中的CancelResources字段解析出错${vo.cancel}")
            } else {
                vo.cancelResourcesMap = reward2
            }

            val conditionMap = hashMapOf<Int, LinkedList<Int>>()
            if (vo.condition != "0") {
                val conditions = stringsSplit(vo.condition, "|")

                for (cs in conditions) {
                    val css = stringsSplit(cs, ";")

                    if (css.size != 3) {
                        throw RuntimeException("research.xml中的Condition字段解析出错1:${vo.id}")
                    }

                    val conditionBigType = strconvAtoi(css[0])
                    val conditionType = strconvAtoi(css[1])
                    val conditionValue = strconvAtoi(css[2])
                    if (conditionBigType == null || conditionType == null || conditionValue == null) {
                        throw RuntimeException("research.xml中的Condition字段解析出错${vo.id}")
                    }

                    val mm = conditionMap.getOrPut(conditionBigType) { LinkedList() }

                    mm.add(conditionType)
                    mm.add(conditionValue)
                    conditionMap[conditionBigType] = mm
                }
            }
            vo.conditionMap = conditionMap

            val effectMap = hashMapOf<Int, Int>()
            if (vo.effect != "0") {
                val upGradeEffectStr = stringsSplit(vo.effect, "|")

                for (element in upGradeEffectStr) {
                    val b = stringsSplit(element, ";")

                    if (b.size != 2) {
                        throw RuntimeException("research.xml中的Effec解析出错1:$element")
                    }
                    val effType = strconvAtoi(b[0]) ?: throw RuntimeException("research.xml中的Effec解析出错2:$element")

                    val effValue = strconvAtoi(b[1]) ?: throw RuntimeException("research.xml中的Effec解析出错3:$element")

                    effectMap[effType] = effValue

                    val effMap = tmpResearchProtoMapByEffectType.getOrPut(effType) {
                        hashMapOf()
                    }
                    effMap[vo.researchID] = 1
                }
            }
            vo.effectMap = effectMap

            tmpResearchProtoMap[vo.id] = vo

            val researchV = tmpResearchProtoMapByLv.getOrPut(vo.researchID)
            {
                hashMapOf()
            }
            researchV[vo.level] = vo
        }
        this.researchProtoMap = tmpResearchProtoMap
        this.researchProtoMapByLv = tmpResearchProtoMapByLv
        this.researchProtoMapByEffectType = tmpResearchProtoMapByEffectType
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((_, proto) in researchProtoMap) {
            for (r in proto.resourcesMap) {
                if (r.resType == RES_PROPS) {
                    // 检测到了道具,需要这个道具必须满足可以资源补齐
                    val prop = pcs.equipCache.equipProtoMap[r.subType.toInt()]
                    if (prop == null || prop.propertyValue == 0) {
                        throw RuntimeException("research.xml中的resources中的道具消耗ID:${r.subType}在道具表里propertyValue为0, 行ID是:${proto.id}")
                    }
                }
            }
        }
    }

    // 根据科技ID和等级和升级效果类型获取升级的值
    fun getEffValue(researchId: Int, lv: Int, effectType: Int): Int {
        val researchProtoMapByLvresearchId =
            this.researchProtoMapByLv[researchId] ?: throw RuntimeException("根据科技ID和等级和升级效果类型获取升级的值出错1:%v.")
        val researchProto = researchProtoMapByLvresearchId[lv] ?: return 0

        val value = researchProto.effectMap[effectType] ?: return 0

        return value
    }

}