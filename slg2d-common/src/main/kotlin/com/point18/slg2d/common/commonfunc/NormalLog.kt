package com.point18.slg2d.common.commonfunc

import org.slf4j.LoggerFactory

val normalLog = LoggerFactory.getLogger(NormalLog::class.java)
class NormalLog // 普通日志

val abandonLog = LoggerFactory.getLogger(NormalLog::class.java)
class CanAbandonLog // 可抛弃日志

