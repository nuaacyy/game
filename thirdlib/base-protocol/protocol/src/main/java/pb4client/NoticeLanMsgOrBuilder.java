// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface NoticeLanMsgOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.NoticeLanMsg)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required string lanId = 1;</code>
   */
  boolean hasLanId();
  /**
   * <code>required string lanId = 1;</code>
   */
  java.lang.String getLanId();
  /**
   * <code>required string lanId = 1;</code>
   */
  com.google.protobuf.ByteString
      getLanIdBytes();

  /**
   * <code>repeated string params = 2;</code>
   */
  java.util.List<java.lang.String>
      getParamsList();
  /**
   * <code>repeated string params = 2;</code>
   */
  int getParamsCount();
  /**
   * <code>repeated string params = 2;</code>
   */
  java.lang.String getParams(int index);
  /**
   * <code>repeated string params = 2;</code>
   */
  com.google.protobuf.ByteString
      getParamsBytes(int index);
}
