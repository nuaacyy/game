package com.point18.slg2d.common.pc

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.point18.slg2d.common.commonfunc.*
import com.point18.slg2d.common.constg.ZH_CN
import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.recipes.shared.SharedCount
import org.apache.zookeeper.CreateMode
import java.io.File
import java.io.Serializable
import java.util.*
import java.util.Arrays.asList
import kotlin.collections.HashMap

var xmlDir = "config_game/"
var cfgZkPath = "/Mgr/GameConfig/"
var versionZkPath = "/Mgr/GameConfig/ConfigVersion"

abstract class ProtoCacheInit(
    val configFileName: String
) {
    //加载数据
    abstract fun load(pcs: ProtoCacheStore): Serializable

    // 模板初始化
    abstract fun init(pcs: ProtoCacheStore)

    // 后置检查
    abstract fun postCheck(pcs: ProtoCacheStore)

    // 将文件数据转换成二进制数据
    //abstract fun fileData2ZkBinary(filename: String): ByteArray
    fun stringsSplit(str: String?, d: String): List<String> {
        return str?.split(d) ?: throw RuntimeException("分离的字符串str是null")
    }

    fun parse2IntArray(s: String, p: String): LinkedList<Int>? {
        val ss = stringsSplit(s, p)

        if (ss.size != 2) {
            return null
        }

        // 第1个参数
        val i = strconvAtoi(ss[0]) ?: return null

        // 第2个参数
        val j = strconvAtoi(ss[1]) ?: return null

        return LinkedList(asList(i, j))
    }

    fun strconvAtoi(str: String?): Int? {
        return if (str != null) {
            val int = str.toIntOrNull()
            int
        } else {
            null
        }
    }

    fun parseIntMap(intStr: String): HashMap<Int, Int>? {
        val intMap = hashMapOf<Int, Int>()
        if (intStr == "0") {
            return intMap
        }
        val intStrs = intStr.split("|")
        for (str in intStrs) {
            val strs = str.split(";")
            if (strs.size < 2) {
                return null
            }
            val s1 = (strs[0].toIntOrNull()) ?: return null
            val s2 = (strs[1].toIntOrNull()) ?: return null
            val ex = intMap[s1]
            if (ex != null) {
                return null
            }
            intMap[s1] = s2
        }
        return intMap
    }

    fun parseIntPairList(intStr: String): List<Pair<Int, Int>>? {
        val intList = LinkedList<Pair<Int, Int>>()
        if (intStr == "0") {
            return intList
        }
        val intStrs = intStr.split("|")
        for (str in intStrs) {
            val strs = str.split(";")
            if (strs.size < 2) {
                return null
            }
            val s1 = (strs[0].toIntOrNull()) ?: return null
            val s2 = (strs[1].toIntOrNull()) ?: return null
            intList.add(Pair(s1, s2))
        }
        return intList
    }

    fun parseFloatMap(floatStr: String): HashMap<Int, Double>? {
        val floatMap = hashMapOf<Int, Double>()
        if (floatStr == "0") {
            return floatMap
        }
        val intStrs = floatStr.split("|")
        for (str in intStrs) {
            val strs = str.split(";")
            if (strs.size < 2) {
                return null
            }
            val s1 = (strs[0].toIntOrNull()) ?: return null
            val s2 = (strs[1].toDoubleOrNull()) ?: return null
            val ex = floatMap[s1]
            if (ex != null) {
                return null
            }
            floatMap[s1] = s2
        }
        return floatMap
    }
}

interface ILanProtoCache {
    var lanMap: Map<String, String>
}

open class XmlTools {

    protected val xmlMapper = XmlMapper()

    init {
        xmlMapper.registerKotlinModule()
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

}

// xml
object XmlUnmarshalMap : XmlTools() {

    fun xmlMapRegis(): XmlMapper {
        return xmlMapper
    }

}

// 所有模板的仓库
@Volatile
var pcs: ProtoCacheStore = ProtoCacheStore()

class ProtoCacheStore {
    constructor() {
        //从本地配置加载
        loadFromZk = false
    }

    constructor(zkClient: CuratorFramework) {
        //从zk加载
        loadFromZk = true
        this.zkClient = zkClient

        loadVersion()
    }

    val loadFromZk: Boolean
    lateinit var zkClient: CuratorFramework
    var currentVersion = 0

