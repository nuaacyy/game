// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface GetEasyFightInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.GetEasyFightInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *查看类型  1-普通战报  2-竞技场战报
   * </pre>
   *
   * <code>required int32 selectType = 1;</code>
   */
  boolean hasSelectType();
  /**
   * <pre>
   *查看类型  1-普通战报  2-竞技场战报
   * </pre>
   *
   * <code>required int32 selectType = 1;</code>
   */
  int getSelectType();

  /**
   * <pre>
   *最新的那封的ID 如果第一次进游戏.传0
   * </pre>
   *
   * <code>required int64 lastId = 2;</code>
   */
  boolean hasLastId();
  /**
   * <pre>
   *最新的那封的ID 如果第一次进游戏.传0
   * </pre>
   *
   * <code>required int64 lastId = 2;</code>
   */
  long getLastId();
}
