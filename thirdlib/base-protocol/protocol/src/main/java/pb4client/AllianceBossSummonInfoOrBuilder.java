// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface AllianceBossSummonInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.AllianceBossSummonInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * </pre>
   *
   * <code>required int32 bossId = 1;</code>
   */
  boolean hasBossId();
  /**
   * <pre>
   * </pre>
   *
   * <code>required int32 bossId = 1;</code>
   */
  int getBossId();

  /**
   * <pre>
   * 0- 未召唤 1-已召唤
   * </pre>
   *
   * <code>required int32 state = 2;</code>
   */
  boolean hasState();
  /**
   * <pre>
   * 0- 未召唤 1-已召唤
   * </pre>
   *
   * <code>required int32 state = 2;</code>
   */
  int getState();
}
