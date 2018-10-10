package com.point18.slg2d.robot.robotData

import com.point18.slg2d.common.mcache.OneKeyIndex
import pb4client.WalkGroup


data class WalkGroupData(var GroupId: Int)

class RDataWalkGroup {

    lateinit var myWalkGroups: OneKeyIndex<Int, WalkGroupData>
    lateinit var warnWalkGroups: OneKeyIndex<Int, WalkGroupData>

    init {
    }

    fun addMyWalkGroup(walkGroup: WalkGroup) {
		val walkGroupData = WalkGroupData(walkGroup.groupId.toInt())
		this.myWalkGroups.insertByKey(walkGroupData)
    }

    fun addWarnWalkGroup(walkGroup: WalkGroup) {
		val walkGroupData = WalkGroupData(walkGroup.groupId.toInt())
		this.warnWalkGroups.insertByKey(walkGroupData)
    }
}





