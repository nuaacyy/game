package tryGrpc

import io.grpc.ManagedChannelBuilder
import pb4battle.BattleMsg
import pb4battle.BattleServiceGrpc.newBlockingStub

fun main(args: Array<String>) {
    val request = BattleMsg.DoHeroFightReq.newBuilder().build()
    val channel = ManagedChannelBuilder.forAddress("localhost", 2018).usePlaintext().build()
    val stub = newBlockingStub(channel)
    for (i in 0..10) {
        val resp = stub.doHeroFight(request)
        println(resp)
    }
}