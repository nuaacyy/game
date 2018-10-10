package com.point18.slg2d.common.persistence

import com.google.common.collect.Lists
import com.google.common.hash.HashFunction
import com.google.common.hash.Hashing
import com.google.common.math.LongMath
import com.point18.slg2d.common.configuration.ZkDatasourceConfig
import com.point18.slg2d.common.homeentities.HomePlayerEntity
import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration
import org.hibernate.shards.ShardId
import org.hibernate.shards.strategy.access.LoadBalancedSequentialShardAccessStrategy
import org.hibernate.shards.strategy.resolution.BaseShardResolutionStrategy
import org.hibernate.shards.strategy.selection.ShardResolutionStrategyData
import org.hibernate.shards.strategy.selection.ShardSelectionStrategy
import xyz.ariane.util.*
import xyz.ariane.util.io.ClassResolver
import java.io.Serializable

/** 属于玩家财产的数据 */
interface PlayerAsset : IEntity {
    var playerId: Long
}

/** 与玩家关系为一对一的财产数据 */
interface OneToOnePlayerAsset : PlayerAsset {
    override fun primaryKey(): Serializable {
        return playerId
    }
}

/** 属于游戏世界的财产 */
interface WorldAsset : IEntity {
    val worldId: Long
}

/** 属于军团 */
interface LegionAsset : IEntity {
    val legionId: Long
}

/** 属于国家 */
interface CountryAsset : IEntity {
    val countryId: Long
}

/** 独立的全局数据表 */
interface GlobalEntity : IEntity {
    val id: Long
}

/** 独立的全局数据表，自定义数据表 */
interface GlobalEntityCustomShard : IEntity {
    fun valueToHash(): String
}

/** 公共数据表 */
interface PublicAsset : IEntity {
    val publicId: Long
}

/** 公会表 **/
interface AllianceAsset : IEntity {
    val allianceId: Long
}

/**
 * 虚拟分片数，即最大物理分片数，默认1000个
 */
const val VIRTUAL_SHARD_NUM = 1000

/**
 * 当前物理分片数，默认10个，也就是10个MySQL实例
 *
 * 如果是调试环境，那么物理分片数默认是1个。
 *
 * 到底使用多少物理分片数，完全根据传入的分片配置数决定，也就是说要么1个，要么10个。
 */
// TODO 出于调试目的，生产环境的物理分片数暂时设置为4。
const val PHYSICAL_SHARD_NUM = 4

/**
 * 用于选择worldId对应shardId时散列
 */
val MURMUR3_HASH_FUNCTION: HashFunction = Hashing.murmur3_32()

fun hashWorldId(worldId: Long): Long {
    return Math.abs(MURMUR3_HASH_FUNCTION.hashLong(worldId).asInt().toLong())
}

fun commonHash(key: String): Long {
    return Math.abs(MURMUR3_HASH_FUNCTION.hashUnencodedChars(key).asInt().toLong())
}

private val oneToOnePlayerAssetClasses: Set<String> by lazy(LazyThreadSafetyMode.PUBLICATION) {
    ClassResolver<OneToOnePlayerAsset>()
        .findImplementations(OneToOnePlayerAsset::class.java, HomePlayerEntity::class.java.`package`.name)
        .classes.map {
        it.name
    }.toSet()
}

