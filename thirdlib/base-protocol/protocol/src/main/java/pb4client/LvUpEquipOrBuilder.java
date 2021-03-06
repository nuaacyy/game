// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface LvUpEquipOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.LvUpEquip)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 要强化的装备
   * </pre>
   *
   * <code>required int64 equipId = 1;</code>
   */
  boolean hasEquipId();
  /**
   * <pre>
   * 要强化的装备
   * </pre>
   *
   * <code>required int64 equipId = 1;</code>
   */
  long getEquipId();

  /**
   * <pre>
   * 强化材料ID
   * </pre>
   *
   * <code>repeated int64 equipIds = 2;</code>
   */
  java.util.List<java.lang.Long> getEquipIdsList();
  /**
   * <pre>
   * 强化材料ID
   * </pre>
   *
   * <code>repeated int64 equipIds = 2;</code>
   */
  int getEquipIdsCount();
  /**
   * <pre>
   * 强化材料ID
   * </pre>
   *
   * <code>repeated int64 equipIds = 2;</code>
   */
  long getEquipIds(int index);
}