    var protoCaches: LinkedList<ProtoCacheInit> = LinkedList()
    var lanProtoMap: HashMap<String, ILanProtoCache> = HashMap()

    //
    lateinit var wordCache: WordProtoCache
    lateinit var basicProtoCache: BasicProtoCache
    lateinit var armyRestrainCache: ArmyRestrainProtoCache
    lateinit var intSkillCache: IntSkillProtoCache
    lateinit var unitBaseCache: UnitBaseProtoCache
    lateinit var heroLevelUpCache: HeroLevelUpProtoCache
    lateinit var creatingPropertiesCache: CreatingPropertiesProtoCache
    lateinit var allianceFlagCache: AllianceFlagProtoCache
    lateinit var diamondConsumeCache: DiamondConsumeProtoCache
    lateinit var posRightCache: PosRightProtoCache
    lateinit var fightingParaProtoCache: FightingParaCache
    lateinit var questCache: QuestProtoCache
    lateinit var uiConditionCache: UiConditionProtoCache
    lateinit var equipCache: EquipProtoCache
    lateinit var compoundCache: CompoundProtoCache
    lateinit var jjcNpcCache: JjcNpcProtoCache
    lateinit var jjcChallengeCache: JjcChallengeProtoCache
    lateinit var jjcRewardCache: JjcRewardProtoCache
    lateinit var dropBagCache: DropBagProtoCache
    lateinit var researchCache: ResearchProtoCache
    lateinit var resShopCache: ResShopProtoCache
    lateinit var soliderCache: SoliderProtoCache
    lateinit var relicBoxCache: RelicBoxProtoCache
    lateinit var vipSetCache: VipSetProtoCache
    lateinit var talentCache: TalentProtoCache
    lateinit var kingExpCache: KingExpProtoCache
    lateinit var lordEquipmentPositionCache: LordEquipmentPositionProtoCache
    lateinit var lordEquipmentCache: LordEquipmentProtoCache
    lateinit var lordSuitCache: LordSuitProtoCache
    lateinit var mapLocationProtoCache: MapLocationProtoCache
    lateinit var mapMonsterProtoCache: MapMonsterProtoCache
    lateinit var mapRelicProtoCache: MapRelicProtoCache
    lateinit var wonderProtoCache: WonderProtoCache
    lateinit var wonderLocationProtoCache: WonderLocationProtoCache
    lateinit var resPointProtoCache: ResPointProtoCache
    lateinit var monsterProtoCache: MonsterProtoCache
    lateinit var bigMapBlockProtoCache: BigMapBlockProtoCache
    lateinit var lordExpAwardProtoCache: LordExpAwardProtoCache
    lateinit var heroExpAwardProtoCache: HeroExpAwardProtoCache
    lateinit var resZoneProtoCache: ResZoneProtoCache
    lateinit var monsterRefreshProtoCache: MonsterRefreshProtoCache
    lateinit var relicProtoCache: RelicProtoCache
    lateinit var mapCallProtoCache: MapCallProtoCache
    lateinit var monsterHurtProtoCache: MonsterHurtProtoCache
    lateinit var dropPropsProtoCache: DropPropsProtoCache
    lateinit var tradShipProtoCache: TradShipProtoCache
    lateinit var tradShipRefreshProtoCache: TradShipRefreshProtoCache
    lateinit var tradShipSurpriseProtoCache: TradShipSurpriseProtoCache
    lateinit var tradShipSurpriseRefreshProtoCache: TradShipSurpriseRefreshProtoCache
    lateinit var shopTotalProtoCache: ShopTotalProtoCache
    lateinit var bankProtoCache: BankProtoCache
    lateinit var bankAccelerateProtoCache: BankAccelerateProtoCache
    lateinit var skinAttributeCache: SkinAttributeProtoCache
    lateinit var innerBuildingAreaCache: InnerBuildingAreaProtoCache
    lateinit var innerBuildingCache: InnerBuildingProtoCache
    lateinit var innerBuildingDataCache: InnerBuildingDataProtoCache
    lateinit var innerBuildingLocationCache: InnerBuildingLocationProtoCache
    lateinit var heroTrophiesRankProtoCache: HeroTrophiesRankProtoCache
    lateinit var heroStarProtoCache: HeroStarProtoCache
    lateinit var heroRankProtoCache: HeroRankProtoCache
    lateinit var monsterAllianceScoreProtoCache: MonsterAllianceScoreProtoCache
    lateinit var monsterAllianceProtoCache: MonsterAllianceProtoCache
    lateinit var monsterWorldProtoCache: MonsterWorldProtoCache
    lateinit var monsterWorldRewardProtoCache: MonsterWorldRewardProtoCache
    lateinit var buffBasicProtoCache: BuffBasicProtoCache
    lateinit var buffProtoCache: BuffProtoCache
    lateinit var getHitOrderCache: GetHitOrderProtoCache
    lateinit var eventConditionProtoCache: EventConditionProtoCache
    lateinit var eventInformationProtoCache: EventInformationProtoCache
    lateinit var eventTimeProtoCache: EventTimeProtoCache
    lateinit var eventAllianceInformationProtoCache: EventAllianceInformationProtoCache
    lateinit var giftShopProtoCache: GiftShopProtoCache
    lateinit var unitTaskProtoCache: UnitTaskProtoCache
    lateinit var prisonBuffProtoCache: PrisonBuffProtoCache
    lateinit var prisonTimeProtoCache: PrisonTimeProtoCache
    lateinit var fastDeadPropsProtoCache: FastDeadPropsProtoCache
    lateinit var onlineGiftProtoCache: OnlineGiftProtoCache
    lateinit var allianceTreAwardProtoCache: AllianceTreAwardProtoCache
    lateinit var allianceTreRefreshLuckyProtoCache: AllianceTreRefreshLuckyProtoCache
    lateinit var allianceTreRefreshProtoCache: AllianceTreRefreshProtoCache
    lateinit var interiorTaskProtoCache: InteriorTaskProtoCache
    lateinit var interiorTaskRefreshProtoCache: InteriorTaskRefreshProtoCache
    lateinit var interiorTaskRewardProtoCache: InteriorTaskRewardProtoCache
    lateinit var interiorTaskRefreshLuckyProtoCache: InteriorTaskRefreshLuckyProtoCache
    lateinit var achievementProtoCache: AchievementProtoCache
    lateinit var allianceCompetitionQuestProtoCache: AllianceCompetitionQuestProtoCache
    lateinit var allianceCompetitionRankingProtoCache: AllianceCompetitionRankingProtoCache
    lateinit var allianceCompetitionRewardProtoCache: AllianceCompetitionRewardProtoCache
    lateinit var officeProtoCache: OfficeProtoCache
    lateinit var kingAwardProtoCache: KingAwardProtoCache
    lateinit var kingBuffProtoCache: KingBuffProtoCache
    lateinit var starAttributeWeekProtoCache: StarAttributeWeekProtoCache
    lateinit var unitTeamProtoCache: UnitTeamProtoCache
    lateinit var heroSkillProtoCache: HeroSkillProtoCache
    lateinit var heroSkillEffProtoCache: HeroSkillEffProtoCache
    lateinit var instanceProtoCache: InstanceProtoCache
    lateinit var instanceUnitProtoCache: InstanceUnitProtoCache
    lateinit var dropExtraProtoCache: DropExtraProtoCache
    lateinit var allianceGiftProtoCache: AllianceGiftProtoCache
    lateinit var allianceGiftLevelProtoCache: AllianceGiftLevelProtoCache
    lateinit var instanceDropExtraProtoCache: InstanceDropExtraProtoCache
    lateinit var lordHeadIconProtoCache: LordHeadIconProtoCache
    lateinit var clearanceProtoCache: ClearanceProtoCache
    lateinit var lordEquipCardProtoCache: LordEquipCardProtoCache
    lateinit var arenaShopProtoCache: ArenaShopProtoCache
    lateinit var arenaAchievementExchangeCache: ArenaAchievementExchangeCache
    lateinit var furnitureProtoCache: FurnitureProtoCache
    lateinit var homeAreaProtoCache: HomeAreaProtoCache
    lateinit var furnitureSubjectProtoCache: FurnitureSubjectProtoCache
    lateinit var altarBuffProtoCache: AltarBuffProtoCache
    lateinit var drawHeroProtoCache: DrawHeroProtoCache
    lateinit var attackOrderProtoCache: AttackOrderProtoCache
    lateinit var soliderTeamProtoCache: SoliderTeamProtoCache
    lateinit var mapOpenProtoCache: MapOpenProtoCache
    lateinit var monsterActivityLocationProtoCache: MonsterActivityLocationProtoCache
    lateinit var monsterActivityProtoCache: MonsterActivityProtoCache
    lateinit var palaceRefreshProtoCache: PalaceRefreshProtoCache
    lateinit var palaceProtoCache: PalaceProtoCache
    lateinit var palaceCrystalProtoCache: PalaceCrystalProtoCache
    lateinit var mandatoryGuideProtoCache: MandatoryGuideProtoCache
    lateinit var triggerGuideProtoCache: TriggerGuideProtoCache
    lateinit var emigrationCostProtoCache: EmigrationCostProtoCache
    lateinit var giftBagProtoCache: GiftBagProtoCache
    lateinit var appNoticeProtoCache: AppNoticeProtoCache
    lateinit var quotaBagProtoCache: QuotaBagProtoCache
    lateinit var languageZhCNProtoCache: LanguageZhCNProtoCache

