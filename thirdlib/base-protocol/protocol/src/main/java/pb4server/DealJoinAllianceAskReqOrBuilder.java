// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

public interface DealJoinAllianceAskReqOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.DealJoinAllianceAskReq)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 reqType = 1;</code>
   */
  int getReqType();

  /**
   * <code>int64 pid = 2;</code>
   */
  long getPid();

  /**
   * <code>int64 reqPid = 3;</code>
   */
  long getReqPid();

  /**
   * <code>.pb4server.QueryPlayerInfoAskVo queryPlayerInfoAskVo = 4;</code>
   */
  boolean hasQueryPlayerInfoAskVo();
  /**
   * <code>.pb4server.QueryPlayerInfoAskVo queryPlayerInfoAskVo = 4;</code>
   */
  pb4server.QueryPlayerInfoAskVo getQueryPlayerInfoAskVo();
  /**
   * <code>.pb4server.QueryPlayerInfoAskVo queryPlayerInfoAskVo = 4;</code>
   */
  pb4server.QueryPlayerInfoAskVoOrBuilder getQueryPlayerInfoAskVoOrBuilder();
}
