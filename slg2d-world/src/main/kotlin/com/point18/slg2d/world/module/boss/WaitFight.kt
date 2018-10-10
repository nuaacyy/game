package com.point18.slg2d.world.module.boss

import com.point18.slg2d.common.constg.*
import com.point18.slg2d.world.area4data.AreaCache
import com.point18.slg2d.world.area4data.IBossInfo
import com.point18.slg2d.common.homeentities.MailInfo
import com.point18.slg2d.world.module.walk.walkComm.goHome
import com.point18.slg2d.world.common.sendMail
import java.util.*
import java.util.Arrays.asList

//移除等待战斗部队
fun removeWaitFightGroup(areaCache: AreaCache, bossInfo: IBossInfo, walkType: Int) {
    val waitFightGroups =
        areaCache.walkForceGroupCache.findWalkForceGroupsByPosAndState(bossInfo.getPosX(), bossInfo.getPosY(), WaitFight)
    waitFightGroups.forEach {
        goHome(areaCache, bossInfo.getPosX(), bossInfo.getPosY(), it)

        //发送来迟邮件
        val mailInfo = MailInfo(
            TEXT_READ_LAN,
            SYSTEM_MAIL,
            LinkedList(),
            TARGET_DISAPPEAR_CONTENT,
            LinkedList(asList(WALK_PARAS + walkType.toString()))
        )
        sendMail(areaCache, it.mainPlayerId, mailInfo)
    }
}

