package com.point18.slg2d.common.constg

/************************游戏日志：玩家行为************************/
/*				新增Action后，需要及时更新ActionName				*/
/****************************************************************/

const val ACTION_INVALID: Int = 0 //无效的行为ID

// 角色
const val ACTION_ACCOUNT_REG: Int = 101 //新用户注册
const val ACTION_ROLE_ACT: Int = 102 //用户激活
const val ACTION_LOGIN: Int = 104 //登录
const val ACTION_PAY: Int = 106 //充值获取
const val ACTION_PAY_REWARD: Int = 108 //充值赠送
const val ACTION_CODE_KEY: Int = 110 //礼包码兑换
const val ACTION_GM_GIVE_HERO_STAR_RES: Int = 112 //后台GM发武将升星材料
const val ACTION_GM_GIVE_HERO_EQUIP_RES: Int = 114 //GM发武将装备升级材料
const val ACTION_MGR_GM_GIVE_EQUIP: Int = 116 //后台GM发道具
const val ACTION_YZ_GM_GIVE_EQUIP: Int = 118 //游族GM发道具
const val ACTION_MISSION_REWARD: Int = 120 //玩家任务奖励
const val ACTION_UNIT_REWARD: Int = 121 //玩家章节任务奖励
const val ACTION_MAIL_REWARD: Int = 122 //领取邮件奖励
const val ACTION_WORLD_TASK_REWARD: Int = 124 //天下大势奖励
const val ACTION_JJC_REWARD: Int = 125 //竞技场奖励
const val ACTION_WORLD_TASK_FINISH: Int = 126 //完成天下大势
const val ACTION_TASK_FINISH: Int = 128 //完成个人任务
const val ACTION_CHANGE_GUIDE: Int = 130 //修改新手引导
const val ACTION_PHOTO_CHANGE: Int = 132 //更换头像
const val ACTION_NAME_CHANGE: Int = 134 //更改名称
const val ACTION_GET_UNIT_TASK_REWARD: Int = 135 //领取章节任务奖励
const val ACTION_GET_ACHIEVEMENT_REWARD: Int = 136 //领取成就奖励
const val ACTION_GET_ACHIEVEMENT_SHARE_REWARD: Int = 137 //领取成就分享奖励

// 城池与建筑
const val ACTION_CREATE_BUILDING: Int = 208 //建造建筑
const val ACTION_UNLOCK_BUILDING: Int = 209 //建筑解锁
const val ACTION_LV_UP_BUILDING: Int = 210 //建筑升级
const val ACTION_DESTROY_BUILDING: Int = 211 //建筑拆除
const val ACTION_CANCEL_LV_BUILDING: Int = 212 //取消建筑升级
const val ACTION_MOVE_CITY: Int = 224 //迁城
const val ACTION_REFRESH_JJC_SHOP: Int = 230 //刷新竞技场商店
const val ACTION_BUY_IN_JJC_SHOP: Int = 232 //竞技场商店购买
const val ACTION_UNLOCK_BUILDINGAREA: Int = 234 //建筑区域(云)解锁
const val ACTION_ACCEPT_BUILDING: Int = 235 //收纳建筑

// 武将
const val ACTION_LV_UP_SKILL: Int = 304 //技能升级
const val ACTION_HERO_ADVANCE: Int = 308 //武将进阶
const val ACTION_HERO_LV_UP: Int = 310 //武将升级
const val ACTION_USE_HERO_CARD: Int = 312 //使用武将卡
const val ACTION_EXTRACT_CHEST: Int = 314 //抽宝箱
const val ACTION_FREE_CHEST: Int = 316 //领取宝箱
const val ACTION_ARMY_CHANGE: Int = 318 //兵种转换
const val ACTION_ARMY_LV_UP: Int = 320 //兵种升级
const val ACTION_SKILL_LV_UP: Int = 322 //技能库升级

