// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.GetArmyPlanRt}
 */
public  final class GetArmyPlanRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.GetArmyPlanRt)
    GetArmyPlanRtOrBuilder {
  // Use GetArmyPlanRt.newBuilder() to construct.
  private GetArmyPlanRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GetArmyPlanRt() {
    rt_ = 0;
    heroInfo_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GetArmyPlanRt(
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
              heroInfo_ = new java.util.ArrayList<pb4client.HeroPos>();
              mutable_bitField0_ |= 0x00000002;
            }
            heroInfo_.add(
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
        heroInfo_ = java.util.Collections.unmodifiableList(heroInfo_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_GetArmyPlanRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_GetArmyPlanRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.GetArmyPlanRt.class, pb4client.GetArmyPlanRt.Builder.class);
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

  public static final int HEROINFO_FIELD_NUMBER = 2;
  private java.util.List<pb4client.HeroPos> heroInfo_;
  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroPos heroInfo = 2;</code>
   */
  public java.util.List<pb4client.HeroPos> getHeroInfoList() {
    return heroInfo_;
  }
  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroPos heroInfo = 2;</code>
   */
  public java.util.List<? extends pb4client.HeroPosOrBuilder> 
      getHeroInfoOrBuilderList() {
    return heroInfo_;
  }
  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroPos heroInfo = 2;</code>
   */
  public int getHeroInfoCount() {
    return heroInfo_.size();
  }
  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroPos heroInfo = 2;</code>
   */
  public pb4client.HeroPos getHeroInfo(int index) {
    return heroInfo_.get(index);
  }
  /**
   * <pre>
   *英雄信息
   * </pre>
   *
   * <code>repeated .client2server.HeroPos heroInfo = 2;</code>
   */
  public pb4client.HeroPosOrBuilder getHeroInfoOrBuilder(
      int index) {
    return heroInfo_.get(index);
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
    for (int i = 0; i < getHeroInfoCount(); i++) {
      if (!getHeroInfo(i).isInitialized()) {
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
    for (int i = 0; i < heroInfo_.size(); i++) {
      output.writeMessage(2, heroInfo_.get(i));
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
    for (int i = 0; i < heroInfo_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, heroInfo_.get(i));
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
    if (!(obj instanceof pb4client.GetArmyPlanRt)) {
      return super.equals(obj);
    }
    pb4client.GetArmyPlanRt other = (pb4client.GetArmyPlanRt) obj;

    boolean result = true;
    result = result && (hasRt() == other.hasRt());
    if (hasRt()) {
      result = result && (getRt()
          == other.getRt());
    }
    result = result && getHeroInfoList()
        .equals(other.getHeroInfoList());
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
    if (getHeroInfoCount() > 0) {
      hash = (37 * hash) + HEROINFO_FIELD_NUMBER;
      hash = (53 * hash) + getHeroInfoList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.GetArmyPlanRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.GetArmyPlanRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.GetArmyPlanRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.GetArmyPlanRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.GetArmyPlanRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.GetArmyPlanRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.GetArmyPlanRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.GetArmyPlanRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.GetArmyPlanRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.GetArmyPlanRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.GetArmyPlanRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.GetArmyPlanRt parseFrom(
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
  public static Builder newBuilder(pb4client.GetArmyPlanRt prototype) {
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
   * Protobuf type {@code client2server.GetArmyPlanRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.GetArmyPlanRt)
      pb4client.GetArmyPlanRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_GetArmyPlanRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_GetArmyPlanRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.GetArmyPlanRt.class, pb4client.GetArmyPlanRt.Builder.class);
    }

    // Construct using pb4client.GetArmyPlanRt.newBuilder()
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
        getHeroInfoFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      rt_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      if (heroInfoBuilder_ == null) {
        heroInfo_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        heroInfoBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_GetArmyPlanRt_descriptor;
    }

    public pb4client.GetArmyPlanRt getDefaultInstanceForType() {
      return pb4client.GetArmyPlanRt.getDefaultInstance();
    }

    public pb4client.GetArmyPlanRt build() {
      pb4client.GetArmyPlanRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.GetArmyPlanRt buildPartial() {
      pb4client.GetArmyPlanRt result = new pb4client.GetArmyPlanRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.rt_ = rt_;
      if (heroInfoBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          heroInfo_ = java.util.Collections.unmodifiableList(heroInfo_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.heroInfo_ = heroInfo_;
      } else {
        result.heroInfo_ = heroInfoBuilder_.build();
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
      if (other instanceof pb4client.GetArmyPlanRt) {
        return mergeFrom((pb4client.GetArmyPlanRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.GetArmyPlanRt other) {
      if (other == pb4client.GetArmyPlanRt.getDefaultInstance()) return this;
      if (other.hasRt()) {
        setRt(other.getRt());
      }
      if (heroInfoBuilder_ == null) {
        if (!other.heroInfo_.isEmpty()) {
          if (heroInfo_.isEmpty()) {
            heroInfo_ = other.heroInfo_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureHeroInfoIsMutable();
            heroInfo_.addAll(other.heroInfo_);
          }
          onChanged();
        }
      } else {
        if (!other.heroInfo_.isEmpty()) {
          if (heroInfoBuilder_.isEmpty()) {
            heroInfoBuilder_.dispose();
            heroInfoBuilder_ = null;
            heroInfo_ = other.heroInfo_;
            bitField0_ = (bitField0_ & ~0x00000002);
            heroInfoBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getHeroInfoFieldBuilder() : null;
          } else {
            heroInfoBuilder_.addAllMessages(other.heroInfo_);
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
      for (int i = 0; i < getHeroInfoCount(); i++) {
        if (!getHeroInfo(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.GetArmyPlanRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.GetArmyPlanRt) e.getUnfinishedMessage();
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

    private java.util.List<pb4client.HeroPos> heroInfo_ =
      java.util.Collections.emptyList();
    private void ensureHeroInfoIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        heroInfo_ = new java.util.ArrayList<pb4client.HeroPos>(heroInfo_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.HeroPos, pb4client.HeroPos.Builder, pb4client.HeroPosOrBuilder> heroInfoBuilder_;

    /**
     * <pre>
     *英雄信息
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroInfo = 2;</code>
     */
    public java.util.List<pb4client.HeroPos> getHeroInfoList() {
      if (heroInfoBuilder_ == null) {
        return java.util.Collections.unmodifiableList(heroInfo_);
      } else {
        return heroInfoBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     *英雄信息
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroInfo = 2;</code>
     */
    public int getHeroInfoCount() {
      if (heroInfoBuilder_ == null) {
        return heroInfo_.size();
      } else {
        return heroInfoBuilder_.getCount();
      }
    }
    /**
     * <pre>
     *英雄信息
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroInfo = 2;</code>
     */
    public pb4client.HeroPos getHeroInfo(int index) {
      if (heroInfoBuilder_ == null) {
        return heroInfo_.get(index);
      } else {
        return heroInfoBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     *英雄信息
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroInfo = 2;</code>
     */
    public Builder setHeroInfo(
        int index, pb4client.HeroPos value) {
      if (heroInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureHeroInfoIsMutable();
        heroInfo_.set(index, value);
        onChanged();
      } else {
        heroInfoBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     *英雄信息
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroInfo = 2;</code>
     */
    public Builder setHeroInfo(
        int index, pb4client.HeroPos.Builder builderForValue) {
      if (heroInfoBuilder_ == null) {
        ensureHeroInfoIsMutable();
        heroInfo_.set(index, builderForValue.build());
        onChanged();
      } else {
        heroInfoBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *英雄信息
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroInfo = 2;</code>
     */
    public Builder addHeroInfo(pb4client.HeroPos value) {
      if (heroInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureHeroInfoIsMutable();
        heroInfo_.add(value);
        onChanged();
      } else {
        heroInfoBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     *英雄信息
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroInfo = 2;</code>
     */
    public Builder addHeroInfo(
        int index, pb4client.HeroPos value) {
      if (heroInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureHeroInfoIsMutable();
        heroInfo_.add(index, value);
        onChanged();
      } else {
        heroInfoBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     *英雄信息
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroInfo = 2;</code>
     */
    public Builder addHeroInfo(
        pb4client.HeroPos.Builder builderForValue) {
      if (heroInfoBuilder_ == null) {
        ensureHeroInfoIsMutable();
        heroInfo_.add(builderForValue.build());
        onChanged();
      } else {
        heroInfoBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *英雄信息
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroInfo = 2;</code>
     */
    public Builder addHeroInfo(
        int index, pb4client.HeroPos.Builder builderForValue) {
      if (heroInfoBuilder_ == null) {
        ensureHeroInfoIsMutable();
        heroInfo_.add(index, builderForValue.build());
        onChanged();
      } else {
        heroInfoBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     *英雄信息
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroInfo = 2;</code>
     */
    public Builder addAllHeroInfo(
        java.lang.Iterable<? extends pb4client.HeroPos> values) {
      if (heroInfoBuilder_ == null) {
        ensureHeroInfoIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, heroInfo_);
        onChanged();
      } else {
        heroInfoBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     *英雄信息
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroInfo = 2;</code>
     */
    public Builder clearHeroInfo() {
      if (heroInfoBuilder_ == null) {
        heroInfo_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        heroInfoBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     *英雄信息
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroInfo = 2;</code>
     */
    public Builder removeHeroInfo(int index) {
      if (heroInfoBuilder_ == null) {
        ensureHeroInfoIsMutable();
        heroInfo_.remove(index);
        onChanged();
      } else {
        heroInfoBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     *英雄信息
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroInfo = 2;</code>
     */
    public pb4client.HeroPos.Builder getHeroInfoBuilder(
        int index) {
      return getHeroInfoFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     *英雄信息
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroInfo = 2;</code>
     */
    public pb4client.HeroPosOrBuilder getHeroInfoOrBuilder(
        int index) {
      if (heroInfoBuilder_ == null) {
        return heroInfo_.get(index);  } else {
        return heroInfoBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     *英雄信息
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroInfo = 2;</code>
     */
    public java.util.List<? extends pb4client.HeroPosOrBuilder> 
         getHeroInfoOrBuilderList() {
      if (heroInfoBuilder_ != null) {
        return heroInfoBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(heroInfo_);
      }
    }
    /**
     * <pre>
     *英雄信息
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroInfo = 2;</code>
     */
    public pb4client.HeroPos.Builder addHeroInfoBuilder() {
      return getHeroInfoFieldBuilder().addBuilder(
          pb4client.HeroPos.getDefaultInstance());
    }
    /**
     * <pre>
     *英雄信息
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroInfo = 2;</code>
     */
    public pb4client.HeroPos.Builder addHeroInfoBuilder(
        int index) {
      return getHeroInfoFieldBuilder().addBuilder(
          index, pb4client.HeroPos.getDefaultInstance());
    }
    /**
     * <pre>
     *英雄信息
     * </pre>
     *
     * <code>repeated .client2server.HeroPos heroInfo = 2;</code>
     */
    public java.util.List<pb4client.HeroPos.Builder> 
         getHeroInfoBuilderList() {
      return getHeroInfoFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.HeroPos, pb4client.HeroPos.Builder, pb4client.HeroPosOrBuilder> 
        getHeroInfoFieldBuilder() {
      if (heroInfoBuilder_ == null) {
        heroInfoBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.HeroPos, pb4client.HeroPos.Builder, pb4client.HeroPosOrBuilder>(
                heroInfo_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        heroInfo_ = null;
      }
      return heroInfoBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.GetArmyPlanRt)
  }

  // @@protoc_insertion_point(class_scope:client2server.GetArmyPlanRt)
  private static final pb4client.GetArmyPlanRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.GetArmyPlanRt();
  }

  public static pb4client.GetArmyPlanRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<GetArmyPlanRt>
      PARSER = new com.google.protobuf.AbstractParser<GetArmyPlanRt>() {
    public GetArmyPlanRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new GetArmyPlanRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GetArmyPlanRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GetArmyPlanRt> getParserForType() {
    return PARSER;
  }

  public pb4client.GetArmyPlanRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

