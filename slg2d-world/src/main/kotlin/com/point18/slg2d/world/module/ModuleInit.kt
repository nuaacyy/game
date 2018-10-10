package com.point18.slg2d.world.module

import com.point18.slg2d.world.module.alliance.allianceM
import com.point18.slg2d.world.module.barracks.BarracksM
import com.point18.slg2d.world.module.bigMap.BigMapM
import com.point18.slg2d.world.module.boss.BossM
import com.point18.slg2d.world.module.buff.BuffM
import com.point18.slg2d.world.module.cave.CaveM
import com.point18.slg2d.world.module.farm.FarmM
import com.point18.slg2d.world.module.gmCommand.GmM
import com.point18.slg2d.world.module.heroBattle.HeroBattleModuleM
import com.point18.slg2d.world.module.homeHeart.HomeHeartM
import com.point18.slg2d.world.module.jjc.JjcM
import com.point18.slg2d.world.module.king.KingM
import com.point18.slg2d.world.module.library.HomeProxyLibraryM
import com.point18.slg2d.world.module.mainHeroprison.mainHeroPrisonM
import com.point18.slg2d.world.module.moveCity.MoveCityM
import com.point18.slg2d.world.module.playerActivity.PlayerActivityM
import com.point18.slg2d.world.module.realm.RealmM
import com.point18.slg2d.world.module.setting.SettingM
import com.point18.slg2d.world.module.soliderBattle.BattleM
import com.point18.slg2d.world.module.task.TaskM
import com.point18.slg2d.world.module.transport.TransportM
import com.point18.slg2d.world.module.walk.walkM
import com.point18.slg2d.world.module.wonder.WonderM
import com.point18.slg2d.world.module.worldmap.WorldMapM

fun initModules() {
    registerModule(RealmM)                          // 登录验证
    registerModule(allianceM)                       // 登录验证
    registerModule(SettingM)                        // 玩家设置
    //registerModule(building.BuildingM)            // 建筑
//	registerModule(FriendM)                         // 好友
    //registerModule(chat.ChatM)                    // 聊天
    registerModule(MoveCityM)                       // 迁城
    registerModule(walkM)                           // 行军
    registerModule(WorldMapM)                       // 地图
    registerModule(BigMapM)                         // 大地图
    registerModule(BattleM)                         // 战斗
    registerModule(HeroBattleModuleM)               // 战斗
    registerModule(TaskM)                           // 任务
    // registerModule(notice.NoticeM)               // 公告
    //registerModule(logs.RecordOnlineLogM)         // 时间段登录统计（5分钟一次）

    registerModule(GmM)                             // GM命令
//	registerModule(grow.HeroM)                      // 部队
    registerModule(JjcM)                            // 竞技场模块
    registerModule(BarracksM)                       // 兵营模块
    registerModule(CaveM)                           // 藏兵模块
    registerModule(TransportM)                      // 运输请求模块
    registerModule(BuffM)                           // buff模块
    registerModule(PlayerActivityM)                 // 玩家活动模块
    registerModule(mainHeroPrisonM)                 // 监禁模块
    registerModule(KingM)                           // 领主升级模块
    registerModule(WonderM)                         // 奇观模块
    registerModule(BossM)                           //Boss模块
    registerModule(FarmM)                           //采集模块
    registerModule(HomeHeartM)                      //home服心跳模块
    registerModule(HomeProxyLibraryM)               //home图书馆模块

    // 执行初始化
    com.point18.slg2d.common.baseg.modules.forEach { it.moduleInit() }
}

fun <T> registerModule(module: T) where T : com.point18.slg2d.common.baseg.IModule {
    com.point18.slg2d.common.baseg.modules.add(module)
}