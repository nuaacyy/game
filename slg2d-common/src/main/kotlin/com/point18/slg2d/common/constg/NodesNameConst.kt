package com.point18.slg2d.common.constg

const val MGR_PARENT_NODE = "/Mgr"
const val MGR_INFO_NODE_NAME = "$MGR_PARENT_NODE/Info" // GM配置
const val CLUSTER_NODE_NAME = "$MGR_PARENT_NODE/CommCfg" // 集群配置
const val PROCESS_NODE_NAME = "$MGR_PARENT_NODE/Process" // 进程配置
const val PLATFORM_NODE_NAME = "$MGR_PARENT_NODE/Platform" // 平台配置
const val DATASOURCE_NODE_NAME = "$MGR_PARENT_NODE/datasource"  // 数据库配置
const val WORLD_AREA_NODE_NAME = "$MGR_PARENT_NODE/GameArea" // 世界区配置
const val GRPC_SERVICE_NODE_NAME = "$MGR_PARENT_NODE/Grpc" //grpc服务