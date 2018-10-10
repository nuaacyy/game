package trykotlin.completablefuture.clientwait

import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors

fun main(args: Array<String>) {
    println("----- 开始演示 CompletableFuture 自我等待 机制")

    var future: CompletableFuture<Int>? = null
    val es = Executors.newFixedThreadPool(10)
    val f = es.submit() {
        future = CompletableFuture<Int>()
        val f = future

        println("${Thread.currentThread().id} 准备进入CompletableFuture的自我等待")
        f?.get()

        println("${Thread.currentThread().id} CompletableFuture的自我等待 结束")
    }

    Thread.sleep(2000)
    println("${Thread.currentThread().id} 外部线程短暂等待后，准备让目标等待结束")
    future?.complete(100)

    Thread.sleep(2000)
    es.shutdown()
}