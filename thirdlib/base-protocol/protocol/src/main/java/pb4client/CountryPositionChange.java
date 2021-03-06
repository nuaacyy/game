// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 3166
 * 服务器 -&gt; 客户端
 * 官职变化主推
 * </pre>
 *
 * Protobuf type {@code client2server.CountryPositionChange}
 */
public  final class CountryPositionChange extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.CountryPositionChange)
    CountryPositionChangeOrBuilder {
  // Use CountryPositionChange.newBuilder() to construct.
  private CountryPositionChange(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CountryPositionChange() {
    playerId_ = 0L;
    currentPos_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private CountryPositionChange(
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
            playerId_ = input.readInt64();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            currentPos_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_CountryPositionChange_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_CountryPositionChange_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.CountryPositionChange.class, pb4client.CountryPositionChange.Builder.class);
  }

  private int bitField0_;
  public static final int PLAYERID_FIELD_NUMBER = 1;
  private long playerId_;
  /**
   * <pre>
   *玩家ID
   * </pre>
   *
   * <code>required int64 playerId = 1;</code>
   */
  public boolean hasPlayerId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *玩家ID
   * </pre>
   *
   * <code>required int64 playerId = 1;</code>
   */
  public long getPlayerId() {
    return playerId_;
  }

  public static final int CURRENTPOS_FIELD_NUMBER = 2;
  private int currentPos_;
  /**
   * <pre>
   *当前的官职信息
   * </pre>
   *
   * <code>required int32 currentPos = 2;</code>
   */
  public boolean hasCurrentPos() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *当前的官职信息
   * </pre>
   *
   * <code>required int32 currentPos = 2;</code>
   */
  public int getCurrentPos() {
    return currentPos_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasPlayerId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasCurrentPos()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt64(1, playerId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, currentPos_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, playerId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, currentPos_);
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
    if (!(obj instanceof pb4client.CountryPositionChange)) {
      return super.equals(obj);
    }
    pb4client.CountryPositionChange other = (pb4client.CountryPositionChange) obj;

    boolean result = true;
    result = result && (hasPlayerId() == other.hasPlayerId());
    if (hasPlayerId()) {
      result = result && (getPlayerId()
          == other.getPlayerId());
    }
    result = result && (hasCurrentPos() == other.hasCurrentPos());
    if (hasCurrentPos()) {
      result = result && (getCurrentPos()
          == other.getCurrentPos());
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
    if (hasPlayerId()) {
      hash = (37 * hash) + PLAYERID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getPlayerId());
    }
    if (hasCurrentPos()) {
      hash = (37 * hash) + CURRENTPOS_FIELD_NUMBER;
      hash = (53 * hash) + getCurrentPos();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.CountryPositionChange parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.CountryPositionChange parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.CountryPositionChange parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.CountryPositionChange parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.CountryPositionChange parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.CountryPositionChange parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.CountryPositionChange parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.CountryPositionChange parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.CountryPositionChange parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.CountryPositionChange parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.CountryPositionChange parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.CountryPositionChange parseFrom(
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
  public static Builder newBuilder(pb4client.CountryPositionChange prototype) {
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
   * msgType = 3166
   * 服务器 -&gt; 客户端
   * 官职变化主推
   * </pre>
   *
   * Protobuf type {@code client2server.CountryPositionChange}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.CountryPositionChange)
      pb4client.CountryPositionChangeOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_CountryPositionChange_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_CountryPositionChange_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.CountryPositionChange.class, pb4client.CountryPositionChange.Builder.class);
    }

    // Construct using pb4client.CountryPositionChange.newBuilder()
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
      playerId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      currentPos_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_CountryPositionChange_descriptor;
    }

    public pb4client.CountryPositionChange getDefaultInstanceForType() {
      return pb4client.CountryPositionChange.getDefaultInstance();
    }

    public pb4client.CountryPositionChange build() {
      pb4client.CountryPositionChange result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.CountryPositionChange buildPartial() {
      pb4client.CountryPositionChange result = new pb4client.CountryPositionChange(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.playerId_ = playerId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.currentPos_ = currentPos_;
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
      if (other instanceof pb4client.CountryPositionChange) {
        return mergeFrom((pb4client.CountryPositionChange)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.CountryPositionChange other) {
      if (other == pb4client.CountryPositionChange.getDefaultInstance()) return this;
      if (other.hasPlayerId()) {
        setPlayerId(other.getPlayerId());
      }
      if (other.hasCurrentPos()) {
        setCurrentPos(other.getCurrentPos());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasPlayerId()) {
        return false;
      }
      if (!hasCurrentPos()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.CountryPositionChange parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.CountryPositionChange) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long playerId_ ;
    /**
     * <pre>
     *玩家ID
     * </pre>
     *
     * <code>required int64 playerId = 1;</code>
     */
    public boolean hasPlayerId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *玩家ID
     * </pre>
     *
     * <code>required int64 playerId = 1;</code>
     */
    public long getPlayerId() {
      return playerId_;
    }
    /**
     * <pre>
     *玩家ID
     * </pre>
     *
     * <code>required int64 playerId = 1;</code>
     */
    public Builder setPlayerId(long value) {
      bitField0_ |= 0x00000001;
      playerId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *玩家ID
     * </pre>
     *
     * <code>required int64 playerId = 1;</code>
     */
    public Builder clearPlayerId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      playerId_ = 0L;
      onChanged();
      return this;
    }

    private int currentPos_ ;
    /**
     * <pre>
     *当前的官职信息
     * </pre>
     *
     * <code>required int32 currentPos = 2;</code>
     */
    public boolean hasCurrentPos() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *当前的官职信息
     * </pre>
     *
     * <code>required int32 currentPos = 2;</code>
     */
    public int getCurrentPos() {
      return currentPos_;
    }
    /**
     * <pre>
     *当前的官职信息
     * </pre>
     *
     * <code>required int32 currentPos = 2;</code>
     */
    public Builder setCurrentPos(int value) {
      bitField0_ |= 0x00000002;
      currentPos_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *当前的官职信息
     * </pre>
     *
     * <code>required int32 currentPos = 2;</code>
     */
    public Builder clearCurrentPos() {
      bitField0_ = (bitField0_ & ~0x00000002);
      currentPos_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.CountryPositionChange)
  }

  // @@protoc_insertion_point(class_scope:client2server.CountryPositionChange)
  private static final pb4client.CountryPositionChange DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.CountryPositionChange();
  }

  public static pb4client.CountryPositionChange getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<CountryPositionChange>
      PARSER = new com.google.protobuf.AbstractParser<CountryPositionChange>() {
    public CountryPositionChange parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new CountryPositionChange(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<CountryPositionChange> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CountryPositionChange> getParserForType() {
    return PARSER;
  }

  public pb4client.CountryPositionChange getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

