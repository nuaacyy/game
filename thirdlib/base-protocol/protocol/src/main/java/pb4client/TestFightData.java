// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 *测试战斗数据
 * </pre>
 *
 * Protobuf type {@code client2server.TestFightData}
 */
public  final class TestFightData extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.TestFightData)
    TestFightDataOrBuilder {
  // Use TestFightData.newBuilder() to construct.
  private TestFightData(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TestFightData() {
    heros_ = java.util.Collections.emptyList();
    soliders_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private TestFightData(
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
              heros_ = new java.util.ArrayList<pb4client.TestFightHero>();
              mutable_bitField0_ |= 0x00000001;
            }
            heros_.add(
                input.readMessage(pb4client.TestFightHero.PARSER, extensionRegistry));
            break;
          }
          case 18: {
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              soliders_ = new java.util.ArrayList<pb4client.TestFightSolider>();
              mutable_bitField0_ |= 0x00000002;
            }
            soliders_.add(
                input.readMessage(pb4client.TestFightSolider.PARSER, extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
        soliders_ = java.util.Collections.unmodifiableList(soliders_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_TestFightData_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_TestFightData_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.TestFightData.class, pb4client.TestFightData.Builder.class);
  }

  public static final int HEROS_FIELD_NUMBER = 1;
  private java.util.List<pb4client.TestFightHero> heros_;
  /**
   * <code>repeated .client2server.TestFightHero heros = 1;</code>
   */
  public java.util.List<pb4client.TestFightHero> getHerosList() {
    return heros_;
  }
  /**
   * <code>repeated .client2server.TestFightHero heros = 1;</code>
   */
  public java.util.List<? extends pb4client.TestFightHeroOrBuilder> 
      getHerosOrBuilderList() {
    return heros_;
  }
  /**
   * <code>repeated .client2server.TestFightHero heros = 1;</code>
   */
  public int getHerosCount() {
    return heros_.size();
  }
  /**
   * <code>repeated .client2server.TestFightHero heros = 1;</code>
   */
  public pb4client.TestFightHero getHeros(int index) {
    return heros_.get(index);
  }
  /**
   * <code>repeated .client2server.TestFightHero heros = 1;</code>
   */
  public pb4client.TestFightHeroOrBuilder getHerosOrBuilder(
      int index) {
    return heros_.get(index);
  }

  public static final int SOLIDERS_FIELD_NUMBER = 2;
  private java.util.List<pb4client.TestFightSolider> soliders_;
  /**
   * <code>repeated .client2server.TestFightSolider soliders = 2;</code>
   */
  public java.util.List<pb4client.TestFightSolider> getSolidersList() {
    return soliders_;
  }
  /**
   * <code>repeated .client2server.TestFightSolider soliders = 2;</code>
   */
  public java.util.List<? extends pb4client.TestFightSoliderOrBuilder> 
      getSolidersOrBuilderList() {
    return soliders_;
  }
  /**
   * <code>repeated .client2server.TestFightSolider soliders = 2;</code>
   */
  public int getSolidersCount() {
    return soliders_.size();
  }
  /**
   * <code>repeated .client2server.TestFightSolider soliders = 2;</code>
   */
  public pb4client.TestFightSolider getSoliders(int index) {
    return soliders_.get(index);
  }
  /**
   * <code>repeated .client2server.TestFightSolider soliders = 2;</code>
   */
  public pb4client.TestFightSoliderOrBuilder getSolidersOrBuilder(
      int index) {
    return soliders_.get(index);
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
    for (int i = 0; i < getSolidersCount(); i++) {
      if (!getSoliders(i).isInitialized()) {
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
    for (int i = 0; i < soliders_.size(); i++) {
      output.writeMessage(2, soliders_.get(i));
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
    for (int i = 0; i < soliders_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, soliders_.get(i));
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
    if (!(obj instanceof pb4client.TestFightData)) {
      return super.equals(obj);
    }
    pb4client.TestFightData other = (pb4client.TestFightData) obj;

    boolean result = true;
    result = result && getHerosList()
        .equals(other.getHerosList());
    result = result && getSolidersList()
        .equals(other.getSolidersList());
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
    if (getSolidersCount() > 0) {
      hash = (37 * hash) + SOLIDERS_FIELD_NUMBER;
      hash = (53 * hash) + getSolidersList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.TestFightData parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.TestFightData parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.TestFightData parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.TestFightData parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.TestFightData parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.TestFightData parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.TestFightData parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.TestFightData parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.TestFightData parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.TestFightData parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.TestFightData parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.TestFightData parseFrom(
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
  public static Builder newBuilder(pb4client.TestFightData prototype) {
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
   *测试战斗数据
   * </pre>
   *
   * Protobuf type {@code client2server.TestFightData}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.TestFightData)
      pb4client.TestFightDataOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_TestFightData_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_TestFightData_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.TestFightData.class, pb4client.TestFightData.Builder.class);
    }

    // Construct using pb4client.TestFightData.newBuilder()
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
        getSolidersFieldBuilder();
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
      if (solidersBuilder_ == null) {
        soliders_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        solidersBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_TestFightData_descriptor;
    }

    public pb4client.TestFightData getDefaultInstanceForType() {
      return pb4client.TestFightData.getDefaultInstance();
    }

    public pb4client.TestFightData build() {
      pb4client.TestFightData result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.TestFightData buildPartial() {
      pb4client.TestFightData result = new pb4client.TestFightData(this);
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
      if (solidersBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          soliders_ = java.util.Collections.unmodifiableList(soliders_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.soliders_ = soliders_;
      } else {
        result.soliders_ = solidersBuilder_.build();
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
      if (other instanceof pb4client.TestFightData) {
        return mergeFrom((pb4client.TestFightData)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.TestFightData other) {
      if (other == pb4client.TestFightData.getDefaultInstance()) return this;
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
      if (solidersBuilder_ == null) {
        if (!other.soliders_.isEmpty()) {
          if (soliders_.isEmpty()) {
            soliders_ = other.soliders_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureSolidersIsMutable();
            soliders_.addAll(other.soliders_);
          }
          onChanged();
        }
      } else {
        if (!other.soliders_.isEmpty()) {
          if (solidersBuilder_.isEmpty()) {
            solidersBuilder_.dispose();
            solidersBuilder_ = null;
            soliders_ = other.soliders_;
            bitField0_ = (bitField0_ & ~0x00000002);
            solidersBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getSolidersFieldBuilder() : null;
          } else {
            solidersBuilder_.addAllMessages(other.soliders_);
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
      for (int i = 0; i < getSolidersCount(); i++) {
        if (!getSoliders(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.TestFightData parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.TestFightData) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<pb4client.TestFightHero> heros_ =
      java.util.Collections.emptyList();
    private void ensureHerosIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        heros_ = new java.util.ArrayList<pb4client.TestFightHero>(heros_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.TestFightHero, pb4client.TestFightHero.Builder, pb4client.TestFightHeroOrBuilder> herosBuilder_;

    /**
     * <code>repeated .client2server.TestFightHero heros = 1;</code>
     */
    public java.util.List<pb4client.TestFightHero> getHerosList() {
      if (herosBuilder_ == null) {
        return java.util.Collections.unmodifiableList(heros_);
      } else {
        return herosBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .client2server.TestFightHero heros = 1;</code>
     */
    public int getHerosCount() {
      if (herosBuilder_ == null) {
        return heros_.size();
      } else {
        return herosBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .client2server.TestFightHero heros = 1;</code>
     */
    public pb4client.TestFightHero getHeros(int index) {
      if (herosBuilder_ == null) {
        return heros_.get(index);
      } else {
        return herosBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .client2server.TestFightHero heros = 1;</code>
     */
    public Builder setHeros(
        int index, pb4client.TestFightHero value) {
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
     * <code>repeated .client2server.TestFightHero heros = 1;</code>
     */
    public Builder setHeros(
        int index, pb4client.TestFightHero.Builder builderForValue) {
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
     * <code>repeated .client2server.TestFightHero heros = 1;</code>
     */
    public Builder addHeros(pb4client.TestFightHero value) {
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
     * <code>repeated .client2server.TestFightHero heros = 1;</code>
     */
    public Builder addHeros(
        int index, pb4client.TestFightHero value) {
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
     * <code>repeated .client2server.TestFightHero heros = 1;</code>
     */
    public Builder addHeros(
        pb4client.TestFightHero.Builder builderForValue) {
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
     * <code>repeated .client2server.TestFightHero heros = 1;</code>
     */
    public Builder addHeros(
        int index, pb4client.TestFightHero.Builder builderForValue) {
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
     * <code>repeated .client2server.TestFightHero heros = 1;</code>
     */
    public Builder addAllHeros(
        java.lang.Iterable<? extends pb4client.TestFightHero> values) {
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
     * <code>repeated .client2server.TestFightHero heros = 1;</code>
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
     * <code>repeated .client2server.TestFightHero heros = 1;</code>
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
     * <code>repeated .client2server.TestFightHero heros = 1;</code>
     */
    public pb4client.TestFightHero.Builder getHerosBuilder(
        int index) {
      return getHerosFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .client2server.TestFightHero heros = 1;</code>
     */
    public pb4client.TestFightHeroOrBuilder getHerosOrBuilder(
        int index) {
      if (herosBuilder_ == null) {
        return heros_.get(index);  } else {
        return herosBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .client2server.TestFightHero heros = 1;</code>
     */
    public java.util.List<? extends pb4client.TestFightHeroOrBuilder> 
         getHerosOrBuilderList() {
      if (herosBuilder_ != null) {
        return herosBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(heros_);
      }
    }
    /**
     * <code>repeated .client2server.TestFightHero heros = 1;</code>
     */
    public pb4client.TestFightHero.Builder addHerosBuilder() {
      return getHerosFieldBuilder().addBuilder(
          pb4client.TestFightHero.getDefaultInstance());
    }
    /**
     * <code>repeated .client2server.TestFightHero heros = 1;</code>
     */
    public pb4client.TestFightHero.Builder addHerosBuilder(
        int index) {
      return getHerosFieldBuilder().addBuilder(
          index, pb4client.TestFightHero.getDefaultInstance());
    }
    /**
     * <code>repeated .client2server.TestFightHero heros = 1;</code>
     */
    public java.util.List<pb4client.TestFightHero.Builder> 
         getHerosBuilderList() {
      return getHerosFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.TestFightHero, pb4client.TestFightHero.Builder, pb4client.TestFightHeroOrBuilder> 
        getHerosFieldBuilder() {
      if (herosBuilder_ == null) {
        herosBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.TestFightHero, pb4client.TestFightHero.Builder, pb4client.TestFightHeroOrBuilder>(
                heros_,
                ((bitField0_ & 0x00000001) == 0x00000001),
                getParentForChildren(),
                isClean());
        heros_ = null;
      }
      return herosBuilder_;
    }

    private java.util.List<pb4client.TestFightSolider> soliders_ =
      java.util.Collections.emptyList();
    private void ensureSolidersIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        soliders_ = new java.util.ArrayList<pb4client.TestFightSolider>(soliders_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.TestFightSolider, pb4client.TestFightSolider.Builder, pb4client.TestFightSoliderOrBuilder> solidersBuilder_;

    /**
     * <code>repeated .client2server.TestFightSolider soliders = 2;</code>
     */
    public java.util.List<pb4client.TestFightSolider> getSolidersList() {
      if (solidersBuilder_ == null) {
        return java.util.Collections.unmodifiableList(soliders_);
      } else {
        return solidersBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .client2server.TestFightSolider soliders = 2;</code>
     */
    public int getSolidersCount() {
      if (solidersBuilder_ == null) {
        return soliders_.size();
      } else {
        return solidersBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .client2server.TestFightSolider soliders = 2;</code>
     */
    public pb4client.TestFightSolider getSoliders(int index) {
      if (solidersBuilder_ == null) {
        return soliders_.get(index);
      } else {
        return solidersBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .client2server.TestFightSolider soliders = 2;</code>
     */
    public Builder setSoliders(
        int index, pb4client.TestFightSolider value) {
      if (solidersBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureSolidersIsMutable();
        soliders_.set(index, value);
        onChanged();
      } else {
        solidersBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.TestFightSolider soliders = 2;</code>
     */
    public Builder setSoliders(
        int index, pb4client.TestFightSolider.Builder builderForValue) {
      if (solidersBuilder_ == null) {
        ensureSolidersIsMutable();
        soliders_.set(index, builderForValue.build());
        onChanged();
      } else {
        solidersBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.TestFightSolider soliders = 2;</code>
     */
    public Builder addSoliders(pb4client.TestFightSolider value) {
      if (solidersBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureSolidersIsMutable();
        soliders_.add(value);
        onChanged();
      } else {
        solidersBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.TestFightSolider soliders = 2;</code>
     */
    public Builder addSoliders(
        int index, pb4client.TestFightSolider value) {
      if (solidersBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureSolidersIsMutable();
        soliders_.add(index, value);
        onChanged();
      } else {
        solidersBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.TestFightSolider soliders = 2;</code>
     */
    public Builder addSoliders(
        pb4client.TestFightSolider.Builder builderForValue) {
      if (solidersBuilder_ == null) {
        ensureSolidersIsMutable();
        soliders_.add(builderForValue.build());
        onChanged();
      } else {
        solidersBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.TestFightSolider soliders = 2;</code>
     */
    public Builder addSoliders(
        int index, pb4client.TestFightSolider.Builder builderForValue) {
      if (solidersBuilder_ == null) {
        ensureSolidersIsMutable();
        soliders_.add(index, builderForValue.build());
        onChanged();
      } else {
        solidersBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .client2server.TestFightSolider soliders = 2;</code>
     */
    public Builder addAllSoliders(
        java.lang.Iterable<? extends pb4client.TestFightSolider> values) {
      if (solidersBuilder_ == null) {
        ensureSolidersIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, soliders_);
        onChanged();
      } else {
        solidersBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.TestFightSolider soliders = 2;</code>
     */
    public Builder clearSoliders() {
      if (solidersBuilder_ == null) {
        soliders_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        solidersBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .client2server.TestFightSolider soliders = 2;</code>
     */
    public Builder removeSoliders(int index) {
      if (solidersBuilder_ == null) {
        ensureSolidersIsMutable();
        soliders_.remove(index);
        onChanged();
      } else {
        solidersBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .client2server.TestFightSolider soliders = 2;</code>
     */
    public pb4client.TestFightSolider.Builder getSolidersBuilder(
        int index) {
      return getSolidersFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .client2server.TestFightSolider soliders = 2;</code>
     */
    public pb4client.TestFightSoliderOrBuilder getSolidersOrBuilder(
        int index) {
      if (solidersBuilder_ == null) {
        return soliders_.get(index);  } else {
        return solidersBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .client2server.TestFightSolider soliders = 2;</code>
     */
    public java.util.List<? extends pb4client.TestFightSoliderOrBuilder> 
         getSolidersOrBuilderList() {
      if (solidersBuilder_ != null) {
        return solidersBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(soliders_);
      }
    }
    /**
     * <code>repeated .client2server.TestFightSolider soliders = 2;</code>
     */
    public pb4client.TestFightSolider.Builder addSolidersBuilder() {
      return getSolidersFieldBuilder().addBuilder(
          pb4client.TestFightSolider.getDefaultInstance());
    }
    /**
     * <code>repeated .client2server.TestFightSolider soliders = 2;</code>
     */
    public pb4client.TestFightSolider.Builder addSolidersBuilder(
        int index) {
      return getSolidersFieldBuilder().addBuilder(
          index, pb4client.TestFightSolider.getDefaultInstance());
    }
    /**
     * <code>repeated .client2server.TestFightSolider soliders = 2;</code>
     */
    public java.util.List<pb4client.TestFightSolider.Builder> 
         getSolidersBuilderList() {
      return getSolidersFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.TestFightSolider, pb4client.TestFightSolider.Builder, pb4client.TestFightSoliderOrBuilder> 
        getSolidersFieldBuilder() {
      if (solidersBuilder_ == null) {
        solidersBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.TestFightSolider, pb4client.TestFightSolider.Builder, pb4client.TestFightSoliderOrBuilder>(
                soliders_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        soliders_ = null;
      }
      return solidersBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.TestFightData)
  }

  // @@protoc_insertion_point(class_scope:client2server.TestFightData)
  private static final pb4client.TestFightData DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.TestFightData();
  }

  public static pb4client.TestFightData getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<TestFightData>
      PARSER = new com.google.protobuf.AbstractParser<TestFightData>() {
    public TestFightData parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new TestFightData(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<TestFightData> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<TestFightData> getParserForType() {
    return PARSER;
  }

  public pb4client.TestFightData getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

