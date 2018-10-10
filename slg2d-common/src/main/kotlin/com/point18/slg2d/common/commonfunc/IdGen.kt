package com.point18.slg2d.common.commonfunc

/**
 * SnowFlake算法 64位Long类型生成唯一ID 第一位0，表明正数 2-42，41位，表示毫秒时间戳差值，起始值自定义
 * 43-52，10位，机器编号，5位数据中心编号，5位进程编号 53-64，12位，毫秒内计数器 本机内存生成，性能高
 *
 * 主要就是三部分： 时间戳，进程id，序列号 时间戳41，id10位，序列号12位
 */
class IdGenerator(private val processId: Long) {

    private var lastTs = 0L
    private val processIdBits = 10

    private var sequence = 0L
    private val sequenceBits = 12

    init {
        if (processId > (1 shl processIdBits) - 1) {
            throw RuntimeException(
                "进程ID超出范围，设置位数" + processIdBits + "，最大"
                    + ((1 shl processIdBits) - 1)
            )
        }
    }

    private fun timeGen(): Long {
        return System.currentTimeMillis()
    }

    @Synchronized
    fun nextId(): Long {
        var ts = timeGen()
        if (ts < lastTs) {// 刚刚生成的时间戳比上次的时间戳还小，出错
            throw RuntimeException("时间戳顺序错误")
        }
        if (ts == lastTs) {// 刚刚生成的时间戳跟上次的时间戳一样，则需要生成一个sequence序列号
            // sequence循环自增
            sequence = (sequence + 1) and ((1 shl sequenceBits).toLong() - 1)
            // 如果sequence=0则需要重新生成时间戳
            if (sequence == 0L) {
                // 且必须保证时间戳序列往后
                ts = nextTs(lastTs)
            }
        } else {// 如果ts>lastTs，时间戳序列已经不同了，此时可以不必生成sequence了，直接取0
            sequence = 0L
        }
        lastTs = ts// 更新lastTs时间戳
        return (((ts - beginTs) shl (processIdBits + sequenceBits)) or (processId shl sequenceBits)
            or sequence)
    }

    protected fun nextTs(lastTs: Long): Long {
        var ts = timeGen()
        while (ts <= lastTs) {
            ts = timeGen()
        }
        return ts
    }

    companion object {
        private val beginTs = 1483200000000L
    }
}