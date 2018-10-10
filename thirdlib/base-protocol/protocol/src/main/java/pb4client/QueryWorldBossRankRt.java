// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.QueryWorldBossRankRt}
 */
public  final class QueryWorldBossRankRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.QueryWorldBossRankRt)
    QueryWorldBossRankRtOrBuilder {
  // Use QueryWorldBossRankRt.newBuilder() to construct.
  private QueryWorldBossRankRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private QueryWorldBossRankRt() {
    rt_ = 0;
    ranks_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private QueryWorldBossRankRt(
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
            rt_ = input.readInt32();
            break;
          }
          case 18: {
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              ranks_ = new java.util.ArrayList<pb4client.WorldBossRank>();
              mutable_bitField0_ |= 0x00000002;
            }
            ranks_.add(
                input.readMessage(pb4client.WorldBossRank.PARSER, extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
        ranks_ = java.util.Collections.unmodifiableList(ranks_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_QueryWorldBossRankRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_QueryWorldBossRankRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.QueryWorldBossRankRt.class, pb4client.QueryWorldBossRankRt.Builder.class);
  }

  private int bitField0_;
  public static final int RT_FIELD_NUMBER = 1;
  private int rt_;
  /**
   * <pre>
   * 返回值
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  public boolean hasRt() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 返回值
   * </pre>
   *
   * <code>required int32 rt = 1;</code>
   */
  public int getRt() {
    return rt_;
  }

  public static final int RANKS_FIELD_NUMBER = 2;
  private java.util.List<pb4client.WorldBossRank> ranks_;
  /**
   * <pre>
   * </pre>
   *
   * <code>repeated .client2server.WorldBossRank ranks = 2;</code>
   */
  public java.util.List<pb4client.WorldBossRank> getRanksList() {
    return ranks_;
  }
  /**
   * <pre>
   * </pre>
   *
   * <code>repeated .client2server.WorldBossRank ranks = 2;</code>
   */
  public java.util.List<? extends pb4client.WorldBossRankOrBuilder> 
      getRanksOrBuilderList() {
    return ranks_;
  }
  /**
   * <pre>
   * </pre>
   *
   * <code>repeated .client2server.WorldBossRank ranks = 2;</code>
   */
  public int getRanksCount() {
    return ranks_.size();
  }
  /**
   * <pre>
   * </pre>
   *
   * <code>repeated .client2server.WorldBossRank ranks = 2;</code>
   */
  public pb4client.WorldBossRank getRanks(int index) {
    return ranks_.get(index);
  }
  /**
   * <pre>
   * </pre>
   *
   * <code>repeated .client2server.WorldBossRank ranks = 2;</code>
   */
  public pb4client.WorldBossRankOrBuilder getRanksOrBuilder(
      int index) {
    return ranks_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasRt()) {
      memoizedIsInitialized = 0;
      return false;
    }
    for (int i = 0; i < getRanksCount(); i++) {
      if (!getRanks(i).isInitialized()) {
        memoizedIsInitialized = 0;
        return false;
      }
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, rt_);
    }
    for (int i = 0; i < ranks_.size(); i++) {
      output.writeMessage(2, ranks_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, rt_);
    }
    for (int i = 0; i < ranks_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, ranks_.get(i));
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
    if (!(obj instanceof pb4client.QueryWorldBossRankRt)) {
      return super.equals(obj);
    }
    pb4client.QueryWorldBossRankRt other = (pb4client.QueryWorldBossRankRt) obj;

    boolean result = true;
    result = result && (hasRt() == other.hasRt());
    if (hasRt()) {
      result = result && (getRt()
          == other.getRt());
    }
    result = result && getRanksList()
        .equals(other.getRanksList());
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
    if (hasRt()) {
      hash = (37 * hash) + RT_FIELD_NUMBER;
      hash = (53 * hash) + getRt();
    }
    if (getRanksCount() > 0) {
      hash = (37 * hash) + RANKS_FIELD_NUMBER;
      hash = (53 * hash) + getRanksList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.QueryWorldBossRankRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.QueryWorldBossRankRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.QueryWorldBossRankRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.QueryWorldBossRankRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.QueryWorldBossRankRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.QueryWorldBossRankRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.QueryWorldBossRankRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.QueryWorldBossRankRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.QueryWorldBossRankRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.QueryWorldBossRankRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.QueryWorldBossRankRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.QueryWorldBossRankRt parseFrom(
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
  public static Builder newBuilder(pb4client.QueryWorldBossRankRt prototype) {
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
   * Protobuf type {@code client2server.QueryWorldBossRankRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.QueryWorldBossRankRt)
      pb4client.QueryWorldBossRankRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_QueryWorldBossRankRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_QueryWorldBossRankRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.QueryWorldBossRankRt.class, pb4client.QueryWorldBossRankRt.Builder.class);
    }

    // Construct using pb4client.QueryWorldBossRankRt.newBuilder()
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
        getRanksFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      rt_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      if (ranksBuilder_ == null) {
        ranks_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        ranksBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_QueryWorldBossRankRt_descriptor;
    }

    public pb4client.QueryWorldBossRankRt getDefaultInstanceForType() {
      return pb4client.QueryWorldBossRankRt.getDefaultInstance();
    }

    public pb4client.QueryWorldBossRankRt build() {
      pb4client.QueryWorldBossRankRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.QueryWorldBossRankRt buildPartial() {
      pb4client.QueryWorldBossRankRt result = new pb4client.QueryWorldBossRankRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.rt_ = rt_;
      if (ranksBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          ranks_ = java.util.Collections.unmodifiableList(ranks_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.ranks_ = ranks_;
      } else {
        result.ranks_ = ranksBuilder_.build();
      }
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
      if (other instanceof pb4client.QueryWorldBossRankRt) {
        return mergeFrom((pb4client.QueryWorldBossRankRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.QueryWorldBossRankRt other) {
      if (other == pb4client.QueryWorldBossRankRt.getDefaultInstance()) return this;
      if (other.hasRt()) {
        setRt(other.getRt());
      }
      if (ranksBuilder_ == null) {
        if (!other.ranks_.isEmpty()) {
          if (ranks_.isEmpty()) {
            ranks_ = other.ranks_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureRanksIsMutable();
            ranks_.addAll(other.ranks_);
          }
          onChanged();
        }
      } else {
        if (!other.ranks_.isEmpty()) {
          if (ranksBuilder_.isEmpty()) {
            ranksBuilder_.dispose();
            ranksBuilder_ = null;
            ranks_ = other.ranks_;
            bitField0_ = (bitField0_ & ~0x00000002);
            ranksBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getRanksFieldBuilder() : null;
          } else {
            ranksBuilder_.addAllMessages(other.ranks_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasRt()) {
        return false;
      }
      for (int i = 0; i < getRanksCount(); i++) {
        if (!getRanks(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.QueryWorldBossRankRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.QueryWorldBossRankRt) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int rt_ ;
    /**
     * <pre>
     * 返回值
     * </pre>
     *
     * <code>required int32 rt = 1;</code>
     */
    public boolean hasRt() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 返回值
     * </pre>
     *
     * <code>required int32 rt = 1;</code>
     */
    public int getRt() {
      return rt_;
    }
    /**
     * <pre>
     * 返回值
     * </pre>
     *
     * <code>required int32 rt = 1;</code>
     */
    public Builder setRt(int value) {
      bitField0_ |= 0x00000001;
      rt_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 返回值
     * </pre>
     *
     * <code>required int32 rt = 1;</code>
     */
    public Builder clearRt() {
      bitField0_ = (bitField0_ & ~0x00000001);
      rt_ = 0;
      onChanged();
      return this;
    }

    private java.util.List<pb4client.WorldBossRank> ranks_ =
      java.util.Collections.emptyList();
    private void ensureRanksIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        ranks_ = new java.util.ArrayList<pb4client.WorldBossRank>(ranks_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.WorldBossRank, pb4client.WorldBossRank.Builder, pb4client.WorldBossRankOrBuilder> ranksBuilder_;

    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.WorldBossRank ranks = 2;</code>
     */
    public java.util.List<pb4client.WorldBossRank> getRanksList() {
      if (ranksBuilder_ == null) {
        return java.util.Collections.unmodifiableList(ranks_);
      } else {
        return ranksBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.WorldBossRank ranks = 2;</code>
     */
    public int getRanksCount() {
      if (ranksBuilder_ == null) {
        return ranks_.size();
      } else {
        return ranksBuilder_.getCount();
      }
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.WorldBossRank ranks = 2;</code>
     */
    public pb4client.WorldBossRank getRanks(int index) {
      if (ranksBuilder_ == null) {
        return ranks_.get(index);
      } else {
        return ranksBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.WorldBossRank ranks = 2;</code>
     */
    public Builder setRanks(
        int index, pb4client.WorldBossRank value) {
      if (ranksBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureRanksIsMutable();
        ranks_.set(index, value);
        onChanged();
      } else {
        ranksBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.WorldBossRank ranks = 2;</code>
     */
    public Builder setRanks(
        int index, pb4client.WorldBossRank.Builder builderForValue) {
      if (ranksBuilder_ == null) {
        ensureRanksIsMutable();
        ranks_.set(index, builderForValue.build());
        onChanged();
      } else {
        ranksBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.WorldBossRank ranks = 2;</code>
     */
    public Builder addRanks(pb4client.WorldBossRank value) {
      if (ranksBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureRanksIsMutable();
        ranks_.add(value);
        onChanged();
      } else {
        ranksBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.WorldBossRank ranks = 2;</code>
     */
    public Builder addRanks(
        int index, pb4client.WorldBossRank value) {
      if (ranksBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureRanksIsMutable();
        ranks_.add(index, value);
        onChanged();
      } else {
        ranksBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.WorldBossRank ranks = 2;</code>
     */
    public Builder addRanks(
        pb4client.WorldBossRank.Builder builderForValue) {
      if (ranksBuilder_ == null) {
        ensureRanksIsMutable();
        ranks_.add(builderForValue.build());
        onChanged();
      } else {
        ranksBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.WorldBossRank ranks = 2;</code>
     */
    public Builder addRanks(
        int index, pb4client.WorldBossRank.Builder builderForValue) {
      if (ranksBuilder_ == null) {
        ensureRanksIsMutable();
        ranks_.add(index, builderForValue.build());
        onChanged();
      } else {
        ranksBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.WorldBossRank ranks = 2;</code>
     */
    public Builder addAllRanks(
        java.lang.Iterable<? extends pb4client.WorldBossRank> values) {
      if (ranksBuilder_ == null) {
        ensureRanksIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, ranks_);
        onChanged();
      } else {
        ranksBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.WorldBossRank ranks = 2;</code>
     */
    public Builder clearRanks() {
      if (ranksBuilder_ == null) {
        ranks_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        ranksBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.WorldBossRank ranks = 2;</code>
     */
    public Builder removeRanks(int index) {
      if (ranksBuilder_ == null) {
        ensureRanksIsMutable();
        ranks_.remove(index);
        onChanged();
      } else {
        ranksBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.WorldBossRank ranks = 2;</code>
     */
    public pb4client.WorldBossRank.Builder getRanksBuilder(
        int index) {
      return getRanksFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.WorldBossRank ranks = 2;</code>
     */
    public pb4client.WorldBossRankOrBuilder getRanksOrBuilder(
        int index) {
      if (ranksBuilder_ == null) {
        return ranks_.get(index);  } else {
        return ranksBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.WorldBossRank ranks = 2;</code>
     */
    public java.util.List<? extends pb4client.WorldBossRankOrBuilder> 
         getRanksOrBuilderList() {
      if (ranksBuilder_ != null) {
        return ranksBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(ranks_);
      }
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.WorldBossRank ranks = 2;</code>
     */
    public pb4client.WorldBossRank.Builder addRanksBuilder() {
      return getRanksFieldBuilder().addBuilder(
          pb4client.WorldBossRank.getDefaultInstance());
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.WorldBossRank ranks = 2;</code>
     */
    public pb4client.WorldBossRank.Builder addRanksBuilder(
        int index) {
      return getRanksFieldBuilder().addBuilder(
          index, pb4client.WorldBossRank.getDefaultInstance());
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.WorldBossRank ranks = 2;</code>
     */
    public java.util.List<pb4client.WorldBossRank.Builder> 
         getRanksBuilderList() {
      return getRanksFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.WorldBossRank, pb4client.WorldBossRank.Builder, pb4client.WorldBossRankOrBuilder> 
        getRanksFieldBuilder() {
      if (ranksBuilder_ == null) {
        ranksBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.WorldBossRank, pb4client.WorldBossRank.Builder, pb4client.WorldBossRankOrBuilder>(
                ranks_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        ranks_ = null;
      }
      return ranksBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.QueryWorldBossRankRt)
  }

  // @@protoc_insertion_point(class_scope:client2server.QueryWorldBossRankRt)
  private static final pb4client.QueryWorldBossRankRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.QueryWorldBossRankRt();
  }

  public static pb4client.QueryWorldBossRankRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<QueryWorldBossRankRt>
      PARSER = new com.google.protobuf.AbstractParser<QueryWorldBossRankRt>() {
    public QueryWorldBossRankRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new QueryWorldBossRankRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<QueryWorldBossRankRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<QueryWorldBossRankRt> getParserForType() {
    return PARSER;
  }

  public pb4client.QueryWorldBossRankRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
