package com.point18.slg2d.common.resultcode

import com.point18.slg2d.common.netmsg.EnumConverter
import com.point18.slg2d.common.netmsg.buildValueMap

enum class ResultCode(val code: Int) {

    SUCCESS(1),
    PARAMETER_ERROR(2), //传递的参数不正确(用于协议内容错误,刷包协议)
    PARAMETER_CANT_PARSE_JSON(3), // 传递的参数无法按照json解析

    NAME_LONG(4), //角色名太长
    OFFLINE(5), //对方不在线
    UNLAWFUL_NAME(6), //名字不合法
    GAG_TIME(7), //禁言中
    IP_CLOSE(8), //账号封禁中
    IP_NONENTITY(9), //帐号不存在
    KEYWORDS(10), //关键词不允许

    // 通讯
    NO_XML_PROTO(11), // XML不存在模板
    NAME_NIL(12), // 名字长度不够
    NAME_NONENTITY(13), //名字不存在
    IN_BLACK(14), //对方正在黑名单中，不能聊天
    USE_NAME(15), //名字已经被使用
    NO_PLAYER(18), // 玩家不存在

    // kafka连接
    GET_CONNECTION_FAILED(21),

    CONN_ERR_FOR_DUANXIANCHONGLIAN(22), // 断线重连失败,原因顶号冷却

    IN_OTHER_BLACK(23), // 我被对方拉黑了.无法发送消息给他

    AREABASE_NULL(24), // areabase为空

    ROUTINE_IS_RUN(25), // 游戏区未关闭,无法执行此操作
    NO_FIND_DB_NAME(26), // 未找到该删除的数据库名
    LONG_CONN_ERROR(27), // 远程连接无法访问
    NO_FIND_TARGET_AREA_CONFIG(28), // 找不到目标区对应的配置
    CANT_GET_DB_CONNECTION(29), // 拿不到数据库连接

    // 登录
    HAS_NOT_LOGIN(31), // 尚未登录
    ERROR_ACC_PWD(32), // 错误的账号密码
    HAS_NOT_ENTER_GAME(33), // 尚未进入游戏
    SIGN_ERROR(34), // 登录令牌签名错误
    NAME_HAS_SPACE(35), // 名称不能含有空格
    NAME_LENGTH_EXCEED(36), // 名称超过规定长度
    NO_SIGN_KEY(37), // 没有找到匹配的密钥
    SERVER_MAX_PLAYER(38), // 服务器人数已满
    SESSION_NOT_FOUND(39), // 登陆后找不到session

    // 创建城池
    CASTLE_EXISTED(41), // 玩家已创建
    CASTLE_DONT_EXISTED(42), // 城池不存在
    NO_VALID_BORN_POS(43), // 找不到可用的出生点
    NO_CITY_EXTEND(44), // 扩建次数不足
    EXTEND_BEFORE(45), // 该位置已经被扩建

    // 黑白名单提示
    BLACK_ROSTER(46), // 用户在黑名单中
    WHITE_ROSTER(47), // 服务器维护中，用户不在白名单中
    SERVER_OVER(48), // 服务器关闭

    IS_IN_BLACK(70), // 玩家已经在黑名单列表中
    ISNOT_BLACKPLAYER(71), // 该玩家不在黑名单列表
    FRIEND_NO_BLACK(72), // 好友不能加入黑名单
    IS_IN_OTHER_BLACK(73), // 您已被对方拉入黑名单

    // 更换头像
    PHOTO_HERO_PROTO_ID_NOT_EXISTS(81), //背包中没有该武将卡
    PHOTO_HERO_PROTO_IS_EXPIRE(82), //当前头像已经是这个武将卡
    PHOTO_ID_LENGTH_EXCEED(83), //无法处理'玩家头像'参数
    PHOTO_NO_EXISTS(84),

    // 好友
    OVER_MORE_LENGTH(89), // 组名长度不符
    NO_MORE_FRIEND(90), // 好友数量已达上限
    IS_FRIEND(91), // 该玩家已存在好友列表中
    NOT_FRIEND(92), // 该玩家不在你的好友列表中
    GROUP_NUM_MORE(93), // 联系人分组数量已达上限
    NOT_FRIEND_GROUP(94), // 该好友分组不存在
    IN_GROUP(95), // 好友已经在聊天组中了
    GROUP_NO_MEMBER(96), // 该群成员超出上限
    MAKE_FRIEND_ERR(97), // 不能加自己为好友
    WAIT_TALK(98),       // 发言CD冷却中，稍后再试
    CHECK_WORD_ERR(100), // 含有非法屏蔽字

    // 升级
    FAILUREUP(102), // 升级失败
    FAILUREUPTOP(103), // 升级到达顶级

    // 玩家名字检测 110 - 120
    PLAYER_NAME_ERROR(110), // 玩家名字不合法

    // 聊天相关 121-135
    NO_CHAT_ROOM(121),           // 未找到对应聊天室
    NO_AT_ROOM(122),            // 玩家不在当前聊天室
    HAVE_ROOM(123),             // 这个人已经在这个聊天室了
    ROOM_FULL(124),             // 群满人了
    OWNER_CAN_NOT_QUIT(125),    // 群主不能退群
    SAME_ROOM_NAME(126),        // 聊天群名相同
    NO_MORE_ROOM(127),          // 聊天群超出数量限制
    IN_HIS_BLACK_LIST(128),     // 在对方的黑名单中

    // 武将
    NO_HERO(200), //武将不存在
    HERO_IN_FORCE(201), //相同模板的武将已经在某部队中上阵
    NO_PROTO_HERO(202), //当前没有正在上阵的此武将模板
    HERO_POS_STATE_ERROR(203), //英雄位置错误
    NO_FIND_HERO_EQUIP(204), //找不到指定武将装备
    HERO_EQUIP_MAX_LV(205), //武将装备已满级
    HERO_EQUIP_LV_UP_CONDITION_ERROR(206), //前置条件不满足
    HERO_STARLV_IN(207), //武将正在升星中
    HERO_STAR_LV_UP_QUEUE_ERROR(208), //升星队列不足
    HERO_SUPER_LV_UP_QUEUE_ERROR(209), //升阶队列不足
    HERO_STAR_MAX_LV(210), //已经升到满星
    HERO_SUPER_MAX_LV(211), //已经升到满阶
    HERO_SUPERLV_IN(212), //武将正在升阶中
    HERO_SUPERLV_EQUIP_ERROR(213), //装备等级不允许武将进阶
    HERO_SKILL_LVUP_SUPER_LV_ERROR(214), //技能等级已达到阶级最高
    HERO_CANCEL_STAR_LVUP_ERROR(215), //武将不在升星中
    HERO_CANCEL_SUPER_LVUP_ERROR(216), //武将不在升阶中
    HERO_SURVIVE(217), // 英雄存活

    // 学习技能
    SKILL_DOES_NOT_EXIST(500), // 该技能不存在
    SKILL_DOES_NOT_LV(501), // 武将等级不够
    SKILL_ALREADY_EXIST(502), // 已有技能
    SKILL_LOCK(503), // 无法卸载的技能
    SKILL_ERR(504), // 技能错误
    SKILL_COUNT_ERR(505), // 该技能学习次数过多
    MAP_PROTO_NOT_EXIST(506), // 地图模板不存在
    MAP_PROTO_NOT_EXIST1(507), // NPC怪不存在

    // 交易
    SAME_RESOURCE_TYPE(650), // 同种资源类型 无法交易
    MORE_MAX(651), // 超出资源上限
    LESS_RESOUCE(652), // 资源不足
    RES_ERROR(653), // 资源错误

    // 部队数据错误
    NO_HERO_IN_FORCE_ERROR(670), //部队中无英雄
    HAVE_HERO_IN_FORCE_ERROR(671), //部队中有英雄
    HERO_IN_FORCE_OUT_OF_RANGE_ERROR(672), //英雄超出上限
    NO_SOLIDER_IN_FORCE_ERROR(673), //部队中无士兵
    HAVE_SOLIDER_IN_FORCE_ERROR(674), //部队中有士兵
    SOLIDER_IN_FORCE_OUT_OF_RANGE_ERROR(675), //士兵超出上限
    NO_ENOUGH_SOLIDER_IN_BARRACK_ERROR(676), //兵营中无足够的士兵

    // 标记
    MARK_NOT_ADD(701), // 无法添加标记，标记已存在
    MARK_NOT_DEL(702), // 无法删除标记，标记不存在
    MARK_ERR(703), // 标记错误
    MARK_NUM_EXCEED(704), // 标记数目已达上限

