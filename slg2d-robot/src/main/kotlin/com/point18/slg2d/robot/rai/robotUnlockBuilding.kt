package com.point18.slg2d.robot.rai

import com.point18.slg2d.common.constg.CLEAR_TIME_INNER_CITY_BUILDING_UPGRADE
import com.point18.slg2d.robot.robot.AiLoop
import com.point18.slg2d.robot.robot.AiSelect
import com.point18.slg2d.robot.robot.AiSequence
import com.point18.slg2d.robot.robot.clearTime.AiClearTime
import com.point18.slg2d.robot.robot.innerBuilding.AiCreateInnerBuilding
import com.point18.slg2d.robot.robot.innerBuilding.AiUnlockInnerCity
import com.point18.slg2d.robot.robot.innerBuilding.AiUpInnerBuilding
import com.point18.slg2d.robot.robot.realm.AiOffline
import com.point18.slg2d.robot.robotData.RobotAction

fun robotUnlockBuilding(): RobotAction {
    val robotBeh = AiSequence(

        prepareAiEnterGame(), // 打包好的进入游戏流程

        AiSelect(
            AiLoop(

                // 这里可以填无限大，如果正确运行的话，肯定不会运行无限次，因为下面的AiSelect会在某一个循环当中失败跳出而走AiOffline
                100000,

                AiSequence(
                    robotGmAddSource(), // 加资源，如果资源够会直接成功

                    AiClearTime(CLEAR_TIME_INNER_CITY_BUILDING_UPGRADE), // 如果没有可以加速就SUCCESS 继续进行下面的操作

                    // AiSelect 只有全部子节点全部失败才算失败，全部失败证明了没有建筑可以升级了
                    AiSelect(
                        AiCreateInnerBuilding(),    // 建造建筑
                        AiUnlockInnerCity(),        // 解锁建筑
                        AiUpInnerBuilding()         // 升级建筑
                    )
                )

            ),

            AiOffline() // 做完之后下线
        ),

        AiOffline() // 做完之后下线

    )

    return robotBeh

}