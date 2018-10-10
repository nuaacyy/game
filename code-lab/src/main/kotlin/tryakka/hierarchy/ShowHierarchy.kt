package tryakka.hierarchy

import akka.actor.Props
import akka.actor.AbstractActor
import akka.actor.ActorRef
import akka.actor.ActorSystem

fun main(args: Array<String>) {

	val system = ActorSystem.create("testSystem")

	// 创建Actor：first-actor
	val firstRef = system.actorOf(Props.create(PrintMyActorRefActor::class.java), "first-actor")
	println("First: " + firstRef)

	// 发送msg
	firstRef.tell("printit", ActorRef.noSender())

	println(">>> Press ENTER to exit <<<")

	try {
		System.`in`.read()
	} finally {
		system.terminate()
	}

}

internal class PrintMyActorRefActor : AbstractActor() {

	override fun createReceive(): AbstractActor.Receive {
		return receiveBuilder()
			.matchEquals("printit") {
				// 创建Actor：second-actor
				val secondRef = context.actorOf(Props.empty(), "second-actor")
				println("Second: " + secondRef)
			}
			.build()
	}
}