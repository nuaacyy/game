// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface EnterGameOfflineOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.EnterGameOffline)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *更新上下线信息(1:私聊/群组 2:好友)
   * </pre>
   *
   * <code>required int32 type = 1;</code>
   */
  boolean hasType();
  /**
   * <pre>
   *更新上下线信息(1:私聊/群组 2:好友)
   * </pre>
   *
   * <code>required int32 type = 1;</code>
   */
  int getType();

  /**
   * <code>required int64 roomId = 2;</code>
   */
  boolean hasRoomId();
  /**
   * <code>required int64 roomId = 2;</code>
   */
  long getRoomId();

  /**
   * <code>required int64 playerId = 3;</code>
   */
  boolean hasPlayerId();
  /**
   * <code>required int64 playerId = 3;</code>
   */
  long getPlayerId();

  /**
   * <code>required int32 isOnline = 4;</code>
   */
  boolean hasIsOnline();
  /**
   * <code>required int32 isOnline = 4;</code>
   */
  int getIsOnline();
}
