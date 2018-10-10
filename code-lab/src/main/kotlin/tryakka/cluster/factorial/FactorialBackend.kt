package tryakka.cluster.factorial

import java.math.BigInteger
import java.util.concurrent.CompletableFuture
import akka.actor.AbstractActor
import akka.pattern.PatternsCS.pipe

// 后端是用于计算
class FactorialBackend : AbstractActor() {

    override fun createReceive(): AbstractActor.Receive {
        return receiveBuilder()
            .match(FactorialParam::class.java) { param ->
                val num = param.n
                val result = CompletableFuture.supplyAsync {
                    factorial(num)
                }.thenApply { factorial ->
                    FactorialResult(num, factorial)
                }

                pipe(result, context.dispatcher()).to(sender())

            }
            .build()
    }

    internal fun factorial(n: Int): BigInteger {
        var acc = BigInteger.ONE
        for (i in 1..n) {
            acc = acc.multiply(BigInteger.valueOf(i.toLong()))
        }
        return acc
    }
}