const val ACTION_NEW_HERO_LV_UP: Int = 323 //新版武将升级
const val ACTION_NEWHERO_STAR_LV_UP: Int = 324 //新版武将升星
const val ACTION_NEWHERO_AWAKE: Int = 325 //武将进阶
const val ACTION_DRAW_HERO: Int = 326 //新召唤武将
const val ACTION_COMPOUND_HERO: Int = 327 //武将合成
const val ACTION_HERO_HURT_OVER_ATONCE: Int = 328 //武将秒重伤
const val ACTION_HERO_EQUIP_LV_UP: Int = 329 //武将装备进阶
const val ACTION_HERO_SKILL_LV_UP: Int = 330 //武将技能升级
const val ACTION_NEWHERO_CANCEL_STAR_LV_UP: Int = 331 //取消武将升星
const val ACTION_NEWHERO_ARMY_LV_UP: Int = 332 // 武将兵团升级

// 联盟
const val ACTION_ALLIANCE_CREATE: Int = 402 //联盟创建
const val ACTION_ALLIANCE_DONATE_RES: Int = 404 //联盟资源捐献
const val ACTION_ALLIANCE_EXCHANGE: Int = 406 //联盟捐卡
const val ACTION_ALLIANCE_FLAG: Int = 408 //联盟修改旗帜
const val ACTION_ALLIANCE_REMOVE: Int = 410 //联盟成员移除
const val ACTION_ALLIANCE_DISMISS: Int = 412 //联盟解散
const val ACTION_ALLIANCE_MAKE_OVER: Int = 418 //盟主转让
const val ACTION_ALLIANCE_TAKE_OVER: Int = 420 //盟主禅让
const val ACTION_ALLIANCE_SET_POS: Int = 422 //职位任命
const val ACTION_ALLIANCE_SET_RELATION: Int = 424 //设置联盟外交
const val ACTION_ALLIANCE_FIRST_JOIN: Int = 426 //首次加入联盟
const val ACTION_ALLIANCE_IMPEACH: Int = 428 // 弹劾盟主
const val ACTION_ALLIANCE_SET_NAME: Int = 429 // 修改联盟信息
const val ACTION_ALLIANCE_INVITE: Int = 430 // 邀请玩家加入联盟
const val ACTION_ALLIANCE_GIFT_OPEN: Int = 431 // 打开联盟礼物
const val ACTION_GET_ALLIANCE_MISSION_GIFT: Int = 433 // 领取联盟活跃度奖励
const val ACTION_GIDT_ALLIANCE_MEMBER: Int = 434 // 联盟赠礼

// 部队
const val ACTION_BUY_DECREE: Int = 526 //购买政令

//黑名单 550-555
const val ACTION_IN_BLACK_LIST: Int = 550 //加入黑名单
const val ACTION_OFF_BLACK_LIST: Int = 551 //移出黑名单

//藏兵洞穴 556-560
const val ACTION_CONFIG_CAVE: Int = 556 //藏兵
const val ACYION_CANCEL_CAVE: Int = 557 //取消藏兵

// 内政
const val ACTION_RESOURCE_EXCHANGE: Int = 604 //资源交换
const val ACTION_BUY_TOWER_NUM: Int = 610 //购买爬塔券
const val ACTION_USE_TOWER_NUM: Int = 612 //爬塔消耗爬塔券
const val ACTION_USE_BUY_JJC_COUNT: Int = 614 //购买竞技场挑战次数
const val ACTION_USE_ALLIANCE_NICKNAME: Int = 616 //修改联盟昵称
const val ACTION_GET_JJC_RANK_REWARD: Int = 617 //领取竞技场排名奖励

