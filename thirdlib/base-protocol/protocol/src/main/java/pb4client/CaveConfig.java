// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 35
 * 客户端 -&gt; 服务器
 * 藏兵设置
 * </pre>
 *
 * Protobuf type {@code client2server.CaveConfig}
 */
public  final class CaveConfig extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.CaveConfig)
    CaveConfigOrBuilder {
  // Use CaveConfig.newBuilder() to construct.
  private CaveConfig(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CaveConfig() {
    protoId_ = 0;
    holdKing_ = 0;
    soldiers_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private CaveConfig(
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
            protoId_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            holdKing_ = input.readInt32();
            break;
          }
          case 26: {
            if (!((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
              soldiers_ = new java.util.ArrayList<pb4client.CaveSoldier>();
              mutable_bitField0_ |= 0x00000004;
            }
            soldiers_.add(
                input.readMessage(pb4client.CaveSoldier.PARSER, extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
        soldiers_ = java.util.Collections.unmodifiableList(soldiers_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_CaveConfig_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_CaveConfig_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.CaveConfig.class, pb4client.CaveConfig.Builder.class);
  }

  private int bitField0_;
  public static final int PROTOID_FIELD_NUMBER = 1;
  private int protoId_;
  /**
   * <pre>
   * 藏兵配置的模板ID，主要是时间
   * </pre>
   *
   * <code>required int32 protoId = 1;</code>
   */
  public boolean hasProtoId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 藏兵配置的模板ID，主要是时间
   * </pre>
   *
   * <code>required int32 protoId = 1;</code>
   */
  public int getProtoId() {
    return protoId_;
  }

  public static final int HOLDKING_FIELD_NUMBER = 2;
  private int holdKing_;
  /**
   * <pre>
   * 保存君主，0：不保存，1：保存
   * </pre>
   *
   * <code>required int32 holdKing = 2;</code>
   */
  public boolean hasHoldKing() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 保存君主，0：不保存，1：保存
   * </pre>
   *
   * <code>required int32 holdKing = 2;</code>
   */
  public int getHoldKing() {
    return holdKing_;
  }

  public static final int SOLDIERS_FIELD_NUMBER = 3;
  private java.util.List<pb4client.CaveSoldier> soldiers_;
  /**
   * <pre>
   * 要保存的士兵信息
   * </pre>
   *
   * <code>repeated .client2server.CaveSoldier soldiers = 3;</code>
   */
  public java.util.List<pb4client.CaveSoldier> getSoldiersList() {
    return soldiers_;
  }
  /**
   * <pre>
   * 要保存的士兵信息
   * </pre>
   *
   * <code>repeated .client2server.CaveSoldier soldiers = 3;</code>
   */
  public java.util.List<? extends pb4client.CaveSoldierOrBuilder> 
      getSoldiersOrBuilderList() {
    return soldiers_;
  }
  /**
   * <pre>
   * 要保存的士兵信息
   * </pre>
   *
   * <code>repeated .client2server.CaveSoldier soldiers = 3;</code>
   */
  public int getSoldiersCount() {
    return soldiers_.size();
  }
  /**
   * <pre>
   * 要保存的士兵信息
   * </pre>
   *
   * <code>repeated .client2server.CaveSoldier soldiers = 3;</code>
   */
  public pb4client.CaveSoldier getSoldiers(int index) {
    return soldiers_.get(index);
  }
  /**
   * <pre>
   * 要保存的士兵信息
   * </pre>
   *
   * <code>repeated .client2server.CaveSoldier soldiers = 3;</code>
   */
  public pb4client.CaveSoldierOrBuilder getSoldiersOrBuilder(
      int index) {
    return soldiers_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasProtoId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasHoldKing()) {
      memoizedIsInitialized = 0;
      return false;
    }
    for (int i = 0; i < getSoldiersCount(); i++) {
      if (!getSoldiers(i).isInitialized()) {
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
      output.writeInt32(1, protoId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, holdKing_);
    }
    for (int i = 0; i < soldiers_.size(); i++) {
      output.writeMessage(3, soldiers_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, protoId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, holdKing_);
    }
    for (int i = 0; i < soldiers_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, soldiers_.get(i));
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
    if (!(obj instanceof pb4client.CaveConfig)) {
      return super.equals(obj);
    }
    pb4client.CaveConfig other = (pb4client.CaveConfig) obj;

    boolean result = true;
    result = result && (hasProtoId() == other.hasProtoId());
    if (hasProtoId()) {
      result = result && (getProtoId()
          == other.getProtoId());
    }
    result = result && (hasHoldKing() == other.hasHoldKing());
    if (hasHoldKing()) {
      result = result && (getHoldKing()
          == other.getHoldKing());
    }
    result = result && getSoldiersList()
        .equals(other.getSoldiersList());
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
    if (hasProtoId()) {
      hash = (37 * hash) + PROTOID_FIELD_NUMBER;
      hash = (53 * hash) + getProtoId();
    }
    if (hasHoldKing()) {
      hash = (37 * hash) + HOLDKING_FIELD_NUMBER;
      hash = (53 * hash) + getHoldKing();
    }
    if (getSoldiersCount() > 0) {
      hash = (37 * hash) + SOLDIERS_FIELD_NUMBER;
      hash = (53 * hash) + getSoldiersList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.CaveConfig parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.CaveConfig parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.CaveConfig parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.CaveConfig parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.CaveConfig parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.CaveConfig parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.CaveConfig parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.CaveConfig parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.CaveConfig parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.CaveConfig parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.CaveConfig parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.CaveConfig parseFrom(
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
  public static Builder newBuilder(pb4client.CaveConfig prototype) {
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
   * msgType = 35
   * 客户端 -&gt; 服务器
   * 藏兵设置
   * </pre>
   *
   * Protobuf type {@code client2server.CaveConfig}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.CaveConfig)
      pb4client.CaveConfigOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_CaveConfig_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_CaveConfig_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.CaveConfig.class, pb4client.CaveConfig.Builder.class);
    }

    // Construct using pb4client.CaveConfig.newBuilder()
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
        getSoldiersFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      protoId_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      holdKing_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      if (soldiersBuilder_ == null) {
        soldiers_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
      } else {
        soldiersBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_CaveConfig_descriptor;
    }

    public pb4client.CaveConfig getDefaultInstanceForType() {
      return pb4client.CaveConfig.getDefaultInstance();
    }

    public pb4client.CaveConfig build() {
      pb4client.CaveConfig result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.CaveConfig buildPartial() {
      pb4client.CaveConfig result = new pb4client.CaveConfig(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.protoId_ = protoId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.holdKing_ = holdKing_;
      if (soldiersBuilder_ == null) {
        if (((bitField0_ & 0x00000004) == 0x00000004)) {
          soldiers_ = java.util.Collections.unmodifiableList(soldiers_);
          bitField0_ = (bitField0_ & ~0x00000004);
        }
        result.soldiers_ = soldiers_;
      } else {
        result.soldiers_ = soldiersBuilder_.build();
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
      if (other instanceof pb4client.CaveConfig) {
        return mergeFrom((pb4client.CaveConfig)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.CaveConfig other) {
      if (other == pb4client.CaveConfig.getDefaultInstance()) return this;
      if (other.hasProtoId()) {
        setProtoId(other.getProtoId());
      }
      if (other.hasHoldKing()) {
        setHoldKing(other.getHoldKing());
      }
      if (soldiersBuilder_ == null) {
        if (!other.soldiers_.isEmpty()) {
          if (soldiers_.isEmpty()) {
            soldiers_ = other.soldiers_;
            bitField0_ = (bitField0_ & ~0x00000004);
          } else {
            ensureSoldiersIsMutable();
            soldiers_.addAll(other.soldiers_);
          }
          onChanged();
        }
      } else {
        if (!other.soldiers_.isEmpty()) {
          if (soldiersBuilder_.isEmpty()) {
            soldiersBuilder_.dispose();
            soldiersBuilder_ = null;
            soldiers_ = other.soldiers_;
            bitField0_ = (bitField0_ & ~0x00000004);
            soldiersBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getSoldiersFieldBuilder() : null;
          } else {
            soldiersBuilder_.addAllMessages(other.soldiers_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasProtoId()) {
        return false;
      }
      if (!hasHoldKing()) {
        return false;
      }
      for (int i = 0; i < getSoldiersCount(); i++) {
        if (!getSoldiers(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.CaveConfig parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.CaveConfig) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int protoId_ ;
    /**
     * <pre>
     * 藏兵配置的模板ID，主要是时间
     * </pre>
     *
     * <code>required int32 protoId = 1;</code>
     */
    public boolean hasProtoId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 藏兵配置的模板ID，主要是时间
     * </pre>
     *
     * <code>required int32 protoId = 1;</code>
     */
    public int getProtoId() {
      return protoId_;
    }
    /**
     * <pre>
     * 藏兵配置的模板ID，主要是时间
     * </pre>
     *
     * <code>required int32 protoId = 1;</code>
     */
    public Builder setProtoId(int value) {
      bitField0_ |= 0x00000001;
      protoId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 藏兵配置的模板ID，主要是时间
     * </pre>
     *
     * <code>required int32 protoId = 1;</code>
     */
    public Builder clearProtoId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      protoId_ = 0;
      onChanged();
      return this;
    }

    private int holdKing_ ;
    /**
     * <pre>
     * 保存君主，0：不保存，1：保存
     * </pre>
     *
     * <code>required int32 holdKing = 2;</code>
     */
    public boolean hasHoldKing() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 保存君主，0：不保存，1：保存
     * </pre>
     *
     * <code>required int32 holdKing = 2;</code>
     */
    public int getHoldKing() {
      return holdKing_;
    }
    /**
     * <pre>
     * 保存君主，0：不保存，1：保存
     * </pre>
     *
     * <code>required int32 holdKing = 2;</code>
     */
    public Builder setHoldKing(int value) {
      bitField0_ |= 0x00000002;
      holdKing_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 保存君主，0：不保存，1：保存
     * </pre>
     *
     * <code>required int32 holdKing = 2;</code>
     */
    public Builder clearHoldKing() {
      bitField0_ = (bitField0_ & ~0x00000002);
      holdKing_ = 0;
      onChanged();
      return this;
    }

    private java.util.List<pb4client.CaveSoldier> soldiers_ =
      java.util.Collections.emptyList();
    private void ensureSoldiersIsMutable() {
      if (!((bitField0_ & 0x00000004) == 0x00000004)) {
        soldiers_ = new java.util.ArrayList<pb4client.CaveSoldier>(soldiers_);
        bitField0_ |= 0x00000004;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.CaveSoldier, pb4client.CaveSoldier.Builder, pb4client.CaveSoldierOrBuilder> soldiersBuilder_;

    /**
     * <pre>
     * 要保存的士兵信息
     * </pre>
     *
     * <code>repeated .client2server.CaveSoldier soldiers = 3;</code>
     */
    public java.util.List<pb4client.CaveSoldier> getSoldiersList() {
      if (soldiersBuilder_ == null) {
        return java.util.Collections.unmodifiableList(soldiers_);
      } else {
        return soldiersBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     * 要保存的士兵信息
     * </pre>
     *
     * <code>repeated .client2server.CaveSoldier soldiers = 3;</code>
     */
    public int getSoldiersCount() {
      if (soldiersBuilder_ == null) {
        return soldiers_.size();
      } else {
        return soldiersBuilder_.getCount();
      }
    }
    /**
     * <pre>
     * 要保存的士兵信息
     * </pre>
     *
     * <code>repeated .client2server.CaveSoldier soldiers = 3;</code>
     */
    public pb4client.CaveSoldier getSoldiers(int index) {
      if (soldiersBuilder_ == null) {
        return soldiers_.get(index);
      } else {
        return soldiersBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     * 要保存的士兵信息
     * </pre>
     *
     * <code>repeated .client2server.CaveSoldier soldiers = 3;</code>
     */
    public Builder setSoldiers(
        int index, pb4client.CaveSoldier value) {
      if (soldiersBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureSoldiersIsMutable();
        soldiers_.set(index, value);
        onChanged();
      } else {
        soldiersBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * 要保存的士兵信息
     * </pre>
     *
     * <code>repeated .client2server.CaveSoldier soldiers = 3;</code>
     */
    public Builder setSoldiers(
        int index, pb4client.CaveSoldier.Builder builderForValue) {
      if (soldiersBuilder_ == null) {
        ensureSoldiersIsMutable();
        soldiers_.set(index, builderForValue.build());
        onChanged();
      } else {
        soldiersBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 要保存的士兵信息
     * </pre>
     *
     * <code>repeated .client2server.CaveSoldier soldiers = 3;</code>
     */
    public Builder addSoldiers(pb4client.CaveSoldier value) {
      if (soldiersBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureSoldiersIsMutable();
        soldiers_.add(value);
        onChanged();
      } else {
        soldiersBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     * 要保存的士兵信息
     * </pre>
     *
     * <code>repeated .client2server.CaveSoldier soldiers = 3;</code>
     */
    public Builder addSoldiers(
        int index, pb4client.CaveSoldier value) {
      if (soldiersBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureSoldiersIsMutable();
        soldiers_.add(index, value);
        onChanged();
      } else {
        soldiersBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * 要保存的士兵信息
     * </pre>
     *
     * <code>repeated .client2server.CaveSoldier soldiers = 3;</code>
     */
    public Builder addSoldiers(
        pb4client.CaveSoldier.Builder builderForValue) {
      if (soldiersBuilder_ == null) {
        ensureSoldiersIsMutable();
        soldiers_.add(builderForValue.build());
        onChanged();
      } else {
        soldiersBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 要保存的士兵信息
     * </pre>
     *
     * <code>repeated .client2server.CaveSoldier soldiers = 3;</code>
     */
    public Builder addSoldiers(
        int index, pb4client.CaveSoldier.Builder builderForValue) {
      if (soldiersBuilder_ == null) {
        ensureSoldiersIsMutable();
        soldiers_.add(index, builderForValue.build());
        onChanged();
      } else {
        soldiersBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 要保存的士兵信息
     * </pre>
     *
     * <code>repeated .client2server.CaveSoldier soldiers = 3;</code>
     */
    public Builder addAllSoldiers(
        java.lang.Iterable<? extends pb4client.CaveSoldier> values) {
      if (soldiersBuilder_ == null) {
        ensureSoldiersIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, soldiers_);
        onChanged();
      } else {
        soldiersBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     * 要保存的士兵信息
     * </pre>
     *
     * <code>repeated .client2server.CaveSoldier soldiers = 3;</code>
     */
    public Builder clearSoldiers() {
      if (soldiersBuilder_ == null) {
        soldiers_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
        onChanged();
      } else {
        soldiersBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     * 要保存的士兵信息
     * </pre>
     *
     * <code>repeated .client2server.CaveSoldier soldiers = 3;</code>
     */
    public Builder removeSoldiers(int index) {
      if (soldiersBuilder_ == null) {
        ensureSoldiersIsMutable();
        soldiers_.remove(index);
        onChanged();
      } else {
        soldiersBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     * 要保存的士兵信息
     * </pre>
     *
     * <code>repeated .client2server.CaveSoldier soldiers = 3;</code>
     */
    public pb4client.CaveSoldier.Builder getSoldiersBuilder(
        int index) {
      return getSoldiersFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     * 要保存的士兵信息
     * </pre>
     *
     * <code>repeated .client2server.CaveSoldier soldiers = 3;</code>
     */
    public pb4client.CaveSoldierOrBuilder getSoldiersOrBuilder(
        int index) {
      if (soldiersBuilder_ == null) {
        return soldiers_.get(index);  } else {
        return soldiersBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     * 要保存的士兵信息
     * </pre>
     *
     * <code>repeated .client2server.CaveSoldier soldiers = 3;</code>
     */
    public java.util.List<? extends pb4client.CaveSoldierOrBuilder> 
         getSoldiersOrBuilderList() {
      if (soldiersBuilder_ != null) {
        return soldiersBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(soldiers_);
      }
    }
    /**
     * <pre>
     * 要保存的士兵信息
     * </pre>
     *
     * <code>repeated .client2server.CaveSoldier soldiers = 3;</code>
     */
    public pb4client.CaveSoldier.Builder addSoldiersBuilder() {
      return getSoldiersFieldBuilder().addBuilder(
          pb4client.CaveSoldier.getDefaultInstance());
    }
    /**
     * <pre>
     * 要保存的士兵信息
     * </pre>
     *
     * <code>repeated .client2server.CaveSoldier soldiers = 3;</code>
     */
    public pb4client.CaveSoldier.Builder addSoldiersBuilder(
        int index) {
      return getSoldiersFieldBuilder().addBuilder(
          index, pb4client.CaveSoldier.getDefaultInstance());
    }
    /**
     * <pre>
     * 要保存的士兵信息
     * </pre>
     *
     * <code>repeated .client2server.CaveSoldier soldiers = 3;</code>
     */
    public java.util.List<pb4client.CaveSoldier.Builder> 
         getSoldiersBuilderList() {
      return getSoldiersFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.CaveSoldier, pb4client.CaveSoldier.Builder, pb4client.CaveSoldierOrBuilder> 
        getSoldiersFieldBuilder() {
      if (soldiersBuilder_ == null) {
        soldiersBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.CaveSoldier, pb4client.CaveSoldier.Builder, pb4client.CaveSoldierOrBuilder>(
                soldiers_,
                ((bitField0_ & 0x00000004) == 0x00000004),
                getParentForChildren(),
                isClean());
        soldiers_ = null;
      }
      return soldiersBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.CaveConfig)
  }

  // @@protoc_insertion_point(class_scope:client2server.CaveConfig)
  private static final pb4client.CaveConfig DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.CaveConfig();
  }

  public static pb4client.CaveConfig getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<CaveConfig>
      PARSER = new com.google.protobuf.AbstractParser<CaveConfig>() {
    public CaveConfig parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new CaveConfig(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<CaveConfig> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CaveConfig> getParserForType() {
    return PARSER;
  }

  public pb4client.CaveConfig getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

