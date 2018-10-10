// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface InstanceWipeRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.InstanceWipeRt)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required int32 rt = 1;</code>
   */
  boolean hasRt();
  /**
   * <code>required int32 rt = 1;</code>
   */
  int getRt();

  /**
   * <pre>
   * 要扫荡的层数
   * </pre>
   *
   * <code>optional int32 floorId = 2;</code>
   */
  boolean hasFloorId();
  /**
   * <pre>
   * 要扫荡的层数
   * </pre>
   *
   * <code>optional int32 floorId = 2;</code>
   */
  int getFloorId();

  /**
   * <pre>
   * 要扫荡的次数
   * </pre>
   *
   * <code>optional int32 wipeNum = 3;</code>
   */
  boolean hasWipeNum();
  /**
   * <pre>
   * 要扫荡的次数
   * </pre>
   *
   * <code>optional int32 wipeNum = 3;</code>
   */
  int getWipeNum();

  /**
   * <pre>
   * 扫荡奖励
   * </pre>
   *
   * <code>repeated string instanceWipeRewards = 4;</code>
   */
  java.util.List<java.lang.String>
      getInstanceWipeRewardsList();
  /**
   * <pre>
   * 扫荡奖励
   * </pre>
   *
   * <code>repeated string instanceWipeRewards = 4;</code>
   */
  int getInstanceWipeRewardsCount();
  /**
   * <pre>
   * 扫荡奖励
   * </pre>
   *
   * <code>repeated string instanceWipeRewards = 4;</code>
   */
  java.lang.String getInstanceWipeRewards(int index);
  /**
   * <pre>
   * 扫荡奖励
   * </pre>
   *
   * <code>repeated string instanceWipeRewards = 4;</code>
   */
  com.google.protobuf.ByteString
      getInstanceWipeRewardsBytes(int index);
}