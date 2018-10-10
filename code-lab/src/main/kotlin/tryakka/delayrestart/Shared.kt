package tryakka.delayrestart

import akka.actor.AbstractActor
import akka.pattern.BackoffSupervisor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import scala.concurrent.duration.FiniteDuration
import java.util.concurrent.TimeUnit

val logger: Logger = LoggerFactory.getLogger("codelab")

class MyException(val info: String) : RuntimeException(info) {}

class MayFailActorWithReset : AbstractActor() {

	override fun preStart() {
		logger.info("preStart")
		context.system.scheduler().scheduleOnce(
			FiniteDuration(1, TimeUnit.SECONDS), self, "RESET", context.dispatcher(), self
		)
	}

	override fun postStop() {
		logger.info("postStop")
	}

	override fun createReceive(): Receive {
		return receiveBuilder()
			.matchEquals("FAIL") {
				logger.info("接受到 FAIL")
				throw RuntimeException("failed")
			}
			.matchEquals("STOP") {
				logger.info("接受到 STOP")
				context.stop(self)
			}
			.matchEquals("RESET") {
				logger.info("接受到 RESET，尝试发送reset给parent ${context.parent.path()}")
//				context.parent.tell(BackoffSupervisor.getCurrentChild(), self)
//				context.parent.tell(BackoffSupervisor.Reset, self)
			}
			.match(BackoffSupervisor.CurrentChild::class.java) {
				logger.info("${it}")
			}
			.build()
	}
}

class MayFailActorWithMyEx : AbstractActor() {

	override fun preStart() {
		logger.info("preStart")
		context.system.scheduler().scheduleOnce(
			FiniteDuration(1, TimeUnit.SECONDS), self, "RESET", context.dispatcher(), self
		)
	}

	override fun postStop() {
		logger.info("postStop")
	}

	override fun createReceive(): Receive {
		return receiveBuilder()
			.matchEquals("FAIL") {
				logger.info("接受到 FAIL")
				throw RuntimeException("failed")
			}
			.matchEquals("FAILMyEx") {
				logger.info("接受到 FAILMyEx")
				throw MyException("failed")
			}
			.matchEquals("STOP") {
				logger.info("接受到 STOP")
				context.stop(self)
			}
			.match(BackoffSupervisor.CurrentChild::class.java) {
				logger.info("${it}")
			}
			.build()
	}
}

class MayFailActor : AbstractActor() {

	override fun preStart() {
		logger.info("preStart")
	}

	override fun postStop() {
		logger.info("postStop")
	}

	override fun createReceive(): Receive {
		return receiveBuilder()
			.matchEquals("FAIL") {
				logger.info("接受到 FAIL")
				throw RuntimeException("failed")
			}
			.matchEquals("STOP") {
				logger.info("接受到 STOP")
				context.stop(self)
			}
			.build()
	}
}