package tryakka.delayrestart

import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import akka.pattern.Backoff
import akka.pattern.BackoffSupervisor
import scala.concurrent.duration.Duration
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {

	val system = ActorSystem.create("testSystem")

	// 创建一个Actor的Props
	val childProps = Props.create(MayFailActor::class.java)

	// 创建supervisor的Props
	val supervisorProps = BackoffSupervisor.props(
		/**
		 * TIP：下面的配置会在"停止"时起作用，异常失败时不会起作用
		 */
		Backoff.onStop(
			childProps,
			"myEcho",
			Duration.create(3, TimeUnit.SECONDS), // 最小3秒
			Duration.create(30, TimeUnit.SECONDS), // 最大30秒
			0.2)) // adds 20% "noise" to vary the intervals slightly

	val failSupervisor = system.actorOf(supervisorProps, "failSupervisor")

	Thread.sleep(3000)
	failSupervisor.tell("STOP", ActorRef.noSender())
	logger.info("told STOP to failSupervisor actor")

	Thread.sleep(1000)
	logger.info(">>> Press ENTER to exit <<<")

	try {
		System.`in`.read()
	} finally {
		system.terminate()
	}

}
