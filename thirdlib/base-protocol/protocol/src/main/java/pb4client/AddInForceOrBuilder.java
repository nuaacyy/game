// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface AddInForceOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.AddInForce)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *武将ID
   * </pre>
   *
   * <code>required int64 heroId = 1;</code>
   */
  boolean hasHeroId();
  /**
   * <pre>
   *武将ID
   * </pre>
   *
   * <code>required int64 heroId = 1;</code>
   */
  long getHeroId();

  /**
   * <pre>
   *&#47;/在部队中的什么位置  0-不在部队内 3-前锋 2-中军 1-大营
   * </pre>
   *
   * <code>required int32 inForceState = 2;</code>
   */
  boolean hasInForceState();
  /**
   * <pre>
   *&#47;/在部队中的什么位置  0-不在部队内 3-前锋 2-中军 1-大营
   * </pre>
   *
   * <code>required int32 inForceState = 2;</code>
   */
  int getInForceState();

  /**
   * <pre>
   *所属部队ID
   * </pre>
   *
   * <code>required int64 force = 3;</code>
   */
  boolean hasForce();
  /**
   * <pre>
   *所属部队ID
   * </pre>
   *
   * <code>required int64 force = 3;</code>
   */
  long getForce();

  /**
   * <pre>
   *征兵开始时间
   * </pre>
   *
   * <code>required int32 conscriptionStartTime = 4;</code>
   */
  boolean hasConscriptionStartTime();
  /**
   * <pre>
   *征兵开始时间
   * </pre>
   *
   * <code>required int32 conscriptionStartTime = 4;</code>
   */
  int getConscriptionStartTime();

  /**
   * <pre>
   *征兵结束时间
   * </pre>
   *
   * <code>required int32 conscriptionEndTime = 5;</code>
   */
  boolean hasConscriptionEndTime();
  /**
   * <pre>
   *征兵结束时间
   * </pre>
   *
   * <code>required int32 conscriptionEndTime = 5;</code>
   */
  int getConscriptionEndTime();

  /**
   * <pre>
   *征兵可得兵力
   * </pre>
   *
   * <code>required int32 getBingli = 6;</code>
   */
  boolean hasGetBingli();
  /**
   * <pre>
   *征兵可得兵力
   * </pre>
   *
   * <code>required int32 getBingli = 6;</code>
   */
  int getGetBingli();

  /**
   * <pre>
   *唯一ID
   * </pre>
   *
   * <code>required int64 geziId = 7;</code>
   */
  boolean hasGeziId();
  /**
   * <pre>
   *唯一ID
   * </pre>
   *
   * <code>required int64 geziId = 7;</code>
   */
  long getGeziId();
}