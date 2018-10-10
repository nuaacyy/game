package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class AppNoticeProtoResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<AppNoticeProto>
): Serializable

data class AppNoticeProto(
    val id: Int,  // 编号
    val typeId: Int,  // 推送分类序号
    val idTitle: String, // 推送标题
    val switch: Int,  // 默认开关
    val disturbSwitch: Int, // 默认勿扰开关
    val messageId: Int, // 分类子序号
    val message: String,  // 推送内容
    val messagemusic: String, //音效
    val messageIcon: String //图标
): Serializable

class AppNoticeProtoCache : ProtoCacheInit("inform.xml") {
    var appNoticeProtoMap: Map<Int, AppNoticeProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<AppNoticeProtoResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as AppNoticeProtoResult

        val tmpAppNoticeProtoMap: HashMap<Int, AppNoticeProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            if (tmpAppNoticeProtoMap.containsKey(vo.id)) {
                throw RuntimeException("inform.xml :: id[${vo.id}]重复")
            }

            val proto = tmpAppNoticeProtoMap[vo.id]
            if (proto != null) {
                throw RuntimeException("inform.xml :: type${vo.id} 重复的Id")
            }
            tmpAppNoticeProtoMap[vo.id] = vo
        }

        appNoticeProtoMap = tmpAppNoticeProtoMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {}

}