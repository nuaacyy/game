// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * 资源地块信息
 * </pre>
 *
 * Protobuf type {@code client2server.ResLandInfo}
 */
public  final class ResLandInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.ResLandInfo)
    ResLandInfoOrBuilder {
  // Use ResLandInfo.newBuilder() to construct.
  private ResLandInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ResLandInfo() {
    lv_ = 0;
    resType_ = 0;
    resNum_ = 0;
    forceState_ = 0;
    playerId_ = 0L;
    allianceId_ = 0L;
    disappearTime_ = 0;
    tileId_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ResLandInfo(
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
            lv_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            resType_ = input.readInt32();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            resNum_ = input.readInt32();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            forceState_ = input.readInt32();
            break;
          }
          case 40: {
            bitField0_ |= 0x00000010;
            playerId_ = input.readInt64();
            break;
          }
          case 48: {
            bitField0_ |= 0x00000020;
            allianceId_ = input.readInt64();
            break;
          }
          case 56: {
            bitField0_ |= 0x00000040;
            disappearTime_ = input.readInt32();
            break;
          }
          case 800: {
            bitField0_ |= 0x00000080;
            tileId_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_ResLandInfo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_ResLandInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.ResLandInfo.class, pb4client.ResLandInfo.Builder.class);
  }

  private int bitField0_;
  public static final int LV_FIELD_NUMBER = 1;
  private int lv_;
  /**
   * <pre>
   * 等级
   * </pre>
   *
   * <code>required int32 lv = 1;</code>
   */
  public boolean hasLv() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 等级
   * </pre>
   *
   * <code>required int32 lv = 1;</code>
   */
  public int getLv() {
    return lv_;
  }

  public static final int RESTYPE_FIELD_NUMBER = 2;
  private int resType_;
  /**
   * <pre>
   * 资源点类型
   * </pre>
   *
   * <code>required int32 resType = 2;</code>
   */
  public boolean hasResType() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   * 资源点类型
   * </pre>
   *
   * <code>required int32 resType = 2;</code>
   */
  public int getResType() {
    return resType_;
  }

  public static final int RESNUM_FIELD_NUMBER = 3;
  private int resNum_;
  /**
   * <pre>
   * 资源点值
   * </pre>
   *
   * <code>required int32 resNum = 3;</code>
   */
  public boolean hasResNum() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   * 资源点值
   * </pre>
   *
   * <code>required int32 resNum = 3;</code>
   */
  public int getResNum() {
    return resNum_;
  }

  public static final int FORCESTATE_FIELD_NUMBER = 4;
  private int forceState_;
  /**
   * <pre>
   * 部队状态 0、无  2、驻扎 8、采集
   * </pre>
   *
   * <code>optional int32 forceState = 4;</code>
   */
  public boolean hasForceState() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   * 部队状态 0、无  2、驻扎 8、采集
   * </pre>
   *
   * <code>optional int32 forceState = 4;</code>
   */
  public int getForceState() {
    return forceState_;
  }

  public static final int PLAYERID_FIELD_NUMBER = 5;
  private long playerId_;
  /**
   * <pre>
   * 菜鸡的玩家ID
   * </pre>
   *
   * <code>optional int64 playerId = 5;</code>
   */
  public boolean hasPlayerId() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <pre>
   * 菜鸡的玩家ID
   * </pre>
   *
   * <code>optional int64 playerId = 5;</code>
   */
  public long getPlayerId() {
    return playerId_;
  }

  public static final int ALLIANCEID_FIELD_NUMBER = 6;
  private long allianceId_;
  /**
   * <pre>
   * 联盟ID
   * </pre>
   *
   * <code>optional int64 allianceId = 6;</code>
   */
  public boolean hasAllianceId() {
    return ((bitField0_ & 0x00000020) == 0x00000020);
  }
  /**
   * <pre>
   * 联盟ID
   * </pre>
   *
   * <code>optional int64 allianceId = 6;</code>
   */
  public long getAllianceId() {
    return allianceId_;
  }

  public static final int DISAPPEARTIME_FIELD_NUMBER = 7;
  private int disappearTime_;
  /**
   * <pre>
   *资源地消失时间
   * </pre>
   *
   * <code>optional int32 disappearTime = 7;</code>
   */
  public boolean hasDisappearTime() {
    return ((bitField0_ & 0x00000040) == 0x00000040);
  }
  /**
   * <pre>
   *资源地消失时间
   * </pre>
   *
   * <code>optional int32 disappearTime = 7;</code>
   */
  public int getDisappearTime() {
    return disappearTime_;
  }

  public static final int TILEID_FIELD_NUMBER = 100;
  private int tileId_;
  /**
   * <pre>
   * (客户端使用)
   * </pre>
   *
   * <code>optional int32 tileId = 100;</code>
   */
  public boolean hasTileId() {
    return ((bitField0_ & 0x00000080) == 0x00000080);
  }
  /**
   * <pre>
   * (客户端使用)
   * </pre>
   *
   * <code>optional int32 tileId = 100;</code>
   */
  public int getTileId() {
    return tileId_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasLv()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasResType()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasResNum()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, lv_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, resType_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, resNum_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt32(4, forceState_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      output.writeInt64(5, playerId_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      output.writeInt64(6, allianceId_);
    }
    if (((bitField0_ & 0x00000040) == 0x00000040)) {
      output.writeInt32(7, disappearTime_);
    }
    if (((bitField0_ & 0x00000080) == 0x00000080)) {
      output.writeInt32(100, tileId_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, lv_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, resType_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, resNum_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, forceState_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(5, playerId_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(6, allianceId_);
    }
    if (((bitField0_ & 0x00000040) == 0x00000040)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(7, disappearTime_);
    }
    if (((bitField0_ & 0x00000080) == 0x00000080)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(100, tileId_);
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
    if (!(obj instanceof pb4client.ResLandInfo)) {
      return super.equals(obj);
    }
    pb4client.ResLandInfo other = (pb4client.ResLandInfo) obj;

    boolean result = true;
    result = result && (hasLv() == other.hasLv());
    if (hasLv()) {
      result = result && (getLv()
          == other.getLv());
    }
    result = result && (hasResType() == other.hasResType());
    if (hasResType()) {
      result = result && (getResType()
          == other.getResType());
    }
    result = result && (hasResNum() == other.hasResNum());
    if (hasResNum()) {
      result = result && (getResNum()
          == other.getResNum());
    }
    result = result && (hasForceState() == other.hasForceState());
    if (hasForceState()) {
      result = result && (getForceState()
          == other.getForceState());
    }
    result = result && (hasPlayerId() == other.hasPlayerId());
    if (hasPlayerId()) {
      result = result && (getPlayerId()
          == other.getPlayerId());
    }
    result = result && (hasAllianceId() == other.hasAllianceId());
    if (hasAllianceId()) {
      result = result && (getAllianceId()
          == other.getAllianceId());
    }
    result = result && (hasDisappearTime() == other.hasDisappearTime());
    if (hasDisappearTime()) {
      result = result && (getDisappearTime()
          == other.getDisappearTime());
    }
    result = result && (hasTileId() == other.hasTileId());
    if (hasTileId()) {
      result = result && (getTileId()
          == other.getTileId());
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
    if (hasLv()) {
      hash = (37 * hash) + LV_FIELD_NUMBER;
      hash = (53 * hash) + getLv();
    }
    if (hasResType()) {
      hash = (37 * hash) + RESTYPE_FIELD_NUMBER;
      hash = (53 * hash) + getResType();
    }
    if (hasResNum()) {
      hash = (37 * hash) + RESNUM_FIELD_NUMBER;
      hash = (53 * hash) + getResNum();
    }
    if (hasForceState()) {
      hash = (37 * hash) + FORCESTATE_FIELD_NUMBER;
      hash = (53 * hash) + getForceState();
    }
    if (hasPlayerId()) {
      hash = (37 * hash) + PLAYERID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getPlayerId());
    }
    if (hasAllianceId()) {
      hash = (37 * hash) + ALLIANCEID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getAllianceId());
    }
    if (hasDisappearTime()) {
      hash = (37 * hash) + DISAPPEARTIME_FIELD_NUMBER;
      hash = (53 * hash) + getDisappearTime();
    }
    if (hasTileId()) {
      hash = (37 * hash) + TILEID_FIELD_NUMBER;
      hash = (53 * hash) + getTileId();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.ResLandInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.ResLandInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.ResLandInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.ResLandInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.ResLandInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.ResLandInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.ResLandInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.ResLandInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.ResLandInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.ResLandInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.ResLandInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.ResLandInfo parseFrom(
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
  public static Builder newBuilder(pb4client.ResLandInfo prototype) {
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
   * 资源地块信息
   * </pre>
   *
   * Protobuf type {@code client2server.ResLandInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.ResLandInfo)
      pb4client.ResLandInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_ResLandInfo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_ResLandInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.ResLandInfo.class, pb4client.ResLandInfo.Builder.class);
    }

    // Construct using pb4client.ResLandInfo.newBuilder()
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
      lv_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      resType_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      resNum_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      forceState_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      playerId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000010);
      allianceId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000020);
      disappearTime_ = 0;
      bitField0_ = (bitField0_ & ~0x00000040);
      tileId_ = 0;
      bitField0_ = (bitField0_ & ~0x00000080);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_ResLandInfo_descriptor;
    }

    public pb4client.ResLandInfo getDefaultInstanceForType() {
      return pb4client.ResLandInfo.getDefaultInstance();
    }

    public pb4client.ResLandInfo build() {
      pb4client.ResLandInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.ResLandInfo buildPartial() {
      pb4client.ResLandInfo result = new pb4client.ResLandInfo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.lv_ = lv_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.resType_ = resType_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.resNum_ = resNum_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.forceState_ = forceState_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.playerId_ = playerId_;
      if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
        to_bitField0_ |= 0x00000020;
      }
      result.allianceId_ = allianceId_;
      if (((from_bitField0_ & 0x00000040) == 0x00000040)) {
        to_bitField0_ |= 0x00000040;
      }
      result.disappearTime_ = disappearTime_;
      if (((from_bitField0_ & 0x00000080) == 0x00000080)) {
        to_bitField0_ |= 0x00000080;
      }
      result.tileId_ = tileId_;
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
      if (other instanceof pb4client.ResLandInfo) {
        return mergeFrom((pb4client.ResLandInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.ResLandInfo other) {
      if (other == pb4client.ResLandInfo.getDefaultInstance()) return this;
      if (other.hasLv()) {
        setLv(other.getLv());
      }
      if (other.hasResType()) {
        setResType(other.getResType());
      }
      if (other.hasResNum()) {
        setResNum(other.getResNum());
      }
      if (other.hasForceState()) {
        setForceState(other.getForceState());
      }
      if (other.hasPlayerId()) {
        setPlayerId(other.getPlayerId());
      }
      if (other.hasAllianceId()) {
        setAllianceId(other.getAllianceId());
      }
      if (other.hasDisappearTime()) {
        setDisappearTime(other.getDisappearTime());
      }
      if (other.hasTileId()) {
        setTileId(other.getTileId());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasLv()) {
        return false;
      }
      if (!hasResType()) {
        return false;
      }
      if (!hasResNum()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.ResLandInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.ResLandInfo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int lv_ ;
    /**
     * <pre>
     * 等级
     * </pre>
     *
     * <code>required int32 lv = 1;</code>
     */
    public boolean hasLv() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 等级
     * </pre>
     *
     * <code>required int32 lv = 1;</code>
     */
    public int getLv() {
      return lv_;
    }
    /**
     * <pre>
     * 等级
     * </pre>
     *
     * <code>required int32 lv = 1;</code>
     */
    public Builder setLv(int value) {
      bitField0_ |= 0x00000001;
      lv_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 等级
     * </pre>
     *
     * <code>required int32 lv = 1;</code>
     */
    public Builder clearLv() {
      bitField0_ = (bitField0_ & ~0x00000001);
      lv_ = 0;
      onChanged();
      return this;
    }

    private int resType_ ;
    /**
     * <pre>
     * 资源点类型
     * </pre>
     *
     * <code>required int32 resType = 2;</code>
     */
    public boolean hasResType() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     * 资源点类型
     * </pre>
     *
     * <code>required int32 resType = 2;</code>
     */
    public int getResType() {
      return resType_;
    }
    /**
     * <pre>
     * 资源点类型
     * </pre>
     *
     * <code>required int32 resType = 2;</code>
     */
    public Builder setResType(int value) {
      bitField0_ |= 0x00000002;
      resType_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 资源点类型
     * </pre>
     *
     * <code>required int32 resType = 2;</code>
     */
    public Builder clearResType() {
      bitField0_ = (bitField0_ & ~0x00000002);
      resType_ = 0;
      onChanged();
      return this;
    }

    private int resNum_ ;
    /**
     * <pre>
     * 资源点值
     * </pre>
     *
     * <code>required int32 resNum = 3;</code>
     */
    public boolean hasResNum() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     * 资源点值
     * </pre>
     *
     * <code>required int32 resNum = 3;</code>
     */
    public int getResNum() {
      return resNum_;
    }
    /**
     * <pre>
     * 资源点值
     * </pre>
     *
     * <code>required int32 resNum = 3;</code>
     */
    public Builder setResNum(int value) {
      bitField0_ |= 0x00000004;
      resNum_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 资源点值
     * </pre>
     *
     * <code>required int32 resNum = 3;</code>
     */
    public Builder clearResNum() {
      bitField0_ = (bitField0_ & ~0x00000004);
      resNum_ = 0;
      onChanged();
      return this;
    }

    private int forceState_ ;
    /**
     * <pre>
     * 部队状态 0、无  2、驻扎 8、采集
     * </pre>
     *
     * <code>optional int32 forceState = 4;</code>
     */
    public boolean hasForceState() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     * 部队状态 0、无  2、驻扎 8、采集
     * </pre>
     *
     * <code>optional int32 forceState = 4;</code>
     */
    public int getForceState() {
      return forceState_;
    }
    /**
     * <pre>
     * 部队状态 0、无  2、驻扎 8、采集
     * </pre>
     *
     * <code>optional int32 forceState = 4;</code>
     */
    public Builder setForceState(int value) {
      bitField0_ |= 0x00000008;
      forceState_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 部队状态 0、无  2、驻扎 8、采集
     * </pre>
     *
     * <code>optional int32 forceState = 4;</code>
     */
    public Builder clearForceState() {
      bitField0_ = (bitField0_ & ~0x00000008);
      forceState_ = 0;
      onChanged();
      return this;
    }

    private long playerId_ ;
    /**
     * <pre>
     * 菜鸡的玩家ID
     * </pre>
     *
     * <code>optional int64 playerId = 5;</code>
     */
    public boolean hasPlayerId() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <pre>
     * 菜鸡的玩家ID
     * </pre>
     *
     * <code>optional int64 playerId = 5;</code>
     */
    public long getPlayerId() {
      return playerId_;
    }
    /**
     * <pre>
     * 菜鸡的玩家ID
     * </pre>
     *
     * <code>optional int64 playerId = 5;</code>
     */
    public Builder setPlayerId(long value) {
      bitField0_ |= 0x00000010;
      playerId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 菜鸡的玩家ID
     * </pre>
     *
     * <code>optional int64 playerId = 5;</code>
     */
    public Builder clearPlayerId() {
      bitField0_ = (bitField0_ & ~0x00000010);
      playerId_ = 0L;
      onChanged();
      return this;
    }

    private long allianceId_ ;
    /**
     * <pre>
     * 联盟ID
     * </pre>
     *
     * <code>optional int64 allianceId = 6;</code>
     */
    public boolean hasAllianceId() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    /**
     * <pre>
     * 联盟ID
     * </pre>
     *
     * <code>optional int64 allianceId = 6;</code>
     */
    public long getAllianceId() {
      return allianceId_;
    }
    /**
     * <pre>
     * 联盟ID
     * </pre>
     *
     * <code>optional int64 allianceId = 6;</code>
     */
    public Builder setAllianceId(long value) {
      bitField0_ |= 0x00000020;
      allianceId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 联盟ID
     * </pre>
     *
     * <code>optional int64 allianceId = 6;</code>
     */
    public Builder clearAllianceId() {
      bitField0_ = (bitField0_ & ~0x00000020);
      allianceId_ = 0L;
      onChanged();
      return this;
    }

    private int disappearTime_ ;
    /**
     * <pre>
     *资源地消失时间
     * </pre>
     *
     * <code>optional int32 disappearTime = 7;</code>
     */
    public boolean hasDisappearTime() {
      return ((bitField0_ & 0x00000040) == 0x00000040);
    }
    /**
     * <pre>
     *资源地消失时间
     * </pre>
     *
     * <code>optional int32 disappearTime = 7;</code>
     */
    public int getDisappearTime() {
      return disappearTime_;
    }
    /**
     * <pre>
     *资源地消失时间
     * </pre>
     *
     * <code>optional int32 disappearTime = 7;</code>
     */
    public Builder setDisappearTime(int value) {
      bitField0_ |= 0x00000040;
      disappearTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *资源地消失时间
     * </pre>
     *
     * <code>optional int32 disappearTime = 7;</code>
     */
    public Builder clearDisappearTime() {
      bitField0_ = (bitField0_ & ~0x00000040);
      disappearTime_ = 0;
      onChanged();
      return this;
    }

    private int tileId_ ;
    /**
     * <pre>
     * (客户端使用)
     * </pre>
     *
     * <code>optional int32 tileId = 100;</code>
     */
    public boolean hasTileId() {
      return ((bitField0_ & 0x00000080) == 0x00000080);
    }
    /**
     * <pre>
     * (客户端使用)
     * </pre>
     *
     * <code>optional int32 tileId = 100;</code>
     */
    public int getTileId() {
      return tileId_;
    }
    /**
     * <pre>
     * (客户端使用)
     * </pre>
     *
     * <code>optional int32 tileId = 100;</code>
     */
    public Builder setTileId(int value) {
      bitField0_ |= 0x00000080;
      tileId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * (客户端使用)
     * </pre>
     *
     * <code>optional int32 tileId = 100;</code>
     */
    public Builder clearTileId() {
      bitField0_ = (bitField0_ & ~0x00000080);
      tileId_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.ResLandInfo)
  }

  // @@protoc_insertion_point(class_scope:client2server.ResLandInfo)
  private static final pb4client.ResLandInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.ResLandInfo();
  }

  public static pb4client.ResLandInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<ResLandInfo>
      PARSER = new com.google.protobuf.AbstractParser<ResLandInfo>() {
    public ResLandInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new ResLandInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ResLandInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ResLandInfo> getParserForType() {
    return PARSER;
  }

  public pb4client.ResLandInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
