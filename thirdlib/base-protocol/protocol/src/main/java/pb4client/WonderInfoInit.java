// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 3304
 * 服务器 -&gt; 客户端
 * 王国信息初始化
 * </pre>
 *
 * Protobuf type {@code client2server.WonderInfoInit}
 */
public  final class WonderInfoInit extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.WonderInfoInit)
    WonderInfoInitOrBuilder {
  // Use WonderInfoInit.newBuilder() to construct.
  private WonderInfoInit(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private WonderInfoInit() {
    currentPos_ = 0;
    buffCoolTime_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private WonderInfoInit(
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
          case 928: {
            bitField0_ |= 0x00000001;
            currentPos_ = input.readInt32();
            break;
          }
          case 936: {
            bitField0_ |= 0x00000002;
            buffCoolTime_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_WonderInfoInit_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_WonderInfoInit_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.WonderInfoInit.class, pb4client.WonderInfoInit.Builder.class);
  }

  private int bitField0_;
  public static final int CURRENTPOS_FIELD_NUMBER = 116;
  private int currentPos_;
  /**
   * <pre>
   *当前的官职信息
   * </pre>
   *
   * <code>optional int32 currentPos = 116;</code>
   */
  public boolean hasCurrentPos() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *当前的官职信息
   * </pre>
   *
   * <code>optional int32 currentPos = 116;</code>
   */
  public int getCurrentPos() {
    return currentPos_;
  }

  public static final int BUFFCOOLTIME_FIELD_NUMBER = 117;
  private int buffCoolTime_;
  /**
   * <pre>
   *王国buff冷却时间
   * </pre>
   *
   * <code>optional int32 buffCoolTime = 117;</code>
   */
  public boolean hasBuffCoolTime() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *王国buff冷却时间
   * </pre>
   *
   * <code>optional int32 buffCoolTime = 117;</code>
   */
  public int getBuffCoolTime() {
    return buffCoolTime_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(116, currentPos_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(117, buffCoolTime_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(116, currentPos_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(117, buffCoolTime_);
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
    if (!(obj instanceof pb4client.WonderInfoInit)) {
      return super.equals(obj);
    }
    pb4client.WonderInfoInit other = (pb4client.WonderInfoInit) obj;

    boolean result = true;
    result = result && (hasCurrentPos() == other.hasCurrentPos());
    if (hasCurrentPos()) {
      result = result && (getCurrentPos()
          == other.getCurrentPos());
    }
    result = result && (hasBuffCoolTime() == other.hasBuffCoolTime());
    if (hasBuffCoolTime()) {
      result = result && (getBuffCoolTime()
          == other.getBuffCoolTime());
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
    if (hasCurrentPos()) {
      hash = (37 * hash) + CURRENTPOS_FIELD_NUMBER;
      hash = (53 * hash) + getCurrentPos();
    }
    if (hasBuffCoolTime()) {
      hash = (37 * hash) + BUFFCOOLTIME_FIELD_NUMBER;
      hash = (53 * hash) + getBuffCoolTime();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.WonderInfoInit parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.WonderInfoInit parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.WonderInfoInit parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.WonderInfoInit parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.WonderInfoInit parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.WonderInfoInit parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.WonderInfoInit parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.WonderInfoInit parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.WonderInfoInit parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.WonderInfoInit parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.WonderInfoInit parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.WonderInfoInit parseFrom(
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
  public static Builder newBuilder(pb4client.WonderInfoInit prototype) {
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
   * msgType = 3304
   * 服务器 -&gt; 客户端
   * 王国信息初始化
   * </pre>
   *
   * Protobuf type {@code client2server.WonderInfoInit}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.WonderInfoInit)
      pb4client.WonderInfoInitOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_WonderInfoInit_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_WonderInfoInit_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.WonderInfoInit.class, pb4client.WonderInfoInit.Builder.class);
    }

    // Construct using pb4client.WonderInfoInit.newBuilder()
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
      currentPos_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      buffCoolTime_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_WonderInfoInit_descriptor;
    }

    public pb4client.WonderInfoInit getDefaultInstanceForType() {
      return pb4client.WonderInfoInit.getDefaultInstance();
    }

    public pb4client.WonderInfoInit build() {
      pb4client.WonderInfoInit result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.WonderInfoInit buildPartial() {
      pb4client.WonderInfoInit result = new pb4client.WonderInfoInit(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.currentPos_ = currentPos_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.buffCoolTime_ = buffCoolTime_;
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
      if (other instanceof pb4client.WonderInfoInit) {
        return mergeFrom((pb4client.WonderInfoInit)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.WonderInfoInit other) {
      if (other == pb4client.WonderInfoInit.getDefaultInstance()) return this;
      if (other.hasCurrentPos()) {
        setCurrentPos(other.getCurrentPos());
      }
      if (other.hasBuffCoolTime()) {
        setBuffCoolTime(other.getBuffCoolTime());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.WonderInfoInit parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.WonderInfoInit) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int currentPos_ ;
    /**
     * <pre>
     *当前的官职信息
     * </pre>
     *
     * <code>optional int32 currentPos = 116;</code>
     */
    public boolean hasCurrentPos() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *当前的官职信息
     * </pre>
     *
     * <code>optional int32 currentPos = 116;</code>
     */
    public int getCurrentPos() {
      return currentPos_;
    }
    /**
     * <pre>
     *当前的官职信息
     * </pre>
     *
     * <code>optional int32 currentPos = 116;</code>
     */
    public Builder setCurrentPos(int value) {
      bitField0_ |= 0x00000001;
      currentPos_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *当前的官职信息
     * </pre>
     *
     * <code>optional int32 currentPos = 116;</code>
     */
    public Builder clearCurrentPos() {
      bitField0_ = (bitField0_ & ~0x00000001);
      currentPos_ = 0;
      onChanged();
      return this;
    }

    private int buffCoolTime_ ;
    /**
     * <pre>
     *王国buff冷却时间
     * </pre>
     *
     * <code>optional int32 buffCoolTime = 117;</code>
     */
    public boolean hasBuffCoolTime() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *王国buff冷却时间
     * </pre>
     *
     * <code>optional int32 buffCoolTime = 117;</code>
     */
    public int getBuffCoolTime() {
      return buffCoolTime_;
    }
    /**
     * <pre>
     *王国buff冷却时间
     * </pre>
     *
     * <code>optional int32 buffCoolTime = 117;</code>
     */
    public Builder setBuffCoolTime(int value) {
      bitField0_ |= 0x00000002;
      buffCoolTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *王国buff冷却时间
     * </pre>
     *
     * <code>optional int32 buffCoolTime = 117;</code>
     */
    public Builder clearBuffCoolTime() {
      bitField0_ = (bitField0_ & ~0x00000002);
      buffCoolTime_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.WonderInfoInit)
  }

  // @@protoc_insertion_point(class_scope:client2server.WonderInfoInit)
  private static final pb4client.WonderInfoInit DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.WonderInfoInit();
  }

  public static pb4client.WonderInfoInit getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<WonderInfoInit>
      PARSER = new com.google.protobuf.AbstractParser<WonderInfoInit>() {
    public WonderInfoInit parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new WonderInfoInit(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<WonderInfoInit> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<WonderInfoInit> getParserForType() {
    return PARSER;
  }

  public pb4client.WonderInfoInit getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

