// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 3010
 * 服务器 -&gt; 客户端
 * 武将信息变化主推
 * </pre>
 *
 * Protobuf type {@code client2server.HeroChange}
 */
public  final class HeroChange extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.HeroChange)
    HeroChangeOrBuilder {
  // Use HeroChange.newBuilder() to construct.
  private HeroChange(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private HeroChange() {
    heroInfo_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private HeroChange(
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
          case 10: {
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              heroInfo_ = new java.util.ArrayList<pb4client.DetailedQueryHerosInFo>();
              mutable_bitField0_ |= 0x00000001;
            }
            heroInfo_.add(
                input.readMessage(pb4client.DetailedQueryHerosInFo.PARSER, extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
        heroInfo_ = java.util.Collections.unmodifiableList(heroInfo_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_HeroChange_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_HeroChange_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.HeroChange.class, pb4client.HeroChange.Builder.class);
  }

  public static final int HEROINFO_FIELD_NUMBER = 1;
  private java.util.List<pb4client.DetailedQueryHerosInFo> heroInfo_;
  /**
   * <pre>
   *武将信息
   * </pre>
   *
   * <code>repeated .client2server.DetailedQueryHerosInFo heroInfo = 1;</code>
   */
  public java.util.List<pb4client.DetailedQueryHerosInFo> getHeroInfoList() {
    return heroInfo_;
  }
  /**
   * <pre>
   *武将信息
   * </pre>
   *
   * <code>repeated .client2server.DetailedQueryHerosInFo heroInfo = 1;</code>
   */
  public java.util.List<? extends pb4client.DetailedQueryHerosInFoOrBuilder> 
      getHeroInfoOrBuilderList() {
    return heroInfo_;
  }
  /**
   * <pre>
   *武将信息
   * </pre>
   *
   * <code>repeated .client2server.DetailedQueryHerosInFo heroInfo = 1;</code>
   */
  public int getHeroInfoCount() {
    return heroInfo_.size();
  }
  /**
   * <pre>
   *武将信息
   * </pre>
   *
   * <code>repeated .client2server.DetailedQueryHerosInFo heroInfo = 1;</code>
   */
  public pb4client.DetailedQueryHerosInFo getHeroInfo(int index) {
    return heroInfo_.get(index);
  }
  /**
   * <pre>
   *武将信息
   * </pre>
   *
   * <code>repeated .client2server.DetailedQueryHerosInFo heroInfo = 1;</code>
   */
  public pb4client.DetailedQueryHerosInFoOrBuilder getHeroInfoOrBuilder(
      int index) {
    return heroInfo_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

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
    for (int i = 0; i < heroInfo_.size(); i++) {
      output.writeMessage(1, heroInfo_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < heroInfo_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, heroInfo_.get(i));
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
    if (!(obj instanceof pb4client.HeroChange)) {
      return super.equals(obj);
    }
    pb4client.HeroChange other = (pb4client.HeroChange) obj;

    boolean result = true;
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
    if (getHeroInfoCount() > 0) {
      hash = (37 * hash) + HEROINFO_FIELD_NUMBER;
      hash = (53 * hash) + getHeroInfoList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.HeroChange parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.HeroChange parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.HeroChange parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.HeroChange parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.HeroChange parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.HeroChange parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.HeroChange parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.HeroChange parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.HeroChange parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.HeroChange parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.HeroChange parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.HeroChange parseFrom(
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
  public static Builder newBuilder(pb4client.HeroChange prototype) {
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
   * msgType = 3010
   * 服务器 -&gt; 客户端
   * 武将信息变化主推
   * </pre>
   *
   * Protobuf type {@code client2server.HeroChange}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.HeroChange)
      pb4client.HeroChangeOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_HeroChange_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_HeroChange_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.HeroChange.class, pb4client.HeroChange.Builder.class);
    }

    // Construct using pb4client.HeroChange.newBuilder()
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
      if (heroInfoBuilder_ == null) {
        heroInfo_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        heroInfoBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_HeroChange_descriptor;
    }

    public pb4client.HeroChange getDefaultInstanceForType() {
      return pb4client.HeroChange.getDefaultInstance();
    }

    public pb4client.HeroChange build() {
      pb4client.HeroChange result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.HeroChange buildPartial() {
      pb4client.HeroChange result = new pb4client.HeroChange(this);
      int from_bitField0_ = bitField0_;
      if (heroInfoBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          heroInfo_ = java.util.Collections.unmodifiableList(heroInfo_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.heroInfo_ = heroInfo_;
      } else {
        result.heroInfo_ = heroInfoBuilder_.build();
      }
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
      if (other instanceof pb4client.HeroChange) {
        return mergeFrom((pb4client.HeroChange)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.HeroChange other) {
      if (other == pb4client.HeroChange.getDefaultInstance()) return this;
      if (heroInfoBuilder_ == null) {
        if (!other.heroInfo_.isEmpty()) {
          if (heroInfo_.isEmpty()) {
            heroInfo_ = other.heroInfo_;
            bitField0_ = (bitField0_ & ~0x00000001);
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
            bitField0_ = (bitField0_ & ~0x00000001);
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
      pb4client.HeroChange parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.HeroChange) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<pb4client.DetailedQueryHerosInFo> heroInfo_ =
      java.util.Collections.emptyList();
    private void ensureHeroInfoIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        heroInfo_ = new java.util.ArrayList<pb4client.DetailedQueryHerosInFo>(heroInfo_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.DetailedQueryHerosInFo, pb4client.DetailedQueryHerosInFo.Builder, pb4client.DetailedQueryHerosInFoOrBuilder> heroInfoBuilder_;

    /**
     * <pre>
     *武将信息
     * </pre>
     *
     * <code>repeated .client2server.DetailedQueryHerosInFo heroInfo = 1;</code>
     */
    public java.util.List<pb4client.DetailedQueryHerosInFo> getHeroInfoList() {
      if (heroInfoBuilder_ == null) {
        return java.util.Collections.unmodifiableList(heroInfo_);
      } else {
        return heroInfoBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     *武将信息
     * </pre>
     *
     * <code>repeated .client2server.DetailedQueryHerosInFo heroInfo = 1;</code>
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
     *武将信息
     * </pre>
     *
     * <code>repeated .client2server.DetailedQueryHerosInFo heroInfo = 1;</code>
     */
    public pb4client.DetailedQueryHerosInFo getHeroInfo(int index) {
      if (heroInfoBuilder_ == null) {
        return heroInfo_.get(index);
      } else {
        return heroInfoBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     *武将信息
     * </pre>
     *
     * <code>repeated .client2server.DetailedQueryHerosInFo heroInfo = 1;</code>
     */
    public Builder setHeroInfo(
        int index, pb4client.DetailedQueryHerosInFo value) {
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
     *武将信息
     * </pre>
     *
     * <code>repeated .client2server.DetailedQueryHerosInFo heroInfo = 1;</code>
     */
    public Builder setHeroInfo(
        int index, pb4client.DetailedQueryHerosInFo.Builder builderForValue) {
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
     *武将信息
     * </pre>
     *
     * <code>repeated .client2server.DetailedQueryHerosInFo heroInfo = 1;</code>
     */
    public Builder addHeroInfo(pb4client.DetailedQueryHerosInFo value) {
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
     *武将信息
     * </pre>
     *
     * <code>repeated .client2server.DetailedQueryHerosInFo heroInfo = 1;</code>
     */
    public Builder addHeroInfo(
        int index, pb4client.DetailedQueryHerosInFo value) {
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
     *武将信息
     * </pre>
     *
     * <code>repeated .client2server.DetailedQueryHerosInFo heroInfo = 1;</code>
     */
    public Builder addHeroInfo(
        pb4client.DetailedQueryHerosInFo.Builder builderForValue) {
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
     *武将信息
     * </pre>
     *
     * <code>repeated .client2server.DetailedQueryHerosInFo heroInfo = 1;</code>
     */
    public Builder addHeroInfo(
        int index, pb4client.DetailedQueryHerosInFo.Builder builderForValue) {
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
     *武将信息
     * </pre>
     *
     * <code>repeated .client2server.DetailedQueryHerosInFo heroInfo = 1;</code>
     */
    public Builder addAllHeroInfo(
        java.lang.Iterable<? extends pb4client.DetailedQueryHerosInFo> values) {
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
     *武将信息
     * </pre>
     *
     * <code>repeated .client2server.DetailedQueryHerosInFo heroInfo = 1;</code>
     */
    public Builder clearHeroInfo() {
      if (heroInfoBuilder_ == null) {
        heroInfo_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        heroInfoBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     *武将信息
     * </pre>
     *
     * <code>repeated .client2server.DetailedQueryHerosInFo heroInfo = 1;</code>
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
     *武将信息
     * </pre>
     *
     * <code>repeated .client2server.DetailedQueryHerosInFo heroInfo = 1;</code>
     */
    public pb4client.DetailedQueryHerosInFo.Builder getHeroInfoBuilder(
        int index) {
      return getHeroInfoFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     *武将信息
     * </pre>
     *
     * <code>repeated .client2server.DetailedQueryHerosInFo heroInfo = 1;</code>
     */
    public pb4client.DetailedQueryHerosInFoOrBuilder getHeroInfoOrBuilder(
        int index) {
      if (heroInfoBuilder_ == null) {
        return heroInfo_.get(index);  } else {
        return heroInfoBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     *武将信息
     * </pre>
     *
     * <code>repeated .client2server.DetailedQueryHerosInFo heroInfo = 1;</code>
     */
    public java.util.List<? extends pb4client.DetailedQueryHerosInFoOrBuilder> 
         getHeroInfoOrBuilderList() {
      if (heroInfoBuilder_ != null) {
        return heroInfoBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(heroInfo_);
      }
    }
    /**
     * <pre>
     *武将信息
     * </pre>
     *
     * <code>repeated .client2server.DetailedQueryHerosInFo heroInfo = 1;</code>
     */
    public pb4client.DetailedQueryHerosInFo.Builder addHeroInfoBuilder() {
      return getHeroInfoFieldBuilder().addBuilder(
          pb4client.DetailedQueryHerosInFo.getDefaultInstance());
    }
    /**
     * <pre>
     *武将信息
     * </pre>
     *
     * <code>repeated .client2server.DetailedQueryHerosInFo heroInfo = 1;</code>
     */
    public pb4client.DetailedQueryHerosInFo.Builder addHeroInfoBuilder(
        int index) {
      return getHeroInfoFieldBuilder().addBuilder(
          index, pb4client.DetailedQueryHerosInFo.getDefaultInstance());
    }
    /**
     * <pre>
     *武将信息
     * </pre>
     *
     * <code>repeated .client2server.DetailedQueryHerosInFo heroInfo = 1;</code>
     */
    public java.util.List<pb4client.DetailedQueryHerosInFo.Builder> 
         getHeroInfoBuilderList() {
      return getHeroInfoFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.DetailedQueryHerosInFo, pb4client.DetailedQueryHerosInFo.Builder, pb4client.DetailedQueryHerosInFoOrBuilder> 
        getHeroInfoFieldBuilder() {
      if (heroInfoBuilder_ == null) {
        heroInfoBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.DetailedQueryHerosInFo, pb4client.DetailedQueryHerosInFo.Builder, pb4client.DetailedQueryHerosInFoOrBuilder>(
                heroInfo_,
                ((bitField0_ & 0x00000001) == 0x00000001),
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


    // @@protoc_insertion_point(builder_scope:client2server.HeroChange)
  }

  // @@protoc_insertion_point(class_scope:client2server.HeroChange)
  private static final pb4client.HeroChange DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.HeroChange();
  }

  public static pb4client.HeroChange getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<HeroChange>
      PARSER = new com.google.protobuf.AbstractParser<HeroChange>() {
    public HeroChange parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new HeroChange(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<HeroChange> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<HeroChange> getParserForType() {
    return PARSER;
  }

  public pb4client.HeroChange getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

