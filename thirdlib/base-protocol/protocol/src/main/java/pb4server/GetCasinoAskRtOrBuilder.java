// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

public interface GetCasinoAskRtOrBuilder extends
    // @@protoc_insertion_point(interface_extends:pb4server.GetCasinoAskRt)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 rt = 1;</code>
   */
  int getRt();

  /**
   * <code>int64 totalMoney = 2;</code>
   */
  long getTotalMoney();

  /**
   * <code>repeated .client2server.CasinosWinner casinosWinner = 3;</code>
   */
  java.util.List<pb4client.CasinosWinner> 
      getCasinosWinnerList();
  /**
   * <code>repeated .client2server.CasinosWinner casinosWinner = 3;</code>
   */
  pb4client.CasinosWinner getCasinosWinner(int index);
  /**
   * <code>repeated .client2server.CasinosWinner casinosWinner = 3;</code>
   */
  int getCasinosWinnerCount();
  /**
   * <code>repeated .client2server.CasinosWinner casinosWinner = 3;</code>
   */
  java.util.List<? extends pb4client.CasinosWinnerOrBuilder> 
      getCasinosWinnerOrBuilderList();
  /**
   * <code>repeated .client2server.CasinosWinner casinosWinner = 3;</code>
   */
  pb4client.CasinosWinnerOrBuilder getCasinosWinnerOrBuilder(
      int index);
}