    // 新的建筑
    BUILDING_INVALID_LOCATION_ID(711), // 无效的位置ID
    BUILDING_UN_PAIR_LOCATION_SCENE(712), // 场景是不匹配
    BUILDING_LOCATION_IS_BUILDING(714), // 建筑正在升级中
    BUILDING_NEXT_BUILDING_NOT_EXIST(715), // 已达到建筑最高等级
    BUILDING_BUILDING_QUEUE_EXCEED(716), // 建筑队列已满
    BUILDING_BUILDING_PRE_BUILD_INVALID(717), // 前置建筑等级不足
    BUILDING_BUILDING_WORLD_TASK_INVALID(718), // 天下大势等级不足
    BUILDING_BUILDING_CANCEL_UPGRADE_INVALID(719), // 建筑不在升级中
    BUILDING_BUILDING_ALREADY_UNLOCK(721), // 此位置已经解锁
    BUILDING_BUILDING_UNLOCK_NOT_NEED(722), // 此位置不需要解锁
    BUILDING_BUILDING_IS_DESTROYING(724), // 建筑处于拆除中
    BUILDING_BUILDING_CANCEL_DESTROY_INVALID(725), // 建筑不在拆除中
    BUILDING_BUILDING_PROTO_NOT_EXIST(726), // 找不到此建筑的配置模版
    BUILDING_BUILDING_SCENE_EXCEED(727), // 建筑数量已达场景规定上限
    BUILDING_BUILDING_AREA_NO_LOCK_ERROR(729), // 该区域尚未解锁
    BUILDINGID_NOT_EXIST(730), // 无效的位置ID
    BUILDING_TYPE_NOT_EXIST(731), // 建筑类型不存在
    BUILDING_POSTION_REPEAT(732), // 该位置无法建造建筑
    BUILDING_TYPE_AND_LV1_NOT_EXIST(733), // 该类型的1级模板找不到
    BUILDING_INVALID_INSTANCE_ID(734), // 无效的建筑实例唯一ID

    // 竞技场
    JJC_FORCE_GRID_NOT_EXIST(740), // 尚未配置的布阵
    JJC_FORCE_GRID_NO_HERO(741), // 无效的武将卡
    JJC_FORCE_GRID_REPEAT_HERO(742), // 武将重复上阵
    JJC_FORCE_GRID_REPEAT_FORCE(743), // 部队重复配置
    JJC_FORCE_GRID_PLAN_NAME_ERROR(744), // 布阵名称不合法
    JJC_FORCE_GRID_PLAN_NAME_NOT_ENOUGH(745), // 布阵名称长度不足
    JJC_FORCE_GRID_PLAN_NAME_EXCEED(746), // 布阵名称长度超过规定字数限制
    JJC_FORCE_GRID_PLAN_NUM_EXCEED(747), // 布阵数量超过上限
    JJC_FORCE_GRID_FORCE_NUM_EXCEED(748), // 部队数量超过上限
    JJC_FORCE_GRID_DEFENSE_FORCE_EXCEED(749), // 防守布阵至少设置一个大营
    JJC_REWARD_ALREADY_DRAW(750), // 奖励已经领取
    JJC_REWARD_MAX_RANK_FORBIDDEN(751), // 最高排名条件不足
    JJC_REWARD_SCORE_FORBIDDEN(752), // 积分条件不足
    JJC_REWARD_DIFFERENCE(753), // 不是相同类型的奖励
    JJC_SHOP_BUY_HISTORY_FORBIDDEN(754), // 无法购买的历史赛季商品
    JJC_SHOP_BUY_NOW_FORBIDDEN(755), // 不能重复购买的商品
    JJC_SHOP_BUY_NOW_NOT_FOUND(756), // 商店中没有这个商品
    JJC_WORLD_INFO_NOT_FOUND(757),   // 世界的竞技场信息找不到
    JJC_FIGHT_CD(760), // 处于挑战CD中
    JJC_FIGHT_ERROR_NO_FIND_DEF_PLAYER(761), // 挑战对手不处于我的挑战列表内
    JJC_FIGHT_ERROR_NO_FIGHT_NUM(762), // 挑战次数不足
    JJC_FIGHT_ERROR_ENOUGH_FIGHT_NUM(763), // 挑战次数充足,无需购买
    JJC_FIGHT_ERROR_NO_CD(764), // 当前不处于挑战CD中,无法清除
    JJC_SHOP_SQL_NOT_EXIST(765), // 竞技场商店记录不存在
    JJC_SHOP_ITEM_NO_EXIST(766), // 竞技场商店没有这个项目
    JJC_REWARD_ITEM_GOT(767), // 竞技场已经领取过没有这个项目

