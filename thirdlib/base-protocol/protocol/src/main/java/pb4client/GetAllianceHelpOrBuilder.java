// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface GetAllianceHelpOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.GetAllianceHelp)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 帮助人的名字
   * </pre>
   *
   * <code>required string helpPlayerName = 1;</code>
   */
  boolean hasHelpPlayerName();
  /**
   * <pre>
   * 帮助人的名字
   * </pre>
   *
   * <code>required string helpPlayerName = 1;</code>
   */
  java.lang.String getHelpPlayerName();
  /**
   * <pre>
   * 帮助人的名字
   * </pre>
   *
   * <code>required string helpPlayerName = 1;</code>
   */
  com.google.protobuf.ByteString
      getHelpPlayerNameBytes();

  /**
   * <pre>
   * 被帮忙的类型
   * </pre>
   *
   * <code>required int32 helpType = 2;</code>
   */
  boolean hasHelpType();
  /**
   * <pre>
   * 被帮忙的类型
   * </pre>
   *
   * <code>required int32 helpType = 2;</code>
   */
  int getHelpType();
}
