// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface NoticeOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.Notice)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *邮件结构体的读取类型 1-读lan (一般都是游戏内发的) 2-直接读取内容(一般都是后台发的)
   * </pre>
   *
   * <code>required int32 readType = 1;</code>
   */
  boolean hasReadType();
  /**
   * <pre>
   *邮件结构体的读取类型 1-读lan (一般都是游戏内发的) 2-直接读取内容(一般都是后台发的)
   * </pre>
   *
   * <code>required int32 readType = 1;</code>
   */
  int getReadType();

  /**
   * <pre>
   *公告内容lanId
   * </pre>
   *
   * <code>required string noticeLanId = 2;</code>
   */
  boolean hasNoticeLanId();
  /**
   * <pre>
   *公告内容lanId
   * </pre>
   *
   * <code>required string noticeLanId = 2;</code>
   */
  java.lang.String getNoticeLanId();
  /**
   * <pre>
   *公告内容lanId
   * </pre>
   *
   * <code>required string noticeLanId = 2;</code>
   */
  com.google.protobuf.ByteString
      getNoticeLanIdBytes();

  /**
   * <pre>
   *公告参数
   * </pre>
   *
   * <code>repeated string noticeParams = 3;</code>
   */
  java.util.List<java.lang.String>
      getNoticeParamsList();
  /**
   * <pre>
   *公告参数
   * </pre>
   *
   * <code>repeated string noticeParams = 3;</code>
   */
  int getNoticeParamsCount();
  /**
   * <pre>
   *公告参数
   * </pre>
   *
   * <code>repeated string noticeParams = 3;</code>
   */
  java.lang.String getNoticeParams(int index);
  /**
   * <pre>
   *公告参数
   * </pre>
   *
   * <code>repeated string noticeParams = 3;</code>
   */
  com.google.protobuf.ByteString
      getNoticeParamsBytes(int index);
}
