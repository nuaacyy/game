// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.OnlineRewardInfo}
 */
public  final class OnlineRewardInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.OnlineRewardInfo)
    OnlineRewardInfoOrBuilder {
  // Use OnlineRewardInfo.newBuilder() to construct.
  private OnlineRewardInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private OnlineRewardInfo() {
    todayOnlineNum_ = 0;
    nextOnlineReward_ = "";
    overTime_ = 0;
    bigOnlineReward_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private OnlineRewardInfo(
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
            todayOnlineNum_ = input.readInt32();
            break;
          }
          case 18: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000002;
            nextOnlineReward_ = bs;
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            overTime_ = input.readInt32();
            break;
          }
          case 34: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000008;
            bigOnlineReward_ = bs;
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
    return pb4client.War2GamePkt.internal_static_client2server_OnlineRewardInfo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_OnlineRewardInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.OnlineRewardInfo.class, pb4client.OnlineRewardInfo.Builder.class);
  }

  private int bitField0_;
  public static final int TODAYONLINENUM_FIELD_NUMBER = 1;
  private int todayOnlineNum_;
  /**
   * <pre>
   * 本日领取次数
   * </pre>
   *
   * <code>required int32 todayOnlineNum = 1;</code>
   */
  public boolean hasTodayOnlineNum() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 本日领取次数
   * </pre>
   *
   * <code>required int32 todayOnlineNum = 1;</code>
   */
  public int getTodayOnlineNum() {
    return todayOnlineNum_;
  }

  public static final int NEXTONLINEREWARD_FIELD_NUMBER = 2;
  private volatile java.lang.Object nextOnlineReward_;
  /**
   * <pre>
   * 当前档奖励
   * </pre>
   *
   * <code>required string nextOnlineReward = 2;</code>
   */
  public boolean hasNextOnlineReward() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 当前档奖励
   * </pre>
   *
   * <code>required string nextOnlineReward = 2;</code>
   */
  public java.lang.String getNextOnlineReward() {
    java.lang.Object ref = nextOnlineReward_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        nextOnlineReward_ = s;
      }
      return s;
    }
  }
  /**
   * <pre>
   * 当前档奖励
   * </pre>
   *
   * <code>required string nextOnlineReward = 2;</code>
   */
  public com.google.protobuf.ByteString
      getNextOnlineRewardBytes() {
    java.lang.Object ref = nextOnlineReward_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      nextOnlineReward_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int OVERTIME_FIELD_NUMBER = 3;
  private int overTime_;
  /**
   * <pre>
   * 当前档可领奖时间
   * </pre>
   *
   * <code>required int32 overTime = 3;</code>
   */
  public boolean hasOverTime() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   * 当前档可领奖时间
   * </pre>
   *
   * <code>required int32 overTime = 3;</code>
   */
  public int getOverTime() {
    return overTime_;
  }

  public static final int BIGONLINEREWARD_FIELD_NUMBER = 4;
  private volatile java.lang.Object bigOnlineReward_;
  /**
   * <pre>
   * 本日大奖奖励
   * </pre>
   *
   * <code>required string bigOnlineReward = 4;</code>
   */
  public boolean hasBigOnlineReward() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   * 本日大奖奖励
   * </pre>
   *
   * <code>required string bigOnlineReward = 4;</code>
   */
  public java.lang.String getBigOnlineReward() {
    java.lang.Object ref = bigOnlineReward_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        bigOnlineReward_ = s;
      }
      return s;
    }
  }
  /**
   * <pre>
   * 本日大奖奖励
   * </pre>
   *
   * <code>required string bigOnlineReward = 4;</code>
   */
  public com.google.protobuf.ByteString
      getBigOnlineRewardBytes() {
    java.lang.Object ref = bigOnlineReward_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      bigOnlineReward_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasTodayOnlineNum()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasNextOnlineReward()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasOverTime()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasBigOnlineReward()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, todayOnlineNum_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, nextOnlineReward_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, overTime_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, bigOnlineReward_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, todayOnlineNum_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, nextOnlineReward_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, overTime_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, bigOnlineReward_);
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
    if (!(obj instanceof pb4client.OnlineRewardInfo)) {
      return super.equals(obj);
    }
    pb4client.OnlineRewardInfo other = (pb4client.OnlineRewardInfo) obj;

    boolean result = true;
    result = result && (hasTodayOnlineNum() == other.hasTodayOnlineNum());
    if (hasTodayOnlineNum()) {
      result = result && (getTodayOnlineNum()
          == other.getTodayOnlineNum());
    }
    result = result && (hasNextOnlineReward() == other.hasNextOnlineReward());
    if (hasNextOnlineReward()) {
      result = result && getNextOnlineReward()
          .equals(other.getNextOnlineReward());
    }
    result = result && (hasOverTime() == other.hasOverTime());
    if (hasOverTime()) {
      result = result && (getOverTime()
          == other.getOverTime());
    }
    result = result && (hasBigOnlineReward() == other.hasBigOnlineReward());
    if (hasBigOnlineReward()) {
      result = result && getBigOnlineReward()
          .equals(other.getBigOnlineReward());
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
    if (hasTodayOnlineNum()) {
      hash = (37 * hash) + TODAYONLINENUM_FIELD_NUMBER;
      hash = (53 * hash) + getTodayOnlineNum();
    }
    if (hasNextOnlineReward()) {
      hash = (37 * hash) + NEXTONLINEREWARD_FIELD_NUMBER;
      hash = (53 * hash) + getNextOnlineReward().hashCode();
    }
    if (hasOverTime()) {
      hash = (37 * hash) + OVERTIME_FIELD_NUMBER;
      hash = (53 * hash) + getOverTime();
    }
    if (hasBigOnlineReward()) {
      hash = (37 * hash) + BIGONLINEREWARD_FIELD_NUMBER;
      hash = (53 * hash) + getBigOnlineReward().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.OnlineRewardInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.OnlineRewardInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.OnlineRewardInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.OnlineRewardInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.OnlineRewardInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.OnlineRewardInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.OnlineRewardInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.OnlineRewardInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.OnlineRewardInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.OnlineRewardInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.OnlineRewardInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.OnlineRewardInfo parseFrom(
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
  public static Builder newBuilder(pb4client.OnlineRewardInfo prototype) {
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
   * Protobuf type {@code client2server.OnlineRewardInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.OnlineRewardInfo)
      pb4client.OnlineRewardInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_OnlineRewardInfo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_OnlineRewardInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.OnlineRewardInfo.class, pb4client.OnlineRewardInfo.Builder.class);
    }

    // Construct using pb4client.OnlineRewardInfo.newBuilder()
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
      todayOnlineNum_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      nextOnlineReward_ = "";
      bitField0_ = (bitField0_ & ~0x00000002);
      overTime_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      bigOnlineReward_ = "";
      bitField0_ = (bitField0_ & ~0x00000008);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_OnlineRewardInfo_descriptor;
    }

    public pb4client.OnlineRewardInfo getDefaultInstanceForType() {
      return pb4client.OnlineRewardInfo.getDefaultInstance();
    }

    public pb4client.OnlineRewardInfo build() {
      pb4client.OnlineRewardInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.OnlineRewardInfo buildPartial() {
      pb4client.OnlineRewardInfo result = new pb4client.OnlineRewardInfo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.todayOnlineNum_ = todayOnlineNum_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.nextOnlineReward_ = nextOnlineReward_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.overTime_ = overTime_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.bigOnlineReward_ = bigOnlineReward_;
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
      if (other instanceof pb4client.OnlineRewardInfo) {
        return mergeFrom((pb4client.OnlineRewardInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.OnlineRewardInfo other) {
      if (other == pb4client.OnlineRewardInfo.getDefaultInstance()) return this;
      if (other.hasTodayOnlineNum()) {
        setTodayOnlineNum(other.getTodayOnlineNum());
      }
      if (other.hasNextOnlineReward()) {
        bitField0_ |= 0x00000002;
        nextOnlineReward_ = other.nextOnlineReward_;
        onChanged();
      }
      if (other.hasOverTime()) {
        setOverTime(other.getOverTime());
      }
      if (other.hasBigOnlineReward()) {
        bitField0_ |= 0x00000008;
        bigOnlineReward_ = other.bigOnlineReward_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasTodayOnlineNum()) {
        return false;
      }
      if (!hasNextOnlineReward()) {
        return false;
      }
      if (!hasOverTime()) {
        return false;
      }
      if (!hasBigOnlineReward()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.OnlineRewardInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.OnlineRewardInfo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int todayOnlineNum_ ;
    /**
     * <pre>
     * 本日领取次数
     * </pre>
     *
     * <code>required int32 todayOnlineNum = 1;</code>
     */
    public boolean hasTodayOnlineNum() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 本日领取次数
     * </pre>
     *
     * <code>required int32 todayOnlineNum = 1;</code>
     */
    public int getTodayOnlineNum() {
      return todayOnlineNum_;
    }
    /**
     * <pre>
     * 本日领取次数
     * </pre>
     *
     * <code>required int32 todayOnlineNum = 1;</code>
     */
    public Builder setTodayOnlineNum(int value) {
      bitField0_ |= 0x00000001;
      todayOnlineNum_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 本日领取次数
     * </pre>
     *
     * <code>required int32 todayOnlineNum = 1;</code>
     */
    public Builder clearTodayOnlineNum() {
      bitField0_ = (bitField0_ & ~0x00000001);
      todayOnlineNum_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object nextOnlineReward_ = "";
    /**
     * <pre>
     * 当前档奖励
     * </pre>
     *
     * <code>required string nextOnlineReward = 2;</code>
     */
    public boolean hasNextOnlineReward() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 当前档奖励
     * </pre>
     *
     * <code>required string nextOnlineReward = 2;</code>
     */
    public java.lang.String getNextOnlineReward() {
      java.lang.Object ref = nextOnlineReward_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          nextOnlineReward_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * 当前档奖励
     * </pre>
     *
     * <code>required string nextOnlineReward = 2;</code>
     */
    public com.google.protobuf.ByteString
        getNextOnlineRewardBytes() {
      java.lang.Object ref = nextOnlineReward_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        nextOnlineReward_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * 当前档奖励
     * </pre>
     *
     * <code>required string nextOnlineReward = 2;</code>
     */
    public Builder setNextOnlineReward(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      nextOnlineReward_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 当前档奖励
     * </pre>
     *
     * <code>required string nextOnlineReward = 2;</code>
     */
    public Builder clearNextOnlineReward() {
      bitField0_ = (bitField0_ & ~0x00000002);
      nextOnlineReward_ = getDefaultInstance().getNextOnlineReward();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 当前档奖励
     * </pre>
     *
     * <code>required string nextOnlineReward = 2;</code>
     */
    public Builder setNextOnlineRewardBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      nextOnlineReward_ = value;
      onChanged();
      return this;
    }

    private int overTime_ ;
    /**
     * <pre>
     * 当前档可领奖时间
     * </pre>
     *
     * <code>required int32 overTime = 3;</code>
     */
    public boolean hasOverTime() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     * 当前档可领奖时间
     * </pre>
     *
     * <code>required int32 overTime = 3;</code>
     */
    public int getOverTime() {
      return overTime_;
    }
    /**
     * <pre>
     * 当前档可领奖时间
     * </pre>
     *
     * <code>required int32 overTime = 3;</code>
     */
    public Builder setOverTime(int value) {
      bitField0_ |= 0x00000004;
      overTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 当前档可领奖时间
     * </pre>
     *
     * <code>required int32 overTime = 3;</code>
     */
    public Builder clearOverTime() {
      bitField0_ = (bitField0_ & ~0x00000004);
      overTime_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object bigOnlineReward_ = "";
    /**
     * <pre>
     * 本日大奖奖励
     * </pre>
     *
     * <code>required string bigOnlineReward = 4;</code>
     */
    public boolean hasBigOnlineReward() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     * 本日大奖奖励
     * </pre>
     *
     * <code>required string bigOnlineReward = 4;</code>
     */
    public java.lang.String getBigOnlineReward() {
      java.lang.Object ref = bigOnlineReward_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          bigOnlineReward_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * 本日大奖奖励
     * </pre>
     *
     * <code>required string bigOnlineReward = 4;</code>
     */
    public com.google.protobuf.ByteString
        getBigOnlineRewardBytes() {
      java.lang.Object ref = bigOnlineReward_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        bigOnlineReward_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * 本日大奖奖励
     * </pre>
     *
     * <code>required string bigOnlineReward = 4;</code>
     */
    public Builder setBigOnlineReward(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
      bigOnlineReward_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 本日大奖奖励
     * </pre>
     *
     * <code>required string bigOnlineReward = 4;</code>
     */
    public Builder clearBigOnlineReward() {
      bitField0_ = (bitField0_ & ~0x00000008);
      bigOnlineReward_ = getDefaultInstance().getBigOnlineReward();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 本日大奖奖励
     * </pre>
     *
     * <code>required string bigOnlineReward = 4;</code>
     */
    public Builder setBigOnlineRewardBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
      bigOnlineReward_ = value;
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


    // @@protoc_insertion_point(builder_scope:client2server.OnlineRewardInfo)
  }

  // @@protoc_insertion_point(class_scope:client2server.OnlineRewardInfo)
  private static final pb4client.OnlineRewardInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.OnlineRewardInfo();
  }

  public static pb4client.OnlineRewardInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<OnlineRewardInfo>
      PARSER = new com.google.protobuf.AbstractParser<OnlineRewardInfo>() {
    public OnlineRewardInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new OnlineRewardInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<OnlineRewardInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<OnlineRewardInfo> getParserForType() {
    return PARSER;
  }

  public pb4client.OnlineRewardInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

