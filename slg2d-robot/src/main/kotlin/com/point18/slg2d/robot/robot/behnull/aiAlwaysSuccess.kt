package com.point18.slg2d.robot.robot.behnull

import com.point18.slg2d.robot.robotData.ActionResult
import com.point18.slg2d.robot.robotData.Robot
import com.point18.slg2d.robot.robotData.RobotAction
import com.point18.slg2d.robot.robotruntime.RobotPropChg

class AiAlwaysSuccess : com.point18.slg2d.robot.robot.AiLeafBase() {

    override fun actionDesc(): String {
        return "AiAlwaysSuccess - Always Success"
    }

    override fun reset(): RobotAction {
        val aiAlwaysSuccess = AiAlwaysSuccess()
        aiAlwaysSuccess.parentAction = this.parentAction
        return aiAlwaysSuccess
    }

    override fun msgTrigger(robot: Robot, chg: RobotPropChg): ActionResult {
        return com.point18.slg2d.robot.robotData.RUNNING
    }

    override fun update(robot: Robot): ActionResult {
        return com.point18.slg2d.robot.robotData.SUCCESS
    }


}
