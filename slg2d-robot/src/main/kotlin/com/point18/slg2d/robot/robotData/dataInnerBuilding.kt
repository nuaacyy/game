package com.point18.slg2d.robot.robotData

import com.point18.slg2d.common.mcache.OneKeyIndex
import xyz.ariane.util.lzDebug
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.common.constg.STABLE
import pb4client.InnerCityInfo

class InnerBuildingData(
    var id: Long,
    var type: Int,              // 建筑类型
    var startTime: Int,         // 开始建造或升级时间
    var completeTime: Int,      // 完成建造或升级的时间
    var state: Int,             // 状态
    var positionIndex: Int,     // 位置
    var lv: Int                 // 等级
)

class RDataInnerBuilding {
    var haveBuilt: MutableMap<Int, Int> = hashMapOf()     //  已经建造的数量
    var innerBuildings = OneKeyIndex<Long, InnerBuildingData> { it.id }

    fun addInnerBuilding(innerBuilding: InnerCityInfo) {
        val innerBuildingData = InnerBuildingData(
            innerBuilding.innerCityId,
            innerBuilding.innerCityType,
            innerBuilding.startTime,
            innerBuilding.completeTime,
            innerBuilding.state,
            innerBuilding.positionIndex,
            innerBuilding.lv
        )

        normalLog.lzDebug {
            "添加建筑(id:${innerBuilding.innerCityId}),类型:${innerBuildingData.type}" +
                ",等级:${innerBuildingData.lv},位置:${innerBuildingData.positionIndex}"
        }

        this.innerBuildings.insertByKey(innerBuildingData)
    }

    fun deleteInnerBuilding(buildingId: Long) {
        this.innerBuildings.deleteWithKey(buildingId)
    }

    fun updateInnerBuilding(innerBuildingData: InnerBuildingData, innerBuilding: InnerCityInfo) {
        innerBuildingData.type = (innerBuilding.innerCityType)
        innerBuildingData.startTime = innerBuilding.startTime
        innerBuildingData.completeTime = innerBuilding.completeTime
        innerBuildingData.state = innerBuilding.state
        innerBuildingData.positionIndex = innerBuilding.positionIndex
        innerBuildingData.lv = innerBuilding.lv
    }

    fun findSpecTypeBuildings(buildingType: Int): MutableList<InnerBuildingData> {
        val innerBuildings = mutableListOf<InnerBuildingData>()
        this.innerBuildings.map { innerBuilding ->
            if (innerBuilding.type == buildingType) {
                innerBuildings.add(innerBuilding)
                return@map false
            }
            return@map true
        }
        return innerBuildings
    }

    fun findSpecBuildingById(buildingId: Long): InnerBuildingData? {
        val innerBuildingDataObj = this.innerBuildings.findByKey(buildingId)
        if (innerBuildingDataObj == null) {
            return null
        } else {
            return innerBuildingDataObj
        }
    }

    fun findSpecBuildingAtSpecPos(pos: Int): InnerBuildingData? {
        var targetInnerBuilding: InnerBuildingData? = null
        this.innerBuildings.map { it ->
            if (it.positionIndex == pos) {
                targetInnerBuilding = it
                return@map false
            }
            return@map true
        }
        return targetInnerBuilding
    }

    fun findStableBuildings(): MutableList<InnerBuildingData> {
        val innerBuildings1 = mutableListOf<InnerBuildingData>()
        this.innerBuildings.map { it ->
            if (it.state == STABLE) {
                innerBuildings1.add(it)
            }
            return@map true
        }

        return innerBuildings1
    }
}



