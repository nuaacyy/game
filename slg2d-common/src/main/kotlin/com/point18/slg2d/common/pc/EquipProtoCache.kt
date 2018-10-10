package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.point18.slg2d.common.constg.PROP_KING_EQUIP
import com.point18.slg2d.common.constg.RES_BIND_GOLD
import java.io.Serializable
import java.util.*

data class EquipResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<EquipProto>
) : Serializable

data class EquipProto(
    val id: Int,  //唯一ID
    val equipId: Int, //装备ID
    val haveSkillNum: Int, //携带技能个数,1就代表有1个技能(specialRule中随机)
    val name: String,  //装备名字
    val mainType: Int,  //物品大类型 1-装备  2-道具 3-君主装备
    val subType: Int,  //物品类型1=武器；2=衣服；3=鞋子；4=饰品；5=腰带；6=戒指
    val quality: Int, //品质
    val attack: Int,   //攻击
    val defence: Int, //防御
    val magic: Int,  //谋略
    val speed: Int,  //速度
    val attCity: Int,  //攻城
    val purchasePrice: Int,  //购买价格
    val repurchasePrice: Int,  //回购价格
    val specialLv: Int, //初始效果等级
    val isHide: Int,  //是否有隐藏效果 0-不可以 1-可以
    val dressDem: Int, //穿戴所需要的天下大势等级
    val stackNum: Int,  //最大叠加数 1表示不能叠加
    val useAtOnce: Int,  //该物品是否必须获得后马上使用 0-否 1-是
    val useGet: String, //使用物品获得的奖励字符串
    val fastBuy: String, //快速购买价格(奖励格式)
    val extend1: String,//扩展字段
    val extend2: String,  // 掉落控制规则

    // 上面的老的可能有很多字段都    :          不需要了,先不管,下面是新的字段
    val suitType: Int,  // 不同编号对应套装变（suit）的套装ID
    val intensiveExp: Int,  // 该装备作为强化材料时可增加的强化经验
    // 君主装备使用
    val conquer: Int,  // 是否为征服装备	0表示不是，1表示是
    val basicEffect: String, // 基础属性	"效果同科技/建筑效果，配置为 效果ID：值%效果ID：值%...."
    val conquerEffect: String,  // 征服属性	"效果同科技/建筑效果，配置为，0表示没有征服属性 效果ID：值%效果ID：值%...."
    val suit: Int, // 套装效果ID	调用suit表的配置，为0表示为非套装
    val level: Int,  // 穿戴等级	表示穿戴需要的君主等级
    val destroyRandomNum: Int, // 拆解随机次数
    val destroyRandom: String, // 拆解随机返还	掉落配置法
    val destroyTure: String, // 拆解必然返还	奖励格式
    val salePrice: String, // 拆解必然返还	奖励格式
    val isTop: Int,  // 0-非顶级 1-顶级
    val diamondPrice: Int,
    val libraryType: Int,
    val propertyValue: Int  // 是否会配置在资源补齐中  0-否 1-是
) : Serializable {
    var useGetMap: List<ResVo> = listOf()
    var extend2DropMap: Map<String, Int> = mapOf() // 掉落控制规则
    var basicEffectMap: Map<Int, Int> = mapOf()
    var conquerEffectMap: Map<Int, Int> = mapOf()
    var fastBuyMap: List<ResVo> = listOf()
    var destroyRandomMap: Map<String, Int> = mapOf()
    var destroyTureMap: List<ResVo> = listOf()
    var salePriceMap: List<ResVo> = listOf()  //出售价格

}

class EquipProtoCache : ProtoCacheInit("props.xml") {
    var equipProtoList: List<EquipProto> = listOf()
    var equipProtoMap: Map<Int, EquipProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<EquipResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as EquipResult

        val tmpEquipProtoList: LinkedList<EquipProto> = LinkedList()
        val tmpEquipProtoMap: HashMap<Int, EquipProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            if (tmpEquipProtoMap.containsKey(vo.id)) {
                throw RuntimeException("props.xml :: id[${vo.id}]重复")
            }

            val vouseGetMap = resStringToResVoList(vo.useGet)
                ?: throw RuntimeException("props.xml中的UseGet字段解析出错:${vo.useGet},错误行ID是:${vo.id}")
            vo.useGetMap = vouseGetMap

            if (vo.destroyRandom != "0") {
                val (vodestroyRandomMap, _, _) = resStringToDropBag(vo.destroyRandom)

                if (vodestroyRandomMap == null) {
                    throw RuntimeException("props.xml中的DestroyRandom字段解析出错:${vo.id}")
                }
                vo.destroyRandomMap = vodestroyRandomMap
            }

            if (vo.fastBuy != "0") {
                val fastBuyMap = resStringToResVoList(vo.fastBuy)
                    ?: throw RuntimeException("props.xml中的fastBuy字段解析出错:${vo.fastBuy},错误行ID是:${vo.id}")
                vo.fastBuyMap = fastBuyMap
            }

            if (vo.destroyTure != "0") {
                val destroyTureMap = resStringToResVoList(vo.destroyTure)
                    ?: throw RuntimeException("props.xml中的destroyTure字段解析出错:${vo.destroyTure},错误行ID是:${vo.id}")
                vo.destroyTureMap = destroyTureMap
            }

