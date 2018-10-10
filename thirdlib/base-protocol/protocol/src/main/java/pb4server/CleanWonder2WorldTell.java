// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2world.proto

package pb4server;

/**
 * <pre>
 * Public-&gt;Game
 * 通知世界清除奇观
 * </pre>
 *
 * Protobuf type {@code pb4server.CleanWonder2WorldTell}
 */
public  final class CleanWonder2WorldTell extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.CleanWonder2WorldTell)
    CleanWonder2WorldTellOrBuilder {
  // Use CleanWonder2WorldTell.newBuilder() to construct.
  private CleanWonder2WorldTell(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CleanWonder2WorldTell() {
    wonderProtoId_ = java.util.Collections.emptyList();
    allianceId_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private CleanWonder2WorldTell(
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
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              wonderProtoId_ = new java.util.ArrayList<java.lang.Integer>();
              mutable_bitField0_ |= 0x00000001;
            }
            wonderProtoId_.add(input.readInt32());
            break;
          }
          case 10: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001) && input.getBytesUntilLimit() > 0) {
              wonderProtoId_ = new java.util.ArrayList<java.lang.Integer>();
              mutable_bitField0_ |= 0x00000001;
            }
            while (input.getBytesUntilLimit() > 0) {
              wonderProtoId_.add(input.readInt32());
            }
            input.popLimit(limit);
            break;
          }
          case 16: {

            allianceId_ = input.readInt64();
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
      if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
        wonderProtoId_ = java.util.Collections.unmodifiableList(wonderProtoId_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalWkt.internal_static_pb4server_CleanWonder2WorldTell_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalWkt.internal_static_pb4server_CleanWonder2WorldTell_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.CleanWonder2WorldTell.class, pb4server.CleanWonder2WorldTell.Builder.class);
  }

  private int bitField0_;
  public static final int WONDERPROTOID_FIELD_NUMBER = 1;
  private java.util.List<java.lang.Integer> wonderProtoId_;
  /**
   * <code>repeated int32 wonderProtoId = 1;</code>
   */
  public java.util.List<java.lang.Integer>
      getWonderProtoIdList() {
    return wonderProtoId_;
  }
  /**
   * <code>repeated int32 wonderProtoId = 1;</code>
   */
  public int getWonderProtoIdCount() {
    return wonderProtoId_.size();
  }
  /**
   * <code>repeated int32 wonderProtoId = 1;</code>
   */
  public int getWonderProtoId(int index) {
    return wonderProtoId_.get(index);
  }
  private int wonderProtoIdMemoizedSerializedSize = -1;

  public static final int ALLIANCEID_FIELD_NUMBER = 2;
  private long allianceId_;
  /**
   * <code>int64 allianceId = 2;</code>
   */
  public long getAllianceId() {
    return allianceId_;
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
    if (getWonderProtoIdList().size() > 0) {
      output.writeUInt32NoTag(10);
      output.writeUInt32NoTag(wonderProtoIdMemoizedSerializedSize);
    }
    for (int i = 0; i < wonderProtoId_.size(); i++) {
      output.writeInt32NoTag(wonderProtoId_.get(i));
    }
    if (allianceId_ != 0L) {
      output.writeInt64(2, allianceId_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    {
      int dataSize = 0;
      for (int i = 0; i < wonderProtoId_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeInt32SizeNoTag(wonderProtoId_.get(i));
      }
      size += dataSize;
      if (!getWonderProtoIdList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      wonderProtoIdMemoizedSerializedSize = dataSize;
    }
    if (allianceId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, allianceId_);
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
    if (!(obj instanceof pb4server.CleanWonder2WorldTell)) {
      return super.equals(obj);
    }
    pb4server.CleanWonder2WorldTell other = (pb4server.CleanWonder2WorldTell) obj;

    boolean result = true;
    result = result && getWonderProtoIdList()
        .equals(other.getWonderProtoIdList());
    result = result && (getAllianceId()
        == other.getAllianceId());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getWonderProtoIdCount() > 0) {
      hash = (37 * hash) + WONDERPROTOID_FIELD_NUMBER;
      hash = (53 * hash) + getWonderProtoIdList().hashCode();
    }
    hash = (37 * hash) + ALLIANCEID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getAllianceId());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.CleanWonder2WorldTell parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.CleanWonder2WorldTell parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.CleanWonder2WorldTell parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.CleanWonder2WorldTell parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.CleanWonder2WorldTell parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.CleanWonder2WorldTell parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.CleanWonder2WorldTell parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.CleanWonder2WorldTell parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.CleanWonder2WorldTell parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.CleanWonder2WorldTell parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.CleanWonder2WorldTell parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.CleanWonder2WorldTell parseFrom(
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
  public static Builder newBuilder(pb4server.CleanWonder2WorldTell prototype) {
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
   * Public-&gt;Game
   * 通知世界清除奇观
   * </pre>
   *
   * Protobuf type {@code pb4server.CleanWonder2WorldTell}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.CleanWonder2WorldTell)
      pb4server.CleanWonder2WorldTellOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalWkt.internal_static_pb4server_CleanWonder2WorldTell_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalWkt.internal_static_pb4server_CleanWonder2WorldTell_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.CleanWonder2WorldTell.class, pb4server.CleanWonder2WorldTell.Builder.class);
    }

    // Construct using pb4server.CleanWonder2WorldTell.newBuilder()
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
      wonderProtoId_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000001);
      allianceId_ = 0L;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalWkt.internal_static_pb4server_CleanWonder2WorldTell_descriptor;
    }

    public pb4server.CleanWonder2WorldTell getDefaultInstanceForType() {
      return pb4server.CleanWonder2WorldTell.getDefaultInstance();
    }

    public pb4server.CleanWonder2WorldTell build() {
      pb4server.CleanWonder2WorldTell result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.CleanWonder2WorldTell buildPartial() {
      pb4server.CleanWonder2WorldTell result = new pb4server.CleanWonder2WorldTell(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        wonderProtoId_ = java.util.Collections.unmodifiableList(wonderProtoId_);
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.wonderProtoId_ = wonderProtoId_;
      result.allianceId_ = allianceId_;
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
      if (other instanceof pb4server.CleanWonder2WorldTell) {
        return mergeFrom((pb4server.CleanWonder2WorldTell)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.CleanWonder2WorldTell other) {
      if (other == pb4server.CleanWonder2WorldTell.getDefaultInstance()) return this;
      if (!other.wonderProtoId_.isEmpty()) {
        if (wonderProtoId_.isEmpty()) {
          wonderProtoId_ = other.wonderProtoId_;
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          ensureWonderProtoIdIsMutable();
          wonderProtoId_.addAll(other.wonderProtoId_);
        }
        onChanged();
      }
      if (other.getAllianceId() != 0L) {
        setAllianceId(other.getAllianceId());
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
      pb4server.CleanWonder2WorldTell parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.CleanWonder2WorldTell) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<java.lang.Integer> wonderProtoId_ = java.util.Collections.emptyList();
    private void ensureWonderProtoIdIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        wonderProtoId_ = new java.util.ArrayList<java.lang.Integer>(wonderProtoId_);
        bitField0_ |= 0x00000001;
       }
    }
    /**
     * <code>repeated int32 wonderProtoId = 1;</code>
     */
    public java.util.List<java.lang.Integer>
        getWonderProtoIdList() {
      return java.util.Collections.unmodifiableList(wonderProtoId_);
    }
    /**
     * <code>repeated int32 wonderProtoId = 1;</code>
     */
    public int getWonderProtoIdCount() {
      return wonderProtoId_.size();
    }
    /**
     * <code>repeated int32 wonderProtoId = 1;</code>
     */
    public int getWonderProtoId(int index) {
      return wonderProtoId_.get(index);
    }
    /**
     * <code>repeated int32 wonderProtoId = 1;</code>
     */
    public Builder setWonderProtoId(
        int index, int value) {
      ensureWonderProtoIdIsMutable();
      wonderProtoId_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int32 wonderProtoId = 1;</code>
     */
    public Builder addWonderProtoId(int value) {
      ensureWonderProtoIdIsMutable();
      wonderProtoId_.add(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int32 wonderProtoId = 1;</code>
     */
    public Builder addAllWonderProtoId(
        java.lang.Iterable<? extends java.lang.Integer> values) {
      ensureWonderProtoIdIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, wonderProtoId_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int32 wonderProtoId = 1;</code>
     */
    public Builder clearWonderProtoId() {
      wonderProtoId_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }

    private long allianceId_ ;
    /**
     * <code>int64 allianceId = 2;</code>
     */
    public long getAllianceId() {
      return allianceId_;
    }
    /**
     * <code>int64 allianceId = 2;</code>
     */
    public Builder setAllianceId(long value) {
      
      allianceId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 allianceId = 2;</code>
     */
    public Builder clearAllianceId() {
      
      allianceId_ = 0L;
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


    // @@protoc_insertion_point(builder_scope:pb4server.CleanWonder2WorldTell)
  }

  // @@protoc_insertion_point(class_scope:pb4server.CleanWonder2WorldTell)
  private static final pb4server.CleanWonder2WorldTell DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.CleanWonder2WorldTell();
  }

  public static pb4server.CleanWonder2WorldTell getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CleanWonder2WorldTell>
      PARSER = new com.google.protobuf.AbstractParser<CleanWonder2WorldTell>() {
    public CleanWonder2WorldTell parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new CleanWonder2WorldTell(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<CleanWonder2WorldTell> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CleanWonder2WorldTell> getParserForType() {
    return PARSER;
  }

  public pb4server.CleanWonder2WorldTell getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

