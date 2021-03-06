// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.MarkInfo}
 */
public  final class MarkInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.MarkInfo)
    MarkInfoOrBuilder {
  // Use MarkInfo.newBuilder() to construct.
  private MarkInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private MarkInfo() {
    landX_ = 0;
    landY_ = 0;
    areaNo_ = 0;
    group_ = 0;
    name_ = "";
    id_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private MarkInfo(
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
            landX_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            landY_ = input.readInt32();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            areaNo_ = input.readInt32();
            break;
          }
          case 32: {
            bitField0_ |= 0x00000008;
            group_ = input.readInt32();
            break;
          }
          case 42: {
            com.google.protobuf.ByteString bs = input.readBytes();
            bitField0_ |= 0x00000010;
            name_ = bs;
            break;
          }
          case 48: {
            bitField0_ |= 0x00000020;
            id_ = input.readInt64();
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
    return pb4client.War2GamePkt.internal_static_client2server_MarkInfo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_MarkInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.MarkInfo.class, pb4client.MarkInfo.Builder.class);
  }

  private int bitField0_;
  public static final int LANDX_FIELD_NUMBER = 1;
  private int landX_;
  /**
   * <code>required int32 landX = 1;</code>
   */
  public boolean hasLandX() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>required int32 landX = 1;</code>
   */
  public int getLandX() {
    return landX_;
  }

  public static final int LANDY_FIELD_NUMBER = 2;
  private int landY_;
  /**
   * <code>required int32 landY = 2;</code>
   */
  public boolean hasLandY() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>required int32 landY = 2;</code>
   */
  public int getLandY() {
    return landY_;
  }

  public static final int AREANO_FIELD_NUMBER = 3;
  private int areaNo_;
  /**
   * <pre>
   * 服务区ID
   * </pre>
   *
   * <code>required int32 areaNo = 3;</code>
   */
  public boolean hasAreaNo() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <pre>
   * 服务区ID
   * </pre>
   *
   * <code>required int32 areaNo = 3;</code>
   */
  public int getAreaNo() {
    return areaNo_;
  }

  public static final int GROUP_FIELD_NUMBER = 4;
  private int group_;
  /**
   * <pre>
   * 分组类型
   * </pre>
   *
   * <code>required int32 group = 4;</code>
   */
  public boolean hasGroup() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <pre>
   * 分组类型
   * </pre>
   *
   * <code>required int32 group = 4;</code>
   */
  public int getGroup() {
    return group_;
  }

  public static final int NAME_FIELD_NUMBER = 5;
  private volatile java.lang.Object name_;
  /**
   * <pre>
   * 名字
   * </pre>
   *
   * <code>required string name = 5;</code>
   */
  public boolean hasName() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <pre>
   * 名字
   * </pre>
   *
   * <code>required string name = 5;</code>
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
   * 名字
   * </pre>
   *
   * <code>required string name = 5;</code>
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

  public static final int ID_FIELD_NUMBER = 6;
  private long id_;
  /**
   * <pre>
   * 标记点唯一ID
   * </pre>
   *
   * <code>required int64 id = 6;</code>
   */
  public boolean hasId() {
    return ((bitField0_ & 0x00000020) == 0x00000020);
  }
  /**
   * <pre>
   * 标记点唯一ID
   * </pre>
   *
   * <code>required int64 id = 6;</code>
   */
  public long getId() {
    return id_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasLandX()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasLandY()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasAreaNo()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasGroup()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasName()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, landX_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, landY_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, areaNo_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeInt32(4, group_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, name_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      output.writeInt64(6, id_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, landX_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, landY_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, areaNo_);
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, group_);
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, name_);
    }
    if (((bitField0_ & 0x00000020) == 0x00000020)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(6, id_);
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
    if (!(obj instanceof pb4client.MarkInfo)) {
      return super.equals(obj);
    }
    pb4client.MarkInfo other = (pb4client.MarkInfo) obj;

    boolean result = true;
    result = result && (hasLandX() == other.hasLandX());
    if (hasLandX()) {
      result = result && (getLandX()
          == other.getLandX());
    }
    result = result && (hasLandY() == other.hasLandY());
    if (hasLandY()) {
      result = result && (getLandY()
          == other.getLandY());
    }
    result = result && (hasAreaNo() == other.hasAreaNo());
    if (hasAreaNo()) {
      result = result && (getAreaNo()
          == other.getAreaNo());
    }
    result = result && (hasGroup() == other.hasGroup());
    if (hasGroup()) {
      result = result && (getGroup()
          == other.getGroup());
    }
    result = result && (hasName() == other.hasName());
    if (hasName()) {
      result = result && getName()
          .equals(other.getName());
    }
    result = result && (hasId() == other.hasId());
    if (hasId()) {
      result = result && (getId()
          == other.getId());
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
    if (hasLandX()) {
      hash = (37 * hash) + LANDX_FIELD_NUMBER;
      hash = (53 * hash) + getLandX();
    }
    if (hasLandY()) {
      hash = (37 * hash) + LANDY_FIELD_NUMBER;
      hash = (53 * hash) + getLandY();
    }
    if (hasAreaNo()) {
      hash = (37 * hash) + AREANO_FIELD_NUMBER;
      hash = (53 * hash) + getAreaNo();
    }
    if (hasGroup()) {
      hash = (37 * hash) + GROUP_FIELD_NUMBER;
      hash = (53 * hash) + getGroup();
    }
    if (hasName()) {
      hash = (37 * hash) + NAME_FIELD_NUMBER;
      hash = (53 * hash) + getName().hashCode();
    }
    if (hasId()) {
      hash = (37 * hash) + ID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getId());
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.MarkInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.MarkInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.MarkInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.MarkInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.MarkInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.MarkInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.MarkInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.MarkInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.MarkInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.MarkInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.MarkInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.MarkInfo parseFrom(
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
  public static Builder newBuilder(pb4client.MarkInfo prototype) {
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
   * Protobuf type {@code client2server.MarkInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.MarkInfo)
      pb4client.MarkInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_MarkInfo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_MarkInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.MarkInfo.class, pb4client.MarkInfo.Builder.class);
    }

    // Construct using pb4client.MarkInfo.newBuilder()
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
      landX_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      landY_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      areaNo_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      group_ = 0;
      bitField0_ = (bitField0_ & ~0x00000008);
      name_ = "";
      bitField0_ = (bitField0_ & ~0x00000010);
      id_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000020);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_MarkInfo_descriptor;
    }

    public pb4client.MarkInfo getDefaultInstanceForType() {
      return pb4client.MarkInfo.getDefaultInstance();
    }

    public pb4client.MarkInfo build() {
      pb4client.MarkInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.MarkInfo buildPartial() {
      pb4client.MarkInfo result = new pb4client.MarkInfo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.landX_ = landX_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.landY_ = landY_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.areaNo_ = areaNo_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.group_ = group_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.name_ = name_;
      if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
        to_bitField0_ |= 0x00000020;
      }
      result.id_ = id_;
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
      if (other instanceof pb4client.MarkInfo) {
        return mergeFrom((pb4client.MarkInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.MarkInfo other) {
      if (other == pb4client.MarkInfo.getDefaultInstance()) return this;
      if (other.hasLandX()) {
        setLandX(other.getLandX());
      }
      if (other.hasLandY()) {
        setLandY(other.getLandY());
      }
      if (other.hasAreaNo()) {
        setAreaNo(other.getAreaNo());
      }
      if (other.hasGroup()) {
        setGroup(other.getGroup());
      }
      if (other.hasName()) {
        bitField0_ |= 0x00000010;
        name_ = other.name_;
        onChanged();
      }
      if (other.hasId()) {
        setId(other.getId());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasLandX()) {
        return false;
      }
      if (!hasLandY()) {
        return false;
      }
      if (!hasAreaNo()) {
        return false;
      }
      if (!hasGroup()) {
        return false;
      }
      if (!hasName()) {
        return false;
      }
      if (!hasId()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.MarkInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.MarkInfo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int landX_ ;
    /**
     * <code>required int32 landX = 1;</code>
     */
    public boolean hasLandX() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 landX = 1;</code>
     */
    public int getLandX() {
      return landX_;
    }
    /**
     * <code>required int32 landX = 1;</code>
     */
    public Builder setLandX(int value) {
      bitField0_ |= 0x00000001;
      landX_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 landX = 1;</code>
     */
    public Builder clearLandX() {
      bitField0_ = (bitField0_ & ~0x00000001);
      landX_ = 0;
      onChanged();
      return this;
    }

    private int landY_ ;
    /**
     * <code>required int32 landY = 2;</code>
     */
    public boolean hasLandY() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required int32 landY = 2;</code>
     */
    public int getLandY() {
      return landY_;
    }
    /**
     * <code>required int32 landY = 2;</code>
     */
    public Builder setLandY(int value) {
      bitField0_ |= 0x00000002;
      landY_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 landY = 2;</code>
     */
    public Builder clearLandY() {
      bitField0_ = (bitField0_ & ~0x00000002);
      landY_ = 0;
      onChanged();
      return this;
    }

    private int areaNo_ ;
    /**
     * <pre>
     * 服务区ID
     * </pre>
     *
     * <code>required int32 areaNo = 3;</code>
     */
    public boolean hasAreaNo() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <pre>
     * 服务区ID
     * </pre>
     *
     * <code>required int32 areaNo = 3;</code>
     */
    public int getAreaNo() {
      return areaNo_;
    }
    /**
     * <pre>
     * 服务区ID
     * </pre>
     *
     * <code>required int32 areaNo = 3;</code>
     */
    public Builder setAreaNo(int value) {
      bitField0_ |= 0x00000004;
      areaNo_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 服务区ID
     * </pre>
     *
     * <code>required int32 areaNo = 3;</code>
     */
    public Builder clearAreaNo() {
      bitField0_ = (bitField0_ & ~0x00000004);
      areaNo_ = 0;
      onChanged();
      return this;
    }

    private int group_ ;
    /**
     * <pre>
     * 分组类型
     * </pre>
     *
     * <code>required int32 group = 4;</code>
     */
    public boolean hasGroup() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <pre>
     * 分组类型
     * </pre>
     *
     * <code>required int32 group = 4;</code>
     */
    public int getGroup() {
      return group_;
    }
    /**
     * <pre>
     * 分组类型
     * </pre>
     *
     * <code>required int32 group = 4;</code>
     */
    public Builder setGroup(int value) {
      bitField0_ |= 0x00000008;
      group_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 分组类型
     * </pre>
     *
     * <code>required int32 group = 4;</code>
     */
    public Builder clearGroup() {
      bitField0_ = (bitField0_ & ~0x00000008);
      group_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object name_ = "";
    /**
     * <pre>
     * 名字
     * </pre>
     *
     * <code>required string name = 5;</code>
     */
    public boolean hasName() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <pre>
     * 名字
     * </pre>
     *
     * <code>required string name = 5;</code>
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
     * 名字
     * </pre>
     *
     * <code>required string name = 5;</code>
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
     * 名字
     * </pre>
     *
     * <code>required string name = 5;</code>
     */
    public Builder setName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000010;
      name_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 名字
     * </pre>
     *
     * <code>required string name = 5;</code>
     */
    public Builder clearName() {
      bitField0_ = (bitField0_ & ~0x00000010);
      name_ = getDefaultInstance().getName();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 名字
     * </pre>
     *
     * <code>required string name = 5;</code>
     */
    public Builder setNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000010;
      name_ = value;
      onChanged();
      return this;
    }

    private long id_ ;
    /**
     * <pre>
     * 标记点唯一ID
     * </pre>
     *
     * <code>required int64 id = 6;</code>
     */
    public boolean hasId() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    /**
     * <pre>
     * 标记点唯一ID
     * </pre>
     *
     * <code>required int64 id = 6;</code>
     */
    public long getId() {
      return id_;
    }
    /**
     * <pre>
     * 标记点唯一ID
     * </pre>
     *
     * <code>required int64 id = 6;</code>
     */
    public Builder setId(long value) {
      bitField0_ |= 0x00000020;
      id_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 标记点唯一ID
     * </pre>
     *
     * <code>required int64 id = 6;</code>
     */
    public Builder clearId() {
      bitField0_ = (bitField0_ & ~0x00000020);
      id_ = 0L;
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


    // @@protoc_insertion_point(builder_scope:client2server.MarkInfo)
  }

  // @@protoc_insertion_point(class_scope:client2server.MarkInfo)
  private static final pb4client.MarkInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.MarkInfo();
  }

  public static pb4client.MarkInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<MarkInfo>
      PARSER = new com.google.protobuf.AbstractParser<MarkInfo>() {
    public MarkInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new MarkInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<MarkInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<MarkInfo> getParserForType() {
    return PARSER;
  }

  public pb4client.MarkInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

