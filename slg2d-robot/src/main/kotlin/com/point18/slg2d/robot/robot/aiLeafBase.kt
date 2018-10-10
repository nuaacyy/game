package com.point18.slg2d.robot.robot

import com.point18.slg2d.robot.robotData.LEAF
import com.point18.slg2d.robot.robotData.RobotAction

abstract class AiLeafBase : RobotAction() {

    init {

    }

    override fun whatType(): Int {
        return LEAF
    }

}
