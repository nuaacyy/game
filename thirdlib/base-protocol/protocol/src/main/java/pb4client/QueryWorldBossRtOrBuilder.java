// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface QueryWorldBossRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.QueryWorldBossRt)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 返回值
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  boolean hasRt();
  /**
   * <pre>
   * 返回值
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  int getRt();

  /**
   * <code>optional int32 bossId = 2;</code>
   */
  boolean hasBossId();
  /**
   * <code>optional int32 bossId = 2;</code>
   */
  int getBossId();

  /**
   * <code>optional int32 x = 3;</code>
   */
  boolean hasX();
  /**
   * <code>optional int32 x = 3;</code>
   */
  int getX();

  /**
   * <code>optional int32 y = 4;</code>
   */
  boolean hasY();
  /**
   * <code>optional int32 y = 4;</code>
   */
  int getY();

  /**
   * <code>optional int32 nowHp = 5;</code>
   */
  boolean hasNowHp();
  /**
   * <code>optional int32 nowHp = 5;</code>
   */
  int getNowHp();

  /**
   * <code>optional int32 overTime = 6;</code>
   */
  boolean hasOverTime();
  /**
   * <code>optional int32 overTime = 6;</code>
   */
  int getOverTime();

  /**
   * <code>optional int32 refTime = 7;</code>
   */
  boolean hasRefTime();
  /**
   * <code>optional int32 refTime = 7;</code>
   */
  int getRefTime();

  /**
   * <pre>
   * </pre>
   *
   * <code>repeated .client2server.WorldBossRank ranks = 8;</code>
   */
  java.util.List<pb4client.WorldBossRank> 
      getRanksList();
  /**
   * <pre>
   * </pre>
   *
   * <code>repeated .client2server.WorldBossRank ranks = 8;</code>
   */
  pb4client.WorldBossRank getRanks(int index);
  /**
   * <pre>
   * </pre>
   *
   * <code>repeated .client2server.WorldBossRank ranks = 8;</code>
   */
  int getRanksCount();
  /**
   * <pre>
   * </pre>
   *
   * <code>repeated .client2server.WorldBossRank ranks = 8;</code>
   */
  java.util.List<? extends pb4client.WorldBossRankOrBuilder> 
      getRanksOrBuilderList();
  /**
   * <pre>
   * </pre>
   *
   * <code>repeated .client2server.WorldBossRank ranks = 8;</code>
   */
  pb4client.WorldBossRankOrBuilder getRanksOrBuilder(
      int index);
}