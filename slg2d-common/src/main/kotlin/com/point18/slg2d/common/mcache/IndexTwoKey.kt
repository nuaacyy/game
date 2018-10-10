package com.point18.slg2d.common.mcache

import java.util.*

class TwoKeyIndex<K, T, V> {
    private var index = hashMapOf<K, HashMap<T, V>>()

    private var fetch1Key: (v: V) -> K
    private var fetch2Key: (v: V) -> T
    private var ignoreIndexCheck: ((k: K, t: T) -> Boolean)? = null

    constructor(fetch1: (v: V) -> K, fetch2: (v: V) -> T) {
        this.fetch1Key = fetch1
        this.fetch2Key = fetch2
    }

    constructor (fetch1: (v: V) -> K, fetch2: (v: V) -> T, check: (k: K, t: T) -> Boolean) : this(fetch1, fetch2) {
        this.ignoreIndexCheck = check
    }

    fun indexLen(): Int {
        var num = 0
        for ((_, map) in this.index) {
            num += map.size
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
        valueMap[key2] = targetValue
    }

    fun deleteByKey(targetValue: V) {
        val key1 = this.fetch1Key(targetValue)
        val valMap = this.index[key1]
        if (valMap === null) {
            return
        }
        val key2 = this.fetch2Key(targetValue)
        valMap.remove(key2)
        if (valMap.isEmpty()) {
            this.index.remove(key1)
        }
    }

    fun deleteById(key1: K, key2: T): V? {
        val valMap = this.index[key1]
        if (valMap === null) {
            return null
        }
        val v = valMap.remove(key2)
        if (valMap.isEmpty()) {
            this.index.remove(key1)
        }
        return v
    }

    fun findByKey(key1: K, key2: T): V? {
        val valMap = this.index[key1]
        if (valMap === null) {
            return null
        }
        return valMap[key2]
    }

    fun findByOneKeyFilter(key1: K, filter: (value: V) -> Boolean) {
        val valMap = this.index[key1]
        if (valMap === null) {
            return
        }
        for ((_, value) in valMap) {
            if (!filter(value)) {
                break
            }
        }
    }

    fun map(filter: (v: V) -> Boolean) {
        for ((_, valMap) in this.index) {
            for ((_, value) in valMap) {
                if (!filter(value)) {
                    break
                }
            }
        }
    }

    fun keys(): LinkedList<K> {
        val keys = LinkedList<K>()
        for ((k, _) in this.index) {
            keys.add(k)
        }
        return keys
    }

    fun clear() {
        this.index.clear()
    }
}