// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface OnEquipOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.OnEquip)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 要穿的装备ID
   * </pre>
   *
   * <code>required int64 equipId = 1;</code>
   */
  boolean hasEquipId();
  /**
   * <pre>
   * 要穿的装备ID
   * </pre>
   *
   * <code>required int64 equipId = 1;</code>
   */
  long getEquipId();

  /**
   * <pre>
   * 要穿的装备部位
   * </pre>
   *
   * <code>required int32 equipPart = 2;</code>
   */
  boolean hasEquipPart();
  /**
   * <pre>
   * 要穿的装备部位
   * </pre>
   *
   * <code>required int32 equipPart = 2;</code>
   */
  int getEquipPart();

  /**
   * <pre>
   * 穿戴的武将ID
   * </pre>
   *
   * <code>required int64 heroId = 3;</code>
   */
  boolean hasHeroId();
  /**
   * <pre>
   * 穿戴的武将ID
   * </pre>
   *
   * <code>required int64 heroId = 3;</code>
   */
  long getHeroId();
}
