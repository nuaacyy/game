// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface AwardAllianceOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.AwardAlliance)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *赏赐Id
   * </pre>
   *
   * <code>required int32 awardId = 1;</code>
   */
  boolean hasAwardId();
  /**
   * <pre>
   *赏赐Id
   * </pre>
   *
   * <code>required int32 awardId = 1;</code>
   */
  int getAwardId();

  /**
   * <pre>
   *赏赐的玩家ID，可跨服
   * </pre>
   *
   * <code>required int64 targetPlayerId = 2;</code>
   */
  boolean hasTargetPlayerId();
  /**
   * <pre>
   *赏赐的玩家ID，可跨服
   * </pre>
   *
   * <code>required int64 targetPlayerId = 2;</code>
   */
  long getTargetPlayerId();
}
