// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface RefreshJjcShopItemRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.RefreshJjcShopItemRt)
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
   * 刷新的次数
   * </pre>
   *
   * <code>optional int32 times = 2;</code>
   */
  boolean hasTimes();
  /**
   * <pre>
   * 刷新的次数
   * </pre>
   *
   * <code>optional int32 times = 2;</code>
   */
  int getTimes();

  /**
   * <pre>
   * 商店各项商品信息
   * </pre>
   *
   * <code>repeated .client2server.ShopItemInfo items = 3;</code>
   */
  java.util.List<pb4client.ShopItemInfo> 
      getItemsList();
  /**
   * <pre>
   * 商店各项商品信息
   * </pre>
   *
   * <code>repeated .client2server.ShopItemInfo items = 3;</code>
   */
  pb4client.ShopItemInfo getItems(int index);
  /**
   * <pre>
   * 商店各项商品信息
   * </pre>
   *
   * <code>repeated .client2server.ShopItemInfo items = 3;</code>
   */
  int getItemsCount();
  /**
   * <pre>
   * 商店各项商品信息
   * </pre>
   *
   * <code>repeated .client2server.ShopItemInfo items = 3;</code>
   */
  java.util.List<? extends pb4client.ShopItemInfoOrBuilder> 
      getItemsOrBuilderList();
  /**
   * <pre>
   * 商店各项商品信息
   * </pre>
   *
   * <code>repeated .client2server.ShopItemInfo items = 3;</code>
   */
  pb4client.ShopItemInfoOrBuilder getItemsOrBuilder(
      int index);
}
