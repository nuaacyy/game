package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.point18.slg2d.common.constg.RES_PROPS
import java.io.Serializable
import java.util.*

data class SoliderResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<SoliderProto>
) : Serializable

data class SoliderProto(
    val id: Int,  //唯一ID
    val soliderID: Int, //造兵兵种ID
    val name: String,//名称
    val step: Int, //每个兵种都会有等级
    val armyType: Int, //1为骑，2为弓，3为步
    val mainType: Int, //1-为普通兵，2-为攻城车，3-陷阱
    val trainPrice: String,  // 训练费用
    val trainCancle: String,  // 取消训练返还
    val trainTime: Int,  // 训练时间(秒)
    val curePrice: String,  // 治疗费用
    val cureCancle: String,  // 取消治疗返还
    val cureTime: Double,  // 治疗时间(秒)
    val condition: String,  //1:建筑ID_等级%2:科技ID_等级
    val attack: Double,
    val defence: Double,
    val hp: Double,
    val crit: Double,
    val critMult: Double,
    val speed: Double,
    val attCity: Double,
    val weight: Int,
    val power: Int, //军团实力
    val lordExp: Double,
    val heroExp: Double,
    val expendFood: Double,  //耗粮属性
    val curePriceActivity: String,  // 治疗（活动）费用
    val cureTimeActivity: Double,  // 治疗（活动）时间(秒)
    val cureCancleActivity: String, // 取消治疗（活动）返还
    val killWeight: Int //杀敌权重
) : Serializable {
    var trainPriceMap: List<ResVo> = listOf()                     // // 训练费用
    var trainCancleMap: List<ResVo> = listOf()                    // // 取消训练返还
    var curePriceMap: List<ResVo> = listOf()                    // // 治疗费
    var cureCancleMap: List<ResVo> = listOf()                // // 取消治疗返还
    var conditionMap: Map<Int, List<Int>> = mapOf()
    var curePriceActivityMap: List<ResVo> = listOf()                    // // 治疗（活动）费
    var cureCancleActivityMap: List<ResVo> = listOf()                // // 取消治疗（活动）返还
}

class SoliderProtoCache : ProtoCacheInit("solider.xml") {
    var soliderProtoMap: Map<Int, SoliderProto> = mapOf()
    var soliderProtoListByArmyType: Map<Int, LinkedList<SoliderProto>> = mapOf()
    var soliderProtoListByIncreaseStep: List<SoliderProto> = listOf()
    var soliderProtoListByDecreaseStep: List<SoliderProto> = listOf()
    var soliderProtoMapByArmyTypeAndStep: Map<Int, Map<Int, SoliderProto>> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<SoliderResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as SoliderResult

