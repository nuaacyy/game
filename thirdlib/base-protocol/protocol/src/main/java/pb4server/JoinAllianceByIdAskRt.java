// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: internal2public.proto

package pb4server;

/**
 * Protobuf type {@code pb4server.JoinAllianceByIdAskRt}
 */
public  final class JoinAllianceByIdAskRt extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:pb4server.JoinAllianceByIdAskRt)
    JoinAllianceByIdAskRtOrBuilder {
  // Use JoinAllianceByIdAskRt.newBuilder() to construct.
  private JoinAllianceByIdAskRt(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private JoinAllianceByIdAskRt() {
    rt_ = 0;
    isCreateJoinInfo_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private JoinAllianceByIdAskRt(
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

            rt_ = input.readInt32();
            break;
          }
          case 16: {

            isCreateJoinInfo_ = input.readInt32();
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
    return pb4server.InternalPkt.internal_static_pb4server_JoinAllianceByIdAskRt_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pb4server.InternalPkt.internal_static_pb4server_JoinAllianceByIdAskRt_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pb4server.JoinAllianceByIdAskRt.class, pb4server.JoinAllianceByIdAskRt.Builder.class);
  }

  public static final int RT_FIELD_NUMBER = 1;
  private int rt_;
  /**
   * <code>int32 rt = 1;</code>
   */
  public int getRt() {
    return rt_;
  }

  public static final int ISCREATEJOININFO_FIELD_NUMBER = 2;
  private int isCreateJoinInfo_;
  /**
   * <pre>
   * 是否需要创建申请记录
   * </pre>
   *
   * <code>int32 isCreateJoinInfo = 2;</code>
   */
  public int getIsCreateJoinInfo() {
    return isCreateJoinInfo_;
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
    if (rt_ != 0) {
      output.writeInt32(1, rt_);
    }
    if (isCreateJoinInfo_ != 0) {
      output.writeInt32(2, isCreateJoinInfo_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (rt_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, rt_);
    }
    if (isCreateJoinInfo_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, isCreateJoinInfo_);
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
    if (!(obj instanceof pb4server.JoinAllianceByIdAskRt)) {
      return super.equals(obj);
    }
    pb4server.JoinAllianceByIdAskRt other = (pb4server.JoinAllianceByIdAskRt) obj;

    boolean result = true;
    result = result && (getRt()
        == other.getRt());
    result = result && (getIsCreateJoinInfo()
        == other.getIsCreateJoinInfo());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + RT_FIELD_NUMBER;
    hash = (53 * hash) + getRt();
    hash = (37 * hash) + ISCREATEJOININFO_FIELD_NUMBER;
    hash = (53 * hash) + getIsCreateJoinInfo();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pb4server.JoinAllianceByIdAskRt parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.JoinAllianceByIdAskRt parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.JoinAllianceByIdAskRt parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.JoinAllianceByIdAskRt parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.JoinAllianceByIdAskRt parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pb4server.JoinAllianceByIdAskRt parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pb4server.JoinAllianceByIdAskRt parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.JoinAllianceByIdAskRt parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.JoinAllianceByIdAskRt parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pb4server.JoinAllianceByIdAskRt parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pb4server.JoinAllianceByIdAskRt parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pb4server.JoinAllianceByIdAskRt parseFrom(
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
  public static Builder newBuilder(pb4server.JoinAllianceByIdAskRt prototype) {
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
   * Protobuf type {@code pb4server.JoinAllianceByIdAskRt}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:pb4server.JoinAllianceByIdAskRt)
      pb4server.JoinAllianceByIdAskRtOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pb4server.InternalPkt.internal_static_pb4server_JoinAllianceByIdAskRt_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pb4server.InternalPkt.internal_static_pb4server_JoinAllianceByIdAskRt_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pb4server.JoinAllianceByIdAskRt.class, pb4server.JoinAllianceByIdAskRt.Builder.class);
    }

    // Construct using pb4server.JoinAllianceByIdAskRt.newBuilder()
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
      rt_ = 0;

      isCreateJoinInfo_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pb4server.InternalPkt.internal_static_pb4server_JoinAllianceByIdAskRt_descriptor;
    }

    public pb4server.JoinAllianceByIdAskRt getDefaultInstanceForType() {
      return pb4server.JoinAllianceByIdAskRt.getDefaultInstance();
    }

    public pb4server.JoinAllianceByIdAskRt build() {
      pb4server.JoinAllianceByIdAskRt result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public pb4server.JoinAllianceByIdAskRt buildPartial() {
      pb4server.JoinAllianceByIdAskRt result = new pb4server.JoinAllianceByIdAskRt(this);
      result.rt_ = rt_;
      result.isCreateJoinInfo_ = isCreateJoinInfo_;
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
      if (other instanceof pb4server.JoinAllianceByIdAskRt) {
        return mergeFrom((pb4server.JoinAllianceByIdAskRt)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pb4server.JoinAllianceByIdAskRt other) {
      if (other == pb4server.JoinAllianceByIdAskRt.getDefaultInstance()) return this;
      if (other.getRt() != 0) {
        setRt(other.getRt());
      }
      if (other.getIsCreateJoinInfo() != 0) {
        setIsCreateJoinInfo(other.getIsCreateJoinInfo());
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
      pb4server.JoinAllianceByIdAskRt parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (pb4server.JoinAllianceByIdAskRt) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int rt_ ;
    /**
     * <code>int32 rt = 1;</code>
     */
    public int getRt() {
      return rt_;
    }
    /**
     * <code>int32 rt = 1;</code>
     */
    public Builder setRt(int value) {
      
      rt_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 rt = 1;</code>
     */
    public Builder clearRt() {
      
      rt_ = 0;
      onChanged();
      return this;
    }

    private int isCreateJoinInfo_ ;
    /**
     * <pre>
     * 是否需要创建申请记录
     * </pre>
     *
     * <code>int32 isCreateJoinInfo = 2;</code>
     */
    public int getIsCreateJoinInfo() {
      return isCreateJoinInfo_;
    }
    /**
     * <pre>
     * 是否需要创建申请记录
     * </pre>
     *
     * <code>int32 isCreateJoinInfo = 2;</code>
     */
    public Builder setIsCreateJoinInfo(int value) {
      
      isCreateJoinInfo_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 是否需要创建申请记录
     * </pre>
     *
     * <code>int32 isCreateJoinInfo = 2;</code>
     */
    public Builder clearIsCreateJoinInfo() {
      
      isCreateJoinInfo_ = 0;
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


    // @@protoc_insertion_point(builder_scope:pb4server.JoinAllianceByIdAskRt)
  }

  // @@protoc_insertion_point(class_scope:pb4server.JoinAllianceByIdAskRt)
  private static final pb4server.JoinAllianceByIdAskRt DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pb4server.JoinAllianceByIdAskRt();
  }

  public static pb4server.JoinAllianceByIdAskRt getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<JoinAllianceByIdAskRt>
      PARSER = new com.google.protobuf.AbstractParser<JoinAllianceByIdAskRt>() {
    public JoinAllianceByIdAskRt parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new JoinAllianceByIdAskRt(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<JoinAllianceByIdAskRt> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<JoinAllianceByIdAskRt> getParserForType() {
    return PARSER;
  }

  public pb4server.JoinAllianceByIdAskRt getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

