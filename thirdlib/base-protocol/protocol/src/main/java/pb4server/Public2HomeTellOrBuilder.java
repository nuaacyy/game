// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2home.proto

package pb4server;

public interface Public2HomeTellOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.Public2HomeTell)
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
   * <code>.pb4server.DealAfterHelpTell dealAfterHelpTell = 11;</code>
   */
  pb4server.DealAfterHelpTell getDealAfterHelpTell();
  /**
   * <code>.pb4server.DealAfterHelpTell dealAfterHelpTell = 11;</code>
   */
  pb4server.DealAfterHelpTellOrBuilder getDealAfterHelpTellOrBuilder();

  /**
   * <code>.pb4server.AllianceCompetitionInfoChangeNotic2GTell allianceCompetitionInfoChangeNotic2GTell = 12;</code>
   */
  pb4server.AllianceCompetitionInfoChangeNotic2GTell getAllianceCompetitionInfoChangeNotic2GTell();
  /**
   * <code>.pb4server.AllianceCompetitionInfoChangeNotic2GTell allianceCompetitionInfoChangeNotic2GTell = 12;</code>
   */
  pb4server.AllianceCompetitionInfoChangeNotic2GTellOrBuilder getAllianceCompetitionInfoChangeNotic2GTellOrBuilder();

  /**
   * <code>.pb4server.AllianceCompetitionOverNotic2GTell allianceCompetitionOverNotic2GTell = 13;</code>
   */
  pb4server.AllianceCompetitionOverNotic2GTell getAllianceCompetitionOverNotic2GTell();
  /**
   * <code>.pb4server.AllianceCompetitionOverNotic2GTell allianceCompetitionOverNotic2GTell = 13;</code>
   */
  pb4server.AllianceCompetitionOverNotic2GTellOrBuilder getAllianceCompetitionOverNotic2GTellOrBuilder();

  /**
   * <code>.pb4server.OccupyWonder2HomeTell occupyWonderTell = 14;</code>
   */
  pb4server.OccupyWonder2HomeTell getOccupyWonderTell();
  /**
   * <code>.pb4server.OccupyWonder2HomeTell occupyWonderTell = 14;</code>
   */
  pb4server.OccupyWonder2HomeTellOrBuilder getOccupyWonderTellOrBuilder();

  public pb4server.Public2HomeTell.MsgCase getMsgCase();
}
