// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface KillActivityBossReportOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.KillActivityBossReport)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required string playerName = 1;</code>
   */
  boolean hasPlayerName();
  /**
   * <code>required string playerName = 1;</code>
   */
  java.lang.String getPlayerName();
  /**
   * <code>required string playerName = 1;</code>
   */
  com.google.protobuf.ByteString
      getPlayerNameBytes();

  /**
   * <code>required string allianceShortName = 2;</code>
   */
  boolean hasAllianceShortName();
  /**
   * <code>required string allianceShortName = 2;</code>
   */
  java.lang.String getAllianceShortName();
  /**
   * <code>required string allianceShortName = 2;</code>
   */
  com.google.protobuf.ByteString
      getAllianceShortNameBytes();

  /**
   * <code>required int32 activityBossId = 3;</code>
   */
  boolean hasActivityBossId();
  /**
   * <code>required int32 activityBossId = 3;</code>
   */
  int getActivityBossId();
}