    // 加载配置
    fun initProtoCache() {
        // 准备模板缓存类
        prepareCaches()

        // 加载配置
        for (eachProtoCache in this.protoCaches) {
            eachProtoCache.init(this)
        }

        // 配置加载完成后的关联性验证
        for (eachProtoCache in this.protoCaches) {
            eachProtoCache.postCheck(this)
        }

    }

    // 将配置导入zk
    fun resolveProto2Zk(client: CuratorFramework) {
        // 准备模板缓存类
        prepareCaches()

        zkIdGen(client, versionZkPath)?.let { newVersion ->
            try {
                // 将配置加载上来，导入zk中
                for (eachProtoCache in this.protoCaches) {
                    if (!eachProtoCache.configFileName.endsWith(".xml") && !eachProtoCache.configFileName.endsWith(".json")) {
                        continue
                    }
                    try {
                        val loadData = eachProtoCache.load(this)
                        val bytes = obj2Bytes(loadData)
                        val gzipBytes = requireNotNull(gzip(bytes))
                        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                            .forPath("$cfgZkPath$newVersion/${eachProtoCache.configFileName}", gzipBytes)
                    } catch (e: Exception) {
                        println(eachProtoCache.configFileName + ":导入zk出错，错误原因${e.message}")
                        //删除出错版本节点
                        if (client.checkExists().forPath(cfgZkPath + newVersion) != null) {
                            client.delete().guaranteed().deletingChildrenIfNeeded().forPath(cfgZkPath + newVersion)
                        }
                        return
                    }
                }

                //删除旧版本数据
                for (index in 5..15) {
                    if (client.checkExists().forPath(cfgZkPath + (newVersion - index)) != null) {
                        client.delete().guaranteed().deletingChildrenIfNeeded()
                            .forPath(cfgZkPath + (newVersion - index))
                    }
                }

                println("导入zk成功，版本：$newVersion")
            } catch (e: Exception) {
                println("zk设置版本错误:$e")
            }
        }

    }

