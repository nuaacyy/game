package tryakka.receivetimeout

import akka.actor.*
import scala.concurrent.duration.Duration
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
	val system = ActorSystem.create("test")

	// 创建Actor
	println("启动Actor，Actor会每隔1s输出 超时 ")
	val timeout = system.actorOf(TryReceiveTimeoutActor.props())

	println("如果需要将超时间隔设置为5s，输入回车")
	System.`in`.read()

	// 发送5s间隔Msg
	timeout.tell("Hello", ActorRef.noSender())

	println("如果需要停止显示超时输出，输入回车")
	System.`in`.read()

	// 发送停止msg
	timeout.tell("stop", ActorRef.noSender())

	println(">>> Press ENTER to exit <<<")
	try {
		System.`in`.read()
	} finally {
		system.terminate()
	}
}

class TryReceiveTimeoutActor : AbstractActor() {

	companion object {
		fun props(): Props {
			return Props.create(TryReceiveTimeoutActor::class.java)
		}
	}

	override fun preStart() {
		val d = Duration.create(1, TimeUnit.SECONDS)
		context.setReceiveTimeout(d)
	}

	override fun createReceive(): Receive {
		return receiveBuilder()
			.matchEquals("Hello", {
				println("重设超时间隔为5s")
				val d = Duration.create(5, TimeUnit.SECONDS)
				context.setReceiveTimeout(d)
			})
			.match(ReceiveTimeout::class.java, {
				println("超时！")

			})
			.matchEquals("stop", {
				println("stop！")
				context.setReceiveTimeout(Duration.Undefined())
			})
			.build()
	}
}