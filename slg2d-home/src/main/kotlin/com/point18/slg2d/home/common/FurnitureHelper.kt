package com.point18.slg2d.home.common

import com.point18.slg2d.common.commonfunc.getNowMTime
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.mcache.ThreeKeyIndex
import com.point18.slg2d.common.pc.*
import com.point18.slg2d.common.resultcode.ResultCode
import com.point18.slg2d.home.actor.PlayerActor
import com.point18.slg2d.home.dc.*
import com.point18.slg2d.home.helper.HomeHelperPlus4
import com.point18.slg2d.home.msgnotice.createFurnitureProduceChangeNotifier
import xyz.ariane.util.lzDebug
import java.util.*
import java.util.Arrays.asList

data class ProduceReturn(
    val rtCode: ResultCode,
    val checkTime: Long,
    val res: LinkedList<ResVo>
)

data class EndTimeReturn(
    val rtCode: ResultCode,
    val checkTime: Long
)

data class AddResReturn(
    val rtCode: ResultCode, // 结果是否正确
    val addRes: LinkedList<ResVo> // 新增加的资源
)

data class ProduceTimeInfo(
    val id: Long,
    val startTime: Long,
    val endTime: Long
)

class FurnitureHelper :
    HomeHelperPlus4<HomePlayerDC, GuildHouseDC, FurnitureDC, InnerCityDC>(
        HomePlayerDC::class.java, GuildHouseDC::class.java, FurnitureDC::class.java, InnerCityDC::class.java
    ) {

    // 批量创建家具 包括创建需要的校验
    fun createAllFurniture(
        session: PlayerActor,
        resHelper: ResHelper,
        protoId: Int,
        buyNum: Int
    ): ResultCode {
        return prepare(session) { homePlayerDC: HomePlayerDC, guildHouseDC: GuildHouseDC,
                                  furnitureDC: FurnitureDC, innerCityDC: InnerCityDC ->
            // 找到家具模板
            val furnitureProto = pcs.furnitureProtoCache.furnitureProtoMap[protoId]
                ?: return@prepare ResultCode.NO_FURNITURE_PROTO // 家具模板没找到没找到

            // 校验家具可购买数量
            if (furnitureProto.num != 0) {
                var size = furnitureDC.furnitureBag[protoId]?.size ?: 0
                furnitureDC.nowFurniture.forEach {
                    if (it.value.protoId == furnitureProto.id) {
                        size++
                    }
                }
                if (size + buyNum > furnitureProto.num) {
                    return@prepare ResultCode.LIMIT_FURNITURE_NUM
                }
            }

            // 获取当前玩家
            val player = homePlayerDC.player

            // 校验资源
            val costRes = furnitureProto.costRes
            val costAllRes = LinkedList<ResVo>()
            val checkCost = if (buyNum == 1) {
                resHelper.checkRes(session, costAllRes)
            } else {
                for (i in 1..buyNum) {
                    // 累计资源
                    costAllRes += costRes
                }
                resHelper.checkRes(session, costAllRes)
            }
            if (!checkCost) {
                return@prepare ResultCode.LESS_RESOUCE
            }

            // 创建家具
            for (i in 1..buyNum) {
                // 扣除资源
                resHelper.costRes(
                    session,
                    ACTION_BUY_FURNITURE,
                    player,
                    furnitureProto.costRes
                )
                furnitureDC.createFurniture(furnitureDC.playerId, protoId)
            }
            return@prepare ResultCode.SUCCESS
        }
    }

    fun changeFurnitureState(
        session: PlayerActor,
        furniture: Furniture,
        floorIdx: Int,
        newX: Int,
        newY: Int,
        direction: Int,
        type: Int
    ): ResultCode {
        return prepare(session) { homePlayerDC: HomePlayerDC, guildHouseDC: GuildHouseDC,
                                  furnitureDC: FurnitureDC, innerCityDC: InnerCityDC ->
            when (type) {
                PUT_FURNITURE -> {
                    val check = putFurnitureCheck(session, furniture.protoId, floorIdx, newX, newY, direction)
                    return@prepare if (check.code != ResultCode.SUCCESS.code) {
                        check
                    } else {
                        putFurniture(session, furniture, floorIdx, newX, newY, direction)
                    }
                }
                MOVE_FURNITURE -> {
                    val check = moveFurnitureCheck(furniture, floorIdx, newX, newY, direction, furnitureDC, innerCityDC)
                    return@prepare if (check.code != ResultCode.SUCCESS.code) {
                        check
                    } else {
                        moveFurniture(furniture, floorIdx, newX, newY, direction, furnitureDC)
                    }
                }
                REMOVE_FURNITURE -> {
                    return@prepare removeFurniture(furniture, guildHouseDC, furnitureDC)
                }
            }

            normalLog.error("不存在的状态改变策略")
            return@prepare ResultCode.PARAMETER_ERROR
        }
    }

    fun putFurnitureCheck(
        session: PlayerActor,
        protoId: Int,
        floorIdx: Int,
        newX: Int,
        newY: Int,
        direction: Int
    ): ResultCode {
        return prepare(session) { homePlayerDC: HomePlayerDC, guildHouseDC: GuildHouseDC,
                                  furnitureDC: FurnitureDC, innerCityDC: InnerCityDC ->
            // 获取占地缓存
            val nowFurniture = furnitureDC.nowFurniture
            val area = furnitureDC.area

            // 获取家具模板
            val proto = pcs.furnitureProtoCache.furnitureProtoMap[protoId]
                ?: return@prepare ResultCode.NO_FURNITURE_PROTO

            // 单一家具类型校验
            if (proto.type == FLOOR_BOARD || proto.type == WALL_PAPER) {
                nowFurniture.values.forEach {
                    val nowProto = pcs.furnitureProtoCache.furnitureProtoMap[it.protoId]
                        ?: return@forEach
                    if (nowProto.type == proto.type) {
                        return@prepare ResultCode.SINGLE_TYPE_FUR
                    }
                }
            }

            // 获取后宅等级
            val buildingList =
                innerCityDC.findEffectiveInnerBuildingsByType(GuildHouse)
            if (buildingList.count() == 0) {
                return@prepare ResultCode.INNER_CITY_NOT_FIND_BUILDING
            }
            val guildHouseBuilding = buildingList[0]
            val houseLv = guildHouseBuilding.lv

            // 校验坐标
            val checkAreaResult = checkArea(area, floorIdx, newX, newY, direction, houseLv, proto)
            if (checkAreaResult != ResultCode.SUCCESS) {
                com.point18.slg2d.common.commonfunc.normalLog.error("proto[${proto.id}]坐标[$floorIdx,$newX,$newY]朝向[$direction]被占用")
                printArea(area)
                return@prepare checkAreaResult
            }

            return@prepare ResultCode.SUCCESS
        }
    }

    fun putFurniture(
        session: PlayerActor,
        furniture: Furniture,
        floorIdx: Int,
        newX: Int,
        newY: Int,
        direction: Int
    ): ResultCode {
        return prepare(session) { homePlayerDC: HomePlayerDC, guildHouseDC: GuildHouseDC,
                                  furnitureDC: FurnitureDC, innerCityDC: InnerCityDC ->
            // 获取后宅缓存
            val guildHouse = guildHouseDC.guildHouse

            // 获取占地缓存
            val nowFurniture = furnitureDC.nowFurniture
            val area = furnitureDC.area

            // 获取家具模板
            val proto = pcs.furnitureProtoCache.furnitureProtoMap[furniture.protoId]
                ?: return@prepare ResultCode.NO_FURNITURE_PROTO

            /**
             * 状态的维护
             * 1 家具本身状态
             * 2 功能家具的产出
             * 3 家具背包状态
             * 4 舒适度
             * 5 坐标
             */
            furniture.state = 1
            furniture.floorIdx = floorIdx
            furniture.x = newX
            furniture.y = newY
            furniture.direction = direction

            if (!proto.produceRes.isEmpty()) { // 通过家具的产出非空，来判断是否是功能家具
                furniture.produceRes = LinkedList()
                furniture.startTime = com.point18.slg2d.common.commonfunc.getNowMTime()
                furniture.checkTime = furniture.startTime
                furniture.endTime = 0

                // 从当前场景中的家具找到产出类型相同的家具，作相应处理
                nowFurniture.forEach { _, nowFur ->
                    val nowProto = pcs.furnitureProtoCache.furnitureProtoMap[nowFur.protoId]
                        ?: return@forEach
                    // 这个判断从当前场景中的家具，筛选出和摆放的家具有[相同类型产出]的[功能]家具
                    if (!nowProto.produceRes.isEmpty() && nowProto.produceRes[0].resType == proto.produceRes[0].resType) {
                        val nowPro = nowProto.produceRes[0].num
                        val newPro = proto.produceRes[0].num
                        /**
                         * 新家具产出高，并且当前家具的以产出资源量非空(没被刷过可能是空的结构体，刷过后如果还是没产出，
                         * 结构体中是会有0的，所以产出资源0算是逻辑意义上的已产出，需要转移资源的)
                         * 当前家具的的以产出资源转移到新家具上
                         */
                        if (newPro > nowPro && !nowFur.produceRes.isEmpty()
                        /*&& nowFur.produceRes[0].resNum.last() > 0 这个条件是错的*/) {
                            furniture.produceRes = nowFur.produceRes
                            furniture.startTime = nowFur.startTime
                            furniture.checkTime = nowFur.checkTime
                            nowFur.produceRes = LinkedList()
                            nowFur.startTime = 0
                            nowFur.checkTime = 0
                        } else if (newPro <= nowPro) {
                            furniture.startTime = 0
                            furniture.checkTime = 0
                        }
                    }
                }
            }
            furnitureDC.putOutOrInBag(furniture)
            guildHouse.comfort = guildHouse.comfort + proto.comf

            // 新的占地坐标
            if (furniture.direction == 0) {
                for (x in newX until newX + proto.floorSpace[0]) {
                    for (y in newY until newY + proto.floorSpace[1]) {
                        area.insertByKey(arrayOf(floorIdx, x, y))
                    }
                }
            } else {
                for (x in newX until newX + proto.floorSpace[1]) {
                    for (y in newY until newY + proto.floorSpace[0]) {
                        area.insertByKey(arrayOf(floorIdx, x, y))
                    }
                }
            }
            normalLog.lzDebug { "proto[${furniture.protoId}]坐标输出(新占地)" }
            printArea(area)

            return@prepare ResultCode.SUCCESS
        }
    }

    private fun moveFurnitureCheck(
        furniture: Furniture,
        floorIdx: Int,
        newX: Int,
        newY: Int,
        direction: Int,
        furnitureDC: FurnitureDC,
        innerCityDC: InnerCityDC
    ): ResultCode {
        // 获取家具模板
        val proto = pcs.furnitureProtoCache.furnitureProtoMap[furniture.protoId]
            ?: return ResultCode.NO_FURNITURE_PROTO

        // 获取占地缓存
        val area = furnitureDC.area
        val tempArea = ThreeKeyIndex(
            { it: Array<Int> -> it[0] },
            { it: Array<Int> -> it[1] },
            { it: Array<Int> -> it[2] }
        )
        // 复制一个占地缓存
        area.map {
            tempArea.insertByKey(arrayOf(it[0], it[1], it[2]))
            true
        }

        // 在缓存坐标内清除占地坐标
        val oldX = furniture.x
        val oldY = furniture.y
        if (furniture.direction == 0) {
            for (x in oldX until oldX + proto.floorSpace[0]) {
                for (y in oldY until oldY + proto.floorSpace[1]) {
                    tempArea.deleteByKey(arrayOf(furniture.floorIdx, x, y))
                }
            }
        } else {
            for (x in oldX until oldX + proto.floorSpace[1]) {
                for (y in oldY until oldY + proto.floorSpace[0]) {
                    tempArea.deleteByKey(arrayOf(furniture.floorIdx, x, y))
                }
            }
        }

        // 获取后宅等级
        val buildingList = innerCityDC.findEffectiveInnerBuildingsByType(GuildHouse)
        if (buildingList.count() == 0) {
            return ResultCode.INNER_CITY_NOT_FIND_BUILDING
        }
        val guildHouseBuilding = buildingList[0]
        val houseLv = guildHouseBuilding.lv

        // 校验坐标
        val checkAreaResult = checkArea(tempArea, floorIdx, newX, newY, direction, houseLv, proto)
        if (checkAreaResult != ResultCode.SUCCESS) {
            com.point18.slg2d.common.commonfunc.normalLog.error("proto[${proto.id}]坐标[$floorIdx,$newX,$newY]朝向[$direction]被占用")
            printArea(tempArea)
            return checkAreaResult
        }

        return ResultCode.SUCCESS
    }

    private fun moveFurniture(
        furniture: Furniture,
        floorIdx: Int,
        newX: Int,
        newY: Int,
        direction: Int,
        furnitureDC: FurnitureDC
    ): ResultCode {
        // 获取占地缓存
        val area = furnitureDC.area

        // 获取家具模板
        val proto = pcs.furnitureProtoCache.furnitureProtoMap[furniture.protoId]
            ?: return ResultCode.NO_FURNITURE_PROTO

        /**
         * 状态的维护
         * 1 家具本身状态
         * 2 坐标
         */
        // 清除占地坐标
        val oldX = furniture.x
        val oldY = furniture.y
        if (furniture.direction == 0) {
            for (x in oldX until oldX + proto.floorSpace[0]) {
                for (y in oldY until oldY + proto.floorSpace[1]) {
                    area.deleteByKey(arrayOf(furniture.floorIdx, x, y))
                }
            }
        } else {
            for (x in oldX until oldX + proto.floorSpace[1]) {
                for (y in oldY until oldY + proto.floorSpace[0]) {
                    area.deleteByKey(arrayOf(furniture.floorIdx, x, y))
                }
            }
        }

        furniture.x = newX
        furniture.y = newY
        furniture.direction = direction

        // 新的占地坐标
        if (furniture.direction == 0) {
            for (x in newX until newX + proto.floorSpace[0]) {
                for (y in newY until newY + proto.floorSpace[1]) {
                    area.insertByKey(arrayOf(floorIdx, x, y))
                }
            }
        } else {
            for (x in newX until newX + proto.floorSpace[1]) {
                for (y in newY until newY + proto.floorSpace[0]) {
                    area.insertByKey(arrayOf(floorIdx, x, y))
                }
            }
        }
        normalLog.lzDebug { "proto[${furniture.protoId}]坐标输出(新占地)" }
        printArea(area)

        return ResultCode.SUCCESS
    }

    private fun removeFurniture(
        furniture: Furniture,
        guildHouseDC: GuildHouseDC,
        furnitureDC: FurnitureDC
    ): ResultCode {
        // 获取后宅缓存
        val guildHouse = guildHouseDC.guildHouse

        // 获取占地缓存
        val area = furnitureDC.area

        val nowFurniture = furnitureDC.nowFurniture

        // 获取家具模板
        val proto = pcs.furnitureProtoCache.furnitureProtoMap[furniture.protoId]
            ?: return ResultCode.NO_FURNITURE_PROTO

        /**
         * 状态的维护
         * 1 家具本身状态
         * 2 坐标
         * 3 舒适度
         * 4 产出
         */
        // 清除占地坐标
        val oldX = furniture.x
        val oldY = furniture.y
        if (furniture.direction == 0) {
            for (x in oldX until oldX + proto.floorSpace[0]) {
                for (y in oldY until oldY + proto.floorSpace[1]) {
                    area.deleteByKey(arrayOf(furniture.floorIdx, x, y))
                }
            }
        } else {
            for (x in oldX until oldX + proto.floorSpace[1]) {
                for (y in oldY until oldY + proto.floorSpace[0]) {
                    area.deleteByKey(arrayOf(furniture.floorIdx, x, y))
                }
            }
        }
        normalLog.lzDebug { "proto[${furniture.protoId}]坐标输出(清除后)" }
        printArea(area)

        furniture.state = 0
        furniture.floorIdx = 0
        furniture.x = 0
        furniture.y = 0
        furniture.direction = 0
        furnitureDC.putOutOrInBag(furniture)
        guildHouse.comfort = guildHouse.comfort - proto.comf

        if (!furniture.produceRes.isEmpty() && furniture.produceRes[0].num > 0) {
            // 移除的家具有产出,将产出放到当前产出最高的功能家具上
            var topProFurniture: Furniture? = null
            var topProFurnitureProto: FurnitureProto? = null
            for ((_, nowFur) in nowFurniture) {
                val nowProto = pcs.furnitureProtoCache.furnitureProtoMap[nowFur.protoId] ?: continue
                if (!nowProto.produceRes.isEmpty() && nowProto.produceRes[0].resType == proto.produceRes[0].resType) {
                    // 比较当前场景中相同产出的功能家具
                    val tp = topProFurnitureProto
                    if (tp != null && topProFurniture != null) {
                        val nowPro = nowProto.produceRes[0].num
                        val topPro = tp.produceRes[0].num
                        if (nowPro > topPro) {
                            topProFurnitureProto = nowProto
                            topProFurniture = nowFur
                        }
                    } else {
                        topProFurnitureProto = nowProto
                        topProFurniture = nowFur
                    }
                }
            }
            val tf = topProFurniture
            if (tf != null) {
                tf.produceRes = furniture.produceRes
                tf.startTime = furniture.startTime
                tf.checkTime = furniture.checkTime
            }
        }

        furniture.produceRes = LinkedList()
        furniture.startTime = 0
        furniture.checkTime = 0
        furniture.endTime = 0

        return ResultCode.SUCCESS
    }

    /**
     * 通知产出变化情况
     */
    fun noticeProduce(
        session: PlayerActor
    ): ResultCode {
        return prepare(session) { homePlayerDC: HomePlayerDC, guildHouseDC: GuildHouseDC,
                                  furnitureDC: FurnitureDC, innerCityDC: InnerCityDC ->
            // 获取后宅缓存
            val guildHouse = guildHouseDC.guildHouse
            val comfort = guildHouse.comfort

            val nowFurniture = furnitureDC.nowFurniture.values

            val timeInfoList = LinkedList<ProduceTimeInfo>()
            val typeProduceMap = mutableMapOf<Int, Furniture>()
            nowFurniture.forEach {
                // 获取模板
                val proto = pcs.furnitureProtoCache.furnitureProtoMap[it.protoId]
                    ?: return@prepare ResultCode.NO_FURNITURE_PROTO
                // 查找需要刷新的家具
                if (!proto.produceRes.isEmpty()) { // 是功能家具
                    val proResType = proto.produceRes[0].resType
                    val topProFurniture = typeProduceMap[proResType]
                    if (topProFurniture == null) {
                        typeProduceMap[proResType] = it
                    } else {
                        val topProFurnitureProto = pcs.furnitureProtoCache.furnitureProtoMap[topProFurniture.protoId]
                            ?: return@prepare ResultCode.NO_FURNITURE_PROTO
                        if (topProFurnitureProto.produceRes[0].num < proto.produceRes[0].num) {
                            typeProduceMap[proResType] = it
                        } else if (topProFurnitureProto.produceRes[0].num == proto.produceRes[0].num) {
                            // 模板产出相同情况下, 替换掉当前无产出的
                            /**
                             * if (topProFurniture.produceRes.isEmpty()) {
                             * 用startTime判断这个家具是不是正在计算产出的，startTime==0就算是功能家具也不算产出
                             */
                            if (topProFurniture.startTime == 0L) {
                                typeProduceMap[proResType] = it
                            }
                            /*else if (topProFurniture.produceRes[0].resNum.last() == 0L) {
                            typeProduceMap[proResType] = it
                        }*/
                        }
                    }
                }
            }

            typeProduceMap.values.forEach {
                // 刷新产出
                val (
                    rtCode: ResultCode,
                    endTime: Long
                ) = getProduceEndTime(it, comfort)
                if (rtCode.code != ResultCode.SUCCESS.code) {
                    normalLog.error("获取家具产出失败${it.id}")
                    return@prepare rtCode
                }
                timeInfoList += ProduceTimeInfo(it.id, it.startTime, endTime)
            }

            val notifier = createFurnitureProduceChangeNotifier()
            timeInfoList.forEach {
                notifier.append(it.id, it.startTime, it.endTime)
            }
            return@prepare ResultCode.SUCCESS
        }
    }
}

