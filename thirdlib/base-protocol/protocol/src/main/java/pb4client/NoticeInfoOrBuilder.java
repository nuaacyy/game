// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface NoticeInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.NoticeInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *公告内容
   * </pre>
   *
   * <code>required .client2server.Notice noticeInfos = 1;</code>
   */
  boolean hasNoticeInfos();
  /**
   * <pre>
   *公告内容
   * </pre>
   *
   * <code>required .client2server.Notice noticeInfos = 1;</code>
   */
  pb4client.Notice getNoticeInfos();
  /**
   * <pre>
   *公告内容
   * </pre>
   *
   * <code>required .client2server.Notice noticeInfos = 1;</code>
   */
  pb4client.NoticeOrBuilder getNoticeInfosOrBuilder();

  /**
   * <pre>
   *公告类型（1.屏幕中央 2.聊天框 3.屏幕中央+聊天框）
   * </pre>
   *
   * <code>required int32 noticeType = 2;</code>
   */
  boolean hasNoticeType();
  /**
   * <pre>
   *公告类型（1.屏幕中央 2.聊天框 3.屏幕中央+聊天框）
   * </pre>
   *
   * <code>required int32 noticeType = 2;</code>
   */
  int getNoticeType();
}
