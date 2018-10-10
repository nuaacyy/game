package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class PosRightResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<PosRightProto>
) : Serializable

data class PosRightProto(

    val id: Int,
    val name: String,
    val powerManagement: String, // 对应权限：1	任命官员;2设立外交;3成员管理;4任务设置;5公告修改;6联盟邮件;7解散联盟;8	创建聊天频道;10联盟任务
    val solePosition: String, // 不能并存的职位 后者替换前者
    val positionNumb: Int, // 成员最大数量
    val type: Int, // 职位类型,影响任命规则  1表示职权阶级,人满就不能设置了, 2表示特殊职位,只会存在一个,并且后者直接替换前者
    val step: Int
) : Serializable {
    var solePositionMap: Map<Int, Int> = mapOf()// 不能并存的职位 后者替换前者

}

class PosRightProtoCache : ProtoCacheInit("cripsPower.xml") {
    var posName: Map<Int, PosRightProto> = mapOf()//
    var posRightMap: Map<Int, Map<Int, Boolean>> = mapOf()//

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<PosRightResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as PosRightResult

        val tmpPosName: HashMap<Int, PosRightProto> = hashMapOf()
        val tmpPosRightMap: HashMap<Int, HashMap<Int, Boolean>> = hashMapOf()
        for (vo in readXmlResult.l) {
            if (tmpPosName.containsKey(vo.id)) {
                throw RuntimeException("cripsPower.xml :: id[${vo.id}]重复")
            }

            val m = hashMapOf<Int, Boolean>()
            for (s in (vo.powerManagement.split(","))) {
                val i = (s.toInt())
                m[i] = true
                tmpPosRightMap[vo.id] = m
            }

            val positionMap = hashMapOf<Int, Int>()
            val solePoss = vo.solePosition.split("%")
            for (sp in solePoss) {
                val p = (sp.toInt())
                positionMap[p] = 1
            }
            vo.solePositionMap = positionMap

            tmpPosName[vo.id] = vo
        }
        this.posName = tmpPosName
        this.posRightMap = tmpPosRightMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {

    }

    fun hasRight(pos: Int, right: Int): Boolean {
        val m = this.posRightMap[pos] ?: return false
        m[right] ?: return false
        return true
    }
}

