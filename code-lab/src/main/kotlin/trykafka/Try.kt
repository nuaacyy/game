package trykafka

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import java.lang.Thread.sleep
import java.time.LocalDateTime
import java.util.*

fun main(args: Array<String>) {
    sendLog()
}

fun sendLog() {
    val props = Properties()
    props["bootstrap.servers"] = "172.18.3.203:19092"
    props["acks"] = "all"
    props["retries"] = 0
    props["batch.size"] = 16384
    props["linger.ms"] = 1
    props["buffer.memory"] = 33554432
    props["key.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"
    props["value.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"

    val producer = KafkaProducer<String, String>(props)
    for (i in 0 until 100) {
        val t = System.currentTimeMillis()
        val localDateTime = LocalDateTime.now()
        producer.send(
            ProducerRecord<String, String>(
                "test3", """
            {"name":"log"}
        """.trimIndent(),
                """
            {"account": "testvm", "logTime": "${localDateTime}", "money": 2000}
        """.trimIndent()
            )
        )
        println("发送日志 $t")
        sleep(100L)
    }

    producer.close()
}