        val tmpSoliderProtoMap: HashMap<Int, SoliderProto> = hashMapOf()
        val tmpSoliderProtoListByArmyType: HashMap<Int, LinkedList<SoliderProto>> = hashMapOf()
        val tmpSoliderProtoListByIncreaseStep: LinkedList<SoliderProto> = LinkedList()
        val tmpSoliderProtoListByDecreaseStep: LinkedList<SoliderProto> = LinkedList()
        val tmpSoliderProtoMapByArmyTypeAndStep: HashMap<Int, HashMap<Int, SoliderProto>> = hashMapOf()
        for (vo in readXmlResult.l) {
            vo.trainPriceMap = LinkedList()
            val reward1 = resStringToResVoList(vo.trainPrice)

            if (null == reward1) {
                throw RuntimeException("solider.xml中的TrainPrice字段解析出错${vo.trainPrice}")
            } else {
                vo.trainPriceMap = reward1
            }

            vo.trainCancleMap = LinkedList()
            val reward2 = resStringToResVoList(vo.trainCancle)

            if (null == reward2) {
                throw RuntimeException("solider.xml中的TrainCancle字段解析出错${vo.trainCancle}")
            } else {
                vo.trainCancleMap = reward2
            }

            vo.curePriceMap = LinkedList()
            val reward3 = resStringToResVoList(vo.curePrice)

            if (null == reward3) {
                throw RuntimeException("solider.xml中的CurePrice字段解析出错${vo.curePrice}")
            } else {
                vo.curePriceMap = reward3
            }

            vo.cureCancleMap = LinkedList()
            val reward5 = resStringToResVoList(vo.cureCancle)

            if (null == reward5) {
                throw RuntimeException("solider.xml中的CureCancle字段解析出错${vo.cureCancle}")
            } else {
                vo.cureCancleMap = reward5
            }

            val conditionMap = hashMapOf<Int, LinkedList<Int>>()
            if (vo.condition != "0") {
                val conditions = stringsSplit(vo.condition, "|")

                for (cs in conditions) {
                    val css = stringsSplit(cs, ";")

                    if (css.size != 3) {
                        throw RuntimeException("solider.xml中的Condition字段解析出错1:${vo.condition}")
                    }

                    val conditionBigType = strconvAtoi(css[0])
                    val conditionType = strconvAtoi(css[1])
                    val conditionValue = strconvAtoi(css[2])
                    if (conditionBigType == null || conditionType == null || conditionValue == null) {
                        throw RuntimeException("solider.xml中的Condition字段解析出错${vo.condition}")
                    }

                    val mm = conditionMap.getOrPut(conditionBigType) {
                        LinkedList()
                    }
                    mm.add(conditionType)
                    mm.add(conditionValue)
                    conditionMap[conditionBigType] = mm
                }
            }
            vo.conditionMap = conditionMap

            tmpSoliderProtoMap[vo.soliderID] = vo

            val list = tmpSoliderProtoListByArmyType.getOrPut(vo.armyType) {
                LinkedList()
            }
            list.add(vo)

            val mmap = tmpSoliderProtoMapByArmyTypeAndStep.getOrPut(vo.armyType) {
                hashMapOf()
            }
            mmap[vo.step] = vo

            tmpSoliderProtoListByIncreaseStep.add(vo)
            tmpSoliderProtoListByDecreaseStep.add(vo)

            vo.curePriceActivityMap = LinkedList()
            val reward7 = resStringToResVoList(vo.curePriceActivity)

            if (null == reward7) {
                throw RuntimeException("solider.xml中的CurePriceActivity字段解析出错${vo.curePriceActivity}")
            } else {
                vo.curePriceActivityMap = reward7
            }

            vo.cureCancleActivityMap = LinkedList()
            val reward8 = resStringToResVoList(vo.cureCancleActivity)

            if (null == reward8) {
                throw RuntimeException("solider.xml中的CureCancleActivity字段解析出错${vo.cureCancleActivity}")
            } else {
                vo.cureCancleActivityMap = reward8
            }
        }

        tmpSoliderProtoListByIncreaseStep.sortBy { it.step }
        tmpSoliderProtoListByDecreaseStep.sortByDescending { it.step }

        this.soliderProtoMap = tmpSoliderProtoMap
        this.soliderProtoListByArmyType = tmpSoliderProtoListByArmyType
        this.soliderProtoMapByArmyTypeAndStep = tmpSoliderProtoMapByArmyTypeAndStep
        this.soliderProtoListByIncreaseStep = tmpSoliderProtoListByIncreaseStep
        this.soliderProtoListByDecreaseStep = tmpSoliderProtoListByDecreaseStep

    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((_, vo) in this.soliderProtoMapByArmyTypeAndStep) {
            vo[1] ?: throw RuntimeException("solider.xml中的某兵种没有初始的造兵ID,内容为:$vo .")

            for ((_, v) in vo) {
                for (r in v.curePriceMap) {
                    if (r.resType == RES_PROPS) {
                        // 检测到了道具,需要这个道具必须满足可以资源补齐
                        val prop = pcs.equipCache.equipProtoMap[r.subType.toInt()]
                        if (prop == null || prop.propertyValue == 0) {
                            throw RuntimeException("solider.xml中的curePrice中的道具消耗ID:${r.subType}在道具表里propertyValue为0, 行ID是:${v.id}")
                        }
                    }
                }

                for (r in v.curePriceActivityMap) {
                    if (r.resType == RES_PROPS) {
                        // 检测到了道具,需要这个道具必须满足可以资源补齐
                        val prop = pcs.equipCache.equipProtoMap[r.subType.toInt()]
                        if (prop == null || prop.propertyValue == 0) {
                            throw RuntimeException("solider.xml中的curePriceActivity中的道具消耗ID:${r.subType}在道具表里propertyValue为0, 行ID是:${v.id}")
                        }
                    }
                }

                for (r in v.trainPriceMap) {
                    if (r.resType == RES_PROPS) {
                        // 检测到了道具,需要这个道具必须满足可以资源补齐
                        val prop = pcs.equipCache.equipProtoMap[r.subType.toInt()]
                        if (prop == null || prop.propertyValue == 0) {
                            throw RuntimeException("solider.xml中的trainPrice中的道具消耗ID:${r.subType}在道具表里propertyValue为0, 行ID是:${v.id}")
                        }
                    }
                }
            }
        }
    }

}