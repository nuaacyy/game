// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2world.proto

package pb4server;

public interface PosChangeNoticAllAllianceTellOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.PosChangeNoticAllAllianceTell)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 allianceId = 1;</code>
   */
  long getAllianceId();

  /**
   * <code>int32 pos = 2;</code>
   */
  int getPos();

  /**
   * <pre>
   * 给予职位的玩家名
   * </pre>
   *
   * <code>string playerName = 3;</code>
   */
  java.lang.String getPlayerName();
  /**
   * <pre>
   * 给予职位的玩家名
   * </pre>
   *
   * <code>string playerName = 3;</code>
   */
  com.google.protobuf.ByteString
      getPlayerNameBytes();

  /**
   * <pre>
   * 被任命的玩家名
   * </pre>
   *
   * <code>string getPosPlayerName = 4;</code>
   */
  java.lang.String getGetPosPlayerName();
  /**
   * <pre>
   * 被任命的玩家名
   * </pre>
   *
   * <code>string getPosPlayerName = 4;</code>
   */
  com.google.protobuf.ByteString
      getGetPosPlayerNameBytes();

  /**
   * <pre>
   * 1-新增  2-减少
   * </pre>
   *
   * <code>int32 changeType = 5;</code>
   */
  int getChangeType();

  /**
   * <pre>
   * 被任命后的职位
   * </pre>
   *
   * <code>repeated int32 positions = 6;</code>
   */
  java.util.List<java.lang.Integer> getPositionsList();
  /**
   * <pre>
   * 被任命后的职位
   * </pre>
   *
   * <code>repeated int32 positions = 6;</code>
   */
  int getPositionsCount();
  /**
   * <pre>
   * 被任命后的职位
   * </pre>
   *
   * <code>repeated int32 positions = 6;</code>
   */
  int getPositions(int index);

  /**
   * <pre>
   * 被任命的玩家ID
   * </pre>
   *
   * <code>int64 setPlayerId = 7;</code>
   */
  long getSetPlayerId();

  /**
   * <pre>
   * 被任命的玩家是否在线  0-离线  1-在线
   * </pre>
   *
   * <code>int32 isOnline = 8;</code>
   */
  int getIsOnline();

  /**
   * <pre>
   * 头像
   * </pre>
   *
   * <code>int32 photoProtoId = 9;</code>
   */
  int getPhotoProtoId();
}
