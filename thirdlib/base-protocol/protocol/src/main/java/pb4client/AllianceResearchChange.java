// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 3125
 * 玩家帮派科技变化信息
 * </pre>
 *
 * Protobuf type {@code client2server.AllianceResearchChange}
 */
public  final class AllianceResearchChange extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.AllianceResearchChange)
    AllianceResearchChangeOrBuilder {
  // Use AllianceResearchChange.newBuilder() to construct.
  private AllianceResearchChange(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AllianceResearchChange() {
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AllianceResearchChange(
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
          case 10: {
            pb4client.OpenAllianceResearchInfo.Builder subBuilder = null;
            if (((bitField0_ & 0x00000001) == 0x00000001)) {
              subBuilder = research_.toBuilder();
            }
            research_ = input.readMessage(pb4client.OpenAllianceResearchInfo.PARSER, extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(research_);
              research_ = subBuilder.buildPartial();
            }
            bitField0_ |= 0x00000001;
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
    return pb4client.War2GamePkt.internal_static_client2server_AllianceResearchChange_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_AllianceResearchChange_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.AllianceResearchChange.class, pb4client.AllianceResearchChange.Builder.class);
  }

  private int bitField0_;
  public static final int RESEARCH_FIELD_NUMBER = 1;
  private pb4client.OpenAllianceResearchInfo research_;
  /**
   * <code>required .client2server.OpenAllianceResearchInfo research = 1;</code>
   */
  public boolean hasResearch() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>required .client2server.OpenAllianceResearchInfo research = 1;</code>
   */
  public pb4client.OpenAllianceResearchInfo getResearch() {
    return research_ == null ? pb4client.OpenAllianceResearchInfo.getDefaultInstance() : research_;
  }
  /**
   * <code>required .client2server.OpenAllianceResearchInfo research = 1;</code>
   */
  public pb4client.OpenAllianceResearchInfoOrBuilder getResearchOrBuilder() {
    return research_ == null ? pb4client.OpenAllianceResearchInfo.getDefaultInstance() : research_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    if (!hasResearch()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!getResearch().isInitialized()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeMessage(1, getResearch());
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getResearch());
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
    if (!(obj instanceof pb4client.AllianceResearchChange)) {
      return super.equals(obj);
    }
    pb4client.AllianceResearchChange other = (pb4client.AllianceResearchChange) obj;

    boolean result = true;
    result = result && (hasResearch() == other.hasResearch());
    if (hasResearch()) {
      result = result && getResearch()
          .equals(other.getResearch());
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
    if (hasResearch()) {
      hash = (37 * hash) + RESEARCH_FIELD_NUMBER;
      hash = (53 * hash) + getResearch().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.AllianceResearchChange parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceResearchChange parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceResearchChange parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceResearchChange parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceResearchChange parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.AllianceResearchChange parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.AllianceResearchChange parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.AllianceResearchChange parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.AllianceResearchChange parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.AllianceResearchChange parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.AllianceResearchChange parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.AllianceResearchChange parseFrom(
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
  public static Builder newBuilder(pb4client.AllianceResearchChange prototype) {
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
   * msgType = 3125
   * 玩家帮派科技变化信息
   * </pre>
   *
   * Protobuf type {@code client2server.AllianceResearchChange}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.AllianceResearchChange)
      pb4client.AllianceResearchChangeOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceResearchChange_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceResearchChange_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.AllianceResearchChange.class, pb4client.AllianceResearchChange.Builder.class);
    }

    // Construct using pb4client.AllianceResearchChange.newBuilder()
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
        getResearchFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (researchBuilder_ == null) {
        research_ = null;
      } else {
        researchBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4client.War2GamePkt.internal_static_client2server_AllianceResearchChange_descriptor;
    }

    public pb4client.AllianceResearchChange getDefaultInstanceForType() {
      return pb4client.AllianceResearchChange.getDefaultInstance();
    }

    public pb4client.AllianceResearchChange build() {
      pb4client.AllianceResearchChange result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.AllianceResearchChange buildPartial() {
      pb4client.AllianceResearchChange result = new pb4client.AllianceResearchChange(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      if (researchBuilder_ == null) {
        result.research_ = research_;
      } else {
        result.research_ = researchBuilder_.build();
      }
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
      if (other instanceof pb4client.AllianceResearchChange) {
        return mergeFrom((pb4client.AllianceResearchChange)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.AllianceResearchChange other) {
      if (other == pb4client.AllianceResearchChange.getDefaultInstance()) return this;
      if (other.hasResearch()) {
        mergeResearch(other.getResearch());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      if (!hasResearch()) {
        return false;
      }
      if (!getResearch().isInitialized()) {
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      pb4client.AllianceResearchChange parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.AllianceResearchChange) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private pb4client.OpenAllianceResearchInfo research_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4client.OpenAllianceResearchInfo, pb4client.OpenAllianceResearchInfo.Builder, pb4client.OpenAllianceResearchInfoOrBuilder> researchBuilder_;
    /**
     * <code>required .client2server.OpenAllianceResearchInfo research = 1;</code>
     */
    public boolean hasResearch() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required .client2server.OpenAllianceResearchInfo research = 1;</code>
     */
    public pb4client.OpenAllianceResearchInfo getResearch() {
      if (researchBuilder_ == null) {
        return research_ == null ? pb4client.OpenAllianceResearchInfo.getDefaultInstance() : research_;
      } else {
        return researchBuilder_.getMessage();
      }
    }
    /**
     * <code>required .client2server.OpenAllianceResearchInfo research = 1;</code>
     */
    public Builder setResearch(pb4client.OpenAllianceResearchInfo value) {
      if (researchBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        research_ = value;
        onChanged();
      } else {
        researchBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000001;
      return this;
    }
    /**
     * <code>required .client2server.OpenAllianceResearchInfo research = 1;</code>
     */
    public Builder setResearch(
        pb4client.OpenAllianceResearchInfo.Builder builderForValue) {
      if (researchBuilder_ == null) {
        research_ = builderForValue.build();
        onChanged();
      } else {
        researchBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000001;
      return this;
    }
    /**
     * <code>required .client2server.OpenAllianceResearchInfo research = 1;</code>
     */
    public Builder mergeResearch(pb4client.OpenAllianceResearchInfo value) {
      if (researchBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001) &&
            research_ != null &&
            research_ != pb4client.OpenAllianceResearchInfo.getDefaultInstance()) {
          research_ =
            pb4client.OpenAllianceResearchInfo.newBuilder(research_).mergeFrom(value).buildPartial();
        } else {
          research_ = value;
        }
        onChanged();
      } else {
        researchBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000001;
      return this;
    }
    /**
     * <code>required .client2server.OpenAllianceResearchInfo research = 1;</code>
     */
    public Builder clearResearch() {
      if (researchBuilder_ == null) {
        research_ = null;
        onChanged();
      } else {
        researchBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }
    /**
     * <code>required .client2server.OpenAllianceResearchInfo research = 1;</code>
     */
    public pb4client.OpenAllianceResearchInfo.Builder getResearchBuilder() {
      bitField0_ |= 0x00000001;
      onChanged();
      return getResearchFieldBuilder().getBuilder();
    }
    /**
     * <code>required .client2server.OpenAllianceResearchInfo research = 1;</code>
     */
    public pb4client.OpenAllianceResearchInfoOrBuilder getResearchOrBuilder() {
      if (researchBuilder_ != null) {
        return researchBuilder_.getMessageOrBuilder();
      } else {
        return research_ == null ?
            pb4client.OpenAllianceResearchInfo.getDefaultInstance() : research_;
      }
    }
    /**
     * <code>required .client2server.OpenAllianceResearchInfo research = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        pb4client.OpenAllianceResearchInfo, pb4client.OpenAllianceResearchInfo.Builder, pb4client.OpenAllianceResearchInfoOrBuilder> 
        getResearchFieldBuilder() {
      if (researchBuilder_ == null) {
        researchBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            pb4client.OpenAllianceResearchInfo, pb4client.OpenAllianceResearchInfo.Builder, pb4client.OpenAllianceResearchInfoOrBuilder>(
                getResearch(),
                getParentForChildren(),
                isClean());
        research_ = null;
      }
      return researchBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.AllianceResearchChange)
  }

  // @@protoc_insertion_point(class_scope:client2server.AllianceResearchChange)
  private static final pb4client.AllianceResearchChange DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.AllianceResearchChange();
  }

  public static pb4client.AllianceResearchChange getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<AllianceResearchChange>
      PARSER = new com.google.protobuf.AbstractParser<AllianceResearchChange>() {
    public AllianceResearchChange parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new AllianceResearchChange(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AllianceResearchChange> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AllianceResearchChange> getParserForType() {
    return PARSER;
  }

  public pb4client.AllianceResearchChange getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
