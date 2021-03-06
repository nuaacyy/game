// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

public interface OpenAllianceCompetitionMainAskRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.OpenAllianceCompetitionMainAskRt)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 rt = 1;</code>
   */
  int getRt();

  /**
   * <pre>
   * 当前积分
   * </pre>
   *
   * <code>int32 nowScore = 2;</code>
   */
  int getNowScore();

  /**
   * <pre>
   * 参与人数
   * </pre>
   *
   * <code>int32 joinPlayerNum = 3;</code>
   */
  int getJoinPlayerNum();

  /**
   * <pre>
   * 领奖时间
   * </pre>
   *
   * <code>int32 rewardTime = 4;</code>
   */
  int getRewardTime();

  /**
   * <pre>
   * 联盟总动员任务信息
   * </pre>
   *
   * <code>repeated .pb4server.AllianceCompetitionQuestVo quests = 5;</code>
   */
  java.util.List<pb4server.AllianceCompetitionQuestVo> 
      getQuestsList();
  /**
   * <pre>
   * 联盟总动员任务信息
   * </pre>
   *
   * <code>repeated .pb4server.AllianceCompetitionQuestVo quests = 5;</code>
   */
  pb4server.AllianceCompetitionQuestVo getQuests(int index);
  /**
   * <pre>
   * 联盟总动员任务信息
   * </pre>
   *
   * <code>repeated .pb4server.AllianceCompetitionQuestVo quests = 5;</code>
   */
  int getQuestsCount();
  /**
   * <pre>
   * 联盟总动员任务信息
   * </pre>
   *
   * <code>repeated .pb4server.AllianceCompetitionQuestVo quests = 5;</code>
   */
  java.util.List<? extends pb4server.AllianceCompetitionQuestVoOrBuilder> 
      getQuestsOrBuilderList();
  /**
   * <pre>
   * 联盟总动员任务信息
   * </pre>
   *
   * <code>repeated .pb4server.AllianceCompetitionQuestVo quests = 5;</code>
   */
  pb4server.AllianceCompetitionQuestVoOrBuilder getQuestsOrBuilder(
      int index);
}
