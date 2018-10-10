package com.point18.slg2d.robot.robot

import com.point18.slg2d.robot.robotData.*
import com.point18.slg2d.robot.robotruntime.RobotPropChg

class AiLoop(private var loopNum: Int, robotAction: RobotAction) : RobotAction() {
    private var action: RobotAction    // 需要循环的行为
    private var nowLoopTimes: Int = 0 // 当前循环次数
    private var inited: Boolean

    init {
        action = if (robotAction.whatType() == LEAF) {     // 如果传入的是LEAF类型，就把它包起来
            AiBehWrap( robotAction)
        } else {
            robotAction
        }
        action.parentAction = this
        inited = true
    }

    override fun actionDesc(): String {
        return "[${this.nodeIndex}]循环节点"
    }

    override fun msgTrigger(robot: Robot, chg: RobotPropChg): ActionResult {
        val rt = this.action.msgTrigger(robot, chg)
        if (rt != RUNNING) {
            this.action = this.action.reset() // 复位
            if (rt == SUCCESS) {
                // 子行为结束，循环增加1
                this.nowLoopTimes = 1 + this.nowLoopTimes
                if (this.nowLoopTimes >= this.loopNum) {
                    return SUCCESS
                }
            } else {
                return FAILED
            }
        }

        return RUNNING
    }

    override fun update(robot: Robot): ActionResult {
        if (!this.inited) {
            throw RuntimeException("${this} loop没有初始化")
        }

        // 执行
        while (true) {
            showExecPath(this.action)
            val rt = this.action.update(robot)  // 这个rt == success时循环执行

            if (rt == RUNNING) {  // 非阻塞
                return rt
            } else if (rt == SUCCESS) { //
                //  节点执行成功，复位，不是return success而是继续执行上面的update，直至满足跳出条件
                this.action = this.action.reset()
                this.nowLoopTimes++
                if (nowLoopTimes > 2000){
                    println("注意，Ai loop循环已经超过2000次！~！~！")
                }
                if (this.nowLoopTimes >= this.loopNum) {  // 跳出条件  return SUCCESS
                    return SUCCESS
                }
            } else {
                // 循环节点的子节点执行失败了，那么考虑直接退出，而不是重试。
                this.action = this.action.reset()
                return rt
            }
        }
    }

    override fun reset(): RobotAction {
        this.nowLoopTimes = 0
        return this
    }

    override fun whatType(): Int {
        return CONTAINER
    }
}

