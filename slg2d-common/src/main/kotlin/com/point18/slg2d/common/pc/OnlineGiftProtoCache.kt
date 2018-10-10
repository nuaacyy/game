package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class OnlineGiftResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<OnlineGiftProto>
): Serializable

data class OnlineGiftProto(
    val id: Int,
    val num: Int, // 领取次数
    val castleLv: Int, // 主堡等级
    val cdTime: Int,// CD时间
    val reward: Int // dropProps表ID
): Serializable

class OnlineGiftProtoCache : ProtoCacheInit("onlineGift.xml") {
    var protoMap: Map<Int, Map<Int, OnlineGiftProto>> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<OnlineGiftResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as OnlineGiftResult

        // 添加到模版MAP中

        val tmpProtoMap: HashMap<Int, HashMap<Int, OnlineGiftProto>> = hashMapOf()
        for (vo in readXmlResult.l) {
            val protoMapcastleLv = tmpProtoMap.getOrPut(vo.castleLv)
            {
                hashMapOf()
            }
            protoMapcastleLv[vo.num] = vo
        }
        this.protoMap = tmpProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((castleLv, infos) in this.protoMap) {
            infos[1] ?: throw RuntimeException("onlineGift中主堡等级为${castleLv}的时候找不到1级的配置行")

            infos[10] ?: throw RuntimeException("onlineGift中主堡等级为${castleLv}的时候找不到10级的配置行")

        }


        for ((_, infos) in this.protoMap) {

            for ((_, info) in infos) {
                pcs.dropPropsProtoCache.dropPropsMap[info.reward]
                    ?: throw RuntimeException("onlineGift中$infos Reward字段值在dropProps.xml中找不到,配置值为:${info.reward}")

            }
        }
    }

}