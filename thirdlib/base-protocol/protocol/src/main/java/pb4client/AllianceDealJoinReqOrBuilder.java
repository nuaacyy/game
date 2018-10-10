// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface AllianceDealJoinReqOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.AllianceDealJoinReq)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *待处理玩家ID
   * </pre>
   *
   * <code>required int64 reqPlayerId = 1;</code>
   */
  boolean hasReqPlayerId();
  /**
   * <pre>
   *待处理玩家ID
   * </pre>
   *
   * <code>required int64 reqPlayerId = 1;</code>
   */
  long getReqPlayerId();

  /**
   * <pre>
   *1-同意加入；2-拒绝加入
   * </pre>
   *
   * <code>required int32 reqType = 2;</code>
   */
  boolean hasReqType();
  /**
   * <pre>
   *1-同意加入；2-拒绝加入
   * </pre>
   *
   * <code>required int32 reqType = 2;</code>
   */
  int getReqType();
}
