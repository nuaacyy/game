// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2world.proto

package pb4server;

/**
 * <pre>
 * 发布联盟邮件主题之后的推送
 * </pre>
 *
 * Protobuf type {@code pb4server.DealAfterAlliancePublishTopicTell}
 */
public  final class DealAfterAlliancePublishTopicTell extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.DealAfterAlliancePublishTopicTell)
    DealAfterAlliancePublishTopicTellOrBuilder {
  // Use DealAfterAlliancePublishTopicTell.newBuilder() to construct.
  private DealAfterAlliancePublishTopicTell(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private DealAfterAlliancePublishTopicTell() {
    allianceId_ = 0L;
    aTopicId_ = 0L;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private DealAfterAlliancePublishTopicTell(
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
          case 16: {

            aTopicId_ = input.readInt64();
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
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pb4server.InternalWkt.internal_static_pb4server_DealAfterAlliancePublishTopicTell_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalWkt.internal_static_pb4server_DealAfterAlliancePublishTopicTell_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.DealAfterAlliancePublishTopicTell.class, pb4server.DealAfterAlliancePublishTopicTell.Builder.class);
  }

  public static final int ALLIANCEID_FIELD_NUMBER = 1;
  private long allianceId_;
  /**
   * <code>int64 allianceId = 1;</code>
   */
  public long getAllianceId() {
    return allianceId_;
  }

  public static final int ATOPICID_FIELD_NUMBER = 2;
  private long aTopicId_;
  /**
   * <code>int64 aTopicId = 2;</code>
   */
  public long getATopicId() {
    return aTopicId_;
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
    if (allianceId_ != 0L) {
      output.writeInt64(1, allianceId_);
    }
    if (aTopicId_ != 0L) {
      output.writeInt64(2, aTopicId_);
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
    if (aTopicId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, aTopicId_);
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
    if (!(obj instanceof pb4server.DealAfterAlliancePublishTopicTell)) {
      return super.equals(obj);
    }
    pb4server.DealAfterAlliancePublishTopicTell other = (pb4server.DealAfterAlliancePublishTopicTell) obj;

    boolean result = true;
    result = result && (getAllianceId()
        == other.getAllianceId());
    result = result && (getATopicId()
        == other.getATopicId());
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
    hash = (37 * hash) + ATOPICID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getATopicId());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.DealAfterAlliancePublishTopicTell parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.DealAfterAlliancePublishTopicTell parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.DealAfterAlliancePublishTopicTell parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.DealAfterAlliancePublishTopicTell parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.DealAfterAlliancePublishTopicTell parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.DealAfterAlliancePublishTopicTell parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.DealAfterAlliancePublishTopicTell parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.DealAfterAlliancePublishTopicTell parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.DealAfterAlliancePublishTopicTell parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.DealAfterAlliancePublishTopicTell parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.DealAfterAlliancePublishTopicTell parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.DealAfterAlliancePublishTopicTell parseFrom(
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
  public static Builder newBuilder(pb4server.DealAfterAlliancePublishTopicTell prototype) {
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
   * 发布联盟邮件主题之后的推送
   * </pre>
   *
   * Protobuf type {@code pb4server.DealAfterAlliancePublishTopicTell}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.DealAfterAlliancePublishTopicTell)
      pb4server.DealAfterAlliancePublishTopicTellOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalWkt.internal_static_pb4server_DealAfterAlliancePublishTopicTell_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalWkt.internal_static_pb4server_DealAfterAlliancePublishTopicTell_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.DealAfterAlliancePublishTopicTell.class, pb4server.DealAfterAlliancePublishTopicTell.Builder.class);
    }

    // Construct using pb4server.DealAfterAlliancePublishTopicTell.newBuilder()
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

      aTopicId_ = 0L;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalWkt.internal_static_pb4server_DealAfterAlliancePublishTopicTell_descriptor;
    }

    public pb4server.DealAfterAlliancePublishTopicTell getDefaultInstanceForType() {
      return pb4server.DealAfterAlliancePublishTopicTell.getDefaultInstance();
    }

    public pb4server.DealAfterAlliancePublishTopicTell build() {
      pb4server.DealAfterAlliancePublishTopicTell result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.DealAfterAlliancePublishTopicTell buildPartial() {
      pb4server.DealAfterAlliancePublishTopicTell result = new pb4server.DealAfterAlliancePublishTopicTell(this);
      result.allianceId_ = allianceId_;
      result.aTopicId_ = aTopicId_;
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
      if (other instanceof pb4server.DealAfterAlliancePublishTopicTell) {
        return mergeFrom((pb4server.DealAfterAlliancePublishTopicTell)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.DealAfterAlliancePublishTopicTell other) {
      if (other == pb4server.DealAfterAlliancePublishTopicTell.getDefaultInstance()) return this;
      if (other.getAllianceId() != 0L) {
        setAllianceId(other.getAllianceId());
      }
      if (other.getATopicId() != 0L) {
        setATopicId(other.getATopicId());
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
      pb4server.DealAfterAlliancePublishTopicTell parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.DealAfterAlliancePublishTopicTell) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long allianceId_ ;
    /**
     * <code>int64 allianceId = 1;</code>
     */
    public long getAllianceId() {
      return allianceId_;
    }
    /**
     * <code>int64 allianceId = 1;</code>
     */
    public Builder setAllianceId(long value) {
      
      allianceId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 allianceId = 1;</code>
     */
    public Builder clearAllianceId() {
      
      allianceId_ = 0L;
      onChanged();
      return this;
    }

    private long aTopicId_ ;
    /**
     * <code>int64 aTopicId = 2;</code>
     */
    public long getATopicId() {
      return aTopicId_;
    }
    /**
     * <code>int64 aTopicId = 2;</code>
     */
    public Builder setATopicId(long value) {
      
      aTopicId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 aTopicId = 2;</code>
     */
    public Builder clearATopicId() {
      
      aTopicId_ = 0L;
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


    // @@protoc_insertion_point(builder_scope:pb4server.DealAfterAlliancePublishTopicTell)
  }

  // @@protoc_insertion_point(class_scope:pb4server.DealAfterAlliancePublishTopicTell)
  private static final pb4server.DealAfterAlliancePublishTopicTell DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.DealAfterAlliancePublishTopicTell();
  }

  public static pb4server.DealAfterAlliancePublishTopicTell getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<DealAfterAlliancePublishTopicTell>
      PARSER = new com.google.protobuf.AbstractParser<DealAfterAlliancePublishTopicTell>() {
    public DealAfterAlliancePublishTopicTell parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new DealAfterAlliancePublishTopicTell(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<DealAfterAlliancePublishTopicTell> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<DealAfterAlliancePublishTopicTell> getParserForType() {
    return PARSER;
  }

  public pb4server.DealAfterAlliancePublishTopicTell getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

