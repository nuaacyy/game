// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 1259
 * 客户端 -&gt; 服务器
 * 集结成员遣返回家
 * </pre>
 *
 * Protobuf type {@code client2server.SendMassMemberHome}
 */
public  final class SendMassMemberHome extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.SendMassMemberHome)
    SendMassMemberHomeOrBuilder {
  // Use SendMassMemberHome.newBuilder() to construct.
  private SendMassMemberHome(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SendMassMemberHome() {
    massId_ = 0L;
    tarPlayerIds_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private SendMassMemberHome(
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
            massId_ = input.readInt64();
            break;
          }
          case 16: {
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
              tarPlayerIds_ = new java.util.ArrayList<java.lang.Long>();
              mutable_bitField0_ |= 0x00000002;
            }
            tarPlayerIds_.add(input.readInt64());
            break;
          }
          case 18: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000002) == 0x00000002) && input.getBytesUntilLimit() > 0) {
              tarPlayerIds_ = new java.util.ArrayList<java.lang.Long>();
              mutable_bitField0_ |= 0x00000002;
            }
            while (input.getBytesUntilLimit() > 0) {
              tarPlayerIds_.add(input.readInt64());
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
        tarPlayerIds_ = java.util.Collections.unmodifiableList(tarPlayerIds_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4client.War2GamePkt.internal_static_client2server_SendMassMemberHome_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_SendMassMemberHome_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.SendMassMemberHome.class, pb4client.SendMassMemberHome.Builder.class);
  }

  private int bitField0_;
  public static final int MASSID_FIELD_NUMBER = 1;
  private long massId_;
  /**
   * <code>required int64 massId = 1;</code>
   */
  public boolean hasMassId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>required int64 massId = 1;</code>
   */
  public long getMassId() {
    return massId_;
  }

  public static final int TARPLAYERIDS_FIELD_NUMBER = 2;
  private java.util.List<java.lang.Long> tarPlayerIds_;
  /**
   * <code>repeated int64 tarPlayerIds = 2;</code>
   */
  public java.util.List<java.lang.Long>
      getTarPlayerIdsList() {
    return tarPlayerIds_;
  }
  /**
   * <code>repeated int64 tarPlayerIds = 2;</code>
   */
  public int getTarPlayerIdsCount() {
    return tarPlayerIds_.size();
  }
  /**
   * <code>repeated int64 tarPlayerIds = 2;</code>
   */
  public long getTarPlayerIds(int index) {
    return tarPlayerIds_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasMassId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt64(1, massId_);
    }
    for (int i = 0; i < tarPlayerIds_.size(); i++) {
      output.writeInt64(2, tarPlayerIds_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, massId_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < tarPlayerIds_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeInt64SizeNoTag(tarPlayerIds_.get(i));
      }
      size += dataSize;
      size += 1 * getTarPlayerIdsList().size();
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
    if (!(obj instanceof pb4client.SendMassMemberHome)) {
      return super.equals(obj);
    }
    pb4client.SendMassMemberHome other = (pb4client.SendMassMemberHome) obj;

    boolean result = true;
    result = result && (hasMassId() == other.hasMassId());
    if (hasMassId()) {
      result = result && (getMassId()
          == other.getMassId());
    }
    result = result && getTarPlayerIdsList()
        .equals(other.getTarPlayerIdsList());
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
    if (hasMassId()) {
      hash = (37 * hash) + MASSID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getMassId());
    }
    if (getTarPlayerIdsCount() > 0) {
      hash = (37 * hash) + TARPLAYERIDS_FIELD_NUMBER;
      hash = (53 * hash) + getTarPlayerIdsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.SendMassMemberHome parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.SendMassMemberHome parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.SendMassMemberHome parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.SendMassMemberHome parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.SendMassMemberHome parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.SendMassMemberHome parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.SendMassMemberHome parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.SendMassMemberHome parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.SendMassMemberHome parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.SendMassMemberHome parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.SendMassMemberHome parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.SendMassMemberHome parseFrom(
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
  public static Builder newBuilder(pb4client.SendMassMemberHome prototype) {
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
   * msgType = 1259
   * 客户端 -&gt; 服务器
   * 集结成员遣返回家
   * </pre>
   *
   * Protobuf type {@code client2server.SendMassMemberHome}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.SendMassMemberHome)
      pb4client.SendMassMemberHomeOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_SendMassMemberHome_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_SendMassMemberHome_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.SendMassMemberHome.class, pb4client.SendMassMemberHome.Builder.class);
    }

    // Construct using pb4client.SendMassMemberHome.newBuilder()
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
      massId_ = 0L;
      bitField0_ = (bitField0_ & ~0x00000001);
      tarPlayerIds_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_SendMassMemberHome_descriptor;
    }

    public pb4client.SendMassMemberHome getDefaultInstanceForType() {
      return pb4client.SendMassMemberHome.getDefaultInstance();
    }

    public pb4client.SendMassMemberHome build() {
      pb4client.SendMassMemberHome result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.SendMassMemberHome buildPartial() {
      pb4client.SendMassMemberHome result = new pb4client.SendMassMemberHome(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.massId_ = massId_;
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        tarPlayerIds_ = java.util.Collections.unmodifiableList(tarPlayerIds_);
        bitField0_ = (bitField0_ & ~0x00000002);
      }
      result.tarPlayerIds_ = tarPlayerIds_;
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
      if (other instanceof pb4client.SendMassMemberHome) {
        return mergeFrom((pb4client.SendMassMemberHome)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.SendMassMemberHome other) {
      if (other == pb4client.SendMassMemberHome.getDefaultInstance()) return this;
      if (other.hasMassId()) {
        setMassId(other.getMassId());
      }
      if (!other.tarPlayerIds_.isEmpty()) {
        if (tarPlayerIds_.isEmpty()) {
          tarPlayerIds_ = other.tarPlayerIds_;
          bitField0_ = (bitField0_ & ~0x00000002);
        } else {
          ensureTarPlayerIdsIsMutable();
          tarPlayerIds_.addAll(other.tarPlayerIds_);
        }
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasMassId()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.SendMassMemberHome parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.SendMassMemberHome) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long massId_ ;
    /**
     * <code>required int64 massId = 1;</code>
     */
    public boolean hasMassId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int64 massId = 1;</code>
     */
    public long getMassId() {
      return massId_;
    }
    /**
     * <code>required int64 massId = 1;</code>
     */
    public Builder setMassId(long value) {
      bitField0_ |= 0x00000001;
      massId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int64 massId = 1;</code>
     */
    public Builder clearMassId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      massId_ = 0L;
      onChanged();
      return this;
    }

    private java.util.List<java.lang.Long> tarPlayerIds_ = java.util.Collections.emptyList();
    private void ensureTarPlayerIdsIsMutable() {
      if (!((bitField0_ & 0x00000002) == 0x00000002)) {
        tarPlayerIds_ = new java.util.ArrayList<java.lang.Long>(tarPlayerIds_);
        bitField0_ |= 0x00000002;
       }
    }
    /**
     * <code>repeated int64 tarPlayerIds = 2;</code>
     */
    public java.util.List<java.lang.Long>
        getTarPlayerIdsList() {
      return java.util.Collections.unmodifiableList(tarPlayerIds_);
    }
    /**
     * <code>repeated int64 tarPlayerIds = 2;</code>
     */
    public int getTarPlayerIdsCount() {
      return tarPlayerIds_.size();
    }
    /**
     * <code>repeated int64 tarPlayerIds = 2;</code>
     */
    public long getTarPlayerIds(int index) {
      return tarPlayerIds_.get(index);
    }
    /**
     * <code>repeated int64 tarPlayerIds = 2;</code>
     */
    public Builder setTarPlayerIds(
        int index, long value) {
      ensureTarPlayerIdsIsMutable();
      tarPlayerIds_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int64 tarPlayerIds = 2;</code>
     */
    public Builder addTarPlayerIds(long value) {
      ensureTarPlayerIdsIsMutable();
      tarPlayerIds_.add(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int64 tarPlayerIds = 2;</code>
     */
    public Builder addAllTarPlayerIds(
        java.lang.Iterable<? extends java.lang.Long> values) {
      ensureTarPlayerIdsIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, tarPlayerIds_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int64 tarPlayerIds = 2;</code>
     */
    public Builder clearTarPlayerIds() {
      tarPlayerIds_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000002);
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


    // @@protoc_insertion_point(builder_scope:client2server.SendMassMemberHome)
  }

  // @@protoc_insertion_point(class_scope:client2server.SendMassMemberHome)
  private static final pb4client.SendMassMemberHome DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.SendMassMemberHome();
  }

  public static pb4client.SendMassMemberHome getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<SendMassMemberHome>
      PARSER = new com.google.protobuf.AbstractParser<SendMassMemberHome>() {
    public SendMassMemberHome parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new SendMassMemberHome(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SendMassMemberHome> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SendMassMemberHome> getParserForType() {
    return PARSER;
  }

  public pb4client.SendMassMemberHome getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

