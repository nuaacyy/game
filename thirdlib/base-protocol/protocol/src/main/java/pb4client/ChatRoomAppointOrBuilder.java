// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface ChatRoomAppointOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.ChatRoomAppoint)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 转让的群id
   * </pre>
   *
   * <code>required int64 roomId = 1;</code>
   */
  boolean hasRoomId();
  /**
   * <pre>
   * 转让的群id
   * </pre>
   *
   * <code>required int64 roomId = 1;</code>
   */
  long getRoomId();

  /**
   * <pre>
   * 新群主id
   * </pre>
   *
   * <code>required int64 playerId = 2;</code>
   */
  boolean hasPlayerId();
  /**
   * <pre>
   * 新群主id
   * </pre>
   *
   * <code>required int64 playerId = 2;</code>
   */
  long getPlayerId();
}
