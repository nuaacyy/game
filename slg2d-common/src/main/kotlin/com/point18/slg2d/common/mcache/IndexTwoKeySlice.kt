package com.point18.slg2d.common.mcache

import java.util.*

class TwoKeyIndexSlice<K, T, V> {
    private var index = hashMapOf<K, HashMap<T, LinkedList<V>>>()
    private var fetch1Key: (v: V) -> K
    private var fetch2Key: (v: V) -> T
    private var equal: (itEle: V, targetVal: V) -> Boolean
    private var ignoreIndexCheck: ((k: K, t: T) -> Boolean)? = null

    constructor(fetch1: (v: V) -> K, fetch2: (v: V) -> T, equalFunc: (itEle: V, targetVal: V) -> Boolean) {
        this.fetch1Key = fetch1
        this.fetch2Key = fetch2
        this.equal = equalFunc
    }

    constructor (
        fetch1: (v: V) -> K,
        fetch2: (v: V) -> T,
        equalFunc: (itEle: V, targetVal: V) -> Boolean,
        check: (k: K, t: T) -> Boolean
    ) : this(fetch1, fetch2, equalFunc) {
        this.ignoreIndexCheck = check
    }

    fun indexLen(): Int {
        var num = 0
        for ((_, valMap) in this.index) {
            for ((_, list) in valMap) {
                num += list.size
            }
        }
        return num
    }

    fun updateByKey(newKey1: K, newKey2: T, targetValue: V, updateKeyCb: () -> Unit) {
        val oldKey1 = this.fetch1Key(targetValue)
        val oldKey2 = this.fetch2Key(targetValue)
        if (newKey1 == oldKey1 && newKey2 == oldKey2) {
            return
        }

        this.deleteByKey(targetValue)

        val check = this.ignoreIndexCheck
        if (check == null || !check(newKey1, newKey2)) {
            this.move2NewIndex(newKey1, newKey2, targetValue)
        }

        updateKeyCb()
    }

    fun insertByKey(targetValue: V) {
        val key1 = this.fetch1Key(targetValue)
        val key2 = this.fetch2Key(targetValue)

        val check = this.ignoreIndexCheck
        if (check == null || !check(key1, key2)) {
            this.move2NewIndex(key1, key2, targetValue)
        }
    }

    private fun move2NewIndex(key1: K, key2: T, targetValue: V) {
        val valueMap = this.index.getOrPut(key1) { hashMapOf() }
        val valueList = valueMap.getOrPut(key2) { LinkedList() }
        valueList.add(targetValue)
    }

    fun deleteByKey(targetValue: V) {
        val key1 = this.fetch1Key(targetValue)
        val valMap = this.index[key1]
        if (valMap === null) {
            return
        }
        val key2 = this.fetch2Key(targetValue)
        val valList = valMap[key2]
        if (valList === null) {
            return
        }
        valList.removeIf { value: V -> this.equal(value, targetValue) }
        if (valList.size == 0) {
            valMap.remove(key2)
        }
        if (valMap.isEmpty()) {
            this.index.remove(key1)
        }
    }

    fun findByKey(key1: K, key2: T, filter: (value: V) -> Boolean) {
        val valMap = this.index[key1]
        if (valMap === null) {
            return
        }
        val valList = valMap[key2]
        if (valList === null) {
            return
        }
        for (value in valList) {
            if (!filter(value)) {
                break
            }
        }
    }

    fun findByOneKeyFilter(key1: K, filter: (value: V) -> Boolean) {
        val valMap = this.index[key1]
        if (valMap === null) {
            return
        }
        for ((_, valList) in valMap) {
            for (value in valList) {
                if (!filter(value)) {
                    break
                }
            }
        }
    }

    fun map(filter: (v: V) -> Boolean) {
        for ((_, valMap) in this.index) {
            for ((_, valList) in valMap) {
                for (value in valList) {
                    if (!filter(value)) {
                        return
                    }
                }
            }
        }
    }
}