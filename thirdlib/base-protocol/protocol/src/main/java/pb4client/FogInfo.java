// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 *迷雾信息
 * </pre>
 *
 * Protobuf type {@code client2server.FogInfo}
 */
public  final class FogInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.FogInfo)
    FogInfoOrBuilder {
  // Use FogInfo.newBuilder() to construct.
  private FogInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private FogInfo() {
    fogId_ = 0;
    state_ = 0;
    power_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private FogInfo(
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
            fogId_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            state_ = input.readInt32();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            power_ = input.readInt64();
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
    return pb4client.War2GamePkt.internal_static_client2server_FogInfo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_FogInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.FogInfo.class, pb4client.FogInfo.Builder.class);
  }

  private int bitField0_;
  public static final int FOGID_FIELD_NUMBER = 1;
  private int fogId_;
  /**
   * <pre>
   *配置ID
   * </pre>
   *
   * <code>required int32 fogId = 1;</code>
   */
  public boolean hasFogId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *配置ID
   * </pre>
   *
   * <code>required int32 fogId = 1;</code>
   */
  public int getFogId() {
    return fogId_;
  }

  public static final int STATE_FIELD_NUMBER = 2;
  private int state_;
  /**
   * <pre>
   *迷雾状态 1、未战胜 2、已战胜未领取奖励 3、已战胜且领取奖励
   * </pre>
   *
   * <code>required int32 state = 2;</code>
   */
  public boolean hasState() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *迷雾状态 1、未战胜 2、已战胜未领取奖励 3、已战胜且领取奖励
   * </pre>
   *
   * <code>required int32 state = 2;</code>
   */
  public int getState() {
    return state_;
  }

  public static final int POWER_FIELD_NUMBER = 3;
  private long power_;
  /**
   * <pre>
   *迷雾战斗力
   * </pre>
   *
   * <code>optional int64 power = 3;</code>
   */
  public boolean hasPower() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   *迷雾战斗力
   * </pre>
   *
   * <code>optional int64 power = 3;</code>
   */
  public long getPower() {
    return power_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasFogId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasState()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, fogId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, state_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt64(3, power_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, fogId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, state_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, power_);
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
    if (!(obj instanceof pb4client.FogInfo)) {
      return super.equals(obj);
    }
    pb4client.FogInfo other = (pb4client.FogInfo) obj;

    boolean result = true;
    result = result && (hasFogId() == other.hasFogId());
    if (hasFogId()) {
      result = result && (getFogId()
          == other.getFogId());
    }
    result = result && (hasState() == other.hasState());
    if (hasState()) {
      result = result && (getState()
          == other.getState());
    }
    result = result && (hasPower() == other.hasPower());
    if (hasPower()) {
      result = result && (getPower()
          == other.getPower());
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
    if (hasFogId()) {
      hash = (37 * hash) + FOGID_FIELD_NUMBER;
      hash = (53 * hash) + getFogId();
    }
    if (hasState()) {
      hash = (37 * hash) + STATE_FIELD_NUMBER;
      hash = (53 * hash) + getState();
    }
    if (hasPower()) {
      hash = (37 * hash) + POWER_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getPower());
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.FogInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.FogInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.FogInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.FogInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.FogInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.FogInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.FogInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.FogInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.FogInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.FogInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.FogInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.FogInfo parseFrom(
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
  public static Builder newBuilder(pb4client.FogInfo prototype) {
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
   *迷雾信息
   * </pre>
   *
   * Protobuf type {@code client2server.FogInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.FogInfo)
      pb4client.FogInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_FogInfo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_FogInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.FogInfo.class, pb4client.FogInfo.Builder.class);
    }

    // Construct using pb4client.FogInfo.newBuilder()
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
      fogId_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      state_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      power_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000004);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_FogInfo_descriptor;
    }

    public pb4client.FogInfo getDefaultInstanceForType() {
      return pb4client.FogInfo.getDefaultInstance();
    }

    public pb4client.FogInfo build() {
      pb4client.FogInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.FogInfo buildPartial() {
      pb4client.FogInfo result = new pb4client.FogInfo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.fogId_ = fogId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.state_ = state_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.power_ = power_;
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
      if (other instanceof pb4client.FogInfo) {
        return mergeFrom((pb4client.FogInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.FogInfo other) {
      if (other == pb4client.FogInfo.getDefaultInstance()) return this;
      if (other.hasFogId()) {
        setFogId(other.getFogId());
      }
      if (other.hasState()) {
        setState(other.getState());
      }
      if (other.hasPower()) {
        setPower(other.getPower());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasFogId()) {
        return false;
      }
      if (!hasState()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.FogInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.FogInfo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int fogId_ ;
    /**
     * <pre>
     *配置ID
     * </pre>
     *
     * <code>required int32 fogId = 1;</code>
     */
    public boolean hasFogId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *配置ID
     * </pre>
     *
     * <code>required int32 fogId = 1;</code>
     */
    public int getFogId() {
      return fogId_;
    }
    /**
     * <pre>
     *配置ID
     * </pre>
     *
     * <code>required int32 fogId = 1;</code>
     */
    public Builder setFogId(int value) {
      bitField0_ |= 0x00000001;
      fogId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *配置ID
     * </pre>
     *
     * <code>required int32 fogId = 1;</code>
     */
    public Builder clearFogId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      fogId_ = 0;
      onChanged();
      return this;
    }

    private int state_ ;
    /**
     * <pre>
     *迷雾状态 1、未战胜 2、已战胜未领取奖励 3、已战胜且领取奖励
     * </pre>
     *
     * <code>required int32 state = 2;</code>
     */
    public boolean hasState() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *迷雾状态 1、未战胜 2、已战胜未领取奖励 3、已战胜且领取奖励
     * </pre>
     *
     * <code>required int32 state = 2;</code>
     */
    public int getState() {
      return state_;
    }
    /**
     * <pre>
     *迷雾状态 1、未战胜 2、已战胜未领取奖励 3、已战胜且领取奖励
     * </pre>
     *
     * <code>required int32 state = 2;</code>
     */
    public Builder setState(int value) {
      bitField0_ |= 0x00000002;
      state_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *迷雾状态 1、未战胜 2、已战胜未领取奖励 3、已战胜且领取奖励
     * </pre>
     *
     * <code>required int32 state = 2;</code>
     */
    public Builder clearState() {
      bitField0_ = (bitField0_ & ~0x00000002);
      state_ = 0;
      onChanged();
      return this;
    }

    private long power_ ;
    /**
     * <pre>
     *迷雾战斗力
     * </pre>
     *
     * <code>optional int64 power = 3;</code>
     */
    public boolean hasPower() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     *迷雾战斗力
     * </pre>
     *
     * <code>optional int64 power = 3;</code>
     */
    public long getPower() {
      return power_;
    }
    /**
     * <pre>
     *迷雾战斗力
     * </pre>
     *
     * <code>optional int64 power = 3;</code>
     */
    public Builder setPower(long value) {
      bitField0_ |= 0x00000004;
      power_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *迷雾战斗力
     * </pre>
     *
     * <code>optional int64 power = 3;</code>
     */
    public Builder clearPower() {
      bitField0_ = (bitField0_ & ~0x00000004);
      power_ = 0L;
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


    // @@protoc_insertion_point(builder_scope:client2server.FogInfo)
  }

  // @@protoc_insertion_point(class_scope:client2server.FogInfo)
  private static final pb4client.FogInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.FogInfo();
  }

  public static pb4client.FogInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<FogInfo>
      PARSER = new com.google.protobuf.AbstractParser<FogInfo>() {
    public FogInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new FogInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<FogInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<FogInfo> getParserForType() {
    return PARSER;
  }

  public pb4client.FogInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