// 战斗
const val ACTION_FIGHT_ALLIANCE_BOSS: Int = 701 //打联盟boss
const val ACTION_FIGHT_BOSS: Int = 702 //打野外魔物
const val ACTION_FIGHT_WONDER: Int = 704 //打奇观
const val ACTION_FIGHT_RELIC: Int = 705 //遗迹
const val ACTION_FIGHT_CASTLE: Int = 706 //玩家
const val ACTION_OCCUPY_CELL: Int = 707 //占领空地
const val ACTION_OCCUPY_STRONGHOLD: Int = 708 //占领据点
const val ACTION_FARM_RES: Int = 709 //采集
const val ACTION_TRANSPORT: Int = 710 //运输
const val ACTION_JOIN_MASS: Int = 711 //加入集结
const val ACTION_SEND_MASS_HOME: Int = 712 //遣返集结
const val ACTION_SEND_REINFORCE_HOME: Int = 713 //遣返增援
const val ACTION_START_MASS: Int = 714 //发起集结
const val ACTION_CANCEL_MASS: Int = 715 //取消集结
const val ACTION_STATION: Int = 716 //驻扎
const val ACTION_REINFORCE_CASTLE: Int = 717 //增援玩家城
const val ACTION_REINFORCE_WONDER: Int = 718 //增援奇观
const val ACTION_SCOUT: Int = 719 //侦查
const val ACTION_GO_BACK_HOME: Int = 720 //召回
const val ACTION_SEND_WONDER_WAR_PLAYER_HOME: Int = 721 //遣返奇观战玩家
const val ACTION_SET_FORCE_PLAN: Int = 722 //设置部队预设
const val ACTION_LOG_PVE_FIGHT_JJC: Int = 723 //竞技场战斗胜利
const val ACTION_SEND_STATION_HOME: Int = 724 //遣返驻扎

const val ACTION_FARM_STRONGHOLD: Int = 730 //据点上供
const val ACTION_FIGHT_TOWER: Int = 731 //爬塔
const val ACTION_FIGHT_MISSION: Int = 732 //推图
const val ACTION_FIGHT_JJC: Int = 733 //jjc
const val ACTION_FIGHT_FOG: Int = 734 //迷雾

// 道具
const val ACTION_BUY_SHOP_NORMAL: Int = 802 //普通商店购买
const val ACTION_REFRESH_SHOP_NORMAL: Int = 806 //普通商店刷新
const val ACTION_BUY_EQUIP: Int = 812 //购买装备
const val ACTION_SELL_EQUIP: Int = 814 //出售装备
const val ACTION_EQUIP_COMP: Int = 816 //道具合成
const val ACTION_EQUIP_SPLIT: Int = 817 //道具分解
const val ACTION_REFRESH_EQUIP_SHOP: Int = 826 //刷新装备商城
const val ACTION_USE_PROPS: Int = 828 //使用道具
const val ACTION_EQUIP_LV_UP: Int = 832 //装备强化
const val ACTION_BUY_DIAMOND_SHOP: Int = 834 //购买钻石商城物品
const val ACTION_OFF_EQUIP: Int = 835 //脱装备
const val ACTION_ON_EQUIP: Int = 836 //穿装备
const val ACTION_AUTO_USE_DECREE: Int = 837 //自动使用行动力

// 科技研发 + 快速使用道具功能 850-880
const val ACTION_RESEARCH_LV_UP: Int = 850 // 普通研发科技
const val ACTION_CANCEL_RESEARCH_LV_UP: Int = 851 // 取消研发科技
const val ACTION_CLEAR_TIME_RESEARCH: Int = 853 // 秒科技CD
const val ACTION_BUY_RESSHOP: Int = 854 // 购买resShop表物品
const val ACTION_CLEAR_TIME_MAKE_SOLIDER: Int = 855 // 秒造兵CD
const val ACTION_CLEAR_TIME_CURE_SOLIDER: Int = 856 // 秒治疗兵CD
const val ACTION_CLEAR_TIME_HERO_STAR_LV: Int = 857 // 秒武将升星
const val ACTION_CLEAR_TIME_HERO_SUPER_LV: Int = 858 // 秒武将升阶
const val ACTION_CLEAR_TIME_SOLIDER_UP: Int = 859 // 秒晋升
const val ACTION_CLEAR_TIME_ALLIANCE_TREASURE: Int = 860 // 秒联盟宝藏
const val ACTION_CLEAR_TIME_INTERIOR_TASK: Int = 861 // 秒内政任务
const val ACTION_CLEAR_TIME_INNER_CITY_BUILDING: Int = 862 // 秒内城建筑
const val ACTION_CLEAR_TIME_RELIC_BOX: Int = 863 // 时光之盒加速

