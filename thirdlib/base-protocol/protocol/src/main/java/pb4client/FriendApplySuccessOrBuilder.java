// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface FriendApplySuccessOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.FriendApplySuccess)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 1：新增，2：修改，3：删除
   * </pre>
   *
   * <code>required int32 type = 1;</code>
   */
  boolean hasType();
  /**
   * <pre>
   * 1：新增，2：修改，3：删除
   * </pre>
   *
   * <code>required int32 type = 1;</code>
   */
  int getType();

  /**
   * <pre>
   * 好友信息
   * </pre>
   *
   * <code>required .client2server.FriendInfo friendInfo = 2;</code>
   */
  boolean hasFriendInfo();
  /**
   * <pre>
   * 好友信息
   * </pre>
   *
   * <code>required .client2server.FriendInfo friendInfo = 2;</code>
   */
  pb4client.FriendInfo getFriendInfo();
  /**
   * <pre>
   * 好友信息
   * </pre>
   *
   * <code>required .client2server.FriendInfo friendInfo = 2;</code>
   */
  pb4client.FriendInfoOrBuilder getFriendInfoOrBuilder();
}
