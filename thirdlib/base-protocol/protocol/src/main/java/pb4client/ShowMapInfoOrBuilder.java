// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface ShowMapInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.ShowMapInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *坐标X
   * </pre>
   *
   * <code>required int32 x = 1;</code>
   */
  boolean hasX();
  /**
   * <pre>
   *坐标X
   * </pre>
   *
   * <code>required int32 x = 1;</code>
   */
  int getX();

  /**
   * <pre>
   *坐标Y
   * </pre>
   *
   * <code>required int32 y = 2;</code>
   */
  boolean hasY();
  /**
   * <pre>
   *坐标Y
   * </pre>
   *
   * <code>required int32 y = 2;</code>
   */
  int getY();

  /**
   * <pre>
   *详见LandBase中类型，但只会包括:100-主城;106-玩家要塞;107-玩家军营;201~205、250-NPC城池;301-野外要塞;302-野外军营;303-关卡;304-码头
   * </pre>
   *
   * <code>required int32 type = 3;</code>
   */
  boolean hasType();
  /**
   * <pre>
   *详见LandBase中类型，但只会包括:100-主城;106-玩家要塞;107-玩家军营;201~205、250-NPC城池;301-野外要塞;302-野外军营;303-关卡;304-码头
   * </pre>
   *
   * <code>required int32 type = 3;</code>
   */
  int getType();

  /**
   * <pre>
   *关系:0-自己;1-同盟成员;2-同盟下级成员;3-其他玩家;4-同盟所属;5-非同盟联盟
   * </pre>
   *
   * <code>required int32 relation = 4;</code>
   */
  boolean hasRelation();
  /**
   * <pre>
   *关系:0-自己;1-同盟成员;2-同盟下级成员;3-其他玩家;4-同盟所属;5-非同盟联盟
   * </pre>
   *
   * <code>required int32 relation = 4;</code>
   */
  int getRelation();

  /**
   * <pre>
   *如果是NPC城池或关卡，并且被占领，则会返回占领联盟的名称
   * </pre>
   *
   * <code>optional string alceName = 5;</code>
   */
  boolean hasAlceName();
  /**
   * <pre>
   *如果是NPC城池或关卡，并且被占领，则会返回占领联盟的名称
   * </pre>
   *
   * <code>optional string alceName = 5;</code>
   */
  java.lang.String getAlceName();
  /**
   * <pre>
   *如果是NPC城池或关卡，并且被占领，则会返回占领联盟的名称
   * </pre>
   *
   * <code>optional string alceName = 5;</code>
   */
  com.google.protobuf.ByteString
      getAlceNameBytes();

  /**
   * <pre>
   *如果是NPC城池或关卡，并且被占领，则会返回占领联盟的简称
   * </pre>
   *
   * <code>optional string alceShortName = 6;</code>
   */
  boolean hasAlceShortName();
  /**
   * <pre>
   *如果是NPC城池或关卡，并且被占领，则会返回占领联盟的简称
   * </pre>
   *
   * <code>optional string alceShortName = 6;</code>
   */
  java.lang.String getAlceShortName();
  /**
   * <pre>
   *如果是NPC城池或关卡，并且被占领，则会返回占领联盟的简称
   * </pre>
   *
   * <code>optional string alceShortName = 6;</code>
   */
  com.google.protobuf.ByteString
      getAlceShortNameBytes();

  /**
   * <pre>
   *如果是NPC城池或关卡，被占领城主，并且任命城主，则会返回被任命的玩家名称
   * </pre>
   *
   * <code>optional string taiShouName = 7;</code>
   */
  boolean hasTaiShouName();
  /**
   * <pre>
   *如果是NPC城池或关卡，被占领城主，并且任命城主，则会返回被任命的玩家名称
   * </pre>
   *
   * <code>optional string taiShouName = 7;</code>
   */
  java.lang.String getTaiShouName();
  /**
   * <pre>
   *如果是NPC城池或关卡，被占领城主，并且任命城主，则会返回被任命的玩家名称
   * </pre>
   *
   * <code>optional string taiShouName = 7;</code>
   */
  com.google.protobuf.ByteString
      getTaiShouNameBytes();
}
