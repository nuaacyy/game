// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface CancelMakeKingEquipRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.CancelMakeKingEquipRt)
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
   * 锻造唯一ID
   * </pre>
   *
   * <code>optional int64 makeId = 2;</code>
   */
  boolean hasMakeId();
  /**
   * <pre>
   * 锻造唯一ID
   * </pre>
   *
   * <code>optional int64 makeId = 2;</code>
   */
  long getMakeId();
}
