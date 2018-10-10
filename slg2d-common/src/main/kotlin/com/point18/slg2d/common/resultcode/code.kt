package com.point18.slg2d.common.resultcode

enum class Code(val code: Int, val errMsg: String) {
    FAL_CODE(0, "错误"),
    SUC_CODE(1, "成功"),
    ZK_DATA_DONT_EXIST(2, "ZK数据不存在"),

    NOT_LOGIN(100004, "尚未登录"),
    PARAM_JSON_CANT_PARSE(100005, "请求json无法解析"),
    PARAM_ERROR(100006, "请求参数错误"),
    DBDATA_JSON_CANT_PARSE(100007, "数据库数据反序列化错误"),
    PARAM_EMPTY(100008, "请求参数为空"),

    MGR_ACCOUNT_NOT_FOUND(100010, "找不到帐号信息"),
    MGR_ACCOUNT_NULL(100011, "帐号输入不能为空"),
    MGR_ACCOUNT_PWD_INVALID(100012, "帐号密码不正确"),
    MGR_ACCOUNT_NOT_ACTIVE(100013, "帐号未激活"),
    MGR_PWD_FORMAT_ERR(100014, "密码格式不正确"),
    MGR_ACCOUNT_NO_PER(100015, "权限不足"),
    MGR_PWD_INVALID(100016, "原始密码不正确"),
    MGR_ACC_DROPPED(100017, "用户掉线"),
    GROUP_NAME_NULL(100020, "群组名称不能为空"),
    GROUP_NAME_EXIST(100021, "群组名称已存在"),
    GROUP_STATE_ERR(100022, "群组状态不对"),
    GROUP_COPY_NOT_EXIST(100023, "拷贝群组不存在"),
    GROUP_NOT_EXIST(100024, "群组不存在"),
    GROUP_PLATFORM_NOT_EXIST(100025, "平台不存在"),
    GROUP_RIGHTS_NOT_EXIST(100026, "权限不存在"),
    GROUP_NOT_DELETE(100027, "群组不能删除，有数据存在"),
    PLATFORM_NOT_FOUND(100030, "平台不存在"),
    PLATFORM_EXIST_SERVER(100031, "平台存在物理机"),
    PLATFORM_OPID_EXIST(100032, "平台OPId已存在"),
    PROCESS_NOT_FOUND(100040, "进程配置不存在"),
    SAME_PROCESSIP_AND_PROCESSTYPE_EXIST(100041, "该类型的进程IP已存在"),
    GAMESERVER_EXIST_AREA(100042, "进程上有游戏区存在"),
    GAMESERVER_NOT_AREA(100043, "进程没有游戏区"),
    GAMESERVER_NOT_START(100044, "进程不能被拉配置"),
    GAMESERVER_HAS_STARTED(100045, "进程有运行状态"),
    GAMESERVER_HAS_NOT_LOGSERVER(100046, "进程没有日志服"),
    PROCESS_TYPE_ERROR(100047, "进程类型错误"),
    PROCESSNO_EXIST(100048, "processNo存在"),
    INVALID_PROCESS_NO(100049, "无效的进程编号"),
    GAMEAREA_NOT_FOUND(100050, "游戏区不存在"),
    GAMEAREA_HAS_EXIST(100051, "游戏区已存在"),
    PROCESSLOG_EXIST_LOG(100052, "日志服已被使用"),
    GAMEAREA_DBNAME_EXIST(100053, "数据库命名重复"),
    GAMEAREA_NOT_DELETE(100054, "游戏区不能删除"),
    SHIELDING_TEXT_STRNULL(100055, "屏蔽字为空"),
    SHIELDING_TEXT_EXIST(100056, "屏蔽字存在"),
    SHIELDING_TEXT_NOTEXIST(100057, "屏蔽字不存在"),
    GM_ACCOUNT_FORMAT_ERR(100060, "GM账号格式不对(6-20)"),
    GM_PWD_FORMAT_ERR(100061, "GM密码格式不对(6-20)"),
    GM_ACCOUNT_EXIST(100062, "GM用户名已存在"),
    APPLY_USER_FORMAT_ERR(100063, "使用人长度不对(<20)"),
    ID_GEN_ERR(100064, "Id生成失败"),

    ROSTERIP_TEXT_EXIST(100066, "名单已存在"),
    ROSTERIP_TEXT_NOTEXIST(100067, "名单不存在"),
    ROSTERIP_QUERY_TYPE_ERR(100068, "查询类型不存在"),

    CHATAREA_NOT_FOUND(100070, "聊天服不存在"),
    CHATAREA_HAS_EXIST(100071, "聊天服已存在"),

    FIGHTING_DEMO_NOT_FOUND(100080, "战斗模板不存在"),

    HOME_SERVER_HAS_NOT_PROCESS(100090, "登录服未先配置进程"),
    PROCESS_TYPE_NOT_MATCH(100091, "该进程ID类型不是登录服"),
    HOME_SERVER_NOT_FOUND(100092, "没找到该登录服"),

