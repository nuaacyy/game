// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

public interface QueryAllianceListAskReqOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.QueryAllianceListAskReq)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 查看者所属地图服ID
   * </pre>
   *
   * <code>int32 playerMapAreaNo = 1;</code>
   */
  int getPlayerMapAreaNo();

  /**
   * <pre>
   * 联盟名字或者简称
   * </pre>
   *
   * <code>string allianceName = 2;</code>
   */
  java.lang.String getAllianceName();
  /**
   * <pre>
   * 联盟名字或者简称
   * </pre>
   *
   * <code>string allianceName = 2;</code>
   */
  com.google.protobuf.ByteString
      getAllianceNameBytes();

  /**
   * <pre>
   * 所选语言
   * </pre>
   *
   * <code>int32 allianceLan = 3;</code>
   */
  int getAllianceLan();
}
