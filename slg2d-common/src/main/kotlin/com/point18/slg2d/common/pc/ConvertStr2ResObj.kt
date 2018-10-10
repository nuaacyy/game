package com.point18.slg2d.common.pc

import com.google.common.base.Strings
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.resultcode.ResultCode
import java.io.Serializable
import java.util.*
import kotlin.math.roundToInt

data class ResVo(val resType: ResourceType, val subType: Long, val num: Long) : Serializable

/**
从模板中把奖励字符串解析成切片
 */

fun resStringToResVoList(resString: String): List<ResVo>? {
    val resVos: LinkedList<ResVo> = LinkedList()
    if (resString == "0" || Strings.isNullOrEmpty(resString)) {
        return resVos
    }

    val resStrs = resString.split("|")
    for (oneRes in resStrs) {
        val resVo = resStringToResVo(oneRes)
        if (resVo == null) {
            normalLog.error("解析资源配置错误:$resString")
            continue
        }
        resVos.add(resVo)
    }

    return resVos
}

fun resStringToResVo(oneResStr: String): ResVo? {
    val res = oneResStr.split(";")
    if (res.size != 3) {
        return null
    }

    val resType = res[0].toInt()
    // 检测配置奖励头是否正确
    if (!isResType(resType)) {
        return null
    }

    val particular = res[1].toInt()  // 第二位截到的是一个特殊的值,如果第一位截到的是道具 这个值是道具ID  如果不是道具 这个值必定为0

    if (resType == RES_PROPS && particular == 0) {
        // 格式不对
        return null
    }
    if (resType != RES_PROPS && particular != 0) {
        // 格式不对
        return null
    }

    val subType = res[1].toLong()
    val num = res[2].toLong()
    return ResVo(resType, subType, num)
}

/**
把奖励切片转换成奖励字符串使用
 */
fun resVoToResString(resVos: List<ResVo>?): String {
    var resString = ""
    if (resVos == null) {
        return resString
    }

    resVos.indices.forEach { index ->
        val resVoStr = "${resVos[index].resType};${resVos[index].subType};${resVos[index].num}"
        if (index != 0) {
            resString = "$resString|$resVoStr"
        } else {
            resString = resVoStr
        }
    }

    return resString
}

/**
把奖励切片翻X倍,用于配置内容是单价的功能
 */
data class ResVoAddXFunRet(val res: Boolean, var listOfResVo: LinkedList<ResVo>)

fun resVoAddX(resVos: List<ResVo>?, x: Int): ResVoAddXFunRet {
    val newResVo = LinkedList<ResVo>()

    if (resVos == null) {
        return ResVoAddXFunRet(false, newResVo)
    }

    for (resVo in resVos) {
        // 避免刷资源的情况
        if (resVo.num < 0) {
            println("ResVoAddX() 资源问题BUG：${resVo.resType}, ${resVo.num}")
            return ResVoAddXFunRet(false, newResVo)
        }

        when (resVo.resType) {

            RES_COIN, RES_HERO_EXP_POOL, RES_ALLIANCE_COIN, RES_FOOD, RES_WOOD, RES_STONE, RES_IRON, RES_GOLD,
            RES_DECREE, RES_FAME, RES_BIND_GOLD, RES_HONOR, RES_JJC_COIN, RES_CASINO_COIN, RES_SILVER_COIN,
            RES_GOLD_COIN -> {
                val r = ResVo(resVo.resType, NOT_PROPS_SUB_TYPE, resVo.num * x)
                newResVo.add(r)
            }

            RES_PROPS -> {
                val r = ResVo(resVo.resType, resVo.subType, resVo.num * x)
                newResVo.add(r)
            }

            else -> {
                println("检测奖励格式的时候发现未定义的奖励类型-${resVo.resType}")
                return ResVoAddXFunRet(false, newResVo)
            }
        }
    }

    return ResVoAddXFunRet(true, newResVo)
}

//资源合并
fun resVoCombine(resVos: LinkedList<ResVo>?): LinkedList<ResVo> {
    if ((resVos == null)) {
        return LinkedList()
    }
    if ((resVos.size) < 2) {
        return resVos
    }
    val resMap = hashMapOf<ResourceType, Long>()
    val propMap = hashMapOf<Long, Long>()

    loop@ for (vo in resVos) {
        when (vo.resType) {
            RES_PROPS -> {
                propMap[vo.subType] = vo.num + (propMap[vo.subType] ?: 0)
            }
            else -> {
                resMap[vo.resType] = vo.num + (resMap[vo.resType] ?: 0)
            }
        }
    }

    val newResVos = LinkedList<ResVo>()
    for ((resType, num) in resMap) {
        val resVo = ResVo(resType, NOT_PROPS_SUB_TYPE, num)
        newResVos.add(resVo)
    }
    for ((propId, num) in propMap) {
        val resVo = ResVo(RES_PROPS, propId, num)
        newResVos.add(resVo)
    }
    return newResVos
}

/**
把奖励切片打折
 */
data class ResVoDiscountFunRet(val res: Boolean, var listOfResVo: LinkedList<ResVo>)

