package com.point18.slg2d.mgr.resp

data class AllWorldAreaResp(
    val iD: Long,
    val areaNo: Int,
    val gameAreaName: String,
    val pltAreaNo: Long,
    val areaState: Int,
    val whiteRosters: String,
    val blackRosters: String,
    val opgameId: Int,
    val payKey: String,
    val loginKey: String,
    val giftKey: String,
    val openAreaDate: Long,
    val operationState: Int,
    val deployState: Int,
    val whichAction: Int,
    val clusterId:Long
)

data class QueryWorldAreaByAreaIdResp(
    val rt: Int,
    val serverIp: String,
    val port: Int
)

open class MgrGateBaseResp(
    val rt: Int,
    val errorMsg: String?,
    val data: Any? = null
)

data class MgrGateBaseResp2Client(
    val status: Int,
    val msg: String?,
    val data: Any?
)

data class ServerListResp(
    val status: Int,
    val msg: String,
    val data: Any
)

data class MgrFightingResp(
    val rt: Int,
    val key: Int,
    val data: Any
)

data class MgrGateBaseTabResp(
    val rt: Int,
    val errorMsg: String,
    val data: Any,
    val page: Int,
    val count: Int,
    val pages: Int,
    val counts: Int
)

data class GuidestepResp(
    val rt: Int,
    val allPlayerNum: Int,
    val accNum: Int,
    val data: Any
)