package com.point18.slg2d.common.constg

const val AllianceMessage_Num: Int = 100 //

//
const val MaxGroupChatMembersNum: Int = 50 // 一个聊天群的最大人数
const val MaxKeepChatRecordTime: Int = 5 // 聊天消息的最大保存时间,单位天
const val MaxKeepChatRecordNum: Int = 50 // 聊天消息的最大保存条数

// 邮件
const val PersonChat: Int = 1 // 私聊
const val GroupChat: Int = 2 // 群组聊

const val CHAT_TYPE_WORLD: Int = 0 // 世界聊天
const val CHAT_TYPE_STATE: Int = 1 // 国家聊天
const val CHAT_TYPE_ALLIANCE: Int = 2 // 联盟频道聊天
const val CHAT_TYPE_PRIVATE: Int = 3 // 私聊聊天
const val CHAT_TYPE_GROUP: Int = 4 // 群聊
const val CHAT_TYPE_SYSTEM: Int = 5 // 系统公告
const val CHAT_TYPE_ALLIANCE_BAIHUTEAM: Int = 6 // 白虎团频道聊天
const val CHAT_TYPE_ALLIANCE_QINGLONGTEAM: Int = 7 // 青龙团频道聊天
const val CHAT_TYPE_ALLIANCE_XUANWUTEAM: Int = 8 // 玄武团频道聊天
const val CHAT_TYPE_ALLIANCE_ZHUQUETEAM: Int = 9 // 朱雀团频道聊天

const val ChatTypeGroupFriend: Int = 100 // 群组聊天(这个常量仅用于323号协议)


// 跑马灯 显示的位置
const val NOTICE_TYPE_CENTER: Int = 1 // 1.屏幕中央
const val NOTICE_TYPE_CHAT: Int = 2 // 2.聊天框
const val NOTICE_TYPE_ALL: Int = 3 // 3.屏幕中央+聊天框

// 聊天消息类型
const val NORMAL_MESSAGE_NOTICE: Int = 1 // 普通消息
const val RED_BAG_MESSAGE_NOTICE: Int = 2 // 红包消息
const val CATTON_DISPLAY: Int = 3 // 表情
const val FIGHT_INFO_SHARE: Int = 4 // 战报分享
const val MASS_INFO: Int = 5 // 集结信息
const val TRMPET: Int = 6 // 喇叭
const val LOCATION_SHARE: Int = 7 // 坐标分享
const val SYSTEM_NOTICE: Int = 8 // 系统通知

// 添加我的人的数据
const val FRIEND_APPLY_WAIT_STATE: Int = 0 // 等待处理
const val FRIEND_APPLY_OK_STATE: Int = 1 // 已经添加
const val FRIEND_APPLY_REMOVE_STATE: Int = 2 // 已经拒绝
