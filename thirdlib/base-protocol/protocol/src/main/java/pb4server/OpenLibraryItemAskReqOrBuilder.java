// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2home.proto

package pb4server;

public interface OpenLibraryItemAskReqOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.OpenLibraryItemAskReq)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 类型常量
   * </pre>
   *
   * <code>int32 type = 1;</code>
   */
  int getType();

  /**
   * <pre>
   * 怪物图鉴-推图instance表id/道具图鉴-prop表id
   * </pre>
   *
   * <code>int32 protoId = 2;</code>
   */
  int getProtoId();

  /**
   * <pre>
   * 类型是魔物时，表示是否杀死了魔物
   * </pre>
   *
   * <code>int32 kill = 3;</code>
   */
  int getKill();
}
