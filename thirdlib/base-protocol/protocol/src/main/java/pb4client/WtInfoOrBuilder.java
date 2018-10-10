// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface WtInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.WtInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *模板ID
   * </pre>
   *
   * <code>required int32 taskProto_id = 1;</code>
   */
  boolean hasTaskProtoId();
  /**
   * <pre>
   *模板ID
   * </pre>
   *
   * <code>required int32 taskProto_id = 1;</code>
   */
  int getTaskProtoId();

  /**
   * <pre>
   *当前状态 0-进行中 1-结束并且成功完成 2-结束但是并没有成功完成
   * </pre>
   *
   * <code>required int32 taskNowState = 2;</code>
   */
  boolean hasTaskNowState();
  /**
   * <pre>
   *当前状态 0-进行中 1-结束并且成功完成 2-结束但是并没有成功完成
   * </pre>
   *
   * <code>required int32 taskNowState = 2;</code>
   */
  int getTaskNowState();

  /**
   * <pre>
   *当前完成度
   * </pre>
   *
   * <code>required int32 taskFinish = 3;</code>
   */
  boolean hasTaskFinish();
  /**
   * <pre>
   *当前完成度
   * </pre>
   *
   * <code>required int32 taskFinish = 3;</code>
   */
  int getTaskFinish();

  /**
   * <pre>
   *结束时间
   * </pre>
   *
   * <code>required int32 endTime = 4;</code>
   */
  boolean hasEndTime();
  /**
   * <pre>
   *结束时间
   * </pre>
   *
   * <code>required int32 endTime = 4;</code>
   */
  int getEndTime();

  /**
   * <pre>
   *完成时间
   * </pre>
   *
   * <code>required int32 finishTime = 5;</code>
   */
  boolean hasFinishTime();
  /**
   * <pre>
   *完成时间
   * </pre>
   *
   * <code>required int32 finishTime = 5;</code>
   */
  int getFinishTime();

  /**
   * <pre>
   *当前完成者帮派名字集合
   * </pre>
   *
   * <code>repeated string nowFinishInfo = 6;</code>
   */
  java.util.List<java.lang.String>
      getNowFinishInfoList();
  /**
   * <pre>
   *当前完成者帮派名字集合
   * </pre>
   *
   * <code>repeated string nowFinishInfo = 6;</code>
   */
  int getNowFinishInfoCount();
  /**
   * <pre>
   *当前完成者帮派名字集合
   * </pre>
   *
   * <code>repeated string nowFinishInfo = 6;</code>
   */
  java.lang.String getNowFinishInfo(int index);
  /**
   * <pre>
   *当前完成者帮派名字集合
   * </pre>
   *
   * <code>repeated string nowFinishInfo = 6;</code>
   */
  com.google.protobuf.ByteString
      getNowFinishInfoBytes(int index);
}