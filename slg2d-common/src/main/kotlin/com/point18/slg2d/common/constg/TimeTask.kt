package com.point18.slg2d.common.constg

//定时任务大类型常量
const val TimeTaskRefreshTarget: Int = 1  // 刷新每周成就排行榜
const val TimeTaskRefreshWorldTask: Int = 2  // 每五分钟刷新特定的一些天下大势任务
const val RecordOnlineLogint: Int = 3  // 每五分钟记录一次在线人数
const val RecordRankInfo: Int = 4  // 0点记录排行榜信息
const val RefreshBigRank: Int = 5  // 每30分钟刷新一次总排行榜
const val RefreshCracksCard: Int = 6  // 每天在固定时间点刷新卡包
const val RefreshCracksThief: Int = 7  // 每天在固定时间点刷新贼兵
const val RefreshWeekRank: Int = 8  // 每5分钟刷新一次周排行榜
const val AllianceDeleteLogs: Int = 10 // 每天0点10分删除联盟过期日志
const val AllianceDeleteTopics: Int = 11 // 每天0点15分删除联盟过期邮件主题
const val AllPayNumInFiveMin: Int = 12 // 每5分钟记录本五分钟的充值金额
const val DealOnLineLoginInOut: Int = 13 // 每天00:00补发在线玩家的登入/登录日志
const val SnapShotServerLog: Int = 14
const val SnapshotPlayerResourceDailyLog: Int = 15
const val SnapshotPlayerBasicDailyLog: Int = 16
const val SnapShotCastleLog: Int = 17
const val OnlineReport: Int = 19 // 每1分钟上传在线人数到游族
const val RefreshTower: Int = 20 // 每周日0点重置所有玩家的爬塔数据
const val RefreshJjcRank: Int = 21 // 每天21点30,保存JJC排行榜数据并且发奖励
const val RefreshRelic: Int = 22 // 每1小时刷新对应等级的遗迹
const val RefreshBigMap: Int = 23 // 每天定时刷新大地图元素
const val CheckActivityOpen: Int = 24 // 每1小时检测是否有新活动开始
const val RefreshGetVipReward: Int = 25 // 每天定时领取vip奖励
