// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.AllianceOccupyInfo}
 */
public  final class AllianceOccupyInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.AllianceOccupyInfo)
    AllianceOccupyInfoOrBuilder {
  // Use AllianceOccupyInfo.newBuilder() to construct.
  private AllianceOccupyInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AllianceOccupyInfo() {
    allianceId_ = 0L;
    allianceMainMemberName_ = "";
    allianceAreaNo_ = 0;
    allianceName_ = "";
    allianceShortName_ = "";
    worldId_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private AllianceOccupyInfo(
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
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            allianceMainMemberName_ = s;
            break;
          }
          case 24: {

            allianceAreaNo_ = input.readInt32();
            break;
          }
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            allianceName_ = s;
            break;
          }
          case 42: {
            java.lang.String s = input.readStringRequireUtf8();

            allianceShortName_ = s;
            break;
          }
          case 48: {
            if (!((mutable_bitField0_ & 0x00000020) == 0x00000020)) {
              worldId_ = new java.util.ArrayList<java.lang.Long>();
              mutable_bitField0_ |= 0x00000020;
            }
            worldId_.add(input.readInt64());
            break;
          }
          case 50: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000020) == 0x00000020) && input.getBytesUntilLimit() > 0) {
              worldId_ = new java.util.ArrayList<java.lang.Long>();
              mutable_bitField0_ |= 0x00000020;
            }
            while (input.getBytesUntilLimit() > 0) {
              worldId_.add(input.readInt64());
            }
            input.popLimit(limit);
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
        worldId_ = java.util.Collections.unmodifiableList(worldId_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalPkt.internal_static_pb4server_AllianceOccupyInfo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalPkt.internal_static_pb4server_AllianceOccupyInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.AllianceOccupyInfo.class, pb4server.AllianceOccupyInfo.Builder.class);
  }

  private int bitField0_;
  public static final int ALLIANCEID_FIELD_NUMBER = 1;
  private long allianceId_;
  /**
   * <pre>
   * 联盟ID
   * </pre>
   *
   * <code>int64 allianceId = 1;</code>
   */
  public long getAllianceId() {
    return allianceId_;
  }

  public static final int ALLIANCEMAINMEMBERNAME_FIELD_NUMBER = 2;
  private volatile java.lang.Object allianceMainMemberName_;
  /**
   * <pre>
   * 盟主名字
   * </pre>
   *
   * <code>string allianceMainMemberName = 2;</code>
   */
  public java.lang.String getAllianceMainMemberName() {
    java.lang.Object ref = allianceMainMemberName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      allianceMainMemberName_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * 盟主名字
   * </pre>
   *
   * <code>string allianceMainMemberName = 2;</code>
   */
  public com.google.protobuf.ByteString
      getAllianceMainMemberNameBytes() {
    java.lang.Object ref = allianceMainMemberName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      allianceMainMemberName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int ALLIANCEAREANO_FIELD_NUMBER = 3;
  private int allianceAreaNo_;
  /**
   * <pre>
   * 联盟所属服务器
   * </pre>
   *
   * <code>int32 allianceAreaNo = 3;</code>
   */
  public int getAllianceAreaNo() {
    return allianceAreaNo_;
  }

  public static final int ALLIANCENAME_FIELD_NUMBER = 4;
  private volatile java.lang.Object allianceName_;
  /**
   * <pre>
   * 联盟名字
   * </pre>
   *
   * <code>string allianceName = 4;</code>
   */
  public java.lang.String getAllianceName() {
    java.lang.Object ref = allianceName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      allianceName_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * 联盟名字
   * </pre>
   *
   * <code>string allianceName = 4;</code>
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

  public static final int ALLIANCESHORTNAME_FIELD_NUMBER = 5;
  private volatile java.lang.Object allianceShortName_;
  /**
   * <pre>
   * 联盟简称
   * </pre>
   *
   * <code>string allianceShortName = 5;</code>
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
   * <pre>
   * 联盟简称
   * </pre>
   *
   * <code>string allianceShortName = 5;</code>
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

  public static final int WORLDID_FIELD_NUMBER = 6;
  private java.util.List<java.lang.Long> worldId_;
  /**
   * <pre>
   * 占领的世界ID
   * </pre>
   *
   * <code>repeated int64 worldId = 6;</code>
   */
  public java.util.List<java.lang.Long>
      getWorldIdList() {
    return worldId_;
  }
  /**
   * <pre>
   * 占领的世界ID
   * </pre>
   *
   * <code>repeated int64 worldId = 6;</code>
   */
  public int getWorldIdCount() {
    return worldId_.size();
  }
  /**
   * <pre>
   * 占领的世界ID
   * </pre>
   *
   * <code>repeated int64 worldId = 6;</code>
   */
  public long getWorldId(int index) {
    return worldId_.get(index);
  }
  private int worldIdMemoizedSerializedSize = -1;

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
    if (!getAllianceMainMemberNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, allianceMainMemberName_);
    }
    if (allianceAreaNo_ != 0) {
      output.writeInt32(3, allianceAreaNo_);
    }
    if (!getAllianceNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, allianceName_);
    }
    if (!getAllianceShortNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, allianceShortName_);
    }
    if (getWorldIdList().size() > 0) {
      output.writeUInt32NoTag(50);
      output.writeUInt32NoTag(worldIdMemoizedSerializedSize);
    }
    for (int i = 0; i < worldId_.size(); i++) {
      output.writeInt64NoTag(worldId_.get(i));
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
    if (!getAllianceMainMemberNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, allianceMainMemberName_);
    }
    if (allianceAreaNo_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, allianceAreaNo_);
    }
    if (!getAllianceNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, allianceName_);
    }
    if (!getAllianceShortNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, allianceShortName_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < worldId_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeInt64SizeNoTag(worldId_.get(i));
      }
      size += dataSize;
      if (!getWorldIdList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      worldIdMemoizedSerializedSize = dataSize;
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
    if (!(obj instanceof pb4server.AllianceOccupyInfo)) {
      return super.equals(obj);
    }
    pb4server.AllianceOccupyInfo other = (pb4server.AllianceOccupyInfo) obj;

    boolean result = true;
    result = result && (getAllianceId()
        == other.getAllianceId());
    result = result && getAllianceMainMemberName()
        .equals(other.getAllianceMainMemberName());
    result = result && (getAllianceAreaNo()
        == other.getAllianceAreaNo());
    result = result && getAllianceName()
        .equals(other.getAllianceName());
    result = result && getAllianceShortName()
        .equals(other.getAllianceShortName());
    result = result && getWorldIdList()
        .equals(other.getWorldIdList());
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
    hash = (37 * hash) + ALLIANCEMAINMEMBERNAME_FIELD_NUMBER;
    hash = (53 * hash) + getAllianceMainMemberName().hashCode();
    hash = (37 * hash) + ALLIANCEAREANO_FIELD_NUMBER;
    hash = (53 * hash) + getAllianceAreaNo();
    hash = (37 * hash) + ALLIANCENAME_FIELD_NUMBER;
    hash = (53 * hash) + getAllianceName().hashCode();
    hash = (37 * hash) + ALLIANCESHORTNAME_FIELD_NUMBER;
    hash = (53 * hash) + getAllianceShortName().hashCode();
    if (getWorldIdCount() > 0) {
      hash = (37 * hash) + WORLDID_FIELD_NUMBER;
      hash = (53 * hash) + getWorldIdList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.AllianceOccupyInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceOccupyInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceOccupyInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceOccupyInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceOccupyInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.AllianceOccupyInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.AllianceOccupyInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.AllianceOccupyInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.AllianceOccupyInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.AllianceOccupyInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.AllianceOccupyInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.AllianceOccupyInfo parseFrom(
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
  public static Builder newBuilder(pb4server.AllianceOccupyInfo prototype) {
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
   * Protobuf type {@code pb4server.AllianceOccupyInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.AllianceOccupyInfo)
      pb4server.AllianceOccupyInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalPkt.internal_static_pb4server_AllianceOccupyInfo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalPkt.internal_static_pb4server_AllianceOccupyInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.AllianceOccupyInfo.class, pb4server.AllianceOccupyInfo.Builder.class);
    }

    // Construct using pb4server.AllianceOccupyInfo.newBuilder()
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

      allianceMainMemberName_ = "";

      allianceAreaNo_ = 0;

      allianceName_ = "";

      allianceShortName_ = "";

      worldId_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000020);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalPkt.internal_static_pb4server_AllianceOccupyInfo_descriptor;
    }

    public pb4server.AllianceOccupyInfo getDefaultInstanceForType() {
      return pb4server.AllianceOccupyInfo.getDefaultInstance();
    }

    public pb4server.AllianceOccupyInfo build() {
      pb4server.AllianceOccupyInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.AllianceOccupyInfo buildPartial() {
      pb4server.AllianceOccupyInfo result = new pb4server.AllianceOccupyInfo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.allianceId_ = allianceId_;
      result.allianceMainMemberName_ = allianceMainMemberName_;
      result.allianceAreaNo_ = allianceAreaNo_;
      result.allianceName_ = allianceName_;
      result.allianceShortName_ = allianceShortName_;
      if (((bitField0_ & 0x00000020) == 0x00000020)) {
        worldId_ = java.util.Collections.unmodifiableList(worldId_);
        bitField0_ = (bitField0_ & ~0x00000020);
      }
      result.worldId_ = worldId_;
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
      if (other instanceof pb4server.AllianceOccupyInfo) {
        return mergeFrom((pb4server.AllianceOccupyInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.AllianceOccupyInfo other) {
      if (other == pb4server.AllianceOccupyInfo.getDefaultInstance()) return this;
      if (other.getAllianceId() != 0L) {
        setAllianceId(other.getAllianceId());
      }
      if (!other.getAllianceMainMemberName().isEmpty()) {
        allianceMainMemberName_ = other.allianceMainMemberName_;
        onChanged();
      }
      if (other.getAllianceAreaNo() != 0) {
        setAllianceAreaNo(other.getAllianceAreaNo());
      }
      if (!other.getAllianceName().isEmpty()) {
        allianceName_ = other.allianceName_;
        onChanged();
      }
      if (!other.getAllianceShortName().isEmpty()) {
        allianceShortName_ = other.allianceShortName_;
        onChanged();
      }
      if (!other.worldId_.isEmpty()) {
        if (worldId_.isEmpty()) {
          worldId_ = other.worldId_;
          bitField0_ = (bitField0_ & ~0x00000020);
        } else {
          ensureWorldIdIsMutable();
          worldId_.addAll(other.worldId_);
        }
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
      pb4server.AllianceOccupyInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.AllianceOccupyInfo) e.getUnfinishedMessage();
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
     * <pre>
     * 联盟ID
     * </pre>
     *
     * <code>int64 allianceId = 1;</code>
     */
    public long getAllianceId() {
      return allianceId_;
    }
    /**
     * <pre>
     * 联盟ID
     * </pre>
     *
     * <code>int64 allianceId = 1;</code>
     */
    public Builder setAllianceId(long value) {
      
      allianceId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 联盟ID
     * </pre>
     *
     * <code>int64 allianceId = 1;</code>
     */
    public Builder clearAllianceId() {
      
      allianceId_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object allianceMainMemberName_ = "";
    /**
     * <pre>
     * 盟主名字
     * </pre>
     *
     * <code>string allianceMainMemberName = 2;</code>
     */
    public java.lang.String getAllianceMainMemberName() {
      java.lang.Object ref = allianceMainMemberName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        allianceMainMemberName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * 盟主名字
     * </pre>
     *
     * <code>string allianceMainMemberName = 2;</code>
     */
    public com.google.protobuf.ByteString
        getAllianceMainMemberNameBytes() {
      java.lang.Object ref = allianceMainMemberName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        allianceMainMemberName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * 盟主名字
     * </pre>
     *
     * <code>string allianceMainMemberName = 2;</code>
     */
    public Builder setAllianceMainMemberName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      allianceMainMemberName_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 盟主名字
     * </pre>
     *
     * <code>string allianceMainMemberName = 2;</code>
     */
    public Builder clearAllianceMainMemberName() {
      
      allianceMainMemberName_ = getDefaultInstance().getAllianceMainMemberName();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 盟主名字
     * </pre>
     *
     * <code>string allianceMainMemberName = 2;</code>
     */
    public Builder setAllianceMainMemberNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      allianceMainMemberName_ = value;
      onChanged();
      return this;
    }

    private int allianceAreaNo_ ;
    /**
     * <pre>
     * 联盟所属服务器
     * </pre>
     *
     * <code>int32 allianceAreaNo = 3;</code>
     */
    public int getAllianceAreaNo() {
      return allianceAreaNo_;
    }
    /**
     * <pre>
     * 联盟所属服务器
     * </pre>
     *
     * <code>int32 allianceAreaNo = 3;</code>
     */
    public Builder setAllianceAreaNo(int value) {
      
      allianceAreaNo_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 联盟所属服务器
     * </pre>
     *
     * <code>int32 allianceAreaNo = 3;</code>
     */
    public Builder clearAllianceAreaNo() {
      
      allianceAreaNo_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object allianceName_ = "";
    /**
     * <pre>
     * 联盟名字
     * </pre>
     *
     * <code>string allianceName = 4;</code>
     */
    public java.lang.String getAllianceName() {
      java.lang.Object ref = allianceName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        allianceName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * 联盟名字
     * </pre>
     *
     * <code>string allianceName = 4;</code>
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
     * 联盟名字
     * </pre>
     *
     * <code>string allianceName = 4;</code>
     */
    public Builder setAllianceName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      allianceName_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 联盟名字
     * </pre>
     *
     * <code>string allianceName = 4;</code>
     */
    public Builder clearAllianceName() {
      
      allianceName_ = getDefaultInstance().getAllianceName();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 联盟名字
     * </pre>
     *
     * <code>string allianceName = 4;</code>
     */
    public Builder setAllianceNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      allianceName_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object allianceShortName_ = "";
    /**
     * <pre>
     * 联盟简称
     * </pre>
     *
     * <code>string allianceShortName = 5;</code>
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
     * <pre>
     * 联盟简称
     * </pre>
     *
     * <code>string allianceShortName = 5;</code>
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
     * 联盟简称
     * </pre>
     *
     * <code>string allianceShortName = 5;</code>
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
     * <pre>
     * 联盟简称
     * </pre>
     *
     * <code>string allianceShortName = 5;</code>
     */
    public Builder clearAllianceShortName() {
      
      allianceShortName_ = getDefaultInstance().getAllianceShortName();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 联盟简称
     * </pre>
     *
     * <code>string allianceShortName = 5;</code>
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

    private java.util.List<java.lang.Long> worldId_ = java.util.Collections.emptyList();
    private void ensureWorldIdIsMutable() {
      if (!((bitField0_ & 0x00000020) == 0x00000020)) {
        worldId_ = new java.util.ArrayList<java.lang.Long>(worldId_);
        bitField0_ |= 0x00000020;
       }
    }
    /**
     * <pre>
     * 占领的世界ID
     * </pre>
     *
     * <code>repeated int64 worldId = 6;</code>
     */
    public java.util.List<java.lang.Long>
        getWorldIdList() {
      return java.util.Collections.unmodifiableList(worldId_);
    }
    /**
     * <pre>
     * 占领的世界ID
     * </pre>
     *
     * <code>repeated int64 worldId = 6;</code>
     */
    public int getWorldIdCount() {
      return worldId_.size();
    }
    /**
     * <pre>
     * 占领的世界ID
     * </pre>
     *
     * <code>repeated int64 worldId = 6;</code>
     */
    public long getWorldId(int index) {
      return worldId_.get(index);
    }
    /**
     * <pre>
     * 占领的世界ID
     * </pre>
     *
     * <code>repeated int64 worldId = 6;</code>
     */
    public Builder setWorldId(
        int index, long value) {
      ensureWorldIdIsMutable();
      worldId_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 占领的世界ID
     * </pre>
     *
     * <code>repeated int64 worldId = 6;</code>
     */
    public Builder addWorldId(long value) {
      ensureWorldIdIsMutable();
      worldId_.add(value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 占领的世界ID
     * </pre>
     *
     * <code>repeated int64 worldId = 6;</code>
     */
    public Builder addAllWorldId(
        java.lang.Iterable<? extends java.lang.Long> values) {
      ensureWorldIdIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, worldId_);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 占领的世界ID
     * </pre>
     *
     * <code>repeated int64 worldId = 6;</code>
     */
    public Builder clearWorldId() {
      worldId_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000020);
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


    // @@protoc_insertion_point(builder_scope:pb4server.AllianceOccupyInfo)
  }

  // @@protoc_insertion_point(class_scope:pb4server.AllianceOccupyInfo)
  private static final pb4server.AllianceOccupyInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.AllianceOccupyInfo();
  }

  public static pb4server.AllianceOccupyInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AllianceOccupyInfo>
      PARSER = new com.google.protobuf.AbstractParser<AllianceOccupyInfo>() {
    public AllianceOccupyInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new AllianceOccupyInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AllianceOccupyInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AllianceOccupyInfo> getParserForType() {
    return PARSER;
  }

  public pb4server.AllianceOccupyInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

