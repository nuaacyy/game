// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * 联盟统计信息
 * </pre>
 *
 * Protobuf type {@code client2server.AllianceCountInfo}
 */
public  final class AllianceCountInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.AllianceCountInfo)
    AllianceCountInfoOrBuilder {
  // Use AllianceCountInfo.newBuilder() to construct.
  private AllianceCountInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AllianceCountInfo() {
    day_ = 0;
    playerId_ = 0L;
    playerName_ = "";
    contributions_ = 0;
    meritorious_ = 0;
    landDurables_ = 0;
    cityDurables_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AllianceCountInfo(
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
            day_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            playerId_ = input.readInt64();
            break;
          }
          case 26: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000004;
            playerName_ = bs;
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            contributions_ = input.readInt32();
            break;
          }
          case 40: {
            bitField0_ |= 0x00000010;
            meritorious_ = input.readInt32();
            break;
          }
          case 48: {
            bitField0_ |= 0x00000020;
            landDurables_ = input.readInt32();
            break;
          }
          case 56: {
            bitField0_ |= 0x00000040;
            cityDurables_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_AllianceCountInfo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_AllianceCountInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.AllianceCountInfo.class, pb4client.AllianceCountInfo.Builder.class);
  }

  private int bitField0_;
  public static final int DAY_FIELD_NUMBER = 1;
  private int day_;
  /**
   * <pre>
   *查询期间：0-今天；1-昨天
   * </pre>
   *
   * <code>required int32 day = 1;</code>
   */
  public boolean hasDay() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *查询期间：0-今天；1-昨天
   * </pre>
   *
   * <code>required int32 day = 1;</code>
   */
  public int getDay() {
    return day_;
  }

  public static final int PLAYERID_FIELD_NUMBER = 2;
  private long playerId_;
  /**
   * <pre>
   *玩家ID
   * </pre>
   *
   * <code>required int64 playerId = 2;</code>
   */
  public boolean hasPlayerId() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *玩家ID
   * </pre>
   *
   * <code>required int64 playerId = 2;</code>
   */
  public long getPlayerId() {
    return playerId_;
  }

  public static final int PLAYERNAME_FIELD_NUMBER = 3;
  private volatile java.lang.Object playerName_;
  /**
   * <pre>
   *玩家名称
   * </pre>
   *
   * <code>required string playerName = 3;</code>
   */
  public boolean hasPlayerName() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   *玩家名称
   * </pre>
   *
   * <code>required string playerName = 3;</code>
   */
  public java.lang.String getPlayerName() {
    java.lang.Object ref = playerName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        playerName_ = s;
      }
      return s;
    }
  }
  /**
   * <pre>
   *玩家名称
   * </pre>
   *
   * <code>required string playerName = 3;</code>
   */
  public com.google.protobuf.ByteString
      getPlayerNameBytes() {
    java.lang.Object ref = playerName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      playerName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int CONTRIBUTIONS_FIELD_NUMBER = 4;
  private int contributions_;
  /**
   * <pre>
   *贡献
   * </pre>
   *
   * <code>required int32 contributions = 4;</code>
   */
  public boolean hasContributions() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   *贡献
   * </pre>
   *
   * <code>required int32 contributions = 4;</code>
   */
  public int getContributions() {
    return contributions_;
  }

  public static final int MERITORIOUS_FIELD_NUMBER = 5;
  private int meritorious_;
  /**
   * <pre>
   *功勋
   * </pre>
   *
   * <code>required int32 meritorious = 5;</code>
   */
  public boolean hasMeritorious() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <pre>
   *功勋
   * </pre>
   *
   * <code>required int32 meritorious = 5;</code>
   */
  public int getMeritorious() {
    return meritorious_;
  }

  public static final int LANDDURABLES_FIELD_NUMBER = 6;
  private int landDurables_;
  /**
   * <pre>
   *拆地值
   * </pre>
   *
   * <code>required int32 landDurables = 6;</code>
   */
  public boolean hasLandDurables() {
    return ((bitField0_ & 0x00000020) == 0x00000020);
  }
  /**
   * <pre>
   *拆地值
   * </pre>
   *
   * <code>required int32 landDurables = 6;</code>
   */
  public int getLandDurables() {
    return landDurables_;
  }

  public static final int CITYDURABLES_FIELD_NUMBER = 7;
  private int cityDurables_;
  /**
   * <pre>
   *攻城值
   * </pre>
   *
   * <code>required int32 cityDurables = 7;</code>
   */
  public boolean hasCityDurables() {
    return ((bitField0_ & 0x00000040) == 0x00000040);
  }
  /**
   * <pre>
   *攻城值
   * </pre>
   *
   * <code>required int32 cityDurables = 7;</code>
   */
  public int getCityDurables() {
    return cityDurables_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasDay()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasPlayerId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasPlayerName()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasContributions()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasMeritorious()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasLandDurables()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasCityDurables()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, day_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt64(2, playerId_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, playerName_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt32(4, contributions_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      output.writeInt32(5, meritorious_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      output.writeInt32(6, landDurables_);
    }
    if (((bitField0_ & 0x00000040) == 0x00000040)) {
      output.writeInt32(7, cityDurables_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, day_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, playerId_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, playerName_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, contributions_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, meritorious_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, landDurables_);
    }
    if (((bitField0_ & 0x00000040) == 0x00000040)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(7, cityDurables_);
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
    if (!(obj instanceof pb4client.AllianceCountInfo)) {
      return super.equals(obj);
    }
    pb4client.AllianceCountInfo other = (pb4client.AllianceCountInfo) obj;

    boolean result = true;
    result = result && (hasDay() == other.hasDay());
    if (hasDay()) {
      result = result && (getDay()
          == other.getDay());
    }
    result = result && (hasPlayerId() == other.hasPlayerId());
    if (hasPlayerId()) {
      result = result && (getPlayerId()
          == other.getPlayerId());
    }
    result = result && (hasPlayerName() == other.hasPlayerName());
    if (hasPlayerName()) {
      result = result && getPlayerName()
          .equals(other.getPlayerName());
    }
    result = result && (hasContributions() == other.hasContributions());
    if (hasContributions()) {
      result = result && (getContributions()
          == other.getContributions());
    }
    result = result && (hasMeritorious() == other.hasMeritorious());
    if (hasMeritorious()) {
      result = result && (getMeritorious()
          == other.getMeritorious());
    }
    result = result && (hasLandDurables() == other.hasLandDurables());
    if (hasLandDurables()) {
      result = result && (getLandDurables()
          == other.getLandDurables());
    }
    result = result && (hasCityDurables() == other.hasCityDurables());
    if (hasCityDurables()) {
      result = result && (getCityDurables()
          == other.getCityDurables());
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
    if (hasDay()) {
      hash = (37 * hash) + DAY_FIELD_NUMBER;
      hash = (53 * hash) + getDay();
    }
    if (hasPlayerId()) {
      hash = (37 * hash) + PLAYERID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getPlayerId());
    }
    if (hasPlayerName()) {
      hash = (37 * hash) + PLAYERNAME_FIELD_NUMBER;
      hash = (53 * hash) + getPlayerName().hashCode();
    }
    if (hasContributions()) {
      hash = (37 * hash) + CONTRIBUTIONS_FIELD_NUMBER;
      hash = (53 * hash) + getContributions();
    }
    if (hasMeritorious()) {
      hash = (37 * hash) + MERITORIOUS_FIELD_NUMBER;
      hash = (53 * hash) + getMeritorious();
    }
    if (hasLandDurables()) {
      hash = (37 * hash) + LANDDURABLES_FIELD_NUMBER;
      hash = (53 * hash) + getLandDurables();
    }
    if (hasCityDurables()) {
      hash = (37 * hash) + CITYDURABLES_FIELD_NUMBER;
      hash = (53 * hash) + getCityDurables();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.AllianceCountInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceCountInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceCountInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceCountInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceCountInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceCountInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceCountInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.AllianceCountInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.AllianceCountInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.AllianceCountInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.AllianceCountInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.AllianceCountInfo parseFrom(
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
  public static Builder newBuilder(pb4client.AllianceCountInfo prototype) {
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
   * 联盟统计信息
   * </pre>
   *
   * Protobuf type {@code client2server.AllianceCountInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.AllianceCountInfo)
      pb4client.AllianceCountInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceCountInfo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceCountInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.AllianceCountInfo.class, pb4client.AllianceCountInfo.Builder.class);
    }

    // Construct using pb4client.AllianceCountInfo.newBuilder()
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
      day_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      playerId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000002);
      playerName_ = "";
      bitField0_ = (bitField0_ & ~0x00000004);
      contributions_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      meritorious_ = 0;
      bitField0_ = (bitField0_ & ~0x00000010);
      landDurables_ = 0;
      bitField0_ = (bitField0_ & ~0x00000020);
      cityDurables_ = 0;
      bitField0_ = (bitField0_ & ~0x00000040);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceCountInfo_descriptor;
    }

    public pb4client.AllianceCountInfo getDefaultInstanceForType() {
      return pb4client.AllianceCountInfo.getDefaultInstance();
    }

    public pb4client.AllianceCountInfo build() {
      pb4client.AllianceCountInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.AllianceCountInfo buildPartial() {
      pb4client.AllianceCountInfo result = new pb4client.AllianceCountInfo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.day_ = day_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.playerId_ = playerId_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.playerName_ = playerName_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.contributions_ = contributions_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.meritorious_ = meritorious_;
      if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
        to_bitField0_ |= 0x00000020;
      }
      result.landDurables_ = landDurables_;
      if (((from_bitField0_ & 0x00000040) == 0x00000040)) {
        to_bitField0_ |= 0x00000040;
      }
      result.cityDurables_ = cityDurables_;
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
      if (other instanceof pb4client.AllianceCountInfo) {
        return mergeFrom((pb4client.AllianceCountInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.AllianceCountInfo other) {
      if (other == pb4client.AllianceCountInfo.getDefaultInstance()) return this;
      if (other.hasDay()) {
        setDay(other.getDay());
      }
      if (other.hasPlayerId()) {
        setPlayerId(other.getPlayerId());
      }
      if (other.hasPlayerName()) {
        bitField0_ |= 0x00000004;
        playerName_ = other.playerName_;
        onChanged();
      }
      if (other.hasContributions()) {
        setContributions(other.getContributions());
      }
      if (other.hasMeritorious()) {
        setMeritorious(other.getMeritorious());
      }
      if (other.hasLandDurables()) {
        setLandDurables(other.getLandDurables());
      }
      if (other.hasCityDurables()) {
        setCityDurables(other.getCityDurables());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasDay()) {
        return false;
      }
      if (!hasPlayerId()) {
        return false;
      }
      if (!hasPlayerName()) {
        return false;
      }
      if (!hasContributions()) {
        return false;
      }
      if (!hasMeritorious()) {
        return false;
      }
      if (!hasLandDurables()) {
        return false;
      }
      if (!hasCityDurables()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.AllianceCountInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.AllianceCountInfo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int day_ ;
    /**
     * <pre>
     *查询期间：0-今天；1-昨天
     * </pre>
     *
     * <code>required int32 day = 1;</code>
     */
    public boolean hasDay() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *查询期间：0-今天；1-昨天
     * </pre>
     *
     * <code>required int32 day = 1;</code>
     */
    public int getDay() {
      return day_;
    }
    /**
     * <pre>
     *查询期间：0-今天；1-昨天
     * </pre>
     *
     * <code>required int32 day = 1;</code>
     */
    public Builder setDay(int value) {
      bitField0_ |= 0x00000001;
      day_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *查询期间：0-今天；1-昨天
     * </pre>
     *
     * <code>required int32 day = 1;</code>
     */
    public Builder clearDay() {
      bitField0_ = (bitField0_ & ~0x00000001);
      day_ = 0;
      onChanged();
      return this;
    }

    private long playerId_ ;
    /**
     * <pre>
     *玩家ID
     * </pre>
     *
     * <code>required int64 playerId = 2;</code>
     */
    public boolean hasPlayerId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *玩家ID
     * </pre>
     *
     * <code>required int64 playerId = 2;</code>
     */
    public long getPlayerId() {
      return playerId_;
    }
    /**
     * <pre>
     *玩家ID
     * </pre>
     *
     * <code>required int64 playerId = 2;</code>
     */
    public Builder setPlayerId(long value) {
      bitField0_ |= 0x00000002;
      playerId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *玩家ID
     * </pre>
     *
     * <code>required int64 playerId = 2;</code>
     */
    public Builder clearPlayerId() {
      bitField0_ = (bitField0_ & ~0x00000002);
      playerId_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object playerName_ = "";
    /**
     * <pre>
     *玩家名称
     * </pre>
     *
     * <code>required string playerName = 3;</code>
     */
    public boolean hasPlayerName() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     *玩家名称
     * </pre>
     *
     * <code>required string playerName = 3;</code>
     */
    public java.lang.String getPlayerName() {
      java.lang.Object ref = playerName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          playerName_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *玩家名称
     * </pre>
     *
     * <code>required string playerName = 3;</code>
     */
    public com.google.protobuf.ByteString
        getPlayerNameBytes() {
      java.lang.Object ref = playerName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        playerName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *玩家名称
     * </pre>
     *
     * <code>required string playerName = 3;</code>
     */
    public Builder setPlayerName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
      playerName_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *玩家名称
     * </pre>
     *
     * <code>required string playerName = 3;</code>
     */
    public Builder clearPlayerName() {
      bitField0_ = (bitField0_ & ~0x00000004);
      playerName_ = getDefaultInstance().getPlayerName();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *玩家名称
     * </pre>
     *
     * <code>required string playerName = 3;</code>
     */
    public Builder setPlayerNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
      playerName_ = value;
      onChanged();
      return this;
    }

    private int contributions_ ;
    /**
     * <pre>
     *贡献
     * </pre>
     *
     * <code>required int32 contributions = 4;</code>
     */
    public boolean hasContributions() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     *贡献
     * </pre>
     *
     * <code>required int32 contributions = 4;</code>
     */
    public int getContributions() {
      return contributions_;
    }
    /**
     * <pre>
     *贡献
     * </pre>
     *
     * <code>required int32 contributions = 4;</code>
     */
    public Builder setContributions(int value) {
      bitField0_ |= 0x00000008;
      contributions_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *贡献
     * </pre>
     *
     * <code>required int32 contributions = 4;</code>
     */
    public Builder clearContributions() {
      bitField0_ = (bitField0_ & ~0x00000008);
      contributions_ = 0;
      onChanged();
      return this;
    }

    private int meritorious_ ;
    /**
     * <pre>
     *功勋
     * </pre>
     *
     * <code>required int32 meritorious = 5;</code>
     */
    public boolean hasMeritorious() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <pre>
     *功勋
     * </pre>
     *
     * <code>required int32 meritorious = 5;</code>
     */
    public int getMeritorious() {
      return meritorious_;
    }
    /**
     * <pre>
     *功勋
     * </pre>
     *
     * <code>required int32 meritorious = 5;</code>
     */
    public Builder setMeritorious(int value) {
      bitField0_ |= 0x00000010;
      meritorious_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *功勋
     * </pre>
     *
     * <code>required int32 meritorious = 5;</code>
     */
    public Builder clearMeritorious() {
      bitField0_ = (bitField0_ & ~0x00000010);
      meritorious_ = 0;
      onChanged();
      return this;
    }

    private int landDurables_ ;
    /**
     * <pre>
     *拆地值
     * </pre>
     *
     * <code>required int32 landDurables = 6;</code>
     */
    public boolean hasLandDurables() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    /**
     * <pre>
     *拆地值
     * </pre>
     *
     * <code>required int32 landDurables = 6;</code>
     */
    public int getLandDurables() {
      return landDurables_;
    }
    /**
     * <pre>
     *拆地值
     * </pre>
     *
     * <code>required int32 landDurables = 6;</code>
     */
    public Builder setLandDurables(int value) {
      bitField0_ |= 0x00000020;
      landDurables_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *拆地值
     * </pre>
     *
     * <code>required int32 landDurables = 6;</code>
     */
    public Builder clearLandDurables() {
      bitField0_ = (bitField0_ & ~0x00000020);
      landDurables_ = 0;
      onChanged();
      return this;
    }

    private int cityDurables_ ;
    /**
     * <pre>
     *攻城值
     * </pre>
     *
     * <code>required int32 cityDurables = 7;</code>
     */
    public boolean hasCityDurables() {
      return ((bitField0_ & 0x00000040) == 0x00000040);
    }
    /**
     * <pre>
     *攻城值
     * </pre>
     *
     * <code>required int32 cityDurables = 7;</code>
     */
    public int getCityDurables() {
      return cityDurables_;
    }
    /**
     * <pre>
     *攻城值
     * </pre>
     *
     * <code>required int32 cityDurables = 7;</code>
     */
    public Builder setCityDurables(int value) {
      bitField0_ |= 0x00000040;
      cityDurables_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *攻城值
     * </pre>
     *
     * <code>required int32 cityDurables = 7;</code>
     */
    public Builder clearCityDurables() {
      bitField0_ = (bitField0_ & ~0x00000040);
      cityDurables_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.AllianceCountInfo)
  }

  // @@protoc_insertion_point(class_scope:client2server.AllianceCountInfo)
  private static final pb4client.AllianceCountInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.AllianceCountInfo();
  }

  public static pb4client.AllianceCountInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<AllianceCountInfo>
      PARSER = new com.google.protobuf.AbstractParser<AllianceCountInfo>() {
    public AllianceCountInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new AllianceCountInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AllianceCountInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AllianceCountInfo> getParserForType() {
    return PARSER;
  }

  public pb4client.AllianceCountInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

