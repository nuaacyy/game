// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface SeleteAllianceActivityInfosRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.SeleteAllianceActivityInfosRt)
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
   * 活动ID
   * </pre>
   *
   * <code>repeated .client2server.AllianceActivityInfo allianceActivityInfoss = 2;</code>
   */
  java.util.List<pb4client.AllianceActivityInfo> 
      getAllianceActivityInfossList();
  /**
   * <pre>
   * 活动ID
   * </pre>
   *
   * <code>repeated .client2server.AllianceActivityInfo allianceActivityInfoss = 2;</code>
   */
  pb4client.AllianceActivityInfo getAllianceActivityInfoss(int index);
  /**
   * <pre>
   * 活动ID
   * </pre>
   *
   * <code>repeated .client2server.AllianceActivityInfo allianceActivityInfoss = 2;</code>
   */
  int getAllianceActivityInfossCount();
  /**
   * <pre>
   * 活动ID
   * </pre>
   *
   * <code>repeated .client2server.AllianceActivityInfo allianceActivityInfoss = 2;</code>
   */
  java.util.List<? extends pb4client.AllianceActivityInfoOrBuilder> 
      getAllianceActivityInfossOrBuilderList();
  /**
   * <pre>
   * 活动ID
   * </pre>
   *
   * <code>repeated .client2server.AllianceActivityInfo allianceActivityInfoss = 2;</code>
   */
  pb4client.AllianceActivityInfoOrBuilder getAllianceActivityInfossOrBuilder(
      int index);
}
