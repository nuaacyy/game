package genenvelope

import com.google.protobuf.DescriptorProtos
import java.io.File
import java.io.FileInputStream

fun main(args: Array<String>) {
    getExtendInfo("envelope.desc")
}

@Throws(Exception::class)
fun getExtendInfo(extendDescFile: String) {
    val fdSet = DescriptorProtos.FileDescriptorSet.parseFrom(FileInputStream(extendDescFile))

    var c2sMsg = ""
    var s2cMsg = ""
    for (fileDescriptorProto in fdSet.getFileList()) {
        for (location in fileDescriptorProto.sourceCodeInfo.locationList) {
            if (location.pathCount == 2 && location.getPath(0) == 4) {
                // 消息
                val messageIndex = location.getPath(1)
                val message = fileDescriptorProto.getMessageType(messageIndex)
                var msgType = 0
                if (location.leadingComments.contains("msgType")) {
                    val comments = location.leadingComments.split("\n")
                    for (comment in comments) {
                        if (comment.contains("msgType")) {
                            val msgValues = comment.replace(" ", "").split("=")
                            msgType = msgValues[1].toInt()
                        }
                    }

                    val nameValue = message.name.substring(0, 1).toLowerCase() + message.name.substring(1);
                    c2sMsg += "\t\t${message.name} ${nameValue} = $msgType;\n"
                    if (msgType < 3000) {
                        s2cMsg += "\t\t${message.name}Rt ${nameValue}Rt = $msgType;\n"
                    }
                }
            }
        }
    }

    gen(c2sMsg, s2cMsg)
}

fun gen(c2sMsg: String, s2cMsg: String) {
    val clientMsgTemplet = """
package client2server;

import "client2server.proto";

option java_package = "pb4server";
option java_outer_classname = "EnvelopePkt";
option java_multiple_files = true;

message CSMsg {
    oneof payload {
${c2sMsg}
    }
}

message SCMsg {
    oneof payload {
${s2cMsg}
    }
}

message ProtoPlayerEnvelope {
    required int32 msgType = 1;
    required int64 playerId = 2;
    required int32 clientMsgNo = 3;
    required CSMsg payload = 4;
}

message ProtoWorldEnvelope {
    required int32 msgType = 1;
    required int64 playerId = 2;
    required int64 worldId = 3;
    required int32 clientMsgNo = 4;
    required CSMsg payload = 5;
}

""".trimIndent()

    //println(clientMsgTemplet)
    val filePath = "src/main/proto/envelope.proto"
    val file = File(filePath)
    file.writeText(clientMsgTemplet)
}
