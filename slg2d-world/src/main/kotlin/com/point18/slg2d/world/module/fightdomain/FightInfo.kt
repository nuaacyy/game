package com.point18.slg2d.world.module.fightdomain

import java.util.*

data class FightInfo(
    var fightResult: Int,  // `json:"fightResult"` //战斗结果  1-进攻方全灭  2-防守方全灭  3-规定回合未结束  4-未战
    val tempRecords: HashMap<Int, Any>,//临时记录数据
    var detailInfo: EachFightInfo// `json:"detailInfo"`  //详细信息
)

data class AllFightInfo(
    var fightResult: Int,  // `json:"fightResult"` //战斗结果  1-进攻方全灭  2-防守方全灭  3-规定回合未结束  4-未战
    var detailInfoList: LinkedList<EachFightInfo>// `json:"detailInfo"`  //详细信息
)

data class EachFightInfo(
    var record: FightRecordCollection,
    var detailFightInfo: ByteArray
)

