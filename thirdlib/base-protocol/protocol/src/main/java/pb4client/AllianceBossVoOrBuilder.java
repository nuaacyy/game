// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface AllianceBossVoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.AllianceBossVo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required int32 bossId = 1;</code>
   */
  boolean hasBossId();
  /**
   * <code>required int32 bossId = 1;</code>
   */
  int getBossId();

  /**
   * <pre>
   * 服务器编号
   * </pre>
   *
   * <code>required int32 areaNo = 2;</code>
   */
  boolean hasAreaNo();
  /**
   * <pre>
   * 服务器编号
   * </pre>
   *
   * <code>required int32 areaNo = 2;</code>
   */
  int getAreaNo();

  /**
   * <code>required int32 x = 3;</code>
   */
  boolean hasX();
  /**
   * <code>required int32 x = 3;</code>
   */
  int getX();

  /**
   * <code>required int32 y = 4;</code>
   */
  boolean hasY();
  /**
   * <code>required int32 y = 4;</code>
   */
  int getY();

  /**
   * <pre>
   * 结束时间
   * </pre>
   *
   * <code>required int32 overTime = 5;</code>
   */
  boolean hasOverTime();
  /**
   * <pre>
   * 结束时间
   * </pre>
   *
   * <code>required int32 overTime = 5;</code>
   */
  int getOverTime();
}