// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.WorldBossRank}
 */
public  final class WorldBossRank extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.WorldBossRank)
    WorldBossRankOrBuilder {
  // Use WorldBossRank.newBuilder() to construct.
  private WorldBossRank(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private WorldBossRank() {
    playerId_ = 0L;
    playerName_ = "";
    allianceName_ = "";
    allianceShortName_ = "";
    hurt_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private WorldBossRank(
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
          case 18: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000002;
            playerName_ = bs;
            break;
          }
          case 26: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000004;
            allianceName_ = bs;
            break;
          }
          case 34: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000008;
            allianceShortName_ = bs;
            break;
          }
          case 40: {
            bitField0_ |= 0x00000010;
            hurt_ = input.readInt64();
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
    return pb4client.War2GamePkt.internal_static_client2server_WorldBossRank_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_WorldBossRank_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.WorldBossRank.class, pb4client.WorldBossRank.Builder.class);
  }

  private int bitField0_;
  public static final int PLAYERID_FIELD_NUMBER = 1;
  private long playerId_;
  /**
   * <code>required int64 playerId = 1;</code>
   */
  public boolean hasPlayerId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>required int64 playerId = 1;</code>
   */
  public long getPlayerId() {
    return playerId_;
  }

  public static final int PLAYERNAME_FIELD_NUMBER = 2;
  private volatile java.lang.Object playerName_;
  /**
   * <code>required string playerName = 2;</code>
   */
  public boolean hasPlayerName() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>required string playerName = 2;</code>
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
   * <code>required string playerName = 2;</code>
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

  public static final int ALLIANCENAME_FIELD_NUMBER = 3;
  private volatile java.lang.Object allianceName_;
  /**
   * <code>required string allianceName = 3;</code>
   */
  public boolean hasAllianceName() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <code>required string allianceName = 3;</code>
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
   * <code>required string allianceName = 3;</code>
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

  public static final int ALLIANCESHORTNAME_FIELD_NUMBER = 4;
  private volatile java.lang.Object allianceShortName_;
  /**
   * <code>required string allianceShortName = 4;</code>
   */
  public boolean hasAllianceShortName() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <code>required string allianceShortName = 4;</code>
   */
  public java.lang.String getAllianceShortName() {
    java.lang.Object ref = allianceShortName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        allianceShortName_ = s;
      }
      return s;
    }
  }
  /**
   * <code>required string allianceShortName = 4;</code>
   */
  public com.google.protobuf.ByteString
      getAllianceShortNameBytes() {
    java.lang.Object ref = allianceShortName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      allianceShortName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int HURT_FIELD_NUMBER = 5;
  private long hurt_;
  /**
   * <code>required int64 hurt = 5;</code>
   */
  public boolean hasHurt() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <code>required int64 hurt = 5;</code>
   */
  public long getHurt() {
    return hurt_;
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
    if (!hasPlayerName()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasAllianceName()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasAllianceShortName()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasHurt()) {
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
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, playerName_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, allianceName_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, allianceShortName_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      output.writeInt64(5, hurt_);
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
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, playerName_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, allianceName_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, allianceShortName_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(5, hurt_);
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
    if (!(obj instanceof pb4client.WorldBossRank)) {
      return super.equals(obj);
    }
    pb4client.WorldBossRank other = (pb4client.WorldBossRank) obj;

    boolean result = true;
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
    result = result && (hasAllianceName() == other.hasAllianceName());
    if (hasAllianceName()) {
      result = result && getAllianceName()
          .equals(other.getAllianceName());
    }
    result = result && (hasAllianceShortName() == other.hasAllianceShortName());
    if (hasAllianceShortName()) {
      result = result && getAllianceShortName()
          .equals(other.getAllianceShortName());
    }
    result = result && (hasHurt() == other.hasHurt());
    if (hasHurt()) {
      result = result && (getHurt()
          == other.getHurt());
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
    if (hasPlayerName()) {
      hash = (37 * hash) + PLAYERNAME_FIELD_NUMBER;
      hash = (53 * hash) + getPlayerName().hashCode();
    }
    if (hasAllianceName()) {
      hash = (37 * hash) + ALLIANCENAME_FIELD_NUMBER;
      hash = (53 * hash) + getAllianceName().hashCode();
    }
    if (hasAllianceShortName()) {
      hash = (37 * hash) + ALLIANCESHORTNAME_FIELD_NUMBER;
      hash = (53 * hash) + getAllianceShortName().hashCode();
    }
    if (hasHurt()) {
      hash = (37 * hash) + HURT_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getHurt());
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.WorldBossRank parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.WorldBossRank parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.WorldBossRank parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.WorldBossRank parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.WorldBossRank parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.WorldBossRank parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.WorldBossRank parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.WorldBossRank parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.WorldBossRank parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.WorldBossRank parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.WorldBossRank parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.WorldBossRank parseFrom(
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
  public static Builder newBuilder(pb4client.WorldBossRank prototype) {
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
   * Protobuf type {@code client2server.WorldBossRank}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.WorldBossRank)
      pb4client.WorldBossRankOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_WorldBossRank_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_WorldBossRank_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.WorldBossRank.class, pb4client.WorldBossRank.Builder.class);
    }

    // Construct using pb4client.WorldBossRank.newBuilder()
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
      playerName_ = "";
      bitField0_ = (bitField0_ & ~0x00000002);
      allianceName_ = "";
      bitField0_ = (bitField0_ & ~0x00000004);
      allianceShortName_ = "";
      bitField0_ = (bitField0_ & ~0x00000008);
      hurt_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000010);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_WorldBossRank_descriptor;
    }

    public pb4client.WorldBossRank getDefaultInstanceForType() {
      return pb4client.WorldBossRank.getDefaultInstance();
    }

    public pb4client.WorldBossRank build() {
      pb4client.WorldBossRank result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.WorldBossRank buildPartial() {
      pb4client.WorldBossRank result = new pb4client.WorldBossRank(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.playerId_ = playerId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.playerName_ = playerName_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.allianceName_ = allianceName_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.allianceShortName_ = allianceShortName_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.hurt_ = hurt_;
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
      if (other instanceof pb4client.WorldBossRank) {
        return mergeFrom((pb4client.WorldBossRank)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.WorldBossRank other) {
      if (other == pb4client.WorldBossRank.getDefaultInstance()) return this;
      if (other.hasPlayerId()) {
        setPlayerId(other.getPlayerId());
      }
      if (other.hasPlayerName()) {
        bitField0_ |= 0x00000002;
        playerName_ = other.playerName_;
        onChanged();
      }
      if (other.hasAllianceName()) {
        bitField0_ |= 0x00000004;
        allianceName_ = other.allianceName_;
        onChanged();
      }
      if (other.hasAllianceShortName()) {
        bitField0_ |= 0x00000008;
        allianceShortName_ = other.allianceShortName_;
        onChanged();
      }
      if (other.hasHurt()) {
        setHurt(other.getHurt());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasPlayerId()) {
        return false;
      }
      if (!hasPlayerName()) {
        return false;
      }
      if (!hasAllianceName()) {
        return false;
      }
      if (!hasAllianceShortName()) {
        return false;
      }
      if (!hasHurt()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.WorldBossRank parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.WorldBossRank) e.getUnfinishedMessage();
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
     * <code>required int64 playerId = 1;</code>
     */
    public boolean hasPlayerId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int64 playerId = 1;</code>
     */
    public long getPlayerId() {
      return playerId_;
    }
    /**
     * <code>required int64 playerId = 1;</code>
     */
    public Builder setPlayerId(long value) {
      bitField0_ |= 0x00000001;
      playerId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int64 playerId = 1;</code>
     */
    public Builder clearPlayerId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      playerId_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object playerName_ = "";
    /**
     * <code>required string playerName = 2;</code>
     */
    public boolean hasPlayerName() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required string playerName = 2;</code>
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
     * <code>required string playerName = 2;</code>
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
     * <code>required string playerName = 2;</code>
     */
    public Builder setPlayerName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      playerName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required string playerName = 2;</code>
     */
    public Builder clearPlayerName() {
      bitField0_ = (bitField0_ & ~0x00000002);
      playerName_ = getDefaultInstance().getPlayerName();
      onChanged();
      return this;
    }
    /**
     * <code>required string playerName = 2;</code>
     */
    public Builder setPlayerNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      playerName_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object allianceName_ = "";
    /**
     * <code>required string allianceName = 3;</code>
     */
    public boolean hasAllianceName() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required string allianceName = 3;</code>
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
     * <code>required string allianceName = 3;</code>
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
     * <code>required string allianceName = 3;</code>
     */
    public Builder setAllianceName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
      allianceName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required string allianceName = 3;</code>
     */
    public Builder clearAllianceName() {
      bitField0_ = (bitField0_ & ~0x00000004);
      allianceName_ = getDefaultInstance().getAllianceName();
      onChanged();
      return this;
    }
    /**
     * <code>required string allianceName = 3;</code>
     */
    public Builder setAllianceNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
      allianceName_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object allianceShortName_ = "";
    /**
     * <code>required string allianceShortName = 4;</code>
     */
    public boolean hasAllianceShortName() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>required string allianceShortName = 4;</code>
     */
    public java.lang.String getAllianceShortName() {
      java.lang.Object ref = allianceShortName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          allianceShortName_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>required string allianceShortName = 4;</code>
     */
    public com.google.protobuf.ByteString
        getAllianceShortNameBytes() {
      java.lang.Object ref = allianceShortName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        allianceShortName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>required string allianceShortName = 4;</code>
     */
    public Builder setAllianceShortName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
      allianceShortName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required string allianceShortName = 4;</code>
     */
    public Builder clearAllianceShortName() {
      bitField0_ = (bitField0_ & ~0x00000008);
      allianceShortName_ = getDefaultInstance().getAllianceShortName();
      onChanged();
      return this;
    }
    /**
     * <code>required string allianceShortName = 4;</code>
     */
    public Builder setAllianceShortNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
      allianceShortName_ = value;
      onChanged();
      return this;
    }

    private long hurt_ ;
    /**
     * <code>required int64 hurt = 5;</code>
     */
    public boolean hasHurt() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <code>required int64 hurt = 5;</code>
     */
    public long getHurt() {
      return hurt_;
    }
    /**
     * <code>required int64 hurt = 5;</code>
     */
    public Builder setHurt(long value) {
      bitField0_ |= 0x00000010;
      hurt_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int64 hurt = 5;</code>
     */
    public Builder clearHurt() {
      bitField0_ = (bitField0_ & ~0x00000010);
      hurt_ = 0L;
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


    // @@protoc_insertion_point(builder_scope:client2server.WorldBossRank)
  }

  // @@protoc_insertion_point(class_scope:client2server.WorldBossRank)
  private static final pb4client.WorldBossRank DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.WorldBossRank();
  }

  public static pb4client.WorldBossRank getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<WorldBossRank>
      PARSER = new com.google.protobuf.AbstractParser<WorldBossRank>() {
    public WorldBossRank parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new WorldBossRank(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<WorldBossRank> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<WorldBossRank> getParserForType() {
    return PARSER;
  }

  public pb4client.WorldBossRank getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

