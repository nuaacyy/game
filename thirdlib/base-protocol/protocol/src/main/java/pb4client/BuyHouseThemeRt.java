// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.BuyHouseThemeRt}
 */
public  final class BuyHouseThemeRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.BuyHouseThemeRt)
    BuyHouseThemeRtOrBuilder {
  // Use BuyHouseThemeRt.newBuilder() to construct.
  private BuyHouseThemeRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private BuyHouseThemeRt() {
    rt_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private BuyHouseThemeRt(
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
            pb4client.HouseThemeInfo.Builder subBuilder = null;
            if (((bitField0_ & 0x00000002) == 0x00000002)) {
              subBuilder = theme_.toBuilder();
            }
            theme_ = input.readMessage(pb4client.HouseThemeInfo.PARSER, extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(theme_);
              theme_ = subBuilder.buildPartial();
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
    return pb4client.War2GamePkt.internal_static_client2server_BuyHouseThemeRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_BuyHouseThemeRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.BuyHouseThemeRt.class, pb4client.BuyHouseThemeRt.Builder.class);
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

  public static final int THEME_FIELD_NUMBER = 2;
  private pb4client.HouseThemeInfo theme_;
  /**
   * <code>optional .client2server.HouseThemeInfo theme = 2;</code>
   */
  public boolean hasTheme() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>optional .client2server.HouseThemeInfo theme = 2;</code>
   */
  public pb4client.HouseThemeInfo getTheme() {
    return theme_ == null ? pb4client.HouseThemeInfo.getDefaultInstance() : theme_;
  }
  /**
   * <code>optional .client2server.HouseThemeInfo theme = 2;</code>
   */
  public pb4client.HouseThemeInfoOrBuilder getThemeOrBuilder() {
    return theme_ == null ? pb4client.HouseThemeInfo.getDefaultInstance() : theme_;
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
    if (hasTheme()) {
      if (!getTheme().isInitialized()) {
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
      output.writeMessage(2, getTheme());
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
        .computeMessageSize(2, getTheme());
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
    if (!(obj instanceof pb4client.BuyHouseThemeRt)) {
      return super.equals(obj);
    }
    pb4client.BuyHouseThemeRt other = (pb4client.BuyHouseThemeRt) obj;

    boolean result = true;
    result = result && (hasRt() == other.hasRt());
    if (hasRt()) {
      result = result && (getRt()
          == other.getRt());
    }
    result = result && (hasTheme() == other.hasTheme());
    if (hasTheme()) {
      result = result && getTheme()
          .equals(other.getTheme());
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
    if (hasTheme()) {
      hash = (37 * hash) + THEME_FIELD_NUMBER;
      hash = (53 * hash) + getTheme().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.BuyHouseThemeRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.BuyHouseThemeRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.BuyHouseThemeRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.BuyHouseThemeRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.BuyHouseThemeRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.BuyHouseThemeRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.BuyHouseThemeRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.BuyHouseThemeRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.BuyHouseThemeRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.BuyHouseThemeRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.BuyHouseThemeRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.BuyHouseThemeRt parseFrom(
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
  public static Builder newBuilder(pb4client.BuyHouseThemeRt prototype) {
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
   * Protobuf type {@code client2server.BuyHouseThemeRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.BuyHouseThemeRt)
      pb4client.BuyHouseThemeRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_BuyHouseThemeRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_BuyHouseThemeRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.BuyHouseThemeRt.class, pb4client.BuyHouseThemeRt.Builder.class);
    }

    // Construct using pb4client.BuyHouseThemeRt.newBuilder()
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
        getThemeFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      rt_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      if (themeBuilder_ == null) {
        theme_ = null;
      } else {
        themeBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_BuyHouseThemeRt_descriptor;
    }

    public pb4client.BuyHouseThemeRt getDefaultInstanceForType() {
      return pb4client.BuyHouseThemeRt.getDefaultInstance();
    }

    public pb4client.BuyHouseThemeRt build() {
      pb4client.BuyHouseThemeRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.BuyHouseThemeRt buildPartial() {
      pb4client.BuyHouseThemeRt result = new pb4client.BuyHouseThemeRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.rt_ = rt_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      if (themeBuilder_ == null) {
        result.theme_ = theme_;
      } else {
        result.theme_ = themeBuilder_.build();
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
      if (other instanceof pb4client.BuyHouseThemeRt) {
        return mergeFrom((pb4client.BuyHouseThemeRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.BuyHouseThemeRt other) {
      if (other == pb4client.BuyHouseThemeRt.getDefaultInstance()) return this;
      if (other.hasRt()) {
        setRt(other.getRt());
      }
      if (other.hasTheme()) {
        mergeTheme(other.getTheme());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasRt()) {
        return false;
      }
      if (hasTheme()) {
        if (!getTheme().isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.BuyHouseThemeRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.BuyHouseThemeRt) e.getUnfinishedMessage();
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

    private pb4client.HouseThemeInfo theme_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4client.HouseThemeInfo, pb4client.HouseThemeInfo.Builder, pb4client.HouseThemeInfoOrBuilder> themeBuilder_;
    /**
     * <code>optional .client2server.HouseThemeInfo theme = 2;</code>
     */
    public boolean hasTheme() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional .client2server.HouseThemeInfo theme = 2;</code>
     */
    public pb4client.HouseThemeInfo getTheme() {
      if (themeBuilder_ == null) {
        return theme_ == null ? pb4client.HouseThemeInfo.getDefaultInstance() : theme_;
      } else {
        return themeBuilder_.getMessage();
      }
    }
    /**
     * <code>optional .client2server.HouseThemeInfo theme = 2;</code>
     */
    public Builder setTheme(pb4client.HouseThemeInfo value) {
      if (themeBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        theme_ = value;
        onChanged();
      } else {
        themeBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <code>optional .client2server.HouseThemeInfo theme = 2;</code>
     */
    public Builder setTheme(
        pb4client.HouseThemeInfo.Builder builderForValue) {
      if (themeBuilder_ == null) {
        theme_ = builderForValue.build();
        onChanged();
      } else {
        themeBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <code>optional .client2server.HouseThemeInfo theme = 2;</code>
     */
    public Builder mergeTheme(pb4client.HouseThemeInfo value) {
      if (themeBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002) &&
            theme_ != null &&
            theme_ != pb4client.HouseThemeInfo.getDefaultInstance()) {
          theme_ =
            pb4client.HouseThemeInfo.newBuilder(theme_).mergeFrom(value).buildPartial();
        } else {
          theme_ = value;
        }
        onChanged();
      } else {
        themeBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <code>optional .client2server.HouseThemeInfo theme = 2;</code>
     */
    public Builder clearTheme() {
      if (themeBuilder_ == null) {
        theme_ = null;
        onChanged();
      } else {
        themeBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }
    /**
     * <code>optional .client2server.HouseThemeInfo theme = 2;</code>
     */
    public pb4client.HouseThemeInfo.Builder getThemeBuilder() {
      bitField0_ |= 0x00000002;
      onChanged();
      return getThemeFieldBuilder().getBuilder();
    }
    /**
     * <code>optional .client2server.HouseThemeInfo theme = 2;</code>
     */
    public pb4client.HouseThemeInfoOrBuilder getThemeOrBuilder() {
      if (themeBuilder_ != null) {
        return themeBuilder_.getMessageOrBuilder();
      } else {
        return theme_ == null ?
            pb4client.HouseThemeInfo.getDefaultInstance() : theme_;
      }
    }
    /**
     * <code>optional .client2server.HouseThemeInfo theme = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4client.HouseThemeInfo, pb4client.HouseThemeInfo.Builder, pb4client.HouseThemeInfoOrBuilder> 
        getThemeFieldBuilder() {
      if (themeBuilder_ == null) {
        themeBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            pb4client.HouseThemeInfo, pb4client.HouseThemeInfo.Builder, pb4client.HouseThemeInfoOrBuilder>(
                getTheme(),
                getParentForChildren(),
                isClean());
        theme_ = null;
      }
      return themeBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.BuyHouseThemeRt)
  }

  // @@protoc_insertion_point(class_scope:client2server.BuyHouseThemeRt)
  private static final pb4client.BuyHouseThemeRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.BuyHouseThemeRt();
  }

  public static pb4client.BuyHouseThemeRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<BuyHouseThemeRt>
      PARSER = new com.google.protobuf.AbstractParser<BuyHouseThemeRt>() {
    public BuyHouseThemeRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new BuyHouseThemeRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<BuyHouseThemeRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<BuyHouseThemeRt> getParserForType() {
    return PARSER;
  }

  public pb4client.BuyHouseThemeRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

