package com.point18.slg2d.home.common

import com.point18.slg2d.common.constg.MAIN_HERO
import com.point18.slg2d.common.constg.PROPS_QUALITY_BAI
import com.point18.slg2d.common.constg.PROPS_QUALITY_CHENG
import com.point18.slg2d.home.dc.BagDC
import com.point18.slg2d.home.dc.HeroDC
import com.point18.slg2d.home.dc.HomePlayer
import com.point18.slg2d.home.dc.NewEquipDC
import com.point18.slg2d.common.pc.pcs

// 玩家身上的装备套装散件个数统计
data class KingEquipSuitNumInfo(
    var Num: Int,     // 本套装散件数量
    var QualityMap: HashMap<Int, Int> // 本套装各品质数量 key : 品质 value : 数量
)

// 玩家身上的装备套装散件个数统计
data class KingEquipSuits(
    var SuitId: Int, // 套装唯一ID
    var Quality: Int // 套装品质
)

// 获取一个君主身上所有的装备的效果值
fun getKingEquipEffect(player: HomePlayer, heroDC: HeroDC,
                       newEquipDC: NewEquipDC, bagDC: BagDC): (HashMap<Int, Int>) {
    val allEffectMap = hashMapOf<Int, Int>()

    val hero = heroDC.findHeroById(player.mainHeroId)
    if (hero == null) {
        return allEffectMap
    }

    if (hero.mainHeroState != MAIN_HERO) {
        // 领主状态异常 天赋模块与装备模块失效
        return allEffectMap
    }

    // 在获取武将穿戴的装备信息
    val equips = newEquipDC.findKingEquipsByPlayerId(bagDC, player.playerId)

    for (equip in equips) {

        val equipProto = pcs.equipCache.equipProtoMap[equip.equipProtoId]
        if (equipProto == null) {
            continue
        }
        // 获取装备的基础属性
        for ((effType, effValue) in equipProto.basicEffectMap) {
            val vv = allEffectMap[effType]
            if (vv == null) {
                allEffectMap[effType] = effValue
            } else {
                allEffectMap[effType] = vv + effValue
            }
        }

        // 增加宝石的属性
        for ((_, cardProtoId) in equip.cardInfoMap) {
            val cardProto = pcs.equipCache.equipProtoMap[cardProtoId]
            if (cardProto == null) {
                continue
            }
            for ((effType, effValue) in cardProto.basicEffectMap) {
                val vv = allEffectMap[effType]
                if (vv == null) {
                    allEffectMap[effType] = effValue
                } else {
                    allEffectMap[effType] = vv + effValue
                }
            }
        }
    }

    return allEffectMap
}

// 返回参数解析  第一个int表示激活的套装的品质  map表示激活这个套装消耗掉的具体品质情况  map<品质>数量
data class FindWhatQualityZReturn(var q: Int, var reallyCost: HashMap<Int, Int>)

fun findWhatQualityZ(suitNum: KingEquipSuitNumInfo, j: Int): (FindWhatQualityZReturn) {
    // 统计各种品质的向上兼容的真实数量,例: 3个橙色3个紫色  map内容就是橙色3个,紫色6个(3+3)
    var tmpJ = j
    val reallyMap = hashMapOf<Int, Int>()
    for (i in PROPS_QUALITY_BAI..PROPS_QUALITY_CHENG) {
        for ((q, n) in suitNum.QualityMap) {
            if (q >= i) {
                val nn = reallyMap[i]

                if (nn == null) {
                    continue
                }
                reallyMap[i] = nn + n
            }
        }
    }
    var whatQuality = 0
    var reallyCost = hashMapOf<Int, Int>()

    // 遍历上面那个for找到的数据,找到激活这个套装的品质
    for ((q, n) in reallyMap) {
        if (n >= tmpJ && q > whatQuality) {
            reallyCost = hashMapOf()
            // 确定套装品质
            whatQuality = q
            // 计算具体消耗
            for (k in q..PROPS_QUALITY_CHENG) {
                val num = suitNum.QualityMap[k]
                if (num == null) {
                    continue
                }
                if (num >= tmpJ) {
                    reallyCost[k] = tmpJ
                    break
                } else {
                    reallyCost[k] = num
                    tmpJ -= num
                    continue
                }
            }
        }
    }

    return FindWhatQualityZReturn(whatQuality, reallyCost)
}
