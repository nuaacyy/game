// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface SoliderInfoForReportOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.SoliderInfoForReport)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *配置ID
   * </pre>
   *
   * <code>required int32 protoId = 1;</code>
   */
  boolean hasProtoId();
  /**
   * <pre>
   *配置ID
   * </pre>
   *
   * <code>required int32 protoId = 1;</code>
   */
  int getProtoId();

  /**
   * <pre>
   *总兵力
   * </pre>
   *
   * <code>required int32 totalNum = 2;</code>
   */
  boolean hasTotalNum();
  /**
   * <pre>
   *总兵力
   * </pre>
   *
   * <code>required int32 totalNum = 2;</code>
   */
  int getTotalNum();

  /**
   * <pre>
   *伤兵量
   * </pre>
   *
   * <code>required int32 woundedNum = 3;</code>
   */
  boolean hasWoundedNum();
  /**
   * <pre>
   *伤兵量
   * </pre>
   *
   * <code>required int32 woundedNum = 3;</code>
   */
  int getWoundedNum();

  /**
   * <pre>
   *死兵量
   * </pre>
   *
   * <code>required int32 diedNum = 4;</code>
   */
  boolean hasDiedNum();
  /**
   * <pre>
   *死兵量
   * </pre>
   *
   * <code>required int32 diedNum = 4;</code>
   */
  int getDiedNum();
}