    // 联盟
    ALLIANCE_ARGS_ERROR(800), //服务端接收到的参数不正确（参数个数不正确或必须项为空）
    ALLIANCE_NAME_ALREADY_EXISTED(801), //联盟名称已存在
    ALLIANCE_COIN_NOT_ENOUGH(802), //玩家拥有的铜币数量不足
    ALLIANCE_GOLD_NOT_ENOUGH(803), //玩家拥有的钻石数量不足
    ALLIANCE_MAIN_CITY_LV_NOT_ENOUGH(804), //玩家城主府等级不足
    ALLIANCE_PUNISH_TIME(805), //玩家处于退出联盟惩罚时间
    ALLIANCE_PLAYER_HAS_ALLIANCE(806), //玩家已加入联盟
    ALLIANCE_REQ_ALREADY_EXIST(807), //已申请加入该联盟
    ALLIANCE_POWER_NOT_ENOUGH(808), //战斗力未达到对方联盟要求
    ALLIANCE_SHORT_NAME_NOT_INPUT(809), //联盟简称不能为空
    ALLIANCE_SHORT_NAME_NOT_ALLOWED(810), //联盟简称不合法
    ALLIANCE_SHORT_NAME_ALREADY_EXIST(811), //联盟简称已存在
    ALLIANCE_NAME_LENGTH_NOT_ENOUGH(812), //联盟名称长度不足
    ALLIANCE_SHORT_NAME_NOT_ENOUGH(813), //联盟简称长度不足
    ALLIANCE_PLAYERS_EXCEED(814), //联盟人数已经达到招收人数上限
    ALLIANCE_NAME_ERROR(815), //联盟名称不合法
    ALLIANCE_NAME_LENGTH_EXCEED(816), //联盟名称超过规定字数限制
    ALLIANCE_DESCRIPTION_LENGTH_EXCEED(817), //联盟公告超过规定字数限制
    ALLIANCE_MARK_TITLE_LENGTH_EXCEED(818), //标题超过规定字数限制
    ALLIANCE_MARK_DESCRIPTION_LENGTH_EXCEED(819), //内容超过规定字数限制
    ALLIANCE_SHORT_NAME_LENGTH_EXCEED(820), //联盟简称超过规定字数限制
    ALLIANCE_JOIN_REQ_NOT_EXIST(821), //找不到玩家加入此联盟的请求
    ALLIANCE_ALLOWED_ALREADY_EXIST(822), //玩家已加入其他联盟
    ALLIANCE_MARK_TITLE_LENGTH_NOT_ENOUGH(823), //联盟标题长度不足
    ALLIANCE_MARK_DESCRIPTION_LENGTH_NOT_ENOUGH(824), //联盟内容长度不足
    ALLIANCE_QUERY_NOT_EXIST(825), //联盟不存在
    ALLIANCE_PLAYER_RELATION_IS_OCCUPIED(826), //玩家处于沦陷中,无法更改联盟关系
    ALLIANCE_PLAYER_MANAGER_IS_OCCUPIED(827), //玩家处于沦陷中,无法进行成员管理
    ALLIANCE_PLAYER_JOIN_IS_OCCUPIED(828), //玩家处于沦陷中,无法加入联盟
    ALLIANCE_MARK_COUNT_EXCEED(829), //联盟标记数量已达上限
    ALLIANCE_REMOVE_FORBIDDEN(831), //执行移除操作的玩家和被移除玩家不是同一联盟
    ALLIANCE_SET_POSITION_FORBIDDEN(832), //没有权限修改玩家在联盟中的职位失败
    ALLIANCE_SET_POSITION_EXCEED(833), //该职位玩家已达上限
    ALLIANCE_SET_DESCRIPTION_FORBIDDEN(834), //联盟公告中存在敏感字符
    ALLIANCE_MAKE_OVER_DOING(835), //盟主转让正在进行中
    ALLIANCE_SET_POSITION_ONLY_NORMAL(836), //仅可将普通成员任命为团成员
    ALLIANCE_QUIT_FORBIDDEN(841), //如果是盟主，则必须先进行禅让后，才可以退出联盟
    ALLIANCE_PLAYER_NO_JOIN(842), //玩家没有加入联盟
    ALLIANCE_RESOURCE_NOT_ENOUGH(851), //玩家资源数量不足
    ALLIANCE_RESOURCE_ALREADY_MAX(852), //联盟等级已达最高等级
    ALLIANCE_SET_RELATION_FORBIDDEN(861), //只能是中立、友好、敌对中任一种
    ALLIANCE_EXCHANGE_HERO_NOT_ALLOWED(862), //武将品质不在需求范围内
    ALLIANCE_FLAG_NOT_SET(863), //联盟旗帜没有发生改变
    ALLIANCE_FLAG_NOT_EXIST(864), //没有找到旗帜类型
    ALLIANCE_TOPIC_TITLE_LENGTH_EXCEED(865), //标题长度不足或超过规定长度
    ALLIANCE_TOPIC_MESSAGE_LENGTH_EXCEED(866), //内容长度不足或超过规定长度
    ALLIANCE_REPLY_MESSAGE_LENGTH_EXCEED(866), //消息长度不足或超过规定长度
    ALLIANCE_TOPIC_NOT_EXISTS(867), //联盟邮件不存在
    ALLIANCE_SET_RELATION_DURATION(868), //修改外交关系处于冷却中
    ALLIANCE_PLAYER_IS_OCCUPIED(870), //玩家处于沦陷中
    ALLIANCE_REBEL_NOT_OCCUPIED(871), //玩家没有被沦陷、不需要上缴资源
    ALLIANCE_REBEL_RES_NOT_ENOUGH(872), //玩家缴纳的资源数不足
    ALLIANCE_REBEL_RESOURCE_MORE(873), //多缴了反叛资源
    ALLIANCE_SET_OFFICE_NOT_ALLOWED(874), //联盟没有占领NPC城池
    ALLIANCE_SET_OFFICE_NOT_COUNTY(875), //只能选择郡城任命太守
    ALLIANCE_NOT_NPC_CITY(876), //坐标所在地不是NPC城池
    ALLIANCE_MISSION_SET_FORBIDDEN(880), //无法选择所属联盟为目标
    ALLIANCE_MISSION_OVER_NOT_ACCEPT(881), //结束时间不在范围内
    ALLIANCE_MISSION_PUBLISH_REPEAT(882), //联盟目标不能重复发布
    ALLIANCE_MISSION_QTY_EXCEED(883), //已经有2个联盟目标正在进行中
    ALLIANCE_MISSION_OPEN_LV_NOT_REACH(884), //联盟目标未达到开启等级
    ALLIANCE_MISSION_NPC_CITY_ALREADY_HOLD(885), //NPC城池已经被占领
    ALLIANCE_MISSION_PUBLISH_DURATION(886), //发布联盟目标处于冷却中
    ALLIANCE_MISSION_NOT_EXIST(887), //联盟尚未发布该类型的任务
    ALLIANCE_MISSION_CLOSE_FORBIDDEN(893), //联盟目标处于不可关闭中
    ALLIANCE_EXCHANGE_HERO_NOT_EXIST(894), //背包中没有这个武将卡
    ALLIANCE_EXCHANGE_DEMAND_COLD_TIME(895), //发布需求处于冷却中
    ALLIANCE_EXCHANGE_SAME_ALLIANCE_ONLY(896), //只有同一联盟的玩家之间才可换卡
    ALLIANCE_EXCHANGE_DEMAND_EXPIRE(897), //捐卡需求可能已过期
    ALLIANCE_EXCHANGE_DONATE_LIMIT(898), //已达单次捐献上限
    ALLIANCE_POSITION_RIGHT_FORBIDDEN(899), //权限表中找不到对应操作权限
    ALLIANCE_EXCHANGE_DONATE_DAY_LIMIT(900), //已达单日捐献上限
    DECREE_REACH_TOP(901), //政令达到上限
    DECREE_BUY_REACH_TOP(902), //政令购买达到上限
    DECREE_BUY_SHORTAGE(903), //政令购买钻石不足
    BUY_NUM_ERROR(904), //本日购买次数已达到上限
    MEMBER_POS_MAX_ERROR(905), //该成员已达到职位数量上限
    SAME_POS_ERROR(906), //职位互斥,无法设置
    ALLIANCE_POSITION_NO_ENERGH(907), //权限不足
    MEMBER_DISMISS_ALLIANCE_ERROR(908), //联盟成员数量不允许解散联盟
    IM_ERROR_NO_FIND_TIME(909), //弹劾失败,找不到该职位的弹劾时间
    IM_ERROR_TIME_NO_ENOUGH(910), //弹劾失败,弹劾时间不足
    INVITE_PLAYER_CD(911), //邀请玩家冷却中
    ALLIANCE_GIFT_TIME_OVER(912), //礼物已经超时
    ALLIANCE_GIFT_HAVE(913), //礼物已经领取过
    ALLIANCE_GIFT_ERROR(914), //礼物数据异常
    ALLIANCE_GIFT_REMOVE_ERROR(915), //可领取的礼物无法删除
    ALLIANCE_NO_FIND_BOSS(916), //找不到可召唤的联盟BOSS
    ALLIANCE_NO_FIND_BOSS_ID(917), //传入的bossId异常
    ALLIANCE_NO_ENOUGN_BOSS_SCORE(918), //积分不足
    BIG_MAP_ERROR(919), //超出地图
    ALLIANCE_MISSION_GIFT_NUM_ERROR(920), //可领取宝箱数量不足
    NOT_OPEN_ALLIANCE_SEND_GIFT(921), //未开启联盟赠礼功能
    TARGET_MEMBER_UNABLE_RECEIVE_GIFT(922), //对方无法收取礼物
    JOIN_IN_ALLIANCE_TIME_ERROR(923), //加入联盟时间不足
    ALLIANCE_TREASURE_GO_ERROR(924), //当前已有队伍在挖掘
    ALLIANCE_TREASURE_VIP_HIGH_ERROR(925), //VIP等级可直接领取奖励无需挖掘
    GIFT_ONLY_SEND_LOWER_POWER_MEMBER(926), //联盟赠礼只能发送低实力盟友
    NO_JOIN_ALLIANCE_COMPETITION(927), //玩家没有参与本次联盟总动员
    HAVE_ALLIANCE_COMPETITION_QUEST(928), //玩家当前已领联盟总动员任务
    ALLIANCE_COMPETITION_ALLIANCE_ERROR(929), //玩家本日联盟总动员的效力帮派与当前帮派不符
    ALLIANCE_COMPETITION_QUEST_NO_NUM(930), //玩家联盟总动员领取任务次数不足
    ALLIANCE_COMPETITION_QUEST_CAN_NO_GET(931), //玩家联盟总动员任务当前已进入刷新倒计时
    NO_HAVE_ALLIANCE_COMPETITION_QUEST(932), //玩家当前没有联盟总动员任务可以放弃
    ALLIANCE_COMPETITION_QUEST_STATE_NOT_REMOVE(933), //当前无任务
    ALLIANCE_COMPETITION_QUEST_NUM_ENOUGH(934), //任务次数还未使用完无需购买
    ALLIANCE_COMPETITION_QUEST_NOT_FINISH(935), //联盟总动员任务还未完成
    BUY_ALLIANCE_COMPETITION_QUEST_MAX(936), //联盟总动员购买任务次数已达上限
    GET_ALLIANCE_COMPETITION_REWARD_ERROR(937), //联盟总动员领取奖励的客户端数据异常
    GET_ALLIANCE_COMPETITION_NO_FIND_REWARD_ERROR(938), //联盟总动员领取奖励失败,已经领过或者没有该阶段奖励
    GET_ALLIANCE_COMPETITION_REWARD_NO_SCORE_ERROR(939), //联盟总动员领取奖励失败,积分不足
    PUBLIC_ACTIVITY_NO_OPEN_ERROR(940), //活动当前状态不允许此操作
    ALLIANCE_COMPETITION_NO_REWARD_TIME_ERROR(941), //当前不是领奖时间
    WONDER_DISMISS_ALLIANCE_ERROR(942), //联盟当前占领大奇观，无法解散
    ALLIANCE_IMPEACH_TIME_ERROR(943), // 弹劾失败,弹劾者所在服比盟主服新
    ALLIANCE_GIVE_TIME_ERROR(944), // 转让失败,被转让者所在服比盟主服新
    PLAYER_JOIN_ALLIANCE_BUSY(945), // 玩家正在尝试加入联盟中
    APPLY_ALLIANCE_MAX_ERROR(946), // 当前申请联盟数已达上限

