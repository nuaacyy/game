// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.SendRedBagInfoVo}
 */
public  final class SendRedBagInfoVo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.SendRedBagInfoVo)
    SendRedBagInfoVoOrBuilder {
  // Use SendRedBagInfoVo.newBuilder() to construct.
  private SendRedBagInfoVo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SendRedBagInfoVo() {
    redBagType_ = 0;
    redBagAllMoney_ = 0L;
    redBagNowMoney_ = 0L;
    redBagAllNum_ = 0;
    redBagNowNum_ = 0;
    sendTime_ = 0;
    overTime_ = 0;
    isBack_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private SendRedBagInfoVo(
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
            redBagType_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            redBagAllMoney_ = input.readInt64();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            redBagNowMoney_ = input.readInt64();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            redBagAllNum_ = input.readInt32();
            break;
          }
          case 40: {
            bitField0_ |= 0x00000010;
            redBagNowNum_ = input.readInt32();
            break;
          }
          case 48: {
            bitField0_ |= 0x00000020;
            sendTime_ = input.readInt32();
            break;
          }
          case 56: {
            bitField0_ |= 0x00000040;
            overTime_ = input.readInt32();
            break;
          }
          case 64: {
            bitField0_ |= 0x00000080;
            isBack_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_SendRedBagInfoVo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_SendRedBagInfoVo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.SendRedBagInfoVo.class, pb4client.SendRedBagInfoVo.Builder.class);
  }

  private int bitField0_;
  public static final int REDBAGTYPE_FIELD_NUMBER = 1;
  private int redBagType_;
  /**
   * <pre>
   * 1-普通红包  2-现金红包
   * </pre>
   *
   * <code>required int32 redBagType = 1;</code>
   */
  public boolean hasRedBagType() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 1-普通红包  2-现金红包
   * </pre>
   *
   * <code>required int32 redBagType = 1;</code>
   */
  public int getRedBagType() {
    return redBagType_;
  }

  public static final int REDBAGALLMONEY_FIELD_NUMBER = 2;
  private long redBagAllMoney_;
  /**
   * <pre>
   * 一共塞了多少钱
   * </pre>
   *
   * <code>required int64 redBagAllMoney = 2;</code>
   */
  public boolean hasRedBagAllMoney() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 一共塞了多少钱
   * </pre>
   *
   * <code>required int64 redBagAllMoney = 2;</code>
   */
  public long getRedBagAllMoney() {
    return redBagAllMoney_;
  }

  public static final int REDBAGNOWMONEY_FIELD_NUMBER = 3;
  private long redBagNowMoney_;
  /**
   * <pre>
   * 当前剩余多少钱
   * </pre>
   *
   * <code>required int64 redBagNowMoney = 3;</code>
   */
  public boolean hasRedBagNowMoney() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   * 当前剩余多少钱
   * </pre>
   *
   * <code>required int64 redBagNowMoney = 3;</code>
   */
  public long getRedBagNowMoney() {
    return redBagNowMoney_;
  }

  public static final int REDBAGALLNUM_FIELD_NUMBER = 4;
  private int redBagAllNum_;
  /**
   * <pre>
   * 一共多少个红包
   * </pre>
   *
   * <code>required int32 redBagAllNum = 4;</code>
   */
  public boolean hasRedBagAllNum() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   * 一共多少个红包
   * </pre>
   *
   * <code>required int32 redBagAllNum = 4;</code>
   */
  public int getRedBagAllNum() {
    return redBagAllNum_;
  }

  public static final int REDBAGNOWNUM_FIELD_NUMBER = 5;
  private int redBagNowNum_;
  /**
   * <pre>
   * 当前剩余多少个未领取
   * </pre>
   *
   * <code>required int32 redBagNowNum = 5;</code>
   */
  public boolean hasRedBagNowNum() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <pre>
   * 当前剩余多少个未领取
   * </pre>
   *
   * <code>required int32 redBagNowNum = 5;</code>
   */
  public int getRedBagNowNum() {
    return redBagNowNum_;
  }

  public static final int SENDTIME_FIELD_NUMBER = 6;
  private int sendTime_;
  /**
   * <pre>
   * 发出去的时间
   * </pre>
   *
   * <code>required int32 sendTime = 6;</code>
   */
  public boolean hasSendTime() {
    return ((bitField0_ & 0x00000020) == 0x00000020);
  }
  /**
   * <pre>
   * 发出去的时间
   * </pre>
   *
   * <code>required int32 sendTime = 6;</code>
   */
  public int getSendTime() {
    return sendTime_;
  }

  public static final int OVERTIME_FIELD_NUMBER = 7;
  private int overTime_;
  /**
   * <pre>
   * 过期时间 
   * </pre>
   *
   * <code>required int32 overTime = 7;</code>
   */
  public boolean hasOverTime() {
    return ((bitField0_ & 0x00000040) == 0x00000040);
  }
  /**
   * <pre>
   * 过期时间 
   * </pre>
   *
   * <code>required int32 overTime = 7;</code>
   */
  public int getOverTime() {
    return overTime_;
  }

  public static final int ISBACK_FIELD_NUMBER = 8;
  private int isBack_;
  /**
   * <pre>
   * 是否已经过期退还过了  0-否  1-是 
   * </pre>
   *
   * <code>required int32 isBack = 8;</code>
   */
  public boolean hasIsBack() {
    return ((bitField0_ & 0x00000080) == 0x00000080);
  }
  /**
   * <pre>
   * 是否已经过期退还过了  0-否  1-是 
   * </pre>
   *
   * <code>required int32 isBack = 8;</code>
   */
  public int getIsBack() {
    return isBack_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasRedBagType()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasRedBagAllMoney()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasRedBagNowMoney()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasRedBagAllNum()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasRedBagNowNum()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasSendTime()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasOverTime()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasIsBack()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, redBagType_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt64(2, redBagAllMoney_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt64(3, redBagNowMoney_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt32(4, redBagAllNum_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      output.writeInt32(5, redBagNowNum_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      output.writeInt32(6, sendTime_);
    }
    if (((bitField0_ & 0x00000040) == 0x00000040)) {
      output.writeInt32(7, overTime_);
    }
    if (((bitField0_ & 0x00000080) == 0x00000080)) {
      output.writeInt32(8, isBack_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, redBagType_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, redBagAllMoney_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, redBagNowMoney_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, redBagAllNum_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, redBagNowNum_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, sendTime_);
    }
    if (((bitField0_ & 0x00000040) == 0x00000040)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(7, overTime_);
    }
    if (((bitField0_ & 0x00000080) == 0x00000080)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(8, isBack_);
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
    if (!(obj instanceof pb4client.SendRedBagInfoVo)) {
      return super.equals(obj);
    }
    pb4client.SendRedBagInfoVo other = (pb4client.SendRedBagInfoVo) obj;

    boolean result = true;
    result = result && (hasRedBagType() == other.hasRedBagType());
    if (hasRedBagType()) {
      result = result && (getRedBagType()
          == other.getRedBagType());
    }
    result = result && (hasRedBagAllMoney() == other.hasRedBagAllMoney());
    if (hasRedBagAllMoney()) {
      result = result && (getRedBagAllMoney()
          == other.getRedBagAllMoney());
    }
    result = result && (hasRedBagNowMoney() == other.hasRedBagNowMoney());
    if (hasRedBagNowMoney()) {
      result = result && (getRedBagNowMoney()
          == other.getRedBagNowMoney());
    }
    result = result && (hasRedBagAllNum() == other.hasRedBagAllNum());
    if (hasRedBagAllNum()) {
      result = result && (getRedBagAllNum()
          == other.getRedBagAllNum());
    }
    result = result && (hasRedBagNowNum() == other.hasRedBagNowNum());
    if (hasRedBagNowNum()) {
      result = result && (getRedBagNowNum()
          == other.getRedBagNowNum());
    }
    result = result && (hasSendTime() == other.hasSendTime());
    if (hasSendTime()) {
      result = result && (getSendTime()
          == other.getSendTime());
    }
    result = result && (hasOverTime() == other.hasOverTime());
    if (hasOverTime()) {
      result = result && (getOverTime()
          == other.getOverTime());
    }
    result = result && (hasIsBack() == other.hasIsBack());
    if (hasIsBack()) {
      result = result && (getIsBack()
          == other.getIsBack());
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
    if (hasRedBagType()) {
      hash = (37 * hash) + REDBAGTYPE_FIELD_NUMBER;
      hash = (53 * hash) + getRedBagType();
    }
    if (hasRedBagAllMoney()) {
      hash = (37 * hash) + REDBAGALLMONEY_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getRedBagAllMoney());
    }
    if (hasRedBagNowMoney()) {
      hash = (37 * hash) + REDBAGNOWMONEY_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getRedBagNowMoney());
    }
    if (hasRedBagAllNum()) {
      hash = (37 * hash) + REDBAGALLNUM_FIELD_NUMBER;
      hash = (53 * hash) + getRedBagAllNum();
    }
    if (hasRedBagNowNum()) {
      hash = (37 * hash) + REDBAGNOWNUM_FIELD_NUMBER;
      hash = (53 * hash) + getRedBagNowNum();
    }
    if (hasSendTime()) {
      hash = (37 * hash) + SENDTIME_FIELD_NUMBER;
      hash = (53 * hash) + getSendTime();
    }
    if (hasOverTime()) {
      hash = (37 * hash) + OVERTIME_FIELD_NUMBER;
      hash = (53 * hash) + getOverTime();
    }
    if (hasIsBack()) {
      hash = (37 * hash) + ISBACK_FIELD_NUMBER;
      hash = (53 * hash) + getIsBack();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.SendRedBagInfoVo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.SendRedBagInfoVo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.SendRedBagInfoVo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.SendRedBagInfoVo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.SendRedBagInfoVo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.SendRedBagInfoVo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.SendRedBagInfoVo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.SendRedBagInfoVo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.SendRedBagInfoVo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.SendRedBagInfoVo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.SendRedBagInfoVo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.SendRedBagInfoVo parseFrom(
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
  public static Builder newBuilder(pb4client.SendRedBagInfoVo prototype) {
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
   * Protobuf type {@code client2server.SendRedBagInfoVo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.SendRedBagInfoVo)
      pb4client.SendRedBagInfoVoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_SendRedBagInfoVo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_SendRedBagInfoVo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.SendRedBagInfoVo.class, pb4client.SendRedBagInfoVo.Builder.class);
    }

    // Construct using pb4client.SendRedBagInfoVo.newBuilder()
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
      redBagType_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      redBagAllMoney_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000002);
      redBagNowMoney_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000004);
      redBagAllNum_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      redBagNowNum_ = 0;
      bitField0_ = (bitField0_ & ~0x00000010);
      sendTime_ = 0;
      bitField0_ = (bitField0_ & ~0x00000020);
      overTime_ = 0;
      bitField0_ = (bitField0_ & ~0x00000040);
      isBack_ = 0;
      bitField0_ = (bitField0_ & ~0x00000080);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_SendRedBagInfoVo_descriptor;
    }

    public pb4client.SendRedBagInfoVo getDefaultInstanceForType() {
      return pb4client.SendRedBagInfoVo.getDefaultInstance();
    }

    public pb4client.SendRedBagInfoVo build() {
      pb4client.SendRedBagInfoVo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.SendRedBagInfoVo buildPartial() {
      pb4client.SendRedBagInfoVo result = new pb4client.SendRedBagInfoVo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.redBagType_ = redBagType_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.redBagAllMoney_ = redBagAllMoney_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.redBagNowMoney_ = redBagNowMoney_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.redBagAllNum_ = redBagAllNum_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.redBagNowNum_ = redBagNowNum_;
      if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
        to_bitField0_ |= 0x00000020;
      }
      result.sendTime_ = sendTime_;
      if (((from_bitField0_ & 0x00000040) == 0x00000040)) {
        to_bitField0_ |= 0x00000040;
      }
      result.overTime_ = overTime_;
      if (((from_bitField0_ & 0x00000080) == 0x00000080)) {
        to_bitField0_ |= 0x00000080;
      }
      result.isBack_ = isBack_;
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
      if (other instanceof pb4client.SendRedBagInfoVo) {
        return mergeFrom((pb4client.SendRedBagInfoVo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.SendRedBagInfoVo other) {
      if (other == pb4client.SendRedBagInfoVo.getDefaultInstance()) return this;
      if (other.hasRedBagType()) {
        setRedBagType(other.getRedBagType());
      }
      if (other.hasRedBagAllMoney()) {
        setRedBagAllMoney(other.getRedBagAllMoney());
      }
      if (other.hasRedBagNowMoney()) {
        setRedBagNowMoney(other.getRedBagNowMoney());
      }
      if (other.hasRedBagAllNum()) {
        setRedBagAllNum(other.getRedBagAllNum());
      }
      if (other.hasRedBagNowNum()) {
        setRedBagNowNum(other.getRedBagNowNum());
      }
      if (other.hasSendTime()) {
        setSendTime(other.getSendTime());
      }
      if (other.hasOverTime()) {
        setOverTime(other.getOverTime());
      }
      if (other.hasIsBack()) {
        setIsBack(other.getIsBack());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasRedBagType()) {
        return false;
      }
      if (!hasRedBagAllMoney()) {
        return false;
      }
      if (!hasRedBagNowMoney()) {
        return false;
      }
      if (!hasRedBagAllNum()) {
        return false;
      }
      if (!hasRedBagNowNum()) {
        return false;
      }
      if (!hasSendTime()) {
        return false;
      }
      if (!hasOverTime()) {
        return false;
      }
      if (!hasIsBack()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.SendRedBagInfoVo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.SendRedBagInfoVo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int redBagType_ ;
    /**
     * <pre>
     * 1-普通红包  2-现金红包
     * </pre>
     *
     * <code>required int32 redBagType = 1;</code>
     */
    public boolean hasRedBagType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 1-普通红包  2-现金红包
     * </pre>
     *
     * <code>required int32 redBagType = 1;</code>
     */
    public int getRedBagType() {
      return redBagType_;
    }
    /**
     * <pre>
     * 1-普通红包  2-现金红包
     * </pre>
     *
     * <code>required int32 redBagType = 1;</code>
     */
    public Builder setRedBagType(int value) {
      bitField0_ |= 0x00000001;
      redBagType_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 1-普通红包  2-现金红包
     * </pre>
     *
     * <code>required int32 redBagType = 1;</code>
     */
    public Builder clearRedBagType() {
      bitField0_ = (bitField0_ & ~0x00000001);
      redBagType_ = 0;
      onChanged();
      return this;
    }

    private long redBagAllMoney_ ;
    /**
     * <pre>
     * 一共塞了多少钱
     * </pre>
     *
     * <code>required int64 redBagAllMoney = 2;</code>
     */
    public boolean hasRedBagAllMoney() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 一共塞了多少钱
     * </pre>
     *
     * <code>required int64 redBagAllMoney = 2;</code>
     */
    public long getRedBagAllMoney() {
      return redBagAllMoney_;
    }
    /**
     * <pre>
     * 一共塞了多少钱
     * </pre>
     *
     * <code>required int64 redBagAllMoney = 2;</code>
     */
    public Builder setRedBagAllMoney(long value) {
      bitField0_ |= 0x00000002;
      redBagAllMoney_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 一共塞了多少钱
     * </pre>
     *
     * <code>required int64 redBagAllMoney = 2;</code>
     */
    public Builder clearRedBagAllMoney() {
      bitField0_ = (bitField0_ & ~0x00000002);
      redBagAllMoney_ = 0L;
      onChanged();
      return this;
    }

    private long redBagNowMoney_ ;
    /**
     * <pre>
     * 当前剩余多少钱
     * </pre>
     *
     * <code>required int64 redBagNowMoney = 3;</code>
     */
    public boolean hasRedBagNowMoney() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     * 当前剩余多少钱
     * </pre>
     *
     * <code>required int64 redBagNowMoney = 3;</code>
     */
    public long getRedBagNowMoney() {
      return redBagNowMoney_;
    }
    /**
     * <pre>
     * 当前剩余多少钱
     * </pre>
     *
     * <code>required int64 redBagNowMoney = 3;</code>
     */
    public Builder setRedBagNowMoney(long value) {
      bitField0_ |= 0x00000004;
      redBagNowMoney_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 当前剩余多少钱
     * </pre>
     *
     * <code>required int64 redBagNowMoney = 3;</code>
     */
    public Builder clearRedBagNowMoney() {
      bitField0_ = (bitField0_ & ~0x00000004);
      redBagNowMoney_ = 0L;
      onChanged();
      return this;
    }

    private int redBagAllNum_ ;
    /**
     * <pre>
     * 一共多少个红包
     * </pre>
     *
     * <code>required int32 redBagAllNum = 4;</code>
     */
    public boolean hasRedBagAllNum() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     * 一共多少个红包
     * </pre>
     *
     * <code>required int32 redBagAllNum = 4;</code>
     */
    public int getRedBagAllNum() {
      return redBagAllNum_;
    }
    /**
     * <pre>
     * 一共多少个红包
     * </pre>
     *
     * <code>required int32 redBagAllNum = 4;</code>
     */
    public Builder setRedBagAllNum(int value) {
      bitField0_ |= 0x00000008;
      redBagAllNum_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 一共多少个红包
     * </pre>
     *
     * <code>required int32 redBagAllNum = 4;</code>
     */
    public Builder clearRedBagAllNum() {
      bitField0_ = (bitField0_ & ~0x00000008);
      redBagAllNum_ = 0;
      onChanged();
      return this;
    }

    private int redBagNowNum_ ;
    /**
     * <pre>
     * 当前剩余多少个未领取
     * </pre>
     *
     * <code>required int32 redBagNowNum = 5;</code>
     */
    public boolean hasRedBagNowNum() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <pre>
     * 当前剩余多少个未领取
     * </pre>
     *
     * <code>required int32 redBagNowNum = 5;</code>
     */
    public int getRedBagNowNum() {
      return redBagNowNum_;
    }
    /**
     * <pre>
     * 当前剩余多少个未领取
     * </pre>
     *
     * <code>required int32 redBagNowNum = 5;</code>
     */
    public Builder setRedBagNowNum(int value) {
      bitField0_ |= 0x00000010;
      redBagNowNum_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 当前剩余多少个未领取
     * </pre>
     *
     * <code>required int32 redBagNowNum = 5;</code>
     */
    public Builder clearRedBagNowNum() {
      bitField0_ = (bitField0_ & ~0x00000010);
      redBagNowNum_ = 0;
      onChanged();
      return this;
    }

    private int sendTime_ ;
    /**
     * <pre>
     * 发出去的时间
     * </pre>
     *
     * <code>required int32 sendTime = 6;</code>
     */
    public boolean hasSendTime() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    /**
     * <pre>
     * 发出去的时间
     * </pre>
     *
     * <code>required int32 sendTime = 6;</code>
     */
    public int getSendTime() {
      return sendTime_;
    }
    /**
     * <pre>
     * 发出去的时间
     * </pre>
     *
     * <code>required int32 sendTime = 6;</code>
     */
    public Builder setSendTime(int value) {
      bitField0_ |= 0x00000020;
      sendTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 发出去的时间
     * </pre>
     *
     * <code>required int32 sendTime = 6;</code>
     */
    public Builder clearSendTime() {
      bitField0_ = (bitField0_ & ~0x00000020);
      sendTime_ = 0;
      onChanged();
      return this;
    }

    private int overTime_ ;
    /**
     * <pre>
     * 过期时间 
     * </pre>
     *
     * <code>required int32 overTime = 7;</code>
     */
    public boolean hasOverTime() {
      return ((bitField0_ & 0x00000040) == 0x00000040);
    }
    /**
     * <pre>
     * 过期时间 
     * </pre>
     *
     * <code>required int32 overTime = 7;</code>
     */
    public int getOverTime() {
      return overTime_;
    }
    /**
     * <pre>
     * 过期时间 
     * </pre>
     *
     * <code>required int32 overTime = 7;</code>
     */
    public Builder setOverTime(int value) {
      bitField0_ |= 0x00000040;
      overTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 过期时间 
     * </pre>
     *
     * <code>required int32 overTime = 7;</code>
     */
    public Builder clearOverTime() {
      bitField0_ = (bitField0_ & ~0x00000040);
      overTime_ = 0;
      onChanged();
      return this;
    }

    private int isBack_ ;
    /**
     * <pre>
     * 是否已经过期退还过了  0-否  1-是 
     * </pre>
     *
     * <code>required int32 isBack = 8;</code>
     */
    public boolean hasIsBack() {
      return ((bitField0_ & 0x00000080) == 0x00000080);
    }
    /**
     * <pre>
     * 是否已经过期退还过了  0-否  1-是 
     * </pre>
     *
     * <code>required int32 isBack = 8;</code>
     */
    public int getIsBack() {
      return isBack_;
    }
    /**
     * <pre>
     * 是否已经过期退还过了  0-否  1-是 
     * </pre>
     *
     * <code>required int32 isBack = 8;</code>
     */
    public Builder setIsBack(int value) {
      bitField0_ |= 0x00000080;
      isBack_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 是否已经过期退还过了  0-否  1-是 
     * </pre>
     *
     * <code>required int32 isBack = 8;</code>
     */
    public Builder clearIsBack() {
      bitField0_ = (bitField0_ & ~0x00000080);
      isBack_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.SendRedBagInfoVo)
  }

  // @@protoc_insertion_point(class_scope:client2server.SendRedBagInfoVo)
  private static final pb4client.SendRedBagInfoVo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.SendRedBagInfoVo();
  }

  public static pb4client.SendRedBagInfoVo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<SendRedBagInfoVo>
      PARSER = new com.google.protobuf.AbstractParser<SendRedBagInfoVo>() {
    public SendRedBagInfoVo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new SendRedBagInfoVo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SendRedBagInfoVo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SendRedBagInfoVo> getParserForType() {
    return PARSER;
  }

  public pb4client.SendRedBagInfoVo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

