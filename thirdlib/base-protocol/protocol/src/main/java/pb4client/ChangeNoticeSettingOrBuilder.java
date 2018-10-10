// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface ChangeNoticeSettingOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.ChangeNoticeSetting)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *勿扰开始时间
   * </pre>
   *
   * <code>required int32 refuseDisturbOpen = 1;</code>
   */
  boolean hasRefuseDisturbOpen();
  /**
   * <pre>
   *勿扰开始时间
   * </pre>
   *
   * <code>required int32 refuseDisturbOpen = 1;</code>
   */
  int getRefuseDisturbOpen();

  /**
   * <pre>
   *勿扰结束时间
   * </pre>
   *
   * <code>required int32 refuseDisturbEnd = 2;</code>
   */
  boolean hasRefuseDisturbEnd();
  /**
   * <pre>
   *勿扰结束时间
   * </pre>
   *
   * <code>required int32 refuseDisturbEnd = 2;</code>
   */
  int getRefuseDisturbEnd();

  /**
   * <pre>
   *警戒等级
   * </pre>
   *
   * <code>required int32 cautionLv = 3;</code>
   */
  boolean hasCautionLv();
  /**
   * <pre>
   *警戒等级
   * </pre>
   *
   * <code>required int32 cautionLv = 3;</code>
   */
  int getCautionLv();

  /**
   * <pre>
   *开关
   * </pre>
   *
   * <code>repeated .client2server.NoticeSwitch switches = 4;</code>
   */
  java.util.List<pb4client.NoticeSwitch> 
      getSwitchesList();
  /**
   * <pre>
   *开关
   * </pre>
   *
   * <code>repeated .client2server.NoticeSwitch switches = 4;</code>
   */
  pb4client.NoticeSwitch getSwitches(int index);
  /**
   * <pre>
   *开关
   * </pre>
   *
   * <code>repeated .client2server.NoticeSwitch switches = 4;</code>
   */
  int getSwitchesCount();
  /**
   * <pre>
   *开关
   * </pre>
   *
   * <code>repeated .client2server.NoticeSwitch switches = 4;</code>
   */
  java.util.List<? extends pb4client.NoticeSwitchOrBuilder> 
      getSwitchesOrBuilderList();
  /**
   * <pre>
   *开关
   * </pre>
   *
   * <code>repeated .client2server.NoticeSwitch switches = 4;</code>
   */
  pb4client.NoticeSwitchOrBuilder getSwitchesOrBuilder(
      int index);
}