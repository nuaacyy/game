// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2home.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.FriendInfoVo}
 */
public  final class FriendInfoVo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.FriendInfoVo)
    FriendInfoVoOrBuilder {
  // Use FriendInfoVo.newBuilder() to construct.
  private FriendInfoVo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private FriendInfoVo() {
    myPlayerId_ = 0L;
    name_ = "";
    photoProtoId_ = 0;
    castleLv_ = 0;
    skinType_ = 0;
    vipLv_ = 0;
    areaNo_ = 0;
    allianceShortName_ = "";
    shortName_ = "";
    worldId_ = 0L;
    power_ = 0;
    allianceNickName_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private FriendInfoVo(
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

            myPlayerId_ = input.readInt64();
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            name_ = s;
            break;
          }
          case 24: {

            photoProtoId_ = input.readInt32();
            break;
          }
          case 32: {

            castleLv_ = input.readInt32();
            break;
          }
          case 40: {

            skinType_ = input.readInt32();
            break;
          }
          case 48: {

            vipLv_ = input.readInt32();
            break;
          }
          case 56: {

            areaNo_ = input.readInt32();
            break;
          }
          case 66: {
            java.lang.String s = input.readStringRequireUtf8();

            allianceShortName_ = s;
            break;
          }
          case 74: {
            java.lang.String s = input.readStringRequireUtf8();

            shortName_ = s;
            break;
          }
          case 80: {

            worldId_ = input.readInt64();
            break;
          }
          case 88: {

            power_ = input.readInt32();
            break;
          }
          case 98: {
            java.lang.String s = input.readStringRequireUtf8();

            allianceNickName_ = s;
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
    return pb4server.InternalHkt.internal_static_pb4server_FriendInfoVo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalHkt.internal_static_pb4server_FriendInfoVo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.FriendInfoVo.class, pb4server.FriendInfoVo.Builder.class);
  }

  public static final int MYPLAYERID_FIELD_NUMBER = 1;
  private long myPlayerId_;
  /**
   * <code>int64 myPlayerId = 1;</code>
   */
  public long getMyPlayerId() {
    return myPlayerId_;
  }

  public static final int NAME_FIELD_NUMBER = 2;
  private volatile java.lang.Object name_;
  /**
   * <code>string name = 2;</code>
   */
  public java.lang.String getName() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      name_ = s;
      return s;
    }
  }
  /**
   * <code>string name = 2;</code>
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

  public static final int PHOTOPROTOID_FIELD_NUMBER = 3;
  private int photoProtoId_;
  /**
   * <code>int32 photoProtoId = 3;</code>
   */
  public int getPhotoProtoId() {
    return photoProtoId_;
  }

  public static final int CASTLELV_FIELD_NUMBER = 4;
  private int castleLv_;
  /**
   * <code>int32 castleLv = 4;</code>
   */
  public int getCastleLv() {
    return castleLv_;
  }

  public static final int SKINTYPE_FIELD_NUMBER = 5;
  private int skinType_;
  /**
   * <code>int32 skinType = 5;</code>
   */
  public int getSkinType() {
    return skinType_;
  }

  public static final int VIPLV_FIELD_NUMBER = 6;
  private int vipLv_;
  /**
   * <code>int32 vipLv = 6;</code>
   */
  public int getVipLv() {
    return vipLv_;
  }

  public static final int AREANO_FIELD_NUMBER = 7;
  private int areaNo_;
  /**
   * <code>int32 areaNo = 7;</code>
   */
  public int getAreaNo() {
    return areaNo_;
  }

  public static final int ALLIANCESHORTNAME_FIELD_NUMBER = 8;
  private volatile java.lang.Object allianceShortName_;
  /**
   * <code>string allianceShortName = 8;</code>
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
   * <code>string allianceShortName = 8;</code>
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

  public static final int SHORTNAME_FIELD_NUMBER = 9;
  private volatile java.lang.Object shortName_;
  /**
   * <code>string shortName = 9;</code>
   */
  public java.lang.String getShortName() {
    java.lang.Object ref = shortName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      shortName_ = s;
      return s;
    }
  }
  /**
   * <code>string shortName = 9;</code>
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

  public static final int WORLDID_FIELD_NUMBER = 10;
  private long worldId_;
  /**
   * <code>int64 worldId = 10;</code>
   */
  public long getWorldId() {
    return worldId_;
  }

  public static final int POWER_FIELD_NUMBER = 11;
  private int power_;
  /**
   * <code>int32 power = 11;</code>
   */
  public int getPower() {
    return power_;
  }

  public static final int ALLIANCENICKNAME_FIELD_NUMBER = 12;
  private volatile java.lang.Object allianceNickName_;
  /**
   * <code>string allianceNickName = 12;</code>
   */
  public java.lang.String getAllianceNickName() {
    java.lang.Object ref = allianceNickName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      allianceNickName_ = s;
      return s;
    }
  }
  /**
   * <code>string allianceNickName = 12;</code>
   */
  public com.google.protobuf.ByteString
      getAllianceNickNameBytes() {
    java.lang.Object ref = allianceNickName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      allianceNickName_ = b;
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
    if (myPlayerId_ != 0L) {
      output.writeInt64(1, myPlayerId_);
    }
    if (!getNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, name_);
    }
    if (photoProtoId_ != 0) {
      output.writeInt32(3, photoProtoId_);
    }
    if (castleLv_ != 0) {
      output.writeInt32(4, castleLv_);
    }
    if (skinType_ != 0) {
      output.writeInt32(5, skinType_);
    }
    if (vipLv_ != 0) {
      output.writeInt32(6, vipLv_);
    }
    if (areaNo_ != 0) {
      output.writeInt32(7, areaNo_);
    }
    if (!getAllianceShortNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 8, allianceShortName_);
    }
    if (!getShortNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 9, shortName_);
    }
    if (worldId_ != 0L) {
      output.writeInt64(10, worldId_);
    }
    if (power_ != 0) {
      output.writeInt32(11, power_);
    }
    if (!getAllianceNickNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 12, allianceNickName_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (myPlayerId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, myPlayerId_);
    }
    if (!getNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, name_);
    }
    if (photoProtoId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, photoProtoId_);
    }
    if (castleLv_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, castleLv_);
    }
    if (skinType_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, skinType_);
    }
    if (vipLv_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, vipLv_);
    }
    if (areaNo_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(7, areaNo_);
    }
    if (!getAllianceShortNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(8, allianceShortName_);
    }
    if (!getShortNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(9, shortName_);
    }
    if (worldId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(10, worldId_);
    }
    if (power_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(11, power_);
    }
    if (!getAllianceNickNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(12, allianceNickName_);
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
    if (!(obj instanceof pb4server.FriendInfoVo)) {
      return super.equals(obj);
    }
    pb4server.FriendInfoVo other = (pb4server.FriendInfoVo) obj;

    boolean result = true;
    result = result && (getMyPlayerId()
        == other.getMyPlayerId());
    result = result && getName()
        .equals(other.getName());
    result = result && (getPhotoProtoId()
        == other.getPhotoProtoId());
    result = result && (getCastleLv()
        == other.getCastleLv());
    result = result && (getSkinType()
        == other.getSkinType());
    result = result && (getVipLv()
        == other.getVipLv());
    result = result && (getAreaNo()
        == other.getAreaNo());
    result = result && getAllianceShortName()
        .equals(other.getAllianceShortName());
    result = result && getShortName()
        .equals(other.getShortName());
    result = result && (getWorldId()
        == other.getWorldId());
    result = result && (getPower()
        == other.getPower());
    result = result && getAllianceNickName()
        .equals(other.getAllianceNickName());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + MYPLAYERID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getMyPlayerId());
    hash = (37 * hash) + NAME_FIELD_NUMBER;
    hash = (53 * hash) + getName().hashCode();
    hash = (37 * hash) + PHOTOPROTOID_FIELD_NUMBER;
    hash = (53 * hash) + getPhotoProtoId();
    hash = (37 * hash) + CASTLELV_FIELD_NUMBER;
    hash = (53 * hash) + getCastleLv();
    hash = (37 * hash) + SKINTYPE_FIELD_NUMBER;
    hash = (53 * hash) + getSkinType();
    hash = (37 * hash) + VIPLV_FIELD_NUMBER;
    hash = (53 * hash) + getVipLv();
    hash = (37 * hash) + AREANO_FIELD_NUMBER;
    hash = (53 * hash) + getAreaNo();
    hash = (37 * hash) + ALLIANCESHORTNAME_FIELD_NUMBER;
    hash = (53 * hash) + getAllianceShortName().hashCode();
    hash = (37 * hash) + SHORTNAME_FIELD_NUMBER;
    hash = (53 * hash) + getShortName().hashCode();
    hash = (37 * hash) + WORLDID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getWorldId());
    hash = (37 * hash) + POWER_FIELD_NUMBER;
    hash = (53 * hash) + getPower();
    hash = (37 * hash) + ALLIANCENICKNAME_FIELD_NUMBER;
    hash = (53 * hash) + getAllianceNickName().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.FriendInfoVo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.FriendInfoVo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.FriendInfoVo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.FriendInfoVo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.FriendInfoVo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.FriendInfoVo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.FriendInfoVo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.FriendInfoVo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.FriendInfoVo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.FriendInfoVo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.FriendInfoVo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.FriendInfoVo parseFrom(
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
  public static Builder newBuilder(pb4server.FriendInfoVo prototype) {
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
   * Protobuf type {@code pb4server.FriendInfoVo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.FriendInfoVo)
      pb4server.FriendInfoVoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalHkt.internal_static_pb4server_FriendInfoVo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalHkt.internal_static_pb4server_FriendInfoVo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.FriendInfoVo.class, pb4server.FriendInfoVo.Builder.class);
    }

    // Construct using pb4server.FriendInfoVo.newBuilder()
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
      myPlayerId_ = 0L;

      name_ = "";

      photoProtoId_ = 0;

      castleLv_ = 0;

      skinType_ = 0;

      vipLv_ = 0;

      areaNo_ = 0;

      allianceShortName_ = "";

      shortName_ = "";

      worldId_ = 0L;

      power_ = 0;

      allianceNickName_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalHkt.internal_static_pb4server_FriendInfoVo_descriptor;
    }

    public pb4server.FriendInfoVo getDefaultInstanceForType() {
      return pb4server.FriendInfoVo.getDefaultInstance();
    }

    public pb4server.FriendInfoVo build() {
      pb4server.FriendInfoVo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.FriendInfoVo buildPartial() {
      pb4server.FriendInfoVo result = new pb4server.FriendInfoVo(this);
      result.myPlayerId_ = myPlayerId_;
      result.name_ = name_;
      result.photoProtoId_ = photoProtoId_;
      result.castleLv_ = castleLv_;
      result.skinType_ = skinType_;
      result.vipLv_ = vipLv_;
      result.areaNo_ = areaNo_;
      result.allianceShortName_ = allianceShortName_;
      result.shortName_ = shortName_;
      result.worldId_ = worldId_;
      result.power_ = power_;
      result.allianceNickName_ = allianceNickName_;
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
      if (other instanceof pb4server.FriendInfoVo) {
        return mergeFrom((pb4server.FriendInfoVo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.FriendInfoVo other) {
      if (other == pb4server.FriendInfoVo.getDefaultInstance()) return this;
      if (other.getMyPlayerId() != 0L) {
        setMyPlayerId(other.getMyPlayerId());
      }
      if (!other.getName().isEmpty()) {
        name_ = other.name_;
        onChanged();
      }
      if (other.getPhotoProtoId() != 0) {
        setPhotoProtoId(other.getPhotoProtoId());
      }
      if (other.getCastleLv() != 0) {
        setCastleLv(other.getCastleLv());
      }
      if (other.getSkinType() != 0) {
        setSkinType(other.getSkinType());
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
      if (!other.getShortName().isEmpty()) {
        shortName_ = other.shortName_;
        onChanged();
      }
      if (other.getWorldId() != 0L) {
        setWorldId(other.getWorldId());
      }
      if (other.getPower() != 0) {
        setPower(other.getPower());
      }
      if (!other.getAllianceNickName().isEmpty()) {
        allianceNickName_ = other.allianceNickName_;
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
      pb4server.FriendInfoVo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.FriendInfoVo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long myPlayerId_ ;
    /**
     * <code>int64 myPlayerId = 1;</code>
     */
    public long getMyPlayerId() {
      return myPlayerId_;
    }
    /**
     * <code>int64 myPlayerId = 1;</code>
     */
    public Builder setMyPlayerId(long value) {
      
      myPlayerId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 myPlayerId = 1;</code>
     */
    public Builder clearMyPlayerId() {
      
      myPlayerId_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object name_ = "";
    /**
     * <code>string name = 2;</code>
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        name_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string name = 2;</code>
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
     * <code>string name = 2;</code>
     */
    public Builder setName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      name_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string name = 2;</code>
     */
    public Builder clearName() {
      
      name_ = getDefaultInstance().getName();
      onChanged();
      return this;
    }
    /**
     * <code>string name = 2;</code>
     */
    public Builder setNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      name_ = value;
      onChanged();
      return this;
    }

    private int photoProtoId_ ;
    /**
     * <code>int32 photoProtoId = 3;</code>
     */
    public int getPhotoProtoId() {
      return photoProtoId_;
    }
    /**
     * <code>int32 photoProtoId = 3;</code>
     */
    public Builder setPhotoProtoId(int value) {
      
      photoProtoId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 photoProtoId = 3;</code>
     */
    public Builder clearPhotoProtoId() {
      
      photoProtoId_ = 0;
      onChanged();
      return this;
    }

    private int castleLv_ ;
    /**
     * <code>int32 castleLv = 4;</code>
     */
    public int getCastleLv() {
      return castleLv_;
    }
    /**
     * <code>int32 castleLv = 4;</code>
     */
    public Builder setCastleLv(int value) {
      
      castleLv_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 castleLv = 4;</code>
     */
    public Builder clearCastleLv() {
      
      castleLv_ = 0;
      onChanged();
      return this;
    }

    private int skinType_ ;
    /**
     * <code>int32 skinType = 5;</code>
     */
    public int getSkinType() {
      return skinType_;
    }
    /**
     * <code>int32 skinType = 5;</code>
     */
    public Builder setSkinType(int value) {
      
      skinType_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 skinType = 5;</code>
     */
    public Builder clearSkinType() {
      
      skinType_ = 0;
      onChanged();
      return this;
    }

    private int vipLv_ ;
    /**
     * <code>int32 vipLv = 6;</code>
     */
    public int getVipLv() {
      return vipLv_;
    }
    /**
     * <code>int32 vipLv = 6;</code>
     */
    public Builder setVipLv(int value) {
      
      vipLv_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 vipLv = 6;</code>
     */
    public Builder clearVipLv() {
      
      vipLv_ = 0;
      onChanged();
      return this;
    }

    private int areaNo_ ;
    /**
     * <code>int32 areaNo = 7;</code>
     */
    public int getAreaNo() {
      return areaNo_;
    }
    /**
     * <code>int32 areaNo = 7;</code>
     */
    public Builder setAreaNo(int value) {
      
      areaNo_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 areaNo = 7;</code>
     */
    public Builder clearAreaNo() {
      
      areaNo_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object allianceShortName_ = "";
    /**
     * <code>string allianceShortName = 8;</code>
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
     * <code>string allianceShortName = 8;</code>
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
     * <code>string allianceShortName = 8;</code>
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
     * <code>string allianceShortName = 8;</code>
     */
    public Builder clearAllianceShortName() {
      
      allianceShortName_ = getDefaultInstance().getAllianceShortName();
      onChanged();
      return this;
    }
    /**
     * <code>string allianceShortName = 8;</code>
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

    private java.lang.Object shortName_ = "";
    /**
     * <code>string shortName = 9;</code>
     */
    public java.lang.String getShortName() {
      java.lang.Object ref = shortName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        shortName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string shortName = 9;</code>
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
     * <code>string shortName = 9;</code>
     */
    public Builder setShortName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      shortName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string shortName = 9;</code>
     */
    public Builder clearShortName() {
      
      shortName_ = getDefaultInstance().getShortName();
      onChanged();
      return this;
    }
    /**
     * <code>string shortName = 9;</code>
     */
    public Builder setShortNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      shortName_ = value;
      onChanged();
      return this;
    }

    private long worldId_ ;
    /**
     * <code>int64 worldId = 10;</code>
     */
    public long getWorldId() {
      return worldId_;
    }
    /**
     * <code>int64 worldId = 10;</code>
     */
    public Builder setWorldId(long value) {
      
      worldId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 worldId = 10;</code>
     */
    public Builder clearWorldId() {
      
      worldId_ = 0L;
      onChanged();
      return this;
    }

    private int power_ ;
    /**
     * <code>int32 power = 11;</code>
     */
    public int getPower() {
      return power_;
    }
    /**
     * <code>int32 power = 11;</code>
     */
    public Builder setPower(int value) {
      
      power_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 power = 11;</code>
     */
    public Builder clearPower() {
      
      power_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object allianceNickName_ = "";
    /**
     * <code>string allianceNickName = 12;</code>
     */
    public java.lang.String getAllianceNickName() {
      java.lang.Object ref = allianceNickName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        allianceNickName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string allianceNickName = 12;</code>
     */
    public com.google.protobuf.ByteString
        getAllianceNickNameBytes() {
      java.lang.Object ref = allianceNickName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        allianceNickName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string allianceNickName = 12;</code>
     */
    public Builder setAllianceNickName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      allianceNickName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string allianceNickName = 12;</code>
     */
    public Builder clearAllianceNickName() {
      
      allianceNickName_ = getDefaultInstance().getAllianceNickName();
      onChanged();
      return this;
    }
    /**
     * <code>string allianceNickName = 12;</code>
     */
    public Builder setAllianceNickNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      allianceNickName_ = value;
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


    // @@protoc_insertion_point(builder_scope:pb4server.FriendInfoVo)
  }

  // @@protoc_insertion_point(class_scope:pb4server.FriendInfoVo)
  private static final pb4server.FriendInfoVo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.FriendInfoVo();
  }

  public static pb4server.FriendInfoVo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<FriendInfoVo>
      PARSER = new com.google.protobuf.AbstractParser<FriendInfoVo>() {
    public FriendInfoVo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new FriendInfoVo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<FriendInfoVo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<FriendInfoVo> getParserForType() {
    return PARSER;
  }

  public pb4server.FriendInfoVo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