    ERR_PARAM_ERR(100100, "参数错误"),
    GM_ACCOUNT_NOT_EXIST(100101, "GM账号不存在"),
    ERR_ACCOUNT_NO_RIGHT(100102, "此账号无此操作的权限"),
    ERR_ACCOUNT_NO_PLATFORM(100103, "此账号无平台的权限"),
    ERR_ACCOUNT_NOT_ACTIVE(100104, "GM账号未激活"),
    ERR_ACCOUNT_RE_LOGIN(100105, "长时间离开，请重新登陆"),
    GAMESERVER_NOT_EXIST(100106, "游戏服务器不存在"),
    GAMESERVER_CANT_CONNECT(100107, "无法连接指定的游戏服务器"),
    ACCOUNT_PWD_INVALID(100108, "密码错误"),
    ACCOUNT_PWD_NOT_NULL(100109, "账号或密码不能为空"),
    ONLY_SHOW_SQL(100110, "sql语句参数错误或该sql只能查询"),
    GAMESERVER_MODEL_NOT_EXIST(100111, "游戏平台模板不存在"),
    MUST_REMOTE_SERVER(100112, "当前服务器不是跨服服务器"),
    UNABLE_CONNECT_SERVER(100113, "无法连接当前服务器"),
    GAMEAREA_NOT_EXIST(100114, "游戏区不存在"),
    SERVER_NOT_EXIST(100115, "服务器不存在"),
    SERVER_SYNCHRO_FAIL(100116, "服务器同步失败"),
    LOGSERVER_NOT_EXIST(100117, "日志服务器不存在"),

    PROTONAMEEXIST(100118, "模板已存在"),
    DEPLOYMENT_FAILED(100119, "步署失败"),
    LOGCONFIGSERVER_NOT_EXIST(100120, "日志服不存在"),
    LOGCONFIGSERVER_NOT_FOUND(100120, "没找到该日志服"),
    STARTTIMEBIGGERTHANENDTIME(100121, "开始时间大于结束时间"),
    INPUTTIMEBIGGERTHANNOW(100122, "查询时间不能大于当前日期"),
    IPCONFIGDONTMATCH(100123, "IP格式不符"),
    PORTDONTMATCH(100124, "PORT格式不符"),
    IPANDPORTDONTMATCH(100125, "配置格式不符ip:port"),
    KAFKAPID_NOT_MATCH(100126, "进程所属分区ID必须是10的整数倍"),
    PROCESSNUM_NOT_MATCH(100127, "进程号要介于1-10"),
    PROCESS_HAS_EXIST(100128, "进程号已存在"),

    LOGINSERVERIP_NOT_EXIST(100130, "该ip查询不到登录服"),
    PLATFORMID_GAMEPROCESS_NOT_EXIST(100131, "该平台ID查询不到游戏服进程"),
    HOME_SERVERIP_HAS_EXIST(100132, "登陆服已存在"),

    HEROPROTO_NOT_MATCH_SOLIDER(100140, "武将模板不为0时携带士兵ID不能为0"),

    YOUZUAPI_REQUEST_FAIL(100150, "游族API接口请求失败"),
    YOUZUAPI_RESPONSE_FAIL(100151, "游族API接口返回失败"),
    YOUZUAPI_RESPONSEDATA_CANT_PARSE(100152, "游族API接口返回数据解析失败"),

    COMM_CFG_NOT_FOUND(100160, "找不到目标集群配置"),
    COMM_CFG_NO_ERROR(100161, "集群配置的序号错误"),
    COMM_CFG_NAME_ERROR(100162, "集群配置的名字错误"),
    COMM_CFG_OTHER_PARAM_ERROR(100163, "集群配置的其他参数错误"),
    COMM_CFG_SAME_EXIST(100164, "已经存在相同etcd和groupNo的集群配置"),

    KAFKA_CANT_ACCESS(100180, "目标kafka集群无法访问"),
    KAFKA_TOPICS_FETCH_ERROR(100180, "获取目标kafka的Topic列表失败"),

    OTHER_CONN_OWNED_STATUS(100300, "进程状态被其他访问连接占用了"),
    PROCESS_TOKEN_ERROR(100301, "令牌错误，没有权限访问"),

    RPC_SERVICE_CANT_CONNECT(100400, "远程rpc服务器无法连接"),
    RPC_REQ_ERROR(100401, "rpc服务请求失败"),
    RPC_RT_ERROR(100402, "rpc服务返回失败"),

    PUBLICAREA_NOT_FOUND(100501, "PUBLIC服不存在"),
    PUBLICAREA_HAS_EXIST(100502, "PUBLIC服已存在"),
    PUBLICSERVER_NOT_FOUND(100503, "没找到该public服"),
    PUBLICAREA_DB_HAS_EXIST(100504, "该数据库的公共服已存在"),
    PUBLICAREA_DB_IS_NULL(100505, "公共服数据库配置不能为空"),

    // 网关服
    GATEAREA_DB_IS_NULL(100605, "网关服数据库配置不能为空"),
    GATEAREA_HAS_EXIST(100606, "网关服名字重复，不要重复添加"),
    GATEAREA_NOT_FOUND(100608, "网关服不存在"),

    // process
    ZK_PROCESS_DATA_ERR(100700, "一个area存在多个process"),

    // database
    DATABASE_EXIST(100800, "这个database已经存在,不要重复添加"),
    DATABASE_DONT_EXIST(100801, "这个database不存在")
}
