// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.ExtractChestRt}
 */
public  final class ExtractChestRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.ExtractChestRt)
    ExtractChestRtOrBuilder {
  // Use ExtractChestRt.newBuilder() to construct.
  private ExtractChestRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ExtractChestRt() {
    rt_ = 0;
    chestId_ = 0;
    prize_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ExtractChestRt(
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
          case 16: {
            bitField0_ |= 0x00000002;
            chestId_ = input.readInt32();
            break;
          }
          case 26: {
            if (!((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
              prize_ = new java.util.ArrayList<pb4client.ChestPrize>();
              mutable_bitField0_ |= 0x00000004;
            }
            prize_.add(
                input.readMessage(pb4client.ChestPrize.PARSER, extensionRegistry));
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
      if (((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
        prize_ = java.util.Collections.unmodifiableList(prize_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_ExtractChestRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_ExtractChestRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.ExtractChestRt.class, pb4client.ExtractChestRt.Builder.class);
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

  public static final int CHESTID_FIELD_NUMBER = 2;
  private int chestId_;
  /**
   * <pre>
   * 宝箱编号
   * </pre>
   *
   * <code>optional int32 chestId = 2;</code>
   */
  public boolean hasChestId() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 宝箱编号
   * </pre>
   *
   * <code>optional int32 chestId = 2;</code>
   */
  public int getChestId() {
    return chestId_;
  }

  public static final int PRIZE_FIELD_NUMBER = 3;
  private java.util.List<pb4client.ChestPrize> prize_;
  /**
   * <pre>
   * 奖励
   * </pre>
   *
   * <code>repeated .client2server.ChestPrize prize = 3;</code>
   */
  public java.util.List<pb4client.ChestPrize> getPrizeList() {
    return prize_;
  }
  /**
   * <pre>
   * 奖励
   * </pre>
   *
   * <code>repeated .client2server.ChestPrize prize = 3;</code>
   */
  public java.util.List<? extends pb4client.ChestPrizeOrBuilder> 
      getPrizeOrBuilderList() {
    return prize_;
  }
  /**
   * <pre>
   * 奖励
   * </pre>
   *
   * <code>repeated .client2server.ChestPrize prize = 3;</code>
   */
  public int getPrizeCount() {
    return prize_.size();
  }
  /**
   * <pre>
   * 奖励
   * </pre>
   *
   * <code>repeated .client2server.ChestPrize prize = 3;</code>
   */
  public pb4client.ChestPrize getPrize(int index) {
    return prize_.get(index);
  }
  /**
   * <pre>
   * 奖励
   * </pre>
   *
   * <code>repeated .client2server.ChestPrize prize = 3;</code>
   */
  public pb4client.ChestPrizeOrBuilder getPrizeOrBuilder(
      int index) {
    return prize_.get(index);
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
    for (int i = 0; i < getPrizeCount(); i++) {
      if (!getPrize(i).isInitialized()) {
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
      output.writeInt32(2, chestId_);
    }
    for (int i = 0; i < prize_.size(); i++) {
      output.writeMessage(3, prize_.get(i));
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
        .computeInt32Size(2, chestId_);
    }
    for (int i = 0; i < prize_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, prize_.get(i));
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
    if (!(obj instanceof pb4client.ExtractChestRt)) {
      return super.equals(obj);
    }
    pb4client.ExtractChestRt other = (pb4client.ExtractChestRt) obj;

    boolean result = true;
    result = result && (hasRt() == other.hasRt());
    if (hasRt()) {
      result = result && (getRt()
          == other.getRt());
    }
    result = result && (hasChestId() == other.hasChestId());
    if (hasChestId()) {
      result = result && (getChestId()
          == other.getChestId());
    }
    result = result && getPrizeList()
        .equals(other.getPrizeList());
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
    if (hasChestId()) {
      hash = (37 * hash) + CHESTID_FIELD_NUMBER;
      hash = (53 * hash) + getChestId();
    }
    if (getPrizeCount() > 0) {
      hash = (37 * hash) + PRIZE_FIELD_NUMBER;
      hash = (53 * hash) + getPrizeList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.ExtractChestRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.ExtractChestRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.ExtractChestRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.ExtractChestRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.ExtractChestRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.ExtractChestRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.ExtractChestRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.ExtractChestRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.ExtractChestRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.ExtractChestRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.ExtractChestRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.ExtractChestRt parseFrom(
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
  public static Builder newBuilder(pb4client.ExtractChestRt prototype) {
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
   * Protobuf type {@code client2server.ExtractChestRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.ExtractChestRt)
      pb4client.ExtractChestRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_ExtractChestRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_ExtractChestRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.ExtractChestRt.class, pb4client.ExtractChestRt.Builder.class);
    }

    // Construct using pb4client.ExtractChestRt.newBuilder()
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
        getPrizeFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      rt_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      chestId_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      if (prizeBuilder_ == null) {
        prize_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
      } else {
        prizeBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_ExtractChestRt_descriptor;
    }

    public pb4client.ExtractChestRt getDefaultInstanceForType() {
      return pb4client.ExtractChestRt.getDefaultInstance();
    }

    public pb4client.ExtractChestRt build() {
      pb4client.ExtractChestRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.ExtractChestRt buildPartial() {
      pb4client.ExtractChestRt result = new pb4client.ExtractChestRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.rt_ = rt_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.chestId_ = chestId_;
      if (prizeBuilder_ == null) {
        if (((bitField0_ & 0x00000004) == 0x00000004)) {
          prize_ = java.util.Collections.unmodifiableList(prize_);
          bitField0_ = (bitField0_ & ~0x00000004);
        }
        result.prize_ = prize_;
      } else {
        result.prize_ = prizeBuilder_.build();
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
      if (other instanceof pb4client.ExtractChestRt) {
        return mergeFrom((pb4client.ExtractChestRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.ExtractChestRt other) {
      if (other == pb4client.ExtractChestRt.getDefaultInstance()) return this;
      if (other.hasRt()) {
        setRt(other.getRt());
      }
      if (other.hasChestId()) {
        setChestId(other.getChestId());
      }
      if (prizeBuilder_ == null) {
        if (!other.prize_.isEmpty()) {
          if (prize_.isEmpty()) {
            prize_ = other.prize_;
            bitField0_ = (bitField0_ & ~0x00000004);
          } else {
            ensurePrizeIsMutable();
            prize_.addAll(other.prize_);
          }
          onChanged();
        }
      } else {
        if (!other.prize_.isEmpty()) {
          if (prizeBuilder_.isEmpty()) {
            prizeBuilder_.dispose();
            prizeBuilder_ = null;
            prize_ = other.prize_;
            bitField0_ = (bitField0_ & ~0x00000004);
            prizeBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getPrizeFieldBuilder() : null;
          } else {
            prizeBuilder_.addAllMessages(other.prize_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasRt()) {
        return false;
      }
      for (int i = 0; i < getPrizeCount(); i++) {
        if (!getPrize(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.ExtractChestRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.ExtractChestRt) e.getUnfinishedMessage();
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

    private int chestId_ ;
    /**
     * <pre>
     * 宝箱编号
     * </pre>
     *
     * <code>optional int32 chestId = 2;</code>
     */
    public boolean hasChestId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 宝箱编号
     * </pre>
     *
     * <code>optional int32 chestId = 2;</code>
     */
    public int getChestId() {
      return chestId_;
    }
    /**
     * <pre>
     * 宝箱编号
     * </pre>
     *
     * <code>optional int32 chestId = 2;</code>
     */
    public Builder setChestId(int value) {
      bitField0_ |= 0x00000002;
      chestId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 宝箱编号
     * </pre>
     *
     * <code>optional int32 chestId = 2;</code>
     */
    public Builder clearChestId() {
      bitField0_ = (bitField0_ & ~0x00000002);
      chestId_ = 0;
      onChanged();
      return this;
    }

    private java.util.List<pb4client.ChestPrize> prize_ =
      java.util.Collections.emptyList();
    private void ensurePrizeIsMutable() {
      if (!((bitField0_ & 0x00000004) == 0x00000004)) {
        prize_ = new java.util.ArrayList<pb4client.ChestPrize>(prize_);
        bitField0_ |= 0x00000004;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.ChestPrize, pb4client.ChestPrize.Builder, pb4client.ChestPrizeOrBuilder> prizeBuilder_;

    /**
     * <pre>
     * 奖励
     * </pre>
     *
     * <code>repeated .client2server.ChestPrize prize = 3;</code>
     */
    public java.util.List<pb4client.ChestPrize> getPrizeList() {
      if (prizeBuilder_ == null) {
        return java.util.Collections.unmodifiableList(prize_);
      } else {
        return prizeBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     * 奖励
     * </pre>
     *
     * <code>repeated .client2server.ChestPrize prize = 3;</code>
     */
    public int getPrizeCount() {
      if (prizeBuilder_ == null) {
        return prize_.size();
      } else {
        return prizeBuilder_.getCount();
      }
    }
    /**
     * <pre>
     * 奖励
     * </pre>
     *
     * <code>repeated .client2server.ChestPrize prize = 3;</code>
     */
    public pb4client.ChestPrize getPrize(int index) {
      if (prizeBuilder_ == null) {
        return prize_.get(index);
      } else {
        return prizeBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     * 奖励
     * </pre>
     *
     * <code>repeated .client2server.ChestPrize prize = 3;</code>
     */
    public Builder setPrize(
        int index, pb4client.ChestPrize value) {
      if (prizeBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensurePrizeIsMutable();
        prize_.set(index, value);
        onChanged();
      } else {
        prizeBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * 奖励
     * </pre>
     *
     * <code>repeated .client2server.ChestPrize prize = 3;</code>
     */
    public Builder setPrize(
        int index, pb4client.ChestPrize.Builder builderForValue) {
      if (prizeBuilder_ == null) {
        ensurePrizeIsMutable();
        prize_.set(index, builderForValue.build());
        onChanged();
      } else {
        prizeBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 奖励
     * </pre>
     *
     * <code>repeated .client2server.ChestPrize prize = 3;</code>
     */
    public Builder addPrize(pb4client.ChestPrize value) {
      if (prizeBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensurePrizeIsMutable();
        prize_.add(value);
        onChanged();
      } else {
        prizeBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     * 奖励
     * </pre>
     *
     * <code>repeated .client2server.ChestPrize prize = 3;</code>
     */
    public Builder addPrize(
        int index, pb4client.ChestPrize value) {
      if (prizeBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensurePrizeIsMutable();
        prize_.add(index, value);
        onChanged();
      } else {
        prizeBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * 奖励
     * </pre>
     *
     * <code>repeated .client2server.ChestPrize prize = 3;</code>
     */
    public Builder addPrize(
        pb4client.ChestPrize.Builder builderForValue) {
      if (prizeBuilder_ == null) {
        ensurePrizeIsMutable();
        prize_.add(builderForValue.build());
        onChanged();
      } else {
        prizeBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 奖励
     * </pre>
     *
     * <code>repeated .client2server.ChestPrize prize = 3;</code>
     */
    public Builder addPrize(
        int index, pb4client.ChestPrize.Builder builderForValue) {
      if (prizeBuilder_ == null) {
        ensurePrizeIsMutable();
        prize_.add(index, builderForValue.build());
        onChanged();
      } else {
        prizeBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 奖励
     * </pre>
     *
     * <code>repeated .client2server.ChestPrize prize = 3;</code>
     */
    public Builder addAllPrize(
        java.lang.Iterable<? extends pb4client.ChestPrize> values) {
      if (prizeBuilder_ == null) {
        ensurePrizeIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, prize_);
        onChanged();
      } else {
        prizeBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     * 奖励
     * </pre>
     *
     * <code>repeated .client2server.ChestPrize prize = 3;</code>
     */
    public Builder clearPrize() {
      if (prizeBuilder_ == null) {
        prize_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
        onChanged();
      } else {
        prizeBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     * 奖励
     * </pre>
     *
     * <code>repeated .client2server.ChestPrize prize = 3;</code>
     */
    public Builder removePrize(int index) {
      if (prizeBuilder_ == null) {
        ensurePrizeIsMutable();
        prize_.remove(index);
        onChanged();
      } else {
        prizeBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     * 奖励
     * </pre>
     *
     * <code>repeated .client2server.ChestPrize prize = 3;</code>
     */
    public pb4client.ChestPrize.Builder getPrizeBuilder(
        int index) {
      return getPrizeFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     * 奖励
     * </pre>
     *
     * <code>repeated .client2server.ChestPrize prize = 3;</code>
     */
    public pb4client.ChestPrizeOrBuilder getPrizeOrBuilder(
        int index) {
      if (prizeBuilder_ == null) {
        return prize_.get(index);  } else {
        return prizeBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     * 奖励
     * </pre>
     *
     * <code>repeated .client2server.ChestPrize prize = 3;</code>
     */
    public java.util.List<? extends pb4client.ChestPrizeOrBuilder> 
         getPrizeOrBuilderList() {
      if (prizeBuilder_ != null) {
        return prizeBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(prize_);
      }
    }
    /**
     * <pre>
     * 奖励
     * </pre>
     *
     * <code>repeated .client2server.ChestPrize prize = 3;</code>
     */
    public pb4client.ChestPrize.Builder addPrizeBuilder() {
      return getPrizeFieldBuilder().addBuilder(
          pb4client.ChestPrize.getDefaultInstance());
    }
    /**
     * <pre>
     * 奖励
     * </pre>
     *
     * <code>repeated .client2server.ChestPrize prize = 3;</code>
     */
    public pb4client.ChestPrize.Builder addPrizeBuilder(
        int index) {
      return getPrizeFieldBuilder().addBuilder(
          index, pb4client.ChestPrize.getDefaultInstance());
    }
    /**
     * <pre>
     * 奖励
     * </pre>
     *
     * <code>repeated .client2server.ChestPrize prize = 3;</code>
     */
    public java.util.List<pb4client.ChestPrize.Builder> 
         getPrizeBuilderList() {
      return getPrizeFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.ChestPrize, pb4client.ChestPrize.Builder, pb4client.ChestPrizeOrBuilder> 
        getPrizeFieldBuilder() {
      if (prizeBuilder_ == null) {
        prizeBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.ChestPrize, pb4client.ChestPrize.Builder, pb4client.ChestPrizeOrBuilder>(
                prize_,
                ((bitField0_ & 0x00000004) == 0x00000004),
                getParentForChildren(),
                isClean());
        prize_ = null;
      }
      return prizeBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.ExtractChestRt)
  }

  // @@protoc_insertion_point(class_scope:client2server.ExtractChestRt)
  private static final pb4client.ExtractChestRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.ExtractChestRt();
  }

  public static pb4client.ExtractChestRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<ExtractChestRt>
      PARSER = new com.google.protobuf.AbstractParser<ExtractChestRt>() {
    public ExtractChestRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new ExtractChestRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ExtractChestRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ExtractChestRt> getParserForType() {
    return PARSER;
  }

  public pb4client.ExtractChestRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

