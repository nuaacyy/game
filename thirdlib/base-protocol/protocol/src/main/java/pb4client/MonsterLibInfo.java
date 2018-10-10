// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * 怪物图鉴
 * </pre>
 *
 * Protobuf type {@code client2server.MonsterLibInfo}
 */
public  final class MonsterLibInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.MonsterLibInfo)
    MonsterLibInfoOrBuilder {
  // Use MonsterLibInfo.newBuilder() to construct.
  private MonsterLibInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private MonsterLibInfo() {
    libraryType_ = 0;
    protoId_ = 0;
    killNum_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private MonsterLibInfo(
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
            libraryType_ = input.readInt32();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            protoId_ = input.readInt32();
            break;
          }
          case 24: {
            bitField0_ |= 0x00000004;
            killNum_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_MonsterLibInfo_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_MonsterLibInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.MonsterLibInfo.class, pb4client.MonsterLibInfo.Builder.class);
  }

  private int bitField0_;
  public static final int LIBRARYTYPE_FIELD_NUMBER = 1;
  private int libraryType_;
  /**
   * <code>required int32 libraryType = 1;</code>
   */
  public boolean hasLibraryType() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>required int32 libraryType = 1;</code>
   */
  public int getLibraryType() {
    return libraryType_;
  }

  public static final int PROTOID_FIELD_NUMBER = 2;
  private int protoId_;
  /**
   * <code>required int32 protoId = 2;</code>
   */
  public boolean hasProtoId() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>required int32 protoId = 2;</code>
   */
  public int getProtoId() {
    return protoId_;
  }

  public static final int KILLNUM_FIELD_NUMBER = 3;
  private int killNum_;
  /**
   * <code>required int32 killNum = 3;</code>
   */
  public boolean hasKillNum() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <code>required int32 killNum = 3;</code>
   */
  public int getKillNum() {
    return killNum_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasLibraryType()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasProtoId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasKillNum()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, libraryType_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, protoId_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeInt32(3, killNum_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, libraryType_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, protoId_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, killNum_);
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
    if (!(obj instanceof pb4client.MonsterLibInfo)) {
      return super.equals(obj);
    }
    pb4client.MonsterLibInfo other = (pb4client.MonsterLibInfo) obj;

    boolean result = true;
    result = result && (hasLibraryType() == other.hasLibraryType());
    if (hasLibraryType()) {
      result = result && (getLibraryType()
          == other.getLibraryType());
    }
    result = result && (hasProtoId() == other.hasProtoId());
    if (hasProtoId()) {
      result = result && (getProtoId()
          == other.getProtoId());
    }
    result = result && (hasKillNum() == other.hasKillNum());
    if (hasKillNum()) {
      result = result && (getKillNum()
          == other.getKillNum());
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
    if (hasLibraryType()) {
      hash = (37 * hash) + LIBRARYTYPE_FIELD_NUMBER;
      hash = (53 * hash) + getLibraryType();
    }
    if (hasProtoId()) {
      hash = (37 * hash) + PROTOID_FIELD_NUMBER;
      hash = (53 * hash) + getProtoId();
    }
    if (hasKillNum()) {
      hash = (37 * hash) + KILLNUM_FIELD_NUMBER;
      hash = (53 * hash) + getKillNum();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.MonsterLibInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.MonsterLibInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.MonsterLibInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.MonsterLibInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.MonsterLibInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.MonsterLibInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.MonsterLibInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.MonsterLibInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.MonsterLibInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.MonsterLibInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.MonsterLibInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.MonsterLibInfo parseFrom(
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
  public static Builder newBuilder(pb4client.MonsterLibInfo prototype) {
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
   * 怪物图鉴
   * </pre>
   *
   * Protobuf type {@code client2server.MonsterLibInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.MonsterLibInfo)
      pb4client.MonsterLibInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_MonsterLibInfo_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_MonsterLibInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.MonsterLibInfo.class, pb4client.MonsterLibInfo.Builder.class);
    }

    // Construct using pb4client.MonsterLibInfo.newBuilder()
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
      libraryType_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      protoId_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      killNum_ = 0;
      bitField0_ = (bitField0_ & ~0x00000004);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_MonsterLibInfo_descriptor;
    }

    public pb4client.MonsterLibInfo getDefaultInstanceForType() {
      return pb4client.MonsterLibInfo.getDefaultInstance();
    }

    public pb4client.MonsterLibInfo build() {
      pb4client.MonsterLibInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.MonsterLibInfo buildPartial() {
      pb4client.MonsterLibInfo result = new pb4client.MonsterLibInfo(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.libraryType_ = libraryType_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.protoId_ = protoId_;
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.killNum_ = killNum_;
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
      if (other instanceof pb4client.MonsterLibInfo) {
        return mergeFrom((pb4client.MonsterLibInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.MonsterLibInfo other) {
      if (other == pb4client.MonsterLibInfo.getDefaultInstance()) return this;
      if (other.hasLibraryType()) {
        setLibraryType(other.getLibraryType());
      }
      if (other.hasProtoId()) {
        setProtoId(other.getProtoId());
      }
      if (other.hasKillNum()) {
        setKillNum(other.getKillNum());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasLibraryType()) {
        return false;
      }
      if (!hasProtoId()) {
        return false;
      }
      if (!hasKillNum()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.MonsterLibInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.MonsterLibInfo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int libraryType_ ;
    /**
     * <code>required int32 libraryType = 1;</code>
     */
    public boolean hasLibraryType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 libraryType = 1;</code>
     */
    public int getLibraryType() {
      return libraryType_;
    }
    /**
     * <code>required int32 libraryType = 1;</code>
     */
    public Builder setLibraryType(int value) {
      bitField0_ |= 0x00000001;
      libraryType_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 libraryType = 1;</code>
     */
    public Builder clearLibraryType() {
      bitField0_ = (bitField0_ & ~0x00000001);
      libraryType_ = 0;
      onChanged();
      return this;
    }

    private int protoId_ ;
    /**
     * <code>required int32 protoId = 2;</code>
     */
    public boolean hasProtoId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required int32 protoId = 2;</code>
     */
    public int getProtoId() {
      return protoId_;
    }
    /**
     * <code>required int32 protoId = 2;</code>
     */
    public Builder setProtoId(int value) {
      bitField0_ |= 0x00000002;
      protoId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 protoId = 2;</code>
     */
    public Builder clearProtoId() {
      bitField0_ = (bitField0_ & ~0x00000002);
      protoId_ = 0;
      onChanged();
      return this;
    }

    private int killNum_ ;
    /**
     * <code>required int32 killNum = 3;</code>
     */
    public boolean hasKillNum() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required int32 killNum = 3;</code>
     */
    public int getKillNum() {
      return killNum_;
    }
    /**
     * <code>required int32 killNum = 3;</code>
     */
    public Builder setKillNum(int value) {
      bitField0_ |= 0x00000004;
      killNum_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 killNum = 3;</code>
     */
    public Builder clearKillNum() {
      bitField0_ = (bitField0_ & ~0x00000004);
      killNum_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.MonsterLibInfo)
  }

  // @@protoc_insertion_point(class_scope:client2server.MonsterLibInfo)
  private static final pb4client.MonsterLibInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.MonsterLibInfo();
  }

  public static pb4client.MonsterLibInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<MonsterLibInfo>
      PARSER = new com.google.protobuf.AbstractParser<MonsterLibInfo>() {
    public MonsterLibInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new MonsterLibInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<MonsterLibInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<MonsterLibInfo> getParserForType() {
    return PARSER;
  }

  public pb4client.MonsterLibInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

