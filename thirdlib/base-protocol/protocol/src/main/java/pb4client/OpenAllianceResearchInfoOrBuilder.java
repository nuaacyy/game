// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface OpenAllianceResearchInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.OpenAllianceResearchInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 联盟科技ID
   * </pre>
   *
   * <code>required int32 allianceResearchId = 1;</code>
   */
  boolean hasAllianceResearchId();
  /**
   * <pre>
   * 联盟科技ID
   * </pre>
   *
   * <code>required int32 allianceResearchId = 1;</code>
   */
  int getAllianceResearchId();

  /**
   * <pre>
   * 联盟科技等级
   * </pre>
   *
   * <code>required int32 allianceResearchLv = 2;</code>
   */
  boolean hasAllianceResearchLv();
  /**
   * <pre>
   * 联盟科技等级
   * </pre>
   *
   * <code>required int32 allianceResearchLv = 2;</code>
   */
  int getAllianceResearchLv();

  /**
   * <pre>
   * 联盟科技经验
   * </pre>
   *
   * <code>required int32 allianceResearchExp = 3;</code>
   */
  boolean hasAllianceResearchExp();
  /**
   * <pre>
   * 联盟科技经验
   * </pre>
   *
   * <code>required int32 allianceResearchExp = 3;</code>
   */
  int getAllianceResearchExp();

  /**
   * <pre>
   * 是否优先研发  0-否 1-是
   * </pre>
   *
   * <code>required int32 isFirst = 4;</code>
   */
  boolean hasIsFirst();
  /**
   * <pre>
   * 是否优先研发  0-否 1-是
   * </pre>
   *
   * <code>required int32 isFirst = 4;</code>
   */
  int getIsFirst();
}
