// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface BattleSimpleRsOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.BattleSimpleRs)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required int32 atkerPhotoId = 1;</code>
   */
  boolean hasAtkerPhotoId();
  /**
   * <code>required int32 atkerPhotoId = 1;</code>
   */
  int getAtkerPhotoId();

  /**
   * <code>required string atkerAllianceShortName = 2;</code>
   */
  boolean hasAtkerAllianceShortName();
  /**
   * <code>required string atkerAllianceShortName = 2;</code>
   */
  java.lang.String getAtkerAllianceShortName();
  /**
   * <code>required string atkerAllianceShortName = 2;</code>
   */
  com.google.protobuf.ByteString
      getAtkerAllianceShortNameBytes();

  /**
   * <code>required string atkerName = 3;</code>
   */
  boolean hasAtkerName();
  /**
   * <code>required string atkerName = 3;</code>
   */
  java.lang.String getAtkerName();
  /**
   * <code>required string atkerName = 3;</code>
   */
  com.google.protobuf.ByteString
      getAtkerNameBytes();

  /**
   * <code>required int32 atkerBattleRs = 4;</code>
   */
  boolean hasAtkerBattleRs();
  /**
   * <code>required int32 atkerBattleRs = 4;</code>
   */
  int getAtkerBattleRs();

  /**
   * <code>required int32 deferPhotoId = 5;</code>
   */
  boolean hasDeferPhotoId();
  /**
   * <code>required int32 deferPhotoId = 5;</code>
   */
  int getDeferPhotoId();

  /**
   * <code>required string deferAllianceShortName = 6;</code>
   */
  boolean hasDeferAllianceShortName();
  /**
   * <code>required string deferAllianceShortName = 6;</code>
   */
  java.lang.String getDeferAllianceShortName();
  /**
   * <code>required string deferAllianceShortName = 6;</code>
   */
  com.google.protobuf.ByteString
      getDeferAllianceShortNameBytes();

  /**
   * <code>required string deferName = 7;</code>
   */
  boolean hasDeferName();
  /**
   * <code>required string deferName = 7;</code>
   */
  java.lang.String getDeferName();
  /**
   * <code>required string deferName = 7;</code>
   */
  com.google.protobuf.ByteString
      getDeferNameBytes();

  /**
   * <code>required int32 deferBattleRs = 8;</code>
   */
  boolean hasDeferBattleRs();
  /**
   * <code>required int32 deferBattleRs = 8;</code>
   */
  int getDeferBattleRs();

  /**
   * <code>required int64 atkerPlayerId = 9;</code>
   */
  boolean hasAtkerPlayerId();
  /**
   * <code>required int64 atkerPlayerId = 9;</code>
   */
  long getAtkerPlayerId();

  /**
   * <code>required int64 deferPlayerId = 10;</code>
   */
  boolean hasDeferPlayerId();
  /**
   * <code>required int64 deferPlayerId = 10;</code>
   */
  long getDeferPlayerId();
}