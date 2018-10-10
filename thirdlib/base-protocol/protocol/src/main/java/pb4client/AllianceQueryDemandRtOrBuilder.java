// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface AllianceQueryDemandRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.AllianceQueryDemandRt)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *返回值
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  boolean hasRt();
  /**
   * <pre>
   *返回值
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  int getRt();

  /**
   * <pre>
   *需求信息
   * </pre>
   *
   * <code>optional .client2server.AllianceQueryDemandInfo demand = 2;</code>
   */
  boolean hasDemand();
  /**
   * <pre>
   *需求信息
   * </pre>
   *
   * <code>optional .client2server.AllianceQueryDemandInfo demand = 2;</code>
   */
  pb4client.AllianceQueryDemandInfo getDemand();
  /**
   * <pre>
   *需求信息
   * </pre>
   *
   * <code>optional .client2server.AllianceQueryDemandInfo demand = 2;</code>
   */
  pb4client.AllianceQueryDemandInfoOrBuilder getDemandOrBuilder();

  /**
   * <pre>
   * 捐卡明细
   * </pre>
   *
   * <code>repeated .client2server.AllianceExchangeDetailInfo details = 3;</code>
   */
  java.util.List<pb4client.AllianceExchangeDetailInfo> 
      getDetailsList();
  /**
   * <pre>
   * 捐卡明细
   * </pre>
   *
   * <code>repeated .client2server.AllianceExchangeDetailInfo details = 3;</code>
   */
  pb4client.AllianceExchangeDetailInfo getDetails(int index);
  /**
   * <pre>
   * 捐卡明细
   * </pre>
   *
   * <code>repeated .client2server.AllianceExchangeDetailInfo details = 3;</code>
   */
  int getDetailsCount();
  /**
   * <pre>
   * 捐卡明细
   * </pre>
   *
   * <code>repeated .client2server.AllianceExchangeDetailInfo details = 3;</code>
   */
  java.util.List<? extends pb4client.AllianceExchangeDetailInfoOrBuilder> 
      getDetailsOrBuilderList();
  /**
   * <pre>
   * 捐卡明细
   * </pre>
   *
   * <code>repeated .client2server.AllianceExchangeDetailInfo details = 3;</code>
   */
  pb4client.AllianceExchangeDetailInfoOrBuilder getDetailsOrBuilder(
      int index);
}
