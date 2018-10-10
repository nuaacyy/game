// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 3165
 * 服务器 -&gt; 客户端
 * 产量上限变化主推
 * </pre>
 *
 * Protobuf type {@code client2server.StoreLimitChange}
 */
public  final class StoreLimitChange extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.StoreLimitChange)
    StoreLimitChangeOrBuilder {
  // Use StoreLimitChange.newBuilder() to construct.
  private StoreLimitChange(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private StoreLimitChange() {
    woodLimit_ = 0L;
    ironLimit_ = 0L;
    foodLimit_ = 0L;
    stoneLimit_ = 0L;
    coinLimit_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private StoreLimitChange(
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
            woodLimit_ = input.readInt64();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            ironLimit_ = input.readInt64();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            foodLimit_ = input.readInt64();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            stoneLimit_ = input.readInt64();
            break;
          }
          case 40: {
            bitField0_ |= 0x00000010;
            coinLimit_ = input.readInt64();
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
    return pb4client.War2GamePkt.internal_static_client2server_StoreLimitChange_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_StoreLimitChange_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.StoreLimitChange.class, pb4client.StoreLimitChange.Builder.class);
  }

  private int bitField0_;
  public static final int WOODLIMIT_FIELD_NUMBER = 1;
  private long woodLimit_;
  /**
   * <code>required int64 woodLimit = 1;</code>
   */
  public boolean hasWoodLimit() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>required int64 woodLimit = 1;</code>
   */
  public long getWoodLimit() {
    return woodLimit_;
  }

  public static final int IRONLIMIT_FIELD_NUMBER = 2;
  private long ironLimit_;
  /**
   * <code>required int64 ironLimit = 2;</code>
   */
  public boolean hasIronLimit() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>required int64 ironLimit = 2;</code>
   */
  public long getIronLimit() {
    return ironLimit_;
  }

  public static final int FOODLIMIT_FIELD_NUMBER = 3;
  private long foodLimit_;
  /**
   * <code>required int64 foodLimit = 3;</code>
   */
  public boolean hasFoodLimit() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <code>required int64 foodLimit = 3;</code>
   */
  public long getFoodLimit() {
    return foodLimit_;
  }

  public static final int STONELIMIT_FIELD_NUMBER = 4;
  private long stoneLimit_;
  /**
   * <code>required int64 stoneLimit = 4;</code>
   */
  public boolean hasStoneLimit() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <code>required int64 stoneLimit = 4;</code>
   */
  public long getStoneLimit() {
    return stoneLimit_;
  }

  public static final int COINLIMIT_FIELD_NUMBER = 5;
  private long coinLimit_;
  /**
   * <code>required int64 coinLimit = 5;</code>
   */
  public boolean hasCoinLimit() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <code>required int64 coinLimit = 5;</code>
   */
  public long getCoinLimit() {
    return coinLimit_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasWoodLimit()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasIronLimit()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasFoodLimit()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasStoneLimit()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasCoinLimit()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt64(1, woodLimit_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt64(2, ironLimit_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt64(3, foodLimit_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt64(4, stoneLimit_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      output.writeInt64(5, coinLimit_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, woodLimit_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, ironLimit_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, foodLimit_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(4, stoneLimit_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(5, coinLimit_);
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
    if (!(obj instanceof pb4client.StoreLimitChange)) {
      return super.equals(obj);
    }
    pb4client.StoreLimitChange other = (pb4client.StoreLimitChange) obj;

    boolean result = true;
    result = result && (hasWoodLimit() == other.hasWoodLimit());
    if (hasWoodLimit()) {
      result = result && (getWoodLimit()
          == other.getWoodLimit());
    }
    result = result && (hasIronLimit() == other.hasIronLimit());
    if (hasIronLimit()) {
      result = result && (getIronLimit()
          == other.getIronLimit());
    }
    result = result && (hasFoodLimit() == other.hasFoodLimit());
    if (hasFoodLimit()) {
      result = result && (getFoodLimit()
          == other.getFoodLimit());
    }
    result = result && (hasStoneLimit() == other.hasStoneLimit());
    if (hasStoneLimit()) {
      result = result && (getStoneLimit()
          == other.getStoneLimit());
    }
    result = result && (hasCoinLimit() == other.hasCoinLimit());
    if (hasCoinLimit()) {
      result = result && (getCoinLimit()
          == other.getCoinLimit());
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
    if (hasWoodLimit()) {
      hash = (37 * hash) + WOODLIMIT_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getWoodLimit());
    }
    if (hasIronLimit()) {
      hash = (37 * hash) + IRONLIMIT_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getIronLimit());
    }
    if (hasFoodLimit()) {
      hash = (37 * hash) + FOODLIMIT_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getFoodLimit());
    }
    if (hasStoneLimit()) {
      hash = (37 * hash) + STONELIMIT_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getStoneLimit());
    }
    if (hasCoinLimit()) {
      hash = (37 * hash) + COINLIMIT_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getCoinLimit());
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.StoreLimitChange parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.StoreLimitChange parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.StoreLimitChange parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.StoreLimitChange parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.StoreLimitChange parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.StoreLimitChange parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.StoreLimitChange parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.StoreLimitChange parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.StoreLimitChange parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.StoreLimitChange parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.StoreLimitChange parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.StoreLimitChange parseFrom(
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
  public static Builder newBuilder(pb4client.StoreLimitChange prototype) {
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
   * msgType = 3165
   * 服务器 -&gt; 客户端
   * 产量上限变化主推
   * </pre>
   *
   * Protobuf type {@code client2server.StoreLimitChange}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.StoreLimitChange)
      pb4client.StoreLimitChangeOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_StoreLimitChange_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_StoreLimitChange_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.StoreLimitChange.class, pb4client.StoreLimitChange.Builder.class);
    }

    // Construct using pb4client.StoreLimitChange.newBuilder()
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
      woodLimit_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      ironLimit_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000002);
      foodLimit_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000004);
      stoneLimit_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000008);
      coinLimit_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000010);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_StoreLimitChange_descriptor;
    }

    public pb4client.StoreLimitChange getDefaultInstanceForType() {
      return pb4client.StoreLimitChange.getDefaultInstance();
    }

    public pb4client.StoreLimitChange build() {
      pb4client.StoreLimitChange result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.StoreLimitChange buildPartial() {
      pb4client.StoreLimitChange result = new pb4client.StoreLimitChange(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.woodLimit_ = woodLimit_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.ironLimit_ = ironLimit_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.foodLimit_ = foodLimit_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.stoneLimit_ = stoneLimit_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.coinLimit_ = coinLimit_;
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
      if (other instanceof pb4client.StoreLimitChange) {
        return mergeFrom((pb4client.StoreLimitChange)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.StoreLimitChange other) {
      if (other == pb4client.StoreLimitChange.getDefaultInstance()) return this;
      if (other.hasWoodLimit()) {
        setWoodLimit(other.getWoodLimit());
      }
      if (other.hasIronLimit()) {
        setIronLimit(other.getIronLimit());
      }
      if (other.hasFoodLimit()) {
        setFoodLimit(other.getFoodLimit());
      }
      if (other.hasStoneLimit()) {
        setStoneLimit(other.getStoneLimit());
      }
      if (other.hasCoinLimit()) {
        setCoinLimit(other.getCoinLimit());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasWoodLimit()) {
        return false;
      }
      if (!hasIronLimit()) {
        return false;
      }
      if (!hasFoodLimit()) {
        return false;
      }
      if (!hasStoneLimit()) {
        return false;
      }
      if (!hasCoinLimit()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.StoreLimitChange parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.StoreLimitChange) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long woodLimit_ ;
    /**
     * <code>required int64 woodLimit = 1;</code>
     */
    public boolean hasWoodLimit() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int64 woodLimit = 1;</code>
     */
    public long getWoodLimit() {
      return woodLimit_;
    }
    /**
     * <code>required int64 woodLimit = 1;</code>
     */
    public Builder setWoodLimit(long value) {
      bitField0_ |= 0x00000001;
      woodLimit_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int64 woodLimit = 1;</code>
     */
    public Builder clearWoodLimit() {
      bitField0_ = (bitField0_ & ~0x00000001);
      woodLimit_ = 0L;
      onChanged();
      return this;
    }

    private long ironLimit_ ;
    /**
     * <code>required int64 ironLimit = 2;</code>
     */
    public boolean hasIronLimit() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required int64 ironLimit = 2;</code>
     */
    public long getIronLimit() {
      return ironLimit_;
    }
    /**
     * <code>required int64 ironLimit = 2;</code>
     */
    public Builder setIronLimit(long value) {
      bitField0_ |= 0x00000002;
      ironLimit_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int64 ironLimit = 2;</code>
     */
    public Builder clearIronLimit() {
      bitField0_ = (bitField0_ & ~0x00000002);
      ironLimit_ = 0L;
      onChanged();
      return this;
    }

    private long foodLimit_ ;
    /**
     * <code>required int64 foodLimit = 3;</code>
     */
    public boolean hasFoodLimit() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required int64 foodLimit = 3;</code>
     */
    public long getFoodLimit() {
      return foodLimit_;
    }
    /**
     * <code>required int64 foodLimit = 3;</code>
     */
    public Builder setFoodLimit(long value) {
      bitField0_ |= 0x00000004;
      foodLimit_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int64 foodLimit = 3;</code>
     */
    public Builder clearFoodLimit() {
      bitField0_ = (bitField0_ & ~0x00000004);
      foodLimit_ = 0L;
      onChanged();
      return this;
    }

    private long stoneLimit_ ;
    /**
     * <code>required int64 stoneLimit = 4;</code>
     */
    public boolean hasStoneLimit() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>required int64 stoneLimit = 4;</code>
     */
    public long getStoneLimit() {
      return stoneLimit_;
    }
    /**
     * <code>required int64 stoneLimit = 4;</code>
     */
    public Builder setStoneLimit(long value) {
      bitField0_ |= 0x00000008;
      stoneLimit_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int64 stoneLimit = 4;</code>
     */
    public Builder clearStoneLimit() {
      bitField0_ = (bitField0_ & ~0x00000008);
      stoneLimit_ = 0L;
      onChanged();
      return this;
    }

    private long coinLimit_ ;
    /**
     * <code>required int64 coinLimit = 5;</code>
     */
    public boolean hasCoinLimit() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <code>required int64 coinLimit = 5;</code>
     */
    public long getCoinLimit() {
      return coinLimit_;
    }
    /**
     * <code>required int64 coinLimit = 5;</code>
     */
    public Builder setCoinLimit(long value) {
      bitField0_ |= 0x00000010;
      coinLimit_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int64 coinLimit = 5;</code>
     */
    public Builder clearCoinLimit() {
      bitField0_ = (bitField0_ & ~0x00000010);
      coinLimit_ = 0L;
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


    // @@protoc_insertion_point(builder_scope:client2server.StoreLimitChange)
  }

  // @@protoc_insertion_point(class_scope:client2server.StoreLimitChange)
  private static final pb4client.StoreLimitChange DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.StoreLimitChange();
  }

  public static pb4client.StoreLimitChange getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<StoreLimitChange>
      PARSER = new com.google.protobuf.AbstractParser<StoreLimitChange>() {
    public StoreLimitChange parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new StoreLimitChange(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<StoreLimitChange> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<StoreLimitChange> getParserForType() {
    return PARSER;
  }

  public pb4client.StoreLimitChange getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

