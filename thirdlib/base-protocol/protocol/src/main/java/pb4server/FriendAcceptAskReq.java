// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2home.proto

package pb4server;

/**
 * <pre>
 * 接受好友请求
 * </pre>
 *
 * Protobuf type {@code pb4server.FriendAcceptAskReq}
 */
public  final class FriendAcceptAskReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.FriendAcceptAskReq)
    FriendAcceptAskReqOrBuilder {
  // Use FriendAcceptAskReq.newBuilder() to construct.
  private FriendAcceptAskReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private FriendAcceptAskReq() {
    myPlayerId_ = 0L;
    worldId_ = 0L;
    name_ = "";
    photoProtoId_ = 0;
    castleLv_ = 0;
    skinType_ = 0;
    vipLv_ = 0;
    areaNo_ = 0;
    allianceShortName_ = "";
    type_ = 0;
    allianceNickname_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private FriendAcceptAskReq(
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
          case 16: {

            worldId_ = input.readInt64();
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            name_ = s;
            break;
          }
          case 32: {

            photoProtoId_ = input.readInt32();
            break;
          }
          case 40: {

            castleLv_ = input.readInt32();
            break;
          }
          case 48: {

            skinType_ = input.readInt32();
            break;
          }
          case 56: {

            vipLv_ = input.readInt32();
            break;
          }
          case 64: {

            areaNo_ = input.readInt32();
            break;
          }
          case 74: {
            java.lang.String s = input.readStringRequireUtf8();

            allianceShortName_ = s;
            break;
          }
          case 80: {

            type_ = input.readInt32();
            break;
          }
          case 90: {
            java.lang.String s = input.readStringRequireUtf8();

            allianceNickname_ = s;
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
    return pb4server.InternalHkt.internal_static_pb4server_FriendAcceptAskReq_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalHkt.internal_static_pb4server_FriendAcceptAskReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.FriendAcceptAskReq.class, pb4server.FriendAcceptAskReq.Builder.class);
  }

  public static final int MYPLAYERID_FIELD_NUMBER = 1;
  private long myPlayerId_;
  /**
   * <code>int64 myPlayerId = 1;</code>
   */
  public long getMyPlayerId() {
    return myPlayerId_;
  }

  public static final int WORLDID_FIELD_NUMBER = 2;
  private long worldId_;
  /**
   * <code>int64 worldId = 2;</code>
   */
  public long getWorldId() {
    return worldId_;
  }

  public static final int NAME_FIELD_NUMBER = 3;
  private volatile java.lang.Object name_;
  /**
   * <code>string name = 3;</code>
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
   * <code>string name = 3;</code>
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

  public static final int PHOTOPROTOID_FIELD_NUMBER = 4;
  private int photoProtoId_;
  /**
   * <code>int32 photoProtoId = 4;</code>
   */
  public int getPhotoProtoId() {
    return photoProtoId_;
  }

  public static final int CASTLELV_FIELD_NUMBER = 5;
  private int castleLv_;
  /**
   * <code>int32 castleLv = 5;</code>
   */
  public int getCastleLv() {
    return castleLv_;
  }

  public static final int SKINTYPE_FIELD_NUMBER = 6;
  private int skinType_;
  /**
   * <code>int32 skinType = 6;</code>
   */
  public int getSkinType() {
    return skinType_;
  }

  public static final int VIPLV_FIELD_NUMBER = 7;
  private int vipLv_;
  /**
   * <code>int32 vipLv = 7;</code>
   */
  public int getVipLv() {
    return vipLv_;
  }

  public static final int AREANO_FIELD_NUMBER = 8;
  private int areaNo_;
  /**
   * <code>int32 areaNo = 8;</code>
   */
  public int getAreaNo() {
    return areaNo_;
  }

  public static final int ALLIANCESHORTNAME_FIELD_NUMBER = 9;
  private volatile java.lang.Object allianceShortName_;
  /**
   * <code>string allianceShortName = 9;</code>
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
   * <code>string allianceShortName = 9;</code>
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

  public static final int TYPE_FIELD_NUMBER = 10;
  private int type_;
  /**
   * <code>int32 type = 10;</code>
   */
  public int getType() {
    return type_;
  }

  public static final int ALLIANCENICKNAME_FIELD_NUMBER = 11;
  private volatile java.lang.Object allianceNickname_;
  /**
   * <code>string allianceNickname = 11;</code>
   */
  public java.lang.String getAllianceNickname() {
    java.lang.Object ref = allianceNickname_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      allianceNickname_ = s;
      return s;
    }
  }
  /**
   * <code>string allianceNickname = 11;</code>
   */
  public com.google.protobuf.ByteString
      getAllianceNicknameBytes() {
    java.lang.Object ref = allianceNickname_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      allianceNickname_ = b;
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
    if (worldId_ != 0L) {
      output.writeInt64(2, worldId_);
    }
    if (!getNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, name_);
    }
    if (photoProtoId_ != 0) {
      output.writeInt32(4, photoProtoId_);
    }
    if (castleLv_ != 0) {
      output.writeInt32(5, castleLv_);
    }
    if (skinType_ != 0) {
      output.writeInt32(6, skinType_);
    }
    if (vipLv_ != 0) {
      output.writeInt32(7, vipLv_);
    }
    if (areaNo_ != 0) {
      output.writeInt32(8, areaNo_);
    }
    if (!getAllianceShortNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 9, allianceShortName_);
    }
    if (type_ != 0) {
      output.writeInt32(10, type_);
    }
    if (!getAllianceNicknameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 11, allianceNickname_);
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
    if (worldId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, worldId_);
    }
    if (!getNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, name_);
    }
    if (photoProtoId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, photoProtoId_);
    }
    if (castleLv_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, castleLv_);
    }
    if (skinType_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, skinType_);
    }
    if (vipLv_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(7, vipLv_);
    }
    if (areaNo_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(8, areaNo_);
    }
    if (!getAllianceShortNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(9, allianceShortName_);
    }
    if (type_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(10, type_);
    }
    if (!getAllianceNicknameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(11, allianceNickname_);
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
    if (!(obj instanceof pb4server.FriendAcceptAskReq)) {
      return super.equals(obj);
    }
    pb4server.FriendAcceptAskReq other = (pb4server.FriendAcceptAskReq) obj;

    boolean result = true;
    result = result && (getMyPlayerId()
        == other.getMyPlayerId());
    result = result && (getWorldId()
        == other.getWorldId());
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
    result = result && (getType()
        == other.getType());
    result = result && getAllianceNickname()
        .equals(other.getAllianceNickname());
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
    hash = (37 * hash) + WORLDID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getWorldId());
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
    hash = (37 * hash) + TYPE_FIELD_NUMBER;
    hash = (53 * hash) + getType();
    hash = (37 * hash) + ALLIANCENICKNAME_FIELD_NUMBER;
    hash = (53 * hash) + getAllianceNickname().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.FriendAcceptAskReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.FriendAcceptAskReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.FriendAcceptAskReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.FriendAcceptAskReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.FriendAcceptAskReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.FriendAcceptAskReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.FriendAcceptAskReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.FriendAcceptAskReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.FriendAcceptAskReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.FriendAcceptAskReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.FriendAcceptAskReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.FriendAcceptAskReq parseFrom(
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
  public static Builder newBuilder(pb4server.FriendAcceptAskReq prototype) {
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
   * 接受好友请求
   * </pre>
   *
   * Protobuf type {@code pb4server.FriendAcceptAskReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.FriendAcceptAskReq)
      pb4server.FriendAcceptAskReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalHkt.internal_static_pb4server_FriendAcceptAskReq_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalHkt.internal_static_pb4server_FriendAcceptAskReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.FriendAcceptAskReq.class, pb4server.FriendAcceptAskReq.Builder.class);
    }

    // Construct using pb4server.FriendAcceptAskReq.newBuilder()
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

      worldId_ = 0L;

      name_ = "";

      photoProtoId_ = 0;

      castleLv_ = 0;

      skinType_ = 0;

      vipLv_ = 0;

      areaNo_ = 0;

      allianceShortName_ = "";

      type_ = 0;

      allianceNickname_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalHkt.internal_static_pb4server_FriendAcceptAskReq_descriptor;
    }

    public pb4server.FriendAcceptAskReq getDefaultInstanceForType() {
      return pb4server.FriendAcceptAskReq.getDefaultInstance();
    }

    public pb4server.FriendAcceptAskReq build() {
      pb4server.FriendAcceptAskReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.FriendAcceptAskReq buildPartial() {
      pb4server.FriendAcceptAskReq result = new pb4server.FriendAcceptAskReq(this);
      result.myPlayerId_ = myPlayerId_;
      result.worldId_ = worldId_;
      result.name_ = name_;
      result.photoProtoId_ = photoProtoId_;
      result.castleLv_ = castleLv_;
      result.skinType_ = skinType_;
      result.vipLv_ = vipLv_;
      result.areaNo_ = areaNo_;
      result.allianceShortName_ = allianceShortName_;
      result.type_ = type_;
      result.allianceNickname_ = allianceNickname_;
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
      if (other instanceof pb4server.FriendAcceptAskReq) {
        return mergeFrom((pb4server.FriendAcceptAskReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.FriendAcceptAskReq other) {
      if (other == pb4server.FriendAcceptAskReq.getDefaultInstance()) return this;
      if (other.getMyPlayerId() != 0L) {
        setMyPlayerId(other.getMyPlayerId());
      }
      if (other.getWorldId() != 0L) {
        setWorldId(other.getWorldId());
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
      if (other.getType() != 0) {
        setType(other.getType());
      }
      if (!other.getAllianceNickname().isEmpty()) {
        allianceNickname_ = other.allianceNickname_;
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
      pb4server.FriendAcceptAskReq parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.FriendAcceptAskReq) e.getUnfinishedMessage();
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

    private long worldId_ ;
    /**
     * <code>int64 worldId = 2;</code>
     */
    public long getWorldId() {
      return worldId_;
    }
    /**
     * <code>int64 worldId = 2;</code>
     */
    public Builder setWorldId(long value) {
      
      worldId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 worldId = 2;</code>
     */
    public Builder clearWorldId() {
      
      worldId_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object name_ = "";
    /**
     * <code>string name = 3;</code>
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
     * <code>string name = 3;</code>
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
     * <code>string name = 3;</code>
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
     * <code>string name = 3;</code>
     */
    public Builder clearName() {
      
      name_ = getDefaultInstance().getName();
      onChanged();
      return this;
    }
    /**
     * <code>string name = 3;</code>
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
     * <code>int32 photoProtoId = 4;</code>
     */
    public int getPhotoProtoId() {
      return photoProtoId_;
    }
    /**
     * <code>int32 photoProtoId = 4;</code>
     */
    public Builder setPhotoProtoId(int value) {
      
      photoProtoId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 photoProtoId = 4;</code>
     */
    public Builder clearPhotoProtoId() {
      
      photoProtoId_ = 0;
      onChanged();
      return this;
    }

    private int castleLv_ ;
    /**
     * <code>int32 castleLv = 5;</code>
     */
    public int getCastleLv() {
      return castleLv_;
    }
    /**
     * <code>int32 castleLv = 5;</code>
     */
    public Builder setCastleLv(int value) {
      
      castleLv_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 castleLv = 5;</code>
     */
    public Builder clearCastleLv() {
      
      castleLv_ = 0;
      onChanged();
      return this;
    }

    private int skinType_ ;
    /**
     * <code>int32 skinType = 6;</code>
     */
    public int getSkinType() {
      return skinType_;
    }
    /**
     * <code>int32 skinType = 6;</code>
     */
    public Builder setSkinType(int value) {
      
      skinType_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 skinType = 6;</code>
     */
    public Builder clearSkinType() {
      
      skinType_ = 0;
      onChanged();
      return this;
    }

    private int vipLv_ ;
    /**
     * <code>int32 vipLv = 7;</code>
     */
    public int getVipLv() {
      return vipLv_;
    }
    /**
     * <code>int32 vipLv = 7;</code>
     */
    public Builder setVipLv(int value) {
      
      vipLv_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 vipLv = 7;</code>
     */
    public Builder clearVipLv() {
      
      vipLv_ = 0;
      onChanged();
      return this;
    }

    private int areaNo_ ;
    /**
     * <code>int32 areaNo = 8;</code>
     */
    public int getAreaNo() {
      return areaNo_;
    }
    /**
     * <code>int32 areaNo = 8;</code>
     */
    public Builder setAreaNo(int value) {
      
      areaNo_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 areaNo = 8;</code>
     */
    public Builder clearAreaNo() {
      
      areaNo_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object allianceShortName_ = "";
    /**
     * <code>string allianceShortName = 9;</code>
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
     * <code>string allianceShortName = 9;</code>
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
     * <code>string allianceShortName = 9;</code>
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
     * <code>string allianceShortName = 9;</code>
     */
    public Builder clearAllianceShortName() {
      
      allianceShortName_ = getDefaultInstance().getAllianceShortName();
      onChanged();
      return this;
    }
    /**
     * <code>string allianceShortName = 9;</code>
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

    private int type_ ;
    /**
     * <code>int32 type = 10;</code>
     */
    public int getType() {
      return type_;
    }
    /**
     * <code>int32 type = 10;</code>
     */
    public Builder setType(int value) {
      
      type_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 type = 10;</code>
     */
    public Builder clearType() {
      
      type_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object allianceNickname_ = "";
    /**
     * <code>string allianceNickname = 11;</code>
     */
    public java.lang.String getAllianceNickname() {
      java.lang.Object ref = allianceNickname_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        allianceNickname_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string allianceNickname = 11;</code>
     */
    public com.google.protobuf.ByteString
        getAllianceNicknameBytes() {
      java.lang.Object ref = allianceNickname_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        allianceNickname_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string allianceNickname = 11;</code>
     */
    public Builder setAllianceNickname(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      allianceNickname_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string allianceNickname = 11;</code>
     */
    public Builder clearAllianceNickname() {
      
      allianceNickname_ = getDefaultInstance().getAllianceNickname();
      onChanged();
      return this;
    }
    /**
     * <code>string allianceNickname = 11;</code>
     */
    public Builder setAllianceNicknameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      allianceNickname_ = value;
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


    // @@protoc_insertion_point(builder_scope:pb4server.FriendAcceptAskReq)
  }

  // @@protoc_insertion_point(class_scope:pb4server.FriendAcceptAskReq)
  private static final pb4server.FriendAcceptAskReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.FriendAcceptAskReq();
  }

  public static pb4server.FriendAcceptAskReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<FriendAcceptAskReq>
      PARSER = new com.google.protobuf.AbstractParser<FriendAcceptAskReq>() {
    public FriendAcceptAskReq parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new FriendAcceptAskReq(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<FriendAcceptAskReq> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<FriendAcceptAskReq> getParserForType() {
    return PARSER;
  }

  public pb4server.FriendAcceptAskReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
