// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface RefreshAllianceShopRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.RefreshAllianceShopRt)
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
   *商品信息
   * </pre>
   *
   * <code>repeated .client2server.AllianceShopInfo sInfo = 2;</code>
   */
  java.util.List<pb4client.AllianceShopInfo> 
      getSInfoList();
  /**
   * <pre>
   *商品信息
   * </pre>
   *
   * <code>repeated .client2server.AllianceShopInfo sInfo = 2;</code>
   */
  pb4client.AllianceShopInfo getSInfo(int index);
  /**
   * <pre>
   *商品信息
   * </pre>
   *
   * <code>repeated .client2server.AllianceShopInfo sInfo = 2;</code>
   */
  int getSInfoCount();
  /**
   * <pre>
   *商品信息
   * </pre>
   *
   * <code>repeated .client2server.AllianceShopInfo sInfo = 2;</code>
   */
  java.util.List<? extends pb4client.AllianceShopInfoOrBuilder> 
      getSInfoOrBuilderList();
  /**
   * <pre>
   *商品信息
   * </pre>
   *
   * <code>repeated .client2server.AllianceShopInfo sInfo = 2;</code>
   */
  pb4client.AllianceShopInfoOrBuilder getSInfoOrBuilder(
      int index);

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
