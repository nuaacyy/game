// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface RelicBusOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.RelicBus)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 前往的遗迹XY坐标
   * </pre>
   *
   * <code>required int32 relicX = 2;</code>
   */
  boolean hasRelicX();
  /**
   * <pre>
   * 前往的遗迹XY坐标
   * </pre>
   *
   * <code>required int32 relicX = 2;</code>
   */
  int getRelicX();

  /**
   * <pre>
   * 前往的遗迹XY坐标
   * </pre>
   *
   * <code>required int32 relicY = 3;</code>
   */
  boolean hasRelicY();
  /**
   * <pre>
   * 前往的遗迹XY坐标
   * </pre>
   *
   * <code>required int32 relicY = 3;</code>
   */
  int getRelicY();

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
   * 团长集合的XY坐标
   * </pre>
   *
   * <code>required int32 startX = 5;</code>
   */
  boolean hasStartX();
  /**
   * <pre>
   * 团长集合的XY坐标
   * </pre>
   *
   * <code>required int32 startX = 5;</code>
   */
  int getStartX();

  /**
   * <pre>
   * 团长集合的XY坐标
   * </pre>
   *
   * <code>required int32 startY = 6;</code>
   */
  boolean hasStartY();
  /**
   * <pre>
   * 团长集合的XY坐标
   * </pre>
   *
   * <code>required int32 startY = 6;</code>
   */
  int getStartY();

  /**
   * <pre>
   * 开车时间
   * </pre>
   *
   * <code>required int32 goTime = 7;</code>
   */
  boolean hasGoTime();
  /**
   * <pre>
   * 开车时间
   * </pre>
   *
   * <code>required int32 goTime = 7;</code>
   */
  int getGoTime();

  /**
   * <pre>
   * 战斗成员信息
   * </pre>
   *
   * <code>required string membereInfos = 8;</code>
   */
  boolean hasMembereInfos();
  /**
   * <pre>
   * 战斗成员信息
   * </pre>
   *
   * <code>required string membereInfos = 8;</code>
   */
  java.lang.String getMembereInfos();
  /**
   * <pre>
   * 战斗成员信息
   * </pre>
   *
   * <code>required string membereInfos = 8;</code>
   */
  com.google.protobuf.ByteString
      getMembereInfosBytes();

  /**
   * <pre>
   * 本车的ID
   * </pre>
   *
   * <code>required int64 relicBusId = 9;</code>
   */
  boolean hasRelicBusId();
  /**
   * <pre>
   * 本车的ID
   * </pre>
   *
   * <code>required int64 relicBusId = 9;</code>
   */
  long getRelicBusId();

  /**
   * <pre>
   * 团长玩家ID
   * </pre>
   *
   * <code>required int64 mainPlayerId = 10;</code>
   */
  boolean hasMainPlayerId();
  /**
   * <pre>
   * 团长玩家ID
   * </pre>
   *
   * <code>required int64 mainPlayerId = 10;</code>
   */
  long getMainPlayerId();

  /**
   * <pre>
   * 遗迹等级
   * </pre>
   *
   * <code>required int32 relicLv = 11;</code>
   */
  boolean hasRelicLv();
  /**
   * <pre>
   * 遗迹等级
   * </pre>
   *
   * <code>required int32 relicLv = 11;</code>
   */
  int getRelicLv();
}
