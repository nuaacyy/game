package tryakka.lifecycle

import akka.actor.AbstractActor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props

fun main(args: Array<String>) {
	val system = ActorSystem.create("testSystem")

	// 创建Actor：first
	val first = system.actorOf(Props.create(StartStopActor1::class.java), "first")

	// 发送停止Msg
	first.tell("stop", ActorRef.noSender())

	println(">>> Press ENTER to exit <<<")
	try {
		System.`in`.read()
	} finally {
		system.terminate()
	}
}

class StartStopActor1 : AbstractActor() {

	override fun preStart() {
		println("first started")

		// 创建Actor：second
		context.actorOf(Props.create(StartStopActor2::class.java), "second")
	}

	override fun postStop() {
		println("first stopped")
	}

	override fun createReceive(): AbstractActor.Receive {
		return receiveBuilder()
			.matchEquals("stop") {
				// 停止自己
				// 注意：只有等子Actor停止后，才会执行自己的postStop方法
				context.stop(self)
			}
			.build()
	}
}

class StartStopActor2 : AbstractActor() {
	override fun preStart() {
		println("second started")
	}

	override fun postStop() {
		println("second stopped")
	}

	// Actor.emptyBehavior is a useful placeholder when we don't
	// want to handle any messages in the actor.
	override fun createReceive(): AbstractActor.Receive {
		return receiveBuilder()
			.build()
	}
}