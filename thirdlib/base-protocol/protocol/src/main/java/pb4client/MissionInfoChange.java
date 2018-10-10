// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 3120
 * 推图信息变化
 * </pre>
 *
 * Protobuf type {@code client2server.MissionInfoChange}
 */
public  final class MissionInfoChange extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.MissionInfoChange)
    MissionInfoChangeOrBuilder {
  // Use MissionInfoChange.newBuilder() to construct.
  private MissionInfoChange(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private MissionInfoChange() {
    missionId_ = 0;
    missionDurability_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private MissionInfoChange(
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
            missionId_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            missionDurability_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_MissionInfoChange_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_MissionInfoChange_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.MissionInfoChange.class, pb4client.MissionInfoChange.Builder.class);
  }

  private int bitField0_;
  public static final int MISSIONID_FIELD_NUMBER = 1;
  private int missionId_;
  /**
   * <pre>
   *当前关卡ID
   * </pre>
   *
   * <code>required int32 missionId = 1;</code>
   */
  public boolean hasMissionId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *当前关卡ID
   * </pre>
   *
   * <code>required int32 missionId = 1;</code>
   */
  public int getMissionId() {
    return missionId_;
  }

  public static final int MISSIONDURABILITY_FIELD_NUMBER = 2;
  private int missionDurability_;
  /**
   * <pre>
   *当前关卡耐久 0表示配置满值 非0表示当前剩余耐久
   * </pre>
   *
   * <code>required int32 missionDurability = 2;</code>
   */
  public boolean hasMissionDurability() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *当前关卡耐久 0表示配置满值 非0表示当前剩余耐久
   * </pre>
   *
   * <code>required int32 missionDurability = 2;</code>
   */
  public int getMissionDurability() {
    return missionDurability_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasMissionId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasMissionDurability()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, missionId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, missionDurability_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, missionId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, missionDurability_);
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
    if (!(obj instanceof pb4client.MissionInfoChange)) {
      return super.equals(obj);
    }
    pb4client.MissionInfoChange other = (pb4client.MissionInfoChange) obj;

    boolean result = true;
    result = result && (hasMissionId() == other.hasMissionId());
    if (hasMissionId()) {
      result = result && (getMissionId()
          == other.getMissionId());
    }
    result = result && (hasMissionDurability() == other.hasMissionDurability());
    if (hasMissionDurability()) {
      result = result && (getMissionDurability()
          == other.getMissionDurability());
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
    if (hasMissionId()) {
      hash = (37 * hash) + MISSIONID_FIELD_NUMBER;
      hash = (53 * hash) + getMissionId();
    }
    if (hasMissionDurability()) {
      hash = (37 * hash) + MISSIONDURABILITY_FIELD_NUMBER;
      hash = (53 * hash) + getMissionDurability();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.MissionInfoChange parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.MissionInfoChange parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.MissionInfoChange parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.MissionInfoChange parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.MissionInfoChange parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.MissionInfoChange parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.MissionInfoChange parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.MissionInfoChange parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.MissionInfoChange parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.MissionInfoChange parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.MissionInfoChange parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.MissionInfoChange parseFrom(
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
  public static Builder newBuilder(pb4client.MissionInfoChange prototype) {
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
   * msgType = 3120
   * 推图信息变化
   * </pre>
   *
   * Protobuf type {@code client2server.MissionInfoChange}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.MissionInfoChange)
      pb4client.MissionInfoChangeOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_MissionInfoChange_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_MissionInfoChange_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.MissionInfoChange.class, pb4client.MissionInfoChange.Builder.class);
    }

    // Construct using pb4client.MissionInfoChange.newBuilder()
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
      missionId_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      missionDurability_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_MissionInfoChange_descriptor;
    }

    public pb4client.MissionInfoChange getDefaultInstanceForType() {
      return pb4client.MissionInfoChange.getDefaultInstance();
    }

    public pb4client.MissionInfoChange build() {
      pb4client.MissionInfoChange result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.MissionInfoChange buildPartial() {
      pb4client.MissionInfoChange result = new pb4client.MissionInfoChange(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.missionId_ = missionId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.missionDurability_ = missionDurability_;
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
      if (other instanceof pb4client.MissionInfoChange) {
        return mergeFrom((pb4client.MissionInfoChange)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.MissionInfoChange other) {
      if (other == pb4client.MissionInfoChange.getDefaultInstance()) return this;
      if (other.hasMissionId()) {
        setMissionId(other.getMissionId());
      }
      if (other.hasMissionDurability()) {
        setMissionDurability(other.getMissionDurability());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasMissionId()) {
        return false;
      }
      if (!hasMissionDurability()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.MissionInfoChange parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.MissionInfoChange) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int missionId_ ;
    /**
     * <pre>
     *当前关卡ID
     * </pre>
     *
     * <code>required int32 missionId = 1;</code>
     */
    public boolean hasMissionId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *当前关卡ID
     * </pre>
     *
     * <code>required int32 missionId = 1;</code>
     */
    public int getMissionId() {
      return missionId_;
    }
    /**
     * <pre>
     *当前关卡ID
     * </pre>
     *
     * <code>required int32 missionId = 1;</code>
     */
    public Builder setMissionId(int value) {
      bitField0_ |= 0x00000001;
      missionId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *当前关卡ID
     * </pre>
     *
     * <code>required int32 missionId = 1;</code>
     */
    public Builder clearMissionId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      missionId_ = 0;
      onChanged();
      return this;
    }

    private int missionDurability_ ;
    /**
     * <pre>
     *当前关卡耐久 0表示配置满值 非0表示当前剩余耐久
     * </pre>
     *
     * <code>required int32 missionDurability = 2;</code>
     */
    public boolean hasMissionDurability() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *当前关卡耐久 0表示配置满值 非0表示当前剩余耐久
     * </pre>
     *
     * <code>required int32 missionDurability = 2;</code>
     */
    public int getMissionDurability() {
      return missionDurability_;
    }
    /**
     * <pre>
     *当前关卡耐久 0表示配置满值 非0表示当前剩余耐久
     * </pre>
     *
     * <code>required int32 missionDurability = 2;</code>
     */
    public Builder setMissionDurability(int value) {
      bitField0_ |= 0x00000002;
      missionDurability_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *当前关卡耐久 0表示配置满值 非0表示当前剩余耐久
     * </pre>
     *
     * <code>required int32 missionDurability = 2;</code>
     */
    public Builder clearMissionDurability() {
      bitField0_ = (bitField0_ & ~0x00000002);
      missionDurability_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.MissionInfoChange)
  }

  // @@protoc_insertion_point(class_scope:client2server.MissionInfoChange)
  private static final pb4client.MissionInfoChange DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.MissionInfoChange();
  }

  public static pb4client.MissionInfoChange getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<MissionInfoChange>
      PARSER = new com.google.protobuf.AbstractParser<MissionInfoChange>() {
    public MissionInfoChange parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new MissionInfoChange(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<MissionInfoChange> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<MissionInfoChange> getParserForType() {
    return PARSER;
  }

  public pb4client.MissionInfoChange getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

