package com.point18.slg2d.common.commonfunc

import java.util.*
import kotlin.collections.HashMap

// K：值对应的唯一键
// T：根据值获取的排序依据
// V：值
// U: 同名次排行依据
// comparator：比较器
// maxNum:排行榜最大存数 -1表示无限制
class RankMap<K, T : Comparable<T>, V, U>(
        private val fetchKey: (v: V) -> K,
        private val fetchSortAccording: (v: V) -> T,
        private val fetchSameScoreSortAccording: (v: V) -> U,
        private val comparator: Comparator<T>,
        private val sameScoreComparator: Comparator<U>,
        private val minValue: T,
        private val maxNum: Int = -1 // 设置上榜最低条件 -1表示无条件
) {
    private val rankMap = TreeMap<T, TreeMap<U, V>>(comparator)
    private val indexMap = HashMap<K, T>() // 保存对象的主排序值
    private val indexMap1 = HashMap<K, U>() // 保存对象的次排序值

    // 插入/更新值
    fun updateValue(v: V) {
        //先移除旧记录
        removeValue(v)

        // 重新插入记录
        val t = fetchSortAccording(v)
        if (t > minValue) {
            val k = fetchKey(v)
            val u = fetchSameScoreSortAccording(v)
            indexMap[k] = t
            indexMap1[k] = u
            val sameRankMap = rankMap.getOrPut(t) { TreeMap(sameScoreComparator) }
            sameRankMap[u] = v

            if (maxNum != -1 && queryAllJoinNum() > maxNum) {
                // 如果排行榜有存数上限需求 并且当前已经超出 就删除尾部元素
                val delValue = rankMap.lastEntry().value.lastEntry().value
                removeValue(delValue)
            }
        }
    }

    // 移除值
    fun removeValue(v: V) {
        val k = fetchKey(v)
        val oldT = indexMap[k]
        if (oldT == null) {
            return
        }
        val oldU = indexMap1[k]
        if (oldU == null) {
            return
        }
        
        // 移除旧记录
        indexMap.remove(k)
        indexMap1.remove(k)
        val oldRankMap = rankMap[oldT]
        if (oldRankMap != null) {
            oldRankMap.remove(oldU)
            if (oldRankMap.count() == 0) {
                rankMap.remove(oldT)
            }
        }
    }

    // 根据键查找当前值
    fun findByKey(k: K): V? {
        val t = indexMap[k]
        if (t == null) {
            return null
        }
        val u = indexMap1[k]
        if (u == null) {
            return null
        }
        val treeMap = this.rankMap[t]
        if (treeMap == null) {
            return null
        }
        return treeMap[u]
    }

    // 查询前N名
    fun queryValue(num: Int): LinkedList<V> {
        val valueList = LinkedList<V>()
        val iterator1 = rankMap.iterator()
        while (iterator1.hasNext() && valueList.count() < num) {
            val valueEntry = iterator1.next()
            val iterator2 = valueEntry.value.iterator()
            while (iterator2.hasNext() && valueList.count() < num) {
                valueList.add(iterator2.next().value)
            }
        }
        return valueList
    }

    // 获取总参与排行人数
    fun queryAllJoinNum(): Int {
        var joinNum = 0
        val iterator1 = rankMap.iterator()
        while (iterator1.hasNext()) {
            val valueEntry = iterator1.next()
            joinNum += valueEntry.value.count()
        }
        return joinNum
    }

    // 查询第N名 - 第M名
    fun queryPartValue(startNum: Int, endNum: Int): LinkedList<V> {
        var nowValue = 0
        val valueList = LinkedList<V>()
        val iterator1 = rankMap.iterator()
        while (iterator1.hasNext()) {
            val valueEntry = iterator1.next()
            val iterator2 = valueEntry.value.iterator()
            while (iterator2.hasNext()) {
                nowValue += 1
                val aValue = iterator2.next()
                if (nowValue in startNum..endNum) {
                    valueList.add(aValue.value)
                }
            }
        }

        return valueList
    }

    // 通过V传出外层的排行依据
    fun findTByV(v: V): T {
        return fetchSortAccording(v)
    }
}