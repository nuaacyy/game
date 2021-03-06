// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2world.proto

package pb4server;

/**
 * <pre>
 * 帮派职位变化通知给所有帮众
 * </pre>
 *
 * Protobuf type {@code pb4server.PosChangeNoticAllAllianceTell}
 */
public  final class PosChangeNoticAllAllianceTell extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.PosChangeNoticAllAllianceTell)
    PosChangeNoticAllAllianceTellOrBuilder {
  // Use PosChangeNoticAllAllianceTell.newBuilder() to construct.
  private PosChangeNoticAllAllianceTell(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private PosChangeNoticAllAllianceTell() {
    allianceId_ = 0L;
    pos_ = 0;
    playerName_ = "";
    getPosPlayerName_ = "";
    changeType_ = 0;
    positions_ = java.util.Collections.emptyList();
    setPlayerId_ = 0L;
    isOnline_ = 0;
    photoProtoId_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private PosChangeNoticAllAllianceTell(
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

            allianceId_ = input.readInt64();
            break;
          }
          case 16: {

            pos_ = input.readInt32();
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            playerName_ = s;
            break;
          }
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            getPosPlayerName_ = s;
            break;
          }
          case 40: {

            changeType_ = input.readInt32();
            break;
          }
          case 48: {
            if (!((mutable_bitField0_ & 0x00000020) == 0x00000020)) {
              positions_ = new java.util.ArrayList<java.lang.Integer>();
              mutable_bitField0_ |= 0x00000020;
            }
            positions_.add(input.readInt32());
            break;
          }
          case 50: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000020) == 0x00000020) && input.getBytesUntilLimit() > 0) {
              positions_ = new java.util.ArrayList<java.lang.Integer>();
              mutable_bitField0_ |= 0x00000020;
            }
            while (input.getBytesUntilLimit() > 0) {
              positions_.add(input.readInt32());
            }
            input.popLimit(limit);
            break;
          }
          case 56: {

            setPlayerId_ = input.readInt64();
            break;
          }
          case 64: {

            isOnline_ = input.readInt32();
            break;
          }
          case 72: {

            photoProtoId_ = input.readInt32();
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
      if (((mutable_bitField0_ & 0x00000020) == 0x00000020)) {
        positions_ = java.util.Collections.unmodifiableList(positions_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalWkt.internal_static_pb4server_PosChangeNoticAllAllianceTell_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalWkt.internal_static_pb4server_PosChangeNoticAllAllianceTell_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.PosChangeNoticAllAllianceTell.class, pb4server.PosChangeNoticAllAllianceTell.Builder.class);
  }

  private int bitField0_;
  public static final int ALLIANCEID_FIELD_NUMBER = 1;
  private long allianceId_;
  /**
   * <code>int64 allianceId = 1;</code>
   */
  public long getAllianceId() {
    return allianceId_;
  }

  public static final int POS_FIELD_NUMBER = 2;
  private int pos_;
  /**
   * <code>int32 pos = 2;</code>
   */
  public int getPos() {
    return pos_;
  }

  public static final int PLAYERNAME_FIELD_NUMBER = 3;
  private volatile java.lang.Object playerName_;
  /**
   * <pre>
   * 给予职位的玩家名
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
   * 给予职位的玩家名
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

  public static final int GETPOSPLAYERNAME_FIELD_NUMBER = 4;
  private volatile java.lang.Object getPosPlayerName_;
  /**
   * <pre>
   * 被任命的玩家名
   * </pre>
   *
   * <code>string getPosPlayerName = 4;</code>
   */
  public java.lang.String getGetPosPlayerName() {
    java.lang.Object ref = getPosPlayerName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      getPosPlayerName_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * 被任命的玩家名
   * </pre>
   *
   * <code>string getPosPlayerName = 4;</code>
   */
  public com.google.protobuf.ByteString
      getGetPosPlayerNameBytes() {
    java.lang.Object ref = getPosPlayerName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      getPosPlayerName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int CHANGETYPE_FIELD_NUMBER = 5;
  private int changeType_;
  /**
   * <pre>
   * 1-新增  2-减少
   * </pre>
   *
   * <code>int32 changeType = 5;</code>
   */
  public int getChangeType() {
    return changeType_;
  }

  public static final int POSITIONS_FIELD_NUMBER = 6;
  private java.util.List<java.lang.Integer> positions_;
  /**
   * <pre>
   * 被任命后的职位
   * </pre>
   *
   * <code>repeated int32 positions = 6;</code>
   */
  public java.util.List<java.lang.Integer>
      getPositionsList() {
    return positions_;
  }
  /**
   * <pre>
   * 被任命后的职位
   * </pre>
   *
   * <code>repeated int32 positions = 6;</code>
   */
  public int getPositionsCount() {
    return positions_.size();
  }
  /**
   * <pre>
   * 被任命后的职位
   * </pre>
   *
   * <code>repeated int32 positions = 6;</code>
   */
  public int getPositions(int index) {
    return positions_.get(index);
  }
  private int positionsMemoizedSerializedSize = -1;

  public static final int SETPLAYERID_FIELD_NUMBER = 7;
  private long setPlayerId_;
  /**
   * <pre>
   * 被任命的玩家ID
   * </pre>
   *
   * <code>int64 setPlayerId = 7;</code>
   */
  public long getSetPlayerId() {
    return setPlayerId_;
  }

  public static final int ISONLINE_FIELD_NUMBER = 8;
  private int isOnline_;
  /**
   * <pre>
   * 被任命的玩家是否在线  0-离线  1-在线
   * </pre>
   *
   * <code>int32 isOnline = 8;</code>
   */
  public int getIsOnline() {
    return isOnline_;
  }

  public static final int PHOTOPROTOID_FIELD_NUMBER = 9;
  private int photoProtoId_;
  /**
   * <pre>
   * 头像
   * </pre>
   *
   * <code>int32 photoProtoId = 9;</code>
   */
  public int getPhotoProtoId() {
    return photoProtoId_;
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
    getSerializedSize();
    if (allianceId_ != 0L) {
      output.writeInt64(1, allianceId_);
    }
    if (pos_ != 0) {
      output.writeInt32(2, pos_);
    }
    if (!getPlayerNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, playerName_);
    }
    if (!getGetPosPlayerNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, getPosPlayerName_);
    }
    if (changeType_ != 0) {
      output.writeInt32(5, changeType_);
    }
    if (getPositionsList().size() > 0) {
      output.writeUInt32NoTag(50);
      output.writeUInt32NoTag(positionsMemoizedSerializedSize);
    }
    for (int i = 0; i < positions_.size(); i++) {
      output.writeInt32NoTag(positions_.get(i));
    }
    if (setPlayerId_ != 0L) {
      output.writeInt64(7, setPlayerId_);
    }
    if (isOnline_ != 0) {
      output.writeInt32(8, isOnline_);
    }
    if (photoProtoId_ != 0) {
      output.writeInt32(9, photoProtoId_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (allianceId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, allianceId_);
    }
    if (pos_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, pos_);
    }
    if (!getPlayerNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, playerName_);
    }
    if (!getGetPosPlayerNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, getPosPlayerName_);
    }
    if (changeType_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, changeType_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < positions_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeInt32SizeNoTag(positions_.get(i));
      }
      size += dataSize;
      if (!getPositionsList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      positionsMemoizedSerializedSize = dataSize;
    }
    if (setPlayerId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(7, setPlayerId_);
    }
    if (isOnline_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(8, isOnline_);
    }
    if (photoProtoId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(9, photoProtoId_);
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
    if (!(obj instanceof pb4server.PosChangeNoticAllAllianceTell)) {
      return super.equals(obj);
    }
    pb4server.PosChangeNoticAllAllianceTell other = (pb4server.PosChangeNoticAllAllianceTell) obj;

    boolean result = true;
    result = result && (getAllianceId()
        == other.getAllianceId());
    result = result && (getPos()
        == other.getPos());
    result = result && getPlayerName()
        .equals(other.getPlayerName());
    result = result && getGetPosPlayerName()
        .equals(other.getGetPosPlayerName());
    result = result && (getChangeType()
        == other.getChangeType());
    result = result && getPositionsList()
        .equals(other.getPositionsList());
    result = result && (getSetPlayerId()
        == other.getSetPlayerId());
    result = result && (getIsOnline()
        == other.getIsOnline());
    result = result && (getPhotoProtoId()
        == other.getPhotoProtoId());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ALLIANCEID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getAllianceId());
    hash = (37 * hash) + POS_FIELD_NUMBER;
    hash = (53 * hash) + getPos();
    hash = (37 * hash) + PLAYERNAME_FIELD_NUMBER;
    hash = (53 * hash) + getPlayerName().hashCode();
    hash = (37 * hash) + GETPOSPLAYERNAME_FIELD_NUMBER;
    hash = (53 * hash) + getGetPosPlayerName().hashCode();
    hash = (37 * hash) + CHANGETYPE_FIELD_NUMBER;
    hash = (53 * hash) + getChangeType();
    if (getPositionsCount() > 0) {
      hash = (37 * hash) + POSITIONS_FIELD_NUMBER;
      hash = (53 * hash) + getPositionsList().hashCode();
    }
    hash = (37 * hash) + SETPLAYERID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getSetPlayerId());
    hash = (37 * hash) + ISONLINE_FIELD_NUMBER;
    hash = (53 * hash) + getIsOnline();
    hash = (37 * hash) + PHOTOPROTOID_FIELD_NUMBER;
    hash = (53 * hash) + getPhotoProtoId();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.PosChangeNoticAllAllianceTell parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.PosChangeNoticAllAllianceTell parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.PosChangeNoticAllAllianceTell parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.PosChangeNoticAllAllianceTell parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.PosChangeNoticAllAllianceTell parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.PosChangeNoticAllAllianceTell parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.PosChangeNoticAllAllianceTell parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.PosChangeNoticAllAllianceTell parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.PosChangeNoticAllAllianceTell parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.PosChangeNoticAllAllianceTell parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.PosChangeNoticAllAllianceTell parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.PosChangeNoticAllAllianceTell parseFrom(
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
  public static Builder newBuilder(pb4server.PosChangeNoticAllAllianceTell prototype) {
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
   * 帮派职位变化通知给所有帮众
   * </pre>
   *
   * Protobuf type {@code pb4server.PosChangeNoticAllAllianceTell}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.PosChangeNoticAllAllianceTell)
      pb4server.PosChangeNoticAllAllianceTellOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalWkt.internal_static_pb4server_PosChangeNoticAllAllianceTell_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalWkt.internal_static_pb4server_PosChangeNoticAllAllianceTell_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.PosChangeNoticAllAllianceTell.class, pb4server.PosChangeNoticAllAllianceTell.Builder.class);
    }

    // Construct using pb4server.PosChangeNoticAllAllianceTell.newBuilder()
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

      pos_ = 0;

      playerName_ = "";

      getPosPlayerName_ = "";

      changeType_ = 0;

      positions_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000020);
      setPlayerId_ = 0L;

      isOnline_ = 0;

      photoProtoId_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalWkt.internal_static_pb4server_PosChangeNoticAllAllianceTell_descriptor;
    }

    public pb4server.PosChangeNoticAllAllianceTell getDefaultInstanceForType() {
      return pb4server.PosChangeNoticAllAllianceTell.getDefaultInstance();
    }

    public pb4server.PosChangeNoticAllAllianceTell build() {
      pb4server.PosChangeNoticAllAllianceTell result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.PosChangeNoticAllAllianceTell buildPartial() {
      pb4server.PosChangeNoticAllAllianceTell result = new pb4server.PosChangeNoticAllAllianceTell(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.allianceId_ = allianceId_;
      result.pos_ = pos_;
      result.playerName_ = playerName_;
      result.getPosPlayerName_ = getPosPlayerName_;
      result.changeType_ = changeType_;
      if (((bitField0_ & 0x00000020) == 0x00000020)) {
        positions_ = java.util.Collections.unmodifiableList(positions_);
        bitField0_ = (bitField0_ & ~0x00000020);
      }
      result.positions_ = positions_;
      result.setPlayerId_ = setPlayerId_;
      result.isOnline_ = isOnline_;
      result.photoProtoId_ = photoProtoId_;
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
      if (other instanceof pb4server.PosChangeNoticAllAllianceTell) {
        return mergeFrom((pb4server.PosChangeNoticAllAllianceTell)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.PosChangeNoticAllAllianceTell other) {
      if (other == pb4server.PosChangeNoticAllAllianceTell.getDefaultInstance()) return this;
      if (other.getAllianceId() != 0L) {
        setAllianceId(other.getAllianceId());
      }
      if (other.getPos() != 0) {
        setPos(other.getPos());
      }
      if (!other.getPlayerName().isEmpty()) {
        playerName_ = other.playerName_;
        onChanged();
      }
      if (!other.getGetPosPlayerName().isEmpty()) {
        getPosPlayerName_ = other.getPosPlayerName_;
        onChanged();
      }
      if (other.getChangeType() != 0) {
        setChangeType(other.getChangeType());
      }
      if (!other.positions_.isEmpty()) {
        if (positions_.isEmpty()) {
          positions_ = other.positions_;
          bitField0_ = (bitField0_ & ~0x00000020);
        } else {
          ensurePositionsIsMutable();
          positions_.addAll(other.positions_);
        }
        onChanged();
      }
      if (other.getSetPlayerId() != 0L) {
        setSetPlayerId(other.getSetPlayerId());
      }
      if (other.getIsOnline() != 0) {
        setIsOnline(other.getIsOnline());
      }
      if (other.getPhotoProtoId() != 0) {
        setPhotoProtoId(other.getPhotoProtoId());
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
      pb4server.PosChangeNoticAllAllianceTell parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.PosChangeNoticAllAllianceTell) e.getUnfinishedMessage();
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
     * <code>int64 allianceId = 1;</code>
     */
    public long getAllianceId() {
      return allianceId_;
    }
    /**
     * <code>int64 allianceId = 1;</code>
     */
    public Builder setAllianceId(long value) {
      
      allianceId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 allianceId = 1;</code>
     */
    public Builder clearAllianceId() {
      
      allianceId_ = 0L;
      onChanged();
      return this;
    }

    private int pos_ ;
    /**
     * <code>int32 pos = 2;</code>
     */
    public int getPos() {
      return pos_;
    }
    /**
     * <code>int32 pos = 2;</code>
     */
    public Builder setPos(int value) {
      
      pos_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 pos = 2;</code>
     */
    public Builder clearPos() {
      
      pos_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object playerName_ = "";
    /**
     * <pre>
     * 给予职位的玩家名
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
     * 给予职位的玩家名
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
     * 给予职位的玩家名
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
     * 给予职位的玩家名
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
     * 给予职位的玩家名
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

    private java.lang.Object getPosPlayerName_ = "";
    /**
     * <pre>
     * 被任命的玩家名
     * </pre>
     *
     * <code>string getPosPlayerName = 4;</code>
     */
    public java.lang.String getGetPosPlayerName() {
      java.lang.Object ref = getPosPlayerName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        getPosPlayerName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * 被任命的玩家名
     * </pre>
     *
     * <code>string getPosPlayerName = 4;</code>
     */
    public com.google.protobuf.ByteString
        getGetPosPlayerNameBytes() {
      java.lang.Object ref = getPosPlayerName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        getPosPlayerName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * 被任命的玩家名
     * </pre>
     *
     * <code>string getPosPlayerName = 4;</code>
     */
    public Builder setGetPosPlayerName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      getPosPlayerName_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 被任命的玩家名
     * </pre>
     *
     * <code>string getPosPlayerName = 4;</code>
     */
    public Builder clearGetPosPlayerName() {
      
      getPosPlayerName_ = getDefaultInstance().getGetPosPlayerName();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 被任命的玩家名
     * </pre>
     *
     * <code>string getPosPlayerName = 4;</code>
     */
    public Builder setGetPosPlayerNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      getPosPlayerName_ = value;
      onChanged();
      return this;
    }

    private int changeType_ ;
    /**
     * <pre>
     * 1-新增  2-减少
     * </pre>
     *
     * <code>int32 changeType = 5;</code>
     */
    public int getChangeType() {
      return changeType_;
    }
    /**
     * <pre>
     * 1-新增  2-减少
     * </pre>
     *
     * <code>int32 changeType = 5;</code>
     */
    public Builder setChangeType(int value) {
      
      changeType_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 1-新增  2-减少
     * </pre>
     *
     * <code>int32 changeType = 5;</code>
     */
    public Builder clearChangeType() {
      
      changeType_ = 0;
      onChanged();
      return this;
    }

    private java.util.List<java.lang.Integer> positions_ = java.util.Collections.emptyList();
    private void ensurePositionsIsMutable() {
      if (!((bitField0_ & 0x00000020) == 0x00000020)) {
        positions_ = new java.util.ArrayList<java.lang.Integer>(positions_);
        bitField0_ |= 0x00000020;
       }
    }
    /**
     * <pre>
     * 被任命后的职位
     * </pre>
     *
     * <code>repeated int32 positions = 6;</code>
     */
    public java.util.List<java.lang.Integer>
        getPositionsList() {
      return java.util.Collections.unmodifiableList(positions_);
    }
    /**
     * <pre>
     * 被任命后的职位
     * </pre>
     *
     * <code>repeated int32 positions = 6;</code>
     */
    public int getPositionsCount() {
      return positions_.size();
    }
    /**
     * <pre>
     * 被任命后的职位
     * </pre>
     *
     * <code>repeated int32 positions = 6;</code>
     */
    public int getPositions(int index) {
      return positions_.get(index);
    }
    /**
     * <pre>
     * 被任命后的职位
     * </pre>
     *
     * <code>repeated int32 positions = 6;</code>
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
     * 被任命后的职位
     * </pre>
     *
     * <code>repeated int32 positions = 6;</code>
     */
    public Builder addPositions(int value) {
      ensurePositionsIsMutable();
      positions_.add(value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 被任命后的职位
     * </pre>
     *
     * <code>repeated int32 positions = 6;</code>
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
     * 被任命后的职位
     * </pre>
     *
     * <code>repeated int32 positions = 6;</code>
     */
    public Builder clearPositions() {
      positions_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000020);
      onChanged();
      return this;
    }

    private long setPlayerId_ ;
    /**
     * <pre>
     * 被任命的玩家ID
     * </pre>
     *
     * <code>int64 setPlayerId = 7;</code>
     */
    public long getSetPlayerId() {
      return setPlayerId_;
    }
    /**
     * <pre>
     * 被任命的玩家ID
     * </pre>
     *
     * <code>int64 setPlayerId = 7;</code>
     */
    public Builder setSetPlayerId(long value) {
      
      setPlayerId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 被任命的玩家ID
     * </pre>
     *
     * <code>int64 setPlayerId = 7;</code>
     */
    public Builder clearSetPlayerId() {
      
      setPlayerId_ = 0L;
      onChanged();
      return this;
    }

    private int isOnline_ ;
    /**
     * <pre>
     * 被任命的玩家是否在线  0-离线  1-在线
     * </pre>
     *
     * <code>int32 isOnline = 8;</code>
     */
    public int getIsOnline() {
      return isOnline_;
    }
    /**
     * <pre>
     * 被任命的玩家是否在线  0-离线  1-在线
     * </pre>
     *
     * <code>int32 isOnline = 8;</code>
     */
    public Builder setIsOnline(int value) {
      
      isOnline_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 被任命的玩家是否在线  0-离线  1-在线
     * </pre>
     *
     * <code>int32 isOnline = 8;</code>
     */
    public Builder clearIsOnline() {
      
      isOnline_ = 0;
      onChanged();
      return this;
    }

    private int photoProtoId_ ;
    /**
     * <pre>
     * 头像
     * </pre>
     *
     * <code>int32 photoProtoId = 9;</code>
     */
    public int getPhotoProtoId() {
      return photoProtoId_;
    }
    /**
     * <pre>
     * 头像
     * </pre>
     *
     * <code>int32 photoProtoId = 9;</code>
     */
    public Builder setPhotoProtoId(int value) {
      
      photoProtoId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 头像
     * </pre>
     *
     * <code>int32 photoProtoId = 9;</code>
     */
    public Builder clearPhotoProtoId() {
      
      photoProtoId_ = 0;
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


    // @@protoc_insertion_point(builder_scope:pb4server.PosChangeNoticAllAllianceTell)
  }

  // @@protoc_insertion_point(class_scope:pb4server.PosChangeNoticAllAllianceTell)
  private static final pb4server.PosChangeNoticAllAllianceTell DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.PosChangeNoticAllAllianceTell();
  }

  public static pb4server.PosChangeNoticAllAllianceTell getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<PosChangeNoticAllAllianceTell>
      PARSER = new com.google.protobuf.AbstractParser<PosChangeNoticAllAllianceTell>() {
    public PosChangeNoticAllAllianceTell parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new PosChangeNoticAllAllianceTell(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<PosChangeNoticAllAllianceTell> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<PosChangeNoticAllAllianceTell> getParserForType() {
    return PARSER;
  }

  public pb4server.PosChangeNoticAllAllianceTell getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

