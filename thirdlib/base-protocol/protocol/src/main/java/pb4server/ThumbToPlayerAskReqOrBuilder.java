// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2home.proto

package pb4server;

public interface ThumbToPlayerAskReqOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.ThumbToPlayerAskReq)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 sendPlayerId = 1;</code>
   */
  long getSendPlayerId();

  /**
   * <code>string playerName = 2;</code>
   */
  java.lang.String getPlayerName();
  /**
   * <code>string playerName = 2;</code>
   */
  com.google.protobuf.ByteString
      getPlayerNameBytes();

  /**
   * <code>int32 lv = 3;</code>
   */
  int getLv();

  /**
   * <code>string intro = 4;</code>
   */
  java.lang.String getIntro();
  /**
   * <code>string intro = 4;</code>
   */
  com.google.protobuf.ByteString
      getIntroBytes();

  /**
   * <code>int64 thumbTime = 5;</code>
   */
  long getThumbTime();
}
