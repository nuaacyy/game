// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface OccupiedChangeOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.OccupiedChange)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *沦陷首次推送标记 1-首次 0-非首次
   * </pre>
   *
   * <code>required int32 flag = 1;</code>
   */
  boolean hasFlag();
  /**
   * <pre>
   *沦陷首次推送标记 1-首次 0-非首次
   * </pre>
   *
   * <code>required int32 flag = 1;</code>
   */
  int getFlag();

  /**
   * <pre>
   *上级同盟ID
   * </pre>
   *
   * <code>required int64 id = 2;</code>
   */
  boolean hasId();
  /**
   * <pre>
   *上级同盟ID
   * </pre>
   *
   * <code>required int64 id = 2;</code>
   */
  long getId();

  /**
   * <pre>
   *上级同盟名称
   * </pre>
   *
   * <code>required string name = 3;</code>
   */
  boolean hasName();
  /**
   * <pre>
   *上级同盟名称
   * </pre>
   *
   * <code>required string name = 3;</code>
   */
  java.lang.String getName();
  /**
   * <pre>
   *上级同盟名称
   * </pre>
   *
   * <code>required string name = 3;</code>
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <pre>
   *上级同盟简称
   * </pre>
   *
   * <code>required string shortName = 4;</code>
   */
  boolean hasShortName();
  /**
   * <pre>
   *上级同盟简称
   * </pre>
   *
   * <code>required string shortName = 4;</code>
   */
  java.lang.String getShortName();
  /**
   * <pre>
   *上级同盟简称
   * </pre>
   *
   * <code>required string shortName = 4;</code>
   */
  com.google.protobuf.ByteString
      getShortNameBytes();

  /**
   * <pre>
   *攻击的玩家ID
   * </pre>
   *
   * <code>required int64 playerId = 5;</code>
   */
  boolean hasPlayerId();
  /**
   * <pre>
   *攻击的玩家ID
   * </pre>
   *
   * <code>required int64 playerId = 5;</code>
   */
  long getPlayerId();

  /**
   * <pre>
   *攻击的玩家名字
   * </pre>
   *
   * <code>required string playerName = 6;</code>
   */
  boolean hasPlayerName();
  /**
   * <pre>
   *攻击的玩家名字
   * </pre>
   *
   * <code>required string playerName = 6;</code>
   */
  java.lang.String getPlayerName();
  /**
   * <pre>
   *攻击的玩家名字
   * </pre>
   *
   * <code>required string playerName = 6;</code>
   */
  com.google.protobuf.ByteString
      getPlayerNameBytes();

  /**
   * <pre>
   *沦陷时间
   * </pre>
   *
   * <code>required int32 at = 7;</code>
   */
  boolean hasAt();
  /**
   * <pre>
   *沦陷时间
   * </pre>
   *
   * <code>required int32 at = 7;</code>
   */
  int getAt();
}
