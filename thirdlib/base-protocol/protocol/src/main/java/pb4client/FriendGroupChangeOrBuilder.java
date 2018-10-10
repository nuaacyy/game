// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface FriendGroupChangeOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.FriendGroupChange)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 1: 新增，2：修改，3：删除
   * </pre>
   *
   * <code>required int32 type = 1;</code>
   */
  boolean hasType();
  /**
   * <pre>
   * 1: 新增，2：修改，3：删除
   * </pre>
   *
   * <code>required int32 type = 1;</code>
   */
  int getType();

  /**
   * <pre>
   * 群组信息
   * </pre>
   *
   * <code>required .client2server.GroupInfo groupInfo = 2;</code>
   */
  boolean hasGroupInfo();
  /**
   * <pre>
   * 群组信息
   * </pre>
   *
   * <code>required .client2server.GroupInfo groupInfo = 2;</code>
   */
  pb4client.GroupInfo getGroupInfo();
  /**
   * <pre>
   * 群组信息
   * </pre>
   *
   * <code>required .client2server.GroupInfo groupInfo = 2;</code>
   */
  pb4client.GroupInfoOrBuilder getGroupInfoOrBuilder();
}