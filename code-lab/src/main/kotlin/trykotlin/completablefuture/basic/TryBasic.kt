package trykotlin.completablefuture.basic

import java.util.concurrent.Executors

fun main(args: Array<String>) {
    // 固定线程数量的线程池执行器
    println("启动逻辑的线程：${Thread.currentThread().id}")
    val es = Executors.newFixedThreadPool(10)
    val f = es.submit<Int>() {
        println("任务执行的线程：${Thread.currentThread().id}")
        100
    }

    println(f.get())

    println("尝试关闭")
    es.shutdown()
}