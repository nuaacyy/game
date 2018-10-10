package com.point18.slg2d.common.pc

import com.point18.slg2d.common.commonfunc.processConfig
import java.io.File
import java.io.Serializable
import java.util.*

var filterWordsFromGm: LinkedList<String> = LinkedList()

data class TreeNode(
    //游戏的屏蔽字树
    val nodes: HashMap<Char, TreeNode>,
    //后台的屏蔽字树
    val otherNodes: HashMap<Char, TreeNode>,
    var finish: Int
) : Serializable

val KEYWORD_FILE = "wordList.txt"
val SYMBOL_FILE = "wordList_2.txt"
val SYMBOL_FILE_FOR_ALLIANCE_NAME = "wordList_3.txt"
val SYMBOL_FILE_FOR_ALLIANCE_SHORT_NAME = "wordList_4.txt"

val KEYWORD_FILE_OVERSEA = "wordList_oversea.txt"
val SYMBOL_FILE_OVERSEA = "wordList_2_oversea.txt"
val SYMBOL_FILE_OVERSEA_FOR_ALLIANCE_NAME = "wordList_3_oversea.txt"
val SYMBOL_FILE_OVERSEA_FOR_ALLIANCE_SHORT_NAME = "wordList_4_oversea.txt"

const val INLAND = 185 //国内游戏ID
const val OVERSEA = 254 //海外游戏ID
typealias WordCheck = Int

const val WORD_CHECK_KEYWORD = 1.shl(2)                                     // 敏感字
const val WORD_CHECK_SYMBOL = 1.shl(3)                                     // 屏蔽的特殊符号
const val WORD_CHECK_SYMBOL_FOR_ALLIANCE_NAME = 1.shl(4)                  // 联盟名称屏蔽的特殊符号
const val WORD_CHECK_SYMBOL_FOR_ALLIANCE_SHORT_NAME = 1.shl(5)            // 联盟简称屏蔽的特殊符号
const val WORD_CHECK_LETTER_NUMBER = 1.shl(6)                               // 数字和字母
const val WORD_CHECK_NO_CHINESE = 1.shl(7)                             // 不能出现中文

const val WORD_CHECK_NAME = WORD_CHECK_KEYWORD or (WORD_CHECK_SYMBOL)        // 名称检测：检查敏感字和特殊符号
const val WORD_CHECK_MESSAGE = WORD_CHECK_KEYWORD                             // 只检查敏感字
const val WORD_CHECK_ALLIANCE_SLOGAN =
    WORD_CHECK_KEYWORD or WORD_CHECK_NO_CHINESE                             // 只检查敏感字 + 不能中文 用于联盟标语

const val WORD_CHECK_KEYWORD_LETTER_NUMBER = WORD_CHECK_KEYWORD or (WORD_CHECK_LETTER_NUMBER) // 检查敏感字，并且只能是数字或字母(可能中文)
const val WORD_CHECK_LETTER_NUMBER_NOCHINESE =
    WORD_CHECK_KEYWORD or WORD_CHECK_LETTER_NUMBER or WORD_CHECK_NO_CHINESE // 检查敏感字，并且只能是数字或字母

const val WORD_CHECK_ALLIANCE_NAME =
    WORD_CHECK_KEYWORD or (WORD_CHECK_SYMBOL_FOR_ALLIANCE_NAME) or (WORD_CHECK_NO_CHINESE)     // 联盟名检测
const val WORD_CHECK_SHORT_ALLIANCE_NAME =
    WORD_CHECK_KEYWORD or (WORD_CHECK_SYMBOL_FOR_ALLIANCE_SHORT_NAME) or (WORD_CHECK_NO_CHINESE)// 联盟简称检测

class WordCheckResult(
    var wordCheckResult: Int = 0,
    var newString: String = ""
)

const val WORD_CHECK_RESULT_FORBIDDEN = -1 // 失败: 非法字符
const val WORD_CHECK_RESULT_SUCCESS = 0  // 成功: 没有错误
const val WORD_CHECK_RESULT_LENGTH_SHORT = 1  // 失败: 长度不足
const val WORD_CHECK_RESULT_LENGTH_EXCEED = 2  // 失败: 长度超过

/**
 * 查找并替换关键词
 *
 */
data class CheckWord(
    var has: Boolean,
    var newStr: String
)