// 兵营功能 881-900
const val ACTION_MAKE_SOLIDER: Int = 881 // 造兵
const val ACTION_CANCEL_MAKE_SOLIDER: Int = 882 // 取消造兵
const val ACTION_CURE_SOLIDER: Int = 884 // 治疗兵
const val ACTION_CANCEL_CURE_SOLIDER: Int = 885 // 取消治疗兵
const val ACTION_SOLIDER_UP: Int = 886 // 兵种晋升
const val ACTION_CANCEL_SOLIDER_UP: Int = 887 // 取消兵种晋升
const val ACTION_DISMISS_SOLIDER: Int = 888 //遣散士兵
const val ACTION_FIRE_FIGHTING: Int = 889 // 城墙灭火

// 遗迹 901-920
const val ACTION_GET_TIME_BOX_REWARD: Int = 901 // 获取时光之盒奖励
const val ACTION_STUDY_TIME_BOX: Int = 902 // 研究时光之盒
const val ACTION_CANCEL_STUDY_TIME_BOX: Int = 903 // 取消研究时光之盒
const val ACTION_REMOVE_TIME_BOX: Int = 904 // 移除时光之盒

// vip 921-930
const val ACTION_VIP_LV_UP_REWARD: Int = 921 //vip升级奖励
const val ACTION_GET_VIP_LOGIN_REWARD: Int = 922 //vip登录奖励
const val ACTION_RESET_FREE_ENERGY: Int = 923 //重置免费行动力

//talent 931-940
const val ACTION_RESET_TALENT: Int = 931 //重置天赋
const val ACTION_UPGRADE_TALENT: Int = 932 //升级天赋
const val ACTION_USE_TALENT_PLAN: Int = 933 //使用天赋预设
const val ACTION_DEL_TALENT_PLAN: Int = 934 //删除天赋预设
const val ACTION_SET_TALENT_PLAN: Int = 935 //设置天赋预设

// 君主装备 941 - 960
const val ACTION_MAKE_KING_EQUIP: Int = 941 // 锻造君主装备
const val ACTION_GET_KING_EQUIP: Int = 942 // 领取君主装备
const val ACTION_SPLIT_KING_EQUIP: Int = 944 // 拆解装备
const val ACTION_CANCEL_MAKE_KING_EQUIP: Int = 945 // 取消锻造
const val ACTION_GET_KING_EQUIP_BY_YBMAKE: Int = 946 // 元宝锻造君主装备
const val ACTION_MAKE_KING_EQUIP_ADD_CARD: Int = 947 // 给一个君主装备打卡片
const val ACTION_MAKE_KING_EQUIP_OFF_CARD: Int = 948 // 取下君主装备上的卡片
const val ACTION_MAKE_KING_EQUIP_COMPOUND_CARD: Int = 949 // 君主装备卡片合成

// 小镇 961-980
const val ACTION_TOWN_EXPEND: Int = 961 // 扩建
const val ACTION_PLANT: Int = 962 // 种菜
const val ACTION_GET_PLANT: Int = 963 // 收菜
const val ACTION_BUY_SEED: Int = 964 // 买菜

// 其他 981 - 1050
const val ACTION_CHAT: Int = 992 //聊天
const val ACTION_GM: Int = 994 //GM命令
const val ACTION_LOGOUT: Int = 999 //退出

// 商船 1051 - 1060
const val ACTION_SHIP_EXCHANGE: Int = 1051 // 商船兑换
const val ACTION_SHIP_LOCK: Int = 1052 // 商船商品加锁

