// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface OneFightInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.OneFightInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *简单战报
   * </pre>
   *
   * <code>required .client2server.EasyFightInfo easyFightInfo = 2;</code>
   */
  boolean hasEasyFightInfo();
  /**
   * <pre>
   *简单战报
   * </pre>
   *
   * <code>required .client2server.EasyFightInfo easyFightInfo = 2;</code>
   */
  pb4client.EasyFightInfo getEasyFightInfo();
  /**
   * <pre>
   *简单战报
   * </pre>
   *
   * <code>required .client2server.EasyFightInfo easyFightInfo = 2;</code>
   */
  pb4client.EasyFightInfoOrBuilder getEasyFightInfoOrBuilder();

  /**
   * <pre>
   *详细战报
   * </pre>
   *
   * <code>required string detailFightInfo = 3;</code>
   */
  boolean hasDetailFightInfo();
  /**
   * <pre>
   *详细战报
   * </pre>
   *
   * <code>required string detailFightInfo = 3;</code>
   */
  java.lang.String getDetailFightInfo();
  /**
   * <pre>
   *详细战报
   * </pre>
   *
   * <code>required string detailFightInfo = 3;</code>
   */
  com.google.protobuf.ByteString
      getDetailFightInfoBytes();
}
