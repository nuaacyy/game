// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

public interface ResetAlliancePosAskReqOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.ResetAlliancePosAskReq)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 被设置职位人ID
   * </pre>
   *
   * <code>int64 posPlayerId = 1;</code>
   */
  long getPosPlayerId();

  /**
   * <pre>
   * 职位
   * </pre>
   *
   * <code>repeated int32 posId = 2;</code>
   */
  java.util.List<java.lang.Integer> getPosIdList();
  /**
   * <pre>
   * 职位
   * </pre>
   *
   * <code>repeated int32 posId = 2;</code>
   */
  int getPosIdCount();
  /**
   * <pre>
   * 职位
   * </pre>
   *
   * <code>repeated int32 posId = 2;</code>
   */
  int getPosId(int index);
}
