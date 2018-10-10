// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface AllianceQueryMissionProRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.AllianceQueryMissionProRt)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *返回值
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  boolean hasRt();
  /**
   * <pre>
   *返回值
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  int getRt();

  /**
   * <pre>
   *联盟目标统计
   * </pre>
   *
   * <code>repeated .client2server.AllianceMissionProInfo pros = 2;</code>
   */
  java.util.List<pb4client.AllianceMissionProInfo> 
      getProsList();
  /**
   * <pre>
   *联盟目标统计
   * </pre>
   *
   * <code>repeated .client2server.AllianceMissionProInfo pros = 2;</code>
   */
  pb4client.AllianceMissionProInfo getPros(int index);
  /**
   * <pre>
   *联盟目标统计
   * </pre>
   *
   * <code>repeated .client2server.AllianceMissionProInfo pros = 2;</code>
   */
  int getProsCount();
  /**
   * <pre>
   *联盟目标统计
   * </pre>
   *
   * <code>repeated .client2server.AllianceMissionProInfo pros = 2;</code>
   */
  java.util.List<? extends pb4client.AllianceMissionProInfoOrBuilder> 
      getProsOrBuilderList();
  /**
   * <pre>
   *联盟目标统计
   * </pre>
   *
   * <code>repeated .client2server.AllianceMissionProInfo pros = 2;</code>
   */
  pb4client.AllianceMissionProInfoOrBuilder getProsOrBuilder(
      int index);

  /**
   * <pre>
   *自己的统计
   * </pre>
   *
   * <code>optional .client2server.AllianceMissionProInfo myPro = 3;</code>
   */
  boolean hasMyPro();
  /**
   * <pre>
   *自己的统计
   * </pre>
   *
   * <code>optional .client2server.AllianceMissionProInfo myPro = 3;</code>
   */
  pb4client.AllianceMissionProInfo getMyPro();
  /**
   * <pre>
   *自己的统计
   * </pre>
   *
   * <code>optional .client2server.AllianceMissionProInfo myPro = 3;</code>
   */
  pb4client.AllianceMissionProInfoOrBuilder getMyProOrBuilder();
}