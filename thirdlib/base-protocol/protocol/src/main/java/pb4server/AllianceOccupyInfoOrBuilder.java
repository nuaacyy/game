// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

public interface AllianceOccupyInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.AllianceOccupyInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 联盟ID
   * </pre>
   *
   * <code>int64 allianceId = 1;</code>
   */
  long getAllianceId();

  /**
   * <pre>
   * 盟主名字
   * </pre>
   *
   * <code>string allianceMainMemberName = 2;</code>
   */
  java.lang.String getAllianceMainMemberName();
  /**
   * <pre>
   * 盟主名字
   * </pre>
   *
   * <code>string allianceMainMemberName = 2;</code>
   */
  com.google.protobuf.ByteString
      getAllianceMainMemberNameBytes();

  /**
   * <pre>
   * 联盟所属服务器
   * </pre>
   *
   * <code>int32 allianceAreaNo = 3;</code>
   */
  int getAllianceAreaNo();

  /**
   * <pre>
   * 联盟名字
   * </pre>
   *
   * <code>string allianceName = 4;</code>
   */
  java.lang.String getAllianceName();
  /**
   * <pre>
   * 联盟名字
   * </pre>
   *
   * <code>string allianceName = 4;</code>
   */
  com.google.protobuf.ByteString
      getAllianceNameBytes();

  /**
   * <pre>
   * 联盟简称
   * </pre>
   *
   * <code>string allianceShortName = 5;</code>
   */
  java.lang.String getAllianceShortName();
  /**
   * <pre>
   * 联盟简称
   * </pre>
   *
   * <code>string allianceShortName = 5;</code>
   */
  com.google.protobuf.ByteString
      getAllianceShortNameBytes();

  /**
   * <pre>
   * 占领的世界ID
   * </pre>
   *
   * <code>repeated int64 worldId = 6;</code>
   */
  java.util.List<java.lang.Long> getWorldIdList();
  /**
   * <pre>
   * 占领的世界ID
   * </pre>
   *
   * <code>repeated int64 worldId = 6;</code>
   */
  int getWorldIdCount();
  /**
   * <pre>
   * 占领的世界ID
   * </pre>
   *
   * <code>repeated int64 worldId = 6;</code>
   */
  long getWorldId(int index);
}
