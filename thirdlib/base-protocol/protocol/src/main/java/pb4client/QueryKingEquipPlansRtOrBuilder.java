// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface QueryKingEquipPlansRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.QueryKingEquipPlansRt)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 返回值
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  boolean hasRt();
  /**
   * <pre>
   * 返回值
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  int getRt();

  /**
   * <code>repeated .client2server.QueryKingEquipPlanVo queryKingEquipPlanVos = 2;</code>
   */
  java.util.List<pb4client.QueryKingEquipPlanVo> 
      getQueryKingEquipPlanVosList();
  /**
   * <code>repeated .client2server.QueryKingEquipPlanVo queryKingEquipPlanVos = 2;</code>
   */
  pb4client.QueryKingEquipPlanVo getQueryKingEquipPlanVos(int index);
  /**
   * <code>repeated .client2server.QueryKingEquipPlanVo queryKingEquipPlanVos = 2;</code>
   */
  int getQueryKingEquipPlanVosCount();
  /**
   * <code>repeated .client2server.QueryKingEquipPlanVo queryKingEquipPlanVos = 2;</code>
   */
  java.util.List<? extends pb4client.QueryKingEquipPlanVoOrBuilder> 
      getQueryKingEquipPlanVosOrBuilderList();
  /**
   * <code>repeated .client2server.QueryKingEquipPlanVo queryKingEquipPlanVos = 2;</code>
   */
  pb4client.QueryKingEquipPlanVoOrBuilder getQueryKingEquipPlanVosOrBuilder(
      int index);
}
