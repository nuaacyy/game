package tryakka.stop

import akka.actor.*

fun main(args: Array<String>) {
	val system = ActorSystem.create("tryKill")

	val actor = system.actorOf(TryKillActor.props())

	Thread.sleep(2000)

	println("尝试发送Kill消息，这会导致目标Actor非正常Stop")
	actor.tell(Kill.getInstance(), ActorRef.noSender())

	println(">>> Press ENTER to exit <<<")
	try {
		System.`in`.read()
	} finally {
		system.terminate()
	}
}

class TryKillActor : AbstractActor() {

	companion object {
		fun props(): Props {
			return Props.create(TryKillActor::class.java)
		}
	}

	override fun preStart() {
		println("preStart")
	}

	override fun postStop() {
		println("postStop")
	}

	override fun createReceive(): Receive {
		return receiveBuilder()
			.build()
	}
}
