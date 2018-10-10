package com.point18.slg2d.common.commonfunc

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

val logDc = LogDc()

// 日志条目接口
interface DcLogEntity {
    val localDt: String
    val logType: Int
}

const val DC_LOG_PROCESS_START = 1

class ProcessStartDcLog(
    val pType: String, // 进程类型
    val processIp: String,
    val akkaPort: Int
): DcLogEntity {
    override val localDt: String = DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(ZonedDateTime.now())
    override val logType: Int = DC_LOG_PROCESS_START
}

/**
 * 向kafka发送日志的工具类
 */
class LogDc {

    private lateinit var producer: Producer<String, String>

    private var topic: String = "dclog"

    fun init(kafkaAddrs: String, topic: String) {

        DateTimeFormatter.ISO_TIME;

        val props = Properties()
        props["bootstrap.servers"] = kafkaAddrs
        props["acks"] = "all"
        props["retries"] = 0
        props["batch.size"] = 16384
        props["linger.ms"] = 1
        props["buffer.memory"] = 33554432
        props["key.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"
        props["value.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"

        this.topic = topic

        producer = KafkaProducer<String, String>(props)
    }

    /**
     * 发送日志
     */
    fun <T : DcLogEntity> send(value: T) {
//        val logJson = toJson(value)
//        this.producer.send(ProducerRecord<String, String>(topic, logJson)) { metadata, e ->
//
//        }
    }

}
