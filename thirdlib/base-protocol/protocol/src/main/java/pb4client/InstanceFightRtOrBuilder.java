// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface InstanceFightRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.InstanceFightRt)
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
   * 要挑战的层数
   * </pre>
   *
   * <code>optional int32 floorId = 2;</code>
   */
  boolean hasFloorId();
  /**
   * <pre>
   * 要挑战的层数
   * </pre>
   *
   * <code>optional int32 floorId = 2;</code>
   */
  int getFloorId();

  /**
   * <pre>
   * 挑战结果
   * </pre>
   *
   * <code>optional int32 fightResult = 3;</code>
   */
  boolean hasFightResult();
  /**
   * <pre>
   * 挑战结果
   * </pre>
   *
   * <code>optional int32 fightResult = 3;</code>
   */
  int getFightResult();

  /**
   * <pre>
   * 满足的条件
   * </pre>
   *
   * <code>repeated int32 meetCondition = 4;</code>
   */
  java.util.List<java.lang.Integer> getMeetConditionList();
  /**
   * <pre>
   * 满足的条件
   * </pre>
   *
   * <code>repeated int32 meetCondition = 4;</code>
   */
  int getMeetConditionCount();
  /**
   * <pre>
   * 满足的条件
   * </pre>
   *
   * <code>repeated int32 meetCondition = 4;</code>
   */
  int getMeetCondition(int index);

  /**
   * <pre>
   *奖励信息
   * </pre>
   *
   * <code>optional string reward = 6;</code>
   */
  boolean hasReward();
  /**
   * <pre>
   *奖励信息
   * </pre>
   *
   * <code>optional string reward = 6;</code>
   */
  java.lang.String getReward();
  /**
   * <pre>
   *奖励信息
   * </pre>
   *
   * <code>optional string reward = 6;</code>
   */
  com.google.protobuf.ByteString
      getRewardBytes();

  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroInfoForReport heroInfos = 7;</code>
   */
  java.util.List<pb4client.HeroInfoForReport> 
      getHeroInfosList();
  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroInfoForReport heroInfos = 7;</code>
   */
  pb4client.HeroInfoForReport getHeroInfos(int index);
  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroInfoForReport heroInfos = 7;</code>
   */
  int getHeroInfosCount();
  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroInfoForReport heroInfos = 7;</code>
   */
  java.util.List<? extends pb4client.HeroInfoForReportOrBuilder> 
      getHeroInfosOrBuilderList();
  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroInfoForReport heroInfos = 7;</code>
   */
  pb4client.HeroInfoForReportOrBuilder getHeroInfosOrBuilder(
      int index);
}
