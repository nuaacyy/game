package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class KingExpResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<KingExpProto>
) : Serializable

data class KingExpProto(
    val id: Int,//唯一ID
    val level: Int, //君主等级
    val exp: Int, //升级所需经验
    val militaryTalent: Int, //军事天赋点
    val economicsTalent: Int, //经济天赋点
    val monsterTalent: Int,  //魔物天赋点
    val pveExp: Int,//pve经验
    val power: Int, //实力
    val reward: String, //奖励
    val heroLevelTop: Int // 当前君主等级武将可至最高等级
) : Serializable {
    var rewardResVo: List<ResVo> = listOf()

}

class KingExpProtoCache : ProtoCacheInit("lord.xml") {
    var kingExpMap: Map<Int, KingExpProto> = mapOf()
    var list: List<KingExpProto> = listOf()
    var maxLvProto: KingExpProto = KingExpProto(0, 0, 0, 0, 0, 0, 0, 0, "", 0)

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<KingExpResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as KingExpResult

        val tmpKingExpMap: HashMap<Int, KingExpProto> = hashMapOf()
        val tmpList: LinkedList<KingExpProto> = LinkedList()
        for (proto in readXmlResult.l) {
            val reward = resStringToResVoList(proto.reward)

            if (reward != null) {
                proto.rewardResVo = reward
            } else {
                throw RuntimeException("lord中的reward字段解析出错: ${proto.id}reward == null ")
            }

            tmpKingExpMap[proto.level] = proto


            tmpList.add(proto)
        }
        this.kingExpMap = tmpKingExpMap

        tmpList.sortBy { it.level }
        this.list = tmpList

        this.maxLvProto = this.list[(this.list).size - 1]

        if (maxLvProto.id == 0) {
            //todo 异常
            throw RuntimeException("lord中的reward字段解析出错:maxLvProto.id == 0")
        }

    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}