    // 卡牌养成
    HERO_GROW_LV_ERROR(1000), // 当前武将已升级
    HERO_GROW_ADV_LV_MAX(1001), // 卡牌进阶等级已达上限
    HERO_GROW_CNT_NOT_ENOUGH(1002), // 卡牌数量不足
    HERO_GROW_SKILL_MAX(1003), // 技能已达最大等级
    HERO_GROW_HERO_LV_NOT_ENOUGH(1004), // 武将等级不足
    HERO_LV_MAX(1005), // 武将等级已达上限
    HERO_CARD_NOT_ENOUGH(1006), // 道具数量不足
    HERO_EXP_CARD_NOT_ENOUGH(1007), // 经验卡不足
    HERO_LV_ALREADY(1008), // 武将已达到目标等级
    HERO_LV_CAN_NO_KING_LV_ERROR(1009), // 武将等级不得超过君主等级

    CHEST_NOT_EXIST(1100), // 宝箱不存在
    CHEST_GAIN_ALREADY(1101), // 宝箱已领取
    CHEST_KILL_NOT_ENOUGH(1102), // 击杀数量不足

    // 战斗与战报 1200-1299
    NO_SCOUT_TARGET(1200), //无侦查目标
    NO_FIGHT_BOSS(1201), //无战斗BOSS
    NO_FARM_RES(1202), //无采集资源
    HAVE_IN_FARM(1203), //正在采集中
    SAME_ALLIANCE_PLAYER_IN_FARM(1204), //同盟玩家正在采集中
    OTHER_ALLIANCE_PLAYER_IN_FARM(1205), //其他玩家正在采集中
    CELL_NOT_FREE(1206), //土地非空闲
    NOT_IN_SAME_ALLIANCE(1207), //不在同一联盟中
    IN_SAME_ALLIANCE(1208), //在同一联盟中
    NothingOnCell(1209), //地块上为空
    MassCanNotJoin(1210), //集结无法加入
    TransportResUpLimit(1211), //运输的资源超出上限
    ReinforceSoliderUpLimit(1212), //增援的士兵超出上限
    MassTargetError(1213), //集结目标错误
    MassRelicNeedLianjinsuo(1214), //集结遗迹需要炼金所
    MassSoliderUpLimit(1215), //集结的士兵超出上限
    MassNeedFightLobby(1216), //集结需要战争大厅
    MassNeedInAlliance(1217), //集结需要在联盟中
    HaveStartMass(1218), //已发起过集结
    WalkHaveFinished(1219), //行军已结束
    NotInSameMap(1220), //不在一张地图上
    HasInWalkHome(1221), //已在回城中
    ForceNotStatic(1222), //部队不是静止的
    UnableOperateGroup(1223), //无权限操作行军组
    MassForceUnableSelfHome(1224), //集结部队无法自行撤退
    MassMemberUnableSendHome(1225), //无权遣返集结成员
    MassTargetBuildTooLow(1226), //集结玩家城堡等级过低
    WalkQueueUpLimit(1227), //行军队列达到上限
    ReinforceRepeat(1228), //重复增援
    HAVE_FORCE_FIGHT_BOSS(1229), //已有部队攻打魔物
    HAVE_FANZHENCHA_BUFF(1230), //对方处于反侦察中
    NO_SCOUT_RESEARCH(1231), //无可侦查科技
    NONE_CAN_SCOUT(1232), //木有课侦查的东西
    HAVE_DEF_COVER_BUFF_WHEN_FIGHT(1233), //攻击的时候，对方有罩子
    NO_STRONGHOLD(1234), //无据点
    NO_ATK_STRONGHOLD_COUNT(1235), //无攻击据点次数
    WONDER_IN_PEACE(1236), //奇观在和平状态中
    ONLY_ALLIANCE_CAN_OCCUPY_WONDER(1237), //只有联盟能占领奇观
    HAVE_OCCUPY_WONDER(1238), //已占领奇观
    NO_COMMAND_IN_WONDER(1239), //奇观中无指挥官
    NOT_OCCUPY_WONDER(1240), //未占领奇观
    NOT_COMMAND(1241), //不是奇观战指挥官
    REINFORCE_NOT_IN_WONDER(1242), //增援部队不在奇观中
    HAVE_DEF_COVER_BUFF_WHEN_SCOUT(1243), //侦查的时候，对方有罩子
    NO_SET_ARMY_PLAN(1244), //找不到阵型预设
    ONLY_ONE_MASS_IN_ALLIANCE(1245), //同一个联盟只能对一个玩家发起一次集结
    OVER_LIMIT_MASS(1246), //一个玩家被集结超过上限
    OVER_LIMIT_STATION(1247),//驻扎部队数量超过上限
    HAVE_ATK_COVER_BUFF_WHEN_FIGHT(1248), //攻击的时候，自身有罩子
    WALK_DISTANCE_LIMIT(1249),//超出行军距离上限

    // 任务 1300 -  1350
    HAS_REWARD(1300), //该奖励已领取

    // 功能开启 1351 -  1399
    UI_CONDITION_ERROR(1351), //功能未开启
    UI_CONDITION_BUILD_LV_ERROR(1352), //建筑等级不足
    UI_CONDITION_VIPLV_ERROR(1358), //VIP等级不足
    UI_CONDITION_KINGLV_ERROR(1359), //君主等级不足
    UI_CONDITION_INSTANCE_ERROR(1360), //推图关卡不足
    UI_CONDITION_UNITTASK_ERROR(1361), //章节任务进度不足
    UI_CONDITION_MAINTASK_ERROR(1362), //主线任务进度不足

    //兵种转换功能 1451-1499
    WORLDTASK_ERROR(1452), //天下大势完成度不足
    UP_ERROR(1453), //该兵种已经升级到顶级
    SAME_QU_ERROR(1454), //必须放入同一品质的武将卡
    NO_NUM_ERROR(1455), //武将卡数量不足
    NO_SAME_ERROR(1456), //素材卡品质与要求品质不同
    NO_SAME_HERO_ERROR(1457), //素材卡武将与要求武将不同
    NUM_OVER_ERROR(1458), //客户端传来的素材卡数量超过实际值
    HERO_LV_ERROR(1459), //武将等级不足
    ARMY_GROUP_ERROR(1460), //相同分组的兵种才可以进行转换
    ARMY_OPEN_ERROR(1461), //你还未激活此兵种
    ARMY_MAX_ERROR(1462), //该兵种转换武将数已达上限
    SUCAI_ERROR(1463), //放入的素材卡有误
    NO_IN_CITY_ERROR(1464), //只有在本城内待命状态的武将才可以进行兵种转换

    //技能库功能  1500 - 1550
    SKILLUP_MAX_ERROR(1500), //该技能已升级到最高级
    NO_STUDY_SKILL_ERROR(1501), //你还没有掌握该技能
    STUDY_MAX_ERROR(1502), //学习该技能的武将数已达上限
    HAVE_SKILL_ERROR(1503), //该武将已经学习了本位置的技能
    NO_SKILLUP_ERROR(1504), //找不到技能模板
    STUDY_ER_ERROR(1505), //武将等级不足无法学习第二个技能
    STUDY_SAN_ERROR(1506), //武将等级不足无法学习第三个技能
    HAVE_NO_SKILL(1507), //本位置没有已学技能
    NO_UP_SKILL_ERROR(1508), //未满足升级此技能的要求
    ARMY_STUDY_ERROR(1509), //武将兵种不符合该技能学习
    HAVE_THIS_ERROR(1510), //你已学会该技能
    NO_IN_CITY_NOT_STUDY_ERROR(1511), //只有在本城内待命状态的武将才可以进行学习技能
    NO_IN_CITY_NOT_FORGET_ERROR(1512), //只有在本城内待命状态的武将才可以进行遗忘技能

