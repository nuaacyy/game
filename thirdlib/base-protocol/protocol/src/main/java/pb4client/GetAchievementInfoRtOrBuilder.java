// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface GetAchievementInfoRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.GetAchievementInfoRt)
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
   * <code>repeated .client2server.AchievementInfo achieveInfo = 2;</code>
   */
  java.util.List<pb4client.AchievementInfo> 
      getAchieveInfoList();
  /**
   * <code>repeated .client2server.AchievementInfo achieveInfo = 2;</code>
   */
  pb4client.AchievementInfo getAchieveInfo(int index);
  /**
   * <code>repeated .client2server.AchievementInfo achieveInfo = 2;</code>
   */
  int getAchieveInfoCount();
  /**
   * <code>repeated .client2server.AchievementInfo achieveInfo = 2;</code>
   */
  java.util.List<? extends pb4client.AchievementInfoOrBuilder> 
      getAchieveInfoOrBuilderList();
  /**
   * <code>repeated .client2server.AchievementInfo achieveInfo = 2;</code>
   */
  pb4client.AchievementInfoOrBuilder getAchieveInfoOrBuilder(
      int index);
}