// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface FbForceGridInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.FbForceGridInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *部队编号:从左往右依次是1、2、3、4、5
   * </pre>
   *
   * <code>required int32 forceNo = 1;</code>
   */
  boolean hasForceNo();
  /**
   * <pre>
   *部队编号:从左往右依次是1、2、3、4、5
   * </pre>
   *
   * <code>required int32 forceNo = 1;</code>
   */
  int getForceNo();

  /**
   * <pre>
   *格子编号:1~大营;2-中军;3-前锋
   * </pre>
   *
   * <code>required int32 gridNo = 2;</code>
   */
  boolean hasGridNo();
  /**
   * <pre>
   *格子编号:1~大营;2-中军;3-前锋
   * </pre>
   *
   * <code>required int32 gridNo = 2;</code>
   */
  int getGridNo();

  /**
   * <pre>
   *武将ID
   * </pre>
   *
   * <code>required int64 heroId = 3;</code>
   */
  boolean hasHeroId();
  /**
   * <pre>
   *武将ID
   * </pre>
   *
   * <code>required int64 heroId = 3;</code>
   */
  long getHeroId();

  /**
   * <pre>
   *剩余兵力
   * </pre>
   *
   * <code>required int32 troops = 4;</code>
   */
  boolean hasTroops();
  /**
   * <pre>
   *剩余兵力
   * </pre>
   *
   * <code>required int32 troops = 4;</code>
   */
  int getTroops();

  /**
   * <pre>
   *重伤结束时间
   * </pre>
   *
   * <code>required int32 hurtEndTime = 5;</code>
   */
  boolean hasHurtEndTime();
  /**
   * <pre>
   *重伤结束时间
   * </pre>
   *
   * <code>required int32 hurtEndTime = 5;</code>
   */
  int getHurtEndTime();
}