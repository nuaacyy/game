// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 *显示内容：
 *1. 自己的主城、分城、要塞、军营、码头
 *2. 同盟成员的主城、码头
 *3. 同盟下级成员的主城、码头
 *4. 其他玩家的码头
 *5. 同盟所属的NPC城池、关卡
 *6. 非同盟所属的NPC城池、关卡
 * </pre>
 *
 * Protobuf type {@code client2server.ShowMapInfo}
 */
public  final class ShowMapInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.ShowMapInfo)
    ShowMapInfoOrBuilder {
  // Use ShowMapInfo.newBuilder() to construct.
  private ShowMapInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ShowMapInfo() {
    x_ = 0;
    y_ = 0;
    type_ = 0;
    relation_ = 0;
    alceName_ = "";
    alceShortName_ = "";
    taiShouName_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ShowMapInfo(
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
            x_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            y_ = input.readInt32();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            type_ = input.readInt32();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            relation_ = input.readInt32();
            break;
          }
          case 42: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000010;
            alceName_ = bs;
            break;
          }
          case 50: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000020;
            alceShortName_ = bs;
            break;
          }
          case 58: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000040;
            taiShouName_ = bs;
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
    return pb4client.War2GamePkt.internal_static_client2server_ShowMapInfo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_ShowMapInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.ShowMapInfo.class, pb4client.ShowMapInfo.Builder.class);
  }

  private int bitField0_;
  public static final int X_FIELD_NUMBER = 1;
  private int x_;
  /**
   * <pre>
   *坐标X
   * </pre>
   *
   * <code>required int32 x = 1;</code>
   */
  public boolean hasX() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *坐标X
   * </pre>
   *
   * <code>required int32 x = 1;</code>
   */
  public int getX() {
    return x_;
  }

  public static final int Y_FIELD_NUMBER = 2;
  private int y_;
  /**
   * <pre>
   *坐标Y
   * </pre>
   *
   * <code>required int32 y = 2;</code>
   */
  public boolean hasY() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *坐标Y
   * </pre>
   *
   * <code>required int32 y = 2;</code>
   */
  public int getY() {
    return y_;
  }

  public static final int TYPE_FIELD_NUMBER = 3;
  private int type_;
  /**
   * <pre>
   *详见LandBase中类型，但只会包括:100-主城;106-玩家要塞;107-玩家军营;201~205、250-NPC城池;301-野外要塞;302-野外军营;303-关卡;304-码头
   * </pre>
   *
   * <code>required int32 type = 3;</code>
   */
  public boolean hasType() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   *详见LandBase中类型，但只会包括:100-主城;106-玩家要塞;107-玩家军营;201~205、250-NPC城池;301-野外要塞;302-野外军营;303-关卡;304-码头
   * </pre>
   *
   * <code>required int32 type = 3;</code>
   */
  public int getType() {
    return type_;
  }

  public static final int RELATION_FIELD_NUMBER = 4;
  private int relation_;
  /**
   * <pre>
   *关系:0-自己;1-同盟成员;2-同盟下级成员;3-其他玩家;4-同盟所属;5-非同盟联盟
   * </pre>
   *
   * <code>required int32 relation = 4;</code>
   */
  public boolean hasRelation() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   *关系:0-自己;1-同盟成员;2-同盟下级成员;3-其他玩家;4-同盟所属;5-非同盟联盟
   * </pre>
   *
   * <code>required int32 relation = 4;</code>
   */
  public int getRelation() {
    return relation_;
  }

  public static final int ALCENAME_FIELD_NUMBER = 5;
  private volatile java.lang.Object alceName_;
  /**
   * <pre>
   *如果是NPC城池或关卡，并且被占领，则会返回占领联盟的名称
   * </pre>
   *
   * <code>optional string alceName = 5;</code>
   */
  public boolean hasAlceName() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <pre>
   *如果是NPC城池或关卡，并且被占领，则会返回占领联盟的名称
   * </pre>
   *
   * <code>optional string alceName = 5;</code>
   */
  public java.lang.String getAlceName() {
    java.lang.Object ref = alceName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        alceName_ = s;
      }
      return s;
    }
  }
  /**
   * <pre>
   *如果是NPC城池或关卡，并且被占领，则会返回占领联盟的名称
   * </pre>
   *
   * <code>optional string alceName = 5;</code>
   */
  public com.google.protobuf.ByteString
      getAlceNameBytes() {
    java.lang.Object ref = alceName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      alceName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int ALCESHORTNAME_FIELD_NUMBER = 6;
  private volatile java.lang.Object alceShortName_;
  /**
   * <pre>
   *如果是NPC城池或关卡，并且被占领，则会返回占领联盟的简称
   * </pre>
   *
   * <code>optional string alceShortName = 6;</code>
   */
  public boolean hasAlceShortName() {
    return ((bitField0_ & 0x00000020) == 0x00000020);
  }
  /**
   * <pre>
   *如果是NPC城池或关卡，并且被占领，则会返回占领联盟的简称
   * </pre>
   *
   * <code>optional string alceShortName = 6;</code>
   */
  public java.lang.String getAlceShortName() {
    java.lang.Object ref = alceShortName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        alceShortName_ = s;
      }
      return s;
    }
  }
  /**
   * <pre>
   *如果是NPC城池或关卡，并且被占领，则会返回占领联盟的简称
   * </pre>
   *
   * <code>optional string alceShortName = 6;</code>
   */
  public com.google.protobuf.ByteString
      getAlceShortNameBytes() {
    java.lang.Object ref = alceShortName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      alceShortName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int TAISHOUNAME_FIELD_NUMBER = 7;
  private volatile java.lang.Object taiShouName_;
  /**
   * <pre>
   *如果是NPC城池或关卡，被占领城主，并且任命城主，则会返回被任命的玩家名称
   * </pre>
   *
   * <code>optional string taiShouName = 7;</code>
   */
  public boolean hasTaiShouName() {
    return ((bitField0_ & 0x00000040) == 0x00000040);
  }
  /**
   * <pre>
   *如果是NPC城池或关卡，被占领城主，并且任命城主，则会返回被任命的玩家名称
   * </pre>
   *
   * <code>optional string taiShouName = 7;</code>
   */
  public java.lang.String getTaiShouName() {
    java.lang.Object ref = taiShouName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        taiShouName_ = s;
      }
      return s;
    }
  }
  /**
   * <pre>
   *如果是NPC城池或关卡，被占领城主，并且任命城主，则会返回被任命的玩家名称
   * </pre>
   *
   * <code>optional string taiShouName = 7;</code>
   */
  public com.google.protobuf.ByteString
      getTaiShouNameBytes() {
    java.lang.Object ref = taiShouName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      taiShouName_ = b;
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

    if (!hasX()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasY()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasType()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasRelation()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, x_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, y_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, type_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt32(4, relation_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, alceName_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 6, alceShortName_);
    }
    if (((bitField0_ & 0x00000040) == 0x00000040)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 7, taiShouName_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, x_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, y_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, type_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, relation_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, alceName_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(6, alceShortName_);
    }
    if (((bitField0_ & 0x00000040) == 0x00000040)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(7, taiShouName_);
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
    if (!(obj instanceof pb4client.ShowMapInfo)) {
      return super.equals(obj);
    }
    pb4client.ShowMapInfo other = (pb4client.ShowMapInfo) obj;

    boolean result = true;
    result = result && (hasX() == other.hasX());
    if (hasX()) {
      result = result && (getX()
          == other.getX());
    }
    result = result && (hasY() == other.hasY());
    if (hasY()) {
      result = result && (getY()
          == other.getY());
    }
    result = result && (hasType() == other.hasType());
    if (hasType()) {
      result = result && (getType()
          == other.getType());
    }
    result = result && (hasRelation() == other.hasRelation());
    if (hasRelation()) {
      result = result && (getRelation()
          == other.getRelation());
    }
    result = result && (hasAlceName() == other.hasAlceName());
    if (hasAlceName()) {
      result = result && getAlceName()
          .equals(other.getAlceName());
    }
    result = result && (hasAlceShortName() == other.hasAlceShortName());
    if (hasAlceShortName()) {
      result = result && getAlceShortName()
          .equals(other.getAlceShortName());
    }
    result = result && (hasTaiShouName() == other.hasTaiShouName());
    if (hasTaiShouName()) {
      result = result && getTaiShouName()
          .equals(other.getTaiShouName());
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
    if (hasX()) {
      hash = (37 * hash) + X_FIELD_NUMBER;
      hash = (53 * hash) + getX();
    }
    if (hasY()) {
      hash = (37 * hash) + Y_FIELD_NUMBER;
      hash = (53 * hash) + getY();
    }
    if (hasType()) {
      hash = (37 * hash) + TYPE_FIELD_NUMBER;
      hash = (53 * hash) + getType();
    }
    if (hasRelation()) {
      hash = (37 * hash) + RELATION_FIELD_NUMBER;
      hash = (53 * hash) + getRelation();
    }
    if (hasAlceName()) {
      hash = (37 * hash) + ALCENAME_FIELD_NUMBER;
      hash = (53 * hash) + getAlceName().hashCode();
    }
    if (hasAlceShortName()) {
      hash = (37 * hash) + ALCESHORTNAME_FIELD_NUMBER;
      hash = (53 * hash) + getAlceShortName().hashCode();
    }
    if (hasTaiShouName()) {
      hash = (37 * hash) + TAISHOUNAME_FIELD_NUMBER;
      hash = (53 * hash) + getTaiShouName().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.ShowMapInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.ShowMapInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.ShowMapInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.ShowMapInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.ShowMapInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.ShowMapInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.ShowMapInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.ShowMapInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.ShowMapInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.ShowMapInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.ShowMapInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.ShowMapInfo parseFrom(
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
  public static Builder newBuilder(pb4client.ShowMapInfo prototype) {
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
   *显示内容：
   *1. 自己的主城、分城、要塞、军营、码头
   *2. 同盟成员的主城、码头
   *3. 同盟下级成员的主城、码头
   *4. 其他玩家的码头
   *5. 同盟所属的NPC城池、关卡
   *6. 非同盟所属的NPC城池、关卡
   * </pre>
   *
   * Protobuf type {@code client2server.ShowMapInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.ShowMapInfo)
      pb4client.ShowMapInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_ShowMapInfo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_ShowMapInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.ShowMapInfo.class, pb4client.ShowMapInfo.Builder.class);
    }

    // Construct using pb4client.ShowMapInfo.newBuilder()
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
      x_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      y_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      type_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      relation_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      alceName_ = "";
      bitField0_ = (bitField0_ & ~0x00000010);
      alceShortName_ = "";
      bitField0_ = (bitField0_ & ~0x00000020);
      taiShouName_ = "";
      bitField0_ = (bitField0_ & ~0x00000040);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_ShowMapInfo_descriptor;
    }

    public pb4client.ShowMapInfo getDefaultInstanceForType() {
      return pb4client.ShowMapInfo.getDefaultInstance();
    }

    public pb4client.ShowMapInfo build() {
      pb4client.ShowMapInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.ShowMapInfo buildPartial() {
      pb4client.ShowMapInfo result = new pb4client.ShowMapInfo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.x_ = x_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.y_ = y_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.type_ = type_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.relation_ = relation_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.alceName_ = alceName_;
      if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
        to_bitField0_ |= 0x00000020;
      }
      result.alceShortName_ = alceShortName_;
      if (((from_bitField0_ & 0x00000040) == 0x00000040)) {
        to_bitField0_ |= 0x00000040;
      }
      result.taiShouName_ = taiShouName_;
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
      if (other instanceof pb4client.ShowMapInfo) {
        return mergeFrom((pb4client.ShowMapInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.ShowMapInfo other) {
      if (other == pb4client.ShowMapInfo.getDefaultInstance()) return this;
      if (other.hasX()) {
        setX(other.getX());
      }
      if (other.hasY()) {
        setY(other.getY());
      }
      if (other.hasType()) {
        setType(other.getType());
      }
      if (other.hasRelation()) {
        setRelation(other.getRelation());
      }
      if (other.hasAlceName()) {
        bitField0_ |= 0x00000010;
        alceName_ = other.alceName_;
        onChanged();
      }
      if (other.hasAlceShortName()) {
        bitField0_ |= 0x00000020;
        alceShortName_ = other.alceShortName_;
        onChanged();
      }
      if (other.hasTaiShouName()) {
        bitField0_ |= 0x00000040;
        taiShouName_ = other.taiShouName_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasX()) {
        return false;
      }
      if (!hasY()) {
        return false;
      }
      if (!hasType()) {
        return false;
      }
      if (!hasRelation()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.ShowMapInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.ShowMapInfo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int x_ ;
    /**
     * <pre>
     *坐标X
     * </pre>
     *
     * <code>required int32 x = 1;</code>
     */
    public boolean hasX() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *坐标X
     * </pre>
     *
     * <code>required int32 x = 1;</code>
     */
    public int getX() {
      return x_;
    }
    /**
     * <pre>
     *坐标X
     * </pre>
     *
     * <code>required int32 x = 1;</code>
     */
    public Builder setX(int value) {
      bitField0_ |= 0x00000001;
      x_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *坐标X
     * </pre>
     *
     * <code>required int32 x = 1;</code>
     */
    public Builder clearX() {
      bitField0_ = (bitField0_ & ~0x00000001);
      x_ = 0;
      onChanged();
      return this;
    }

    private int y_ ;
    /**
     * <pre>
     *坐标Y
     * </pre>
     *
     * <code>required int32 y = 2;</code>
     */
    public boolean hasY() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *坐标Y
     * </pre>
     *
     * <code>required int32 y = 2;</code>
     */
    public int getY() {
      return y_;
    }
    /**
     * <pre>
     *坐标Y
     * </pre>
     *
     * <code>required int32 y = 2;</code>
     */
    public Builder setY(int value) {
      bitField0_ |= 0x00000002;
      y_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *坐标Y
     * </pre>
     *
     * <code>required int32 y = 2;</code>
     */
    public Builder clearY() {
      bitField0_ = (bitField0_ & ~0x00000002);
      y_ = 0;
      onChanged();
      return this;
    }

    private int type_ ;
    /**
     * <pre>
     *详见LandBase中类型，但只会包括:100-主城;106-玩家要塞;107-玩家军营;201~205、250-NPC城池;301-野外要塞;302-野外军营;303-关卡;304-码头
     * </pre>
     *
     * <code>required int32 type = 3;</code>
     */
    public boolean hasType() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     *详见LandBase中类型，但只会包括:100-主城;106-玩家要塞;107-玩家军营;201~205、250-NPC城池;301-野外要塞;302-野外军营;303-关卡;304-码头
     * </pre>
     *
     * <code>required int32 type = 3;</code>
     */
    public int getType() {
      return type_;
    }
    /**
     * <pre>
     *详见LandBase中类型，但只会包括:100-主城;106-玩家要塞;107-玩家军营;201~205、250-NPC城池;301-野外要塞;302-野外军营;303-关卡;304-码头
     * </pre>
     *
     * <code>required int32 type = 3;</code>
     */
    public Builder setType(int value) {
      bitField0_ |= 0x00000004;
      type_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *详见LandBase中类型，但只会包括:100-主城;106-玩家要塞;107-玩家军营;201~205、250-NPC城池;301-野外要塞;302-野外军营;303-关卡;304-码头
     * </pre>
     *
     * <code>required int32 type = 3;</code>
     */
    public Builder clearType() {
      bitField0_ = (bitField0_ & ~0x00000004);
      type_ = 0;
      onChanged();
      return this;
    }

    private int relation_ ;
    /**
     * <pre>
     *关系:0-自己;1-同盟成员;2-同盟下级成员;3-其他玩家;4-同盟所属;5-非同盟联盟
     * </pre>
     *
     * <code>required int32 relation = 4;</code>
     */
    public boolean hasRelation() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     *关系:0-自己;1-同盟成员;2-同盟下级成员;3-其他玩家;4-同盟所属;5-非同盟联盟
     * </pre>
     *
     * <code>required int32 relation = 4;</code>
     */
    public int getRelation() {
      return relation_;
    }
    /**
     * <pre>
     *关系:0-自己;1-同盟成员;2-同盟下级成员;3-其他玩家;4-同盟所属;5-非同盟联盟
     * </pre>
     *
     * <code>required int32 relation = 4;</code>
     */
    public Builder setRelation(int value) {
      bitField0_ |= 0x00000008;
      relation_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *关系:0-自己;1-同盟成员;2-同盟下级成员;3-其他玩家;4-同盟所属;5-非同盟联盟
     * </pre>
     *
     * <code>required int32 relation = 4;</code>
     */
    public Builder clearRelation() {
      bitField0_ = (bitField0_ & ~0x00000008);
      relation_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object alceName_ = "";
    /**
     * <pre>
     *如果是NPC城池或关卡，并且被占领，则会返回占领联盟的名称
     * </pre>
     *
     * <code>optional string alceName = 5;</code>
     */
    public boolean hasAlceName() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <pre>
     *如果是NPC城池或关卡，并且被占领，则会返回占领联盟的名称
     * </pre>
     *
     * <code>optional string alceName = 5;</code>
     */
    public java.lang.String getAlceName() {
      java.lang.Object ref = alceName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          alceName_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *如果是NPC城池或关卡，并且被占领，则会返回占领联盟的名称
     * </pre>
     *
     * <code>optional string alceName = 5;</code>
     */
    public com.google.protobuf.ByteString
        getAlceNameBytes() {
      java.lang.Object ref = alceName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        alceName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *如果是NPC城池或关卡，并且被占领，则会返回占领联盟的名称
     * </pre>
     *
     * <code>optional string alceName = 5;</code>
     */
    public Builder setAlceName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000010;
      alceName_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *如果是NPC城池或关卡，并且被占领，则会返回占领联盟的名称
     * </pre>
     *
     * <code>optional string alceName = 5;</code>
     */
    public Builder clearAlceName() {
      bitField0_ = (bitField0_ & ~0x00000010);
      alceName_ = getDefaultInstance().getAlceName();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *如果是NPC城池或关卡，并且被占领，则会返回占领联盟的名称
     * </pre>
     *
     * <code>optional string alceName = 5;</code>
     */
    public Builder setAlceNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000010;
      alceName_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object alceShortName_ = "";
    /**
     * <pre>
     *如果是NPC城池或关卡，并且被占领，则会返回占领联盟的简称
     * </pre>
     *
     * <code>optional string alceShortName = 6;</code>
     */
    public boolean hasAlceShortName() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    /**
     * <pre>
     *如果是NPC城池或关卡，并且被占领，则会返回占领联盟的简称
     * </pre>
     *
     * <code>optional string alceShortName = 6;</code>
     */
    public java.lang.String getAlceShortName() {
      java.lang.Object ref = alceShortName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          alceShortName_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *如果是NPC城池或关卡，并且被占领，则会返回占领联盟的简称
     * </pre>
     *
     * <code>optional string alceShortName = 6;</code>
     */
    public com.google.protobuf.ByteString
        getAlceShortNameBytes() {
      java.lang.Object ref = alceShortName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        alceShortName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *如果是NPC城池或关卡，并且被占领，则会返回占领联盟的简称
     * </pre>
     *
     * <code>optional string alceShortName = 6;</code>
     */
    public Builder setAlceShortName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000020;
      alceShortName_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *如果是NPC城池或关卡，并且被占领，则会返回占领联盟的简称
     * </pre>
     *
     * <code>optional string alceShortName = 6;</code>
     */
    public Builder clearAlceShortName() {
      bitField0_ = (bitField0_ & ~0x00000020);
      alceShortName_ = getDefaultInstance().getAlceShortName();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *如果是NPC城池或关卡，并且被占领，则会返回占领联盟的简称
     * </pre>
     *
     * <code>optional string alceShortName = 6;</code>
     */
    public Builder setAlceShortNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000020;
      alceShortName_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object taiShouName_ = "";
    /**
     * <pre>
     *如果是NPC城池或关卡，被占领城主，并且任命城主，则会返回被任命的玩家名称
     * </pre>
     *
     * <code>optional string taiShouName = 7;</code>
     */
    public boolean hasTaiShouName() {
      return ((bitField0_ & 0x00000040) == 0x00000040);
    }
    /**
     * <pre>
     *如果是NPC城池或关卡，被占领城主，并且任命城主，则会返回被任命的玩家名称
     * </pre>
     *
     * <code>optional string taiShouName = 7;</code>
     */
    public java.lang.String getTaiShouName() {
      java.lang.Object ref = taiShouName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          taiShouName_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *如果是NPC城池或关卡，被占领城主，并且任命城主，则会返回被任命的玩家名称
     * </pre>
     *
     * <code>optional string taiShouName = 7;</code>
     */
    public com.google.protobuf.ByteString
        getTaiShouNameBytes() {
      java.lang.Object ref = taiShouName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        taiShouName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *如果是NPC城池或关卡，被占领城主，并且任命城主，则会返回被任命的玩家名称
     * </pre>
     *
     * <code>optional string taiShouName = 7;</code>
     */
    public Builder setTaiShouName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000040;
      taiShouName_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *如果是NPC城池或关卡，被占领城主，并且任命城主，则会返回被任命的玩家名称
     * </pre>
     *
     * <code>optional string taiShouName = 7;</code>
     */
    public Builder clearTaiShouName() {
      bitField0_ = (bitField0_ & ~0x00000040);
      taiShouName_ = getDefaultInstance().getTaiShouName();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *如果是NPC城池或关卡，被占领城主，并且任命城主，则会返回被任命的玩家名称
     * </pre>
     *
     * <code>optional string taiShouName = 7;</code>
     */
    public Builder setTaiShouNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000040;
      taiShouName_ = value;
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


    // @@protoc_insertion_point(builder_scope:client2server.ShowMapInfo)
  }

  // @@protoc_insertion_point(class_scope:client2server.ShowMapInfo)
  private static final pb4client.ShowMapInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.ShowMapInfo();
  }

  public static pb4client.ShowMapInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<ShowMapInfo>
      PARSER = new com.google.protobuf.AbstractParser<ShowMapInfo>() {
    public ShowMapInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new ShowMapInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ShowMapInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ShowMapInfo> getParserForType() {
    return PARSER;
  }

  public pb4client.ShowMapInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

