package com.point18.slg2d.home.dc

import com.point18.slg2d.common.commonfunc.toJson
import com.point18.slg2d.common.commonfunc.toObj
import com.point18.slg2d.common.constg.*
import com.point18.slg2d.common.homeentities.NEW_EQUIP_NAMED_QUERY
import com.point18.slg2d.common.homeentities.NewEquipEntity
import com.point18.slg2d.common.homeentities.NewEquipPK
import com.point18.slg2d.common.mcache.TwoKeyIndexSlice
import com.point18.slg2d.common.pc.pcs
import com.point18.slg2d.home.AbstractDataContainer
import com.point18.slg2d.home.hpm
import xyz.ariane.util.CommonDao
import xyz.ariane.util.DataContainerRepo
import xyz.ariane.util.listNoDup
import xyz.ariane.util.memodb.EntityWrapper
import java.io.Serializable
import java.time.Duration
import java.util.*

class NewEquipDC : AbstractDataContainer<List<NewEquipEntity>>() {
    val equipOnHeroMap =
        TwoKeyIndexSlice<Long, Long, NewEquip>({ it.bagId }, { it.foreignId }, { eA, eB -> eA.id == eB.id })
    val myEquips = LinkedList<NewEquip>()

    override fun initImpl(data: List<NewEquipEntity>?, depDCRepo: DataContainerRepo) {
        data?.map {
            val equip = wdb.recover(it) { NewEquip() }

            equipOnHeroMap.insertByKey(equip)
            myEquips += equip
        }
    }

    override fun loadAllFromDB(playerId: PlayerId, dao: CommonDao): List<NewEquipEntity>? {
        val data = dao.findWithTransaction { session ->
            val list = session.getNamedQuery(NEW_EQUIP_NAMED_QUERY)
                .setLong("playerId", playerId)
                .listNoDup<NewEquipEntity>()
            list
        }
        return data
    }

    fun createNewEquip(
        bagId: Long,
        equipProtoId: Int,
        num: Int,
        equipProps: HashMap<Int, HashMap<Int, Int>>
    ): NewEquip {
        val id = hpm.generateObjIdNew()
        val newEquip = NewEquip(
            id,
            equipProtoId,
            0,
            num,
            0,
            0,
            bagId,
            playerId,
            playerId
        )
        newEquip.propertyMap = equipProps
        newEquip.cardInfoMap = hashMapOf()

        wdb.save(newEquip)
        equipOnHeroMap.insertByKey(newEquip)
        myEquips += newEquip

        return newEquip
    }

    fun deleteNewEquip(newEquip: NewEquip) {
        wdb.delete(newEquip)
        this.equipOnHeroMap.deleteByKey(newEquip)
        this.myEquips.remove(newEquip)
    }

    // 找到一个玩家的所有道具
    fun findPropsByPlayerId(): LinkedList<NewEquip> {
        return myEquips
    }

    // 找到一个玩家的所有君主装备(不管穿没穿)
    fun findAllKingEquipsByPlayerId(): Int {
        // 尝试从缓存中获取
        var num = 0
        for (equip in myEquips) {
            val propProto = pcs.equipCache.equipProtoMap[equip.equipProtoId] ?: continue
            if (propProto.mainType == PROP_KING_EQUIP) {
                num++
            }
        }
        return num
    }

    // 找到一个玩家普通背包里的所有道具,一般用法是要消耗物品的时候.在其余背包中的东西是无法消耗的
    fun findPropsByPlayerIdAndBagId(bagDC: BagDC, bagType: Int, propId: Long): NewEquip? {
        // 尝试从缓存中获取
        var rt: NewEquip? = null
        // 获取到所有的背包
        val bagId = bagDC.findBagIdByPlayerIdAndBagType(bagType)
        this.equipOnHeroMap.findByOneKeyFilter(bagId) {
            if (it.id == propId) {
                rt = it
            }
            it.id != propId
        }

        return rt
    }

    // 拿某模板从普通背包中查装备信息
    fun findNormalBagPropByProtoId(bagDC: BagDC, protoId: Int): NewEquip? {
        var rt: NewEquip? = null

        val bagId = bagDC.findBagIdByPlayerIdAndBagType(NORMAL_BAG)
        this.equipOnHeroMap.findByOneKeyFilter(bagId) {
            if (it.equipProtoId == protoId) {
                rt = it
                return@findByOneKeyFilter false
            }
            true
        }

        return rt
    }

    // 拿某模板的装备信息
    fun findPropByProtoId(protoId: Int): NewEquip? {
        for (equip in myEquips) {
            if (equip.equipProtoId == protoId) {
                return equip
            }
        }
        return null
    }

    fun findAllPropByProtoId(protoId: Int): LinkedList<NewEquip> {
        val rt = LinkedList<NewEquip>()

        for (equip in myEquips) {
            if (equip.equipProtoId == protoId) {
                rt += equip
            }
        }


        return rt
    }

    // 根据ID拿物品
    fun findPropById(id: Long): NewEquip? {
        var rt: NewEquip? = null

        for (equip in myEquips) {
            if (equip.id == id) {
                return equip
            }
        }

        return null
    }

    // 查看某武将的某部位的装备
    fun findEquipByHeroIdAndPart(bagDC: BagDC, heroId: Long, part: Int): NewEquip? {
        var rt: NewEquip? = null

        val bagId = bagDC.findBagIdByPlayerIdAndBagType(HERO_BAG)
        this.equipOnHeroMap.findByKey(bagId, heroId) {
            if (it.equipOnAddress == part) {
                rt = it
                return@findByKey false
            }
            true
        }

        return rt
    }

