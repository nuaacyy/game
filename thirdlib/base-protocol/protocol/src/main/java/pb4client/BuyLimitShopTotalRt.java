// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.BuyLimitShopTotalRt}
 */
public  final class BuyLimitShopTotalRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.BuyLimitShopTotalRt)
    BuyLimitShopTotalRtOrBuilder {
  // Use BuyLimitShopTotalRt.newBuilder() to construct.
  private BuyLimitShopTotalRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private BuyLimitShopTotalRt() {
    rt_ = 0;
    limitGoodsVos_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private BuyLimitShopTotalRt(
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
              limitGoodsVos_ = new java.util.ArrayList<pb4client.LimitGoodsVo>();
              mutable_bitField0_ |= 0x00000002;
            }
            limitGoodsVos_.add(
                input.readMessage(pb4client.LimitGoodsVo.PARSER, extensionRegistry));
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
        limitGoodsVos_ = java.util.Collections.unmodifiableList(limitGoodsVos_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_BuyLimitShopTotalRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_BuyLimitShopTotalRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.BuyLimitShopTotalRt.class, pb4client.BuyLimitShopTotalRt.Builder.class);
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

  public static final int LIMITGOODSVOS_FIELD_NUMBER = 2;
  private java.util.List<pb4client.LimitGoodsVo> limitGoodsVos_;
  /**
   * <pre>
   * </pre>
   *
   * <code>repeated .client2server.LimitGoodsVo LimitGoodsVos = 2;</code>
   */
  public java.util.List<pb4client.LimitGoodsVo> getLimitGoodsVosList() {
    return limitGoodsVos_;
  }
  /**
   * <pre>
   * </pre>
   *
   * <code>repeated .client2server.LimitGoodsVo LimitGoodsVos = 2;</code>
   */
  public java.util.List<? extends pb4client.LimitGoodsVoOrBuilder> 
      getLimitGoodsVosOrBuilderList() {
    return limitGoodsVos_;
  }
  /**
   * <pre>
   * </pre>
   *
   * <code>repeated .client2server.LimitGoodsVo LimitGoodsVos = 2;</code>
   */
  public int getLimitGoodsVosCount() {
    return limitGoodsVos_.size();
  }
  /**
   * <pre>
   * </pre>
   *
   * <code>repeated .client2server.LimitGoodsVo LimitGoodsVos = 2;</code>
   */
  public pb4client.LimitGoodsVo getLimitGoodsVos(int index) {
    return limitGoodsVos_.get(index);
  }
  /**
   * <pre>
   * </pre>
   *
   * <code>repeated .client2server.LimitGoodsVo LimitGoodsVos = 2;</code>
   */
  public pb4client.LimitGoodsVoOrBuilder getLimitGoodsVosOrBuilder(
      int index) {
    return limitGoodsVos_.get(index);
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
    for (int i = 0; i < getLimitGoodsVosCount(); i++) {
      if (!getLimitGoodsVos(i).isInitialized()) {
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
    for (int i = 0; i < limitGoodsVos_.size(); i++) {
      output.writeMessage(2, limitGoodsVos_.get(i));
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
    for (int i = 0; i < limitGoodsVos_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, limitGoodsVos_.get(i));
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
    if (!(obj instanceof pb4client.BuyLimitShopTotalRt)) {
      return super.equals(obj);
    }
    pb4client.BuyLimitShopTotalRt other = (pb4client.BuyLimitShopTotalRt) obj;

    boolean result = true;
    result = result && (hasRt() == other.hasRt());
    if (hasRt()) {
      result = result && (getRt()
          == other.getRt());
    }
    result = result && getLimitGoodsVosList()
        .equals(other.getLimitGoodsVosList());
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
    if (getLimitGoodsVosCount() > 0) {
      hash = (37 * hash) + LIMITGOODSVOS_FIELD_NUMBER;
      hash = (53 * hash) + getLimitGoodsVosList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.BuyLimitShopTotalRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.BuyLimitShopTotalRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.BuyLimitShopTotalRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.BuyLimitShopTotalRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.BuyLimitShopTotalRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.BuyLimitShopTotalRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.BuyLimitShopTotalRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.BuyLimitShopTotalRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.BuyLimitShopTotalRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.BuyLimitShopTotalRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.BuyLimitShopTotalRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.BuyLimitShopTotalRt parseFrom(
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
  public static Builder newBuilder(pb4client.BuyLimitShopTotalRt prototype) {
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
   * Protobuf type {@code client2server.BuyLimitShopTotalRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.BuyLimitShopTotalRt)
      pb4client.BuyLimitShopTotalRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_BuyLimitShopTotalRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_BuyLimitShopTotalRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.BuyLimitShopTotalRt.class, pb4client.BuyLimitShopTotalRt.Builder.class);
    }

    // Construct using pb4client.BuyLimitShopTotalRt.newBuilder()
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
        getLimitGoodsVosFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      rt_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      if (limitGoodsVosBuilder_ == null) {
        limitGoodsVos_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
      } else {
        limitGoodsVosBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_BuyLimitShopTotalRt_descriptor;
    }

    public pb4client.BuyLimitShopTotalRt getDefaultInstanceForType() {
      return pb4client.BuyLimitShopTotalRt.getDefaultInstance();
    }

    public pb4client.BuyLimitShopTotalRt build() {
      pb4client.BuyLimitShopTotalRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.BuyLimitShopTotalRt buildPartial() {
      pb4client.BuyLimitShopTotalRt result = new pb4client.BuyLimitShopTotalRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.rt_ = rt_;
      if (limitGoodsVosBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          limitGoodsVos_ = java.util.Collections.unmodifiableList(limitGoodsVos_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.limitGoodsVos_ = limitGoodsVos_;
      } else {
        result.limitGoodsVos_ = limitGoodsVosBuilder_.build();
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
      if (other instanceof pb4client.BuyLimitShopTotalRt) {
        return mergeFrom((pb4client.BuyLimitShopTotalRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.BuyLimitShopTotalRt other) {
      if (other == pb4client.BuyLimitShopTotalRt.getDefaultInstance()) return this;
      if (other.hasRt()) {
        setRt(other.getRt());
      }
      if (limitGoodsVosBuilder_ == null) {
        if (!other.limitGoodsVos_.isEmpty()) {
          if (limitGoodsVos_.isEmpty()) {
            limitGoodsVos_ = other.limitGoodsVos_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureLimitGoodsVosIsMutable();
            limitGoodsVos_.addAll(other.limitGoodsVos_);
          }
          onChanged();
        }
      } else {
        if (!other.limitGoodsVos_.isEmpty()) {
          if (limitGoodsVosBuilder_.isEmpty()) {
            limitGoodsVosBuilder_.dispose();
            limitGoodsVosBuilder_ = null;
            limitGoodsVos_ = other.limitGoodsVos_;
            bitField0_ = (bitField0_ & ~0x00000002);
            limitGoodsVosBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getLimitGoodsVosFieldBuilder() : null;
          } else {
            limitGoodsVosBuilder_.addAllMessages(other.limitGoodsVos_);
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
      for (int i = 0; i < getLimitGoodsVosCount(); i++) {
        if (!getLimitGoodsVos(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.BuyLimitShopTotalRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.BuyLimitShopTotalRt) e.getUnfinishedMessage();
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

    private java.util.List<pb4client.LimitGoodsVo> limitGoodsVos_ =
      java.util.Collections.emptyList();
    private void ensureLimitGoodsVosIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        limitGoodsVos_ = new java.util.ArrayList<pb4client.LimitGoodsVo>(limitGoodsVos_);
        bitField0_ |= 0x00000002;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.LimitGoodsVo, pb4client.LimitGoodsVo.Builder, pb4client.LimitGoodsVoOrBuilder> limitGoodsVosBuilder_;

    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.LimitGoodsVo LimitGoodsVos = 2;</code>
     */
    public java.util.List<pb4client.LimitGoodsVo> getLimitGoodsVosList() {
      if (limitGoodsVosBuilder_ == null) {
        return java.util.Collections.unmodifiableList(limitGoodsVos_);
      } else {
        return limitGoodsVosBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.LimitGoodsVo LimitGoodsVos = 2;</code>
     */
    public int getLimitGoodsVosCount() {
      if (limitGoodsVosBuilder_ == null) {
        return limitGoodsVos_.size();
      } else {
        return limitGoodsVosBuilder_.getCount();
      }
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.LimitGoodsVo LimitGoodsVos = 2;</code>
     */
    public pb4client.LimitGoodsVo getLimitGoodsVos(int index) {
      if (limitGoodsVosBuilder_ == null) {
        return limitGoodsVos_.get(index);
      } else {
        return limitGoodsVosBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.LimitGoodsVo LimitGoodsVos = 2;</code>
     */
    public Builder setLimitGoodsVos(
        int index, pb4client.LimitGoodsVo value) {
      if (limitGoodsVosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureLimitGoodsVosIsMutable();
        limitGoodsVos_.set(index, value);
        onChanged();
      } else {
        limitGoodsVosBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.LimitGoodsVo LimitGoodsVos = 2;</code>
     */
    public Builder setLimitGoodsVos(
        int index, pb4client.LimitGoodsVo.Builder builderForValue) {
      if (limitGoodsVosBuilder_ == null) {
        ensureLimitGoodsVosIsMutable();
        limitGoodsVos_.set(index, builderForValue.build());
        onChanged();
      } else {
        limitGoodsVosBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.LimitGoodsVo LimitGoodsVos = 2;</code>
     */
    public Builder addLimitGoodsVos(pb4client.LimitGoodsVo value) {
      if (limitGoodsVosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureLimitGoodsVosIsMutable();
        limitGoodsVos_.add(value);
        onChanged();
      } else {
        limitGoodsVosBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.LimitGoodsVo LimitGoodsVos = 2;</code>
     */
    public Builder addLimitGoodsVos(
        int index, pb4client.LimitGoodsVo value) {
      if (limitGoodsVosBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureLimitGoodsVosIsMutable();
        limitGoodsVos_.add(index, value);
        onChanged();
      } else {
        limitGoodsVosBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.LimitGoodsVo LimitGoodsVos = 2;</code>
     */
    public Builder addLimitGoodsVos(
        pb4client.LimitGoodsVo.Builder builderForValue) {
      if (limitGoodsVosBuilder_ == null) {
        ensureLimitGoodsVosIsMutable();
        limitGoodsVos_.add(builderForValue.build());
        onChanged();
      } else {
        limitGoodsVosBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.LimitGoodsVo LimitGoodsVos = 2;</code>
     */
    public Builder addLimitGoodsVos(
        int index, pb4client.LimitGoodsVo.Builder builderForValue) {
      if (limitGoodsVosBuilder_ == null) {
        ensureLimitGoodsVosIsMutable();
        limitGoodsVos_.add(index, builderForValue.build());
        onChanged();
      } else {
        limitGoodsVosBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.LimitGoodsVo LimitGoodsVos = 2;</code>
     */
    public Builder addAllLimitGoodsVos(
        java.lang.Iterable<? extends pb4client.LimitGoodsVo> values) {
      if (limitGoodsVosBuilder_ == null) {
        ensureLimitGoodsVosIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, limitGoodsVos_);
        onChanged();
      } else {
        limitGoodsVosBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.LimitGoodsVo LimitGoodsVos = 2;</code>
     */
    public Builder clearLimitGoodsVos() {
      if (limitGoodsVosBuilder_ == null) {
        limitGoodsVos_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        limitGoodsVosBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.LimitGoodsVo LimitGoodsVos = 2;</code>
     */
    public Builder removeLimitGoodsVos(int index) {
      if (limitGoodsVosBuilder_ == null) {
        ensureLimitGoodsVosIsMutable();
        limitGoodsVos_.remove(index);
        onChanged();
      } else {
        limitGoodsVosBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.LimitGoodsVo LimitGoodsVos = 2;</code>
     */
    public pb4client.LimitGoodsVo.Builder getLimitGoodsVosBuilder(
        int index) {
      return getLimitGoodsVosFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.LimitGoodsVo LimitGoodsVos = 2;</code>
     */
    public pb4client.LimitGoodsVoOrBuilder getLimitGoodsVosOrBuilder(
        int index) {
      if (limitGoodsVosBuilder_ == null) {
        return limitGoodsVos_.get(index);  } else {
        return limitGoodsVosBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.LimitGoodsVo LimitGoodsVos = 2;</code>
     */
    public java.util.List<? extends pb4client.LimitGoodsVoOrBuilder> 
         getLimitGoodsVosOrBuilderList() {
      if (limitGoodsVosBuilder_ != null) {
        return limitGoodsVosBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(limitGoodsVos_);
      }
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.LimitGoodsVo LimitGoodsVos = 2;</code>
     */
    public pb4client.LimitGoodsVo.Builder addLimitGoodsVosBuilder() {
      return getLimitGoodsVosFieldBuilder().addBuilder(
          pb4client.LimitGoodsVo.getDefaultInstance());
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.LimitGoodsVo LimitGoodsVos = 2;</code>
     */
    public pb4client.LimitGoodsVo.Builder addLimitGoodsVosBuilder(
        int index) {
      return getLimitGoodsVosFieldBuilder().addBuilder(
          index, pb4client.LimitGoodsVo.getDefaultInstance());
    }
    /**
     * <pre>
     * </pre>
     *
     * <code>repeated .client2server.LimitGoodsVo LimitGoodsVos = 2;</code>
     */
    public java.util.List<pb4client.LimitGoodsVo.Builder> 
         getLimitGoodsVosBuilderList() {
      return getLimitGoodsVosFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.LimitGoodsVo, pb4client.LimitGoodsVo.Builder, pb4client.LimitGoodsVoOrBuilder> 
        getLimitGoodsVosFieldBuilder() {
      if (limitGoodsVosBuilder_ == null) {
        limitGoodsVosBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.LimitGoodsVo, pb4client.LimitGoodsVo.Builder, pb4client.LimitGoodsVoOrBuilder>(
                limitGoodsVos_,
                ((bitField0_ & 0x00000002) == 0x00000002),
                getParentForChildren(),
                isClean());
        limitGoodsVos_ = null;
      }
      return limitGoodsVosBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.BuyLimitShopTotalRt)
  }

  // @@protoc_insertion_point(class_scope:client2server.BuyLimitShopTotalRt)
  private static final pb4client.BuyLimitShopTotalRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.BuyLimitShopTotalRt();
  }

  public static pb4client.BuyLimitShopTotalRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<BuyLimitShopTotalRt>
      PARSER = new com.google.protobuf.AbstractParser<BuyLimitShopTotalRt>() {
    public BuyLimitShopTotalRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new BuyLimitShopTotalRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<BuyLimitShopTotalRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<BuyLimitShopTotalRt> getParserForType() {
    return PARSER;
  }

  public pb4client.BuyLimitShopTotalRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
