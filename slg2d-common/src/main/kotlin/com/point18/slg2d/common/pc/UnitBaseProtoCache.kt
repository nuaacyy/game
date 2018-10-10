package com.point18.slg2d.common.pc

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.point18.slg2d.common.constg.UNIT_TYPE_HERO
import java.io.Serializable
import java.util.*

data class UnitBaseResult(
    @JacksonXmlElementWrapper(useWrapping = false)
    var l: LinkedList<UnitBaseProto>
) : Serializable

data class UnitBaseProto(
    val id: Int,
    val name: String,
    val range: Int,     //攻击距离
    val attackType: Int, //攻击类别
    val attack: Double, //物理攻击
    val defence: Double, //物理防御
    val magic: Double, //法术攻击
    val magicDef: Double,  //法术防御
    val crit: Double,  //暴击
    val critMult: Double,  //暴伤
    val speed: Double,  //速度
    val hp: Double, //生命
    val hpGrowth: Double, //生命成长
    val attackGrowth: Double, //物攻成长
    val defenceGrowth: Double,  //物防成长
    val magicGrowth: Double,  //法攻成长
    val magicDefGrowth: Double, //法防成长
    val type: Int,
    val skill: String,

    val intSkill: String,

    val fuseChip: String, // 合成信息
    val heroTrophies: String, // 武将初始装备状态
    val starAttribute: String,// 武将所属星象
    val hpRecover: Double, //生命恢复
    val moraleRecover: Double, //士气回复
    val attackAddHp: Double, //攻击回血
    val attackAddMorale: Double,  //攻击回气
    val dodge: Double, //闪避
    val attackTime: Float,  //攻击间隔
    val attackAnimation: String,  //攻击前摇时间(毫秒)
    val touchVolume: Float, //触碰体积
    val atkVolume: Float, // 攻击体积
    var bulletSpeed: Int, // 飞行速度
    val libraryType: Int,  // 图书馆类型
    val npcType: Int, //NPC类型
    val basicPosition: Int, //初始站位
    val SPPos: Int,//测试站位
    val battleNum: Long,      //固定战力
    val positionType: Int   //站位类型 1、前排 2、中排 3、后排
) : Serializable {
    var fuseChipRes: List<ResVo> = listOf()
    var heroTrophiesMap: Map<Int, Int> = mapOf()
    var starAttributeMap: Set<Int> = setOf()
    var atkAnimationList: List<Int> = listOf()

    var skill1: Int = 0
    var skill2: Int = 0
    var skill3: Int = 0
    var skill4: Int = 0

    var intSkill1: Int = 0
    var intSkill2: Int = 0
    var intSkill3: Int = 0
}

class UnitBaseProtoCache : ProtoCacheInit("unitBase.xml") {
    var protoMap: Map<Int, UnitBaseProto> = mapOf()
    var protos: List<UnitBaseProto> = listOf()

    override fun load(pcs: ProtoCacheStore): Serializable {
        return loadConfig<UnitBaseResult>(pcs, configFileName)
    }

