// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface LeaveChatRoomOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.LeaveChatRoom)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *离开当前聊天室关注的房间(若无则为0)
   * </pre>
   *
   * <code>required int64 roomId = 1;</code>
   */
  boolean hasRoomId();
  /**
   * <pre>
   *离开当前聊天室关注的房间(若无则为0)
   * </pre>
   *
   * <code>required int64 roomId = 1;</code>
   */
  long getRoomId();
}
