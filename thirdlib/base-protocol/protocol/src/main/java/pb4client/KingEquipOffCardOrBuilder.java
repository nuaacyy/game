// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface KingEquipOffCardOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.KingEquipOffCard)
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
   * 要取下的位置
   * </pre>
   *
   * <code>required int32 address = 2;</code>
   */
  boolean hasAddress();
  /**
   * <pre>
   * 要取下的位置
   * </pre>
   *
   * <code>required int32 address = 2;</code>
   */
  int getAddress();
}
