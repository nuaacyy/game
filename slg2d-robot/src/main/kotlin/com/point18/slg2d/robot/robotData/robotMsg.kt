package com.point18.slg2d.robot.robotData

import com.point18.slg2d.common.netmsg.MsgType
import com.point18.slg2d.robot.robotruntime.RobotPropChg

typealias MsgDeal = (robot: Robot, chg: RobotPropChg) -> Boolean  // lambda表达式，表示参数是Robot和RobotPropChg，返回的是bool

var msgDeals: MutableMap<MsgType, MsgDeal> = mutableMapOf()  // msgDeals 表 可以找到3000以后MsgType对应的处理方法MsgDeal



