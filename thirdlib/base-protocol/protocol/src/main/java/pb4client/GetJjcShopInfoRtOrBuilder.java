// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface GetJjcShopInfoRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.GetJjcShopInfoRt)
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
   * 上次的刷新时间 毫秒时间戳
   * </pre>
   *
   * <code>optional int64 refreshTime = 2;</code>
   */
  boolean hasRefreshTime();
  /**
   * <pre>
   * 上次的刷新时间 毫秒时间戳
   * </pre>
   *
   * <code>optional int64 refreshTime = 2;</code>
   */
  long getRefreshTime();

  /**
   * <pre>
   * 刷新的次数
   * </pre>
   *
   * <code>optional int32 times = 3;</code>
   */
  boolean hasTimes();
  /**
   * <pre>
   * 刷新的次数
   * </pre>
   *
   * <code>optional int32 times = 3;</code>
   */
  int getTimes();

  /**
   * <pre>
   * 商店各项商品信息
   * </pre>
   *
   * <code>repeated .client2server.ShopItemInfo items = 4;</code>
   */
  java.util.List<pb4client.ShopItemInfo> 
      getItemsList();
  /**
   * <pre>
   * 商店各项商品信息
   * </pre>
   *
   * <code>repeated .client2server.ShopItemInfo items = 4;</code>
   */
  pb4client.ShopItemInfo getItems(int index);
  /**
   * <pre>
   * 商店各项商品信息
   * </pre>
   *
   * <code>repeated .client2server.ShopItemInfo items = 4;</code>
   */
  int getItemsCount();
  /**
   * <pre>
   * 商店各项商品信息
   * </pre>
   *
   * <code>repeated .client2server.ShopItemInfo items = 4;</code>
   */
  java.util.List<? extends pb4client.ShopItemInfoOrBuilder> 
      getItemsOrBuilderList();
  /**
   * <pre>
   * 商店各项商品信息
   * </pre>
   *
   * <code>repeated .client2server.ShopItemInfo items = 4;</code>
   */
  pb4client.ShopItemInfoOrBuilder getItemsOrBuilder(
      int index);
}
