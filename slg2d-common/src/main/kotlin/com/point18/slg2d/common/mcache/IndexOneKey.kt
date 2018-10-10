package com.point18.slg2d.common.mcache

class OneKeyIndex<K, V> {
    val index = hashMapOf<K, V>()
    val fetch1Key: (v: V) -> K  // 输入值，返回键的函数
    private var ignoreIndexCheck: ((k: K) -> Boolean)? = null // 输入键，返回bool的函数

    constructor(fetch: (v: V) -> K) {
        this.fetch1Key = fetch
    }

    constructor (fetch: (v: V) -> K, check: (k: K) -> Boolean) : this(fetch) {
        this.ignoreIndexCheck = check
    }

    fun indexLen(): Int {
        return this.index.size
    }

    fun updateByKey(newKey: K, targetValue: V, updateKeyCb: () -> Unit) {
        val oldKey = this.fetch1Key(targetValue)
        if (newKey == oldKey) {
            return
        }

        this.deleteByKey(targetValue)

        val check = this.ignoreIndexCheck
        if (check === null || !check(newKey)) {
            this.move2NewIndex(newKey, targetValue)
        }

        updateKeyCb()
    }

    fun insertByKey(targetValue: V) {
        val key = this.fetch1Key(targetValue)
        val check = this.ignoreIndexCheck
        if (check === null || !check(key)) {
            this.move2NewIndex(key, targetValue)
        }
    }

    private fun move2NewIndex(newKey: K, targetValue: V) {
        this.index[newKey] = targetValue
    }

    fun deleteByKey(targetValue: V): V? {
        val key = this.fetch1Key(targetValue)
        return this.index.remove(key)
    }

    fun deleteWithKey(key: K) {
        this.index.remove(key)
    }

    fun clear() {
        this.index.clear()
    }

    fun findByKey(key: K): V? {
        return this.index[key]
    }

    fun containsKey(key: K): Boolean {
        return this.index.containsKey(key)
    }

    inline fun map(filter: (v: V) -> Boolean) {
        for ((_, value) in this.index) {
            if (!filter(value)) {
                break
            }
        }
    }
}