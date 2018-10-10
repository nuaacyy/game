// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 824
 * 客户端 -&gt; 服务器
 * 玩家反叛（脱离沦陷的一种方式：上缴一定资源）
 * </pre>
 *
 * Protobuf type {@code client2server.AllianceRebelRes}
 */
public  final class AllianceRebelRes extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.AllianceRebelRes)
    AllianceRebelResOrBuilder {
  // Use AllianceRebelRes.newBuilder() to construct.
  private AllianceRebelRes(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AllianceRebelRes() {
    woodQty_ = 0L;
    ironQty_ = 0L;
    stoneQty_ = 0L;
    foodstuffQty_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AllianceRebelRes(
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
            woodQty_ = input.readInt64();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            ironQty_ = input.readInt64();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            stoneQty_ = input.readInt64();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            foodstuffQty_ = input.readInt64();
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
    return pb4client.War2GamePkt.internal_static_client2server_AllianceRebelRes_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_AllianceRebelRes_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.AllianceRebelRes.class, pb4client.AllianceRebelRes.Builder.class);
  }

  private int bitField0_;
  public static final int WOODQTY_FIELD_NUMBER = 1;
  private long woodQty_;
  /**
   * <pre>
   *捐献木料数量
   * </pre>
   *
   * <code>required int64 woodQty = 1;</code>
   */
  public boolean hasWoodQty() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *捐献木料数量
   * </pre>
   *
   * <code>required int64 woodQty = 1;</code>
   */
  public long getWoodQty() {
    return woodQty_;
  }

  public static final int IRONQTY_FIELD_NUMBER = 2;
  private long ironQty_;
  /**
   * <pre>
   *捐献铁矿数量
   * </pre>
   *
   * <code>required int64 ironQty = 2;</code>
   */
  public boolean hasIronQty() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *捐献铁矿数量
   * </pre>
   *
   * <code>required int64 ironQty = 2;</code>
   */
  public long getIronQty() {
    return ironQty_;
  }

  public static final int STONEQTY_FIELD_NUMBER = 3;
  private long stoneQty_;
  /**
   * <pre>
   *捐献石料数量
   * </pre>
   *
   * <code>required int64 stoneQty = 3;</code>
   */
  public boolean hasStoneQty() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   *捐献石料数量
   * </pre>
   *
   * <code>required int64 stoneQty = 3;</code>
   */
  public long getStoneQty() {
    return stoneQty_;
  }

  public static final int FOODSTUFFQTY_FIELD_NUMBER = 4;
  private long foodstuffQty_;
  /**
   * <pre>
   *捐献粮食数量
   * </pre>
   *
   * <code>required int64 foodstuffQty = 4;</code>
   */
  public boolean hasFoodstuffQty() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   *捐献粮食数量
   * </pre>
   *
   * <code>required int64 foodstuffQty = 4;</code>
   */
  public long getFoodstuffQty() {
    return foodstuffQty_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasWoodQty()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasIronQty()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasStoneQty()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasFoodstuffQty()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt64(1, woodQty_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt64(2, ironQty_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt64(3, stoneQty_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt64(4, foodstuffQty_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, woodQty_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, ironQty_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, stoneQty_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(4, foodstuffQty_);
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
    if (!(obj instanceof pb4client.AllianceRebelRes)) {
      return super.equals(obj);
    }
    pb4client.AllianceRebelRes other = (pb4client.AllianceRebelRes) obj;

    boolean result = true;
    result = result && (hasWoodQty() == other.hasWoodQty());
    if (hasWoodQty()) {
      result = result && (getWoodQty()
          == other.getWoodQty());
    }
    result = result && (hasIronQty() == other.hasIronQty());
    if (hasIronQty()) {
      result = result && (getIronQty()
          == other.getIronQty());
    }
    result = result && (hasStoneQty() == other.hasStoneQty());
    if (hasStoneQty()) {
      result = result && (getStoneQty()
          == other.getStoneQty());
    }
    result = result && (hasFoodstuffQty() == other.hasFoodstuffQty());
    if (hasFoodstuffQty()) {
      result = result && (getFoodstuffQty()
          == other.getFoodstuffQty());
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
    if (hasWoodQty()) {
      hash = (37 * hash) + WOODQTY_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getWoodQty());
    }
    if (hasIronQty()) {
      hash = (37 * hash) + IRONQTY_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getIronQty());
    }
    if (hasStoneQty()) {
      hash = (37 * hash) + STONEQTY_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getStoneQty());
    }
    if (hasFoodstuffQty()) {
      hash = (37 * hash) + FOODSTUFFQTY_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getFoodstuffQty());
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.AllianceRebelRes parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceRebelRes parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceRebelRes parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceRebelRes parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceRebelRes parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceRebelRes parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceRebelRes parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.AllianceRebelRes parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.AllianceRebelRes parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.AllianceRebelRes parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.AllianceRebelRes parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.AllianceRebelRes parseFrom(
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
  public static Builder newBuilder(pb4client.AllianceRebelRes prototype) {
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
   * msgType = 824
   * 客户端 -&gt; 服务器
   * 玩家反叛（脱离沦陷的一种方式：上缴一定资源）
   * </pre>
   *
   * Protobuf type {@code client2server.AllianceRebelRes}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.AllianceRebelRes)
      pb4client.AllianceRebelResOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceRebelRes_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceRebelRes_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.AllianceRebelRes.class, pb4client.AllianceRebelRes.Builder.class);
    }

    // Construct using pb4client.AllianceRebelRes.newBuilder()
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
      woodQty_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      ironQty_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000002);
      stoneQty_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000004);
      foodstuffQty_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000008);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceRebelRes_descriptor;
    }

    public pb4client.AllianceRebelRes getDefaultInstanceForType() {
      return pb4client.AllianceRebelRes.getDefaultInstance();
    }

    public pb4client.AllianceRebelRes build() {
      pb4client.AllianceRebelRes result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.AllianceRebelRes buildPartial() {
      pb4client.AllianceRebelRes result = new pb4client.AllianceRebelRes(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.woodQty_ = woodQty_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.ironQty_ = ironQty_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.stoneQty_ = stoneQty_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.foodstuffQty_ = foodstuffQty_;
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
      if (other instanceof pb4client.AllianceRebelRes) {
        return mergeFrom((pb4client.AllianceRebelRes)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.AllianceRebelRes other) {
      if (other == pb4client.AllianceRebelRes.getDefaultInstance()) return this;
      if (other.hasWoodQty()) {
        setWoodQty(other.getWoodQty());
      }
      if (other.hasIronQty()) {
        setIronQty(other.getIronQty());
      }
      if (other.hasStoneQty()) {
        setStoneQty(other.getStoneQty());
      }
      if (other.hasFoodstuffQty()) {
        setFoodstuffQty(other.getFoodstuffQty());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasWoodQty()) {
        return false;
      }
      if (!hasIronQty()) {
        return false;
      }
      if (!hasStoneQty()) {
        return false;
      }
      if (!hasFoodstuffQty()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.AllianceRebelRes parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.AllianceRebelRes) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long woodQty_ ;
    /**
     * <pre>
     *捐献木料数量
     * </pre>
     *
     * <code>required int64 woodQty = 1;</code>
     */
    public boolean hasWoodQty() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *捐献木料数量
     * </pre>
     *
     * <code>required int64 woodQty = 1;</code>
     */
    public long getWoodQty() {
      return woodQty_;
    }
    /**
     * <pre>
     *捐献木料数量
     * </pre>
     *
     * <code>required int64 woodQty = 1;</code>
     */
    public Builder setWoodQty(long value) {
      bitField0_ |= 0x00000001;
      woodQty_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *捐献木料数量
     * </pre>
     *
     * <code>required int64 woodQty = 1;</code>
     */
    public Builder clearWoodQty() {
      bitField0_ = (bitField0_ & ~0x00000001);
      woodQty_ = 0L;
      onChanged();
      return this;
    }

    private long ironQty_ ;
    /**
     * <pre>
     *捐献铁矿数量
     * </pre>
     *
     * <code>required int64 ironQty = 2;</code>
     */
    public boolean hasIronQty() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *捐献铁矿数量
     * </pre>
     *
     * <code>required int64 ironQty = 2;</code>
     */
    public long getIronQty() {
      return ironQty_;
    }
    /**
     * <pre>
     *捐献铁矿数量
     * </pre>
     *
     * <code>required int64 ironQty = 2;</code>
     */
    public Builder setIronQty(long value) {
      bitField0_ |= 0x00000002;
      ironQty_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *捐献铁矿数量
     * </pre>
     *
     * <code>required int64 ironQty = 2;</code>
     */
    public Builder clearIronQty() {
      bitField0_ = (bitField0_ & ~0x00000002);
      ironQty_ = 0L;
      onChanged();
      return this;
    }

    private long stoneQty_ ;
    /**
     * <pre>
     *捐献石料数量
     * </pre>
     *
     * <code>required int64 stoneQty = 3;</code>
     */
    public boolean hasStoneQty() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     *捐献石料数量
     * </pre>
     *
     * <code>required int64 stoneQty = 3;</code>
     */
    public long getStoneQty() {
      return stoneQty_;
    }
    /**
     * <pre>
     *捐献石料数量
     * </pre>
     *
     * <code>required int64 stoneQty = 3;</code>
     */
    public Builder setStoneQty(long value) {
      bitField0_ |= 0x00000004;
      stoneQty_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *捐献石料数量
     * </pre>
     *
     * <code>required int64 stoneQty = 3;</code>
     */
    public Builder clearStoneQty() {
      bitField0_ = (bitField0_ & ~0x00000004);
      stoneQty_ = 0L;
      onChanged();
      return this;
    }

    private long foodstuffQty_ ;
    /**
     * <pre>
     *捐献粮食数量
     * </pre>
     *
     * <code>required int64 foodstuffQty = 4;</code>
     */
    public boolean hasFoodstuffQty() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     *捐献粮食数量
     * </pre>
     *
     * <code>required int64 foodstuffQty = 4;</code>
     */
    public long getFoodstuffQty() {
      return foodstuffQty_;
    }
    /**
     * <pre>
     *捐献粮食数量
     * </pre>
     *
     * <code>required int64 foodstuffQty = 4;</code>
     */
    public Builder setFoodstuffQty(long value) {
      bitField0_ |= 0x00000008;
      foodstuffQty_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *捐献粮食数量
     * </pre>
     *
     * <code>required int64 foodstuffQty = 4;</code>
     */
    public Builder clearFoodstuffQty() {
      bitField0_ = (bitField0_ & ~0x00000008);
      foodstuffQty_ = 0L;
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


    // @@protoc_insertion_point(builder_scope:client2server.AllianceRebelRes)
  }

  // @@protoc_insertion_point(class_scope:client2server.AllianceRebelRes)
  private static final pb4client.AllianceRebelRes DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.AllianceRebelRes();
  }

  public static pb4client.AllianceRebelRes getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<AllianceRebelRes>
      PARSER = new com.google.protobuf.AbstractParser<AllianceRebelRes>() {
    public AllianceRebelRes parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new AllianceRebelRes(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AllianceRebelRes> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AllianceRebelRes> getParserForType() {
    return PARSER;
  }

  public pb4client.AllianceRebelRes getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

