// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 1064
 * 客户端 -&gt; 服务器
 * 购买并使用道具
 * </pre>
 *
 * Protobuf type {@code client2server.BuyAndUseProp}
 */
public  final class BuyAndUseProp extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.BuyAndUseProp)
    BuyAndUsePropOrBuilder {
  // Use BuyAndUseProp.newBuilder() to construct.
  private BuyAndUseProp(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private BuyAndUseProp() {
    usePropId_ = 0L;
    usePropNum_ = 0;
    extendVal_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private BuyAndUseProp(
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
            usePropId_ = input.readInt64();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            usePropNum_ = input.readInt32();
            break;
          }
          case 26: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000004;
            extendVal_ = bs;
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
    return pb4client.War2GamePkt.internal_static_client2server_BuyAndUseProp_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_BuyAndUseProp_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.BuyAndUseProp.class, pb4client.BuyAndUseProp.Builder.class);
  }

  private int bitField0_;
  public static final int USEPROPID_FIELD_NUMBER = 1;
  private long usePropId_;
  /**
   * <pre>
   * 使用物品的配置ID
   * </pre>
   *
   * <code>required int64 usePropId = 1;</code>
   */
  public boolean hasUsePropId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 使用物品的配置ID
   * </pre>
   *
   * <code>required int64 usePropId = 1;</code>
   */
  public long getUsePropId() {
    return usePropId_;
  }

  public static final int USEPROPNUM_FIELD_NUMBER = 2;
  private int usePropNum_;
  /**
   * <pre>
   * 使用的物品数量
   * </pre>
   *
   * <code>required int32 usePropNum = 2;</code>
   */
  public boolean hasUsePropNum() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 使用的物品数量
   * </pre>
   *
   * <code>required int32 usePropNum = 2;</code>
   */
  public int getUsePropNum() {
    return usePropNum_;
  }

  public static final int EXTENDVAL_FIELD_NUMBER = 3;
  private volatile java.lang.Object extendVal_;
  /**
   * <pre>
   * 扩展字段
   * </pre>
   *
   * <code>optional string extendVal = 3;</code>
   */
  public boolean hasExtendVal() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   * 扩展字段
   * </pre>
   *
   * <code>optional string extendVal = 3;</code>
   */
  public java.lang.String getExtendVal() {
    java.lang.Object ref = extendVal_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        extendVal_ = s;
      }
      return s;
    }
  }
  /**
   * <pre>
   * 扩展字段
   * </pre>
   *
   * <code>optional string extendVal = 3;</code>
   */
  public com.google.protobuf.ByteString
      getExtendValBytes() {
    java.lang.Object ref = extendVal_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      extendVal_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasUsePropId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasUsePropNum()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt64(1, usePropId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, usePropNum_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, extendVal_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, usePropId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, usePropNum_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, extendVal_);
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
    if (!(obj instanceof pb4client.BuyAndUseProp)) {
      return super.equals(obj);
    }
    pb4client.BuyAndUseProp other = (pb4client.BuyAndUseProp) obj;

    boolean result = true;
    result = result && (hasUsePropId() == other.hasUsePropId());
    if (hasUsePropId()) {
      result = result && (getUsePropId()
          == other.getUsePropId());
    }
    result = result && (hasUsePropNum() == other.hasUsePropNum());
    if (hasUsePropNum()) {
      result = result && (getUsePropNum()
          == other.getUsePropNum());
    }
    result = result && (hasExtendVal() == other.hasExtendVal());
    if (hasExtendVal()) {
      result = result && getExtendVal()
          .equals(other.getExtendVal());
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
    if (hasUsePropId()) {
      hash = (37 * hash) + USEPROPID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getUsePropId());
    }
    if (hasUsePropNum()) {
      hash = (37 * hash) + USEPROPNUM_FIELD_NUMBER;
      hash = (53 * hash) + getUsePropNum();
    }
    if (hasExtendVal()) {
      hash = (37 * hash) + EXTENDVAL_FIELD_NUMBER;
      hash = (53 * hash) + getExtendVal().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.BuyAndUseProp parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.BuyAndUseProp parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.BuyAndUseProp parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.BuyAndUseProp parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.BuyAndUseProp parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.BuyAndUseProp parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.BuyAndUseProp parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.BuyAndUseProp parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.BuyAndUseProp parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.BuyAndUseProp parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.BuyAndUseProp parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.BuyAndUseProp parseFrom(
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
  public static Builder newBuilder(pb4client.BuyAndUseProp prototype) {
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
   * msgType = 1064
   * 客户端 -&gt; 服务器
   * 购买并使用道具
   * </pre>
   *
   * Protobuf type {@code client2server.BuyAndUseProp}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.BuyAndUseProp)
      pb4client.BuyAndUsePropOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_BuyAndUseProp_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_BuyAndUseProp_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.BuyAndUseProp.class, pb4client.BuyAndUseProp.Builder.class);
    }

    // Construct using pb4client.BuyAndUseProp.newBuilder()
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
      }
    }
    public Builder clear() {
      super.clear();
      usePropId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      usePropNum_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      extendVal_ = "";
      bitField0_ = (bitField0_ & ~0x00000004);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_BuyAndUseProp_descriptor;
    }

    public pb4client.BuyAndUseProp getDefaultInstanceForType() {
      return pb4client.BuyAndUseProp.getDefaultInstance();
    }

    public pb4client.BuyAndUseProp build() {
      pb4client.BuyAndUseProp result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.BuyAndUseProp buildPartial() {
      pb4client.BuyAndUseProp result = new pb4client.BuyAndUseProp(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.usePropId_ = usePropId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.usePropNum_ = usePropNum_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.extendVal_ = extendVal_;
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
      if (other instanceof pb4client.BuyAndUseProp) {
        return mergeFrom((pb4client.BuyAndUseProp)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.BuyAndUseProp other) {
      if (other == pb4client.BuyAndUseProp.getDefaultInstance()) return this;
      if (other.hasUsePropId()) {
        setUsePropId(other.getUsePropId());
      }
      if (other.hasUsePropNum()) {
        setUsePropNum(other.getUsePropNum());
      }
      if (other.hasExtendVal()) {
        bitField0_ |= 0x00000004;
        extendVal_ = other.extendVal_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasUsePropId()) {
        return false;
      }
      if (!hasUsePropNum()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.BuyAndUseProp parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.BuyAndUseProp) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long usePropId_ ;
    /**
     * <pre>
     * 使用物品的配置ID
     * </pre>
     *
     * <code>required int64 usePropId = 1;</code>
     */
    public boolean hasUsePropId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 使用物品的配置ID
     * </pre>
     *
     * <code>required int64 usePropId = 1;</code>
     */
    public long getUsePropId() {
      return usePropId_;
    }
    /**
     * <pre>
     * 使用物品的配置ID
     * </pre>
     *
     * <code>required int64 usePropId = 1;</code>
     */
    public Builder setUsePropId(long value) {
      bitField0_ |= 0x00000001;
      usePropId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 使用物品的配置ID
     * </pre>
     *
     * <code>required int64 usePropId = 1;</code>
     */
    public Builder clearUsePropId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      usePropId_ = 0L;
      onChanged();
      return this;
    }

    private int usePropNum_ ;
    /**
     * <pre>
     * 使用的物品数量
     * </pre>
     *
     * <code>required int32 usePropNum = 2;</code>
     */
    public boolean hasUsePropNum() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 使用的物品数量
     * </pre>
     *
     * <code>required int32 usePropNum = 2;</code>
     */
    public int getUsePropNum() {
      return usePropNum_;
    }
    /**
     * <pre>
     * 使用的物品数量
     * </pre>
     *
     * <code>required int32 usePropNum = 2;</code>
     */
    public Builder setUsePropNum(int value) {
      bitField0_ |= 0x00000002;
      usePropNum_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 使用的物品数量
     * </pre>
     *
     * <code>required int32 usePropNum = 2;</code>
     */
    public Builder clearUsePropNum() {
      bitField0_ = (bitField0_ & ~0x00000002);
      usePropNum_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object extendVal_ = "";
    /**
     * <pre>
     * 扩展字段
     * </pre>
     *
     * <code>optional string extendVal = 3;</code>
     */
    public boolean hasExtendVal() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     * 扩展字段
     * </pre>
     *
     * <code>optional string extendVal = 3;</code>
     */
    public java.lang.String getExtendVal() {
      java.lang.Object ref = extendVal_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          extendVal_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * 扩展字段
     * </pre>
     *
     * <code>optional string extendVal = 3;</code>
     */
    public com.google.protobuf.ByteString
        getExtendValBytes() {
      java.lang.Object ref = extendVal_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        extendVal_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * 扩展字段
     * </pre>
     *
     * <code>optional string extendVal = 3;</code>
     */
    public Builder setExtendVal(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
      extendVal_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 扩展字段
     * </pre>
     *
     * <code>optional string extendVal = 3;</code>
     */
    public Builder clearExtendVal() {
      bitField0_ = (bitField0_ & ~0x00000004);
      extendVal_ = getDefaultInstance().getExtendVal();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 扩展字段
     * </pre>
     *
     * <code>optional string extendVal = 3;</code>
     */
    public Builder setExtendValBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
      extendVal_ = value;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.BuyAndUseProp)
  }

  // @@protoc_insertion_point(class_scope:client2server.BuyAndUseProp)
  private static final pb4client.BuyAndUseProp DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.BuyAndUseProp();
  }

  public static pb4client.BuyAndUseProp getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<BuyAndUseProp>
      PARSER = new com.google.protobuf.AbstractParser<BuyAndUseProp>() {
    public BuyAndUseProp parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new BuyAndUseProp(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<BuyAndUseProp> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<BuyAndUseProp> getParserForType() {
    return PARSER;
  }

  public pb4client.BuyAndUseProp getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

