package com.point18.slg2d.home.dc

import com.point18.slg2d.common.commonfunc.getNowTime
import com.point18.slg2d.common.homeentities.*
import com.point18.slg2d.home.AbstractDataContainer
import xyz.ariane.util.*
import xyz.ariane.util.memodb.EntityWrapper
import com.point18.slg2d.common.constg.PlayerId
import com.point18.slg2d.common.pc.pcs
import java.io.Serializable
import java.time.Duration

class VipDC : AbstractDataContainer<VipEntity>() {
    lateinit var vipInfo: Vip

    override fun initImpl(data: VipEntity?, depDCRepo: DataContainerRepo) {
        if (data != null) {
            val vip = wdb.recover(data) { Vip() }
            this.vipInfo = vip
        }else{
            val vip = Vip(
                1,
                0,
                getNowTime(),
                0,
                getNowTime(),
                playerId
            )

            wdb.save(vip)

            vipInfo = vip
        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): VipEntity? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(VIP_NAMED_QUERY)
                .setLong("playerId", playerId)
                .listNoDup<VipEntity>()
            if (list.isEmpty()) {
                null
            } else {
                list[0]
            }
        }


        return data
    }

    fun findVipByPlayerId(): Vip {
        return this.vipInfo
    }

    fun getVipEffectInfoMapByPlayerId(): Map<Int, Int> {
        val vipProto = pcs.vipSetCache.vipSetMap[vipInfo.vipLv] ?: return hashMapOf()
        return vipProto.ability
    }

    fun getVipLv(): Int {
        return vipInfo.vipLv
    }
}

class Vip(
    var vipLv: Int,                      // VIP等级
    var vipExp: Int,                    // vip经验
    var lastGetVipRewardTime: Long,     // 上次领取vip奖励时间
    var continueOnlineDay: Int,         // 连续上线天数)
    var lastRefreshEnergyTime: Long,    //上次刷新行动力时间
    var playerId: Long                  // 玩家Id
) : EntityWrapper<VipEntity> {

    constructor() : this(0, 0, 0, 0, 0, 0)

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = playerId

    override fun toEntity(): VipEntity {
        return VipEntity(
            playerId,
            vipLv,
            vipExp,
            lastGetVipRewardTime,
            continueOnlineDay,
            lastRefreshEnergyTime
        )
    }

    override fun wrap(entity: VipEntity) {
        playerId = entity.playerId
        vipLv = entity.vipLv
        vipExp = entity.vipExp
        lastGetVipRewardTime = entity.lastGetVipRewardTime
        continueOnlineDay = entity.continueOnlineDay
        lastRefreshEnergyTime = entity.lastRefreshEnergyTime
    }
}
