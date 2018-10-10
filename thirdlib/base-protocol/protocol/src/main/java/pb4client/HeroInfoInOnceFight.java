// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 *一场战斗中的英雄数据
 * </pre>
 *
 * Protobuf type {@code client2server.HeroInfoInOnceFight}
 */
public  final class HeroInfoInOnceFight extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.HeroInfoInOnceFight)
    HeroInfoInOnceFightOrBuilder {
  // Use HeroInfoInOnceFight.newBuilder() to construct.
  private HeroInfoInOnceFight(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private HeroInfoInOnceFight() {
    heros_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private HeroInfoInOnceFight(
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
              heros_ = new java.util.ArrayList<pb4client.HeroFightHeroInfo>();
              mutable_bitField0_ |= 0x00000001;
            }
            heros_.add(
                input.readMessage(pb4client.HeroFightHeroInfo.PARSER, extensionRegistry));
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
        heros_ = java.util.Collections.unmodifiableList(heros_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_HeroInfoInOnceFight_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_HeroInfoInOnceFight_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.HeroInfoInOnceFight.class, pb4client.HeroInfoInOnceFight.Builder.class);
  }

  public static final int HEROS_FIELD_NUMBER = 1;
  private java.util.List<pb4client.HeroFightHeroInfo> heros_;
  /**
   * <code>repeated .client2server.HeroFightHeroInfo heros = 1;</code>
   */
  public java.util.List<pb4client.HeroFightHeroInfo> getHerosList() {
    return heros_;
  }
  /**
   * <code>repeated .client2server.HeroFightHeroInfo heros = 1;</code>
   */
  public java.util.List<? extends pb4client.HeroFightHeroInfoOrBuilder> 
      getHerosOrBuilderList() {
    return heros_;
  }
  /**
   * <code>repeated .client2server.HeroFightHeroInfo heros = 1;</code>
   */
  public int getHerosCount() {
    return heros_.size();
  }
  /**
   * <code>repeated .client2server.HeroFightHeroInfo heros = 1;</code>
   */
  public pb4client.HeroFightHeroInfo getHeros(int index) {
    return heros_.get(index);
  }
  /**
   * <code>repeated .client2server.HeroFightHeroInfo heros = 1;</code>
   */
  public pb4client.HeroFightHeroInfoOrBuilder getHerosOrBuilder(
      int index) {
    return heros_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    for (int i = 0; i < getHerosCount(); i++) {
      if (!getHeros(i).isInitialized()) {
        memoizedIsInitialized = 0;
        return false;
      }
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < heros_.size(); i++) {
      output.writeMessage(1, heros_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < heros_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, heros_.get(i));
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
    if (!(obj instanceof pb4client.HeroInfoInOnceFight)) {
      return super.equals(obj);
    }
    pb4client.HeroInfoInOnceFight other = (pb4client.HeroInfoInOnceFight) obj;

    boolean result = true;
    result = result && getHerosList()
        .equals(other.getHerosList());
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
    if (getHerosCount() > 0) {
      hash = (37 * hash) + HEROS_FIELD_NUMBER;
      hash = (53 * hash) + getHerosList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.HeroInfoInOnceFight parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.HeroInfoInOnceFight parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.HeroInfoInOnceFight parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.HeroInfoInOnceFight parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.HeroInfoInOnceFight parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.HeroInfoInOnceFight parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.HeroInfoInOnceFight parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.HeroInfoInOnceFight parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.HeroInfoInOnceFight parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.HeroInfoInOnceFight parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.HeroInfoInOnceFight parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.HeroInfoInOnceFight parseFrom(
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
  public static Builder newBuilder(pb4client.HeroInfoInOnceFight prototype) {
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
   *一场战斗中的英雄数据
   * </pre>
   *
   * Protobuf type {@code client2server.HeroInfoInOnceFight}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.HeroInfoInOnceFight)
      pb4client.HeroInfoInOnceFightOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_HeroInfoInOnceFight_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_HeroInfoInOnceFight_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.HeroInfoInOnceFight.class, pb4client.HeroInfoInOnceFight.Builder.class);
    }

    // Construct using pb4client.HeroInfoInOnceFight.newBuilder()
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
        getHerosFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (herosBuilder_ == null) {
        heros_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        herosBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_HeroInfoInOnceFight_descriptor;
    }

    public pb4client.HeroInfoInOnceFight getDefaultInstanceForType() {
      return pb4client.HeroInfoInOnceFight.getDefaultInstance();
    }

    public pb4client.HeroInfoInOnceFight build() {
      pb4client.HeroInfoInOnceFight result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.HeroInfoInOnceFight buildPartial() {
      pb4client.HeroInfoInOnceFight result = new pb4client.HeroInfoInOnceFight(this);
      int from_bitField0_ = bitField0_;
      if (herosBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          heros_ = java.util.Collections.unmodifiableList(heros_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.heros_ = heros_;
      } else {
        result.heros_ = herosBuilder_.build();
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
      if (other instanceof pb4client.HeroInfoInOnceFight) {
        return mergeFrom((pb4client.HeroInfoInOnceFight)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.HeroInfoInOnceFight other) {
      if (other == pb4client.HeroInfoInOnceFight.getDefaultInstance()) return this;
      if (herosBuilder_ == null) {
        if (!other.heros_.isEmpty()) {
          if (heros_.isEmpty()) {
            heros_ = other.heros_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureHerosIsMutable();
            heros_.addAll(other.heros_);
          }
          onChanged();
        }
      } else {
        if (!other.heros_.isEmpty()) {
          if (herosBuilder_.isEmpty()) {
            herosBuilder_.dispose();
            herosBuilder_ = null;
            heros_ = other.heros_;
            bitField0_ = (bitField0_ & ~0x00000001);
            herosBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getHerosFieldBuilder() : null;
          } else {
            herosBuilder_.addAllMessages(other.heros_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      for (int i = 0; i < getHerosCount(); i++) {
        if (!getHeros(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.HeroInfoInOnceFight parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.HeroInfoInOnceFight) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<pb4client.HeroFightHeroInfo> heros_ =
      java.util.Collections.emptyList();
    private void ensureHerosIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        heros_ = new java.util.ArrayList<pb4client.HeroFightHeroInfo>(heros_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.HeroFightHeroInfo, pb4client.HeroFightHeroInfo.Builder, pb4client.HeroFightHeroInfoOrBuilder> herosBuilder_;

    /**
     * <code>repeated .client2server.HeroFightHeroInfo heros = 1;</code>
     */
    public java.util.List<pb4client.HeroFightHeroInfo> getHerosList() {
      if (herosBuilder_ == null) {
        return java.util.Collections.unmodifiableList(heros_);
      } else {
        return herosBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .client2server.HeroFightHeroInfo heros = 1;</code>
     */
    public int getHerosCount() {
      if (herosBuilder_ == null) {
        return heros_.size();
      } else {
        return herosBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .client2server.HeroFightHeroInfo heros = 1;</code>
     */
    public pb4client.HeroFightHeroInfo getHeros(int index) {
      if (herosBuilder_ == null) {
        return heros_.get(index);
      } else {
        return herosBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .client2server.HeroFightHeroInfo heros = 1;</code>
     */
    public Builder setHeros(
        int index, pb4client.HeroFightHeroInfo value) {
      if (herosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureHerosIsMutable();
        heros_.set(index, value);
        onChanged();
      } else {
        herosBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.HeroFightHeroInfo heros = 1;</code>
     */
    public Builder setHeros(
        int index, pb4client.HeroFightHeroInfo.Builder builderForValue) {
      if (herosBuilder_ == null) {
        ensureHerosIsMutable();
        heros_.set(index, builderForValue.build());
        onChanged();
      } else {
        herosBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.HeroFightHeroInfo heros = 1;</code>
     */
    public Builder addHeros(pb4client.HeroFightHeroInfo value) {
      if (herosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureHerosIsMutable();
        heros_.add(value);
        onChanged();
      } else {
        herosBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.HeroFightHeroInfo heros = 1;</code>
     */
    public Builder addHeros(
        int index, pb4client.HeroFightHeroInfo value) {
      if (herosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureHerosIsMutable();
        heros_.add(index, value);
        onChanged();
      } else {
        herosBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.HeroFightHeroInfo heros = 1;</code>
     */
    public Builder addHeros(
        pb4client.HeroFightHeroInfo.Builder builderForValue) {
      if (herosBuilder_ == null) {
        ensureHerosIsMutable();
        heros_.add(builderForValue.build());
        onChanged();
      } else {
        herosBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.HeroFightHeroInfo heros = 1;</code>
     */
    public Builder addHeros(
        int index, pb4client.HeroFightHeroInfo.Builder builderForValue) {
      if (herosBuilder_ == null) {
        ensureHerosIsMutable();
        heros_.add(index, builderForValue.build());
        onChanged();
      } else {
        herosBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.HeroFightHeroInfo heros = 1;</code>
     */
    public Builder addAllHeros(
        java.lang.Iterable<? extends pb4client.HeroFightHeroInfo> values) {
      if (herosBuilder_ == null) {
        ensureHerosIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, heros_);
        onChanged();
      } else {
        herosBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.HeroFightHeroInfo heros = 1;</code>
     */
    public Builder clearHeros() {
      if (herosBuilder_ == null) {
        heros_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        herosBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .client2server.HeroFightHeroInfo heros = 1;</code>
     */
    public Builder removeHeros(int index) {
      if (herosBuilder_ == null) {
        ensureHerosIsMutable();
        heros_.remove(index);
        onChanged();
      } else {
        herosBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.HeroFightHeroInfo heros = 1;</code>
     */
    public pb4client.HeroFightHeroInfo.Builder getHerosBuilder(
        int index) {
      return getHerosFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .client2server.HeroFightHeroInfo heros = 1;</code>
     */
    public pb4client.HeroFightHeroInfoOrBuilder getHerosOrBuilder(
        int index) {
      if (herosBuilder_ == null) {
        return heros_.get(index);  } else {
        return herosBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .client2server.HeroFightHeroInfo heros = 1;</code>
     */
    public java.util.List<? extends pb4client.HeroFightHeroInfoOrBuilder> 
         getHerosOrBuilderList() {
      if (herosBuilder_ != null) {
        return herosBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(heros_);
      }
    }
    /**
     * <code>repeated .client2server.HeroFightHeroInfo heros = 1;</code>
     */
    public pb4client.HeroFightHeroInfo.Builder addHerosBuilder() {
      return getHerosFieldBuilder().addBuilder(
          pb4client.HeroFightHeroInfo.getDefaultInstance());
    }
    /**
     * <code>repeated .client2server.HeroFightHeroInfo heros = 1;</code>
     */
    public pb4client.HeroFightHeroInfo.Builder addHerosBuilder(
        int index) {
      return getHerosFieldBuilder().addBuilder(
          index, pb4client.HeroFightHeroInfo.getDefaultInstance());
    }
    /**
     * <code>repeated .client2server.HeroFightHeroInfo heros = 1;</code>
     */
    public java.util.List<pb4client.HeroFightHeroInfo.Builder> 
         getHerosBuilderList() {
      return getHerosFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.HeroFightHeroInfo, pb4client.HeroFightHeroInfo.Builder, pb4client.HeroFightHeroInfoOrBuilder> 
        getHerosFieldBuilder() {
      if (herosBuilder_ == null) {
        herosBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.HeroFightHeroInfo, pb4client.HeroFightHeroInfo.Builder, pb4client.HeroFightHeroInfoOrBuilder>(
                heros_,
                ((bitField0_ & 0x00000001) == 0x00000001),
                getParentForChildren(),
                isClean());
        heros_ = null;
      }
      return herosBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.HeroInfoInOnceFight)
  }

  // @@protoc_insertion_point(class_scope:client2server.HeroInfoInOnceFight)
  private static final pb4client.HeroInfoInOnceFight DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.HeroInfoInOnceFight();
  }

  public static pb4client.HeroInfoInOnceFight getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<HeroInfoInOnceFight>
      PARSER = new com.google.protobuf.AbstractParser<HeroInfoInOnceFight>() {
    public HeroInfoInOnceFight parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new HeroInfoInOnceFight(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<HeroInfoInOnceFight> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<HeroInfoInOnceFight> getParserForType() {
    return PARSER;
  }

  public pb4client.HeroInfoInOnceFight getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