    // 查看君主的某部位的装备
    fun findKingEquipByPlayerIdAndPart(bagDC: BagDC, part: Int): NewEquip? {
        var rt: NewEquip? = null

        val bagId = bagDC.findBagIdByPlayerIdAndBagType(KING_BAG)
        this.equipOnHeroMap.findByKey(bagId, playerId) {
            if (it.equipOnAddress == part) {
                rt = it
                return@findByKey false
            }
            true
        }

        return rt
    }

    // 查看某装备是被哪个武将ID所佩戴
    fun findEquipIsOnHero(bagDC: BagDC, equipId: Long): Long {
        var heroId = 0L
        val bagId = bagDC.findBagIdByPlayerIdAndBagType(HERO_BAG)
        this.equipOnHeroMap.findByOneKeyFilter(bagId) {
            if (it.id == equipId) {
                heroId = it.foreignId
                return@findByOneKeyFilter false
            }
            true
        }

        return heroId
    }

    // 查看君主穿戴的所有装备
    fun findKingEquipsByPlayerId(bagDC: BagDC, playerId: Long): LinkedList<NewEquip> {
        val equips = LinkedList<NewEquip>()
        val bagId = bagDC.findBagIdByPlayerIdAndBagType(KING_BAG)
        this.equipOnHeroMap.findByKey(bagId, playerId) {
            equips += it
            true
        }

        return equips
    }

    // 装备主人变化
    fun updateEquipMaster(bagDC: BagDC, equip: NewEquip, heroId: Long, part: Int) {
        if (heroId == 0L) {
            // 下装备
            val bagId = bagDC.findBagIdByPlayerIdAndBagType(NORMAL_BAG)
//		commonfun.DebugAssert(bagId != 0, "拖武将装备的的时候找不到玩家的普通背包")

            this.equipOnHeroMap.updateByKey(bagId, 0, equip) {
                equip.bagId = bagId
                equip.foreignId = heroId
            }
        }

        equip.equipOnAddress = part

        if (heroId != 0L) {
            // 穿装备
            val bagId = bagDC.findBagIdByPlayerIdAndBagType(HERO_BAG)
//		commonfun.DebugAssert(bagId != 0, "穿武将装备的的时候找不到玩家的武将背包")

            this.equipOnHeroMap.updateByKey(bagId, heroId, equip) {
                equip.bagId = bagId
                equip.foreignId = heroId
            }
        }
    }

    // 穿君主装备主人变化
    fun onKingEquip(bagDC: BagDC, playerId: Long, equip: NewEquip, part: Int) {
        equip.equipOnAddress = part

        // 穿装备
        val bagId = bagDC.findBagIdByPlayerIdAndBagType(KING_BAG)
//	commonfun.DebugAssert(bagId != 0, "穿君主装备的的时候找不到玩家的君主背包")

        this.equipOnHeroMap.updateByKey(bagId, playerId, equip) {
            equip.bagId = bagId
            equip.foreignId = playerId
        }

    }

    // 卸君主装备主人变化
    fun offKingEquip(bagDC: BagDC, equip: NewEquip) {
        // 下装备
        val bagId = bagDC.findBagIdByPlayerIdAndBagType(NORMAL_BAG)
//	commonfun.DebugAssert(bagId != 0, "脱君主装备的的时候找不到玩家的普通背包")

        this.equipOnHeroMap.updateByKey(bagId, 0, equip) {
            equip.bagId = bagId
            equip.foreignId = 0
        }

        equip.equipOnAddress = 0
    }

    //根据类型，找到玩家所有的道具
    fun findAllPropByPlayerIdAndMainType(mainType: Int, subType: Int): LinkedList<NewEquip> {
        val allProps = LinkedList<NewEquip>()
        for (equip in myEquips) {
            val propProto = pcs.equipCache.equipProtoMap[equip.equipProtoId]
            if (propProto == null) {
                continue
            }

            if (propProto.mainType == mainType && propProto.subType == subType) {
                allProps += equip
            }
        }
        return allProps
    }

}

class NewEquip(
    var id: Long,

    var equipProtoId: Int,  // 装备模板
    var equipOnAddress: Int,  // 佩戴部位
    var haveNum: Int,  // 物品数量
    var lv: Int,
    var exp: Int,
    var bagId: Long,  // 隶属的背包ID
    var foreignId: Long,  // 外键ID,表示这个道具所属,如果是穿在武将身上 就是武将ID ,如果是在普通背包或者君主装备在身上,就是玩家ID
    var playerId: Long
) : EntityWrapper<NewEquipEntity> {
    var propertyMap: HashMap<Int, HashMap<Int, Int>> = hashMapOf()   // key:属性位置 key:属性代号 value:属性值
    var cardInfoMap: HashMap<Int, Int> = hashMapOf()   // 卡片信息<key-槽位,value-卡片模版ID>

    constructor() : this(
        0, 0, 0, 0, 0, 0, 0, 0, 0
    )

    override fun getCheckModInterval(): Duration = Duration.ofSeconds(10L)

    override fun getPrimaryKey(): Serializable = NewEquipPK(playerId, id)

    override fun toEntity(): NewEquipEntity {
        return NewEquipEntity(
            id,
            equipProtoId,
            equipOnAddress,
            haveNum,
            toJson(propertyMap),
            lv,
            exp,
            bagId,
            toJson(cardInfoMap),
            foreignId,
            playerId
        )
    }

    override fun wrap(entity: NewEquipEntity) {
        id = entity.id
        equipProtoId = entity.equipProtoId
        equipOnAddress = entity.equipOnAddress
        haveNum = entity.haveNum
        lv = entity.lv
        exp = entity.exp
        bagId = entity.bagId
        foreignId = entity.foreignId
        propertyMap = toObj(entity.propertys)
        cardInfoMap = toObj(entity.cardInfo)
        playerId = entity.playerId
    }
}

