// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface PlayerRewardBagInfoChangeOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.PlayerRewardBagInfoChange)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 推送类型  1-新增  2-减少
   * </pre>
   *
   * <code>required int32 changeInfo = 1;</code>
   */
  boolean hasChangeInfo();
  /**
   * <pre>
   * 推送类型  1-新增  2-减少
   * </pre>
   *
   * <code>required int32 changeInfo = 1;</code>
   */
  int getChangeInfo();

  /**
   * <pre>
   * 信息
   * </pre>
   *
   * <code>repeated .client2server.PlayerRewardBagInfo playerRewardBagInfos = 2;</code>
   */
  java.util.List<pb4client.PlayerRewardBagInfo> 
      getPlayerRewardBagInfosList();
  /**
   * <pre>
   * 信息
   * </pre>
   *
   * <code>repeated .client2server.PlayerRewardBagInfo playerRewardBagInfos = 2;</code>
   */
  pb4client.PlayerRewardBagInfo getPlayerRewardBagInfos(int index);
  /**
   * <pre>
   * 信息
   * </pre>
   *
   * <code>repeated .client2server.PlayerRewardBagInfo playerRewardBagInfos = 2;</code>
   */
  int getPlayerRewardBagInfosCount();
  /**
   * <pre>
   * 信息
   * </pre>
   *
   * <code>repeated .client2server.PlayerRewardBagInfo playerRewardBagInfos = 2;</code>
   */
  java.util.List<? extends pb4client.PlayerRewardBagInfoOrBuilder> 
      getPlayerRewardBagInfosOrBuilderList();
  /**
   * <pre>
   * 信息
   * </pre>
   *
   * <code>repeated .client2server.PlayerRewardBagInfo playerRewardBagInfos = 2;</code>
   */
  pb4client.PlayerRewardBagInfoOrBuilder getPlayerRewardBagInfosOrBuilder(
      int index);
}