/**
 * 获取家具的产出资源
 */
fun getProduce(
    furniture: Furniture,
    comfort: Int
): ProduceReturn {
    // 获取模板
    val furnitureProto = pcs.furnitureProtoCache.furnitureProtoMap[furniture.protoId]
    if (furnitureProto == null) {
        normalLog.error("家具模板不存在或不是功能性家具")
        return ProduceReturn(ResultCode.NO_FURNITURE_PROTO, 0, LinkedList())
    }
    if (furnitureProto.produceRes.isEmpty()) {
        normalLog.error("家具不是功能家具${furniture.id}")
        return ProduceReturn(ResultCode.FURNITURE_NOT_FUNCTIONAL, 0, LinkedList())
    }

    /** 上一次刷新资源产出 **/
    val oldProduceRes = furniture.produceRes
    val newProduceRes = LinkedList<ResVo>()
    newProduceRes += furniture.produceRes
    var oldRes: ResVo? = null
    if (oldProduceRes.size > 1) {
        normalLog.error("旧的资源产出出错!")
        return ProduceReturn(ResultCode.PARAMETER_ERROR, 0, LinkedList())
    }
    if (!oldProduceRes.isEmpty()) {
        oldRes = oldProduceRes[0]
    }

    /** 模板周期资源产出 **/
    val produceRes = furnitureProto.produceRes
    if (produceRes.size != 1) {
        normalLog.error("模板资源产出出错!")
        return ProduceReturn(ResultCode.PARAMETER_ERROR, 0, LinkedList())
    }
    val cycleRes = produceRes[0]

    /** 模板资源产出上限 **/
    val upperLimRes = furnitureProto.upperLimRes
    if (produceRes.size != 1) {
        normalLog.error("模板资源上限出错!")
        return ProduceReturn(ResultCode.PARAMETER_ERROR, 0, LinkedList())
    }
    val resLim = upperLimRes[0]

    /** 模板周期时间 **/
    val cycleTime = (furnitureProto.cycle * 1000).toLong()

    /** 舒适度影响参数 **/
    val rate = furnitureProto.rate

    val lastCheckTime = furniture.checkTime
    val nowTime = getNowMTime()

    // 周期数(上次刷新~此时)
    val cycleNum = ((nowTime - lastCheckTime) / cycleTime).toInt()
    // 计算产量到满需要多少个周期
    val fullCycleNum = calFullCycleNum(
        oldRes, cycleRes, resLim,
        rate, comfort
    )

    val trueCycleNum = if (fullCycleNum <= cycleNum) {
        // 达到上限或刚刚超过上限 需要的周期数 <= 周期数(上次刷新~此时)
        // 当前产出已经满
        fullCycleNum // 达到上限或刚刚超过上限 需要的周期数
    } else {
        // 当前产出还未满
        cycleNum // 检测时理论经历的周期次数
    }

    // 计算增加的产量(上次刷新后的cycleNum个周期内)
    val (rtCode, addRes) = calAddRes(
        oldRes, cycleRes, resLim,
        rate, comfort, trueCycleNum
    )
    if (rtCode.code != ResultCode.SUCCESS.code) {
        normalLog.error("计算产出出错!")
        return ProduceReturn(rtCode, 0, LinkedList())
    }

    var newCheckTime = lastCheckTime + trueCycleNum * cycleTime // 满的时候checktime=当前时间
    if (trueCycleNum >= fullCycleNum) {
        newCheckTime = nowTime
    }

    newProduceRes += addRes
    return ProduceReturn(ResultCode.SUCCESS, newCheckTime, resVoCombine(newProduceRes))
}

