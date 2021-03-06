// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface CallBossCellOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.CallBossCell)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 玩家名
   * </pre>
   *
   * <code>optional string playerName = 1;</code>
   */
  boolean hasPlayerName();
  /**
   * <pre>
   * 玩家名
   * </pre>
   *
   * <code>optional string playerName = 1;</code>
   */
  java.lang.String getPlayerName();
  /**
   * <pre>
   * 玩家名
   * </pre>
   *
   * <code>optional string playerName = 1;</code>
   */
  com.google.protobuf.ByteString
      getPlayerNameBytes();

  /**
   * <pre>
   * 联盟简称 
   * </pre>
   *
   * <code>optional string allianceShortName = 2;</code>
   */
  boolean hasAllianceShortName();
  /**
   * <pre>
   * 联盟简称 
   * </pre>
   *
   * <code>optional string allianceShortName = 2;</code>
   */
  java.lang.String getAllianceShortName();
  /**
   * <pre>
   * 联盟简称 
   * </pre>
   *
   * <code>optional string allianceShortName = 2;</code>
   */
  com.google.protobuf.ByteString
      getAllianceShortNameBytes();

  /**
   * <pre>
   * 如果类型是魔物的话,这个是monster表的id字段,模版与等级都可以取到
   * </pre>
   *
   * <code>required int32 bossId = 8;</code>
   */
  boolean hasBossId();
  /**
   * <pre>
   * 如果类型是魔物的话,这个是monster表的id字段,模版与等级都可以取到
   * </pre>
   *
   * <code>required int32 bossId = 8;</code>
   */
  int getBossId();

  /**
   * <pre>
   * 血量	(合并allianceBossHp字段,根据BossId计算血量，再计算比例)
   * </pre>
   *
   * <code>required int32 bossHp = 9;</code>
   */
  boolean hasBossHp();
  /**
   * <pre>
   * 血量	(合并allianceBossHp字段,根据BossId计算血量，再计算比例)
   * </pre>
   *
   * <code>required int32 bossHp = 9;</code>
   */
  int getBossHp();

  /**
   * <pre>
   * 召唤类魔物消失时间（取代allianceOverTime）
   * </pre>
   *
   * <code>required int32 disappearTime = 10;</code>
   */
  boolean hasDisappearTime();
  /**
   * <pre>
   * 召唤类魔物消失时间（取代allianceOverTime）
   * </pre>
   *
   * <code>required int32 disappearTime = 10;</code>
   */
  int getDisappearTime();

  /**
   * <pre>
   * 魔物解锁时间
   * </pre>
   *
   * <code>required int32 unlockTime = 11;</code>
   */
  boolean hasUnlockTime();
  /**
   * <pre>
   * 魔物解锁时间
   * </pre>
   *
   * <code>required int32 unlockTime = 11;</code>
   */
  int getUnlockTime();

  /**
   * <pre>
   * 协助玩家数量
   * </pre>
   *
   * <code>required int32 helpMemberCount = 12;</code>
   */
  boolean hasHelpMemberCount();
  /**
   * <pre>
   * 协助玩家数量
   * </pre>
   *
   * <code>required int32 helpMemberCount = 12;</code>
   */
  int getHelpMemberCount();

  /**
   * <pre>
   * 玩家头像
   * </pre>
   *
   * <code>optional int32 playerPhotoId = 13;</code>
   */
  boolean hasPlayerPhotoId();
  /**
   * <pre>
   * 玩家头像
   * </pre>
   *
   * <code>optional int32 playerPhotoId = 13;</code>
   */
  int getPlayerPhotoId();
}
