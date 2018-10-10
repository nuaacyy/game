package tryakka.stop

import akka.actor.*

fun main(args: Array<String>) {
	val system = ActorSystem.create("tryPoisonPill")

	val actor = system.actorOf(TryPoisonPillActor.props())

	Thread.sleep(2000)

	println("tell PoisonPill")
	actor.tell(PoisonPill.getInstance(), ActorRef.noSender())

	println(">>> Press ENTER to exit <<<")
	try {
		System.`in`.read()
	} finally {
		system.terminate()
	}
}

class TryPoisonPillActor: AbstractActor() {

	companion object {
		fun props(): Props {
			return Props.create(TryPoisonPillActor::class.java)
		}
	}

	override fun preStart() {
		println("preStart")
	}

	override fun postStop() {
		println("postStop")
	}

	override fun createReceive(): Receive {
		return receiveBuilder().build()
	}
}
