// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface AddMarkOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.AddMark)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required int32 aimsX = 1;</code>
   */
  boolean hasAimsX();
  /**
   * <code>required int32 aimsX = 1;</code>
   */
  int getAimsX();

  /**
   * <code>required int32 aimsY = 2;</code>
   */
  boolean hasAimsY();
  /**
   * <code>required int32 aimsY = 2;</code>
   */
  int getAimsY();

  /**
   * <pre>
   * 服务器区号
   * </pre>
   *
   * <code>required int32 areaNo = 3;</code>
   */
  boolean hasAreaNo();
  /**
   * <pre>
   * 服务器区号
   * </pre>
   *
   * <code>required int32 areaNo = 3;</code>
   */
  int getAreaNo();

  /**
   * <pre>
   * 分组类型
   * </pre>
   *
   * <code>required int32 group = 4;</code>
   */
  boolean hasGroup();
  /**
   * <pre>
   * 分组类型
   * </pre>
   *
   * <code>required int32 group = 4;</code>
   */
  int getGroup();

  /**
   * <pre>
   * 名字
   * </pre>
   *
   * <code>required string name = 5;</code>
   */
  boolean hasName();
  /**
   * <pre>
   * 名字
   * </pre>
   *
   * <code>required string name = 5;</code>
   */
  java.lang.String getName();
  /**
   * <pre>
   * 名字
   * </pre>
   *
   * <code>required string name = 5;</code>
   */
  com.google.protobuf.ByteString
      getNameBytes();
}
