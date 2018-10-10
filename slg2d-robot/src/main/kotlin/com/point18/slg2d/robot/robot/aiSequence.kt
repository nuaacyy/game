package com.point18.slg2d.robot.robot

import xyz.ariane.util.lzDebug
import com.point18.slg2d.robot.robotData.*
import com.point18.slg2d.robot.robotruntime.RobotPropChg

// 选择节点
class AiSequence(vararg acts: RobotAction) : RobotAction() {

    private var actions: MutableList<RobotAction> = mutableListOf()
    private var selectedIndex: Int = 0 // 选中的行为，选取的节点需要从0开始
    private var selectedAction: RobotAction? = null // 正在执行的选中行为
    private var inited: Boolean = true

    init {
        for ((nIndex, eachAction) in acts.withIndex()) {  // 从不定参数当中获取RobotAction进行初始化
            var tmpAct = eachAction
            if (tmpAct.whatType() == LEAF) {  // 如果是叶子节点，则打包起来
                tmpAct = AiBehWrap(tmpAct)
            }
            tmpAct.nodeIndex = (nIndex)  // 标记这个子RobotAction是父RobotAction第nIndex个节点
            tmpAct.parentAction = (this)  // 标记子RobotAction的父RobotAction
            actions.add(tmpAct)  // 往子RobotAction队列添加这个节点
        }

        selectedIndex = 0
    }

    override fun whatType(): Int {
        return CONTAINER  // 这个节点是容器
    }

    override fun actionDesc(): String {
        return "[队列 $nodeIndex]序列节点"
    }

    override fun msgTrigger(robot: Robot, chg: RobotPropChg): ActionResult {
        val act = selectedAction
        if (act != null) {
            loggerAct.lzDebug { "msgTrigger:${this.javaClass.name} - msg to actions[$selectedIndex]:${act.javaClass.name}" }
            val rt: ActionResult = act.msgTrigger(robot, chg)  // 父动作执行子动作msgTrigger
            when (rt) {
                RUNNING -> {
                    loggerAct.lzDebug { "msgTrigger:${this.javaClass.name} running - actions[$selectedIndex]:${act.javaClass.name} msgTrigger return RUNNING" }
                    return rt
                }
                SUCCESS -> {
                    // 执行成功，成功的动作复位，selectedIndex++，进行下一个动作
                    loggerAct.lzDebug { "msgTrigger:${this.javaClass.name} running - actions[$selectedIndex]:${act.javaClass.name} msgTrigger return SUCCESS" }
                    actions[selectedIndex] = act.reset()
                    selectedAction = null
                    selectedIndex += 1

                    return RUNNING
                }
                else -> {
                    // 执行失败，同样需要复位
                    loggerAct.lzDebug { "msgTrigger:${this.javaClass.name} fail - actions[$selectedIndex]:${act.javaClass.name} msgTrigger return FAILED" }
                    actions[selectedIndex] = act.reset()
                    selectedAction = null
                    return rt
                }
            }
        }

        return RUNNING

    }

    override fun update(robot: Robot): ActionResult {
        loggerAct.lzDebug { "<<<<< Sequence update start >>>>>" }
        if (!inited) {
            throw RuntimeException("AiSequence 尚未初始化")
        }

        // 如果之前有尚未执行结束的行为，从之前的行为再次执行。
        val act = selectedAction
        if (act != null) {
            loggerAct.lzDebug { "update:${this.javaClass.name} - execute last actions[$selectedIndex]:${act.javaClass.name}" }
            val rt: ActionResult = act.update(robot)

            if (rt == RUNNING) {  // return
                loggerAct.lzDebug { "update:${this.javaClass.name} running - actions[$selectedIndex]:${act.javaClass.name} return RUNNING" }
                loggerAct.lzDebug { ">>>>> Sequence update end <<<<<" }
                return rt
            } else if (rt == FAILED) {  // return
                // 执行失败，需要复位
                loggerAct.lzDebug { "update:${this.javaClass.name} fail - actions[$selectedIndex]:${act.javaClass.name} return FAILED" }
                actions[selectedIndex] = act.reset()
                selectedAction = null
                loggerAct.lzDebug { ">>>>> Sequence update end <<<<<" }
                return rt
            } else {
                // 执行成功  同样需要复位  但不要return， 继续执行下一个节点
                loggerAct.lzDebug { "update:${this.javaClass.name} - actions[$selectedIndex]:${act.javaClass.name} return SUCCESS" }
                actions[selectedIndex] = act.reset()
                selectedAction = null
                // 切到下一个节点
                selectedIndex += 1
            }
        }

        // 从头开始执行的，或者之前的节点执行成功了，从下一个节点继续执行。
        while (true) {
            if (selectedIndex >= actions.size) {
                // 没有节点可以执行了
                loggerAct.lzDebug { "update:${this.javaClass.name} success - index reach the upper limit ${actions.lastIndex}" }
                loggerAct.lzDebug { ">>>>> Sequence update end <<<<<" }
                return SUCCESS
            }

            val newAct = actions[selectedIndex]
            loggerAct.lzDebug { "update:${this.javaClass.name} - execute actions[$selectedIndex]:${actions[selectedIndex].javaClass.name}" }
            val rt = newAct.update(robot)
            when (rt) {
                RUNNING -> {  // 需要返回结果
                    selectedAction = newAct // 保存当前选中的行为
                    loggerAct.lzDebug { "update:${this.javaClass.name} running - actions[$selectedIndex]:${newAct.javaClass.name} return RUNNING" }
                    loggerAct.lzDebug { ">>>>> Sequence update end <<<<<" }
                    return rt
                }
                FAILED -> {// 需要返回结果
                    // 执行失败，需要复位
                    loggerAct.lzDebug { "update:${this.javaClass.name} fail - actions[$selectedIndex]:${newAct.javaClass.name} return FAILED" }
                    actions[selectedIndex] = newAct.reset()
                    loggerAct.lzDebug { ">>>>> Sequence update end <<<<<" }
                    return rt
                }
                else -> {// 不需要返回结果
                    loggerAct.lzDebug { "update:${this.javaClass.name} - actions[$selectedIndex]:${newAct.javaClass.name} return SUCCESS" }
                    actions[selectedIndex] = newAct.reset()   // 执行成功，同样需要复位
                    selectedIndex += 1   // 之后再次循环，从后续节点继续尝试
                }
            }

        }

    }

    override fun reset(): RobotAction {
        selectedIndex = 0
        selectedAction = null
        return this
    }

}