fun buildGameShardedSessionFactory(
    physicalShardConfigs: List<ZkDatasourceConfig>,
    processPrototypeConfig: (Configuration) -> Unit = {}
): SessionFactory {

    var physicalShardNum = physicalShardConfigs.size
    if (physicalShardNum != 1) {
        // SS：非调试环境，那么数据库配置必须等于默认的。
        // SS：验证得到的配置是否满足默认的。
        require(physicalShardNum == PHYSICAL_SHARD_NUM) {
            "Unexpected physical shard number, expected:$PHYSICAL_SHARD_NUM, but:${physicalShardNum}"
        }

    } else {
        physicalShardNum = 1
    }

    // SS：下面的翻译过程有点绕。
    // SS：从zk的对象配置翻译成xml文本
    // SS：再从xml文本解析为xml节点对象文档
    // SS：
    val physicalShardConfigDocuments = physicalShardConfigs.map { zkCfg ->
        renderShardHibernateCfgXml(
            shardId = zkCfg.shardId,
            socket = zkCfg.socket,
            database = zkCfg.database,
            user = zkCfg.user,
            password = zkCfg.password
        ).let(::buildXmlDocumentFromText)
    }

    val virtualShardNum = VIRTUAL_SHARD_NUM
    val virtualShardIdList = Lists.newArrayListWithCapacity<ShardId>(virtualShardNum) // SS：空间已经提前准备好了
    (0 until virtualShardNum).mapTo(virtualShardIdList, ::ShardId) // 生成ShardId对象，并填入列表中

    // SS：根据组件的ID算出shardId
    // SS：ShardId内部只有一个整数
    fun shardIdOf(obj: Any): ShardId? = when (obj) {
        is PlayerAsset -> ShardId(LongMath.mod(obj.playerId, virtualShardNum))
        is WorldAsset -> ShardId(LongMath.mod(hashWorldId(obj.worldId), virtualShardNum))
        is AllianceAsset -> ShardId(LongMath.mod(hashWorldId(obj.allianceId), virtualShardNum))
        is PublicAsset -> ShardId(LongMath.mod(hashWorldId(obj.publicId), virtualShardNum))
        is LegionAsset -> ShardId(LongMath.mod(obj.legionId, virtualShardNum))
        is CountryAsset -> ShardId(LongMath.mod(obj.countryId, virtualShardNum))
        is GlobalEntity -> ShardId(LongMath.mod(obj.id, virtualShardNum))
        is GlobalEntityCustomShard -> ShardId(LongMath.mod(commonHash(obj.valueToHash()), virtualShardNum))
        else -> null
    }

    // SS：准备3个策略
    val selectionStrategy = ShardSelectionStrategy { entity ->
        shardIdOf(entity) ?: throw IllegalArgumentException("Unknown type ${entity.javaClass}")
    }

    val resolutionStrategy = object : BaseShardResolutionStrategy(virtualShardIdList) {

        override fun selectShardIdsFromShardResolutionStrategyData(data: ShardResolutionStrategyData): List<ShardId> {
            // SS：根据数据主键算出shardId，并生成一个shardId列表。
            val shardId = shardIdOf(data.id)
            return shardId?.let {
                listOf(it)
            } ?: if (oneToOnePlayerAssetClasses.contains(data.entityName)) {
                // SS：目测感觉这里的if else根本跑不到啊！
                listOf(ShardId(LongMath.mod(data.id as Long, virtualShardNum)))
            } else {
                error("Cannot locate shard, entity:${data.entityName}, id:${data.id} shardId:$shardId")
            }
        }
    }

    val accessStrategy = LoadBalancedSequentialShardAccessStrategy()

    val staticVirtualShardMap = calShardMap(VIRTUAL_SHARD_NUM, physicalShardNum)

    // SS：建立用于配置SessionFactory的配置
    val config = ShardedSessionFactoryConfig(
        physicalShardConfigDocuments = physicalShardConfigDocuments,
        selectionStrategy = selectionStrategy,
        resolutionStrategy = resolutionStrategy,
        accessStrategy = accessStrategy,
        virtualShardMap = staticVirtualShardMap,
        processPrototypeConfig = processPrototypeConfig
    )

    return buildShardedSessionFactory(config)
}

/**
 * 计算Shard映射
 */
private fun calShardMap(virtual: Int, physical: Int): Map<Int, Int> {
    if (virtual <= 0 || physical <= 0) {
        throw RuntimeException("错误的虚拟（物理）分片数，$virtual，$physical")
    }

    val map = mutableMapOf<Int, Int>()
    val divide = virtual / physical
    for (eachVirtual in 0 until virtual) {
        map[eachVirtual] = eachVirtual / divide
    }
    return map
}

//private val staticVirtualShardMap: Map<Int, Int> = mapOf(
//    0 to 0,
//    1 to 0,
//    2 to 0,
//    3 to 0,
//    4 to 0
//)

