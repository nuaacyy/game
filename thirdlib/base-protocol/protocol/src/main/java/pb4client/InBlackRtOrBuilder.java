// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface InBlackRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.InBlackRt)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required int32 rt = 1;</code>
   */
  boolean hasRt();
  /**
   * <code>required int32 rt = 1;</code>
   */
  int getRt();

  /**
   * <pre>
   * 黑名单信息
   * </pre>
   *
   * <code>optional .client2server.FriendInfo blackPlayerInfo = 2;</code>
   */
  boolean hasBlackPlayerInfo();
  /**
   * <pre>
   * 黑名单信息
   * </pre>
   *
   * <code>optional .client2server.FriendInfo blackPlayerInfo = 2;</code>
   */
  pb4client.FriendInfo getBlackPlayerInfo();
  /**
   * <pre>
   * 黑名单信息
   * </pre>
   *
   * <code>optional .client2server.FriendInfo blackPlayerInfo = 2;</code>
   */
  pb4client.FriendInfoOrBuilder getBlackPlayerInfoOrBuilder();
}
