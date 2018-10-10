// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface AllianceActivityRankVoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.AllianceActivityRankVo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 联盟Id
   * </pre>
   *
   * <code>required int64 allianceId = 1;</code>
   */
  boolean hasAllianceId();
  /**
   * <pre>
   * 联盟Id
   * </pre>
   *
   * <code>required int64 allianceId = 1;</code>
   */
  long getAllianceId();

  /**
   * <pre>
   * 联盟名
   * </pre>
   *
   * <code>required string allianceName = 2;</code>
   */
  boolean hasAllianceName();
  /**
   * <pre>
   * 联盟名
   * </pre>
   *
   * <code>required string allianceName = 2;</code>
   */
  java.lang.String getAllianceName();
  /**
   * <pre>
   * 联盟名
   * </pre>
   *
   * <code>required string allianceName = 2;</code>
   */
  com.google.protobuf.ByteString
      getAllianceNameBytes();

  /**
   * <pre>
   * 联盟简称
   * </pre>
   *
   * <code>required string shortName = 3;</code>
   */
  boolean hasShortName();
  /**
   * <pre>
   * 联盟简称
   * </pre>
   *
   * <code>required string shortName = 3;</code>
   */
  java.lang.String getShortName();
  /**
   * <pre>
   * 联盟简称
   * </pre>
   *
   * <code>required string shortName = 3;</code>
   */
  com.google.protobuf.ByteString
      getShortNameBytes();

  /**
   * <pre>
   * 积分记录
   * </pre>
   *
   * <code>required int32 myScore = 4;</code>
   */
  boolean hasMyScore();
  /**
   * <pre>
   * 积分记录
   * </pre>
   *
   * <code>required int32 myScore = 4;</code>
   */
  int getMyScore();

  /**
   * <pre>
   *旗帜颜色
   * </pre>
   *
   * <code>required int32 flagColor = 5;</code>
   */
  boolean hasFlagColor();
  /**
   * <pre>
   *旗帜颜色
   * </pre>
   *
   * <code>required int32 flagColor = 5;</code>
   */
  int getFlagColor();

  /**
   * <pre>
   *旗帜样式
   * </pre>
   *
   * <code>required int32 flagStyle = 6;</code>
   */
  boolean hasFlagStyle();
  /**
   * <pre>
   *旗帜样式
   * </pre>
   *
   * <code>required int32 flagStyle = 6;</code>
   */
  int getFlagStyle();

  /**
   * <pre>
   *旗帜图案
   * </pre>
   *
   * <code>required int32 flagEffect = 7;</code>
   */
  boolean hasFlagEffect();
  /**
   * <pre>
   *旗帜图案
   * </pre>
   *
   * <code>required int32 flagEffect = 7;</code>
   */
  int getFlagEffect();
}
