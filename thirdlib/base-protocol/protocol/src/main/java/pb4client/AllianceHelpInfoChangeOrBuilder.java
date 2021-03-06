// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface AllianceHelpInfoChangeOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.AllianceHelpInfoChange)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 推送类型  1-新增  2-减少
   * </pre>
   *
   * <code>required int32 changeInfo = 1;</code>
   */
  boolean hasChangeInfo();
  /**
   * <pre>
   * 推送类型  1-新增  2-减少
   * </pre>
   *
   * <code>required int32 changeInfo = 1;</code>
   */
  int getChangeInfo();

  /**
   * <pre>
   * 变化的条目的唯一ID ,新增应该只需要增加这个字段的长度,减少的要判断客户端缓存里有没有这行.说不定已经没有了,如:建筑建造完毕推送客户端-1 但是客户端找不到缓存了,因为该玩家已经帮助过了,帮助的时候就-1了
   * </pre>
   *
   * <code>repeated int64 helpId = 2;</code>
   */
  java.util.List<java.lang.Long> getHelpIdList();
  /**
   * <pre>
   * 变化的条目的唯一ID ,新增应该只需要增加这个字段的长度,减少的要判断客户端缓存里有没有这行.说不定已经没有了,如:建筑建造完毕推送客户端-1 但是客户端找不到缓存了,因为该玩家已经帮助过了,帮助的时候就-1了
   * </pre>
   *
   * <code>repeated int64 helpId = 2;</code>
   */
  int getHelpIdCount();
  /**
   * <pre>
   * 变化的条目的唯一ID ,新增应该只需要增加这个字段的长度,减少的要判断客户端缓存里有没有这行.说不定已经没有了,如:建筑建造完毕推送客户端-1 但是客户端找不到缓存了,因为该玩家已经帮助过了,帮助的时候就-1了
   * </pre>
   *
   * <code>repeated int64 helpId = 2;</code>
   */
  long getHelpId(int index);
}