fun checkWord(treeNode: TreeNode, target: String): CheckWord {
    // 将目标字符串转换为字符数组。
    val targetStr = target.toCharArray()
    // 搜索并替换
    searchAndReplaceWord(treeNode, targetStr, 1)
    // 重新构建为字符串，并返回
    val newStr = String(targetStr)

    if (newStr == target) {
        return CheckWord(false, newStr)
    }
    return CheckWord(true, newStr)
}

class WordProtoCache : ProtoCacheInit("") {

    lateinit var keyWordTree: TreeNode
    lateinit var symbolTree: TreeNode
    lateinit var keyWordForAllianceNameTree: TreeNode
    lateinit var keyWordForAlliancShortNameTree: TreeNode

    override fun load(pcs: ProtoCacheStore): Serializable {
        return TreeNode(hashMapOf(), hashMapOf(), 0)
    }

    override fun init(pcs: ProtoCacheStore) {
        // 屏蔽字
        val keyWord: String
        val symbol: String
        val symbolForAllianceName: String
        val symbolForAllianceShortName: String
        val gameId = processConfig.gameId

        if (gameId == INLAND) {
            // 国内
            keyWord = KEYWORD_FILE
            symbol = SYMBOL_FILE
            symbolForAllianceName = SYMBOL_FILE_FOR_ALLIANCE_NAME
            symbolForAllianceShortName = SYMBOL_FILE_FOR_ALLIANCE_SHORT_NAME
        } else {
            // todo 非国内的情况全部视为国外
            // todo gameId在public服和home服还是0 暂且把一起视为国内
            keyWord = KEYWORD_FILE
            symbol = SYMBOL_FILE
            symbolForAllianceName = SYMBOL_FILE_FOR_ALLIANCE_NAME
            symbolForAllianceShortName = SYMBOL_FILE_FOR_ALLIANCE_SHORT_NAME
        }

        val keyWordTree = initWord(keyWord)

        if (keyWordTree == null) {
            throw RuntimeException("初始化关键词失败!")
        }

        // 标点符号
        val symbolTree = initWord(symbol)

        if (symbolTree == null) {
            throw RuntimeException("初始化符号失败!")
        }

        // 标点符号
        val symbolTreeForAllianceName = initWord(symbolForAllianceName)

        if (symbolTreeForAllianceName == null) {
            throw RuntimeException("初始化联盟名称失败!")
        }

        // 标点符号
        val symbolTreeForAllianceShortName = initWord(symbolForAllianceShortName)

        if (symbolTreeForAllianceShortName == null) {
            throw RuntimeException("初始化联盟简称失败!")
        }

        this.keyWordTree = keyWordTree
        this.symbolTree = symbolTree
        this.keyWordForAllianceNameTree = symbolTreeForAllianceName
        this.keyWordForAlliancShortNameTree = symbolTreeForAllianceShortName
    }

    override fun postCheck(pcs: ProtoCacheStore) {

    }

    fun check(s: String, l: List<Int>, check: WordCheck): WordCheckResult {
        if (processConfig.isCheckText == 0) {
            return WordCheckResult(WORD_CHECK_RESULT_SUCCESS, s)
        }
        val w = s

        val ch = w.toCharArray()
        for (c in ch) {
            //todo 非法字符与控制字符待添加
//            if (!utf8.ValidRune(r)) {
//                // 含有非法的Unicode码点
//                return WORD_CHECK_RESULT_FORBIDDEN
//            } else if (unicode.isControl(r)) {
//                // 控制字符
//                return WORD_CHECK_RESULT_FORBIDDEN
            if ((check and WORD_CHECK_LETTER_NUMBER) == WORD_CHECK_LETTER_NUMBER) {
                val isDigit = Character.isDigit(c)

                val isLetter = Character.isLetter(c)
                if (!isDigit && !isLetter) {
                    return WordCheckResult(WORD_CHECK_RESULT_FORBIDDEN, s)
                }
            }
            if ((check and WORD_CHECK_NO_CHINESE) == WORD_CHECK_NO_CHINESE) {
                val ub = Character.UnicodeBlock.of(c)
                if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                    || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                    || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                    || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                    || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                    || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                ) {
                    return WordCheckResult(WORD_CHECK_RESULT_FORBIDDEN, s)
                }
            }

        }

        // 长度不足

        if (s.length < l[0]) {
            return WordCheckResult(WORD_CHECK_RESULT_LENGTH_SHORT, s)
        }

        // 长度超过

