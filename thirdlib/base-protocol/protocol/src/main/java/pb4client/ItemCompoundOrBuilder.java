// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface ItemCompoundOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.ItemCompound)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 合成表ID
   * </pre>
   *
   * <code>required int32 compoundPropId = 1;</code>
   */
  boolean hasCompoundPropId();
  /**
   * <pre>
   * 合成表ID
   * </pre>
   *
   * <code>required int32 compoundPropId = 1;</code>
   */
  int getCompoundPropId();

  /**
   * <pre>
   * 合成散件ID
   * </pre>
   *
   * <code>repeated int64 propIds = 2;</code>
   */
  java.util.List<java.lang.Long> getPropIdsList();
  /**
   * <pre>
   * 合成散件ID
   * </pre>
   *
   * <code>repeated int64 propIds = 2;</code>
   */
  int getPropIdsCount();
  /**
   * <pre>
   * 合成散件ID
   * </pre>
   *
   * <code>repeated int64 propIds = 2;</code>
   */
  long getPropIds(int index);

  /**
   * <pre>
   * 合成个数
   * </pre>
   *
   * <code>required int32 num = 3;</code>
   */
  boolean hasNum();
  /**
   * <pre>
   * 合成个数
   * </pre>
   *
   * <code>required int32 num = 3;</code>
   */
  int getNum();
}
