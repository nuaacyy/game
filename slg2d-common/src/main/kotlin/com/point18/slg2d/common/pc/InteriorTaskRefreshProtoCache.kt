package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class InteriorTaskRefreshProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<InteriorTaskRefreshProto>
): Serializable

data class InteriorTaskRefreshProto(
    val id: Int,  // 编号
    val num: Int,  // 任务数量
    val refreshBag: String, // 方案
    val luckyRefresh: String // 幸运方案

): Serializable {
    var refreshBagMap: Map<String, Int> = hashMapOf()
    var luckyValue: Int = 0   // 幸运
    var luckyChoose: Int = 0      // 方案
}

class InteriorTaskRefreshProtoCache : ProtoCacheInit("interiorTaskRefresh.xml") {
    var interiorTaskRefreshMap: Map<Int, InteriorTaskRefreshProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<InteriorTaskRefreshProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as InteriorTaskRefreshProtoResult

        val tmpInteriorTaskRefreshMap: HashMap<Int, InteriorTaskRefreshProto> = hashMapOf()
        for (vo in readXmlResult.l) {

            if (vo.refreshBag != "0") {

                val (vorefreshBagMap, ok, sum) = resStringToDropBag(vo.refreshBag)

                if (!ok || vorefreshBagMap == null) {
                    throw RuntimeException("interiorTaskRefresh.xml中的RefreshBag解析出错1:${vo.refreshBag}")
                }

                if (sum < 10000) {
                    throw RuntimeException("interiorTaskRefresh.xml中的RefreshBag字段总权值少于10000,当前值是:$sum")
                }
            }


            if (vo.luckyRefresh != "0") {
                val tempStr = stringsSplit(vo.luckyRefresh, ";")

                if (tempStr.size != 2) {
                    throw RuntimeException("interiorTaskRefresh.xml中的LuckyRefresh解析出错1:${vo.luckyRefresh}")
                }

                val luck = strconvAtoi(tempStr[0])
                    ?: throw RuntimeException("interiorTaskRefresh.xml中的LuckyRefresh解析出错2:${tempStr[0]}.")

                val choose = strconvAtoi(tempStr[1])
                    ?: throw RuntimeException("interiorTaskRefresh.xml中的LuckyRefresh解析出错3:${tempStr[1]}.")

                vo.luckyChoose = choose
                vo.luckyValue = luck

            }

            tmpInteriorTaskRefreshMap[vo.num] = vo
        }
        this.interiorTaskRefreshMap = tmpInteriorTaskRefreshMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {

        for ((_, proto) in this.interiorTaskRefreshMap) {
            // 检测方案
            val tempStr = stringsSplit(proto.refreshBag, "|")

            for (res in tempStr) {
                val str = stringsSplit(res, ";")

                if (str.size != 2) {
                    throw RuntimeException("interiorTaskRefresh.refreshBag:${proto.id}.")
                }

                val refresh =
                    strconvAtoi(str[0]) ?: throw RuntimeException("interiorTaskRefresh.xml中的RefreshBag解析出错2:${str[0]}.")

                pcs.interiorTaskRefreshLuckyProtoCache.interiorTaskRefreshLuckyMap[refresh]
                    ?: throw RuntimeException("interiorTaskRefresh:$refresh, 在interiorTaskRefreshLucky表中找不到该方案")

            }

            pcs.interiorTaskRefreshLuckyProtoCache.interiorTaskRefreshLuckyMap[proto.luckyChoose]
                ?: throw RuntimeException("interiorTaskRefresh:${proto.luckyChoose}, 在interiorTaskRefreshLucky表中找不到该方案")

        }
    }

}