        if (s.length > l[1]) {
            return WordCheckResult(WORD_CHECK_RESULT_LENGTH_EXCEED, s)
        }

        // 特殊符号检测

        if ((check and WORD_CHECK_SYMBOL) == WORD_CHECK_SYMBOL) {
            val checkRst = checkWord(this.symbolTree, w)

            if (checkRst.has) {
                return WordCheckResult(WORD_CHECK_RESULT_FORBIDDEN, s)
            }
        }

        // 联盟名称特殊符号检测

        if (check and WORD_CHECK_SYMBOL_FOR_ALLIANCE_NAME == WORD_CHECK_SYMBOL_FOR_ALLIANCE_NAME) {
            val checkRst = checkWord(this.keyWordForAllianceNameTree, w)

            if (checkRst.has) {
                return WordCheckResult(WORD_CHECK_RESULT_FORBIDDEN, s)
            }
        }

        // 联盟简称特殊符号检测

        if (check and WORD_CHECK_SYMBOL_FOR_ALLIANCE_SHORT_NAME == WORD_CHECK_SYMBOL_FOR_ALLIANCE_SHORT_NAME) {
            val checkRst = checkWord(this.keyWordForAlliancShortNameTree, w)

            if (checkRst.has) {
                return WordCheckResult(WORD_CHECK_RESULT_FORBIDDEN, s)
            }
        }

        // 敏感字检测

        if ((check and WORD_CHECK_KEYWORD) == WORD_CHECK_KEYWORD) {
            val checkRst = checkWord(this.keyWordTree, w)

            if (checkRst.has) {
                return WordCheckResult(WORD_CHECK_RESULT_FORBIDDEN, checkRst.newStr)
            }
        }

        return WordCheckResult(WORD_CHECK_RESULT_SUCCESS, s)
    }

}

fun initWord(file: String): TreeNode? {
    // 建立关键词树
    val keyWords = fetchWords(file)

    val treeNode = createWordTree(keyWords, filterWordsFromGm)
    return treeNode
}

fun fetchWords(file: String): (List<String>) {
    val keyWords = LinkedList<String>()
    val filePath = xmlDir + file
    File(filePath).useLines {
        for (line in it) {
            //过滤空行
            if (line.trim().isEmpty()) {
                continue
            }
            keyWords.add(line)
        }
    }

    // 如果是标点符号，那么需要添加 \r \n
    if (file == SYMBOL_FILE) {
        keyWords.add("\r")
        keyWords.add("\n")
    }

    return keyWords
}

fun createWordTree(keyWords: List<String>, otherWord: List<String>): TreeNode {
    val tempKeyWordNode = TreeNode(hashMapOf(), hashMapOf(), 0)
    for (keyWord in keyWords) {
        val chars = keyWord.toCharArray()
        // 这里构建DFA数组
        var tmpNode = tempKeyWordNode
        val length = chars.size
        for (i in 0..(length - 1)) {
            var node = tmpNode.nodes[chars[i]]
            if (node == null) {
                node = TreeNode(hashMapOf(), hashMapOf(), 0)
                tmpNode.nodes[chars[i]] = node
            }
            tmpNode = node
            if (i < length - 1) {
                // 不是最后一个字符
                if (tmpNode.finish == 1) {
                    tmpNode.finish = 2 // 后续还有屏蔽字，设置为2，表示这里是个临时终止点
                }
            } else {
                if (tmpNode.nodes.count() == 0) {
                    // 如果之前没有屏蔽字，就设置结束标记
                    tmpNode.finish = 1
                } else {
                    tmpNode.finish = 2 // 后续还有屏蔽字，设置为2，表示这里是个临时终止点
                }
            }
        }
    }
    for (keyWord in otherWord) {
        val chars = keyWord.toCharArray()
        // 这里构建DFA数组
        var tmpNode = tempKeyWordNode
        val length = chars.size
        for (i in 0..(length - 1)) {
            var node = tmpNode.otherNodes[chars[i]]
            if (node == null) {
                node = TreeNode(hashMapOf(), hashMapOf(), 0)
                tmpNode.otherNodes[chars[i]] = node
            }
            tmpNode = node
            if (i < length - 1) {
                // 不是最后一个字符
                if (tmpNode.finish == 1) {
                    tmpNode.finish = 2 // 后续还有屏蔽字，设置为2，表示这里是个临时终止点
                }
            } else {
                if (tmpNode.otherNodes.count() == 0) {
                    // 如果之前没有屏蔽字，就设置结束标记
                    tmpNode.finish = 1
                } else {
                    tmpNode.finish = 2 // 后续还有屏蔽字，设置为2，表示这里是个临时终止点
                }
            }
        }
    }

    return tempKeyWordNode
}