    //装备养成功能  1551 - 1600
    WORLD_TASK_LV_ERROR(1551), // 天下大势等级不足
    EQUIP_SHOP_NIL(1552), // 玩家装备商城数据为空
    REFRESH_CD_FULL(1553), // 刷新冷却时间已满
    NO_EQUIP_SHOP_PROTO(1554), // 找不到亲密度升级模板
    NO_REFRESH_EQUIP_SHOP_CD_ERROR(1555), // 当前没有冷却时间无需清除
    NO_HAVE_FREE_XILIAN_NUM_ERROR(1556), // 当前无免费洗炼次数可用
    NO_BUY_SHOP_ERROR(1557), // 找不到要求购买的商品
    BUY_OVER_ERROR(1558), // 你已经购买过该物品了
    NO_BUY_PROTO(1559), // 找不到物品模板
    NO_EQUIP_ERROR(1560), // 物品不存在
    EQUIP_NO_IN_BAG_ERROR(1561), // 装备不在仓库内
    NO_CAN_HUIFU_ERROR(1562), // 没有可恢复的技能
    PROPS_NO_SELL(1563), // 该物品不可出售
    PUT_ON_NOT_ERROR(1564), // 该物品不可穿戴
    EQUIP_HAS_MASTER_ERROR(1565), // 装备已经被使用
    EQUIP_NO_PUT_ON_ERROR(1566), // 装备没有被穿戴
    NO_FIND_TAOZHUANG_FORCE_ERROR(1568), // 找不到装备所属的部队
    NO_FIND_PUT_ON_TARGET_ERROR(1569), // 找不到装备所要佩戴到的目标
    EQUIP_FORCE_STATE_ERROR(1570), // 部队状态不允许此操作
    COMMON_TAOZHUANG_PEIFANG_ERROR(1571), // 合成套装散件类型错误
    COMMON_TAOZHUANG_JUANZHOU_ERROR(1572), // 合成套装卷轴错误
    COMMON_TAOZHUANG_NUM_ERROR(1573), // 合成套装材料数量错误
    COMMON_TAOZHUANG_EQUIP_NO_YINCANG_ERROR(1574), // 合成普通套装的装备未激活隐藏属性
    COMMON_TAOZHUANG_SKILL_ERROR(1575), // 合成普通套装的装备技能不符合要求
    COMMON_TAOZHUANG_SANJIAN_ERROR(1576), // 合成的散件不匹配
    FENJIE_TAOZHUANG_TYPE_ERROR(1577), // 分解套装传来的物品类型不正确
    ITEM_HECHENG_NO_EN_ERROR(1578), // 合成道具不足
    ITEM_CAN_NO_SPLIT_ERROR(1579), // 该物品无法分解

    // 商城功能  1601-1650
    NO_FIND_SHOP_INFO(1601), // 找不到玩家商城信息
    REFRESH_ALLIANCE_SHOP(1602), // 马上请求联盟商城
    REFRESH_SHOP(1603), // 马上请求黑市商城
    LIMIT_SHOP(1604), // 限购上限
    OVER_TIME_SHOP(1605), // 限购商品不在时间范围内

    //使用道具 1651 - 1700
    NO_ENOUGH_PROP_NUM(1651), //没有足够的数量使用
    NO_USE_EFFECT(1652), //找不到该使用类型的适配器
    TransferToMapServer(1653), //转发至地图服处理
    NO_MORE_MARK(1654), // 收藏已达上限

    // 群组错误码1701-1750
    ROOM_MASTER_ERR(1701), // 群主不能被踢出群组
    NO_MORE_GROUP(1702), // 群组创建已达上限
    NO_MORE_ALLIANCE_GROUP(1703), // 团聊天组已存在
    NO_POWER(1704), // 群组权限不足
    NO_POWER_ALLIANCE(1705), // 联盟权限不足
    PERSON_CHAT_ALREADY_EXIST(1706), // 私聊聊天室已创建

    // 新手引导错误码 1751-1800
    NOW_NO_IN_GUIDE(1751), //玩家不在新手引导状态

    // 爬塔模块错误码 1801-1850
    TOWER_ERROR_NO_CONDITION(1801), //不满足挑战本层条件
    TOWER_ERROR_MAX(1802), //你已达到顶层

    // 外交留言板 1851-1870
    PLAYER_WAIJIAO_MAX_NUM(1851), // 本日个人外交次数已达上限
    ALLIANCE_WAIJIAO_MAX_NUM(1852), // 本日联盟外交次数已达上限
    WAIJIAO_INFO_ERROR(1853), // 外交内容不合法
    WAIJIAO_INFO_TOO_SHORT(1854), // 外交内容长度不足
    WAIJIAO_INFO_TOO_LANG(1855), // 外交内容超出上限
    WAIJIAO_REMOVE_ERROR(1856), // 未找到要删除的留言

    // 新武将养成 1871-1900
    NO_SUCAI_ERROR(1871), // 素材卡数量不符
    SUCAI_NO_SAME_ERROR(1872), // 满级后的素材卡不是同卡
    SUCAI_SAME_MAX_ERROR(1873), // 同名卡数量超出上限
    UP_STAR_NUM_ERROR(1876), // 升星材料不足
    UP_STAR_ERROR(1877), // 升星材料星数不符
    UP_STAR_MAX_ERROR(1878), // 武将已经达到最高星
    AWAKE_MAX_ERROR(1879), // 已达到觉醒顶级
    SUCAI_HERO_IN_FORCE(1880), // 素材武将正在上阵中
    SUCAI_HERO_IN_JJC_FORCE(1881), // 素材武将正在竞技场阵中
    SUCAI_HERO_IN_LOCK(1882), // 素材武将正在锁定中
    HERO_IN_LOCK_ERROR(1883), // 该武将已锁定
    HERO_NO_IN_LOCK_ERROR(1884), // 该武将不在锁定中
    HERO_CARD_BAG_TIME_OVER(1885), // 该卡包已下架
    HERO_NO_IN_HURT(1886), // 该武将不在重伤中
    UP_STAR_LV_ERROR(1887), // 武将等级不足,无法升星

    // 新装备养成 1901-1950
    EQUIP_MAIN_TYPE_ERROR(1901), // 该物品不是装备
    EQUIP_SUB_TYPE_ERROR(1902), // 装备位置错误
    EQUIP_NOW_ON_ERROR(1903), // 装备当前正在被使用
    EQUIP_NOW_NO_ON_ERROR(1904), // 装备当前无使用
    EQUIP_OFF_MASTER_ERROR(1905), // 装备主人与传来武将不符
    EQUIP_LV_UP_NO_SUCAI_ERROR(1906), // 装备强化材料不足
    EQUIP_LV_UP_MAX_ERROR(1907), // 装备已经强化到顶级

    // 推图错误码 1951-2000
    MISSION_ID_ERROR(1951), // 推图关卡ID错误
    MISSION_MAX_ERROR(1952), // 已经达到最后一关

    // 科技研发 + 快速使用物品错误码 2001-2050
    RESEARCH_CODITION_ERROR(2001), // 研发条件不足
    RESEARCH_DUILIE_ERROR(2002), // 研发队列不足
    CANCEL_RESEARCH_NO_IN_ERROR(2003), // 该科技不在研发中
    CANCEL_RESEARCH_IN_ERROR(2004), // 该科技正在研发中
    QUICK_PROPS_ERROR(2005), // 加速道具错误
    QUICK_PROPS_MAX_ERROR(2006), // 加速道具溢出错误
    NO_FIND_USE_PROPS_ACTION(2007), // 找不到物品使用策略
    SEND_ALLIANCE_HELP_ERROR(2008), // 登记信息不匹配
    SEND_ALLIANCE_HELP_TYPE_ERROR(2009), // 类型无效
    GO_ALLIANCE_HELP_NO_SAME_ERROR(2010), // 无法帮助非本帮成员
    GO_ALLIANCE_HELP_NO_IN_ERROR(2011), // 玩家不在研发中无法帮助其加速
    GO_ALLIANCE_HELP_HAVE_ERROR(2012), // 你已经帮此玩家加速过了
    GO_ALLIANCE_HELP_HAS_ERROR(2013), // 你已经申请过帮助了
    GO_ALLIANCE_HELP_MYSELF_ERROR(2014), // 无法帮助自己
    GO_ALLIANCE_HELP_MAX_NUM_ERROR(2015), // 帮助次数已达上限
    GO_ALLIANCE_HELP_NO_SAME_SERVER_ERROR(2016), // 无法帮助迁服的玩家

