// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * 详细的联盟玩家在线信息
 * </pre>
 *
 * Protobuf type {@code client2server.MemberPlayerInfo}
 */
public  final class MemberPlayerInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.MemberPlayerInfo)
    MemberPlayerInfoOrBuilder {
  // Use MemberPlayerInfo.newBuilder() to construct.
  private MemberPlayerInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private MemberPlayerInfo() {
    playerId_ = 0L;
    playerName_ = "";
    positions_ = java.util.Collections.emptyList();
    isOnline_ = 0;
    protoId_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private MemberPlayerInfo(
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
          case 32: {
            if (!((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
              positions_ = new java.util.ArrayList<java.lang.Integer>();
              mutable_bitField0_ |= 0x00000004;
            }
            positions_.add(input.readInt32());
            break;
          }
          case 34: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000004) == 0x00000004) && input.getBytesUntilLimit() > 0) {
              positions_ = new java.util.ArrayList<java.lang.Integer>();
              mutable_bitField0_ |= 0x00000004;
            }
            while (input.getBytesUntilLimit() > 0) {
              positions_.add(input.readInt32());
            }
            input.popLimit(limit);
            break;
          }
          case 40: {
            bitField0_ |= 0x00000004;
            isOnline_ = input.readInt32();
            break;
          }
          case 48: {
            bitField0_ |= 0x00000008;
            protoId_ = input.readInt32();
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
        positions_ = java.util.Collections.unmodifiableList(positions_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_MemberPlayerInfo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_MemberPlayerInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.MemberPlayerInfo.class, pb4client.MemberPlayerInfo.Builder.class);
  }

  private int bitField0_;
  public static final int PLAYERID_FIELD_NUMBER = 1;
  private long playerId_;
  /**
   * <pre>
   *玩家id
   * </pre>
   *
   * <code>required int64 playerId = 1;</code>
   */
  public boolean hasPlayerId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *玩家id
   * </pre>
   *
   * <code>required int64 playerId = 1;</code>
   */
  public long getPlayerId() {
    return playerId_;
  }

  public static final int PLAYERNAME_FIELD_NUMBER = 2;
  private volatile java.lang.Object playerName_;
  /**
   * <pre>
   *名字
   * </pre>
   *
   * <code>required string playerName = 2;</code>
   */
  public boolean hasPlayerName() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *名字
   * </pre>
   *
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
   * <pre>
   *名字
   * </pre>
   *
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

  public static final int POSITIONS_FIELD_NUMBER = 4;
  private java.util.List<java.lang.Integer> positions_;
  /**
   * <pre>
   *玩家联盟职位：1-盟主 2-副盟主 3-指挥官 4-外交官 5-团长 6-普通成员 7-白虎成员 8-青龙成员 9-玄武成员 10-朱雀成员 11-白虎团长 12-青龙团长 13-玄武团长 14-朱雀团长
   * </pre>
   *
   * <code>repeated int32 positions = 4;</code>
   */
  public java.util.List<java.lang.Integer>
      getPositionsList() {
    return positions_;
  }
  /**
   * <pre>
   *玩家联盟职位：1-盟主 2-副盟主 3-指挥官 4-外交官 5-团长 6-普通成员 7-白虎成员 8-青龙成员 9-玄武成员 10-朱雀成员 11-白虎团长 12-青龙团长 13-玄武团长 14-朱雀团长
   * </pre>
   *
   * <code>repeated int32 positions = 4;</code>
   */
  public int getPositionsCount() {
    return positions_.size();
  }
  /**
   * <pre>
   *玩家联盟职位：1-盟主 2-副盟主 3-指挥官 4-外交官 5-团长 6-普通成员 7-白虎成员 8-青龙成员 9-玄武成员 10-朱雀成员 11-白虎团长 12-青龙团长 13-玄武团长 14-朱雀团长
   * </pre>
   *
   * <code>repeated int32 positions = 4;</code>
   */
  public int getPositions(int index) {
    return positions_.get(index);
  }

  public static final int ISONLINE_FIELD_NUMBER = 5;
  private int isOnline_;
  /**
   * <pre>
   *玩家是否在线
   * </pre>
   *
   * <code>required int32 isOnline = 5;</code>
   */
  public boolean hasIsOnline() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   *玩家是否在线
   * </pre>
   *
   * <code>required int32 isOnline = 5;</code>
   */
  public int getIsOnline() {
    return isOnline_;
  }

  public static final int PROTOID_FIELD_NUMBER = 6;
  private int protoId_;
  /**
   * <pre>
   *玩家头像id
   * </pre>
   *
   * <code>required int32 protoId = 6;</code>
   */
  public boolean hasProtoId() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   *玩家头像id
   * </pre>
   *
   * <code>required int32 protoId = 6;</code>
   */
  public int getProtoId() {
    return protoId_;
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
    if (!hasIsOnline()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasProtoId()) {
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
    for (int i = 0; i < positions_.size(); i++) {
      output.writeInt32(4, positions_.get(i));
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(5, isOnline_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt32(6, protoId_);
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
    {
      int dataSize = 0;
      for (int i = 0; i < positions_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeInt32SizeNoTag(positions_.get(i));
      }
      size += dataSize;
      size += 1 * getPositionsList().size();
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, isOnline_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, protoId_);
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
    if (!(obj instanceof pb4client.MemberPlayerInfo)) {
      return super.equals(obj);
    }
    pb4client.MemberPlayerInfo other = (pb4client.MemberPlayerInfo) obj;

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
    result = result && getPositionsList()
        .equals(other.getPositionsList());
    result = result && (hasIsOnline() == other.hasIsOnline());
    if (hasIsOnline()) {
      result = result && (getIsOnline()
          == other.getIsOnline());
    }
    result = result && (hasProtoId() == other.hasProtoId());
    if (hasProtoId()) {
      result = result && (getProtoId()
          == other.getProtoId());
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
    if (getPositionsCount() > 0) {
      hash = (37 * hash) + POSITIONS_FIELD_NUMBER;
      hash = (53 * hash) + getPositionsList().hashCode();
    }
    if (hasIsOnline()) {
      hash = (37 * hash) + ISONLINE_FIELD_NUMBER;
      hash = (53 * hash) + getIsOnline();
    }
    if (hasProtoId()) {
      hash = (37 * hash) + PROTOID_FIELD_NUMBER;
      hash = (53 * hash) + getProtoId();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.MemberPlayerInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.MemberPlayerInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.MemberPlayerInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.MemberPlayerInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.MemberPlayerInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.MemberPlayerInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.MemberPlayerInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.MemberPlayerInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.MemberPlayerInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.MemberPlayerInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.MemberPlayerInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.MemberPlayerInfo parseFrom(
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
  public static Builder newBuilder(pb4client.MemberPlayerInfo prototype) {
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
   * 详细的联盟玩家在线信息
   * </pre>
   *
   * Protobuf type {@code client2server.MemberPlayerInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.MemberPlayerInfo)
      pb4client.MemberPlayerInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_MemberPlayerInfo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_MemberPlayerInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.MemberPlayerInfo.class, pb4client.MemberPlayerInfo.Builder.class);
    }

    // Construct using pb4client.MemberPlayerInfo.newBuilder()
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
      positions_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000004);
      isOnline_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      protoId_ = 0;
      bitField0_ = (bitField0_ & ~0x00000010);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_MemberPlayerInfo_descriptor;
    }

    public pb4client.MemberPlayerInfo getDefaultInstanceForType() {
      return pb4client.MemberPlayerInfo.getDefaultInstance();
    }

    public pb4client.MemberPlayerInfo build() {
      pb4client.MemberPlayerInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.MemberPlayerInfo buildPartial() {
      pb4client.MemberPlayerInfo result = new pb4client.MemberPlayerInfo(this);
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
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        positions_ = java.util.Collections.unmodifiableList(positions_);
        bitField0_ = (bitField0_ & ~0x00000004);
      }
      result.positions_ = positions_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000004;
      }
      result.isOnline_ = isOnline_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000008;
      }
      result.protoId_ = protoId_;
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
      if (other instanceof pb4client.MemberPlayerInfo) {
        return mergeFrom((pb4client.MemberPlayerInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.MemberPlayerInfo other) {
      if (other == pb4client.MemberPlayerInfo.getDefaultInstance()) return this;
      if (other.hasPlayerId()) {
        setPlayerId(other.getPlayerId());
      }
      if (other.hasPlayerName()) {
        bitField0_ |= 0x00000002;
        playerName_ = other.playerName_;
        onChanged();
      }
      if (!other.positions_.isEmpty()) {
        if (positions_.isEmpty()) {
          positions_ = other.positions_;
          bitField0_ = (bitField0_ & ~0x00000004);
        } else {
          ensurePositionsIsMutable();
          positions_.addAll(other.positions_);
        }
        onChanged();
      }
      if (other.hasIsOnline()) {
        setIsOnline(other.getIsOnline());
      }
      if (other.hasProtoId()) {
        setProtoId(other.getProtoId());
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
      if (!hasIsOnline()) {
        return false;
      }
      if (!hasProtoId()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.MemberPlayerInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.MemberPlayerInfo) e.getUnfinishedMessage();
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
     * <pre>
     *玩家id
     * </pre>
     *
     * <code>required int64 playerId = 1;</code>
     */
    public boolean hasPlayerId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *玩家id
     * </pre>
     *
     * <code>required int64 playerId = 1;</code>
     */
    public long getPlayerId() {
      return playerId_;
    }
    /**
     * <pre>
     *玩家id
     * </pre>
     *
     * <code>required int64 playerId = 1;</code>
     */
    public Builder setPlayerId(long value) {
      bitField0_ |= 0x00000001;
      playerId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *玩家id
     * </pre>
     *
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
     * <pre>
     *名字
     * </pre>
     *
     * <code>required string playerName = 2;</code>
     */
    public boolean hasPlayerName() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *名字
     * </pre>
     *
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
     * <pre>
     *名字
     * </pre>
     *
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
     * <pre>
     *名字
     * </pre>
     *
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
     * <pre>
     *名字
     * </pre>
     *
     * <code>required string playerName = 2;</code>
     */
    public Builder clearPlayerName() {
      bitField0_ = (bitField0_ & ~0x00000002);
      playerName_ = getDefaultInstance().getPlayerName();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *名字
     * </pre>
     *
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

    private java.util.List<java.lang.Integer> positions_ = java.util.Collections.emptyList();
    private void ensurePositionsIsMutable() {
      if (!((bitField0_ & 0x00000004) == 0x00000004)) {
        positions_ = new java.util.ArrayList<java.lang.Integer>(positions_);
        bitField0_ |= 0x00000004;
       }
    }
    /**
     * <pre>
     *玩家联盟职位：1-盟主 2-副盟主 3-指挥官 4-外交官 5-团长 6-普通成员 7-白虎成员 8-青龙成员 9-玄武成员 10-朱雀成员 11-白虎团长 12-青龙团长 13-玄武团长 14-朱雀团长
     * </pre>
     *
     * <code>repeated int32 positions = 4;</code>
     */
    public java.util.List<java.lang.Integer>
        getPositionsList() {
      return java.util.Collections.unmodifiableList(positions_);
    }
    /**
     * <pre>
     *玩家联盟职位：1-盟主 2-副盟主 3-指挥官 4-外交官 5-团长 6-普通成员 7-白虎成员 8-青龙成员 9-玄武成员 10-朱雀成员 11-白虎团长 12-青龙团长 13-玄武团长 14-朱雀团长
     * </pre>
     *
     * <code>repeated int32 positions = 4;</code>
     */
    public int getPositionsCount() {
      return positions_.size();
    }
    /**
     * <pre>
     *玩家联盟职位：1-盟主 2-副盟主 3-指挥官 4-外交官 5-团长 6-普通成员 7-白虎成员 8-青龙成员 9-玄武成员 10-朱雀成员 11-白虎团长 12-青龙团长 13-玄武团长 14-朱雀团长
     * </pre>
     *
     * <code>repeated int32 positions = 4;</code>
     */
    public int getPositions(int index) {
      return positions_.get(index);
    }
    /**
     * <pre>
     *玩家联盟职位：1-盟主 2-副盟主 3-指挥官 4-外交官 5-团长 6-普通成员 7-白虎成员 8-青龙成员 9-玄武成员 10-朱雀成员 11-白虎团长 12-青龙团长 13-玄武团长 14-朱雀团长
     * </pre>
     *
     * <code>repeated int32 positions = 4;</code>
     */
    public Builder setPositions(
        int index, int value) {
      ensurePositionsIsMutable();
      positions_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *玩家联盟职位：1-盟主 2-副盟主 3-指挥官 4-外交官 5-团长 6-普通成员 7-白虎成员 8-青龙成员 9-玄武成员 10-朱雀成员 11-白虎团长 12-青龙团长 13-玄武团长 14-朱雀团长
     * </pre>
     *
     * <code>repeated int32 positions = 4;</code>
     */
    public Builder addPositions(int value) {
      ensurePositionsIsMutable();
      positions_.add(value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *玩家联盟职位：1-盟主 2-副盟主 3-指挥官 4-外交官 5-团长 6-普通成员 7-白虎成员 8-青龙成员 9-玄武成员 10-朱雀成员 11-白虎团长 12-青龙团长 13-玄武团长 14-朱雀团长
     * </pre>
     *
     * <code>repeated int32 positions = 4;</code>
     */
    public Builder addAllPositions(
        java.lang.Iterable<? extends java.lang.Integer> values) {
      ensurePositionsIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, positions_);
      onChanged();
      return this;
    }
    /**
     * <pre>
     *玩家联盟职位：1-盟主 2-副盟主 3-指挥官 4-外交官 5-团长 6-普通成员 7-白虎成员 8-青龙成员 9-玄武成员 10-朱雀成员 11-白虎团长 12-青龙团长 13-玄武团长 14-朱雀团长
     * </pre>
     *
     * <code>repeated int32 positions = 4;</code>
     */
    public Builder clearPositions() {
      positions_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000004);
      onChanged();
      return this;
    }

    private int isOnline_ ;
    /**
     * <pre>
     *玩家是否在线
     * </pre>
     *
     * <code>required int32 isOnline = 5;</code>
     */
    public boolean hasIsOnline() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     *玩家是否在线
     * </pre>
     *
     * <code>required int32 isOnline = 5;</code>
     */
    public int getIsOnline() {
      return isOnline_;
    }
    /**
     * <pre>
     *玩家是否在线
     * </pre>
     *
     * <code>required int32 isOnline = 5;</code>
     */
    public Builder setIsOnline(int value) {
      bitField0_ |= 0x00000008;
      isOnline_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *玩家是否在线
     * </pre>
     *
     * <code>required int32 isOnline = 5;</code>
     */
    public Builder clearIsOnline() {
      bitField0_ = (bitField0_ & ~0x00000008);
      isOnline_ = 0;
      onChanged();
      return this;
    }

    private int protoId_ ;
    /**
     * <pre>
     *玩家头像id
     * </pre>
     *
     * <code>required int32 protoId = 6;</code>
     */
    public boolean hasProtoId() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <pre>
     *玩家头像id
     * </pre>
     *
     * <code>required int32 protoId = 6;</code>
     */
    public int getProtoId() {
      return protoId_;
    }
    /**
     * <pre>
     *玩家头像id
     * </pre>
     *
     * <code>required int32 protoId = 6;</code>
     */
    public Builder setProtoId(int value) {
      bitField0_ |= 0x00000010;
      protoId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *玩家头像id
     * </pre>
     *
     * <code>required int32 protoId = 6;</code>
     */
    public Builder clearProtoId() {
      bitField0_ = (bitField0_ & ~0x00000010);
      protoId_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.MemberPlayerInfo)
  }

  // @@protoc_insertion_point(class_scope:client2server.MemberPlayerInfo)
  private static final pb4client.MemberPlayerInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.MemberPlayerInfo();
  }

  public static pb4client.MemberPlayerInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<MemberPlayerInfo>
      PARSER = new com.google.protobuf.AbstractParser<MemberPlayerInfo>() {
    public MemberPlayerInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new MemberPlayerInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<MemberPlayerInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<MemberPlayerInfo> getParserForType() {
    return PARSER;
  }

  public pb4client.MemberPlayerInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

