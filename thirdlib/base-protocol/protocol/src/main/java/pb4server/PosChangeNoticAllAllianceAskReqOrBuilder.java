// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

public interface PosChangeNoticAllAllianceAskReqOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.PosChangeNoticAllAllianceAskReq)
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
   * <code>string playerName = 3;</code>
   */
  java.lang.String getPlayerName();
  /**
   * <code>string playerName = 3;</code>
   */
  com.google.protobuf.ByteString
      getPlayerNameBytes();

  /**
   * <code>string getPosPlayerName = 4;</code>
   */
  java.lang.String getGetPosPlayerName();
  /**
   * <code>string getPosPlayerName = 4;</code>
   */
  com.google.protobuf.ByteString
      getGetPosPlayerNameBytes();

  /**
   * <code>int32 changeType = 5;</code>
   */
  int getChangeType();

  /**
   * <code>repeated int32 positions = 6;</code>
   */
  java.util.List<java.lang.Integer> getPositionsList();
  /**
   * <code>repeated int32 positions = 6;</code>
   */
  int getPositionsCount();
  /**
   * <code>repeated int32 positions = 6;</code>
   */
  int getPositions(int index);

  /**
   * <code>int64 setPlayerId = 7;</code>
   */
  long getSetPlayerId();

  /**
   * <code>int32 isOnline = 8;</code>
   */
  int getIsOnline();

  /**
   * <code>int32 photoProtoId = 9;</code>
   */
  int getPhotoProtoId();
}
