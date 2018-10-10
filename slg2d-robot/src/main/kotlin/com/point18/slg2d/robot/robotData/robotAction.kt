package com.point18.slg2d.robot.robotData

import xyz.ariane.util.lzDebug
import com.point18.slg2d.common.commonfunc.normalLog
import com.point18.slg2d.robot.robotruntime.RobotPropChg

typealias ActionResult = Int
val FAILED: ActionResult = 1
val SUCCESS: ActionResult = 2
val RUNNING: ActionResult = 3

typealias NodeType = Int
val LEAF: NodeType = 1
val CONTAINER: NodeType = 2

abstract class RobotAction {
    var parentAction: RobotAction? = null
    var nodeIndex: Int = 0
    val loggerAct = normalLog
    // 节点什么类型的
    abstract fun whatType(): ActionResult

    // 节点的描述
    abstract fun actionDesc(): String

    // 判断AI
    abstract fun update(robot: Robot): ActionResult

    // 消息触发
    abstract fun msgTrigger(robot: Robot, chg: RobotPropChg): ActionResult

    // 复位
    abstract fun reset(): RobotAction
}

// 显示执行路径    sprnitf
fun showExecPath(robotAction: RobotAction?) {
    if (robotAction?.whatType() == com.point18.slg2d.robot.robotData.LEAF) {    //  如果是叶子节点则打印执行路径
        var currentAction = robotAction
        var execPath = currentAction.actionDesc()
        while (true) {     //  循环获取父节点的描述，拼接成路径
            currentAction = currentAction?.parentAction
            if (currentAction != null) {
                execPath = "${currentAction.actionDesc()}->$execPath"
            } else {
                break
            }
        }
        normalLog.lzDebug {"Leaf execPath：$execPath"}
    } /*else {
        println(robotAction!!.javaClass.name)
    }*/
}