    // 兵营,配兵,伤兵营 2051-2100
    BARRACK_DUILIE_ERROR(2051), // 造兵队列不足
    BARRACK_IN_ERROR(2052), // 正在造兵中
    BARRACK_NO_IN_ERROR(2053), // 当前不在造兵中
    BARRACK_GO_ALL_RES_ERROR(2054), // 计算总消耗出错
    CONFIG_SOLIDER_UNITBASE_ERROR(2055), // 该武将无法配置该兵种
    CONFIG_SOLIDER_OUT_MAX_ERROR(2056), // 所配兵数超过了总数量
    CONFIG_SOLIDER_OUT_LV_MAX_ERROR(2057), // 所配兵数超过了兵力上限
    BARRACK_CURE_DUILIE_ERROR(2058), // 治疗兵队列不足
    BARRACK_IN_CURE_ERROR(2059), // 正在治疗中
    DISMISS_SOLIDER_OUT_MAX_ERROR(2060), // 遣散士兵超过了总数量
    UP_SOLIDER_ERROR_NUM(2061), // 晋升士兵数量超过了总数
    NO_FIND_SOLIDER_UP_TARGET(2062), // 找不到晋升目标兵种
    BARRACK_NO_IN_UP_ERROR(2063), // 当前不在晋升中
    BARRACK_LESS_EMPTY_POS(2064), //兵营空位不足
    NO_FIRE_ON(2065), // 城墙没有着火

    // 内政功能 2101-2120
    INT_HERO_NO_ERROR(2101), // 该武将不是内政武将
    INT_HERO_IN_FORCE_ERROR(2102), // 该武将正在部队中
    INT_HERO_IN_INT_ERROR(2103), // 该武将正在执政中
    INT_HERO_NO_IN_INT_ERROR(2104), // 该武将不在执政中
    INT_HERO_OUT_MAX_ERROR(2105), // 超过执政最大数量

    // 钻石商店 2121 - 2140
    DIAMONDSHOP_MAX_ERROR(2121), // 超出限购数量

    // 联盟科技建筑错误码  2141 - 2200
    ALLIANCE_RESEARCH_MAX_LV(2141), // 该科技已满级
    ALLIANCE_CASTLE_MAX_ERROR(2142), // 可造数量不足
    ALLIANCE_RESEARCH_NUM_ERROR(2143), // 研发次数不足
    ALLIANCE_BUILD_FLAG_MAX_ERROR(2144), // 建造战旗人数已满

    // 遗迹(新行军结构)错误码 2201-2250
    WALK_NUM_TOO_MORE(2201), // 行军部队数超过规定上限
    NO_FIND_RELIC_INFO(2202), // 找不到遗迹数据
    RELIC_CLOSE(2203), // 遗迹关闭中
    RELIC_CHOUBEI_TIME_ERROR(2204), // 筹备时间错误
    GOTO_NO_RELIC_CHOUBEI_TIME(2205), // 目标地不在筹备遗迹中
    RELIC_PLAYER_NUM_MAX_ERROR(2206), // 参加遗迹人数已经达到上限
    NO_RELIC_MEMBER(2207), // 没有该成员,无法遣返
    NO_FIND_RELIC_WALK(2208), // 找不到参加遗迹的行军
    NO_FIND_RELIC_FIGHT_INFO(2209), // 找不到遗迹战报
    NO_FIND_TIME_BOX_INDEX(2210), // 找不到时光之盒槽位
    NO_FIND_TIME_BOX(2211), // 槽位内找不到时光之盒
    TIME_BOX_IN_STUDY(2212), // 时光之盒正在研究中
    TIME_BOX_STUDY_OVER(2213), // 时光之盒已经研究结束
    TIME_BOX_GET_REWARD_ERROR(2214), // 时光之盒还未研究结束
    TIME_BOX_QUEUE_ERROR(2215), // 时光之盒研究队列不足
    FORCE_IN_RELIC(2216), // 已经有部队在参加遗迹中
    WALK_GROUP_NOT_EXIST(2217), //
    FIGHT_INFO_NOT_FOUND(2218),  // 找不到战报
    RELIC_LV_LIMIT_ERROR(2219), //可攻打遗迹等级不足

    // vip 2251-2270
    VIP_TIME_CARD_ERROR(2251), //vip时间卡错误
    VIP_EXP_CARD_ERROR(2252), //vip经验卡错误
    VIP_LEVEL_UP_LIMIT(2253), //vip等级已经达到上限

    // talent 2271-2290
    TALENT_PRECONDITION_DISSATIFY(2271), //天赋前置条件不满足
    LESS_TALENT_POINT(2272), //天赋点不足
    TALENT_RESET_ERROR(2273), //天赋重置道具错误

    // kingexp 2291-2300
    King_LEVEL_UP_LIMIT(2291), //君主等级已经达到上限
    King_EQUIP_BAG_ERROR(2292), //君主背包容量不足

    // 君主装备 2301-2350
    KING_EQUIP_CONDITION_ERROR(2301), // 锻造前置装备不满足条件
    KING_EQUIP_HEIYAOSHI_ERROR(2302), // 锻造征服装备的时候放入了黑曜石
    KING_EQUIP_DUILIE_ERROR(2303), // 无可用锻造队列
    KING_EQUIP_TIME_NO_OVER_ERROR(2304), // 还未锻造完成
    KING_EQUIP_NO_TOP_ERROR(2305), // 君主装备不是顶级的
    KING_EQUIP_HAVE_ZHENGFU_ERROR(2306), // 君主装备已经是征服装备了
    KING_EQUIP_SUIT_CONDITION_ERROR(2307), // 锻造装备前置条件不足
    KING_EQUIP_HEIYAOSHI_CD_ERROR(2308), // 黑曜石使用冷却中
    KING_EQUIP_QU_NO_ENERGH_ERROR(2309), // 装备品质不足无法使用黑曜石
    KING_EQUIP_ON_POS_ERROR(2310), // 该装备无法佩戴在君主的该槽位
    KING_EQUIP_PLAN_NUM_ERROR(2311), // 君主装备预设份数不足
    KING_EQUIP_PLAN_NAME_ERROR(2312), // 预设名称不合法
    KING_EQUIP_PLAN_NAME_NOT_ENOUGH(2313), // 预设名称长度不足
    KING_EQUIP_PLAN_NAME_EXCEED(2314), // 预设名称长度超过规定字数限制
    KING_EQUIP_PLAN_MORE_PORT_ERROR(2315), // 预设时发来了一个位置多个装备
    KING_EQUIP_PLAN_DEL_ERROR(2316), // 找不到要删除的预设
    KING_EQUIP_NO_IN_MAKE_ERROR(2317), // 没有君主装备正在锻造或者已结束
    KING_EQUIP_NO_OPEN_LOCK(2318), // 该槽位还未解锁

    // 小镇功能 2351-2400
    BUILDING_CAN_NO_ON_LAND(2351), // 地块类型不在建筑白名单内
    TOWN_EXPAND_ERROR(2352), // 未扩建区域
    NO_FIND_EXPEND_ERROR(2353), // 未找到扩建坐标
    EXPEND_OVER_ERROR(2354), // 坐标已经扩建过
    EXPEND_IN_ERROR(2355), // 正在扩建中
    BUILDING_BIG_TYPE_ERROR(2356), // 建筑功能分类的大类型不符
    SEED_TYPE_ERROR(2357), // 种子类型错误
    CELL_HAVE_SEED_ERROR(2358), // 该地块已有植物
    EXPEND_NO_IN_ERROR(2359), // 不在扩建中
    EXPEND_NO_FINISH_ERROR(2360), // 扩建还未结束
    BUILD_CAN_NO_DIS_ERROR(2361), // 建筑不可拆
    GET_BUILD_NO_ENOUGH_ERROR(2362), // 建筑未满足收获条件
    NO_FIND_ACCEPT_BUILD(2363), // 没有对应的建筑收纳信息
    UPDATE_TOWN_EROR(2364), // 编辑模式失败
    EXPEND_CONDITION_EROR(2365), // 扩建无效

    // 迁服2401-2450
    MOVE_CITY_POS_ERROR(2401), // 坐标不可迁移
    NO_CAN_MOVE_POS(2402), // 无可迁城的位置
    FORCE_RUNNING_OUTSIDE(2403), // 有部队在外行军
    MOVE_NEW_AREA_ERROR(2404), // 无法迁入未出保的新服

