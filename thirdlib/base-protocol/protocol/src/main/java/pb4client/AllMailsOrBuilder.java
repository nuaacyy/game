// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface AllMailsOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.AllMails)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *邮件类型 1、系统邮件 2、联盟邮件 3、收藏邮件
   * </pre>
   *
   * <code>required int32 mailType = 1;</code>
   */
  boolean hasMailType();
  /**
   * <pre>
   *邮件类型 1、系统邮件 2、联盟邮件 3、收藏邮件
   * </pre>
   *
   * <code>required int32 mailType = 1;</code>
   */
  int getMailType();

  /**
   * <pre>
   *页数，从1开始
   * </pre>
   *
   * <code>required int32 page = 2;</code>
   */
  boolean hasPage();
  /**
   * <pre>
   *页数，从1开始
   * </pre>
   *
   * <code>required int32 page = 2;</code>
   */
  int getPage();
}