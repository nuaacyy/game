// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.SetDefHereRt}
 */
public  final class SetDefHereRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.SetDefHereRt)
    SetDefHereRtOrBuilder {
  // Use SetDefHereRt.newBuilder() to construct.
  private SetDefHereRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SetDefHereRt() {
    rt_ = 0;
    heroIds_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private SetDefHereRt(
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
              heroIds_ = new java.util.ArrayList<pb4client.HeroPos>();
              mutable_bitField0_ |= 0x00000002;
            }
            heroIds_.add(
                input.readMessage(pb4client.HeroPos.PARSER, extensionRegistry));
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
        heroIds_ = java.util.Collections.unmodifiableList(heroIds_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_SetDefHereRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_SetDefHereRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.SetDefHereRt.class, pb4client.SetDefHereRt.Builder.class);
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

  public static final int HEROIDS_FIELD_NUMBER = 2;
  private java.util.List<pb4client.HeroPos> heroIds_;
  /**
   * <pre>
   *武将IDS
   * </pre>
   *
   * <code>repeated .client2server.HeroPos heroIds = 2;</code>
   */
  public java.util.List<pb4client.HeroPos> getHeroIdsList() {
    return heroIds_;
  }
  /**
   * <pre>
   *武将IDS
   * </pre>
   *
   * <code>repeated .client2server.HeroPos heroIds = 2;</code>
   */
  public java.util.List<? extends pb4client.HeroPosOrBuilder> 
      getHeroIdsOrBuilderList() {
    return heroIds_;
  }
  /**
   * <pre>
   *武将IDS
   * </pre>
   *
   * <code>repeated .client2server.HeroPos heroIds = 2;</code>
   */
  public int getHeroIdsCount() {
    return heroIds_.size();
  }
  /**
   * <pre>
   *武将IDS
   * </pre>
   *
   * <code>repeated .client2server.HeroPos heroIds = 2;</code>
   */
  public pb4client.HeroPos getHeroIds(int index) {
    return heroIds_.get(index);
  }
  /**
   * <pre>
   *武将IDS
   * </pre>
   *
   * <code>repeated .client2server.HeroPos heroIds = 2;</code>
   */
  public pb4client.HeroPosOrBuilder getHeroIdsOrBuilder(
      int index) {
    return heroIds_.get(index);
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
    for (int i = 0; i < getHeroIdsCount(); i++) {
      if (!getHeroIds(i).isInitialized()) {
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
    for (int i = 0; i < heroIds_.size(); i++) {
      output.writeMessage(2, heroIds_.get(i));
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
    for (int i = 0; i < heroIds_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, heroIds_.get(i));
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
    if (!(obj instanceof pb4client.SetDefHereRt)) {
      return super.equals(obj);
    }
    pb4client.SetDefHereRt other = (pb4client.SetDefHereRt) obj;

    boolean result = true;
    result = result && (hasRt() == other.hasRt());
    if (hasRt()) {
      result = result && (getRt()
          == other.getRt());
    }
    result = result && getHeroIdsList()
        .equals(other.getHeroIdsList());
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
    if (getHeroIdsCount() > 0) {
      hash = (37 * hash) + HEROIDS_FIELD_NUMBER;
      hash = (53 * hash) + getHeroIdsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.SetDefHereRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.SetDefHereRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.SetDefHereRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.SetDefHereRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.SetDefHereRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.SetDefHereRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.SetDefHereRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.SetDefHereRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.SetDefHereRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.SetDefHereRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.SetDefHereRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.SetDefHereRt parseFrom(
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
  public static Builder newBuilder(pb4client.SetDefHereRt prototype) {
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
   * Protobuf type {@code client2server.SetDefHereRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.SetDefHereRt)
      pb4client.SetDefHereRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_SetDefHereRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_SetDefHereRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.SetDefHereRt.class, pb4client.SetDefHereRt.Builder.class);
    }

    // Construct using pb4client.SetDefHereRt.newBuilder()
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
        getHeroIdsFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      rt_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      if (heroIdsBuilder_ == null) {
        heroIds_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        heroIdsBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_SetDefHereRt_descriptor;
    }

    public pb4client.SetDefHereRt getDefaultInstanceForType() {
      return pb4client.SetDefHereRt.getDefaultInstance();
    }

    public pb4client.SetDefHereRt build() {
      pb4client.SetDefHereRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.SetDefHereRt buildPartial() {
      pb4client.SetDefHereRt result = new pb4client.SetDefHereRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.rt_ = rt_;
      if (heroIdsBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          heroIds_ = java.util.Collections.unmodifiableList(heroIds_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.heroIds_ = heroIds_;
      } else {
        result.heroIds_ = heroIdsBuilder_.build();
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
      if (other instanceof pb4client.SetDefHereRt) {
        return mergeFrom((pb4client.SetDefHereRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.SetDefHereRt other) {
      if (other == pb4client.SetDefHereRt.getDefaultInstance()) return this;
      if (other.hasRt()) {
        setRt(other.getRt());
      }
      if (heroIdsBuilder_ == null) {
        if (!other.heroIds_.isEmpty()) {
          if (heroIds_.isEmpty()) {
            heroIds_ = other.heroIds_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureHeroIdsIsMutable();
            heroIds_.addAll(other.heroIds_);
          }
          onChanged();
        }
      } else {
        if (!other.heroIds_.isEmpty()) {
          if (heroIdsBuilder_.isEmpty()) {
            heroIdsBuilder_.dispose();
            heroIdsBuilder_ = null;
            heroIds_ = other.heroIds_;
            bitField0_ = (bitField0_ & ~0x00000002);
            heroIdsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getHeroIdsFieldBuilder() : null;
          } else {
            heroIdsBuilder_.addAllMessages(other.heroIds_);
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
      for (int i = 0; i < getHeroIdsCount(); i++) {
        if (!getHeroIds(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.SetDefHereRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.SetDefHereRt) e.getUnfinishedMessage();
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

    private java.util.List<pb4client.HeroPos> heroIds_ =
      java.util.Collections.emptyList();
    private void ensureHeroIdsIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        heroIds_ = new java.util.ArrayList<pb4client.HeroPos>(heroIds_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.HeroPos, pb4client.HeroPos.Builder, pb4client.HeroPosOrBuilder> heroIdsBuilder_;

    /**
     * <pre>
     *武将IDS
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroIds = 2;</code>
     */
    public java.util.List<pb4client.HeroPos> getHeroIdsList() {
      if (heroIdsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(heroIds_);
      } else {
        return heroIdsBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     *武将IDS
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroIds = 2;</code>
     */
    public int getHeroIdsCount() {
      if (heroIdsBuilder_ == null) {
        return heroIds_.size();
      } else {
        return heroIdsBuilder_.getCount();
      }
    }
    /**
     * <pre>
     *武将IDS
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroIds = 2;</code>
     */
    public pb4client.HeroPos getHeroIds(int index) {
      if (heroIdsBuilder_ == null) {
        return heroIds_.get(index);
      } else {
        return heroIdsBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     *武将IDS
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroIds = 2;</code>
     */
    public Builder setHeroIds(
        int index, pb4client.HeroPos value) {
      if (heroIdsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureHeroIdsIsMutable();
        heroIds_.set(index, value);
        onChanged();
      } else {
        heroIdsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     *武将IDS
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroIds = 2;</code>
     */
    public Builder setHeroIds(
        int index, pb4client.HeroPos.Builder builderForValue) {
      if (heroIdsBuilder_ == null) {
        ensureHeroIdsIsMutable();
        heroIds_.set(index, builderForValue.build());
        onChanged();
      } else {
        heroIdsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *武将IDS
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroIds = 2;</code>
     */
    public Builder addHeroIds(pb4client.HeroPos value) {
      if (heroIdsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureHeroIdsIsMutable();
        heroIds_.add(value);
        onChanged();
      } else {
        heroIdsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     *武将IDS
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroIds = 2;</code>
     */
    public Builder addHeroIds(
        int index, pb4client.HeroPos value) {
      if (heroIdsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureHeroIdsIsMutable();
        heroIds_.add(index, value);
        onChanged();
      } else {
        heroIdsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     *武将IDS
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroIds = 2;</code>
     */
    public Builder addHeroIds(
        pb4client.HeroPos.Builder builderForValue) {
      if (heroIdsBuilder_ == null) {
        ensureHeroIdsIsMutable();
        heroIds_.add(builderForValue.build());
        onChanged();
      } else {
        heroIdsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *武将IDS
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroIds = 2;</code>
     */
    public Builder addHeroIds(
        int index, pb4client.HeroPos.Builder builderForValue) {
      if (heroIdsBuilder_ == null) {
        ensureHeroIdsIsMutable();
        heroIds_.add(index, builderForValue.build());
        onChanged();
      } else {
        heroIdsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *武将IDS
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroIds = 2;</code>
     */
    public Builder addAllHeroIds(
        java.lang.Iterable<? extends pb4client.HeroPos> values) {
      if (heroIdsBuilder_ == null) {
        ensureHeroIdsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, heroIds_);
        onChanged();
      } else {
        heroIdsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     *武将IDS
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroIds = 2;</code>
     */
    public Builder clearHeroIds() {
      if (heroIdsBuilder_ == null) {
        heroIds_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        heroIdsBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     *武将IDS
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroIds = 2;</code>
     */
    public Builder removeHeroIds(int index) {
      if (heroIdsBuilder_ == null) {
        ensureHeroIdsIsMutable();
        heroIds_.remove(index);
        onChanged();
      } else {
        heroIdsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     *武将IDS
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroIds = 2;</code>
     */
    public pb4client.HeroPos.Builder getHeroIdsBuilder(
        int index) {
      return getHeroIdsFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     *武将IDS
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroIds = 2;</code>
     */
    public pb4client.HeroPosOrBuilder getHeroIdsOrBuilder(
        int index) {
      if (heroIdsBuilder_ == null) {
        return heroIds_.get(index);  } else {
        return heroIdsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     *武将IDS
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroIds = 2;</code>
     */
    public java.util.List<? extends pb4client.HeroPosOrBuilder> 
         getHeroIdsOrBuilderList() {
      if (heroIdsBuilder_ != null) {
        return heroIdsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(heroIds_);
      }
    }
    /**
     * <pre>
     *武将IDS
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroIds = 2;</code>
     */
    public pb4client.HeroPos.Builder addHeroIdsBuilder() {
      return getHeroIdsFieldBuilder().addBuilder(
          pb4client.HeroPos.getDefaultInstance());
    }
    /**
     * <pre>
     *武将IDS
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroIds = 2;</code>
     */
    public pb4client.HeroPos.Builder addHeroIdsBuilder(
        int index) {
      return getHeroIdsFieldBuilder().addBuilder(
          index, pb4client.HeroPos.getDefaultInstance());
    }
    /**
     * <pre>
     *武将IDS
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroIds = 2;</code>
     */
    public java.util.List<pb4client.HeroPos.Builder> 
         getHeroIdsBuilderList() {
      return getHeroIdsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.HeroPos, pb4client.HeroPos.Builder, pb4client.HeroPosOrBuilder> 
        getHeroIdsFieldBuilder() {
      if (heroIdsBuilder_ == null) {
        heroIdsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.HeroPos, pb4client.HeroPos.Builder, pb4client.HeroPosOrBuilder>(
                heroIds_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        heroIds_ = null;
      }
      return heroIdsBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.SetDefHereRt)
  }

  // @@protoc_insertion_point(class_scope:client2server.SetDefHereRt)
  private static final pb4client.SetDefHereRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.SetDefHereRt();
  }

  public static pb4client.SetDefHereRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<SetDefHereRt>
      PARSER = new com.google.protobuf.AbstractParser<SetDefHereRt>() {
    public SetDefHereRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new SetDefHereRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SetDefHereRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SetDefHereRt> getParserForType() {
    return PARSER;
  }

  public pb4client.SetDefHereRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