    // 商船错误码  2451 - 2470
    MER_IS_EXCHANGE_ERROR(2451), // 该物品已兑换过
    MER_INFO_IS_LOCK(2452), // 该物品已锁定
    MER_INFO_IS_NO_LOCK(2453), // 该物品未锁定
    MER_INFO_IS_NO_PAY(2454), // 惊喜物品无法购买,还未接入充值接口

    // 银行错误码  2471 - 2500
    BANK_MONEY_ERROR(2471), // 投资金额不符合要求
    BANK_HAVE_ERROR(2472), // 当前已有投资项
    BANK_NO_HAVE_ERROR(2473), // 当前没有投资项
    BANK_NO_TIME_OVER_ERROR(2474), // 还未到领取时间
    BANK_TIME_OVER_ERROR(2475), // 已经到达领取时间
    BANK_ACCELERATE_ERROR(2476), // 投资的时间不足
    BANK_BUILD_LV_ERROR(2477), // 建筑最高等级不足

    // 藏兵错误码 2501-2520
    MAIN_HERO_NOT_IN_CITY(2501), //领主不在城内
    NO_SUCH_SOLIDER_IN_CITY(2502), //城内木有如此数量士兵
    HAVE_CAVE_FORCE_GROUP(2503), //已有部队在藏兵中
    NO_CAVE_FORCE_GROUP(2504), //木有部队在藏兵中

    // BOSS错误码 2521 - 2530
    SUMMON_ALLIANCE_BOSS_XY_ERROR(2521), // 该坐标无法放置BOSS
    BOSS_AREA_NOT_EXIST(2522),  //BOSS区域不存在
    BOSS_LOCK(2523),  //BOSS处于锁定状态，尚不能攻击
    NOT_ON_BOSS_AREA(2524), // 不在BOSS所在区域

    //运输请求错误码 2531-2540
    HAVE_PUBLISH_TRANSPORT_REQUEST(2531), //已发布运输请求

    // buff 2541 - 2550
    NO_DEF_BECAUSE_WALK_EFFECT(2541), // 无法使用保护罩因为有战争狂热
    NO_DEF_BECAUSE_SNOW_COVER(2542), // 在雪地上无法使用保护罩
    NO_DEF_BECAUSE_WONDER_COVER(2543), // 在黑土地上无法使用保护罩

    // 监狱错误码  2551 - 2560
    NO_FIND_PRISON_PLAYER(2551), // 监狱中找不到该玩家
    RANSOM_ERROR(2552), // 赎金不符合要求
    RANK_ERROR(2553), // 排名获取异常
    EAR_POISON_TIME_ERROR(2554), // 超出使用毒蘑菇时间
    NO_SET_RANSOM(2555), // 对方未开出赎金
    NO_MORE_PRISON_ADD(2556), // 不能再添加犯人
    FAIL_TO_SET_FAKE_HERO(2557), // 设置buff领主失败
    DEF_COVER_EFFECT_ON(2558), // 防护罩开启，不能开监禁buff

    // 城堡皮肤错误码 2580 - 2590
    SKIN_NO_HAVE_ERROR(2580), // 找不到皮肤
    SKIN_MAX_STAR_ERROR(2581), // 满星
    SKIN_USE_NUM_ERROR(2582), // 数量不在范围内
    SKIN_HAVE_ERROR(2583), // 已有皮肤
    SKIN_IN_USE_ERROR(2584),//皮肤在使用中

    // 在线礼包错误码 2591 - 2600
    ONLINE_GIFT_TIME_ERROR(2591), // 还未到领奖时间
    ONLINE_GIFT_OVER_ERROR(2592), // 本日任务已经全部领取结束
    ONLINE_GIFT_RESVO_ERROR(2593), // 掉落出来的奖励错误

    // 内政任务错误码 2610 - 2620
    INTERIOR_TASK_EXECUTING_TASK(2610),
    INTERIOR_TASK_NO_POS(2611),
    INTERIOR_TASK_NO_BASE_REWARD(2612),

    // 红包功能 2621 - 2630
    GET_RED_BAG_REDIS_ERROR(2621), // 找不到红包信息
    GET_RED_BAG_JSON_ERROR(2622), // 红包解析出错
    GET_RED_BAG_TIME_OVER_ERROR(2623), // 红包已过期
    GET_RED_BAG_NUM_OVER_ERROR(2624), // 红包已被领取完
    GET_RED_BAG_HAVE_ERROR(2625), // 你已经领取过这个红包了
    GET_RED_BAG_CAN_NOT_HAVE_ERROR(2626), // 你没有领取这个红包的资格
    GET_RED_BAG_MONEY_ERROR(2627), // 红包金额异常
    GET_RED_BAG_REDIS_SAVE_ERROR(2628), // 领取红包之后的存储异常

    // 成就功能 2631 - 2635
    ACHIEVEMENT_NOT_FINISH(2631), //成就未完成或奖励已领取
    ACHIEVEMENT_SHARE_REWARD_HAVE_RECEIVE(2632), //成就分享奖励已领取

    //官职功能 2636-2655
    UNABLE_SET_SUCH_OFFICE(2636), //无法设置该类官职
    LIMIT_TO_SET_OFFICE(2637), //没有权限设置国家官职
    NO_OFFICE_TO_CANCEL(2638), //木有官职可取消
    LIMIT_TO_AWARD_ALLIANCE(2639), //木有权限大赏三军
    AWARD_NUM_LIMIT(2640), //奖励人数限制
    HAVE_AWARDED(2641), //已给奖励
    WONDER_NOT_PEACE(2642), //奇观不在和平状态
    LIMIT_TO_OPEN_COUNTRY_BUFF(2643), //木有权限开启全国buff
    WHOLE_COUNTRY_BUFF_IN_ACTIVE(2644), //全国Buff生效中
    WHOLE_COUNTRY_BUFF_IN_COOL(2645), //全国Buff冷却中
    LIMIT_TO_AMNESTY_WHOLE_COUNTRY(2646), //木有权限大赦天下
    NO_AMNESTY_COUNT(2647), //无赦免次数
    LIMIT_TO_EDITOR_NOTICE(2648), //木有权限编辑公告
    COUNTRY_NOTICE_NOT_ENOUGH(2649), // 公告长度不足
    COUNTRY_NOTICE_EXCEED(2650), // 公告长度超过规定字数限制
    NO_COUNTRY_NOTICE(2651), //无公告信息
    WONDER_IN_WAR(2652), //奇观争夺战未结束
    COUNTRY_NOTICE_IN_COOL(2653), //王国公告冷却中
    WONDER_NOT_EXIST(2654),

    // 内城功能 2670-2700
    INNER_CITY_LOCK(2670), // 前置建筑未达到要求，不可建造
    INNER_CITY_NOT_FIND_BUILDING(2671), // 内城建筑未找到
    INNER_CITY_STATE_ERROR(2672), // 内城建筑状态错误
    INNER_CITY_QUEUE_ERROR(2673), // 内城建筑队列已满
    INNER_CITY_EXIST_BUILDING(2674), // 坑位上有内城建筑了
    INNER_CITY_NOT_FIND_POSITION_INDEX(2675), // 没找到建筑坑位
    INNER_CITY_CAN_NOT_BUILD(2676), // 坑位上不能造该建筑
    INNER_CITY_CAN_NOT_DESTROY(2677), // 坑位上不能拆除
    INNER_CITY_CAN_NOT_MOVE_TO_SELF(2678), // 不能移动到自身坑位上
    INNER_CITY_AREA_LOCK(2679), // 区域未解锁
    INNER_CITY_AREA_UnlOCK(2680), // 区域已解锁

    // 推图功能 2701 - 2720
    INSTANCE_STAR_BOX_HAVE_ERROR(2701), // 该宝箱已领取
    INSTANCE_STAR_BOX_NO_ENOUGH_ERROR(2702), // 还未获得目标星数
    INSTANCE_STENGTH_BUY_NUM_ERROR(2703), // 购买体力次数达到上限

    //魔物功能 2721 - 2750
    MONSTER_NOT_EXIST(2721),    //魔物不存在
    CALL_BOSS_CELL_NOT_EMPTY(2722), //召唤魔物的地块不是空地

    // 挑战功能 2751 - 2760
    ACTIVITY_RANK_TIME_OVER(2751), // 挑战排行已过期

