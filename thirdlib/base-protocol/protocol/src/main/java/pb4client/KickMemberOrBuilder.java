// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface KickMemberOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.KickMember)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *团ID
   * </pre>
   *
   * <code>required int64 relicBusId = 1;</code>
   */
  boolean hasRelicBusId();
  /**
   * <pre>
   *团ID
   * </pre>
   *
   * <code>required int64 relicBusId = 1;</code>
   */
  long getRelicBusId();

  /**
   * <pre>
   *被踢玩家ID
   * </pre>
   *
   * <code>required int64 kickPlayerId = 2;</code>
   */
  boolean hasKickPlayerId();
  /**
   * <pre>
   *被踢玩家ID
   * </pre>
   *
   * <code>required int64 kickPlayerId = 2;</code>
   */
  long getKickPlayerId();
}
