package com.point18.slg2d.common.pc

import com.point18.slg2d.common.commonfunc.getRandIntAtArea
import com.point18.slg2d.common.commonfunc.ran
import java.util.*

interface IPercentObj {
    fun getRate(): Int
}

//从列表中随机选择一项
fun <T : IPercentObj> randomSelect(selectObjList: List<T>, ranSeed: Random = ran, defaultTotalRate: Int = 0): T? {
    var totalRate = defaultTotalRate
    if (totalRate == 0) {
        for (obj in selectObjList) {
            totalRate += obj.getRate()
        }
    }
    val rand = getRandIntAtArea(ranSeed, totalRate)
    var tempRate = 0
    for (obj in selectObjList) {
        if (rand <= tempRate + obj.getRate()) {
            return obj
        }
        tempRate += obj.getRate()
    }
    return null
}