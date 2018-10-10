// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface BuyAllianceShopRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.BuyAllianceShopRt)
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
   * 购买的物品在商店中的位置
   * </pre>
   *
   * <code>optional int32 shopAddress = 2;</code>
   */
  boolean hasShopAddress();
  /**
   * <pre>
   * 购买的物品在商店中的位置
   * </pre>
   *
   * <code>optional int32 shopAddress = 2;</code>
   */
  int getShopAddress();

  /**
   * <pre>
   *荣誉值
   * </pre>
   *
   * <code>optional int32 honor = 3;</code>
   */
  boolean hasHonor();
  /**
   * <pre>
   *荣誉值
   * </pre>
   *
   * <code>optional int32 honor = 3;</code>
   */
  int getHonor();
}