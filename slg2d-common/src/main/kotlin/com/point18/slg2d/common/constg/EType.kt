package com.point18.slg2d.common.constg

import com.point18.slg2d.common.baseg.EventType

// 1号事件类型已经被使用！
// (x) 表示暂时没有地方触发的事件
// (?) 事件定义不正确
const val NO_HANDLER: EventType = 0  //没事的事件,用来占位的事件类型
const val DECREE_RESTORE: EventType = 1  //政令恢复
const val PART_CITY_UP_FINISH: EventType = 2  //建立分城(x)
const val HERO_UP_FINISH: EventType = 3  //武将升级事件
const val BUILDING_UP_FINISH: EventType = 4  //内城建筑升级完成事件
const val RESEARCH_UP_FINISH: EventType = 5 //科研升级完成事件
const val ENTER_GAME: EventType = 6  //进入游戏事件
const val OFFLINE: EventType = 7  //下线事件(x)
const val SKILL_LV_UP: EventType = 8  //技能升级事件(x)
const val ALLOW_ALLIANCE: EventType = 9  //加入联盟事件
const val ALLIANCE_SET_POSITION: EventType = 10 //联盟职位变更事件
const val ALLIANCE_NAME_CHANGE: EventType = 11 //联盟改名事件
const val QUIT_ALLIANCE: EventType = 12 //退出联盟事件
const val GET_UNIT_TASK_REWARD: EventType = 13 //领取章节任务奖励事件
const val GET_TASK_REWARD: EventType = 14 //领取任务奖励事件
const val HERO_IN_FORMATION: EventType = 15 //武将上阵事件(x)
const val SOLDIER_NUM_ADD: EventType = 16 //兵力获得增加事件(x)
const val FIGHTEND: EventType = 17 //战斗结束更新武将数据事件(还未获得地)
const val PVP_FIGHT_WIN: EventType = 18 //PVP战斗胜利
const val PVE_FIGHT_WIN: EventType = 19 //PVE战斗胜利
const val GET_NEW_TASK: EventType = 20 //获得新任务
const val ATCK_NPC_CITY_RESULT: EventType = 22 //攻打NPC城池即如果事件
const val PLAYER_PVP_RESULT: EventType = 23 //玩家PVP功勋事件
const val GET_NEW_WORLD_TASK: EventType = 24 //获得新的天下大势任务
const val FINISH_WORLD_TASK: EventType = 25 //完成了一个天下大势任务
const val RECORD_ONLINE_LOG: EventType = 26 //定时记录在线人数事件
const val BEGIN_FINISH_WORLD_TASK: EventType = 27 //进入了天下大势心跳之后,在结算之前,抛出时间进行更新
const val GET_TAX: EventType = 28 //获得一次税收(x)
const val GET_ONLINE_REWARD: EventType = 29 //获得一次在线奖励
const val USER_READY_SOLD: EventType = 30 //使用预备兵进行征兵(x)
const val EXCHANGE_ALLIANCE_CARD: EventType = 31 //帮派换卡发布(x)
const val GET_WORLD_TASK: EventType = 32 //请求天下大势(x)
const val HERO_STAR_UP: EventType = 33 //武将升星事件
const val GET_NEW_CELL: EventType = 34 //服务器存入一块新的地
const val DEL_NEW_CELL: EventType = 35 //服务器放弃一块地
//36
const val INNER_CITY_EXTEND: EventType = 37 //城池扩建事件(x)
const val REFRESH_INNER_CITY_EFFECT: EventType = 38 //刷新所有内城建筑效果(?)
const val PLAYER_DELETE_NEW_CELL: EventType = 39 //玩家放弃土地(x)
const val CREATE_ALLIANCE: EventType = 40 //创建同盟事件
const val SHOW_NEAR_MAP: EventType = 41 //拖动一下地图
//42
const val FORCE_INFO_CHANGE: EventType = 43 //部队战斗力有变化(包括武将变化跟格子装备变化)
const val USE_PROPS_AT_ONCE: EventType = 44 //获得了一件物品-配置是马上使用
const val REFRESH_BUILDING_EFFECT: EventType = 45 //刷新所有建筑升级效果
const val GET_HERO_CARD: EventType = 46 //抽卡
const val PAY: EventType = 47 //充值(x)
//48 49
const val REFRESH_RANK_BECAUSE_LOST_TANTO: EventType = 50 //排行榜数据触碰到保险丝,丢失的数据太多,但是没有新数据补上来,在游戏协程异步加载数据库更新排行榜
const val RESEARCH_EFFECT_CHANGE: EventType = 51 //科技效果变化
const val BLACKLIST_CHANGE: EventType = 52 //黑名单变化
//53
const val VIP_LV_CHANGE: EventType = 54 //vip等级改变
const val VIP_ACTIVE: EventType = 55 //vip激活
const val VIP_UNACTIVE: EventType = 56 //vip失效
const val TALENT_LV_CHANGE: EventType = 57 //天赋等级变化
const val TALENT_RESET: EventType = 58 //天赋重置
const val KING_LV_CHANGE: EventType = 59 //君主等级变化
const val BOSS_FIGHT: EventType = 60 //魔物战斗
const val BOSS_DIE: EventType = 61 //魔物死亡
const val RELIC_DISAPPEAR: EventType = 62 //遗迹被打掉
const val ALLIANCE_MONSTER_SCORE_CHANGE: EventType = 63 //玩家魔物积分增长
const val ALLIANCE_BOSS_SCORE_CHANGE: EventType = 64 //联盟BOSS积分增长
const val TRANSPORT_START: EventType = 65 //运输开始事件
const val GET_BUFF: EventType = 66 //获得buff
const val PLAYER_ACTIVITY_CHANGE: EventType = 67 //玩家活动信息变化
//const val STRONGHOLD_DISAPPEAR: EventType = 68 //据点消失
const val PRISON_AFTER_PVP: EventType = 69 //PVP之后触发了监禁
const val FINAL_QUIT_ALLIANCE: EventType = 70 //完全退出联盟（数据已修改）
const val RESCUE_PRISON: EventType = 71 //pvp胜利后，释放监禁
const val HERO_SUPER_UP: EventType = 72 //武将升阶事件
const val POWER_CHANGE: EventType = 73 //实力值变化
const val TARGET_CHANGE: EventType = 74 //统计目标变化
const val ACHIEVEMENT_FINISH: EventType = 75 //成就达成
const val PVP_DEF_CASTLE_LOSE: EventType = 76 //防守城池失败
//77
const val COUNTRY_POS_CHANGE: EventType = 78 //官职变化
const val FARM_END: EventType = 79 //采集结束
const val FARM_EMPTY: EventType = 80 //资源采空

