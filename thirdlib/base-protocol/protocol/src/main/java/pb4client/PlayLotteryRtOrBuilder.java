// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface PlayLotteryRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.PlayLotteryRt)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>required int32 rt = 1;</code>
   */
  boolean hasRt();
  /**
   * <code>required int32 rt = 1;</code>
   */
  int getRt();

  /**
   * <pre>
   * 获得的武将/武将碎片/道具模板id 数量
   * </pre>
   *
   * <code>repeated .client2server.DrawResInfo rewards = 2;</code>
   */
  java.util.List<pb4client.DrawResInfo> 
      getRewardsList();
  /**
   * <pre>
   * 获得的武将/武将碎片/道具模板id 数量
   * </pre>
   *
   * <code>repeated .client2server.DrawResInfo rewards = 2;</code>
   */
  pb4client.DrawResInfo getRewards(int index);
  /**
   * <pre>
   * 获得的武将/武将碎片/道具模板id 数量
   * </pre>
   *
   * <code>repeated .client2server.DrawResInfo rewards = 2;</code>
   */
  int getRewardsCount();
  /**
   * <pre>
   * 获得的武将/武将碎片/道具模板id 数量
   * </pre>
   *
   * <code>repeated .client2server.DrawResInfo rewards = 2;</code>
   */
  java.util.List<? extends pb4client.DrawResInfoOrBuilder> 
      getRewardsOrBuilderList();
  /**
   * <pre>
   * 获得的武将/武将碎片/道具模板id 数量
   * </pre>
   *
   * <code>repeated .client2server.DrawResInfo rewards = 2;</code>
   */
  pb4client.DrawResInfoOrBuilder getRewardsOrBuilder(
      int index);
}