// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface SellVoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.SellVo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 要卖掉的ID
   * </pre>
   *
   * <code>required int64 equipId = 1;</code>
   */
  boolean hasEquipId();
  /**
   * <pre>
   * 要卖掉的ID
   * </pre>
   *
   * <code>required int64 equipId = 1;</code>
   */
  long getEquipId();

  /**
   * <pre>
   * 要卖掉的数量
   * </pre>
   *
   * <code>required int64 equipNum = 2;</code>
   */
  boolean hasEquipNum();
  /**
   * <pre>
   * 要卖掉的数量
   * </pre>
   *
   * <code>required int64 equipNum = 2;</code>
   */
  long getEquipNum();
}
