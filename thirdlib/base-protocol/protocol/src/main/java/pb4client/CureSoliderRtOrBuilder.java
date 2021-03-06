// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface CureSoliderRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.CureSoliderRt)
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
   * 治疗类型  1-普通治疗  2-元宝补齐资源治疗
   * </pre>
   *
   * <code>optional int32 cureType = 2;</code>
   */
  boolean hasCureType();
  /**
   * <pre>
   * 治疗类型  1-普通治疗  2-元宝补齐资源治疗
   * </pre>
   *
   * <code>optional int32 cureType = 2;</code>
   */
  int getCureType();
}
