package com.point18.slg2d.robot.robotData

interface RobotCondition {
    fun check(robot: Robot): Boolean
}