    // 后宅 2761 - 2780
    NO_SESSION_GUILD_HOUSE(2761),       // 找不到后宅缓存
    NO_SESSION_HOME_AREA(2762),         // 占地缓存未初始化
    NO_SESSION_THUMB_INFO(2763),        // 找不到点赞信息缓存
    CANT_FIND_FURNITURE(2764),          // 找不到家具
    NO_FURNITURE_PROTO(2765),           // 找不到家具协议
    NO_HOME_AREA_PROTO(2766),           // 找不到后宅面积协议
    LIMIT_FURNITURE_NUM(2767),          // 家具数量超过购买上限
    LIMIT_HERO_NUM(2768),               // 英雄数量超过放置上限
    LIMIT_HOUSE_THEME_NUM(2769),        //  主题数量超过上限
    HOUSE_THEME_ERROR(2770),            // 玩家主题配置出错
    FURNITURE_INDEX_ERROR(2771),        // 家具摆放位置错误
    FURNITURE_NOT_FUNCTIONAL(2772),     // 不是功能家具
    REPEAT_THUMB(2773),                 // 今天已经点过赞了
    PRODUCE_CAL_ERROR(2774),            // 计算产出出错
    SINGLE_TYPE_FUR(2775),              // 已拥有相同类型的家具
    NO_SUBJECT_PROTO(2776),             // 找不到商店主题协议

    // 抽卡 (2781-2790)
    LOTTERY_OUT_OF_DATE(2781),          // 抽卡活动过期

    // 图书馆 (2791 - 2800)
    NO_SESSION_LIBRARY(2791),           // 找不到图书馆缓存
    NUM_ERROR(2792),                    // 数值错误

    // 聊天 (2801-2810)
    CHAT_MSG_ERR(2801),             // 内容含有屏蔽内容
    CHAT_MSG_LENGTH_OVER(2802),     // 内容长度不符合范围

    // 黑名单 (2811-2820)
    BLACK_IS_EXIST(2811),     // 存在名单中
    BLACK_IS_NOT_EXIST(2812),  // 不存在黑名单中

    //迷雾 (2821-2830)
    KING_LV_LIMIT(2821),                //君主等级不够
    FOG_CONDITION_NOT_UNLOCK(2822),             //迷雾条件未解锁

    // 赌场 (2831-2840)
    NO_PLAYERS(2831),                //无中奖人数
    CASINO_ERROR(2832),               //赌场配置出错
    CASINO_TYPE_ERROR(2833),               // 没有该赌场

    // 迁城 (2841-2850)
    MOVE_CITY_LV_LIMIT(2841),         //未达到迁城到该土地的等级

    // 玩家设置(2851-2860)
    CANT_FIND_PLAYER_SETTING(2851),

    // 迁服 (3000 - 3020)
    MOVE_SERVER_PLAYER_EXIST_ERROR(3000), // 玩家迁服数据已存在
    MOVE_SERVER_XY_EXIST_ERROR(3001), // 目标点已经被占去
    MOVE_SERVER_ENTITY_ERROR(3002), // 必要的entity获取不到

    // 礼包(3021-3030)
    GIFTBAG_LAST_GIFTBAG_BUY_COUNT_NOENOUGH(3021), // 上一档位礼包购买次数不足
    GIFTBAG_BUY_COUNT_MAX(3022), // 礼包购买次数最大
    GIFTBAG_NOT_OPEN(3023), // 礼包未开放
    GIFTBAG_MONTH_CARD_ACTIVE(3024), // 月卡有效，暂不能购买
    MONTH_CARD_INVALID(3025), // 月卡失效
    MONTH_CARD_IS_RECVED(3026), // 今日月卡已经领取过
    TRIGGER_GIFTBAG_IS_OVERDUE(3027), // 触发礼包已过期
    TRIGGER_GIFTBAG_NOT_EXIST(3028), // 触发礼包不存在

    // 模板
    NO_PROTO(1451), //找不到模板

    // 日志查询错误码
    QUERY_LOG_SERVER_ERR(9000), //查询过程中出现错误
    QUERY_LOG_AT_START_TIME_ERR(9001), //日志开始时间格式错误(YYYY-MM-DD HH:MM:SS)
    QUERY_LOG_AT_END_TIME_ERR(9002), //日志结束时间格式错误(YYYY-MM-DD HH:MM:SS)
    QUERY_LOG_START_END_TIME_ERR(9003), //日志开始时间不能大于结束时间
    QUERY_LOG_ARGS_NOT_INTEGER(9004), //参数不能转换成整数类型
    QUERY_LOG_ARGS_PAGE_ERR(9005), //查询参数【当前页】错误
    QUERY_LOG_ARGS_COUNT_ERR(9006), //查询参数【每页显示记录数】错误

    SERVER_INTERNAL_ERR(9999), //服务端错误（多数可能是因为panic产生的不好发现的错误）

    GM_MSG_AT_ERROR_STATE(10001), // GM消息发送在错误的状态

    PROCESS_ERROR_PROTO_INIT_FAILED(10010), // 模板初始化失败
    PROCESS_ERROR_DB_INIT_FAILED(10011), // 数据库初始化失败
    PROCESS_ERROR_LOG_CONNECT_INIT_FAILED(10012), // 日志服连接初始化失败
    PROCESS_ERROR_DC_INIT_FAILED(10013), // 数据中心连接失败
    PROCESS_ERROR_EXIST_AREAS_START_FAILED(10014), // 初始游戏区启动失败
    PROCESS_ERROR_GENERATE_TABLE_FINISHED(10015), // 数据库表重建结束（这个不是错误，但生产环境绝对不能出现这个！）
    PROCESS_ERROR_RPC_PORT_CANT_BIND(10016), // RPC端口无法绑定
    PROCESS_ERROR_ETCD_CANT_ACCESS(10017), // etcd无法访问
    PROCESS_ERROR_NO_VALID_RPC_SERVICE(10018), // 无法找到可用的rpc服务
    PROCESS_ERROR_CANT_ACCESS_RPC_SERVICE(10019), // 无法访问目标rpc服务
    PROCESS_ERROR_RPC_RT_FAILED(10020), // rpc返回失败
    PROCESS_ERROR_RPC_REG_ERROR(10021), // rpc服务注册失败
    PROCESS_ERROR_RPC_KEEP_ALIVE_ERROR(10021), // rpc服务续约失败
    PROCESS_ERROR_MSG_CONSUME_INIT_FAILED(10022), // 消息消费初始化失败
    PROCESS_ERROR_MSG_PRODUCE_INIT_FAILED(10023), // 消息生产初始化失败
    PROCESS_ERROR_RPC_EXCEPTION(10024), // 服务不可用
    PROCESS_ERROR_BATTLE_SERVICE_ERROR(10025), //战斗服务不可用

    ADD_NEW_AREA_PARAM_ERROR(11000), // 加区参数错误
    ADD_NEW_AREA_DB_ERROR(11001), // 加区无法连接数据库
    ADD_NEW_AREA_CODE_VERSION_ERROR(11002), // 加区代码版本错误
    ADD_NEW_AREA_CREATE_TABLE_ERROR(11003), // 加区创建表错误
    ADD_NEW_AREA_DB_EXIST(11004), // 加区，目标数据库已经存在
    FETCH_AREA_BASE_ERROR(11005), // 不存在的AreaId

    SWITCH_M_AREA_NOT_RUNNING(11100), // 切换维护状态时，发现游戏区尚未运行

    STOP_AREA_NOT_RUNNING(11200), // 关停游戏区时，发现游戏区尚未运行
    STOP_AREA_CLOSE_TIME_OUT(11201), // 关服超时

    HEART_INVALID(11300), // 心跳无效

    REDIS_INIT_FAILED(13000), // Redis初始化失败

    ASK_ERROR1(14000), // 目标服务器异常--ERR报错
    ASK_ERROR2(14001), // 目标服务器异常--RESP空报错
    ASK_ERROR3(14002), // 目标服务器异常--捕获到异常

    // 登录服错误码
    REALM_QUERY_PLAYER_FROM_DB_FAILED(20001), // 登录服从数据库查询玩家信息失败
    REALM_INSERT_PLAYER_TO_DB_FAILED(20002), // 插入玩家信息到数据库失败
    REALM_UPDATE_PLAYER_TO_DB_FAILED(20003), // 更新玩家信息到数据库失败
    REALM_UPDATE_PLAYER_HAD_EXCEPTION(20004), // 更新玩家发生异常
    REALM_QUERY_PLAYER_HAD_EXCEPTION(20005) // 登录服从数据库查询玩家信息发生异常
    ;

    // 聊天服错误码

    companion object : EnumConverter<Int, ResultCode>(buildValueMap(ResultCode::code))
}