    fun loadVersion() {
        //从zk加载数据
        val counter = SharedCount(this.zkClient, versionZkPath, 0)
        counter.start()
        this.currentVersion = counter.count
        counter.close()
    }

    private fun prepareCaches() {

        this.triggerGuideProtoCache = TriggerGuideProtoCache()
        protoCaches.add(this.triggerGuideProtoCache)

        this.mandatoryGuideProtoCache = MandatoryGuideProtoCache()
        protoCaches.add(this.mandatoryGuideProtoCache)

        this.palaceCrystalProtoCache = com.point18.slg2d.common.pc.PalaceCrystalProtoCache()
        protoCaches.add(this.palaceCrystalProtoCache)

        this.palaceProtoCache = com.point18.slg2d.common.pc.PalaceProtoCache()
        protoCaches.add(this.palaceProtoCache)

        this.palaceRefreshProtoCache = com.point18.slg2d.common.pc.PalaceRefreshProtoCache()
        protoCaches.add(this.palaceRefreshProtoCache)

        this.mapOpenProtoCache = com.point18.slg2d.common.pc.MapOpenProtoCache()
        protoCaches.add(this.mapOpenProtoCache)

        this.soliderTeamProtoCache = com.point18.slg2d.common.pc.SoliderTeamProtoCache()
        protoCaches.add(this.soliderTeamProtoCache)

        this.attackOrderProtoCache = com.point18.slg2d.common.pc.AttackOrderProtoCache()
        protoCaches.add(this.attackOrderProtoCache)

        this.drawHeroProtoCache = com.point18.slg2d.common.pc.DrawHeroProtoCache()
        protoCaches.add(this.drawHeroProtoCache)

        this.altarBuffProtoCache = com.point18.slg2d.common.pc.AltarBuffProtoCache()
        protoCaches.add(this.altarBuffProtoCache)

        this.wordCache = WordProtoCache()
        protoCaches.add(this.wordCache)

        this.basicProtoCache = BasicProtoCache()
        protoCaches.add(this.basicProtoCache)

        this.armyRestrainCache = ArmyRestrainProtoCache()
        protoCaches.add(this.armyRestrainCache)

        this.intSkillCache = IntSkillProtoCache()
        protoCaches.add(this.intSkillCache)

        this.unitBaseCache = UnitBaseProtoCache()
        protoCaches.add(this.unitBaseCache)

        this.heroLevelUpCache = HeroLevelUpProtoCache()
        protoCaches.add(this.heroLevelUpCache)

        this.creatingPropertiesCache = CreatingPropertiesProtoCache()
        protoCaches.add(this.creatingPropertiesCache)

        this.allianceFlagCache = AllianceFlagProtoCache()
        protoCaches.add(this.allianceFlagCache)

        this.diamondConsumeCache = DiamondConsumeProtoCache()
        protoCaches.add(this.diamondConsumeCache)

        this.posRightCache = PosRightProtoCache()
        protoCaches.add(this.posRightCache)

        this.fightingParaProtoCache = FightingParaCache()
        protoCaches.add(this.fightingParaProtoCache)

        this.questCache = QuestProtoCache()
        protoCaches.add(this.questCache)

        this.uiConditionCache = UiConditionProtoCache()
        protoCaches.add(this.uiConditionCache)

        this.equipCache = EquipProtoCache()
        protoCaches.add(this.equipCache)

        this.compoundCache = CompoundProtoCache()
        protoCaches.add(this.compoundCache)

        this.jjcNpcCache = JjcNpcProtoCache()
        protoCaches.add(this.jjcNpcCache)

        this.jjcChallengeCache = JjcChallengeProtoCache()
        protoCaches.add(this.jjcChallengeCache)

        this.jjcRewardCache = JjcRewardProtoCache()
        protoCaches.add(this.jjcRewardCache)

        this.dropBagCache = DropBagProtoCache()
        protoCaches.add(this.dropBagCache)

        this.researchCache = ResearchProtoCache()
        protoCaches.add(this.researchCache)

        this.resShopCache = ResShopProtoCache()
        protoCaches.add(this.resShopCache)

        this.soliderCache = SoliderProtoCache()
        protoCaches.add(this.soliderCache)

        this.relicBoxCache = RelicBoxProtoCache()
        protoCaches.add(this.relicBoxCache)

        this.vipSetCache = VipSetProtoCache()
        protoCaches.add(this.vipSetCache)

        this.talentCache = TalentProtoCache()
        protoCaches.add(this.talentCache)

        this.kingExpCache = KingExpProtoCache()
        protoCaches.add(this.kingExpCache)

        this.lordEquipmentPositionCache = LordEquipmentPositionProtoCache()
        protoCaches.add(this.lordEquipmentPositionCache)

        this.lordEquipmentCache = LordEquipmentProtoCache()
        protoCaches.add(this.lordEquipmentCache)

        this.lordSuitCache = LordSuitProtoCache()
        protoCaches.add(this.lordSuitCache)

        this.mapLocationProtoCache = MapLocationProtoCache()
        protoCaches.add(this.mapLocationProtoCache)

        this.mapMonsterProtoCache = MapMonsterProtoCache()
        protoCaches.add(this.mapMonsterProtoCache)

        this.mapRelicProtoCache = MapRelicProtoCache()
        protoCaches.add(this.mapRelicProtoCache)

        this.wonderProtoCache = WonderProtoCache()
        protoCaches.add(this.wonderProtoCache)

        this.wonderLocationProtoCache = WonderLocationProtoCache()
        protoCaches.add(this.wonderLocationProtoCache)

        this.resPointProtoCache = ResPointProtoCache()
        protoCaches.add(this.resPointProtoCache)

        this.monsterProtoCache = MonsterProtoCache()
        protoCaches.add(this.monsterProtoCache)

        this.bigMapBlockProtoCache = BigMapBlockProtoCache()
        protoCaches.add(this.bigMapBlockProtoCache)

        this.lordExpAwardProtoCache = LordExpAwardProtoCache()
        protoCaches.add(this.lordExpAwardProtoCache)

        this.heroExpAwardProtoCache = HeroExpAwardProtoCache()
        protoCaches.add(this.heroExpAwardProtoCache)

        this.resZoneProtoCache = ResZoneProtoCache()
        protoCaches.add(this.resZoneProtoCache)

        this.monsterRefreshProtoCache = MonsterRefreshProtoCache()
        protoCaches.add(this.monsterRefreshProtoCache)

        this.relicProtoCache = RelicProtoCache()
        protoCaches.add(this.relicProtoCache)

        this.mapCallProtoCache = MapCallProtoCache()
        protoCaches.add(this.mapCallProtoCache)

        this.monsterHurtProtoCache = MonsterHurtProtoCache()
        protoCaches.add(this.monsterHurtProtoCache)

        this.dropPropsProtoCache = DropPropsProtoCache()
        protoCaches.add(this.dropPropsProtoCache)

        this.tradShipProtoCache = TradShipProtoCache()
        protoCaches.add(this.tradShipProtoCache)

        this.tradShipRefreshProtoCache = TradShipRefreshProtoCache()
        protoCaches.add(this.tradShipRefreshProtoCache)

        this.tradShipSurpriseProtoCache = TradShipSurpriseProtoCache()
        protoCaches.add(this.tradShipSurpriseProtoCache)

        this.tradShipSurpriseRefreshProtoCache = TradShipSurpriseRefreshProtoCache()
        protoCaches.add(this.tradShipSurpriseRefreshProtoCache)

        this.shopTotalProtoCache = ShopTotalProtoCache()
        protoCaches.add(this.shopTotalProtoCache)

        this.bankProtoCache = BankProtoCache()
        protoCaches.add(this.bankProtoCache)

        this.bankAccelerateProtoCache = BankAccelerateProtoCache()
        protoCaches.add(this.bankAccelerateProtoCache)

        this.skinAttributeCache = SkinAttributeProtoCache()
        protoCaches.add(this.skinAttributeCache)

        this.innerBuildingAreaCache = InnerBuildingAreaProtoCache()
        protoCaches.add(this.innerBuildingAreaCache)

        this.innerBuildingCache = InnerBuildingProtoCache()
        protoCaches.add(this.innerBuildingCache)

        this.innerBuildingDataCache = InnerBuildingDataProtoCache()
        protoCaches.add(this.innerBuildingDataCache)

        this.innerBuildingLocationCache = InnerBuildingLocationProtoCache()
        protoCaches.add(this.innerBuildingLocationCache)

        this.interiorTaskProtoCache = InteriorTaskProtoCache()
        protoCaches.add(this.interiorTaskProtoCache)

        this.interiorTaskRefreshLuckyProtoCache = InteriorTaskRefreshLuckyProtoCache()
        protoCaches.add(this.interiorTaskRefreshLuckyProtoCache)

        this.interiorTaskRefreshProtoCache = InteriorTaskRefreshProtoCache()
        protoCaches.add(this.interiorTaskRefreshProtoCache)

        this.interiorTaskRewardProtoCache = InteriorTaskRewardProtoCache()
        protoCaches.add(this.interiorTaskRewardProtoCache)

        this.heroTrophiesRankProtoCache = HeroTrophiesRankProtoCache()
        protoCaches.add(this.heroTrophiesRankProtoCache)

        this.heroStarProtoCache = HeroStarProtoCache()
        protoCaches.add(this.heroStarProtoCache)

        this.heroRankProtoCache = HeroRankProtoCache()
        protoCaches.add(this.heroRankProtoCache)

        this.monsterAllianceScoreProtoCache = MonsterAllianceScoreProtoCache()
        protoCaches.add(this.monsterAllianceScoreProtoCache)

        this.monsterAllianceProtoCache = MonsterAllianceProtoCache()
        protoCaches.add(this.monsterAllianceProtoCache)

        this.monsterWorldProtoCache = MonsterWorldProtoCache()
        protoCaches.add(this.monsterWorldProtoCache)

        this.monsterWorldRewardProtoCache = MonsterWorldRewardProtoCache()
        protoCaches.add(this.monsterWorldRewardProtoCache)

        this.buffBasicProtoCache = BuffBasicProtoCache()
        protoCaches.add(this.buffBasicProtoCache)

        this.buffProtoCache = BuffProtoCache()
        protoCaches.add(this.buffProtoCache)

        this.getHitOrderCache = GetHitOrderProtoCache()
        protoCaches.add(this.getHitOrderCache)

        this.eventConditionProtoCache = EventConditionProtoCache()
        protoCaches.add(this.eventConditionProtoCache)

        this.eventInformationProtoCache = EventInformationProtoCache()
        protoCaches.add(this.eventInformationProtoCache)

        this.eventTimeProtoCache = EventTimeProtoCache()
        protoCaches.add(this.eventTimeProtoCache)

        this.eventAllianceInformationProtoCache = EventAllianceInformationProtoCache()
        protoCaches.add(this.eventAllianceInformationProtoCache)

        this.giftShopProtoCache = GiftShopProtoCache()
        protoCaches.add(this.giftShopProtoCache)

        this.unitTaskProtoCache = UnitTaskProtoCache()
        protoCaches.add(this.unitTaskProtoCache)

        this.prisonBuffProtoCache = PrisonBuffProtoCache()
        protoCaches.add(this.prisonBuffProtoCache)

        this.prisonTimeProtoCache = PrisonTimeProtoCache()
        protoCaches.add(this.prisonTimeProtoCache)

        this.fastDeadPropsProtoCache = FastDeadPropsProtoCache()
        protoCaches.add(this.fastDeadPropsProtoCache)

        this.onlineGiftProtoCache = OnlineGiftProtoCache()
        protoCaches.add(this.onlineGiftProtoCache)

        this.allianceTreAwardProtoCache = AllianceTreAwardProtoCache()
        protoCaches.add(this.allianceTreAwardProtoCache)

        this.allianceTreRefreshLuckyProtoCache = AllianceTreRefreshLuckyProtoCache()
        protoCaches.add(this.allianceTreRefreshLuckyProtoCache)

        this.allianceTreRefreshProtoCache = AllianceTreRefreshProtoCache()
        protoCaches.add(this.allianceTreRefreshProtoCache)

        this.achievementProtoCache = AchievementProtoCache()
        protoCaches.add(this.achievementProtoCache)

        this.allianceCompetitionQuestProtoCache = AllianceCompetitionQuestProtoCache()
        protoCaches.add(this.allianceCompetitionQuestProtoCache)

        this.allianceCompetitionRankingProtoCache = AllianceCompetitionRankingProtoCache()
        protoCaches.add(this.allianceCompetitionRankingProtoCache)

        this.allianceCompetitionRewardProtoCache = AllianceCompetitionRewardProtoCache()
        protoCaches.add(this.allianceCompetitionRewardProtoCache)

        this.officeProtoCache = OfficeProtoCache()
        protoCaches.add(this.officeProtoCache)

        this.kingAwardProtoCache = KingAwardProtoCache()
        protoCaches.add(this.kingAwardProtoCache)

        this.kingBuffProtoCache = KingBuffProtoCache()
        protoCaches.add(this.kingBuffProtoCache)

        this.starAttributeWeekProtoCache = StarAttributeWeekProtoCache()
        protoCaches.add(this.starAttributeWeekProtoCache)

        this.unitTeamProtoCache = UnitTeamProtoCache()
        protoCaches.add(this.unitTeamProtoCache)

        this.heroSkillProtoCache = HeroSkillProtoCache()
        protoCaches.add(this.heroSkillProtoCache)

        this.heroSkillEffProtoCache = HeroSkillEffProtoCache()
        protoCaches.add(this.heroSkillEffProtoCache)

        this.instanceProtoCache = InstanceProtoCache()
        protoCaches.add(this.instanceProtoCache)

        this.instanceUnitProtoCache = InstanceUnitProtoCache()
        protoCaches.add(this.instanceUnitProtoCache)

        this.dropExtraProtoCache = DropExtraProtoCache()
        protoCaches.add(this.dropExtraProtoCache)

        this.allianceGiftProtoCache = AllianceGiftProtoCache()
        protoCaches.add(this.allianceGiftProtoCache)

        this.allianceGiftLevelProtoCache = AllianceGiftLevelProtoCache()
        protoCaches.add(this.allianceGiftLevelProtoCache)

        this.instanceDropExtraProtoCache = InstanceDropExtraProtoCache()
        protoCaches.add(this.instanceDropExtraProtoCache)

        this.lordHeadIconProtoCache = LordHeadIconProtoCache()
        protoCaches.add(this.lordHeadIconProtoCache)

        this.clearanceProtoCache = ClearanceProtoCache()
        protoCaches.add(this.clearanceProtoCache)

        this.lordEquipCardProtoCache = LordEquipCardProtoCache()
        protoCaches.add(this.lordEquipCardProtoCache)

        this.arenaShopProtoCache = ArenaShopProtoCache()
        protoCaches.add(this.arenaShopProtoCache)

        this.arenaAchievementExchangeCache = ArenaAchievementExchangeCache()
        protoCaches.add(this.arenaAchievementExchangeCache)

        this.furnitureProtoCache = FurnitureProtoCache()
        protoCaches.add(this.furnitureProtoCache)

        this.homeAreaProtoCache = HomeAreaProtoCache()
        protoCaches.add(this.homeAreaProtoCache)

        this.furnitureSubjectProtoCache = FurnitureSubjectProtoCache()
        protoCaches.add(this.furnitureSubjectProtoCache)

        this.monsterActivityLocationProtoCache = MonsterActivityLocationProtoCache()
        protoCaches.add(this.monsterActivityLocationProtoCache)

        this.monsterActivityProtoCache = MonsterActivityProtoCache()
        protoCaches.add(this.monsterActivityProtoCache)

        this.emigrationCostProtoCache = EmigrationCostProtoCache()
        protoCaches.add(this.emigrationCostProtoCache)

        this.giftBagProtoCache = GiftBagProtoCache()
        protoCaches.add(this.giftBagProtoCache)

        this.appNoticeProtoCache = AppNoticeProtoCache()
        protoCaches.add(this.appNoticeProtoCache)

        this.quotaBagProtoCache = QuotaBagProtoCache()
        protoCaches.add(this.quotaBagProtoCache)

        // lan的配置缓存
        this.languageZhCNProtoCache = LanguageZhCNProtoCache()
        protoCaches.add(this.languageZhCNProtoCache)
        lanProtoMap[ZH_CN] = this.languageZhCNProtoCache

    }

