// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface NewMessageNumOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.NewMessageNum)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 聊天室id
   * </pre>
   *
   * <code>required int32 chatRoomId = 1;</code>
   */
  boolean hasChatRoomId();
  /**
   * <pre>
   * 聊天室id
   * </pre>
   *
   * <code>required int32 chatRoomId = 1;</code>
   */
  int getChatRoomId();

  /**
   * <pre>
   * 聊天内容
   * </pre>
   *
   * <code>required string message = 2;</code>
   */
  boolean hasMessage();
  /**
   * <pre>
   * 聊天内容
   * </pre>
   *
   * <code>required string message = 2;</code>
   */
  java.lang.String getMessage();
  /**
   * <pre>
   * 聊天内容
   * </pre>
   *
   * <code>required string message = 2;</code>
   */
  com.google.protobuf.ByteString
      getMessageBytes();
}
