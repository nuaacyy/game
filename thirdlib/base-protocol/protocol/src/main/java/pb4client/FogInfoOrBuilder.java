// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface FogInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.FogInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *配置ID
   * </pre>
   *
   * <code>required int32 fogId = 1;</code>
   */
  boolean hasFogId();
  /**
   * <pre>
   *配置ID
   * </pre>
   *
   * <code>required int32 fogId = 1;</code>
   */
  int getFogId();

  /**
   * <pre>
   *迷雾状态 1、未战胜 2、已战胜未领取奖励 3、已战胜且领取奖励
   * </pre>
   *
   * <code>required int32 state = 2;</code>
   */
  boolean hasState();
  /**
   * <pre>
   *迷雾状态 1、未战胜 2、已战胜未领取奖励 3、已战胜且领取奖励
   * </pre>
   *
   * <code>required int32 state = 2;</code>
   */
  int getState();

  /**
   * <pre>
   *迷雾战斗力
   * </pre>
   *
   * <code>optional int64 power = 3;</code>
   */
  boolean hasPower();
  /**
   * <pre>
   *迷雾战斗力
   * </pre>
   *
   * <code>optional int64 power = 3;</code>
   */
  long getPower();
}
