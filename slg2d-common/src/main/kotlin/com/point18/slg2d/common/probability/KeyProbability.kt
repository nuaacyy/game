package com.point18.slg2d.common.probability

import java.util.*
import java.util.concurrent.ThreadLocalRandom
import java.util.stream.Collector
import java.util.stream.Collectors
import kotlin.collections.LinkedHashMap

/**
 * 随机选择key
 */
class KeyProbability<K> {
    private var allProbability = 0
    private val datas = LinkedList<KProbability<K>>()

    fun add(key: K, probability: Int, append: Int = 0) {
        this.allProbability += probability
        datas.add(KProbability(key, probability, append))
    }

    /**
     * 根据所有数据随机一个
     * @factor 附加因子
     */
    fun randomKey(factor: Int = 0): K {
        if (datas.isEmpty()) {
            throw RuntimeException("KeyProbability :: 没有概率数据")
        }

        var tmpAllProbability = this.allProbability
        val tmpLinkedMap = LinkedHashMap<K, Int>()
        datas.forEach { data ->
            val tmpDataProb = data.allPro(factor)
            tmpAllProbability += tmpDataProb
            tmpLinkedMap[data.key] = tmpDataProb
        }

        if (tmpAllProbability == 0) {
            throw RuntimeException("当factor == $factor KeyProbability :: 没有概率数据")
        }

        val result = ThreadLocalRandom.current().nextInt(tmpAllProbability)
        for ((k, v) in tmpLinkedMap) {
            if (result < v) {
                return k
            }
        }
        return datas.last.key
    }

    fun getKeys(): List<K> {
        return datas.stream().map { data -> data.key }.collect(Collectors.toList())
    }

    fun getFirstKey(): K {
        if (datas.isEmpty()) {
            throw RuntimeException("KeyProbability :: 没有概率数据")
        }
        return datas.first.key
    }
}

/**
 * @basicProb 基本选择概率
 * @append 附加概率
 */
private data class KProbability<K>(val key: K, val basicProb: Int, val append: Int) {
    fun allPro(factor: Int): Int = basicProb + append * factor
}