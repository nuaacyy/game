// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface QueryBuildCityRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.QueryBuildCityRt)
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
   * X
   * </pre>
   *
   * <code>optional int32 x = 2;</code>
   */
  boolean hasX();
  /**
   * <pre>
   * X
   * </pre>
   *
   * <code>optional int32 x = 2;</code>
   */
  int getX();

  /**
   * <pre>
   * Y
   * </pre>
   *
   * <code>optional int32 y = 3;</code>
   */
  boolean hasY();
  /**
   * <pre>
   * Y
   * </pre>
   *
   * <code>optional int32 y = 3;</code>
   */
  int getY();

  /**
   * <code>repeated .client2server.QueryBuildCityInfo queryBuildCityInfo = 4;</code>
   */
  java.util.List<pb4client.QueryBuildCityInfo> 
      getQueryBuildCityInfoList();
  /**
   * <code>repeated .client2server.QueryBuildCityInfo queryBuildCityInfo = 4;</code>
   */
  pb4client.QueryBuildCityInfo getQueryBuildCityInfo(int index);
  /**
   * <code>repeated .client2server.QueryBuildCityInfo queryBuildCityInfo = 4;</code>
   */
  int getQueryBuildCityInfoCount();
  /**
   * <code>repeated .client2server.QueryBuildCityInfo queryBuildCityInfo = 4;</code>
   */
  java.util.List<? extends pb4client.QueryBuildCityInfoOrBuilder> 
      getQueryBuildCityInfoOrBuilderList();
  /**
   * <code>repeated .client2server.QueryBuildCityInfo queryBuildCityInfo = 4;</code>
   */
  pb4client.QueryBuildCityInfoOrBuilder getQueryBuildCityInfoOrBuilder(
      int index);
}