/**
 * 计算产出结束时间
 */
fun getProduceEndTime(
    furniture: Furniture,
    comfort: Int
): EndTimeReturn {
    // 获取模板
    val furnitureProto = pcs.furnitureProtoCache.furnitureProtoMap[furniture.protoId]
    if (furnitureProto == null) {
        normalLog.error("家具模板不存在或不是功能性家具")
        return EndTimeReturn(ResultCode.NO_FURNITURE_PROTO, 0)
    }
    if (furnitureProto.produceRes.isEmpty()) {
        normalLog.error("家具不是功能家具${furniture.id}")
        return EndTimeReturn(ResultCode.FURNITURE_NOT_FUNCTIONAL, 0)
    }

    /** 上一次刷新资源产出 **/
    val oldProduceRes = furniture.produceRes
    val newProduceRes = LinkedList<ResVo>()
    newProduceRes += furniture.produceRes
    var oldRes: ResVo? = null
    if (oldProduceRes.size > 1) {
        normalLog.error("旧的资源产出出错!")
        return EndTimeReturn(ResultCode.PARAMETER_ERROR, 0)
    }
    if (!oldProduceRes.isEmpty()) {
        oldRes = oldProduceRes[0]
    }

    /** 模板周期资源产出 **/
    val produceRes = furnitureProto.produceRes
    if (produceRes.size != 1) {
        normalLog.error("模板资源产出出错!")
        return EndTimeReturn(ResultCode.PARAMETER_ERROR, 0)
    }
    val cycleRes = produceRes[0]

    /** 模板资源产出上限 **/
    val upperLimRes = furnitureProto.upperLimRes
    if (produceRes.size != 1) {
        normalLog.error("模板资源上限出错!")
        return EndTimeReturn(ResultCode.PARAMETER_ERROR, 0)
    }
    val resLim = upperLimRes[0]

    /** 模板周期时间 **/
    val cycleTime = (furnitureProto.cycle * 1000).toLong()

    /** 舒适度影响参数 **/
    val rate = furnitureProto.rate

    val lastCheckTime = furniture.checkTime

    // 计算产量到满需要多少个周期
    val fullCycleNum = calFullCycleNum(
        oldRes, cycleRes, resLim,
        rate, comfort
    )

    val endTime = lastCheckTime + fullCycleNum * cycleTime

    return EndTimeReturn(ResultCode.SUCCESS, endTime)
}

