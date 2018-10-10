// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface GetDetailFightInfoRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.GetDetailFightInfoRt)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required int32 rt = 1;</code>
   */
  boolean hasRt();
  /**
   * <code>required int32 rt = 1;</code>
   */
  int getRt();

  /**
   * <pre>
   *战报ID
   * </pre>
   *
   * <code>optional int64 reportId = 2;</code>
   */
  boolean hasReportId();
  /**
   * <pre>
   *战报ID
   * </pre>
   *
   * <code>optional int64 reportId = 2;</code>
   */
  long getReportId();

  /**
   * <code>optional int32 reportType = 3;</code>
   */
  boolean hasReportType();
  /**
   * <code>optional int32 reportType = 3;</code>
   */
  int getReportType();

  /**
   * <code>optional string soliderFightReport = 4;</code>
   */
  boolean hasSoliderFightReport();
  /**
   * <code>optional string soliderFightReport = 4;</code>
   */
  java.lang.String getSoliderFightReport();
  /**
   * <code>optional string soliderFightReport = 4;</code>
   */
  com.google.protobuf.ByteString
      getSoliderFightReportBytes();

  /**
   * <code>repeated .client2server.HeroFightReport heroFightReport = 5;</code>
   */
  java.util.List<pb4client.HeroFightReport> 
      getHeroFightReportList();
  /**
   * <code>repeated .client2server.HeroFightReport heroFightReport = 5;</code>
   */
  pb4client.HeroFightReport getHeroFightReport(int index);
  /**
   * <code>repeated .client2server.HeroFightReport heroFightReport = 5;</code>
   */
  int getHeroFightReportCount();
  /**
   * <code>repeated .client2server.HeroFightReport heroFightReport = 5;</code>
   */
  java.util.List<? extends pb4client.HeroFightReportOrBuilder> 
      getHeroFightReportOrBuilderList();
  /**
   * <code>repeated .client2server.HeroFightReport heroFightReport = 5;</code>
   */
  pb4client.HeroFightReportOrBuilder getHeroFightReportOrBuilder(
      int index);
}
