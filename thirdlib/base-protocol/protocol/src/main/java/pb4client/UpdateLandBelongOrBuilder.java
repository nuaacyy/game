// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

public interface UpdateLandBelongOrBuilder extends
    // @@protoc_insertion_point(interface_extends:client2server.UpdateLandBelong)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *格子信息
   * </pre>
   *
   * <code>repeated .client2server.NewEveryLandInfo lands = 1;</code>
   */
  java.util.List<pb4client.NewEveryLandInfo> 
      getLandsList();
  /**
   * <pre>
   *格子信息
   * </pre>
   *
   * <code>repeated .client2server.NewEveryLandInfo lands = 1;</code>
   */
  pb4client.NewEveryLandInfo getLands(int index);
  /**
   * <pre>
   *格子信息
   * </pre>
   *
   * <code>repeated .client2server.NewEveryLandInfo lands = 1;</code>
   */
  int getLandsCount();
  /**
   * <pre>
   *格子信息
   * </pre>
   *
   * <code>repeated .client2server.NewEveryLandInfo lands = 1;</code>
   */
  java.util.List<? extends pb4client.NewEveryLandInfoOrBuilder> 
      getLandsOrBuilderList();
  /**
   * <pre>
   *格子信息
   * </pre>
   *
   * <code>repeated .client2server.NewEveryLandInfo lands = 1;</code>
   */
  pb4client.NewEveryLandInfoOrBuilder getLandsOrBuilder(
      int index);

  /**
   * <pre>
   * 需要恢复成原始状态的地块坐标
   * </pre>
   *
   * <code>repeated .client2server.CellPoint dels = 2;</code>
   */
  java.util.List<pb4client.CellPoint> 
      getDelsList();
  /**
   * <pre>
   * 需要恢复成原始状态的地块坐标
   * </pre>
   *
   * <code>repeated .client2server.CellPoint dels = 2;</code>
   */
  pb4client.CellPoint getDels(int index);
  /**
   * <pre>
   * 需要恢复成原始状态的地块坐标
   * </pre>
   *
   * <code>repeated .client2server.CellPoint dels = 2;</code>
   */
  int getDelsCount();
  /**
   * <pre>
   * 需要恢复成原始状态的地块坐标
   * </pre>
   *
   * <code>repeated .client2server.CellPoint dels = 2;</code>
   */
  java.util.List<? extends pb4client.CellPointOrBuilder> 
      getDelsOrBuilderList();
  /**
   * <pre>
   * 需要恢复成原始状态的地块坐标
   * </pre>
   *
   * <code>repeated .client2server.CellPoint dels = 2;</code>
   */
  pb4client.CellPointOrBuilder getDelsOrBuilder(
      int index);
}
