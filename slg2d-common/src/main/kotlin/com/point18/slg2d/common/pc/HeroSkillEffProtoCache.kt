package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.io.Serializable
import java.util.*

data class HeroSkillEffResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<HeroSkillEffProto>
) : Serializable

data class HeroSkillEffProto(
    //BuffID
    val id: Int,
    //Buff名称
    val skillEffName: String,
    //释放距离
    val putRange: Int,
    //AOE半径
    val aoeRadius: Int,
    //线型长度
    val lineLength: String,

    //aoe目标数量
    val aoeNum: Int,
    //是否可重复选择目标
    val repeat: Int,
    //目标阵营
    val faction: Int,
    //目标类型
    val unitType: Int,
    //目标兵种
    val army: Int,
    //目标特殊效果
    val special: Int,
    //弹道速度
    val bulletSpeed: Int,
    //弹道时间
    val bulletTime: Int,
    //飞行速度
    var dartSpeed: Int,
    //伤害类型  1=物攻型        :  法攻型
    val hurtType: Int,
    //技能持续时间(毫秒)
    val auraHandler: Int,
    //技能生效时间间隔(毫秒        :
    val haveTime: Int,
    //技能大分类
    val skillType: Int,
    //伤害加深比例
    val harmChangeRate: Int,
    //变化最大(小)值
    val changeLimit: Int,
    //距离影响伤害比例
    val rangeHarmRate: Int,
    //血量影响伤害比例
    val hpHarmRate: Int,
    //治疗属性 todo 无效
    val treatUseAttribute: Int,
    //技能类型(1-有害 2-有益)
    val isConduce: Int,
    //技能效果基准类型	属性代号(1.攻击:2.防御 3.魔攻 4.魔防 5.速度 6.攻城 7.暴击 8.暴击伤害 100.攻击距离)
    val skillEffBaseType: Int,
    //技能效果基准值	万分比数字
    val skillEffBasePoint: Double,
    //是否取百分比(0-是 1-否)
    val isPer: Int,
    //技能效果额外值
    val skillEffExtraPoint: Int,
    //是否可叠加
    val isMerGe: Int,
    //是否会被过滤(0-过滤掉  1-完全叠: 2-如果身上有同技能种类的话就过滤.否则就叠加)(如果身上已经有此大类型+小类型的buff存在,是否无视这个buff,如已有晕眩,在上一个,会无视,并不会刷新持续时间)
    val isFilter: Int,
    //技能效果成功率	万分比数字
    val successRate: Int,
    //只产生1次效果
    val onceEffect: Int,
    //是否无视兵种相克(0-不是  1-是) todo 无效
    val isRestrain: Int,
    //是否按照命中单位计算
    val isHitNum: Int,
    //召唤物
    val callUnit: String,
    //技能生效的条件集合,
    val condition: String,
    //触发时机
    val triggerTime: String,
    //筛选条件
    val sift: String
) : Serializable {
    var lineType: Int = 0
    var lineLengthInt: Double = 0.0
    var callUnitId: Int = 0
    var callUnitLv: Int = 0
    var callUnitNum: Int = 0
    var callUnitMaxExistNum: Int = 0
    var conditionMap: Map<Int, Int> = mapOf()

    var triggerMoment: Int = 0
    var triggerConditions: List<Int> = listOf()
    var siftSkillTypeList: Set<Int> = setOf()

    fun isBuff(): Boolean {
        when (this.skillType) {
            com.point18.slg2d.common.constg.SKILL_SHUNJIANSHANGHAI,
            com.point18.slg2d.common.constg.SKILL_SHUNJIANZHILIAO,
            com.point18.slg2d.common.constg.SKILL_TIAOFEI,
            com.point18.slg2d.common.constg.SKILL_DAPA,
            com.point18.slg2d.common.constg.SKILL_JITUI,
            com.point18.slg2d.common.constg.SKILL_YICHUYOUYI,
            com.point18.slg2d.common.constg.SKILL_YICHUYOUHAI,
            com.point18.slg2d.common.constg.SKILL_XUELIANGJIAJIANXUE,
            com.point18.slg2d.common.constg.SKILL_XUELIANGSHANGXIANJIAJIANXUE,
            com.point18.slg2d.common.constg.SKILL_TIAOYUE,
            com.point18.slg2d.common.constg.SKILL_SHANXIAN,
            com.point18.slg2d.common.constg.SKILL_GOUREN,
            com.point18.slg2d.common.constg.SKILL_ZHAOHUAN,
            com.point18.slg2d.common.constg.SKILL_SHUNJIANHUIQI,
            com.point18.slg2d.common.constg.SKILL_SHUNJIANJIANQI->
                //TODO 细化判断
                return false
        }
        return true
    }
}

