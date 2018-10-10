// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 3126
 * 玩家帮派科技研发数据发生变化
 * </pre>
 *
 * Protobuf type {@code client2server.PlayerAllianceResearchChange}
 */
public  final class PlayerAllianceResearchChange extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.PlayerAllianceResearchChange)
    PlayerAllianceResearchChangeOrBuilder {
  // Use PlayerAllianceResearchChange.newBuilder() to construct.
  private PlayerAllianceResearchChange(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private PlayerAllianceResearchChange() {
    allianceResearchNum_ = 0;
    lastAllianceResearchTime_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private PlayerAllianceResearchChange(
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
            allianceResearchNum_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            lastAllianceResearchTime_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_PlayerAllianceResearchChange_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_PlayerAllianceResearchChange_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.PlayerAllianceResearchChange.class, pb4client.PlayerAllianceResearchChange.Builder.class);
  }

  private int bitField0_;
  public static final int ALLIANCERESEARCHNUM_FIELD_NUMBER = 1;
  private int allianceResearchNum_;
  /**
   * <pre>
   *联盟普通捐献次数
   * </pre>
   *
   * <code>required int32 allianceResearchNum = 1;</code>
   */
  public boolean hasAllianceResearchNum() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *联盟普通捐献次数
   * </pre>
   *
   * <code>required int32 allianceResearchNum = 1;</code>
   */
  public int getAllianceResearchNum() {
    return allianceResearchNum_;
  }

  public static final int LASTALLIANCERESEARCHTIME_FIELD_NUMBER = 2;
  private int lastAllianceResearchTime_;
  /**
   * <pre>
   *上次联盟普通捐献次数回复时间
   * </pre>
   *
   * <code>required int32 lastAllianceResearchTime = 2;</code>
   */
  public boolean hasLastAllianceResearchTime() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *上次联盟普通捐献次数回复时间
   * </pre>
   *
   * <code>required int32 lastAllianceResearchTime = 2;</code>
   */
  public int getLastAllianceResearchTime() {
    return lastAllianceResearchTime_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasAllianceResearchNum()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasLastAllianceResearchTime()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, allianceResearchNum_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, lastAllianceResearchTime_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, allianceResearchNum_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, lastAllianceResearchTime_);
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
    if (!(obj instanceof pb4client.PlayerAllianceResearchChange)) {
      return super.equals(obj);
    }
    pb4client.PlayerAllianceResearchChange other = (pb4client.PlayerAllianceResearchChange) obj;

    boolean result = true;
    result = result && (hasAllianceResearchNum() == other.hasAllianceResearchNum());
    if (hasAllianceResearchNum()) {
      result = result && (getAllianceResearchNum()
          == other.getAllianceResearchNum());
    }
    result = result && (hasLastAllianceResearchTime() == other.hasLastAllianceResearchTime());
    if (hasLastAllianceResearchTime()) {
      result = result && (getLastAllianceResearchTime()
          == other.getLastAllianceResearchTime());
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
    if (hasAllianceResearchNum()) {
      hash = (37 * hash) + ALLIANCERESEARCHNUM_FIELD_NUMBER;
      hash = (53 * hash) + getAllianceResearchNum();
    }
    if (hasLastAllianceResearchTime()) {
      hash = (37 * hash) + LASTALLIANCERESEARCHTIME_FIELD_NUMBER;
      hash = (53 * hash) + getLastAllianceResearchTime();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.PlayerAllianceResearchChange parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.PlayerAllianceResearchChange parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.PlayerAllianceResearchChange parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.PlayerAllianceResearchChange parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.PlayerAllianceResearchChange parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.PlayerAllianceResearchChange parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.PlayerAllianceResearchChange parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.PlayerAllianceResearchChange parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.PlayerAllianceResearchChange parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.PlayerAllianceResearchChange parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.PlayerAllianceResearchChange parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.PlayerAllianceResearchChange parseFrom(
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
  public static Builder newBuilder(pb4client.PlayerAllianceResearchChange prototype) {
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
   * msgType = 3126
   * 玩家帮派科技研发数据发生变化
   * </pre>
   *
   * Protobuf type {@code client2server.PlayerAllianceResearchChange}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.PlayerAllianceResearchChange)
      pb4client.PlayerAllianceResearchChangeOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_PlayerAllianceResearchChange_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_PlayerAllianceResearchChange_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.PlayerAllianceResearchChange.class, pb4client.PlayerAllianceResearchChange.Builder.class);
    }

    // Construct using pb4client.PlayerAllianceResearchChange.newBuilder()
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
      allianceResearchNum_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      lastAllianceResearchTime_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_PlayerAllianceResearchChange_descriptor;
    }

    public pb4client.PlayerAllianceResearchChange getDefaultInstanceForType() {
      return pb4client.PlayerAllianceResearchChange.getDefaultInstance();
    }

    public pb4client.PlayerAllianceResearchChange build() {
      pb4client.PlayerAllianceResearchChange result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.PlayerAllianceResearchChange buildPartial() {
      pb4client.PlayerAllianceResearchChange result = new pb4client.PlayerAllianceResearchChange(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.allianceResearchNum_ = allianceResearchNum_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.lastAllianceResearchTime_ = lastAllianceResearchTime_;
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
      if (other instanceof pb4client.PlayerAllianceResearchChange) {
        return mergeFrom((pb4client.PlayerAllianceResearchChange)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.PlayerAllianceResearchChange other) {
      if (other == pb4client.PlayerAllianceResearchChange.getDefaultInstance()) return this;
      if (other.hasAllianceResearchNum()) {
        setAllianceResearchNum(other.getAllianceResearchNum());
      }
      if (other.hasLastAllianceResearchTime()) {
        setLastAllianceResearchTime(other.getLastAllianceResearchTime());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasAllianceResearchNum()) {
        return false;
      }
      if (!hasLastAllianceResearchTime()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.PlayerAllianceResearchChange parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.PlayerAllianceResearchChange) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int allianceResearchNum_ ;
    /**
     * <pre>
     *联盟普通捐献次数
     * </pre>
     *
     * <code>required int32 allianceResearchNum = 1;</code>
     */
    public boolean hasAllianceResearchNum() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *联盟普通捐献次数
     * </pre>
     *
     * <code>required int32 allianceResearchNum = 1;</code>
     */
    public int getAllianceResearchNum() {
      return allianceResearchNum_;
    }
    /**
     * <pre>
     *联盟普通捐献次数
     * </pre>
     *
     * <code>required int32 allianceResearchNum = 1;</code>
     */
    public Builder setAllianceResearchNum(int value) {
      bitField0_ |= 0x00000001;
      allianceResearchNum_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *联盟普通捐献次数
     * </pre>
     *
     * <code>required int32 allianceResearchNum = 1;</code>
     */
    public Builder clearAllianceResearchNum() {
      bitField0_ = (bitField0_ & ~0x00000001);
      allianceResearchNum_ = 0;
      onChanged();
      return this;
    }

    private int lastAllianceResearchTime_ ;
    /**
     * <pre>
     *上次联盟普通捐献次数回复时间
     * </pre>
     *
     * <code>required int32 lastAllianceResearchTime = 2;</code>
     */
    public boolean hasLastAllianceResearchTime() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *上次联盟普通捐献次数回复时间
     * </pre>
     *
     * <code>required int32 lastAllianceResearchTime = 2;</code>
     */
    public int getLastAllianceResearchTime() {
      return lastAllianceResearchTime_;
    }
    /**
     * <pre>
     *上次联盟普通捐献次数回复时间
     * </pre>
     *
     * <code>required int32 lastAllianceResearchTime = 2;</code>
     */
    public Builder setLastAllianceResearchTime(int value) {
      bitField0_ |= 0x00000002;
      lastAllianceResearchTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *上次联盟普通捐献次数回复时间
     * </pre>
     *
     * <code>required int32 lastAllianceResearchTime = 2;</code>
     */
    public Builder clearLastAllianceResearchTime() {
      bitField0_ = (bitField0_ & ~0x00000002);
      lastAllianceResearchTime_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.PlayerAllianceResearchChange)
  }

  // @@protoc_insertion_point(class_scope:client2server.PlayerAllianceResearchChange)
  private static final pb4client.PlayerAllianceResearchChange DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.PlayerAllianceResearchChange();
  }

  public static pb4client.PlayerAllianceResearchChange getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<PlayerAllianceResearchChange>
      PARSER = new com.google.protobuf.AbstractParser<PlayerAllianceResearchChange>() {
    public PlayerAllianceResearchChange parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new PlayerAllianceResearchChange(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<PlayerAllianceResearchChange> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<PlayerAllianceResearchChange> getParserForType() {
    return PARSER;
  }

  public pb4client.PlayerAllianceResearchChange getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

