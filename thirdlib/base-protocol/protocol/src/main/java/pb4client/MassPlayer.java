// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.MassPlayer}
 */
public  final class MassPlayer extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.MassPlayer)
    MassPlayerOrBuilder {
  // Use MassPlayer.newBuilder() to construct.
  private MassPlayer(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private MassPlayer() {
    playerId_ = 0L;
    name_ = "";
    photo_ = 0;
    allianceId_ = 0L;
    allianceName_ = "";
    allianceShortName_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private MassPlayer(
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
          case 72: {
            bitField0_ |= 0x00000001;
            playerId_ = input.readInt64();
            break;
          }
          case 82: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000002;
            name_ = bs;
            break;
          }
          case 88: {
            bitField0_ |= 0x00000004;
            photo_ = input.readInt32();
            break;
          }
          case 96: {
            bitField0_ |= 0x00000008;
            allianceId_ = input.readInt64();
            break;
          }
          case 106: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000010;
            allianceName_ = bs;
            break;
          }
          case 114: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000020;
            allianceShortName_ = bs;
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
    return pb4client.War2GamePkt.internal_static_client2server_MassPlayer_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_MassPlayer_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.MassPlayer.class, pb4client.MassPlayer.Builder.class);
  }

  private int bitField0_;
  public static final int PLAYERID_FIELD_NUMBER = 9;
  private long playerId_;
  /**
   * <pre>
   *目标玩家Id
   * </pre>
   *
   * <code>optional int64 playerId = 9;</code>
   */
  public boolean hasPlayerId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *目标玩家Id
   * </pre>
   *
   * <code>optional int64 playerId = 9;</code>
   */
  public long getPlayerId() {
    return playerId_;
  }

  public static final int NAME_FIELD_NUMBER = 10;
  private volatile java.lang.Object name_;
  /**
   * <pre>
   *目标玩家名称
   * </pre>
   *
   * <code>optional string name = 10;</code>
   */
  public boolean hasName() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *目标玩家名称
   * </pre>
   *
   * <code>optional string name = 10;</code>
   */
  public java.lang.String getName() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        name_ = s;
      }
      return s;
    }
  }
  /**
   * <pre>
   *目标玩家名称
   * </pre>
   *
   * <code>optional string name = 10;</code>
   */
  public com.google.protobuf.ByteString
      getNameBytes() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      name_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PHOTO_FIELD_NUMBER = 11;
  private int photo_;
  /**
   * <pre>
   *目标玩家头像
   * </pre>
   *
   * <code>optional int32 photo = 11;</code>
   */
  public boolean hasPhoto() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   *目标玩家头像
   * </pre>
   *
   * <code>optional int32 photo = 11;</code>
   */
  public int getPhoto() {
    return photo_;
  }

  public static final int ALLIANCEID_FIELD_NUMBER = 12;
  private long allianceId_;
  /**
   * <pre>
   *目标玩家联盟Id
   * </pre>
   *
   * <code>optional int64 allianceId = 12;</code>
   */
  public boolean hasAllianceId() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   *目标玩家联盟Id
   * </pre>
   *
   * <code>optional int64 allianceId = 12;</code>
   */
  public long getAllianceId() {
    return allianceId_;
  }

  public static final int ALLIANCENAME_FIELD_NUMBER = 13;
  private volatile java.lang.Object allianceName_;
  /**
   * <pre>
   *目标联盟名称
   * </pre>
   *
   * <code>optional string allianceName = 13;</code>
   */
  public boolean hasAllianceName() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <pre>
   *目标联盟名称
   * </pre>
   *
   * <code>optional string allianceName = 13;</code>
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
   *目标联盟名称
   * </pre>
   *
   * <code>optional string allianceName = 13;</code>
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

  public static final int ALLIANCESHORTNAME_FIELD_NUMBER = 14;
  private volatile java.lang.Object allianceShortName_;
  /**
   * <pre>
   *目标联盟名称
   * </pre>
   *
   * <code>optional string allianceShortName = 14;</code>
   */
  public boolean hasAllianceShortName() {
    return ((bitField0_ & 0x00000020) == 0x00000020);
  }
  /**
   * <pre>
   *目标联盟名称
   * </pre>
   *
   * <code>optional string allianceShortName = 14;</code>
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
   * <pre>
   *目标联盟名称
   * </pre>
   *
   * <code>optional string allianceShortName = 14;</code>
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
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt64(9, playerId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 10, name_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(11, photo_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt64(12, allianceId_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 13, allianceName_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 14, allianceShortName_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(9, playerId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(10, name_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(11, photo_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(12, allianceId_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(13, allianceName_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(14, allianceShortName_);
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
    if (!(obj instanceof pb4client.MassPlayer)) {
      return super.equals(obj);
    }
    pb4client.MassPlayer other = (pb4client.MassPlayer) obj;

    boolean result = true;
    result = result && (hasPlayerId() == other.hasPlayerId());
    if (hasPlayerId()) {
      result = result && (getPlayerId()
          == other.getPlayerId());
    }
    result = result && (hasName() == other.hasName());
    if (hasName()) {
      result = result && getName()
          .equals(other.getName());
    }
    result = result && (hasPhoto() == other.hasPhoto());
    if (hasPhoto()) {
      result = result && (getPhoto()
          == other.getPhoto());
    }
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
    result = result && (hasAllianceShortName() == other.hasAllianceShortName());
    if (hasAllianceShortName()) {
      result = result && getAllianceShortName()
          .equals(other.getAllianceShortName());
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
    if (hasName()) {
      hash = (37 * hash) + NAME_FIELD_NUMBER;
      hash = (53 * hash) + getName().hashCode();
    }
    if (hasPhoto()) {
      hash = (37 * hash) + PHOTO_FIELD_NUMBER;
      hash = (53 * hash) + getPhoto();
    }
    if (hasAllianceId()) {
      hash = (37 * hash) + ALLIANCEID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getAllianceId());
    }
    if (hasAllianceName()) {
      hash = (37 * hash) + ALLIANCENAME_FIELD_NUMBER;
      hash = (53 * hash) + getAllianceName().hashCode();
    }
    if (hasAllianceShortName()) {
      hash = (37 * hash) + ALLIANCESHORTNAME_FIELD_NUMBER;
      hash = (53 * hash) + getAllianceShortName().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.MassPlayer parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.MassPlayer parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.MassPlayer parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.MassPlayer parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.MassPlayer parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.MassPlayer parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.MassPlayer parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.MassPlayer parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.MassPlayer parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.MassPlayer parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.MassPlayer parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.MassPlayer parseFrom(
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
  public static Builder newBuilder(pb4client.MassPlayer prototype) {
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
   * Protobuf type {@code client2server.MassPlayer}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.MassPlayer)
      pb4client.MassPlayerOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_MassPlayer_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_MassPlayer_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.MassPlayer.class, pb4client.MassPlayer.Builder.class);
    }

    // Construct using pb4client.MassPlayer.newBuilder()
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
      name_ = "";
      bitField0_ = (bitField0_ & ~0x00000002);
      photo_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      allianceId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000008);
      allianceName_ = "";
      bitField0_ = (bitField0_ & ~0x00000010);
      allianceShortName_ = "";
      bitField0_ = (bitField0_ & ~0x00000020);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_MassPlayer_descriptor;
    }

    public pb4client.MassPlayer getDefaultInstanceForType() {
      return pb4client.MassPlayer.getDefaultInstance();
    }

    public pb4client.MassPlayer build() {
      pb4client.MassPlayer result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.MassPlayer buildPartial() {
      pb4client.MassPlayer result = new pb4client.MassPlayer(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.playerId_ = playerId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.name_ = name_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.photo_ = photo_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.allianceId_ = allianceId_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.allianceName_ = allianceName_;
      if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
        to_bitField0_ |= 0x00000020;
      }
      result.allianceShortName_ = allianceShortName_;
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
      if (other instanceof pb4client.MassPlayer) {
        return mergeFrom((pb4client.MassPlayer)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.MassPlayer other) {
      if (other == pb4client.MassPlayer.getDefaultInstance()) return this;
      if (other.hasPlayerId()) {
        setPlayerId(other.getPlayerId());
      }
      if (other.hasName()) {
        bitField0_ |= 0x00000002;
        name_ = other.name_;
        onChanged();
      }
      if (other.hasPhoto()) {
        setPhoto(other.getPhoto());
      }
      if (other.hasAllianceId()) {
        setAllianceId(other.getAllianceId());
      }
      if (other.hasAllianceName()) {
        bitField0_ |= 0x00000010;
        allianceName_ = other.allianceName_;
        onChanged();
      }
      if (other.hasAllianceShortName()) {
        bitField0_ |= 0x00000020;
        allianceShortName_ = other.allianceShortName_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
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
      pb4client.MassPlayer parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.MassPlayer) e.getUnfinishedMessage();
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
     *目标玩家Id
     * </pre>
     *
     * <code>optional int64 playerId = 9;</code>
     */
    public boolean hasPlayerId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *目标玩家Id
     * </pre>
     *
     * <code>optional int64 playerId = 9;</code>
     */
    public long getPlayerId() {
      return playerId_;
    }
    /**
     * <pre>
     *目标玩家Id
     * </pre>
     *
     * <code>optional int64 playerId = 9;</code>
     */
    public Builder setPlayerId(long value) {
      bitField0_ |= 0x00000001;
      playerId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *目标玩家Id
     * </pre>
     *
     * <code>optional int64 playerId = 9;</code>
     */
    public Builder clearPlayerId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      playerId_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object name_ = "";
    /**
     * <pre>
     *目标玩家名称
     * </pre>
     *
     * <code>optional string name = 10;</code>
     */
    public boolean hasName() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *目标玩家名称
     * </pre>
     *
     * <code>optional string name = 10;</code>
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          name_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *目标玩家名称
     * </pre>
     *
     * <code>optional string name = 10;</code>
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *目标玩家名称
     * </pre>
     *
     * <code>optional string name = 10;</code>
     */
    public Builder setName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      name_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *目标玩家名称
     * </pre>
     *
     * <code>optional string name = 10;</code>
     */
    public Builder clearName() {
      bitField0_ = (bitField0_ & ~0x00000002);
      name_ = getDefaultInstance().getName();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *目标玩家名称
     * </pre>
     *
     * <code>optional string name = 10;</code>
     */
    public Builder setNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      name_ = value;
      onChanged();
      return this;
    }

    private int photo_ ;
    /**
     * <pre>
     *目标玩家头像
     * </pre>
     *
     * <code>optional int32 photo = 11;</code>
     */
    public boolean hasPhoto() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     *目标玩家头像
     * </pre>
     *
     * <code>optional int32 photo = 11;</code>
     */
    public int getPhoto() {
      return photo_;
    }
    /**
     * <pre>
     *目标玩家头像
     * </pre>
     *
     * <code>optional int32 photo = 11;</code>
     */
    public Builder setPhoto(int value) {
      bitField0_ |= 0x00000004;
      photo_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *目标玩家头像
     * </pre>
     *
     * <code>optional int32 photo = 11;</code>
     */
    public Builder clearPhoto() {
      bitField0_ = (bitField0_ & ~0x00000004);
      photo_ = 0;
      onChanged();
      return this;
    }

    private long allianceId_ ;
    /**
     * <pre>
     *目标玩家联盟Id
     * </pre>
     *
     * <code>optional int64 allianceId = 12;</code>
     */
    public boolean hasAllianceId() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     *目标玩家联盟Id
     * </pre>
     *
     * <code>optional int64 allianceId = 12;</code>
     */
    public long getAllianceId() {
      return allianceId_;
    }
    /**
     * <pre>
     *目标玩家联盟Id
     * </pre>
     *
     * <code>optional int64 allianceId = 12;</code>
     */
    public Builder setAllianceId(long value) {
      bitField0_ |= 0x00000008;
      allianceId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *目标玩家联盟Id
     * </pre>
     *
     * <code>optional int64 allianceId = 12;</code>
     */
    public Builder clearAllianceId() {
      bitField0_ = (bitField0_ & ~0x00000008);
      allianceId_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object allianceName_ = "";
    /**
     * <pre>
     *目标联盟名称
     * </pre>
     *
     * <code>optional string allianceName = 13;</code>
     */
    public boolean hasAllianceName() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <pre>
     *目标联盟名称
     * </pre>
     *
     * <code>optional string allianceName = 13;</code>
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
     *目标联盟名称
     * </pre>
     *
     * <code>optional string allianceName = 13;</code>
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
     *目标联盟名称
     * </pre>
     *
     * <code>optional string allianceName = 13;</code>
     */
    public Builder setAllianceName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000010;
      allianceName_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *目标联盟名称
     * </pre>
     *
     * <code>optional string allianceName = 13;</code>
     */
    public Builder clearAllianceName() {
      bitField0_ = (bitField0_ & ~0x00000010);
      allianceName_ = getDefaultInstance().getAllianceName();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *目标联盟名称
     * </pre>
     *
     * <code>optional string allianceName = 13;</code>
     */
    public Builder setAllianceNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000010;
      allianceName_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object allianceShortName_ = "";
    /**
     * <pre>
     *目标联盟名称
     * </pre>
     *
     * <code>optional string allianceShortName = 14;</code>
     */
    public boolean hasAllianceShortName() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    /**
     * <pre>
     *目标联盟名称
     * </pre>
     *
     * <code>optional string allianceShortName = 14;</code>
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
     * <pre>
     *目标联盟名称
     * </pre>
     *
     * <code>optional string allianceShortName = 14;</code>
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
     * <pre>
     *目标联盟名称
     * </pre>
     *
     * <code>optional string allianceShortName = 14;</code>
     */
    public Builder setAllianceShortName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000020;
      allianceShortName_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *目标联盟名称
     * </pre>
     *
     * <code>optional string allianceShortName = 14;</code>
     */
    public Builder clearAllianceShortName() {
      bitField0_ = (bitField0_ & ~0x00000020);
      allianceShortName_ = getDefaultInstance().getAllianceShortName();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *目标联盟名称
     * </pre>
     *
     * <code>optional string allianceShortName = 14;</code>
     */
    public Builder setAllianceShortNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000020;
      allianceShortName_ = value;
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


    // @@protoc_insertion_point(builder_scope:client2server.MassPlayer)
  }

  // @@protoc_insertion_point(class_scope:client2server.MassPlayer)
  private static final pb4client.MassPlayer DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.MassPlayer();
  }

  public static pb4client.MassPlayer getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<MassPlayer>
      PARSER = new com.google.protobuf.AbstractParser<MassPlayer>() {
    public MassPlayer parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new MassPlayer(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<MassPlayer> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<MassPlayer> getParserForType() {
    return PARSER;
  }

  public pb4client.MassPlayer getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

