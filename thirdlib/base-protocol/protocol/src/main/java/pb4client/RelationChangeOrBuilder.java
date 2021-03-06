// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface RelationChangeOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.RelationChange)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *变化类型:1-玩家(pid)加入联盟(aid) 2-玩家(pid)退出联盟(aid) 3-玩家(pid)被联盟(aid)沦陷 4-玩家(pid)从联盟(aid)脱离沦陷
   * </pre>
   *
   * <code>required int32 type = 1;</code>
   */
  boolean hasType();
  /**
   * <pre>
   *变化类型:1-玩家(pid)加入联盟(aid) 2-玩家(pid)退出联盟(aid) 3-玩家(pid)被联盟(aid)沦陷 4-玩家(pid)从联盟(aid)脱离沦陷
   * </pre>
   *
   * <code>required int32 type = 1;</code>
   */
  int getType();

  /**
   * <pre>
   *玩家ID
   * </pre>
   *
   * <code>required int64 pid = 2;</code>
   */
  boolean hasPid();
  /**
   * <pre>
   *玩家ID
   * </pre>
   *
   * <code>required int64 pid = 2;</code>
   */
  long getPid();

  /**
   * <pre>
   *联盟ID
   * </pre>
   *
   * <code>required int64 aid = 3;</code>
   */
  boolean hasAid();
  /**
   * <pre>
   *联盟ID
   * </pre>
   *
   * <code>required int64 aid = 3;</code>
   */
  long getAid();

  /**
   * <pre>
   *联盟名称
   * </pre>
   *
   * <code>required string name = 4;</code>
   */
  boolean hasName();
  /**
   * <pre>
   *联盟名称
   * </pre>
   *
   * <code>required string name = 4;</code>
   */
  java.lang.String getName();
  /**
   * <pre>
   *联盟名称
   * </pre>
   *
   * <code>required string name = 4;</code>
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <pre>
   *联盟简称
   * </pre>
   *
   * <code>required string shortName = 5;</code>
   */
  boolean hasShortName();
  /**
   * <pre>
   *联盟简称
   * </pre>
   *
   * <code>required string shortName = 5;</code>
   */
  java.lang.String getShortName();
  /**
   * <pre>
   *联盟简称
   * </pre>
   *
   * <code>required string shortName = 5;</code>
   */
  com.google.protobuf.ByteString
      getShortNameBytes();
}
