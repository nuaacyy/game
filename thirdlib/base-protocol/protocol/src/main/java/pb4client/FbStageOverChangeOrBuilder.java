// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface FbStageOverChangeOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.FbStageOverChange)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *0-失败；1-通关
   * </pre>
   *
   * <code>required int32 flag = 1;</code>
   */
  boolean hasFlag();
  /**
   * <pre>
   *0-失败；1-通关
   * </pre>
   *
   * <code>required int32 flag = 1;</code>
   */
  int getFlag();

  /**
   * <pre>
   *通关星级
   * </pre>
   *
   * <code>required int32 star = 2;</code>
   */
  boolean hasStar();
  /**
   * <pre>
   *通关星级
   * </pre>
   *
   * <code>required int32 star = 2;</code>
   */
  int getStar();
}
