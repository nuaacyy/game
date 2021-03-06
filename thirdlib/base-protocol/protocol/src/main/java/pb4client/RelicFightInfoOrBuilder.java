// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface RelicFightInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.RelicFightInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 遗迹战斗信息ID
   * </pre>
   *
   * <code>required int64 relicFightInfoId = 1;</code>
   */
  boolean hasRelicFightInfoId();
  /**
   * <pre>
   * 遗迹战斗信息ID
   * </pre>
   *
   * <code>required int64 relicFightInfoId = 1;</code>
   */
  long getRelicFightInfoId();

  /**
   * <pre>
   * 帮派名
   * </pre>
   *
   * <code>required string allianceName = 2;</code>
   */
  boolean hasAllianceName();
  /**
   * <pre>
   * 帮派名
   * </pre>
   *
   * <code>required string allianceName = 2;</code>
   */
  java.lang.String getAllianceName();
  /**
   * <pre>
   * 帮派名
   * </pre>
   *
   * <code>required string allianceName = 2;</code>
   */
  com.google.protobuf.ByteString
      getAllianceNameBytes();

  /**
   * <pre>
   * 团长名
   * </pre>
   *
   * <code>required string playerName = 3;</code>
   */
  boolean hasPlayerName();
  /**
   * <pre>
   * 团长名
   * </pre>
   *
   * <code>required string playerName = 3;</code>
   */
  java.lang.String getPlayerName();
  /**
   * <pre>
   * 团长名
   * </pre>
   *
   * <code>required string playerName = 3;</code>
   */
  com.google.protobuf.ByteString
      getPlayerNameBytes();

  /**
   * <pre>
   * 团长炼金者协会建筑等级
   * </pre>
   *
   * <code>required int32 playerBuildLv = 4;</code>
   */
  boolean hasPlayerBuildLv();
  /**
   * <pre>
   * 团长炼金者协会建筑等级
   * </pre>
   *
   * <code>required int32 playerBuildLv = 4;</code>
   */
  int getPlayerBuildLv();

  /**
   * <pre>
   * 遗迹等级
   * </pre>
   *
   * <code>required int32 relicLv = 5;</code>
   */
  boolean hasRelicLv();
  /**
   * <pre>
   * 遗迹等级
   * </pre>
   *
   * <code>required int32 relicLv = 5;</code>
   */
  int getRelicLv();

  /**
   * <pre>
   * 遗迹坐标
   * </pre>
   *
   * <code>required int32 x = 6;</code>
   */
  boolean hasX();
  /**
   * <pre>
   * 遗迹坐标
   * </pre>
   *
   * <code>required int32 x = 6;</code>
   */
  int getX();

  /**
   * <pre>
   * 遗迹坐标
   * </pre>
   *
   * <code>required int32 y = 7;</code>
   */
  boolean hasY();
  /**
   * <pre>
   * 遗迹坐标
   * </pre>
   *
   * <code>required int32 y = 7;</code>
   */
  int getY();

  /**
   * <pre>
   * 战斗结果
   * </pre>
   *
   * <code>required int32 fightResult = 8;</code>
   */
  boolean hasFightResult();
  /**
   * <pre>
   * 战斗结果
   * </pre>
   *
   * <code>required int32 fightResult = 8;</code>
   */
  int getFightResult();

  /**
   * <pre>
   * 参与人数
   * </pre>
   *
   * <code>required int32 joinPlayerNum = 9;</code>
   */
  boolean hasJoinPlayerNum();
  /**
   * <pre>
   * 参与人数
   * </pre>
   *
   * <code>required int32 joinPlayerNum = 9;</code>
   */
  int getJoinPlayerNum();

  /**
   * <pre>
   * 战斗时间
   * </pre>
   *
   * <code>required int32 fightTime = 10;</code>
   */
  boolean hasFightTime();
  /**
   * <pre>
   * 战斗时间
   * </pre>
   *
   * <code>required int32 fightTime = 10;</code>
   */
  int getFightTime();
}
