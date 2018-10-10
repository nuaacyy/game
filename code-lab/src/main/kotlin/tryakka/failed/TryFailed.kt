package tryakka.failed

import akka.actor.AbstractActor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import scala.Option
import java.util.*

fun main(args: Array<String>) {
	val system = ActorSystem.create("testSystem")

	// 创建Actor：supervising-actor
	val supervisingActor = system.actorOf(Props.create(SupervisingActor::class.java), "supervising-actor")

	// 发送Msg，请求让子Actor失败
	supervisingActor.tell("failChild", ActorRef.noSender())

	println(">>> Press ENTER to exit <<<")
	try {
		System.`in`.read()
	} finally {
		system.terminate()
	}
}

class SupervisingActor : AbstractActor() {
	// 创建SupervisedActor
	var child = getContext().actorOf(Props.create(SupervisedActor::class.java), "supervised-actor")

	public override fun preStart() {
		println("supervising actor started")
	}

	public override fun postStop() {
		println("supervising actor stopped")
	}

	public override fun createReceive(): AbstractActor.Receive {
		return receiveBuilder()
			.matchEquals<String>("failChild", {
				// 向子Actor发送失败Msg
				child.tell("fail", getSelf())
			})
			.build()
	}
}

class SupervisedActor : AbstractActor() {

	var whichActor: Long = 0

	public override fun preStart() {
		this.whichActor = System.currentTimeMillis()

		println("supervised actor ${whichActor} started")
	}

	public override fun postStop() {
		println("supervised actor stopped")
	}

	public override fun createReceive(): AbstractActor.Receive {
		return receiveBuilder()
			.matchEquals<String>("fail", {
				println("supervised actor fails now")

				// 抛出异常
				// 然后这里默认的supervisor策略是让父Actor重启自己
				throw Exception("I failed!")
			})
			.build()
	}
}