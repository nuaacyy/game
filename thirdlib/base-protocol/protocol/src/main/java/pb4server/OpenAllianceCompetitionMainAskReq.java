// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

/**
 * <pre>
 * 打开联盟总动员主界面
 * </pre>
 *
 * Protobuf type {@code pb4server.OpenAllianceCompetitionMainAskReq}
 */
public  final class OpenAllianceCompetitionMainAskReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.OpenAllianceCompetitionMainAskReq)
    OpenAllianceCompetitionMainAskReqOrBuilder {
  // Use OpenAllianceCompetitionMainAskReq.newBuilder() to construct.
  private OpenAllianceCompetitionMainAskReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private OpenAllianceCompetitionMainAskReq() {
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private OpenAllianceCompetitionMainAskReq(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
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
    return pb4server.InternalPkt.internal_static_pb4server_OpenAllianceCompetitionMainAskReq_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalPkt.internal_static_pb4server_OpenAllianceCompetitionMainAskReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.OpenAllianceCompetitionMainAskReq.class, pb4server.OpenAllianceCompetitionMainAskReq.Builder.class);
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
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof pb4server.OpenAllianceCompetitionMainAskReq)) {
      return super.equals(obj);
    }
    pb4server.OpenAllianceCompetitionMainAskReq other = (pb4server.OpenAllianceCompetitionMainAskReq) obj;

    boolean result = true;
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.OpenAllianceCompetitionMainAskReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.OpenAllianceCompetitionMainAskReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.OpenAllianceCompetitionMainAskReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.OpenAllianceCompetitionMainAskReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.OpenAllianceCompetitionMainAskReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.OpenAllianceCompetitionMainAskReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.OpenAllianceCompetitionMainAskReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.OpenAllianceCompetitionMainAskReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.OpenAllianceCompetitionMainAskReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.OpenAllianceCompetitionMainAskReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.OpenAllianceCompetitionMainAskReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.OpenAllianceCompetitionMainAskReq parseFrom(
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
  public static Builder newBuilder(pb4server.OpenAllianceCompetitionMainAskReq prototype) {
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
   * 打开联盟总动员主界面
   * </pre>
   *
   * Protobuf type {@code pb4server.OpenAllianceCompetitionMainAskReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.OpenAllianceCompetitionMainAskReq)
      pb4server.OpenAllianceCompetitionMainAskReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalPkt.internal_static_pb4server_OpenAllianceCompetitionMainAskReq_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalPkt.internal_static_pb4server_OpenAllianceCompetitionMainAskReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.OpenAllianceCompetitionMainAskReq.class, pb4server.OpenAllianceCompetitionMainAskReq.Builder.class);
    }

    // Construct using pb4server.OpenAllianceCompetitionMainAskReq.newBuilder()
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
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalPkt.internal_static_pb4server_OpenAllianceCompetitionMainAskReq_descriptor;
    }

    public pb4server.OpenAllianceCompetitionMainAskReq getDefaultInstanceForType() {
      return pb4server.OpenAllianceCompetitionMainAskReq.getDefaultInstance();
    }

    public pb4server.OpenAllianceCompetitionMainAskReq build() {
      pb4server.OpenAllianceCompetitionMainAskReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.OpenAllianceCompetitionMainAskReq buildPartial() {
      pb4server.OpenAllianceCompetitionMainAskReq result = new pb4server.OpenAllianceCompetitionMainAskReq(this);
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
      if (other instanceof pb4server.OpenAllianceCompetitionMainAskReq) {
        return mergeFrom((pb4server.OpenAllianceCompetitionMainAskReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.OpenAllianceCompetitionMainAskReq other) {
      if (other == pb4server.OpenAllianceCompetitionMainAskReq.getDefaultInstance()) return this;
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
      pb4server.OpenAllianceCompetitionMainAskReq parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.OpenAllianceCompetitionMainAskReq) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
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


    // @@protoc_insertion_point(builder_scope:pb4server.OpenAllianceCompetitionMainAskReq)
  }

  // @@protoc_insertion_point(class_scope:pb4server.OpenAllianceCompetitionMainAskReq)
  private static final pb4server.OpenAllianceCompetitionMainAskReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.OpenAllianceCompetitionMainAskReq();
  }

  public static pb4server.OpenAllianceCompetitionMainAskReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<OpenAllianceCompetitionMainAskReq>
      PARSER = new com.google.protobuf.AbstractParser<OpenAllianceCompetitionMainAskReq>() {
    public OpenAllianceCompetitionMainAskReq parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new OpenAllianceCompetitionMainAskReq(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<OpenAllianceCompetitionMainAskReq> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<OpenAllianceCompetitionMainAskReq> getParserForType() {
    return PARSER;
  }

  public pb4server.OpenAllianceCompetitionMainAskReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

