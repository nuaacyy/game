// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.GetAllianceCompetitionRewardVo}
 */
public  final class GetAllianceCompetitionRewardVo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.GetAllianceCompetitionRewardVo)
    GetAllianceCompetitionRewardVoOrBuilder {
  // Use GetAllianceCompetitionRewardVo.newBuilder() to construct.
  private GetAllianceCompetitionRewardVo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GetAllianceCompetitionRewardVo() {
    score_ = 0;
    index_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GetAllianceCompetitionRewardVo(
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
            score_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            index_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_GetAllianceCompetitionRewardVo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_GetAllianceCompetitionRewardVo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.GetAllianceCompetitionRewardVo.class, pb4client.GetAllianceCompetitionRewardVo.Builder.class);
  }

  private int bitField0_;
  public static final int SCORE_FIELD_NUMBER = 1;
  private int score_;
  /**
   * <pre>
   * 奖励积分档
   * </pre>
   *
   * <code>required int32 score = 1;</code>
   */
  public boolean hasScore() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 奖励积分档
   * </pre>
   *
   * <code>required int32 score = 1;</code>
   */
  public int getScore() {
    return score_;
  }

  public static final int INDEX_FIELD_NUMBER = 2;
  private int index_;
  /**
   * <pre>
   * 奖励位置
   * </pre>
   *
   * <code>required int32 index = 2;</code>
   */
  public boolean hasIndex() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 奖励位置
   * </pre>
   *
   * <code>required int32 index = 2;</code>
   */
  public int getIndex() {
    return index_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasScore()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasIndex()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, score_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, index_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, score_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, index_);
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
    if (!(obj instanceof pb4client.GetAllianceCompetitionRewardVo)) {
      return super.equals(obj);
    }
    pb4client.GetAllianceCompetitionRewardVo other = (pb4client.GetAllianceCompetitionRewardVo) obj;

    boolean result = true;
    result = result && (hasScore() == other.hasScore());
    if (hasScore()) {
      result = result && (getScore()
          == other.getScore());
    }
    result = result && (hasIndex() == other.hasIndex());
    if (hasIndex()) {
      result = result && (getIndex()
          == other.getIndex());
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
    if (hasScore()) {
      hash = (37 * hash) + SCORE_FIELD_NUMBER;
      hash = (53 * hash) + getScore();
    }
    if (hasIndex()) {
      hash = (37 * hash) + INDEX_FIELD_NUMBER;
      hash = (53 * hash) + getIndex();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.GetAllianceCompetitionRewardVo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.GetAllianceCompetitionRewardVo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.GetAllianceCompetitionRewardVo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.GetAllianceCompetitionRewardVo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.GetAllianceCompetitionRewardVo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.GetAllianceCompetitionRewardVo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.GetAllianceCompetitionRewardVo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.GetAllianceCompetitionRewardVo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.GetAllianceCompetitionRewardVo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.GetAllianceCompetitionRewardVo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.GetAllianceCompetitionRewardVo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.GetAllianceCompetitionRewardVo parseFrom(
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
  public static Builder newBuilder(pb4client.GetAllianceCompetitionRewardVo prototype) {
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
   * Protobuf type {@code client2server.GetAllianceCompetitionRewardVo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.GetAllianceCompetitionRewardVo)
      pb4client.GetAllianceCompetitionRewardVoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_GetAllianceCompetitionRewardVo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_GetAllianceCompetitionRewardVo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.GetAllianceCompetitionRewardVo.class, pb4client.GetAllianceCompetitionRewardVo.Builder.class);
    }

    // Construct using pb4client.GetAllianceCompetitionRewardVo.newBuilder()
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
      score_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      index_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_GetAllianceCompetitionRewardVo_descriptor;
    }

    public pb4client.GetAllianceCompetitionRewardVo getDefaultInstanceForType() {
      return pb4client.GetAllianceCompetitionRewardVo.getDefaultInstance();
    }

    public pb4client.GetAllianceCompetitionRewardVo build() {
      pb4client.GetAllianceCompetitionRewardVo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.GetAllianceCompetitionRewardVo buildPartial() {
      pb4client.GetAllianceCompetitionRewardVo result = new pb4client.GetAllianceCompetitionRewardVo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.score_ = score_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.index_ = index_;
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
      if (other instanceof pb4client.GetAllianceCompetitionRewardVo) {
        return mergeFrom((pb4client.GetAllianceCompetitionRewardVo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.GetAllianceCompetitionRewardVo other) {
      if (other == pb4client.GetAllianceCompetitionRewardVo.getDefaultInstance()) return this;
      if (other.hasScore()) {
        setScore(other.getScore());
      }
      if (other.hasIndex()) {
        setIndex(other.getIndex());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasScore()) {
        return false;
      }
      if (!hasIndex()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.GetAllianceCompetitionRewardVo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.GetAllianceCompetitionRewardVo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int score_ ;
    /**
     * <pre>
     * 奖励积分档
     * </pre>
     *
     * <code>required int32 score = 1;</code>
     */
    public boolean hasScore() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 奖励积分档
     * </pre>
     *
     * <code>required int32 score = 1;</code>
     */
    public int getScore() {
      return score_;
    }
    /**
     * <pre>
     * 奖励积分档
     * </pre>
     *
     * <code>required int32 score = 1;</code>
     */
    public Builder setScore(int value) {
      bitField0_ |= 0x00000001;
      score_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 奖励积分档
     * </pre>
     *
     * <code>required int32 score = 1;</code>
     */
    public Builder clearScore() {
      bitField0_ = (bitField0_ & ~0x00000001);
      score_ = 0;
      onChanged();
      return this;
    }

    private int index_ ;
    /**
     * <pre>
     * 奖励位置
     * </pre>
     *
     * <code>required int32 index = 2;</code>
     */
    public boolean hasIndex() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 奖励位置
     * </pre>
     *
     * <code>required int32 index = 2;</code>
     */
    public int getIndex() {
      return index_;
    }
    /**
     * <pre>
     * 奖励位置
     * </pre>
     *
     * <code>required int32 index = 2;</code>
     */
    public Builder setIndex(int value) {
      bitField0_ |= 0x00000002;
      index_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 奖励位置
     * </pre>
     *
     * <code>required int32 index = 2;</code>
     */
    public Builder clearIndex() {
      bitField0_ = (bitField0_ & ~0x00000002);
      index_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.GetAllianceCompetitionRewardVo)
  }

  // @@protoc_insertion_point(class_scope:client2server.GetAllianceCompetitionRewardVo)
  private static final pb4client.GetAllianceCompetitionRewardVo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.GetAllianceCompetitionRewardVo();
  }

  public static pb4client.GetAllianceCompetitionRewardVo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<GetAllianceCompetitionRewardVo>
      PARSER = new com.google.protobuf.AbstractParser<GetAllianceCompetitionRewardVo>() {
    public GetAllianceCompetitionRewardVo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new GetAllianceCompetitionRewardVo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GetAllianceCompetitionRewardVo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GetAllianceCompetitionRewardVo> getParserForType() {
    return PARSER;
  }

  public pb4client.GetAllianceCompetitionRewardVo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
