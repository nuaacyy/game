// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * Protobuf type {@code client2server.RelationShip}
 */
public  final class RelationShip extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.RelationShip)
    RelationShipOrBuilder {
  // Use RelationShip.newBuilder() to construct.
  private RelationShip(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private RelationShip() {
    setAllianceId_ = 0L;
    relationShipId_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private RelationShip(
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
            setAllianceId_ = input.readInt64();
            break;
          }
          case 16: {
            bitField0_ |= 0x00000002;
            relationShipId_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_RelationShip_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_RelationShip_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.RelationShip.class, pb4client.RelationShip.Builder.class);
  }

  private int bitField0_;
  public static final int SETALLIANCEID_FIELD_NUMBER = 1;
  private long setAllianceId_;
  /**
   * <pre>
   *对方联盟ID
   * </pre>
   *
   * <code>required int64 setAllianceId = 1;</code>
   */
  public boolean hasSetAllianceId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   *对方联盟ID
   * </pre>
   *
   * <code>required int64 setAllianceId = 1;</code>
   */
  public long getSetAllianceId() {
    return setAllianceId_;
  }

  public static final int RELATIONSHIPID_FIELD_NUMBER = 2;
  private int relationShipId_;
  /**
   * <pre>
   *联盟关系ID:1-友好;2-敌对
   * </pre>
   *
   * <code>required int32 relationShipId = 2;</code>
   */
  public boolean hasRelationShipId() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <pre>
   *联盟关系ID:1-友好;2-敌对
   * </pre>
   *
   * <code>required int32 relationShipId = 2;</code>
   */
  public int getRelationShipId() {
    return relationShipId_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasSetAllianceId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasRelationShipId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt64(1, setAllianceId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeInt32(2, relationShipId_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, setAllianceId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, relationShipId_);
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
    if (!(obj instanceof pb4client.RelationShip)) {
      return super.equals(obj);
    }
    pb4client.RelationShip other = (pb4client.RelationShip) obj;

    boolean result = true;
    result = result && (hasSetAllianceId() == other.hasSetAllianceId());
    if (hasSetAllianceId()) {
      result = result && (getSetAllianceId()
          == other.getSetAllianceId());
    }
    result = result && (hasRelationShipId() == other.hasRelationShipId());
    if (hasRelationShipId()) {
      result = result && (getRelationShipId()
          == other.getRelationShipId());
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
    if (hasSetAllianceId()) {
      hash = (37 * hash) + SETALLIANCEID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getSetAllianceId());
    }
    if (hasRelationShipId()) {
      hash = (37 * hash) + RELATIONSHIPID_FIELD_NUMBER;
      hash = (53 * hash) + getRelationShipId();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.RelationShip parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.RelationShip parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.RelationShip parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.RelationShip parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.RelationShip parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.RelationShip parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.RelationShip parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.RelationShip parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.RelationShip parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.RelationShip parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.RelationShip parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.RelationShip parseFrom(
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
  public static Builder newBuilder(pb4client.RelationShip prototype) {
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
   * Protobuf type {@code client2server.RelationShip}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.RelationShip)
      pb4client.RelationShipOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_RelationShip_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_RelationShip_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.RelationShip.class, pb4client.RelationShip.Builder.class);
    }

    // Construct using pb4client.RelationShip.newBuilder()
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
      setAllianceId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      relationShipId_ = 0;
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_RelationShip_descriptor;
    }

    public pb4client.RelationShip getDefaultInstanceForType() {
      return pb4client.RelationShip.getDefaultInstance();
    }

    public pb4client.RelationShip build() {
      pb4client.RelationShip result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.RelationShip buildPartial() {
      pb4client.RelationShip result = new pb4client.RelationShip(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.setAllianceId_ = setAllianceId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.relationShipId_ = relationShipId_;
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
      if (other instanceof pb4client.RelationShip) {
        return mergeFrom((pb4client.RelationShip)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.RelationShip other) {
      if (other == pb4client.RelationShip.getDefaultInstance()) return this;
      if (other.hasSetAllianceId()) {
        setSetAllianceId(other.getSetAllianceId());
      }
      if (other.hasRelationShipId()) {
        setRelationShipId(other.getRelationShipId());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasSetAllianceId()) {
        return false;
      }
      if (!hasRelationShipId()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.RelationShip parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.RelationShip) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long setAllianceId_ ;
    /**
     * <pre>
     *对方联盟ID
     * </pre>
     *
     * <code>required int64 setAllianceId = 1;</code>
     */
    public boolean hasSetAllianceId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     *对方联盟ID
     * </pre>
     *
     * <code>required int64 setAllianceId = 1;</code>
     */
    public long getSetAllianceId() {
      return setAllianceId_;
    }
    /**
     * <pre>
     *对方联盟ID
     * </pre>
     *
     * <code>required int64 setAllianceId = 1;</code>
     */
    public Builder setSetAllianceId(long value) {
      bitField0_ |= 0x00000001;
      setAllianceId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *对方联盟ID
     * </pre>
     *
     * <code>required int64 setAllianceId = 1;</code>
     */
    public Builder clearSetAllianceId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      setAllianceId_ = 0L;
      onChanged();
      return this;
    }

    private int relationShipId_ ;
    /**
     * <pre>
     *联盟关系ID:1-友好;2-敌对
     * </pre>
     *
     * <code>required int32 relationShipId = 2;</code>
     */
    public boolean hasRelationShipId() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *联盟关系ID:1-友好;2-敌对
     * </pre>
     *
     * <code>required int32 relationShipId = 2;</code>
     */
    public int getRelationShipId() {
      return relationShipId_;
    }
    /**
     * <pre>
     *联盟关系ID:1-友好;2-敌对
     * </pre>
     *
     * <code>required int32 relationShipId = 2;</code>
     */
    public Builder setRelationShipId(int value) {
      bitField0_ |= 0x00000002;
      relationShipId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *联盟关系ID:1-友好;2-敌对
     * </pre>
     *
     * <code>required int32 relationShipId = 2;</code>
     */
    public Builder clearRelationShipId() {
      bitField0_ = (bitField0_ & ~0x00000002);
      relationShipId_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.RelationShip)
  }

  // @@protoc_insertion_point(class_scope:client2server.RelationShip)
  private static final pb4client.RelationShip DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.RelationShip();
  }

  public static pb4client.RelationShip getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<RelationShip>
      PARSER = new com.google.protobuf.AbstractParser<RelationShip>() {
    public RelationShip parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new RelationShip(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<RelationShip> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<RelationShip> getParserForType() {
    return PARSER;
  }

  public pb4client.RelationShip getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

