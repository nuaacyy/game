// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface JjcFightOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.JjcFight)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 攻击的排名
   * </pre>
   *
   * <code>required int32 defRank = 1;</code>
   */
  boolean hasDefRank();
  /**
   * <pre>
   * 攻击的排名
   * </pre>
   *
   * <code>required int32 defRank = 1;</code>
   */
  int getDefRank();

  /**
   * <pre>
   * 攻击的玩家id，机器人是0
   * </pre>
   *
   * <code>required int64 defPlayerId = 2;</code>
   */
  boolean hasDefPlayerId();
  /**
   * <pre>
   * 攻击的玩家id，机器人是0
   * </pre>
   *
   * <code>required int64 defPlayerId = 2;</code>
   */
  long getDefPlayerId();
}
