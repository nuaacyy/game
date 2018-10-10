package tryakka.stop

import akka.actor.AbstractActor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props

fun main(args: Array<String>) {
	val system = ActorSystem.create("tryStop")

	val actor = system.actorOf(TrySelfStopActor.props())

	Thread.sleep(2000)

	actor.tell("StopSelf", ActorRef.noSender())

	println(">>> Press ENTER to exit <<<")
	try {
		System.`in`.read()
	} finally {
		system.terminate()
	}

}

class TrySelfStopActor : AbstractActor() {

	companion object {
		fun props(): Props {
			return Props.create(TrySelfStopActor::class.java)
		}
	}

	override fun preStart() {
		println("preStart")
	}

	override fun createReceive(): Receive {
		return receiveBuilder()
			.matchEquals("StopSelf", {
				println("stopped self")
				context.stop(self)
			})
			.build()
	}
}