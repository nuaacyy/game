package com.point18.slg2d.common.mcache

import java.util.*

class OneKeyIndexSlice<K, V> {
    private var index = hashMapOf<K, LinkedList<V>>()
    private var fetch1Key: (v: V) -> K
    private var equal: (itEle: V, targetVal: V) -> Boolean
    private var ignoreIndexCheck: ((k: K) -> Boolean)? = null

    constructor(fetch: (v: V) -> K, equalFunc: (itEle: V, targetVal: V) -> Boolean) {
        this.fetch1Key = fetch
        this.equal = equalFunc
    }

    constructor (fetch: (v: V) -> K, equalFunc: (itEle: V, targetVal: V) -> Boolean, check: (k: K) -> Boolean) : this(
        fetch,
        equalFunc
    ) {
        this.ignoreIndexCheck = check
    }

    fun indexLen(): Int {
        var num = 0
        for ((_, list) in this.index) {
            num += list.size
        }
        return num
    }

    fun updateByKey(newKey: K, targetValue: V, updateKeyCb: () -> Unit) {
        val oldKey = this.fetch1Key(targetValue)
        if (newKey == oldKey) {
            return
        }

        this.deleteByKey(targetValue)

        val check = this.ignoreIndexCheck
        if (check == null || !check(newKey)) {
            this.move2NewIndex(newKey, targetValue)
        }

        updateKeyCb()
    }

    fun insertByKey(targetValue: V) {
        val key = this.fetch1Key(targetValue)
        val check = this.ignoreIndexCheck
        if (check == null || !check(key)) {
            this.move2NewIndex(key, targetValue)
        }
    }

    private fun move2NewIndex(newKey: K, targetValue: V) {
        val valList = this.index.getOrPut(newKey) { LinkedList() }
        valList.add(targetValue)
    }

    fun deleteByKey(targetValue: V) {
        val key = this.fetch1Key(targetValue)
        val valList = this.index[key]
        if (valList === null) {
            return
        }
        valList.removeIf { value: V -> this.equal(value, targetValue) }
        if (valList.size == 0) {
            this.index.remove(key)
        }
    }

    fun deleteWithKey(key: K) {
        this.index.remove(key)
    }

    fun clear() {
        this.index.clear()
    }

    fun findByKey(key: K, filter: (value: V) -> Boolean) {
        if (!this.index.containsKey(key)) {
            return
        }
        val valList = this.index.getValue(key)
        for (value in valList) {
            if (!filter(value)) {
                break
            }
        }
    }

    fun findByKey(key: K): LinkedList<V>? {
        return this.index[key]
    }

    fun map(filter: (v: V) -> Boolean) {
        for ((_, valList) in this.index) {
            for (value in valList) {
                if (!filter(value)) {
                    return
                }
            }
        }
    }
}