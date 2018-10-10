// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2home.proto

package pb4server;

public interface Home2HomeAskRespOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.Home2HomeAskResp)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 playerId = 1;</code>
   */
  long getPlayerId();

  /**
   * <code>int64 senderId = 2;</code>
   */
  long getSenderId();

  /**
   * <code>int32 clientMsgNo = 3;</code>
   */
  int getClientMsgNo();

  /**
   * <code>.pb4server.ThumbToPlayerAskRt thumbToPlayerAskRt = 11;</code>
   */
  pb4server.ThumbToPlayerAskRt getThumbToPlayerAskRt();
  /**
   * <code>.pb4server.ThumbToPlayerAskRt thumbToPlayerAskRt = 11;</code>
   */
  pb4server.ThumbToPlayerAskRtOrBuilder getThumbToPlayerAskRtOrBuilder();

  /**
   * <code>.pb4server.BeforeJoinRoomAskRt beforeJoinRoomAskRt = 12;</code>
   */
  pb4server.BeforeJoinRoomAskRt getBeforeJoinRoomAskRt();
  /**
   * <code>.pb4server.BeforeJoinRoomAskRt beforeJoinRoomAskRt = 12;</code>
   */
  pb4server.BeforeJoinRoomAskRtOrBuilder getBeforeJoinRoomAskRtOrBuilder();

  /**
   * <code>.pb4server.AskStrangerInfoAskRt askStrangerInfoAskRt = 13;</code>
   */
  pb4server.AskStrangerInfoAskRt getAskStrangerInfoAskRt();
  /**
   * <code>.pb4server.AskStrangerInfoAskRt askStrangerInfoAskRt = 13;</code>
   */
  pb4server.AskStrangerInfoAskRtOrBuilder getAskStrangerInfoAskRtOrBuilder();

  /**
   * <code>.pb4server.FriendNoticeAskRt friendNoticeAskRt = 14;</code>
   */
  pb4server.FriendNoticeAskRt getFriendNoticeAskRt();
  /**
   * <code>.pb4server.FriendNoticeAskRt friendNoticeAskRt = 14;</code>
   */
  pb4server.FriendNoticeAskRtOrBuilder getFriendNoticeAskRtOrBuilder();

  /**
   * <code>.pb4server.FriendAcceptAskRt friendAcceptAskRt = 15;</code>
   */
  pb4server.FriendAcceptAskRt getFriendAcceptAskRt();
  /**
   * <code>.pb4server.FriendAcceptAskRt friendAcceptAskRt = 15;</code>
   */
  pb4server.FriendAcceptAskRtOrBuilder getFriendAcceptAskRtOrBuilder();

  /**
   * <code>.pb4server.FriendRemoveAskRt friendRemoveAskRt = 16;</code>
   */
  pb4server.FriendRemoveAskRt getFriendRemoveAskRt();
  /**
   * <code>.pb4server.FriendRemoveAskRt friendRemoveAskRt = 16;</code>
   */
  pb4server.FriendRemoveAskRtOrBuilder getFriendRemoveAskRtOrBuilder();

  /**
   * <code>.pb4server.FriendGroupNoticeAskRt friendGroupNoticeAskRt = 17;</code>
   */
  pb4server.FriendGroupNoticeAskRt getFriendGroupNoticeAskRt();
  /**
   * <code>.pb4server.FriendGroupNoticeAskRt friendGroupNoticeAskRt = 17;</code>
   */
  pb4server.FriendGroupNoticeAskRtOrBuilder getFriendGroupNoticeAskRtOrBuilder();

  /**
   * <code>.pb4server.FindPlayerInBlackAskRt findPlayerInBlackAskRt = 18;</code>
   */
  pb4server.FindPlayerInBlackAskRt getFindPlayerInBlackAskRt();
  /**
   * <code>.pb4server.FindPlayerInBlackAskRt findPlayerInBlackAskRt = 18;</code>
   */
  pb4server.FindPlayerInBlackAskRtOrBuilder getFindPlayerInBlackAskRtOrBuilder();

  /**
   * <code>.pb4server.QueryInfoByHomeRt queryInfoByHomeRt = 19;</code>
   */
  pb4server.QueryInfoByHomeRt getQueryInfoByHomeRt();
  /**
   * <code>.pb4server.QueryInfoByHomeRt queryInfoByHomeRt = 19;</code>
   */
  pb4server.QueryInfoByHomeRtOrBuilder getQueryInfoByHomeRtOrBuilder();

  /**
   * <code>.pb4server.QueryBattleReportInfoAskRt queryBattleReportInfoAskRt = 20;</code>
   */
  pb4server.QueryBattleReportInfoAskRt getQueryBattleReportInfoAskRt();
  /**
   * <code>.pb4server.QueryBattleReportInfoAskRt queryBattleReportInfoAskRt = 20;</code>
   */
  pb4server.QueryBattleReportInfoAskRtOrBuilder getQueryBattleReportInfoAskRtOrBuilder();

  /**
   * <code>.pb4server.AskFightInfoDetailAskRt askFightInfoDetailAskRt = 21;</code>
   */
  pb4server.AskFightInfoDetailAskRt getAskFightInfoDetailAskRt();
  /**
   * <code>.pb4server.AskFightInfoDetailAskRt askFightInfoDetailAskRt = 21;</code>
   */
  pb4server.AskFightInfoDetailAskRtOrBuilder getAskFightInfoDetailAskRtOrBuilder();

  /**
   * <code>.pb4server.QueryPlayerTargetAskRt queryPlayerTargetAskRt = 22;</code>
   */
  pb4server.QueryPlayerTargetAskRt getQueryPlayerTargetAskRt();
  /**
   * <code>.pb4server.QueryPlayerTargetAskRt queryPlayerTargetAskRt = 22;</code>
   */
  pb4server.QueryPlayerTargetAskRtOrBuilder getQueryPlayerTargetAskRtOrBuilder();

  public pb4server.Home2HomeAskResp.MsgCase getMsgCase();
}