package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class InteriorTaskRefreshLuckyProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<InteriorTaskRefreshLuckyProto>
): Serializable

data class InteriorTaskRefreshLuckyProto(
    val id: Int, // 编号
    val name: String,  // 方案
    val lucky: Int, // 幸运值
    val baseDrop: String  // 保底掉落

): Serializable {
    var addDropQuality: Int = 0
    var addDropId: Int = 0
}

class InteriorTaskRefreshLuckyProtoCache : ProtoCacheInit("interiorTaskRefreshLucky.xml") {
    var interiorTaskRefreshLuckyMap: Map<Int, InteriorTaskRefreshLuckyProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<InteriorTaskRefreshLuckyProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as InteriorTaskRefreshLuckyProtoResult

        val tmpInteriorTaskRefreshLuckyMap: HashMap<Int, InteriorTaskRefreshLuckyProto> = hashMapOf()
        for (vo in readXmlResult.l) {

            if (vo.baseDrop != "0") {
                val tempStr = stringsSplit(vo.baseDrop, ";")

                if (tempStr.size != 2) {
                    throw RuntimeException("interiorTaskRefreshLucky.xml中的BaseDrop解析出错1:${vo.baseDrop}.")
                }

                val quality = strconvAtoi(tempStr[0])
                    ?: throw RuntimeException("interiorTaskRefreshLucky.xml中的BaseDrop解析出错2:${vo.baseDrop}.")

                val addId = strconvAtoi(tempStr[1])
                    ?: throw RuntimeException("interiorTaskRefreshLucky.xml中的BaseDrop解析出错3:${vo.baseDrop}.")

                vo.addDropQuality = quality
                vo.addDropId = addId
            }

            tmpInteriorTaskRefreshLuckyMap[vo.id] = vo
        }
        this.interiorTaskRefreshLuckyMap = tmpInteriorTaskRefreshLuckyMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((_, proto) in this.interiorTaskRefreshLuckyMap) {
            // 检测方案
            val tempStr = stringsSplit(proto.name, "|")

            for (res in tempStr) {
                val str = stringsSplit(res, ";")

                if (str.size != 2) {
                    throw RuntimeException("interiorTaskRefreshLucky.refreshBag:$res.")
                }
                val quality = strconvAtoi(str[0])
                    ?: throw RuntimeException("interiorTaskRefreshLucky.xml中的RefreshBag解析出错2:$res.")

                pcs.interiorTaskProtoCache.interiorTaskProtoMapByQuality[quality]
                    ?: throw RuntimeException("interiorTaskRefreshLucky:$res, 在interiorTask表中找不到该品质组")

            }

            pcs.interiorTaskRefreshLuckyProtoCache.interiorTaskRefreshLuckyMap[proto.addDropQuality]
                ?: throw RuntimeException("interiorTaskRefreshLucky:${proto.addDropQuality}, 在interiorTask表中找不到该品质组")

            pcs.dropBagCache.dropBagMap[proto.addDropId]
                ?: throw RuntimeException("interiorTaskRefreshLucky:${proto.addDropId}, 找不到该掉落包")

        }
    }

}