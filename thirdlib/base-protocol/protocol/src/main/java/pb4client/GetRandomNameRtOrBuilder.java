// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface GetRandomNameRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.GetRandomNameRt)
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
   * 随机名
   * </pre>
   *
   * <code>optional string name = 2;</code>
   */
  boolean hasName();
  /**
   * <pre>
   * 随机名
   * </pre>
   *
   * <code>optional string name = 2;</code>
   */
  java.lang.String getName();
  /**
   * <pre>
   * 随机名
   * </pre>
   *
   * <code>optional string name = 2;</code>
   */
  com.google.protobuf.ByteString
      getNameBytes();
}