// 联盟币商店 1061 - 1070
const val ACTION_SHOP_TOTAL_BUY: Int = 1061 // 商店购买商品

// 银行 1071 - 1080
const val ACTION_BANK: Int = 1071 // 银行投资
const val ACTION_GET_BANK: Int = 1072 // 收获银行投资
const val ACTION_CANCEL_BANK: Int = 1073 // 取消银行投资
const val ACTION_MAIL_BANK: Int = 1074 // 银行投资到点邮件

// 活动 1081 -1085
const val ACTION_GET_PLAYER_REWARD_BAG: Int = 1081 //领取玩家领奖背包内物品

// 城堡皮肤 1086 -1090
const val ACTION_STRENGTH_SKIN: Int = 1086 //强化皮肤
const val ACTION_BUY_SKIN: Int = 1087 //购买皮肤
const val ACTION_CHANGE_SKIN: Int = 1088 //更换皮肤

// 领主、建筑升级奖励 1091 -1095
const val ACTION_KING_LV_UP: Int = 1091
const val ACTION_BUILDING_LV_UP: Int = 1092

// 内政任务奖励 1096 -1100
const val ACTION_INTERIOR_TASK: Int = 1096 // 进行内政任务
const val ACTION_INTERIOR_RESET: Int = 1097 // 重置内政任务

// 监禁 1101 - 1120
const val ACTION_GO_RANSOM: Int = 1101 // 交钱赎人
const val ACTION_SET_REWARD_GOLD: Int = 1102 // 设置赏金
const val ACTION_EAT_POISON: Int = 1103 // 吃毒蘑菇
const val ACTION_RESCUE_PRISON: Int = 1104 // 解救
const val ACTION_GET_RESURGENCE: Int = 1105 // 领取复活
const val ACTION_KILL_PRISON: Int = 1106 // 杀监禁领主
const val ACTION_FREE_PRISON: Int = 1107 // 是否监禁领主
const val ACTION_BUY_RESURGENCE: Int = 1108 // 购买复活
const val ACTION_SET_RANSOM: Int = 1109 // 设置赎金
const val ACTION_SET_MAX_LV_PRISON_BUFF: Int = 1110 // 购买最高级的监狱buff

// 在线礼包 1121 - 1130
const val ACTION_GET_ONLINE_GIFT: Int = 1121 // 收获在线礼包

// 联盟宝藏 1131 - 1140
const val ACTION_GET_ALLIANCE_TREASURE_REWARD: Int = 1131 // 领取联盟宝藏
const val ACTION_REF_ALLIANCE_TREASURE: Int = 1132 // 手动刷新联盟宝藏

// 红包功能 1141 - 1150
const val ACTION_SEND_RED_BAG: Int = 1141 // 发红包
const val ACTION_GET_RED_BAG: Int = 1142 // 抢红包
const val ACTION_RED_BAG_TIME_OVER_ACTION: Int = 1143 // 红包过期

// 联盟总动员 1151 - 1160
const val ACTION_BUY_ALLIANCE_COMPETITION_QUEST_NUM: Int = 1151 // 购买领取联盟总动员任务次数
const val ACTION_GET_ALLIANCE_COMPETITION_QUEST_REWARD: Int = 1152 // 领取联盟总动员任务的阶段奖励

//官职 1161-1169
const val ACTION_ALLIANCE_REWARD: Int = 1161 //联盟奖励
const val ACTION_OPEN_WHOLE_COUNTRY_BUFF: Int = 1162 //开启全国Buff
const val ACTION_SEND_NOTICE_TO_LEADER: Int = 1163 //发送公告给联盟盟主
const val ACTION_AMNESTY_WHOLE_COUNTRY: Int = 1164 //大赦天下
const val ACTION_EDITOR_COUNTRY_NOTICE: Int = 1165 //编辑国家公告
const val ACTION_SET_OFFICE: Int = 1166 //设置官职
const val ACTION_SEND_WONDER_WAR_AWARD: Int = 1167 //发送奇观战奖励

