// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface MoveServerResultOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.MoveServerResult)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required .client2server.ServerInfo serverInfo = 1;</code>
   */
  boolean hasServerInfo();
  /**
   * <code>required .client2server.ServerInfo serverInfo = 1;</code>
   */
  pb4client.ServerInfo getServerInfo();
  /**
   * <code>required .client2server.ServerInfo serverInfo = 1;</code>
   */
  pb4client.ServerInfoOrBuilder getServerInfoOrBuilder();

  /**
   * <pre>
   * 迁服成功之后的新坐标X
   * </pre>
   *
   * <code>required int32 x = 2;</code>
   */
  boolean hasX();
  /**
   * <pre>
   * 迁服成功之后的新坐标X
   * </pre>
   *
   * <code>required int32 x = 2;</code>
   */
  int getX();

  /**
   * <pre>
   * 迁服成功之后的新坐标Y
   * </pre>
   *
   * <code>required int32 y = 3;</code>
   */
  boolean hasY();
  /**
   * <pre>
   * 迁服成功之后的新坐标Y
   * </pre>
   *
   * <code>required int32 y = 3;</code>
   */
  int getY();
}
