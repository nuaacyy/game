// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface MerchantShipLockOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.MerchantShipLock)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 兑换项的记录ID
   * </pre>
   *
   * <code>required int32 exchangeId = 1;</code>
   */
  boolean hasExchangeId();
  /**
   * <pre>
   * 兑换项的记录ID
   * </pre>
   *
   * <code>required int32 exchangeId = 1;</code>
   */
  int getExchangeId();

  /**
   * <pre>
   * 1-锁  2-解锁
   * </pre>
   *
   * <code>required int32 lockType = 2;</code>
   */
  boolean hasLockType();
  /**
   * <pre>
   * 1-锁  2-解锁
   * </pre>
   *
   * <code>required int32 lockType = 2;</code>
   */
  int getLockType();
}