// 内城 1170 - 1200
const val ACTION_CREATE_INNER_CITY_BUILDING: Int = 1170 // 建造内城建筑
const val ACTION_UP_INNER_CITY_BUILDING: Int = 1171 // 升级内城建筑
const val ACTION_CANCEL_UP_INNER_CITY_BUILDING: Int = 1172 // 取消升级内城建筑
const val ACTION_DESTROY_INNER_CITY_BUILDING: Int = 1173 // 拆除内城建筑
const val ACTION_CANCEL_DESTROY_INNER_CITY_BUILDING: Int = 1174 // 取消拆除内城建筑
const val ACTION_MOVE_INNER_CITY_BUILDING: Int = 1175 // 移动内城建筑
const val ACTION_UNLOCK_INNER_CITY_BUILDING: Int = 1176 // 解锁内城建筑
const val ACTION_UNLOCK_INNER_CITY_AREA: Int = 1177 // 解锁内城区域

// 达到某条件发送邮件的功能 1201 - 1210
const val ACTION_SEND_MAIL_FOR_CODITION: Int = 1201

//新手引导 1211-1215
const val ACTION_GUIDE_CHANGE: Int = 1211 //新手引导步骤变化

//内政英雄 1216-1220
const val ACTION_ON_INT_HERO: Int = 1216 //设置执政英雄
const val ACTION_OFF_INT_HERO: Int = 1217 //取消执政英雄
const val ACTION_SET_DEF_HERO: Int = 1218 //设置守城英雄

//收藏 1221-1225
const val ACTION_ADD_MARK: Int = 1221 //添加收藏
const val ACTION_DEL_MARK: Int = 1222 //删除收藏

//运输 1226-1230
const val ACTION_PUBLISH_TRANSPORT_REQUEST = 1226 //发布运输请求
const val ACTION_DEL_TRANSPORT_REQUEST = 1226 //删除运输请求

// 推图 1231 - 1240
const val ACTION_INSTANCE_GET_STAR_BOX = 1231 // 领取星宝箱
const val ACTION_INSTANCE_GET_BIG_BOX = 1232 // 领取章节大宝箱
const val ACTION_INSTANCE_FIGHT = 1233 // 推图战斗胜利奖励
const val ACTION_INSTANCE_WIPE = 1234 // 推图扫荡奖励

// 挑战 1241 - 1250
const val PLAYER_ACTIVITY_STAGE_REWARD = 1241 // 玩家挑战阶段奖励
const val PLAYER_ACTIVITY_RANK_REWARD = 1242 // 玩家挑战排行奖励
const val ALLIANCE_ACTIVITY_STAGE_REWARD = 1243 // 联盟挑战阶段奖励
const val ALLIANCE_ACTIVITY_RANK_REWARD = 1244 // 联盟挑战排行奖励

// 后宅 1251 - 1260
const val ACTION_BUY_FURNITURE = 1251 // 购买家具
const val ACTION_FURNITURE_PRODUCE = 1252 //家具产出

//迷雾 1261 - 1270
const val ACTION_GET_FOG_REWARD = 1261 //获取迷雾奖励

//赌场 1271 - 1275
const val ACTION_GET_FOG_CASINO = 1271 //获取奖励

//迁服 1276-1300
const val ACTION_MOVE_SERVER = 1276 //迁服

// 充值 1301-1305
const val RECHARGE_GIFTBAG = 1301
const val MONTH_CARD_REWARD = 1302

