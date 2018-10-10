// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.QueryQuotaBagRt}
 */
public  final class QueryQuotaBagRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.QueryQuotaBagRt)
    QueryQuotaBagRtOrBuilder {
  // Use QueryQuotaBagRt.newBuilder() to construct.
  private QueryQuotaBagRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private QueryQuotaBagRt() {
    rt_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private QueryQuotaBagRt(
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
            pb4client.QuotaBagInfo.Builder subBuilder = null;
            if (((bitField0_ & 0x00000002) == 0x00000002)) {
              subBuilder = quotaBagInfo_.toBuilder();
            }
            quotaBagInfo_ = input.readMessage(pb4client.QuotaBagInfo.PARSER, extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(quotaBagInfo_);
              quotaBagInfo_ = subBuilder.buildPartial();
            }
            bitField0_ |= 0x00000002;
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
    return pb4client.War2GamePkt.internal_static_client2server_QueryQuotaBagRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_QueryQuotaBagRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.QueryQuotaBagRt.class, pb4client.QueryQuotaBagRt.Builder.class);
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

  public static final int QUOTABAGINFO_FIELD_NUMBER = 2;
  private pb4client.QuotaBagInfo quotaBagInfo_;
  /**
   * <pre>
   * 满额礼包信息
   * </pre>
   *
   * <code>optional .client2server.QuotaBagInfo quotaBagInfo = 2;</code>
   */
  public boolean hasQuotaBagInfo() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 满额礼包信息
   * </pre>
   *
   * <code>optional .client2server.QuotaBagInfo quotaBagInfo = 2;</code>
   */
  public pb4client.QuotaBagInfo getQuotaBagInfo() {
    return quotaBagInfo_ == null ? pb4client.QuotaBagInfo.getDefaultInstance() : quotaBagInfo_;
  }
  /**
   * <pre>
   * 满额礼包信息
   * </pre>
   *
   * <code>optional .client2server.QuotaBagInfo quotaBagInfo = 2;</code>
   */
  public pb4client.QuotaBagInfoOrBuilder getQuotaBagInfoOrBuilder() {
    return quotaBagInfo_ == null ? pb4client.QuotaBagInfo.getDefaultInstance() : quotaBagInfo_;
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
    if (hasQuotaBagInfo()) {
      if (!getQuotaBagInfo().isInitialized()) {
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
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeMessage(2, getQuotaBagInfo());
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
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getQuotaBagInfo());
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
    if (!(obj instanceof pb4client.QueryQuotaBagRt)) {
      return super.equals(obj);
    }
    pb4client.QueryQuotaBagRt other = (pb4client.QueryQuotaBagRt) obj;

    boolean result = true;
    result = result && (hasRt() == other.hasRt());
    if (hasRt()) {
      result = result && (getRt()
          == other.getRt());
    }
    result = result && (hasQuotaBagInfo() == other.hasQuotaBagInfo());
    if (hasQuotaBagInfo()) {
      result = result && getQuotaBagInfo()
          .equals(other.getQuotaBagInfo());
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
    if (hasRt()) {
      hash = (37 * hash) + RT_FIELD_NUMBER;
      hash = (53 * hash) + getRt();
    }
    if (hasQuotaBagInfo()) {
      hash = (37 * hash) + QUOTABAGINFO_FIELD_NUMBER;
      hash = (53 * hash) + getQuotaBagInfo().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.QueryQuotaBagRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.QueryQuotaBagRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.QueryQuotaBagRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.QueryQuotaBagRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.QueryQuotaBagRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.QueryQuotaBagRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.QueryQuotaBagRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.QueryQuotaBagRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.QueryQuotaBagRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.QueryQuotaBagRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.QueryQuotaBagRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.QueryQuotaBagRt parseFrom(
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
  public static Builder newBuilder(pb4client.QueryQuotaBagRt prototype) {
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
   * Protobuf type {@code client2server.QueryQuotaBagRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.QueryQuotaBagRt)
      pb4client.QueryQuotaBagRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_QueryQuotaBagRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_QueryQuotaBagRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.QueryQuotaBagRt.class, pb4client.QueryQuotaBagRt.Builder.class);
    }

    // Construct using pb4client.QueryQuotaBagRt.newBuilder()
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
        getQuotaBagInfoFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      rt_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      if (quotaBagInfoBuilder_ == null) {
        quotaBagInfo_ = null;
      } else {
        quotaBagInfoBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_QueryQuotaBagRt_descriptor;
    }

    public pb4client.QueryQuotaBagRt getDefaultInstanceForType() {
      return pb4client.QueryQuotaBagRt.getDefaultInstance();
    }

    public pb4client.QueryQuotaBagRt build() {
      pb4client.QueryQuotaBagRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.QueryQuotaBagRt buildPartial() {
      pb4client.QueryQuotaBagRt result = new pb4client.QueryQuotaBagRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.rt_ = rt_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      if (quotaBagInfoBuilder_ == null) {
        result.quotaBagInfo_ = quotaBagInfo_;
      } else {
        result.quotaBagInfo_ = quotaBagInfoBuilder_.build();
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
      if (other instanceof pb4client.QueryQuotaBagRt) {
        return mergeFrom((pb4client.QueryQuotaBagRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.QueryQuotaBagRt other) {
      if (other == pb4client.QueryQuotaBagRt.getDefaultInstance()) return this;
      if (other.hasRt()) {
        setRt(other.getRt());
      }
      if (other.hasQuotaBagInfo()) {
        mergeQuotaBagInfo(other.getQuotaBagInfo());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasRt()) {
        return false;
      }
      if (hasQuotaBagInfo()) {
        if (!getQuotaBagInfo().isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.QueryQuotaBagRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.QueryQuotaBagRt) e.getUnfinishedMessage();
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

    private pb4client.QuotaBagInfo quotaBagInfo_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4client.QuotaBagInfo, pb4client.QuotaBagInfo.Builder, pb4client.QuotaBagInfoOrBuilder> quotaBagInfoBuilder_;
    /**
     * <pre>
     * 满额礼包信息
     * </pre>
     *
     * <code>optional .client2server.QuotaBagInfo quotaBagInfo = 2;</code>
     */
    public boolean hasQuotaBagInfo() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 满额礼包信息
     * </pre>
     *
     * <code>optional .client2server.QuotaBagInfo quotaBagInfo = 2;</code>
     */
    public pb4client.QuotaBagInfo getQuotaBagInfo() {
      if (quotaBagInfoBuilder_ == null) {
        return quotaBagInfo_ == null ? pb4client.QuotaBagInfo.getDefaultInstance() : quotaBagInfo_;
      } else {
        return quotaBagInfoBuilder_.getMessage();
      }
    }
    /**
     * <pre>
     * 满额礼包信息
     * </pre>
     *
     * <code>optional .client2server.QuotaBagInfo quotaBagInfo = 2;</code>
     */
    public Builder setQuotaBagInfo(pb4client.QuotaBagInfo value) {
      if (quotaBagInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        quotaBagInfo_ = value;
        onChanged();
      } else {
        quotaBagInfoBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <pre>
     * 满额礼包信息
     * </pre>
     *
     * <code>optional .client2server.QuotaBagInfo quotaBagInfo = 2;</code>
     */
    public Builder setQuotaBagInfo(
        pb4client.QuotaBagInfo.Builder builderForValue) {
      if (quotaBagInfoBuilder_ == null) {
        quotaBagInfo_ = builderForValue.build();
        onChanged();
      } else {
        quotaBagInfoBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <pre>
     * 满额礼包信息
     * </pre>
     *
     * <code>optional .client2server.QuotaBagInfo quotaBagInfo = 2;</code>
     */
    public Builder mergeQuotaBagInfo(pb4client.QuotaBagInfo value) {
      if (quotaBagInfoBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002) &&
            quotaBagInfo_ != null &&
            quotaBagInfo_ != pb4client.QuotaBagInfo.getDefaultInstance()) {
          quotaBagInfo_ =
            pb4client.QuotaBagInfo.newBuilder(quotaBagInfo_).mergeFrom(value).buildPartial();
        } else {
          quotaBagInfo_ = value;
        }
        onChanged();
      } else {
        quotaBagInfoBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <pre>
     * 满额礼包信息
     * </pre>
     *
     * <code>optional .client2server.QuotaBagInfo quotaBagInfo = 2;</code>
     */
    public Builder clearQuotaBagInfo() {
      if (quotaBagInfoBuilder_ == null) {
        quotaBagInfo_ = null;
        onChanged();
      } else {
        quotaBagInfoBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }
    /**
     * <pre>
     * 满额礼包信息
     * </pre>
     *
     * <code>optional .client2server.QuotaBagInfo quotaBagInfo = 2;</code>
     */
    public pb4client.QuotaBagInfo.Builder getQuotaBagInfoBuilder() {
      bitField0_ |= 0x00000002;
      onChanged();
      return getQuotaBagInfoFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     * 满额礼包信息
     * </pre>
     *
     * <code>optional .client2server.QuotaBagInfo quotaBagInfo = 2;</code>
     */
    public pb4client.QuotaBagInfoOrBuilder getQuotaBagInfoOrBuilder() {
      if (quotaBagInfoBuilder_ != null) {
        return quotaBagInfoBuilder_.getMessageOrBuilder();
      } else {
        return quotaBagInfo_ == null ?
            pb4client.QuotaBagInfo.getDefaultInstance() : quotaBagInfo_;
      }
    }
    /**
     * <pre>
     * 满额礼包信息
     * </pre>
     *
     * <code>optional .client2server.QuotaBagInfo quotaBagInfo = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4client.QuotaBagInfo, pb4client.QuotaBagInfo.Builder, pb4client.QuotaBagInfoOrBuilder> 
        getQuotaBagInfoFieldBuilder() {
      if (quotaBagInfoBuilder_ == null) {
        quotaBagInfoBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            pb4client.QuotaBagInfo, pb4client.QuotaBagInfo.Builder, pb4client.QuotaBagInfoOrBuilder>(
                getQuotaBagInfo(),
                getParentForChildren(),
                isClean());
        quotaBagInfo_ = null;
      }
      return quotaBagInfoBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.QueryQuotaBagRt)
  }

  // @@protoc_insertion_point(class_scope:client2server.QueryQuotaBagRt)
  private static final pb4client.QueryQuotaBagRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.QueryQuotaBagRt();
  }

  public static pb4client.QueryQuotaBagRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<QueryQuotaBagRt>
      PARSER = new com.google.protobuf.AbstractParser<QueryQuotaBagRt>() {
    public QueryQuotaBagRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new QueryQuotaBagRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<QueryQuotaBagRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<QueryQuotaBagRt> getParserForType() {
    return PARSER;
  }

  public pb4client.QueryQuotaBagRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