class HeroSkillEffProtoCache : ProtoCacheInit("heroSkillEff.xml") {
    var heroSkillEffMap: Map<Int, HeroSkillEffProto> = mapOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<HeroSkillEffResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as HeroSkillEffResult

        val tmpHeroSkillEffMap: HashMap<Int, HeroSkillEffProto> = hashMapOf()
        for (vo in readXmlResult.l) {
            val conditionMap = hashMapOf<Int, Int>()
            if (vo.condition != "0") {
                val filters = stringsSplit(vo.condition, ";")

                for (filter in filters) {
                    val conditionType = strconvAtoi(filter)
                        ?: throw RuntimeException("heroSkillEff.xml.ConditionStr :: ${vo.condition} 配置错误...")
                    conditionMap[conditionType] = 1
                }
            }
            vo.conditionMap = conditionMap

            if (vo.lineLength != "0") {
                val strs = stringsSplit(vo.lineLength, ";")

                if (strs.size != 2) {
                    throw RuntimeException("heroSkillEff.xml.lineLength :: ${vo.lineLength} 配置错误...")
                }
                val lineTypeTMP = strconvAtoi(strs[0])
                    ?: throw RuntimeException("heroSkillEff.xml.lineLength :: ${vo.lineLength} 配置错误...")

                val lineLengthtmp = strs[1].toDoubleOrNull()
                    ?: throw RuntimeException("skilleff.xml.lineLength :: ${vo.lineLength} 配置错误...")

                vo.lineType = lineTypeTMP
                vo.lineLengthInt = lineLengthtmp
            }

            if (vo.callUnit != "0") {
                val strs = stringsSplit(vo.callUnit, ":")
                if (strs.size != 4) {
                    throw RuntimeException("heroSkillEff.xml.CallUnit :: callUnit${vo.callUnit} 配置错误...")
                }
                vo.callUnitId = strconvAtoi(strs[0]) ?: throw RuntimeException("heroSkillEff.xml.CallUnit :: callUnit${vo.callUnit} 配置错误...")
                vo.callUnitLv = strconvAtoi(strs[1]) ?: throw RuntimeException("heroSkillEff.xml.CallUnit :: callUnit ${vo.callUnit}配置错误...")
                vo.callUnitNum = strconvAtoi(strs[2]) ?: throw RuntimeException("heroSkillEff.xml.CallUnit :: callUnit${vo.callUnit} 配置错误...")
                vo.callUnitMaxExistNum = strconvAtoi(strs[3]) ?: throw RuntimeException("heroSkillEff.xml.CallUnit :: callUnit${vo.callUnit} 配置错误...")
            }

            if (vo.triggerTime != "0") {
                val tmpTriggerConditions = LinkedList<Int>()
                val triggers = stringsSplit(vo.triggerTime, ";")
                vo.triggerMoment = triggers[0].toIntOrNull() ?: throw RuntimeException("heroSkillEff中的triggerTime字段解析出错:${vo.triggerTime}")
                for (i in 1 until triggers.count()) {
                    val condition = triggers[i].toIntOrNull()
                        ?: throw RuntimeException("heroSkillEff中的triggerTime字段解析出错:${vo.triggerTime}")
                    tmpTriggerConditions.add(condition)
                }
                vo.triggerConditions = tmpTriggerConditions
            }
            if (vo.sift != "0") {
                val tmpSiftSkillTypeList = hashSetOf<Int>()
                val sifts = stringsSplit(vo.sift, "|")
                sifts.forEach {
                    val skillId =
                        it.toIntOrNull() ?: throw RuntimeException("heroSkillEff中的sift字段解析出错:${vo.sift}")
                    tmpSiftSkillTypeList.add(skillId)
                }
                vo.siftSkillTypeList = tmpSiftSkillTypeList
            }

            tmpHeroSkillEffMap[vo.id] = vo
        }
        this.heroSkillEffMap = tmpHeroSkillEffMap
    }

    override fun postCheck(pcs: ProtoCacheStore) {
    }

}