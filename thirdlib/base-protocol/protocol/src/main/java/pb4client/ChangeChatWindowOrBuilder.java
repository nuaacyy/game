// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface ChangeChatWindowOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.ChangeChatWindow)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * new私聊id
   * </pre>
   *
   * <code>required int64 playerIdNew = 1;</code>
   */
  boolean hasPlayerIdNew();
  /**
   * <pre>
   * new私聊id
   * </pre>
   *
   * <code>required int64 playerIdNew = 1;</code>
   */
  long getPlayerIdNew();

  /**
   * <pre>
   * new聊天室id
   * </pre>
   *
   * <code>required int64 roomIdNew = 2;</code>
   */
  boolean hasRoomIdNew();
  /**
   * <pre>
   * new聊天室id
   * </pre>
   *
   * <code>required int64 roomIdNew = 2;</code>
   */
  long getRoomIdNew();
}
