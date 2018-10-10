package trynjy

import java.util.*

fun main(args: Array<String>) {
    match1(5, 3)
}


// 一个n位数乘以m等于n的反数算法
private fun match1(n: Int, m: Int) {
    while (true) {
        val randNums = getRandNum(n)
        if (randNums.nnn * m == randNums.mmm) {
            // 找到结果值
            println("找到${n}位数乘以${m}等于相反数的答案为 : ${randNums.nnn} * ${m} = ${randNums.mmm}")
            break
        }
    }
}

// 生成一个各位不重复且第一位不为0的n位数以及它的反数
data class Result(var nnn: Int, var mmm: Int)

private fun getRandNum(n: Int): Result {
    // 构造随机帮助结构体
    val nowNumList = mutableListOf<Int>()
    for (i in 1..n) {
        // 开始随机
        var r: Int
        while (true) {
            r = Random().nextInt(10)
            if (i == 1 && r == 0) {
                continue
            } else if (nowNumList.contains(r)) {
                continue
            } else {
                nowNumList.add(r)
                break
            }
        }
    }

    // list转成X位数
    var nnnString = ""
    for (m in nowNumList) {
        nnnString += m.toString()
    }

    var mmmString = ""
    for (i in nowNumList.size - 1 downTo 0) {
        mmmString += nowNumList[i].toString()
    }
    return Result(nnnString.toInt(), mmmString.toInt())
}