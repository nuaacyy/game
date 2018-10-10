package com.point18.slg2d.robot.robotData

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.point18.slg2d.robot.robotruntime.RobotConfig
import java.io.File

lateinit var robotCfg: RobotConfig

// 加载配置
fun initConfig(): (RobotConfig) {
    // 创建服务器配置对象

    val mapper = jacksonObjectMapper()
    val robotConfigFilePath = "robot-cfg/robot-cfg.json"
    val robotConfigFile = File(robotConfigFilePath)
    robotCfg = mapper.readValue(robotConfigFile)
    println("$robotCfg")
    
    return robotCfg
}

