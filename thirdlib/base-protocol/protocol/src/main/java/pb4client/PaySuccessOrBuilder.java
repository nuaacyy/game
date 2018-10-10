// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface PaySuccessOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.PaySuccess)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 支付渠道
   * </pre>
   *
   * <code>required int32 payChannel = 1;</code>
   */
  boolean hasPayChannel();
  /**
   * <pre>
   * 支付渠道
   * </pre>
   *
   * <code>required int32 payChannel = 1;</code>
   */
  int getPayChannel();

  /**
   * <pre>
   * 充值金额   --(这个会是小数,所以是*1000传来的整数,你自己除以1000了用)
   * </pre>
   *
   * <code>required int32 payNum = 2;</code>
   */
  boolean hasPayNum();
  /**
   * <pre>
   * 充值金额   --(这个会是小数,所以是*1000传来的整数,你自己除以1000了用)
   * </pre>
   *
   * <code>required int32 payNum = 2;</code>
   */
  int getPayNum();

  /**
   * <pre>
   * 充值单位
   * </pre>
   *
   * <code>required string payUnit = 3;</code>
   */
  boolean hasPayUnit();
  /**
   * <pre>
   * 充值单位
   * </pre>
   *
   * <code>required string payUnit = 3;</code>
   */
  java.lang.String getPayUnit();
  /**
   * <pre>
   * 充值单位
   * </pre>
   *
   * <code>required string payUnit = 3;</code>
   */
  com.google.protobuf.ByteString
      getPayUnitBytes();

  /**
   * <pre>
   * 充值类型
   * </pre>
   *
   * <code>required int32 payType = 4;</code>
   */
  boolean hasPayType();
  /**
   * <pre>
   * 充值类型
   * </pre>
   *
   * <code>required int32 payType = 4;</code>
   */
  int getPayType();

  /**
   * <pre>
   * 充值折扣
   * </pre>
   *
   * <code>required int32 payDiscount = 5;</code>
   */
  boolean hasPayDiscount();
  /**
   * <pre>
   * 充值折扣
   * </pre>
   *
   * <code>required int32 payDiscount = 5;</code>
   */
  int getPayDiscount();

  /**
   * <pre>
   * 道具标识
   * </pre>
   *
   * <code>required string payItem = 6;</code>
   */
  boolean hasPayItem();
  /**
   * <pre>
   * 道具标识
   * </pre>
   *
   * <code>required string payItem = 6;</code>
   */
  java.lang.String getPayItem();
  /**
   * <pre>
   * 道具标识
   * </pre>
   *
   * <code>required string payItem = 6;</code>
   */
  com.google.protobuf.ByteString
      getPayItemBytes();

  /**
   * <pre>
   * 订单标识
   * </pre>
   *
   * <code>required string payOrderId = 7;</code>
   */
  boolean hasPayOrderId();
  /**
   * <pre>
   * 订单标识
   * </pre>
   *
   * <code>required string payOrderId = 7;</code>
   */
  java.lang.String getPayOrderId();
  /**
   * <pre>
   * 订单标识
   * </pre>
   *
   * <code>required string payOrderId = 7;</code>
   */
  com.google.protobuf.ByteString
      getPayOrderIdBytes();
}