/**
 * 计算产量到满需要多少个周期
 */
private fun calFullCycleNum(
    oldRes: ResVo?,
    cycleRes: ResVo,
    resLim: ResVo,
    x: Double,
    comfort: Int
): Int {
    // 已经产出
    var oldPro = 0L
    if (oldRes != null) {
        oldPro = oldRes.num
    }

    // 每个周期实际产出(受舒适度加成)
    val trueCycleRes = (cycleRes.num * (1 + comfort * x)).toLong()

    // 实际资源上限(受舒适度加成)
    val trueLimit = (resLim.num * (1 + comfort * x)).toLong()

    return Math.ceil((trueLimit - oldPro) / trueCycleRes.toDouble()).toInt()
}

/**
 * 计算增加的产量
 */
private fun calAddRes(
    oldRes: ResVo?,
    cycleRes: ResVo,
    resLim: ResVo,
    x: Double,
    comfort: Int,
    trueCycleNum: Int
): AddResReturn {
    var rtCode = ResultCode.SUCCESS
    val addRes = LinkedList<ResVo>()

    // 已经产出
    var oldPro = 0L
    if (oldRes != null) {
        oldPro = oldRes.num
    }

    // 实际资源上限(受舒适度加成)
    val trueLimit = (resLim.num * (1 + comfort * x)).toLong()

    // 已产出未达到上限，则计算产出
    if (oldPro < trueLimit) {

        // 实际产出(受舒适度加成)
        val discountRet =
            resVoDiscount(LinkedList(asList(cycleRes)), (10000 + 10000 * x * comfort).toInt() * trueCycleNum)

        if (discountRet.res) {
            addRes += discountRet.listOfResVo
        } else {
            rtCode = ResultCode.PRODUCE_CAL_ERROR
        }
    }

    return AddResReturn(rtCode, addRes)
}

