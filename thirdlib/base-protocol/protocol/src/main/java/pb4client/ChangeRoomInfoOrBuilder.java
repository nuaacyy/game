// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface ChangeRoomInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.ChangeRoomInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 聊天室id
   * </pre>
   *
   * <code>required int64 roomId = 1;</code>
   */
  boolean hasRoomId();
  /**
   * <pre>
   * 聊天室id
   * </pre>
   *
   * <code>required int64 roomId = 1;</code>
   */
  long getRoomId();

  /**
   * <pre>
   * 新名字
   * </pre>
   *
   * <code>required string newName = 2;</code>
   */
  boolean hasNewName();
  /**
   * <pre>
   * 新名字
   * </pre>
   *
   * <code>required string newName = 2;</code>
   */
  java.lang.String getNewName();
  /**
   * <pre>
   * 新名字
   * </pre>
   *
   * <code>required string newName = 2;</code>
   */
  com.google.protobuf.ByteString
      getNewNameBytes();
}
