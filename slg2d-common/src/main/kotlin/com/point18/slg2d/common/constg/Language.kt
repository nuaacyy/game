package com.point18.slg2d.common.constg

// LangId枚举，借助这个枚举，可以做配置检测
enum class LangId(langId: String, exceptCheck: Boolean) {

}

const val ZH_CN = "zh-CN"

const val MAIL_SET_POSITION_TITLE: String = "yj_title_6"
const val MAIL_REMOVE_PLAYER_TITLE: String = "yj_title_8"
const val MAIL_SET_POSITION_CONTENT: String = "yj_content_6"
const val MAIL_REMOVE_PLAYER_CONTENT: String = "yj_content_9"
const val MAIL_ALLIANCE_BAMIAN_CONTENT: String = "yj_content_34"


const val YJ_TITLE_21: String = "yj_title_21"   // 邀请玩家加入联盟的标题
const val YJ_CONTENT_35: String = "yj_content_35" // 邀请玩家加入联盟的内容

const val BANK_TITLE: String = "bank_getGoldMail1"   //  投资钻石可回报
const val BANK_CONTENT: String = "bank_getGoldMail2" //  您投资的钻石存单可回报，共获得$1。快去领取把

const val BANK_ACCELERATE_TITLE: String = "bank_getAccelerateMail1"   // 投资加速可回报
const val BANK_ACCELERATE_CONTENT: String = "bank_getAccelerateMail2" // 您投资的加速存单可回报，共获得$1。快去领取把

const val RANK_MAIL_TITLE: String = "rankMailTitle" // $1英雄成功进阶至$2
const val RANK_MAIL: String = "rankMail"      // 您的$1成功进阶提升至$2-(br)-获得以下提升：-(br)-英雄属性的基础值上升 -(br)-英雄带兵数增至$3"

const val STAR_MAIL_TITLE: String = "starMailTitle" // $1英雄成功升星至$2星"
const val STAR_MAIL: String = "starMail"      // 您的$1成功升星提升至$2-(br)-获得以下提升：-(br)-英雄成长率提升 -(br)-军团技能大幅度增强

const val SYSTEM_MAIL: String = "xitongyoujian" //系统邮件
const val RES_BE_FARMING_CONTENT: String = "res_be_farming_content"  //资源点正在被采集
const val FORCE_BE_SEND_HOME_CONTENT: String = "emailType_2"   //你的部队已被$1遣返
const val MASS_DISMISS_CONTENT: String = "emailType_12"  //集结解散
const val TARGET_UNABLE_ATTACK_CONTENT: String = "emailType_13"  //目标无法攻击
const val TARGET_DISAPPEAR_CONTENT: String = "marchWarning_1" //因为目标消失，无法$1，部队返回
const val ALLIANCE_RELATION_CHANGE_CONTENT: String = "marchWarning_2" //因为联盟关系发生变化，无法进行$1,部队返回
const val HAVE_REINFORCE_CONTENT: String = "marchWarning_3" //目标点已有玩家驻防，部队返回
const val SELF_HAVE_COVER_CONTENT: String = "baohuzhaomail_1" //自身有保护罩，无法攻击
const val WALK_PARAS: String = "marchType_" //行军类型参数
const val WALK_BE_SEND_HOME_CONTENT: String = "marchWarning_4" //你的部队已被遣返
const val EXCHANGE_WONDER_DEF_FAIL: String = "exchangeWonderDef" //奇观换防失败，当前的指挥官必须解散后才能换防

const val RECEIVE_ALLIANCE_AWARD_TITLE: String = "kingdomAwardMailTitle" //领取联盟奖励标题
const val RECEIVE_ALLIANCE_AWARD_CONTENT: String = "kingdomAwardMail"      //领取联盟奖励内容

const val SEND_NOTICE_TO_LEADER_TITLE: String = "noticeTitle" //发送国家公告给盟主的邮件标题
const val COUNTRY_NOTICE_SYSTEM_CHAT: String = "kingInform" //系统通知国家公告

const val AMNESTY_WHOLE_COUNTRY_TITLE: String = "pardonMailTitle"   //国王天下大赦
const val AMNESTY_WHOLE_COUNTRY_CONTENT: String = "pardonMailDes"     //您所处王国的国王使用了天下大赦，所有监禁的领主被释放
const val BE_AMNESTY_TITLE: String = "prisonerMailTitle" //领主已被释放
const val BE_AMNESTY_CONTENT: String = "prisonerMailDes"   //您所处王国的国王使用了天下大赦，您的领主被释放

const val NOTICE_OFFICE: String = "setOffice"      //您已被册封为$1
const val NOTICE_OTHER_OFFICE: String = "setOfficeOther" //$1已被册封为$2

const val BE_SPEED: String = "highSpeed_1" //你的部队已经受到加速帮助
const val ALLIANCE_MEMBER_SPEED: String = "highSpeed_2" //盟军加速，正准备靠近你的领地
const val ENEMY_SPEED: String = "highSpeed_3" //敌军加速，正准备靠近你的领地

