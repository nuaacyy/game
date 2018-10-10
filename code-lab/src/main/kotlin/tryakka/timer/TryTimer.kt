package tryakka.timer

import akka.actor.AbstractActorWithTimers
import akka.actor.ActorSystem
import akka.actor.Props
import scala.concurrent.duration.Duration
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
	val system = ActorSystem.create("timer")

	// 创建Actor
	val actor = system.actorOf(TryTimerActor.props())

	println(">>> Press ENTER to exit <<<")
	try {
		System.`in`.read()
	} finally {
		system.terminate()
	}
}

val TICK_KEY = "TickKey"

class TryTimerActor: AbstractActorWithTimers() {

	class FirstTick

	class Tick

	companion object {
		fun props(): Props {
			return Props.create(TryTimerActor::class.java)
		}
	}

	override fun preStart() {
		timers.startSingleTimer(TICK_KEY, FirstTick(), Duration.create(500, TimeUnit.MILLISECONDS))
	}

	override fun createReceive(): Receive {
		return receiveBuilder()
			.match(FirstTick::class.java, {
				println("First tick trigger")
				timers.startPeriodicTimer(TICK_KEY, Tick(), Duration.create(1, TimeUnit.SECONDS))
			})
			.match(Tick::class.java, {
				println("Tick trigger")
			})
			.build()
	}
}