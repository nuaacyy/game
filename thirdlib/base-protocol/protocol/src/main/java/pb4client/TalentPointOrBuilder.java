// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface TalentPointOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.TalentPoint)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *天赋类别
   * </pre>
   *
   * <code>required int32 talentType = 1;</code>
   */
  boolean hasTalentType();
  /**
   * <pre>
   *天赋类别
   * </pre>
   *
   * <code>required int32 talentType = 1;</code>
   */
  int getTalentType();

  /**
   * <pre>
   *天赋点数
   * </pre>
   *
   * <code>required int32 leftTalentPoint = 2;</code>
   */
  boolean hasLeftTalentPoint();
  /**
   * <pre>
   *天赋点数
   * </pre>
   *
   * <code>required int32 leftTalentPoint = 2;</code>
   */
  int getLeftTalentPoint();
}