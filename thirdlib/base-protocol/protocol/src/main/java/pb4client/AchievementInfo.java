// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.AchievementInfo}
 */
public  final class AchievementInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.AchievementInfo)
    AchievementInfoOrBuilder {
  // Use AchievementInfo.newBuilder() to construct.
  private AchievementInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AchievementInfo() {
    id_ = 0L;
    protoId_ = 0;
    state_ = 0;
    progress_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AchievementInfo(
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
            id_ = input.readInt64();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            protoId_ = input.readInt32();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            state_ = input.readInt32();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            progress_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_AchievementInfo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_AchievementInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.AchievementInfo.class, pb4client.AchievementInfo.Builder.class);
  }

  private int bitField0_;
  public static final int ID_FIELD_NUMBER = 1;
  private long id_;
  /**
   * <pre>
   * 成就唯一ID
   * </pre>
   *
   * <code>required int64 id = 1;</code>
   */
  public boolean hasId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 成就唯一ID
   * </pre>
   *
   * <code>required int64 id = 1;</code>
   */
  public long getId() {
    return id_;
  }

  public static final int PROTOID_FIELD_NUMBER = 2;
  private int protoId_;
  /**
   * <pre>
   * 成就模板ID
   * </pre>
   *
   * <code>required int32 protoId = 2;</code>
   */
  public boolean hasProtoId() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 成就模板ID
   * </pre>
   *
   * <code>required int32 protoId = 2;</code>
   */
  public int getProtoId() {
    return protoId_;
  }

  public static final int STATE_FIELD_NUMBER = 3;
  private int state_;
  /**
   * <pre>
   * 成就状态 0-进行中 1-已完成 2-已领取奖励
   * </pre>
   *
   * <code>required int32 state = 3;</code>
   */
  public boolean hasState() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   * 成就状态 0-进行中 1-已完成 2-已领取奖励
   * </pre>
   *
   * <code>required int32 state = 3;</code>
   */
  public int getState() {
    return state_;
  }

  public static final int PROGRESS_FIELD_NUMBER = 4;
  private int progress_;
  /**
   * <pre>
   * 成就当前进度
   * </pre>
   *
   * <code>required int32 progress = 4;</code>
   */
  public boolean hasProgress() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   * 成就当前进度
   * </pre>
   *
   * <code>required int32 progress = 4;</code>
   */
  public int getProgress() {
    return progress_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasProtoId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasState()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasProgress()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt64(1, id_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, protoId_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, state_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt32(4, progress_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, id_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, protoId_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, state_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, progress_);
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
    if (!(obj instanceof pb4client.AchievementInfo)) {
      return super.equals(obj);
    }
    pb4client.AchievementInfo other = (pb4client.AchievementInfo) obj;

    boolean result = true;
    result = result && (hasId() == other.hasId());
    if (hasId()) {
      result = result && (getId()
          == other.getId());
    }
    result = result && (hasProtoId() == other.hasProtoId());
    if (hasProtoId()) {
      result = result && (getProtoId()
          == other.getProtoId());
    }
    result = result && (hasState() == other.hasState());
    if (hasState()) {
      result = result && (getState()
          == other.getState());
    }
    result = result && (hasProgress() == other.hasProgress());
    if (hasProgress()) {
      result = result && (getProgress()
          == other.getProgress());
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
    if (hasId()) {
      hash = (37 * hash) + ID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getId());
    }
    if (hasProtoId()) {
      hash = (37 * hash) + PROTOID_FIELD_NUMBER;
      hash = (53 * hash) + getProtoId();
    }
    if (hasState()) {
      hash = (37 * hash) + STATE_FIELD_NUMBER;
      hash = (53 * hash) + getState();
    }
    if (hasProgress()) {
      hash = (37 * hash) + PROGRESS_FIELD_NUMBER;
      hash = (53 * hash) + getProgress();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.AchievementInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AchievementInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AchievementInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AchievementInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AchievementInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AchievementInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AchievementInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.AchievementInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.AchievementInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.AchievementInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.AchievementInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.AchievementInfo parseFrom(
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
  public static Builder newBuilder(pb4client.AchievementInfo prototype) {
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
   * Protobuf type {@code client2server.AchievementInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.AchievementInfo)
      pb4client.AchievementInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_AchievementInfo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_AchievementInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.AchievementInfo.class, pb4client.AchievementInfo.Builder.class);
    }

    // Construct using pb4client.AchievementInfo.newBuilder()
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
      id_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      protoId_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      state_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      progress_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_AchievementInfo_descriptor;
    }

    public pb4client.AchievementInfo getDefaultInstanceForType() {
      return pb4client.AchievementInfo.getDefaultInstance();
    }

    public pb4client.AchievementInfo build() {
      pb4client.AchievementInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.AchievementInfo buildPartial() {
      pb4client.AchievementInfo result = new pb4client.AchievementInfo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.id_ = id_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.protoId_ = protoId_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.state_ = state_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.progress_ = progress_;
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
      if (other instanceof pb4client.AchievementInfo) {
        return mergeFrom((pb4client.AchievementInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.AchievementInfo other) {
      if (other == pb4client.AchievementInfo.getDefaultInstance()) return this;
      if (other.hasId()) {
        setId(other.getId());
      }
      if (other.hasProtoId()) {
        setProtoId(other.getProtoId());
      }
      if (other.hasState()) {
        setState(other.getState());
      }
      if (other.hasProgress()) {
        setProgress(other.getProgress());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasId()) {
        return false;
      }
      if (!hasProtoId()) {
        return false;
      }
      if (!hasState()) {
        return false;
      }
      if (!hasProgress()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.AchievementInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.AchievementInfo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long id_ ;
    /**
     * <pre>
     * 成就唯一ID
     * </pre>
     *
     * <code>required int64 id = 1;</code>
     */
    public boolean hasId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 成就唯一ID
     * </pre>
     *
     * <code>required int64 id = 1;</code>
     */
    public long getId() {
      return id_;
    }
    /**
     * <pre>
     * 成就唯一ID
     * </pre>
     *
     * <code>required int64 id = 1;</code>
     */
    public Builder setId(long value) {
      bitField0_ |= 0x00000001;
      id_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 成就唯一ID
     * </pre>
     *
     * <code>required int64 id = 1;</code>
     */
    public Builder clearId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      id_ = 0L;
      onChanged();
      return this;
    }

    private int protoId_ ;
    /**
     * <pre>
     * 成就模板ID
     * </pre>
     *
     * <code>required int32 protoId = 2;</code>
     */
    public boolean hasProtoId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 成就模板ID
     * </pre>
     *
     * <code>required int32 protoId = 2;</code>
     */
    public int getProtoId() {
      return protoId_;
    }
    /**
     * <pre>
     * 成就模板ID
     * </pre>
     *
     * <code>required int32 protoId = 2;</code>
     */
    public Builder setProtoId(int value) {
      bitField0_ |= 0x00000002;
      protoId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 成就模板ID
     * </pre>
     *
     * <code>required int32 protoId = 2;</code>
     */
    public Builder clearProtoId() {
      bitField0_ = (bitField0_ & ~0x00000002);
      protoId_ = 0;
      onChanged();
      return this;
    }

    private int state_ ;
    /**
     * <pre>
     * 成就状态 0-进行中 1-已完成 2-已领取奖励
     * </pre>
     *
     * <code>required int32 state = 3;</code>
     */
    public boolean hasState() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     * 成就状态 0-进行中 1-已完成 2-已领取奖励
     * </pre>
     *
     * <code>required int32 state = 3;</code>
     */
    public int getState() {
      return state_;
    }
    /**
     * <pre>
     * 成就状态 0-进行中 1-已完成 2-已领取奖励
     * </pre>
     *
     * <code>required int32 state = 3;</code>
     */
    public Builder setState(int value) {
      bitField0_ |= 0x00000004;
      state_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 成就状态 0-进行中 1-已完成 2-已领取奖励
     * </pre>
     *
     * <code>required int32 state = 3;</code>
     */
    public Builder clearState() {
      bitField0_ = (bitField0_ & ~0x00000004);
      state_ = 0;
      onChanged();
      return this;
    }

    private int progress_ ;
    /**
     * <pre>
     * 成就当前进度
     * </pre>
     *
     * <code>required int32 progress = 4;</code>
     */
    public boolean hasProgress() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     * 成就当前进度
     * </pre>
     *
     * <code>required int32 progress = 4;</code>
     */
    public int getProgress() {
      return progress_;
    }
    /**
     * <pre>
     * 成就当前进度
     * </pre>
     *
     * <code>required int32 progress = 4;</code>
     */
    public Builder setProgress(int value) {
      bitField0_ |= 0x00000008;
      progress_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 成就当前进度
     * </pre>
     *
     * <code>required int32 progress = 4;</code>
     */
    public Builder clearProgress() {
      bitField0_ = (bitField0_ & ~0x00000008);
      progress_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.AchievementInfo)
  }

  // @@protoc_insertion_point(class_scope:client2server.AchievementInfo)
  private static final pb4client.AchievementInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.AchievementInfo();
  }

  public static pb4client.AchievementInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<AchievementInfo>
      PARSER = new com.google.protobuf.AbstractParser<AchievementInfo>() {
    public AchievementInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new AchievementInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AchievementInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AchievementInfo> getParserForType() {
    return PARSER;
  }

  public pb4client.AchievementInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
