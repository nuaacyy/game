// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface BagInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.BagInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 物品唯一ID
   * </pre>
   *
   * <code>required int64 itemId = 1;</code>
   */
  boolean hasItemId();
  /**
   * <pre>
   * 物品唯一ID
   * </pre>
   *
   * <code>required int64 itemId = 1;</code>
   */
  long getItemId();

  /**
   * <pre>
   *物品模板
   * </pre>
   *
   * <code>required int32 itemProtoId = 2;</code>
   */
  boolean hasItemProtoId();
  /**
   * <pre>
   *物品模板
   * </pre>
   *
   * <code>required int32 itemProtoId = 2;</code>
   */
  int getItemProtoId();

  /**
   * <pre>
   *数量
   * </pre>
   *
   * <code>required int32 num = 3;</code>
   */
  boolean hasNum();
  /**
   * <pre>
   *数量
   * </pre>
   *
   * <code>required int32 num = 3;</code>
   */
  int getNum();

  /**
   * <pre>
   *装备状态  0-仓库内,非0表示武将ID,表示被此武将佩戴
   * </pre>
   *
   * <code>required int64 itemType = 5;</code>
   */
  boolean hasItemType();
  /**
   * <pre>
   *装备状态  0-仓库内,非0表示武将ID,表示被此武将佩戴
   * </pre>
   *
   * <code>required int64 itemType = 5;</code>
   */
  long getItemType();

  /**
   * <code>required int32 equipOnAddress = 8;</code>
   */
  boolean hasEquipOnAddress();
  /**
   * <code>required int32 equipOnAddress = 8;</code>
   */
  int getEquipOnAddress();

  /**
   * <pre>
   *装备强化等级
   * </pre>
   *
   * <code>required int32 equipLv = 9;</code>
   */
  boolean hasEquipLv();
  /**
   * <pre>
   *装备强化等级
   * </pre>
   *
   * <code>required int32 equipLv = 9;</code>
   */
  int getEquipLv();

  /**
   * <pre>
   *装备属性
   * </pre>
   *
   * <code>repeated .client2server.EquipProps props = 10;</code>
   */
  java.util.List<pb4client.EquipProps> 
      getPropsList();
  /**
   * <pre>
   *装备属性
   * </pre>
   *
   * <code>repeated .client2server.EquipProps props = 10;</code>
   */
  pb4client.EquipProps getProps(int index);
  /**
   * <pre>
   *装备属性
   * </pre>
   *
   * <code>repeated .client2server.EquipProps props = 10;</code>
   */
  int getPropsCount();
  /**
   * <pre>
   *装备属性
   * </pre>
   *
   * <code>repeated .client2server.EquipProps props = 10;</code>
   */
  java.util.List<? extends pb4client.EquipPropsOrBuilder> 
      getPropsOrBuilderList();
  /**
   * <pre>
   *装备属性
   * </pre>
   *
   * <code>repeated .client2server.EquipProps props = 10;</code>
   */
  pb4client.EquipPropsOrBuilder getPropsOrBuilder(
      int index);

  /**
   * <pre>
   *装备强化经验
   * </pre>
   *
   * <code>required int32 equipExp = 11;</code>
   */
  boolean hasEquipExp();
  /**
   * <pre>
   *装备强化经验
   * </pre>
   *
   * <code>required int32 equipExp = 11;</code>
   */
  int getEquipExp();

  /**
   * <pre>
   *君主装备的卡片信息
   * </pre>
   *
   * <code>repeated .client2server.KingEquipCardInfo kingEquipCardInfos = 12;</code>
   */
  java.util.List<pb4client.KingEquipCardInfo> 
      getKingEquipCardInfosList();
  /**
   * <pre>
   *君主装备的卡片信息
   * </pre>
   *
   * <code>repeated .client2server.KingEquipCardInfo kingEquipCardInfos = 12;</code>
   */
  pb4client.KingEquipCardInfo getKingEquipCardInfos(int index);
  /**
   * <pre>
   *君主装备的卡片信息
   * </pre>
   *
   * <code>repeated .client2server.KingEquipCardInfo kingEquipCardInfos = 12;</code>
   */
  int getKingEquipCardInfosCount();
  /**
   * <pre>
   *君主装备的卡片信息
   * </pre>
   *
   * <code>repeated .client2server.KingEquipCardInfo kingEquipCardInfos = 12;</code>
   */
  java.util.List<? extends pb4client.KingEquipCardInfoOrBuilder> 
      getKingEquipCardInfosOrBuilderList();
  /**
   * <pre>
   *君主装备的卡片信息
   * </pre>
   *
   * <code>repeated .client2server.KingEquipCardInfo kingEquipCardInfos = 12;</code>
   */
  pb4client.KingEquipCardInfoOrBuilder getKingEquipCardInfosOrBuilder(
      int index);
}