fun resVoDiscount(resVos: LinkedList<ResVo>?, x: Int): ResVoDiscountFunRet {
    val discount = (x.toDouble()) / (10000.toDouble())
    val newResVo = LinkedList<ResVo>()
    if (resVos == null) {
        return ResVoDiscountFunRet(false, newResVo)
    }

    for (resVo in resVos) {
        // 避免刷资源的情况
        if (resVo.num < 0) {
            println("ResVoAddX() 资源问题BUG：Type：${resVo.resType}，Num：${resVo.num}.")
            return ResVoDiscountFunRet(false, newResVo)
        }

        when (resVo.resType) {
            RES_COIN,
            RES_ALLIANCE_COIN,
            RES_HERO_EXP_POOL,
            RES_FOOD,
            RES_WOOD,
            RES_STONE,
            RES_IRON,
            RES_GOLD,
            RES_DECREE,
            RES_FAME,
            RES_BIND_GOLD,
            RES_HONOR,
            RES_JJC_COIN,
            RES_ACTION_NUM,
            RES_CASINO_COIN,
            RES_SILVER_COIN,
            RES_GOLD_COIN -> {
                val n = ((resVo.num.toDouble() * discount).roundToInt()).toLong()
                val r = ResVo(resVo.resType, NOT_PROPS_SUB_TYPE, n)
                newResVo.add(r)
            }

            else -> {
                println("检测奖励格式的时候发现未定义的奖励类型-${resVo.resType}.")
                return ResVoDiscountFunRet(false, newResVo)
            }
        }
    }

    return ResVoDiscountFunRet(true, newResVo)
}

data class Props2GoldCostFunRet(val res: ResultCode, var listOfResVo: LinkedList<ResVo>)

// 道具不足转钻石消耗
fun props2GoldCost(cost: ResVo): Props2GoldCostFunRet {
    if (cost.resType != RES_PROPS) {
        return Props2GoldCostFunRet(ResultCode.LESS_RESOUCE, LinkedList())
    }

    val propsProto = pcs.equipCache.equipProtoMap[cost.subType.toInt()] ?: return Props2GoldCostFunRet(
        ResultCode.NO_PROTO,
        LinkedList()
    )
    if (propsProto.fastBuy == "0") {
        return Props2GoldCostFunRet(ResultCode.PARAMETER_ERROR, LinkedList())
    }

    val needRes = LinkedList(propsProto.fastBuyMap)
    return Props2GoldCostFunRet(ResultCode.SUCCESS, needRes)
}

/**
从模板中把几率掉落规则的字符串解析成map来使用,
最后一个参数返回的是总权值
 */
data class ResStringToDropBagFunRet(var map: HashMap<String, Int>?, var ok: Boolean, var int: Int)

fun resStringToDropBag(resString: String): ResStringToDropBagFunRet {
    var sum = 0
    if (resString == "0") {
        return ResStringToDropBagFunRet(null, true, 0)
    }

    //11:1019_1

    var nowJilv = 0 // 当前的随机值
    val dropMap = hashMapOf<String, Int>()
    val ress = resString.split("|")
    for (oneRes in ress) {
        val res = oneRes.split(";")
        //res[0] = 11
        //res[1] = 1019_1
        if (res.size != 2) {
            return ResStringToDropBagFunRet(null, false, 0)
        }

        val v = res[0].toIntOrNull()
        val jilv = res[1].toIntOrNull()
        if (v == null || jilv == null) {
            return ResStringToDropBagFunRet(null, false, 0)
        }

        /*
        if err != null {
            return null, false, 0
        }
        */
        sum += jilv

        val maxJilv = nowJilv + jilv

        val key = (nowJilv.toString()) + "_" + (maxJilv.toString())
        dropMap[key] = v
        nowJilv = maxJilv + 1
    }

    return ResStringToDropBagFunRet(dropMap, true, sum)
}

/**
从一个掉落库中掉一个value出来
 */
fun findValueFromDropBag(dropBag: Map<String, Int>): Int {
    val key = com.point18.slg2d.common.commonfunc.getRandInt(10000)
    for ((k, v) in dropBag) {
        val ks = k.split("_")
        val min = (ks[0].toInt())
        val max = (ks[1].toInt())
        if ((key >= min) && (key <= max)) {
            return v
        }
    }
    return 0
}

//掉落包字符串转换

fun dropStringToDrops(dropString: String): LinkedList<Drop>? {
    val drops = LinkedList<Drop>()
    if (dropString == "0") {
        return drops
    }
    val dropStrs = dropString.split("|")
    for (dropStr in dropStrs) {
        val paras = dropStr.split(";")
        if ((paras.size) < 3) {
            return null
        }
        val id = (paras[0]).toIntOrNull()
        if (id == null) {
            return null
        }
        val num = (paras[1]).toIntOrNull()
        if (num == null) {
            return null
        }
        val percent = (paras[2]).toIntOrNull()
        if (percent == null) {
            return null
        }
        drops.add(Drop(id, num, percent))

    }
    return drops
}