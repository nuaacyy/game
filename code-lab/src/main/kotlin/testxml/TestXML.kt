package testxml

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.File

data class AchievementResult(
	@JacksonXmlElementWrapper(useWrapping = false)
	var l: List<AchievementProto>
)

data class AchievementProto(
//	var r :String
	var id: Int,
	var achieveType: Int
	//var star: Int


	// 完成条件集合
//	var completeCondMap: MutableMap<Int, MutableList<Int>> = hashMapOf()
//	// 分享奖励
//	var shareRewards: MutableList<ResVo> = mutableListOf(),
//	// 任务完成奖励
//	var rewards: MutableList<ResVo> = mutableListOf()

) {
	lateinit var lll: MutableList<Int>
}

fun main(args: Array<String>) {
	val xmlFilePath = "code-lab/src/main/resources/achievement.xml"
	val xmlFile = File(xmlFilePath)
	val xmlMapper: ObjectMapper = XmlMapper()
	xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	xmlMapper.registerModule(KotlinModule())
	//xmlMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
	val rqq = xmlMapper.readValue<AchievementResult>(xmlFile)

	println(rqq.l[1])
}
