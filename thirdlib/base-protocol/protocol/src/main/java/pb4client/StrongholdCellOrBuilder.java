// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface StrongholdCellOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.StrongholdCell)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *玩家名称信息
   * </pre>
   *
   * <code>optional .client2server.PlayerNameInfo info = 1;</code>
   */
  boolean hasInfo();
  /**
   * <pre>
   *玩家名称信息
   * </pre>
   *
   * <code>optional .client2server.PlayerNameInfo info = 1;</code>
   */
  pb4client.PlayerNameInfo getInfo();
  /**
   * <pre>
   *玩家名称信息
   * </pre>
   *
   * <code>optional .client2server.PlayerNameInfo info = 1;</code>
   */
  pb4client.PlayerNameInfoOrBuilder getInfoOrBuilder();

  /**
   * <pre>
   *行军组Id
   * </pre>
   *
   * <code>optional int64 groupId = 2;</code>
   */
  boolean hasGroupId();
  /**
   * <pre>
   *行军组Id
   * </pre>
   *
   * <code>optional int64 groupId = 2;</code>
   */
  long getGroupId();

  /**
   * <pre>
   *总上供次数
   * </pre>
   *
   * <code>required int32 totalGiveTributeCount = 3;</code>
   */
  boolean hasTotalGiveTributeCount();
  /**
   * <pre>
   *总上供次数
   * </pre>
   *
   * <code>required int32 totalGiveTributeCount = 3;</code>
   */
  int getTotalGiveTributeCount();

  /**
   * <pre>
   *剩余上供次数
   * </pre>
   *
   * <code>required int32 leftGiveTributeCount = 4;</code>
   */
  boolean hasLeftGiveTributeCount();
  /**
   * <pre>
   *剩余上供次数
   * </pre>
   *
   * <code>required int32 leftGiveTributeCount = 4;</code>
   */
  int getLeftGiveTributeCount();

  /**
   * <pre>
   *上供时间
   * </pre>
   *
   * <code>required int32 giveTributeTime = 5;</code>
   */
  boolean hasGiveTributeTime();
  /**
   * <pre>
   *上供时间
   * </pre>
   *
   * <code>required int32 giveTributeTime = 5;</code>
   */
  int getGiveTributeTime();

  /**
   * <pre>
   *已等待的上供时间
   * </pre>
   *
   * <code>required int32 waitTributeTime = 6;</code>
   */
  boolean hasWaitTributeTime();
  /**
   * <pre>
   *已等待的上供时间
   * </pre>
   *
   * <code>required int32 waitTributeTime = 6;</code>
   */
  int getWaitTributeTime();

  /**
   * <pre>
   *随机奖励
   * </pre>
   *
   * <code>required string randReward = 7;</code>
   */
  boolean hasRandReward();
  /**
   * <pre>
   *随机奖励
   * </pre>
   *
   * <code>required string randReward = 7;</code>
   */
  java.lang.String getRandReward();
  /**
   * <pre>
   *随机奖励
   * </pre>
   *
   * <code>required string randReward = 7;</code>
   */
  com.google.protobuf.ByteString
      getRandRewardBytes();

  /**
   * <pre>
   *据点是否有异宝 0- 木有 1-有
   * </pre>
   *
   * <code>required int32 haveTreasure = 8;</code>
   */
  boolean hasHaveTreasure();
  /**
   * <pre>
   *据点是否有异宝 0- 木有 1-有
   * </pre>
   *
   * <code>required int32 haveTreasure = 8;</code>
   */
  int getHaveTreasure();

  /**
   * <pre>
   *异宝奖励
   * </pre>
   *
   * <code>optional string treasureReward = 9;</code>
   */
  boolean hasTreasureReward();
  /**
   * <pre>
   *异宝奖励
   * </pre>
   *
   * <code>optional string treasureReward = 9;</code>
   */
  java.lang.String getTreasureReward();
  /**
   * <pre>
   *异宝奖励
   * </pre>
   *
   * <code>optional string treasureReward = 9;</code>
   */
  com.google.protobuf.ByteString
      getTreasureRewardBytes();

  /**
   * <pre>
   *npc守军阵容Id
   * </pre>
   *
   * <code>optional int32 lineupId = 10;</code>
   */
  boolean hasLineupId();
  /**
   * <pre>
   *npc守军阵容Id
   * </pre>
   *
   * <code>optional int32 lineupId = 10;</code>
   */
  int getLineupId();
}