package com.point18.slg2d.robot.robot

import com.point18.slg2d.robot.robotData.*
import com.point18.slg2d.robot.robotruntime.RobotPropChg

class AiSelect(vararg acts: RobotAction) : RobotAction() {
    private var actions: MutableList<RobotAction> = mutableListOf()
    private var selectedIndex: Int = 0
    private var selectedAction: RobotAction? = null// 正在执行的选中行为
    private var inited: Boolean = true

    init {
        var nIndex = 0
        for (eachAction in acts) {
            if (eachAction.whatType() == LEAF) {
                val tmpEachAction = AiBehWrap(eachAction)
                actions.add(tmpEachAction)
            } else {
                actions.add(eachAction)
            }
            eachAction.nodeIndex = nIndex++  // 给子接点编序号
            eachAction.parentAction = this // 给子接点编定父节点
        }
        this.selectedIndex = 0
        this.inited = true
    }

    override fun actionDesc(): String {
        return ("[${this.nodeIndex}]选择节点")
    }

    override fun whatType(): ActionResult {
        return CONTAINER
    }

    override fun msgTrigger(robot: Robot, chg: RobotPropChg): ActionResult {
        val act = this.selectedAction
        if (act != null) {
            val rt = act.msgTrigger(robot, chg)
            when (rt) {
                RUNNING -> return rt
                SUCCESS -> {
                    // 执行成功，需要复位
                    this.actions[this.selectedIndex] = act.reset()
                    this.selectedAction = null
                    return rt
                }
                else -> {
                    // 执行失败，同样需要复位
                    this.actions[this.selectedIndex] = act.reset()
                    this.selectedAction = null
                    this.selectedIndex += 1
                    return RUNNING
                }
            }
        }
        return RUNNING
    }

    override fun update(robot: Robot): ActionResult {
        if (!this.inited) {
            //panic("AiSelect 尚未初始化")
        }

        // 如果之前有尚未执行结束的行为，从之前的行为再次执行。
        val act = this.selectedAction
        if (act != null) {
            showExecPath(this.selectedAction)
            val rt = act.update(robot)
            if (rt == RUNNING) {
                return rt
            } else if (rt == SUCCESS) {
                // 只要一个子节点成功，就可以复位返回SUCCESS
                this.actions[this.selectedIndex] = act.reset()
                this.selectedAction = null
                return rt
            } else {
                // 执行失败，同样需要复位，但是不返回，而是尝试执行下一个子节点
                this.actions[this.selectedIndex] = act.reset()
                this.selectedAction = null
                // 切到下一个节点
                this.selectedIndex += 1
            }
        }

        // 从头开始执行的，或者之前的节点执行失败了，从下一个节点继续执行。
        while (true) {
            if (this.selectedIndex > this.actions.lastIndex) {
                // 执行到没有节点可以执行了，证明之前的节点一个都没有成功
                return FAILED
            }
            this.selectedAction = this.actions[this.selectedIndex]
            showExecPath(selectedAction)
            val actSelected = this.selectedAction
            if (actSelected != null) {
                val rt = actSelected.update(robot)
                if (rt == RUNNING) {
                    return rt
                } else if (rt == SUCCESS) {
                    // 执行成功，需要复位
                    this.actions[this.selectedIndex] = actSelected.reset()
                    return rt
                } else {
                    // 执行失败，同样需要复位
                    this.actions[this.selectedIndex] = actSelected.reset()
                    // 之后再次循环，从后续节点继续尝试
                    this.selectedIndex += 1
                }
            }
        }
    }

    override fun reset(): RobotAction {
        this.selectedIndex = 0
        this.selectedAction = null
        return this
    }

}