// 校验坐标
fun checkArea(
    area: ThreeKeyIndex<Int, Int, Int, Array<Int>>,
    floorIdx: Int,
    newX: Int,
    newY: Int,
    direction: Int,
    houseLv: Int,
    proto: FurnitureProto
): ResultCode {
    val homeArea = pcs.innerBuildingDataCache.getEffValue(GuildHouse, houseLv, LV_EFF_HOME_AREA)
    if (homeArea == 0) {
        return ResultCode.FURNITURE_INDEX_ERROR
    }

    if (direction == 0) {
        for (x in newX until newX + proto.floorSpace[0]) {
            for (y in newY until newY + proto.floorSpace[1]) {
                // 家具类型坐标校验
                if (proto.type == WALL_DECORATION) {
                    // 墙饰
                    if ((x >= 0 && y >= 0) || (x < 0 && y < 0)) {
                        return ResultCode.FURNITURE_INDEX_ERROR
                    }
                } else if (proto.type == FLOOR_DECORATION || proto.type == COMMON_FUR
                    || proto.type == FUNCTIONAL_FUR
                ) {
                    // 装饰
                    if (x < 0 || y < 0) {
                        return ResultCode.FURNITURE_INDEX_ERROR
                    }
                }
                // 占地校验
                if (area.findByKey(floorIdx, x, y) != null || x >= homeArea || y >= homeArea) {
                    return ResultCode.FURNITURE_INDEX_ERROR
                }
            }
        }
    } else {
        for (x in newX until newX + proto.floorSpace[1]) {
            for (y in newY until newY + proto.floorSpace[0]) {
                // 家具类型坐标校验
                if (proto.type == WALL_DECORATION) {
                    // 墙饰
                    if ((x >= 0 && y >= 0) || (x < 0 && y < 0)) {
                        return ResultCode.FURNITURE_INDEX_ERROR
                    }
                } else if (proto.type == FLOOR_DECORATION || proto.type == COMMON_FUR
                    || proto.type == FUNCTIONAL_FUR
                ) {
                    // 装饰
                    if (x < 0 || y < 0) {
                        return ResultCode.FURNITURE_INDEX_ERROR
                    }
                }
                // 占地校验
                if (area.findByKey(floorIdx, x, y) != null || x >= homeArea || y >= homeArea) {
                    return ResultCode.FURNITURE_INDEX_ERROR
                }
            }
        }
    }
    return ResultCode.SUCCESS
}

