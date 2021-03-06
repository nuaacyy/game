// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.ConscriptionRt}
 */
public  final class ConscriptionRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.ConscriptionRt)
    ConscriptionRtOrBuilder {
  // Use ConscriptionRt.newBuilder() to construct.
  private ConscriptionRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ConscriptionRt() {
    rt_ = 0;
    heroEndTime_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ConscriptionRt(
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
              heroEndTime_ = new java.util.ArrayList<pb4client.HeroEndTime>();
              mutable_bitField0_ |= 0x00000002;
            }
            heroEndTime_.add(
                input.readMessage(pb4client.HeroEndTime.PARSER, extensionRegistry));
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
        heroEndTime_ = java.util.Collections.unmodifiableList(heroEndTime_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_ConscriptionRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_ConscriptionRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.ConscriptionRt.class, pb4client.ConscriptionRt.Builder.class);
  }

  private int bitField0_;
  public static final int RT_FIELD_NUMBER = 1;
  private int rt_;
  /**
   * <code>required int32 rt = 1;</code>
   */
  public boolean hasRt() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>required int32 rt = 1;</code>
   */
  public int getRt() {
    return rt_;
  }

  public static final int HEROENDTIME_FIELD_NUMBER = 2;
  private java.util.List<pb4client.HeroEndTime> heroEndTime_;
  /**
   * <pre>
   *征兵信息
   * </pre>
   *
   * <code>repeated .client2server.HeroEndTime heroEndTime = 2;</code>
   */
  public java.util.List<pb4client.HeroEndTime> getHeroEndTimeList() {
    return heroEndTime_;
  }
  /**
   * <pre>
   *征兵信息
   * </pre>
   *
   * <code>repeated .client2server.HeroEndTime heroEndTime = 2;</code>
   */
  public java.util.List<? extends pb4client.HeroEndTimeOrBuilder> 
      getHeroEndTimeOrBuilderList() {
    return heroEndTime_;
  }
  /**
   * <pre>
   *征兵信息
   * </pre>
   *
   * <code>repeated .client2server.HeroEndTime heroEndTime = 2;</code>
   */
  public int getHeroEndTimeCount() {
    return heroEndTime_.size();
  }
  /**
   * <pre>
   *征兵信息
   * </pre>
   *
   * <code>repeated .client2server.HeroEndTime heroEndTime = 2;</code>
   */
  public pb4client.HeroEndTime getHeroEndTime(int index) {
    return heroEndTime_.get(index);
  }
  /**
   * <pre>
   *征兵信息
   * </pre>
   *
   * <code>repeated .client2server.HeroEndTime heroEndTime = 2;</code>
   */
  public pb4client.HeroEndTimeOrBuilder getHeroEndTimeOrBuilder(
      int index) {
    return heroEndTime_.get(index);
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
    for (int i = 0; i < getHeroEndTimeCount(); i++) {
      if (!getHeroEndTime(i).isInitialized()) {
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
    for (int i = 0; i < heroEndTime_.size(); i++) {
      output.writeMessage(2, heroEndTime_.get(i));
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
    for (int i = 0; i < heroEndTime_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, heroEndTime_.get(i));
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
    if (!(obj instanceof pb4client.ConscriptionRt)) {
      return super.equals(obj);
    }
    pb4client.ConscriptionRt other = (pb4client.ConscriptionRt) obj;

    boolean result = true;
    result = result && (hasRt() == other.hasRt());
    if (hasRt()) {
      result = result && (getRt()
          == other.getRt());
    }
    result = result && getHeroEndTimeList()
        .equals(other.getHeroEndTimeList());
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
    if (getHeroEndTimeCount() > 0) {
      hash = (37 * hash) + HEROENDTIME_FIELD_NUMBER;
      hash = (53 * hash) + getHeroEndTimeList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.ConscriptionRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.ConscriptionRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.ConscriptionRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.ConscriptionRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.ConscriptionRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.ConscriptionRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.ConscriptionRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.ConscriptionRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.ConscriptionRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.ConscriptionRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.ConscriptionRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.ConscriptionRt parseFrom(
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
  public static Builder newBuilder(pb4client.ConscriptionRt prototype) {
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
   * Protobuf type {@code client2server.ConscriptionRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.ConscriptionRt)
      pb4client.ConscriptionRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_ConscriptionRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_ConscriptionRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.ConscriptionRt.class, pb4client.ConscriptionRt.Builder.class);
    }

    // Construct using pb4client.ConscriptionRt.newBuilder()
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
        getHeroEndTimeFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      rt_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      if (heroEndTimeBuilder_ == null) {
        heroEndTime_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        heroEndTimeBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_ConscriptionRt_descriptor;
    }

    public pb4client.ConscriptionRt getDefaultInstanceForType() {
      return pb4client.ConscriptionRt.getDefaultInstance();
    }

    public pb4client.ConscriptionRt build() {
      pb4client.ConscriptionRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.ConscriptionRt buildPartial() {
      pb4client.ConscriptionRt result = new pb4client.ConscriptionRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.rt_ = rt_;
      if (heroEndTimeBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          heroEndTime_ = java.util.Collections.unmodifiableList(heroEndTime_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.heroEndTime_ = heroEndTime_;
      } else {
        result.heroEndTime_ = heroEndTimeBuilder_.build();
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
      if (other instanceof pb4client.ConscriptionRt) {
        return mergeFrom((pb4client.ConscriptionRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.ConscriptionRt other) {
      if (other == pb4client.ConscriptionRt.getDefaultInstance()) return this;
      if (other.hasRt()) {
        setRt(other.getRt());
      }
      if (heroEndTimeBuilder_ == null) {
        if (!other.heroEndTime_.isEmpty()) {
          if (heroEndTime_.isEmpty()) {
            heroEndTime_ = other.heroEndTime_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureHeroEndTimeIsMutable();
            heroEndTime_.addAll(other.heroEndTime_);
          }
          onChanged();
        }
      } else {
        if (!other.heroEndTime_.isEmpty()) {
          if (heroEndTimeBuilder_.isEmpty()) {
            heroEndTimeBuilder_.dispose();
            heroEndTimeBuilder_ = null;
            heroEndTime_ = other.heroEndTime_;
            bitField0_ = (bitField0_ & ~0x00000002);
            heroEndTimeBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getHeroEndTimeFieldBuilder() : null;
          } else {
            heroEndTimeBuilder_.addAllMessages(other.heroEndTime_);
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
      for (int i = 0; i < getHeroEndTimeCount(); i++) {
        if (!getHeroEndTime(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.ConscriptionRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.ConscriptionRt) e.getUnfinishedMessage();
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
     * <code>required int32 rt = 1;</code>
     */
    public boolean hasRt() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 rt = 1;</code>
     */
    public int getRt() {
      return rt_;
    }
    /**
     * <code>required int32 rt = 1;</code>
     */
    public Builder setRt(int value) {
      bitField0_ |= 0x00000001;
      rt_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 rt = 1;</code>
     */
    public Builder clearRt() {
      bitField0_ = (bitField0_ & ~0x00000001);
      rt_ = 0;
      onChanged();
      return this;
    }

    private java.util.List<pb4client.HeroEndTime> heroEndTime_ =
      java.util.Collections.emptyList();
    private void ensureHeroEndTimeIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        heroEndTime_ = new java.util.ArrayList<pb4client.HeroEndTime>(heroEndTime_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.HeroEndTime, pb4client.HeroEndTime.Builder, pb4client.HeroEndTimeOrBuilder> heroEndTimeBuilder_;

    /**
     * <pre>
     *征兵信息
     * </pre>
     *
     * <code>repeated .client2server.HeroEndTime heroEndTime = 2;</code>
     */
    public java.util.List<pb4client.HeroEndTime> getHeroEndTimeList() {
      if (heroEndTimeBuilder_ == null) {
        return java.util.Collections.unmodifiableList(heroEndTime_);
      } else {
        return heroEndTimeBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     *征兵信息
     * </pre>
     *
     * <code>repeated .client2server.HeroEndTime heroEndTime = 2;</code>
     */
    public int getHeroEndTimeCount() {
      if (heroEndTimeBuilder_ == null) {
        return heroEndTime_.size();
      } else {
        return heroEndTimeBuilder_.getCount();
      }
    }
    /**
     * <pre>
     *征兵信息
     * </pre>
     *
     * <code>repeated .client2server.HeroEndTime heroEndTime = 2;</code>
     */
    public pb4client.HeroEndTime getHeroEndTime(int index) {
      if (heroEndTimeBuilder_ == null) {
        return heroEndTime_.get(index);
      } else {
        return heroEndTimeBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     *征兵信息
     * </pre>
     *
     * <code>repeated .client2server.HeroEndTime heroEndTime = 2;</code>
     */
    public Builder setHeroEndTime(
        int index, pb4client.HeroEndTime value) {
      if (heroEndTimeBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureHeroEndTimeIsMutable();
        heroEndTime_.set(index, value);
        onChanged();
      } else {
        heroEndTimeBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     *征兵信息
     * </pre>
     *
     * <code>repeated .client2server.HeroEndTime heroEndTime = 2;</code>
     */
    public Builder setHeroEndTime(
        int index, pb4client.HeroEndTime.Builder builderForValue) {
      if (heroEndTimeBuilder_ == null) {
        ensureHeroEndTimeIsMutable();
        heroEndTime_.set(index, builderForValue.build());
        onChanged();
      } else {
        heroEndTimeBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *征兵信息
     * </pre>
     *
     * <code>repeated .client2server.HeroEndTime heroEndTime = 2;</code>
     */
    public Builder addHeroEndTime(pb4client.HeroEndTime value) {
      if (heroEndTimeBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureHeroEndTimeIsMutable();
        heroEndTime_.add(value);
        onChanged();
      } else {
        heroEndTimeBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     *征兵信息
     * </pre>
     *
     * <code>repeated .client2server.HeroEndTime heroEndTime = 2;</code>
     */
    public Builder addHeroEndTime(
        int index, pb4client.HeroEndTime value) {
      if (heroEndTimeBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureHeroEndTimeIsMutable();
        heroEndTime_.add(index, value);
        onChanged();
      } else {
        heroEndTimeBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     *征兵信息
     * </pre>
     *
     * <code>repeated .client2server.HeroEndTime heroEndTime = 2;</code>
     */
    public Builder addHeroEndTime(
        pb4client.HeroEndTime.Builder builderForValue) {
      if (heroEndTimeBuilder_ == null) {
        ensureHeroEndTimeIsMutable();
        heroEndTime_.add(builderForValue.build());
        onChanged();
      } else {
        heroEndTimeBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *征兵信息
     * </pre>
     *
     * <code>repeated .client2server.HeroEndTime heroEndTime = 2;</code>
     */
    public Builder addHeroEndTime(
        int index, pb4client.HeroEndTime.Builder builderForValue) {
      if (heroEndTimeBuilder_ == null) {
        ensureHeroEndTimeIsMutable();
        heroEndTime_.add(index, builderForValue.build());
        onChanged();
      } else {
        heroEndTimeBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *征兵信息
     * </pre>
     *
     * <code>repeated .client2server.HeroEndTime heroEndTime = 2;</code>
     */
    public Builder addAllHeroEndTime(
        java.lang.Iterable<? extends pb4client.HeroEndTime> values) {
      if (heroEndTimeBuilder_ == null) {
        ensureHeroEndTimeIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, heroEndTime_);
        onChanged();
      } else {
        heroEndTimeBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     *征兵信息
     * </pre>
     *
     * <code>repeated .client2server.HeroEndTime heroEndTime = 2;</code>
     */
    public Builder clearHeroEndTime() {
      if (heroEndTimeBuilder_ == null) {
        heroEndTime_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        heroEndTimeBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     *征兵信息
     * </pre>
     *
     * <code>repeated .client2server.HeroEndTime heroEndTime = 2;</code>
     */
    public Builder removeHeroEndTime(int index) {
      if (heroEndTimeBuilder_ == null) {
        ensureHeroEndTimeIsMutable();
        heroEndTime_.remove(index);
        onChanged();
      } else {
        heroEndTimeBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     *征兵信息
     * </pre>
     *
     * <code>repeated .client2server.HeroEndTime heroEndTime = 2;</code>
     */
    public pb4client.HeroEndTime.Builder getHeroEndTimeBuilder(
        int index) {
      return getHeroEndTimeFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     *征兵信息
     * </pre>
     *
     * <code>repeated .client2server.HeroEndTime heroEndTime = 2;</code>
     */
    public pb4client.HeroEndTimeOrBuilder getHeroEndTimeOrBuilder(
        int index) {
      if (heroEndTimeBuilder_ == null) {
        return heroEndTime_.get(index);  } else {
        return heroEndTimeBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     *征兵信息
     * </pre>
     *
     * <code>repeated .client2server.HeroEndTime heroEndTime = 2;</code>
     */
    public java.util.List<? extends pb4client.HeroEndTimeOrBuilder> 
         getHeroEndTimeOrBuilderList() {
      if (heroEndTimeBuilder_ != null) {
        return heroEndTimeBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(heroEndTime_);
      }
    }
    /**
     * <pre>
     *征兵信息
     * </pre>
     *
     * <code>repeated .client2server.HeroEndTime heroEndTime = 2;</code>
     */
    public pb4client.HeroEndTime.Builder addHeroEndTimeBuilder() {
      return getHeroEndTimeFieldBuilder().addBuilder(
          pb4client.HeroEndTime.getDefaultInstance());
    }
    /**
     * <pre>
     *征兵信息
     * </pre>
     *
     * <code>repeated .client2server.HeroEndTime heroEndTime = 2;</code>
     */
    public pb4client.HeroEndTime.Builder addHeroEndTimeBuilder(
        int index) {
      return getHeroEndTimeFieldBuilder().addBuilder(
          index, pb4client.HeroEndTime.getDefaultInstance());
    }
    /**
     * <pre>
     *征兵信息
     * </pre>
     *
     * <code>repeated .client2server.HeroEndTime heroEndTime = 2;</code>
     */
    public java.util.List<pb4client.HeroEndTime.Builder> 
         getHeroEndTimeBuilderList() {
      return getHeroEndTimeFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.HeroEndTime, pb4client.HeroEndTime.Builder, pb4client.HeroEndTimeOrBuilder> 
        getHeroEndTimeFieldBuilder() {
      if (heroEndTimeBuilder_ == null) {
        heroEndTimeBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.HeroEndTime, pb4client.HeroEndTime.Builder, pb4client.HeroEndTimeOrBuilder>(
                heroEndTime_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        heroEndTime_ = null;
      }
      return heroEndTimeBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.ConscriptionRt)
  }

  // @@protoc_insertion_point(class_scope:client2server.ConscriptionRt)
  private static final pb4client.ConscriptionRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.ConscriptionRt();
  }

  public static pb4client.ConscriptionRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<ConscriptionRt>
      PARSER = new com.google.protobuf.AbstractParser<ConscriptionRt>() {
    public ConscriptionRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new ConscriptionRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ConscriptionRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ConscriptionRt> getParserForType() {
    return PARSER;
  }

  public pb4client.ConscriptionRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

