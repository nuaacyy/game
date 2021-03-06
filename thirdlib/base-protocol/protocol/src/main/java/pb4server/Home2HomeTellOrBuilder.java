// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2home.proto

package pb4server;

public interface Home2HomeTellOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.Home2HomeTell)
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
   * <code>.pb4server.ChatRoomDismissTell chatRoomDismissTell = 11;</code>
   */
  pb4server.ChatRoomDismissTell getChatRoomDismissTell();
  /**
   * <code>.pb4server.ChatRoomDismissTell chatRoomDismissTell = 11;</code>
   */
  pb4server.ChatRoomDismissTellOrBuilder getChatRoomDismissTellOrBuilder();

  /**
   * <code>.pb4server.ChatRoomChangeInfoTell chatRoomChangeInfoTell = 12;</code>
   */
  pb4server.ChatRoomChangeInfoTell getChatRoomChangeInfoTell();
  /**
   * <code>.pb4server.ChatRoomChangeInfoTell chatRoomChangeInfoTell = 12;</code>
   */
  pb4server.ChatRoomChangeInfoTellOrBuilder getChatRoomChangeInfoTellOrBuilder();

  /**
   * <code>.pb4server.JoinChatRoomTell joinChatRoomTell = 13;</code>
   */
  pb4server.JoinChatRoomTell getJoinChatRoomTell();
  /**
   * <code>.pb4server.JoinChatRoomTell joinChatRoomTell = 13;</code>
   */
  pb4server.JoinChatRoomTellOrBuilder getJoinChatRoomTellOrBuilder();

  /**
   * <code>.pb4server.ChatRoomKickOutTell chatRoomKickOutTell = 14;</code>
   */
  pb4server.ChatRoomKickOutTell getChatRoomKickOutTell();
  /**
   * <code>.pb4server.ChatRoomKickOutTell chatRoomKickOutTell = 14;</code>
   */
  pb4server.ChatRoomKickOutTellOrBuilder getChatRoomKickOutTellOrBuilder();

  /**
   * <code>.pb4server.ChatRoomNewNameTell chatRoomNewNameTell = 15;</code>
   */
  pb4server.ChatRoomNewNameTell getChatRoomNewNameTell();
  /**
   * <code>.pb4server.ChatRoomNewNameTell chatRoomNewNameTell = 15;</code>
   */
  pb4server.ChatRoomNewNameTellOrBuilder getChatRoomNewNameTellOrBuilder();

  /**
   * <code>.pb4server.SaveFriendChatRecordTell saveFriendChatRecordTell = 16;</code>
   */
  pb4server.SaveFriendChatRecordTell getSaveFriendChatRecordTell();
  /**
   * <code>.pb4server.SaveFriendChatRecordTell saveFriendChatRecordTell = 16;</code>
   */
  pb4server.SaveFriendChatRecordTellOrBuilder getSaveFriendChatRecordTellOrBuilder();

  /**
   * <code>.pb4server.BlackTell blackTell = 17;</code>
   */
  pb4server.BlackTell getBlackTell();
  /**
   * <code>.pb4server.BlackTell blackTell = 17;</code>
   */
  pb4server.BlackTellOrBuilder getBlackTellOrBuilder();

  /**
   * <code>.pb4server.FriendRefreshNoticeTell friendRefreshNoticeTell = 18;</code>
   */
  pb4server.FriendRefreshNoticeTell getFriendRefreshNoticeTell();
  /**
   * <code>.pb4server.FriendRefreshNoticeTell friendRefreshNoticeTell = 18;</code>
   */
  pb4server.FriendRefreshNoticeTellOrBuilder getFriendRefreshNoticeTellOrBuilder();

  /**
   * <code>.pb4server.CreateMailTell createMailTell = 19;</code>
   */
  pb4server.CreateMailTell getCreateMailTell();
  /**
   * <code>.pb4server.CreateMailTell createMailTell = 19;</code>
   */
  pb4server.CreateMailTellOrBuilder getCreateMailTellOrBuilder();

  public pb4server.Home2HomeTell.MsgCase getMsgCase();
}
