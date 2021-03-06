// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 3122
 * 玩家获得帮助提示窗
 * </pre>
 *
 * Protobuf type {@code client2server.GetAllianceHelp}
 */
public  final class GetAllianceHelp extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.GetAllianceHelp)
    GetAllianceHelpOrBuilder {
  // Use GetAllianceHelp.newBuilder() to construct.
  private GetAllianceHelp(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GetAllianceHelp() {
    helpPlayerName_ = "";
    helpType_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GetAllianceHelp(
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
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000001;
            helpPlayerName_ = bs;
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            helpType_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_GetAllianceHelp_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_GetAllianceHelp_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.GetAllianceHelp.class, pb4client.GetAllianceHelp.Builder.class);
  }

  private int bitField0_;
  public static final int HELPPLAYERNAME_FIELD_NUMBER = 1;
  private volatile java.lang.Object helpPlayerName_;
  /**
   * <pre>
   * 帮助人的名字
   * </pre>
   *
   * <code>required string helpPlayerName = 1;</code>
   */
  public boolean hasHelpPlayerName() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 帮助人的名字
   * </pre>
   *
   * <code>required string helpPlayerName = 1;</code>
   */
  public java.lang.String getHelpPlayerName() {
    java.lang.Object ref = helpPlayerName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        helpPlayerName_ = s;
      }
      return s;
    }
  }
  /**
   * <pre>
   * 帮助人的名字
   * </pre>
   *
   * <code>required string helpPlayerName = 1;</code>
   */
  public com.google.protobuf.ByteString
      getHelpPlayerNameBytes() {
    java.lang.Object ref = helpPlayerName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      helpPlayerName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int HELPTYPE_FIELD_NUMBER = 2;
  private int helpType_;
  /**
   * <pre>
   * 被帮忙的类型
   * </pre>
   *
   * <code>required int32 helpType = 2;</code>
   */
  public boolean hasHelpType() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 被帮忙的类型
   * </pre>
   *
   * <code>required int32 helpType = 2;</code>
   */
  public int getHelpType() {
    return helpType_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasHelpPlayerName()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasHelpType()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, helpPlayerName_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, helpType_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, helpPlayerName_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, helpType_);
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
    if (!(obj instanceof pb4client.GetAllianceHelp)) {
      return super.equals(obj);
    }
    pb4client.GetAllianceHelp other = (pb4client.GetAllianceHelp) obj;

    boolean result = true;
    result = result && (hasHelpPlayerName() == other.hasHelpPlayerName());
    if (hasHelpPlayerName()) {
      result = result && getHelpPlayerName()
          .equals(other.getHelpPlayerName());
    }
    result = result && (hasHelpType() == other.hasHelpType());
    if (hasHelpType()) {
      result = result && (getHelpType()
          == other.getHelpType());
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
    if (hasHelpPlayerName()) {
      hash = (37 * hash) + HELPPLAYERNAME_FIELD_NUMBER;
      hash = (53 * hash) + getHelpPlayerName().hashCode();
    }
    if (hasHelpType()) {
      hash = (37 * hash) + HELPTYPE_FIELD_NUMBER;
      hash = (53 * hash) + getHelpType();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.GetAllianceHelp parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.GetAllianceHelp parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.GetAllianceHelp parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.GetAllianceHelp parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.GetAllianceHelp parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.GetAllianceHelp parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.GetAllianceHelp parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.GetAllianceHelp parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.GetAllianceHelp parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.GetAllianceHelp parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.GetAllianceHelp parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.GetAllianceHelp parseFrom(
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
  public static Builder newBuilder(pb4client.GetAllianceHelp prototype) {
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
   * msgType = 3122
   * 玩家获得帮助提示窗
   * </pre>
   *
   * Protobuf type {@code client2server.GetAllianceHelp}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.GetAllianceHelp)
      pb4client.GetAllianceHelpOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_GetAllianceHelp_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_GetAllianceHelp_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.GetAllianceHelp.class, pb4client.GetAllianceHelp.Builder.class);
    }

    // Construct using pb4client.GetAllianceHelp.newBuilder()
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
      helpPlayerName_ = "";
      bitField0_ = (bitField0_ & ~0x00000001);
      helpType_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_GetAllianceHelp_descriptor;
    }

    public pb4client.GetAllianceHelp getDefaultInstanceForType() {
      return pb4client.GetAllianceHelp.getDefaultInstance();
    }

    public pb4client.GetAllianceHelp build() {
      pb4client.GetAllianceHelp result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.GetAllianceHelp buildPartial() {
      pb4client.GetAllianceHelp result = new pb4client.GetAllianceHelp(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.helpPlayerName_ = helpPlayerName_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.helpType_ = helpType_;
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
      if (other instanceof pb4client.GetAllianceHelp) {
        return mergeFrom((pb4client.GetAllianceHelp)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.GetAllianceHelp other) {
      if (other == pb4client.GetAllianceHelp.getDefaultInstance()) return this;
      if (other.hasHelpPlayerName()) {
        bitField0_ |= 0x00000001;
        helpPlayerName_ = other.helpPlayerName_;
        onChanged();
      }
      if (other.hasHelpType()) {
        setHelpType(other.getHelpType());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasHelpPlayerName()) {
        return false;
      }
      if (!hasHelpType()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.GetAllianceHelp parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.GetAllianceHelp) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object helpPlayerName_ = "";
    /**
     * <pre>
     * 帮助人的名字
     * </pre>
     *
     * <code>required string helpPlayerName = 1;</code>
     */
    public boolean hasHelpPlayerName() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 帮助人的名字
     * </pre>
     *
     * <code>required string helpPlayerName = 1;</code>
     */
    public java.lang.String getHelpPlayerName() {
      java.lang.Object ref = helpPlayerName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          helpPlayerName_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * 帮助人的名字
     * </pre>
     *
     * <code>required string helpPlayerName = 1;</code>
     */
    public com.google.protobuf.ByteString
        getHelpPlayerNameBytes() {
      java.lang.Object ref = helpPlayerName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        helpPlayerName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * 帮助人的名字
     * </pre>
     *
     * <code>required string helpPlayerName = 1;</code>
     */
    public Builder setHelpPlayerName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      helpPlayerName_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 帮助人的名字
     * </pre>
     *
     * <code>required string helpPlayerName = 1;</code>
     */
    public Builder clearHelpPlayerName() {
      bitField0_ = (bitField0_ & ~0x00000001);
      helpPlayerName_ = getDefaultInstance().getHelpPlayerName();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 帮助人的名字
     * </pre>
     *
     * <code>required string helpPlayerName = 1;</code>
     */
    public Builder setHelpPlayerNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      helpPlayerName_ = value;
      onChanged();
      return this;
    }

    private int helpType_ ;
    /**
     * <pre>
     * 被帮忙的类型
     * </pre>
     *
     * <code>required int32 helpType = 2;</code>
     */
    public boolean hasHelpType() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 被帮忙的类型
     * </pre>
     *
     * <code>required int32 helpType = 2;</code>
     */
    public int getHelpType() {
      return helpType_;
    }
    /**
     * <pre>
     * 被帮忙的类型
     * </pre>
     *
     * <code>required int32 helpType = 2;</code>
     */
    public Builder setHelpType(int value) {
      bitField0_ |= 0x00000002;
      helpType_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 被帮忙的类型
     * </pre>
     *
     * <code>required int32 helpType = 2;</code>
     */
    public Builder clearHelpType() {
      bitField0_ = (bitField0_ & ~0x00000002);
      helpType_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.GetAllianceHelp)
  }

  // @@protoc_insertion_point(class_scope:client2server.GetAllianceHelp)
  private static final pb4client.GetAllianceHelp DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.GetAllianceHelp();
  }

  public static pb4client.GetAllianceHelp getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<GetAllianceHelp>
      PARSER = new com.google.protobuf.AbstractParser<GetAllianceHelp>() {
    public GetAllianceHelp parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new GetAllianceHelp(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GetAllianceHelp> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GetAllianceHelp> getParserForType() {
    return PARSER;
  }

  public pb4client.GetAllianceHelp getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

