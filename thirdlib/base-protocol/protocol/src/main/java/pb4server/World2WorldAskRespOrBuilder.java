// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2world.proto

package pb4server;

public interface World2WorldAskRespOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.World2WorldAskResp)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 playerId = 1;</code>
   */
  long getPlayerId();

  /**
   * <code>int64 worldId = 2;</code>
   */
  long getWorldId();

  /**
   * <code>int32 clientMsgNo = 3;</code>
   */
  int getClientMsgNo();

  /**
   * <code>.pb4server.CheckMoveServerXyRt checkMoveServerXyRt = 11;</code>
   */
  pb4server.CheckMoveServerXyRt getCheckMoveServerXyRt();
  /**
   * <code>.pb4server.CheckMoveServerXyRt checkMoveServerXyRt = 11;</code>
   */
  pb4server.CheckMoveServerXyRtOrBuilder getCheckMoveServerXyRtOrBuilder();

  /**
   * <code>.pb4server.ChangeWatchAskResp changeWatchAskResp = 12;</code>
   */
  pb4server.ChangeWatchAskResp getChangeWatchAskResp();
  /**
   * <code>.pb4server.ChangeWatchAskResp changeWatchAskResp = 12;</code>
   */
  pb4server.ChangeWatchAskRespOrBuilder getChangeWatchAskRespOrBuilder();

  public pb4server.World2WorldAskResp.MsgCase getMsgCase();
}
