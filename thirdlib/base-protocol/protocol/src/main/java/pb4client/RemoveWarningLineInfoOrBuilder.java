// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface RemoveWarningLineInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.RemoveWarningLineInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 需要删除的ID
   * </pre>
   *
   * <code>required int64 onlyId = 1;</code>
   */
  boolean hasOnlyId();
  /**
   * <pre>
   * 需要删除的ID
   * </pre>
   *
   * <code>required int64 onlyId = 1;</code>
   */
  long getOnlyId();

  /**
   * <pre>
   *删除信息来源 1-来自自己  2-来自盟友 3-来自下属
   * </pre>
   *
   * <code>required int32 removeSource = 2;</code>
   */
  boolean hasRemoveSource();
  /**
   * <pre>
   *删除信息来源 1-来自自己  2-来自盟友 3-来自下属
   * </pre>
   *
   * <code>required int32 removeSource = 2;</code>
   */
  int getRemoveSource();
}
