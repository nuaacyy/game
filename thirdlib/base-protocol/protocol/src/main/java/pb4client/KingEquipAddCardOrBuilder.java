// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface KingEquipAddCardOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.KingEquipAddCard)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 操作的装备ID
   * </pre>
   *
   * <code>required int64 equipId = 1;</code>
   */
  boolean hasEquipId();
  /**
   * <pre>
   * 操作的装备ID
   * </pre>
   *
   * <code>required int64 equipId = 1;</code>
   */
  long getEquipId();

  /**
   * <pre>
   * 要打上去的卡片唯一ID
   * </pre>
   *
   * <code>required int64 cardId = 2;</code>
   */
  boolean hasCardId();
  /**
   * <pre>
   * 要打上去的卡片唯一ID
   * </pre>
   *
   * <code>required int64 cardId = 2;</code>
   */
  long getCardId();

  /**
   * <pre>
   * 要打在的位置
   * </pre>
   *
   * <code>required int32 address = 3;</code>
   */
  boolean hasAddress();
  /**
   * <pre>
   * 要打在的位置
   * </pre>
   *
   * <code>required int32 address = 3;</code>
   */
  int getAddress();
}
