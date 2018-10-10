// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.InviteJoinChatAskReq}
 */
public  final class InviteJoinChatAskReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.InviteJoinChatAskReq)
    InviteJoinChatAskReqOrBuilder {
  // Use InviteJoinChatAskReq.newBuilder() to construct.
  private InviteJoinChatAskReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private InviteJoinChatAskReq() {
    roomId_ = 0L;
    playerIdAdd_ = 0L;
    playerName_ = "";
    protoId_ = 0;
    vipLv_ = 0;
    areaNo_ = 0;
    allianceShortName_ = "";
    fightValue_ = 0L;
    castleLv_ = 0;
    playerShortName_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private InviteJoinChatAskReq(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 8: {

            roomId_ = input.readInt64();
            break;
          }
          case 16: {

            playerIdAdd_ = input.readInt64();
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            playerName_ = s;
            break;
          }
          case 32: {

            protoId_ = input.readInt32();
            break;
          }
          case 40: {

            vipLv_ = input.readInt32();
            break;
          }
          case 48: {

            areaNo_ = input.readInt32();
            break;
          }
          case 58: {
            java.lang.String s = input.readStringRequireUtf8();

            allianceShortName_ = s;
            break;
          }
          case 64: {

            fightValue_ = input.readInt64();
            break;
          }
          case 72: {

            castleLv_ = input.readInt32();
            break;
          }
          case 82: {
            java.lang.String s = input.readStringRequireUtf8();

            playerShortName_ = s;
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
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalPkt.internal_static_pb4server_InviteJoinChatAskReq_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalPkt.internal_static_pb4server_InviteJoinChatAskReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.InviteJoinChatAskReq.class, pb4server.InviteJoinChatAskReq.Builder.class);
  }

  public static final int ROOMID_FIELD_NUMBER = 1;
  private long roomId_;
  /**
   * <code>int64 roomId = 1;</code>
   */
  public long getRoomId() {
    return roomId_;
  }

  public static final int PLAYERIDADD_FIELD_NUMBER = 2;
  private long playerIdAdd_;
  /**
   * <code>int64 playerIdAdd = 2;</code>
   */
  public long getPlayerIdAdd() {
    return playerIdAdd_;
  }

  public static final int PLAYERNAME_FIELD_NUMBER = 3;
  private volatile java.lang.Object playerName_;
  /**
   * <pre>
   * 玩家名字
   * </pre>
   *
   * <code>string playerName = 3;</code>
   */
  public java.lang.String getPlayerName() {
    java.lang.Object ref = playerName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      playerName_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * 玩家名字
   * </pre>
   *
   * <code>string playerName = 3;</code>
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

  public static final int PROTOID_FIELD_NUMBER = 4;
  private int protoId_;
  /**
   * <pre>
   * 头像ID
   * </pre>
   *
   * <code>int32 protoId = 4;</code>
   */
  public int getProtoId() {
    return protoId_;
  }

  public static final int VIPLV_FIELD_NUMBER = 5;
  private int vipLv_;
  /**
   * <code>int32 vipLv = 5;</code>
   */
  public int getVipLv() {
    return vipLv_;
  }

  public static final int AREANO_FIELD_NUMBER = 6;
  private int areaNo_;
  /**
   * <code>int32 areaNo = 6;</code>
   */
  public int getAreaNo() {
    return areaNo_;
  }

  public static final int ALLIANCESHORTNAME_FIELD_NUMBER = 7;
  private volatile java.lang.Object allianceShortName_;
  /**
   * <code>string allianceShortName = 7;</code>
   */
  public java.lang.String getAllianceShortName() {
    java.lang.Object ref = allianceShortName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      allianceShortName_ = s;
      return s;
    }
  }
  /**
   * <code>string allianceShortName = 7;</code>
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

  public static final int FIGHTVALUE_FIELD_NUMBER = 8;
  private long fightValue_;
  /**
   * <code>int64 fightValue = 8;</code>
   */
  public long getFightValue() {
    return fightValue_;
  }

  public static final int CASTLELV_FIELD_NUMBER = 9;
  private int castleLv_;
  /**
   * <code>int32 castleLv = 9;</code>
   */
  public int getCastleLv() {
    return castleLv_;
  }

  public static final int PLAYERSHORTNAME_FIELD_NUMBER = 10;
  private volatile java.lang.Object playerShortName_;
  /**
   * <code>string playerShortName = 10;</code>
   */
  public java.lang.String getPlayerShortName() {
    java.lang.Object ref = playerShortName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      playerShortName_ = s;
      return s;
    }
  }
  /**
   * <code>string playerShortName = 10;</code>
   */
  public com.google.protobuf.ByteString
      getPlayerShortNameBytes() {
    java.lang.Object ref = playerShortName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      playerShortName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (roomId_ != 0L) {
      output.writeInt64(1, roomId_);
    }
    if (playerIdAdd_ != 0L) {
      output.writeInt64(2, playerIdAdd_);
    }
    if (!getPlayerNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, playerName_);
    }
    if (protoId_ != 0) {
      output.writeInt32(4, protoId_);
    }
    if (vipLv_ != 0) {
      output.writeInt32(5, vipLv_);
    }
    if (areaNo_ != 0) {
      output.writeInt32(6, areaNo_);
    }
    if (!getAllianceShortNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 7, allianceShortName_);
    }
    if (fightValue_ != 0L) {
      output.writeInt64(8, fightValue_);
    }
    if (castleLv_ != 0) {
      output.writeInt32(9, castleLv_);
    }
    if (!getPlayerShortNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 10, playerShortName_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (roomId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, roomId_);
    }
    if (playerIdAdd_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, playerIdAdd_);
    }
    if (!getPlayerNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, playerName_);
    }
    if (protoId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, protoId_);
    }
    if (vipLv_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, vipLv_);
    }
    if (areaNo_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, areaNo_);
    }
    if (!getAllianceShortNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(7, allianceShortName_);
    }
    if (fightValue_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(8, fightValue_);
    }
    if (castleLv_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(9, castleLv_);
    }
    if (!getPlayerShortNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(10, playerShortName_);
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof pb4server.InviteJoinChatAskReq)) {
      return super.equals(obj);
    }
    pb4server.InviteJoinChatAskReq other = (pb4server.InviteJoinChatAskReq) obj;

    boolean result = true;
    result = result && (getRoomId()
        == other.getRoomId());
    result = result && (getPlayerIdAdd()
        == other.getPlayerIdAdd());
    result = result && getPlayerName()
        .equals(other.getPlayerName());
    result = result && (getProtoId()
        == other.getProtoId());
    result = result && (getVipLv()
        == other.getVipLv());
    result = result && (getAreaNo()
        == other.getAreaNo());
    result = result && getAllianceShortName()
        .equals(other.getAllianceShortName());
    result = result && (getFightValue()
        == other.getFightValue());
    result = result && (getCastleLv()
        == other.getCastleLv());
    result = result && getPlayerShortName()
        .equals(other.getPlayerShortName());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ROOMID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getRoomId());
    hash = (37 * hash) + PLAYERIDADD_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getPlayerIdAdd());
    hash = (37 * hash) + PLAYERNAME_FIELD_NUMBER;
    hash = (53 * hash) + getPlayerName().hashCode();
    hash = (37 * hash) + PROTOID_FIELD_NUMBER;
    hash = (53 * hash) + getProtoId();
    hash = (37 * hash) + VIPLV_FIELD_NUMBER;
    hash = (53 * hash) + getVipLv();
    hash = (37 * hash) + AREANO_FIELD_NUMBER;
    hash = (53 * hash) + getAreaNo();
    hash = (37 * hash) + ALLIANCESHORTNAME_FIELD_NUMBER;
    hash = (53 * hash) + getAllianceShortName().hashCode();
    hash = (37 * hash) + FIGHTVALUE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getFightValue());
    hash = (37 * hash) + CASTLELV_FIELD_NUMBER;
    hash = (53 * hash) + getCastleLv();
    hash = (37 * hash) + PLAYERSHORTNAME_FIELD_NUMBER;
    hash = (53 * hash) + getPlayerShortName().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.InviteJoinChatAskReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.InviteJoinChatAskReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.InviteJoinChatAskReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.InviteJoinChatAskReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.InviteJoinChatAskReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.InviteJoinChatAskReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.InviteJoinChatAskReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.InviteJoinChatAskReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.InviteJoinChatAskReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.InviteJoinChatAskReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.InviteJoinChatAskReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.InviteJoinChatAskReq parseFrom(
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
  public static Builder newBuilder(pb4server.InviteJoinChatAskReq prototype) {
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
   * Protobuf type {@code pb4server.InviteJoinChatAskReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.InviteJoinChatAskReq)
      pb4server.InviteJoinChatAskReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalPkt.internal_static_pb4server_InviteJoinChatAskReq_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalPkt.internal_static_pb4server_InviteJoinChatAskReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.InviteJoinChatAskReq.class, pb4server.InviteJoinChatAskReq.Builder.class);
    }

    // Construct using pb4server.InviteJoinChatAskReq.newBuilder()
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
      roomId_ = 0L;

      playerIdAdd_ = 0L;

      playerName_ = "";

      protoId_ = 0;

      vipLv_ = 0;

      areaNo_ = 0;

      allianceShortName_ = "";

      fightValue_ = 0L;

      castleLv_ = 0;

      playerShortName_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalPkt.internal_static_pb4server_InviteJoinChatAskReq_descriptor;
    }

    public pb4server.InviteJoinChatAskReq getDefaultInstanceForType() {
      return pb4server.InviteJoinChatAskReq.getDefaultInstance();
    }

    public pb4server.InviteJoinChatAskReq build() {
      pb4server.InviteJoinChatAskReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.InviteJoinChatAskReq buildPartial() {
      pb4server.InviteJoinChatAskReq result = new pb4server.InviteJoinChatAskReq(this);
      result.roomId_ = roomId_;
      result.playerIdAdd_ = playerIdAdd_;
      result.playerName_ = playerName_;
      result.protoId_ = protoId_;
      result.vipLv_ = vipLv_;
      result.areaNo_ = areaNo_;
      result.allianceShortName_ = allianceShortName_;
      result.fightValue_ = fightValue_;
      result.castleLv_ = castleLv_;
      result.playerShortName_ = playerShortName_;
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
      if (other instanceof pb4server.InviteJoinChatAskReq) {
        return mergeFrom((pb4server.InviteJoinChatAskReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.InviteJoinChatAskReq other) {
      if (other == pb4server.InviteJoinChatAskReq.getDefaultInstance()) return this;
      if (other.getRoomId() != 0L) {
        setRoomId(other.getRoomId());
      }
      if (other.getPlayerIdAdd() != 0L) {
        setPlayerIdAdd(other.getPlayerIdAdd());
      }
      if (!other.getPlayerName().isEmpty()) {
        playerName_ = other.playerName_;
        onChanged();
      }
      if (other.getProtoId() != 0) {
        setProtoId(other.getProtoId());
      }
      if (other.getVipLv() != 0) {
        setVipLv(other.getVipLv());
      }
      if (other.getAreaNo() != 0) {
        setAreaNo(other.getAreaNo());
      }
      if (!other.getAllianceShortName().isEmpty()) {
        allianceShortName_ = other.allianceShortName_;
        onChanged();
      }
      if (other.getFightValue() != 0L) {
        setFightValue(other.getFightValue());
      }
      if (other.getCastleLv() != 0) {
        setCastleLv(other.getCastleLv());
      }
      if (!other.getPlayerShortName().isEmpty()) {
        playerShortName_ = other.playerShortName_;
        onChanged();
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4server.InviteJoinChatAskReq parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.InviteJoinChatAskReq) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long roomId_ ;
    /**
     * <code>int64 roomId = 1;</code>
     */
    public long getRoomId() {
      return roomId_;
    }
    /**
     * <code>int64 roomId = 1;</code>
     */
    public Builder setRoomId(long value) {
      
      roomId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 roomId = 1;</code>
     */
    public Builder clearRoomId() {
      
      roomId_ = 0L;
      onChanged();
      return this;
    }

    private long playerIdAdd_ ;
    /**
     * <code>int64 playerIdAdd = 2;</code>
     */
    public long getPlayerIdAdd() {
      return playerIdAdd_;
    }
    /**
     * <code>int64 playerIdAdd = 2;</code>
     */
    public Builder setPlayerIdAdd(long value) {
      
      playerIdAdd_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 playerIdAdd = 2;</code>
     */
    public Builder clearPlayerIdAdd() {
      
      playerIdAdd_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object playerName_ = "";
    /**
     * <pre>
     * 玩家名字
     * </pre>
     *
     * <code>string playerName = 3;</code>
     */
    public java.lang.String getPlayerName() {
      java.lang.Object ref = playerName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        playerName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * 玩家名字
     * </pre>
     *
     * <code>string playerName = 3;</code>
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
     * 玩家名字
     * </pre>
     *
     * <code>string playerName = 3;</code>
     */
    public Builder setPlayerName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      playerName_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 玩家名字
     * </pre>
     *
     * <code>string playerName = 3;</code>
     */
    public Builder clearPlayerName() {
      
      playerName_ = getDefaultInstance().getPlayerName();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 玩家名字
     * </pre>
     *
     * <code>string playerName = 3;</code>
     */
    public Builder setPlayerNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      playerName_ = value;
      onChanged();
      return this;
    }

    private int protoId_ ;
    /**
     * <pre>
     * 头像ID
     * </pre>
     *
     * <code>int32 protoId = 4;</code>
     */
    public int getProtoId() {
      return protoId_;
    }
    /**
     * <pre>
     * 头像ID
     * </pre>
     *
     * <code>int32 protoId = 4;</code>
     */
    public Builder setProtoId(int value) {
      
      protoId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 头像ID
     * </pre>
     *
     * <code>int32 protoId = 4;</code>
     */
    public Builder clearProtoId() {
      
      protoId_ = 0;
      onChanged();
      return this;
    }

    private int vipLv_ ;
    /**
     * <code>int32 vipLv = 5;</code>
     */
    public int getVipLv() {
      return vipLv_;
    }
    /**
     * <code>int32 vipLv = 5;</code>
     */
    public Builder setVipLv(int value) {
      
      vipLv_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 vipLv = 5;</code>
     */
    public Builder clearVipLv() {
      
      vipLv_ = 0;
      onChanged();
      return this;
    }

    private int areaNo_ ;
    /**
     * <code>int32 areaNo = 6;</code>
     */
    public int getAreaNo() {
      return areaNo_;
    }
    /**
     * <code>int32 areaNo = 6;</code>
     */
    public Builder setAreaNo(int value) {
      
      areaNo_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 areaNo = 6;</code>
     */
    public Builder clearAreaNo() {
      
      areaNo_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object allianceShortName_ = "";
    /**
     * <code>string allianceShortName = 7;</code>
     */
    public java.lang.String getAllianceShortName() {
      java.lang.Object ref = allianceShortName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        allianceShortName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string allianceShortName = 7;</code>
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
     * <code>string allianceShortName = 7;</code>
     */
    public Builder setAllianceShortName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      allianceShortName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string allianceShortName = 7;</code>
     */
    public Builder clearAllianceShortName() {
      
      allianceShortName_ = getDefaultInstance().getAllianceShortName();
      onChanged();
      return this;
    }
    /**
     * <code>string allianceShortName = 7;</code>
     */
    public Builder setAllianceShortNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      allianceShortName_ = value;
      onChanged();
      return this;
    }

    private long fightValue_ ;
    /**
     * <code>int64 fightValue = 8;</code>
     */
    public long getFightValue() {
      return fightValue_;
    }
    /**
     * <code>int64 fightValue = 8;</code>
     */
    public Builder setFightValue(long value) {
      
      fightValue_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 fightValue = 8;</code>
     */
    public Builder clearFightValue() {
      
      fightValue_ = 0L;
      onChanged();
      return this;
    }

    private int castleLv_ ;
    /**
     * <code>int32 castleLv = 9;</code>
     */
    public int getCastleLv() {
      return castleLv_;
    }
    /**
     * <code>int32 castleLv = 9;</code>
     */
    public Builder setCastleLv(int value) {
      
      castleLv_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 castleLv = 9;</code>
     */
    public Builder clearCastleLv() {
      
      castleLv_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object playerShortName_ = "";
    /**
     * <code>string playerShortName = 10;</code>
     */
    public java.lang.String getPlayerShortName() {
      java.lang.Object ref = playerShortName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        playerShortName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string playerShortName = 10;</code>
     */
    public com.google.protobuf.ByteString
        getPlayerShortNameBytes() {
      java.lang.Object ref = playerShortName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        playerShortName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string playerShortName = 10;</code>
     */
    public Builder setPlayerShortName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      playerShortName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string playerShortName = 10;</code>
     */
    public Builder clearPlayerShortName() {
      
      playerShortName_ = getDefaultInstance().getPlayerShortName();
      onChanged();
      return this;
    }
    /**
     * <code>string playerShortName = 10;</code>
     */
    public Builder setPlayerShortNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      playerShortName_ = value;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:pb4server.InviteJoinChatAskReq)
  }

  // @@protoc_insertion_point(class_scope:pb4server.InviteJoinChatAskReq)
  private static final pb4server.InviteJoinChatAskReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.InviteJoinChatAskReq();
  }

  public static pb4server.InviteJoinChatAskReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<InviteJoinChatAskReq>
      PARSER = new com.google.protobuf.AbstractParser<InviteJoinChatAskReq>() {
    public InviteJoinChatAskReq parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new InviteJoinChatAskReq(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<InviteJoinChatAskReq> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<InviteJoinChatAskReq> getParserForType() {
    return PARSER;
  }

  public pb4server.InviteJoinChatAskReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

