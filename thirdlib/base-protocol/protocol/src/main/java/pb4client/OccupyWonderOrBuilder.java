// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface OccupyWonderOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.OccupyWonder)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *世界Id
   * </pre>
   *
   * <code>required int64 worldId = 1;</code>
   */
  boolean hasWorldId();
  /**
   * <pre>
   *世界Id
   * </pre>
   *
   * <code>required int64 worldId = 1;</code>
   */
  long getWorldId();

  /**
   * <pre>
   *奇观配置Id
   * </pre>
   *
   * <code>repeated int32 wonderIds = 2;</code>
   */
  java.util.List<java.lang.Integer> getWonderIdsList();
  /**
   * <pre>
   *奇观配置Id
   * </pre>
   *
   * <code>repeated int32 wonderIds = 2;</code>
   */
  int getWonderIdsCount();
  /**
   * <pre>
   *奇观配置Id
   * </pre>
   *
   * <code>repeated int32 wonderIds = 2;</code>
   */
  int getWonderIds(int index);
}
