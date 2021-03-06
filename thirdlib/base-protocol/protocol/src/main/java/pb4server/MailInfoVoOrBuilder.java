// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2home.proto

package pb4server;

public interface MailInfoVoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.MailInfoVo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *邮件结构体的读取类型 1-读lan (一般都是游戏内发的) 2-直接读取内容(一般都是后台发的)
   * </pre>
   *
   * <code>int32 readType = 1;</code>
   */
  int getReadType();

  /**
   * <pre>
   *邮件标题lanId
   * </pre>
   *
   * <code>string title = 2;</code>
   */
  java.lang.String getTitle();
  /**
   * <pre>
   *邮件标题lanId
   * </pre>
   *
   * <code>string title = 2;</code>
   */
  com.google.protobuf.ByteString
      getTitleBytes();

  /**
   * <pre>
   *邮件标题中参数
   * </pre>
   *
   * <code>repeated string titleParam = 3;</code>
   */
  java.util.List<java.lang.String>
      getTitleParamList();
  /**
   * <pre>
   *邮件标题中参数
   * </pre>
   *
   * <code>repeated string titleParam = 3;</code>
   */
  int getTitleParamCount();
  /**
   * <pre>
   *邮件标题中参数
   * </pre>
   *
   * <code>repeated string titleParam = 3;</code>
   */
  java.lang.String getTitleParam(int index);
  /**
   * <pre>
   *邮件标题中参数
   * </pre>
   *
   * <code>repeated string titleParam = 3;</code>
   */
  com.google.protobuf.ByteString
      getTitleParamBytes(int index);

  /**
   * <pre>
   *邮件内容lanId
   * </pre>
   *
   * <code>string message = 4;</code>
   */
  java.lang.String getMessage();
  /**
   * <pre>
   *邮件内容lanId
   * </pre>
   *
   * <code>string message = 4;</code>
   */
  com.google.protobuf.ByteString
      getMessageBytes();

  /**
   * <pre>
   *邮件内容中参数
   * </pre>
   *
   * <code>repeated string messageParam = 5;</code>
   */
  java.util.List<java.lang.String>
      getMessageParamList();
  /**
   * <pre>
   *邮件内容中参数
   * </pre>
   *
   * <code>repeated string messageParam = 5;</code>
   */
  int getMessageParamCount();
  /**
   * <pre>
   *邮件内容中参数
   * </pre>
   *
   * <code>repeated string messageParam = 5;</code>
   */
  java.lang.String getMessageParam(int index);
  /**
   * <pre>
   *邮件内容中参数
   * </pre>
   *
   * <code>repeated string messageParam = 5;</code>
   */
  com.google.protobuf.ByteString
      getMessageParamBytes(int index);
}
