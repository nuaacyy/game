// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

public interface QueryAllianceRankAskReqOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.QueryAllianceRankAskReq)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 排行类型
   * </pre>
   *
   * <code>int32 rankType = 1;</code>
   */
  int getRankType();

  /**
   * <code>int32 page = 2;</code>
   */
  int getPage();

  /**
   * <code>int32 num = 3;</code>
   */
  int getNum();

  /**
   * <pre>
   *需要查询的玩家服
   * </pre>
   *
   * <code>int32 areaNo = 4;</code>
   */
  int getAreaNo();
}
