package com.point18.slg2d.common.constg

/** public shard最大分片数 */
const val MAX_NUMBER_OF_PUBLIC_SHARD = 1000

// SS：邮箱名称
const val AKKA_MAILBOX_SMALL = "akka.actor.mailbox.small-bounded"
const val AKKA_MAILBOX_LARGE = "akka.actor.mailbox.large-bounded"

/** 用于和停服脚本交互的标记文件目录 */
const val GRACEFUL_SHUTDOWN_FLAG_DIR = "/dev/shm/graceful_shutdown_flags/"

/** 下线原因 */
enum class LogoutReason(val code: Int) {
    /** 正常下线 */
    NORMAL(1),
    /** GM踢下线 */
    GM_KICK(2),
    /** 自己把自己挤下线 */
    SELF_KICK(3),
    /** 其他 */
    OTHER(4)
}

const val JDBC_BATCH_SIZE = 30

/** zk根节点 */
val ZK_ROOT: String by lazy {
    val p = System.getProperty("zkroot")
    if (p.isNullOrBlank()) "topsango" else p
}

// 公会管理器
const val ALLIANCE_MANAGER = "allianceManager"
const val ALLIANCE_MANAGER_PATH = "/user/$ALLIANCE_MANAGER"