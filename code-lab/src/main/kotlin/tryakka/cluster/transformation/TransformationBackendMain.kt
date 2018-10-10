package tryakka.cluster.transformation

import akka.actor.ActorSystem
import akka.actor.Props
import com.typesafe.config.ConfigFactory

fun backendStart(args: Array<String>) {
    // Override the configuration of the port when specified as program argument
    val port = if (args.size > 0) args[0] else "0"
    val config = ConfigFactory.parseString(
        "akka.remote.netty.tcp.port=" + port + "\n" +
            "akka.remote.artery.canonical.port=" + port
    )
        .withFallback(ConfigFactory.parseString("akka.cluster.roles = [backend]"))
        .withFallback(ConfigFactory.load("transformation.conf"))

    val system = ActorSystem.create("ClusterSystem", config)

    system.actorOf(Props.create(TransformationBackend::class.java), "backend")

}