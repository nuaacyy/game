// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2world.proto

package pb4server;

public interface MakeSoliderAskReqOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.MakeSoliderAskReq)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 soliderId = 1;</code>
   */
  int getSoliderId();

  /**
   * <code>int32 makeType = 2;</code>
   */
  int getMakeType();

  /**
   * <code>int32 makeNum = 3;</code>
   */
  int getMakeNum();

  /**
   * <code>repeated .pb4server.EffectVo effectMap = 4;</code>
   */
  java.util.List<pb4server.EffectVo> 
      getEffectMapList();
  /**
   * <code>repeated .pb4server.EffectVo effectMap = 4;</code>
   */
  pb4server.EffectVo getEffectMap(int index);
  /**
   * <code>repeated .pb4server.EffectVo effectMap = 4;</code>
   */
  int getEffectMapCount();
  /**
   * <code>repeated .pb4server.EffectVo effectMap = 4;</code>
   */
  java.util.List<? extends pb4server.EffectVoOrBuilder> 
      getEffectMapOrBuilderList();
  /**
   * <code>repeated .pb4server.EffectVo effectMap = 4;</code>
   */
  pb4server.EffectVoOrBuilder getEffectMapOrBuilder(
      int index);
}