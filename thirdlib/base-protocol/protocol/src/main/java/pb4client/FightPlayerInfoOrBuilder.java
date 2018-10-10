// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface FightPlayerInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.FightPlayerInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *玩家名字
   * </pre>
   *
   * <code>optional string name = 2;</code>
   */
  boolean hasName();
  /**
   * <pre>
   *玩家名字
   * </pre>
   *
   * <code>optional string name = 2;</code>
   */
  java.lang.String getName();
  /**
   * <pre>
   *玩家名字
   * </pre>
   *
   * <code>optional string name = 2;</code>
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <pre>
   *玩家头像
   * </pre>
   *
   * <code>optional int32 photo = 3;</code>
   */
  boolean hasPhoto();
  /**
   * <pre>
   *玩家头像
   * </pre>
   *
   * <code>optional int32 photo = 3;</code>
   */
  int getPhoto();

  /**
   * <pre>
   *玩家坐标X
   * </pre>
   *
   * <code>optional int32 posX = 4;</code>
   */
  boolean hasPosX();
  /**
   * <pre>
   *玩家坐标X
   * </pre>
   *
   * <code>optional int32 posX = 4;</code>
   */
  int getPosX();

  /**
   * <pre>
   *玩家坐标Y
   * </pre>
   *
   * <code>optional int32 posY = 5;</code>
   */
  boolean hasPosY();
  /**
   * <pre>
   *玩家坐标Y
   * </pre>
   *
   * <code>optional int32 posY = 5;</code>
   */
  int getPosY();

  /**
   * <pre>
   *玩家等级
   * </pre>
   *
   * <code>optional int32 level = 6;</code>
   */
  boolean hasLevel();
  /**
   * <pre>
   *玩家等级
   * </pre>
   *
   * <code>optional int32 level = 6;</code>
   */
  int getLevel();

  /**
   * <pre>
   *玩家Vip等级
   * </pre>
   *
   * <code>optional int32 vipLevel = 7;</code>
   */
  boolean hasVipLevel();
  /**
   * <pre>
   *玩家Vip等级
   * </pre>
   *
   * <code>optional int32 vipLevel = 7;</code>
   */
  int getVipLevel();

  /**
   * <pre>
   *玩家ID
   * </pre>
   *
   * <code>optional int64 id = 8;</code>
   */
  boolean hasId();
  /**
   * <pre>
   *玩家ID
   * </pre>
   *
   * <code>optional int64 id = 8;</code>
   */
  long getId();

  /**
   * <pre>
   *联盟ID
   * </pre>
   *
   * <code>optional int64 allianceId = 9;</code>
   */
  boolean hasAllianceId();
  /**
   * <pre>
   *联盟ID
   * </pre>
   *
   * <code>optional int64 allianceId = 9;</code>
   */
  long getAllianceId();

  /**
   * <pre>
   *联盟名称
   * </pre>
   *
   * <code>optional string allianceName = 10;</code>
   */
  boolean hasAllianceName();
  /**
   * <pre>
   *联盟名称
   * </pre>
   *
   * <code>optional string allianceName = 10;</code>
   */
  java.lang.String getAllianceName();
  /**
   * <pre>
   *联盟名称
   * </pre>
   *
   * <code>optional string allianceName = 10;</code>
   */
  com.google.protobuf.ByteString
      getAllianceNameBytes();

  /**
   * <pre>
   *联盟简称
   * </pre>
   *
   * <code>optional string allianceShortName = 11;</code>
   */
  boolean hasAllianceShortName();
  /**
   * <pre>
   *联盟简称
   * </pre>
   *
   * <code>optional string allianceShortName = 11;</code>
   */
  java.lang.String getAllianceShortName();
  /**
   * <pre>
   *联盟简称
   * </pre>
   *
   * <code>optional string allianceShortName = 11;</code>
   */
  com.google.protobuf.ByteString
      getAllianceShortNameBytes();

  /**
   * <pre>
   * 联盟阶级
   * </pre>
   *
   * <code>optional int32 allianceRnum = 12;</code>
   */
  boolean hasAllianceRnum();
  /**
   * <pre>
   * 联盟阶级
   * </pre>
   *
   * <code>optional int32 allianceRnum = 12;</code>
   */
  int getAllianceRnum();

  /**
   * <pre>
   *国家官职
   * </pre>
   *
   * <code>optional int32 countryPos = 13;</code>
   */
  boolean hasCountryPos();
  /**
   * <pre>
   *国家官职
   * </pre>
   *
   * <code>optional int32 countryPos = 13;</code>
   */
  int getCountryPos();
}