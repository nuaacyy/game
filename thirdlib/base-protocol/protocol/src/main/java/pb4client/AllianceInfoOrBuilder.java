// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface AllianceInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.AllianceInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *联盟Id
   * </pre>
   *
   * <code>required int64 id = 1;</code>
   */
  boolean hasId();
  /**
   * <pre>
   *联盟Id
   * </pre>
   *
   * <code>required int64 id = 1;</code>
   */
  long getId();

  /**
   * <pre>
   *联盟名称
   * </pre>
   *
   * <code>required string name = 2;</code>
   */
  boolean hasName();
  /**
   * <pre>
   *联盟名称
   * </pre>
   *
   * <code>required string name = 2;</code>
   */
  java.lang.String getName();
  /**
   * <pre>
   *联盟名称
   * </pre>
   *
   * <code>required string name = 2;</code>
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <pre>
   *联盟简称
   * </pre>
   *
   * <code>required string shortName = 3;</code>
   */
  boolean hasShortName();
  /**
   * <pre>
   *联盟简称
   * </pre>
   *
   * <code>required string shortName = 3;</code>
   */
  java.lang.String getShortName();
  /**
   * <pre>
   *联盟简称
   * </pre>
   *
   * <code>required string shortName = 3;</code>
   */
  com.google.protobuf.ByteString
      getShortNameBytes();

  /**
   * <pre>
   *职位：1-盟主 2-副盟主 3-指挥官 4-外交官 5-团长 6-普通成员 7-白虎成员 8-青龙成员 9-玄武成员 10-朱雀成员 11-白虎团长 12-青龙团长 13-玄武团长 14-朱雀团长
   * </pre>
   *
   * <code>repeated int32 positions = 5;</code>
   */
  java.util.List<java.lang.Integer> getPositionsList();
  /**
   * <pre>
   *职位：1-盟主 2-副盟主 3-指挥官 4-外交官 5-团长 6-普通成员 7-白虎成员 8-青龙成员 9-玄武成员 10-朱雀成员 11-白虎团长 12-青龙团长 13-玄武团长 14-朱雀团长
   * </pre>
   *
   * <code>repeated int32 positions = 5;</code>
   */
  int getPositionsCount();
  /**
   * <pre>
   *职位：1-盟主 2-副盟主 3-指挥官 4-外交官 5-团长 6-普通成员 7-白虎成员 8-青龙成员 9-玄武成员 10-朱雀成员 11-白虎团长 12-青龙团长 13-玄武团长 14-朱雀团长
   * </pre>
   *
   * <code>repeated int32 positions = 5;</code>
   */
  int getPositions(int index);

  /**
   * <pre>
   *旗帜的颜色（没有设置过，则值为0）
   * </pre>
   *
   * <code>required int32 color = 6;</code>
   */
  boolean hasColor();
  /**
   * <pre>
   *旗帜的颜色（没有设置过，则值为0）
   * </pre>
   *
   * <code>required int32 color = 6;</code>
   */
  int getColor();

  /**
   * <pre>
   *旗帜的样式（没有设置过，则值为0）
   * </pre>
   *
   * <code>required int32 style = 7;</code>
   */
  boolean hasStyle();
  /**
   * <pre>
   *旗帜的样式（没有设置过，则值为0）
   * </pre>
   *
   * <code>required int32 style = 7;</code>
   */
  int getStyle();

  /**
   * <pre>
   *旗帜的图案（没有设置过，则值为0）
   * </pre>
   *
   * <code>required int32 effect = 8;</code>
   */
  boolean hasEffect();
  /**
   * <pre>
   *旗帜的图案（没有设置过，则值为0）
   * </pre>
   *
   * <code>required int32 effect = 8;</code>
   */
  int getEffect();
}
