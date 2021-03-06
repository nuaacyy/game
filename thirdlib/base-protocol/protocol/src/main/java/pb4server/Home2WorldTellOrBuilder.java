// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2world.proto

package pb4server;

public interface Home2WorldTellOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.Home2WorldTell)
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
   * <code>.pb4server.GetChatHistoryTell getChatHistoryTell = 11;</code>
   */
  pb4server.GetChatHistoryTell getGetChatHistoryTell();
  /**
   * <code>.pb4server.GetChatHistoryTell getChatHistoryTell = 11;</code>
   */
  pb4server.GetChatHistoryTellOrBuilder getGetChatHistoryTellOrBuilder();

  /**
   * <code>.pb4server.LeaveAllianceBySelfTell leaveAllianceBySelfTell = 12;</code>
   */
  pb4server.LeaveAllianceBySelfTell getLeaveAllianceBySelfTell();
  /**
   * <code>.pb4server.LeaveAllianceBySelfTell leaveAllianceBySelfTell = 12;</code>
   */
  pb4server.LeaveAllianceBySelfTellOrBuilder getLeaveAllianceBySelfTellOrBuilder();

  /**
   * <code>.pb4server.CreateTaskToWorldTell createTaskToWorldTell = 13;</code>
   */
  pb4server.CreateTaskToWorldTell getCreateTaskToWorldTell();
  /**
   * <code>.pb4server.CreateTaskToWorldTell createTaskToWorldTell = 13;</code>
   */
  pb4server.CreateTaskToWorldTellOrBuilder getCreateTaskToWorldTellOrBuilder();

  /**
   * <code>.pb4server.RemoveTaskToWorldTell removeTaskToWorldTell = 14;</code>
   */
  pb4server.RemoveTaskToWorldTell getRemoveTaskToWorldTell();
  /**
   * <code>.pb4server.RemoveTaskToWorldTell removeTaskToWorldTell = 14;</code>
   */
  pb4server.RemoveTaskToWorldTellOrBuilder getRemoveTaskToWorldTellOrBuilder();

  /**
   * <code>.pb4server.ClientDisconnectTell clientDisconnectTell = 15;</code>
   */
  pb4server.ClientDisconnectTell getClientDisconnectTell();
  /**
   * <code>.pb4server.ClientDisconnectTell clientDisconnectTell = 15;</code>
   */
  pb4server.ClientDisconnectTellOrBuilder getClientDisconnectTellOrBuilder();

  /**
   * <code>.pb4server.JoinNoSucTell joinNoSucTell = 16;</code>
   */
  pb4server.JoinNoSucTell getJoinNoSucTell();
  /**
   * <code>.pb4server.JoinNoSucTell joinNoSucTell = 16;</code>
   */
  pb4server.JoinNoSucTellOrBuilder getJoinNoSucTellOrBuilder();

  public pb4server.Home2WorldTell.MsgCase getMsgCase();
}
