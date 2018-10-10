// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.GetJackpotInfoRt}
 */
public  final class GetJackpotInfoRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.GetJackpotInfoRt)
    GetJackpotInfoRtOrBuilder {
  // Use GetJackpotInfoRt.newBuilder() to construct.
  private GetJackpotInfoRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GetJackpotInfoRt() {
    rt_ = 0;
    totalMoney_ = 0L;
    casinosWinner_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GetJackpotInfoRt(
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
            totalMoney_ = input.readInt64();
            break;
          }
          case 26: {
            if (!((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
              casinosWinner_ = new java.util.ArrayList<pb4client.CasinosWinner>();
              mutable_bitField0_ |= 0x00000004;
            }
            casinosWinner_.add(
                input.readMessage(pb4client.CasinosWinner.PARSER, extensionRegistry));
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
        casinosWinner_ = java.util.Collections.unmodifiableList(casinosWinner_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_GetJackpotInfoRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_GetJackpotInfoRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.GetJackpotInfoRt.class, pb4client.GetJackpotInfoRt.Builder.class);
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

  public static final int TOTALMONEY_FIELD_NUMBER = 2;
  private long totalMoney_;
  /**
   * <pre>
   * 奖池里的总数
   * </pre>
   *
   * <code>optional int64 totalMoney = 2;</code>
   */
  public boolean hasTotalMoney() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 奖池里的总数
   * </pre>
   *
   * <code>optional int64 totalMoney = 2;</code>
   */
  public long getTotalMoney() {
    return totalMoney_;
  }

  public static final int CASINOSWINNER_FIELD_NUMBER = 3;
  private java.util.List<pb4client.CasinosWinner> casinosWinner_;
  /**
   * <pre>
   * 中奖的人
   * </pre>
   *
   * <code>repeated .client2server.CasinosWinner casinosWinner = 3;</code>
   */
  public java.util.List<pb4client.CasinosWinner> getCasinosWinnerList() {
    return casinosWinner_;
  }
  /**
   * <pre>
   * 中奖的人
   * </pre>
   *
   * <code>repeated .client2server.CasinosWinner casinosWinner = 3;</code>
   */
  public java.util.List<? extends pb4client.CasinosWinnerOrBuilder> 
      getCasinosWinnerOrBuilderList() {
    return casinosWinner_;
  }
  /**
   * <pre>
   * 中奖的人
   * </pre>
   *
   * <code>repeated .client2server.CasinosWinner casinosWinner = 3;</code>
   */
  public int getCasinosWinnerCount() {
    return casinosWinner_.size();
  }
  /**
   * <pre>
   * 中奖的人
   * </pre>
   *
   * <code>repeated .client2server.CasinosWinner casinosWinner = 3;</code>
   */
  public pb4client.CasinosWinner getCasinosWinner(int index) {
    return casinosWinner_.get(index);
  }
  /**
   * <pre>
   * 中奖的人
   * </pre>
   *
   * <code>repeated .client2server.CasinosWinner casinosWinner = 3;</code>
   */
  public pb4client.CasinosWinnerOrBuilder getCasinosWinnerOrBuilder(
      int index) {
    return casinosWinner_.get(index);
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
    for (int i = 0; i < getCasinosWinnerCount(); i++) {
      if (!getCasinosWinner(i).isInitialized()) {
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
      output.writeInt64(2, totalMoney_);
    }
    for (int i = 0; i < casinosWinner_.size(); i++) {
      output.writeMessage(3, casinosWinner_.get(i));
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
        .computeInt64Size(2, totalMoney_);
    }
    for (int i = 0; i < casinosWinner_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, casinosWinner_.get(i));
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
    if (!(obj instanceof pb4client.GetJackpotInfoRt)) {
      return super.equals(obj);
    }
    pb4client.GetJackpotInfoRt other = (pb4client.GetJackpotInfoRt) obj;

    boolean result = true;
    result = result && (hasRt() == other.hasRt());
    if (hasRt()) {
      result = result && (getRt()
          == other.getRt());
    }
    result = result && (hasTotalMoney() == other.hasTotalMoney());
    if (hasTotalMoney()) {
      result = result && (getTotalMoney()
          == other.getTotalMoney());
    }
    result = result && getCasinosWinnerList()
        .equals(other.getCasinosWinnerList());
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
    if (hasTotalMoney()) {
      hash = (37 * hash) + TOTALMONEY_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getTotalMoney());
    }
    if (getCasinosWinnerCount() > 0) {
      hash = (37 * hash) + CASINOSWINNER_FIELD_NUMBER;
      hash = (53 * hash) + getCasinosWinnerList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.GetJackpotInfoRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.GetJackpotInfoRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.GetJackpotInfoRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.GetJackpotInfoRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.GetJackpotInfoRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.GetJackpotInfoRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.GetJackpotInfoRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.GetJackpotInfoRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.GetJackpotInfoRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.GetJackpotInfoRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.GetJackpotInfoRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.GetJackpotInfoRt parseFrom(
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
  public static Builder newBuilder(pb4client.GetJackpotInfoRt prototype) {
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
   * Protobuf type {@code client2server.GetJackpotInfoRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.GetJackpotInfoRt)
      pb4client.GetJackpotInfoRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_GetJackpotInfoRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_GetJackpotInfoRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.GetJackpotInfoRt.class, pb4client.GetJackpotInfoRt.Builder.class);
    }

    // Construct using pb4client.GetJackpotInfoRt.newBuilder()
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
        getCasinosWinnerFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      rt_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      totalMoney_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000002);
      if (casinosWinnerBuilder_ == null) {
        casinosWinner_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
      } else {
        casinosWinnerBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_GetJackpotInfoRt_descriptor;
    }

    public pb4client.GetJackpotInfoRt getDefaultInstanceForType() {
      return pb4client.GetJackpotInfoRt.getDefaultInstance();
    }

    public pb4client.GetJackpotInfoRt build() {
      pb4client.GetJackpotInfoRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.GetJackpotInfoRt buildPartial() {
      pb4client.GetJackpotInfoRt result = new pb4client.GetJackpotInfoRt(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.rt_ = rt_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.totalMoney_ = totalMoney_;
      if (casinosWinnerBuilder_ == null) {
        if (((bitField0_ & 0x00000004) == 0x00000004)) {
          casinosWinner_ = java.util.Collections.unmodifiableList(casinosWinner_);
          bitField0_ = (bitField0_ & ~0x00000004);
        }
        result.casinosWinner_ = casinosWinner_;
      } else {
        result.casinosWinner_ = casinosWinnerBuilder_.build();
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
      if (other instanceof pb4client.GetJackpotInfoRt) {
        return mergeFrom((pb4client.GetJackpotInfoRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.GetJackpotInfoRt other) {
      if (other == pb4client.GetJackpotInfoRt.getDefaultInstance()) return this;
      if (other.hasRt()) {
        setRt(other.getRt());
      }
      if (other.hasTotalMoney()) {
        setTotalMoney(other.getTotalMoney());
      }
      if (casinosWinnerBuilder_ == null) {
        if (!other.casinosWinner_.isEmpty()) {
          if (casinosWinner_.isEmpty()) {
            casinosWinner_ = other.casinosWinner_;
            bitField0_ = (bitField0_ & ~0x00000004);
          } else {
            ensureCasinosWinnerIsMutable();
            casinosWinner_.addAll(other.casinosWinner_);
          }
          onChanged();
        }
      } else {
        if (!other.casinosWinner_.isEmpty()) {
          if (casinosWinnerBuilder_.isEmpty()) {
            casinosWinnerBuilder_.dispose();
            casinosWinnerBuilder_ = null;
            casinosWinner_ = other.casinosWinner_;
            bitField0_ = (bitField0_ & ~0x00000004);
            casinosWinnerBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getCasinosWinnerFieldBuilder() : null;
          } else {
            casinosWinnerBuilder_.addAllMessages(other.casinosWinner_);
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
      for (int i = 0; i < getCasinosWinnerCount(); i++) {
        if (!getCasinosWinner(i).isInitialized()) {
          return false;
        }
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.GetJackpotInfoRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.GetJackpotInfoRt) e.getUnfinishedMessage();
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

    private long totalMoney_ ;
    /**
     * <pre>
     * 奖池里的总数
     * </pre>
     *
     * <code>optional int64 totalMoney = 2;</code>
     */
    public boolean hasTotalMoney() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 奖池里的总数
     * </pre>
     *
     * <code>optional int64 totalMoney = 2;</code>
     */
    public long getTotalMoney() {
      return totalMoney_;
    }
    /**
     * <pre>
     * 奖池里的总数
     * </pre>
     *
     * <code>optional int64 totalMoney = 2;</code>
     */
    public Builder setTotalMoney(long value) {
      bitField0_ |= 0x00000002;
      totalMoney_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 奖池里的总数
     * </pre>
     *
     * <code>optional int64 totalMoney = 2;</code>
     */
    public Builder clearTotalMoney() {
      bitField0_ = (bitField0_ & ~0x00000002);
      totalMoney_ = 0L;
      onChanged();
      return this;
    }

    private java.util.List<pb4client.CasinosWinner> casinosWinner_ =
      java.util.Collections.emptyList();
    private void ensureCasinosWinnerIsMutable() {
      if (!((bitField0_ & 0x00000004) == 0x00000004)) {
        casinosWinner_ = new java.util.ArrayList<pb4client.CasinosWinner>(casinosWinner_);
        bitField0_ |= 0x00000004;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.CasinosWinner, pb4client.CasinosWinner.Builder, pb4client.CasinosWinnerOrBuilder> casinosWinnerBuilder_;

    /**
     * <pre>
     * 中奖的人
     * </pre>
     *
     * <code>repeated .client2server.CasinosWinner casinosWinner = 3;</code>
     */
    public java.util.List<pb4client.CasinosWinner> getCasinosWinnerList() {
      if (casinosWinnerBuilder_ == null) {
        return java.util.Collections.unmodifiableList(casinosWinner_);
      } else {
        return casinosWinnerBuilder_.getMessageList();
      }
    }
    /**
     * <pre>
     * 中奖的人
     * </pre>
     *
     * <code>repeated .client2server.CasinosWinner casinosWinner = 3;</code>
     */
    public int getCasinosWinnerCount() {
      if (casinosWinnerBuilder_ == null) {
        return casinosWinner_.size();
      } else {
        return casinosWinnerBuilder_.getCount();
      }
    }
    /**
     * <pre>
     * 中奖的人
     * </pre>
     *
     * <code>repeated .client2server.CasinosWinner casinosWinner = 3;</code>
     */
    public pb4client.CasinosWinner getCasinosWinner(int index) {
      if (casinosWinnerBuilder_ == null) {
        return casinosWinner_.get(index);
      } else {
        return casinosWinnerBuilder_.getMessage(index);
      }
    }
    /**
     * <pre>
     * 中奖的人
     * </pre>
     *
     * <code>repeated .client2server.CasinosWinner casinosWinner = 3;</code>
     */
    public Builder setCasinosWinner(
        int index, pb4client.CasinosWinner value) {
      if (casinosWinnerBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureCasinosWinnerIsMutable();
        casinosWinner_.set(index, value);
        onChanged();
      } else {
        casinosWinnerBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * 中奖的人
     * </pre>
     *
     * <code>repeated .client2server.CasinosWinner casinosWinner = 3;</code>
     */
    public Builder setCasinosWinner(
        int index, pb4client.CasinosWinner.Builder builderForValue) {
      if (casinosWinnerBuilder_ == null) {
        ensureCasinosWinnerIsMutable();
        casinosWinner_.set(index, builderForValue.build());
        onChanged();
      } else {
        casinosWinnerBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 中奖的人
     * </pre>
     *
     * <code>repeated .client2server.CasinosWinner casinosWinner = 3;</code>
     */
    public Builder addCasinosWinner(pb4client.CasinosWinner value) {
      if (casinosWinnerBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureCasinosWinnerIsMutable();
        casinosWinner_.add(value);
        onChanged();
      } else {
        casinosWinnerBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <pre>
     * 中奖的人
     * </pre>
     *
     * <code>repeated .client2server.CasinosWinner casinosWinner = 3;</code>
     */
    public Builder addCasinosWinner(
        int index, pb4client.CasinosWinner value) {
      if (casinosWinnerBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureCasinosWinnerIsMutable();
        casinosWinner_.add(index, value);
        onChanged();
      } else {
        casinosWinnerBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <pre>
     * 中奖的人
     * </pre>
     *
     * <code>repeated .client2server.CasinosWinner casinosWinner = 3;</code>
     */
    public Builder addCasinosWinner(
        pb4client.CasinosWinner.Builder builderForValue) {
      if (casinosWinnerBuilder_ == null) {
        ensureCasinosWinnerIsMutable();
        casinosWinner_.add(builderForValue.build());
        onChanged();
      } else {
        casinosWinnerBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 中奖的人
     * </pre>
     *
     * <code>repeated .client2server.CasinosWinner casinosWinner = 3;</code>
     */
    public Builder addCasinosWinner(
        int index, pb4client.CasinosWinner.Builder builderForValue) {
      if (casinosWinnerBuilder_ == null) {
        ensureCasinosWinnerIsMutable();
        casinosWinner_.add(index, builderForValue.build());
        onChanged();
      } else {
        casinosWinnerBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <pre>
     * 中奖的人
     * </pre>
     *
     * <code>repeated .client2server.CasinosWinner casinosWinner = 3;</code>
     */
    public Builder addAllCasinosWinner(
        java.lang.Iterable<? extends pb4client.CasinosWinner> values) {
      if (casinosWinnerBuilder_ == null) {
        ensureCasinosWinnerIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, casinosWinner_);
        onChanged();
      } else {
        casinosWinnerBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <pre>
     * 中奖的人
     * </pre>
     *
     * <code>repeated .client2server.CasinosWinner casinosWinner = 3;</code>
     */
    public Builder clearCasinosWinner() {
      if (casinosWinnerBuilder_ == null) {
        casinosWinner_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
        onChanged();
      } else {
        casinosWinnerBuilder_.clear();
      }
      return this;
    }
    /**
     * <pre>
     * 中奖的人
     * </pre>
     *
     * <code>repeated .client2server.CasinosWinner casinosWinner = 3;</code>
     */
    public Builder removeCasinosWinner(int index) {
      if (casinosWinnerBuilder_ == null) {
        ensureCasinosWinnerIsMutable();
        casinosWinner_.remove(index);
        onChanged();
      } else {
        casinosWinnerBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <pre>
     * 中奖的人
     * </pre>
     *
     * <code>repeated .client2server.CasinosWinner casinosWinner = 3;</code>
     */
    public pb4client.CasinosWinner.Builder getCasinosWinnerBuilder(
        int index) {
      return getCasinosWinnerFieldBuilder().getBuilder(index);
    }
    /**
     * <pre>
     * 中奖的人
     * </pre>
     *
     * <code>repeated .client2server.CasinosWinner casinosWinner = 3;</code>
     */
    public pb4client.CasinosWinnerOrBuilder getCasinosWinnerOrBuilder(
        int index) {
      if (casinosWinnerBuilder_ == null) {
        return casinosWinner_.get(index);  } else {
        return casinosWinnerBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <pre>
     * 中奖的人
     * </pre>
     *
     * <code>repeated .client2server.CasinosWinner casinosWinner = 3;</code>
     */
    public java.util.List<? extends pb4client.CasinosWinnerOrBuilder> 
         getCasinosWinnerOrBuilderList() {
      if (casinosWinnerBuilder_ != null) {
        return casinosWinnerBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(casinosWinner_);
      }
    }
    /**
     * <pre>
     * 中奖的人
     * </pre>
     *
     * <code>repeated .client2server.CasinosWinner casinosWinner = 3;</code>
     */
    public pb4client.CasinosWinner.Builder addCasinosWinnerBuilder() {
      return getCasinosWinnerFieldBuilder().addBuilder(
          pb4client.CasinosWinner.getDefaultInstance());
    }
    /**
     * <pre>
     * 中奖的人
     * </pre>
     *
     * <code>repeated .client2server.CasinosWinner casinosWinner = 3;</code>
     */
    public pb4client.CasinosWinner.Builder addCasinosWinnerBuilder(
        int index) {
      return getCasinosWinnerFieldBuilder().addBuilder(
          index, pb4client.CasinosWinner.getDefaultInstance());
    }
    /**
     * <pre>
     * 中奖的人
     * </pre>
     *
     * <code>repeated .client2server.CasinosWinner casinosWinner = 3;</code>
     */
    public java.util.List<pb4client.CasinosWinner.Builder> 
         getCasinosWinnerBuilderList() {
      return getCasinosWinnerFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        pb4client.CasinosWinner, pb4client.CasinosWinner.Builder, pb4client.CasinosWinnerOrBuilder> 
        getCasinosWinnerFieldBuilder() {
      if (casinosWinnerBuilder_ == null) {
        casinosWinnerBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            pb4client.CasinosWinner, pb4client.CasinosWinner.Builder, pb4client.CasinosWinnerOrBuilder>(
                casinosWinner_,
                ((bitField0_ & 0x00000004) == 0x00000004),
                getParentForChildren(),
                isClean());
        casinosWinner_ = null;
      }
      return casinosWinnerBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.GetJackpotInfoRt)
  }

  // @@protoc_insertion_point(class_scope:client2server.GetJackpotInfoRt)
  private static final pb4client.GetJackpotInfoRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.GetJackpotInfoRt();
  }

  public static pb4client.GetJackpotInfoRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<GetJackpotInfoRt>
      PARSER = new com.google.protobuf.AbstractParser<GetJackpotInfoRt>() {
    public GetJackpotInfoRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new GetJackpotInfoRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GetJackpotInfoRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GetJackpotInfoRt> getParserForType() {
    return PARSER;
  }

  public pb4client.GetJackpotInfoRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
