// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2home.proto

package pb4server;

public interface World2HomeTellOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.World2HomeTell)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 playerId = 1;</code>
   */
  long getPlayerId();

  /**
   * <code>int32 clientMsgNo = 2;</code>
   */
  int getClientMsgNo();

  /**
   * <code>.pb4server.UseGmReqTell useGmReqTell = 11;</code>
   */
  pb4server.UseGmReqTell getUseGmReqTell();
  /**
   * <code>.pb4server.UseGmReqTell useGmReqTell = 11;</code>
   */
  pb4server.UseGmReqTellOrBuilder getUseGmReqTellOrBuilder();

  /**
   * <code>.pb4server.CreateMailTell createMailTell = 12;</code>
   */
  pb4server.CreateMailTell getCreateMailTell();
  /**
   * <code>.pb4server.CreateMailTell createMailTell = 12;</code>
   */
  pb4server.CreateMailTellOrBuilder getCreateMailTellOrBuilder();

  /**
   * <code>.pb4server.LeaveAllianceTell leaveAllianceTell = 13;</code>
   */
  pb4server.LeaveAllianceTell getLeaveAllianceTell();
  /**
   * <code>.pb4server.LeaveAllianceTell leaveAllianceTell = 13;</code>
   */
  pb4server.LeaveAllianceTellOrBuilder getLeaveAllianceTellOrBuilder();

  /**
   * <code>.pb4server.HaveAllianceTell haveAllianceTell = 14;</code>
   */
  pb4server.HaveAllianceTell getHaveAllianceTell();
  /**
   * <code>.pb4server.HaveAllianceTell haveAllianceTell = 14;</code>
   */
  pb4server.HaveAllianceTellOrBuilder getHaveAllianceTellOrBuilder();

  /**
   * <code>.pb4server.AllianceInfoChangeTell allianceInfoChangeTell = 15;</code>
   */
  pb4server.AllianceInfoChangeTell getAllianceInfoChangeTell();
  /**
   * <code>.pb4server.AllianceInfoChangeTell allianceInfoChangeTell = 15;</code>
   */
  pb4server.AllianceInfoChangeTellOrBuilder getAllianceInfoChangeTellOrBuilder();

  /**
   * <code>.pb4server.TaskFinishOnWorldTell taskFinishOnWorldTell = 16;</code>
   */
  pb4server.TaskFinishOnWorldTell getTaskFinishOnWorldTell();
  /**
   * <code>.pb4server.TaskFinishOnWorldTell taskFinishOnWorldTell = 16;</code>
   */
  pb4server.TaskFinishOnWorldTellOrBuilder getTaskFinishOnWorldTellOrBuilder();

  /**
   * <code>.pb4server.AchieveFinishOnWorldTell achieveFinishOnWorldTell = 17;</code>
   */
  pb4server.AchieveFinishOnWorldTell getAchieveFinishOnWorldTell();
  /**
   * <code>.pb4server.AchieveFinishOnWorldTell achieveFinishOnWorldTell = 17;</code>
   */
  pb4server.AchieveFinishOnWorldTellOrBuilder getAchieveFinishOnWorldTellOrBuilder();

  /**
   * <code>.pb4server.FriendChangeToHomeTell friendChangeToHomeTell = 18;</code>
   */
  pb4server.FriendChangeToHomeTell getFriendChangeToHomeTell();
  /**
   * <code>.pb4server.FriendChangeToHomeTell friendChangeToHomeTell = 18;</code>
   */
  pb4server.FriendChangeToHomeTellOrBuilder getFriendChangeToHomeTellOrBuilder();

  /**
   * <code>.pb4server.UnLockJoinAllianceStateTell unLockJoinAllianceStateTell = 19;</code>
   */
  pb4server.UnLockJoinAllianceStateTell getUnLockJoinAllianceStateTell();
  /**
   * <code>.pb4server.UnLockJoinAllianceStateTell unLockJoinAllianceStateTell = 19;</code>
   */
  pb4server.UnLockJoinAllianceStateTellOrBuilder getUnLockJoinAllianceStateTellOrBuilder();

  /**
   * <code>.pb4server.JoinNoSuccessTell joinNoSuccessTell = 20;</code>
   */
  pb4server.JoinNoSuccessTell getJoinNoSuccessTell();
  /**
   * <code>.pb4server.JoinNoSuccessTell joinNoSuccessTell = 20;</code>
   */
  pb4server.JoinNoSuccessTellOrBuilder getJoinNoSuccessTellOrBuilder();

  public pb4server.World2HomeTell.MsgCase getMsgCase();
}
