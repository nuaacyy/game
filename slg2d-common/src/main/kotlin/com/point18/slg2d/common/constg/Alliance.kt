package com.point18.slg2d.common.constg

// 联盟总人数
const val ALLIANCE_MAX_MEMBER = 100 // 联盟最大

// 职位对应的权限
typealias RightType = Int

const val A_RIGHT_SET_POS = 1   // 任命职位
const val A_RIGHT_MEMBER_MANAGER = 2   // 成员管理
const val A_RIGHT_SET_DESCRIPTION = 3   // 公告修改
const val A_RIGHT_TOPIC_ALL = 4   // 全体邮件
const val A_RIGHT_DISMISS = 5   // 解散联盟
const val A_RIGHT_MARK = 6   // 联盟标记
const val A_RIGHT_FLAG = 7   // 修改联盟旗帜
const val ALLIANCE_POWER_NAME = 8   // 联盟改名
const val ALLIANCE_POWER_NICKNAME = 9   // 联盟改昵称
const val ALLIANCE_POWER_CITY = 10  // 联盟改所属王城
const val ALLIANCE_POWER_SLOGAN = 11  // 更改联盟标语
const val ALLIANCE_POWER_SIGNOUT = 12  // 退出联盟
const val ALLIANCE_POWER_SHANRANG = 13  // 盟主禅让
const val ALLIANCE_POWER_LANGUAGE = 14  // 更改语种
const val ALLIANCE_POWER_INFO = 15  // 个人信息查看
const val ALLIANCE_POWER_CHAT = 16  // 聊天
const val ALLIANCE_POWER_FIGHTVALUE = 17  // 设置战斗力
const val ALLIANCE_POWER_IMPEACHMENT = 18  // 弹劾盟主
const val ALLIANCE_SUMMON_ALLIANCE_BOSS = 20  // 召唤联盟BOSS
const val ALLIANCE_REMOVE_ALLIANCE_WAIJIAO = 30  // 删除联盟外交留言
const val CANCEL_ALLIANCE_COMPETITION_QUEST = 100 // 取消联盟总动员任务

// 联盟成员的职位

const val ALLIANCE_POSITION_BOSS = 101 //帮主
const val ALLIANCE_POSITION_ASSISTANT = 102 //R2
const val ALLIANCE_POSITION_MANAGER = 103 //3
const val ALLIANCE_POSITION_ELITE = 104 //4
const val ALLIANCE_POSITION_MEMBER = 105 // R5 成员
const val ALLIANCE_POSITION_VALKYRIE = 201 //武神
const val ALLIANCE_POSITION_COMMANDER = 301 //指挥官
const val ALLIANCE_POSITION_DIPLOMAT = 401 //外交官
const val ALLIANCE_POSITION_TUTOR = 501 //导师

// 联盟外交关系

const val RELATION_SHIP_FRIEND = 1 //友好
const val RELATION_SHIP_HOSTILE = 2 //敌对
const val RELATION_SHIP_NEUTRAL = 3 //中立


// 联盟日志类型
typealias  LogType = Int

const val A_LOG_CREATE_ALLIANCE: LogType = 101 //$1创建了联盟
const val A_LOG_ENTER_ALLIANCE: LogType = 102 //$1加入了联盟
const val A_LOG_SET_BIAOYU: LogType = 103 //$1修改了标语
const val A_LOG_MODIFY_NOTICE: LogType = 104 //$1调整了公告
const val A_LOG_CREATE_ALLIANCE_MARK: LogType = 105 //$1发布者名称，$2表示标记名称
const val A_LOG_DELETE_ALLIANCE_MARK: LogType = 106 //$1取消者名称，$2表示标记名称
const val A_LOG_SET_ALLIANCE_NAME: LogType = 107 //修改联盟名称 $1修改者名称
const val A_LOG_SET_ALLIANCE_SHORT_NAME: LogType = 108 //修改联盟简称 $1修改者名称
const val A_LOG_LEAVE_ALLIANCE: LogType = 110 //$1离开了联盟
const val A_LOG_SET_FLAG: LogType = 111 //$1更改了联盟旗帜
const val A_LOG_SET_ALLIANCE_LAN: LogType = 113 //$1更改了语种

const val A_LOG_MAKE_OVER: LogType = 201 //$1将盟主禅让给$2
const val A_LOG_APPOINT: LogType = 202 //$1将$2任命为$3
const val A_LOG_ALLIANCE_JOIN: LogType = 203 //联盟收人 $1批准者，$2加入联盟玩家的名称
const val A_LOG_EXPEL_MEMBER: LogType = 204 //$1将$2移出联盟
const val A_LOG_IMPEACH: LogType = 207 // 弹劾盟主 $1弹劾者，$2被弹劾者
const val A_LOG_SET_POWER: LogType = 208 // 修改联盟免审批战斗力条件 $1修改者名字 $2修改者昵称

// 联盟旗帜类型

const val FLAG_TYPE_COLOR = 1 //旗帜颜色
const val FLAG_TYPE_STYLE = 2 //旗帜样式
const val FLAG_TYPE_EFFECT = 3 //旗帜特效


typealias AllianceMemberFlag = Int

