package tryakka.sharding

import akka.actor.PoisonPill
import akka.cluster.sharding.ShardRegion
import akka.persistence.AbstractPersistentActor
import akka.persistence.UntypedPersistentActor
import org.slf4j.LoggerFactory
import java.util.concurrent.ThreadLocalRandom

class UserActor : AbstractPersistentActor() {

    private val id: String
    private var count = 0 // it just do +

    init {
        this.id = this.self().path().name()

        logger.info("UserActor-{} created. ", id)
    }

    private fun updateState(msg: Any) {
        if (msg is Add) {
            count += msg.num
        }
    }

    override fun createReceiveRecover(): Receive {
        return receiveBuilder()
            .matchAny() { msg ->
                logger.info("UserActor-{} receive recover message", id)

                if (r.nextInt(10) > 7) {
                    logger.info("UserActor-{} will recover slow", id)
                    Thread.sleep(10000)
                }

                updateState(msg)

                logger.info("UserActor-{} recover done", id)

            }.build()

    }

    override fun createReceive(): Receive {
        return receiveBuilder()
            .match(Add::class.java) { msg ->
                logger.info("UserActor-{} receive: add {}", id, msg.num)
                persist<Any>(msg) { add -> updateState(msg) }
            }.match(Print::class.java) {
                logger.info("UserActor-{}: count={}", id, count)
            }.match(Stop::class.java) {
                logger.info("UserActor-{} will stop", id)
                context.parent().tell(
                    ShardRegion.Passivate(PoisonPill.getInstance()), self
                )
            }
            .build()
    }

    override fun persistenceId(): String {
        return "UserActor-" + id
    }

    override fun postStop() {
        logger.info("UserActor-{} stopped", id)
    }

    companion object {

        private val r = ThreadLocalRandom.current()
        private val logger = LoggerFactory.getLogger(UserActor::class.java)
    }
}