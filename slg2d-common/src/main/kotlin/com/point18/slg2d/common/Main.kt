package com.point18.slg2d.common

import com.point18.slg2d.common.pc.xmlDir

fun main(args: Array<String>) {
    if (args.isNotEmpty()) {
        if (args[0] == "CheckConfig") {
            // 检测配置
            println("开始检测配置文件")
            xmlDir = "../2csv_server/"
            com.point18.slg2d.common.pc.pcs.initProtoCache()
            println("检测配置文件结束")
            return
        } else if (args[0] == "CheckConfigLocal") {
            // 检测配置
            println("开始检测配置文件")
            xmlDir = "../config_game/"
            com.point18.slg2d.common.pc.pcs.initProtoCache()
            println("检测配置文件结束")
            return
        } else if (args[0] == "CheckHotUploadConfigLocal") {
            // 检测配置
            println("开始检测配置文件")
            xmlDir = "../cehuaproto/2csv_server/"
            com.point18.slg2d.common.pc.pcs.initProtoCache()
            println("检测配置文件结束")
            return
        }

    }
}