    override fun init(pcs: ProtoCacheStore) {
        val readXmlResult = load(pcs) as UnitBaseResult

        val tmpProtoMap: HashMap<Int, UnitBaseProto> = hashMapOf()
        val tmpProtos: LinkedList<UnitBaseProto> = LinkedList()

        for (vo in readXmlResult.l) {
            vo.fuseChipRes = LinkedList()
            if (vo.fuseChip != "0") {
                val fuseChipRes = resStringToResVoList(vo.fuseChip)

                if (fuseChipRes != null) {
                    vo.fuseChipRes = fuseChipRes
                } else {
                    throw RuntimeException("unitbase中的FuseChip字段解析出错:${vo.fuseChip}")
                }
            }


            if (vo.skill != "0") {
                val skills = stringsSplit(vo.skill, "|")

                if (skills.size != 4) {
                    throw RuntimeException("unitbase中的Skill字段解析出错1:${vo.intSkill}")
                }


                for (index in skills.indices) {

                    val sId = strconvAtoi(skills[index]) ?: throw RuntimeException("unitbase中的Skill字段解析出错")

                    when (index) {
                        0 -> vo.skill1 = sId
                        1 -> vo.skill2 = sId
                        2 -> vo.skill3 = sId
                        3 -> vo.skill4 = sId
                    }
                }
            }


            if (vo.intSkill != "0") {
                val intSkills = stringsSplit(vo.intSkill, "|")

                if (intSkills.size != 3) {
                    throw RuntimeException("unitbase中的IntSkill字段解析出错1:${vo.intSkill}")
                }


                for (index in intSkills.indices) {
                    val sId = strconvAtoi(intSkills[index]) ?: throw RuntimeException("unitbase中的IntSkill字段解析出错1")
                    when (index) {
                        0 -> vo.intSkill1 = sId
                        1 -> vo.intSkill2 = sId
                        2 -> vo.intSkill3 = sId
                    }
                }
            }

            if (vo.npcType == UNIT_TYPE_HERO) {
                //英雄类型才有效
                val heroTrophiesMap = hashMapOf<Int, Int>()
                val heroTrophiess = stringsSplit(vo.heroTrophies, "|")

                for (heroT in heroTrophiess) {
                    val ht = stringsSplit(heroT, ";")

                    if (ht.size != 2) {
                        throw RuntimeException("unitbase中的HeroTrophies字段解析出错,解析长度不正确:${vo.heroTrophies}")
                    }

                    val heroEquipId = strconvAtoi(ht[0])
                    val equipAdvLv = strconvAtoi(ht[1])
                    val ex = vo.heroTrophiesMap[heroEquipId]
                    if (ex != null) {
                        throw RuntimeException("unitbase中的HeroTrophies字段解析出错,一个武将的装备ID重复:${vo.heroTrophiesMap[heroEquipId]}")
                    }
                    if (heroEquipId == null || equipAdvLv == null) {
                        throw RuntimeException("unitbase中的HeroTrophies字段解析出错:${vo.heroTrophies}")
                    }
                    heroTrophiesMap[heroEquipId] = equipAdvLv
                }
                vo.heroTrophiesMap = heroTrophiesMap

                if (vo.heroTrophiesMap.size != 6) {
                    throw RuntimeException("unitbase中的HeroTrophies字段解析出错,装备个数不足:${vo.heroTrophiesMap},ID为:${vo.id}")
                }
            }

            val starAttributeMap = hashSetOf<Int>()
            val groups = stringsSplit(vo.starAttribute, "|")

            for (g in groups) {
                val gg = strconvAtoi(g) ?: throw RuntimeException("unitbase中的starAttribute字段解析出错:${vo.starAttribute}")
                starAttributeMap.add(gg)
            }
            vo.starAttributeMap = starAttributeMap

            val animationList = LinkedList<Int>()
            val animationStrList = vo.attackAnimation.split("|")
            animationStrList.forEach {
                val aniTime = it.toIntOrNull()
                if (aniTime == null) {
                    throw RuntimeException("unitbase中的attackAnimation字段解析出错,ID为:${vo.id}")
                }
                animationList.add(aniTime)
            }
            vo.atkAnimationList = animationList

            tmpProtoMap[vo.id] = vo
            tmpProtos.add(vo)
        }
        this.protoMap = tmpProtoMap
        this.protos = tmpProtos
    }

    override fun postCheck(pcs: ProtoCacheStore) {
        for ((_, vo) in this.protoMap) {

            if (vo.skill1 != 0) {
                pcs.heroSkillProtoCache.heroSkillMap[vo.skill1]
                    ?: throw RuntimeException("unitbase.xml中的Skill字段没有在HeroSkill中找不到,字段ID是${vo.id},内容为:${vo.skill1} .")

            }

            if (vo.skill2 != 0) {
                pcs.heroSkillProtoCache.heroSkillMap[vo.skill2]
                    ?: throw RuntimeException("unitbase.xml中的Skill字段没有在HeroSkill中找不到,字段ID是${vo.id},内容为:${vo.skill1} .")

            }


            if (vo.skill3 != 0) {
                pcs.heroSkillProtoCache.heroSkillMap[vo.skill3]
                    ?: throw RuntimeException("unitbase.xml中的Skill字段没有在HeroSkill中找不到,字段ID是${vo.id},内容为:${vo.skill1} .")

            }


            if (vo.skill4 != 0) {
                pcs.heroSkillProtoCache.heroSkillMap[vo.skill4]
                    ?: throw RuntimeException("unitbase.xml中的Skill字段没有在HeroSkill中找到,字段ID是${vo.id},内容为:${vo.skill1} .")

            }


            if (vo.intSkill1 != 0) {
                pcs.intSkillCache.intSkillMap[vo.intSkill1]
                    ?: throw RuntimeException("unitbase.xml中的intSkill字段没有在intSkill中找不到,字段ID是${vo.id},内容为:${vo.intSkill1} .")

            }

            if (vo.intSkill2 != 0) {
                pcs.intSkillCache.intSkillMap[vo.intSkill2]
                    ?: throw RuntimeException("unitbase.xml中的intSkill字段没有在intSkill中找不到,字段ID是${vo.id},内容为:${vo.skill1} .")

            }


            if (vo.intSkill3 != 0) {
                pcs.intSkillCache.intSkillMap[vo.intSkill3]
                    ?: throw RuntimeException("unitbase.xml中的intSkill字段没有在intSkill中找不到,字段ID是${vo.id},内容为:${vo.skill1} .")

            }

        }


        for ((_, vo) in this.protoMap) {
            if (vo.npcType != UNIT_TYPE_HERO) {
                continue
            }
            val tmp = pcs.heroStarProtoCache.heroStarProtoCache[vo.id]
                ?: throw RuntimeException("unitbase.xml中的武将在武将升星表中找到1级的行数据,武将ID是:${vo.id}")
            tmp[1] ?: throw RuntimeException("unitbase.xml中的武将在武将升星表中找到1级的行数据,武将ID是:${vo.id}")
        }
    }

    // 获取指定模板编号的武将配置
    fun fetchProtoById(generalProtoId: Int): UnitBaseProto? {
        val generalsProto = this.protoMap[generalProtoId]

        return if (generalsProto != null) {
            generalsProto
        } else {
            null
        }
    }
}