fun printArea(area: ThreeKeyIndex<Int, Int, Int, Array<Int>>) {
    area.printArea()
}

/**
 * 刷新当前产出
 */
fun refreshProduce(
    furnitureList: MutableCollection<Furniture>,
    comfort: Int
): ResultCode {
    val typeProduceMap = mutableMapOf<Int, Furniture>()
    furnitureList.forEach {
        // 获取模板
        val proto = pcs.furnitureProtoCache.furnitureProtoMap[it.protoId]
            ?: return ResultCode.NO_FURNITURE_PROTO
        // 查找需要刷新的家具
        if (!proto.produceRes.isEmpty()) { // 是功能家具
            val proResType = proto.produceRes[0].resType // 产出类型
            val topProFurniture = typeProduceMap[proResType]  // 当前同产出的最厉害的家具
            if (topProFurniture == null) {
                typeProduceMap[proResType] = it
            } else {
                val topProFurnitureProto = pcs.furnitureProtoCache.furnitureProtoMap[topProFurniture.protoId]
                    ?: return ResultCode.NO_FURNITURE_PROTO
                if (topProFurnitureProto.produceRes[0].num < proto.produceRes[0].num) {
                    typeProduceMap[proResType] = it
                } else if (topProFurnitureProto.produceRes[0].num == proto.produceRes[0].num) {
                    // 模板产出相同情况下, 替换掉当前无产出的
                    /**
                     * if (topProFurniture.produceRes.isEmpty()) {
                     * 用startTime判断这个家具是不是正在计算产出的，startTime==0就算是功能家具也不算产出
                     */
                    if (topProFurniture.startTime == 0L) {
                        typeProduceMap[proResType] = it
                    }
                    /* 产出为0不算无产出的功能家具
                    else if (topProFurniture.produceRes[0].resNum.last() == 0L) {
                        typeProduceMap[proResType] = it
                    }*/
                }
            }
        }
    }

    typeProduceMap.values.forEach {
        // 刷新产出
        val (
                rtCode: ResultCode,
                checkTime: Long,
                res: LinkedList<ResVo>?
        ) = getProduce(it, comfort)
        if (rtCode.code != ResultCode.SUCCESS.code) {
            normalLog.error("获取家具产出失败${it.id}")
            return rtCode
        }
        it.produceRes = res
        it.checkTime = checkTime
        it.endTime = 0 //TODO endTime不需要存，通过计算获得
    }

    return ResultCode.SUCCESS
}