    fun lanWithParam(lanType: String, lanId: String, vararg params: String): String {
        val rt = ""
        val lanProto = pcs.lanProtoMap[lanType]
        if (lanProto == null) {
            return rt
        }

        val content = lanProto.lanMap[lanId]
        if (content == null) {
            return rt
        }

        var tmp: String = content
        for (i in 1 .. params.size) {
            // todo jh 玩家名如果和lan的key相同，会有问题
            val param = lanProto.lanMap[params[i - 1]] ?: params[i - 1]
            tmp = tmp.replace("$$i", param)
        }

        return tmp
    }

}

const val LOAD_TYPE_XML = 1
const val LOAD_TYPE_JSON = 2

// 从zk数据或者文件中加载数据
inline fun <reified T : Any> loadConfig(pcs: ProtoCacheStore, filename: String, loadType: Int = LOAD_TYPE_XML): T {
    if (pcs.loadFromZk) {
        val bytes = pcs.zkClient.data.forPath("$cfgZkPath${pcs.currentVersion}/$filename")
        val unGzipBytes = requireNotNull(unGzip(bytes))
        return bytes2Obj(unGzipBytes) as T
    }

    val readXmlResult = when (loadType) {
        LOAD_TYPE_XML -> {
            val xmlFilePath = xmlDir + filename
            val xmlFile = File(xmlFilePath)
            val xmlMapper = XmlUnmarshalMap.xmlMapRegis()
            xmlMapper.readValue<T>(xmlFile)
        }
        else -> {
            val jsonFilePath = xmlDir + filename
            val jsonConfigFile = File(jsonFilePath)
            val mapper = jacksonObjectMapper()
            mapper.readValue<T>(jsonConfigFile)
        }
    }

    return readXmlResult
}
