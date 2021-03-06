// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface CasinosLotteryRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.CasinosLotteryRt)
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
   * 奖励
   * </pre>
   *
   * <code>optional string reward = 2;</code>
   */
  boolean hasReward();
  /**
   * <pre>
   * 奖励
   * </pre>
   *
   * <code>optional string reward = 2;</code>
   */
  java.lang.String getReward();
  /**
   * <pre>
   * 奖励
   * </pre>
   *
   * <code>optional string reward = 2;</code>
   */
  com.google.protobuf.ByteString
      getRewardBytes();

  /**
   * <pre>
   * 祝福的标签（0：没祝福 1普通 2精英)
   * </pre>
   *
   * <code>optional int32 blessType = 3;</code>
   */
  boolean hasBlessType();
  /**
   * <pre>
   * 祝福的标签（0：没祝福 1普通 2精英)
   * </pre>
   *
   * <code>optional int32 blessType = 3;</code>
   */
  int getBlessType();

  /**
   * <pre>
   * 0: 不是boss， 1：是boss
   * </pre>
   *
   * <code>optional int32 isBoss = 4;</code>
   */
  boolean hasIsBoss();
  /**
   * <pre>
   * 0: 不是boss， 1：是boss
   * </pre>
   *
   * <code>optional int32 isBoss = 4;</code>
   */
  int getIsBoss();

  /**
   * <pre>
   * boss大奖数量
   * </pre>
   *
   * <code>optional int64 giftNum = 5;</code>
   */
  boolean hasGiftNum();
  /**
   * <pre>
   * boss大奖数量
   * </pre>
   *
   * <code>optional int64 giftNum = 5;</code>
   */
  long getGiftNum();

  /**
   * <pre>
   * 攻击次数
   * </pre>
   *
   * <code>optional int32 atkCount = 6;</code>
   */
  boolean hasAtkCount();
  /**
   * <pre>
   * 攻击次数
   * </pre>
   *
   * <code>optional int32 atkCount = 6;</code>
   */
  int getAtkCount();
}
