// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.OneFightInfo}
 */
public  final class OneFightInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.OneFightInfo)
    OneFightInfoOrBuilder {
  // Use OneFightInfo.newBuilder() to construct.
  private OneFightInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private OneFightInfo() {
    detailFightInfo_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private OneFightInfo(
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
          case 18: {
            pb4client.EasyFightInfo.Builder subBuilder = null;
            if (((bitField0_ & 0x00000001) == 0x00000001)) {
              subBuilder = easyFightInfo_.toBuilder();
            }
            easyFightInfo_ = input.readMessage(pb4client.EasyFightInfo.PARSER, extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(easyFightInfo_);
              easyFightInfo_ = subBuilder.buildPartial();
            }
            bitField0_ |= 0x00000001;
            break;
          }
          case 26: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000002;
            detailFightInfo_ = bs;
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
    return pb4client.War2GamePkt.internal_static_client2server_OneFightInfo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_OneFightInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.OneFightInfo.class, pb4client.OneFightInfo.Builder.class);
  }

  private int bitField0_;
  public static final int EASYFIGHTINFO_FIELD_NUMBER = 2;
  private pb4client.EasyFightInfo easyFightInfo_;
  /**
   * <pre>
   *简单战报
   * </pre>
   *
   * <code>required .client2server.EasyFightInfo easyFightInfo = 2;</code>
   */
  public boolean hasEasyFightInfo() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *简单战报
   * </pre>
   *
   * <code>required .client2server.EasyFightInfo easyFightInfo = 2;</code>
   */
  public pb4client.EasyFightInfo getEasyFightInfo() {
    return easyFightInfo_ == null ? pb4client.EasyFightInfo.getDefaultInstance() : easyFightInfo_;
  }
  /**
   * <pre>
   *简单战报
   * </pre>
   *
   * <code>required .client2server.EasyFightInfo easyFightInfo = 2;</code>
   */
  public pb4client.EasyFightInfoOrBuilder getEasyFightInfoOrBuilder() {
    return easyFightInfo_ == null ? pb4client.EasyFightInfo.getDefaultInstance() : easyFightInfo_;
  }

  public static final int DETAILFIGHTINFO_FIELD_NUMBER = 3;
  private volatile java.lang.Object detailFightInfo_;
  /**
   * <pre>
   *详细战报
   * </pre>
   *
   * <code>required string detailFightInfo = 3;</code>
   */
  public boolean hasDetailFightInfo() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *详细战报
   * </pre>
   *
   * <code>required string detailFightInfo = 3;</code>
   */
  public java.lang.String getDetailFightInfo() {
    java.lang.Object ref = detailFightInfo_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        detailFightInfo_ = s;
      }
      return s;
    }
  }
  /**
   * <pre>
   *详细战报
   * </pre>
   *
   * <code>required string detailFightInfo = 3;</code>
   */
  public com.google.protobuf.ByteString
      getDetailFightInfoBytes() {
    java.lang.Object ref = detailFightInfo_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      detailFightInfo_ = b;
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

    if (!hasEasyFightInfo()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasDetailFightInfo()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!getEasyFightInfo().isInitialized()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeMessage(2, getEasyFightInfo());
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, detailFightInfo_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getEasyFightInfo());
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, detailFightInfo_);
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
    if (!(obj instanceof pb4client.OneFightInfo)) {
      return super.equals(obj);
    }
    pb4client.OneFightInfo other = (pb4client.OneFightInfo) obj;

    boolean result = true;
    result = result && (hasEasyFightInfo() == other.hasEasyFightInfo());
    if (hasEasyFightInfo()) {
      result = result && getEasyFightInfo()
          .equals(other.getEasyFightInfo());
    }
    result = result && (hasDetailFightInfo() == other.hasDetailFightInfo());
    if (hasDetailFightInfo()) {
      result = result && getDetailFightInfo()
          .equals(other.getDetailFightInfo());
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
    if (hasEasyFightInfo()) {
      hash = (37 * hash) + EASYFIGHTINFO_FIELD_NUMBER;
      hash = (53 * hash) + getEasyFightInfo().hashCode();
    }
    if (hasDetailFightInfo()) {
      hash = (37 * hash) + DETAILFIGHTINFO_FIELD_NUMBER;
      hash = (53 * hash) + getDetailFightInfo().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.OneFightInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.OneFightInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.OneFightInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.OneFightInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.OneFightInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.OneFightInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.OneFightInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.OneFightInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.OneFightInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.OneFightInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.OneFightInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.OneFightInfo parseFrom(
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
  public static Builder newBuilder(pb4client.OneFightInfo prototype) {
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
   * Protobuf type {@code client2server.OneFightInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.OneFightInfo)
      pb4client.OneFightInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_OneFightInfo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_OneFightInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.OneFightInfo.class, pb4client.OneFightInfo.Builder.class);
    }

    // Construct using pb4client.OneFightInfo.newBuilder()
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
        getEasyFightInfoFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (easyFightInfoBuilder_ == null) {
        easyFightInfo_ = null;
      } else {
        easyFightInfoBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000001);
      detailFightInfo_ = "";
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_OneFightInfo_descriptor;
    }

    public pb4client.OneFightInfo getDefaultInstanceForType() {
      return pb4client.OneFightInfo.getDefaultInstance();
    }

    public pb4client.OneFightInfo build() {
      pb4client.OneFightInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.OneFightInfo buildPartial() {
      pb4client.OneFightInfo result = new pb4client.OneFightInfo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      if (easyFightInfoBuilder_ == null) {
        result.easyFightInfo_ = easyFightInfo_;
      } else {
        result.easyFightInfo_ = easyFightInfoBuilder_.build();
      }
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.detailFightInfo_ = detailFightInfo_;
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
      if (other instanceof pb4client.OneFightInfo) {
        return mergeFrom((pb4client.OneFightInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.OneFightInfo other) {
      if (other == pb4client.OneFightInfo.getDefaultInstance()) return this;
      if (other.hasEasyFightInfo()) {
        mergeEasyFightInfo(other.getEasyFightInfo());
      }
      if (other.hasDetailFightInfo()) {
        bitField0_ |= 0x00000002;
        detailFightInfo_ = other.detailFightInfo_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasEasyFightInfo()) {
        return false;
      }
      if (!hasDetailFightInfo()) {
        return false;
      }
      if (!getEasyFightInfo().isInitialized()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.OneFightInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.OneFightInfo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private pb4client.EasyFightInfo easyFightInfo_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4client.EasyFightInfo, pb4client.EasyFightInfo.Builder, pb4client.EasyFightInfoOrBuilder> easyFightInfoBuilder_;
    /**
     * <pre>
     *简单战报
     * </pre>
     *
     * <code>required .client2server.EasyFightInfo easyFightInfo = 2;</code>
     */
    public boolean hasEasyFightInfo() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *简单战报
     * </pre>
     *
     * <code>required .client2server.EasyFightInfo easyFightInfo = 2;</code>
     */
    public pb4client.EasyFightInfo getEasyFightInfo() {
      if (easyFightInfoBuilder_ == null) {
        return easyFightInfo_ == null ? pb4client.EasyFightInfo.getDefaultInstance() : easyFightInfo_;
      } else {
        return easyFightInfoBuilder_.getMessage();
      }
    }
    /**
     * <pre>
     *简单战报
     * </pre>
     *
     * <code>required .client2server.EasyFightInfo easyFightInfo = 2;</code>
     */
    public Builder setEasyFightInfo(pb4client.EasyFightInfo value) {
      if (easyFightInfoBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        easyFightInfo_ = value;
        onChanged();
      } else {
        easyFightInfoBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000001;
      return this;
    }
    /**
     * <pre>
     *简单战报
     * </pre>
     *
     * <code>required .client2server.EasyFightInfo easyFightInfo = 2;</code>
     */
    public Builder setEasyFightInfo(
        pb4client.EasyFightInfo.Builder builderForValue) {
      if (easyFightInfoBuilder_ == null) {
        easyFightInfo_ = builderForValue.build();
        onChanged();
      } else {
        easyFightInfoBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000001;
      return this;
    }
    /**
     * <pre>
     *简单战报
     * </pre>
     *
     * <code>required .client2server.EasyFightInfo easyFightInfo = 2;</code>
     */
    public Builder mergeEasyFightInfo(pb4client.EasyFightInfo value) {
      if (easyFightInfoBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001) &&
            easyFightInfo_ != null &&
            easyFightInfo_ != pb4client.EasyFightInfo.getDefaultInstance()) {
          easyFightInfo_ =
            pb4client.EasyFightInfo.newBuilder(easyFightInfo_).mergeFrom(value).buildPartial();
        } else {
          easyFightInfo_ = value;
        }
        onChanged();
      } else {
        easyFightInfoBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000001;
      return this;
    }
    /**
     * <pre>
     *简单战报
     * </pre>
     *
     * <code>required .client2server.EasyFightInfo easyFightInfo = 2;</code>
     */
    public Builder clearEasyFightInfo() {
      if (easyFightInfoBuilder_ == null) {
        easyFightInfo_ = null;
        onChanged();
      } else {
        easyFightInfoBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }
    /**
     * <pre>
     *简单战报
     * </pre>
     *
     * <code>required .client2server.EasyFightInfo easyFightInfo = 2;</code>
     */
    public pb4client.EasyFightInfo.Builder getEasyFightInfoBuilder() {
      bitField0_ |= 0x00000001;
      onChanged();
      return getEasyFightInfoFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     *简单战报
     * </pre>
     *
     * <code>required .client2server.EasyFightInfo easyFightInfo = 2;</code>
     */
    public pb4client.EasyFightInfoOrBuilder getEasyFightInfoOrBuilder() {
      if (easyFightInfoBuilder_ != null) {
        return easyFightInfoBuilder_.getMessageOrBuilder();
      } else {
        return easyFightInfo_ == null ?
            pb4client.EasyFightInfo.getDefaultInstance() : easyFightInfo_;
      }
    }
    /**
     * <pre>
     *简单战报
     * </pre>
     *
     * <code>required .client2server.EasyFightInfo easyFightInfo = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4client.EasyFightInfo, pb4client.EasyFightInfo.Builder, pb4client.EasyFightInfoOrBuilder> 
        getEasyFightInfoFieldBuilder() {
      if (easyFightInfoBuilder_ == null) {
        easyFightInfoBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            pb4client.EasyFightInfo, pb4client.EasyFightInfo.Builder, pb4client.EasyFightInfoOrBuilder>(
                getEasyFightInfo(),
                getParentForChildren(),
                isClean());
        easyFightInfo_ = null;
      }
      return easyFightInfoBuilder_;
    }

    private java.lang.Object detailFightInfo_ = "";
    /**
     * <pre>
     *详细战报
     * </pre>
     *
     * <code>required string detailFightInfo = 3;</code>
     */
    public boolean hasDetailFightInfo() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *详细战报
     * </pre>
     *
     * <code>required string detailFightInfo = 3;</code>
     */
    public java.lang.String getDetailFightInfo() {
      java.lang.Object ref = detailFightInfo_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          detailFightInfo_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *详细战报
     * </pre>
     *
     * <code>required string detailFightInfo = 3;</code>
     */
    public com.google.protobuf.ByteString
        getDetailFightInfoBytes() {
      java.lang.Object ref = detailFightInfo_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        detailFightInfo_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *详细战报
     * </pre>
     *
     * <code>required string detailFightInfo = 3;</code>
     */
    public Builder setDetailFightInfo(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      detailFightInfo_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *详细战报
     * </pre>
     *
     * <code>required string detailFightInfo = 3;</code>
     */
    public Builder clearDetailFightInfo() {
      bitField0_ = (bitField0_ & ~0x00000002);
      detailFightInfo_ = getDefaultInstance().getDetailFightInfo();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *详细战报
     * </pre>
     *
     * <code>required string detailFightInfo = 3;</code>
     */
    public Builder setDetailFightInfoBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      detailFightInfo_ = value;
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


    // @@protoc_insertion_point(builder_scope:client2server.OneFightInfo)
  }

  // @@protoc_insertion_point(class_scope:client2server.OneFightInfo)
  private static final pb4client.OneFightInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.OneFightInfo();
  }

  public static pb4client.OneFightInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<OneFightInfo>
      PARSER = new com.google.protobuf.AbstractParser<OneFightInfo>() {
    public OneFightInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new OneFightInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<OneFightInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<OneFightInfo> getParserForType() {
    return PARSER;
  }

  public pb4client.OneFightInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

