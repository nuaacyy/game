// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface ShopInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.ShopInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *在市场中的位置
   * </pre>
   *
   * <code>required int32 shopAddress = 1;</code>
   */
  boolean hasShopAddress();
  /**
   * <pre>
   *在市场中的位置
   * </pre>
   *
   * <code>required int32 shopAddress = 1;</code>
   */
  int getShopAddress();

  /**
   * <pre>
   *商品类型  1-propsId  2-unitBaseId
   * </pre>
   *
   * <code>required int32 shopType = 2;</code>
   */
  boolean hasShopType();
  /**
   * <pre>
   *商品类型  1-propsId  2-unitBaseId
   * </pre>
   *
   * <code>required int32 shopType = 2;</code>
   */
  int getShopType();

  /**
   * <pre>
   *模板唯一ID
   * </pre>
   *
   * <code>required int32 shopProtoId = 3;</code>
   */
  boolean hasShopProtoId();
  /**
   * <pre>
   *模板唯一ID
   * </pre>
   *
   * <code>required int32 shopProtoId = 3;</code>
   */
  int getShopProtoId();

  /**
   * <pre>
   *数量
   * </pre>
   *
   * <code>required int32 shopNum = 4;</code>
   */
  boolean hasShopNum();
  /**
   * <pre>
   *数量
   * </pre>
   *
   * <code>required int32 shopNum = 4;</code>
   */
  int getShopNum();

  /**
   * <pre>
   *购买状态  0-未购买  1-已买
   * </pre>
   *
   * <code>required int32 buyState = 5;</code>
   */
  boolean hasBuyState();
  /**
   * <pre>
   *购买状态  0-未购买  1-已买
   * </pre>
   *
   * <code>required int32 buyState = 5;</code>
   */
  int getBuyState();

  /**
   * <pre>
   *价格类型 1-元宝  2-铜币
   * </pre>
   *
   * <code>required int32 costType = 6;</code>
   */
  boolean hasCostType();
  /**
   * <pre>
   *价格类型 1-元宝  2-铜币
   * </pre>
   *
   * <code>required int32 costType = 6;</code>
   */
  int getCostType();

  /**
   * <pre>
   *价格值
   * </pre>
   *
   * <code>required int32 costValue = 7;</code>
   */
  boolean hasCostValue();
  /**
   * <pre>
   *价格值
   * </pre>
   *
   * <code>required int32 costValue = 7;</code>
   */
  int getCostValue();
}
