// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 1111
 * 客户端 -&gt; 服务器
 * 设置帮派科技研发优先级
 * </pre>
 *
 * Protobuf type {@code client2server.SetAllianceResearchFirst}
 */
public  final class SetAllianceResearchFirst extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.SetAllianceResearchFirst)
    SetAllianceResearchFirstOrBuilder {
  // Use SetAllianceResearchFirst.newBuilder() to construct.
  private SetAllianceResearchFirst(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SetAllianceResearchFirst() {
    allianceResearchId_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private SetAllianceResearchFirst(
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
            allianceResearchId_ = input.readInt32();
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
    return pb4client.War2GamePkt.internal_static_client2server_SetAllianceResearchFirst_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_SetAllianceResearchFirst_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.SetAllianceResearchFirst.class, pb4client.SetAllianceResearchFirst.Builder.class);
  }

  private int bitField0_;
  public static final int ALLIANCERESEARCHID_FIELD_NUMBER = 1;
  private int allianceResearchId_;
  /**
   * <pre>
   * 联盟科技ID
   * </pre>
   *
   * <code>required int32 allianceResearchId = 1;</code>
   */
  public boolean hasAllianceResearchId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <pre>
   * 联盟科技ID
   * </pre>
   *
   * <code>required int32 allianceResearchId = 1;</code>
   */
  public int getAllianceResearchId() {
    return allianceResearchId_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasAllianceResearchId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, allianceResearchId_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, allianceResearchId_);
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
    if (!(obj instanceof pb4client.SetAllianceResearchFirst)) {
      return super.equals(obj);
    }
    pb4client.SetAllianceResearchFirst other = (pb4client.SetAllianceResearchFirst) obj;

    boolean result = true;
    result = result && (hasAllianceResearchId() == other.hasAllianceResearchId());
    if (hasAllianceResearchId()) {
      result = result && (getAllianceResearchId()
          == other.getAllianceResearchId());
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
    if (hasAllianceResearchId()) {
      hash = (37 * hash) + ALLIANCERESEARCHID_FIELD_NUMBER;
      hash = (53 * hash) + getAllianceResearchId();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.SetAllianceResearchFirst parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.SetAllianceResearchFirst parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.SetAllianceResearchFirst parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.SetAllianceResearchFirst parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.SetAllianceResearchFirst parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.SetAllianceResearchFirst parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.SetAllianceResearchFirst parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.SetAllianceResearchFirst parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.SetAllianceResearchFirst parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.SetAllianceResearchFirst parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.SetAllianceResearchFirst parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.SetAllianceResearchFirst parseFrom(
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
  public static Builder newBuilder(pb4client.SetAllianceResearchFirst prototype) {
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
   * msgType = 1111
   * 客户端 -&gt; 服务器
   * 设置帮派科技研发优先级
   * </pre>
   *
   * Protobuf type {@code client2server.SetAllianceResearchFirst}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.SetAllianceResearchFirst)
      pb4client.SetAllianceResearchFirstOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_SetAllianceResearchFirst_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_SetAllianceResearchFirst_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.SetAllianceResearchFirst.class, pb4client.SetAllianceResearchFirst.Builder.class);
    }

    // Construct using pb4client.SetAllianceResearchFirst.newBuilder()
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
      allianceResearchId_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_SetAllianceResearchFirst_descriptor;
    }

    public pb4client.SetAllianceResearchFirst getDefaultInstanceForType() {
      return pb4client.SetAllianceResearchFirst.getDefaultInstance();
    }

    public pb4client.SetAllianceResearchFirst build() {
      pb4client.SetAllianceResearchFirst result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.SetAllianceResearchFirst buildPartial() {
      pb4client.SetAllianceResearchFirst result = new pb4client.SetAllianceResearchFirst(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.allianceResearchId_ = allianceResearchId_;
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
      if (other instanceof pb4client.SetAllianceResearchFirst) {
        return mergeFrom((pb4client.SetAllianceResearchFirst)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.SetAllianceResearchFirst other) {
      if (other == pb4client.SetAllianceResearchFirst.getDefaultInstance()) return this;
      if (other.hasAllianceResearchId()) {
        setAllianceResearchId(other.getAllianceResearchId());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasAllianceResearchId()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.SetAllianceResearchFirst parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.SetAllianceResearchFirst) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int allianceResearchId_ ;
    /**
     * <pre>
     * 联盟科技ID
     * </pre>
     *
     * <code>required int32 allianceResearchId = 1;</code>
     */
    public boolean hasAllianceResearchId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <pre>
     * 联盟科技ID
     * </pre>
     *
     * <code>required int32 allianceResearchId = 1;</code>
     */
    public int getAllianceResearchId() {
      return allianceResearchId_;
    }
    /**
     * <pre>
     * 联盟科技ID
     * </pre>
     *
     * <code>required int32 allianceResearchId = 1;</code>
     */
    public Builder setAllianceResearchId(int value) {
      bitField0_ |= 0x00000001;
      allianceResearchId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 联盟科技ID
     * </pre>
     *
     * <code>required int32 allianceResearchId = 1;</code>
     */
    public Builder clearAllianceResearchId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      allianceResearchId_ = 0;
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


    // @@protoc_insertion_point(builder_scope:client2server.SetAllianceResearchFirst)
  }

  // @@protoc_insertion_point(class_scope:client2server.SetAllianceResearchFirst)
  private static final pb4client.SetAllianceResearchFirst DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.SetAllianceResearchFirst();
  }

  public static pb4client.SetAllianceResearchFirst getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<SetAllianceResearchFirst>
      PARSER = new com.google.protobuf.AbstractParser<SetAllianceResearchFirst>() {
    public SetAllianceResearchFirst parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new SetAllianceResearchFirst(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SetAllianceResearchFirst> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SetAllianceResearchFirst> getParserForType() {
    return PARSER;
  }

  public pb4client.SetAllianceResearchFirst getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

