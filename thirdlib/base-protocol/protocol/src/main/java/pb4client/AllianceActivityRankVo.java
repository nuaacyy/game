// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.AllianceActivityRankVo}
 */
public  final class AllianceActivityRankVo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.AllianceActivityRankVo)
    AllianceActivityRankVoOrBuilder {
  // Use AllianceActivityRankVo.newBuilder() to construct.
  private AllianceActivityRankVo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AllianceActivityRankVo() {
    allianceId_ = 0L;
    allianceName_ = "";
    shortName_ = "";
    myScore_ = 0;
    flagColor_ = 0;
    flagStyle_ = 0;
    flagEffect_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AllianceActivityRankVo(
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
            allianceId_ = input.readInt64();
            break;
          }
          case 18: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000002;
            allianceName_ = bs;
            break;
          }
          case 26: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000004;
            shortName_ = bs;
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            myScore_ = input.readInt32();
            break;
          }
          case 40: {
            bitField0_ |= 0x00000010;
            flagColor_ = input.readInt32();
            break;
          }
          case 48: {
            bitField0_ |= 0x00000020;
            flagStyle_ = input.readInt32();
            break;
          }
          case 56: {
            bitField0_ |= 0x00000040;
            flagEffect_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_AllianceActivityRankVo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_AllianceActivityRankVo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.AllianceActivityRankVo.class, pb4client.AllianceActivityRankVo.Builder.class);
  }

  private int bitField0_;
  public static final int ALLIANCEID_FIELD_NUMBER = 1;
  private long allianceId_;
  /**
   * <pre>
   * 联盟Id
   * </pre>
   *
   * <code>required int64 allianceId = 1;</code>
   */
  public boolean hasAllianceId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 联盟Id
   * </pre>
   *
   * <code>required int64 allianceId = 1;</code>
   */
  public long getAllianceId() {
    return allianceId_;
  }

  public static final int ALLIANCENAME_FIELD_NUMBER = 2;
  private volatile java.lang.Object allianceName_;
  /**
   * <pre>
   * 联盟名
   * </pre>
   *
   * <code>required string allianceName = 2;</code>
   */
  public boolean hasAllianceName() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 联盟名
   * </pre>
   *
   * <code>required string allianceName = 2;</code>
   */
  public java.lang.String getAllianceName() {
    java.lang.Object ref = allianceName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        allianceName_ = s;
      }
      return s;
    }
  }
  /**
   * <pre>
   * 联盟名
   * </pre>
   *
   * <code>required string allianceName = 2;</code>
   */
  public com.google.protobuf.ByteString
      getAllianceNameBytes() {
    java.lang.Object ref = allianceName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      allianceName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int SHORTNAME_FIELD_NUMBER = 3;
  private volatile java.lang.Object shortName_;
  /**
   * <pre>
   * 联盟简称
   * </pre>
   *
   * <code>required string shortName = 3;</code>
   */
  public boolean hasShortName() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   * 联盟简称
   * </pre>
   *
   * <code>required string shortName = 3;</code>
   */
  public java.lang.String getShortName() {
    java.lang.Object ref = shortName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        shortName_ = s;
      }
      return s;
    }
  }
  /**
   * <pre>
   * 联盟简称
   * </pre>
   *
   * <code>required string shortName = 3;</code>
   */
  public com.google.protobuf.ByteString
      getShortNameBytes() {
    java.lang.Object ref = shortName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      shortName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int MYSCORE_FIELD_NUMBER = 4;
  private int myScore_;
  /**
   * <pre>
   * 积分记录
   * </pre>
   *
   * <code>required int32 myScore = 4;</code>
   */
  public boolean hasMyScore() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   * 积分记录
   * </pre>
   *
   * <code>required int32 myScore = 4;</code>
   */
  public int getMyScore() {
    return myScore_;
  }

  public static final int FLAGCOLOR_FIELD_NUMBER = 5;
  private int flagColor_;
  /**
   * <pre>
   *旗帜颜色
   * </pre>
   *
   * <code>required int32 flagColor = 5;</code>
   */
  public boolean hasFlagColor() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <pre>
   *旗帜颜色
   * </pre>
   *
   * <code>required int32 flagColor = 5;</code>
   */
  public int getFlagColor() {
    return flagColor_;
  }

  public static final int FLAGSTYLE_FIELD_NUMBER = 6;
  private int flagStyle_;
  /**
   * <pre>
   *旗帜样式
   * </pre>
   *
   * <code>required int32 flagStyle = 6;</code>
   */
  public boolean hasFlagStyle() {
    return ((bitField0_ & 0x00000020) == 0x00000020);
  }
  /**
   * <pre>
   *旗帜样式
   * </pre>
   *
   * <code>required int32 flagStyle = 6;</code>
   */
  public int getFlagStyle() {
    return flagStyle_;
  }

  public static final int FLAGEFFECT_FIELD_NUMBER = 7;
  private int flagEffect_;
  /**
   * <pre>
   *旗帜图案
   * </pre>
   *
   * <code>required int32 flagEffect = 7;</code>
   */
  public boolean hasFlagEffect() {
    return ((bitField0_ & 0x00000040) == 0x00000040);
  }
  /**
   * <pre>
   *旗帜图案
   * </pre>
   *
   * <code>required int32 flagEffect = 7;</code>
   */
  public int getFlagEffect() {
    return flagEffect_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasAllianceId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasAllianceName()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasShortName()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasMyScore()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasFlagColor()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasFlagStyle()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasFlagEffect()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt64(1, allianceId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, allianceName_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, shortName_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt32(4, myScore_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      output.writeInt32(5, flagColor_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      output.writeInt32(6, flagStyle_);
    }
    if (((bitField0_ & 0x00000040) == 0x00000040)) {
      output.writeInt32(7, flagEffect_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, allianceId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, allianceName_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, shortName_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, myScore_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, flagColor_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, flagStyle_);
    }
    if (((bitField0_ & 0x00000040) == 0x00000040)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(7, flagEffect_);
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
    if (!(obj instanceof pb4client.AllianceActivityRankVo)) {
      return super.equals(obj);
    }
    pb4client.AllianceActivityRankVo other = (pb4client.AllianceActivityRankVo) obj;

    boolean result = true;
    result = result && (hasAllianceId() == other.hasAllianceId());
    if (hasAllianceId()) {
      result = result && (getAllianceId()
          == other.getAllianceId());
    }
    result = result && (hasAllianceName() == other.hasAllianceName());
    if (hasAllianceName()) {
      result = result && getAllianceName()
          .equals(other.getAllianceName());
    }
    result = result && (hasShortName() == other.hasShortName());
    if (hasShortName()) {
      result = result && getShortName()
          .equals(other.getShortName());
    }
    result = result && (hasMyScore() == other.hasMyScore());
    if (hasMyScore()) {
      result = result && (getMyScore()
          == other.getMyScore());
    }
    result = result && (hasFlagColor() == other.hasFlagColor());
    if (hasFlagColor()) {
      result = result && (getFlagColor()
          == other.getFlagColor());
    }
    result = result && (hasFlagStyle() == other.hasFlagStyle());
    if (hasFlagStyle()) {
      result = result && (getFlagStyle()
          == other.getFlagStyle());
    }
    result = result && (hasFlagEffect() == other.hasFlagEffect());
    if (hasFlagEffect()) {
      result = result && (getFlagEffect()
          == other.getFlagEffect());
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
    if (hasAllianceId()) {
      hash = (37 * hash) + ALLIANCEID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getAllianceId());
    }
    if (hasAllianceName()) {
      hash = (37 * hash) + ALLIANCENAME_FIELD_NUMBER;
      hash = (53 * hash) + getAllianceName().hashCode();
    }
    if (hasShortName()) {
      hash = (37 * hash) + SHORTNAME_FIELD_NUMBER;
      hash = (53 * hash) + getShortName().hashCode();
    }
    if (hasMyScore()) {
      hash = (37 * hash) + MYSCORE_FIELD_NUMBER;
      hash = (53 * hash) + getMyScore();
    }
    if (hasFlagColor()) {
      hash = (37 * hash) + FLAGCOLOR_FIELD_NUMBER;
      hash = (53 * hash) + getFlagColor();
    }
    if (hasFlagStyle()) {
      hash = (37 * hash) + FLAGSTYLE_FIELD_NUMBER;
      hash = (53 * hash) + getFlagStyle();
    }
    if (hasFlagEffect()) {
      hash = (37 * hash) + FLAGEFFECT_FIELD_NUMBER;
      hash = (53 * hash) + getFlagEffect();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.AllianceActivityRankVo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceActivityRankVo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceActivityRankVo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceActivityRankVo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceActivityRankVo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceActivityRankVo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceActivityRankVo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.AllianceActivityRankVo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.AllianceActivityRankVo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.AllianceActivityRankVo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.AllianceActivityRankVo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.AllianceActivityRankVo parseFrom(
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
  public static Builder newBuilder(pb4client.AllianceActivityRankVo prototype) {
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
   * Protobuf type {@code client2server.AllianceActivityRankVo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.AllianceActivityRankVo)
      pb4client.AllianceActivityRankVoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceActivityRankVo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceActivityRankVo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.AllianceActivityRankVo.class, pb4client.AllianceActivityRankVo.Builder.class);
    }

    // Construct using pb4client.AllianceActivityRankVo.newBuilder()
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
      allianceId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      allianceName_ = "";
      bitField0_ = (bitField0_ & ~0x00000002);
      shortName_ = "";
      bitField0_ = (bitField0_ & ~0x00000004);
      myScore_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      flagColor_ = 0;
      bitField0_ = (bitField0_ & ~0x00000010);
      flagStyle_ = 0;
      bitField0_ = (bitField0_ & ~0x00000020);
      flagEffect_ = 0;
      bitField0_ = (bitField0_ & ~0x00000040);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceActivityRankVo_descriptor;
    }

    public pb4client.AllianceActivityRankVo getDefaultInstanceForType() {
      return pb4client.AllianceActivityRankVo.getDefaultInstance();
    }

    public pb4client.AllianceActivityRankVo build() {
      pb4client.AllianceActivityRankVo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.AllianceActivityRankVo buildPartial() {
      pb4client.AllianceActivityRankVo result = new pb4client.AllianceActivityRankVo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.allianceId_ = allianceId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.allianceName_ = allianceName_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.shortName_ = shortName_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.myScore_ = myScore_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.flagColor_ = flagColor_;
      if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
        to_bitField0_ |= 0x00000020;
      }
      result.flagStyle_ = flagStyle_;
      if (((from_bitField0_ & 0x00000040) == 0x00000040)) {
        to_bitField0_ |= 0x00000040;
      }
      result.flagEffect_ = flagEffect_;
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
      if (other instanceof pb4client.AllianceActivityRankVo) {
        return mergeFrom((pb4client.AllianceActivityRankVo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.AllianceActivityRankVo other) {
      if (other == pb4client.AllianceActivityRankVo.getDefaultInstance()) return this;
      if (other.hasAllianceId()) {
        setAllianceId(other.getAllianceId());
      }
      if (other.hasAllianceName()) {
        bitField0_ |= 0x00000002;
        allianceName_ = other.allianceName_;
        onChanged();
      }
      if (other.hasShortName()) {
        bitField0_ |= 0x00000004;
        shortName_ = other.shortName_;
        onChanged();
      }
      if (other.hasMyScore()) {
        setMyScore(other.getMyScore());
      }
      if (other.hasFlagColor()) {
        setFlagColor(other.getFlagColor());
      }
      if (other.hasFlagStyle()) {
        setFlagStyle(other.getFlagStyle());
      }
      if (other.hasFlagEffect()) {
        setFlagEffect(other.getFlagEffect());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasAllianceId()) {
        return false;
      }
      if (!hasAllianceName()) {
        return false;
      }
      if (!hasShortName()) {
        return false;
      }
      if (!hasMyScore()) {
        return false;
      }
      if (!hasFlagColor()) {
        return false;
      }
      if (!hasFlagStyle()) {
        return false;
      }
      if (!hasFlagEffect()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.AllianceActivityRankVo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.AllianceActivityRankVo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long allianceId_ ;
    /**
     * <pre>
     * 联盟Id
     * </pre>
     *
     * <code>required int64 allianceId = 1;</code>
     */
    public boolean hasAllianceId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 联盟Id
     * </pre>
     *
     * <code>required int64 allianceId = 1;</code>
     */
    public long getAllianceId() {
      return allianceId_;
    }
    /**
     * <pre>
     * 联盟Id
     * </pre>
     *
     * <code>required int64 allianceId = 1;</code>
     */
    public Builder setAllianceId(long value) {
      bitField0_ |= 0x00000001;
      allianceId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 联盟Id
     * </pre>
     *
     * <code>required int64 allianceId = 1;</code>
     */
    public Builder clearAllianceId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      allianceId_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object allianceName_ = "";
    /**
     * <pre>
     * 联盟名
     * </pre>
     *
     * <code>required string allianceName = 2;</code>
     */
    public boolean hasAllianceName() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 联盟名
     * </pre>
     *
     * <code>required string allianceName = 2;</code>
     */
    public java.lang.String getAllianceName() {
      java.lang.Object ref = allianceName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          allianceName_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * 联盟名
     * </pre>
     *
     * <code>required string allianceName = 2;</code>
     */
    public com.google.protobuf.ByteString
        getAllianceNameBytes() {
      java.lang.Object ref = allianceName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        allianceName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * 联盟名
     * </pre>
     *
     * <code>required string allianceName = 2;</code>
     */
    public Builder setAllianceName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      allianceName_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 联盟名
     * </pre>
     *
     * <code>required string allianceName = 2;</code>
     */
    public Builder clearAllianceName() {
      bitField0_ = (bitField0_ & ~0x00000002);
      allianceName_ = getDefaultInstance().getAllianceName();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 联盟名
     * </pre>
     *
     * <code>required string allianceName = 2;</code>
     */
    public Builder setAllianceNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      allianceName_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object shortName_ = "";
    /**
     * <pre>
     * 联盟简称
     * </pre>
     *
     * <code>required string shortName = 3;</code>
     */
    public boolean hasShortName() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     * 联盟简称
     * </pre>
     *
     * <code>required string shortName = 3;</code>
     */
    public java.lang.String getShortName() {
      java.lang.Object ref = shortName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          shortName_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * 联盟简称
     * </pre>
     *
     * <code>required string shortName = 3;</code>
     */
    public com.google.protobuf.ByteString
        getShortNameBytes() {
      java.lang.Object ref = shortName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        shortName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * 联盟简称
     * </pre>
     *
     * <code>required string shortName = 3;</code>
     */
    public Builder setShortName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
      shortName_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 联盟简称
     * </pre>
     *
     * <code>required string shortName = 3;</code>
     */
    public Builder clearShortName() {
      bitField0_ = (bitField0_ & ~0x00000004);
      shortName_ = getDefaultInstance().getShortName();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 联盟简称
     * </pre>
     *
     * <code>required string shortName = 3;</code>
     */
    public Builder setShortNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
      shortName_ = value;
      onChanged();
      return this;
    }

    private int myScore_ ;
    /**
     * <pre>
     * 积分记录
     * </pre>
     *
     * <code>required int32 myScore = 4;</code>
     */
    public boolean hasMyScore() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     * 积分记录
     * </pre>
     *
     * <code>required int32 myScore = 4;</code>
     */
    public int getMyScore() {
      return myScore_;
    }
    /**
     * <pre>
     * 积分记录
     * </pre>
     *
     * <code>required int32 myScore = 4;</code>
     */
    public Builder setMyScore(int value) {
      bitField0_ |= 0x00000008;
      myScore_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 积分记录
     * </pre>
     *
     * <code>required int32 myScore = 4;</code>
     */
    public Builder clearMyScore() {
      bitField0_ = (bitField0_ & ~0x00000008);
      myScore_ = 0;
      onChanged();
      return this;
    }

    private int flagColor_ ;
    /**
     * <pre>
     *旗帜颜色
     * </pre>
     *
     * <code>required int32 flagColor = 5;</code>
     */
    public boolean hasFlagColor() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <pre>
     *旗帜颜色
     * </pre>
     *
     * <code>required int32 flagColor = 5;</code>
     */
    public int getFlagColor() {
      return flagColor_;
    }
    /**
     * <pre>
     *旗帜颜色
     * </pre>
     *
     * <code>required int32 flagColor = 5;</code>
     */
    public Builder setFlagColor(int value) {
      bitField0_ |= 0x00000010;
      flagColor_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *旗帜颜色
     * </pre>
     *
     * <code>required int32 flagColor = 5;</code>
     */
    public Builder clearFlagColor() {
      bitField0_ = (bitField0_ & ~0x00000010);
      flagColor_ = 0;
      onChanged();
      return this;
    }

    private int flagStyle_ ;
    /**
     * <pre>
     *旗帜样式
     * </pre>
     *
     * <code>required int32 flagStyle = 6;</code>
     */
    public boolean hasFlagStyle() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    /**
     * <pre>
     *旗帜样式
     * </pre>
     *
     * <code>required int32 flagStyle = 6;</code>
     */
    public int getFlagStyle() {
      return flagStyle_;
    }
    /**
     * <pre>
     *旗帜样式
     * </pre>
     *
     * <code>required int32 flagStyle = 6;</code>
     */
    public Builder setFlagStyle(int value) {
      bitField0_ |= 0x00000020;
      flagStyle_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *旗帜样式
     * </pre>
     *
     * <code>required int32 flagStyle = 6;</code>
     */
    public Builder clearFlagStyle() {
      bitField0_ = (bitField0_ & ~0x00000020);
      flagStyle_ = 0;
      onChanged();
      return this;
    }

    private int flagEffect_ ;
    /**
     * <pre>
     *旗帜图案
     * </pre>
     *
     * <code>required int32 flagEffect = 7;</code>
     */
    public boolean hasFlagEffect() {
      return ((bitField0_ & 0x00000040) == 0x00000040);
    }
    /**
     * <pre>
     *旗帜图案
     * </pre>
     *
     * <code>required int32 flagEffect = 7;</code>
     */
    public int getFlagEffect() {
      return flagEffect_;
    }
    /**
     * <pre>
     *旗帜图案
     * </pre>
     *
     * <code>required int32 flagEffect = 7;</code>
     */
    public Builder setFlagEffect(int value) {
      bitField0_ |= 0x00000040;
      flagEffect_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *旗帜图案
     * </pre>
     *
     * <code>required int32 flagEffect = 7;</code>
     */
    public Builder clearFlagEffect() {
      bitField0_ = (bitField0_ & ~0x00000040);
      flagEffect_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.AllianceActivityRankVo)
  }

  // @@protoc_insertion_point(class_scope:client2server.AllianceActivityRankVo)
  private static final pb4client.AllianceActivityRankVo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.AllianceActivityRankVo();
  }

  public static pb4client.AllianceActivityRankVo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<AllianceActivityRankVo>
      PARSER = new com.google.protobuf.AbstractParser<AllianceActivityRankVo>() {
    public AllianceActivityRankVo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new AllianceActivityRankVo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AllianceActivityRankVo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AllianceActivityRankVo> getParserForType() {
    return PARSER;
  }

  public pb4client.AllianceActivityRankVo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

