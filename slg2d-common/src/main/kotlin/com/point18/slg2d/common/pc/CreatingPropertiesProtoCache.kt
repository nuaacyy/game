package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class ResultCreatingResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<CreatingPropertiesProto>
) : Serializable

data class CreatingPropertiesProto(
    val id: Int,
    val name: String,
    val camp: String,
    val tokenRecoveryRate: Int,
    val decreeLimit: Int,
    val fameRecoveryRate: Int,
    val fameLimit: Int,
    val powerValue: Int,
    val cityLimit: Int,
    val fortressLimit: Int,
    val skillLimit: Int,
    val skillExp: Int,
    val generalsLimit: Int,
    val distanceLimit: Int,
    val woodYield: Int,
    val ironYield: Int,
    val stoneYield: Int,
    val foodstuffYield: Int,
    val storageLimit: Int,
    val gold: Int,
    val acer: Int,
    val meritoriousService: Int,
    val reserveSoldiersRecovery: Int,
    val reserveSoldiers: Int, //预备兵上限
    val resource: Int,
    val fame: Int,
    val decree: Int,
    val buildingQueue: Int,
    val heroCost: Double,
    val durability: Int,  //初始耐久
    val durabilityRecover: Double,   //耐久恢复速度
    val basicDurability: Int,  //初始耐久上限
    val soldiers: Int,   //预备兵初始值
    val boundDiamond: Int, //绑定钻石
    val heroExp: Long // 武将经验池
) : Serializable

class CreatingPropertiesProtoCache : ProtoCacheInit("creatingProperties.xml") {
    lateinit var proto: CreatingPropertiesProto

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<ResultCreatingResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as ResultCreatingResult

        this.proto = readXmlResult.l[0]
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}