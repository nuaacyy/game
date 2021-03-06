// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface ChatRoomMessageOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.ChatRoomMessage)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 消息唯一Id
   * </pre>
   *
   * <code>required int64 messageId = 1;</code>
   */
  boolean hasMessageId();
  /**
   * <pre>
   * 消息唯一Id
   * </pre>
   *
   * <code>required int64 messageId = 1;</code>
   */
  long getMessageId();

  /**
   * <pre>
   * 聊天室唯一Id
   * </pre>
   *
   * <code>required int64 chatRoomId = 2;</code>
   */
  boolean hasChatRoomId();
  /**
   * <pre>
   * 聊天室唯一Id
   * </pre>
   *
   * <code>required int64 chatRoomId = 2;</code>
   */
  long getChatRoomId();

  /**
   * <pre>
   * 说话人
   * </pre>
   *
   * <code>required string name = 3;</code>
   */
  boolean hasName();
  /**
   * <pre>
   * 说话人
   * </pre>
   *
   * <code>required string name = 3;</code>
   */
  java.lang.String getName();
  /**
   * <pre>
   * 说话人
   * </pre>
   *
   * <code>required string name = 3;</code>
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <pre>
   * 角色昵称
   * </pre>
   *
   * <code>required string playerShortName = 4;</code>
   */
  boolean hasPlayerShortName();
  /**
   * <pre>
   * 角色昵称
   * </pre>
   *
   * <code>required string playerShortName = 4;</code>
   */
  java.lang.String getPlayerShortName();
  /**
   * <pre>
   * 角色昵称
   * </pre>
   *
   * <code>required string playerShortName = 4;</code>
   */
  com.google.protobuf.ByteString
      getPlayerShortNameBytes();

  /**
   * <pre>
   * 联盟名称
   * </pre>
   *
   * <code>required string allianceName = 5;</code>
   */
  boolean hasAllianceName();
  /**
   * <pre>
   * 联盟名称
   * </pre>
   *
   * <code>required string allianceName = 5;</code>
   */
  java.lang.String getAllianceName();
  /**
   * <pre>
   * 联盟名称
   * </pre>
   *
   * <code>required string allianceName = 5;</code>
   */
  com.google.protobuf.ByteString
      getAllianceNameBytes();

  /**
   * <pre>
   * 联盟简称
   * </pre>
   *
   * <code>required string allianceShortName = 6;</code>
   */
  boolean hasAllianceShortName();
  /**
   * <pre>
   * 联盟简称
   * </pre>
   *
   * <code>required string allianceShortName = 6;</code>
   */
  java.lang.String getAllianceShortName();
  /**
   * <pre>
   * 联盟简称
   * </pre>
   *
   * <code>required string allianceShortName = 6;</code>
   */
  com.google.protobuf.ByteString
      getAllianceShortNameBytes();

  /**
   * <pre>
   * 职位
   * </pre>
   *
   * <code>repeated int32 positions = 7;</code>
   */
  java.util.List<java.lang.Integer> getPositionsList();
  /**
   * <pre>
   * 职位
   * </pre>
   *
   * <code>repeated int32 positions = 7;</code>
   */
  int getPositionsCount();
  /**
   * <pre>
   * 职位
   * </pre>
   *
   * <code>repeated int32 positions = 7;</code>
   */
  int getPositions(int index);

  /**
   * <pre>
   * 是否显示时间
   * </pre>
   *
   * <code>required int32 showTime = 9;</code>
   */
  boolean hasShowTime();
  /**
   * <pre>
   * 是否显示时间
   * </pre>
   *
   * <code>required int32 showTime = 9;</code>
   */
  int getShowTime();

  /**
   * <pre>
   * 头像
   * </pre>
   *
   * <code>required int32 photoProtoId = 10;</code>
   */
  boolean hasPhotoProtoId();
  /**
   * <pre>
   * 头像
   * </pre>
   *
   * <code>required int32 photoProtoId = 10;</code>
   */
  int getPhotoProtoId();

  /**
   * <pre>
   * 内容
   * </pre>
   *
   * <code>required string message = 11;</code>
   */
  boolean hasMessage();
  /**
   * <pre>
   * 内容
   * </pre>
   *
   * <code>required string message = 11;</code>
   */
  java.lang.String getMessage();
  /**
   * <pre>
   * 内容
   * </pre>
   *
   * <code>required string message = 11;</code>
   */
  com.google.protobuf.ByteString
      getMessageBytes();

  /**
   * <pre>
   * 阅读方式
   * </pre>
   *
   * <code>required int32 readType = 12;</code>
   */
  boolean hasReadType();
  /**
   * <pre>
   * 阅读方式
   * </pre>
   *
   * <code>required int32 readType = 12;</code>
   */
  int getReadType();

  /**
   * <pre>
   * 参数
   * </pre>
   *
   * <code>repeated string params = 13;</code>
   */
  java.util.List<java.lang.String>
      getParamsList();
  /**
   * <pre>
   * 参数
   * </pre>
   *
   * <code>repeated string params = 13;</code>
   */
  int getParamsCount();
  /**
   * <pre>
   * 参数
   * </pre>
   *
   * <code>repeated string params = 13;</code>
   */
  java.lang.String getParams(int index);
  /**
   * <pre>
   * 参数
   * </pre>
   *
   * <code>repeated string params = 13;</code>
   */
  com.google.protobuf.ByteString
      getParamsBytes(int index);

  /**
   * <pre>
   * 消息类型  1-普通消息  2-红包消息
   * </pre>
   *
   * <code>optional int32 messageType = 14;</code>
   */
  boolean hasMessageType();
  /**
   * <pre>
   * 消息类型  1-普通消息  2-红包消息
   * </pre>
   *
   * <code>optional int32 messageType = 14;</code>
   */
  int getMessageType();

  /**
   * <pre>
   * 红包参数内容
   * </pre>
   *
   * <code>optional .client2server.RedBagInfo redBagInfo = 15;</code>
   */
  boolean hasRedBagInfo();
  /**
   * <pre>
   * 红包参数内容
   * </pre>
   *
   * <code>optional .client2server.RedBagInfo redBagInfo = 15;</code>
   */
  pb4client.RedBagInfo getRedBagInfo();
  /**
   * <pre>
   * 红包参数内容
   * </pre>
   *
   * <code>optional .client2server.RedBagInfo redBagInfo = 15;</code>
   */
  pb4client.RedBagInfoOrBuilder getRedBagInfoOrBuilder();

  /**
   * <pre>
   * 红包类型 1-可领取 2-已领取 3-已过期 4- 已领完
   * </pre>
   *
   * <code>optional int32 redBagState = 16;</code>
   */
  boolean hasRedBagState();
  /**
   * <pre>
   * 红包类型 1-可领取 2-已领取 3-已过期 4- 已领完
   * </pre>
   *
   * <code>optional int32 redBagState = 16;</code>
   */
  int getRedBagState();

  /**
   * <pre>
   * 官职Id
   * </pre>
   *
   * <code>optional int32 office = 17;</code>
   */
  boolean hasOffice();
  /**
   * <pre>
   * 官职Id
   * </pre>
   *
   * <code>optional int32 office = 17;</code>
   */
  int getOffice();

  /**
   * <pre>
   * vip等级
   * </pre>
   *
   * <code>required int32 vipLv = 18;</code>
   */
  boolean hasVipLv();
  /**
   * <pre>
   * vip等级
   * </pre>
   *
   * <code>required int32 vipLv = 18;</code>
   */
  int getVipLv();

  /**
   * <pre>
   * 服务器编号
   * </pre>
   *
   * <code>required int32 areaNo = 19;</code>
   */
  boolean hasAreaNo();
  /**
   * <pre>
   * 服务器编号
   * </pre>
   *
   * <code>required int32 areaNo = 19;</code>
   */
  int getAreaNo();
}
