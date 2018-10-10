// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface SetKingEquipPlanOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.SetKingEquipPlan)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 排序ID
   * </pre>
   *
   * <code>required int32 planId = 1;</code>
   */
  boolean hasPlanId();
  /**
   * <pre>
   * 排序ID
   * </pre>
   *
   * <code>required int32 planId = 1;</code>
   */
  int getPlanId();

  /**
   * <pre>
   * 名字
   * </pre>
   *
   * <code>required string planName = 2;</code>
   */
  boolean hasPlanName();
  /**
   * <pre>
   * 名字
   * </pre>
   *
   * <code>required string planName = 2;</code>
   */
  java.lang.String getPlanName();
  /**
   * <pre>
   * 名字
   * </pre>
   *
   * <code>required string planName = 2;</code>
   */
  com.google.protobuf.ByteString
      getPlanNameBytes();

  /**
   * <pre>
   * 预设内容
   * </pre>
   *
   * <code>repeated .client2server.PlanVo plan = 3;</code>
   */
  java.util.List<pb4client.PlanVo> 
      getPlanList();
  /**
   * <pre>
   * 预设内容
   * </pre>
   *
   * <code>repeated .client2server.PlanVo plan = 3;</code>
   */
  pb4client.PlanVo getPlan(int index);
  /**
   * <pre>
   * 预设内容
   * </pre>
   *
   * <code>repeated .client2server.PlanVo plan = 3;</code>
   */
  int getPlanCount();
  /**
   * <pre>
   * 预设内容
   * </pre>
   *
   * <code>repeated .client2server.PlanVo plan = 3;</code>
   */
  java.util.List<? extends pb4client.PlanVoOrBuilder> 
      getPlanOrBuilderList();
  /**
   * <pre>
   * 预设内容
   * </pre>
   *
   * <code>repeated .client2server.PlanVo plan = 3;</code>
   */
  pb4client.PlanVoOrBuilder getPlanOrBuilder(
      int index);
}
