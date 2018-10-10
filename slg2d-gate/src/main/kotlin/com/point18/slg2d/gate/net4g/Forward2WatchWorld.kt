package com.point18.slg2d.gate.net4g

import com.point18.slg2d.common.netmsg.MsgType
import java.util.*

//需要发送到观察世界的消息
val FORWARD_TO_WATCH_WORLD_MSG_SET: Set<MsgType> = EnumSet.of(
    MsgType.NewShowNearMap_110,
    MsgType.QueryCell_111,
    MsgType.QueryCountryPosition_1461,
    MsgType.QueryCountryNotice_1462,
    MsgType.QueryHunterRank_1492
)