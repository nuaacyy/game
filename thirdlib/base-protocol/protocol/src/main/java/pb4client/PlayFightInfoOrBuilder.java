// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface PlayFightInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.PlayFightInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *简单战报
   * </pre>
   *
   * <code>required .client2server.EasyFightInfo easyFightInfo = 1;</code>
   */
  boolean hasEasyFightInfo();
  /**
   * <pre>
   *简单战报
   * </pre>
   *
   * <code>required .client2server.EasyFightInfo easyFightInfo = 1;</code>
   */
  pb4client.EasyFightInfo getEasyFightInfo();
  /**
   * <pre>
   *简单战报
   * </pre>
   *
   * <code>required .client2server.EasyFightInfo easyFightInfo = 1;</code>
   */
  pb4client.EasyFightInfoOrBuilder getEasyFightInfoOrBuilder();

  /**
   * <pre>
   *战报
   * </pre>
   *
   * <code>required string detailFightInfo = 2;</code>
   */
  boolean hasDetailFightInfo();
  /**
   * <pre>
   *战报
   * </pre>
   *
   * <code>required string detailFightInfo = 2;</code>
   */
  java.lang.String getDetailFightInfo();
  /**
   * <pre>
   *战报
   * </pre>
   *
   * <code>required string detailFightInfo = 2;</code>
   */
  com.google.protobuf.ByteString
      getDetailFightInfoBytes();
}
