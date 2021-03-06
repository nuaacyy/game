// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface SoliderUpOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.SoliderUp)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 要造的ID
   * </pre>
   *
   * <code>required int32 soliderId = 1;</code>
   */
  boolean hasSoliderId();
  /**
   * <pre>
   * 要造的ID
   * </pre>
   *
   * <code>required int32 soliderId = 1;</code>
   */
  int getSoliderId();

  /**
   * <pre>
   * 晋升类型  1-普通晋升  2-元宝补齐资源晋升
   * </pre>
   *
   * <code>required int32 upype = 2;</code>
   */
  boolean hasUpype();
  /**
   * <pre>
   * 晋升类型  1-普通晋升  2-元宝补齐资源晋升
   * </pre>
   *
   * <code>required int32 upype = 2;</code>
   */
  int getUpype();

  /**
   * <pre>
   * 晋升数量
   * </pre>
   *
   * <code>required int32 upNum = 3;</code>
   */
  boolean hasUpNum();
  /**
   * <pre>
   * 晋升数量
   * </pre>
   *
   * <code>required int32 upNum = 3;</code>
   */
  int getUpNum();
}