fun addNewWord(pcs: ProtoCacheStore, word: String) {
    val chars = word.toCharArray()
    // 这里构建DFA数组
    var tmpNode = pcs.wordCache.keyWordTree
    val length = chars.size
    for (i in 0..(length - 1)) {
        var node = tmpNode.otherNodes[chars[i]]
        if (node == null) {
            node = TreeNode(hashMapOf(), hashMapOf(), 0)
            tmpNode.otherNodes[chars[i]] = node
        }
        tmpNode = node
        if (i < length - 1) {
            // 不是最后一个字符
            if (tmpNode.finish == 1) {
                tmpNode.finish = 2 // 后续还有屏蔽字，设置为2，表示这里是个临时终止点
            }
        } else {
            if (tmpNode.otherNodes.count() == 0) {
                // 如果之前没有屏蔽字，就设置结束标记
                tmpNode.finish = 1
            } else {
                tmpNode.finish = 2 // 后续还有屏蔽字，设置为2，表示这里是个临时终止点
            }
        }
    }
}

fun searchAndReplaceWord(treeNode: TreeNode, targetStr: CharArray, needReplace: Int): Int {
    val length = targetStr.size
    var keyWordNum = 0           // 找到关键词数量（一句话中有可能有几个关键词）
    var foundTmpKeyWorld = false // 当前是否找到临时关键词
    var tmpNode = treeNode
    var hasFound = 0
    var i = 0
    while (i < length) {
        val thisChar = targetStr[i]
        val node = tmpNode.nodes[thisChar]
        if (node == null) {
            tmpNode = treeNode
            i = i - hasFound + 1 // 回朔
            hasFound = 0
            foundTmpKeyWorld = false

        } else if (node.finish == 1) {
            // 找到屏蔽词终结点
            if (!foundTmpKeyWorld) {
                keyWordNum++
            }

            hasFound++
            tmpNode = treeNode
            if (needReplace == 1) {
                keyWordReplace(targetStr, i, hasFound)
            }

            i++
            hasFound = 0
            foundTmpKeyWorld = false

        } else if (node.finish == 2) {
            // 找到屏蔽词临时终结点
            if (!foundTmpKeyWorld) {
                keyWordNum++
                foundTmpKeyWorld = true
            }

            hasFound++ // 这里需要先增加。
            if (needReplace == 1) {
                // 如果需要替换，那么替换当前部分
                keyWordReplace(targetStr, i, hasFound)
            }
            tmpNode = node
            i++
        } else {
            // 找到屏蔽词中间点
            tmpNode = node
            hasFound++
            i++
        }
    }
    var hasFoundToo = 0
    var j = 0
    tmpNode = treeNode
    while (j < length) {
        val thisChar = targetStr[j]
        val node = tmpNode.otherNodes[thisChar]
        if (node == null) {
            tmpNode = treeNode
            j = j - hasFoundToo + 1 // 回朔
            hasFoundToo = 0
            foundTmpKeyWorld = false

        } else if (node.finish == 1) {
            // 找到屏蔽词终结点
            if (!foundTmpKeyWorld) {
                keyWordNum++
            }

            hasFoundToo++
            tmpNode = treeNode
            if (needReplace == 1) {
                keyWordReplace(targetStr, j, hasFoundToo)
            }

            j++
            hasFoundToo = 0
            foundTmpKeyWorld = false

        } else if (node.finish == 2) {
            // 找到屏蔽词临时终结点
            if (!foundTmpKeyWorld) {
                keyWordNum++
                foundTmpKeyWorld = true
            }

            hasFoundToo++ // 这里需要先增加。
            if (needReplace == 1) {
                // 如果需要替换，那么替换当前部分
                keyWordReplace(targetStr, j, hasFoundToo)
            }
            tmpNode = node
            j++
        } else {
            // 找到屏蔽词中间点
            tmpNode = node
            hasFoundToo++
            j++
        }
    }
    return keyWordNum
}

fun keyWordReplace(targetStr: CharArray, i: Int, hasFound: Int) {
    var start = i - hasFound + 1
    for (n in 0..(hasFound - 1)) {
        targetStr[start] = '*'
        start++
    }
}