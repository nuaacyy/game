// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface SetCountryPositionOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.SetCountryPosition)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *官职Id
   * </pre>
   *
   * <code>required int32 posId = 1;</code>
   */
  boolean hasPosId();
  /**
   * <pre>
   *官职Id
   * </pre>
   *
   * <code>required int32 posId = 1;</code>
   */
  int getPosId();

  /**
   * <pre>
   *册封玩家ID
   * </pre>
   *
   * <code>required int64 targetPlayerId = 2;</code>
   */
  boolean hasTargetPlayerId();
  /**
   * <pre>
   *册封玩家ID
   * </pre>
   *
   * <code>required int64 targetPlayerId = 2;</code>
   */
  long getTargetPlayerId();
}
