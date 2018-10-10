package com.point18.slg2d.robot.robotData

import com.point18.slg2d.common.mcache.OneKeyIndex
import pb4client.MassGroup

data class MassGroupData(var massId: Long)

class RDataMassGroup {
    var massGroups: OneKeyIndex<Long, MassGroupData>

    init {
        massGroups = OneKeyIndex { it.massId }
    }

    fun addMassGroup(massGroup: MassGroup) {
        val massGroupData = MassGroupData(massGroup.groupId)
        this.massGroups.insertByKey(massGroupData)
    }

}

