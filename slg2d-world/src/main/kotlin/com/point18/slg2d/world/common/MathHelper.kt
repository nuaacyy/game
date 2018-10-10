package com.point18.slg2d.world.common

import com.point18.slg2d.common.commonfunc.getRandInt
import java.util.*

// 从随机范围中获取一个属于这个范围的值:[start,end)
fun getRandRange(start: Int, end: Int): Int {
    if (start > end) {
        return 0
    }

    if (start == end) {
        return start
    }

    return start + getRandInt(end - start)
}

// 从一组整数中随一个:[]{1,3,6} === 10%概率得到第1个元素，30%概率得到第2个元素，60%概率得到第3个元素。返回值：数组下标索引
fun getRandMulti(rates: List<Int>): Int {
    //预先随机1个值[0,10000)
    val rd = getRandInt(10000)
    var idx = 0

    //先计算配置的概率总和
    var total = 0
    for (v in rates) {
        total += v
    }

    //将每个原有概率转换成万份比概率
    var rate = 0
    for (i in rates.indices) {
        rate += rates[i]
        if (Math.floor((rate * 10000.0 / total)) > rd) {
            idx = i
            break
        }
    }

    return idx
}