const val ALLIANCE_MEMBER_FLAG_JOIN: AllianceMemberFlag = 1 //加入联盟
const val ALLIANCE_MEMBER_FLAG_QUIT: AllianceMemberFlag = 2 //离开联盟
const val ALLIANCE_MEMBER_FLAG_REMOVE: AllianceMemberFlag = 3 //踢出联盟
const val ALLIANCE_MEMBER_FLAG_POSITION: AllianceMemberFlag = 4 //职位变更
const val ALLIANCE_MEMBER_FLAG_LOGIN_IN: AllianceMemberFlag = 5 //上线
const val ALLIANCE_MEMBER_FLAG_LOGIN_OUT: AllianceMemberFlag = 6 //下线
const val ALLIANCE_MEMBER_FLAG_MOVE_CITY: AllianceMemberFlag = 7 //迁城（导致所属国家发生变化）

// rpc改写公共服联盟成员信息 (游戏服写-->公共服改)
// 下面这几个是游戏服能改的
const val UpdatePower = 1  // 势力
const val UpdateOnlineState = 2  // 是否在线 0-否 1-是
const val UpdatePhotoProtoId = 3  // 头像
const val UpdateHonor = 4  // 荣誉值的增长值,消耗的不算
const val UpdateCanHelpNum = 5  // 可被联盟帮助次数
const val UpdateName = 6  // 更新名称
const val UpdateCastleLv = 7  // 更新主堡等级
const val UpdateKillSolider = 8  // 更新杀敌数
const val UpdateCureSolider = 9  // 更新牺牲值
const val UpdateKillMonsterNum = 10 // 击杀魔物数量
const val UpdateTransportationValue = 11 // 运输数量
const val UpdateVipLv = 12 // VIP
const val UpdateOffice = 13 // 官职
const val UpdateMonsterScore = 14 //更新魔物积分
const val UpdateAllianceNickName = 15 // 联盟昵称

// 子类常量
const val OffType = 0 // 离线
const val OnlineType = 1 // 在线

// 联盟标记类型

const val MARK_NORMAL = 1 // 联盟标记
const val MARK_MASS = 2 // 集结请求

// 联盟邮件主题，每次客户端请求的回复消息数量限制
const val TOPIC_REPLY_COUNT_PRE_PAGE = 4

const val GETNEW_POS = 1 // 新增职位
const val REMOVE_POS = 2 // 被罢免职位

const val SET_ALLIANCE_NAME = 1 // 修改联盟名字
const val SET_ALLIANCE_SHORT_NAME = 2 // 修改联盟昵称
const val SET_ALLIANCE_LAN = 3 // 修改联盟语种

const val ADD_ALLIANCE_INFO = 1 // 新增联盟信息(多个功能都有这种新增,减少的 都用这一组常量)
const val REMOVE_ALLIANCE_INFO = 2 // 减少
const val RESET_ALLIANCE_INFO = 3 // 重置

const val NO_HAVE_ALLIANCE_COMPETITION_TICKET = 0 // 没有联盟总动员门票
const val HAVE_ALLIANCE_COMPETITION_TICKET = 1 // 拥有联盟总动员门票

const val ALLIANCE_COMPETITION_QUEST_REWARD_NO = 0 // 还未领取奖励
const val ALLIANCE_COMPETITION_QUEST_REWARD_GET = 1 // 已经领取奖励

// 公共服开启的活动的编号

const val ALLIANCE_COMPETITION_ACTIVITY = 1 // 联盟总动员

// 公共服开启的活动的状态

const val ALLIANCE_ACTIVITY_GOIN = 1 // 活动开启中
const val ALLIANCE_ACTIVITY_REWARD = 2 // 活动领奖时间中
const val ALLIANCE_ACTIVITY_CLOSE = 3 // 活动结束

// 联盟内排行榜常量

const val IN_ALLIANCE_HONOR_RANK = 1  // 总荣誉值
const val IN_ALLIANCE_KILLSOLIDER_RANK = 2  // 总杀敌值
const val IN_ALLIANCE_CureSolider_RANK = 3  // 总牺牲值
const val IN_ALLIANCE_KillMonster_RANK = 4  // 总猎杀魔物
const val IN_ALLIANCE_WEEKHONOR_RANK = 5  // 周荣誉值
const val IN_ALLIANCE_WEEKKILLSOLIDER_RANK = 6  // 周杀敌值
const val IN_ALLIANCE_WEEKCURESOLIDER_RANK = 7  // 周牺牲值
const val IN_ALLIANCE_WEEKTRANSPORTATIONVALUE_RANK = 8  // 周运输值
const val IN_ALLIANCE_WEEKKILLMONSTER_RANK = 9  // 周猎杀魔物
const val IN_ALLIANCE_HELP_RANK = 10 // 周帮助次数
const val IN_ALLIANCE_COMPETITION_RANK = 11 // 联盟总动员排行 (协议返回里的内容有多个值)
const val IN_ALLIANCE_WEEKMONSTERSCORE_RANK = 12 //周魔物积分排行

// 联盟名占用情况
const val ALLIANCE_NAME_TRY_USE = 0 // 该名字正在尝试被使用了
const val ALLIANCE_NAME_IN_USE = 1 // 该名字已经被占用了

// 修改公共服中央节点联盟名暂用情况
const val ALLIANCE_NAME_TRY2USE = 1 // 把暂用状态变成已用状态
const val ALLIANCE_NAME_DEL_USE = 2 // 删除暂用名,用于解散联盟时

// 联盟总动员状态修改 变化状态 1-获得资格  2-游戏时间结束进行奖励展示  3-活动彻底结束
const val ALLIANCE_COMPETITION_CHANGE_OPEN = 1 // 获得资格
const val ALLIANCE_COMPETITION_CHANGE_OVER = 2 // 游戏时间结束进行奖励展示
const val ALLIANCE_COMPETITION_CHANGE_CLOSE = 3 // 活动彻底结束
