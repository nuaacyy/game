// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2world.proto

package pb4server;

public interface QueryInfoByWorldAskRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.QueryInfoByWorldAskRt)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 rt = 1;</code>
   */
  int getRt();

  /**
   * <code>int64 fightValue = 2;</code>
   */
  long getFightValue();

  /**
   * <code>int64 killSoliderNum = 3;</code>
   */
  long getKillSoliderNum();

  /**
   * <code>int32 currentPos = 4;</code>
   */
  int getCurrentPos();

  /**
   * <code>.pb4server.PrisonInfo prisonInfo = 5;</code>
   */
  boolean hasPrisonInfo();
  /**
   * <code>.pb4server.PrisonInfo prisonInfo = 5;</code>
   */
  pb4server.PrisonInfo getPrisonInfo();
  /**
   * <code>.pb4server.PrisonInfo prisonInfo = 5;</code>
   */
  pb4server.PrisonInfoOrBuilder getPrisonInfoOrBuilder();
}
