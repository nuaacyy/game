package com.point18.slg2d.robot.robot

import xyz.ariane.util.lzDebug
import xyz.ariane.util.lzWarn
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.robot.robotData.*
import com.point18.slg2d.robot.robotruntime.RobotPropChg

class AiBehWrap(private var action: RobotAction) : RobotAction() {
    private var started: Boolean = false
    private var updateTimes = 0

    init {
        action.parentAction = this
    }

    override fun whatType(): NodeType {
        return CONTAINER
    }

    override fun actionDesc(): String {
        return "[包 $nodeIndex]"
    }

    override fun msgTrigger(robot: Robot, chg: RobotPropChg): ActionResult {
        return this.action.msgTrigger(robot, chg)
    }

    override fun update(robot: Robot): ActionResult {
        updateTimes++
        if (started) {

            // 如果这个包运行n次都没有处理完，则这个leaf动作有问题或者服务器处理很慢，标记为failed
            if (updateTimes >= 50) {
                robot.failed = true
                normalLog.lzWarn { "update:${this.action.javaClass.name} - update started:$started (action update has run), update times:$updateTimes" }
            }
            normalLog.lzDebug { "update:${this.javaClass.name} - update started:$started (action update has run), update times:$updateTimes" }

            // 如果包里的update运行过，就不要再运行了，直接返回RUNNING吧
            return RUNNING
        }

        showExecPath(this.action)
        val rt = this.action.update(robot)
        this.started = true
        return rt
    }

    override fun reset(): RobotAction {
        this.action = this.action.reset()
        this.started = false
        this.updateTimes = 0
        return this
    }
}
