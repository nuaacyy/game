// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * // msgType = 1335
 * 客户端 -&gt; 服务器
 * 查询我参与过的活动的历史排行榜
 * </pre>
 *
 * Protobuf type {@code client2server.SelectActivityRank}
 */
public  final class SelectActivityRank extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.SelectActivityRank)
    SelectActivityRankOrBuilder {
  // Use SelectActivityRank.newBuilder() to construct.
  private SelectActivityRank(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SelectActivityRank() {
    activityType_ = 0;
    rankId_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private SelectActivityRank(
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
            activityType_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            rankId_ = input.readInt64();
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
    return pb4client.War2GamePkt.internal_static_client2server_SelectActivityRank_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_SelectActivityRank_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.SelectActivityRank.class, pb4client.SelectActivityRank.Builder.class);
  }

  private int bitField0_;
  public static final int ACTIVITYTYPE_FIELD_NUMBER = 1;
  private int activityType_;
  /**
   * <pre>
   * 同策划配置的活动类型   1-个人 2-地狱 3-联盟
   * </pre>
   *
   * <code>required int32 activityType = 1;</code>
   */
  public boolean hasActivityType() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 同策划配置的活动类型   1-个人 2-地狱 3-联盟
   * </pre>
   *
   * <code>required int32 activityType = 1;</code>
   */
  public int getActivityType() {
    return activityType_;
  }

  public static final int RANKID_FIELD_NUMBER = 2;
  private long rankId_;
  /**
   * <pre>
   * 对应的排行榜记录行ID
   * </pre>
   *
   * <code>required int64 rankId = 2;</code>
   */
  public boolean hasRankId() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 对应的排行榜记录行ID
   * </pre>
   *
   * <code>required int64 rankId = 2;</code>
   */
  public long getRankId() {
    return rankId_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasActivityType()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasRankId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, activityType_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt64(2, rankId_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, activityType_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, rankId_);
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
    if (!(obj instanceof pb4client.SelectActivityRank)) {
      return super.equals(obj);
    }
    pb4client.SelectActivityRank other = (pb4client.SelectActivityRank) obj;

    boolean result = true;
    result = result && (hasActivityType() == other.hasActivityType());
    if (hasActivityType()) {
      result = result && (getActivityType()
          == other.getActivityType());
    }
    result = result && (hasRankId() == other.hasRankId());
    if (hasRankId()) {
      result = result && (getRankId()
          == other.getRankId());
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
    if (hasActivityType()) {
      hash = (37 * hash) + ACTIVITYTYPE_FIELD_NUMBER;
      hash = (53 * hash) + getActivityType();
    }
    if (hasRankId()) {
      hash = (37 * hash) + RANKID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getRankId());
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.SelectActivityRank parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.SelectActivityRank parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.SelectActivityRank parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.SelectActivityRank parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.SelectActivityRank parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.SelectActivityRank parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.SelectActivityRank parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.SelectActivityRank parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.SelectActivityRank parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.SelectActivityRank parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.SelectActivityRank parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.SelectActivityRank parseFrom(
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
  public static Builder newBuilder(pb4client.SelectActivityRank prototype) {
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
   * // msgType = 1335
   * 客户端 -&gt; 服务器
   * 查询我参与过的活动的历史排行榜
   * </pre>
   *
   * Protobuf type {@code client2server.SelectActivityRank}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.SelectActivityRank)
      pb4client.SelectActivityRankOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_SelectActivityRank_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_SelectActivityRank_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.SelectActivityRank.class, pb4client.SelectActivityRank.Builder.class);
    }

    // Construct using pb4client.SelectActivityRank.newBuilder()
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
      activityType_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      rankId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_SelectActivityRank_descriptor;
    }

    public pb4client.SelectActivityRank getDefaultInstanceForType() {
      return pb4client.SelectActivityRank.getDefaultInstance();
    }

    public pb4client.SelectActivityRank build() {
      pb4client.SelectActivityRank result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.SelectActivityRank buildPartial() {
      pb4client.SelectActivityRank result = new pb4client.SelectActivityRank(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.activityType_ = activityType_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.rankId_ = rankId_;
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
      if (other instanceof pb4client.SelectActivityRank) {
        return mergeFrom((pb4client.SelectActivityRank)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.SelectActivityRank other) {
      if (other == pb4client.SelectActivityRank.getDefaultInstance()) return this;
      if (other.hasActivityType()) {
        setActivityType(other.getActivityType());
      }
      if (other.hasRankId()) {
        setRankId(other.getRankId());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasActivityType()) {
        return false;
      }
      if (!hasRankId()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.SelectActivityRank parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.SelectActivityRank) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int activityType_ ;
    /**
     * <pre>
     * 同策划配置的活动类型   1-个人 2-地狱 3-联盟
     * </pre>
     *
     * <code>required int32 activityType = 1;</code>
     */
    public boolean hasActivityType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 同策划配置的活动类型   1-个人 2-地狱 3-联盟
     * </pre>
     *
     * <code>required int32 activityType = 1;</code>
     */
    public int getActivityType() {
      return activityType_;
    }
    /**
     * <pre>
     * 同策划配置的活动类型   1-个人 2-地狱 3-联盟
     * </pre>
     *
     * <code>required int32 activityType = 1;</code>
     */
    public Builder setActivityType(int value) {
      bitField0_ |= 0x00000001;
      activityType_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 同策划配置的活动类型   1-个人 2-地狱 3-联盟
     * </pre>
     *
     * <code>required int32 activityType = 1;</code>
     */
    public Builder clearActivityType() {
      bitField0_ = (bitField0_ & ~0x00000001);
      activityType_ = 0;
      onChanged();
      return this;
    }

    private long rankId_ ;
    /**
     * <pre>
     * 对应的排行榜记录行ID
     * </pre>
     *
     * <code>required int64 rankId = 2;</code>
     */
    public boolean hasRankId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 对应的排行榜记录行ID
     * </pre>
     *
     * <code>required int64 rankId = 2;</code>
     */
    public long getRankId() {
      return rankId_;
    }
    /**
     * <pre>
     * 对应的排行榜记录行ID
     * </pre>
     *
     * <code>required int64 rankId = 2;</code>
     */
    public Builder setRankId(long value) {
      bitField0_ |= 0x00000002;
      rankId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 对应的排行榜记录行ID
     * </pre>
     *
     * <code>required int64 rankId = 2;</code>
     */
    public Builder clearRankId() {
      bitField0_ = (bitField0_ & ~0x00000002);
      rankId_ = 0L;
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


    // @@protoc_insertion_point(builder_scope:client2server.SelectActivityRank)
  }

  // @@protoc_insertion_point(class_scope:client2server.SelectActivityRank)
  private static final pb4client.SelectActivityRank DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.SelectActivityRank();
  }

  public static pb4client.SelectActivityRank getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<SelectActivityRank>
      PARSER = new com.google.protobuf.AbstractParser<SelectActivityRank>() {
    public SelectActivityRank parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new SelectActivityRank(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SelectActivityRank> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SelectActivityRank> getParserForType() {
    return PARSER;
  }

  public pb4client.SelectActivityRank getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

