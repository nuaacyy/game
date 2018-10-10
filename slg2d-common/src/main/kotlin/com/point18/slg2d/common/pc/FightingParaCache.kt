package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class FightingParaResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<FightingParaProto>
) : Serializable

data class FightingParaProto(
    val id: Int, //唯一ID
    val attackType: Int,//攻击类型
    val hpPara: Double, //生命战力
    val attackPara: Double, //物攻战力
    val defencePara: Double, //物防战力
    val magicPara: Double, //法攻战力
    val magicDefPara: Double, //法防战力
    val hpRecoverPara: Double, //生命恢复战力
    val moraleRecoverPara: Double, //士气恢复战力
    val attackAddHpPara: Double,//攻击回血战力
    val attackAddMoralePara: Double, //攻击回气战力
    val dodgePara: Double, //闪避战力
    val critPara: Double  //暴击战力
) : Serializable

class FightingParaCache : ProtoCacheInit("fightingPara.xml") {
    var fightingParaProtoMap: Map<Int, FightingParaProto> = mapOf()
    var fightingParaProtoMapByAtkType: Map<Int, FightingParaProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<FightingParaResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as FightingParaResult

        val tmpFightingParaProtoMap: HashMap<Int, FightingParaProto> = hashMapOf()
        val tmpFightingParaProtoMapByAtkType: HashMap<Int, FightingParaProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            if (tmpFightingParaProtoMap.containsKey(vo.id)) {
                throw RuntimeException("fightingPara.xml :: id[${vo.id}]重复")
            }
            if (tmpFightingParaProtoMapByAtkType.containsKey(vo.attackType)) {
                throw RuntimeException("fightingPara.xml :: id[${vo.attackType}]重复")
            }

            tmpFightingParaProtoMap[vo.id] = vo
            tmpFightingParaProtoMapByAtkType[vo.attackType] = vo
        }
        this.fightingParaProtoMap = tmpFightingParaProtoMap
        this.fightingParaProtoMapByAtkType = tmpFightingParaProtoMapByAtkType
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}