//玩家行为名称
//var ActionName map[Action]string = map[Action]string
//
//{
//	ACTION_ACCOUNT_REG:       "新用户注册",
//	ACTION_ROLE_ACT:          "用户激活",
//	ACTION_LOGIN:             "登录",
//	ACTION_PAY:               "充值获取",
//	ACTION_PAY_REWARD:        "充值赠送",
//	ACTION_CODE_KEY:          "礼包码兑换",
//	ACTION_MGR_GM_GIVE_HERO:  "后台GM发武将",
//	ACTION_YZ_GM_GIVE_HERO:   "游族GM发武将",
//	ACTION_MGR_GM_GIVE_EQUIP: "后台GM发道具",
//	ACTION_YZ_GM_GIVE_EQUIP:  "游族GM发道具",
//	ACTION_MISSION_REWARD:    "玩家任务奖励",
//	ACTION_MAIL_REWARD:       "领取邮件奖励",
//	ACTION_WORLD_TASK_REWARD: "天下大势奖励",
//	ACTION_JJC_REWARD:        "竞技场奖励",
//	ACTION_TASK_FINISH:       "完成个人任务",
//	ACTION_CHANGE_GUIDE:      "修改新手引导",
//	ACTION_PHOTO_CHANGE:      "更换头像",
//
//	// 建筑
//	ACTION_CREATE_BUILDING:    "建造其他城池",
//	ACTION_UNLOCK_BUILDING:    "建筑解锁",
//	ACTION_LV_UP_BUILDING:     "建筑升级",
//	ACTION_DESTROY_BUILDING:   "建筑拆除",
//	ACTION_CANCEL_LV_BUILDING: "取消建筑升级",
//	ACTION_MOVE_CITY:          "迁城",
//	ACTION_REFRESH_JJC_SHOP:   "刷新竞技场商店",
//	ACTION_BUY_IN_JJC_SHOP:    "竞技场商店购买",
//
//	// 武将
//	ACTION_LV_UP_SKILL:   "技能升级",
//	ACTION_HERO_ADVANCE:  "武将进阶",
//	ACTION_HERO_LV_UP:    "武将升级",
//	ACTION_USE_HERO_CARD: "使用武将卡",
//	ACTION_EXTRACT_CHEST: "抽宝箱",
//	ACTION_FREE_CHEST:    "领取宝箱",
//	ACTION_ARMY_CHANGE:   "兵种转换",
//	ACTION_ARMY_LV_UP:    "兵种升级",
//	ACTION_SKILL_LV_UP:   "技能库升级",
//
//	// 联盟
//	ACTION_ALLIANCE_CREATE:     "联盟创建",
//	ACTION_ALLIANCE_DONATE_RES: "联盟资源捐献",
//	ACTION_ALLIANCE_EXCHANGE:   "联盟捐卡",
//	ACTION_ALLIANCE_FLAG:       "联盟修改旗帜",
//	ACTION_ALLIANCE_REMOVE:     "联盟成员移除",
//	ACTION_ALLIANCE_DISMISS:    "联盟解散",
//	ACTION_ALLIANCE_MAKE_OVER:  "盟主转让",
//	ACTION_ALLIANCE_TAKE_OVER:  "盟主禅让",
//	ACTION_ALLIANCE_SET_POS:    "职位任命",
//
//	// 部队
//	ACTION_BUY_DECREE: "购买政令",
//
//	// 战斗
//	ACTION_FIGHT_TOWER:  "PVE:爬塔",
//	ACTION_FIGHT_CASTLE: "PVP:玩家",
//
//	// 道具
//	ACTION_BUY_SHOP_NORMAL:     "普通商店购买",
//	ACTION_REFRESH_SHOP_NORMAL: "普通商店刷新",
//	ACTION_BUY_EQUIP:           "购买装备",
//	ACTION_SELL_EQUIP:          "出售装备",
//	ACTION_EQUIP_COMP:          "道具合成",
//	ACTION_REFRESH_EQUIP_SHOP:  "刷新装备商城",
//	ACTION_USE_PROPS:           "使用道具",
//
//	// 其他
//	ACTION_CHAT:   "聊天",
//	ACTION_GM:     "GM命令",
//	ACTION_LOGOUT: "退出",
//}
