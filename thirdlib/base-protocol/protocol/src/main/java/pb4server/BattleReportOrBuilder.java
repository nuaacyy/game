// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2home.proto

package pb4server;

public interface BattleReportOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.BattleReport)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 readState = 1;</code>
   */
  int getReadState();

  /**
   * <code>int32 reportType = 2;</code>
   */
  int getReportType();

  /**
   * <code>int64 fightTime = 3;</code>
   */
  long getFightTime();

  /**
   * <code>int32 posX = 4;</code>
   */
  int getPosX();

  /**
   * <code>int32 posY = 5;</code>
   */
  int getPosY();

  /**
   * <code>int64 pastTime = 6;</code>
   */
  long getPastTime();

  /**
   * <code>bytes reportContent = 10;</code>
   */
  com.google.protobuf.ByteString getReportContent();

  /**
   * <code>bytes fightDetail = 11;</code>
   */
  com.google.protobuf.ByteString getFightDetail();
}
