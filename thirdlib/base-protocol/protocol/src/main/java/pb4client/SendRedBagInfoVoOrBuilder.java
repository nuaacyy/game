// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface SendRedBagInfoVoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.SendRedBagInfoVo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 1-普通红包  2-现金红包
   * </pre>
   *
   * <code>required int32 redBagType = 1;</code>
   */
  boolean hasRedBagType();
  /**
   * <pre>
   * 1-普通红包  2-现金红包
   * </pre>
   *
   * <code>required int32 redBagType = 1;</code>
   */
  int getRedBagType();

  /**
   * <pre>
   * 一共塞了多少钱
   * </pre>
   *
   * <code>required int64 redBagAllMoney = 2;</code>
   */
  boolean hasRedBagAllMoney();
  /**
   * <pre>
   * 一共塞了多少钱
   * </pre>
   *
   * <code>required int64 redBagAllMoney = 2;</code>
   */
  long getRedBagAllMoney();

  /**
   * <pre>
   * 当前剩余多少钱
   * </pre>
   *
   * <code>required int64 redBagNowMoney = 3;</code>
   */
  boolean hasRedBagNowMoney();
  /**
   * <pre>
   * 当前剩余多少钱
   * </pre>
   *
   * <code>required int64 redBagNowMoney = 3;</code>
   */
  long getRedBagNowMoney();

  /**
   * <pre>
   * 一共多少个红包
   * </pre>
   *
   * <code>required int32 redBagAllNum = 4;</code>
   */
  boolean hasRedBagAllNum();
  /**
   * <pre>
   * 一共多少个红包
   * </pre>
   *
   * <code>required int32 redBagAllNum = 4;</code>
   */
  int getRedBagAllNum();

  /**
   * <pre>
   * 当前剩余多少个未领取
   * </pre>
   *
   * <code>required int32 redBagNowNum = 5;</code>
   */
  boolean hasRedBagNowNum();
  /**
   * <pre>
   * 当前剩余多少个未领取
   * </pre>
   *
   * <code>required int32 redBagNowNum = 5;</code>
   */
  int getRedBagNowNum();

  /**
   * <pre>
   * 发出去的时间
   * </pre>
   *
   * <code>required int32 sendTime = 6;</code>
   */
  boolean hasSendTime();
  /**
   * <pre>
   * 发出去的时间
   * </pre>
   *
   * <code>required int32 sendTime = 6;</code>
   */
  int getSendTime();

  /**
   * <pre>
   * 过期时间 
   * </pre>
   *
   * <code>required int32 overTime = 7;</code>
   */
  boolean hasOverTime();
  /**
   * <pre>
   * 过期时间 
   * </pre>
   *
   * <code>required int32 overTime = 7;</code>
   */
  int getOverTime();

  /**
   * <pre>
   * 是否已经过期退还过了  0-否  1-是 
   * </pre>
   *
   * <code>required int32 isBack = 8;</code>
   */
  boolean hasIsBack();
  /**
   * <pre>
   * 是否已经过期退还过了  0-否  1-是 
   * </pre>
   *
   * <code>required int32 isBack = 8;</code>
   */
  int getIsBack();
}
