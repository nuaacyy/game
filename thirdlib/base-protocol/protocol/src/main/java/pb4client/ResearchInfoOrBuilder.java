// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface ResearchInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.ResearchInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 科技ID
   * </pre>
   *
   * <code>required int32 researchId = 1;</code>
   */
  boolean hasResearchId();
  /**
   * <pre>
   * 科技ID
   * </pre>
   *
   * <code>required int32 researchId = 1;</code>
   */
  int getResearchId();

  /**
   * <pre>
   * 科技等级
   * </pre>
   *
   * <code>required int32 researchLv = 2;</code>
   */
  boolean hasResearchLv();
  /**
   * <pre>
   * 科技等级
   * </pre>
   *
   * <code>required int32 researchLv = 2;</code>
   */
  int getResearchLv();

  /**
   * <pre>
   * 科技完成时间
   * </pre>
   *
   * <code>required int32 researchOverTime = 3;</code>
   */
  boolean hasResearchOverTime();
  /**
   * <pre>
   * 科技完成时间
   * </pre>
   *
   * <code>required int32 researchOverTime = 3;</code>
   */
  int getResearchOverTime();

  /**
   * <pre>
   * 帮助里面的唯一ID
   * </pre>
   *
   * <code>required int64 helperId = 4;</code>
   */
  boolean hasHelperId();
  /**
   * <pre>
   * 帮助里面的唯一ID
   * </pre>
   *
   * <code>required int64 helperId = 4;</code>
   */
  long getHelperId();
}
