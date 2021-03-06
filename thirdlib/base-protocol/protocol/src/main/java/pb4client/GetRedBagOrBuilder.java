// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface GetRedBagOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.GetRedBag)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 红包位置 1-除了联盟频道外的频道  2-群聊私聊 3-联盟频道聊天
   * </pre>
   *
   * <code>required int32 type = 1;</code>
   */
  boolean hasType();
  /**
   * <pre>
   * 红包位置 1-除了联盟频道外的频道  2-群聊私聊 3-联盟频道聊天
   * </pre>
   *
   * <code>required int32 type = 1;</code>
   */
  int getType();

  /**
   * <pre>
   * 如果第一个字段是频道 这个字段表示频道类型  如果是群聊私聊,这里是房间ID
   * </pre>
   *
   * <code>required int64 valueId = 2;</code>
   */
  boolean hasValueId();
  /**
   * <pre>
   * 如果第一个字段是频道 这个字段表示频道类型  如果是群聊私聊,这里是房间ID
   * </pre>
   *
   * <code>required int64 valueId = 2;</code>
   */
  long getValueId();

  /**
   * <pre>
   * 红包ID
   * </pre>
   *
   * <code>required int64 redBagId = 3;</code>
   */
  boolean hasRedBagId();
  /**
   * <pre>
   * 红包ID
   * </pre>
   *
   * <code>required int64 redBagId = 3;</code>
   */
  long getRedBagId();
}
