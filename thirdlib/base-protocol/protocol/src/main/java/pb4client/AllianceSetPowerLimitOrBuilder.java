// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface AllianceSetPowerLimitOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.AllianceSetPowerLimit)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *设置允许申请联盟的战力
   * </pre>
   *
   * <code>required int64 powerLimit = 1;</code>
   */
  boolean hasPowerLimit();
  /**
   * <pre>
   *设置允许申请联盟的战力
   * </pre>
   *
   * <code>required int64 powerLimit = 1;</code>
   */
  long getPowerLimit();

  /**
   * <pre>
   *设置可自动加入的战力
   * </pre>
   *
   * <code>required int64 canAddPower = 2;</code>
   */
  boolean hasCanAddPower();
  /**
   * <pre>
   *设置可自动加入的战力
   * </pre>
   *
   * <code>required int64 canAddPower = 2;</code>
   */
  long getCanAddPower();
}