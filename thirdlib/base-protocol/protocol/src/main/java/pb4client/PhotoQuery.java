// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client2server.proto

package pb4client;

/**
 * <pre>
 * msgType = 41
 * 客户端 -&gt; 服务器
 * 打开换头像画面的查询
 * </pre>
 *
 * Protobuf type {@code client2server.PhotoQuery}
 */
public  final class PhotoQuery extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:client2server.PhotoQuery)
    PhotoQueryOrBuilder {
  // Use PhotoQuery.newBuilder() to construct.
  private PhotoQuery(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private PhotoQuery() {
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private PhotoQuery(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
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
    return pb4client.War2GamePkt.internal_static_client2server_PhotoQuery_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4client.War2GamePkt.internal_static_client2server_PhotoQuery_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4client.PhotoQuery.class, pb4client.PhotoQuery.Builder.class);
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
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
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
    if (!(obj instanceof pb4client.PhotoQuery)) {
      return super.equals(obj);
    }
    pb4client.PhotoQuery other = (pb4client.PhotoQuery) obj;

    boolean result = true;
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
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4client.PhotoQuery parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.PhotoQuery parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.PhotoQuery parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.PhotoQuery parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.PhotoQuery parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4client.PhotoQuery parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4client.PhotoQuery parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.PhotoQuery parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.PhotoQuery parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4client.PhotoQuery parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4client.PhotoQuery parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4client.PhotoQuery parseFrom(
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
  public static Builder newBuilder(pb4client.PhotoQuery prototype) {
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
   * msgType = 41
   * 客户端 -&gt; 服务器
   * 打开换头像画面的查询
   * </pre>
   *
   * Protobuf type {@code client2server.PhotoQuery}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:client2server.PhotoQuery)
      pb4client.PhotoQueryOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4client.War2GamePkt.internal_static_client2server_PhotoQuery_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4client.War2GamePkt.internal_static_client2server_PhotoQuery_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4client.PhotoQuery.class, pb4client.PhotoQuery.Builder.class);
    }

    // Construct using pb4client.PhotoQuery.newBuilder()
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
      return pb4client.War2GamePkt.internal_static_client2server_PhotoQuery_descriptor;
    }

    public pb4client.PhotoQuery getDefaultInstanceForType() {
      return pb4client.PhotoQuery.getDefaultInstance();
    }

    public pb4client.PhotoQuery build() {
      pb4client.PhotoQuery result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4client.PhotoQuery buildPartial() {
      pb4client.PhotoQuery result = new pb4client.PhotoQuery(this);
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
      if (other instanceof pb4client.PhotoQuery) {
        return mergeFrom((pb4client.PhotoQuery)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4client.PhotoQuery other) {
      if (other == pb4client.PhotoQuery.getDefaultInstance()) return this;
      this.mergeUnknownFields(other.unknownFields);
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
      pb4client.PhotoQuery parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4client.PhotoQuery) e.getUnfinishedMessage();
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
      return super.setUnknownFields(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:client2server.PhotoQuery)
  }

  // @@protoc_insertion_point(class_scope:client2server.PhotoQuery)
  private static final pb4client.PhotoQuery DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4client.PhotoQuery();
  }

  public static pb4client.PhotoQuery getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  @java.lang.Deprecated public static final com.google.protobuf.Parser<PhotoQuery>
      PARSER = new com.google.protobuf.AbstractParser<PhotoQuery>() {
    public PhotoQuery parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new PhotoQuery(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<PhotoQuery> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<PhotoQuery> getParserForType() {
    return PARSER;
  }

  public pb4client.PhotoQuery getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

