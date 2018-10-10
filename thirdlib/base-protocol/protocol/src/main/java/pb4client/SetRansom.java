// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * // msgType = 1354
 * 客户端 -&gt; 服务器
 * 设置赎金
 * </pre>
 *
 * Protobuf type {@code client2server.SetRansom}
 */
public  final class SetRansom extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.SetRansom)
    SetRansomOrBuilder {
  // Use SetRansom.newBuilder() to construct.
  private SetRansom(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SetRansom() {
    prisonPlayerId_ = 0L;
    setNum_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private SetRansom(
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
            prisonPlayerId_ = input.readInt64();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            setNum_ = input.readInt64();
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
    return pb4client.War2GamePkt.internal_static_client2server_SetRansom_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_SetRansom_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.SetRansom.class, pb4client.SetRansom.Builder.class);
  }

  private int bitField0_;
  public static final int PRISONPLAYERID_FIELD_NUMBER = 1;
  private long prisonPlayerId_;
  /**
   * <pre>
   * 要设置的玩家ID
   * </pre>
   *
   * <code>required int64 prisonPlayerId = 1;</code>
   */
  public boolean hasPrisonPlayerId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 要设置的玩家ID
   * </pre>
   *
   * <code>required int64 prisonPlayerId = 1;</code>
   */
  public long getPrisonPlayerId() {
    return prisonPlayerId_;
  }

  public static final int SETNUM_FIELD_NUMBER = 2;
  private long setNum_;
  /**
   * <pre>
   * 设置的值
   * </pre>
   *
   * <code>required int64 setNum = 2;</code>
   */
  public boolean hasSetNum() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 设置的值
   * </pre>
   *
   * <code>required int64 setNum = 2;</code>
   */
  public long getSetNum() {
    return setNum_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasPrisonPlayerId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasSetNum()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt64(1, prisonPlayerId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt64(2, setNum_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, prisonPlayerId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, setNum_);
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
    if (!(obj instanceof pb4client.SetRansom)) {
      return super.equals(obj);
    }
    pb4client.SetRansom other = (pb4client.SetRansom) obj;

    boolean result = true;
    result = result && (hasPrisonPlayerId() == other.hasPrisonPlayerId());
    if (hasPrisonPlayerId()) {
      result = result && (getPrisonPlayerId()
          == other.getPrisonPlayerId());
    }
    result = result && (hasSetNum() == other.hasSetNum());
    if (hasSetNum()) {
      result = result && (getSetNum()
          == other.getSetNum());
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
    if (hasPrisonPlayerId()) {
      hash = (37 * hash) + PRISONPLAYERID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getPrisonPlayerId());
    }
    if (hasSetNum()) {
      hash = (37 * hash) + SETNUM_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getSetNum());
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.SetRansom parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.SetRansom parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.SetRansom parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.SetRansom parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.SetRansom parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.SetRansom parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.SetRansom parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.SetRansom parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.SetRansom parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.SetRansom parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.SetRansom parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.SetRansom parseFrom(
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
  public static Builder newBuilder(pb4client.SetRansom prototype) {
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
   * // msgType = 1354
   * 客户端 -&gt; 服务器
   * 设置赎金
   * </pre>
   *
   * Protobuf type {@code client2server.SetRansom}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.SetRansom)
      pb4client.SetRansomOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_SetRansom_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_SetRansom_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.SetRansom.class, pb4client.SetRansom.Builder.class);
    }

    // Construct using pb4client.SetRansom.newBuilder()
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
      prisonPlayerId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      setNum_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_SetRansom_descriptor;
    }

    public pb4client.SetRansom getDefaultInstanceForType() {
      return pb4client.SetRansom.getDefaultInstance();
    }

    public pb4client.SetRansom build() {
      pb4client.SetRansom result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.SetRansom buildPartial() {
      pb4client.SetRansom result = new pb4client.SetRansom(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.prisonPlayerId_ = prisonPlayerId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.setNum_ = setNum_;
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
      if (other instanceof pb4client.SetRansom) {
        return mergeFrom((pb4client.SetRansom)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.SetRansom other) {
      if (other == pb4client.SetRansom.getDefaultInstance()) return this;
      if (other.hasPrisonPlayerId()) {
        setPrisonPlayerId(other.getPrisonPlayerId());
      }
      if (other.hasSetNum()) {
        setSetNum(other.getSetNum());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasPrisonPlayerId()) {
        return false;
      }
      if (!hasSetNum()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.SetRansom parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.SetRansom) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long prisonPlayerId_ ;
    /**
     * <pre>
     * 要设置的玩家ID
     * </pre>
     *
     * <code>required int64 prisonPlayerId = 1;</code>
     */
    public boolean hasPrisonPlayerId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 要设置的玩家ID
     * </pre>
     *
     * <code>required int64 prisonPlayerId = 1;</code>
     */
    public long getPrisonPlayerId() {
      return prisonPlayerId_;
    }
    /**
     * <pre>
     * 要设置的玩家ID
     * </pre>
     *
     * <code>required int64 prisonPlayerId = 1;</code>
     */
    public Builder setPrisonPlayerId(long value) {
      bitField0_ |= 0x00000001;
      prisonPlayerId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 要设置的玩家ID
     * </pre>
     *
     * <code>required int64 prisonPlayerId = 1;</code>
     */
    public Builder clearPrisonPlayerId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      prisonPlayerId_ = 0L;
      onChanged();
      return this;
    }

    private long setNum_ ;
    /**
     * <pre>
     * 设置的值
     * </pre>
     *
     * <code>required int64 setNum = 2;</code>
     */
    public boolean hasSetNum() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 设置的值
     * </pre>
     *
     * <code>required int64 setNum = 2;</code>
     */
    public long getSetNum() {
      return setNum_;
    }
    /**
     * <pre>
     * 设置的值
     * </pre>
     *
     * <code>required int64 setNum = 2;</code>
     */
    public Builder setSetNum(long value) {
      bitField0_ |= 0x00000002;
      setNum_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 设置的值
     * </pre>
     *
     * <code>required int64 setNum = 2;</code>
     */
    public Builder clearSetNum() {
      bitField0_ = (bitField0_ & ~0x00000002);
      setNum_ = 0L;
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


    // @@protoc_insertion_point(builder_scope:client2server.SetRansom)
  }

  // @@protoc_insertion_point(class_scope:client2server.SetRansom)
  private static final pb4client.SetRansom DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.SetRansom();
  }

  public static pb4client.SetRansom getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<SetRansom>
      PARSER = new com.google.protobuf.AbstractParser<SetRansom>() {
    public SetRansom parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new SetRansom(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SetRansom> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SetRansom> getParserForType() {
    return PARSER;
  }

  public pb4client.SetRansom getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
