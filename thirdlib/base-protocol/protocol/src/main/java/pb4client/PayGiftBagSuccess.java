// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 3359
 * 服务器 -&gt; 客户端
 * 推送客户端购买礼包成功
 * </pre>
 *
 * Protobuf type {@code client2server.PayGiftBagSuccess}
 */
public  final class PayGiftBagSuccess extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.PayGiftBagSuccess)
    PayGiftBagSuccessOrBuilder {
  // Use PayGiftBagSuccess.newBuilder() to construct.
  private PayGiftBagSuccess(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private PayGiftBagSuccess() {
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private PayGiftBagSuccess(
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
            pb4client.GiftBagInfo.Builder subBuilder = null;
            if (((bitField0_ & 0x00000001) == 0x00000001)) {
              subBuilder = giftBagInfo_.toBuilder();
            }
            giftBagInfo_ = input.readMessage(pb4client.GiftBagInfo.PARSER, extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(giftBagInfo_);
              giftBagInfo_ = subBuilder.buildPartial();
            }
            bitField0_ |= 0x00000001;
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
    return pb4client.War2GamePkt.internal_static_client2server_PayGiftBagSuccess_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_PayGiftBagSuccess_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.PayGiftBagSuccess.class, pb4client.PayGiftBagSuccess.Builder.class);
  }

  private int bitField0_;
  public static final int GIFTBAGINFO_FIELD_NUMBER = 1;
  private pb4client.GiftBagInfo giftBagInfo_;
  /**
   * <pre>
   * 礼包信息
   * </pre>
   *
   * <code>required .client2server.GiftBagInfo giftBagInfo = 1;</code>
   */
  public boolean hasGiftBagInfo() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 礼包信息
   * </pre>
   *
   * <code>required .client2server.GiftBagInfo giftBagInfo = 1;</code>
   */
  public pb4client.GiftBagInfo getGiftBagInfo() {
    return giftBagInfo_ == null ? pb4client.GiftBagInfo.getDefaultInstance() : giftBagInfo_;
  }
  /**
   * <pre>
   * 礼包信息
   * </pre>
   *
   * <code>required .client2server.GiftBagInfo giftBagInfo = 1;</code>
   */
  public pb4client.GiftBagInfoOrBuilder getGiftBagInfoOrBuilder() {
    return giftBagInfo_ == null ? pb4client.GiftBagInfo.getDefaultInstance() : giftBagInfo_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasGiftBagInfo()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!getGiftBagInfo().isInitialized()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeMessage(1, getGiftBagInfo());
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getGiftBagInfo());
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
    if (!(obj instanceof pb4client.PayGiftBagSuccess)) {
      return super.equals(obj);
    }
    pb4client.PayGiftBagSuccess other = (pb4client.PayGiftBagSuccess) obj;

    boolean result = true;
    result = result && (hasGiftBagInfo() == other.hasGiftBagInfo());
    if (hasGiftBagInfo()) {
      result = result && getGiftBagInfo()
          .equals(other.getGiftBagInfo());
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
    if (hasGiftBagInfo()) {
      hash = (37 * hash) + GIFTBAGINFO_FIELD_NUMBER;
      hash = (53 * hash) + getGiftBagInfo().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.PayGiftBagSuccess parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.PayGiftBagSuccess parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.PayGiftBagSuccess parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.PayGiftBagSuccess parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.PayGiftBagSuccess parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.PayGiftBagSuccess parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.PayGiftBagSuccess parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.PayGiftBagSuccess parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.PayGiftBagSuccess parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.PayGiftBagSuccess parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.PayGiftBagSuccess parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.PayGiftBagSuccess parseFrom(
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
  public static Builder newBuilder(pb4client.PayGiftBagSuccess prototype) {
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
   * msgType = 3359
   * 服务器 -&gt; 客户端
   * 推送客户端购买礼包成功
   * </pre>
   *
   * Protobuf type {@code client2server.PayGiftBagSuccess}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.PayGiftBagSuccess)
      pb4client.PayGiftBagSuccessOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_PayGiftBagSuccess_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_PayGiftBagSuccess_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.PayGiftBagSuccess.class, pb4client.PayGiftBagSuccess.Builder.class);
    }

    // Construct using pb4client.PayGiftBagSuccess.newBuilder()
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
        getGiftBagInfoFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (giftBagInfoBuilder_ == null) {
        giftBagInfo_ = null;
      } else {
        giftBagInfoBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_PayGiftBagSuccess_descriptor;
    }

    public pb4client.PayGiftBagSuccess getDefaultInstanceForType() {
      return pb4client.PayGiftBagSuccess.getDefaultInstance();
    }

    public pb4client.PayGiftBagSuccess build() {
      pb4client.PayGiftBagSuccess result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.PayGiftBagSuccess buildPartial() {
      pb4client.PayGiftBagSuccess result = new pb4client.PayGiftBagSuccess(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      if (giftBagInfoBuilder_ == null) {
        result.giftBagInfo_ = giftBagInfo_;
      } else {
        result.giftBagInfo_ = giftBagInfoBuilder_.build();
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
      if (other instanceof pb4client.PayGiftBagSuccess) {
        return mergeFrom((pb4client.PayGiftBagSuccess)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.PayGiftBagSuccess other) {
      if (other == pb4client.PayGiftBagSuccess.getDefaultInstance()) return this;
      if (other.hasGiftBagInfo()) {
        mergeGiftBagInfo(other.getGiftBagInfo());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasGiftBagInfo()) {
        return false;
      }
      if (!getGiftBagInfo().isInitialized()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.PayGiftBagSuccess parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.PayGiftBagSuccess) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private pb4client.GiftBagInfo giftBagInfo_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4client.GiftBagInfo, pb4client.GiftBagInfo.Builder, pb4client.GiftBagInfoOrBuilder> giftBagInfoBuilder_;
    /**
     * <pre>
     * 礼包信息
     * </pre>
     *
     * <code>required .client2server.GiftBagInfo giftBagInfo = 1;</code>
     */
    public boolean hasGiftBagInfo() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 礼包信息
     * </pre>
     *
     * <code>required .client2server.GiftBagInfo giftBagInfo = 1;</code>
     */
    public pb4client.GiftBagInfo getGiftBagInfo() {
      if (giftBagInfoBuilder_ == null) {
        return giftBagInfo_ == null ? pb4client.GiftBagInfo.getDefaultInstance() : giftBagInfo_;
      } else {
        return giftBagInfoBuilder_.getMessage();
      }
    }
    /**
     * <pre>
     * 礼包信息
     * </pre>
     *
     * <code>required .client2server.GiftBagInfo giftBagInfo = 1;</code>
     */
    public Builder setGiftBagInfo(pb4client.GiftBagInfo value) {
      if (giftBagInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        giftBagInfo_ = value;
        onChanged();
      } else {
        giftBagInfoBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000001;
      return this;
    }
    /**
     * <pre>
     * 礼包信息
     * </pre>
     *
     * <code>required .client2server.GiftBagInfo giftBagInfo = 1;</code>
     */
    public Builder setGiftBagInfo(
        pb4client.GiftBagInfo.Builder builderForValue) {
      if (giftBagInfoBuilder_ == null) {
        giftBagInfo_ = builderForValue.build();
        onChanged();
      } else {
        giftBagInfoBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000001;
      return this;
    }
    /**
     * <pre>
     * 礼包信息
     * </pre>
     *
     * <code>required .client2server.GiftBagInfo giftBagInfo = 1;</code>
     */
    public Builder mergeGiftBagInfo(pb4client.GiftBagInfo value) {
      if (giftBagInfoBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001) &&
            giftBagInfo_ != null &&
            giftBagInfo_ != pb4client.GiftBagInfo.getDefaultInstance()) {
          giftBagInfo_ =
            pb4client.GiftBagInfo.newBuilder(giftBagInfo_).mergeFrom(value).buildPartial();
        } else {
          giftBagInfo_ = value;
        }
        onChanged();
      } else {
        giftBagInfoBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000001;
      return this;
    }
    /**
     * <pre>
     * 礼包信息
     * </pre>
     *
     * <code>required .client2server.GiftBagInfo giftBagInfo = 1;</code>
     */
    public Builder clearGiftBagInfo() {
      if (giftBagInfoBuilder_ == null) {
        giftBagInfo_ = null;
        onChanged();
      } else {
        giftBagInfoBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }
    /**
     * <pre>
     * 礼包信息
     * </pre>
     *
     * <code>required .client2server.GiftBagInfo giftBagInfo = 1;</code>
     */
    public pb4client.GiftBagInfo.Builder getGiftBagInfoBuilder() {
      bitField0_ |= 0x00000001;
      onChanged();
      return getGiftBagInfoFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     * 礼包信息
     * </pre>
     *
     * <code>required .client2server.GiftBagInfo giftBagInfo = 1;</code>
     */
    public pb4client.GiftBagInfoOrBuilder getGiftBagInfoOrBuilder() {
      if (giftBagInfoBuilder_ != null) {
        return giftBagInfoBuilder_.getMessageOrBuilder();
      } else {
        return giftBagInfo_ == null ?
            pb4client.GiftBagInfo.getDefaultInstance() : giftBagInfo_;
      }
    }
    /**
     * <pre>
     * 礼包信息
     * </pre>
     *
     * <code>required .client2server.GiftBagInfo giftBagInfo = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4client.GiftBagInfo, pb4client.GiftBagInfo.Builder, pb4client.GiftBagInfoOrBuilder> 
        getGiftBagInfoFieldBuilder() {
      if (giftBagInfoBuilder_ == null) {
        giftBagInfoBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            pb4client.GiftBagInfo, pb4client.GiftBagInfo.Builder, pb4client.GiftBagInfoOrBuilder>(
                getGiftBagInfo(),
                getParentForChildren(),
                isClean());
        giftBagInfo_ = null;
      }
      return giftBagInfoBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.PayGiftBagSuccess)
  }

  // @@protoc_insertion_point(class_scope:client2server.PayGiftBagSuccess)
  private static final pb4client.PayGiftBagSuccess DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.PayGiftBagSuccess();
  }

  public static pb4client.PayGiftBagSuccess getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<PayGiftBagSuccess>
      PARSER = new com.google.protobuf.AbstractParser<PayGiftBagSuccess>() {
    public PayGiftBagSuccess parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new PayGiftBagSuccess(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<PayGiftBagSuccess> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<PayGiftBagSuccess> getParserForType() {
    return PARSER;
  }

  public pb4client.PayGiftBagSuccess getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

