// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2world.proto

package pb4server;

public interface World2WorldTellOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.World2WorldTell)
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
   * <code>.pb4server.MoveServerCellUnLockTell moveServerCellUnLockTell = 11;</code>
   */
  pb4server.MoveServerCellUnLockTell getMoveServerCellUnLockTell();
  /**
   * <code>.pb4server.MoveServerCellUnLockTell moveServerCellUnLockTell = 11;</code>
   */
  pb4server.MoveServerCellUnLockTellOrBuilder getMoveServerCellUnLockTellOrBuilder();

  public pb4server.World2WorldTell.MsgCase getMsgCase();
}
