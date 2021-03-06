// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

public interface SimpleFightReportVoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.SimpleFightReportVo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 魔物,集结战报,侦察等,参考下面的reportType
   * </pre>
   *
   * <code>int32 reportType = 1;</code>
   */
  int getReportType();

  /**
   * <pre>
   * 左边人的名字
   * </pre>
   *
   * <code>string mainPlayer = 2;</code>
   */
  java.lang.String getMainPlayer();
  /**
   * <pre>
   * 左边人的名字
   * </pre>
   *
   * <code>string mainPlayer = 2;</code>
   */
  com.google.protobuf.ByteString
      getMainPlayerBytes();

  /**
   * <pre>
   * 左边人的联盟
   * </pre>
   *
   * <code>string mainPlayerAlliance = 3;</code>
   */
  java.lang.String getMainPlayerAlliance();
  /**
   * <pre>
   * 左边人的联盟
   * </pre>
   *
   * <code>string mainPlayerAlliance = 3;</code>
   */
  com.google.protobuf.ByteString
      getMainPlayerAllianceBytes();

  /**
   * <pre>
   * 攻击或防守 0进攻 1防守
   * </pre>
   *
   * <code>int32 atkOrDef = 4;</code>
   */
  int getAtkOrDef();

  /**
   * <pre>
   * 右边魔物或者对手的名字
   * </pre>
   *
   * <code>string targetName = 5;</code>
   */
  java.lang.String getTargetName();
  /**
   * <pre>
   * 右边魔物或者对手的名字
   * </pre>
   *
   * <code>string targetName = 5;</code>
   */
  com.google.protobuf.ByteString
      getTargetNameBytes();

  /**
   * <pre>
   * 右边魔物等级或者对手的联盟名
   * </pre>
   *
   * <code>string allianceOrLv = 6;</code>
   */
  java.lang.String getAllianceOrLv();
  /**
   * <pre>
   * 右边魔物等级或者对手的联盟名
   * </pre>
   *
   * <code>string allianceOrLv = 6;</code>
   */
  com.google.protobuf.ByteString
      getAllianceOrLvBytes();

  /**
   * <pre>
   * report战报id
   * </pre>
   *
   * <code>int64 reportId = 7;</code>
   */
  long getReportId();

  /**
   * <pre>
   * 左边人头像模板
   * </pre>
   *
   * <code>int32 mainIconId = 8;</code>
   */
  int getMainIconId();

  /**
   * <pre>
   * 右边人头像模板
   * </pre>
   *
   * <code>int32 iconId = 9;</code>
   */
  int getIconId();

  /**
   * <pre>
   * 魔物模板Id
   * </pre>
   *
   * <code>int32 monsterId = 10;</code>
   */
  int getMonsterId();

  /**
   * <pre>
   * 世界服id
   * </pre>
   *
   * <code>int64 world = 11;</code>
   */
  long getWorld();
}
