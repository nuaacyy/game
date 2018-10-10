package trykotlin.completablefuture.getjoindiff

import java.util.concurrent.CompletableFuture

fun main(args: Array<String>) {

    tryGet()

    tryJoin()

}

fun tryJoin() {
    println("尝试Join，这个会包装成Uncheck异常（普通异常）抛出")
    val future = CompletableFuture.supplyAsync() {
        throw RuntimeException("22")
    }

    future.join()
}

fun tryGet() {
    println("尝试Get，这个会包装成ExecutionException异常（必须捕获并处理的异常）抛出")
    val future = CompletableFuture.supplyAsync() {
        throw RuntimeException("11")
    }

    future.get()
}