const val GET_NEW_HERO: EventType = 82 // 获得新武将
const val DEF_COVER_ON: EventType = 83 // 保护罩开启
const val PROP_CHANGE: EventType = 84 // 道具新增或升阶
const val WONDER_OVER: EventType = 85 // 奇观争夺结束
const val HERO_EQUIP_LV_UP: EventType = 86 // 武将装备进阶
const val HERO_SKILL_LV_UP: EventType = 87 // 武将技能升级
const val GET_KING_EQUIP: EventType = 88 // 获得君主装备
const val COMPOUND_CARD: EventType = 89 // 宝石合成
const val KING_EQUIP_ON_CARD: EventType = 90 // 君主装备打宝石
const val JJC_FIGHT: EventType = 91 // 竞技场战斗
const val MERCHANT_SHIP_BUY_SURPRISE: EventType = 92 // 商船购买物品
const val JJC_SHOP_REFRESH: EventType = 93 // 刷新竞技场商店
const val INSTANCE_FIGHT: EventType = 94 // 推图
const val GET_ALLIANCE_GIFT: EventType = 95 // 领取联盟礼物
const val GO_ALLIANCE_HELP: EventType = 96 // 联盟帮助
const val CURE_SOLDIER: EventType = 97 //治疗士兵
const val GOD_OF_WAR_ON: EventType = 98 // 战神庇护开启
const val MAKE_SOLDIER_FINISH: EventType = 99 //造兵
const val ATK_PLAYER: EventType = 100 //攻击
const val KILL_SOLDIER: EventType = 101 //杀兵
const val DAMAGE_SOLDIER: EventType = 102 //击伤兵
const val MASS: EventType = 103 //集结
//104 105
const val FARM_RES: EventType = 106 //采集获得资源
const val GET_ALLIANCE_COMPETITION_REWARD: EventType = 107 // 领取联盟总动员阶段奖励
const val ALLIANCE_COMPETITION_OVER: EventType = 108 // 领取联盟总动员结束
const val CHAT: EventType = 109 // 进行一次游戏内发言
const val PLAYER_ACTIVITY_ADV_REWARD: EventType = 110 // 挑战获得阶段奖励
const val PLAYER_ACTIVITY_RANK: EventType = 111 // 挑战获得排名
const val STRENGTH_SKIN: EventType = 112 // 强化皮肤
const val GM_REFRESH_EVENT: EventType = 113 // GM模块才会发出的事件 用来修改数据之后进行刷新之类用的,比如任务的刷新
const val GET_NEW_SKIN: EventType = 114 // 获得皮肤
const val MOVE_CITY: EventType = 115 // 迁城
const val GET_BANK_REWARD: EventType = 116 // 获得银行投资奖励
const val GET_NEW_LIBRARY: EventType = 117 // 图书馆新图鉴点亮
const val GET_NEW_PHOTO: EventType = 118 // 获得新头像
const val BUY_SHOP_TOTAL: EventType = 119 // 联盟币商店购买物品
const val RES_CHANGE: EventType = 120 // 资源变动
const val CAVE_SOLIDER: EventType = 121 // 藏兵
const val GET_RESCUE_PRISON_REWARD: EventType = 122 // 获得营救赏金
const val GET_NEW_FRIEND: EventType = 123 // 获得一个好友
const val MAKE_TRAP_FINISH: EventType = 124 //制造陷阱
const val GET_CASINO_BOSS_NUM: EventType = 125 // 赌场，遇见boss
const val GET_CASINO_KILL_BOSS_NUM: EventType = 126 // 赌场，杀死boss
const val KILL_PRISON: EventType = 127 // 处决领主
const val TRANSPORT_RES_SUCCESS: EventType = 128 //成功运输资源
const val POWER_ADD: EventType = 129 //实力增加
const val GET_ALLIANCE_COMPETITION_QUEST_REWARD: EventType = 130 // 领取联盟总动员的任务获得积分奖励
const val JJC_FIGHT_WIN: EventType = 131  // 竞技场战斗胜利
const val SET_ARMY_PLAN: EventType = 132 // 英雄布阵
const val SET_CITY_DEF_HERO: EventType = 133 // 设置守城英雄
const val OCCUPY_WONDER: EventType = 134 // 奇观占领并防守成功
const val CLEAR_TIME_EVENT: EventType = 135 // 加速养成
const val CLEAR_FOG_EVENT: EventType = 136 // 清理迷雾
const val ACTIVITY_SCORE_OVER: EventType = 137 // 挑战分数收录完毕