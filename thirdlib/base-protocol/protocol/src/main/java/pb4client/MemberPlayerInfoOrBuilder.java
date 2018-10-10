// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface MemberPlayerInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.MemberPlayerInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *玩家id
   * </pre>
   *
   * <code>required int64 playerId = 1;</code>
   */
  boolean hasPlayerId();
  /**
   * <pre>
   *玩家id
   * </pre>
   *
   * <code>required int64 playerId = 1;</code>
   */
  long getPlayerId();

  /**
   * <pre>
   *名字
   * </pre>
   *
   * <code>required string playerName = 2;</code>
   */
  boolean hasPlayerName();
  /**
   * <pre>
   *名字
   * </pre>
   *
   * <code>required string playerName = 2;</code>
   */
  java.lang.String getPlayerName();
  /**
   * <pre>
   *名字
   * </pre>
   *
   * <code>required string playerName = 2;</code>
   */
  com.google.protobuf.ByteString
      getPlayerNameBytes();

  /**
   * <pre>
   *玩家联盟职位：1-盟主 2-副盟主 3-指挥官 4-外交官 5-团长 6-普通成员 7-白虎成员 8-青龙成员 9-玄武成员 10-朱雀成员 11-白虎团长 12-青龙团长 13-玄武团长 14-朱雀团长
   * </pre>
   *
   * <code>repeated int32 positions = 4;</code>
   */
  java.util.List<java.lang.Integer> getPositionsList();
  /**
   * <pre>
   *玩家联盟职位：1-盟主 2-副盟主 3-指挥官 4-外交官 5-团长 6-普通成员 7-白虎成员 8-青龙成员 9-玄武成员 10-朱雀成员 11-白虎团长 12-青龙团长 13-玄武团长 14-朱雀团长
   * </pre>
   *
   * <code>repeated int32 positions = 4;</code>
   */
  int getPositionsCount();
  /**
   * <pre>
   *玩家联盟职位：1-盟主 2-副盟主 3-指挥官 4-外交官 5-团长 6-普通成员 7-白虎成员 8-青龙成员 9-玄武成员 10-朱雀成员 11-白虎团长 12-青龙团长 13-玄武团长 14-朱雀团长
   * </pre>
   *
   * <code>repeated int32 positions = 4;</code>
   */
  int getPositions(int index);

  /**
   * <pre>
   *玩家是否在线
   * </pre>
   *
   * <code>required int32 isOnline = 5;</code>
   */
  boolean hasIsOnline();
  /**
   * <pre>
   *玩家是否在线
   * </pre>
   *
   * <code>required int32 isOnline = 5;</code>
   */
  int getIsOnline();

  /**
   * <pre>
   *玩家头像id
   * </pre>
   *
   * <code>required int32 protoId = 6;</code>
   */
  boolean hasProtoId();
  /**
   * <pre>
   *玩家头像id
   * </pre>
   *
   * <code>required int32 protoId = 6;</code>
   */
  int getProtoId();
}