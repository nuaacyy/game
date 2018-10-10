// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.AllianceCompetitionInfo}
 */
public  final class AllianceCompetitionInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.AllianceCompetitionInfo)
    AllianceCompetitionInfoOrBuilder {
  // Use AllianceCompetitionInfo.newBuilder() to construct.
  private AllianceCompetitionInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AllianceCompetitionInfo() {
    allianceCompetitionId_ = 0L;
    allianceCompetitionTicket_ = 0;
    allianceCompetitionGetTaskNum_ = 0;
    allianceCompetitionBuyTaskNum_ = 0;
    allianceCompetitionRankLv_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AllianceCompetitionInfo(
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
            allianceCompetitionId_ = input.readInt64();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            allianceCompetitionTicket_ = input.readInt32();
            break;
          }
          case 48: {
            bitField0_ |= 0x00000004;
            allianceCompetitionGetTaskNum_ = input.readInt32();
            break;
          }
          case 56: {
            bitField0_ |= 0x00000008;
            allianceCompetitionBuyTaskNum_ = input.readInt32();
            break;
          }
          case 64: {
            bitField0_ |= 0x00000010;
            allianceCompetitionRankLv_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_AllianceCompetitionInfo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_AllianceCompetitionInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.AllianceCompetitionInfo.class, pb4client.AllianceCompetitionInfo.Builder.class);
  }

  private int bitField0_;
  public static final int ALLIANCECOMPETITIONID_FIELD_NUMBER = 1;
  private long allianceCompetitionId_;
  /**
   * <pre>
   * 本次联盟总动员为哪个帮派效力
   * </pre>
   *
   * <code>required int64 allianceCompetitionId = 1;</code>
   */
  public boolean hasAllianceCompetitionId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 本次联盟总动员为哪个帮派效力
   * </pre>
   *
   * <code>required int64 allianceCompetitionId = 1;</code>
   */
  public long getAllianceCompetitionId() {
    return allianceCompetitionId_;
  }

  public static final int ALLIANCECOMPETITIONTICKET_FIELD_NUMBER = 2;
  private int allianceCompetitionTicket_;
  /**
   * <pre>
   *当前是否拥有联盟总动员门票 0-无 1-有
   * </pre>
   *
   * <code>required int32 allianceCompetitionTicket = 2;</code>
   */
  public boolean hasAllianceCompetitionTicket() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *当前是否拥有联盟总动员门票 0-无 1-有
   * </pre>
   *
   * <code>required int32 allianceCompetitionTicket = 2;</code>
   */
  public int getAllianceCompetitionTicket() {
    return allianceCompetitionTicket_;
  }

  public static final int ALLIANCECOMPETITIONGETTASKNUM_FIELD_NUMBER = 6;
  private int allianceCompetitionGetTaskNum_;
  /**
   * <pre>
   * 可领任务次数
   * </pre>
   *
   * <code>required int32 allianceCompetitionGetTaskNum = 6;</code>
   */
  public boolean hasAllianceCompetitionGetTaskNum() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   * 可领任务次数
   * </pre>
   *
   * <code>required int32 allianceCompetitionGetTaskNum = 6;</code>
   */
  public int getAllianceCompetitionGetTaskNum() {
    return allianceCompetitionGetTaskNum_;
  }

  public static final int ALLIANCECOMPETITIONBUYTASKNUM_FIELD_NUMBER = 7;
  private int allianceCompetitionBuyTaskNum_;
  /**
   * <pre>
   * 已购买任务次数
   * </pre>
   *
   * <code>required int32 allianceCompetitionBuyTaskNum = 7;</code>
   */
  public boolean hasAllianceCompetitionBuyTaskNum() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   * 已购买任务次数
   * </pre>
   *
   * <code>required int32 allianceCompetitionBuyTaskNum = 7;</code>
   */
  public int getAllianceCompetitionBuyTaskNum() {
    return allianceCompetitionBuyTaskNum_;
  }

  public static final int ALLIANCECOMPETITIONRANKLV_FIELD_NUMBER = 8;
  private int allianceCompetitionRankLv_;
  /**
   * <pre>
   * 本次联盟总动员效力帮派的段位
   * </pre>
   *
   * <code>required int32 allianceCompetitionRankLv = 8;</code>
   */
  public boolean hasAllianceCompetitionRankLv() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <pre>
   * 本次联盟总动员效力帮派的段位
   * </pre>
   *
   * <code>required int32 allianceCompetitionRankLv = 8;</code>
   */
  public int getAllianceCompetitionRankLv() {
    return allianceCompetitionRankLv_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasAllianceCompetitionId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasAllianceCompetitionTicket()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasAllianceCompetitionGetTaskNum()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasAllianceCompetitionBuyTaskNum()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasAllianceCompetitionRankLv()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt64(1, allianceCompetitionId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, allianceCompetitionTicket_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(6, allianceCompetitionGetTaskNum_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt32(7, allianceCompetitionBuyTaskNum_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      output.writeInt32(8, allianceCompetitionRankLv_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, allianceCompetitionId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, allianceCompetitionTicket_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, allianceCompetitionGetTaskNum_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(7, allianceCompetitionBuyTaskNum_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(8, allianceCompetitionRankLv_);
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
    if (!(obj instanceof pb4client.AllianceCompetitionInfo)) {
      return super.equals(obj);
    }
    pb4client.AllianceCompetitionInfo other = (pb4client.AllianceCompetitionInfo) obj;

    boolean result = true;
    result = result && (hasAllianceCompetitionId() == other.hasAllianceCompetitionId());
    if (hasAllianceCompetitionId()) {
      result = result && (getAllianceCompetitionId()
          == other.getAllianceCompetitionId());
    }
    result = result && (hasAllianceCompetitionTicket() == other.hasAllianceCompetitionTicket());
    if (hasAllianceCompetitionTicket()) {
      result = result && (getAllianceCompetitionTicket()
          == other.getAllianceCompetitionTicket());
    }
    result = result && (hasAllianceCompetitionGetTaskNum() == other.hasAllianceCompetitionGetTaskNum());
    if (hasAllianceCompetitionGetTaskNum()) {
      result = result && (getAllianceCompetitionGetTaskNum()
          == other.getAllianceCompetitionGetTaskNum());
    }
    result = result && (hasAllianceCompetitionBuyTaskNum() == other.hasAllianceCompetitionBuyTaskNum());
    if (hasAllianceCompetitionBuyTaskNum()) {
      result = result && (getAllianceCompetitionBuyTaskNum()
          == other.getAllianceCompetitionBuyTaskNum());
    }
    result = result && (hasAllianceCompetitionRankLv() == other.hasAllianceCompetitionRankLv());
    if (hasAllianceCompetitionRankLv()) {
      result = result && (getAllianceCompetitionRankLv()
          == other.getAllianceCompetitionRankLv());
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
    if (hasAllianceCompetitionId()) {
      hash = (37 * hash) + ALLIANCECOMPETITIONID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getAllianceCompetitionId());
    }
    if (hasAllianceCompetitionTicket()) {
      hash = (37 * hash) + ALLIANCECOMPETITIONTICKET_FIELD_NUMBER;
      hash = (53 * hash) + getAllianceCompetitionTicket();
    }
    if (hasAllianceCompetitionGetTaskNum()) {
      hash = (37 * hash) + ALLIANCECOMPETITIONGETTASKNUM_FIELD_NUMBER;
      hash = (53 * hash) + getAllianceCompetitionGetTaskNum();
    }
    if (hasAllianceCompetitionBuyTaskNum()) {
      hash = (37 * hash) + ALLIANCECOMPETITIONBUYTASKNUM_FIELD_NUMBER;
      hash = (53 * hash) + getAllianceCompetitionBuyTaskNum();
    }
    if (hasAllianceCompetitionRankLv()) {
      hash = (37 * hash) + ALLIANCECOMPETITIONRANKLV_FIELD_NUMBER;
      hash = (53 * hash) + getAllianceCompetitionRankLv();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.AllianceCompetitionInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceCompetitionInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceCompetitionInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceCompetitionInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceCompetitionInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceCompetitionInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceCompetitionInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.AllianceCompetitionInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.AllianceCompetitionInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.AllianceCompetitionInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.AllianceCompetitionInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.AllianceCompetitionInfo parseFrom(
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
  public static Builder newBuilder(pb4client.AllianceCompetitionInfo prototype) {
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
   * Protobuf type {@code client2server.AllianceCompetitionInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.AllianceCompetitionInfo)
      pb4client.AllianceCompetitionInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceCompetitionInfo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceCompetitionInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.AllianceCompetitionInfo.class, pb4client.AllianceCompetitionInfo.Builder.class);
    }

    // Construct using pb4client.AllianceCompetitionInfo.newBuilder()
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
      allianceCompetitionId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      allianceCompetitionTicket_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      allianceCompetitionGetTaskNum_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      allianceCompetitionBuyTaskNum_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      allianceCompetitionRankLv_ = 0;
      bitField0_ = (bitField0_ & ~0x00000010);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceCompetitionInfo_descriptor;
    }

    public pb4client.AllianceCompetitionInfo getDefaultInstanceForType() {
      return pb4client.AllianceCompetitionInfo.getDefaultInstance();
    }

    public pb4client.AllianceCompetitionInfo build() {
      pb4client.AllianceCompetitionInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.AllianceCompetitionInfo buildPartial() {
      pb4client.AllianceCompetitionInfo result = new pb4client.AllianceCompetitionInfo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.allianceCompetitionId_ = allianceCompetitionId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.allianceCompetitionTicket_ = allianceCompetitionTicket_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.allianceCompetitionGetTaskNum_ = allianceCompetitionGetTaskNum_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.allianceCompetitionBuyTaskNum_ = allianceCompetitionBuyTaskNum_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.allianceCompetitionRankLv_ = allianceCompetitionRankLv_;
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
      if (other instanceof pb4client.AllianceCompetitionInfo) {
        return mergeFrom((pb4client.AllianceCompetitionInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.AllianceCompetitionInfo other) {
      if (other == pb4client.AllianceCompetitionInfo.getDefaultInstance()) return this;
      if (other.hasAllianceCompetitionId()) {
        setAllianceCompetitionId(other.getAllianceCompetitionId());
      }
      if (other.hasAllianceCompetitionTicket()) {
        setAllianceCompetitionTicket(other.getAllianceCompetitionTicket());
      }
      if (other.hasAllianceCompetitionGetTaskNum()) {
        setAllianceCompetitionGetTaskNum(other.getAllianceCompetitionGetTaskNum());
      }
      if (other.hasAllianceCompetitionBuyTaskNum()) {
        setAllianceCompetitionBuyTaskNum(other.getAllianceCompetitionBuyTaskNum());
      }
      if (other.hasAllianceCompetitionRankLv()) {
        setAllianceCompetitionRankLv(other.getAllianceCompetitionRankLv());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasAllianceCompetitionId()) {
        return false;
      }
      if (!hasAllianceCompetitionTicket()) {
        return false;
      }
      if (!hasAllianceCompetitionGetTaskNum()) {
        return false;
      }
      if (!hasAllianceCompetitionBuyTaskNum()) {
        return false;
      }
      if (!hasAllianceCompetitionRankLv()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.AllianceCompetitionInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.AllianceCompetitionInfo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long allianceCompetitionId_ ;
    /**
     * <pre>
     * 本次联盟总动员为哪个帮派效力
     * </pre>
     *
     * <code>required int64 allianceCompetitionId = 1;</code>
     */
    public boolean hasAllianceCompetitionId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 本次联盟总动员为哪个帮派效力
     * </pre>
     *
     * <code>required int64 allianceCompetitionId = 1;</code>
     */
    public long getAllianceCompetitionId() {
      return allianceCompetitionId_;
    }
    /**
     * <pre>
     * 本次联盟总动员为哪个帮派效力
     * </pre>
     *
     * <code>required int64 allianceCompetitionId = 1;</code>
     */
    public Builder setAllianceCompetitionId(long value) {
      bitField0_ |= 0x00000001;
      allianceCompetitionId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 本次联盟总动员为哪个帮派效力
     * </pre>
     *
     * <code>required int64 allianceCompetitionId = 1;</code>
     */
    public Builder clearAllianceCompetitionId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      allianceCompetitionId_ = 0L;
      onChanged();
      return this;
    }

    private int allianceCompetitionTicket_ ;
    /**
     * <pre>
     *当前是否拥有联盟总动员门票 0-无 1-有
     * </pre>
     *
     * <code>required int32 allianceCompetitionTicket = 2;</code>
     */
    public boolean hasAllianceCompetitionTicket() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *当前是否拥有联盟总动员门票 0-无 1-有
     * </pre>
     *
     * <code>required int32 allianceCompetitionTicket = 2;</code>
     */
    public int getAllianceCompetitionTicket() {
      return allianceCompetitionTicket_;
    }
    /**
     * <pre>
     *当前是否拥有联盟总动员门票 0-无 1-有
     * </pre>
     *
     * <code>required int32 allianceCompetitionTicket = 2;</code>
     */
    public Builder setAllianceCompetitionTicket(int value) {
      bitField0_ |= 0x00000002;
      allianceCompetitionTicket_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *当前是否拥有联盟总动员门票 0-无 1-有
     * </pre>
     *
     * <code>required int32 allianceCompetitionTicket = 2;</code>
     */
    public Builder clearAllianceCompetitionTicket() {
      bitField0_ = (bitField0_ & ~0x00000002);
      allianceCompetitionTicket_ = 0;
      onChanged();
      return this;
    }

    private int allianceCompetitionGetTaskNum_ ;
    /**
     * <pre>
     * 可领任务次数
     * </pre>
     *
     * <code>required int32 allianceCompetitionGetTaskNum = 6;</code>
     */
    public boolean hasAllianceCompetitionGetTaskNum() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     * 可领任务次数
     * </pre>
     *
     * <code>required int32 allianceCompetitionGetTaskNum = 6;</code>
     */
    public int getAllianceCompetitionGetTaskNum() {
      return allianceCompetitionGetTaskNum_;
    }
    /**
     * <pre>
     * 可领任务次数
     * </pre>
     *
     * <code>required int32 allianceCompetitionGetTaskNum = 6;</code>
     */
    public Builder setAllianceCompetitionGetTaskNum(int value) {
      bitField0_ |= 0x00000004;
      allianceCompetitionGetTaskNum_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 可领任务次数
     * </pre>
     *
     * <code>required int32 allianceCompetitionGetTaskNum = 6;</code>
     */
    public Builder clearAllianceCompetitionGetTaskNum() {
      bitField0_ = (bitField0_ & ~0x00000004);
      allianceCompetitionGetTaskNum_ = 0;
      onChanged();
      return this;
    }

    private int allianceCompetitionBuyTaskNum_ ;
    /**
     * <pre>
     * 已购买任务次数
     * </pre>
     *
     * <code>required int32 allianceCompetitionBuyTaskNum = 7;</code>
     */
    public boolean hasAllianceCompetitionBuyTaskNum() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     * 已购买任务次数
     * </pre>
     *
     * <code>required int32 allianceCompetitionBuyTaskNum = 7;</code>
     */
    public int getAllianceCompetitionBuyTaskNum() {
      return allianceCompetitionBuyTaskNum_;
    }
    /**
     * <pre>
     * 已购买任务次数
     * </pre>
     *
     * <code>required int32 allianceCompetitionBuyTaskNum = 7;</code>
     */
    public Builder setAllianceCompetitionBuyTaskNum(int value) {
      bitField0_ |= 0x00000008;
      allianceCompetitionBuyTaskNum_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 已购买任务次数
     * </pre>
     *
     * <code>required int32 allianceCompetitionBuyTaskNum = 7;</code>
     */
    public Builder clearAllianceCompetitionBuyTaskNum() {
      bitField0_ = (bitField0_ & ~0x00000008);
      allianceCompetitionBuyTaskNum_ = 0;
      onChanged();
      return this;
    }

    private int allianceCompetitionRankLv_ ;
    /**
     * <pre>
     * 本次联盟总动员效力帮派的段位
     * </pre>
     *
     * <code>required int32 allianceCompetitionRankLv = 8;</code>
     */
    public boolean hasAllianceCompetitionRankLv() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <pre>
     * 本次联盟总动员效力帮派的段位
     * </pre>
     *
     * <code>required int32 allianceCompetitionRankLv = 8;</code>
     */
    public int getAllianceCompetitionRankLv() {
      return allianceCompetitionRankLv_;
    }
    /**
     * <pre>
     * 本次联盟总动员效力帮派的段位
     * </pre>
     *
     * <code>required int32 allianceCompetitionRankLv = 8;</code>
     */
    public Builder setAllianceCompetitionRankLv(int value) {
      bitField0_ |= 0x00000010;
      allianceCompetitionRankLv_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 本次联盟总动员效力帮派的段位
     * </pre>
     *
     * <code>required int32 allianceCompetitionRankLv = 8;</code>
     */
    public Builder clearAllianceCompetitionRankLv() {
      bitField0_ = (bitField0_ & ~0x00000010);
      allianceCompetitionRankLv_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.AllianceCompetitionInfo)
  }

  // @@protoc_insertion_point(class_scope:client2server.AllianceCompetitionInfo)
  private static final pb4client.AllianceCompetitionInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.AllianceCompetitionInfo();
  }

  public static pb4client.AllianceCompetitionInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<AllianceCompetitionInfo>
      PARSER = new com.google.protobuf.AbstractParser<AllianceCompetitionInfo>() {
    public AllianceCompetitionInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new AllianceCompetitionInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AllianceCompetitionInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AllianceCompetitionInfo> getParserForType() {
    return PARSER;
  }

  public pb4client.AllianceCompetitionInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
