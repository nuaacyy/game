// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface OpenEquipShopRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.OpenEquipShopRt)
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
   *下次自动刷新时间
   * </pre>
   *
   * <code>optional int32 cdOverSec = 4;</code>
   */
  boolean hasCdOverSec();
  /**
   * <pre>
   *下次自动刷新时间
   * </pre>
   *
   * <code>optional int32 cdOverSec = 4;</code>
   */
  int getCdOverSec();

  /**
   * <pre>
   *装备信息
   * </pre>
   *
   * <code>repeated .client2server.EquipShopInfo equipInfo = 5;</code>
   */
  java.util.List<pb4client.EquipShopInfo> 
      getEquipInfoList();
  /**
   * <pre>
   *装备信息
   * </pre>
   *
   * <code>repeated .client2server.EquipShopInfo equipInfo = 5;</code>
   */
  pb4client.EquipShopInfo getEquipInfo(int index);
  /**
   * <pre>
   *装备信息
   * </pre>
   *
   * <code>repeated .client2server.EquipShopInfo equipInfo = 5;</code>
   */
  int getEquipInfoCount();
  /**
   * <pre>
   *装备信息
   * </pre>
   *
   * <code>repeated .client2server.EquipShopInfo equipInfo = 5;</code>
   */
  java.util.List<? extends pb4client.EquipShopInfoOrBuilder> 
      getEquipInfoOrBuilderList();
  /**
   * <pre>
   *装备信息
   * </pre>
   *
   * <code>repeated .client2server.EquipShopInfo equipInfo = 5;</code>
   */
  pb4client.EquipShopInfoOrBuilder getEquipInfoOrBuilder(
      int index);
}
