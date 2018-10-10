// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface WonderInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.WonderInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required int32 protoId = 1;</code>
   */
  boolean hasProtoId();
  /**
   * <code>required int32 protoId = 1;</code>
   */
  int getProtoId();

  /**
   * <pre>
   * 1-和平 2-战争
   * </pre>
   *
   * <code>required int32 status = 2;</code>
   */
  boolean hasStatus();
  /**
   * <pre>
   * 1-和平 2-战争
   * </pre>
   *
   * <code>required int32 status = 2;</code>
   */
  int getStatus();

  /**
   * <pre>
   *占领联盟Id(未占领-0)
   * </pre>
   *
   * <code>required int64 allianceId = 3;</code>
   */
  boolean hasAllianceId();
  /**
   * <pre>
   *占领联盟Id(未占领-0)
   * </pre>
   *
   * <code>required int64 allianceId = 3;</code>
   */
  long getAllianceId();

  /**
   * <pre>
   * 争夺中 未占领 活动开始时间/争夺中 已占领 防守开始时间/和平 下次活动开始时间
   * </pre>
   *
   * <code>required int32 startTime = 4;</code>
   */
  boolean hasStartTime();
  /**
   * <pre>
   * 争夺中 未占领 活动开始时间/争夺中 已占领 防守开始时间/和平 下次活动开始时间
   * </pre>
   *
   * <code>required int32 startTime = 4;</code>
   */
  int getStartTime();

  /**
   * <pre>
   * 争夺中 未占领 活动结束时间/争夺中 已占领 防守成功时间/和平 下次活动结束时间
   * </pre>
   *
   * <code>required int32 endTime = 5;</code>
   */
  boolean hasEndTime();
  /**
   * <pre>
   * 争夺中 未占领 活动结束时间/争夺中 已占领 防守成功时间/和平 下次活动结束时间
   * </pre>
   *
   * <code>required int32 endTime = 5;</code>
   */
  int getEndTime();

  /**
   * <code>optional .client2server.PlayerNameInfo player = 6;</code>
   */
  boolean hasPlayer();
  /**
   * <code>optional .client2server.PlayerNameInfo player = 6;</code>
   */
  pb4client.PlayerNameInfo getPlayer();
  /**
   * <code>optional .client2server.PlayerNameInfo player = 6;</code>
   */
  pb4client.PlayerNameInfoOrBuilder getPlayerOrBuilder();
}