//
//private val staticVirtualShardMap: Map<Int, Int> = mapOf(
//    0 to 0,
//    1 to 0,
//    2 to 0,
//    3 to 0,
//    4 to 0,
//    5 to 0,
//    6 to 0,
//    7 to 0,
//    8 to 0,
//    9 to 0,
//    10 to 0,
//    11 to 0,
//    12 to 0,
//    13 to 0,
//    14 to 0,
//    15 to 0,
//    16 to 0,
//    17 to 0,
//    18 to 0,
//    19 to 0,
//    20 to 0,
//    21 to 0,
//    22 to 0,
//    23 to 0,
//    24 to 0,
//    25 to 0,
//    26 to 0,
//    27 to 0,
//    28 to 0,
//    29 to 0,
//    30 to 0,
//    31 to 0,
//    32 to 0,
//    33 to 0,
//    34 to 0,
//    35 to 0,
//    36 to 0,
//    37 to 0,
//    38 to 0,
//    39 to 0,
//    40 to 0,
//    41 to 0,
//    42 to 0,
//    43 to 0,
//    44 to 0,
//    45 to 0,
//    46 to 0,
//    47 to 0,
//    48 to 0,
//    49 to 0,
//    50 to 0,
//    51 to 0,
//    52 to 0,
//    53 to 0,
//    54 to 0,
//    55 to 0,
//    56 to 0,
//    57 to 0,
//    58 to 0,
//    59 to 0,
//    60 to 0,
//    61 to 0,
//    62 to 0,
//    63 to 0,
//    64 to 0,
//    65 to 0,
//    66 to 0,
//    67 to 0,
//    68 to 0,
//    69 to 0,
//    70 to 0,
//    71 to 0,
//    72 to 0,
//    73 to 0,
//    74 to 0,
//    75 to 0,
//    76 to 0,
//    77 to 0,
//    78 to 0,
//    79 to 0,
//    80 to 0,
//    81 to 0,
//    82 to 0,
//    83 to 0,
//    84 to 0,
//    85 to 0,
//    86 to 0,
//    87 to 0,
//    88 to 0,
//    89 to 0,
//    90 to 0,
//    91 to 0,
//    92 to 0,
//    93 to 0,
//    94 to 0,
//    95 to 0,
//    96 to 0,
//    97 to 0,
//    98 to 0,
//    99 to 0,
//    100 to 1,
//    101 to 1,
//    102 to 1,
//    103 to 1,
//    104 to 1,
//    105 to 1,
//    106 to 1,
//    107 to 1,
//    108 to 1,
//    109 to 1,
//    110 to 1,
//    111 to 1,
//    112 to 1,
//    113 to 1,
//    114 to 1,
//    115 to 1,
//    116 to 1,
//    117 to 1,
//    118 to 1,
//    119 to 1,
//    120 to 1,
//    121 to 1,
//    122 to 1,
//    123 to 1,
//    124 to 1,
//    125 to 1,
//    126 to 1,
//    127 to 1,
//    128 to 1,
//    129 to 1,
//    130 to 1,
//    131 to 1,
//    132 to 1,
//    133 to 1,
//    134 to 1,
//    135 to 1,
//    136 to 1,
//    137 to 1,
//    138 to 1,
//    139 to 1,
//    140 to 1,
//    141 to 1,
//    142 to 1,
//    143 to 1,
//    144 to 1,
//    145 to 1,
//    146 to 1,
//    147 to 1,
//    148 to 1,
//    149 to 1,
//    150 to 1,
//    151 to 1,
//    152 to 1,
//    153 to 1,
//    154 to 1,
//    155 to 1,
//    156 to 1,
//    157 to 1,
//    158 to 1,
//    159 to 1,
//    160 to 1,
//    161 to 1,
//    162 to 1,
//    163 to 1,
//    164 to 1,
//    165 to 1,
//    166 to 1,
//    167 to 1,
//    168 to 1,
//    169 to 1,
//    170 to 1,
//    171 to 1,
//    172 to 1,
//    173 to 1,
//    174 to 1,
//    175 to 1,
//    176 to 1,
//    177 to 1,
//    178 to 1,
//    179 to 1,
//    180 to 1,
//    181 to 1,
//    182 to 1,
//    183 to 1,
//    184 to 1,
//    185 to 1,
//    186 to 1,
//    187 to 1,
//    188 to 1,
//    189 to 1,
//    190 to 1,
//    191 to 1,
//    192 to 1,
//    193 to 1,
//    194 to 1,
//    195 to 1,
//    196 to 1,
//    197 to 1,
//    198 to 1,
//    199 to 1,
//    200 to 2,
//    201 to 2,
//    202 to 2,
//    203 to 2,
//    204 to 2,
//    205 to 2,
//    206 to 2,
//    207 to 2,
//    208 to 2,
//    209 to 2,
//    210 to 2,
//    211 to 2,
//    212 to 2,
//    213 to 2,
//    214 to 2,
//    215 to 2,
//    216 to 2,
//    217 to 2,
//    218 to 2,
//    219 to 2,
//    220 to 2,
//    221 to 2,
//    222 to 2,
//    223 to 2,
//    224 to 2,
//    225 to 2,
//    226 to 2,
//    227 to 2,
//    228 to 2,
//    229 to 2,
//    230 to 2,
//    231 to 2,
//    232 to 2,
//    233 to 2,
//    234 to 2,
//    235 to 2,
//    236 to 2,
//    237 to 2,
//    238 to 2,
//    239 to 2,
//    240 to 2,
//    241 to 2,
//    242 to 2,
//    243 to 2,
//    244 to 2,
//    245 to 2,
//    246 to 2,
//    247 to 2,
//    248 to 2,
//    249 to 2,
//    250 to 2,
//    251 to 2,
//    252 to 2,
//    253 to 2,
//    254 to 2,
//    255 to 2,
//    256 to 2,
//    257 to 2,
//    258 to 2,
//    259 to 2,
//    260 to 2,
//    261 to 2,
//    262 to 2,
//    263 to 2,
//    264 to 2,
//    265 to 2,
//    266 to 2,
//    267 to 2,
//    268 to 2,
//    269 to 2,
//    270 to 2,
//    271 to 2,
//    272 to 2,
//    273 to 2,
//    274 to 2,
//    275 to 2,
//    276 to 2,
//    277 to 2,
//    278 to 2,
//    279 to 2,
//    280 to 2,
//    281 to 2,
//    282 to 2,
//    283 to 2,
//    284 to 2,
//    285 to 2,
//    286 to 2,
//    287 to 2,
//    288 to 2,
//    289 to 2,
//    290 to 2,
//    291 to 2,
//    292 to 2,
//    293 to 2,
//    294 to 2,
//    295 to 2,
//    296 to 2,
//    297 to 2,
//    298 to 2,
//    299 to 2,
//    300 to 3,
//    301 to 3,
//    302 to 3,
//    303 to 3,
//    304 to 3,
//    305 to 3,
//    306 to 3,
//    307 to 3,
//    308 to 3,
//    309 to 3,
//    310 to 3,
//    311 to 3,
//    312 to 3,
//    313 to 3,
//    314 to 3,
//    315 to 3,
//    316 to 3,
//    317 to 3,
//    318 to 3,
//    319 to 3,
//    320 to 3,
//    321 to 3,
//    322 to 3,
//    323 to 3,
//    324 to 3,
//    325 to 3,
//    326 to 3,
//    327 to 3,
//    328 to 3,
//    329 to 3,
//    330 to 3,
//    331 to 3,
//    332 to 3,
//    333 to 3,
//    334 to 3,
//    335 to 3,
//    336 to 3,
//    337 to 3,
//    338 to 3,
//    339 to 3,
//    340 to 3,
//    341 to 3,
//    342 to 3,
//    343 to 3,
//    344 to 3,
//    345 to 3,
//    346 to 3,
//    347 to 3,
//    348 to 3,
//    349 to 3,
//    350 to 3,
//    351 to 3,
//    352 to 3,
//    353 to 3,
//    354 to 3,
//    355 to 3,
//    356 to 3,
//    357 to 3,
//    358 to 3,
//    359 to 3,
//    360 to 3,
//    361 to 3,
//    362 to 3,
//    363 to 3,
//    364 to 3,
//    365 to 3,
//    366 to 3,
//    367 to 3,
//    368 to 3,
//    369 to 3,
//    370 to 3,
//    371 to 3,
//    372 to 3,
//    373 to 3,
//    374 to 3,
//    375 to 3,
//    376 to 3,
//    377 to 3,
//    378 to 3,
//    379 to 3,
//    380 to 3,
//    381 to 3,
//    382 to 3,
//    383 to 3,
//    384 to 3,
//    385 to 3,
//    386 to 3,
//    387 to 3,
//    388 to 3,
//    389 to 3,
//    390 to 3,
//    391 to 3,
//    392 to 3,
//    393 to 3,
//    394 to 3,
//    395 to 3,
//    396 to 3,
//    397 to 3,
//    398 to 3,
//    399 to 3,
//    400 to 4,
//    401 to 4,
//    402 to 4,
//    403 to 4,
//    404 to 4,
//    405 to 4,
//    406 to 4,
//    407 to 4,
//    408 to 4,
//    409 to 4,
//    410 to 4,
//    411 to 4,
//    412 to 4,
//    413 to 4,
//    414 to 4,
//    415 to 4,
//    416 to 4,
//    417 to 4,
//    418 to 4,
//    419 to 4,
//    420 to 4,
//    421 to 4,
//    422 to 4,
//    423 to 4,
//    424 to 4,
//    425 to 4,
//    426 to 4,
//    427 to 4,
//    428 to 4,
//    429 to 4,
//    430 to 4,
//    431 to 4,
//    432 to 4,
//    433 to 4,
//    434 to 4,
//    435 to 4,
//    436 to 4,
//    437 to 4,
//    438 to 4,
//    439 to 4,
//    440 to 4,
//    441 to 4,
//    442 to 4,
//    443 to 4,
//    444 to 4,
//    445 to 4,
//    446 to 4,
//    447 to 4,
//    448 to 4,
//    449 to 4,
//    450 to 4,
//    451 to 4,
//    452 to 4,
//    453 to 4,
//    454 to 4,
//    455 to 4,
//    456 to 4,
//    457 to 4,
//    458 to 4,
//    459 to 4,
//    460 to 4,
//    461 to 4,
//    462 to 4,
//    463 to 4,
//    464 to 4,
//    465 to 4,
//    466 to 4,
//    467 to 4,
//    468 to 4,
//    469 to 4,
//    470 to 4,
//    471 to 4,
//    472 to 4,
//    473 to 4,
//    474 to 4,
//    475 to 4,
//    476 to 4,
//    477 to 4,
//    478 to 4,
//    479 to 4,
//    480 to 4,
//    481 to 4,
//    482 to 4,
//    483 to 4,
//    484 to 4,
//    485 to 4,
//    486 to 4,
//    487 to 4,
//    488 to 4,
//    489 to 4,
//    490 to 4,
//    491 to 4,
//    492 to 4,
//    493 to 4,
//    494 to 4,
//    495 to 4,
//    496 to 4,
//    497 to 4,
//    498 to 4,
//    499 to 4,
//    500 to 5,
//    501 to 5,
//    502 to 5,
//    503 to 5,
//    504 to 5,
//    505 to 5,
//    506 to 5,
//    507 to 5,
//    508 to 5,
//    509 to 5,
//    510 to 5,
//    511 to 5,
//    512 to 5,
//    513 to 5,
//    514 to 5,
//    515 to 5,
//    516 to 5,
//    517 to 5,
//    518 to 5,
//    519 to 5,
//    520 to 5,
//    521 to 5,
//    522 to 5,
//    523 to 5,
//    524 to 5,
//    525 to 5,
//    526 to 5,
//    527 to 5,
//    528 to 5,
//    529 to 5,
//    530 to 5,
//    531 to 5,
//    532 to 5,
//    533 to 5,
//    534 to 5,
//    535 to 5,
//    536 to 5,
//    537 to 5,
//    538 to 5,
//    539 to 5,
//    540 to 5,
//    541 to 5,
//    542 to 5,
//    543 to 5,
//    544 to 5,
//    545 to 5,
//    546 to 5,
//    547 to 5,
//    548 to 5,
//    549 to 5,
//    550 to 5,
//    551 to 5,
//    552 to 5,
//    553 to 5,
//    554 to 5,
//    555 to 5,
//    556 to 5,
//    557 to 5,
//    558 to 5,
//    559 to 5,
//    560 to 5,
//    561 to 5,
//    562 to 5,
//    563 to 5,
//    564 to 5,
//    565 to 5,
//    566 to 5,
//    567 to 5,
//    568 to 5,
//    569 to 5,
//    570 to 5,
//    571 to 5,
//    572 to 5,
//    573 to 5,
//    574 to 5,
//    575 to 5,
//    576 to 5,
//    577 to 5,
//    578 to 5,
//    579 to 5,
//    580 to 5,
//    581 to 5,
//    582 to 5,
//    583 to 5,
//    584 to 5,
//    585 to 5,
//    586 to 5,
//    587 to 5,
//    588 to 5,
//    589 to 5,
//    590 to 5,
//    591 to 5,
//    592 to 5,
//    593 to 5,
//    594 to 5,
//    595 to 5,
//    596 to 5,
//    597 to 5,
//    598 to 5,
//    599 to 5,
//    600 to 6,
//    601 to 6,
//    602 to 6,
//    603 to 6,
//    604 to 6,
//    605 to 6,
//    606 to 6,
//    607 to 6,
//    608 to 6,
//    609 to 6,
//    610 to 6,
//    611 to 6,
//    612 to 6,
//    613 to 6,
//    614 to 6,
//    615 to 6,
//    616 to 6,
//    617 to 6,
//    618 to 6,
//    619 to 6,
//    620 to 6,
//    621 to 6,
//    622 to 6,
//    623 to 6,
//    624 to 6,
//    625 to 6,
//    626 to 6,
//    627 to 6,
//    628 to 6,
//    629 to 6,
//    630 to 6,
//    631 to 6,
//    632 to 6,
//    633 to 6,
//    634 to 6,
//    635 to 6,
//    636 to 6,
//    637 to 6,
//    638 to 6,
//    639 to 6,
//    640 to 6,
//    641 to 6,
//    642 to 6,
//    643 to 6,
//    644 to 6,
//    645 to 6,
//    646 to 6,
//    647 to 6,
//    648 to 6,
//    649 to 6,
//    650 to 6,
//    651 to 6,
//    652 to 6,
//    653 to 6,
//    654 to 6,
//    655 to 6,
//    656 to 6,
//    657 to 6,
//    658 to 6,
//    659 to 6,
//    660 to 6,
//    661 to 6,
//    662 to 6,
//    663 to 6,
//    664 to 6,
//    665 to 6,
//    666 to 6,
//    667 to 6,
//    668 to 6,
//    669 to 6,
//    670 to 6,
//    671 to 6,
//    672 to 6,
//    673 to 6,
//    674 to 6,
//    675 to 6,
//    676 to 6,
//    677 to 6,
//    678 to 6,
//    679 to 6,
//    680 to 6,
//    681 to 6,
//    682 to 6,
//    683 to 6,
//    684 to 6,
//    685 to 6,
//    686 to 6,
//    687 to 6,
//    688 to 6,
//    689 to 6,
//    690 to 6,
//    691 to 6,
//    692 to 6,
//    693 to 6,
//    694 to 6,
//    695 to 6,
//    696 to 6,
//    697 to 6,
//    698 to 6,
//    699 to 6,
//    700 to 7,
//    701 to 7,
//    702 to 7,
//    703 to 7,
//    704 to 7,
//    705 to 7,
//    706 to 7,
//    707 to 7,
//    708 to 7,
//    709 to 7,
//    710 to 7,
//    711 to 7,
//    712 to 7,
//    713 to 7,
//    714 to 7,
//    715 to 7,
//    716 to 7,
//    717 to 7,
//    718 to 7,
//    719 to 7,
//    720 to 7,
//    721 to 7,
//    722 to 7,
//    723 to 7,
//    724 to 7,
//    725 to 7,
//    726 to 7,
//    727 to 7,
//    728 to 7,
//    729 to 7,
//    730 to 7,
//    731 to 7,
//    732 to 7,
//    733 to 7,
//    734 to 7,
//    735 to 7,
//    736 to 7,
//    737 to 7,
//    738 to 7,
//    739 to 7,
//    740 to 7,
//    741 to 7,
//    742 to 7,
//    743 to 7,
//    744 to 7,
//    745 to 7,
//    746 to 7,
//    747 to 7,
//    748 to 7,
//    749 to 7,
//    750 to 7,
//    751 to 7,
//    752 to 7,
//    753 to 7,
//    754 to 7,
//    755 to 7,
//    756 to 7,
//    757 to 7,
//    758 to 7,
//    759 to 7,
//    760 to 7,
//    761 to 7,
//    762 to 7,
//    763 to 7,
//    764 to 7,
//    765 to 7,
//    766 to 7,
//    767 to 7,
//    768 to 7,
//    769 to 7,
//    770 to 7,
//    771 to 7,
//    772 to 7,
//    773 to 7,
//    774 to 7,
//    775 to 7,
//    776 to 7,
//    777 to 7,
//    778 to 7,
//    779 to 7,
//    780 to 7,
//    781 to 7,
//    782 to 7,
//    783 to 7,
//    784 to 7,
//    785 to 7,
//    786 to 7,
//    787 to 7,
//    788 to 7,
//    789 to 7,
//    790 to 7,
//    791 to 7,
//    792 to 7,
//    793 to 7,
//    794 to 7,
//    795 to 7,
//    796 to 7,
//    797 to 7,
//    798 to 7,
//    799 to 7,
//    800 to 8,
//    801 to 8,
//    802 to 8,
//    803 to 8,
//    804 to 8,
//    805 to 8,
//    806 to 8,
//    807 to 8,
//    808 to 8,
//    809 to 8,
//    810 to 8,
//    811 to 8,
//    812 to 8,
//    813 to 8,
//    814 to 8,
//    815 to 8,
//    816 to 8,
//    817 to 8,
//    818 to 8,
//    819 to 8,
//    820 to 8,
//    821 to 8,
//    822 to 8,
//    823 to 8,
//    824 to 8,
//    825 to 8,
//    826 to 8,
//    827 to 8,
//    828 to 8,
//    829 to 8,
//    830 to 8,
//    831 to 8,
//    832 to 8,
//    833 to 8,
//    834 to 8,
//    835 to 8,
//    836 to 8,
//    837 to 8,
//    838 to 8,
//    839 to 8,
//    840 to 8,
//    841 to 8,
//    842 to 8,
//    843 to 8,
//    844 to 8,
//    845 to 8,
//    846 to 8,
//    847 to 8,
//    848 to 8,
//    849 to 8,
//    850 to 8,
//    851 to 8,
//    852 to 8,
//    853 to 8,
//    854 to 8,
//    855 to 8,
//    856 to 8,
//    857 to 8,
//    858 to 8,
//    859 to 8,
//    860 to 8,
//    861 to 8,
//    862 to 8,
//    863 to 8,
//    864 to 8,
//    865 to 8,
//    866 to 8,
//    867 to 8,
//    868 to 8,
//    869 to 8,
//    870 to 8,
//    871 to 8,
//    872 to 8,
//    873 to 8,
//    874 to 8,
//    875 to 8,
//    876 to 8,
//    877 to 8,
//    878 to 8,
//    879 to 8,
//    880 to 8,
//    881 to 8,
//    882 to 8,
//    883 to 8,
//    884 to 8,
//    885 to 8,
//    886 to 8,
//    887 to 8,
//    888 to 8,
//    889 to 8,
//    890 to 8,
//    891 to 8,
//    892 to 8,
//    893 to 8,
//    894 to 8,
//    895 to 8,
//    896 to 8,
//    897 to 8,
//    898 to 8,
//    899 to 8,
//    900 to 9,
//    901 to 9,
//    902 to 9,
//    903 to 9,
//    904 to 9,
//    905 to 9,
//    906 to 9,
//    907 to 9,
//    908 to 9,
//    909 to 9,
//    910 to 9,
//    911 to 9,
//    912 to 9,
//    913 to 9,
//    914 to 9,
//    915 to 9,
//    916 to 9,
//    917 to 9,
//    918 to 9,
//    919 to 9,
//    920 to 9,
//    921 to 9,
//    922 to 9,
//    923 to 9,
//    924 to 9,
//    925 to 9,
//    926 to 9,
//    927 to 9,
//    928 to 9,
//    929 to 9,
//    930 to 9,
//    931 to 9,
//    932 to 9,
//    933 to 9,
//    934 to 9,
//    935 to 9,
//    936 to 9,
//    937 to 9,
//    938 to 9,
//    939 to 9,
//    940 to 9,
//    941 to 9,
//    942 to 9,
//    943 to 9,
//    944 to 9,
//    945 to 9,
//    946 to 9,
//    947 to 9,
//    948 to 9,
//    949 to 9,
//    950 to 9,
//    951 to 9,
//    952 to 9,
//    953 to 9,
//    954 to 9,
//    955 to 9,
//    956 to 9,
//    957 to 9,
//    958 to 9,
//    959 to 9,
//    960 to 9,
//    961 to 9,
//    962 to 9,
//    963 to 9,
//    964 to 9,
//    965 to 9,
//    966 to 9,
//    967 to 9,
//    968 to 9,
//    969 to 9,
//    970 to 9,
//    971 to 9,
//    972 to 9,
//    973 to 9,
//    974 to 9,
//    975 to 9,
//    976 to 9,
//    977 to 9,
//    978 to 9,
//    979 to 9,
//    980 to 9,
//    981 to 9,
//    982 to 9,
//    983 to 9,
//    984 to 9,
//    985 to 9,
//    986 to 9,
//    987 to 9,
//    988 to 9,
//    989 to 9,
//    990 to 9,
//    991 to 9,
//    992 to 9,
//    993 to 9,
//    994 to 9,
//    995 to 9,
//    996 to 9,
//    997 to 9,
//    998 to 9,
//    999 to 9
//)