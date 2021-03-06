// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface HeroFightReportOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.HeroFightReport)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *所有战斗单位
   * </pre>
   *
   * <code>repeated .client2server.FightEntity entitys = 1;</code>
   */
  java.util.List<pb4client.FightEntity> 
      getEntitysList();
  /**
   * <pre>
   *所有战斗单位
   * </pre>
   *
   * <code>repeated .client2server.FightEntity entitys = 1;</code>
   */
  pb4client.FightEntity getEntitys(int index);
  /**
   * <pre>
   *所有战斗单位
   * </pre>
   *
   * <code>repeated .client2server.FightEntity entitys = 1;</code>
   */
  int getEntitysCount();
  /**
   * <pre>
   *所有战斗单位
   * </pre>
   *
   * <code>repeated .client2server.FightEntity entitys = 1;</code>
   */
  java.util.List<? extends pb4client.FightEntityOrBuilder> 
      getEntitysOrBuilderList();
  /**
   * <pre>
   *所有战斗单位
   * </pre>
   *
   * <code>repeated .client2server.FightEntity entitys = 1;</code>
   */
  pb4client.FightEntityOrBuilder getEntitysOrBuilder(
      int index);

  /**
   * <pre>
   *战斗请求记录
   * </pre>
   *
   * <code>repeated .client2server.FightRecord records = 2;</code>
   */
  java.util.List<pb4client.FightRecord> 
      getRecordsList();
  /**
   * <pre>
   *战斗请求记录
   * </pre>
   *
   * <code>repeated .client2server.FightRecord records = 2;</code>
   */
  pb4client.FightRecord getRecords(int index);
  /**
   * <pre>
   *战斗请求记录
   * </pre>
   *
   * <code>repeated .client2server.FightRecord records = 2;</code>
   */
  int getRecordsCount();
  /**
   * <pre>
   *战斗请求记录
   * </pre>
   *
   * <code>repeated .client2server.FightRecord records = 2;</code>
   */
  java.util.List<? extends pb4client.FightRecordOrBuilder> 
      getRecordsOrBuilderList();
  /**
   * <pre>
   *战斗请求记录
   * </pre>
   *
   * <code>repeated .client2server.FightRecord records = 2;</code>
   */
  pb4client.FightRecordOrBuilder getRecordsOrBuilder(
      int index);

  /**
   * <pre>
   *战斗结果
   * </pre>
   *
   * <code>required int32 fightResult = 3;</code>
   */
  boolean hasFightResult();
  /**
   * <pre>
   *战斗结果
   * </pre>
   *
   * <code>required int32 fightResult = 3;</code>
   */
  int getFightResult();

  /**
   * <pre>
   *统计数据
   * </pre>
   *
   * <code>repeated .client2server.FightStatistics statistics = 4;</code>
   */
  java.util.List<pb4client.FightStatistics> 
      getStatisticsList();
  /**
   * <pre>
   *统计数据
   * </pre>
   *
   * <code>repeated .client2server.FightStatistics statistics = 4;</code>
   */
  pb4client.FightStatistics getStatistics(int index);
  /**
   * <pre>
   *统计数据
   * </pre>
   *
   * <code>repeated .client2server.FightStatistics statistics = 4;</code>
   */
  int getStatisticsCount();
  /**
   * <pre>
   *统计数据
   * </pre>
   *
   * <code>repeated .client2server.FightStatistics statistics = 4;</code>
   */
  java.util.List<? extends pb4client.FightStatisticsOrBuilder> 
      getStatisticsOrBuilderList();
  /**
   * <pre>
   *统计数据
   * </pre>
   *
   * <code>repeated .client2server.FightStatistics statistics = 4;</code>
   */
  pb4client.FightStatisticsOrBuilder getStatisticsOrBuilder(
      int index);

  /**
   * <pre>
   *操作记录
   * </pre>
   *
   * <code>repeated .client2server.OperateRecord operateRecords = 5;</code>
   */
  java.util.List<pb4client.OperateRecord> 
      getOperateRecordsList();
  /**
   * <pre>
   *操作记录
   * </pre>
   *
   * <code>repeated .client2server.OperateRecord operateRecords = 5;</code>
   */
  pb4client.OperateRecord getOperateRecords(int index);
  /**
   * <pre>
   *操作记录
   * </pre>
   *
   * <code>repeated .client2server.OperateRecord operateRecords = 5;</code>
   */
  int getOperateRecordsCount();
  /**
   * <pre>
   *操作记录
   * </pre>
   *
   * <code>repeated .client2server.OperateRecord operateRecords = 5;</code>
   */
  java.util.List<? extends pb4client.OperateRecordOrBuilder> 
      getOperateRecordsOrBuilderList();
  /**
   * <pre>
   *操作记录
   * </pre>
   *
   * <code>repeated .client2server.OperateRecord operateRecords = 5;</code>
   */
  pb4client.OperateRecordOrBuilder getOperateRecordsOrBuilder(
      int index);
}
