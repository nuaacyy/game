// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface AlliancePublishTopicOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.AlliancePublishTopic)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *主题类型：40-联盟全体成员 41-白虎团邮件 42-青龙团邮件 43-玄武团邮件 44-朱雀团邮件
   * </pre>
   *
   * <code>required int32 type = 1;</code>
   */
  boolean hasType();
  /**
   * <pre>
   *主题类型：40-联盟全体成员 41-白虎团邮件 42-青龙团邮件 43-玄武团邮件 44-朱雀团邮件
   * </pre>
   *
   * <code>required int32 type = 1;</code>
   */
  int getType();

  /**
   * <pre>
   *发布标题
   * </pre>
   *
   * <code>required string title = 2;</code>
   */
  boolean hasTitle();
  /**
   * <pre>
   *发布标题
   * </pre>
   *
   * <code>required string title = 2;</code>
   */
  java.lang.String getTitle();
  /**
   * <pre>
   *发布标题
   * </pre>
   *
   * <code>required string title = 2;</code>
   */
  com.google.protobuf.ByteString
      getTitleBytes();

  /**
   * <pre>
   *发布内容
   * </pre>
   *
   * <code>required string message = 3;</code>
   */
  boolean hasMessage();
  /**
   * <pre>
   *发布内容
   * </pre>
   *
   * <code>required string message = 3;</code>
   */
  java.lang.String getMessage();
  /**
   * <pre>
   *发布内容
   * </pre>
   *
   * <code>required string message = 3;</code>
   */
  com.google.protobuf.ByteString
      getMessageBytes();
}