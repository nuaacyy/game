// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface BuyDiamondShopOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.BuyDiamondShop)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 购买的物品所在页签
   * </pre>
   *
   * <code>required int32 bigType = 1;</code>
   */
  boolean hasBigType();
  /**
   * <pre>
   * 购买的物品所在页签
   * </pre>
   *
   * <code>required int32 bigType = 1;</code>
   */
  int getBigType();

  /**
   * <pre>
   * 购买的物品所在位置
   * </pre>
   *
   * <code>required int32 smallType = 2;</code>
   */
  boolean hasSmallType();
  /**
   * <pre>
   * 购买的物品所在位置
   * </pre>
   *
   * <code>required int32 smallType = 2;</code>
   */
  int getSmallType();

  /**
   * <pre>
   * 购买的物品数量
   * </pre>
   *
   * <code>required int32 buyNum = 3;</code>
   */
  boolean hasBuyNum();
  /**
   * <pre>
   * 购买的物品数量
   * </pre>
   *
   * <code>required int32 buyNum = 3;</code>
   */
  int getBuyNum();
}