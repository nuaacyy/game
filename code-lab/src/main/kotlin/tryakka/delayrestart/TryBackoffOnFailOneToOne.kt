package tryakka.delayrestart

import akka.actor.*
import akka.pattern.Backoff
import akka.pattern.BackoffSupervisor
import scala.concurrent.duration.Duration
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {

	val system = ActorSystem.create("testSystem")

	// 创建一个Actor的Props
	val childProps = Props.create(MayFailActorWithMyEx::class.java)

	// 创建supervisor的Props
	val supervisorProps = BackoffSupervisor.props(

		/**
		 * TIP：下面的配置会在"停止"时起作用，异常失败时不会起作用
		 */
		Backoff.onFailure(
			childProps,
			"myEcho",
			Duration.create(1, TimeUnit.SECONDS), // 最小3秒
			Duration.create(30, TimeUnit.SECONDS), // 最大30秒
			0.0)
			.withSupervisorStrategy(OneForOneStrategy(-1, Duration.Inf()) {
				when (it) {
					is MyException -> {
						logger.info("MyException 2 restart 重启")
						SupervisorStrategy.restart()
					}
					else -> {
						logger.info("else 2 stop 直接停止，不再重启")
						SupervisorStrategy.stop()
					}
				}
			})
	) // adds 20% "noise" to vary the intervals slightly

	val failSupervisor = system.actorOf(supervisorProps, "failSupervisor")

	Thread.sleep(3000)
	failSupervisor.tell("FAILMyEx", ActorRef.noSender())
	logger.info("told FAILMyEx to failSupervisor actor")

	Thread.sleep(3000)
	failSupervisor.tell("FAIL", ActorRef.noSender())
	logger.info("told FAIL to failSupervisor actor")

	Thread.sleep(1000)
	logger.info(">>> Press ENTER to exit <<<")

	try {
		System.`in`.read()
	} finally {
		system.terminate()
	}
}
