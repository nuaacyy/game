// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface OnIntHeroRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.OnIntHeroRt)
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
   * 执政武将Id
   * </pre>
   *
   * <code>optional int64 heroId = 2;</code>
   */
  boolean hasHeroId();
  /**
   * <pre>
   * 执政武将Id
   * </pre>
   *
   * <code>optional int64 heroId = 2;</code>
   */
  long getHeroId();

  /**
   * <pre>
   * 执政槽位
   * </pre>
   *
   * <code>optional int32 address = 3;</code>
   */
  boolean hasAddress();
  /**
   * <pre>
   * 执政槽位
   * </pre>
   *
   * <code>optional int32 address = 3;</code>
   */
  int getAddress();
}
