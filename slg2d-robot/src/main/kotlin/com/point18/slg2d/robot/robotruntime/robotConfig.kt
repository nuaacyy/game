package com.point18.slg2d.robot.robotruntime

data class RobotInstanceConfig(val pltAreaNo: Int, val robotNum: Int)
data class RobotConfig(
    val host: String,
    val port: Int,
    val loop: Boolean,
    val logLv: String,
    val maxOnlineNum: Int,
    val dealAction: Int,
    val clearWalkCdAction: Int,
    val robots: List<RobotInstanceConfig>
)

// 读取日志等级
// interface: LogLvReader
//
//fun RobotConfig.readLogLv(): String {
//    return this.logLv
//}

