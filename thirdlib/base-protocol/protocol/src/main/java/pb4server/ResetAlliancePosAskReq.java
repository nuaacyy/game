// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

/**
 * <pre>
 * 任命职位
 * </pre>
 *
 * Protobuf type {@code pb4server.ResetAlliancePosAskReq}
 */
public  final class ResetAlliancePosAskReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.ResetAlliancePosAskReq)
    ResetAlliancePosAskReqOrBuilder {
  // Use ResetAlliancePosAskReq.newBuilder() to construct.
  private ResetAlliancePosAskReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ResetAlliancePosAskReq() {
    posPlayerId_ = 0L;
    posId_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private ResetAlliancePosAskReq(
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

            posPlayerId_ = input.readInt64();
            break;
          }
          case 16: {
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              posId_ = new java.util.ArrayList<java.lang.Integer>();
              mutable_bitField0_ |= 0x00000002;
            }
            posId_.add(input.readInt32());
            break;
          }
          case 18: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002) && input.getBytesUntilLimit() > 0) {
              posId_ = new java.util.ArrayList<java.lang.Integer>();
              mutable_bitField0_ |= 0x00000002;
            }
            while (input.getBytesUntilLimit() > 0) {
              posId_.add(input.readInt32());
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
      if (((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
        posId_ = java.util.Collections.unmodifiableList(posId_);
      }
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalPkt.internal_static_pb4server_ResetAlliancePosAskReq_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalPkt.internal_static_pb4server_ResetAlliancePosAskReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.ResetAlliancePosAskReq.class, pb4server.ResetAlliancePosAskReq.Builder.class);
  }

  private int bitField0_;
  public static final int POSPLAYERID_FIELD_NUMBER = 1;
  private long posPlayerId_;
  /**
   * <pre>
   * 被设置职位人ID
   * </pre>
   *
   * <code>int64 posPlayerId = 1;</code>
   */
  public long getPosPlayerId() {
    return posPlayerId_;
  }

  public static final int POSID_FIELD_NUMBER = 2;
  private java.util.List<java.lang.Integer> posId_;
  /**
   * <pre>
   * 职位
   * </pre>
   *
   * <code>repeated int32 posId = 2;</code>
   */
  public java.util.List<java.lang.Integer>
      getPosIdList() {
    return posId_;
  }
  /**
   * <pre>
   * 职位
   * </pre>
   *
   * <code>repeated int32 posId = 2;</code>
   */
  public int getPosIdCount() {
    return posId_.size();
  }
  /**
   * <pre>
   * 职位
   * </pre>
   *
   * <code>repeated int32 posId = 2;</code>
   */
  public int getPosId(int index) {
    return posId_.get(index);
  }
  private int posIdMemoizedSerializedSize = -1;

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
    if (posPlayerId_ != 0L) {
      output.writeInt64(1, posPlayerId_);
    }
    if (getPosIdList().size() > 0) {
      output.writeUInt32NoTag(18);
      output.writeUInt32NoTag(posIdMemoizedSerializedSize);
    }
    for (int i = 0; i < posId_.size(); i++) {
      output.writeInt32NoTag(posId_.get(i));
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (posPlayerId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, posPlayerId_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < posId_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeInt32SizeNoTag(posId_.get(i));
      }
      size += dataSize;
      if (!getPosIdList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      posIdMemoizedSerializedSize = dataSize;
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
    if (!(obj instanceof pb4server.ResetAlliancePosAskReq)) {
      return super.equals(obj);
    }
    pb4server.ResetAlliancePosAskReq other = (pb4server.ResetAlliancePosAskReq) obj;

    boolean result = true;
    result = result && (getPosPlayerId()
        == other.getPosPlayerId());
    result = result && getPosIdList()
        .equals(other.getPosIdList());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + POSPLAYERID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getPosPlayerId());
    if (getPosIdCount() > 0) {
      hash = (37 * hash) + POSID_FIELD_NUMBER;
      hash = (53 * hash) + getPosIdList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.ResetAlliancePosAskReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.ResetAlliancePosAskReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.ResetAlliancePosAskReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.ResetAlliancePosAskReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.ResetAlliancePosAskReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.ResetAlliancePosAskReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.ResetAlliancePosAskReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.ResetAlliancePosAskReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.ResetAlliancePosAskReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.ResetAlliancePosAskReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.ResetAlliancePosAskReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.ResetAlliancePosAskReq parseFrom(
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
  public static Builder newBuilder(pb4server.ResetAlliancePosAskReq prototype) {
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
   * 任命职位
   * </pre>
   *
   * Protobuf type {@code pb4server.ResetAlliancePosAskReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.ResetAlliancePosAskReq)
      pb4server.ResetAlliancePosAskReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalPkt.internal_static_pb4server_ResetAlliancePosAskReq_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalPkt.internal_static_pb4server_ResetAlliancePosAskReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.ResetAlliancePosAskReq.class, pb4server.ResetAlliancePosAskReq.Builder.class);
    }

    // Construct using pb4server.ResetAlliancePosAskReq.newBuilder()
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
      posPlayerId_ = 0L;

      posId_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalPkt.internal_static_pb4server_ResetAlliancePosAskReq_descriptor;
    }

    public pb4server.ResetAlliancePosAskReq getDefaultInstanceForType() {
      return pb4server.ResetAlliancePosAskReq.getDefaultInstance();
    }

    public pb4server.ResetAlliancePosAskReq build() {
      pb4server.ResetAlliancePosAskReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.ResetAlliancePosAskReq buildPartial() {
      pb4server.ResetAlliancePosAskReq result = new pb4server.ResetAlliancePosAskReq(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.posPlayerId_ = posPlayerId_;
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        posId_ = java.util.Collections.unmodifiableList(posId_);
        bitField0_ = (bitField0_ & ~0x00000002);
      }
      result.posId_ = posId_;
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
      if (other instanceof pb4server.ResetAlliancePosAskReq) {
        return mergeFrom((pb4server.ResetAlliancePosAskReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.ResetAlliancePosAskReq other) {
      if (other == pb4server.ResetAlliancePosAskReq.getDefaultInstance()) return this;
      if (other.getPosPlayerId() != 0L) {
        setPosPlayerId(other.getPosPlayerId());
      }
      if (!other.posId_.isEmpty()) {
        if (posId_.isEmpty()) {
          posId_ = other.posId_;
          bitField0_ = (bitField0_ & ~0x00000002);
        } else {
          ensurePosIdIsMutable();
          posId_.addAll(other.posId_);
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
      pb4server.ResetAlliancePosAskReq parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.ResetAlliancePosAskReq) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long posPlayerId_ ;
    /**
     * <pre>
     * 被设置职位人ID
     * </pre>
     *
     * <code>int64 posPlayerId = 1;</code>
     */
    public long getPosPlayerId() {
      return posPlayerId_;
    }
    /**
     * <pre>
     * 被设置职位人ID
     * </pre>
     *
     * <code>int64 posPlayerId = 1;</code>
     */
    public Builder setPosPlayerId(long value) {
      
      posPlayerId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 被设置职位人ID
     * </pre>
     *
     * <code>int64 posPlayerId = 1;</code>
     */
    public Builder clearPosPlayerId() {
      
      posPlayerId_ = 0L;
      onChanged();
      return this;
    }

    private java.util.List<java.lang.Integer> posId_ = java.util.Collections.emptyList();
    private void ensurePosIdIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        posId_ = new java.util.ArrayList<java.lang.Integer>(posId_);
        bitField0_ |= 0x00000002;
       }
    }
    /**
     * <pre>
     * 职位
     * </pre>
     *
     * <code>repeated int32 posId = 2;</code>
     */
    public java.util.List<java.lang.Integer>
        getPosIdList() {
      return java.util.Collections.unmodifiableList(posId_);
    }
    /**
     * <pre>
     * 职位
     * </pre>
     *
     * <code>repeated int32 posId = 2;</code>
     */
    public int getPosIdCount() {
      return posId_.size();
    }
    /**
     * <pre>
     * 职位
     * </pre>
     *
     * <code>repeated int32 posId = 2;</code>
     */
    public int getPosId(int index) {
      return posId_.get(index);
    }
    /**
     * <pre>
     * 职位
     * </pre>
     *
     * <code>repeated int32 posId = 2;</code>
     */
    public Builder setPosId(
        int index, int value) {
      ensurePosIdIsMutable();
      posId_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 职位
     * </pre>
     *
     * <code>repeated int32 posId = 2;</code>
     */
    public Builder addPosId(int value) {
      ensurePosIdIsMutable();
      posId_.add(value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 职位
     * </pre>
     *
     * <code>repeated int32 posId = 2;</code>
     */
    public Builder addAllPosId(
        java.lang.Iterable<? extends java.lang.Integer> values) {
      ensurePosIdIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, posId_);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 职位
     * </pre>
     *
     * <code>repeated int32 posId = 2;</code>
     */
    public Builder clearPosId() {
      posId_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000002);
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


    // @@protoc_insertion_point(builder_scope:pb4server.ResetAlliancePosAskReq)
  }

  // @@protoc_insertion_point(class_scope:pb4server.ResetAlliancePosAskReq)
  private static final pb4server.ResetAlliancePosAskReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.ResetAlliancePosAskReq();
  }

  public static pb4server.ResetAlliancePosAskReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ResetAlliancePosAskReq>
      PARSER = new com.google.protobuf.AbstractParser<ResetAlliancePosAskReq>() {
    public ResetAlliancePosAskReq parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new ResetAlliancePosAskReq(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ResetAlliancePosAskReq> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ResetAlliancePosAskReq> getParserForType() {
    return PARSER;
  }

  public pb4server.ResetAlliancePosAskReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

