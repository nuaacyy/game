// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface UpgradeTalentRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.UpgradeTalentRt)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 返回值
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  boolean hasRt();
  /**
   * <pre>
   * 返回值
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  int getRt();

  /**
   * <pre>
   *天赋Id
   * </pre>
   *
   * <code>optional int32 talentId = 2;</code>
   */
  boolean hasTalentId();
  /**
   * <pre>
   *天赋Id
   * </pre>
   *
   * <code>optional int32 talentId = 2;</code>
   */
  int getTalentId();

  /**
   * <pre>
   *天赋目标等级
   * </pre>
   *
   * <code>optional int32 targetTalentLevel = 3;</code>
   */
  boolean hasTargetTalentLevel();
  /**
   * <pre>
   *天赋目标等级
   * </pre>
   *
   * <code>optional int32 targetTalentLevel = 3;</code>
   */
  int getTargetTalentLevel();
}
