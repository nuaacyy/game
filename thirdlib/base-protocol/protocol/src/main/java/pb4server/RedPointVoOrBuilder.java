// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2world.proto

package pb4server;

public interface RedPointVoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.RedPointVo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *红点类型 1-联盟外交 2-联盟标记 3-入帮申请 4-私聊未读消息小红点 5-群聊未读消息小红点 6-联盟帮助小红点 7-联盟科技优先小红点
   * </pre>
   *
   * <code>int32 redPointType = 1;</code>
   */
  int getRedPointType();

  /**
   * <pre>
   *红点在本类型中的子目录
   * </pre>
   *
   * <code>int64 redPointId = 2;</code>
   */
  long getRedPointId();

  /**
   * <pre>
   *红点出现的时间戳
   * </pre>
   *
   * <code>int32 redPointShowTime = 3;</code>
   */
  int getRedPointShowTime();
}