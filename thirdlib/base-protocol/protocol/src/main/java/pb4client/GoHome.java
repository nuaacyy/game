// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 3001
 * 服务器 -&gt; 客户端
 * 玩家回城推送
 * </pre>
 *
 * Protobuf type {@code client2server.GoHome}
 */
public  final class GoHome extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.GoHome)
    GoHomeOrBuilder {
  // Use GoHome.newBuilder() to construct.
  private GoHome(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GoHome() {
    walkTime_ = 0;
    forceId_ = 0L;
    aimsX_ = 0;
    aimsY_ = 0;
    startX_ = 0;
    startY_ = 0;
    cancelWalkTime_ = 0;
    userWalkTime_ = 0;
    armyGroupId_ = 0L;
    forceArmy_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GoHome(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownField(input, unknownFields,
                                   extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 8: {
            bitField0_ |= 0x00000001;
            walkTime_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            forceId_ = input.readInt64();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            aimsX_ = input.readInt32();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            aimsY_ = input.readInt32();
            break;
          }
          case 40: {
            bitField0_ |= 0x00000010;
            startX_ = input.readInt32();
            break;
          }
          case 48: {
            bitField0_ |= 0x00000020;
            startY_ = input.readInt32();
            break;
          }
          case 56: {
            bitField0_ |= 0x00000040;
            cancelWalkTime_ = input.readInt32();
            break;
          }
          case 64: {
            bitField0_ |= 0x00000080;
            userWalkTime_ = input.readInt32();
            break;
          }
          case 72: {
            bitField0_ |= 0x00000100;
            armyGroupId_ = input.readInt64();
            break;
          }
          case 80: {
            bitField0_ |= 0x00000200;
            forceArmy_ = input.readInt32();
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_GoHome_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_GoHome_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.GoHome.class, pb4client.GoHome.Builder.class);
  }

  private int bitField0_;
  public static final int WALKTIME_FIELD_NUMBER = 1;
  private int walkTime_;
  /**
   * <pre>
   *回城时间
   * </pre>
   *
   * <code>required int32 walkTime = 1;</code>
   */
  public boolean hasWalkTime() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *回城时间
   * </pre>
   *
   * <code>required int32 walkTime = 1;</code>
   */
  public int getWalkTime() {
    return walkTime_;
  }

  public static final int FORCEID_FIELD_NUMBER = 2;
  private long forceId_;
  /**
   * <pre>
   *回城部队
   * </pre>
   *
   * <code>required int64 forceId = 2;</code>
   */
  public boolean hasForceId() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *回城部队
   * </pre>
   *
   * <code>required int64 forceId = 2;</code>
   */
  public long getForceId() {
    return forceId_;
  }

  public static final int AIMSX_FIELD_NUMBER = 3;
  private int aimsX_;
  /**
   * <pre>
   *目的地X
   * </pre>
   *
   * <code>required int32 aimsX = 3;</code>
   */
  public boolean hasAimsX() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   *目的地X
   * </pre>
   *
   * <code>required int32 aimsX = 3;</code>
   */
  public int getAimsX() {
    return aimsX_;
  }

  public static final int AIMSY_FIELD_NUMBER = 4;
  private int aimsY_;
  /**
   * <pre>
   *目的地Y
   * </pre>
   *
   * <code>required int32 aimsY = 4;</code>
   */
  public boolean hasAimsY() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   *目的地Y
   * </pre>
   *
   * <code>required int32 aimsY = 4;</code>
   */
  public int getAimsY() {
    return aimsY_;
  }

  public static final int STARTX_FIELD_NUMBER = 5;
  private int startX_;
  /**
   * <pre>
   *出发地X
   * </pre>
   *
   * <code>required int32 startX = 5;</code>
   */
  public boolean hasStartX() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <pre>
   *出发地X
   * </pre>
   *
   * <code>required int32 startX = 5;</code>
   */
  public int getStartX() {
    return startX_;
  }

  public static final int STARTY_FIELD_NUMBER = 6;
  private int startY_;
  /**
   * <pre>
   *出发地Y
   * </pre>
   *
   * <code>required int32 startY = 6;</code>
   */
  public boolean hasStartY() {
    return ((bitField0_ & 0x00000020) == 0x00000020);
  }
  /**
   * <pre>
   *出发地Y
   * </pre>
   *
   * <code>required int32 startY = 6;</code>
   */
  public int getStartY() {
    return startY_;
  }

  public static final int CANCELWALKTIME_FIELD_NUMBER = 7;
  private int cancelWalkTime_;
  /**
   * <pre>
   *半路折回取消行军的回城时间
   * </pre>
   *
   * <code>required int32 cancelWalkTime = 7;</code>
   */
  public boolean hasCancelWalkTime() {
    return ((bitField0_ & 0x00000040) == 0x00000040);
  }
  /**
   * <pre>
   *半路折回取消行军的回城时间
   * </pre>
   *
   * <code>required int32 cancelWalkTime = 7;</code>
   */
  public int getCancelWalkTime() {
    return cancelWalkTime_;
  }

  public static final int USERWALKTIME_FIELD_NUMBER = 8;
  private int userWalkTime_;
  /**
   * <pre>
   *需要的总时间
   * </pre>
   *
   * <code>required int32 userWalkTime = 8;</code>
   */
  public boolean hasUserWalkTime() {
    return ((bitField0_ & 0x00000080) == 0x00000080);
  }
  /**
   * <pre>
   *需要的总时间
   * </pre>
   *
   * <code>required int32 userWalkTime = 8;</code>
   */
  public int getUserWalkTime() {
    return userWalkTime_;
  }

  public static final int ARMYGROUPID_FIELD_NUMBER = 9;
  private long armyGroupId_;
  /**
   * <pre>
   *部队隶属军团ID
   * </pre>
   *
   * <code>required int64 armyGroupId = 9;</code>
   */
  public boolean hasArmyGroupId() {
    return ((bitField0_ & 0x00000100) == 0x00000100);
  }
  /**
   * <pre>
   *部队隶属军团ID
   * </pre>
   *
   * <code>required int64 armyGroupId = 9;</code>
   */
  public long getArmyGroupId() {
    return armyGroupId_;
  }

  public static final int FORCEARMY_FIELD_NUMBER = 10;
  private int forceArmy_;
  /**
   * <pre>
   * 部队兵种
   * </pre>
   *
   * <code>required int32 forceArmy = 10;</code>
   */
  public boolean hasForceArmy() {
    return ((bitField0_ & 0x00000200) == 0x00000200);
  }
  /**
   * <pre>
   * 部队兵种
   * </pre>
   *
   * <code>required int32 forceArmy = 10;</code>
   */
  public int getForceArmy() {
    return forceArmy_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasWalkTime()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasForceId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasAimsX()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasAimsY()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasStartX()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasStartY()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasCancelWalkTime()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasUserWalkTime()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasArmyGroupId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasForceArmy()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, walkTime_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt64(2, forceId_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, aimsX_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt32(4, aimsY_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      output.writeInt32(5, startX_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      output.writeInt32(6, startY_);
    }
    if (((bitField0_ & 0x00000040) == 0x00000040)) {
      output.writeInt32(7, cancelWalkTime_);
    }
    if (((bitField0_ & 0x00000080) == 0x00000080)) {
      output.writeInt32(8, userWalkTime_);
    }
    if (((bitField0_ & 0x00000100) == 0x00000100)) {
      output.writeInt64(9, armyGroupId_);
    }
    if (((bitField0_ & 0x00000200) == 0x00000200)) {
      output.writeInt32(10, forceArmy_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, walkTime_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, forceId_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, aimsX_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, aimsY_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, startX_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, startY_);
    }
    if (((bitField0_ & 0x00000040) == 0x00000040)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(7, cancelWalkTime_);
    }
    if (((bitField0_ & 0x00000080) == 0x00000080)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(8, userWalkTime_);
    }
    if (((bitField0_ & 0x00000100) == 0x00000100)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(9, armyGroupId_);
    }
    if (((bitField0_ & 0x00000200) == 0x00000200)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(10, forceArmy_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof pb4client.GoHome)) {
      return super.equals(obj);
    }
    pb4client.GoHome other = (pb4client.GoHome) obj;

    boolean result = true;
    result = result && (hasWalkTime() == other.hasWalkTime());
    if (hasWalkTime()) {
      result = result && (getWalkTime()
          == other.getWalkTime());
    }
    result = result && (hasForceId() == other.hasForceId());
    if (hasForceId()) {
      result = result && (getForceId()
          == other.getForceId());
    }
    result = result && (hasAimsX() == other.hasAimsX());
    if (hasAimsX()) {
      result = result && (getAimsX()
          == other.getAimsX());
    }
    result = result && (hasAimsY() == other.hasAimsY());
    if (hasAimsY()) {
      result = result && (getAimsY()
          == other.getAimsY());
    }
    result = result && (hasStartX() == other.hasStartX());
    if (hasStartX()) {
      result = result && (getStartX()
          == other.getStartX());
    }
    result = result && (hasStartY() == other.hasStartY());
    if (hasStartY()) {
      result = result && (getStartY()
          == other.getStartY());
    }
    result = result && (hasCancelWalkTime() == other.hasCancelWalkTime());
    if (hasCancelWalkTime()) {
      result = result && (getCancelWalkTime()
          == other.getCancelWalkTime());
    }
    result = result && (hasUserWalkTime() == other.hasUserWalkTime());
    if (hasUserWalkTime()) {
      result = result && (getUserWalkTime()
          == other.getUserWalkTime());
    }
    result = result && (hasArmyGroupId() == other.hasArmyGroupId());
    if (hasArmyGroupId()) {
      result = result && (getArmyGroupId()
          == other.getArmyGroupId());
    }
    result = result && (hasForceArmy() == other.hasForceArmy());
    if (hasForceArmy()) {
      result = result && (getForceArmy()
          == other.getForceArmy());
    }
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasWalkTime()) {
      hash = (37 * hash) + WALKTIME_FIELD_NUMBER;
      hash = (53 * hash) + getWalkTime();
    }
    if (hasForceId()) {
      hash = (37 * hash) + FORCEID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getForceId());
    }
    if (hasAimsX()) {
      hash = (37 * hash) + AIMSX_FIELD_NUMBER;
      hash = (53 * hash) + getAimsX();
    }
    if (hasAimsY()) {
      hash = (37 * hash) + AIMSY_FIELD_NUMBER;
      hash = (53 * hash) + getAimsY();
    }
    if (hasStartX()) {
      hash = (37 * hash) + STARTX_FIELD_NUMBER;
      hash = (53 * hash) + getStartX();
    }
    if (hasStartY()) {
      hash = (37 * hash) + STARTY_FIELD_NUMBER;
      hash = (53 * hash) + getStartY();
    }
    if (hasCancelWalkTime()) {
      hash = (37 * hash) + CANCELWALKTIME_FIELD_NUMBER;
      hash = (53 * hash) + getCancelWalkTime();
    }
    if (hasUserWalkTime()) {
      hash = (37 * hash) + USERWALKTIME_FIELD_NUMBER;
      hash = (53 * hash) + getUserWalkTime();
    }
    if (hasArmyGroupId()) {
      hash = (37 * hash) + ARMYGROUPID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getArmyGroupId());
    }
    if (hasForceArmy()) {
      hash = (37 * hash) + FORCEARMY_FIELD_NUMBER;
      hash = (53 * hash) + getForceArmy();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.GoHome parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.GoHome parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.GoHome parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.GoHome parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.GoHome parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.GoHome parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.GoHome parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.GoHome parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.GoHome parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.GoHome parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.GoHome parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.GoHome parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(pb4client.GoHome prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * msgType = 3001
   * 服务器 -&gt; 客户端
   * 玩家回城推送
   * </pre>
   *
   * Protobuf type {@code client2server.GoHome}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.GoHome)
      pb4client.GoHomeOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_GoHome_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_GoHome_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.GoHome.class, pb4client.GoHome.Builder.class);
    }

    // Construct using pb4client.GoHome.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      walkTime_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      forceId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000002);
      aimsX_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      aimsY_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      startX_ = 0;
      bitField0_ = (bitField0_ & ~0x00000010);
      startY_ = 0;
      bitField0_ = (bitField0_ & ~0x00000020);
      cancelWalkTime_ = 0;
      bitField0_ = (bitField0_ & ~0x00000040);
      userWalkTime_ = 0;
      bitField0_ = (bitField0_ & ~0x00000080);
      armyGroupId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000100);
      forceArmy_ = 0;
      bitField0_ = (bitField0_ & ~0x00000200);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_GoHome_descriptor;
    }

    public pb4client.GoHome getDefaultInstanceForType() {
      return pb4client.GoHome.getDefaultInstance();
    }

    public pb4client.GoHome build() {
      pb4client.GoHome result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.GoHome buildPartial() {
      pb4client.GoHome result = new pb4client.GoHome(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.walkTime_ = walkTime_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.forceId_ = forceId_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.aimsX_ = aimsX_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.aimsY_ = aimsY_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.startX_ = startX_;
      if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
        to_bitField0_ |= 0x00000020;
      }
      result.startY_ = startY_;
      if (((from_bitField0_ & 0x00000040) == 0x00000040)) {
        to_bitField0_ |= 0x00000040;
      }
      result.cancelWalkTime_ = cancelWalkTime_;
      if (((from_bitField0_ & 0x00000080) == 0x00000080)) {
        to_bitField0_ |= 0x00000080;
      }
      result.userWalkTime_ = userWalkTime_;
      if (((from_bitField0_ & 0x00000100) == 0x00000100)) {
        to_bitField0_ |= 0x00000100;
      }
      result.armyGroupId_ = armyGroupId_;
      if (((from_bitField0_ & 0x00000200) == 0x00000200)) {
        to_bitField0_ |= 0x00000200;
      }
      result.forceArmy_ = forceArmy_;
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof pb4client.GoHome) {
        return mergeFrom((pb4client.GoHome)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.GoHome other) {
      if (other == pb4client.GoHome.getDefaultInstance()) return this;
      if (other.hasWalkTime()) {
        setWalkTime(other.getWalkTime());
      }
      if (other.hasForceId()) {
        setForceId(other.getForceId());
      }
      if (other.hasAimsX()) {
        setAimsX(other.getAimsX());
      }
      if (other.hasAimsY()) {
        setAimsY(other.getAimsY());
      }
      if (other.hasStartX()) {
        setStartX(other.getStartX());
      }
      if (other.hasStartY()) {
        setStartY(other.getStartY());
      }
      if (other.hasCancelWalkTime()) {
        setCancelWalkTime(other.getCancelWalkTime());
      }
      if (other.hasUserWalkTime()) {
        setUserWalkTime(other.getUserWalkTime());
      }
      if (other.hasArmyGroupId()) {
        setArmyGroupId(other.getArmyGroupId());
      }
      if (other.hasForceArmy()) {
        setForceArmy(other.getForceArmy());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasWalkTime()) {
        return false;
      }
      if (!hasForceId()) {
        return false;
      }
      if (!hasAimsX()) {
        return false;
      }
      if (!hasAimsY()) {
        return false;
      }
      if (!hasStartX()) {
        return false;
      }
      if (!hasStartY()) {
        return false;
      }
      if (!hasCancelWalkTime()) {
        return false;
      }
      if (!hasUserWalkTime()) {
        return false;
      }
      if (!hasArmyGroupId()) {
        return false;
      }
      if (!hasForceArmy()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.GoHome parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.GoHome) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int walkTime_ ;
    /**
     * <pre>
     *回城时间
     * </pre>
     *
     * <code>required int32 walkTime = 1;</code>
     */
    public boolean hasWalkTime() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *回城时间
     * </pre>
     *
     * <code>required int32 walkTime = 1;</code>
     */
    public int getWalkTime() {
      return walkTime_;
    }
    /**
     * <pre>
     *回城时间
     * </pre>
     *
     * <code>required int32 walkTime = 1;</code>
     */
    public Builder setWalkTime(int value) {
      bitField0_ |= 0x00000001;
      walkTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *回城时间
     * </pre>
     *
     * <code>required int32 walkTime = 1;</code>
     */
    public Builder clearWalkTime() {
      bitField0_ = (bitField0_ & ~0x00000001);
      walkTime_ = 0;
      onChanged();
      return this;
    }

    private long forceId_ ;
    /**
     * <pre>
     *回城部队
     * </pre>
     *
     * <code>required int64 forceId = 2;</code>
     */
    public boolean hasForceId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *回城部队
     * </pre>
     *
     * <code>required int64 forceId = 2;</code>
     */
    public long getForceId() {
      return forceId_;
    }
    /**
     * <pre>
     *回城部队
     * </pre>
     *
     * <code>required int64 forceId = 2;</code>
     */
    public Builder setForceId(long value) {
      bitField0_ |= 0x00000002;
      forceId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *回城部队
     * </pre>
     *
     * <code>required int64 forceId = 2;</code>
     */
    public Builder clearForceId() {
      bitField0_ = (bitField0_ & ~0x00000002);
      forceId_ = 0L;
      onChanged();
      return this;
    }

    private int aimsX_ ;
    /**
     * <pre>
     *目的地X
     * </pre>
     *
     * <code>required int32 aimsX = 3;</code>
     */
    public boolean hasAimsX() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     *目的地X
     * </pre>
     *
     * <code>required int32 aimsX = 3;</code>
     */
    public int getAimsX() {
      return aimsX_;
    }
    /**
     * <pre>
     *目的地X
     * </pre>
     *
     * <code>required int32 aimsX = 3;</code>
     */
    public Builder setAimsX(int value) {
      bitField0_ |= 0x00000004;
      aimsX_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *目的地X
     * </pre>
     *
     * <code>required int32 aimsX = 3;</code>
     */
    public Builder clearAimsX() {
      bitField0_ = (bitField0_ & ~0x00000004);
      aimsX_ = 0;
      onChanged();
      return this;
    }

    private int aimsY_ ;
    /**
     * <pre>
     *目的地Y
     * </pre>
     *
     * <code>required int32 aimsY = 4;</code>
     */
    public boolean hasAimsY() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     *目的地Y
     * </pre>
     *
     * <code>required int32 aimsY = 4;</code>
     */
    public int getAimsY() {
      return aimsY_;
    }
    /**
     * <pre>
     *目的地Y
     * </pre>
     *
     * <code>required int32 aimsY = 4;</code>
     */
    public Builder setAimsY(int value) {
      bitField0_ |= 0x00000008;
      aimsY_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *目的地Y
     * </pre>
     *
     * <code>required int32 aimsY = 4;</code>
     */
    public Builder clearAimsY() {
      bitField0_ = (bitField0_ & ~0x00000008);
      aimsY_ = 0;
      onChanged();
      return this;
    }

    private int startX_ ;
    /**
     * <pre>
     *出发地X
     * </pre>
     *
     * <code>required int32 startX = 5;</code>
     */
    public boolean hasStartX() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <pre>
     *出发地X
     * </pre>
     *
     * <code>required int32 startX = 5;</code>
     */
    public int getStartX() {
      return startX_;
    }
    /**
     * <pre>
     *出发地X
     * </pre>
     *
     * <code>required int32 startX = 5;</code>
     */
    public Builder setStartX(int value) {
      bitField0_ |= 0x00000010;
      startX_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *出发地X
     * </pre>
     *
     * <code>required int32 startX = 5;</code>
     */
    public Builder clearStartX() {
      bitField0_ = (bitField0_ & ~0x00000010);
      startX_ = 0;
      onChanged();
      return this;
    }

    private int startY_ ;
    /**
     * <pre>
     *出发地Y
     * </pre>
     *
     * <code>required int32 startY = 6;</code>
     */
    public boolean hasStartY() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    /**
     * <pre>
     *出发地Y
     * </pre>
     *
     * <code>required int32 startY = 6;</code>
     */
    public int getStartY() {
      return startY_;
    }
    /**
     * <pre>
     *出发地Y
     * </pre>
     *
     * <code>required int32 startY = 6;</code>
     */
    public Builder setStartY(int value) {
      bitField0_ |= 0x00000020;
      startY_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *出发地Y
     * </pre>
     *
     * <code>required int32 startY = 6;</code>
     */
    public Builder clearStartY() {
      bitField0_ = (bitField0_ & ~0x00000020);
      startY_ = 0;
      onChanged();
      return this;
    }

    private int cancelWalkTime_ ;
    /**
     * <pre>
     *半路折回取消行军的回城时间
     * </pre>
     *
     * <code>required int32 cancelWalkTime = 7;</code>
     */
    public boolean hasCancelWalkTime() {
      return ((bitField0_ & 0x00000040) == 0x00000040);
    }
    /**
     * <pre>
     *半路折回取消行军的回城时间
     * </pre>
     *
     * <code>required int32 cancelWalkTime = 7;</code>
     */
    public int getCancelWalkTime() {
      return cancelWalkTime_;
    }
    /**
     * <pre>
     *半路折回取消行军的回城时间
     * </pre>
     *
     * <code>required int32 cancelWalkTime = 7;</code>
     */
    public Builder setCancelWalkTime(int value) {
      bitField0_ |= 0x00000040;
      cancelWalkTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *半路折回取消行军的回城时间
     * </pre>
     *
     * <code>required int32 cancelWalkTime = 7;</code>
     */
    public Builder clearCancelWalkTime() {
      bitField0_ = (bitField0_ & ~0x00000040);
      cancelWalkTime_ = 0;
      onChanged();
      return this;
    }

    private int userWalkTime_ ;
    /**
     * <pre>
     *需要的总时间
     * </pre>
     *
     * <code>required int32 userWalkTime = 8;</code>
     */
    public boolean hasUserWalkTime() {
      return ((bitField0_ & 0x00000080) == 0x00000080);
    }
    /**
     * <pre>
     *需要的总时间
     * </pre>
     *
     * <code>required int32 userWalkTime = 8;</code>
     */
    public int getUserWalkTime() {
      return userWalkTime_;
    }
    /**
     * <pre>
     *需要的总时间
     * </pre>
     *
     * <code>required int32 userWalkTime = 8;</code>
     */
    public Builder setUserWalkTime(int value) {
      bitField0_ |= 0x00000080;
      userWalkTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *需要的总时间
     * </pre>
     *
     * <code>required int32 userWalkTime = 8;</code>
     */
    public Builder clearUserWalkTime() {
      bitField0_ = (bitField0_ & ~0x00000080);
      userWalkTime_ = 0;
      onChanged();
      return this;
    }

    private long armyGroupId_ ;
    /**
     * <pre>
     *部队隶属军团ID
     * </pre>
     *
     * <code>required int64 armyGroupId = 9;</code>
     */
    public boolean hasArmyGroupId() {
      return ((bitField0_ & 0x00000100) == 0x00000100);
    }
    /**
     * <pre>
     *部队隶属军团ID
     * </pre>
     *
     * <code>required int64 armyGroupId = 9;</code>
     */
    public long getArmyGroupId() {
      return armyGroupId_;
    }
    /**
     * <pre>
     *部队隶属军团ID
     * </pre>
     *
     * <code>required int64 armyGroupId = 9;</code>
     */
    public Builder setArmyGroupId(long value) {
      bitField0_ |= 0x00000100;
      armyGroupId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *部队隶属军团ID
     * </pre>
     *
     * <code>required int64 armyGroupId = 9;</code>
     */
    public Builder clearArmyGroupId() {
      bitField0_ = (bitField0_ & ~0x00000100);
      armyGroupId_ = 0L;
      onChanged();
      return this;
    }

    private int forceArmy_ ;
    /**
     * <pre>
     * 部队兵种
     * </pre>
     *
     * <code>required int32 forceArmy = 10;</code>
     */
    public boolean hasForceArmy() {
      return ((bitField0_ & 0x00000200) == 0x00000200);
    }
    /**
     * <pre>
     * 部队兵种
     * </pre>
     *
     * <code>required int32 forceArmy = 10;</code>
     */
    public int getForceArmy() {
      return forceArmy_;
    }
    /**
     * <pre>
     * 部队兵种
     * </pre>
     *
     * <code>required int32 forceArmy = 10;</code>
     */
    public Builder setForceArmy(int value) {
      bitField0_ |= 0x00000200;
      forceArmy_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 部队兵种
     * </pre>
     *
     * <code>required int32 forceArmy = 10;</code>
     */
    public Builder clearForceArmy() {
      bitField0_ = (bitField0_ & ~0x00000200);
      forceArmy_ = 0;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.GoHome)
  }

  // @@protoc_insertion_point(class_scope:client2server.GoHome)
  private static final pb4client.GoHome DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.GoHome();
  }

  public static pb4client.GoHome getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<GoHome>
      PARSER = new com.google.protobuf.AbstractParser<GoHome>() {
    public GoHome parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new GoHome(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GoHome> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GoHome> getParserForType() {
    return PARSER;
  }

  public pb4client.GoHome getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