            if (vo.salePrice != "0") {
                val salePriceMap = resStringToResVoList(vo.salePrice)
                    ?: throw RuntimeException("props.xml中的salePrice字段解析出错:${vo.salePrice},错误行ID是:${vo.id}")
                vo.salePriceMap = salePriceMap
            }

            val basicEffectMap = hashMapOf<Int, Int>()
            if (vo.basicEffect != "0") {
                val bb = stringsSplit(vo.basicEffect, "|")

                for (b in bb) {
                    val effect = stringsSplit(b, ";")

                    if (effect.size != 2) {
                        throw RuntimeException("props.xml中的BasicEffect字段解析出错2:${vo.id}")
                    }

                    val effectType = strconvAtoi(effect[0])
                    val effectValue = strconvAtoi(effect[1])
                    if (effectType == null || effectValue == null) {
                        throw RuntimeException("props.xml中的BasicEffect字段解析出错2:${vo.id}")
                    }

                    basicEffectMap[effectType] = effectValue
                }
            }
            vo.basicEffectMap = basicEffectMap

            val conquerEffectMap = hashMapOf<Int, Int>()
            if (vo.conquerEffect != "0") {
                val bb = stringsSplit(vo.conquerEffect, "%")

                for (b in bb) {
                    val effect = stringsSplit(b, ":")

                    if (effect.size != 2) {
                        throw RuntimeException("props.xml中的ConquerEffect字段解析出错2:${vo.id}")
                    }

                    val effectType = strconvAtoi(effect[0])
                    val effectValue = strconvAtoi(effect[1])
                    if (effectType == null || effectValue == null) {
                        throw RuntimeException("props.xml中的ConquerEffect字段解析出错2:${vo.id}")
                    }
                    conquerEffectMap[effectType] = effectValue
                }
            }
            vo.conquerEffectMap = conquerEffectMap

            if (vo.extend2 != "0") {
                val (dropMap, _, sum) = resStringToDropBag(vo.extend2)

                if (dropMap == null) {
                    throw RuntimeException("props.xml中的Extend2字段配置出错:${vo.extend2}")
                }

                if (sum < 10000) {
                    throw RuntimeException("props.xml中的Extend2字段总权值少于10000:当前值是:$sum,配置行为:${vo.id}")
                }

                vo.extend2DropMap = dropMap
            }
            val extenf1Result = this.checkExtend1(vo.mainType, vo.subType, vo.extend1)

            if (vo.mainType == PROP_KING_EQUIP && vo.stackNum != 1) {
                throw RuntimeException("props.xml中的道具堆叠配置错误:${vo.id}")
            }

            if (!extenf1Result) {
                throw RuntimeException("props.xml中的Extend1字段配置错误:${vo.id},道具大类型是:${vo.mainType},小类型是:${vo.subType}")
            }

            if (vo.propertyValue == 1) {
                if ((vo.fastBuy == "0" || vo.fastBuy == "")) {
                    // 配置的是允许资源补齐 但是未配置价格
                    throw RuntimeException("props.xml中的propertyValue字段 配置的是允许资源补齐 但是未配置价格,行ID为:${vo.id}")
                }

                if (vo.fastBuyMap.size != 1 || vo.fastBuyMap[0].resType != RES_BIND_GOLD) {
                    throw RuntimeException("props.xml中的propertyValue字段 配置的是允许资源补齐 但是价格不是绑钻,行ID为:${vo.id}")
                }
            }

            tmpEquipProtoMap[vo.id] = vo
            tmpEquipProtoList.add(vo)
        }
        this.equipProtoMap = tmpEquipProtoMap
        this.equipProtoList = tmpEquipProtoList
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for (e in this.equipProtoList) {

            if (e.mainType != com.point18.slg2d.common.constg.PROP_BUFF) {
                continue
            }

            val buffId = strconvAtoi(e.extend1)
            pcs.buffBasicProtoCache.protoMap[buffId]
                ?: //commonfunc.debugAssert(false, "使用buff类道具的时候在buffBasic表中找不到行:${  }", buffId)
                continue

        }
        for ((_, id) in pcs.basicProtoCache.moveMainCityConsumeMap) {
            val equipProto = equipProtoMap[id]
            if (equipProto == null) {
                throw RuntimeException("basic.xml moveMainCityConsume 在props.xml没找到匹配道具")
            }
        }
    }

    //private fun checkExtend1 未完成
    private fun checkExtend1(bigType: Int, smallType: Int, extend1: String): Boolean {
        if (bigType == com.point18.slg2d.common.constg.PROP_RES && smallType == com.point18.slg2d.common.constg.SoliderBag) {
            val ssAdd = stringsSplit(extend1, "%")

            for (soliderAddInfo in ssAdd) {
                val soliderAdd = stringsSplit(soliderAddInfo, ":")

                if (soliderAdd.size != 2) {
                    return false
                }
                strconvAtoi(soliderAdd[0]) ?: return false

                strconvAtoi(soliderAdd[1]) ?: return false

            }

        } else if (bigType == com.point18.slg2d.common.constg.PROP_BUFF) {
            // 获得buff
            strconvAtoi(extend1) ?: //commonfunc.debugAssert(false, "使用buff类道具的时候props的ex1字段转型错误:${  }", extend1)
            return false

        }

        return true
    }

}