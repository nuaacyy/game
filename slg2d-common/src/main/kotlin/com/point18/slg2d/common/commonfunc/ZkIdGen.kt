package com.point18.slg2d.common.commonfunc

import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.recipes.shared.SharedCount

//生成zkId
fun zkIdGen(client: CuratorFramework, path: String, seed: Int = 0, retry: Int = 5): Int? {
    // 共享计数器
    val counter = SharedCount(client, path, seed)
    try {
        counter.start()
        val newCount = counter.count + 1

        for (i in 1..retry) {
            val rt = counter.trySetCount(counter.versionedValue, newCount)
            if (!rt) {
                continue
            }
            return newCount
        }
        normalLog.warn("重试5次之后仍然无法设置新的Id")

    } catch (e: Exception) {
        normalLog.error("生成zkId异常:$e")
    } finally {
        counter.close()
    }

    return null
}