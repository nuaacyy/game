// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface BuyDiamondShopRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.BuyDiamondShopRt)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 返回值
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  boolean hasRt();
  /**
   * <pre>
   * 返回值
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  int getRt();

  /**
   * <pre>
   * 购买的物品所在页签
   * </pre>
   *
   * <code>optional int32 bigType = 2;</code>
   */
  boolean hasBigType();
  /**
   * <pre>
   * 购买的物品所在页签
   * </pre>
   *
   * <code>optional int32 bigType = 2;</code>
   */
  int getBigType();

  /**
   * <pre>
   * 购买的物品所在位置
   * </pre>
   *
   * <code>optional int32 smallType = 3;</code>
   */
  boolean hasSmallType();
  /**
   * <pre>
   * 购买的物品所在位置
   * </pre>
   *
   * <code>optional int32 smallType = 3;</code>
   */
  int getSmallType();

  /**
   * <pre>
   * 购买的物品数量
   * </pre>
   *
   * <code>optional int32 buyNum = 4;</code>
   */
  boolean hasBuyNum();
  /**
   * <pre>
   * 购买的物品数量
   * </pre>
   *
   * <code>optional int32 buyNum = 4;</code>
   */
  int getBuyNum();
}
