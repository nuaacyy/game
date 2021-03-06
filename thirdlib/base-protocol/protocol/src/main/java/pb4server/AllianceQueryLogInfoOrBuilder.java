// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

public interface AllianceQueryLogInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.AllianceQueryLogInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *日志记录时间戳
   * </pre>
   *
   * <code>int32 dt = 1;</code>
   */
  int getDt();

  /**
   * <pre>
   *日志类型编号
   * </pre>
   *
   * <code>int32 typ = 2;</code>
   */
  int getTyp();

  /**
   * <pre>
   *参数数组
   * </pre>
   *
   * <code>repeated string lgs = 3;</code>
   */
  java.util.List<java.lang.String>
      getLgsList();
  /**
   * <pre>
   *参数数组
   * </pre>
   *
   * <code>repeated string lgs = 3;</code>
   */
  int getLgsCount();
  /**
   * <pre>
   *参数数组
   * </pre>
   *
   * <code>repeated string lgs = 3;</code>
   */
  java.lang.String getLgs(int index);
  /**
   * <pre>
   *参数数组
   * </pre>
   *
   * <code>repeated string lgs = 3;</code>
   */
  com.google.protobuf.ByteString
      getLgsBytes(int index);
}