const val PLAYER_ACTIVITY_STAGE_TITLE: String = "yj_title_101"   // 个人挑战阶段奖励标题
const val PLAYER_ACTIVITY_STAGE_CONTENT: String = "yj_content_101" //个人挑战阶段奖励内容
const val PLAYER_ACTIVITY_RANK_TITLE: String = "yj_title_102"   //个人挑战排行奖励标题
const val PLAYER_ACTIVITY_RANK_CONTENT: String = "yj_content_102" //个人挑战排行奖励内容
const val ALLIANCE_ACTIVITY_STAGE_TITLE: String = "yj_title_103"   //联盟挑战阶段奖励标题
const val ALLIANCE_ACTIVITY_STAGE_CONTENT: String = "yj_content_103" //联盟挑战阶段奖励内容
const val ALLIANCE_ACTIVITY_RANK_TITLE: String = "yj_title_104"   //联盟挑战排行奖励标题
const val ALLIANCE_ACTIVITY_RANK_CONTENT: String = "yj_content_104" //联盟挑战排行奖励呢内容
const val WONDER_ALLIANCE_RANK_TITLE: String = "yj_title_201" //奇观进行曲联盟排行奖励标题
const val WONDER_ALLIANCE_RANK_CONTENT: String = "yj_content_201" //奇观进行曲联盟排行奖励呢内容
const val ACTIVITY_BOSS_ALLIANCE_RANK_TITLE: String = "yj_title_202" //活动boss联盟排行奖励标题
const val ACTIVITY_BOSS_ALLIANCE_RANK_CONTENT: String = "yj_content_202" //活动boss联盟排行奖励呢内容

const val TITLE_LORD_RELEASE: String = "titleLordRelease"// 领主已被释放
const val CONTENT_LORD_RELEASE: String = "contentLordRelease" // 您的领主已被$1释放

const val TITLE_LORD_RUN: String = "titleLordRun" // 领主已自动逃脱
const val CONTENT_LORD_RUN: String = "contentLordRun" // 您的领主已自动逃脱

const val TITLE_PRISON_FULL: String = "titlePrisonFull" // 监狱已满
const val CONTENT_PRISON_FULL: String = "contentPrisonFull" // 由于监狱已满，领主$1已自动逃脱

const val TITLE_RANSOM: String = "titleRansom" // 赎金通知
const val CONTENT_RANSOM: String = "contentRansom" // $1：缴纳$2赎金可赎回你的领主

const val TITLE_RANSOM_GET: String = "titleRansomGet" // 收到赎金
const val CONTENT_RANSOM_GET: String = "contentRansomGet" // 您收到赎金$1（已扣除30%的税）

const val TITLE_LORD_WAR_RELEASE: String = "titleLordWarRelease" // 领主获救
const val CONTENT_LORD_WAR_RELEASE: String = "contentLordWarRelease" // 由于$1城堡被攻破，您的领主已获救

const val TITLE_LORD_BE_RELEASE: String = "titleLordBeRelease" // 领主释放
const val CONTENT_LORD_BE_RELEASE: String = "contentLordBeRelease" // $1已经主动释放了您的领主

const val TITLE_REWARD_GET: String = "titleRewardGet"  // 获得赏金
const val CONTENT_REWARD_GET: String = "contentRewardGet"  // 您攻破了$1的城堡，释放了所有领主。获得$1赏金

const val TITLE_POISON_USE: String = "titlePoisonUse"    // 囚犯使用毒蘑菇
const val CONTENT_POISON_USE: String = "contentPoisonUse"      // 您监禁的囚犯$1使用了毒蘑菇，将在$2后毒发身亡（毒发身亡的领主不会获得神圣之力加成）

const val TITLE_POISON_DIE: String = "titlePoisonDie" // 囚犯已毒发身亡
const val CONTENT_POISON_DIE: String = "contentPoisonDie" // 您监禁的囚犯$1已经毒发身亡（毒发身亡的领主不会获得神圣之力加成）

const val TITLE_POISON_LORD_DIE: String = "titlePoisonLordDie"     // 领主已毒发身亡
const val CONTENT_POISON_LORD_DIE: String = "contentPoisonLordDie" // 您的领主已毒发身亡，运送回了您的领地

const val TERMINATE_PVP_WALK_TITLE: String = "terminate_pvp_1" //战神庇护开启，已终止所有战争行为
const val TERMINATE_PVP_WALK_CONTENT: String = "terminate_pvp_2" //战神庇护期间，已终止您的所有战争行为。您可通过攻击以玩家为目标的单位解除战神庇护
const val AMNESTY_BY_WONDER_WAR_TITLE: String = "wonder_amnesty_1"   //战神庇护开启，已释放所有领主
const val AMNESTY_BY_WONDER_WAR_CONTENT: String = "wonder_amnesty_2"   //战神庇护期间，已经释放所有的领主
const val BE_AMNESTY_BY_WONDER_WAR_TITLE: String = "wonder_amnesty_3"    //战神庇护开启，领主已被释放
const val BE_AMNESTY_BY_WONDER_WAR_CONTENT: String = "wonder_amnesty_4"    //由于战神庇护开启，您的领主被释放

const val FOUR_DRAGON_ADVANCE_TITLE: String = "four_dragon_mail_1"
const val FOUR_DRAGON_ADVANCE_CONTENT: String = "four_dragon_mail_2"

const val ROOM_DISMISS_TITLE: String = "yj_title_301"
const val ROOM_DISMISS_CONTENT: String = "yj_content_301"

const val MARQUEE_ACTIVITY_BOSS_APPEAR: String = "boss_openBroadcast"